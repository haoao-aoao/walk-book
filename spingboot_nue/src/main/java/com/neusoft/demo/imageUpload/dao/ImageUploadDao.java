package com.neusoft.demo.imageUpload.dao;


import com.neusoft.demo.imageUpload.entity.ImageUpload;
import org.apache.ibatis.annotations.Param;

public interface ImageUploadDao {

    /**
     * 添加图片地址
     * @return
     */

    int addImage(ImageUpload imageUpload);
}
