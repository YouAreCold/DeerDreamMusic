package com.xky.roll.music_api.util.http;

public enum ResultMessage {
	
	RESULT_MESSAGE_SUCCESS("0","返回成功"),
	RESULT_MESSAGE_FAILE("1","返回失败"),
	/* 验证值失败的 以3开头 */
	RESULT_MESSAGE_ISEMPTY("300","json数据为空"),
	RESULT_GETHIS_MESSAGE("400","his系统返回错误"),
	RESULT_SYSTEM_ERROR("500","系统错误");
	
	private String msgcode;
	
	private String msgcontent;

	public String getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	
	private ResultMessage(String msgcode,String msgcontent){
		this.msgcode = msgcode;
		this.msgcontent = msgcontent;
		
	}

}
