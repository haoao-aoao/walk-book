package com.neusoft.demo.banner.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.demo.goodsMessage.entity.GoodsMessage;

import java.util.Date;
import java.util.List;


public class Banner {
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片状态(0启用 1禁用)
     */
    private Integer state;

    /**
     * 有效期起
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    /**
     * 有效期止
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")

    private Date endTime;

    /**
     * id
     */
    private String id;

    /**
     * 序号
     */
    private Integer sortNo;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新者(登陆)
     */
    private String lastModfiedBy;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtModfied;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 商品信息列表
     */
    private List<GoodsMessage> goodsMessages;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    public List<GoodsMessage> getGoodsMessages() {
        return goodsMessages;
    }

    public void setGoodsMessages(List<GoodsMessage> goodsMessages) {
        this.goodsMessages = goodsMessages;
    }
}
