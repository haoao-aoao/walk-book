package com.xzsd.pc.banner.controller;

import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.banner.entity.Banner;
import com.xzsd.pc.banner.service.BannerService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("banner")
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
            String currentUserId = SecurityUtils.getCurrentUserId();
            banner.setCreateUser(currentUserId);
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
    @PostMapping("listBanner")
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
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @PostMapping("deleteBanner")
    public AppResponse deleteBanner(String id){
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            return bannerService.deleteBanner(id,currentUserId);
        }catch (Exception e){
            logger.error("删除轮播图异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改轮播图状态
     * @param id
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @PostMapping("updateBannerState")
    public AppResponse updateBannerState(String id,int state,String version){
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            return bannerService.updateBannerState(id,state,currentUserId,version);
        }catch (Exception e){
            logger.error("修改轮播图异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
