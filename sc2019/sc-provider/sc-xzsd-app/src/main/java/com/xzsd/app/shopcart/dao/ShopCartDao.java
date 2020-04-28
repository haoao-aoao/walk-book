package com.xzsd.app.shopcart.dao;

import com.xzsd.app.shopcart.entity.ShopCart;
import com.xzsd.app.shopcart.entity.ShopCartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCartDao {
    /**
     * 查询商品数量
     * @param goodsCode
     * @return
     */
    Integer selectgoods(@Param("goodsCode") String goodsCode,@Param("userCode") String userCode);
    /**
     * 新增购物车
     * @param shopCart
     * @return
     */
    int addShopCart(ShopCart shopCart);

    /**
     * 修改购物车商品数量
     * @param shopCart
     * @return
     */
    int updateShopCartBuyCount(ShopCart shopCart);

    /**
     * 删除购物车商品
     * @param listCode
     * @return
     */
    int deleteShopCartById(@Param("listCode") List<String> listCode,@Param("userCode") String userCode);

    /**
     * 查询购物车列表
     * @return
     */
    List<ShopCart> listShopCartByPage(String userCode);

    /**
     * 查询购物车列表（用于新增订单）
     * @param listCode
     * @return
     */
    List<ShopCartVo> listShopCart(@Param("listCode") List<String> listCode);
}
