package com.xky.roll.music_service.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "task_schedule_job")
public class TaskScheduleJob {
	@Id
	@Column(name = "job_id")
	@GeneratedValue(generator = "JDBC")
	private Integer jobId;

	@Column(name = "job_name")
	private String jobName;

	@Column(name = "job_group")
	private String jobGroup;

	@Column(name = "cron_expression")
	private String cronExpression;

	@Column(name = "bean_class")
	private String beanClass;

	@Column(name = "method_name")
	private String methodName;

	private String params;

	@Column(name = "job_status")
	private Byte jobStatus;

	@Column(name = "last_time")
	private Date lastTime;

	@Column(name = "next_time")
	private Date nextTime;

	private String description;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * @return job_id
	 */
	public Integer getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return job_name
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return job_group
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * @param jobGroup
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * @return cron_expression
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * @param cronExpression
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * @return bean_class
	 */
	public String getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass
	 */
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @return method_name
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return job_status
	 */
	public Byte getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus
	 */
	public void setJobStatus(Byte jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * @return last_time
	 */
	public Date getLastTime() {
		return lastTime;
	}

	/**
	 * @param lastTime
	 */
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * @return next_time
	 */
	public Date getNextTime() {
		return nextTime;
	}

	/**
	 * @param nextTime
	 */
	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}