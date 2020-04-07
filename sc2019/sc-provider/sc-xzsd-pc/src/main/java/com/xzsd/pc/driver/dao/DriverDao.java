package com.xzsd.pc.driver.dao;

import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.driver.entity.DriverVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverDao {

    /**
     * 新增司机
     * @param driver
     * @return
     */
    int addDriver(Driver driver);

    /**
     * 查询司机详情
     * @param driverCode
     * @return
     */
    DriverVo findDriverById(@Param("driverCode") String driverCode);

    /**
     * 修改司机信息
     * @param driver
     * @return
     */
    int updateDriver(Driver driver);

    /**
     * 删除司机信息
     * @param listCode
     * @param lastModfiedBy
     * @return
     */
    int deleteDriver(@Param("listCode") List<String> listCode, @Param("lastModfiedBy") String lastModfiedBy);

    /**
     * 查询司机信息列表
     * @param driver
     * @return
     */
    List<Driver> listDriver(Driver driver);
}
