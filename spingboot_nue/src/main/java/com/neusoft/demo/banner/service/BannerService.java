package com.neusoft.demo.banner.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.banner.dao.BannerDao;
import com.neusoft.demo.banner.entity.Banner;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
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
    public AppResponse updateBannerState(String id,int state,String userCode){
        List<String> listCode = Arrays.asList(id.split(","));
        int cnt = bannerDao.updateBannerState(listCode,state,userCode);
        if(0 == cnt) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        return AppResponse.success("修改成功！");
    }
}
