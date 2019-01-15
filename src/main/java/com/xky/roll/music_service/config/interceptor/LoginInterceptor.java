package com.xky.roll.music_service.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xky.roll.music_service.pojo.SysUser;

public class LoginInterceptor implements HandlerInterceptor {

	// 平台接收的前缀
	private static String restUrl = "/api/";
	private static String interfaceUrl = "/interface";
	private static String noticeUrl = "/getNotice";
	private static String checkBillUrl = "/support";
	private static String musicDataUrl = "/pullData/";
	private static String musicmanagerUrl = "/musicmanager/";
	private static String musicapiUrl = "/musicapi/";
	private static String indexUrl = "/index";
	private static String imageUrl = "/DeerDreamMusic-View/";

	// 执行handler之前执行 用于用户认证校验或权限校验

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		if (checkUrl(url, request)) {
			return true;
		}

		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("user");
		if (sysUser != null) {
			return true;
		}
		request.getRequestDispatcher("transform.jsp").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public boolean checkUrl(String url, HttpServletRequest request) {
		String path = request.getContextPath();
		if (url == null) {
			return false;
		}
		if (url.indexOf(musicapiUrl) >= 0 ||url.indexOf(restUrl) >= 0 || url.indexOf(interfaceUrl) >= 0 || url.indexOf(noticeUrl) >= 0 || url.indexOf(checkBillUrl) >= 0 || url.indexOf(musicDataUrl) >= 0 || url.indexOf(musicmanagerUrl) >= 0|| url.indexOf(indexUrl) >= 0|| url.indexOf(imageUrl) >= 0) {
			return true;
		}
		if ((path + "/").equals(url) || (path + "/login").endsWith(url)) {
			return true;
		}
		return false;

	}

}
