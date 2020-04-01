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
import java.util.Arrays;
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

    /**
     * 修改热门位商品
     * @param hotGoods
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoodsById(HotGoods hotGoods){
        //检测序号是否重复
        Integer sortNo = hotGoods.getSortNo();
        Integer cnt = hotGoodsDao.selectHotGoodsSortNo(sortNo);
        if (cnt == 1){
            return AppResponse.bizError("序号重复，请重试");
        }
        int count = hotGoodsDao.updateHotGoodsById(hotGoods);
        if (count == 0){
            return AppResponse.bizError("数据有更新，请重试");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 删除热门位商品
     * @param hotgoodsCode
     * @param userCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteHotGoods(String hotgoodsCode,String userCode){
        List<String> listCode = Arrays.asList(hotgoodsCode.split(","));
        int cnt = hotGoodsDao.deleteHotGoods(listCode, userCode);
        if (cnt == 0){
            return AppResponse.bizError("删除失败，请重试");
        }
        return AppResponse.success("删除成功");
    }

    /**
     * 查询热门位商品详情
     * @param hotgoodsCode
     * @return
     */
    public AppResponse selectHotGoods(String hotgoodsCode){
        HotGoods hotGoods = hotGoodsDao.selectHotGoods(hotgoodsCode);
        return AppResponse.success("查询热门位商品详情成功",hotGoods);
    }

    /**
     * 查询设置展示热门位商品
     * @param showNum
     * @return
     */
    public AppResponse setHotGoodsShowNum(int showNum){
        Integer count = hotGoodsDao.selectCount();
        List<HotGoods> hotGoodsList;
        if (showNum <= count){
            hotGoodsList = hotGoodsDao.setHotGoodsShowNum(showNum);
        }else {
            return AppResponse.bizError("展示数目大于有效数目");
        }
        return AppResponse.success("设置成功",hotGoodsList);
    }
}
