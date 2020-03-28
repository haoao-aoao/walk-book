package com.neusoft.demo.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.user.dao.UserDao;
import com.neusoft.demo.user.entity.User;
import com.neusoft.demo.user.entity.UserClient;
import com.neusoft.demo.user.entity.UserVo;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 业务逻辑实现层
 * @author haoao
 * @date 2020-03-24
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    /**
     * demo 管理端登录
     * @param userAcct
     * @param userPassword
     * @return
     */
    public AppResponse sysLogin(String userAcct,String userPassword){
        User user = userDao.sysLogin(userAcct, userPassword);
        if (user == null){
            return AppResponse.bizError("登录失败");
        }
        int role = user.getRole();
        if (role > 1){
            return AppResponse.bizError("登录失败，权限不够");
        }
        return AppResponse.success("登录成功",user);
    }

    /**
     * demo 新增用户
     * @param user
     * @return
     * @author haoao
     * @Date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addUser(User user){
        //获取用户编号
        user.setUserCode(StringUtil.getCommonCode(2));
        //检验用户账户是否存在
        int cnt = userDao.countUserAcct(user);
        if(cnt != 0){
            return AppResponse.bizError("账户已存在,请重新输入");
        }
        int count = userDao.saveUser(user);
        if (count == 0){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增成功");
    }

    /**
     * demo 查询用户详情
     * @param userCode
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    public AppResponse getUserByUserCode(String userCode) {
        UserVo user = userDao.getUserByUserCode(userCode);
        return AppResponse.success("查询成功！",user);
    }

    /**
     * demo 查询用户信息（分页）
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    public AppResponse listUsersByPage(User user){
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<UserVo> users = userDao.listUsersByPage(user);
        PageInfo<UserVo> userPageInfo = new PageInfo<>(users);
        return AppResponse.success("用户信息列表查询成功",userPageInfo);
    }

    /**
     * demo 修改用户信息
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUser(User user){
        //检验用户账户是否存在
        int cnt = userDao.countUserAcct(user);
        if(cnt != 0){
            return AppResponse.bizError("账户已存在,请重新输入");
        }
        int count = userDao.updateUser(user);
        if (count == 0){
            return AppResponse.bizError("数据有更新，请重试");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * demo 删除用户
     * @param userCode
     * @param lastModfiedBy
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userCode,String lastModfiedBy){
        List<String> listCode = Arrays.asList(userCode.split(","));
        userDao.deleteUser(listCode,lastModfiedBy);
        return AppResponse.success("删除成功");
    }

    /**
     * demo 查询客户信息（分页）
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    public AppResponse listClientByPage(User user){
        PageHelper.startPage(user.getPageNum(),user.getPageSize());
        List<UserClient> users = userDao.listClientByPage(user);
        PageInfo<UserClient> userPageInfo = new PageInfo<>(users);
        return AppResponse.success("客户信息列表查询成功",userPageInfo);
    }
}
