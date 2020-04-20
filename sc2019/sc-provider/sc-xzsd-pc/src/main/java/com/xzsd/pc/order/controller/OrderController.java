package com.xzsd.pc.order.controller;

import com.neusoft.security.client.utils.SecurityUtils;
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
@RequestMapping("/order")
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
    @PostMapping("listsOrder")
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
    @PostMapping("listOrderDetById")
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
     * demo 修改订单状态
     * @param
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(int orderState,String orderCode,String version){
        try {
            String currentUserId = SecurityUtils.getCurrentUserId();
            return orderService.updateOrderState(orderState,orderCode,currentUserId,version);
        }catch (Exception e){
            logger.error("修改订单状态异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
