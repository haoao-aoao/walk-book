package com.xzsd.pc.imageUpload.controller;



import com.xzsd.pc.imageUpload.service.ImageUploadService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
