<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'clientareaMain.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${path}/his/js/common.js"></script>
<link href="${path}/css/style1.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/content.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/drp.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/common/js/jquery-1.4.js"></script>
<script type="text/javascript">
	function updateMenu() {
		//var id=document.getElementById("id").value;
		var area = document.getElementById("menu_name").value;
		var reg = new RegExp("^[0-9]*$");
		var obj = document.getElementById("sort_order");
		var obj1 = document.getElementById("sort_order").value;
		ret = window.confirm('您确定要修改吗？');
		if (ret) {
			if (area == '') {
				document.getElementById("spanArea").innerHTML = '(菜单名称不能为空)';
			} else if (!reg.test(obj.value)) {
				document.getElementById('sort_order').focus();
				alert("排序号只能输入数字!");
			} else if (obj1 == '') {
				document.getElementById("spanorder").innerHTML = '(排序号不能为空)';
			} else {

				var menu = {};
				menu.menuId = $("#menuId").val();
				menu.menuName = $("#menu_name").val();
				menu.parentId = $("#client_level").val();
				menu.menuType = $("#menuType").val();
				menu.actionUrl = $("#actionUrl").val();
				menu.sortOrder = $("#sort_order").val();
				menu.isEnable = $("#isEnable").val();
				$.ajax({
					type : "POST",
					url : "${path}/commitUpdate",
					contentType : "application/json",
					data : JSON.stringify(menu),
					success : function() {
						window.parent.location.reload(true);
					}
				});

			}
		}
	}

	//清空span内容
	function checkAreaSpan() {
		var area = document.getElementById("area").value;
		if (area != "") {
			document.getElementById('spanArea').innerText = "";
		}
	}
</script>
<style type="text/css">
body {
	background: #fff;
}

font {
	margin-right: -6px;
	position: relative;
	left: -10px;
}

#clientForm>table tr td label {
	display: block;
	width: 500px;
}

input[type="text"], select {
	float: right;
	display: inline-block;
	width: 200px;
	border: 1px solid #a1aebf;
	margin-right: 40%;
}

.button_03 {
    border: none;
    padding: 6px 8px;
    height: auto;
    border-radius: 4px;
}
</style>
</head>

<body>

	<sf:form id="clientForm" name="clientForm" action="" method="post"
		modelAttribute="lists">
		<table width="95%" border="0" style="margin-top: 100px;" cellspacing="0" cellpadding="0"
			height="20%">
			<tr>
				<td width="30%">
					<!-- 	<div align="right">
							<font color="#FF0000">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						</div> -->
				</td>
				<td width="50%"><label> <input type="text"
						style="display: none;" class="input_01" name="menuId" id="menuId"
						value="${menu.menuId}" /> <font color="#FF0000">*</font>菜单名称:&nbsp;
						<input type="text" class="input_01" name="menuName" id="menu_name"
						value="${menu.menuName}" /><span id="spanArea" style="color: red"></span><br />
					<br /> 父标识:&nbsp; <select name="parentId" class="select1"
						id="client_level" style="">
							<c:forEach items="${list}" var="u">
								<option value="<c:out value="${u.menuId}"></c:out>">
									<c:out value="${u.menuName}"></c:out>
								</option>
							</c:forEach>

					</select><br />
					<br /> <font color="#FF0000">*</font>菜单类型:&nbsp; <%-- <input type="text" class="input_01" name="menu_type" id="menu_type" value="${lists.menu_type }"/><span>注：0：为目录；1：为功能点</span> --%>
						<select name="menuType" class="select1" id="menuType"
						size="20">
							<option <c:if test="${menu.menuType==0}"> selected </c:if>
								value=0>目录</option>
							<option <c:if test="${menu.menuType==1}"> selected </c:if>
								value=1>功能点</option>

					</select><br />
					<br /> 目标地址:&nbsp;<input type="text" class="input_01"
						name="actionUrl" id="actionUrl" value="${menu.actionUrl}" /><br />
					<br /> 是否可用:&nbsp; <select name="isEnable" class="select1"
						id="isEnable" size="20">
							<option <c:if test="${menu.isEnable==0}"> selected </c:if>
								value=0>可用</option>
							<option <c:if test="${menu.isEnable==1}"> selected </c:if>
								value=1>不可用</option>

					</select>
					<!-- <span>注：0；为可用，1；为不可用</span> -->
						<br />
					<br /> <font color="#FF0000">*</font>排序号:&nbsp;<input
						name="sortOrder" type="text" class="input_01" id="sort_order"
						value="${menu.sortOrder}" maxlength="10" onkeyup="checkAreaSpan()" /><span
						id="spanorder" style="color: red"></span> <br />
					<br />
				</label></td>
			</tr>
		</table>

		<br />
		<p align="center">
			<input type="button" class="button_03" id="btnAddRegion"
				onClick="updateMenu()" value=" 修 改 " /> &nbsp;&nbsp;&nbsp;&nbsp; <input
				type="button" class="button_03" id="btnQu" value=" 返 回 "
				onClick="history.go(-1)" />


		</p>
	</sf:form>
</body>
</html>
