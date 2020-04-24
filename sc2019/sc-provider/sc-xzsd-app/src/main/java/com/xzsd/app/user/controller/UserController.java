package com.xzsd.app.user.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.user.entity.User;
import com.xzsd.app.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @deprecated 增删改查DEMO
 * @author haoao
 * @date 2020-03-24
 */
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * demo 注册用户
     * @param user
     * @return
     * @author haoao
     * @Date 2020-03-24
     */
    @PostMapping("register")
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
    @PostMapping("findUserById")
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
     * demo 修改用户密码
     * @param user
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    @PostMapping("updatePassword")
    public AppResponse updateUser(User user){
        try{
            //获取登陆人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            user.setUserCode(currentUserId);
            user.setLastModfiedBy(currentUserId);
            return userService.updateUser(user);
        }catch (Exception e){
            logger.error("修改用户异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询用户绑定的店铺邀请码
     * @return
     */
    @PostMapping("selectCliIncCode")
    public AppResponse selectCliIncCode(){
        try{
            //获取登陆人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            return userService.selectCliIncCode(currentUserId);
        }catch (Exception e){
            logger.error("查询用户邀请码异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改用户绑定店铺邀请码
     * @param user
     * @return
     */
    @PostMapping("updateInvitationCode")
    public AppResponse updateInvitationCode(User user){
        try{
            //获取登陆人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            user.setUserCode(currentUserId);
            return userService.updateInvitationCode(user);
        }catch (Exception e){
            logger.error("修改用户异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询公用个人信息
     * @return
     */
    @PostMapping("selectInfo")
    public AppResponse selectInfo(){
        try{
            //获取登陆人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            return userService.selectInfo(currentUserId);
        }catch (Exception e){
            logger.error("查询信息异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
