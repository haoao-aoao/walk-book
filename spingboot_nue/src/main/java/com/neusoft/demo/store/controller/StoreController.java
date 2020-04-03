package com.neusoft.demo.store.controller;

import com.neusoft.demo.store.entity.Store;
import com.neusoft.demo.store.service.StoreService;
import com.neusoft.demo.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys/store")
public class StoreController {

    public static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;

    /**
     * 新增门店
     * @param store
     * @return
     */
    @PostMapping("addStore")
    public AppResponse addStore(Store store){
        try{
            return storeService.addStore(store);
        }catch (Exception e){
            logger.error("新增异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店详情
     * @param storeCode
     * @return
     */
    @RequestMapping("findStoreById")
    public AppResponse findStoreById(String storeCode){
        try{
            return storeService.findStoreById(storeCode);
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
     * 删除门店
     * @param storeCode
     * @param userCode
     * @return
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStore(String storeCode, String userCode){
        try{
            return storeService.deleteStore(storeCode,userCode);
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
