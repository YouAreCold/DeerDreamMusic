package com.xky.roll.music_service.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xky.roll.music_service.pojo.SysUser;
import com.xky.roll.music_service.service.LoginService;

/**
 * 
 * @ClassName: LoginController
 * @Description: TODO(系统登入 登出)
 * @Author liyifan
 * @Date 2017年5月17日 下午2:48:55
 */
@Controller
public class LoginController{
	@Autowired
	private LoginService loginServiceImpl;
	
	public static Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 登陆的在线人数
	  * @Title: online 
	  * @Description: TODO() 
	  * @param @return    设定文件 
	  * @return String    返回类型 
	  * @throws
	 */
	@RequestMapping("/online")
	public String online(){
		return "online";
	}
	
	/**
	 * 检查是否重复登陆 , session是否过期
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/check")
	public String check(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		// 从session中获得用户的信息
		SysUser sysUser = (SysUser) req.getSession().getAttribute("user");
		// 判断session中的用户是否过期
		if(sysUser == null){
			// 登录的信息已经过期了!
			resp.getWriter().println("1");
			logger.info("登录的信息已经过期了");
		}else{
			// 登录的信息没有过期
			resp.getWriter().println("2");
			logger.info("登录的信息没有过期");
		}
		return null;
	}
	
	/**
	 * 登陆
	 * @param userName
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(String userName, String password, HttpServletRequest request) {
		//接受数据
		Map<String, String[]> map = request.getParameterMap();
		SysUser user = new SysUser();
		user.setPwd(password);
		user.setUserName(userName);
		
		//封装数据
		try {
			BeanUtils.populate(user, map);
			//调用Service层处理数据
			SysUser sysUser = loginServiceImpl.login(userName, password);
			if (sysUser == null) {
				//用户登陆失败
				logger.info("登陆失败（用户名或者密码错误）");
				return "0";
			}else{
				// 用户登录成功
				// 第一个BUG的解决:第二个用户登录后将之前的session销毁!
				request.getSession().invalidate();

				// 第二个BUG的解决:判断用户是否已经在Map集合中,存在：已经在列表中.销毁其session.
				// 获得到ServletCOntext中存的Map集合.
				Map<SysUser, HttpSession> userMap = (Map<SysUser, HttpSession>) request.getSession().getServletContext()
						.getAttribute("userMap");
				System.out.println(userMap);
				// 判断用户是否已经在map集合中'
				if(userMap.containsKey(sysUser)){
					// 说用map中有这个用户.
					HttpSession sessions = userMap.get(sysUser);
					// 将这个session销毁.
					sessions.invalidate();
				}
				
				// 使用监听器:HttpSessionBandingListener作用在JavaBean上的监听器.
				request.getSession().setAttribute("user", sysUser);
				
				//聊天功能
				ServletContext application = request.getSession().getServletContext();

				String sourceMessage = "";

				if (null != application.getAttribute("message")) {
					sourceMessage = application.getAttribute("message")
							.toString();
				}

				sourceMessage += "系统：<font color='gray'>"
						+ sysUser.getUserName() + "进入了院内云平台！</font><br>";
				application.setAttribute("message", sourceMessage);

				return "1";
			}
		} catch (Exception e) {
			logger.info("登陆发生异常");
			e.printStackTrace();
		}
		
		return null;
		
	}

	/**
	 * 退出登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		// 获得session对象
		HttpSession session = request.getSession();
		// 将session销毁
		session.invalidate();
		return "login";
	}

}
