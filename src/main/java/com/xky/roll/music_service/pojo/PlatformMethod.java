package com.xky.roll.music_service.pojo;

import javax.persistence.*;

@Table(name = "platform_method")
public class PlatformMethod {
    @Id
    @Column(name = "platform_method_id")
    private Integer platformMethodId;

    /**
     * 方法名
     */
    @Column(name = "platform_method_name")
    private String platformMethodName;

    /**
     * 方法说明
     */
    private String description;

    /**
     * @return platform_method_id
     */
    public Integer getPlatformMethodId() {
        return platformMethodId;
    }

    /**
     * @param platformMethodId
     */
    public void setPlatformMethodId(Integer platformMethodId) {
        this.platformMethodId = platformMethodId;
    }

    /**
     * 获取方法名
     *
     * @return platform_method_name - 方法名
     */
    public String getPlatformMethodName() {
        return platformMethodName;
    }

    /**
     * 设置方法名
     *
     * @param platformMethodName 方法名
     */
    public void setPlatformMethodName(String platformMethodName) {
        this.platformMethodName = platformMethodName;
    }

    /**
     * 获取方法说明
     *
     * @return description - 方法说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置方法说明
     *
     * @param description 方法说明
     */
    public void setDescription(String description) {
        this.description = description;
    }
}