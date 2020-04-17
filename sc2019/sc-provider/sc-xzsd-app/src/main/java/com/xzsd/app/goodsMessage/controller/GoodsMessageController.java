package com.xzsd.app.goodsMessage.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.AuthUtils;
import com.xzsd.app.goodsMessage.entity.GoodsMessage;
import com.xzsd.app.goodsMessage.service.GoodsMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description增删改查DEMO
 * @author haoao
 * @date 2020-03-25
 */
@RestController
@RequestMapping("/goodsMessage")
public class GoodsMessageController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsMessageController.class);

    @Resource
    private GoodsMessageService goodsMessageService;

    /**
     * demo 查询商品详情
     * @param goodsCode 商品编号
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    @RequestMapping(value = "findGoodsById")
    public AppResponse findGoodsById(String goodsCode){
        try{
            AppResponse goodsById = goodsMessageService.findGoodsById(goodsCode);
            return goodsById;
        }catch (Exception e){
            logger.error("查询商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 商品列表查询（分页）
     * @param goodsMessage
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    @RequestMapping(value = "listGoods")
    public AppResponse listGoods(GoodsMessage goodsMessage){
        try{
            AppResponse appResponse = goodsMessageService.listGoods(goodsMessage);
            return appResponse;
        }catch (Exception e){
            logger.error("查询商品列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 商品选择列表（分页）
     * @param goodsMessage
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    @RequestMapping(value = "goodsChoseList")
    public AppResponse goodsChoseList(GoodsMessage goodsMessage){
        try{
            AppResponse appResponse = goodsMessageService.goodsChoseList(goodsMessage);
            return appResponse;
        }catch (Exception e){
            logger.error("查询商品列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
