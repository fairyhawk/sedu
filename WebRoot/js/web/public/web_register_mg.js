	var loginMessageFlag = "old";
	
	$().ready(function() {
		$("img").lazyload({
			effect : "fadeIn"
		});
		
		checkRegisterResult();
		checkBack();
		showLoginInfo();
		checkRemeberMe();
		initValidate();
//		if(typeof(_gat) !="undefined") {
//			pageTracker = _gat._getTracker('UA-19413742-8');
//		}
 	});
 	
 function initValidate() {
 		jQuery.validator.addMethod("mobile", function(value, element) {
			var pattern = /^1{1}[0-9]{10}$/;
			return this.optional(element) || pattern.test(value);
		});
		
		$("#regForm").validate({
			errorClass: "error",
	        rules: {
	        	"customer.cusPwd" : {
	        		required : true,
	        		maxlength : 20,
	        		minlength : 6
	        	},
	        	"customer.cusName" : {
	        		required : true,
	        		maxlength : 20
	        	},
	        	"customer.mobile" : {
	        		required : true,
	        		mobile : true
	        	},
	        	"customer.subjectId" : {
	        		min : 1
	        	},
	        	"customer.email" : {
	        		required : true,
	        		email : true,
	        		maxlength : 50,
	        		remote : {
		                data: {
	                    	'customer.email': function () {
	                    		return $("input[id=regEmail]").val();
	                    	}
		                },
		                async : false,
		                url : baselocation + "/cus/cusweb!checkEmail.action",
		                type : "post"
	                }
	        	},
	        	"randomCode" : {
	        		required : true,
	        		maxlength : 4,
	        		remote : {
		                data: {
	                    	'randomCode': function () {
	                    		return $("input[id=randomcode]").val();
	                    	}
		                },
		                async : false,
		                url : baselocation + "/cus/cusweb!checkRandomCode.action",
		                type : "post"
	                }
	        	},
	        	"highsoAgreement" : {
	        		required : true
	        	}
	        },
	    	messages : {
	    		"customer.email" : {
	    			remote : "该邮箱已经注册过",			    		
	        		required : "请填写邮箱",
	        		email : "请输入正确的邮箱地址"
	        	},
	        	"customer.cusName" : {
	        		required : "请输入昵称",
	        		maxlength : "昵称不能超过20个字"
	        	},
	        	"customer.cusPwd" : {
	        		required : "密码必须填写",
	        		maxlength : "密码不能低于6位，不能多于20",
	        		minlength : "密码不能低于6位，不能多于20"
	        	},
	        	"cusPwdConfirm" : {
	        		equalTo : "输入密码不一致"
	        	},
	        	"customer.mobile" : {
	        		required : "请输入手机号码",
	        		mobile : "请输入正确的手机号码"
	        	},
	        	"customer.subjectId" : {
	        		min : "请选择一个专业"
	        	},
	        	"randomCode" : {
	        		required : "请输入验证码",
	        		maxlength : "验证码是4位",
	        		remote : "验证码错误"
	        	},
	        	"highsoAgreement" : {
	        		required : " ",
	        		required : "请接受HighSo协议"
	        	}
	    	},
	    	errorPlacement: function(error, element) {
	            if ( element.is(":radio") )
	                error.appendTo( element.parent().next().next() );   
	            else if ( element.is(":checkbox") )
	            	error.appendTo(element.parent().find("span")).css("color", "red");
	            else
	            	error.appendTo(element.parent().parent().next().find("td:last").html("")).css("color", "red");
	        }, 
	        success: function(label) {
	        	var ipt = label.parent().parent().prev().find("input[name=randomCode]")[0];
	        	//alert(label.parent().html());
	        	if(typeof(ipt) != "undefined" && ipt != null) {
	        		return;
	        	}
	        	if(loginMessageFlag == "new") {
	        		label.html("<img src='" + importURL + "/images/web/cn/index/duihao_new.jpg'/>");
	        		return;
	        	}else if(loginMessageFlag == "new2"){
	        		label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        		return;
	        	}
	        	label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        }
		});
 	}
 	
 	function checkRemeberMe() {
		var remeberMe = getCookie("remeberMe");
		if(remeberMe != null && remeberMe != '') {
			var myInfo = remeberMe.split(",");
			if(myInfo != null && myInfo != '' && myInfo[0] == "true") {
				$("#login_email_2").val(myInfo[1]);
				$("#login_pwd_2").val(myInfo[2]);
			}
		}
 	}
 	
 	function checkRegisterResult() {
		if(getParameter("registerSuccess") == 'true') {
			var username = getCookieFromServer("sedu.cookie.ukey").split(',')[3];
			if(username.substring(username.length-1) == '"') {
				username = username.substring(0, username.length-1);
			}
			$("#reg_suc_user_name").html(username);
			//$("#register_success").css("display", "block");
			$("#register_success").fadeIn();
			lockingWindow();
			doafterreg();//注册成功的后续操作
		}
 	}
 	//注册成功的后续操作
	function doafterreg(){
		$.ajax({
				url : baselocation + "/cus/cusweb!doAfterReg.action",
				data : {},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
				},
				error : function(error) {
				}
			});
	}
 	function showLoginInfo() {
 		if(isLogin()) {
			var username = getCookieFromServer("sedu.cookie.ukey").split(',')[3];
			if(username.substring(username.length-1) == '"') {
				username = username.substring(0, username.length-1);
			}
			showLoginedMessage(username);
		} else {
			if(loginMessageFlag == "new") {
				$("#login_message_b").html("[<a href='javascript:showRegisterDiv()'>免费注册</a>] [<a href='javascript:showLoginDiv()'>登录</a>]");
				return ;
			}else if(loginMessageFlag == "new2"){
				$("#n_userinfo").html("<p>[<a href='javascript:showRegisterDiv()'>免费注册</a>]  [<a href='javascript:showLoginDiv()'>登录</a>]</p>");
				$("#login_message_b").html("<a class='btn_reg' href='javascript:showRegisterDiv()'> </a><a class='btn_login' href='javascript:showLoginDiv()'> </a>");
				return ;
				
			}
			$("#login_message_b").html("<a class='btn_reg' href='javascript:showRegisterDiv()'> </a><a class='btn_login' href='javascript:showLoginDiv()'> </a>");
			return ;
		}
 	}
 	
 	// 登陆后的信息
 	function showLoginedMessage(username) {
 		if(loginMessageFlag == "new") {
				$("#login_message_b").html("<font>欢迎，" + username + "</font>   &nbsp; 进入 <a href='javascript:checkLoginTo(\"" + baselocation + "/cus/cuslimit!toUserCenter.action\")' class='btn_a_lan'>[我的HighSo]</a> &nbsp; <a href='javascript:exit()' class='btn_a_hui'>退出</a>");
				return;
			}else if(loginMessageFlag == "new2"){
			$("#n_userinfo").html("<span>欢迎您，"+username+"</span><span><b><a href='javascript:exit()'>退出</a></b>进入 <a href='javascript:checkLoginTo(\"" + baselocation + "/cus/cuslimit!toUserCenter.action\")'>[我的HighSo]</font></a></span>");
			$("#login_message_b").html("欢迎，<font>" + username + "</font><br />请进入 [<a href=\"javascript:checkLoginTo('" + baselocation + "/cus/cuslimit!toUserCenter.action')\" class=\"ablue\">我的HighSo</a>] <a href='javascript:exit()'>退出</a>");
			return;
		}
			$("#login_message_b").html("欢迎，<font>" + username + "</font><br />请进入 [<a href=\"javascript:checkLoginTo('" + baselocation + "/cus/cuslimit!toUserCenter.action')\" class=\"ablue\">我的HighSo</a>] <a href='javascript:exit()'>退出</a>");
 	}
 	
 	
 	
 	function getRegParams() {
 		var params = $("#regForm input,select").serialize();
 		var paramStrs = params.split("&");
 		var subjectNull = true;
 		for(var i=0; i<paramStrs.length; i++) {
 			if(paramStrs[i].indexOf("customer.subjectId")==0) {
 				var cusSubStrs = paramStrs[i].split("=");
 				if(cusSubStrs.length > 1 && !isNaN(cusSubStrs[1]) && parseInt(cusSubStrs[1]) > 0) {
 					subjectNull = false;
 					break;
 				}
 			}
 		}
 		if(subjectNull) {
 			params += ("&customer.subjectId=" + getSubjectIdByIndexPage());
 		}
 		params+="&customer.fromType=3";
 		return params;
 	}
 	
 	function exit() {
 		$.ajax({
			url : baselocation + "/cus/cusweb!ajaxExit.action",
			data : { },
			type : "post",
			dataType : "json",
			cache : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					$("#beforeLogin").css("display", "block");
					$("#afterLogin").css("display", "none");
					var url = window.location.href;
					window.location.href = url.indexOf("?")==-1?url:url.substring(0, url.indexOf("?"));
				}
			},
			error : function(error) {
				alert('error');
			}
		});
 	}
 	
 	function showRegisterDiv(isForword) {
 		if(isForword == true) {
 			forword = true;
 		} else {
 			forword = false;
 		}
 		$("#register_div").fadeIn();
 		$("#login_div").fadeOut();
 		lockingWindow();
 	}
 	
 	function showLoginDiv(isForword) { 
 		if(isForword == true) {
 			forword = true;
 		} else {
 			forword = false;
 		}
 		$("#login_div").fadeIn();
 		$("#register_div").fadeOut();
 		lockingWindow();
 	}
 	
 	function closeRegLogDiv() {
 		forword = false;
 		$("#register_div").fadeOut();
 		$("#login_div").fadeOut();
		$("#login_message").html("").fadeOut();
 		$("#register_success").fadeOut();
 		$("#web_top_black").fadeOut();
 	}
	
	function toHelp(loc) {
		window.location.href = commonquestionURL + "?loc=" + loc;
	}
	
	function checkLoginTo(url) {
		forwordURL = url;
		if(hasLogin()) {
			window.location.href = url;
		} else {
			$("#login_message").html("请先登录。").css("display", "block");
			showLoginDiv(true);
		}
	}
	
	function toUserCenter() {
		
		//checkLoginTo(baselocation + '/cus/cuslimit!goWeiXX.action');
		checkLoginTo(baselocation + '/feed/web!goWeiXX.action');
	}
	
	function hasLogin() {
		var name = 'sedu.cookie.ukey';
		var mycookie = document.cookie;
		var start1 = mycookie.indexOf(name + "=");
		if (start1 == -1) {
			var url =window.location.href;
			return false;
		}
		else {
			return true;
		}
	}
	
	function checkBack() {
		if(getParameter("back") == "true") {
			//从cookie中取出返回的页面地址
			var backURL = getCookie("backURL");
			if(backURL.substring(0,1)=="\"") {
				backURL = backURL.substring( 1, backURL.length - 1);
			}
			//去掉端口号
			if(backURL.indexOf("highso") != -1 && backURL.indexOf("http://") != -1) {
				var tempURL = backURL.substring(backURL.indexOf("//")+2);
				if(tempURL.indexOf(":") != -1) {
					backURL = "http://" + tempURL.substring(0, tempURL.indexOf(":")) + tempURL.substring(tempURL.indexOf("/"));
				}
			}
			checkLoginTo(backURL);
		}
	}

	var forword = false;
	var forwordURL = null;
	// preWin: 全局的变量，保存一个需要隐藏的id
	var preWin = null;
	var pageTracker = null;

	function checkFromAndAgent(){
		var webFrom = getParameter("webFrom");
		var webAgent = getParameter("webAgent");
		//d7表示给cookie指定7天的失效时间
		if(webFrom != null && webFrom.trim() != "") {
			SetCookieOutTime("webFrom", webFrom,"d7");
		}
		if(webAgent != null && webAgent.trim() != "") {
			SetCookieOutTime("webAgent", webAgent,"d7");
		}
	}

	function getUserId() {
		var name = 'sedu.cookie.ukey';
		var mycookie = document.cookie;
		var start1 = mycookie.indexOf(name + "=");
		if (start1 == -1)
			return null;
		else {
			start = mycookie.indexOf("=", start1) + 1;
			var end = mycookie.indexOf(";", start);
			if (end == -1) {
				end = mycookie.length;
			}
			var value = decodeURIComponent(mycookie.substring(start, end));
			if (value == null) {
				return null;
			} else {
				var rlt = value.replace(/^\"|\"$/g, '');
				var str = rlt.split(',');
				return str[0];
			}
		}
	}

	function isLogin(baseLocation) {
		var name = 'sedu.cookie.ukey';
		var mycookie = document.cookie;
		var start1 = mycookie.indexOf(name + "=");
		if (start1 == -1) {
			var url =window.location.href;
			//SetCookie("backURL",url);
			//window.location.href = baseLocation + "static/web/login/login.html?back=true&loginMessage=operatorLimit";
			return false;
		}
		else
		{
			return true;
		}
		
	}
	// 通过cookie名，等到cookie对应的字符串
	function getCookie(cookieName) {
		var cookieString = document.cookie;
		var start = cookieString.indexOf(cookieName + '=');
		// 加上等号的原因是避免在某些 Cookie 的值里有
		// 与 cookieName 一样的字符串。
		if (start == -1) // 找不到
			return null;
		start += cookieName.length + 1;
		var end = cookieString.indexOf(';', start);
		if (end == -1) {
			return unescape(cookieString.substring(start));
		} else {
			return unescape(cookieString.substring(start, end));
		}
	}

	function getCookieFromServer(cookieName) {
	
		var cookieString = document.cookie;
		var start = cookieString.indexOf(cookieName + '=');
		// 加上等号的原因是避免在某些 Cookie 的值里有
		// 与 cookieName 一样的字符串。
		if (start == -1) // 找不到
			return null;
		start += cookieName.length + 1;
		var end = cookieString.indexOf(';', start);
		if (end == -1) {
			return Url.decode(cookieString.substring(start));
		} else {
			return Url.decode(cookieString.substring(start, end));
		}
	}

	// 删除cookie
	function DeleteCookie(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = getCookie(name);
		document.cookie = name + "=" + escape(cval) + ";expires=" + exp.toGMTString() + ";path=/";
	}
	
	// 设置cookie的有效期
	// name: cookie的名字
	// value: cookie值
	function SetCookie(name, value) {
		// 默认为两天
		var Days = 2;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		// 
		document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=/";
	}
	
	
	//自定义cookies失效时间 s指秒 h指天数 d指天数 如s40代表40秒
	function SetCookieOutTime(name, value, outTime) {
		var strsec = getsec(outTime);
		var exp = new Date();
		exp.setTime(exp.getTime() + strsec * 1);
		document.cookie = name + "=" + escape(value) + ";expires="
				+ exp.toGMTString()+";path=/";
	}
	// 转换cookies时间
	function getsec(str) {
		var str1 = str.substring(1, str.length) * 1;
		var str2 = str.substring(0, 1);
		if (str2 == "s") {
			return str1 * 1000;
		} else if (str2 == "h") {
			return str1 * 60 * 60 * 1000;
		} else if (str2 == "d") {
			return str1 * 24 * 60 * 60 * 1000;
		}
	}
	//打包功能
	//参数: 包id 、包名称、总价、 所有课程用#隔开 id, title, teacher, lessontime, reprice, price, gmnum,jifen 
	function addbao (id,name,totalPrice,tupian,allclass,baselocation,isJump) {
		var packageInfo = getCookie("coursesbao");
		if(packageInfo!=null) {
			var packageArray = packageInfo.split("/");
			for(var i=0; i<packageArray.length; i++) {
				var packageGood = packageArray[i];
				if(packageGood != "") {
					var packageGoodInfo = packageGood.split(".");
					if(packageGoodInfo[0] == id) {
						window.open(buyURL,'_self');
						return;
					}
				}
			}
		}
		var goods =  id+"."+name+"."+totalPrice+"."+tupian+"."+allclass+"/";
		var  oldCoursesDan = getCookie("coursesbao");
		if(oldCoursesDan == null) {
			oldCoursesDan = '';
		}
		SetCookie("coursesbao", oldCoursesDan+goods);
		if(isJump) {
			window.open(buyURL,'_self');
		}
	}

	function getParameter(val){
		var uri = window.location.search; 
		var re = new RegExp("" +val+ "=([^&?]*)", "ig"); 
		return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null); 
	}

	var Url = {
		encode : function (string) { 
			return escape(this._utf8_encode(string)); 
		}, 
		decode : function (string) { 
			return this._utf8_decode(unescape(string)); 
		}, 
		_utf8_encode : function (string) { 
			string = string.replace(/\r\n/g,"\n"); 
			var utftext = ""; 
		
			for (var n = 0; n < string.length; n++) { 
			
			var c = string.charCodeAt(n); 
			
			if (c < 128) { 
			utftext += String.fromCharCode(c); 
			} 
			else if((c > 127) && (c < 2048)) { 
			utftext += String.fromCharCode((c >> 6) | 192); 
			utftext += String.fromCharCode((c & 63) | 128); 
			} 
			else { 
			utftext += String.fromCharCode((c >> 12) | 224); 
			utftext += String.fromCharCode(((c >> 6) & 63) | 128); 
			utftext += String.fromCharCode((c & 63) | 128); 
			} 
			
			} 
			
			return utftext; 
			}, 
			
			// private method for UTF-8 decoding 
			_utf8_decode : function (utftext) { 
			var string = ""; 
			var i = 0; 
			var c = c1 = c2 = 0; 
			
			while ( i < utftext.length ) { 
			
			c = utftext.charCodeAt(i); 
			
			if (c < 128) { 
			string += String.fromCharCode(c); 
			i++; 
			} 
			else if((c > 191) && (c < 224)) { 
			c2 = utftext.charCodeAt(i+1); 
			string += String.fromCharCode(((c & 31) << 6) | (c2 & 63)); 
			i += 2; 
			} 
			else { 
			c2 = utftext.charCodeAt(i+1); 
			c3 = utftext.charCodeAt(i+2); 
			string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63)); 
			i += 3; 
			} 
			
			} 
			return string; 
		}
	}
	
	// 错误信息提示，当前，该脚本的意义不大！2011-5-9
	// win: 页面某id，该标签将被隐藏
	// errorText: 错误信息
 	function showErrorWin(errorText, win) {
 		if(win != null  && win !='') {
 			// 将win的属性设置为不显示
			$("#" + win).css("display", "none");
			// preWin: 一个全局变量，保存id
			preWin = win;
 		}
 		// 目前登陆和注册也已修改，不再需要黑底效果。2011-5-9
 		// lockingWindow();
 		
 		// 目前所有错误提示信息，均采用弹出框，或者将来采用js直接生成到DOM中。 2011-5-9
 		// $("#error_win").fadeIn();
 		// $("#error_win").css("top", document.documentElement.scrollTop + document.documentElement.clientHeight * 0.15);
 		// $("#error_message").html(errorText);
 	}
 	
 	function showSuccessWin(successText, win) {
 		if(win != null  && win !='') {
			$("#" + win).css("display", "none");
			preWin = win;
 		}
 		// lockingWindow();
 		// $("#success_win").fadeIn();
 		// $("#success_win").css("top", document.documentElement.scrollTop + document.documentElement.clientHeight * 0.15);
 		// $("#success_message").html(successText);
 	}
 	
 	// closeErrorWin 目前用途不多，暂时可以隐去
 	function closeErrorWin() {
 		if(preWin != null  && preWin !='') {
			$("#" + preWin).fadeIn();
 		} else {
			$("#web_top_black").fadeOut();
 		}
 		$("#error_win").fadeOut();
 		preWin = null;
 	}
 	
 	// closeSuccessWin 目前用途不多，暂时可以隐去
 	function closeSuccessWin() {
 		if(preWin != null  && preWin !='') {
			$("#" + preWin).fadeIn();
 		} else {
			$("#web_top_black").fadeOut();
 		}
 		$("#success_win").fadeOut();
 		preWin = null;
 	}
	
	function toIndexPage(parms){
		var indexURL = getCookie("indexURL");
		if(indexURL==null || indexURL == '') {
			window.location.href = baselocation + "/";
			return;
		}
		
		var temp = indexURL.substring(7);
		var flagIndex = temp.indexOf("highso")==0?1:2;
		var strs = temp.split('/');
		var urlFlag = '';
		if(strs[flagIndex] != null || strs[flagIndex] != '') {
			if(strs[flagIndex].indexOf('?')>-1) {
				urlFlag = strs[flagIndex].substring(0, strs[flagIndex].indexOf("?"));
			} else {
				urlFlag = strs[flagIndex];
			}
		}
		indexURL = "http://";
		for(var i=0; i<flagIndex; i++) {
			indexURL += strs[i] + "/";
		}
		indexURL += urlFlag;
		if(parms != null) {
			indexURL += parms.indexOf("?") == 0 ? parms : "?" + parms;
		}
		window.location.href = indexURL;
	}
	
	function getSubjectIndexFlag(subjectId) {
		if(subjectId == 1) {
			return "rl";
		} else if(subjectId == 2) {
			return "xl";
		} else if(subjectId == 3) {
			return "kjz";
		} else if(subjectId == 5) {
			return "sf";
		} else if(subjectId == 7) {
			return "cpa";
		} else if(subjectId == 8) {
			return "zq";
		} else if(subjectId == 9){
			return "jz" ;
		}else if(subjectId == 10){
			return "gk" ;
		}else if(subjectId == 11){
			return "gwy" ;
		}else if(subjectId == 12){
			return "jjs" ;
		}else if(subjectId == 14){
				return "zlkjs" ;
		}else if(subjectId == 15){
				return "kjs" ;
		}else if(subjectId == 16){
				return "jz02" ;
		}
		return null;
	}
		
	function getSubjectIdByIndexPage() {
		var indexURL = getCookie("indexURL");
		if(indexURL==null || indexURL == '') {
			return 3;
		}
		
		var temp = indexURL.substring(7);
		var flagIndex = temp.indexOf("highso")==0?1:2;
		var strs = temp.split('/');
		var urlFlag = '';
		if(strs[flagIndex] != null || strs[flagIndex] != '') {
			if(strs[flagIndex].indexOf('?')>-1) {
				urlFlag = strs[flagIndex].substring(0, strs[flagIndex].indexOf("?"));
			} else {
				urlFlag = strs[flagIndex];
			}
		}
		if(urlFlag == null || urlFlag == '' || urlFlag == 'kjz') {
			return 3;
		} else if (urlFlag == 'rl') {
			return 1;
		} else if (urlFlag == 'sf') {
			return 5;
		} else if (urlFlag == 'cpa') {
			return 7;
		} else if (urlFlag == 'zq') {
			return 8;
		} else if (urlFlag == 'xl') {
			return 2;
		} else if (urlFlag == 'jz') {
			return 9;
		}else if (urlFlag == 'gk') {
			return 10;
		}else if (urlFlag == 'gwy') {
			return 11;
		}
		return 3;
	}
	
	// 处理器。出来用户是否选中 "记住我" 的复选框。
 	// 参数 i ：标记作用，在function login(i)中，有说明
 	function processRemeberMe(i) {
 		// 将 "记住我" 选中，并写入cookie，否则清空该cookie
 		if($("#remeber_me_" + i).attr("checked")) {
			SetCookie("remeberMe", "true," + $("#login_email_" + i).val() + "," + $("#login_pwd_" + i).val());
		} else {
			DeleteCookie("remeberMe");
		}
 	}
 	
 	// 获取登陆参数，可见得到的params为明文！未经过加密处理
 	// 最后，如果判断成功，将 params 直接返回 -------- 将其处理成后，封装到一个对象或者集合中，会更好！！！
 	function processLoginParams(i) {
 		var params = $("#login_form_" + i + " input").serialize();
 		//alert(params);//customer.email=bai66%40test.com&customer.cusPwd=123123&remember_me_div=on
		if(params == null && params == '') {
			// 在login(i)中，i的参数直接传递为2，一下代码似乎没有意义
			if(i == 3) {
				// showErrorWin("用户名和密码不匹配。", "");
				alert("用户名和密码不匹配。");
			} else {
				// showErrorWin("用户名和密码不匹配。", "login_div");
				alert("用户名和密码不匹配。");
			}
			return false;
		}
		// 将params 进行解析
		var paramsArray = params.split("&");
		for(var j=0; j<paramsArray.length; j++) {
			var entry = paramsArray[j].split("=");
			// 判断                                                 邮箱为空字符串                                          密码为空字符串                                          
			if(entry != null && entry.length > 1 && ((entry[0] == "customer.email" && entry[1] == '') || (entry[0] == "customer.cusPwd" && entry[1] == ''))) {
				if (i == 3) {
					// showErrorWin("用户名和密码不匹配。", "");
					alert("用户名和密码不匹配。");
				} else {
					// showErrorWin("用户名和密码不匹配。", "login_div");
					alert("用户名和密码不匹配。");
				}
				return false;
			}
		}
		return params;
 	}
 	
 	function quickLogKeyUp(event, index) {
 		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;   
        if (keyCode == 13) {
        	login(index);
        }
 	}
 	
 	function  regEmailChg(event) {
	    var e = event||window.event;
	    var keyCode = e.keyCode||e.which;
	    if (keyCode >= 65 && keyCode <= 90){
	    	return false;
	    }
 	}
 	// 该脚本适用于较老的登陆和注册样式，即登陆框和注册框为DIV层。目前为单独的html展示。2011-5-9
	// 黑底效果
	function lockingWindow(){
		document.getElementById("web_top_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight + "px";
		$("#web_top_black").fadeIn();
		//document.getElementById("web_top_black").style.display = "block"; 
	}
 	
 	function  iptLowerCase(event) {
	    var e = event||window.event;
	    var keyCode = e.keyCode||e.which;
	    if (keyCode >= 65 && keyCode <= 90){
	    	e.keyCode = keyCode + 32;
	    }
	    if (keyCode==32){
	    	e.keyCode=0;
			e.returnvalue=false;
	    }
 	}
 	
 	var  highlightcolor='#c1ebff';
	//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
	var  clickcolor='#10b2f6';

	function  changeto(){
		source=event.srcElement;
		if(source.tagName=="TR"||source.tagName=="TABLE")
			return;
		while(source.tagName!="TD")
		source=source.parentElement;
		source=source.parentElement;
		cs  =  source.children;
		if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
	}

	function  changeback(){
		if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
			return
		if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
		//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}

	function  clickto(){
		source=event.srcElement;
		if  (source.tagName=="TR"||source.tagName=="TABLE")
			return;
		while(source.tagName!="TD")
			source=source.parentElement;
			source=source.parentElement;
			cs  =  source.children;

		if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
		else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
	
	String.prototype.trim=function(){
　　    return this.replace(/(^\s*)|(\s*$)/g, "");
　　 }
　　 String.prototype.ltrim=function(){
　　    return this.replace(/(^\s*)/g,"");
　　 }
　　 String.prototype.rtrim=function(){
　　    return this.replace(/(\s*$)/g,"");
　　 }

 	// 登陆Login
 	// 参数 i ：标记作用，用来标记一个id，比如某id为 id="form_test_1"，那么在取值的时候，$("#form_test"+ i )
 	function login(i) {
 		// 进行用户名和密码的配置，如果匹配通过，直接返回 params 
 		// parsms 是 customer.email=bai66%40test.com&customer.cusPwd=123123&remember_me_div=on
 		var params = processLoginParams(i);
		if(!params) {
			return;
		}
		//微学习，使用，根据该字段判断是否在登录之前用户已失效,如果失效则累计用户针对微学习的使用次数(没失效一次算一次累计)
		var isLoginFlag = isLogin();
		
		// 处理用户是否选中'记住我'的复选框
 		processRemeberMe(i);
 		// Ajax 请求
		$.ajax({
			url : baselocation + "/cus/cusweb!quickLogin.action",
			data : params,
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					//艾德思奇统计：登陆。
					//adSageLogin(getUserId());
					if(typeof(forword) !="undefined" && forword) {
						window.location.href = forwordURL;
						return;
					}
					// 登陆时，所传递的参数 i = 2 , 所以该代码用处不大
					if(i == 3) {
						if(typeof(_gaq)!="undefinde"&&_gaq!=null){
								_gaq.push(['_trackPageview', '/virtual/step_enter.html']);//谷歌统计  添加虚拟页面游览量统计
								_gaq.push(['_trackEvent', 'login_account_3', '3','account_3']);//谷歌统计  添加事件追踪统计
						}
						$('#reg_log_div').css("display", "none");
						if(checkCoursesBought()){
							$('#buy_step2').css("display", "block");
						}
						return;
					}
					// 用户登陆后，显示信息提示窗
					showLoginedMessage(result.entity.userName);
					
					if(!$("#remeber_me_" + i).attr("checked")) {
						$("#login_email_2").val("");
						$("#login_pwd_2").val("");
					}
					if(i == 2) {
						// 直接获取注册成功的代码
						var subjectIndexFlag = getSubjectIndexFlag(getCookie("subjectId"));
						if(subjectIndexFlag == null || subjectIndexFlag == '') {
							toIndexPage("?registerSuccess=true");
						} else {
							//调用微学习模块的函数，用于统计用户登录成功数
							try{
								weixuexiEntranceBy_login(isLoginFlag);
							}catch(e){}
							toUserCenter();
						}
					}
				} else if(result.returnMessage == "err.randCode"){
					showErrorWin("验证码错误。", "");
				} else {
					if(i == 3) {
						showErrorWin("用户名和密码不匹配。", "");
					} else {
						showErrorWin("用户名和密码不匹配。", "login_div");
					}
					$("#login_email_" + i).val("");
					$("#login_pwd_" + i).val("");
				}
			},
			error : function(error) {
				alert('error');
			}
		});
 	}
 	
 	// 注册
 	function register() {
 		if($("#regForm").valid()) {
 			var params = getRegParams();
 			
			$.ajax({
				url : baselocation + "/cus/cusweb!register4ajax.action",
				data : params,
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					if(result.returnMessage == "success") {
						//注册后分配机会
						//doafterregChance();
						//调用微学习模块的函数，用于统计用户注册成功数
						try{
							weixuexiEntranceBy_register();
						}catch(e){}
						
						
						//艾德思奇统计：注册。
						//adSageRegister();
						var subjectIndexFlag = getSubjectIndexFlag(getCookie("subjectId"));
						if(subjectIndexFlag == null || subjectIndexFlag == '') {
							toIndexPage("?registerSuccess=true");
						} else {
							toUserCenter();
						}
					} else if(result.returnMessage == "emailInUsed") {
						// $("#reg_email_msg").html("该邮箱已经注册过");
						alert('该邮箱已经注册过！');
					} else if(result.returnMessage == "regDangerWord") {
						// showErrorWin("请不要输入非法关键字。", "register_div");
						alert('请不要输入非法关键字。');
					} else if(result.returnMessage == "err.randCode"){
						// showErrorWin("验证码错误!","register_div") ;
						alert('验证码错误!');
					} else {
						// showErrorWin("注册失败，请稍后再试。", "register_div");
						alert('注册失败，请稍后再试。');
					}
				},
				error : function(error) {
					alert('error');
				}
			});
 		}
 	}

  //注册后分配机会 谢添加
  	function doafterregChance(){
  		$.ajax({
  				url : baselocation + "/crm/crmChance!doAfterReg.action",
  				data : {},
  				type : "post",
  				dataType : "json",
  				cache : false,
  				async : false,
  				success : function(result) {
  				},
  				error : function(error) {
  				}
  			});
  	}
 	