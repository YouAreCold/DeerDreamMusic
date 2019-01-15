package com.xky.roll.music_service.service;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_service.pojo.SysUser;
@Transactional(value="masterTransactionManager")
public interface LoginService {
	public SysUser login(String userName,String password);
}
