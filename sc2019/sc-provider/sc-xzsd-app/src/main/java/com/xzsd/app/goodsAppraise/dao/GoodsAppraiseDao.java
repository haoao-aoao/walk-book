package com.xzsd.app.goodsAppraise.dao;

import com.xzsd.app.goodsAppraise.entity.GoodsAppraise;
import com.xzsd.app.goodsAppraise.entity.GoodsAppraisePic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsAppraiseDao {
    /**
     * 新增商品评价
     * @param goodsAppraiseList
     * @return
     */
    int addGoodsAppraise(@Param("goodsAppraiseList") List<GoodsAppraise> goodsAppraiseList);

    /**
     * 新增商品评价图片
     * @param goodsAppraisePic
     * @return
     */
    int addGoodsAppraisePic(GoodsAppraisePic goodsAppraisePic);

    /**
     * 列表查询商品评价
     * @param goodsAppraise
     * @return
     */
    List<GoodsAppraise> listGoodsAppraiseByPage(GoodsAppraise goodsAppraise);

    /**
     * 查询商品评价图片
     * @return
     */
    GoodsAppraisePic selectAppraisePic();

    /**
     * 计算商品平均星数
     * @param goodsCode
     * @return
     */
    double goodsAvgStar(String goodsCode);

}
