package com.xzsd.pc.user.controller;

import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.service.UserService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @deprecated 增删改查DEMO
 * @author haoao
 * @date 2020-03-24
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * demo 管理端登录
     * @param userAcct
     * @param userPassword
     * @return
     */
    @PostMapping("login")
    public AppResponse sysLogin(String userAcct, String userPassword){
        try{
            return userService.sysLogin(userAcct,userPassword);
        }catch (Exception e){
            logger.error("登录异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 新增用户
     * @param user
     * @return
     * @author haoao
     * @Date 2020-03-24
     */
    @PostMapping("addUser")
    public AppResponse addUser(User user){
        try{
            return userService.addUser(user);
        }catch (Exception e){
            logger.error("用户新增异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 查询用户详情
     * @param userCode
     * @return AppResponse
     * @author haoao
     * @Date 2020-03-24
     */
    @RequestMapping(value = "findUserById")
    public AppResponse getUserByUserCode(String userCode) {
        try {
            return userService.getUserByUserCode(userCode);
        } catch (Exception e) {
            logger.error("用户查询异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 查询用户信息（分页）
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    @RequestMapping(value = "listUsersByPage")
    public AppResponse listUsersByPage(User user){
        try{
            return userService.listUsersByPage(user);
        }catch (Exception e){
            logger.error("用户列表查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 修改用户信息
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    @PostMapping("updateUser")
    public AppResponse updateUser(User user){
        try{
            return userService.updateUser(user);
        }catch (Exception e){
            logger.error("修改用户异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 删除用户
     * @param userCode
     * @param lastModfiedBy
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    @PostMapping("deleteUser")
    public AppResponse deleteUser(String userCode,String lastModfiedBy){
        try {
            return userService.deleteUser(userCode,lastModfiedBy);
        }catch (Exception e){
            logger.error("删除用户异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 查询客户信息（分页）
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-27
     */
    @RequestMapping(value = "listClientByPage")
    public AppResponse listClientByPage(User user){
        try{
            return userService.listClientByPage(user);
        }catch (Exception e){
            logger.error("客户列表查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
