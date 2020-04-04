package com.neusoft.demo.driver.controller;

import com.neusoft.demo.driver.entity.Driver;
import com.neusoft.demo.driver.service.DriverService;
import com.neusoft.demo.user.entity.User;
import com.neusoft.demo.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/sys/driver")
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
    @PostMapping("/addDriver")
    public AppResponse addDriver(Driver driver, User user){
        try{
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
     * 修改司机信息
     * @param driver
     * @param user
     * @return
     */
    @PostMapping("/updateDriver")
    public AppResponse updateDriver(Driver driver,User user){
        try{
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
     * @param lastModfiedBy
     * @return
     */
    @PostMapping("/deleteDriver")
    public AppResponse deleteDriver(String userCode,String lastModfiedBy){
        try{
            return driverService.deleteDriver(userCode,lastModfiedBy);
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
}
