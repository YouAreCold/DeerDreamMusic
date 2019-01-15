package com.xky.roll.music_api.constall;

public enum ResultCode {
	SUCCESS("0", "success!"),
    ADD_FAILURE_HIS_FAIL("400", "MUSIC结果处理失败"),
    ADD_FAILURE_OPENAPI_FAIL("500", "处理失败");
    
    private String msgCode;
    private String msgContent;
    
    public String getMsgCode() {
        return msgCode;
    }
    
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
    
    public String getMsgContent() {
        return msgContent;
    }
    
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    
    private ResultCode(String msgCode, String msgContent) {
        this.msgCode = msgCode;
        this.msgContent = msgContent;
    }
 
}
