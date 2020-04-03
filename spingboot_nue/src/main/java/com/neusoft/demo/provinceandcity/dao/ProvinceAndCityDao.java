package com.neusoft.demo.provinceandcity.dao;

import com.neusoft.demo.provinceandcity.entity.AreaVo;
import com.neusoft.demo.provinceandcity.entity.CityVo;
import com.neusoft.demo.provinceandcity.entity.ProvinceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProvinceAndCityDao {
    /**
     * 省列表
     * @return
     */
    List<ProvinceVo> listProvince();

    /**
     * 市列表
     * @return
     */
    List<CityVo> listCity(@Param("provinceCode") String provinceCode);

    /**a
     * 区列表
     * @return
     */
    List<AreaVo> listArea(@Param("cityCode") String cityCode);
}
