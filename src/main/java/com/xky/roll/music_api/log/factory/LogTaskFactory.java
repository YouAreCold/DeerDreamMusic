package com.xky.roll.music_api.log.factory;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.xky.roll.music_api.constall.LogType;
import com.xky.roll.music_api.service.LoginlogService;
import com.xky.roll.music_api.util.SpringContext;
import com.xky.roll.music_service.pojo.LoginLog;
import com.xky.roll.music_service.pojo.ServiceLog;
import com.xky.roll.music_service.util.StringUtil;

public class LogTaskFactory {
	private static Logger logger = Logger.getLogger(LogTaskFactory.class);
	private static LoginlogService loginMapper = SpringContext.getBean(LoginlogService.class);
	
	public static TimerTask loginTask(final String userId,final String ip){
		 return new TimerTask(){
			@Override
			public void run() {
				LoginLog bulid = LogBuildFactory.createLoginLog(LogType.LOGINLOG, userId, "", ip);
				loginMapper.insert(bulid);
			}
		 };
	}
	
	public static TimerTask exceptionLog(final String userId, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = StringUtil.getExceptionMsg(exception);
                ServiceLog servicelog = LogBuildFactory.crearServiceLog(
                        LogType.EXCEPTION, userId, "", null, null, msg, LogType.FAIL);
                try {
                	loginMapper.insertServiceLog(servicelog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }

}
