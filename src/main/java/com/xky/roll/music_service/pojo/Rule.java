package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

public class Rule {
    @Id
    @Column(name = "rule_id")
    private Integer ruleId;

    /**
     * 关联接口id
     */
    @Column(name = "interface_id")
    private Integer interfaceId;

    /**
     * 关联音乐平台id
     */
    @Column(name = "hospital_id")
    private Integer hospitalId;

    /**
     * 关联要执行的方法id
     */
    @Column(name = "rule_method_id")
    private Integer ruleMethodId;

    /**
     * 需要调整的字段
     */
    @Column(name = "target_fileds")
    private String targetFileds;

    /**
     * 已有或自定义 0:已有 1：自定义
     */
    @Column(name = "exist_or_custom")
    private Byte existOrCustom;

    /**
     * 入参或出参 0：入参 1：出参
     */
    @Column(name = "in_or_out")
    private Byte inOrOut;

    /**
     * 状态位 0：正常 1：禁用
     */
    private Byte status;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "update_time")
    private Date updateTime;

    private String description;

    /**
     * @return rule_id
     */
    public Integer getRuleId() {
        return ruleId;
    }

    /**
     * @param ruleId
     */
    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 获取关联接口id
     *
     * @return interface_id - 关联接口id
     */
    public Integer getInterfaceId() {
        return interfaceId;
    }

    /**
     * 设置关联接口id
     *
     * @param interfaceId 关联接口id
     */
    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
     * 获取关联音乐平台id
     *
     * @return hospital_id - 关联音乐平台id
     */
    public Integer getHospitalId() {
        return hospitalId;
    }

    /**
     * 设置关联音乐平台id
     *
     * @param hospitalId 关联音乐平台id
     */
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * 获取关联要执行的方法id
     *
     * @return rule_method_id - 关联要执行的方法id
     */
    public Integer getRuleMethodId() {
        return ruleMethodId;
    }

    /**
     * 设置关联要执行的方法id
     *
     * @param ruleMethodId 关联要执行的方法id
     */
    public void setRuleMethodId(Integer ruleMethodId) {
        this.ruleMethodId = ruleMethodId;
    }

    /**
     * 获取需要调整的字段
     *
     * @return target_fileds - 需要调整的字段
     */
    public String getTargetFileds() {
        return targetFileds;
    }

    /**
     * 设置需要调整的字段
     *
     * @param targetFileds 需要调整的字段
     */
    public void setTargetFileds(String targetFileds) {
        this.targetFileds = targetFileds;
    }

    /**
     * 获取已有或自定义 0:已有 1：自定义
     *
     * @return exist_or_custom - 已有或自定义 0:已有 1：自定义
     */
    public Byte getExistOrCustom() {
        return existOrCustom;
    }

    /**
     * 设置已有或自定义 0:已有 1：自定义
     *
     * @param existOrCustom 已有或自定义 0:已有 1：自定义
     */
    public void setExistOrCustom(Byte existOrCustom) {
        this.existOrCustom = existOrCustom;
    }

    /**
     * 获取入参或出参 0：入参 1：出参
     *
     * @return in_or_out - 入参或出参 0：入参 1：出参
     */
    public Byte getInOrOut() {
        return inOrOut;
    }

    /**
     * 设置入参或出参 0：入参 1：出参
     *
     * @param inOrOut 入参或出参 0：入参 1：出参
     */
    public void setInOrOut(Byte inOrOut) {
        this.inOrOut = inOrOut;
    }

    /**
     * 获取状态位 0：正常 1：禁用
     *
     * @return status - 状态位 0：正常 1：禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态位 0：正常 1：禁用
     *
     * @param status 状态位 0：正常 1：禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return create_user
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_user
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}