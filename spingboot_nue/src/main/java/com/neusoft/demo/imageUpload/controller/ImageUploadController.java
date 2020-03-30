package com.neusoft.demo.imageUpload.controller;


import com.neusoft.demo.imageUpload.service.ImageUploadService;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.TencentCOS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片上传Controller
 */

@RestController
@RequestMapping("/image")
public class ImageUploadController {

    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * 上传头像
     */
    @PostMapping("upload")
    public AppResponse upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userCode") String userCode) throws Exception {
        try{
            return imageUploadService.upload(multipartFile, userCode);
        }catch (Exception e){
            logger.error("商品分类新增失败");
            System.out.println(e.toString());
            throw e;
        }


    }



}
