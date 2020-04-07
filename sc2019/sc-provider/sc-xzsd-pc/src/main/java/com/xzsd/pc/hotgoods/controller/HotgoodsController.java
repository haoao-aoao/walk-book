package com.xzsd.pc.hotgoods.controller;


import com.xzsd.pc.hotgoods.entity.HotGoods;
import com.xzsd.pc.hotgoods.service.HotgoodsService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys/hotGoods")
public class HotgoodsController {

    private static final Logger logger = LoggerFactory.getLogger(HotgoodsController.class);

    @Resource
    private HotgoodsService hotgoodsService;

    /**
     * 新增热门位商品
     * @param hotGoods
     * @return
     */
    @PostMapping("addHotGoods")
    public AppResponse addHotGoods(HotGoods hotGoods){
        try{
            hotGoods.setCreateUser("admin");
            return hotgoodsService.addHotGoods(hotGoods);
        }catch (Exception e){
            logger.error("新增热门位商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门位商品列表
     * @param
     * @return
     */
    @RequestMapping(value = "listHotGoods")
    public AppResponse listHotGoods(String goodsCode,String goodsName,HotGoods hotGoods){
        try{
            return hotgoodsService.listHotGoods(goodsCode,goodsName,hotGoods);
        }catch (Exception e){
            logger.error("查询热门位商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改热门位商品
     * @param hotGoods
     * @return
     */
    @PostMapping("updateHotGoodsById")
    public AppResponse updateHotGoodsById(HotGoods hotGoods){
        try{
            return hotgoodsService.updateHotGoodsById(hotGoods);
        }catch (Exception e){
            logger.error("修改热门位商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除热门位商品
     * @param hotgoodsCode
     * @param userCode
     * @return
     */
    @PostMapping("deleteHotGoods")
    public AppResponse deleteHotGoods(String hotgoodsCode,String userCode){
        try{
            return hotgoodsService.deleteHotGoods(hotgoodsCode,userCode);
        }catch (Exception e){
            logger.error("修改热门位商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门位商品详情
     * @param hotgoodsCode
     * @return
     */
    @RequestMapping(value = "selectHotGoods")
    public AppResponse selectHotGoods(String hotgoodsCode){
        try{
            return hotgoodsService.selectHotGoods(hotgoodsCode);
        }catch (Exception e){
            logger.error("查询热门位商品详情异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询设置展示热门位商品
     * @param showNum
     * @return
     */
    @RequestMapping(value = "setHotGoodsShowNum")
    public AppResponse setHotGoodsShowNum(int showNum){
        try{
            return hotgoodsService.setHotGoodsShowNum(showNum);
        }catch (Exception e){
            logger.error("设置异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
