package com.xzsd.app.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.store.dao.StoreDao;
import com.xzsd.app.store.entity.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreService {

    @Resource
    private StoreDao storeDao;

    /**
     * 查询门店详情
     * @param userCode
     * @return
     */
    public AppResponse findStoreById(String userCode){
        Store storeById = storeDao.findStoreById(userCode);
        if (storeById == null){
            return AppResponse.success("未绑定门店邀请码");
        }
        return AppResponse.success("查询成功",storeById);
    }

    /**
     * 修改门店信息
     * @param store
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStore(Store store){
        int cnt = storeDao.updateStore(store);
        if (cnt == 0){
            return AppResponse.bizError("修改失败,数据有更新");
        }
        return AppResponse.success("门店修改成功");
    }

    /**
     * 门店列表查询
     * @param store
     * @return
     */
    public AppResponse listStore(Store store){
        PageHelper.startPage(store.getPageNum(),store.getPageSize());
        List<Store> stores = storeDao.listStore(store);
        PageInfo<Store> storePageInfo = new PageInfo<>(stores);
        return AppResponse.success("门店列表查询成功",storePageInfo);
    }
}




