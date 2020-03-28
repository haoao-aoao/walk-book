package com.neusoft.demo.imageUpload.service;

import com.neusoft.demo.imageUpload.dao.ImageUploadDao;
import com.neusoft.demo.imageUpload.entity.ImageUpload;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ImageUploadService {

    @Resource
    private ImageUploadDao imageUploadDao;

    @Transactional(rollbackFor = Exception.class)
    public AppResponse upload(String imgPath,String userCode){
        ImageUpload imageUpload = new ImageUpload();
        imageUpload.setImgId(StringUtil.getCommonCode(2));
        imageUpload.setImgPath(imgPath);
        imageUpload.setUserCode(userCode);
        int count = imageUploadDao.addImage(imageUpload);
        if (count == 0){
            return AppResponse.bizError("上传失败");
        }
        return AppResponse.success("上传成功","https://neu-1301643398.cos.ap-chengdu.myqcloud.com/" + imgPath);
    }
}
