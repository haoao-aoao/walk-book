package com.neusoft.demo.goodsMessage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.goodsMessage.dao.GoodsMessageDao;
import com.neusoft.demo.goodsMessage.entity.GoodsMessage;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.ArrayUtil;

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
     * 新增商品
     * @param goodsMessage
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods(GoodsMessage goodsMessage){
        //设置商品编号
        goodsMessage.setGoodsCode(StringUtil.getCommonCode(2));

        int cnt = goodsMessageDao.addGoods(goodsMessage);
        if(0 == cnt) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");

    }

    /**
     * 查找商品详情
     * @param goodsCode
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    public AppResponse findGoodsById(String goodsCode){
        GoodsMessage goodsById = goodsMessageDao.findGoodsById(goodsCode);
        if(goodsById == null){
            return AppResponse.notFound("没有对应的商品");
        }
        return AppResponse.success("查询成功",goodsById);
    }

    /**
     * 修改商品信息
     * @param goodsMessage
     * @return
     * @author haoao
     * @date 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsById(GoodsMessage goodsMessage){
        int count = goodsMessageDao.updateGoodsById(goodsMessage);
        if(0 == count) {
            return AppResponse.bizError("数据有更新，请刷新！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * demo 删除商品
     * @param goodsCode
     * @param userCode
     * @return
     * @Author haoao
     * @Date 2020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String goodsCode, String userCode){
        AppResponse appResponse = AppResponse.success("删除成功！");
        List<String> listCode = Arrays.asList(goodsCode.split(","));
        int count = goodsMessageDao.deleteGoods(listCode, userCode);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
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
        List<GoodsMessage> goodsMessages = goodsMessageDao.listGoods(goodsMessage);
        //包装Page对象
        PageInfo<GoodsMessage> pageDate = new PageInfo<GoodsMessage>(goodsMessages);
        return AppResponse.success("查询成功！",pageDate);
    }

    /**
     * 修改商品状态
     * @param goodsCode
     * @param goodsState
     * @param lastModfiedBy
     * @return
     * @author haoao
     * @date 2020-03-25
     **/
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsState(String goodsCode, int goodsState ,String lastModfiedBy){
        AppResponse appResponse = AppResponse.success("商品状态修改成功！");
        List<String> listCode = Arrays.asList(goodsCode.split(","));
        int count = goodsMessageDao.updateGoodsState(listCode, goodsState,lastModfiedBy);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
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
        List<GoodsMessage> goodsMessages = goodsMessageDao.goodsChoseList(goodsMessage);
        //包装Page对象
        PageInfo<GoodsMessage> pageDate = new PageInfo<GoodsMessage>(goodsMessages);
        return AppResponse.success("查询成功！",pageDate);
    }
}
