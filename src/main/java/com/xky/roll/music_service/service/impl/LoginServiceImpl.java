package com.xky.roll.music_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.xky.roll.music_api.log.LogManager;
import com.xky.roll.music_api.log.factory.LogTaskFactory;
import com.xky.roll.music_service.mapper.SysUserMapper;
import com.xky.roll.music_service.pojo.SysUser;
import com.xky.roll.music_service.service.LoginService;
import com.xky.roll.music_service.util.MD5;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private SysUserMapper sysUserMapper;

	public SysUser login(String userName, String password) {
		LogManager.getLog().executeLog(LogTaskFactory.loginTask(userName, password));
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("userName", userName);
		String passwordMD5 = new MD5().getMD5ofStr(password);
		List<SysUser> userList = sysUserMapper.selectByExample(example);
		SysUser user = userList.get(0);
		if (userList == null || userList.size() == 0) {
			return null;
		} else {
			if (!user.getPwd().equals(passwordMD5)) {
				return null;
			} else {
				return user;
			}
		}
	}
}
