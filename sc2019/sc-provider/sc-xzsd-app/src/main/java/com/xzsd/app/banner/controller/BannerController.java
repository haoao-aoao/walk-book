package com.xzsd.app.banner.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.banner.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/banner")
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Resource
    private BannerService bannerService;

    /**
     * 查询轮播图列表
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @RequestMapping(value = "listBanner")
    public AppResponse listBanner(){
        try{
            return bannerService.listBanner();
        }catch (Exception e){
            logger.error("查询轮播图列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
