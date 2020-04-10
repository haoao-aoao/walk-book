package com.xzsd.app.banner.entity;


public class Banner {
    /**
     * 图片路径
     */
    private String imageUrl;
    /**
     * id
     */
    private String id;
    /**
     * 序号
     */
    private Integer sortNo;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
