package com.xzsd.pc.imageUpload.dao;


import com.xzsd.pc.imageUpload.entity.ImageUpload;
import org.apache.ibatis.annotations.Param;

public interface ImageUploadDao {

    /**
     * 添加图片地址
     * @return
     */

    int addImage(ImageUpload imageUpload);
}
