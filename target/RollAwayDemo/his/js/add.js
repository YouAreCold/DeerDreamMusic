var addBtn = document.getElementById('addBtn');
var addcontentText = '<table class="infoBox">'
		+ '<tr>'
		+ '<td>音乐平台编码:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>'
		+ '<td>音乐平台名称:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>接口url:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>'
		// + '<td>端点地址:</td>'
		// + '<td><input type="text" name="text"><span>*</span></td>'
		// + '</tr>'
		// + '<tr>'
		// + '<td>命名空间:</td>'
		// + '<td><input type="text" name="text"><span>*</span></td>'
		+ '<td>扩展类:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>'
		+ '</tr>'
		+ '<tr>'
		+ '<td>状态:</td>'
		+ '<td><select name="select"><option>启用</option><option>禁用</option></select><span>*</span></td>'
		+ '<td>接口方式:</td>'
		+ '<td><select name="select"><option value = "1">POST接口</option><option value = "2">GET接口</option><option value = "3">Webservices</option><option value = "4">HL7</option></select><span>*</span></td>'
		+ '</tr>' + '<tr>' + '<td>用户名:</td>'
		+ '<td ><input type="text" name="text">' + '<td>密码:</td>'
		+ '<td ><input type="text" name="text">' + '</tr>' + '<tr>'
		+ '<td>音乐平台ID:</td>' + '<td ><input type="text" name="text"></td>'
		+ '<td>联系人:</td>' + '<td ><input type="text" name="text"></td>'
		+ '</tr>' + '<tr>' + '<td>联系电话:</td>'
		+ '<td ><input type="text" name="text"></td>' + '<td>联系邮箱:</td>'
		+ '<td ><input type="text" name="text"></td>' + '</tr>' + '</table>'
addBtn.onclick = function() {
	layer.alert({
		titleText : "添加",// 标题
		yesBtnText : "保存",// 确定按钮
		noBtnText : "取消",// 取消按钮
		closeBtnText : "",// 关闭按钮
		contentText : addcontentText,// 提示信息
		clearDefaultCss : false,// 不使用默认样式,默认false
		yesCall : function() {// 点击确认按钮回调
			var sysHospitalInfo = {};
			var arr = document.getElementsByName("text");
			var arr1 = document.getElementsByName("select");
			for (var i = 0; i <= 3; i++) {
				if (arr[i].value == "" || arr[i].value == null) {
					alert("必填项不能为空！");
					return false;
				}
			}
			sysHospitalInfo.hospitalId = arr[0].value;
			sysHospitalInfo.hospitalName = arr[1].value;
			sysHospitalInfo.invokingUrl = arr[2].value;
			// sysHospitalInfo.portUrl = arr[3].value;
			// sysHospitalInfo.namespace = arr[4].value;
			sysHospitalInfo.driveTransClass = arr[3].value;
			sysHospitalInfo.status = arr1[0].value == "启用" ? 1 : 0;
			sysHospitalInfo.apiWay = arr1[1].value;
			sysHospitalInfo.userName = arr[4].value;
			sysHospitalInfo.password = arr[5].value;
			sysHospitalInfo.hisId = arr[6].value;
			sysHospitalInfo.contacts = arr[7].value;
			sysHospitalInfo.telephone = arr[8].value;
			sysHospitalInfo.email = arr[9].value;
			$.ajax({
				type : "POST",
				url : server_context+"/hospital/addHospital",
				contentType : "application/json",
				data : JSON.stringify(sysHospitalInfo),
				dataType : "json",
				success : function(flag) {
					if (flag == 0) {
						alert("音乐平台编码已存在！");
					}
					if (flag == 1) {
						alert("添加成功！");
					}
					window.location.reload();
				}
			});
		}
	});
}

var remove = document.getElementById("remove");
var pencil = document.querySelector(".pencil");
var table = document.querySelector(".mytb");

remove.onclick = function() {
	var select = document.getElementsByName("hospitalSelect");
	var sysHospitalInfo = {};
	var hospitalId = "";
	for (var i = 0; i < select.length; i++) {
		if (select[i].checked) {
			hospitalId = select[i].value;
		}
	}

	if (!confirm("确定删除嘛，该操作无法复原！")) {
		return;
	}
	if (hospitalId == null || hospitalId == "") {
		return;
	}
	sysHospitalInfo.hospitalId = hospitalId;
	$.ajax({
		type : "POST",
		url : server_context+"/hospital/deleteHospital",
		contentType : "application/json",
		data : JSON.stringify(sysHospitalInfo),
		success : function() {
			alert("删除成功！");
			window.location.reload();
		}
	});
}