package com.xzsd.pc.order.entity;


import com.xzsd.pc.goodsMessage.entity.GoodsMessage;

import java.util.Date;
import java.util.List;

/**
 * 订单详情实体类
 */
public class OrderDetailed {
    /**
     * 订单详情编号
     */
    private String orderDetCode;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 购买商品数量
     */
    private Integer goodsCnt;

    /**
     * 商品总金额
     */
    private Double goodsAllPrice;

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

    private List<GoodsMessage> goodsMessageList;


    private String goodsName;

    private double goodsPricing;

    private double goodsPrice;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPricing() {
        return goodsPricing;
    }

    public void setGoodsPricing(double goodsPricing) {
        this.goodsPricing = goodsPricing;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public List<GoodsMessage> getGoodsMessageList() {
        return goodsMessageList;
    }

    public void setGoodsMessageList(List<GoodsMessage> goodsMessageList) {
        this.goodsMessageList = goodsMessageList;
    }

    public String getOrderDetCode() {
        return orderDetCode;
    }

    public void setOrderDetCode(String orderDetCode) {
        this.orderDetCode = orderDetCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getGoodsCnt() {
        return goodsCnt;
    }

    public void setGoodsCnt(Integer goodsCnt) {
        this.goodsCnt = goodsCnt;
    }

    public Double getGoodsAllPrice() {
        return goodsAllPrice;
    }

    public void setGoodsAllPrice(Double goodsAllPrice) {
        this.goodsAllPrice = goodsAllPrice;
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
