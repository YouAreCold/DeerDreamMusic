package com.xky.roll.music_api.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.xky.roll.music_service.pojo.TaskScheduleJob;
import com.xky.roll.music_service.service.TaskService;

/**
 * 
 * @Description: 计划任务执行处 无状态
 * @date 2016年08月24日 下午5:05:47
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {
	@Autowired
	private TaskService taskServiceImpl;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	public static Logger log = LoggerFactory.getLogger(QuartzJobFactory.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		try {
			System.out.println("当前有" + scheduler.getCurrentlyExecutingJobs().size() + "个任务在运行");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		TaskScheduleJob scheduleJob = (TaskScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		scheduleJob.setJobStatus(taskServiceImpl.findTaskById(scheduleJob.getJobId()).getJobStatus());
		scheduleJob.setLastTime(context.getFireTime());
		scheduleJob.setNextTime(context.getNextFireTime());
		TaskUtils.invokMethod(scheduleJob);
		taskServiceImpl.update(scheduleJob);
	}
}