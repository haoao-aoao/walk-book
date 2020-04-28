package com.xzsd.app.imageUpload.dao;

import com.xzsd.app.imageUpload.entity.ImageUpload;

public interface ImageUploadDao {

    /**
     * 添加图片地址
     * @return
     */
    int addImage(ImageUpload imageUpload);
}
