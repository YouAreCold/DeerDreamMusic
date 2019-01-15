package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dict")
public class SysDict {
    @Id
    @Column(name = "DICT_ID")
    private Integer dictId;

    @Column(name = "PARENT_ID")
    private Integer parentId;

    @Column(name = "DICT_NAME")
    private String dictName;

    @Column(name = "DICT_TYPE")
    private String dictType;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "CREATOR")
    private Integer creator;

    @Column(name = "IS_ENABLE")
    private Integer isEnable;

    /**
     * @return DICT_ID
     */
    public Integer getDictId() {
        return dictId;
    }

    /**
     * @param dictId
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    /**
     * @return PARENT_ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return DICT_NAME
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * @param dictName
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * @return DICT_TYPE
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * @param dictType
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * @return CREATE_TIME
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
     * @return CREATOR
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * @return IS_ENABLE
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * @param isEnable
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}