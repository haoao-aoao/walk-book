package com.xzsd.pc.goodsMessage.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xzsd.pc.goodsMessage.dao.GoodsMessageDao;
import com.xzsd.pc.goodsMessage.entity.GoodsMessage;
import com.xzsd.pc.goodsMessage.entity.GoodsMessageVo;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.RandomUtil;
import com.xzsd.pc.util.RedisOperator;
import com.xzsd.pc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private RedisOperator redisOperator;

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
        //设置书号
        goodsMessage.setGoodsNo("ISBN"+ RandomUtil.radmonkey(6));
        //浏览量
        goodsMessage.setGoodsView(0);
        //销售量
        goodsMessage.setSalesVolume(0);
        //星数 初始3星
        goodsMessage.setGoodsStar(3);
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
        GoodsMessageVo goodsById = goodsMessageDao.findGoodsById(goodsCode);
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
        /**
         * 删除之前检查所选的商品是否在热门位 或是 在轮播图的 如果是 则不允许删除
         */
        AppResponse appResponse = AppResponse.success("删除成功！");
        List<String> listCode = Arrays.asList(goodsCode.split(","));
        //新建一个list存放可被删除的商品code
        ArrayList<String> newListCode = new ArrayList<>();
        for (String goods : listCode) {
            //cnt为0则不存在热门位 或是 在轮播图
            int cnt = goodsMessageDao.selectGoods(goods);
            if (cnt == 0){
                newListCode.add(goods);
            }
        }
        int count = goodsMessageDao.deleteGoods(newListCode, userCode);
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
        //查询数据库
        List<GoodsMessageVo> goodsMessages = goodsMessageDao.listGoods(goodsMessage);
        //包装Page对象
        PageInfo<GoodsMessageVo> goodsMessagePageInfo = new PageInfo<>(goodsMessages);
        return AppResponse.success("查询成功！",goodsMessagePageInfo);
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
        List<GoodsMessageVo> goodsMessages = goodsMessageDao.goodsChoseList(goodsMessage);
        //包装Page对象
        PageInfo<GoodsMessageVo> pageDate = new PageInfo<>(goodsMessages);
        return AppResponse.success("查询成功！",pageDate);
    }
}
