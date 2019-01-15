package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_param_dict")
public class SysParamDict {
    /**
     * 标识
     */
    @Id
    @Column(name = "PARAM_ID")
    private Integer paramId;

    /**
     * 方法标识
     */
    @Column(name = "METHOD_ID")
    private Integer methodId;

    /**
     * 参数标识
     */
    @Column(name = "COLUMN_ID")
    private Integer columnId;

    /**
     * 平台值
     */
    @Column(name = "SP_VALUE")
    private String spValue;

    /**
     * HIS值
     */
    @Column(name = "HIS_VALUE")
    private String hisValue;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 是否可用(0：可用；1：禁用)
     */
    @Column(name = "IS_ENABLE")
    private Integer isEnable;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 创建者标识
     */
    @Column(name = "CREATEOR")
    private Integer createor;

    /**
     * 获取标识
     *
     * @return PARAM_ID - 标识
     */
    public Integer getParamId() {
        return paramId;
    }

    /**
     * 设置标识
     *
     * @param paramId 标识
     */
    public void setParamId(Integer paramId) {
        this.paramId = paramId;
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
     * 获取平台值
     *
     * @return SP_VALUE - 平台值
     */
    public String getSpValue() {
        return spValue;
    }

    /**
     * 设置平台值
     *
     * @param spValue 平台值
     */
    public void setSpValue(String spValue) {
        this.spValue = spValue;
    }

    /**
     * 获取HIS值
     *
     * @return HIS_VALUE - HIS值
     */
    public String getHisValue() {
        return hisValue;
    }

    /**
     * 设置HIS值
     *
     * @param hisValue HIS值
     */
    public void setHisValue(String hisValue) {
        this.hisValue = hisValue;
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
     * 获取创建者标识
     *
     * @return CREATEOR - 创建者标识
     */
    public Integer getCreateor() {
        return createor;
    }

    /**
     * 设置创建者标识
     *
     * @param createor 创建者标识
     */
    public void setCreateor(Integer createor) {
        this.createor = createor;
    }
}