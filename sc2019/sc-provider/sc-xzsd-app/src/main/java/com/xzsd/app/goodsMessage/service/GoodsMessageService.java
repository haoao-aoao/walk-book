package com.xzsd.app.goodsMessage.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.goodsMessage.dao.GoodsMessageDao;
import com.xzsd.app.goodsMessage.entity.GoodsMessage;
import com.xzsd.app.goodsMessage.entity.GoodsMessageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 业务层
 * @author haoao
 * @date 2020-03-25
 */
@Service
public class GoodsMessageService {

    @Resource
    private GoodsMessageDao goodsMessageDao;

    /**
     * 查找商品详情
     * @param goodsCode
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    public AppResponse findGoodsById(String goodsCode){
        GoodsMessageVo goodsById = goodsMessageDao.findGoodsById(goodsCode);
        if(goodsById == null){
            return AppResponse.notFound("没有对应的商品");
        }
        //更新商品浏览量
        goodsMessageDao.updateGoodsView(goodsCode);
        return AppResponse.success("查询成功",goodsById);
    }

    /**
     * demo 查询商品列表（分页）
     * @param goodsMessage
     * @return
     * @Author haoao
     * @Date 2020-03-25
     */
    public AppResponse listGoods(GoodsMessage goodsMessage){
        PageHelper.startPage(goodsMessage.getPageNum(),goodsMessage.getPageSize());
        //查询数据库
        List<GoodsMessageVo> goodsMessages = goodsMessageDao.listGoods(goodsMessage);
        //包装Page对象
        PageInfo<GoodsMessageVo> goodsMessagePageInfo = new PageInfo<>(goodsMessages);
        return AppResponse.success("查询成功！",goodsMessagePageInfo);
    }

    /**
     * demo 商品选择列表（分页）
     * @param goodsMessage
     * @return
     * @Author haoao
     * @Date 2020-03-25
     */
    public AppResponse goodsChoseList(GoodsMessage goodsMessage){
        PageHelper.startPage(goodsMessage.getPageNum(),goodsMessage.getPageSize());
        List<GoodsMessageVo> goodsMessages = goodsMessageDao.goodsChoseList(goodsMessage);
        //包装Page对象
        PageInfo<GoodsMessageVo> pageDate = new PageInfo<>(goodsMessages);
        return AppResponse.success("查询成功！",pageDate);
    }
}
