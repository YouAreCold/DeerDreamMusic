package com.xky.roll.music_api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xky.roll.music_api.service.LoginlogService;
import com.xky.roll.music_service.mapper.LoginLogMapper;
import com.xky.roll.music_service.mapper.ServiceLogMapper;
import com.xky.roll.music_service.pojo.LoginLog;
import com.xky.roll.music_service.pojo.ServiceLog;

@Service
public class LoginlogServiceImpl implements LoginlogService {
	public static Logger logger = LoggerFactory.getLogger(LoginlogServiceImpl.class);
	@Autowired
	private LoginLogMapper logMapper;
	@Autowired
	private ServiceLogMapper serviceLogMapper;

	@Override
	public void insert(LoginLog loginLog) {
		try{
		logMapper.insert(loginLog);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void insertServiceLog(ServiceLog servicelog) {
		try{
			serviceLogMapper.insert(servicelog);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	 
}
