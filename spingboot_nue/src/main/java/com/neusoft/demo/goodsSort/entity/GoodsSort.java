package com.neusoft.demo.goodsSort.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class GoodsSort {
    /**
     * 分类编号
     */
    private String cateCode;
    /**
     * 分类名称
     */
    private String cateName;
    /**
     * 父级分类编号
     */
    private String cateCodeParent;
    /**
     * 分类等级
     */
    private String level;
    /**
     * 备注
     */
    private String remark;
    /**
     * 序号
     */
    private String sortNo;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDelete;
    /**
     * 创建时间
     */
    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createTime;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 更新时间
     */
    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String gmtModfied;
    /**
     * 更新者
     */
    private String lastModfiedBy;
    /**
     * 版本号
     */
    private String version;

    private List<GoodsSort> goodsClassifyTwo;

    public List<GoodsSort> getGoodsClassifyTwo() {
        return goodsClassifyTwo;
    }

    public void setGoodsClassifyTwo(List<GoodsSort> goodsClassifyTwo) {
        this.goodsClassifyTwo = goodsClassifyTwo;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateCodeParent() {
        return cateCodeParent;
    }

    public void setCateCodeParent(String cateCodeParent) {
        this.cateCodeParent = cateCodeParent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getGmtModfied() {
        return gmtModfied;
    }

    public void setGmtModfied(String gmtModfied) {
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
