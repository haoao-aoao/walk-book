package com.xzsd.app.driver.dao;

import com.xzsd.app.driver.entity.Driver;
import com.xzsd.app.driver.entity.DriverVo;
import com.xzsd.app.store.entity.Store;
import com.xzsd.app.store.entity.StoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverDao {

    /**
     * 查询司机详情
     * @param driverCode
     * @return
     */
    DriverVo findDriverById(@Param("driverCode") String driverCode);

    /**
     * 查询司机信息列表
     * @param driver
     * @return
     */
    List<Driver> listDriver(Driver driver);

    /**
     * 查询司机对应的门店信息
     * @param userCode
     * @return
     */
    List<StoreVo> findStoreManage(@Param("userCode") String userCode);
}
