package com.xzsd.app.banner.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.banner.dao.BannerDao;
import com.xzsd.app.banner.entity.Banner;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerService {

    @Resource
    private BannerDao bannerDao;

    /**
     * 查询轮播图列表（分页）
     * @return
     * @author haoao
     * @date 2020-03-26
     */
    public AppResponse listBanner(){
        List<Banner> listBanner = bannerDao.listBanner();
        return AppResponse.success("查询成功！",listBanner);
    }

}
