package com.xzsd.app.order.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.order.entity.Order;
import com.xzsd.app.order.entity.OrderDetailed;
import com.xzsd.app.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    /**
     * demo 查询订单列表
     * @param order
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @RequestMapping(value = "listsUserOrder")
    public AppResponse listsOrder(Order order){
        try {
            //获取当前登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            order.setUserCode(currentUserId);
            return orderService.listUserOrder(order);
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
     * @date 2020-03-30
     */
    @RequestMapping(value = "orderDetById")
    public AppResponse listOrderDetById(String orderCode){
        try {
            return orderService.listOrderDetById(orderCode);
        }catch (Exception e){
            logger.error("查询异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 添加订单（商品详情页）
     * @param order
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @PostMapping("addOrderOne")
    public AppResponse addOrderAndAddOrderDetailed(Order order, OrderDetailed orderDetailed){
        try {
            //获取当前登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            order.setUserCode(currentUserId);
            return orderService.addOrderAndAddOrderDetailed(order,orderDetailed);
        }catch (Exception e){
            logger.error("新增订单异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 添加订单（购物车页）
     * @param shopcartCode
     * @param sum
     * @return
     * @author haoao
     * @date 2020-04-10
     */
    @PostMapping("addOrderTwo")
    public AppResponse addOrderAndAddOrderDetailed2(String shopcartCode,String sum){
        try {
            return orderService.addOrderAndAddOrderDetailed2(shopcartCode,sum);
        }catch (Exception e){
            logger.error("新增订单异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 修改订单状态(确认收货/取消订单)
     * @param
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @PostMapping("updateCliOrderState")
    public AppResponse updateOrderState(Order order){
        try {
            //获取当前登录人id
            String currentUserId = SecurityUtils.getCurrentUserId();
            order.setLastModfiedBy(currentUserId);
            return orderService.updateOrderState(order);
        }catch (Exception e){
            logger.error("修改订单状态异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
