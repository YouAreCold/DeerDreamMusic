var updateBtn = document.getElementById('updateBtn');

updateBtn.onclick = function() {
	var AInput = document.getElementsByName("hospitalSelect");
	for (var i = 0; i < AInput.length; i++) {
		if (AInput[i].checked) {
			var hospitalId = AInput[i].value;
		}
	}
	$.ajax({
				type : "POST",
				url : server_context+"/hospital/getHospitalDetail/" + hospitalId,
				dataType : "json",
				success : function(data) {
					if (data != null) {
						var status = data.status == 1 ? "启用" : "禁用";
						$("apiWay").val = data.apiWay;
						var contentText = '<table class="infoBox">'
								+ '<tr>'
								+ '<td>音乐平台编码:</td>'
								+ '<td><input type="text" id="hospitalId" name="text" value="'
								+ data.hospitalId
								+ '"disabled="disabled"><span>*</span></td>'
								+ '<td>音乐平台名称:</td>'
								+ '<td><input type="text" id="hospitalName" value="'
								+ data.hospitalName
								+ '" name="text"><span>*</span></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>接口url:</td>'
								+ '<td><input type="text"id="invokingUrl" value="'
								+ data.invokingUrl
								+ '" name="text"><span>*</span></td>'
								// + '<td>端点地址:</td>'
								// + '<td><input type="text" id="portUrl"
								// value="'
								// + data.portUrl
								// + '" name="text"><span>*</span></td>'
								// + '</tr>'
								// + '<tr>'
								// + '<td>命名空间:</td>'
								// + '<td><input type="text" id="namespace"
								// value="'
								// + data.namespace
								// + '" name="text"><span>*</span></td>'
								+ '<td>扩展类:</td>'
								+ '<td><input type="text" id="driveTransClass" value="'
								+ data.driveTransClass
								+ '" name="text"><span>*</span></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>状态:</td>'
								+ '<td><select name="select" id="status" value="'
								+ status
								+ '"><option>启用</option><option>禁用</option></select><span>*</span></td>'
								+ '<td>接口方式:</td>';
						if (data.apiWay == 1) {
							contentText += '<td><select name="select" id="apiWay"><option selected="selected" value="1">POST</option><option value="2">GET</option><option value="3">Webservices</option><option value="4">HL7</option></select><span>*</span></td>';
						} else if (data.apiWay == 2) {
							contentText += '<td><select name="select" id="apiWay"><option value="1">POST</option><option selected="selected" value="2">GET</option><option value="3">Webservices</option></select><span>*</span></td>';
						} else {
							contentText += '<td><select name="select" id="apiWay"><option value = "1">POST</option><option value = "2">GET</option><option selected="selected" value = "3">Webservices</option></select><span>*</span></td>';
						}
						contentText += '</tr><tr>'
								+ '<td>用户名:</td>'
								+ '<td ><input type="text" name="text" id="userName" value="'
								+ data.userName
								+ '">'
								+ '<td>密码:</td>'
								+ '<td ><input type="text" name="text" id="password" value="'
								+ data.password
								+ '">'
								+ '</tr>'
								+ '<tr>'
								+ '<td>HIS音乐平台ID:</td>'
								+ '<td ><input type="text" name="text" id="hisId" value="'
								+ data.hisId
								+ '"></td>'
								+ '<td>联系人:</td>'
								+ '<td ><input type="text" name="text" id="contacts" value="'
								+ data.contacts
								+ '"></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>联系电话:</td>'
								+ '<td ><input type="text" name="text" id="telephone" value="'
								+ data.telephone
								+ '"></td>'
								+ '<td>联系邮箱:</td>'
								+ '<td ><input type="text" name="text" id="email" value="'
								+ data.email + '"></td>' + '</tr>' + '</table>';
						layer
								.alert({
									titleText : "修改",// 标题
									yesBtnText : "保存",// 确定按钮
									noBtnText : "取消",// 取消按钮
									closeBtnText : "",// 关闭按钮
									contentText : contentText,// 提示信息
									clearDefaultCss : false,// 不使用默认样式,默认false
									yesCall : function() {// 点击确认按钮回调
										var sysHospitalInfo = {};
										var arr = document
												.getElementsByName("text");
										var arr1 = document
												.getElementsByName("select");
										for (var i = 0; i <= 3; i++) {
											if (arr[i].value == ""
													|| arr[i].value == null) {
												alert("必填项不能为空！");
												return;
											}
										}
										sysHospitalInfo.hospitalId = arr[0].value;
										sysHospitalInfo.hospitalName = arr[1].value;
										sysHospitalInfo.invokingUrl = arr[2].value;
										//sysHospitalInfo.portUrl = arr[3].value;
										//sysHospitalInfo.namespace = arr[4].value;
										sysHospitalInfo.driveTransClass = arr[3].value;
										sysHospitalInfo.status = arr1[0].value == "启用" ? 1
												: 0;
										sysHospitalInfo.apiWay = arr1[1].value;
										sysHospitalInfo.userName = arr[4].value;
										sysHospitalInfo.password = arr[5].value;
										sysHospitalInfo.hisId = arr[6].value;
										sysHospitalInfo.contacts = arr[7].value;
										sysHospitalInfo.telephone = arr[8].value;
										sysHospitalInfo.email = arr[9].value;
										$
												.ajax({
													type : "POST",
													url : server_context+"/hospital/updateHospital",
													contentType : "application/json",
													data : JSON
															.stringify(sysHospitalInfo),
													success : function() {
														alert("修改成功！");
														window.location
																.reload();
													}
												});

									}
								})
					}
				}
			})
}
