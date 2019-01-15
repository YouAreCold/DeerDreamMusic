package com.xky.roll.music_api.service;

import com.xky.roll.music_service.pojo.LoginLog;
import com.xky.roll.music_service.pojo.ServiceLog;


public interface LoginlogService {

	//插入登入日志
	public void insert(LoginLog loginlog);
	
	public void insertServiceLog(ServiceLog servicelog);

	 

}
