package com.xzsd.app.shopcart.entity;

import com.xzsd.app.goodsMessage.entity.GoodsMessageVo;

import java.util.List;

public class ShopCartVo {
    /**
     * 购物车编号
     */
    private String shopcartCode;
    /**
     * 商品编号
     */
    private String goodsCode;

    /**
     * 购买数量
     */
    private Integer buyCount;

    /**
     * 合计金额
     */
    private Double allmoney;

    /**
     * 商品信息列表
     */
    private GoodsMessageVo goodsMessage;

    public String getShopcartCode() {
        return shopcartCode;
    }

    public void setShopcartCode(String shopcartCode) {
        this.shopcartCode = shopcartCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Double getAllmoney() {
        return allmoney;
    }

    public void setAllmoney(Double allmoney) {
        this.allmoney = allmoney;
    }

    public GoodsMessageVo getGoodsMessage() {
        return goodsMessage;
    }

    public void setGoodsMessage(GoodsMessageVo goodsMessage) {
        this.goodsMessage = goodsMessage;
    }
}
