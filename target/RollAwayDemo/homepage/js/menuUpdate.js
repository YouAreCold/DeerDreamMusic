function panduan(src) {
	var result = src;
	if (src == null || src == '' || src == 'undefined' || src == 'null') {
		result = "";
	}
	return result;
}

var updateBtn = document.getElementById('updateBtn');
updateBtn.onclick = function() {
	if(inputMenuId==null|| inputMenuId == undefined){
		return;
	}
	$.ajax({
		type : "POST",
		url : server_context+"/toaddMenu",
		contentType : "application/json",
		dataType : "json",
		success : function(list) {
			var myList=list;
			ajaxNext(myList);
		}
	})
	
function ajaxNext(myList){
	$.ajax({
				type : "POST",
				url : server_context+"/update/" + inputMenuId,
				dataType : "json",
				success : function(data) {
					if (data != null) {
						var contentText = '<table class="infoBox">' + '<tr>'
								+ '<td>菜单名称:</td>'
								+ '<td><input type="text" name="text" value='
								+ data.menuName + '><span>*</span></td>'
								+ '<td>父标识:</td><td><select name="select" >';
						for (var i = 0; i < myList.length; i++) {
							contentText += '<option value=' + myList[i].menuId
									+ '>' + myList[i].menuName + '</option>';
						}
						contentText += '</select><span>*</span></td>' + '</tr>'
								+ '<tr>' + '<td>菜单类型:</td>';
						if (data.menuType == 0) {
							contentText += '<td><select name="select" ><option checked="checked">目录</option><option>功能点</option></select><span>*</span></td>'
						} else if(data.menuType == 1){
							contentText += '<td><select name="select" ><option checked="checked">功能点</option><option >目录</option></select><span>*</span></td>'
						}
						contentText += '<td>目标地址:</td>'
								+ '<td><input type="text" name="text" value='
								+ data.actionUrl + '><span>*</span></td>'
								+ '</tr>' + '<tr>' + '<td>排序号:</td>'
								+ '<td><input type="text" name="text" value='
								+ data.sortOrder + '><span>*</span></td>'
								+ '<td>是否可用:</td>';
						if (data.isEnable == 1) {
							contentText += '<td><select name="select" ><option checked="checked">不可用</option><option>可用</option></select><span>*</span></td>';
						} else if (data.isEnable == 0){
							contentText += '<td><select name="select" ><option checked="checked">可用</option><option >不可用</option></select><span>*</span></td>';
						}
						contentText +='</tr>' + '</table>';
						var menuid = data.menuId;
						layer
								.alert({
									titleText : "修改",// 标题
									yesBtnText : "保存",// 确定按钮
									noBtnText : "取消",// 取消按钮
									closeBtnText : "",// 关闭按钮
									contentText : contentText,// 提示信息
									clearDefaultCss : false,// 不使用默认样式,默认false
									yesCall : function() {// 点击确认按钮回调
										var arr = document
												.getElementsByName("text");
										var arr1 = document
												.getElementsByName("select");
										var reg = new RegExp("^[0-9]*$");
										if(arr[0].value==null||arr[0].value==""){
											alert("菜单名不能为空！")
											return false;
										}
										if(!reg.test(arr[2].value)){
											alert("排序号只能为数字！")
											return false;
										}
										var menu = {};
										menu.menuName = arr[0].value;
										menu.parentId = arr1[0].value;
										menu.menuType = arr1[1].value == "目录" ? 0
												: 1;
										menu.menuId = menuid;
										menu.actionUrl = arr[1].value;
										menu.sortOrder = arr[2].value;
										menu.isEnable = arr1[2].value == "可用" ? 0
												: 1;
										$.ajax({
											type : "POST",
											url : server_context+"/commitUpdate",
											contentType : "application/json",
											data : JSON.stringify(menu),
											success : function() {
												alert("修改成功！");
												window.parent.location
														.reload(true);
											}
										});
										inputMenuId = null;
									}
								})
					}
				}
			})
}
	
}
