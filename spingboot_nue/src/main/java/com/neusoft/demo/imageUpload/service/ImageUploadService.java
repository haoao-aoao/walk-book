package com.neusoft.demo.imageUpload.service;

import com.neusoft.demo.imageUpload.dao.ImageUploadDao;
import com.neusoft.demo.imageUpload.entity.ImageUpload;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import com.neusoft.demo.util.TencentCOS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Service
public class ImageUploadService {

    @Value("${tencent.path}")
    private String IMAGE_PATH;

    @Resource
    private ImageUploadDao imageUploadDao;

    @Transactional(rollbackFor = Exception.class)
    public AppResponse upload(MultipartFile multipartFile, String userCode) throws Exception {
        //获取文件的名称
        String fileName = multipartFile.getOriginalFilename();

        //判断有无后缀
        if (fileName.lastIndexOf(".") < 0){
            return AppResponse.bizError("上传图片格式不正确");
        }

        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        //如果不是图片
        if (!prefix.equalsIgnoreCase(".jpg") && !prefix.equalsIgnoreCase(".jpeg") && !prefix.equalsIgnoreCase(".svg") && !prefix.equalsIgnoreCase(".gif") && !prefix.equalsIgnoreCase(".png")) {
            return AppResponse.bizError("上传图片格式不正确");
        }

        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        multipartFile.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String imageName = TencentCOS.uploadfile(excelFile, "walk-bookshop");

        //程序结束时，删除临时文件
        deleteFile(excelFile);

        ImageUpload imageUpload = new ImageUpload();
        imageUpload.setImgId(StringUtil.getCommonCode(2));
        imageUpload.setImgPath(IMAGE_PATH + imageName);
        imageUpload.setUserCode(userCode);
        int count = imageUploadDao.addImage(imageUpload);

        if (count == 0){
            return AppResponse.bizError("上传失败");
        }
        return AppResponse.success("上传成功",IMAGE_PATH + imageName);
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
