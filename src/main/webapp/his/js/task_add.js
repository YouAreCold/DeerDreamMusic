var addBtn = document.getElementById('addBtn');
var contentText = '<table class="infoBox">' + '<tr>' + '<td>任务名称:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>' + '</tr>'
		+ '<tr>' + '<td>任务执行的类（含包名）:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>' + '</tr>'
		+ '<tr>' + '<td>方法名:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>' + '</tr>'
		+ '<tr>' + '<td>cron表达式:</td>'
		+ '<td><input type="text" name="text"><span>*</span></td>' + '</tr>'
		+ '<tr>' + '<td>运行时参数:</td>'
		+ '<td><input type="text" name="text"></td>' + '</tr>' + '<tr>'
		+ '<td>任务描述:</td>' + '<td><input type="text" name="text"></td>'
		+ '</tr>' + '</table>'
addBtn.onclick = function() {
	layer.alert({
		titleText : "添加",// 标题
		yesBtnText : "保存",// 确定按钮
		noBtnText : "取消",// 取消按钮
		closeBtnText : "",// 关闭按钮
		contentText : contentText,// 提示信息
		clearDefaultCss : false,// 不使用默认样式,默认false
		yesCall : function() {// 点击确认按钮回调
			var taskScheduleJob = {};
			var arr = document.getElementsByName("text");
			for (var i = 0; i <= 3; i++) {
				if (arr[i].value == "" || arr[i].value == null) {
					alert("必填项不能为空！");
					return false;
				}
			}
			taskScheduleJob.jobName = arr[0].value;
			taskScheduleJob.beanClass = arr[1].value;
			taskScheduleJob.methodName = arr[2].value;
			taskScheduleJob.cronExpression = arr[3].value;
			taskScheduleJob.params = arr[4].value;
			taskScheduleJob.description = arr[5].value;
			taskScheduleJob.jobStatus=1;
			$.ajax({
				type : "POST",
				url : server_context+"/task/insertTask",
				contentType : "application/json",
				data : JSON.stringify(taskScheduleJob),
				dataType : "json",
				success : function(flag) {
					if (flag == 0) {
						alert("无效的cron表达式！");
					} else {
						alert("添加成功！");
						window.location.href = server_context+"/task/getTaskList";
					}

				}
			});
		}
	});
}