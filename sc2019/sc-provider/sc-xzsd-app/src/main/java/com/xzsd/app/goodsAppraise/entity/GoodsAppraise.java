package com.xzsd.app.goodsAppraise.entity;

import java.util.Date;
import java.util.List;

/**
 * 商品评价实体类
 * @author haoao
 */
public class GoodsAppraise {
    /**
     * 评价编号
     */
    private String appraiseCode;

    /**
     * 商品编号
     */
    private String goodsCode;

    /**
     * 商品评价内容
     */
    private String goodsAppraise;

    /**
     * 商品评价星级(1差评 23中评 45好评)
     */
    private Integer goodsStar;

    /**
     * 评论用户的编号
     */
    private String userCode;

    /**
     * 序号
     */
    private Integer soreNo;

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

    /**
     * 订单编号
     */
    private String orderCode;

    private List<GoodsAppraisePic> goodsAppraisePic;

    public List<GoodsAppraisePic> getGoodsAppraisePic() {
        return goodsAppraisePic;
    }

    public void setGoodsAppraisePic(List<GoodsAppraisePic> goodsAppraisePic) {
        this.goodsAppraisePic = goodsAppraisePic;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    private List<GoodsAppraisePic> appraisePicList;

    public List<GoodsAppraisePic> getAppraisePicList() {
        return appraisePicList;
    }

    public void setAppraisePicList(List<GoodsAppraisePic> appraisePicList) {
        this.appraisePicList = appraisePicList;
    }

    public String getAppraiseCode() {
        return appraiseCode;
    }

    public void setAppraiseCode(String appraiseCode) {
        this.appraiseCode = appraiseCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getSoreNo() {
        return soreNo;
    }

    public void setSoreNo(Integer soreNo) {
        this.soreNo = soreNo;
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
