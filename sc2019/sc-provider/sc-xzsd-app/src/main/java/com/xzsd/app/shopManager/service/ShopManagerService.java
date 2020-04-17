package com.xzsd.app.shopManager.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.order.dao.OrderDao;
import com.xzsd.app.order.entity.Order;
import com.xzsd.app.order.entity.OrderDetailed;
import com.xzsd.app.shopManager.dao.ShopManagerDao;
import com.xzsd.app.store.dao.StoreDao;
import com.xzsd.app.store.entity.Store;
import com.xzsd.app.user.entity.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class ShopManagerService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private ShopManagerDao shopManagerDao;

    @Resource
    private StoreDao storeDao;

    /**
     * 查询店长订单列表
     * @param order
     * @return
     */
    public AppResponse findOrderlistByPage(Order order){
        Store store = storeDao.selectInviteCode(order.getUserCode());
        order.setInvitationCode(store.getInvitationCode());
        List<Order> orderlist = shopManagerDao.findOrderlistByPage(order);
        //创建一个新list
        List<Order> newList = new ArrayList<Order>();
        //筛选订单详情不为空的 放进新list
        for (Order listOrder : orderlist) {

            if (!listOrder.getOrderDetList().isEmpty()&&listOrder.getOrderDetList() != null){
                newList.add(listOrder);
            }
        }
        //统计订单的商品总数量
        for (Order order1 : newList) {
            int cntSum = 0;
            List<OrderDetailed> orderDetList = order1.getOrderDetList();
            for (OrderDetailed orderDetailed : orderDetList) {
                cntSum = cntSum + orderDetailed.getGoodsCnt();
            }
            order1.setCntSum(cntSum);
        }
        //分页封装
        PageHelper.startPage(order.getPageNum(),order.getPageSize());
        PageInfo<Order> orderPageInfo = new PageInfo<>(newList);
        return AppResponse.success("查询成功",orderPageInfo);
    }

    /**
     * demo 查询订单详情列表
     * @param orderCode
     * @return
     * @author haoao
     * @date 2020-04-15
     */
    public AppResponse listOrderDetById(String orderCode){
        Order order = shopManagerDao.OrderDetById(orderCode);
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
     * 店长端修改订单状态
     * @param order
     * @return
     */
    public AppResponse updateOrderStateShopMr(Order order){
        int cnt = orderDao.updateOrderState(order);
        if (cnt == 0){
            return AppResponse.bizError("修改失败");
        }else {
            return AppResponse.success("修改成功");
        }
    }

    /**
     * 查询司机信息
     * @param userCode
     * @return
     */
    public AppResponse findDriverManage(String userCode){
        List<UserVo> driverManage = shopManagerDao.findDriverManage(userCode);
        return AppResponse.success("查询成功",driverManage);
    }
}
