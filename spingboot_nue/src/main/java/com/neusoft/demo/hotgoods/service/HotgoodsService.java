package com.neusoft.demo.hotgoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.goodsMessage.entity.GoodsMessage;
import com.neusoft.demo.hotgoods.dao.HotGoodsDao;
import com.neusoft.demo.hotgoods.entity.HotGoods;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotgoodsService {

    @Resource
    private HotGoodsDao hotGoodsDao;

    /**
     * 新增热门位商品
     * @param hotGoods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addHotGoods(HotGoods hotGoods){
        Integer sortNo = hotGoods.getSortNo();
        Integer cnt = hotGoodsDao.selectHotGoodsSortNo(sortNo);
        if (cnt == 1){
            return AppResponse.bizError("序号重复，请重试");
        }
        hotGoods.setHotgoodsCode(StringUtil.getCommonCode(2));
        int count = hotGoodsDao.addHotGoods(hotGoods);
        if (count == 0){
            return AppResponse.bizError("新增失败,请重试");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询热门位商品列表
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listHotGoods(String goodsCode,String goodsName,HotGoods hotGoods){
        PageHelper.startPage(hotGoods.getPageNum(),hotGoods.getPageSize());
        List<HotGoods> hotGoodsList = hotGoodsDao.listHotGoods(goodsCode,goodsName);
        //分页包装
        PageInfo<HotGoods> hotGoodsPageInfo = new PageInfo<>(hotGoodsList);
        return AppResponse.success("查询成功！",hotGoodsPageInfo);
    }
}
