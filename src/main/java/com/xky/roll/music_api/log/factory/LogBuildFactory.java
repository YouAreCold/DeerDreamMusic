package com.xky.roll.music_api.log.factory;

import java.util.Date;

import com.xky.roll.music_api.constall.LogType;
import com.xky.roll.music_service.pojo.LoginLog;
import com.xky.roll.music_service.pojo.ServiceLog;

public class LogBuildFactory {

	   /**
	    * 业务日志封装
	    * @param logType
	    * @param userId
	    * @param bussinessName
	    * @param clazzName
	    * @param methodName
	    * @param msg
	    * @param succeed
	    * @return
	    */
	public static ServiceLog crearServiceLog(LogType logType, String userId, String bussinessName, String clazzName, String methodName, String msg, LogType succeed){
		ServiceLog servicelog = new ServiceLog();
		servicelog.setLogType(logType.getMessage());
		servicelog.setClassname(clazzName);
		servicelog.setCreatetime(new Date());
		servicelog.setLogname(bussinessName);
		servicelog.setMessage(msg);
		servicelog.setMethod(methodName);
		servicelog.setSucceed(logType.getMessage());
		servicelog.setUserid(userId);
		return servicelog;
	}
	
	/**
	 * 登入日志封装
	 * @param logType
	 * @param userId
	 * @param msg
	 * @param ip
	 * @return
	 */
	 public static LoginLog createLoginLog(LogType logType, String userId, String msg,String ip) {
	        LoginLog loginLog = new LoginLog();
	        loginLog.setLogname(logType.getMessage());
	        loginLog.setUserid(userId);
	        loginLog.setCreatetime(new Date());
	        loginLog.setSucceed(logType.SUCCESS.getMessage());
	        loginLog.setIp(ip);
	        loginLog.setMessage(msg);
	        return loginLog;
	    }
	
}
