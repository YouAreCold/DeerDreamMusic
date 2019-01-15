package com.xky.roll.music_api.util.webservice;

import net.sf.json.JSONObject;

/**
 * 
 * @Description:TODO(将His的响应转化为统一响应)
 * @author:liyifan
 * @time:2017年5月27日 上午11:15:13
 */
public class CustomResponse {

	protected boolean result;
	protected String msg = "";
	protected Object data;
	protected String status;

	public static CustomResponse success() {
		return new CustomResponse(true);
	}

	public static CustomResponse failure() {
		return new CustomResponse(false);
	}

	public static CustomResponse success(String message) {
		return new CustomResponse(true, message);
	}

	public static CustomResponse failure(String message) {
		return new CustomResponse(false, message);
	}

	public CustomResponse() {
		super();
	}

	public CustomResponse(boolean success) {
		this(success, success ? "0" : "1");
	}

	public CustomResponse(boolean success, String message) {
		this(success, message, null);
	}

	public CustomResponse(boolean success, String message, Object data) {
		this.result = success;
		this.msg = message;
		this.data = data;
		this.status = success ? "0" : "1";
	}

	public CustomResponse(String status, String message, Object data) {
		this.msg = message;
		this.data = data;
		this.status = status;
	}

	public String toJsonStr() {
		return "";
	}

	public CustomResponse(String status, String message) {
		this.msg = message;
		this.status = status;
	}

	public boolean isResult() {
		return result;
	}

	public CustomResponse result(boolean result) {
		this.result = result;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public CustomResponse message(String message) {
		this.msg = message;
		return this;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.msg = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public CustomResponse data(Object data) {
		this.data = data;
		return this;
	}

	public Object getStatus() {
		return status;
	}

	public CustomResponse status(String status) {
		this.status = status;
		return this;
	}

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CustomResponse count(int count) {
		this.count = count;
		return this;
	}

	public JSONObject toJsonObj() {
		return JSONObject.fromObject(this);
	}

	public JSONObject parseData2Json() {
		return JSONObject.fromObject(this.getData());
	}
}
