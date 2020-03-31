package com.neusoft.demo.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.goodsMessage.dao.GoodsMessageDao;
import com.neusoft.demo.goodsMessage.entity.GoodsMessage;
import com.neusoft.demo.order.dao.OrderDao;
import com.neusoft.demo.order.entity.Order;
import com.neusoft.demo.order.entity.OrderDetailed;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private GoodsMessageDao goodsMessageDao;

    /**
     * demo 查询订单列表
     * @param order
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    public AppResponse listsOrder(Order order){
        PageHelper.startPage(order.getPageNum(),order.getPageSize());
        List<Order> orderList = orderDao.listsOrder(order);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        return AppResponse.success("查询成功",orderPageInfo);
    }

    /**
     * demo 查询订单详情列表
     * @param orderCode
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    public AppResponse listOrderDetById(String orderCode){
        Order order = orderDao.listOrderDetById(orderCode);
        return AppResponse.success("查询成功",order);
    }

    /**
     * demo 添加订单（商品详情页）
     * @param order
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addOrderAndAddOrderDetailed(Order order, OrderDetailed orderDetailed){
        //初始化订单状态为0：已付款
        order.setOrderState(0);
        //分配订单编号
        order.setOrderCode(StringUtil.getCommonCode(2));
        //支付状态 默认 1已支付
        order.setPayState(1);
        //计算支付金额
        //根据商品编号查询商品详情
        GoodsMessage goodsById = goodsMessageDao.findGoodsById(orderDetailed.getGoodsCode());
        double payPrice = (orderDetailed.getGoodsCnt() * goodsById.getGoodsPrice());
        order.setPaymentMoney(payPrice);
        order.setOrderPrice(payPrice);
        orderDao.addOrder(order);
        //将订单编号放进订单详情表
        orderDetailed.setOrderCode(order.getOrderCode());
        //分配订单详情编号
        orderDetailed.setOrderDetCode(StringUtil.getCommonCode(1));
        orderDetailed.setGoodsAllPrice(payPrice);
        orderDao.addOrderDetailed(orderDetailed);
        return AppResponse.success("订单新增成功");
    }

    /**
     * demo 修改订单状态
     * @param
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(int orderState,String orderCode,String userCode,int version){
        List<String> listCode = Arrays.asList(orderCode.split(","));

        int cnt = orderDao.updateOrderState(orderState, listCode, userCode, version);
        if (cnt == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }
}
