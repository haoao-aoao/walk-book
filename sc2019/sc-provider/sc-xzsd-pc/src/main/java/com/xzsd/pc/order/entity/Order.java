package com.xzsd.pc.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzsd.pc.user.entity.User;


import java.util.Date;
import java.util.List;

public class Order {
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单状态(0已付款 1待取货 2已完成未评价 3已完成已评价 4已取消)
     */
    private Integer orderState;

    /**
     * 支付状态(0未支付 1已支付)
     */
    private Integer payState;

    /**
     * 付款时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date payTime;

    /**
     * 收货时间
     */
    private Date receiveTime;

    /**
     * 购买用户编号
     */
    private String userCode;

    /**
     * 门店编号
     */
    private String storeCode;

    /**
     * 订单总价
     */
    private Double orderPrice;

    /**
     * 支付金额
     */
    private Double paymentMoney;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 作废标记 0未删 1已删
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者(登陆)
     */
    private String lastModfiedBy;

    /**
     * 更新时间
     */
    private Date gmtModfied;

    /**
     * 版本号
     */
    private Integer version;


    private String payTimeS;


    private String payTimeE;

    private String name;

    private String phone;


    private User user;

    private List<OrderDetailed> orderDetList;

    public List<OrderDetailed> getOrderDetList() {
        return orderDetList;
    }

    public void setOrderDetList(List<OrderDetailed> orderDetList) {
        this.orderDetList = orderDetList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPayTimeS() {
        return payTimeS;
    }

    public void setPayTimeS(String payTimeS) {
        this.payTimeS = payTimeS;
    }

    public String getPayTimeE() {
        return payTimeE;
    }

    public void setPayTimeE(String payTimeE) {
        this.payTimeE = payTimeE;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Double paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModfiedBy() {
        return lastModfiedBy;
    }

    public void setLastModfiedBy(String lastModfiedBy) {
        this.lastModfiedBy = lastModfiedBy;
    }

    public Date getGmtModfied() {
        return gmtModfied;
    }

    public void setGmtModfied(Date gmtModfied) {
        this.gmtModfied = gmtModfied;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
