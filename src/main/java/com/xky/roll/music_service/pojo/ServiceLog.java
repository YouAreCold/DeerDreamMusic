package com.xky.roll.music_service.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "operation_log")
public class ServiceLog {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "logType")
	private String logType;
	
	@Column(name = "logname")
	private String logname;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "classname")
	private String classname;
	
	@Column(name = "method")
	private String method;
	
	@Column(name = "createtime")
	private Date createtime;
	
	@Column(name = "succeed")
	private String succeed;
	
	@Column(name = "message")
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
