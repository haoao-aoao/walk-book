package com.xzsd.pc.order.dao;

import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.order.entity.OrderDetailed;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;

public interface OrderDao {

    /**
     * 新增订单
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 新增订单详情
     * @param orderDetailed
     * @return
     */
    int addOrderDetailed(OrderDetailed orderDetailed);

    /**
     * 修改订单状态
     * @param orderState
     * @param listCode
     * @param userCode
     * @return
     */
    int updateOrderState(@Param("orderState") int orderState, @Param("listCode") List<String> listCode, @Param("lastModfiedBy") String userCode, @Param("listVersion") List<String> version);

    /**
     * 查询订单详情列表
     * @param orderCode
     * @return
     */
    Order listOrderDetById(@Param("orderCode") String orderCode);

    /**
     * 查询订单列表
     * @param order
     * @return
     */
    List<Order> listsOrder(Order order);
}
