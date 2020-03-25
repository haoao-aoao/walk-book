package com.neusoft.demo.goodsSort.service;

import com.neusoft.demo.goodsSort.dao.GoodsSortDao;
import com.neusoft.demo.goodsSort.entity.GoodsSort;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务层
 * @author haoao
 * @date 2020-03-24
 */
@Service
public class GoodsService {

    @Resource
    private GoodsSortDao goodsSortDao;

    /**
     * 新增商品分类
     * @param goodsSort
     * @return
     * @author haoao
     * @date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsSoft(GoodsSort goodsSort){
        //校验分类是否存在
        int count = goodsSortDao.countGoodsSort(goodsSort);
        if(0 != count) {
            return AppResponse.bizError("商品分类已存在，请重新输入！");
        }
        goodsSort.setCateCode(StringUtil.getCommonCode(2));
        goodsSort.setIsDelete(0);
        //分配商品分类等级
        if(goodsSort.getCateCodeParent() == null){
            goodsSort.setLevel("1");
        }else {
            goodsSort.setLevel("2");
        }
        //新增商品分类
        int cnt = goodsSortDao.addGoodsSort(goodsSort);
        if(0 == cnt) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询商品分类列表
     * @param goodsSort
     * @return
     * @author haoao
     * @date 2020-03-24
     */
    public AppResponse listGoodsSort(GoodsSort goodsSort){
        List<GoodsSort> goodsSorts = goodsSortDao.listGoodsSort(goodsSort);
        return  AppResponse.success("查询成功！",goodsSorts);
    }
}
