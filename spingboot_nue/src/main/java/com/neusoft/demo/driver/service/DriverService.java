package com.neusoft.demo.driver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.driver.dao.DriverDao;
import com.neusoft.demo.driver.entity.Driver;
import com.neusoft.demo.driver.entity.DriverVo;
import com.neusoft.demo.user.dao.UserDao;
import com.neusoft.demo.user.entity.User;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DriverService {

    @Resource
    private DriverDao driverDao;

    @Resource
    private UserDao userDao;

    /**
     * 新增司机信息
     * @param driver
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(Driver driver,User user){
        //检验用户账户是否存在
        int cnt = userDao.countUserAcct(user);
        if(cnt != 0){
            return AppResponse.bizError("账户已存在,请重新输入");
        }
        //获取用户编号
        user.setUserCode(StringUtil.getCommonCode(2));
        user.setRole(3);
        //生成司机编号
        driver.setDriverCode(StringUtil.getCommonCode(2));
        driver.setUserCode(user.getUserCode());
        int userCnt = userDao.saveUser(user);
        int driverCnt = driverDao.addDriver(driver);
        if (userCnt == 0 || driverCnt == 0){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增司机成功");
    }

    /**
     * 查询司机详情
     * @param driverCode
     * @return
     */
    public AppResponse findDriverById(String driverCode){
        DriverVo driver = driverDao.findDriverById(driverCode);
        return AppResponse.success("司机详情查询成功",driver);
    }

    /**
     * 修改司机信息
     * @param driver
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriver(Driver driver,User user){
        //检验用户账户是否存在
        int cnt = userDao.countUserAcct(user);
        if(cnt != 0){
            return AppResponse.bizError("账户已存在,请重新输入");
        }
        int driverCnt = driverDao.updateDriver(driver);
        int userCnt = userDao.updateUser(user);
        if (userCnt == 0 || driverCnt == 0){
            return AppResponse.bizError("数据有更新，请重试");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 删除司机信息
     * @param userCode
     * @param lastModfiedBy
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String userCode,String lastModfiedBy){
        List<String> listCode = Arrays.asList(userCode.split(","));
        int userCnt = userDao.deleteUser(listCode, lastModfiedBy);
        int driverCnt = driverDao.deleteDriver(listCode, lastModfiedBy);
        if (userCnt == 0 || driverCnt == 0){
            return AppResponse.bizError("删除失败，请重试");
        }
        return AppResponse.success("删除成功");
    }

    /**
     * 司机信息列表查询
     * @param driver
     * @return
     */
    public AppResponse listDriver(Driver driver){
        PageHelper.startPage(driver.getPageNum(),driver.getPageSize());
        List<Driver> drivers = driverDao.listDriver(driver);
        PageInfo<Driver> driverPageInfo = new PageInfo<>(drivers);
        return AppResponse.success("司机信息列表查询成功",driverPageInfo);
    }
}
