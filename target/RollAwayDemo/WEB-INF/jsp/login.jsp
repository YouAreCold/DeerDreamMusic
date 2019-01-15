<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	request.getSession().invalidate();
	Cookie[] cookies = request.getCookies();
	if (cookies != null && cookies.length > 0)
		cookies[0].setMaxAge(0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<script type="text/javascript" src="${path}/his/js/common.js"></script>
<link href="${path}/common/CSS/login1.css" rel="stylesheet" type="text/css" />
<link href="${path}/common/CSS/base.css" rel="stylesheet" type="text/css" />
<link href="${path}/common/CSS/style8.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/common/CSS/login_animation.css" />
<script src="${path}/common/scripts/jquery.min.js"></script>
<script type='text/javascript' src='${path}/common/js/security.js'></script>
<script type='text/javascript' src='${path}/common/js/jQuery.md5.js'></script>
<style type="text/css">
body {
	background: silver;
}

.loginbox {
	top: 150px;
	-ms-animation: mymove 0.5s;
	-moz-animation: mymove 0.5s;
	-o-animation: mymove 0.5s;
	-webkit-animation: mymove 0.5s;
	animation: mymove 0.5s;
}

.loginbox .row {
	border-radius: 4px;
	overflow: hidden
}

.loginbox .lt {
	width: 100%;
	background: #fff;
	color: #949995;
	text-align: center;
	font-size: 16px;
}

.loginbox .tabtop li.active {
	border-bottom-color: deepskyblue;
	color: deepskyblue;
}

.loginbox .form-group select {
	height: 42px;
	line-height: 42px;
	background-color: transparent;
	border: 0;
	margin-left: 55px;
	width: 358px;
}

option {
	min-height: 30px;
	margin-left: 60px;
}

.loginbox .form-group {
	margin: 30px 18px;
}

.loginbox .form-group, .loginbox .form-group {
	border: 1px solid #86aff3;
	border-radius: 4px;
}

.loginbox .form-group label {
	border-right: 1px solid #86aff3;
}

.loginbox .btn {
	display: block;
	margin: 8px auto 36px auto;
	width: 40%;
	line-height: 40px;
	background-color: #3374dc;
}

.loginbox .row {
	background-color: #fff;
	border: 1px solid #86aff3;
	box-shadow: 4px 4px 8px #888;
}
.log{
width: 300px;  padding-top: 60px;
}

@media screen and (min-width: 1500px) {
.log{
	width: 40%;  
	padding-top: 80px;
	height:400px;
}
}
</style>

<script type="text/javascript">
	$(function() {
		$('#userType').val(3);
		$('#formbackground').height($(window).height());
		$('#formbackground').width($(window).width());
		//enter键事件,用于登录
		document.onkeydown = function(event) {
			var e = event || window.event
					|| arguments.callee.caller.arguments[0];
			if (e && e.keyCode == 13) { // enter 键
				//要做的事情
				login();
				return false;
			}
		};

	});
</script>

</head>
<body>
	<div id="formbackground" style="position: absolute; z-index: -1;">
		<img src="${path}/common/images/login_bg.png" height="100%" width="100%" />
	</div>
	<div class="main">
		<div class="container">
			<div class="loginbox">
				<div class="row relative log">
					<div class="lt">音&nbsp;乐&nbsp;云&nbsp;管&nbsp;理&nbsp;平&nbsp;台</div>
					<div class="col-12">
						<div class="logintab">
							<div class="">
								<div class=" ">
									<form id="loginForm" name="loginForm" action="" method="post">
										<input type="hidden" name="token"
											value="<%=request.getSession().getId()%>" /> <span
											class="form-control">${loginStatus }</span>
										<div class="form-group">
											<label for="username"><img
												src="${path}/common/images/login_yh.png" /></label> <input type="text"
												class="form-control" name="userName" id="userName"
												placeholder="手机、邮箱或用户名" />
										</div>
										<div class="form-group">
											<label for="password"><img
												src="${path}/common/images/login_mm.png" /></label> <input
												type="password" class="form-control" name="PWD" id="PWD"
												placeholder="密码" /> <input type="hidden"
												class="form-control" name="PWD" />
										</div>
										<button type="button" onclick="login();" 
											class="btn btn-default">登&nbsp;&nbsp;录</button>
										<div class="checkbox"></div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//提交登录信息
		function login() {
			if ($.trim($("#userName").val()) == "") {
				alert("用户名不能为空")
				return;
			} else {
				$("#userName").css("border", "");
			}
			if ($.trim($("#PWD").val()) == "") {
				alert("密码不能为空");
				return;
			} else {
				$("#PWD").css("border", "");
			}
			if (($.trim($("#userName").val()) != "")
					&& ($.trim($("#PWD").val()) != "")) {
				var username = document.getElementById("userName").value;
				var pwd = $.md5(document.getElementById("PWD").value);
				$.ajax({
					type : "POST",
					url : "${path}/login",
					data : "userName=" + username + "&password=" + pwd,
					success : function(msg) {
						if (msg == "1") {
							window.location = "${path}/homePage";
						} else {
							alert("用户或密码错误，请重新输入！！！");
						}
					}
				});
			}
		}
	</script>
</body>
</html>
