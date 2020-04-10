package com.xzsd.app.goodsSort.dao;

import com.xzsd.app.goodsSort.entity.GoodsSort;
import com.xzsd.app.goodsSort.entity.GoodsSortVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className GoodsSortDao
 * @Description GoodsSort
 * @Author haoao
 * @Date 2020-03-24
 */
//@Mapper
public interface GoodsSortDao {


    /**
     * 查询商品分类列表
     * @return
     */
    List<GoodsSort> listGoodsSort();

    /**
     * 一级商品分类列表
     * @return
     */
    List<GoodsSort> listClassifyOne();

    /**
     * 二级商品分类列表
     * @Param careCode
     * @return
     */
    List<GoodsSortVo> listClassifyTwo(@Param("cateCode") String cateCode);
}
