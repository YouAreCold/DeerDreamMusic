package com.xky.roll.music_service.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xky.roll.music_service.pojo.SysUser;
import com.xky.roll.music_service.util.datestate.TimeFormatUtil;
import com.xky.roll.music_service.util.desktop.SensitivewordFilter;

/**
 * 
  * @ClassName: ChatRoomController 
  * @Description: TODO(聊天交谈模块) 
  * @author wujiaxin
  * @date 2017-9-29 上午10:35:13 
  *
 */
@Controller
public class ChatRoomController {
	
	/**
	 * 跳转至聊天页面
	 * @throws IOException 
	 */
	@RequestMapping("/chatRoomPage")
	public String homePage() {
		return "chatRoomPage";
	}
	
	/**
	 * 发送聊天内容
	 * @throws IOException 
	 */
	@RequestMapping("/sendMessage")
	public String sendMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.接收数据 。
		String from = req.getParameter("from"); // 发言人
		String face = req.getParameter("face"); // 表情
		String to = req.getParameter("to"); // 接收者
		String color = req.getParameter("color"); // 字体颜色
		String content = SensitivewordFilter.checkDeskString(req.getParameter("content")); // 发言内容(敏感词过滤)
		
		// 发言时间 正常情况下使用SimpleDateFormat
		String sendTime = new Date().toLocaleString(); // 发言时间
		// 获得ServletContext对象.
		ServletContext application = req.getSession().getServletContext();
		//  从ServletContext中获取消息
		String sourceMessage = (String) application.getAttribute("message");
		// 拼接发言的内容:xx 对 yy 说 xxx
		sourceMessage += "<font color='blue'><strong>" + from
				+ "</strong></font><font color='#CC0000'>" + face
				+ "</font>对<font color='green'>[" + to + "]</font>说："
				+ "<font color='" + color + "'>" + content + "</font>（"
				+ sendTime + "["+TimeFormatUtil.getInterval(new Date())+"]）<br>";
		// 将消息存入到application的范围
		application.setAttribute("message", sourceMessage);
		return getMessage(req, resp);
	}
	
	/**
	 * 返回在线人员列表
	 */
	@RequestMapping("/onlineList")
	public String onlineList(){
		
		return "onlineList";
	}
	
	/**
	 * 获取消息的方法
	 * @throws IOException 
	 */
	@RequestMapping("/getMessage")
	public String getMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String message = (String) req.getSession().getServletContext().getAttribute("message");
		if(message != null){
			resp.getWriter().println(message);
		}
		return null;
	}
	/**
	 * 踢人的功能
	 * @throws IOException 
	 */
	@RequestMapping("/kick")
	public String kick(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.接收参数
		int userId = Integer.parseInt(req.getParameter("userId"));
		// 2.踢人:从userMap中将用户对应的session销毁.
		// 获得userMap集合(在线列表)
		Map<SysUser, HttpSession> userMap = (Map<SysUser, HttpSession>)req.getSession().
				getServletContext().getAttribute("userMap");
		// 获得这个用户对应的session.如何知道是哪个用户呢? id已经传递过来.去数据库中查询.
		// 重写user的equals 和 hashCode 方法 那么只要用户的id相同就认为是同一个用户.
		SysUser user = new SysUser();
		user.setUserId(userId);
		// 从map集合中获得用户的对应的session
		HttpSession session = userMap.get(user);
		// 销毁session
		session.invalidate();
		
		return "chatRoomPage";
	}
}
