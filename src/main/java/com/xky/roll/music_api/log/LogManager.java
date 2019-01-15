package com.xky.roll.music_api.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志管理器
 *
 * @author yangshugan
 * @date 2017-06-03 16:29
 */
public class LogManager {
	
	 
	private static Integer DEALY_TIME = 10;
	 
	//异步线程
	private ScheduledThreadPoolExecutor schdeule = new ScheduledThreadPoolExecutor(10);
	
	public static LogManager logManager = new LogManager();
	
	public LogManager(){}
	
	public static LogManager getLog(){
		return logManager;
	}
	public void executeLog(TimerTask task){
		schdeule.schedule(task, DEALY_TIME,TimeUnit.MILLISECONDS);
	}

}
