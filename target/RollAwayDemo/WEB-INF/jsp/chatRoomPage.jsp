<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<title>交谈室</title>
<link href="CSS/style.css" rel="stylesheet"/>
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
	color: #000;
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

<%-- <script src="<%=request.getcontextPath()%>/common/js/jquery-1.8.3.js"/> --%>
<script type='text/javascript' src='${path}/common/js/jquery-1.8.3.js'></script>
<script>
	var sysBBS = "<span style='font-size:14px; line-height:30px;'>欢迎光临院内云交谈室，请遵守交谈室规则，不要使用不文明用语。</span><br><span style='line-height:22px;'>";
	window.setInterval("showContent();",15000);
	window.setInterval("showOnlineList();",15000);
	//加载窗口
	$(function(){
		showContent();
		showOnlineList();
	});
	
	// 显示聊天的内容
	function showContent(){
		$.post("${path}/getMessage?"+new Date().getTime(),{'method':'getMessage'},function(data){
			$("#content").html(sysBBS+data);
		});
	}
	
	// 显示在线人员列表
	function showOnlineList(){
		$.post("${path}/onlineList",function(data){
			$("#onlineList").html(data);
		});
	}
	
	//添加交谈对象
	function set(selectPerson){	//自动添加聊天对象
		if(selectPerson != "${user.userName}"){
			form1.to.value=selectPerson;
		}else{
			alert("请重新选择聊天对象！");
		}
	}
	//发送聊天信息
	function send(){
		if(form1.to.value==""){
			alert("请选择聊天对象！");
			return false;
		}
		if(form1.content.value==""){
			alert("发送信息不可以为空！");
			form1.content.focus();
			return false;
		}
		// $("#form1").serialize():让表单中所有的元素都提交.
		// jquery提交数据.{id:1,name:aa,age:25}
		$.post("${path}/sendMessage?"+new Date().getTime(),$("#form1").serialize(),function(data){
			$("#content").html(sysBBS+data+"</span>");
			//清空发送信息 输入框
			$("#conId").val("");
		});
	}
	//自动适应  聊天窗口
	function checkScrollScreen(){
		if(!$("#scrollScreen").attr("checked")){
	    	$("#content").css("overflow","scroll");
	    }else{
	    	$("#content").css("overflow","hidden");
	        //当聊天信息超过一屏时，设置最先发送的聊天信息不显示
	        //alert($("#content").height());
	        $("#content").scrollTop($("#content").height()*2);
	    }
	    setTimeout('checkScrollScreen()',500);
	} 
</script>
</head>
<body>
	
	<!-- 在线人员列表 -->
	<table class="tc mytb" id="onlineList" style="width:20%;height:590px; float:left; margin-left: 100px;"></table>
	
	<!-- 交谈内容 -->
	<table class="tc mytb" style="width: 60%;  margin: 14 auto 0 1;">
		<thead>
			<tr>
				<th>交谈内容</th>
			</tr>
		</thead>
		<tbody id="doctorInfos" height="416px">
		  	<tr>
			    <td align="center">
			    	<div id="content" style="height:416px;background-color:#FFF;"></div>
				</td>
		  	</tr>
		</tbody>
	</table>
	
	
	<table class="tc mytb" style="width:60%;font-size:12px;float:left;">

		<form action="" id="form1" name="form1" method="post">
			<input type="hidden" name="method" value="sendMessage"/>
			<tr>
				<td height="37" align="center">
				<input name="from" type="hidden" value="${user.userName}">[${user.userName} ]对 
				<input name="to" type="text" value="" size="35" readonly="readonly"> 表情 
				<select name="face" class="wenbenkuang">
						<option value="无表情的">无表情的</option>
						<option value="微笑着" selected>微笑着</option>
						<option value="笑呵呵地">笑呵呵地</option>
						<option value="热情的">热情的</option>
						<option value="温柔的">温柔的</option>
						<option value="红着脸">红着脸</option>
						<option value="幸福的">幸福的</option>
						<option value="嘟着嘴">嘟着嘴</option>
						<option value="热泪盈眶的">热泪盈眶的</option>
						<option value="依依不舍的">依依不舍的</option>
						<option value="得意的">得意的</option>
						<option value="神秘兮兮的">神秘兮兮的</option>
						<option value="恶狠狠的">恶狠狠的</option>
						<option value="大声的">大声的</option>
						<option value="生气的">生气的</option>
						<option value="幸灾乐祸的">幸灾乐祸的</option>
						<option value="同情的">同情的</option>
						<option value="遗憾的">遗憾的</option>
						<option value="正义凛然的">正义凛然的</option>
						<option value="严肃的">严肃的</option>
						<option value="慢条斯理的">慢条斯理的</option>
						<option value="无精打采的">无精打采的</option>
				</select> 说：</td>
				<td width="189" align="left">
					字体颜色：
					 <select name="color" size="1" class="wenbenkuang" id="select">
							<option selected>默认颜色</option>
							<option style="color:#FF0000" value="FF0000">红色热情</option>
							<option style="color:#0000FF" value="0000ff">蓝色开朗</option>
							<option style="color:#ff00ff" value="ff00ff">桃色浪漫</option>
							<option style="color:#009900" value="009900">绿色青春</option>
							<option style="color:#009999" value="009999">青色清爽</option>
							<option style="color:#990099" value="990099">紫色拘谨</option>
							<option style="color:#990000" value="990000">暗夜兴奋</option>
							<option style="color:#000099" value="000099">深蓝忧郁</option>
							<option style="color:#999900" value="999900">卡其制服</option>
							<option style="color:#ff9900" value="ff9900">镏金岁月</option>
							<option style="color:#0099ff" value="0099ff">湖波荡漾</option>
							<option style="color:#9900ff" value="9900ff">发亮蓝紫</option>
							<option style="color:#ff0099" value="ff0099">爱的暗示</option>
							<option style="color:#006600" value="006600">墨绿深沉</option>
							<option style="color:#999999" value="999999">烟雨蒙蒙</option>
					</select>
				</td>
				<td width="19" align="left"><input name="scrollScreen"
					type="checkbox" class="noborder" id="scrollScreen"
					onClick="checkScrollScreen()" value="1" checked>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="3">
				<input id="conId" name="content" type="text" size="200" style="width:750px;"
					onKeyDown="if(event.keyCode==13 && event.ctrlKey){send();}">
					<input name="Submit2" type="button" class="btn_grey" value="发送"
					onClick="send()">
				</td>
			</tr>
		</form>
	</table>
	
	
	
</body>
</html>
