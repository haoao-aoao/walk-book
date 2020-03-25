package com.neusoft.demo.user.service;

import com.neusoft.demo.user.dao.UserDao;
import com.neusoft.demo.user.entity.User;
import com.neusoft.demo.util.AppResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
     * demo 查询用户详情
     * @param userCode
     * @return
     * @Author haoao
     * @Date 2020-03-24
     */
    public AppResponse getUserByUserCode(String userCode) {
        User user = userDao.getUserByUserCode(userCode);
        return AppResponse.success("查询成功！",user);
    }
}
