package com.xky.roll.music_api.pojo;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
	private long btime;
	private long etime;
	private int code;
	private String msg;
	private int dataLen;
	private Map<Object, Object> data;

	public long getBtime() {
		return btime;
	}

	public void setBtime(long btime) {
		this.btime = btime;
	}

	public long getEtime() {
		return etime;
	}

	public void setEtime(long etime) {
		this.etime = etime;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getDataLen() {
		return dataLen;
	}

	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}

	public Map<Object, Object> getData() {
		return data;
	}

	public void setData(Map<Object, Object> data) {
		this.data = data;
	}

}
