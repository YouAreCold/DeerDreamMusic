package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_method")
public class SysMethod {
    /**
     * 方法标识
     */
    @Id
    @Column(name = "METHOD_ID")
    private Integer methodId;

    /**
     * 音乐平台ID
     */
    @Column(name = "HOSPITAL_ID")
    private Integer hospitalId;

    /**
     * 平台方法名称
     */
    @Column(name = "SP_METHOD")
    private String spMethod;

    /**
     * HIS方法名称
     */
    @Column(name = "HIS_METHOD")
    private String hisMethod;

    /**
     * 方法名称
     */
    @Column(name = "METHOD_NAME")
    private String methodName;

    /**
     * XML格式的集合节点名称
     */
    @Column(name = "TABLE_NAME")
    private String tableName;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 创建人标识
     */
    @Column(name = "CREATEOR")
    private Integer createor;

    /**
     * 最后修改人标识
     */
    @Column(name = "MODIFYOR")
    private Integer modifyor;

    /**
     * 最后修改时间
     */
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    /**
     * 是否可用(0：可用；1：禁用)
     */
    @Column(name = "IS_ENABLE")
    private Integer isEnable;

    /**
     * HIS进参格式
     */
    @Column(name = "PARAM_IN")
    private String paramIn;

    /**
     * HIS出参格式
     */
    @Column(name = "PARAM_OUT")
    private String paramOut;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

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
     * 获取平台方法名称
     *
     * @return SP_METHOD - 平台方法名称
     */
    public String getSpMethod() {
        return spMethod;
    }

    /**
     * 设置平台方法名称
     *
     * @param spMethod 平台方法名称
     */
    public void setSpMethod(String spMethod) {
        this.spMethod = spMethod;
    }

    /**
     * 获取HIS方法名称
     *
     * @return HIS_METHOD - HIS方法名称
     */
    public String getHisMethod() {
        return hisMethod;
    }

    /**
     * 设置HIS方法名称
     *
     * @param hisMethod HIS方法名称
     */
    public void setHisMethod(String hisMethod) {
        this.hisMethod = hisMethod;
    }

    /**
     * 获取方法名称
     *
     * @return METHOD_NAME - 方法名称
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名称
     *
     * @param methodName 方法名称
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取XML格式的集合节点名称
     *
     * @return TABLE_NAME - XML格式的集合节点名称
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置XML格式的集合节点名称
     *
     * @param tableName XML格式的集合节点名称
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
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
     * @return CREATEOR - 创建人标识
     */
    public Integer getCreateor() {
        return createor;
    }

    /**
     * 设置创建人标识
     *
     * @param createor 创建人标识
     */
    public void setCreateor(Integer createor) {
        this.createor = createor;
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
     * 获取是否可用(0：可用；1：禁用)
     *
     * @return IS_ENABLE - 是否可用(0：可用；1：禁用)
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否可用(0：可用；1：禁用)
     *
     * @param isEnable 是否可用(0：可用；1：禁用)
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取HIS进参格式
     *
     * @return PARAM_IN - HIS进参格式
     */
    public String getParamIn() {
        return paramIn;
    }

    /**
     * 设置HIS进参格式
     *
     * @param paramIn HIS进参格式
     */
    public void setParamIn(String paramIn) {
        this.paramIn = paramIn;
    }

    /**
     * 获取HIS出参格式
     *
     * @return PARAM_OUT - HIS出参格式
     */
    public String getParamOut() {
        return paramOut;
    }

    /**
     * 设置HIS出参格式
     *
     * @param paramOut HIS出参格式
     */
    public void setParamOut(String paramOut) {
        this.paramOut = paramOut;
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
}