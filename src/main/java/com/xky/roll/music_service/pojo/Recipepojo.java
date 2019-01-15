package com.xky.roll.music_service.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "recipe")
@Entity
public class Recipepojo {
	
		@Id
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "messageId")
		private String  messageId;   // 消息ID
		
		@Column(name = "patientId")
		private String  patientId;   // 患者id
		
		@Column(name = "age")
		private String  age;   //
		
		@Column(name = "patientName")
		private String  patientName;   // 患者
		
		@Column(name = "cardNO")
		private String  cardNO;   // 患者i卡号
		
		@Column(name = "idcard")
		private String idcard; // 患者身份证
		
		@Column(name = "birth")
		private String birth;// 患者出生年月
		
		@Column(name = "sex")
		private String sex;// 患者性别
		
		@Column(name = "phone")
		private String phone;// 患者电话
		
		@Column(name = "ismzpatient")
		private String ismzpatient;  // 是否是门诊病人
		
		@Column(name = "depId")
		private String depId;  // 科室ID
		
		@Column(name = "depName")
		private String depName;  //科室名称
		
		@Column(name = "docId")
		private String docId;  // 医生id
		
		@Column(name = "jzcount")
		private String jzcount;  //就诊次数
		
		@Column(name = "fylb")
		private String fylb;  //费用类别
		
		@Column(name = "hisorder")
		private String hisorder;   //HIS挂号流水
		 
		@Column(name = "recipeTime")
		private String recipeTime;   //处方开出时间
		
		@Column(name = "orderControl")
		private String orderControl;  //申请控制
		 
		@Column(name = "recipeId")
		private String recipeId;   //处方ID
		
		@Column(name = "recipegroupId")
		private String recipegroupId;   //处方组ID
		
		@Column(name = "state")
		private String state;   //状态
		
		@Column(name = "jl")
		private String jl;   //计量
		
		@Column(name = "jlunits")
		private String jlunits;  //计量单位
		
		@Column(name = "pl")
		private String pl;   //频率
		
		@Column(name = "ts")
		private String ts;   //天数
		
		@Column(name = "cs")
		private String cs;   //次数
		
		@Column(name = "czybm")
		private String czybm;  //操作员编码
		
		@Column(name = "llkdbm")
		private String llkdbm;   //路入科室编码
		
		@Column(name = "hzkdbm")
		private String hzkdbm;//患者科室编码
		
		@Column(name = "ypbm")
		private String ypbm;  //药品编码
		
		@Column(name = "cflb")
		private String cflb;  //处方类别
		
		@Column(name = "yl")
		private String yl;  //用量
		
		@Column(name = "ylunits")
		private String ylunits;  //用量单位
		
		@Column(name = "ylunit")
		private String ylunit;  //用量 
		
		@Column(name = "yfbm")
		private String yfbm;  //药房编码 
		 
		@Column(name = "gyfsbm")
		private String gyfsbm;  //给药方式编码 
		
		@Column(name = "amount")
		private String amount;   //处方金额

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getPatientId() {
			return patientId;
		}

		public void setPatientId(String patientId) {
			this.patientId = patientId;
		}

		public String getCardNO() {
			return cardNO;
		}

		public void setCardNO(String cardNO) {
			this.cardNO = cardNO;
		}

		public String getIdcard() {
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String getBirth() {
			return birth;
		}

		public void setBirth(String birth) {
			this.birth = birth;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getIsmzpatient() {
			return ismzpatient;
		}

		public void setIsmzpatient(String ismzpatient) {
			this.ismzpatient = ismzpatient;
		}

		public String getDepId() {
			return depId;
		}

		public void setDepId(String depId) {
			this.depId = depId;
		}

		public String getDepName() {
			return depName;
		}

		public void setDepName(String depName) {
			this.depName = depName;
		}

		public String getDocId() {
			return docId;
		}

		public void setDocId(String docId) {
			this.docId = docId;
		}

		public String getJzcount() {
			return jzcount;
		}

		public void setJzcount(String jzcount) {
			this.jzcount = jzcount;
		}

		public String getFylb() {
			return fylb;
		}

		public void setFylb(String fylb) {
			this.fylb = fylb;
		}

		public String getHisorder() {
			return hisorder;
		}

		public void setHisorder(String hisorder) {
			this.hisorder = hisorder;
		}

		public String getRecipeTime() {
			return recipeTime;
		}

		public void setRecipeTime(String recipeTime) {
			this.recipeTime = recipeTime;
		}

		public String getOrderControl() {
			return orderControl;
		}

		public void setOrderControl(String orderControl) {
			this.orderControl = orderControl;
		}

		public String getRecipeId() {
			return recipeId;
		}

		public void setRecipeId(String recipeId) {
			this.recipeId = recipeId;
		}

		public String getRecipegroupId() {
			return recipegroupId;
		}

		public void setRecipegroupId(String recipegroupId) {
			this.recipegroupId = recipegroupId;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getJl() {
			return jl;
		}

		public void setJl(String jl) {
			this.jl = jl;
		}

		public String getJlunits() {
			return jlunits;
		}

		public void setJlunits(String jlunits) {
			this.jlunits = jlunits;
		}

		public String getPl() {
			return pl;
		}

		public void setPl(String pl) {
			this.pl = pl;
		}

		public String getTs() {
			return ts;
		}

		public void setTs(String ts) {
			this.ts = ts;
		}

		public String getCs() {
			return cs;
		}

		public void setCs(String cs) {
			this.cs = cs;
		}

		public String getCzybm() {
			return czybm;
		}

		public void setCzybm(String czybm) {
			this.czybm = czybm;
		}

		public String getLlkdbm() {
			return llkdbm;
		}

		public void setLlkdbm(String llkdbm) {
			this.llkdbm = llkdbm;
		}

		public String getHzkdbm() {
			return hzkdbm;
		}

		public void setHzkdbm(String hzkdbm) {
			this.hzkdbm = hzkdbm;
		}

		public String getYpbm() {
			return ypbm;
		}

		public void setYpbm(String ypbm) {
			this.ypbm = ypbm;
		}

		public String getCflb() {
			return cflb;
		}

		public void setCflb(String cflb) {
			this.cflb = cflb;
		}

		public String getYl() {
			return yl;
		}

		public void setYl(String yl) {
			this.yl = yl;
		}

		public String getYlunits() {
			return ylunits;
		}

		public void setYlunits(String ylunits) {
			this.ylunits = ylunits;
		}

		public String getYlunit() {
			return ylunit;
		}

		public void setYlunit(String ylunit) {
			this.ylunit = ylunit;
		}

		public String getYfbm() {
			return yfbm;
		}

		public void setYfbm(String yfbm) {
			this.yfbm = yfbm;
		}

		public String getGyfsbm() {
			return gyfsbm;
		}

		public void setGyfsbm(String gyfsbm) {
			this.gyfsbm = gyfsbm;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getPatientName() {
			return patientName;
		}

		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}

		public String getMessageId() {
			return messageId;
		}

		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}
		
}
