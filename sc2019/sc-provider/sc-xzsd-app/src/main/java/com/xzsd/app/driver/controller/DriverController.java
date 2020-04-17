package com.xzsd.app.driver.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.driver.entity.Driver;
import com.xzsd.app.driver.service.DriverService;
import com.xzsd.app.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("driver")
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Resource
    private DriverService driverService;

    /**
     * 查询司机详情
     * @param driverCode
     * @return
     */
    @RequestMapping("/findDriverById")
    public AppResponse findDriverById(String driverCode){
        try{
            return driverService.findDriverById(driverCode);
        }catch (Exception e){
            logger.error("查询司机信息异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 司机信息列表查询
     * @param driver
     * @return
     */
    @RequestMapping("/listDriver")
    public AppResponse listDriver(Driver driver){
        try{
            return driverService.listDriver(driver);
        }catch (Exception e){
            logger.error("查询司机信息列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机对应的门店信息
     * @return
     */
    @PostMapping("findStoreManage")
    public AppResponse findStoreManage(){
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        return driverService.findStoreManage(currentUserId);
    }
}
