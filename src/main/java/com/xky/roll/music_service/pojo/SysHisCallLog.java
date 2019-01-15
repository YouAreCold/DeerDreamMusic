package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_his_call_log")
public class SysHisCallLog {
    @Id
    private Integer logid;

    private String methodname;

    @Column(name = "resultCode")
    private String resultcode;

    private Date createtime;

    private String calltime;

    private String returntime;

    private String msg;

    private String resultmsg;

    private String errormsg;

    /**
     * @return logid
     */
    public Integer getLogid() {
        return logid;
    }

    /**
     * @param logid
     */
    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    /**
     * @return methodname
     */
    public String getMethodname() {
        return methodname;
    }

    /**
     * @param methodname
     */
    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    /**
     * @return resultCode
     */
    public String getResultcode() {
        return resultcode;
    }

    /**
     * @param resultcode
     */
    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return calltime
     */
    public String getCalltime() {
        return calltime;
    }

    /**
     * @param calltime
     */
    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    /**
     * @return returntime
     */
    public String getReturntime() {
        return returntime;
    }

    /**
     * @param returntime
     */
    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return resultmsg
     */
    public String getResultmsg() {
        return resultmsg;
    }

    /**
     * @param resultmsg
     */
    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }

    /**
     * @return errormsg
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * @param errormsg
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
}