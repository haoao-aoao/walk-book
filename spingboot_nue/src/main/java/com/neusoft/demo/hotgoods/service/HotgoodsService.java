package com.neusoft.demo.hotgoods.service;

import com.neusoft.demo.hotgoods.dao.HotGoodsDao;
import com.neusoft.demo.hotgoods.entity.HotGoods;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
}
