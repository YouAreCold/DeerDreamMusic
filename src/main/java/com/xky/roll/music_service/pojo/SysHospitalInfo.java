package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_hospital_info")
public class SysHospitalInfo {
	/**
	 * 平台编码
	 */
	@Id
	@Column(name = "HOSPITAL_ID")
	private String hospitalId;

	/**
	 * 平台名称
	 */
	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;

	/**
	 * 调用地址
	 */
	@Column(name = "INVOKING_URL")
	private String invokingUrl;

	/**
	 * 驱动转化类
	 */
	@Column(name = "DRIVE_TRANS_CLASS")
	private String driveTransClass;

	/**
	 * 状态（1启用 0禁用）
	 */
	@Column(name = "STATUS")
	private Byte status;

	/**
	 * 接口方式
	 */
	@Column(name = "API_WAY")
	private Integer apiWay;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	/**
	 * HIS平台ID
	 */
	@Column(name = "HIS_ID")
	private String hisId;

	/**
	 * 联系人
	 */
	@Column(name = "CONTACTS")
	private String contacts;

	/**
	 * 联系电话
	 */
	@Column(name = "TELEPHONE")
	private String telephone;

	/**
	 * 联系邮箱
	 */
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * 获取平台编码
	 *
	 * @return HOSPITAL_ID - 平台编码
	 */
	public String getHospitalId() {
		return hospitalId;
	}

	/**
	 * 设置平台编码
	 *
	 * @param hospitalId
	 *            平台编码
	 */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * 获取平台名称
	 *
	 * @return HOSPITAL_NAME - 平台名称
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * 设置平台名称
	 *
	 * @param hospitalName
	 *            平台名称
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * 获取调用地址
	 *
	 * @return INVOKING_URL - 调用地址
	 */
	public String getInvokingUrl() {
		return invokingUrl;
	}

	/**
	 * 设置调用地址
	 *
	 * @param invokingUrl
	 *            调用地址
	 */
	public void setInvokingUrl(String invokingUrl) {
		this.invokingUrl = invokingUrl;
	}

	/**
	 * 获取驱动转化类
	 *
	 * @return DRIVE_TRANS_CLASS - 驱动转化类
	 */
	public String getDriveTransClass() {
		return driveTransClass;
	}

	/**
	 * 设置驱动转化类
	 *
	 * @param driveTransClass
	 *            驱动转化类
	 */
	public void setDriveTransClass(String driveTransClass) {
		this.driveTransClass = driveTransClass;
	}

	/**
	 * 获取状态（1启用 0禁用）
	 *
	 * @return STATUS - 状态（1启用 0禁用）
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 设置状态（1启用 0禁用）
	 *
	 * @param status
	 *            状态（1启用 0禁用）
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * 获取接口方式
	 *
	 * @return API_WAY - 接口方式
	 */
	public Integer getApiWay() {
		return apiWay;
	}

	/**
	 * 设置接口方式
	 *
	 * @param apiWay
	 *            接口方式
	 */
	public void setApiWay(Integer apiWay) {
		this.apiWay = apiWay;
	}

	/**
	 * @return USER_NAME
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return PASSWORD
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取HIS平台ID
	 *
	 * @return HIS_ID - HIS平台ID
	 */
	public String getHisId() {
		return hisId;
	}

	/**
	 * 设置HIS平台ID
	 *
	 * @param hisId
	 *            HIS平台ID
	 */
	public void setHisId(String hisId) {
		this.hisId = hisId;
	}

	/**
	 * 获取联系人
	 *
	 * @return CONTACTS - 联系人
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * 设置联系人
	 *
	 * @param contacts
	 *            联系人
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * 获取联系电话
	 *
	 * @return TELEPHONE - 联系电话
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 设置联系电话
	 *
	 * @param telephone
	 *            联系电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取联系邮箱
	 *
	 * @return EMAIL - 联系邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置联系邮箱
	 *
	 * @param email
	 *            联系邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/*@Transient
	private int[] hospitalIds;

	public int[] getHospitalIds() {
		return hospitalIds;
	}

	public void setHospitalIds(int[] hospitalIds) {
		this.hospitalIds = hospitalIds;
	}*/

}