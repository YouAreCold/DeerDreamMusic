package com.xky.roll.music_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xky.roll.music_service.mapper.SysUserMapper;
import com.xky.roll.music_service.pojo.SysUser;
import com.xky.roll.music_service.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	// 这里的单引号不能少，否则会报错，被识别是一个对象
	/*
	 * private static final String CACHE_KEY = "'user'"; private static final
	 * String DEMO_CACHE_NAME = "myCache";
	 */
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser findUserById(int id) {
		System.out.println("没有走缓存" + id);
		return sysUserMapper.selectByPrimaryKey(id);
	}

}
