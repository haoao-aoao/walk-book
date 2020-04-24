package com.xzsd.app.goodsAppraise.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.goodsAppraise.dao.GoodsAppraiseDao;
import com.xzsd.app.goodsAppraise.entity.GoodsAppraise;
import com.xzsd.app.goodsAppraise.entity.GoodsAppraisePic;
import com.xzsd.app.goodsMessage.dao.GoodsMessageDao;
import com.xzsd.app.goodsMessage.entity.GoodsMessageVo;
import com.xzsd.app.order.dao.OrderDao;
import com.xzsd.app.order.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class GoodsAppraiseService {

    @Resource
    private GoodsAppraiseDao goodsAppraiseDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private GoodsMessageDao goodsMessageDao;

    /**
     * 新增商品评论
     * @param goodsAppraise
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsAppraise(GoodsAppraise goodsAppraise){
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        //设置评价编号
        goodsAppraise.setAppraiseCode(StringUtil.getCommonCode(2));
        goodsAppraise.setUserCode(currentUserId);
        goodsAppraise.setCreateUser(currentUserId);
        //新增商品评论
        int cnt = goodsAppraiseDao.addGoodsAppraise(goodsAppraise);
        if (cnt == 0){
            return AppResponse.bizError("新增失败");
        }
        List<GoodsAppraisePic> appraisePicList = goodsAppraise.getGoodsAppraisePic();
        //新增商品评论图片信息
        if (appraisePicList != null){
            for (GoodsAppraisePic appraisePic : appraisePicList) {
                appraisePic.setId(StringUtil.getCommonCode(2));
                appraisePic.setAppraiseCode(goodsAppraise.getAppraiseCode());
                appraisePic.setCreateUser(currentUserId);
                goodsAppraiseDao.addGoodsAppraisePic(appraisePic);
            }
        }
        //改变订单状态 已完成未评价 -> 已完成已评价
        Order order = new Order();
        order.setOrderState(4);
        order.setOrderCode(goodsAppraise.getOrderCode());
        order.setLastModfiedBy(currentUserId);
        orderDao.updateOrderState(order);
        //商品评分重新统计 1.从评论表里查出该商品的所有评论星数 2.星数的总数除以评论条数得到评价星数 3.化成两位小数 更新到商品表里
        double avgStar = goodsAppraiseDao.goodsAvgStar(goodsAppraise.getGoodsCode());
        //使用bigDecimal保留两位小数
        BigDecimal bigDecimal = new BigDecimal(avgStar);
        BigDecimal goodsStarStar = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        //更新商品星级
        goodsMessageDao.updateGoodsStar(goodsAppraise.getGoodsCode(),goodsStarStar);
        return AppResponse.success("评价成功");
    }

    /**
     * 商品评论列表
     * @param goodsAppraise
     * @return
     */
    public AppResponse listGoodsAppraiseByPage(GoodsAppraise goodsAppraise){
        List<GoodsAppraise> goodsAppraises = goodsAppraiseDao.listGoodsAppraiseByPage(goodsAppraise);
        return AppResponse.success("查询成功",getPageInfo(goodsAppraises));
    }

    /**
     * 查询评论页图片
     * @param orderCode
     * @return
     */
    public AppResponse enterGoodsAppraise(String orderCode){
        List<GoodsMessageVo> goodsMessageVos = orderDao.OrderPic(orderCode);
        return AppResponse.success("查询成功",goodsMessageVos);
    }
}
