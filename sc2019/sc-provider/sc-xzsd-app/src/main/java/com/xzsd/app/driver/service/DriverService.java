package com.xzsd.app.driver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.driver.dao.DriverDao;
import com.xzsd.app.driver.entity.Driver;
import com.xzsd.app.driver.entity.DriverVo;
import com.xzsd.app.store.entity.StoreVo;
import com.xzsd.app.user.dao.UserDao;
import com.xzsd.app.user.entity.User;
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
     * 查询司机详情
     * @param driverCode
     * @return
     */
    public AppResponse findDriverById(String driverCode){
        DriverVo driver = driverDao.findDriverById(driverCode);
        return AppResponse.success("司机详情查询成功",driver);
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

    /**
     * 查询司机对应的门店信息
     * @param userCode
     * @return
     */
    public AppResponse findStoreManage(String userCode){
        List<StoreVo> storeList = driverDao.findStoreManage(userCode);
        return AppResponse.success("查询成功",storeList);
    }
}
