<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>渠道管理</title>
<script type="text/javascript" src="${path}/his/js/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="${path }/common/styles/page/pagination.css" />
<link rel="stylesheet" type="text/css"
	href="${path }/his/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${path }/his/css/index.css">
<script type="text/javascript" src="${path }/common/scripts/jquery.min.js"></script>
<script type="text/javascript"
	src="${path }/common/scripts/page/jquery.pagination.js"></script>
<script type="text/javascript" src="${path }/his/js/mobile_layer.js"></script>
</head>
<style type="text/css">
.container-h {
	letter-spacing: 5px;
}

.container-h i {
	font-style: normal;
}

.terms p {
	display: block;
}

.toollist a {
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	height: 36px;
	line-height: 36px;
	padding-top: 0;
	padding-bottom: 0;
	cursor: pointer
}

.infoBox td {
	padding-right: 10px;
}
</style>
<script type="text/javascript">
	function panduan(src) {
		var result = src;
		if (src == null || src == '' || src == 'undefined' || src == 'null') {
			result = "";
		}
		return result;
	}
	Date.prototype.Format = function(fmt) { //author: meizz  
		var o = {
			"M+" : this.getMonth() + 1, //月份  
			"d+" : this.getDate(), //日  
			"h+" : this.getHours(), //小时  
			"m+" : this.getMinutes(), //分  
			"s+" : this.getSeconds(), //秒  
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度  
			"S" : this.getMilliseconds()
		//毫秒  
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};
	$(function() {
		var recordCount = "${taskCount}";
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
						url : "${path}/task/getList/" + (pageNo + 1),
						success : function(data) {
							$("#taskInfos").empty();
							if (data != null) {
								var str = "";
								for (var i = 0; i < data.length; i++) {
									str += "<tr>";
									str += "<td style='display: none;'><input type='radio' name='checkTask' value="+data[i].jobId+"></td>";
									str += "<td>" + data[i].jobName + "</td>";
									str += "<td>" + data[i].beanClass + "</td>";
									str += "<td>" + data[i].methodName
											+ "</td>";
									str += "<td>" + data[i].params + "</td>";
									str += "<td>" + data[i].cronExpression
											+ "</td>";
									str += "<td>" + panduan(data[i].createTime)
											+ "</td>";
									if (data[i].jobStatus == 1) {
										str += "<td>运行中</td>";
									} else {
										str += "<td>未运行</td>";
									}
									str += "<td>"
											+ (new Date(data[i].lastTime))
													.Format('yyyy-MM-dd hh:mm:ss')
											+ "</td>";
									str += "<td>"
											+ (new Date(data[i].nextTime))
													.Format('yyyy-MM-dd hh:mm:ss')
											+ "</td>";
									str += "<td>" + data[i].description
											+ "</td>";
									str += "</tr>";
								}
							}
							$("#taskInfos").append(str);
						},
						error : function() {
							console.log("sorry,there is a error");
						}
					});
		}
	});
</script>
<body>
	<div class="container">
		<!-- <p class="container-h">
			<span>对接渠道管理</span> <i>>></i> <span>渠道管理</span>
		</p> -->
		<div class="terms">
			<!-- <p  class="container-h">定时任务列表</p> -->
			<p class="toollist">
				<a class="btn" id="addBtn"><i class="fa fa-plus-square"></i>新增</a> <a
					class="btn" id="updateBtn"><i class="fa fa-edit"></i>修改</a> <a
					class="btn" onclick="deleteTask()"><i class="fa fa-remove"></i>删除</a>
				<a class="btn" onclick="pauseTask()"><i
					class="fa fa-minus-circle" style="color: red"></i>暂停任务</a> <a
					class="btn" onclick="continueTask()"><i class="fa fa-repeat"
					style="color: #33e072"></i>继续任务</a> <a class="btn" onclick="runNow()"><i
					class="fa fa-play" style="color: #4b98d0"></i>立刻执行一次</a>
			</p>

		</div>
		<div class="info-table">
			<table class="mytb">
				<tbody>
					<tr>
						<th>任务名称</th>
						<th>任务执行的类</th>
						<th>执行方法</th>
						<th>运行参数</th>
						<th>cron表达式</th>
						<th>创建时间</th>
						<th>定时任务状态</th>
						<th>上次执行</th>
						<th>下次执行</th>
						<th>任务描述</th>
					</tr>
				</tbody>
				<tbody id="taskInfos">
				</tbody>
			</table>
		</div>
	</div>
	<div id="Pagination" class="pagination"></div>

	<script src="${path}/his/js/task_add.js"></script>

	<script type="text/javascript">
		function getId() {
			var select = document.getElementsByName("checkTask");
			var jobId = "";
			for (var i = 0; i < select.length; i++) {
				if (select[i].checked) {
					jobId = select[i].value;
				}
			}
			return jobId;
		}
		function deleteTask() {
			var taskScheduleJob = {};
			var jobId = getId();
			if (jobId == null || jobId == "") {
				return;
			}
			if (!confirm("确定删除嘛，该操作无法复原！")) {
				return;
			}

			taskScheduleJob.jobId = jobId;
			$.ajax({
				type : "POST",
				url : "${path}/task/deleteTask",
				contentType : "application/json",
				data : JSON.stringify(taskScheduleJob),
				success : function() {
					alert("删除成功！");
					window.location.reload();
				}
			});
		}
		function pauseTask() {
			var taskScheduleJob = {};
			var jobId = getId();
			if (jobId == null || jobId == "") {
				return;
			}
			taskScheduleJob.jobId = jobId;
			$.ajax({
				type : "POST",
				url : "${path}/task/pauseTask",
				contentType : "application/json",
				data : JSON.stringify(taskScheduleJob),
				success : function() {
					alert("暂停成功！");
					window.location.reload();
				}
			});
		}
		function continueTask() {
			var taskScheduleJob = {};
			var jobId = getId();
			if (jobId == null || jobId == "") {
				return;
			}
			taskScheduleJob.jobId = jobId;
			$.ajax({
				type : "POST",
				url : "${path}/task/continueTask",
				contentType : "application/json",
				data : JSON.stringify(taskScheduleJob),
				success : function() {
					alert("任务重启成功！");
					window.location.reload();
				}
			});
		}
		function runNow() {
			var taskScheduleJob = {};
			var jobId = getId();
			if (jobId == null || jobId == "") {
				return;
			}
			taskScheduleJob.jobId = jobId;
			$.ajax({
				type : "POST",
				url : "${path}/task/runNow",
				contentType : "application/json",
				data : JSON.stringify(taskScheduleJob),
				success : function() {
					alert("执行成功！");
					window.location.reload();
				}
			});
		}

		var table = document.querySelector(".mytb");
		table.onclick = function(e) {
			var e = e || window.event;
			var target = e.target || e.srcElement;
			if (target.tagName === "TD") {
				var input = null;
				var tr = target.parentNode;
				var tds = tr.children;
				var trs = this.getElementsByTagName("tr");
				input = (tds[0].children)[0];
				if (input.checked === false) {
					for (var i = 0; i <= trs.length - 1; i++) {
						trs[i].style.background = "#fff";
					}
					input.checked = true;
					tr.style.background = "#e2efff";
				} else {
					input.checked = false;
					for (var i = 0; i <= trs.length - 1; i++) {
						trs[i].style.background = "#fff";
					}
				}
			}

		}
	</script>
	<script src="${path }/his/js/task_update.js"></script>
</body>
</html>


