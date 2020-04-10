package com.xzsd.app.goodsSort.entity;

import com.xzsd.app.goodsMessage.entity.GoodsMessageVo;

import java.util.List;

public class GoodsSortVo {
    /**
     * 二级分类编号
     */
    private String goodsClassifyTwoCode;
    /**
     * 二级分类名称
     */
    private String goodsClassifyTwoName;

    /**
     * 商品信息列表
     */
    private List<GoodsMessageVo> goodsMessage;


    public String getGoodsClassifyTwoCode() {
        return goodsClassifyTwoCode;
    }

    public void setGoodsClassifyTwoCode(String goodsClassifyTwoCode) {
        this.goodsClassifyTwoCode = goodsClassifyTwoCode;
    }

    public String getGoodsClassifyTwoName() {
        return goodsClassifyTwoName;
    }

    public void setGoodsClassifyTwoName(String goodsClassifyTwoName) {
        this.goodsClassifyTwoName = goodsClassifyTwoName;
    }

    public List<GoodsMessageVo> getGoodsMessage() {
        return goodsMessage;
    }

    public void setGoodsMessage(List<GoodsMessageVo> goodsMessage) {
        this.goodsMessage = goodsMessage;
    }
}
