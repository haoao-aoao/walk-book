package com.xzsd.app.imageUpload.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.imageUpload.service.ImageUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传Controller
 */

@RestController
@RequestMapping("image")
public class ImageUploadController {

    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * 上传头像
     */
    @PostMapping("upload")
    public AppResponse upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            return imageUploadService.upload(multipartFile,currentUserId);
        }catch (Exception e){
            logger.error("商品分类新增失败");
            System.out.println(e.toString());
            throw e;
        }


    }



}
