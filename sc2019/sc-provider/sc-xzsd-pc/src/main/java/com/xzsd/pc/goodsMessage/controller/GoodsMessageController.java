package com.xzsd.pc.goodsMessage.controller;


import com.xzsd.pc.goodsMessage.entity.GoodsMessage;
import com.xzsd.pc.goodsMessage.service.GoodsMessageService;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description增删改查DEMO
 * @author haoao
 * @date 2020-03-25
 */
@RestController
@RequestMapping("/sys/goodsMessage")
public class GoodsMessageController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsMessageController.class);

    @Resource
    private GoodsMessageService goodsMessageService;

    /**
     * demo 新增商品
     * @param goodsMessage 商品信息
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    @PostMapping("addGoods")
    public AppResponse addGoods(GoodsMessage goodsMessage){
        try{
            goodsMessage.setCreateUser("admin");
            AppResponse appResponse = goodsMessageService.addGoods(goodsMessage);
            return  appResponse;
        }catch (Exception e){
            logger.error("新增商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品信息
     * @param goodsMessage
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    @PostMapping("updateGoodsById")
    public AppResponse updateGoodsById(GoodsMessage goodsMessage){
        try{
            //获取用户code
            String userCode = AuthUtils.getCurrentUserId();
            goodsMessage.setCreateUser(userCode);
            goodsMessage.setLastModfiedBy(userCode);
            AppResponse appResponse = goodsMessageService.updateGoodsById(goodsMessage);
            return appResponse;
        }catch (Exception e){
            logger.error("修改商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 删除商品
     * @param goodsCode
     * @param userCode
     * @return
     * @Author haoao
     * @Date 2020-03-25
     */
    @PostMapping("deleteGoods")
    public AppResponse deleteGoods(String goodsCode,String userCode){
        try{
            AppResponse appResponse = goodsMessageService.deleteGoods(goodsCode, userCode);
            return appResponse;
        }catch (Exception e){
            logger.error("商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

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
     * 修改商品状态
     * @param goodsCode
     * @param goodsState
     * @param lastModfiedBy
     * @return
     * @author haoao
     * @date 2020-03-25
     **/
    @PostMapping("updateGoodsState")
    public AppResponse updateGoodsState(String goodsCode, int goodsState,String lastModfiedBy){
        try{
            AppResponse appResponse = goodsMessageService.updateGoodsState(goodsCode, goodsState,lastModfiedBy);
            return  appResponse;
        }catch (Exception e){
            logger.error("修改商品状态失败", e);
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
