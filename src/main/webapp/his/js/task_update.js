function panduan(src) {
	var result = src;
	if (src == null || src == '' || src == 'undefined' || src == 'null') {
		result = "";
	}
	return result;
}
var updateBtn = document.getElementById('updateBtn');
updateBtn.onclick = function() {
	var AInput = document.getElementsByName("checkTask");
	var jobId = "";
	for (var i = 0; i < AInput.length; i++) {
		if (AInput[i].checked) {
			jobId = AInput[i].value;
		}
	}
	$
			.ajax({
				type : "POST",
				url : server_context+"/task/getTaskDetail/" + jobId,
				dataType : "json",
				success : function(data) {
					if (data != null) {
						var cron = data.cronExpression;
						var contentText = '<table class="infoBox">' + '<tr>'
								+ '<td>任务名称:</td>'
								+ '<td><input type="text" name="text" value='
								+ data.jobName
								+ '><span>*</span></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>任务执行的类（含包名）:</td>'
								+ '<td><input type="text" name="text" value='
								+ data.beanClass
								+ '><span>*</span></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>方法名:</td>'
								+ '<td><input type="text" name="text" value='
								+ data.methodName
								+ '><span>*</span></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>cron表达式:</td>'
								+ '<td><input type="text" name="text" value="'
								+ cron
								+ '"><span>*</span></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>运行时参数:</td>'
								+ '<td><input type="text" name="text" value='
								+ panduan(data.params)
								+ '></td>'
								+ '</tr>'
								+ '<tr>'
								+ '<td>任务描述:</td>'
								+ '<td><input type="text" name="text" value='
								+ panduan(data.description)
								+ '></td>'
								+ '<td hidden="hidden"><input type="text" name="text" value='
								+ data.jobId + '></td>'
								+ '<td hidden="hidden"><input type="text" name="text" value='
								+ data.jobStatus + '></td>'
								+ '</tr>' + '</table>';
						layer
								.alert({
									titleText : "修改",// 标题
									yesBtnText : "保存",// 确定按钮
									noBtnText : "取消",// 取消按钮
									closeBtnText : "",// 关闭按钮
									contentText : contentText,// 提示信息
									clearDefaultCss : false,// 不使用默认样式,默认false
									yesCall : function() {// 点击确认按钮回调
										var taskScheduleJob = {};
										var arr = document
												.getElementsByName("text");
										for (var i = 0; i <= 3; i++) {
											if (arr[i].value == ""
													|| arr[i].value == null) {
												alert("必填项不能为空！");
												return false;
											}
										}
										taskScheduleJob.jobStatus=arr[7].value;
										taskScheduleJob.jobId = arr[6].value;
										taskScheduleJob.jobName = arr[0].value;
										taskScheduleJob.beanClass = arr[1].value;
										taskScheduleJob.methodName = arr[2].value;
										taskScheduleJob.cronExpression = arr[3].value;
										taskScheduleJob.params = arr[4].value;
										taskScheduleJob.description = arr[5].value;
										$
												.ajax({
													type : "POST",
													url : server_context+"/task/updateTask",
													contentType : "application/json",
													data : JSON
															.stringify(taskScheduleJob),
													success : function() {
														alert("修改成功！");
														window.location.href = server_context+"/task/getTaskList";
													}
												});

									}
								})
					}
				}
			})
}
