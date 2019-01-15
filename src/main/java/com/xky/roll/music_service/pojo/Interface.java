package com.xky.roll.music_service.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

public class Interface {
	@Id
	@Column(name = "interface_id")
	private Integer interfaceId;

	/**
	 * 关联平台方法id
	 */
	@Column(name = "platform_method_id")
	private Integer platformMethodId;

	/**
	 * HIS方法名
	 */
	@Column(name = "his_method_name")
	private String hisMethodName;

	/**
	 * 关联音乐平台id
	 */
	@Column(name = "hospital_id")
	private Integer hospitalId;

	/**
	 * 是否需要调用下一个接口(可能多个，逗号分隔)
	 */
	@Column(name = "next_method_id")
	private String nextMethodId;

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
	 * his入参
	 */
	@Column(name = "in_param")
	private String inParam;

	/**
	 * hia出参
	 */
	@Column(name = "out_param")
	private String outParam;

	/**
	 * @return interface_id
	 */
	public Integer getInterfaceId() {
		return interfaceId;
	}

	/**
	 * @param interfaceId
	 */
	public void setInterfaceId(Integer interfaceId) {
		this.interfaceId = interfaceId;
	}

	/**
	 * 获取关联平台方法id
	 *
	 * @return platform_method_id - 关联平台方法id
	 */
	public Integer getPlatformMethodId() {
		return platformMethodId;
	}

	/**
	 * 设置关联平台方法id
	 *
	 * @param platformMethodId
	 *            关联平台方法id
	 */
	public void setPlatformMethodId(Integer platformMethodId) {
		this.platformMethodId = platformMethodId;
	}

	/**
	 * 获取HIS方法名
	 *
	 * @return his_method_name - HIS方法名
	 */
	public String getHisMethodName() {
		return hisMethodName;
	}

	/**
	 * 设置HIS方法名
	 *
	 * @param hisMethodName
	 *            HIS方法名
	 */
	public void setHisMethodName(String hisMethodName) {
		this.hisMethodName = hisMethodName;
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
	 * @param hospitalId
	 *            关联音乐平台id
	 */
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * 获取是否需要调用下一个接口(可能多个，逗号分隔)
	 *
	 * @return next_method_id - 是否需要调用下一个接口(可能多个，逗号分隔)
	 */
	public String getNextMethodId() {
		return nextMethodId;
	}

	/**
	 * 设置是否需要调用下一个接口(可能多个，逗号分隔)
	 *
	 * @param nextMethodId
	 *            是否需要调用下一个接口(可能多个，逗号分隔)
	 */
	public void setNextMethodId(String nextMethodId) {
		this.nextMethodId = nextMethodId;
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
	 * @param status
	 *            状态位 0：正常 1：禁用
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
	 * 获取his入参
	 *
	 * @return in_param - his入参
	 */
	public String getInParam() {
		return inParam;
	}

	/**
	 * 设置his入参
	 *
	 * @param inParam
	 *            his入参
	 */
	public void setInParam(String inParam) {
		this.inParam = inParam;
	}

	/**
	 * 获取hia出参
	 *
	 * @return out_param - hia出参
	 */
	public String getOutParam() {
		return outParam;
	}

	/**
	 * 设置hia出参
	 *
	 * @param outParam
	 *            hia出参
	 */
	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}

	@Transient
	private List<Object> inList;
	@Transient
	private List<Object> outList;
	@Transient
	private List<Object> customInList;
	@Transient
	private List<Object> customOutList;
	@Transient
	private List<Rule> ruleList;

	public List<Rule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<Rule> ruleList) {
		this.ruleList = ruleList;
	}

	public List<Object> getInList() {
		return inList;
	}

	public void setInList(List<Object> inList) {
		this.inList = inList;
	}

	public List<Object> getOutList() {
		return outList;
	}

	public void setOutList(List<Object> outList) {
		this.outList = outList;
	}

	public List<Object> getCustomInList() {
		return customInList;
	}

	public void setCustomInList(List<Object> customInList) {
		this.customInList = customInList;
	}

	public List<Object> getCustomOutList() {
		return customOutList;
	}

	public void setCustomOutList(List<Object> customOutList) {
		this.customOutList = customOutList;
	}

}