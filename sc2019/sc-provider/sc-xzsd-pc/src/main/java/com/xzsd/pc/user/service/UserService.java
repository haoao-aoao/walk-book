package com.xzsd.pc.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.Store;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.entity.UserClient;
import com.xzsd.pc.user.entity.UserVo;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.PasswordUtils;
import com.xzsd.pc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * 业务逻辑实现层
 * @author haoao
 * @date 2020-03-24
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private StoreDao storeDao;

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
        //检验用户数据有无重复
        int cnt = userDao.countUserAcct(user);
        //密码加密
        String pwd = PasswordUtils.generatePassword(user.getUserPassword());
        user.setUserPassword(pwd);
        if(cnt != 0){
            return AppResponse.bizError("数据有重复,请重新输入");
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
        List<UserVo> users = userDao.listUsersByPage(user);
        return AppResponse.success("用户信息列表查询成功",getPageInfo(users));
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
        //密码加密
        String pwd = PasswordUtils.generatePassword(user.getUserPassword());
        user.setUserPassword(pwd);
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
    public AppResponse listClient(User user){
//        PageHelper.startPage(user.getPageNum(),user.getPageSize());
//        List<UserClient> users = userDao.listClientByPage(user);
//        PageInfo<UserClient> userPageInfo = new PageInfo<>(users);
//        return AppResponse.success("客户信息列表查询成功",userPageInfo);
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        UserVo iuser = userDao.getUserByUserCode(currentUserId);
        Integer role = iuser.getRole();
        if (role == 0){
            PageHelper.startPage(user.getPageNum(),user.getPageSize());
            List<UserClient> userClients = userDao.listClientByPage(user);
            PageInfo<UserClient> userPageInfo = new PageInfo<>(userClients);
            return AppResponse.success("管理员显示全部客户",userPageInfo);
        }else {
            //获取当前人门店邀请码
            Store store = storeDao.selectInviteCode(currentUserId);
            String invitationCode = store.getInvitationCode();
            user.setInvitationCode(invitationCode);
            PageHelper.startPage(user.getPageNum(),user.getPageSize());
            List<UserClient> userClients = userDao.listStoreClientByPage(user);
            PageInfo<UserClient> userPageInfo = new PageInfo<>(userClients);
            return AppResponse.success("店长对应的客户",userPageInfo);
        }
    }

    /**
     * 顶部栏信息
     * @param userCode
     * @return
     */
    public AppResponse selectTop(String userCode){
        UserVo userVo = userDao.selectTop(userCode);
        return AppResponse.success("查询成功",userVo);
    }
}
