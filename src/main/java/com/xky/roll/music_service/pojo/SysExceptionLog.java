package com.xky.roll.music_service.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
  * 异常处理  实体类
  * @ClassName: SysExceptionLog 
  * @Description: TODO() 
  * @author wujiaxin
  * @date 2017-9-30 下午5:07:03 
  *
  */
@Table(name = "sys_exception_log")
public class SysExceptionLog {
	
	
	/**
	 * 无参构造函数
	  * <p>Title: </p> 
	  * <p>Description: </p>
	 */
	public SysExceptionLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 有参构造函数
	  * <p>Title: </p> 
	  * <p>Description: </p> 
	  * @param exceptionMessage
	  * @param exceptionCode
	  * @param exceptionLongMessage
	  * @param exceptionLevel
	  * @param createtime
	 */
	public SysExceptionLog(String exceptionMessage, String exceptionCode,
			String exceptionLongMessage, String exceptionLevel,
			String createtime) {
		super();
		this.exceptionMessage = exceptionMessage;
		this.exceptionCode = exceptionCode;
		this.exceptionLongMessage = exceptionLongMessage;
		this.exceptionLevel = exceptionLevel;
		this.createtime = createtime;
	}

	/**
     * 异常信息标识
     */
    @Id
    @Column(name = "EXCEPTION_ID")
    private Integer exceptionId;

    /**
     * 简短异常信息
     */
    @Column(name = "EXCEPTION_MESSAGE")
    private String exceptionMessage;
    
    /**
     * 错误状态码
     */
    @Column(name = "EXCEPTION_CODE")
    private String exceptionCode;

    /**
     * 详细异常信息
     */
    @Column(name = "EXCEPTION_LONG_MESSAGE")
    private String exceptionLongMessage;

    /**
     * 异常严重程度
     */
    @Column(name = "EXCEPTION_LEVEL")
    private String exceptionLevel;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private String createtime;

    
    /**
     * 获取异常信息标识
     *
     */
	public Integer getExceptionId() {
		return exceptionId;
	}
	
	/**
     * 设置异常信息标识
     *
     */
	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	/**
     * 获取简短异常信息
     *
     */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
     * 设置简短异常信息
     *
     */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
     * 获取错误状态码
     *
     */
	public String getExceptionCode() {
		return exceptionCode;
	}

	/**
     * 设置错误状态码
     *
     */
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
     * 获取详细异常信息
     *
     */
	public String getExceptionLongMessage() {
		return exceptionLongMessage;
	}

	/**
     * 设置详细异常信息
     *
     */
	public void setExceptionLongMessage(String exceptionLongMessage) {
		this.exceptionLongMessage = exceptionLongMessage;
	}

	/**
     * 获取异常严重程度
     *
     */
	public String getExceptionLevel() {
		return exceptionLevel;
	}

	/**
     * 设置异常严重程度
     *
     */
	public void setExceptionLevel(String exceptionLevel) {
		this.exceptionLevel = exceptionLevel;
	}

	/**
     * 获取创建时间
     *
     */
	public String getCreatetime() {
		return createtime;
	}

	/**
     * 设置创建时间
     *
     */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	@Override
	public String toString() {
		return "SysExceptionLog [exceptionId=" + exceptionId
				+ ", exceptionMessage=" + exceptionMessage + ", exceptionCode="
				+ exceptionCode + ", exceptionLongMessage="
				+ exceptionLongMessage + ", exceptionLevel=" + exceptionLevel
				+ ", createtime=" + createtime + "]";
	}
}
