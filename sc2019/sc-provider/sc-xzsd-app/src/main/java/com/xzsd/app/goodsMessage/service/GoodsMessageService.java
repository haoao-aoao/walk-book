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
    /*
    public AppResponse listGoods(GoodsMessage goodsMessage){

        //创建key值
        String listGoodsKey =    goodsMessage.getGoodsName()
                                +goodsMessage.getGoodsState()
                                +goodsMessage.getGoodsPress()
                                +goodsMessage.getGoodsAuthor()
                                +goodsMessage.getPageNum()
                                +goodsMessage.getPageSize();
        //判断redis里是否有数据
        if (redisOperator.ttl(listGoodsKey) > 0){
            //Json字符串转列表
            List<GoodsMessage> goodsMessages = JSON.parseArray(redisOperator.get(listGoodsKey), GoodsMessage.class);
            //包装Page对象
            PageInfo<GoodsMessage> pageDate = new PageInfo<GoodsMessage>(goodsMessages);
            return AppResponse.success("从radis查询成功",pageDate);
        }else{
            PageInfo<GoodsMessage> pageDate = null;
            //页码和条数不为空
            if(goodsMessage.getPageSize() != null && goodsMessage.getPageNum() != null){
                PageHelper.startPage(goodsMessage.getPageNum(),goodsMessage.getPageSize());
                //查询数据库
                List<GoodsMessage> goodsMessages = goodsMessageDao.listGoods(goodsMessage);
                //list转json
                String toJson = JSON.toJSONString(goodsMessages);
                //存入redis,存活时间为5分钟
                redisOperator.set(listGoodsKey,toJson,300);
                //包装Page对象
                pageDate = new PageInfo<GoodsMessage>(goodsMessages);
            }
            if (pageDate == null){
                return AppResponse.bizError("查询错误，PageSize与PageNum为null");
            }else{
                return AppResponse.success("从数据库查询成功！",pageDate);
            }
        }
    }*/
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
