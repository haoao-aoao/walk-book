package com.xzsd.app.shopManager.dao;

import com.xzsd.app.driver.entity.Driver;
import com.xzsd.app.order.entity.Order;
import com.xzsd.app.order.entity.OrderDetailed;
import com.xzsd.app.user.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopManagerDao {
    /**
     * 店长订单列表
     * @return
     */
    List<Order> findOrderlistByPage(Order order);

    /**
     * 查询订单详情(关联订单)
     * @return
     */
    OrderDetailed selectOrderDet();

    /**
     * 查询订单详情
     * @param orderCode
     * @return
     */
    Order OrderDetById(@Param("orderCode") String orderCode);

    /**
     * 查询司机信息列表
     * @param userCode 店长编号
     * @return
     */
    List<UserVo> findDriverManage(String userCode);
}
