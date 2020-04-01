package com.neusoft.demo.hotgoods.dao;

import com.neusoft.demo.hotgoods.entity.HotGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotGoodsDao {

    /**
     * 查询序号个数
     * @param sortNo
     * @return
     */
    Integer selectHotGoodsSortNo(int sortNo);

    /**
     * 新增热门位商品
     * @param hotGoods
     * @return
     */
    int addHotGoods(HotGoods hotGoods);

    /**
     * 热门位商品列表
     * @param goodsCode
     * @param goodsName
     * @return
     */
    List<HotGoods> listHotGoods(@Param("goodsCode") String goodsCode,@Param("goodsName") String goodsName);

    /**
     * 修改热门位商品信息
     * @param hotGoods
     * @return
     */
    int updateHotGoodsById(HotGoods hotGoods);

    /**
     * 删除热门位商品
     * @param listCode
     * @param userCode
     * @return
     */
    int deleteHotGoods(@Param("listCode") List<String> listCode,@Param("userCode") String userCode);

    /**
     * 设置展示热门位商品数量
     * @param ShowNum
     * @return
     */
    int setHotGoodsShowNum(int ShowNum);
}
