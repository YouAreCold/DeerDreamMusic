package com.xky.roll.music_service.pojo;

import javax.persistence.*;

@Table(name = "sys_user_role")
public class SysUserRole {
    /**
     * 标识
     */
    @Id
    @Column(name = "UID")
    private Integer uid;

    /**
     * 用户标识
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 角色标识
     */
    @Column(name = "ROLE_ID")
    private Integer roleId;

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
     * 获取用户标识
     *
     * @return USER_ID - 用户标识
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户标识
     *
     * @param userId 用户标识
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
}