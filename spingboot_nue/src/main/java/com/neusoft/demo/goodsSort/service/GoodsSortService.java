package com.neusoft.demo.goodsSort.service;

import com.alibaba.fastjson.JSON;
import com.neusoft.demo.goodsSort.dao.GoodsSortDao;
import com.neusoft.demo.goodsSort.entity.GoodsSort;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;
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

    /*消息队列配置
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    // 发送消息，destination是发送到的队列，message是待发送的消息
    private void sendMessage(Destination destination, String goodsSort){
        jmsMessagingTemplate.convertAndSend(destination, goodsSort);
    }*/

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
        //goodsSort.setIsDelete(0);
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
        //生产者传消息
        String goodsSortJson = JSON.toJSONString(goodsSort);
        //sendMessage(this.queue,goodsSortJson);
        System.out.println("成功发送新增的商品实体类");
        return AppResponse.success("新增成功！");
    }

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
     * 修改商品分类
     * @param goodsSort
     * @return
     * @author haoao
     * @date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsSort(GoodsSort goodsSort){
        AppResponse appResponse = AppResponse.success("修改成功");
        //校验分类是否存在
        int count = goodsSortDao.countGoodsSort(goodsSort);
        if(0 != count) {
            return AppResponse.bizError("商品分类已存在，请重新输入！");
        }
        // 修改用户信息
        int cnt = goodsSortDao.updateGoodsSort(goodsSort);
        if (0 == cnt) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * 查询商品分类详情
     * @param cateCode
     * @return
     * @author haoao
     * @date 2020-03-24
     */
    public AppResponse selectGoodsSortById(String cateCode){
        GoodsSort goodsSort = goodsSortDao.selectGoodsSortById(cateCode);
        int cnt = goodsSortDao.countGoodsSort(goodsSort);
        if(cnt == 0){
            return AppResponse.bizError("查询失败，没有对应的商品分类");
        }
        return AppResponse.success("查询成功",goodsSort);
    }

    /**
     * 删除商品分类
     * @param cateCode
     * @param userCode
     * @return
     * @author haoao
     * @date 2020-03-24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoodsSort(String cateCode,String userCode){
        List<String> listCode = Arrays.asList(cateCode.split(","));
        AppResponse appResponse = AppResponse.success("删除成功");
        //删除商品分类
        int count = goodsSortDao.deleteGoodsSort(listCode,userCode);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
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
        List<GoodsSort> classifyTwoList = goodsSortDao.listClassifyTwo(cateCode);
        return AppResponse.success("查询成功",classifyTwoList);
    }
}
