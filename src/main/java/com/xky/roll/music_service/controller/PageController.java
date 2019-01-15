package com.xky.roll.music_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: HelloController
 * @Description: TODO(只用于页面的跳转至登录)
 * @Author liyifan
 * @Date 2017年5月17日 上午11:36:28
 */
@Controller
public class PageController {
	@RequestMapping("/")
	public String homePage() {
		return "login";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
