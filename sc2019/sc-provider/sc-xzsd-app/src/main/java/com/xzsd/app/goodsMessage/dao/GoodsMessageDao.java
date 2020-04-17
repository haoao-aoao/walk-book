package com.xzsd.app.goodsMessage.dao;


import com.xzsd.app.goodsMessage.entity.GoodsMessage;
import com.xzsd.app.goodsMessage.entity.GoodsMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息mapper
 * @author haoao
 * @date 2020-03-25
 */
public interface GoodsMessageDao {
    /**
     * 查询商品详情
     * @param goodsCode
     * @return
     */
    GoodsMessageVo findGoodsById(@Param("goodsCode") String goodsCode);

    /**
     * 查询商品列表
     * @return
     */
    List<GoodsMessageVo> listGoods(GoodsMessage goodsMessage);

    /**
     * 查询商品选择列表
     * @param goodsMessage
     * @return
     */
    List<GoodsMessageVo> goodsChoseList(GoodsMessage goodsMessage);

    /**
     * 更新商品销量
     * @param count
     * @return
     */
    int updateGoodsSalesVolume(String goodsCode,int count);

    /**
     * 更新商品浏览量
     * @param goodsCode
     * @return
     */
    int updateGoodsView(String goodsCode);

    /**
     * 更新商品库存
     * @param goodsCode
     * @param count
     * @return
     */
    int updateGoodsStock(String goodsCode,int count);

    /**
     * 查询商品库存
     * @param goodsCode
     * @return
     */
    int selectGoodsStock(String goodsCode);

    /**
     * 更新商品星级
     * @param goodsStar
     * @return
     */
    int updateGoodsStar(String goodsCode, BigDecimal goodsStar);
}
