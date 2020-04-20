package com.xzsd.pc.order.service;

import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.Order;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.Store;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.UserVo;
import com.xzsd.pc.util.AppResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StoreDao storeDao;

    @Resource
    private UserDao userDao;

    /**
     * demo 查询订单列表
     * @param order
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    public AppResponse listsOrderByPage(Order order){
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        UserVo iuser = userDao.getUserByUserCode(currentUserId);
        Integer role = iuser.getRole();
        List<Order> orderList = orderDao.listsOrder(order);
        if (role == 0){
            return AppResponse.success("管理员显示全部订单",getPageInfo(orderList));
        }
        //获取当前人门店邀请码
        Store store = storeDao.selectInviteCode(currentUserId);
        String invitationCode = store.getInvitationCode();
        order.setInvitationCode(invitationCode);
        List<Order> orderList1 = orderDao.listsOrder(order);
        return AppResponse.success("店长订单",getPageInfo(orderList1));
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
     * demo 修改订单状态
     * @param
     * @return
     * @author haoao
     * @date 2020-03-30
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(int orderState,String orderCode,String userCode,String version){
        List<String> listCode = Arrays.asList(orderCode.split(","));
        List<String> listVersion = Arrays.asList(version.split(","));
        int cnt = orderDao.updateOrderState(orderState, listCode, userCode, listVersion);
        if (cnt == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }
}
