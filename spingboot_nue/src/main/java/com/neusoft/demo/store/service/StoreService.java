package com.neusoft.demo.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.demo.store.dao.StoreDao;
import com.neusoft.demo.store.entity.Store;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.StringUtil;
import com.neusoft.demo.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreService {

    @Resource
    private StoreDao storeDao;

    /**
     * 新增门店
     * @param store
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addStore(Store store){
        store.setStoreCode("SC"+StringUtil.getCommonCode(1));
        store.setInvitationCode(UUIDUtils.getUUID());
        int cnt = storeDao.addStore(store);
        if (cnt == 0){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增门店成功");
    }

    /**
     * 查询门店详情
     * @param storeCode
     * @return
     */
    public AppResponse findStoreById(String storeCode){
        Store storeById = storeDao.findStoreById(storeCode);
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
     * 删除门店
     * @param storeCode
     * @param userCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStore(String storeCode, String userCode){
        List<String> listCode = Arrays.asList(storeCode.split(","));
        int cnt = storeDao.deleteStore(listCode, userCode);
        if (cnt == 0){
            return AppResponse.bizError("删除错误");
        }
        return AppResponse.success("删除成功");
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




