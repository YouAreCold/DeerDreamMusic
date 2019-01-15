package com.xky.roll.music_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xky.roll.music_service.service.SysUserService;

@Controller
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/cache/{id}")
	public void findUserById(@PathVariable int id) {
		//第一次查询
        System.out.println(sysUserService.findUserById(id));
        //通过缓存查询
        System.out.println(sysUserService.findUserById(id));
	}

}
