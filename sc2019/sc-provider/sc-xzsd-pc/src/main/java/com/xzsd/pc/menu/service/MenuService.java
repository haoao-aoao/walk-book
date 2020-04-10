package com.xzsd.pc.menu.service;

import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.menu.dao.MenuDao;
import com.xzsd.pc.menu.entity.Menu;
import com.xzsd.pc.menu.entity.MenuVo;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.UserVo;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    @Resource
    private MenuDao menuDao;

    @Resource
    private UserDao userDao;

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addMenu(Menu menu){
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        menu.setCreateUser(currentUserId);
        menu.setMenuCode(StringUtil.getCommonCode(2));
        menu.setIsMenu(1);
        int cnt = menuDao.addMenu(menu);
        if (0 == cnt){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增成功");
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateMenuById(Menu menu){
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        menu.setLastModfiedBy(currentUserId);
        int cnt = menuDao.updateMenuById(menu);
        if (0 == cnt){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 查询所有菜单
     * @return
     */
    public AppResponse findAllMenu(){
        List<MenuVo> allMenu = menuDao.findAllMenu();
        return AppResponse.success("查询成功",allMenu);
    }

    /**
     * 删除菜单
     * @param menuCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteMenu(String menuCode){
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        List<String> listCode = Arrays.asList(menuCode.split(","));
        int cnt = menuDao.deleteMenu(listCode, currentUserId);
        if (0 == cnt){
            return AppResponse.bizError("删除失败");
        }
        return AppResponse.success("删除成功");
    }

    /**
     * 查询菜单详情
     * @param menuCode
     * @return
     */
    public AppResponse findMenuById(String menuCode){
        Menu menuById = menuDao.findMenuById(menuCode);
        return AppResponse.success("查询菜单详情成功",menuById);
    }

    /**
     * 查询pc端左侧菜单
     * @return
     */
    public AppResponse findLeftMenu(){
        //获取当前登录人id
        String currentUserId = SecurityUtils.getCurrentUserId();
        UserVo user = userDao.getUserByUserCode(currentUserId);
        Integer role = user.getRole();
        if (role == 0){
            return AppResponse.success("管理员菜单",menuDao.findAllMenu());
        }else {
            return AppResponse.success("店长菜单",menuDao.findLeftMenu());
        }
    }
}
