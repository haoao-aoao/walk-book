package com.xzsd.app.goodsMessage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GoodsMessageVo {
    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
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
    private float goodsStar;
    /**
     * 作者
     */
    private String goodsAuthor;
    /**
     * 商品简介
     */
    private String goodsSketch;

    /**
     * 版本号
     */
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public float getGoodsStar() {
        return goodsStar;
    }

    public void setGoodsStar(float goodsStar) {
        this.goodsStar = goodsStar;
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
}
