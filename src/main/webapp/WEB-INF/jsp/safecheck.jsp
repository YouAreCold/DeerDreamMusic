<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%if(null == session.getAttribute("user")){
	out.println("<script language='javascript'>alert('您的账户已经过期，请重新登录!');window.location='/front-end-processor';</script>");
	return; 
}%>
