package com.xzsd.app.banner.dao;

import com.xzsd.app.banner.entity.Banner;

import java.util.List;

public interface BannerDao {

    /**
     * 查询轮播图列表
     * @param
     * @return
     */
    List<Banner> listBanner();
}
