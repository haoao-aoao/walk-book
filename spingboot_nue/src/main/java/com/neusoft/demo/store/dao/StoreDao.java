package com.neusoft.demo.store.dao;

import com.neusoft.demo.store.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreDao {
    /**
     * 新增门店
     * @param store
     * @return
     */
    int addStore(Store store);

    /**
     * 查询门店详情
     * @param storeCode
     * @return
     */
    Store findStoreById(@Param("storeCode") String storeCode);

    /**
     * 修改门店信息
     * @param store
     * @return
     */
    int updateStore(Store store);

    /**
     * 删除门店
     * @param listCode
     * @param userCode
     * @return
     */
    int deleteStore(@Param("listCode") List<String> listCode,@Param("userCode") String userCode);

    /**
     * 查询门店信息列表
     * @param store
     * @return
     */
    List<Store> listStore(Store store);
}
