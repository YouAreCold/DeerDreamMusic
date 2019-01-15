<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Document</title>

<script type="text/javascript" src="${path}/his/js/common.js"></script>
<script type="text/javascript" src="${path }/common/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${path }/common/scripts/page/jquery.pagination.js"></script>
<link rel="stylesheet" type="text/css"
	href="${path }/common/styles/page/pagination.css" />
<link rel="stylesheet" type="text/css"
	href="${path }/his/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${path }/his/css/index.css">
<link rel="stylesheet" type="text/css" href="${path }/his/css/InterfaceList.css">
<script type="text/javascript" src="${path }/his/js/mobile_layer.js"></script>

<script type="text/javascript">
	$(function() {
		var recordCount = "${hospitalCount}";
		var pageNo = 0;
		$("#Pagination").pagination(recordCount, {
			num_edge_entries : 2, //边缘页数
			num_display_entries : 6, //主体页数
			items_per_page : 5, //每页显示10项
			callback : pageselectCallback,
			prev_text : "前一页",
			next_text : "后一页"
		});
		function pageselectCallback(pageNo) {
			$
					.ajax({
						type : "POST",
						url : "${path }/hospital/getHospitalList/" + (pageNo + 1),
						success : function(data) {
							$("#hospitalInfos").empty();
							if (data != null) {
								var str = "";
								for (var i = 0; i < data.length; i++) {
									str += "<tr>";
									str += "<td style='display: none;'><input name='hospitalSelect' type='radio' value='"+data[i].hospitalId+"'/></td>"
									str += "<td>" + panduan(data[i].hospitalId)
											+ "</td>";
									str += "<td>"
											+ panduan(data[i].hospitalName)
											+ "</td>";
									if (data[i].apiWay == '1') {
										str += "<td>" + 'POST' + "</td>";
									} else if (data[i].apiWay == '2') {
										str += "<td>" + 'GET' + "</td>";
									} else if (data[i].apiWay == '3') {
										str += "<td>" + 'webservice' + "</td>";
									} else if (data[i].apiWay == '4') {
										str += "<td>" + 'HL7' + "</td>";
									}
									str += "<td>"
											+ panduan(data[i].invokingUrl)
											+ "</td>";
									str += "<td>"
											+ panduan(data[i].driveTransClass)
											+ "</td>";
									if (data[i].status == '1') {
										str += "<td>" + '可用' + "</td>";
									} else if (data[i].status == '0') {
										str += "<td>" + '禁用' + "</td>";
									}
									str += "</tr>";
								}
							}
							$("#hospitalInfos").append(str);
						},
						error : function() {
							console.log("sorry,there is a error");
						}

					});
		}

	});

	function panduan(src) {
		var result = src;
		if (src == null || src == '' || src == 'undefined' || src == 'null') {
			result = "";
		}
		return result;
	}
	function queryHospital() {
		var hospitalInfo = {};
		hospitalInfo.hospitalId = $("#inputCode").val();
		hospitalInfo.hospitalName = $("#inputName").val();
		$
				.ajax({
					type : "POST",
					url : "${path}/hospital/queryHospital",
					data : JSON.stringify(hospitalInfo),
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						$("#hospitalInfos").empty();
						if (data != null) {
							var str = "";
							for (var i = 0; i < data.length; i++) {
								str += "<tr>";
								str += "<td style='display: none;'><input name='hospitalSelect'  type='radio' value='"+data[i].hospitalId+"'/></td>"
								str += "<td>" + panduan(data[i].hospitalId)
										+ "</td>";
								str += "<td>" + panduan(data[i].hospitalName)
										+ "</td>";
								if (data[i].apiWay == '1') {
									str += "<td>" + 'POST' + "</td>";
								} else if (data[i].apiWay == '2') {
									str += "<td>" + 'GET' + "</td>";
								} else if (data[i].apiWay == '3') {
									str += "<td>" + 'webservice' + "</td>";
								} else if (data[i].apiWay == '4') {
									str += "<td>" + 'HL7' + "</td>";
								}
								str += "<td>" + panduan(data[i].invokingUrl)
										+ "</td>";
								str += "<td>"
										+ panduan(data[i].driveTransClass)
										+ "</td>";
								if (data[i].status == '1') {
									str += "<td>" + '可用' + "</td>";
								} else if (data[i].status == '0') {
									str += "<td>" + '禁用' + "</td>";
								}
								str += "</tr>";
							}
						}
						$("#hospitalInfos").append(str);
					}
				});

	}
</script>
</head>
<body>
	<div class="container">
		<!-- <p class="container-h">医&nbsp;院&nbsp;管&nbsp;理&nbsp;</p> -->
		<div class="terms">
			<span>音乐平台编码:</span><input type="text" name="" id="inputCode">
			<span>音乐平台名称:</span><input type="text" name="" id="inputName">
			<a href="###" class="btn " onclick="queryHospital()"><i
				class="fa fa-search"></i>查询</a><a class="btn " href="###" id="addBtn"><i
				class="fa fa-plus-square"></i>添加</a> <a href="###" class="btn "
				id="remove" style="color: #ccc"><i class="fa fa-remove"></i>删除</a> <a
				href="###" class="btn pencil" id="updateBtn" style="color: #ccc"><i
				class="fa fa-pencil"></i>修改</a> <a href="###" class="btn "
				id="InterfaceList" style="color: #ccc"><i class="fa fa-list-alt" ></i>接口列表</a>
		</div>
	</div>
	<div class="info-table">
		<table class="mytb">
			<tbody>
				<tr>
					<th>音乐平台编码</th>
					<th>音乐平台名称</th>
					<th>音乐平台接口方式</th>
					<th>音乐平台地址</th>
					<th>音乐平台扩展类</th>
					<th>音乐平台接口状态</th>
				</tr>
			</tbody>
			<tbody id="hospitalInfos">


			</tbody>
		</table>
	</div>

	</div>
	<div id="Pagination" class="pagination">
		<!-- <span class="current prev">前一页</span> <span class="current">1</span>
			<span class="current next">后一页</span> -->
	</div>
</body>
<script type="text/javascript" src="${path}/his/js/add.js"></script>
<script type="text/javascript" src="${path}/his/js/update.js"></script>
<script type="text/javascript" src="${path}/his/js/InterfaceList.js"></script>
<script type="text/javascript">
table.onclick = function(e) {
	var e = e || window.event;
	var target = e.target || e.srcElement;

	if(target.tagName==="TD"){
			var input=null;
			var tr=target.parentNode;
			var trs=this.getElementsByTagName("tr");
			var tds=tr.children;
			input=(tds[0].children)[0];			
			if(input.checked===false){
				for(var i=0;i<=trs.length-1;i++){
					trs[i].style.background="#fff";	
				}
				input.checked=true;
				tr.style.background="#e2efff";
				remove.style.color="#333";
				pencil.style.color="#333";
				InterfaceList.style.color="#333";
			}else{
				input.checked=false;
			//	hospitalId=null;
				for(var i=0;i<=trs.length-1;i++){
					trs[i].style.background="#fff";	
				}
				remove.style.color="#ccc";
				pencil.style.color="#ccc";
				InterfaceList.style.color="#ccc";
			} 
	}

	
}
</script>

</html>


