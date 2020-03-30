package com.neusoft.demo.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.order.dao.OrderDao;
import com.neusoft.demo.order.entity.Order;
import com.neusoft.demo.util.AppResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    public AppResponse listsOrder(Order order){
        PageHelper.startPage(order.getPageNum(),order.getPageSize());
        List<Order> orderList = orderDao.listsOrder(order);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        return AppResponse.success("查询成功",orderPageInfo);
    }

    public AppResponse listOrderDetById(String orderCode){
        Order order = orderDao.listOrderDetById(orderCode);
        return AppResponse.success("查询成功",order);
    }
}
