package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "out_param")
public class OutParam {
    @Id
    @Column(name = "out_param_id")
    private Integer outParamId;

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

    @Column(name = "out_param")
    private String outParam;

    /**
     * @return out_param_id
     */
    public Integer getOutParamId() {
        return outParamId;
    }

    /**
     * @param outParamId
     */
    public void setOutParamId(Integer outParamId) {
        this.outParamId = outParamId;
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

    /**
     * @return out_param
     */
    public String getOutParam() {
        return outParam;
    }

    /**
     * @param outParam
     */
    public void setOutParam(String outParam) {
        this.outParam = outParam;
    }
}