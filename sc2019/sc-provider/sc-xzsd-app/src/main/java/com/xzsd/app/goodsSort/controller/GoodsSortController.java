package com.xzsd.app.goodsSort.controller;


import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.goodsSort.entity.GoodsSort;
import com.xzsd.app.goodsSort.service.GoodsSortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @description增删改查DEMO
 * @author haoao
 * @date 2020-03-24
 */
@RestController
@RequestMapping("/goodsSort")
public class GoodsSortController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsSortController.class);

    @Resource
    private GoodsSortService goodsSortService;

    /**
     * demo 查询商品分类列表
     * @return AppResponse
     * @author haoao
     * @date 2020-03-24
     */
    @RequestMapping(value = "listGoodsSort")
    public AppResponse listGoodsSort(){
        try{
            return goodsSortService.listGoodsSort();
        }catch (Exception e){
            logger.error("查询商品分类列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 商品一级分类列表查询
     * @return
     */
    @RequestMapping(value = "listClassifyOne")
    public AppResponse listClassifyOne(){
        try{
            return goodsSortService.listClassifyOne();
        }catch (Exception e){
            logger.error("查询一级分类异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 商品二级分类列表查询
     * @param cateCode
     * @return
     */
    @RequestMapping(value = "listClassifyTwo")
    public AppResponse listClassifyTwo(String cateCode){
        try{
            return goodsSortService.listClassifyTwo(cateCode);
        }catch (Exception e){
            logger.error("查询一级分类异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
