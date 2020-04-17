package com.xzsd.pc.store.controller;

import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.store.entity.Store;
import com.xzsd.pc.store.service.StoreService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 门店Controller层
 */
@RestController
@RequestMapping("/store")
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
            String currentUserId = SecurityUtils.getCurrentUserId();
            store.setCreateUser(currentUserId);
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
            String currentUserId = SecurityUtils.getCurrentUserId();
            store.setLastModfiedBy(currentUserId);
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
     * @return
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStore(String storeCode){
        try{
            String currentUserId = SecurityUtils.getCurrentUserId();
            return storeService.deleteStore(storeCode,currentUserId);
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
