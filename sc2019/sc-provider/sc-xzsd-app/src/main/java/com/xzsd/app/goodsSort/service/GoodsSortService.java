package com.xzsd.app.goodsSort.service;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.goodsSort.dao.GoodsSortDao;
import com.xzsd.app.goodsSort.entity.GoodsSort;
import com.xzsd.app.goodsSort.entity.GoodsSortVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 业务层
 * @author haoao
 * @date 2020-03-24
 */
@Service
public class GoodsSortService {

    @Resource
    private GoodsSortDao goodsSortDao;



    /**
     * 查询商品分类列表
     * @return
     * @author haoao
     * @date 2020-03-24
     */
    public AppResponse listGoodsSort(){
        List<GoodsSort> goodsSorts = goodsSortDao.listGoodsSort();
        return  AppResponse.success("查询成功！",goodsSorts);
    }

    /**
     * 商品一级分类列表查询
     * @return
     */
    public AppResponse listClassifyOne(){
        List<GoodsSort> classifyOneList = goodsSortDao.listClassifyOne();
        return AppResponse.success("查询成功",classifyOneList);
    }

    /**
     * 商品二级分类列表查询
     * @param cateCode
     * @return
     */
    public AppResponse listClassifyTwo(String cateCode){
        List<GoodsSortVo> listClassifyTwo = goodsSortDao.listClassifyTwo(cateCode);
        return AppResponse.success("查询成功",listClassifyTwo);
    }
}
