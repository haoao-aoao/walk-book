package com.xzsd.pc.provinceandcity.service;

import com.xzsd.pc.provinceandcity.dao.ProvinceAndCityDao;
import com.xzsd.pc.provinceandcity.entity.AreaVo;
import com.xzsd.pc.provinceandcity.entity.CityVo;
import com.xzsd.pc.provinceandcity.entity.ProvinceVo;
import com.xzsd.pc.util.AppResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProvinceAndCityService {

    @Resource
    private ProvinceAndCityDao provinceAndCityDao;

    /**
     * 省列表
     * @return
     */
    public AppResponse listProvince(){
        List<ProvinceVo> provinceVos = provinceAndCityDao.listProvince();
        return AppResponse.success("省列表查询成功",provinceVos);
    }

    /**
     * 市列表
     * @return
     */
    public AppResponse listCity(String provinceCode){
        List<CityVo> cityVos = provinceAndCityDao.listCity(provinceCode);
        return AppResponse.success("省列表查询成功",cityVos);
    }

    /**
     * 市列表
     * @return
     */
    public AppResponse listArea(String cityCode){
        List<AreaVo> areaVos = provinceAndCityDao.listArea(cityCode);
        return AppResponse.success("省列表查询成功",areaVos);
    }
}
