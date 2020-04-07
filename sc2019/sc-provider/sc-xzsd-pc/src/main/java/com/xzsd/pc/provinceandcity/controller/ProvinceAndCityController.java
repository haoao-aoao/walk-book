package com.xzsd.pc.provinceandcity.controller;

import com.xzsd.pc.provinceandcity.service.ProvinceAndCityService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/sys")
@RestController
public class ProvinceAndCityController {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceAndCityController.class);

    @Resource
    private ProvinceAndCityService provinceAndCityService;

    /**
     * 省列表
     * @return
     */
    @RequestMapping("/listProvince")
    public AppResponse listProvince(){
        try{
            return provinceAndCityService.listProvince();
        }catch (Exception e){
            logger.error("省列表查询异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 市列表
     * @return
     */
    @RequestMapping("/listCity")
    public AppResponse listCity(String provinceCode){
        try{
            return provinceAndCityService.listCity(provinceCode);
        }catch (Exception e){
            logger.error("市列表查询异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 区列表
     * @return
     */
    @RequestMapping("/listArea")
    public AppResponse listArea(String cityCode){
        try{
            return provinceAndCityService.listArea(cityCode);
        }catch (Exception e){
            logger.error("区列表查询异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
