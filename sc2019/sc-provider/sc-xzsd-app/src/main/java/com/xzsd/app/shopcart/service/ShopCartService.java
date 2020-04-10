package com.xzsd.app.shopcart.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.shopcart.dao.ShopCartDao;
import com.xzsd.app.shopcart.entity.ShopCart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class ShopCartService {

    @Resource
    private ShopCartDao shopCartDao;

    /**
     * 新增购物车商品
     * @param shopCart
     * @return
     */
    public AppResponse addShopCart(ShopCart shopCart){
        shopCart.setShopcartCode(StringUtil.getCommonCode(2));
        //检测购物车是否已存在该商品
        Integer cnt = shopCartDao.selectgoods(shopCart.getGoodsCode());
        if (cnt == 0){
            int count = shopCartDao.addShopCart(shopCart);
            if (count == 0){
                return AppResponse.bizError("新增失败");
            }else {
                return AppResponse.success("新增成功");
            }
        }else {
            return AppResponse.bizError("该商品已存在购物车");
        }
    }

    /**
     * 修改购物车商品数量
     * @param shopCart
     * @return
     */
    public AppResponse updateShopCartBuyCount(ShopCart shopCart){
        int cnt = shopCartDao.updateShopCartBuyCount(shopCart);
        if (cnt == 0){
            return AppResponse.bizError("修改失败");
        }else {
            return AppResponse.success("修改成功");
        }
    }

    /**
     * 删除购物车商品
     * @param shopcartCode
     * @param userCode
     * @return
     */
    public AppResponse deleteShopCartById(String shopcartCode,String userCode){
        List<String> listCode = Arrays.asList(shopcartCode.split(","));
        int cnt = shopCartDao.deleteShopCartById(listCode, userCode);
        if (cnt == 0){
            return AppResponse.bizError("删除失败");
        }else {
            return AppResponse.success("删除成功");
        }
    }

    /**
     * 查询购物车列表
     * @param userCode
     * @return
     */
    public AppResponse listShopCartByPage(String userCode){
        List<ShopCart> shopCarts = shopCartDao.listShopCartByPage(userCode);
        return AppResponse.success("查询成功", getPageInfo(shopCarts));
    }
}
