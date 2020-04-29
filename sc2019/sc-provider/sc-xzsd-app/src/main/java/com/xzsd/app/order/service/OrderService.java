package com.xzsd.app.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.goodsMessage.dao.GoodsMessageDao;
import com.xzsd.app.goodsMessage.entity.GoodsMessageVo;
import com.xzsd.app.order.dao.OrderDao;
import com.xzsd.app.order.entity.Order;
import com.xzsd.app.order.entity.OrderDetailed;
import com.xzsd.app.shopcart.dao.ShopCartDao;
import com.xzsd.app.shopcart.entity.ShopCartVo;
import com.xzsd.app.store.dao.StoreDao;
import com.xzsd.app.store.entity.Store;
import com.xzsd.app.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private GoodsMessageDao goodsMessageDao;

    @Resource
    private StoreDao storeDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ShopCartDao shopCartDao;

    /**
     * demo 查询订单列表（用户）
     * @param order
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    public AppResponse listUserOrder(Order order){
        PageHelper.startPage(order.getPageNum(),order.getPageSize());
        List<Order> userOrders = orderDao.listsOrder(order);
        //统计订单的商品总数量
        for (Order orderx : userOrders) {
            int cntSum = 0;
            List<OrderDetailed> orderDetList = orderx.getOrderDetList();
            for (OrderDetailed orderDetailed : orderDetList) {
                cntSum = cntSum + orderDetailed.getGoodsCnt();
            }
            orderx.setCntSum(cntSum);
        }
        PageInfo<Order> orderPageInfo = new PageInfo<>(userOrders);
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
        Order order = orderDao.OrderDetById(orderCode);
        //拼接地址
        String province = order.getStoreMsg().getProvince();
        String city = order.getStoreMsg().getCity();
        String area = order.getStoreMsg().getArea();
        String storeAddress = order.getStoreMsg().getStoreAddress();
        Store storeMsg = order.getStoreMsg();
        storeMsg.setStoreAddress(province+city+area+storeAddress);
        order.setStoreMsg(storeMsg);
        //统计订单的商品总数量
        int cntSum = 0;
        List<OrderDetailed> orderDetList = order.getOrderDetList();
        for (OrderDetailed orderDetailed : orderDetList) {
            cntSum = cntSum + orderDetailed.getGoodsCnt();
        }
        order.setCntSum(cntSum);
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
        ArrayList<String> goodsList = new ArrayList<>();
        goodsList.add(orderDetailed.getGoodsCode());
        //查出商品库存
        List<Integer> stock = goodsMessageDao.selectGoodsStock(goodsList);
        if (stock.get(0) >= orderDetailed.getGoodsCnt()){
            //分配订单编号
            order.setOrderCode(StringUtil.getCommonCode(2));
            //初始化支付状态 默认 1已支付
            order.setPayState(1);
            //初始化订单状态为0：已付款
            order.setOrderState(0);
            //查询店铺编号
            String storeCode = userDao.findStoreCode(order.getUserCode());
            order.setStoreCode(storeCode);
            //计算支付金额
            //根据商品编号查询商品详情
            GoodsMessageVo goodsById = goodsMessageDao.findGoodsById(orderDetailed.getGoodsCode());
            double payPrice = (orderDetailed.getGoodsCnt() * goodsById.getGoodsPrice());
            //传入支付金额
            order.setPaymentMoney(payPrice);
            order.setOrderPrice(payPrice);
            orderDao.addOrder(order);
            //将订单编号放进订单详情表
            orderDetailed.setOrderCode(order.getOrderCode());
            //分配订单详情编号
            orderDetailed.setOrderDetCode(StringUtil.getCommonCode(2));
            orderDetailed.setGoodsAllPrice(payPrice);
            //创建列表
            ArrayList<OrderDetailed> orderDetaileds = new ArrayList<>();
            orderDetaileds.add(orderDetailed);
            orderDao.addOrderDetailed(orderDetaileds);
            //修改商品库与销量
            goodsMessageDao.updateGoodsStock(orderDetaileds);
            return AppResponse.success("订单新增成功");
        }
        else {
            return AppResponse.bizError("库存不足,购买失败");
        }
    }

    /**
     * demo 添加订单（购物车页）
     * @param shopcartCode
     * @return
     * @author haoao
     * @date 2020-04-10
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addOrderAndAddOrderDetailed2(String shopcartCode,String sum){
        List<String> listShopCart = Arrays.asList(shopcartCode.split(","));
        //新增订单
        Order order = new Order();
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        order.setUserCode(currentUserId);
        order.setCreateUser(currentUserId);
        //分配订单编号
        order.setOrderCode(StringUtil.getCommonCode(2));
        //初始化支付状态 默认 1已支付
        order.setPayState(1);
        //初始化订单状态为0：已付款
        order.setOrderState(0);
        //注入订单总价
        Double Sum = Double.valueOf(sum);
        order.setPaymentMoney(Sum);
        order.setOrderPrice(Sum);
        //查询店铺编号
        String storeCode = userDao.findStoreCode(currentUserId);
        order.setStoreCode(storeCode);
        //查询查购物车列表
        List<ShopCartVo> shopCartVos = shopCartDao.listShopCart(listShopCart);
        //存放商品编号
        ArrayList<String> goodsCodeList = new ArrayList<>();
        for (ShopCartVo shopCartVo : shopCartVos) {
            goodsCodeList.add(shopCartVo.getGoodsCode());
        }
        List<Integer> goodsStock = goodsMessageDao.selectGoodsStock(goodsCodeList);
        //存放订单明细对象，批量存入表中
        ArrayList<OrderDetailed> orderDetList = new ArrayList<>();
        for (int i = 0; i < shopCartVos.size(); i++) {
            //判断商品库存
            if (goodsStock.get(i) >= shopCartVos.get(i).getBuyCount()){
                OrderDetailed orderDetailed = new OrderDetailed();
                orderDetailed.setOrderCode(order.getOrderCode());
                orderDetailed.setOrderDetCode(StringUtil.getCommonCode(2));
                orderDetailed.setGoodsCode(shopCartVos.get(i).getGoodsCode());
                orderDetailed.setGoodsCnt(shopCartVos.get(i).getBuyCount());
                orderDetailed.setGoodsAllPrice(shopCartVos.get(i).getAllmoney());
                //存放到list中
                orderDetList.add(orderDetailed);
            }else {
                return AppResponse.bizError("库存不足,购买失败");
            }
        }
        //新增订单
        orderDao.addOrder(order);
        //新增订单明细
        orderDao.addOrderDetailed(orderDetList);
        //修改商品库存与销量
        goodsMessageDao.updateGoodsStock(orderDetList);
        //删除购物车商品
        shopCartDao.deleteShopCartById(listShopCart,currentUserId);
        return AppResponse.success("购买成功");
    }

    /**
     * demo 修改订单状态（确认收货）
     * @param
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(Order order){
        Integer orderState = order.getOrderState();
        //取消订单时 要把对应的商品库存 销售量 补回
        if (orderState == 5){
            String orderCode = order.getOrderCode();
            List<OrderDetailed> orderDetList = orderDao.findOderDetList(orderCode);
            goodsMessageDao.updateReGoodsStock(orderDetList);
        }
        int cnt = orderDao.updateOrderState(order);
        if (cnt == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }
}
