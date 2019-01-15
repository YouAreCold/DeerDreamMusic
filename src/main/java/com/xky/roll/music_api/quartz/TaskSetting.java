package com.xky.roll.music_api.quartz;

import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import tk.mybatis.mapper.entity.Example;

import com.xky.roll.music_service.mapper.TaskScheduleJobMapper;
import com.xky.roll.music_service.pojo.TaskScheduleJob;

/**
 * 
 * @Description:TODO(维护任务调度)
 * @author:liyifan
 * @time:2017年6月1日 下午6:42:16
 */
@Component
public class TaskSetting {
	public static Logger log = LoggerFactory.getLogger(TaskSetting.class);
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private TaskScheduleJobMapper taskScheduleJobMapper;

	@PostConstruct
	public void init() throws Exception {
		// 获取数据库中的任务
		Example example = new Example(TaskScheduleJob.class);
		List<TaskScheduleJob> list = taskScheduleJobMapper.selectByExample(example);
		for (TaskScheduleJob job : list) {
			create(job);
		}
	}

	// 创建任务
	public void create(TaskScheduleJob job) throws SchedulerException {
		if (job == null) {
			return;
		}
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobId().toString());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = QuartzJobFactory.class;
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobId().toString()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			trigger = newTrigger().withIdentity(job.getJobId().toString()).forJob(jobDetail)
					.withSchedule(scheduleBuilder).build();
			try {
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
		if (job.getJobStatus() == 0) {
			pause(job);
		}
	}

	// 暂停任务
	public void pause(TaskScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobId().toString());
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			log.info(job.getJobName() + "暂停失败");
		}
	}

	// 恢复任务
	public void resume(TaskScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobId().toString());
		try {
			System.out.println(job.getJobStatus());
			if (job.getJobStatus() == 1) {
				scheduler.resumeJob(jobKey);
			}
		} catch (SchedulerException e) {
			log.info(job.getJobName() + "恢复失败");
		}
	}

	// 删除任务
	public void delete(TaskScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobId().toString());
		try {
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			log.info(job.getJobName() + "删除失败");
		}
	}

	// 立即执行一次
	public void runNow(TaskScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobId().toString());
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.info("立即运行" + job.getJobName() + "失败");
		}
	}

	// 更新任务
	public void reSet(TaskScheduleJob job) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobId().toString());
		// 获取trigger
		CronTrigger trigger = null;
		try {
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
		// 重新设置参数
		trigger.getJobDataMap().put("scheduleJob", job);
		// 按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		// 按新的trigger重新设置job执行
		try {
			scheduler.rescheduleJob(triggerKey, trigger);
			if (job.getJobStatus() == 0) {
				pause(job);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.info("更新" + job.getJobName() + "失败");
		}
	}
}
