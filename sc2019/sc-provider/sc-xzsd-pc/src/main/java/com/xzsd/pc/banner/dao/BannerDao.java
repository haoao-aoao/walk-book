package com.xzsd.pc.banner.dao;

import com.xzsd.pc.banner.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    /**
     * 查询轮播图序号数量
     * @param sotrNo
     * @return
     */
    int selectBannerSortNo(int sotrNo);
    /**
     * 新增轮播图
     * @param banner
     * @return
     */
    int addBanner(Banner banner);

    /**
     * 查询轮播图列表
     * @param banner
     * @return
     */
    List<Banner> listBanner(Banner banner);

    /**
     * 删除轮播图
     * @param listCode
     * @param userCode
     * @return
     */
    int deleteBanner(@Param("listCode") List<String> listCode, @Param("userCode") String userCode);

    /**
     * 修改轮播图状态
     * @param listCode
     * @param state
     * @return
     */
    int updateBannerState(@Param("listCode") List<String> listCode, @Param("state") int state, @Param("userCode") String userCode, @Param("listVersion") List<String> listVersion);
}
