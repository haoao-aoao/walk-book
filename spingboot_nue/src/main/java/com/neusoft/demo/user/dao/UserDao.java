package com.neusoft.demo.user.dao;

import com.neusoft.demo.user.entity.User;
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
     * 管理端登录
     * @param userAcct
     * @param userPassword
     * @return
     */
    User sysLogin(@Param("userAcct") String userAcct,@Param("userPassword") String userPassword);

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
    List<User> listUsersByPage(User user);

    /**
     * 删除用户信息
     * @param listCode 选中的用户编号集合
     * @param lastModfiedBy 更新人
     * @return
     */
    int deleteUser(@Param("listCode") List<String> listCode,@Param("lastModfiedBy") String lastModfiedBy);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 修改结果
     */
    int updateUser(User user);

    /**
     * 查询用户信息
     * @param userCode 用户编号
     * @return 修改结果
     */
    User getUserByUserCode(@Param("userCode") String userCode);
}
