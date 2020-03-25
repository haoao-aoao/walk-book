package com.neusoft.demo.goodsSort.controller;

import com.neusoft.demo.goodsSort.entity.GoodsSort;
import com.neusoft.demo.goodsSort.service.GoodsService;
import com.neusoft.demo.util.AppResponse;
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
@RequestMapping("/sys/goodsSort")
public class GoodsSortController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsSortController.class);

    @Resource
    private GoodsService goodsService;

    /**
     * demo 新增商品分类
     * @param goodsSort
     * @return AppResponse
     * @author haoao
     * @date 2020-03-24
     */
    @PostMapping("addGoodsSoft")
    public AppResponse addGoodsSoft(GoodsSort goodsSort){
        try{
            goodsSort.setCreateUser("admin");
            AppResponse appResponse = goodsService.addGoodsSoft(goodsSort);
            return appResponse;
        }catch (Exception e) {
            logger.error("商品分类新增失败");
            System.out.println(e.toString());
            throw e;
        }

    }

    /**
     * demo 查询商品分类列表
     * @param goodsSort
     * @return AppResponse
     * @author haoao
     * @date 2020-03-24
     */
    @RequestMapping(value = "listGoodsSort")
    public AppResponse listGoodsSort(GoodsSort goodsSort){
        try{
            return goodsService.listGoodsSort(goodsSort);
        }catch (Exception e){
            logger.error("查询商品分类列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
