package com.xzsd.pc.goodsSort.dao;

import com.xzsd.pc.goodsSort.entity.GoodsSort;

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
     * 新增商品分类
     * @param goodsSort 商品分类信息
     * @return
     */
    int addGoodsSort(GoodsSort goodsSort);

    /**
     * 查询商品分类列表
     * @return
     */
    List<GoodsSort> listGoodsSort();

    /**
     * 修改商品分类
     * @param goodsSort 商品分类信息
     * @return
     */
    int updateGoodsSort(GoodsSort goodsSort);

    /**
     * 查询商品分类详情
     * @param cateCode 商品分类编号
     * @return
     */
    GoodsSort selectGoodsSortById(@Param("cateCode") String cateCode);

    /**
     * 删除商品分类
     * @param listCode 选中用户编号集合
     * @param userCode 更新人
     * @return
     */
    int deleteGoodsSort(@Param("listCode") List<String> listCode, @Param("userCode") String userCode);

    /**
     * 商品分类数量
     * @param goodsSort 商品分类信息
     * @return 相同数量
     */
    int countGoodsSort(GoodsSort goodsSort);

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
    List<GoodsSort> listClassifyTwo(@Param("cateCode") String cateCode);

    /**
     * 查询商品里有无该分类的商品
     * @param cateCode
     * @return
     */
    int selectGoodsSort(@Param("cateCode") String cateCode);
}
