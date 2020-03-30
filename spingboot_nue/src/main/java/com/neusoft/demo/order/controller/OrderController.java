package com.neusoft.demo.order.controller;

import com.neusoft.demo.order.entity.Order;
import com.neusoft.demo.order.service.OrderService;
import com.neusoft.demo.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys/Order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "listsOrder")
    public AppResponse listsOrder(Order order){
        try {
            return orderService.listsOrder(order);
        }catch (Exception e){
            logger.error("查询异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

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
}
