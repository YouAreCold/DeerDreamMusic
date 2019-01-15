package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_hosp_connect")
public class SysHospConnect {
    /**
     * 连接ID
     */
    @Id
    @Column(name = "CONNECT_ID")
    private Integer connectId;

    /**
     * 音乐平台ID，当method_ID!=0时，取method_id对应的连接，=0则取音乐平台默认连接
     */
    @Column(name = "HOSPITAL_ID")
    private Integer hospitalId;

    /**
     * 方法ID
     */
    @Column(name = "METHOD_ID")
    private Integer methodId;

    @Column(name = "COMM_METHOD")
    private String commMethod;

    /**
     * 连接方式（1：HTTP网络服务；2：数据库;3:webService方式）
     */
    @Column(name = "CONNECT_TYPE")
    private Integer connectType;

    /**
     * 数据库连接驱动，connect_type=1时不填
     */
    @Column(name = "JDBC_DRIVER")
    private String jdbcDriver;

    /**
     * 连接地址
     */
    @Column(name = "CONNECT_URL")
    private String connectUrl;

    /**
     * 用户
     */
    @Column(name = "CID")
    private String cid;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

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
     * 创建者ID
     */
    @Column(name = "CREATOR_ID")
    private Integer creatorId;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 获取连接ID
     *
     * @return CONNECT_ID - 连接ID
     */
    public Integer getConnectId() {
        return connectId;
    }

    /**
     * 设置连接ID
     *
     * @param connectId 连接ID
     */
    public void setConnectId(Integer connectId) {
        this.connectId = connectId;
    }

    /**
     * 获取音乐平台ID，当method_ID!=0时，取method_id对应的连接，=0则取音乐平台默认连接
     *
     * @return HOSPITAL_ID - 音乐平台ID，当method_ID!=0时，取method_id对应的连接，=0则取音乐平台默认连接
     */
    public Integer getHospitalId() {
        return hospitalId;
    }

    /**
     * 设置音乐平台ID，当method_ID!=0时，取method_id对应的连接，=0则取音乐平台默认连接
     *
     * @param hospitalId 音乐平台ID，当method_ID!=0时，取method_id对应的连接，=0则取音乐平台默认连接
     */
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * 获取方法ID
     *
     * @return METHOD_ID - 方法ID
     */
    public Integer getMethodId() {
        return methodId;
    }

    /**
     * 设置方法ID
     *
     * @param methodId 方法ID
     */
    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    /**
     * @return COMM_METHOD
     */
    public String getCommMethod() {
        return commMethod;
    }

    /**
     * @param commMethod
     */
    public void setCommMethod(String commMethod) {
        this.commMethod = commMethod;
    }

    /**
     * 获取连接方式（1：HTTP网络服务；2：数据库;3:webService方式）
     *
     * @return CONNECT_TYPE - 连接方式（1：HTTP网络服务；2：数据库;3:webService方式）
     */
    public Integer getConnectType() {
        return connectType;
    }

    /**
     * 设置连接方式（1：HTTP网络服务；2：数据库;3:webService方式）
     *
     * @param connectType 连接方式（1：HTTP网络服务；2：数据库;3:webService方式）
     */
    public void setConnectType(Integer connectType) {
        this.connectType = connectType;
    }

    /**
     * 获取数据库连接驱动，connect_type=1时不填
     *
     * @return JDBC_DRIVER - 数据库连接驱动，connect_type=1时不填
     */
    public String getJdbcDriver() {
        return jdbcDriver;
    }

    /**
     * 设置数据库连接驱动，connect_type=1时不填
     *
     * @param jdbcDriver 数据库连接驱动，connect_type=1时不填
     */
    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    /**
     * 获取连接地址
     *
     * @return CONNECT_URL - 连接地址
     */
    public String getConnectUrl() {
        return connectUrl;
    }

    /**
     * 设置连接地址
     *
     * @param connectUrl 连接地址
     */
    public void setConnectUrl(String connectUrl) {
        this.connectUrl = connectUrl;
    }

    /**
     * 获取用户
     *
     * @return CID - 用户
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置用户
     *
     * @param cid 用户
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
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
     * 获取创建者ID
     *
     * @return CREATOR_ID - 创建者ID
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建者ID
     *
     * @param creatorId 创建者ID
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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