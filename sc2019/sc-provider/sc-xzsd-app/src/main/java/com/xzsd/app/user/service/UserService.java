package com.xzsd.app.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.store.dao.StoreDao;
import com.xzsd.app.user.dao.UserDao;
import com.xzsd.app.user.entity.User;
import com.xzsd.app.user.entity.UserVo;
import com.xzsd.app.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
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

    @Resource
    private StoreDao storeDao;

    private static final int Shopowner = 1;

    private static final int Client = 2;

    private static final int Driver = 3;

    /**
     * demo 新增用户(注册)
     * @param user
     * @return
     * @author haoao
     * @Date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addUser(User user){
        //获取用户编号
        user.setUserCode(StringUtil.getCommonCode(2));
        user.setCreateUser(user.getUserCode());
        //检验用户账户是否存在
        int cnt = userDao.countUserAcct(user);
        if(cnt != 0){
            return AppResponse.bizError("账户已存在,请重新输入");
        }
        //角色2：客户
        user.setRole(2);
        //密码加密
        String pwd = PasswordUtils.generatePassword(user.getUserPassword());
        user.setUserPassword(pwd);
        int count = userDao.saveUser(user);
        String invitationCode = user.getInvitationCode();
        //当邀请码不为空时，检验邀请码是否存在
        if (invitationCode != null){
            Integer inviteCodeCnt = storeDao.findInviteCode(invitationCode);
            if (inviteCodeCnt == 0){
                return AppResponse.bizError("邀请码不存在,请重试");
            }
        }
        int num = userDao.addClient(user.getUserCode(),invitationCode);
        if (count == 0 || num == 0){
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
     * demo 修改用户密码
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUser(User user){
        //校验密码
        UserVo userByUserCode = userDao.getUserByUserCode(user.getUserCode());
        String userPassword = userByUserCode.getUserPassword();
        //校验用户输入的原密码
        if (PasswordUtils.passwordMacth(user.getOldPassWord(),userPassword)){
            String pwd = PasswordUtils.generatePassword(user.getNewPassWord());
            user.setNewPassWord(pwd);
            int cnt = userDao.updateUserPwd(user);
            if (cnt == 0){
                return AppResponse.bizError("修改失败,请重试");
            }else {
                return AppResponse.success("修改成功");
            }
        }else {
            return AppResponse.bizError("原密码输入错误,请重试");
        }
    }

    /**
     * 修改用户绑定店铺邀请码
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateInvitationCode(User user){
        //检测邀请码是否存在
        Integer inviteCodeCnt = storeDao.findInviteCode(user.getInvitationCode());
        if (inviteCodeCnt == 0){
            return AppResponse.bizError("邀请码不存在,请重试");
        }else {
            int cnt = userDao.updateClientInvCode(user.getUserCode(), user.getInvitationCode());
            if (cnt == 0){
                return AppResponse.bizError("修改失败,请重试");
            }else {
                return AppResponse.success("修改成功");
            }
        }
    }

    /**
     * 查询公用个人信息
     * @param userCode
     * @return
     */
    public AppResponse selectInfo(String userCode){
        UserVo userVoInfo = userDao.selectInfo(userCode);
        Integer role = userVoInfo.getRole();
        if (role == Shopowner){
            //拼接详细地址
            String province = userVoInfo.getProvince();
            String city = userVoInfo.getCity();
            String area = userVoInfo.getArea();
            String address = userVoInfo.getAddress();
            userVoInfo.setAddress(province+city+area+address);
            return AppResponse.success("查询成功",userVoInfo);
        }else {
            return AppResponse.success("查询成功",userVoInfo);
        }
    }
}
