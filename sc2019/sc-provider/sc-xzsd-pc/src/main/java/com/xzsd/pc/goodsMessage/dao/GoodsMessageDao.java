package com.xzsd.pc.goodsMessage.dao;


import com.xzsd.pc.goodsMessage.entity.GoodsMessage;
import com.xzsd.pc.goodsMessage.entity.GoodsMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息mapper
 * @author haoao
 * @date 2020-03-25
 */
@Mapper
public interface GoodsMessageDao {

    /**
     * 新增商品
     * @param goodsMessage 商品信息
     * @return
     */
    int addGoods(GoodsMessage goodsMessage);

    /**
     * 修改商品信息
     * @param goodsMessage
     * @return
     */
    int updateGoodsById(GoodsMessage goodsMessage);

    /**
     * 删除商品
     * @param listCode
     * @param userCode
     * @return
     */
    int deleteGoods(@Param("listCode") List<String> listCode, @Param("userCode") String userCode);

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
     * 修改商品状态
     * @param listCode 商品编号
     * @param goodsState 商品状态
     * @return
     */
    int updateGoodsState(@Param("listCode") List<String> listCode, @Param("goodsState") int goodsState, @Param("lastModfiedBy") String lastModfiedBy);

    /**
     * 查询商品选择列表
     * @param goodsMessage
     * @return
     */
    List<GoodsMessageVo> goodsChoseList(GoodsMessage goodsMessage);
}
