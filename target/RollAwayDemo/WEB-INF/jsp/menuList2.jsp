<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有信息</title>
<script type="text/javascript" src="${path}/his/js/common.js"></script>
<link href="${path}/common/CSS/base.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${path}/common/CSS/style.css" />
<link rel="stylesheet" type="text/css"
	href="${path}/his/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${path}/common/styles/page/pagination.css" />
<script type="text/javascript" src="${path}/common/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${path}/common/scripts/page/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/common/layer/layer.js"></script>
<script type="text/javascript" src="${path}/common/scripts/laytpl.js"></script>
<style type="text/css">
body {
	background: #f4f8fb;
}

.mytb {
	cellpadding: 0px;
	cellspacing: 0px;
	border-collapse: collapse;
}

.mytb tr td, .mytb tr th {
	height: 32px;
	color: #333;
	line-height: 32px;
	padding: 3px 5px;
	border: 1px solid #bcb7b7;
	text-align: center;
	vertical-align: middle;
}

.mytb tr th {
	border: 1px solid #999;
	background: #f5f5f5;
}

.mytb tr td a {
	transition: all 0.3s;
	text-decoration: none;
	margin: 2px;
	display: inline-block;
	line-height: 20px;
	padding: 0 4px;
	border-radius: 4px;
	color: #fff;
}

.mytb tr:hover td {
	background: #bfe4e0;
}

.mytb tr {
	background: #fff;
}

.myDialog {
	display: none;
}

.myDialog span {
	display: inline-block;
	min-width: 90px;
	height: 40px;
	line-height: 40px;
	text-align: center;
}

#queryCondition select {
	outline: none;
	border: 1px solid #ccc;
	line-height: 30px;
	height: 30px;
	padding-left: 5px;
	min-width: 150px;
}

.myDialog input, .myDialog select {
	outline: none;
	border: 1px solid #ccc;
	line-height: 30px;
	height: 30px;
	padding-left: 5px;
	min-width: 300px;
}

.myDialog textarea {
	outline: none;
	border: 1px solid #ccc;
	width: 300px;
	height: 80px;
	padding: 3px;
}

#queryCondition span {
	display: inline-block;
	min-width: 50px;
	padding: 5px 3px;
	outline: none;
	margin: 5px;
}

#queryCondition input {
	padding: 5px 3px;
	outline: none;
	margin: 5px;
	display: inline-block;
}

.datalistCon {
	display: none;
	width: 200px;
	border: 1px solid #95B8E7;
	padding: 5px;
	background-color: #fff;
	position: absolute;
}

.datalistCon ul li {
	height: 30px;
	line-height: 30px;
	padding-left: 5px;
	border-bottom: 1px solid #ccc;
}

.datalistCon ul li:HOVER {
	background-color: #E8F1FF;
	cursor: pointer;
}

.visible {
	display: inline-block;
}

.page-tabs a.active {
	background: #3374dc;
}

.pagination a, .pagination span {
	padding: 6px 10px;
}

a.btn:hover {
	color: #333;
}

.container-h {
	background: #f9fdff;
	border-bottom: 2px #d2d6d9 solid;
	font-size: 16px;
	color: #333;
	padding: 20px 20px;
}

.btn {
	display: inline-block;
	background: #fff;
	color: #333;
	font-size: 14px;
	text-align: center;
	padding: 8px 12px;
	margin: 10px 0px 0px 10px;
	border: 1px solid #ebebeb;
	border-radius: 4px;
}

.btn i {
	padding-right: 10px;
	-o-transition: all 0.3s;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	-ms-transition: all 0.3s;
	transition: all 0.3s;
}
.layer-title{
 padding-top:10px;
}

.layer-yesBtn,.layer-noBtn{
	margin:10px 0;height: 30px;
	line-height: 30px;
	 border-radius: 4px;
	-o-transition: all 0.3s;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	-ms-transition: all 0.3s;
	transition: all 0.3s;
}
.layer-yesBtn:hover{background: #153c7b;color: #fff;}
.layer-noBtn:hover{background:#a51c1c;color: #fff;}
input[type="text"],select{
	display:inline-block;
	width:200px;
	padding: 6px;
    border-radius: 4px;
    outline: none;
    border: 1px solid #ccc;
    margin:6px 0;
}
.fa:before{
	margin-right: 10px;
}
</style>
</head>
<body>
	<!-- <p class="container-h">菜&nbsp;单&nbsp;管&nbsp;理&nbsp;</p> -->
	<a href='###' class='btn' id="addBtn" 
		style="float: left; margin-left: 45px; margin-bottom: 10px;"><i
		class="fa fa-plus-square"></i>添加</a>
		<a href="###" class="btn " id="remove"
		style="color: #ccc; float: left; margin-left: 10px; margin-bottom: 10px;"><i
		class="fa fa-remove"></i>删除</a>
		<a href="###" class="btn pencil" id="updateBtn"
		style="float: left; margin-right: 10px; margin-bottom: 10px; color: #ccc"><i
		class="fa fa-pencil"></i>修改 </a>
	<table class="tc mytb" style="width: 95%; margin: 14px auto 0 auto;">
		<thead>
			<tr>
				<th style='display: none'>选择</th>
				<th>编号</th>
				<th>菜单名称</th>
				<th>父标识</th>
				<th>菜单类型</th>
				<th>目标地址</th>
				<th>排序号</th>
				<th>创建时间</th>
				<th>是否可用</th>
			</tr>
		</thead>
		<tbody id="doctorInfos">
		</tbody>

	</table>


	<div id="modifyParamDictDialog_div" class="myDialog">
		<form id="modifyParamDict_form"></form>
	</div>

	<div id="Pagination" class="pagination">
		<!-- 这里显示分页 -->
	</div>
</body>
<script type="text/javascript">
	var recordCount = "${menuCount}";
	var pageNo = 0;
	$("#Pagination").pagination(recordCount, {
		num_edge_entries : 2, //边缘页数
		num_display_entries : 6, //主体页数
		items_per_page : 10, //每页显示10项
		callback : pageselectCallback,
		prev_text : "前一页",
		next_text : "后一页"
	});
	function pageselectCallback(pageNo) {
		$
				.ajax({
					type : "POST",
					url : "${path}/querylist/" + (pageNo + 1),
					//data:$("#queryCondition").serialize(),
					dataType : "json",
					success : function(data) {
						$("#doctorInfos").empty();
						if (data != null) {
							var str = "";
							for (var i = 0; i < data.length; i++) {
								str += "<tr><td style='display:none'><input menuId="+data[i].menuId+" type='radio' name='onec' /></td>";
								str += "<td>" + (i + 1) + "</td>";
								str += "<td>" + panduan(data[i].menuName)
										+ "</td>";
								str += "<td>" + panduan(data[i].parentId)
										+ "</td>";
								if (data[i].menuType == '1') {
									str += "<td>" + '功能点' + "</td>";
								} else if (data[i].menuType == '0') {
									str += "<td>" + '目录' + "</td>";
								} else {
									str += "<td>" + '暂无' + "</td>";
								}
								str += "<td>" + panduan(data[i].actionUrl)
										+ "</td>";
								str += "<td>" + panduan(data[i].sortOrder)
										+ "</td>";
								str += "<td>" + panduan(data[i].createtime)
										+ "</td>";
								if (data[i].isEnable == '1') {
									str += "<td>" + '禁用' + "</td>";
								} else if (data[i].isEnable == '0') {
									str += "<td>" + '可用' + "</td>";
								} else {
									str += "<td>" + '暂无' + "</td>";
								}
								/* 						str += "<td><a href='/updatePage/"+data[i].menuId+"' class='change'>修改</a>";
								 str += " <a href='javascript:void(0);'  onclick=\"deleteParaDict('"+data[i].menuId+"');\"  class='dele'>删除</a> ";
								 str += "</td>"; */
								str += "</tr>";
							}
						}
						$("#doctorInfos").append(str);
					},
					error : function() {
						console.log("sorry,there is a error");
					}

				});
	}
	function deleteParaDict(menuId) {
		if (confirm("您确定要删除吗?") == true) {
			$.ajax({
				type : "POST",
				url : "${path}/deleteMenu/" + menuId,
				success : function(msg) {
					if (msg == '1') {
						alert("删除成功");
						window.parent.location.reload();
						inputMenuId = null;
					} else {
						console.log("sorry ,there is a error!");
					}
				}
			});
		}
	}
	function panduan(src) {
		var result = src;
		if (src == null || src == '' || src == 'undefined' || src == 'null') {
			result = "";
		}
		return result;
	}

	function chakan(paramId) {
		$.ajax({
			type : "POST",
			url : "${path}/sys/sysParamDict/" + paramId + ".html",
			success : function(msg) {
				if (msg == 'erro' || msg == 'null') {
					layer.alert('网络错误，请刷新重试');
					return;
				}
				msg = eval('(' + msg + ')');
				var modifyDoctorTpl = $("#modifyParamDictTpl").html();
				var result = laytpl(modifyDoctorTpl).render(msg);//同步的方式进行渲染
				$("#modifyParamDict_form").html(result);
				layer.open({
					type : 1,
					title : '查看详情',
					area : [ '800px', '450px' ],
					btn : [ '确定' ],
					content : $('#modifyParamDictDialog_div')
				});
			}
		});
	}

	var remove = document.getElementById("remove");
	var pencil = document.querySelector(".pencil");
	var table = document.querySelector(".mytb");
	var inputMenuId = null;
	table.onclick = function(e) {
		var e = e || window.event;
		var target = e.target || e.srcElement;
		if (target.tagName === "TD") {
			var input = null;
			var tr = target.parentNode;
			var trs = this.getElementsByTagName("tr");
			var tds = tr.children;
			input = (tds[0].children)[0];
			if (input.checked === false) {
				for (var i = 0; i <= trs.length - 1; i++) {
					trs[i].style.background = "#fff";
				}
				input.checked = true;
				inputMenuId = input.getAttribute("menuId");
				tr.style.background = "#e2efff";
				remove.style.color = "#333";
				pencil.style.color = "#333";
			} else {
				input.checked = false;
				inputMenuId = null;
				for (var i = 0; i <= trs.length - 1; i++) {
					trs[i].style.background = "#fff";
				}
				remove.style.color = "#ccc";
				pencil.style.color = "#ccc";
			}
		}

	}

	remove.onclick = function(e) {
		if (inputMenuId == null || inputMenuId == undefined) {
			return false;
		}
		deleteParaDict(inputMenuId);
		
	}
	function l(s) {
		console.log(s)
	}
</script>
<script type="text/javascript" src="${path}/homepage/js/mobile_layer.js"></script>
<script type="text/javascript" src="${path}/homepage/js/menuAdd.js"></script>
<script type="text/javascript" src="${path}/homepage/js/menuUpdate.js"></script>


</html>
