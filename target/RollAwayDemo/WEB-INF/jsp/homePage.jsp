<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="safecheck.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>后台管理系统</title>

<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<script type="text/javascript" src="${path}/his/js/common.js"></script>
<link href="${path }/common/CSS/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path }/common/CSS/style.css" />
<link rel="stylesheet" href="${path }/homepage/assets/css/bootstrap.css" />
<link rel="stylesheet" href="${path }/homepage/assets/css/font-awesome.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="${path }/homepage/assets/css/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${path }/homepage/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" type="text/css" href="${path }/homepage/css/tab.css" />
<link rel="stylesheet" type="text/css" href="${path }/common/styles/page/pagination.css" />
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${ctx}/homepage/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/homepage/assets/css/ace-ie.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="${path }/homepage/assets/js/ace-extra.js"></script>
<script src="${path }/common/js/jquery-1.4.js"></script>
<script>
$(function(){
	check();
	showOnLine();
});
/* 检查session是否过期 */
window.setInterval("check();",30000);		//定时检查
window.setInterval("showOnLine();",15000);	//定时显示在线人数
function check(){
	$.post("${path}/check",function(data){
		if(data == 1){
			// 提示用户下线了
			alert("用户已经被踢下线了!");
			// 回到登录页面!
			window.location = "/front-end-processor";
		}
	});
}
//显示在线人员列表
function showOnLine(){
	$.post("${path}/online",function(data){
		$("#online").html(data);
	});
}
</script>
<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="${ctx}/homepage/assets/js/html5shiv.js"></script>
		<script src="${ctx}/homepage/assets/js/respond.js"></script>
		<![endif]-->
<link rel="stylesheet" type="text/css" href="${path}/css/homePage.css" />
</head>
<body class="no-skin">
	<!-- #section:basics/navbar.layout -->
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<!-- #section:basics/sidebar.mobile.toggle -->
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<!-- /section:basics/sidebar.mobile.toggle -->
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> 
					<small> <i class="fa fa-laptop"></i>音乐API管理系统 </small>
					<span id="online" style="font-size:12px;margin-left:30px;"></span>
				</a>
			</div>
			<ul class="headmenu absolute">
				<li><i class="fa fa-user"  style="margin-right: 4px;"></i>您好！</li>
				<li>${user.userName }</li>
				<li><a href="${path }/logout">退出登录 </a></li>
			</ul>
		</div>
		<!-- /.navbar-container -->
	</div>
	<div class="main-container ace-save-state" id="main-container">
		<div id="sidebar" class="sidebar responsive ace-save-state" >
			<ul class="nav nav-list">
				<li class=""><a href="#"> <i
						class="menu-icon fa fa-tachometer"></i> <span class="menu-text">控制面板
					</span>
				</a><b class="arrow"></b></li>


				<c:forEach items="${list}" var="u">
					<c:if test="${u.parentId==null }">
						<li class="open"><a href="#" class="dropdown-toggle"><i
								class="menu-icon fa fa-hospital-o"></i> <span class="menu-text">
									${u.menuName }</span> <b class="arrow fa fa-angle-down"></b>
						</a> <b class="arrow"></b>
							<ul class="submenu">
								<c:forEach items="${list}" var="b">
									<c:if test="${b.parentId==u.menuId }">
										<li class=""><a href="${path }${b.actionUrl}" target="iframe"  class="J_menuItem">
												<i class="menu-icon fa fa-caret-right"></i> ${b.menuName }
										</a> <b class="arrow"></b></li>
									</c:if>
								</c:forEach>
							</ul>
					</c:if>
				</c:forEach>
			</ul>
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon"
					class="ace-icon fa fa-mail-reply ace-save-state"
					data-icon1="ace-icon fa fa-mail-reply"
					data-icon2="ace-icon fa fa-share"></i>
			</div>

			<!-- /section:basics/sidebar.layout.minimize -->
		</div>

		<!-- /section:basics/sidebar -->
		<div class="main-content" >
			<div class="main-content-inner">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs" id="breadcrumbs" style="padding:0">
					<div class="content-tabs">
						<button class="roll-nav roll-left J_tabLeft">
							<i class="fa fa-backward"></i>
						</button>
						<nav class="page-tabs J_menuTabs">
							<div class="page-tabs-content"></div>
						</nav>
						<button class="roll-nav roll-right J_tabRight">
							<i class="fa fa-forward"></i>
						</button>
						<div class="btn-group roll-nav roll-right">
							<button class="dropdown J_tabClose" data-toggle="dropdown">
								关闭操作<span class="caret"></span>

							</button>
							<ul role="menu" class="dropdown-menu dropdown-menu-right">
								<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
								<li class="divider"></li>
								<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
								<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="J_mainContent" id="content-main">
					<iframe class="J_iframe" name="iframe" width="100%" height="100%"
						src="${path }/listMenu" frameborder="0"
						data-id="${path }/sysUser/listMenu.html" seamless></iframe>
				</div>
			</div>


		</div>

		<div class="footer">
			<div class="footer-inner">
				<!-- #section:basics/footer -->
				<div class="footer-content">
					<span class="bigger-120"> <span class="blue bolder">个人音乐网</span>
						&copy; 2014-2016
					</span>
				</div>
			</div>
		</div>

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	
	<script src="${path }/homepage/assets/js/jquery.js"></script>

	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='${path}/homepage/assets/js/jquery.mobile.custom.js'>"
							+ "<"+"/script>");
	</script>
	<script src="${path }/homepage/assets/js/bootstrap.js"></script>

	<script src="${path }/homepage/assets/js/ace/elements.scroller.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.colorpicker.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.fileinput.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.typeahead.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.wysiwyg.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.spinner.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.treeview.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.wizard.js"></script>
	<script src="${path }/homepage/assets/js/ace/elements.aside.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.ajax-content.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.touch-drag.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.sidebar.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.sidebar-scroll-1.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.submenu-hover.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.widget-box.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.settings.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.settings-rtl.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.settings-skin.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.widget-on-reload.js"></script>
	<script src="${path }/homepage/assets/js/ace/ace.searchbox-autocomplete.js"></script>
	<script src="${path }/common/layer/layer.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="${path }/homepage/js/contabs.js" type="text/javascript"
		charset="utf-8"></script>
</body>
</html>
