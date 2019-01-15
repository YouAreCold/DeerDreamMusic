package com.xky.roll.music_api.pojo;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "[ORDER]")
public class Order {
	/* 系统订单号 */
	@Id
	@Column(name = "ORDER_NO")
	private String orderNo;
	/* 预约就诊日期 */
	@Column(name = "REG_DATE")
	private String regDate;
	/* 预约开始时间 */
	@Column(name = "REG_BEGTIME")
	private String regBegtime;
	/* 预约结束时间 */
	@Column(name = "REG_ENDTIME")
	private String regEndtime;
	/* 挂号总金额 */
	@Column(name = "PAY_REG_COST")
	private BigDecimal payRegCost;
	/* 支付时间 */
	@Column(name = "PAY_REG_TIME")
	private String payRegTime;
	/* 支付来源 */
	@Column(name = "PAY_REG_METHOD")
	private String payRegMethod;
	/* 支付流水号 */
	@Column(name = "PAY_REG_NO")
	private String payRegNo;
	/* 支付状态 */
	@Column(name = "PAY_REG_STATE")
	private String payRegState;
	/* 科室ID */
	@Column(name = "DEPT_ID")
	private String deptId;
	/* 科室名称 */
	@Column(name = "DEPT_NAME")
	private String deptName;
	/* 医生ID */
	@Column(name = "DOCTOR_ID")
	private String doctorId;
	/* 医生名称 */
	@Column(name = "DOC_NAME")
	private String docName;
	/* 医生等级Id */
	@Column(name = "LEVEL_ID")
	private String levelId;
	/* 医生职称名称 */
	@Column(name = "LEVEL_NAME")
	private String levelName;
	/* 患者ID */
	@Column(name = "HIS_PAT_ID")
	private String hisPatId;
	/* 患者姓名 */
	@Column(name = "PATIENT_NAME")
	private String patientName;
	/* 卡类型 */
	@Column(name = "IDENTY_TYPE")
	private String identyType;
	/* 卡号 */
	@Column(name = "IDENTY_CODE")
	private String identyCode;
	/* 患者出生日期 */
	@Column(name = "BIRTH")
	private String birth;
	/* 患者年龄 */
	@Column(name = "AGE")
	private String age;
	/* 性别 */
	@Column(name = "SEX")
	private String sex;
	/* 手机号 */
	@Column(name = "PHONE")
	private String phone;
	/* 地址 */
	@Column(name = "ADDRESS")
	private String address;
	/* 订单状态 */
	@Column(name = "ORADE_STATE")
	private String oradeState;
	/* 更新时间 */
	@Column(name = "UPD_TIME")
	private String updTime;
	/* 渠道ID */
	@Column(name = "CHANNEL_ID")
	private String channelId;
	/* 线上更新时间 */
	@Column(name = "XS_UPDATE")
	private String xsUpdate;
	/* HIS发起的挂号科室ID 不同于上面的depid */
	@Column(name = "HIS_DEP_ID")
	private String hisDepId;
	/* HIs系统科室对应的小科室ID 队列ID */
	@Column(name = "HIS_DL_DEP_ID")
	private String hisDlDepId;
	/* 监护人姓名 */
	@Column(name = "GUARD_NAME")
	private String guardName;
	/* 监护人身份证号 */
	@Column(name = "GUARD_CARD_NO")
	private String guardCardNo;
	/* 监护人关系 */
	@Column(name = "GUARD_RELATION")
	private String guardRelation;
	/* 订单到达时间 */
	@Column(name = "ORDER_ARRIVE_TIME")
	private String orderArriveTime;
	/* 支付到达时间 */
	@Column(name = "PAY_ARRIVE_TIME")
	private String payArriveTime;
	/* HIs同步时间,HIS回写 */
	@Column(name = "HIS_GET_TIME")
	private String hisGetTime;
	/* HIS同步状态,his回写 */
	@Column(name = "HIS_GET_STATE")
	private String hisGetState;

	/**
	 * @return ORDER_NO
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return REG_DATE
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return REG_BEGTIME
	 */
	public String getRegBegtime() {
		return regBegtime;
	}

	/**
	 * @param regBegtime
	 */
	public void setRegBegtime(String regBegtime) {
		this.regBegtime = regBegtime;
	}

	/**
	 * @return REG_ENDTIME
	 */
	public String getRegEndtime() {
		return regEndtime;
	}

	/**
	 * @param regEndtime
	 */
	public void setRegEndtime(String regEndtime) {
		this.regEndtime = regEndtime;
	}

	/**
	 * @return PAY_REG_COST
	 */
	public BigDecimal getPayRegCost() {
		return payRegCost;
	}

	/**
	 * @param payRegCost
	 */
	public void setPayRegCost(BigDecimal payRegCost) {
		this.payRegCost = payRegCost;
	}

	/**
	 * @return PAY_REG_TIME
	 */
	public String getPayRegTime() {
		return payRegTime;
	}

	/**
	 * @param payRegTime
	 */
	public void setPayRegTime(String payRegTime) {
		this.payRegTime = payRegTime;
	}

	/**
	 * @return PAY_REG_METHOD
	 */
	public String getPayRegMethod() {
		return payRegMethod;
	}

	/**
	 * @param payRegMethod
	 */
	public void setPayRegMethod(String payRegMethod) {
		this.payRegMethod = payRegMethod;
	}

	/**
	 * @return PAY_REG_NO
	 */
	public String getPayRegNo() {
		return payRegNo;
	}

	/**
	 * @param payRegNo
	 */
	public void setPayRegNo(String payRegNo) {
		this.payRegNo = payRegNo;
	}

	/**
	 * @return PAY_REG_STATE
	 */
	public String getPayRegState() {
		return payRegState;
	}

	/**
	 * @param payRegState
	 */
	public void setPayRegState(String payRegState) {
		this.payRegState = payRegState;
	}

	/**
	 * @return DEPT_ID
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return DEPT_NAME
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return DOCTOR_ID
	 */
	public String getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return DOC_NAME
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * @param docName
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * @return LEVEL_ID
	 */
	public String getLevelId() {
		return levelId;
	}

	/**
	 * @param levelId
	 */
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	/**
	 * @return LEVEL_NAME
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * @param levelName
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	/**
	 * @return HIS_PAT_ID
	 */
	public String getHisPatId() {
		return hisPatId;
	}

	/**
	 * @param hisPatId
	 */
	public void setHisPatId(String hisPatId) {
		this.hisPatId = hisPatId;
	}

	/**
	 * @return PATIENT_NAME
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return IDENTY_TYPE
	 */
	public String getIdentyType() {
		return identyType;
	}

	/**
	 * @param identyType
	 */
	public void setIdentyType(String identyType) {
		this.identyType = identyType;
	}

	/**
	 * @return IDENTY_CODE
	 */
	public String getIdentyCode() {
		return identyCode;
	}

	/**
	 * @param identyCode
	 */
	public void setIdentyCode(String identyCode) {
		this.identyCode = identyCode;
	}

	/**
	 * @return BIRTH
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return AGE
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return SEX
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return PHONE
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return ADDRESS
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return ORADE_STATE
	 */
	public String getOradeState() {
		return oradeState;
	}

	/**
	 * @param oradeState
	 */
	public void setOradeState(String oradeState) {
		this.oradeState = oradeState;
	}

	/**
	 * @return UPD_TIME
	 */
	public String getUpdTime() {
		return updTime;
	}

	/**
	 * @param updTime
	 */
	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	/**
	 * @return CHANNEL_ID
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return XS_UPDATE
	 */
	public String getXsUpdate() {
		return xsUpdate;
	}

	/**
	 * @param xsUpdate
	 */
	public void setXsUpdate(String xsUpdate) {
		this.xsUpdate = xsUpdate;
	}

	/**
	 * @return HIS_DEP_ID
	 */
	public String getHisDepId() {
		return hisDepId;
	}

	/**
	 * @param hisDepId
	 */
	public void setHisDepId(String hisDepId) {
		this.hisDepId = hisDepId;
	}

	/**
	 * @return HIS_DL_DEP_ID
	 */
	public String getHisDlDepId() {
		return hisDlDepId;
	}

	/**
	 * @param hisDlDepId
	 */
	public void setHisDlDepId(String hisDlDepId) {
		this.hisDlDepId = hisDlDepId;
	}

	/**
	 * @return GUARD_NAME
	 */
	public String getGuardName() {
		return guardName;
	}

	/**
	 * @param guardName
	 */
	public void setGuardName(String guardName) {
		this.guardName = guardName;
	}

	/**
	 * @return GUARD_CARD_NO
	 */
	public String getGuardCardNo() {
		return guardCardNo;
	}

	/**
	 * @param guardCardNo
	 */
	public void setGuardCardNo(String guardCardNo) {
		this.guardCardNo = guardCardNo;
	}

	/**
	 * @return GUARD_RELATION
	 */
	public String getGuardRelation() {
		return guardRelation;
	}

	/**
	 * @param guardRelation
	 */
	public void setGuardRelation(String guardRelation) {
		this.guardRelation = guardRelation;
	}

	/**
	 * @return ORDER_ARRIVE_TIME
	 */
	public String getOrderArriveTime() {
		return orderArriveTime;
	}

	/**
	 * @param orderArriveTime
	 */
	public void setOrderArriveTime(String orderArriveTime) {
		this.orderArriveTime = orderArriveTime;
	}

	/**
	 * @return PAY_ARRIVE_TIME
	 */
	public String getPayArriveTime() {
		return payArriveTime;
	}

	/**
	 * @param payArriveTime
	 */
	public void setPayArriveTime(String payArriveTime) {
		this.payArriveTime = payArriveTime;
	}

	/**
	 * @return HIS_GET_TIME
	 */
	public String getHisGetTime() {
		return hisGetTime;
	}

	/**
	 * @param hisGetTime
	 */
	public void setHisGetTime(String hisGetTime) {
		this.hisGetTime = hisGetTime;
	}

	/**
	 * @return HIS_GET_STATE
	 */
	public String getHisGetState() {
		return hisGetState;
	}

	/**
	 * @param hisGetState
	 */
	public void setHisGetState(String hisGetState) {
		this.hisGetState = hisGetState;
	}
}