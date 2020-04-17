package com.xzsd.pc.driver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.Driver;
import com.xzsd.pc.driver.entity.DriverVo;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.User;
import com.xzsd.pc.user.entity.UserVo;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DriverService {

    @Resource
    private DriverDao driverDao;

    @Resource
    private UserDao userDao;

    /**
     * 新增司机信息
     * @param driver
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(Driver driver, User user){
        //检验用户账户是否存在
        int cnt = userDao.countUserAcct(user);
        if(cnt != 0){
            return AppResponse.bizError("账户已存在,请重新输入");
        }
        //设置用户编号
        user.setUserCode(StringUtil.getCommonCode(2));
        //设置角色编号
        user.setRole(3);
        //生成司机编号
        driver.setDriverCode(StringUtil.getCommonCode(2));
        driver.setUserCode(user.getUserCode());
        int userCnt = userDao.saveUser(user);
        int driverCnt = driverDao.addDriver(driver);
        if (userCnt == 0 || driverCnt == 0){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增司机成功");
    }

    /**
     * 查询司机详情
     * @param driverCode
     * @return
     */
    public AppResponse findDriverById(String driverCode){
        DriverVo driver = driverDao.findDriverById(driverCode);
        return AppResponse.success("司机详情查询成功",driver);
    }

    /**
     * 修改司机信息
     * @param driver
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriver(Driver driver,User user){
        //检验用户账户是否存在
        int cnt = userDao.countUserAcct(user);
        if(cnt != 0){
            return AppResponse.bizError("账户已存在,请重新输入");
        }
        int driverCnt = driverDao.updateDriver(driver);
        int userCnt = userDao.updateUser(user);
        if (userCnt == 0 || driverCnt == 0){
            return AppResponse.bizError("数据有更新，请重试");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 删除司机信息
     * @param userCode
     * @param lastModfiedBy
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String userCode,String lastModfiedBy){
        List<String> listCode = Arrays.asList(userCode.split(","));
        int userCnt = userDao.deleteUser(listCode, lastModfiedBy);
        int driverCnt = driverDao.deleteDriver(listCode, lastModfiedBy);
        if (userCnt == 0 || driverCnt == 0){
            return AppResponse.bizError("删除失败，请重试");
        }
        return AppResponse.success("删除成功");
    }

    /**
     * 司机信息列表查询
     * @param driver
     * @return
     */
    public AppResponse listDriver(Driver driver){
        PageHelper.startPage(driver.getPageNum(),driver.getPageSize());
        //获取当前登录人的id
        String currentUserId = SecurityUtils.getCurrentUserId();
        UserVo user = userDao.getUserByUserCode(currentUserId);
        Integer role = user.getRole();
        //管理人显示全部 非管理员显示对应门店司机
        if (role == 0){
            List<Driver> drivers = driverDao.listDriver(driver);
            PageInfo<Driver> driverPageInfo = new PageInfo<>(drivers);
            return AppResponse.success("司机信息列表查询成功",driverPageInfo);
        }else {
            driver.setUserCode(currentUserId);
            List<Driver> drivers = driverDao.listShopperDriver(driver);
            PageInfo<Driver> driverPageInfo = new PageInfo<>(drivers);
            return AppResponse.success("司机信息列表查询成功",driverPageInfo);
        }
    }
}
