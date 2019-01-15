package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_interface_log")
public class SysInterfaceLog {
    @Id
    private Integer logid;

    private Integer sortnum;

    private String calltime;

    private String returntime;

    private String methodname;

    private Date createtime;

    private String inparam;

    private String errormsg;

    private String returnmsg;

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
     * @return sortnum
     */
    public Integer getSortnum() {
        return sortnum;
    }

    /**
     * @param sortnum
     */
    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
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
     * @return inparam
     */
    public String getInparam() {
        return inparam;
    }

    /**
     * @param inparam
     */
    public void setInparam(String inparam) {
        this.inparam = inparam;
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

    /**
     * @return returnmsg
     */
    public String getReturnmsg() {
        return returnmsg;
    }

    /**
     * @param returnmsg
     */
    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }
}