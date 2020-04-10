package com.xzsd.app.goodsMessage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzsd.app.goodsSort.entity.GoodsSort;

import java.util.Date;

public class GoodsMessage {
    /**
     * 页码
     */
    private Integer pageSize;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品一级分类编码
     */
    private String classifyOneCode;
    /**
     * 商品二级分类编码
     */
    private String classifyTwoCode;
    /**
     * 商品一级分类名称
     */
    private String classifyOne;
    /**
     * 商品二级分类名称
     */
    private String classifyTwo;
    /**
     * 商品定价
     */
    private double goodsPricing;
    /**
     * 商品售价
     */
    private double goodsPrice;
    /**
     * 商品销量
     */
    private int salesVolume;
    /**
     * 商品库存
     */
    private int goodsStock;
    /**
     * 商品图片编码
     */
    private String goodsImg;
    /**
     * 商品浏览量
     */
    private int goodsView;
    /**
     * 商品评价星级 01差评 23中评 45好评
     */
    private int goodsStar;
    /**
     * 上架时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date upTime;
    /**
     * 商品状态 0在售 1已下架 2未发布
     */
    private Integer goodsState;
    /**
     * 商家名称
     */
    private String vendorName;
    /**
     * 出版社
     */
    private String goodsPress;
    /**
     * 书号
     */
    private String goodsNo;
    /**
     * 作者
     */
    private String goodsAuthor;
    /**
     * 商品简介
     */
    private String goodsSketch;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 更新时间
     */
    private Date gmtModfied;
    /**
     * 更新者
     */
    private String lastModfiedBy;
    /**
     * 版本号
     */
    private String version;
    /**
     * 商品1分类
     */
    private GoodsSort goodsSortOne;

    /**
     * 商品2分类
     */
    private GoodsSort goodsSortTwo;

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public GoodsSort getGoodsSortOne() {
        return goodsSortOne;
    }

    public void setGoodsSortOne(GoodsSort goodsSortOne) {
        this.goodsSortOne = goodsSortOne;
    }

    public GoodsSort getGoodsSortTwo() {
        return goodsSortTwo;
    }

    public void setGoodsSortTwo(GoodsSort goodsSortTwo) {
        this.goodsSortTwo = goodsSortTwo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getClassifyOneCode() {
        return classifyOneCode;
    }

    public void setClassifyOneCode(String classifyOneCode) {
        this.classifyOneCode = classifyOneCode;
    }

    public String getClassifyTwoCode() {
        return classifyTwoCode;
    }

    public void setClassifyTwoCode(String classifyTwoCode) {
        this.classifyTwoCode = classifyTwoCode;
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

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getGoodsView() {
        return goodsView;
    }

    public void setGoodsView(int goodsView) {
        this.goodsView = goodsView;
    }

    public int getGoodsStar() {
        return goodsStar;
    }

    public void setGoodsStar(int goodsStar) {
        this.goodsStar = goodsStar;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getGoodsPress() {
        return goodsPress;
    }

    public void setGoodsPress(String goodsPress) {
        this.goodsPress = goodsPress;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsAuthor() {
        return goodsAuthor;
    }

    public void setGoodsAuthor(String goodsAuthor) {
        this.goodsAuthor = goodsAuthor;
    }

    public String getGoodsSketch() {
        return goodsSketch;
    }

    public void setGoodsSketch(String goodsSketch) {
        this.goodsSketch = goodsSketch;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getGmtModfied() {
        return gmtModfied;
    }

    public void setGmtModfied(Date gmtModfied) {
        this.gmtModfied = gmtModfied;
    }

    public String getLastModfiedBy() {
        return lastModfiedBy;
    }

    public void setLastModfiedBy(String lastModfiedBy) {
        this.lastModfiedBy = lastModfiedBy;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
