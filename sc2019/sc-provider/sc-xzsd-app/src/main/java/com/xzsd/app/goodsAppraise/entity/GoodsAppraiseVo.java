package com.xzsd.app.goodsAppraise.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 商品评价Vo类
 * @author haoao
 */
public class GoodsAppraiseVo {
    /**
     * 商品评价内容
     */
    private String goodsAppraise;
    /**
     * 商品评价星级(1差评 23中评 45好评)
     */
    private Integer goodsStar;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 评论图片
     */
    private List<GoodsAppraisePic> appraisePicList;

    public List<GoodsAppraisePic> getAppraisePicList() {
        return appraisePicList;
    }

    public void setAppraisePicList(List<GoodsAppraisePic> appraisePicList) {
        this.appraisePicList = appraisePicList;
    }

    public String getGoodsAppraise() {
        return goodsAppraise;
    }

    public void setGoodsAppraise(String goodsAppraise) {
        this.goodsAppraise = goodsAppraise;
    }

    public Integer getGoodsStar() {
        return goodsStar;
    }

    public void setGoodsStar(Integer goodsStar) {
        this.goodsStar = goodsStar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
