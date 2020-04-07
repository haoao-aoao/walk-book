package com.xzsd.pc.goodsSort.controller;


import com.xzsd.pc.goodsSort.entity.GoodsSort;
import com.xzsd.pc.goodsSort.service.GoodsSortService;
import com.xzsd.pc.util.AppResponse;
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
    private GoodsSortService goodsSortService;



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
            //sendMessage(this.queue,goodsSort);
            AppResponse appResponse = goodsSortService.addGoodsSoft(goodsSort);
            return appResponse;
        }catch (Exception e) {
            logger.error("商品分类新增失败");
            System.out.println(e.toString());
            throw e;
        }

    }

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
     * demo 修改商品分类
     * @param goodsSort
     * @return AppResponse
     * @author haoao
     * @date 2020-03-24
     */
    @PostMapping("updateGoodsSort")
    public AppResponse updateGoodsSort(GoodsSort goodsSort){
        try{
            return goodsSortService.updateGoodsSort(goodsSort);
        }catch (Exception e){
            logger.error("修改商品分类异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 查询商品分类详情
     * @param cateCode
     * @return
     * @author haoao
     * @date 2020-03-24
     */
    @RequestMapping(value = "selectGoodsSortById")
    public AppResponse selectGoodsSortById(String cateCode){
        try{
            return goodsSortService.selectGoodsSortById(cateCode);
        }catch (Exception e){
            logger.error("查询商品分类详情异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 删除商品分类
     * @param cateCode
     * @param userCode
     * @return
     */
    @PostMapping("deleteGoodsSort")
    public AppResponse deleteGoodsSort(String cateCode,String userCode){
        try {
            return goodsSortService.deleteGoodsSort(cateCode,userCode);
        }catch (Exception e){
            logger.error("商品分类删除错误", e);
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
