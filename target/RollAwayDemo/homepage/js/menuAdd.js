var addBtn = document.getElementById('addBtn');
addBtn.onclick = function() {
		$.ajax({
				type : "POST",
				url : server_context+"/toaddMenu",
				contentType : "application/json",
				dataType : "json",
				success : function(list) {
					var contentText = '<table class="infoBox">'
							+ '<tr>'
							+ '<td>菜单名称:</td>'
							+ '<td><input type="text" name="text"><span>*</span></td>'
							+ '<td>父标识:</td><td><select name="select" >';
					for (var i = 0; i < list.length; i++) {
						contentText += '<option value=' + list[i].menuId + '>'
								+ list[i].menuName + '</option>';
					}
					contentText += '</select><span>*</span></td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td>菜单类型:</td>'
							+ '<td><select name="select" ><option>目录</option><option>功能点</option></select><span>*</span></td>'
							+ '<td>目标地址:</td>'
							+ '<td><input type="text" name="text"><span>*</span></td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td>排序号:</td>'
							+ '<td><input type="text" name="text"><span>*</span></td>'
							+ '<td>是否可用:</td>'
							+ '<td><select name="select" ><option>可用</option><option>不可用</option></select><span>*</span></td>'
							+ '</tr>' + '</table>';
					layer.alert({
						titleText : "添加",// 标题
						yesBtnText : "保存",// 确定按钮
						noBtnText : "取消",// 取消按钮
						closeBtnText : "",// 关闭按钮
						contentText : contentText,// 提示信息
						clearDefaultCss : false,// 不使用默认样式,默认false
						yesCall : function() {// 点击确认按钮回调
							var arr = document.getElementsByName("text");
							var arr1 = document.getElementsByName("select");
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
							menu.menuType = arr1[1].value == "目录" ? 0 : 1;
							menu.actionUrl = arr[1].value;
							menu.sortOrder = arr[2].value;
							menu.isEnable = arr1[2].value == "可用" ? 1 : 0;
							$.ajax({
								type : "POST",
								url : server_context+"/insertMenu",
								contentType : "application/json",
								data : JSON.stringify(menu),
								success : function() {
									alert("添加成功");
									window.parent.location.reload(true);
								}
							});
						}
					});
				}
			});
}
