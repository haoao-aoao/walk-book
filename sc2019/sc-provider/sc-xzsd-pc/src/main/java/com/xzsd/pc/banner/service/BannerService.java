package com.xzsd.pc.banner.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzsd.pc.banner.dao.BannerDao;
import com.xzsd.pc.banner.entity.Banner;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class BannerService {

    @Resource
    private BannerDao bannerDao;

    /**
     * 新增轮播图
     * @param banner
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addBanner(Banner banner){
        //校验轮播图序号是否唯一
        int sortNo = banner.getSortNo();
        int count = bannerDao.selectBannerSortNo(sortNo);
        if (count == 1){
            return AppResponse.bizError("新增错误,序号重复");
        }
        //校验商品是否已存在轮播图
        int goodsCnt = bannerDao.selectGoodsCnt(banner.getGoodsCode());
        if (goodsCnt != 0){
            return AppResponse.bizError("新增错误,商品已存在轮播图中");
        }
        //设置轮播图ID
        banner.setId(StringUtil.getCommonCode(2));

        int cnt = bannerDao.addBanner(banner);
        if(0 == cnt) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询轮播图列表（分页）
     * @param banner
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    public AppResponse listBanner(Banner banner){
        PageHelper.startPage(banner.getPageNum(),banner.getPageSize());
        List<Banner> listBanner = bannerDao.listBanner(banner);
        //包装Page对象
        PageInfo<Banner> pageDate = new PageInfo<Banner>(listBanner);
        return AppResponse.success("查询成功！",pageDate);
    }

    /**
     * 删除轮播图
     * @param id
     * @param userCode
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteBanner(String id,String userCode){
        List<String> listCode = Arrays.asList(id.split(","));
        int cnt = bannerDao.deleteBanner(listCode, userCode);
        if(0 == cnt) {
            return AppResponse.bizError("删除失败，请重试！");
        }
        return AppResponse.success("删除成功！");
    }

    /**
     * 修改轮播图状态
     * @param id
     * @param userCode
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateBannerState(String id,int state,String userCode,String version){
        List<String> listCode = Arrays.asList(id.split(","));
        List<String> listVersion = Arrays.asList(version.split(","));
        int cnt = bannerDao.updateBannerState(listCode,state,userCode,listVersion);
        if(0 == cnt) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        return AppResponse.success("修改成功！");
    }
}
