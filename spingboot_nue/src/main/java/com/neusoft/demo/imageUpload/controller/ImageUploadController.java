package com.neusoft.demo.imageUpload.controller;


import com.neusoft.demo.imageUpload.service.ImageUploadService;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.TencentCOS;
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

    @Value("${tencent.path}")
    private String IMAGE_PATH;

    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * 上传头像
     */
    @PostMapping("upload")
    public AppResponse upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userCode") String userCode) throws Exception {
        //获取文件的名称
        String fileName = multipartFile.getOriginalFilename();

        //判断有无后缀
        if (fileName.lastIndexOf(".") < 0){
            return AppResponse.bizError("上传图片格式不正确");
        }

            //return new ForumResult(500, "上传图片格式不正确", null);

        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        //如果不是图片
        if (!prefix.equalsIgnoreCase(".jpg") && !prefix.equalsIgnoreCase(".jpeg") && !prefix.equalsIgnoreCase(".svg") && !prefix.equalsIgnoreCase(".gif") && !prefix.equalsIgnoreCase(".png")) {
            return AppResponse.bizError("上传图片格式不正确");
            //return new ForumResult(500, "上传图片格式不正确", null);
        }

        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        multipartFile.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String imageName = TencentCOS.uploadfile(excelFile, "walk-bookshop");

        //程序结束时，删除临时文件
        deleteFile(excelFile);

//        //存入图片名称，用于网页显示
//        model.addAttribute("imageName", imageName);

        //更新数据库


        //返回成功信息
        //return new ForumResult(200, "头像更换成功", imageName);
        return imageUploadService.upload(imageName, userCode);
    }

    /**
     * 删除临时文件
     *
     * @param files 临时文件，可变参数
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
