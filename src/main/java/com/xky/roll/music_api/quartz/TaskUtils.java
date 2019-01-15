package com.xky.roll.music_api.quartz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xky.roll.music_service.pojo.TaskScheduleJob;

public class TaskUtils {
	public static Logger log = LoggerFactory.getLogger(TaskUtils.class);
	/**
	 * 任务开始
	 */
	public static final String START = "start";
	/**
	 * 任务暂停
	 */
	public static final String PAUSE = "pause";
	/**
	 * 继续任务
	 */
	public static final String RESUME = "resume";
	/**
	 * 删除任务
	 */
	public static final String DELETE = "delete";

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(TaskScheduleJob scheduleJob) {
		Object object = null;
		Class<?> clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		Method method = null;
		try {
			if (StringUtils.isNotBlank(scheduleJob.getParams())) {
				method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), new Class[] { String.class });
				method.invoke(object, new Object[] { scheduleJob.getParams() });
			} else {
				method = clazz.getMethod(scheduleJob.getMethodName());
				method.invoke(object);
			}
			log.info("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (IllegalArgumentException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (InvocationTargetException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		}

	}

}
