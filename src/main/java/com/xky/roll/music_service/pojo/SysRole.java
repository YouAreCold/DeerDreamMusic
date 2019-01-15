package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole {
    /**
     * 角色标识
     */
    @Id
    @Column(name = "ROLE_ID")
    private Integer roleId;

    /**
     * 角色编码
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 父标识
     */
    @Column(name = "PARENT_ID")
    private Integer parentId;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private Date createtime;

    /**
     * 是否可用（0：是；1：否）
     */
    @Column(name = "IS_ENABLE")
    private Integer isEnable;

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
     * 获取角色编码
     *
     * @return ROLE_CODE - 角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码
     *
     * @param roleCode 角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return CREATETIME - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
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