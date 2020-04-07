package com.neusoft.demo.banner.controller;

import com.neusoft.demo.banner.entity.Banner;
import com.neusoft.demo.banner.service.BannerService;
import com.neusoft.demo.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/sys/banner")
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Resource
    private BannerService bannerService;

    /**
     * 新增轮播图
     * @param banner
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @PostMapping("addBanner")
    public AppResponse addBanner(Banner banner){
        try{
            banner.setCreateUser("admin");
            return bannerService.addBanner(banner);
        }catch (Exception e){
            logger.error("新增轮播图异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询轮播图列表（分页）
     * @param banner
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @RequestMapping(value = "listBanner")
    public AppResponse listBanner(Banner banner){
        try{
            return bannerService.listBanner(banner);
        }catch (Exception e){
            logger.error("查询轮播图列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除轮播图
     * @param id
     * @param userCode
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @PostMapping("deleteBanner")
    public AppResponse deleteBanner(String id,String userCode){
        try{
            return bannerService.deleteBanner(id,userCode);
        }catch (Exception e){
            logger.error("删除轮播图异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改轮播图状态
     * @param id
     * @param userCode
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @PostMapping("updateBannerState")
    public AppResponse updateBannerState(String id,int state,String userCode,String version){
        try{
            return bannerService.updateBannerState(id,state,userCode,version);
        }catch (Exception e){
            logger.error("删除轮播图异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
