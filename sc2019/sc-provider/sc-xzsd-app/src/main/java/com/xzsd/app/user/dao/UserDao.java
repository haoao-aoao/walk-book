package com.xzsd.app.user.dao;

import com.xzsd.app.user.entity.User;
import com.xzsd.app.user.entity.UserClient;
import com.xzsd.app.user.entity.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserDao
 * @Description User
 * @Author haoao
 * @Date 2020-03-24
 */
@Mapper
public interface UserDao {

    /**
     * 统计用户账号数量
     * @param user 用户信息
     * @return
     */
    int countUserAcct(User user);

    /**
     * 新增客户绑定店铺邀请码
     * @param userCode
     * @param invitationCode
     * @return
     */
    int addClient(@Param("userCode") String userCode,@Param("invitationCode") String invitationCode);

    /**
     * 新增用户
     * @param user 用户信息
     * @return
     */
    int saveUser(User user);

    /**
     * 获取所有用户信息
     * @param user 用户信息
     * @return 所有用户信息
     */
    List<UserVo> listUsersByPage(User user);

    /**
     * 修改用户密码
     * @param user 用户信息
     * @return 修改结果
     */
    int updateUserPwd(User user);

    /**
     * 修改用户绑定的门店邀请码
     * @param userCode
     * @param invitationCode
     * @return
     */
    int updateClientInvCode(@Param("userCode") String userCode,@Param("invitationCode") String invitationCode);

    /**
     * 查询用户信息
     * @param userCode 用户编号
     * @return
     */
    UserVo getUserByUserCode(@Param("userCode") String userCode);

    /**
     * 获取客户信息
     * @param user 用户信息
     * @return 所有用户信息
     */
    List<UserClient> listClientByPage(User user);

    /**
     * 对应门店邀请码获取客户信息
     * @param user 用户信息
     * @return 所有用户信息
     */
    List<UserClient> listStoreClientByPage(User user);

    /**
     * 根据客户绑定的邀请码查询店铺编号
     * @param userCode
     * @return
     */
    String findStoreCode(String userCode);

    /**
     * 公用查询个人信息
     * @return
     */
    UserVo selectInfo(String userCode);
}
