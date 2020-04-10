package com.xzsd.pc.order.controller;

import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.order.entity.OrderDetailed;
import com.xzsd.pc.order.service.OrderService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys/Order")
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
    @RequestMapping(value = "listsOrder")
    public AppResponse listsOrder(Order order){
        try {
            return orderService.listsOrderByPage(order);
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
    @RequestMapping(value = "listOrderDetById")
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
    @PostMapping("addOrder")
    public AppResponse addOrderAndAddOrderDetailed(Order order, OrderDetailed orderDetailed){
        try {
            return orderService.addOrderAndAddOrderDetailed(order,orderDetailed);
        }catch (Exception e){
            logger.error("新增订单异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 修改订单状态
     * @param
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(int orderState,String orderCode,String userCode,int version){
        try {
            return orderService.updateOrderState(orderState,orderCode,userCode,version);
        }catch (Exception e){
            logger.error("修改订单状态异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
