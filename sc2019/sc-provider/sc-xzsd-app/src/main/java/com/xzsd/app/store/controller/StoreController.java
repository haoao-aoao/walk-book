package com.xzsd.app.store.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.store.entity.Store;
import com.xzsd.app.store.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/store")
public class StoreController {

    public static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;

    /**
     * 查询门店详情
     * @return
     */
    @RequestMapping("findStoreById")
    public AppResponse findStoreById(){
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            return storeService.findStoreById(currentUserId);
        }catch (Exception e){
            logger.error("查询异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改门店信息
     * @param store
     * @return
     */
    @PostMapping("updateStore")
    public AppResponse updateStore(Store store){
        try{
            return storeService.updateStore(store);
        }catch (Exception e){
            logger.error("修改门店异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * 门店列表查询
     * @param store
     * @return
     */
    @RequestMapping("listStore")
    public AppResponse listStore(Store store){
        try{
            return storeService.listStore(store);
        }catch (Exception e){
            logger.error("修改门店异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
