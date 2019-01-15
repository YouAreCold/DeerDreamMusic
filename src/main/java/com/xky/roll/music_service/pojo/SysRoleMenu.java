package com.xky.roll.music_service.pojo;

import javax.persistence.*;

@Table(name = "sys_role_menu")
public class SysRoleMenu {
    /**
     * 标识
     */
    @Id
    @Column(name = "UID")
    private Integer uid;

    /**
     * 角色标识
     */
    @Column(name = "ROLE_ID")
    private Integer roleId;

    /**
     * 菜单标识
     */
    @Column(name = "MENU_ID")
    private Integer menuId;

    /**
     * 获取标识
     *
     * @return UID - 标识
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置标识
     *
     * @param uid 标识
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取角色标识
     *
     * @return ROLE_ID - 角色标识
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色标识
     *
     * @param roleId 角色标识
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单标识
     *
     * @return MENU_ID - 菜单标识
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单标识
     *
     * @param menuId 菜单标识
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}