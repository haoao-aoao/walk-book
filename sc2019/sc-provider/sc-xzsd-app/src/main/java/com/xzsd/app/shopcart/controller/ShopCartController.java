package com.xzsd.app.shopcart.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.shopcart.entity.ShopCart;
import com.xzsd.app.shopcart.service.ShopCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/shopCart")
public class ShopCartController {

    private static final Logger logger = LoggerFactory.getLogger(ShopCartController.class);

    @Resource
    private ShopCartService shopCartService;

    /**
     * 新增购物车商品
     * @param shopCart
     * @return
     */
    @PostMapping("addShopCart")
    public AppResponse addShopCart(ShopCart shopCart){
        try{
            //获取当前登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            shopCart.setUserCode(currentUserId);
            return shopCartService.addShopCart(shopCart);
        }catch (Exception e){
            logger.error("新增异常");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改购物车商品数量
     * @param shopCart
     * @return
     */
    @PostMapping("updateShopCartBuyCount")
    public AppResponse updateShopCartBuyCount(ShopCart shopCart){
        try{
            //获取当前登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            shopCart.setLastModfiedBy(currentUserId);
            return shopCartService.updateShopCartBuyCount(shopCart);
        }catch (Exception e){
            logger.error("新增异常");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除购物车商品
     * @param shopcartCode
     * @return
     */
    @PostMapping("deleteShopCartById")
    public AppResponse deleteShopCartById(String shopcartCode){
        try{
            //获取当前登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            return shopCartService.deleteShopCartById(shopcartCode,currentUserId);
        }catch (Exception e){
            logger.error("新增异常");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询购物车列表
     * @return
     */
    @PostMapping("listShopCart")
    public AppResponse listShopCartByPage(){
        try{
            //获取当前登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            return shopCartService.listShopCartByPage(currentUserId);
        }catch (Exception e){
            logger.error("新增异常");
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 购物车商品合计金额
     * @param allmoney
     * @return
     */
    @PostMapping("shopCartMoney")
    public AppResponse shopCartMoney(String allmoney){
        try{
            return shopCartService.shopCartMoney(allmoney);
        }catch (Exception e){
            logger.error("新增异常");
            System.out.println(e.toString());
            throw e;
        }
    }
}
