package com.xzsd.pc.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.Store;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.UserVo;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.RandomUtil;
import com.xzsd.pc.util.StringUtil;
import com.xzsd.pc.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 门店Service实现层
 */
@Service
public class StoreService {

    @Resource
    private StoreDao storeDao;

    @Resource
    private UserDao userDao;

    /**
     * 新增门店
     * @param store
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addStore(Store store){
        store.setStoreCode(StringUtil.getCommonCode(2));
        //设置邀请码
        store.setInvitationCode("SC"+ RandomUtil.radmonkey(4));
        //校验信息是否存在重复
        Integer count = storeDao.selectStoreRepeat(store);
        if (count != 0){
            return AppResponse.bizError("数据有重复,请重试");
        }
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
        //校验信息是否存在重复
        Integer count = storeDao.selectStoreRepeat(store);
        if (count != 0){
            return AppResponse.bizError("数据有重复,请重试");
        }
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
        //获取当前登录人的id
        String currentUserId = SecurityUtils.getCurrentUserId();
        UserVo user = userDao.getUserByUserCode(currentUserId);
        Integer role = user.getRole();
        if (role == 0){
            //管理员数据
            List<Store> stores = storeDao.listStore(store);
            PageInfo<Store> storePageInfo = new PageInfo<>(stores);
            return AppResponse.success("门店列表查询成功",storePageInfo);
        }else{
            //店长数据
            List<Store> stores = storeDao.listShopperStore(store);
            PageInfo<Store> storePageInfo = new PageInfo<>(stores);
            return AppResponse.success("门店列表查询成功",storePageInfo);
        }
    }
}




