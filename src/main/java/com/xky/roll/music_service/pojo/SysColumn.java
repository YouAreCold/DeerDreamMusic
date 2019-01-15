package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_column")
public class SysColumn {
    /**
     * 参数标识
     */
    @Id
    @Column(name = "COLUMN_ID")
    private Integer columnId;

    /**
     * 音乐平台ID
     */
    @Column(name = "HOSPITAL_ID")
    private Integer hospitalId;

    /**
     * 方法标识
     */
    @Column(name = "METHOD_ID")
    private Integer methodId;

    /**
     * 平台参数
     */
    @Column(name = "SP_COLUMN")
    private String spColumn;

    /**
     * HIS参数
     */
    @Column(name = "HIS_COLUMN")
    private String hisColumn;

    /**
     * 参数名称
     */
    @Column(name = "COLUMN_NAME")
    private String columnName;

    /**
     * 排序号
     */
    @Column(name = "ORDER_NUM")
    private Integer orderNum;

    /**
     * 参数类型(1：入参; 2:出参)
     */
    @Column(name = "PARAM_TYPE")
    private Integer paramType;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 创建人标识
     */
    @Column(name = "CREATOR")
    private Integer creator;

    /**
     * 最后修改时间
     */
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    /**
     * 最后修改人标识
     */
    @Column(name = "MODIFYOR")
    private Integer modifyor;

    /**
     * 是否可用(0:可用；1：禁用)
     */
    @Column(name = "IS_ENABLE")
    private Integer isEnable;

    /**
     * 未空时的默认值
     */
    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    /**
     * 参数备注
     */
    @Column(name = "COLUMN_DESC")
    private String columnDesc;

    /**
     * 获取参数标识
     *
     * @return COLUMN_ID - 参数标识
     */
    public Integer getColumnId() {
        return columnId;
    }

    /**
     * 设置参数标识
     *
     * @param columnId 参数标识
     */
    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    /**
     * 获取音乐平台ID
     *
     * @return HOSPITAL_ID - 音乐平台ID
     */
    public Integer getHospitalId() {
        return hospitalId;
    }

    /**
     * 设置音乐平台ID
     *
     * @param hospitalId 音乐平台ID
     */
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * 获取方法标识
     *
     * @return METHOD_ID - 方法标识
     */
    public Integer getMethodId() {
        return methodId;
    }

    /**
     * 设置方法标识
     *
     * @param methodId 方法标识
     */
    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    /**
     * 获取平台参数
     *
     * @return SP_COLUMN - 平台参数
     */
    public String getSpColumn() {
        return spColumn;
    }

    /**
     * 设置平台参数
     *
     * @param spColumn 平台参数
     */
    public void setSpColumn(String spColumn) {
        this.spColumn = spColumn;
    }

    /**
     * 获取HIS参数
     *
     * @return HIS_COLUMN - HIS参数
     */
    public String getHisColumn() {
        return hisColumn;
    }

    /**
     * 设置HIS参数
     *
     * @param hisColumn HIS参数
     */
    public void setHisColumn(String hisColumn) {
        this.hisColumn = hisColumn;
    }

    /**
     * 获取参数名称
     *
     * @return COLUMN_NAME - 参数名称
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置参数名称
     *
     * @param columnName 参数名称
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * 获取排序号
     *
     * @return ORDER_NUM - 排序号
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序号
     *
     * @param orderNum 排序号
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取参数类型(1：入参; 2:出参)
     *
     * @return PARAM_TYPE - 参数类型(1：入参; 2:出参)
     */
    public Integer getParamType() {
        return paramType;
    }

    /**
     * 设置参数类型(1：入参; 2:出参)
     *
     * @param paramType 参数类型(1：入参; 2:出参)
     */
    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人标识
     *
     * @return CREATOR - 创建人标识
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * 设置创建人标识
     *
     * @param creator 创建人标识
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取最后修改时间
     *
     * @return MODIFY_TIME - 最后修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param modifyTime 最后修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取最后修改人标识
     *
     * @return MODIFYOR - 最后修改人标识
     */
    public Integer getModifyor() {
        return modifyor;
    }

    /**
     * 设置最后修改人标识
     *
     * @param modifyor 最后修改人标识
     */
    public void setModifyor(Integer modifyor) {
        this.modifyor = modifyor;
    }

    /**
     * 获取是否可用(0:可用；1：禁用)
     *
     * @return IS_ENABLE - 是否可用(0:可用；1：禁用)
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否可用(0:可用；1：禁用)
     *
     * @param isEnable 是否可用(0:可用；1：禁用)
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取未空时的默认值
     *
     * @return DEFAULT_VALUE - 未空时的默认值
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置未空时的默认值
     *
     * @param defaultValue 未空时的默认值
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 获取参数备注
     *
     * @return COLUMN_DESC - 参数备注
     */
    public String getColumnDesc() {
        return columnDesc;
    }

    /**
     * 设置参数备注
     *
     * @param columnDesc 参数备注
     */
    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }
}