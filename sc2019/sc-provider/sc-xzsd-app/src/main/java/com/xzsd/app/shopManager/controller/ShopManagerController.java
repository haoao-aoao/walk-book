package com.xzsd.app.shopManager.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.order.entity.Order;
import com.xzsd.app.shopManager.service.ShopManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("shopManager")
public class ShopManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ShopManagerController.class);

    @Resource
    private ShopManagerService shopManagerService;

    /**
     * 查询店长订单列表
     * @param order
     * @return
     */
    @PostMapping("findOrderlist")
    public AppResponse findOrderlist(Order order){
        try {
            //获取登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            order.setUserCode(currentUserId);
            return shopManagerService.findOrderlistByPage(order);
        }catch (Exception e){
            logger.error("查询异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 查询订单详情列表
     * @param orderCode
     * @return
     * @author haoao
     * @date 2020-04-15
     */
    @PostMapping("findOrderDetailedlist")
    public AppResponse listOrderDetById(String orderCode){
        try {
            return shopManagerService.listOrderDetById(orderCode);
        }catch (Exception e){
            logger.error("查询异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 店长端修改订单状态
     * @param order
     * @return
     */
    @PostMapping("updateOrderStateShopMr")
    public AppResponse updateOrderStateShopMr(Order order){
        try {
            //获取登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            order.setUserCode(currentUserId);
            return shopManagerService.updateOrderStateShopMr(order);
        }catch (Exception e){
            logger.error("修改查询异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机信息
     * @return
     */
    @PostMapping("findDriverManage")
    public AppResponse findDriverManage(){
        try {
            //获取登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            return shopManagerService.findDriverManage(currentUserId);
        }catch (Exception e){
            logger.error("查询异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
