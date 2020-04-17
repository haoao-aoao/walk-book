package com.xzsd.app.store.dao;

import com.xzsd.app.store.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreDao {
    /**
     * 查询门店详情
     * @param userCode
     * @return
     */
    Store findStoreById(@Param("userCode") String userCode);

    /**
     * 修改门店信息
     * @param store
     * @return
     */
    int updateStore(Store store);

    /**
     * 查询门店信息列表
     * @param store
     * @return
     */
    List<Store> listStore(Store store);

    /**
     * 店长编号查门店邀请码
     * @param userCode
     * @return
     */
    Store selectInviteCode(@Param("userCode") String userCode);

    /**
     * 查询邀请码数量
     * @param invitationCode
     * @return
     */
    Integer findInviteCode(@Param("invitationCode") String invitationCode);
}
