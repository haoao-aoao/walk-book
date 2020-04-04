package com.neusoft.demo.driver.entity;

import com.neusoft.demo.user.entity.UserVo;

public class DriverVo {
    /**
     * 司机编号
     */
    private String driverCode;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区
     */
    private String areaName;

    /**
     * 用户信息
     */
    private UserVo userInfo;

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public UserVo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserVo userInfo) {
        this.userInfo = userInfo;
    }
}
