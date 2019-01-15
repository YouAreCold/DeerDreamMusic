var InterfaceList = document.getElementById('InterfaceList');
var hospitalId = null;
var currentId = null;
var InterfaceContentText = '<div id="interfaceBox">'
		+ '<div class="operating">'
		+ '<a class="btn" href="###" id="addList"><i class="fa fa-plus-square"></i>新增</a>'
		+ '<a class="btn" href="###" id="removeList"><i class="fa fa-remove"></i>删除</a>'
		+ '<a class="btn" href="###"><i class="fa fa-pencil-square-o"></i>修改</a>'
		+ '</div>'
		+ '<div class="left-box" >'
		+ '<ul id="listBox">'
		/*
		 * + '<li><a href="###">列表1<i class="fa fa-play" ></i></a></li>' + '<li><a
		 * class="act" href="###">列表2<i class="fa fa-play" ></i></a></li>' + '<li><a
		 * href="###">列表3<i class="fa fa-play" ></i></a></li>' + '<li><a
		 * href="###">列表4<i class="fa fa-play" ></i></a></li>'
		 */
		+ '</ul>'
		+ '</div>'
		+ '<div class="right-box" style="display:none">'
		+ '<div class="step">'
		+ '<span class="img-box" >'
		+ '<img src="../his/image/system.png" >'
		+ '<span >平台方法</span>'
		+ '</span>'
		+ '<span class="img-box" >'
		+ '<img src="../his/image/switch.png"></span>'
		+ '<span class="img-box" >'
		+ '<img  src="../his/image/his.png">'
		+ '<span >HIS方法</span>'
		+ '</span>'
		+ '</div>'
		+ '<div class="input-group">'
		+ '<select name="" id="platformMethodList">'
		+ '</select>'
		+ '<a href="###" id="addInp"><i class="fa fa-share-square-o"></i></a>'
		+ '<div class="inputBox" id="hisMethod">'
		/*
		 * + '<div class="input-item">' + '<a href="###" class="fa fa-remove"></a>' + '<select
		 * name="nextMethodSelect"></select>' + '</div>'
		 */
		+ '</div>'
		+ '</div>'
		+ '<div class="hisInput">'
		+ '<input type="text" id="hisName" name="" >'
		+ '</div>'
		+ '<div class="tab-box">'
		+ '<ul class="tab-btn" >'
		+ '<li><a href="###" class="tab-act">his入参<i class="fa fa-arrow-down" ></i></a></li>'
		+ '<li><a href="###">入参规则<i class="fa fa-arrow-down" ></i></a></li>'
		+ '<li><a href="###">his出参<i class="fa fa-arrow-down" ></i></a></li>'
		+ '<li><a href="###">出参规则<i class="fa fa-arrow-down" ></i></a></li>'
		+ '</ul>'
		+ '<div class="tab-item">'
		+ '<div class="tab-i">'
		+ '<textarea style="resize: none;" id="inParam">'
		+ '</textarea>'
		+ '</div>'
		+ '<div class="tab-i" style="display:none">'
		+ '<div class="input-group">'
		+ '<p>已有入参<a href="###" id="inOldParam"><i class="fa fa-plus-square"></i></a></p>'
		+ '<div class="seleBox" id="inOldParamBox">'
		/*
		 * + '<div class="input-item">' + '<a href="###" class="fa fa-remove"></a>' + '<select
		 * name="inSelectL"></select>' + '<select name="inRuleSelect" ></select>' + '</div>'
		 */
		+ '</div>'
		+ '</div>'
		+ '<div class="input-group">'
		+ '<p>自定义入参<a href="###" id="inNewParam"><i class="fa fa-plus-square"></i></a></p>'
		+ '<div class="seleBox" id="inNewParamBox">'
		/*
		 * + '<div class="input-item">' + '<a href="###" class="fa fa-remove"></a>' + '<input
		 * type="text" name="customIn"/>' + '<select name="customInRuleSelect"></select>' + '</div>'
		 */
		+ '</div>'
		+ '</div>'
		+ '</div>'
		+ '<div class="tab-i" style="display:none">'
		+ '<textarea  style="resize: none;" id="outParam">'
		+ '</textarea>'
		+ '</div>'
		+ '<div class="tab-i" style="display:none">'
		+ '<div class="input-group">'
		+ '<p>已有出参<a href="###" id="outOutParam"><i class="fa fa-plus-square"></i></a></p>'
		+ '<div class="seleBox" id="outOldParamBox">'
		/*
		 * + '<div class="input-item">' + '<a href="###" class="fa fa-remove"></a>' + '<input
		 * type="text" name="outSelectL"/>' + '<select name="outRuleSelect"></select>' + '</div>'
		 */
		+ '</div>'
		+ '</div>'
		+ '<div class="input-group">'
		+ '<p>自定义出参<a href="###" id="outNewParam"><i class="fa fa-plus-square"></i></a></p>'
		+ '<div class="seleBox" id="outNewParamBox">'
		/*
		 * + '<div class="input-item">' + '<a href="###" class="fa fa-remove"></a>' + '<input
		 * type="text" name="customOut"/>' + '<select
		 * name="customOutRuleSelect"></select>' + '</div>'
		 */
		+ '</div>' + '</div>' + '</div>' + '</div>' + '</div>' + '</div>'
		+ '</div>';
InterfaceList.onclick = function() {
	if (!checkHos()) {
		return true;
	}
	showBox();
	getInterfaceList();
	$.ajax({
		type : "POST",
		url : server_context + "/bridge/getPlatMethodList",
		dataType : "json",
		success : function(data) {
			if (data != null && data.length > 0) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					str += "<option value='" + data[i].platformMethodId + "'>"
							+ data[i].description + "</option>"
				}
				$("#platformMethodList").html(str);
				// getMethodFieldsList();
				// getRuleMethodList();
			}
		}
	});

}
function getInterfaceList() {
	$.ajax({
		type : "POST",
		url : server_context + "/bridge/getInterfaceList/" + hospitalId,
		dataType : "json",
		success : function(data) {
			$("#listBox").html("");
			if (data != null && data.length > 0) {
				var str2 = "";
				for (var i = 0; i < data.length; i++) {
					str2 += "<li><a href='###'name='"+data[i].hisMethodName+"' value='" + data[i].interfaceId
							+ "'>" + data[i].hisMethodName
							+ "<i class='fa fa-play' ></i></a></li>";

				}
				$("#listBox").html(str2);
			}
		}
	});
}


function getInterList(sele, id) {
	$.ajax({
		type : "POST",
		url : server_context + "/bridge/getInterfaceList/" + hospitalId,
		dataType : "json",
		success : function(data) {
			if (!sele) {
				$("select[name='nextMethodSelect']").html("");
				if (data != null && data.length > 0) {
					var str1 = "";
					for (var i = 0; i < data.length; i++) {
						str1 += "<option value='" + data[i].interfaceId + "'>"
								+ data[i].hisMethodName + "</option>";

					}
					$("select[name='nextMethodSelect']").html(str1);
				}
			} else {
				if (data != null && data.length > 0) {
					var str1 = "";
					for (var i = 0; i < data.length; i++) {
						str1 += "<option value='" + data[i].interfaceId + "'>"
								+ data[i].hisMethodName + "</option>";

					}
					$(sele).html(str1);
				}
			}
			if (id) {
				for (var j = 0; j < id.length; j++) {
					var nextMethodSelect = document
							.getElementsByName("nextMethodSelect");
					seleOption(nextMethodSelect[j], id);// 绑定His方法
				}
			}
		}

	});
}
// 已有 自定义赋值
function getRuleMethodList(ele) {
	$.ajax({
		type : "POST",
		url : server_context + "/bridge/getRuleMethodList",
		dataType : "json",
		success : function(data) {
			if (data != null && data.length > 0) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					str += "<option value='" + data[i].ruleMethodId + "'>"
							+ data[i].methodInfluence + "</option>"
				}
				if (!ele) {
					$("select[name='inRuleSelect']").html(str);
					$("select[name='outRuleSelect']").html(str);
					$("select[name='customOutRuleSelect']").html(str);
					$("select[name='customInRuleSelect']").html(str);
				} else {
					$(ele).html(str);
				}
			}
		}
	});
}
// 已有下拉框赋值的
function getMethodFieldsList(ele, type) {
	var methodId = $("#platformMethodList").val();
	if (methodId == null || methodId == "") {
		return;
	}

	$.ajax({
		type : "POST",
		url : server_context + "/bridge/getMethodFieldsList",
		dataType : "json",
		data : {
			methodId : methodId
		},
		success : function(data) {
			if (data != null && data.length > 0) {
				var strIn = "";
				var strOut = "";
				for (var i = 0; i < data.length; i++) {
					if (data[i].inOrOut == 0) {
						strIn += "<option value='" + data[i].keyId + "'>"
								+ data[i].keyName + "</option>";
					} else {
						strOut += "<option value='" + data[i].keyId + "'>"
								+ data[i].keyName + "</option>";
					}
				}
				if (!ele) {
					$("select[name='inSelectL']").html(strIn);
					$("select[name='outSelectL']").html(strOut);
				} else {
					if (type == "0") {
						$(ele).html(strIn);
					} else {
						$(ele).html(strOut);
					}
				}

			}

		}
	});
}
function getList(arr1, arr2) {
	var list = [];
	if (arr1.length != arr2.length) {
		return null;
	}
	for (var i = 0; i < arr1.length; i++) {
		var row = {};
		row[arr1[i].value] = arr2[i].value;
		row.ruleId = arr1[i].parentNode.getAttribute("ruleid");
		list.push(row);
	}
	return list;
}
function showBox() {
	layer.alert({
		titleText : "接口列表",// 标题
		yesBtnText : "保存",// 确定按钮
		noBtnText : "取消",// 取消按钮
		closeBtnText : "",// 关闭按钮
		contentText : InterfaceContentText,// 提示信息
		clearDefaultCss : false,// 不使用默认样式,默认false
		yesCall : function() {// 点击确认按钮回调
			var inter = {};
			var interfaceId = currentId;
			inter.interfaceId = interfaceId;
			var platformMethodId = $("#platformMethodList").val();// 所选平台方法
			inter.platformMethodId = platformMethodId;
			var methodList = document.getElementsByName("nextMethodSelect");// his方法
			// 可能为多个连续调用
			var nextMethodId = "";
			for (var i = 0; i < methodList.length; i++) {
				if (i != methodList.length - 1) {
					nextMethodId += methodList[i].value + ",";
				} else {
					nextMethodId += methodList[i].value;
				}
			}
			inter.hospitalId = hospitalId;
			inter.nextMethodId = nextMethodId;
			var hisName = $("#hisName").val();// his方法名
			inter.hisMethodName = hisName;
			var inParam = $("#inParam").val();// his入参
			inter.inParam = inParam;
			var outParam = $("#outParam").val();// his出参
			inter.outParam = outParam;
			// 已有入参
			var inList = getList(document.getElementsByName("inSelectL"),
					document.getElementsByName("inRuleSelect"));
			inter.inList = inList;
			// 已有出参
			var outList = getList(document.getElementsByName("outSelectL"),
					document.getElementsByName("outRuleSelect"));
			inter.outList = outList;
			// 自定义入参
			var customInList = getList(document.getElementsByName("customIn"),
					document.getElementsByName("customInRuleSelect"));
			inter.customInList = customInList;
			// 自定义出参
			var customOutList = getList(
					document.getElementsByName("customOut"), document
							.getElementsByName("customOutRuleSelect"));
			inter.customOutList = customOutList;
			// 验证（TODO）

						
			$.ajax({
				type : "POST",
				url : server_context + "/bridge/addUpdateInterface",
				contentType : "application/json",
				data : JSON.stringify(inter),
				dataType : "json",
				success : function(result) {
					alert(result.msg)
				}
			});
			
		}
	});
	var addInp = document.getElementById("addInp");
	var outOutParam = document.getElementById("outOutParam");
	var outNewParam = document.getElementById("outNewParam");
	var inOldParam = document.getElementById("inOldParam");
	var inNewParam = document.getElementById("inNewParam");
	// 点击添加input 和 select

	addEvents([ addInp ], "click", function() {
		var sele = addInput("select", "hisMethod", "nextMethodSelect");
		getInterList(sele);
	})
	// 入参 出参
	addEvents([ outOutParam ], "click", function() {
		var sele = addInput([ "select", "select" ], "outOldParamBox", [
				"outSelectL", "outRuleSelect" ]);
		getMethodFieldsList(sele[0], 1);
		getRuleMethodList(sele[1]);
	})
	addEvents([ outNewParam ], "click", function() {
		var sele = addInput([ "input", "select" ], "outNewParamBox", [
				"customOut", "customOutRuleSelect" ]);
		getMethodFieldsList(sele[0], 1);
		getRuleMethodList(sele[1]);
	})
	addEvents([ inOldParam ], "click", function() {
		var sele = addInput([ "select", "select" ], "inOldParamBox", [
				"inSelectL", "inRuleSelect" ]);
		getMethodFieldsList(sele[0], 0);
		getRuleMethodList(sele[1]);
	})
	addEvents([ inNewParam ], "click", function() {
		var sele = addInput([ "input", "select" ], "inNewParamBox", [
				"customIn", "customInRuleSelect" ]);
		getMethodFieldsList(sele[0], 0);
		getRuleMethodList(sele[1]);
	})
	// tab切换
	var tabs = document.querySelectorAll('.tab-btn li a');
	var tabItem = document.querySelectorAll('.tab-i');
	tabswitch(tabs, tabItem);

	var interfaceBox = document.getElementById('interfaceBox');
	interfaceBox.onclick = function(e) {
		var e = e || window.event;
		var target = e.target || e.srcElement;
		// 删除规则,方法
		if (target.tagName == "A"
				&& target.parentNode.className == "input-item") {
			removeEle(target.parentNode);
		}

		// 新增一个列表
		if (target.id == "addList" || target.tagName == "I"
				&& target.parentNode.id == "addList") {
			var name=prom("请输入接口的名字");
			//新增之后right-box 的逻辑
			$.ajax({
				type : "POST",
				url : server_context + "/bridge/getInterfaceByName/" + hospitalId+"/"+name,
				dataType : "json",
				success : function(msg) {
					if(msg.status==1){
						alert("HIS方法名已存在！无法添加");
						return false;
					}
					var arr=addList('listBox',name);
						resetRightBox(arr,name);

				}
			});
			
		}

		// 删除一个列表
		if (target.id == "removeList" || target.tagName == "I"
				&& target.parentNode.id == "removeList") {
			// reList("act");
			if (currentId == null) {
				return;
			}
			$.ajax({
				type : "POST",
				dataType : "json",
				url : server_context + "/bridge/deleteInterfaceById/"
						+ hospitalId + "/" + currentId,
				success : function(result) {
					alert(result.msg);
					if (!checkHos()) {
						return true;
					}
					getInterfaceList();
					$(".right-box").attr('style', 'display: none');
				}
			});
		}

	}

	var listBox = document.getElementById("listBox");
	addEvents(
			[ listBox ],
			"click",
			function(e) {
				var e = e || window.event;
				var target = e.target || e.srcElement;
				var listBtn = document.querySelectorAll('.left-box  ul  li a');
				if (target.tagName == "A") { 
					clearClass(listBtn, "act");
					addClass(target, "act");
					var hisMethod = document.getElementById("hisMethod");
					hisMethod.innerHTML = "";
					var name=target.getAttribute("name");
					// 展示选中的接口
					var interId = $(target).attr("value");
					currentId = interId;
					var hisInput = document.getElementById("hisName");
					hisInput.value = name;
					//==================
					$.ajax({
						type : "POST",
						url : server_context + "/bridge/getInterfaceByName/" + hospitalId+"/"+name,
						dataType : "json",
						success : function(msg) {
							l(msg);
				if(msg.status==1){//有数据
					var inOldParamBox = document.getElementById("inOldParamBox");
					var inNewParamBox = document.getElementById("inNewParamBox");
					var outOldParamBox = document.getElementById("outOldParamBox");
					var outNewParamBox = document.getElementById("outNewParamBox");
					inOldParamBox.innerHTML = "";
					inNewParamBox.innerHTML = "";
					outOldParamBox.innerHTML = "";
					outNewParamBox.innerHTML = "";
					$.ajax({
								type : "POST",
								url : server_context
										+ "/bridge/getInterfaceById/" + interId,
								data : "json",
								success : function(data) {
									seleOption("#platformMethodList",
											data.platformMethodId);// 绑定平台方法
									var NextMethodId = getNextMethodId(data.nextMethodId);
									for (var i = 0; i <= NextMethodId.length - 1; i++) {
										addInput("select", "hisMethod",
												"nextMethodSelect");
									}
									getInterList(null, NextMethodId);
									
									// 设置入参 出参文本
									var inParam = document
											.getElementById("inParam");
									var outParam = document
											.getElementById("outParam");
									inParam.value = data.inParam;
									outParam.value = data.outParam;

									// 设置入参 出参
									var ruleList = data.ruleList, inExist = [], inCustom = [], outExist = [], outCustom = [];// 已有入参,自定义入参
									for (var j = 0; j <= ruleList.length - 1; j++) {
										// 已有入参
										if (ruleList[j].inOrOut == 0
												&& ruleList[j].existOrCustom == 0) {
											inExist.push(ruleList[j]);
										}
										// 自定义入参
										else if (ruleList[j].inOrOut == 0
												&& ruleList[j].existOrCustom == 1) {
											inCustom.push(ruleList[j]);
										}
										// 已有入出参
										else if (ruleList[j].inOrOut == 1
												&& ruleList[j].existOrCustom == 0) {
											outExist.push(ruleList[j]);
										}
										// 自定义出参
										else if (ruleList[j].inOrOut == 1
												&& ruleList[j].existOrCustom == 1) {
											outCustom.push(ruleList[j]);
										}
									}

									initRule(inExist, [ "select", "select" ],
											"inOldParamBox", [ "inSelectL",
													"inRuleSelect" ], function(
													inputs, arr) {
												inputs[0].parentNode
														.setAttribute("ruleId",
																arr.ruleId);
												getMethodFieldsList(inputs[0],
														0);
												getRuleMethodList(inputs[1]);
											});
									initRule(
											inCustom,
											[ "input", "select" ],
											"inNewParamBox",
											[ "customIn", "customInRuleSelect" ],
											function(inputs, arr) {
												inputs[0].parentNode
														.setAttribute("ruleId",
																arr.ruleId);
												inputs[0].value = arr.targetFileds;
												getRuleMethodList(inputs[1]);
											});
									initRule(outExist, [ "select", "select" ],
											"outOldParamBox", [ "outSelectL",
													"outRuleSelect" ], function(
													inputs, arr) {
												inputs[0].parentNode
														.setAttribute("ruleId",
																arr.ruleId);
												getMethodFieldsList(inputs[0],
														1);
												getRuleMethodList(inputs[1]);
											});
									initRule(
											outCustom,
											[ "input", "select" ],
											"outNewParamBox",
											[ "customOut", "customOutRuleSelect" ],
											function(inputs, arr) {
												inputs[0].parentNode
														.setAttribute("ruleId",
																arr.ruleId);
												inputs[0].value = arr.targetFileds;
												getRuleMethodList(inputs[1]);
											});
									$(".right-box").attr('style',
											'display: block');
									layer.boxresize();
								}
							});
						}else{//数据库不存在
							resetRightBox(target,name);
						}
				
						}
					});



					//=====================
					
					
				}

			});

}

// 相关方法
function initRule(arr, addType, contain, names, fn) {
	for (var i = 0; i <= arr.length - 1; i++) {
		var inputs = addInput(addType, contain, names);
		fn(inputs, arr[i]);
	}

}
function getNextMethodId(next_method_id) {
	return id = next_method_id.replace(/(^\s*)|(\s*$)/g, "").split(",");
}
function seleOption(select, value) {
	if (typeof select == "string") {
		var sele = document.querySelector(select);
		for (var i = 0; i <= sele.options.length - 1; i++) {
			if (sele.options[i].value == value) {
				sele[i].selected = true;
				return;
			}
		}
	} else if (select instanceof Object) {

		for (var i = 0; i <= select.options.length - 1; i++) {
			if (select.options[i].value == value) {
				select[i].selected = true;
				return;
			}
		}
	}
}

function checkHos() {
	var AInput = document.getElementsByName("hospitalSelect");
	for (var i = 0; i < AInput.length; i++) {
		if (AInput[i].checked) {
			hospitalId = AInput[i].value;
			return true;
		}
	}
	return false;
}
function tabswitch(btn, item) {
	for (var i = 0; i < btn.length; i++) {
		btn[i].index = i;
		btn[i].onclick = function(e) {
			clearClass(btn, "tab-act");
			addClass(this, "tab-act");
			hideEle(item);
			item[this.index].style.display = "block";
		}
	}
}

function removeEle(ele) {
	if (ele.remove) {
		ele.remove();
	} else if (ele.removeNode) {
		ele.removeNode();
	} else {
		ele.parentNode.removeChild(ele);
	}
}

function hideEle(item) {
	for (var i = 0; i < item.length; i++)
		item[i].style.display = "none";
}
function clearClass(ele, css) {
	for (var i = 0; i < ele.length; i++) {
		ele[i].className = ele[i].className.replace(css, "");
	}
}
function addClass(ele, css) {
	var old = ele.className.replace(/^\s+|\s+$/g, "");
	ele.className = old + " " + css;
}
function addEvents(ele, type, fn) {
	for (var i = ele.length - 1; i >= 0; i--) {
		ele[i]['on' + type] = fn;
	}
}
function reList(current) {
	var ele = document.querySelector(".act");
	if (ele) {
		removeEle(ele);
	}
}

function addList(container, text) {
	var listBoxsBox = document.getElementById(container);
	var li = document.createElement("li");
	var a = document.createElement("a");
	a.innerHTML = text + '<i class="fa fa-play"></i>';
	li.appendChild(a);
	listBoxsBox.appendChild(li);
	return [li,a];
}
function addInput(add, container, name) {
	var arr = [];
	var inputBox = document.getElementById(container);
	var div = document.createElement("div");
	div.className = "input-item";
	var a = document.createElement("a");
	a.className = "fa fa-remove";
	div.appendChild(a);
	if (add instanceof Array) {
		var l = document.createElement(add[0]);
		var r = document.createElement(add[1]);
		if (l.tagName == "INPUT") {
			l.type = "text";
		}
		l.name = name[0];
		r.name = name[1];
		div.appendChild(l);
		div.appendChild(r);
		r.style.cssFloat = "right";
		arr = [ l, r ];
	} else {
		var input = document.createElement(add);
		if (input.tagName == "INPUT") {
			input.type = "text";
		}
		input.name = name;
		div.appendChild(input);
		arr = [ input ];
	}

	inputBox.appendChild(div);
	layer.boxresize();
	return arr;
}

//弹出一个输入框，输入一段文字，可以提交  
function prom(text) {  
    var name = prompt(text, ""); //将输入的内容赋给变量 name ，  

    //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值  
    if (name)//如果返回的有内容  
    {  
       return name;
    }  

}  
function resetRightBox(arr,name){
	var listBtn = document.querySelectorAll('.left-box  ul  li a');
	clearClass(listBtn, "act");
	if(arr instanceof Array){
		addClass(arr[1], "act");
		arr[1].setAttribute("name",name);
	}else{
		addClass(arr, "act");
		arr.setAttribute("name",name);
	}
	var hisInput = document.getElementById("hisName");
	var hisMethod = document.getElementById("hisMethod");
	var inParam = document.getElementById("inParam");
	var outParam = document.getElementById("outParam");
	var inOldParamBox = document.getElementById("inOldParamBox");
	var inNewParamBox = document.getElementById("inNewParamBox");
	var outOldParamBox = document.getElementById("outOldParamBox");
	var outNewParamBox = document.getElementById("outNewParamBox");
	hisMethod.innerHTML = "";
	hisInput.value =name;
	inParam.value ="";
	outParam.value ="";
	inOldParamBox.innerHTML = "";
	inNewParamBox.innerHTML = "";
	outOldParamBox.innerHTML = "";
	outNewParamBox.innerHTML = "";
	seleOption("#platformMethodList",1);// 绑定平台方法
	$(".right-box").attr('style','display: block');
	layer.boxresize();
}

function l(s) { console.log(s); }

