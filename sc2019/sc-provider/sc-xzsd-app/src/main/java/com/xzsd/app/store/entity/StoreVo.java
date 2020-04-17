package com.xzsd.app.store.entity;

public class StoreVo {
    /**
     * 店长名字
     */
    private String userName;
    /**
     * 市名
     */
    private String city;
    /**
     * 区名
     */
    private String area;
    /**
     * 省名
     */
    private String province;
    /**
     * 门店编号
     */
    private String storeCode;
    /**
     * 门店名
     */
    private String storeName;
    /**
     * 店长电话
     */
    private String userPhone;
    /**
     * 门店详细地址
     */
    private String storeAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
