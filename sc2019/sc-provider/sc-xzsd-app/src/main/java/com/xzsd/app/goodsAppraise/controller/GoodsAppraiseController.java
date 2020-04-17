package com.xzsd.app.goodsAppraise.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.goodsAppraise.entity.GoodsAppraise;
import com.xzsd.app.goodsAppraise.entity.GoodsAppraisePic;
import com.xzsd.app.goodsAppraise.service.GoodsAppraiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("goodsAppraise")
public class GoodsAppraiseController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsAppraiseController.class);

    @Resource
    private GoodsAppraiseService goodsAppraiseService;

    /**
     * 新增商品评论
     * @param goodsAppraise
     * @return
     */
    @PostMapping("addGoodsAppraise")
    public AppResponse addGoodsAppraise(@RequestBody GoodsAppraise goodsAppraise){
        try{
            return goodsAppraiseService.addGoodsAppraise(goodsAppraise);
        }catch (Exception e){
            logger.error("新增异常");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 商品评论列表
     * @param goodsAppraise
     * @return
     */
    @PostMapping("listGoodsAppraiseByPage")
    public AppResponse listGoodsAppraiseByPage(GoodsAppraise goodsAppraise){
        try{
            return goodsAppraiseService.listGoodsAppraiseByPage(goodsAppraise);
        }catch (Exception e){
            logger.error("查询异常");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询评论页图片
     * @param orderCode
     * @return
     */
    @PostMapping("enterGoodsAppraise")
    public AppResponse enterGoodsAppraise(String orderCode){
        try{
            return goodsAppraiseService.enterGoodsAppraise(orderCode);
        }catch (Exception e){
            logger.error("查询异常");
            System.out.println(e.toString());
            throw e;
        }
    }
}
