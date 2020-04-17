package com.xzsd.app.order.dao;

import com.xzsd.app.order.entity.Order;
import com.xzsd.app.order.entity.OrderDetailed;
import org.apache.ibatis.annotations.Param;

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
     * @param order
     * @return
     */
    int updateOrderState(Order order);

    /**
     * 查询订单详情
     * @param orderCode
     * @return
     */
    Order OrderDetById(@Param("orderCode") String orderCode);

    /**
     * 查询评价页图片
     * @param orderCode
     * @return
     */
    Order OrderPic(@Param("orderCode") String orderCode);

    /**
     * 查询订单列表
     * @param order
     * @return
     */
    List<Order> listsOrder(Order order);

    /**
     * 查询订单详情(关联订单)
     * @return
     */
    OrderDetailed selectOrderDet();
}
