package com.xzsd.pc.client.entity;


import com.xzsd.pc.user.entity.UserVo;

import java.util.Date;
import java.util.List;

public class Client {
    /**
     * 客户编号
     */
    private String userCode;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 作废标记 0未删 1已删
     */
    private Integer isDelete;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者(登陆)
     */
    private String lastModfiedBy;

    /**
     * 更新时间
     */
    private Date gmtModfied;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 用户信息列表
     */
    private List<UserVo> users;
}
