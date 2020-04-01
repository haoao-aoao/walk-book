package com.neusoft.demo.hotgoods.controller;

import com.neusoft.demo.hotgoods.entity.HotGoods;
import com.neusoft.demo.hotgoods.service.HotgoodsService;
import com.neusoft.demo.util.AppResponse;
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
}
