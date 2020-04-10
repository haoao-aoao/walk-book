package com.xzsd.app.hotgoods.service;


import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.hotgoods.dao.HotGoodsDao;
import com.xzsd.app.hotgoods.entity.HotGoods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class HotgoodsService {

    @Resource
    private HotGoodsDao hotGoodsDao;

    /**
     * 查询热门位商品列表
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listHotGoodsByPage(){
        String showNum = hotGoodsDao.selectCount();
        List<HotGoods> hotGoodsList = hotGoodsDao.listHotGoodsByPage(Integer.valueOf(showNum));
        return AppResponse.success("查询成功！",getPageInfo(hotGoodsList));
    }


}
