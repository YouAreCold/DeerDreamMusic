package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "login_log")
@Entity
public class LoginLog {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "logname")
	private String logname;
	
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "createtime")
	private Date createtime;
	
	@Column(name = "succeed")
	private String succeed;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "ip")
	private String ip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
