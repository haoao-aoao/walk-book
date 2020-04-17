package com.xzsd.pc.user.dao;

import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.entity.UserClient;
import com.xzsd.pc.user.entity.UserVo;
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
     * 删除用户信息
     * @param listCode 选中的用户编号集合
     * @param lastModfiedBy 更新人
     * @return
     */
    int deleteUser(@Param("listCode") List<String> listCode, @Param("lastModfiedBy") String lastModfiedBy);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 修改结果
     */
    int updateUser(User user);

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
     * 查询顶部栏信息
     * @param userCode 用户编号
     * @return
     */
    UserVo selectTop(@Param("userCode") String userCode);
}
