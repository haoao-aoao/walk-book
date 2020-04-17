package com.xzsd.app.shopcart.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.goodsMessage.dao.GoodsMessageDao;
import com.xzsd.app.goodsMessage.entity.GoodsMessageVo;
import com.xzsd.app.shopcart.dao.ShopCartDao;
import com.xzsd.app.shopcart.entity.ShopCart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class ShopCartService {

    @Resource
    private ShopCartDao shopCartDao;

    @Resource
    private GoodsMessageDao goodsMessageDao;

    /**
     * 新增购物车商品
     * @param shopCart
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addShopCart(ShopCart shopCart){
        shopCart.setShopcartCode(StringUtil.getCommonCode(2));
        //检测购物车是否已存在该商品
        Integer cnt = shopCartDao.selectgoods(shopCart.getGoodsCode());
        if (cnt == 0){
            //计算商品的价格
            GoodsMessageVo goodsMsg = goodsMessageDao.findGoodsById(shopCart.getGoodsCode());
            double monery = shopCart.getBuyCount()*goodsMsg.getGoodsPrice();
            shopCart.setAllmoney(monery);
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
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateShopCartBuyCount(ShopCart shopCart){
        Integer buyCount = shopCart.getBuyCount();
        if (buyCount >= 1){
            //计算购物车商品总价
            double monery = shopCart.getBuyCount()*shopCart.getGoodsPrice();
            shopCart.setAllmoney(monery);
            int cnt = shopCartDao.updateShopCartBuyCount(shopCart);
            if (cnt == 0){
                return AppResponse.bizError("修改失败");
            }else {
                return AppResponse.success("修改成功");
            }
        }else {
            return AppResponse.bizError("购买数量最少为1");
        }
    }

    /**
     * 删除购物车商品
     * @param shopcartCode
     * @param userCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 购物车商品合计金额
     * @param allmoney
     * @return
     */
    public AppResponse shopCartMoney(String allmoney){
        List<String> listMoney = Arrays.asList(allmoney.split(","));
        double sum = 0;
        for (String money : listMoney) {
            Double mon = Double.valueOf(money);
            sum = sum + mon;
        }
        return AppResponse.success("合计金额","sum : " + sum);
    }
}
