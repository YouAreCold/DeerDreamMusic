package com.xky.roll.music_service.pojo;

import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu {
    /**
     * 菜单标识
     */
    @Id
    @Column(name = "MENU_ID")
    private Integer menuId;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 父标识
     */
    @Column(name = "PARENT_ID")
    private Integer parentId;

    /**
     * 菜单类型（0：目录；1：功能点）
     */
    @Column(name = "MENU_TYPE")
    private Integer menuType;

    /**
     * 目标地址
     */
    @Column(name = "ACTION_URL")
    private String actionUrl;

    /**
     * 排序号
     */
    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private String createtime;

    /**
     * 是否可用（0：是；1：否）
     */
    @Column(name = "IS_ENABLE")
    private Integer isEnable;

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

    /**
     * 获取菜单名称
     *
     * @return MENU_NAME - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取父标识
     *
     * @return PARENT_ID - 父标识
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父标识
     *
     * @param parentId 父标识
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单类型（0：目录；1：功能点）
     *
     * @return MENU_TYPE - 菜单类型（0：目录；1：功能点）
     */
    public Integer getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型（0：目录；1：功能点）
     *
     * @param menuType 菜单类型（0：目录；1：功能点）
     */
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取目标地址
     *
     * @return ACTION_URL - 目标地址
     */
    public String getActionUrl() {
        return actionUrl;
    }

    /**
     * 设置目标地址
     *
     * @param actionUrl 目标地址
     */
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    /**
     * 获取排序号
     *
     * @return SORT_ORDER - 排序号
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序号
     *
     * @param sortOrder 排序号
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 获取创建时间
     *
     * @return CREATETIME - 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取是否可用（0：是；1：否）
     *
     * @return IS_ENABLE - 是否可用（0：是；1：否）
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否可用（0：是；1：否）
     *
     * @param isEnable 是否可用（0：是；1：否）
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}