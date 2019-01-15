package com.xky.roll.music_service.pojo;

import javax.persistence.*;

@Table(name = "method_fields")
public class MethodFields {
    @Id
    @Column(name = "key_id")
    private Integer keyId;

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "platform_method_id")
    private Integer platformMethodId;

    /**
     * 0:入参 1：出参
     */
    @Column(name = "in_or_out")
    private Byte inOrOut;

    /**
     * @return key_id
     */
    public Integer getKeyId() {
        return keyId;
    }

    /**
     * @param keyId
     */
    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    /**
     * @return key_name
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * @param keyName
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

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
     * 获取0:入参 1：出参
     *
     * @return in_or_out - 0:入参 1：出参
     */
    public Byte getInOrOut() {
        return inOrOut;
    }

    /**
     * 设置0:入参 1：出参
     *
     * @param inOrOut 0:入参 1：出参
     */
    public void setInOrOut(Byte inOrOut) {
        this.inOrOut = inOrOut;
    }
}