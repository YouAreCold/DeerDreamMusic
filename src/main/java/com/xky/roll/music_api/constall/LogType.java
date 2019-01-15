package com.xky.roll.music_api.constall;

public enum LogType {
	
	LOGINLOG("登入系统"),
	LOGOUT("登出系统"),
	EXCEPTION("系统异常"),
	SUCCESS("成功"),
	FAIL("失败");

    String message;

    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
