package com.xzsd.pc.menu.controller;

import com.xzsd.pc.menu.entity.Menu;
import com.xzsd.pc.menu.service.MenuService;
import com.xzsd.pc.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private MenuService menuService;

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @PostMapping("/addMenu")
    public AppResponse addMenu(Menu menu){
        try {
            return menuService.addMenu(menu);
        }catch (Exception e){
            logger.error("新增异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @PostMapping("/updateMenuById")
    public AppResponse updateMenuById(Menu menu){
        try {
            return menuService.updateMenuById(menu);
        }catch (Exception e){
            logger.error("修改异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询所有菜单
     * @return
     */
    @RequestMapping("/findAllMenu")
    public AppResponse findAllMenu(){
        try {
            return menuService.findAllMenu();
        }catch (Exception e){
            logger.error("查询异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除菜单
     * @param menuCode
     * @return
     */
    @PostMapping("/deleteMenu")
    public AppResponse deleteMenu(String menuCode){
        try {
            return menuService.deleteMenu(menuCode);
        }catch (Exception e){
            logger.error("删除异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询菜单详情
     * @param menuCode
     * @return
     */
    @RequestMapping("/findMenuById")
    public AppResponse findMenuById(String menuCode){
        try {
            return menuService.findMenuById(menuCode);
        }catch (Exception e){
            logger.error("查询菜单详情异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询pc端左侧菜单
     * @return
     */
    @RequestMapping("/findLeftMenu")
    public AppResponse findLeftMenu(){
        try {
            return menuService.findLeftMenu();
        }catch (Exception e){
            logger.error("查询pc端左侧菜单异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
