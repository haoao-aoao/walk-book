package com.xzsd.pc.menu.entity;

public class MenuVo {
    /**
     * 菜单id
     */
    private String menuCode;
    /**
     * 菜单名称
     */
    private String menuName;

    private Integer version;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
