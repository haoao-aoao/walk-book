package com.xzsd.app.hotgoods.dao;

import com.xzsd.app.hotgoods.entity.HotGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotGoodsDao {

    /**
     * 设置展示热门位商品数量
     * @param showNum
     * @return
     */
    List<HotGoods> listHotGoodsByPage(@Param("showNum") int showNum);


    /**
     * 查询字典表里热门位商品展示数目
     * @return
     */
    String selectCount();
}
