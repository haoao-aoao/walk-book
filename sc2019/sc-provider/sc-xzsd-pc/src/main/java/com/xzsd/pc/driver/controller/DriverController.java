package com.xzsd.pc.driver.controller;

import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.driver.service.DriverService;
import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "driver")
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Resource
    private DriverService driverService;

    /**
     * 新增司机信息
     * @param driver
     * @param user
     * @return
     */
    @PostMapping("addDriver")
    public AppResponse addDriver(Driver driver, User user){
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            user.setCreateUser(currentUserId);
            driver.setCreateUser(currentUserId);
            return driverService.addDriver(driver,user);
        }catch (Exception e){
            logger.error("新增司机信息异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机详情
     * @param driverCode
     * @return
     */
    @PostMapping("findDriverById")
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
     * 修改司机信息
     * @param driver
     * @param user
     * @return
     */
    @PostMapping("updateDriver")
    public AppResponse updateDriver(Driver driver,User user){
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            driver.setLastModfiedBy(currentUserId);
            user.setLastModfiedBy(currentUserId);
            return driverService.updateDriver(driver,user);
        }catch (Exception e){
            logger.error("修改司机信息异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除司机信息
     * @param userCode
     * @return
     */
    @PostMapping("deleteDriver")
    public AppResponse deleteDriver(String userCode){
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            return driverService.deleteDriver(userCode,currentUserId);
        }catch (Exception e){
            logger.error("删除司机信息异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 司机信息列表查询
     * @param driver
     * @return
     */
    @PostMapping("listDriver")
    public AppResponse listDriver(Driver driver){
        try{
            return driverService.listDriver(driver);
        }catch (Exception e){
            logger.error("查询司机信息列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
