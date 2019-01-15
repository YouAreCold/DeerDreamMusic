package com.xky.roll.music_service.pojo;

public class ServiceResult {
	/**
	 * 成功
	 */
	public final static Integer SUCCESS = 0;
	
	/**
	 * 失败
	 */
	public final static Integer FAILED = 1;
	// 响应业务状态(0成功 1失败)
	private Integer status;
	// 响应消息
	private String msg;
	// 响应中的数据
	private Object data;

	public ServiceResult(Object data) {
		this.status = 0;
		this.msg = "操作成功!";
		this.data = data;
	}

	public ServiceResult() {
	}

	public static ServiceResult ok(Object data) {
		return new ServiceResult(data);
	}

	public static ServiceResult ok() {
		return new ServiceResult(null);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
