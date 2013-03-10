
/****************************************试题业务-->关联知识树业务---start***********************************************/

/** 根据专业自动给iframe设置src路径 */
function getQuestionsDataByKsnId(actionUrl,index,indexName) {
	createIframe(actionUrl,index,indexName);
}


var iframeIndex = new Array();


/**
 * 创建iframe标签,针对不同模块的数据处理，index,indexName请保持唯一
 * 
 * @param {} actionUrl	请求地址
 * @param {} index	索引值，数字类型	
 * @param {} indexName	说应对应的名称
 */
function createIframe(actionUrl,index,indexName) {
	
	
	try{
		iframeIndex[index] = indexName;
		
		var frm = document.createElement("iframe");

		frm.setAttribute("id", "feed_op_item_search" + iframeIndex[index]);
		frm.setAttribute("name", "feed_op_item_search" + iframeIndex[index]);
	
		frm.setAttribute("width", "500");
		frm.setAttribute("height", "500");
		frm.setAttribute("src", actionUrl);
		var bname = navigator.appName;
		if (bname.search(/microsoft/i) != 0) {
			/** 非IE */
			//document.getElementsByTagName("div")[0].appendChild(frm);
			document.getElementById(indexName).appendChild(frm);
		} else {
			/** IE */
			
			// 刷新一下，兼容ie6
			//document.getElementsByTagName("div")[0].appendChild(frm);
			//document.frames("feed_op_item_search" + iframeIndex[index]).location.reload();
			document.getElementById(indexName).appendChild(frm);
			document.frames("feed_op_item_search" + iframeIndex[index]).location.reload();
		}
	}catch(e){
	}
}
/**
 * 删除iframe元素
 * 
 * @param {} index	索引值，数字类型，与createIframe参数的index一致
 */
function removeIframe(index) {
	//document.getElementsByTagName("div")[0].removeChild(document.getElementById("feed_op_item_search" + iframeIndex[index]));
	document.getElementById(iframeIndex[index]).removeChild(document.getElementById("feed_op_item_search" + iframeIndex[index]));
}

/**
 * 赋值，将返回的数据，动态添加到页面中进行显示
 * 
 * @param {} containerId 容器id，用于显示数据
 * @param {} str 值
 * @param {} resStatus 状态[0失败/1成功]
 * @param {} resType [1集合/0单项]
 * 
 */
function common_item_show(containerId, str, resStatus, resType,index) {
	
	// 校验处理结果
	if (resStatus == 1) {
		// 完成显示替换操作
		
		if (resType == 1) {// 表示集合（集合则替换）
			$("#" + containerId).html(str);
		} else {// 表示单项（单项则追加到最前面）
			$("#" + containerId).prepend(str);
		}
	} else if(resStatus == 0){
		alert("查询失败!");
	}
	
	// 数据使用完后进行删除
	removeIframe(index);
}

/****************************************试题业务-->关联知识树业务---end***********************************************/


/**
 * 修改某对象上的个数
 * 
 * @param {}
 *            id 容器id
 * @param {}
 *            status 状态：plus=递增，minus=递减
 */
function resetCount(id, status) {
	var s = $("#" + id).html();
	s = parseInt(s);// 类型转换
	if (status == "plus") {
		s += 1;
	} else if (status == "minus") {
		s -= 1;
	}
	$("#" + id).html(s);
}

/****common*****/
/**
 触发某个对象的click事件(单击事件)
 */
/**
 * 触发点击事件,参数只能使用一个，当id值为null时，obj参数将生效
 * 
 * 
 * @param {} id	标签id值
 * @param {} obj 标签对象
 */
function onClickEventEntrust(id,obj) {
	
	var o = null;
	
	if(id == null || id == ""){
		o = obj;
	}else{
		o = document.getElementById(id);
	}
	
	var bname = navigator.appName;
	if (bname.search(/netscape/i) == 0) {
		//FF
		var e = document.createEvent("Events");//第一：创建一个事件
		e.initEvent("click", true, false);//第二：初始化要触发的事件
		o.dispatchEvent(e);//第三：执行 要触发的事件。
	} else if (bname.search(/microsoft/i) == 0) {
		o.fireEvent('onclick');
	}
}

/**
 * 微学习入口页面，注册成功后调用后
 */
function weixuexiEntranceBy_register(){
	
	//这里写业务
	//alert("微学习入口-注册成功");
	
	try{
		//alert($("#feed_weixuexi_page").html());
		if($("#feed_weixuexi_page").html() != null){
			var email = $("#regEmail").val();
			
			if(email != null && email.indexOf("@") != -1){
				setRegLoginLog("2",email);	
				setRegLoginUseLog(getCookie("subjectId"),2,email);//修改'regEmail'在register_mg.htm中
			}
		}
	}catch(e){
		alert("错误" + e);
	}
	
}

/**
 * 微学习入口页面，登录成功后调用后
 * 
 * @param {} isLoginFlag 是否已登录 true/false
 */
function weixuexiEntranceBy_login(isLoginFlag){
	
	//这里写业务
	//alert("微学习入口-登录成功");
	
	try{
		//alert($("#feed_weixuexi_page").html());
		if($("#feed_weixuexi_page").html() != null){
			var email = $("#login_email_2").val();
			
			if(email != null && email.indexOf("@") != -1){
				setRegLoginLog("4",email);
				if(!isLoginFlag){//如果没有登录,登录框中登录成功之前返回的值
					try{
						//修改用户
						setRegLoginUseLog(getCookie("subjectId"),4,email);//'login_email_2'在register_mg.htm中
					}catch(e){}
				}
			}
		}
	}catch(e){
		alert("错误" + e);
	}
}

/**
 * 微学习入口页-->注册，登录，点击调用
 * @param {} sign = reg/login
 */
function weixuexiEntranceBy_click_reg_login(sign){
	
	var type = "0";
	if(sign == "reg"){
		type = "1";//注册
	}else if(sign == "login"){
		type = "3";//登录
	}
	if(type != "0"){
		setRegLoginLog(type);
	}
}

/**
 * 针对，注册/登录统计，后台用于实现，不同时间段用户的访问
 * 
 * @param {} type=(num)
 */
function setRegLoginLog(type,email) {
	var actionURl = "/feed/stat/statistics!regLogin_Stat.action";
	//根据专业id获取缩写字符
	/*
	var param = "type=" + type;
	$.ajax({
		type : 'post',
		url : actionURl,
		data : param,
		success : function(data) {
			//不响应结果
		}
	});*/
	var params = {
		type : type,
		email : email
	}
	$.post(
		actionURl, 
		params, 
		function topic(data) {
			//alert("数据--->" + data);
		}
	,'json');
}

/**
 * 登录/注册,使用微学习统计
 * 
 * @param {} email
 * @param {} subjectId
 */
function setRegLoginUseLog(subjectId,type,email) {
	
	var actionURl = "/feed/stat/statistics!userUse_Stat.action";
	var params = {
		subjectId : subjectId,
		type : type,
		email : email
	}
	$.post(
		actionURl, 
		params, 
		function topic(data) {
			//alert("数据--->" + data);
		}
	,'json');
}


/**
 * 当前页访问统计业务
 * 
 * @param {} actionURl
 * @param {} type
 * 
 * 备注：暂时不用
 */
function setPageBrowse(actionURl, type) {
	//根据专业id获取缩写字符
	var param = "type=" + type;
	$.ajax({
				type : 'post',
				url : actionURl,
				data : param,
				success : function(data) {
					//alert(date);
				}
			});
}

/**
 * 修改用户点击左侧菜单栏”微学习“次数
 * @param {} actionURl
 * @param {} subjectId
 * 
 * 
 * 备注：暂时不用
 */
function setUseClickNum(actionURl, subjectId) {
	//根据专业id获取缩写字符
	var param = "subjectId=" + subjectId;
	$.ajax({
				type : 'post',
				url : actionURl,
				data : param,
				success : function(data) {
					//alert(date);
				}
			});
}
/**
 * weixuexi.jsp 页面浏览,统计
 * @param {} actionURl
 * @param {} subjectId 专业id
 * @param {} type 类型 1表示微学习
 * @param {} from 点击来源
 */
function pageBrowseOrUseClickNum(actionURl, subjectId,type,from){
	var params = {
		subjectId : subjectId,
		type : type,
		from : from
	}
	$.post(
		actionURl, 
		params, 
		function topic(data) {
		}
	,'json');
}

/**
 * 修改用户观看视频数
 * @param {} actionURl
 */
function setUseVideoLookNum(actionURL,videoSecond,subjectId) {
	
	var params = {
		subjectId : subjectId,
		videoSecond : videoSecond
	}
	$.post(
		actionURL, 
		params, 
		function topic(data) {
			//alert("数据--->" + data);
		}
	,'json');
}


/**
 * 微学习，入口页面/访问统计(浏览)
 */
function setEntranceBrowse() {
	var actionURl = "/feed/stat/statistics!entranceBrowse_Stat.action";
	var params = {}
	$.post(
		actionURl, 
		params, 
		function topic(data) {
			//alert("数据--->" + data);
		}
	,'json');
}
