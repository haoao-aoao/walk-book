package com.xzsd.pc.menu.dao;

import com.xzsd.pc.menu.entity.Menu;
import com.xzsd.pc.menu.entity.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {
    /**
     * 新增菜单
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    int updateMenuById(Menu menu);

    /**
     * 查询所有菜单列表
     * @return
     */
    List<MenuVo> findAllMenu();

    /**
     * 删除菜单
     * @param listCode
     * @param userCode
     * @return
     */
    int deleteMenu(@Param("listCode") List<String> listCode,@Param("userCode") String userCode);

    /**
     * 查询菜单详情
     * @param meunCode
     * @return
     */
    Menu findMenuById(String meunCode);

    /**
     * 查询pc端左侧菜单
     * @return
     */
    List<MenuVo> findLeftMenu();
}