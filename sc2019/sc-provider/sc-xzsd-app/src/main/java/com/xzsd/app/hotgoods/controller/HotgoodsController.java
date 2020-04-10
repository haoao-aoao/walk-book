package com.xzsd.app.hotgoods.controller;


import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.hotgoods.entity.HotGoods;
import com.xzsd.app.hotgoods.service.HotgoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hotGoods")
public class HotgoodsController {

    private static final Logger logger = LoggerFactory.getLogger(HotgoodsController.class);

    @Resource
    private HotgoodsService hotgoodsService;


    /**
     * 查询设置展示热门位商品
     * @param
     * @return
     */
    @RequestMapping(value = "listHotGoods")
    public AppResponse listHotGoodsByPage(){
        try{
            return hotgoodsService.listHotGoodsByPage();
        }catch (Exception e){
            logger.error("设置异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
