//利用jquery加载方法
	var isHomePage = false;
	var loginMessageFlag = "old";
	
	$().ready(function() {
		$("img").lazyload({
			effect : "fadeIn"
		});

		checkRegisterPram();
		checkRegisterResult();
		checkBack();
		showLoginInfo();
		checkRemeberMe();
		initValidate();
		if(typeof(index_location) !="undefined") {
			$("#index_location_" + index_location).addClass("on");
		}
 	});

//定义事件函数验证输入邮箱是否符合格式
function regEmailChg(event) {
	var e = event || window.event;
	var keyCode = e.keyCode || e.which;
	if (keyCode >= 65 && keyCode <= 90) {
		return false;
	}
}

//通过document.cookie是否存在判断是不是已登录
function isLogin(baseLocation) {
	var name = 'sedu.cookie.ukey';
	var mycookie = document.cookie;
	var start1 = mycookie.indexOf(name + "=");
	if (start1 == -1) {
		var url = window.location.href;
		// SetCookie("backURL",url);
		// window.location.href = baseLocation +
		// "static/web/login/login.html?back=true&loginMessage=operatorLimit";
		return false;
	} else {
		return true;
	}

}

//定义函数显示用户登录后的信息
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

//定义函数显示用户登录页面
function showLoginInfo() {
 		if(isLogin()) {
			var username = getCookieFromServer("sedu.cookie.ukey").split(',')[3];
			if(username.substring(username.length-1) == '"') {
				username = username.substring(0, username.length-1);
			}
			showLoginedMessage(username);
		}else {
			if(loginMessageFlag == "new") {
				$("#login_message_b").html("[<a href='javascript:showRegisterDiv()'>免费注册</a>] [<a href='javascript:showLoginDiv()'>登录</a>]");
				return;
			}else if(loginMessageFlag == "new2"){
				$("#n_userinfo").html("<p>[<a href='javascript:showRegisterDiv()'>免费注册</a>]  [<a href='javascript:showLoginDiv()'>登录</a>]</p>");
				$("#login_message_b").html("<a class='btn_reg' href='javascript:showRegisterDiv()'> </a><a class='btn_login' href='javascript:showLoginDiv()'> </a>");
				return ;
			}
			$("#login_message_b").html("<a class='btn_reg' href='javascript:showRegisterDiv()'> </a><a class='btn_login' href='javascript:showLoginDiv()'> </a>");
			return ;
		}
 	}

//获取用户选课信息
function checkRegisterPram() {
 		if(isHomePage) {
 			$("<tr>" + 
	            "<th>专业：</th>" + 
	            "<td><select name='customer.subjectId' id='regSubjectId'>" + 
	            "<option value='-1'>-请选择-</option>" + 
	            "	<option value='3'>会计证</option>" + 
	            "	<option value='1'>人力</option>" + 
	            "	<option value='7'>注册会计师</option>" + 
	            "	<option value='5'>司法</option>" + 
	            "	<option value='8'>证券</option>" + 
	            "	<option value='9'>一级建造师</option>" + 
	             "	<option value='10'>高级会计师</option>" + 
	              "	<option value='11'>公务员考试</option>" + 
	            "</select></td>" + 
	            "  </tr>" + 
	            "  <tr>" + 
	            "    <td>&nbsp;</td>" + 
	            "    <td><font>请选择您想学习的专业</font></td>" + 
	            "  </tr>").insertAfter("#register_mobile_tr");
 		}
 	}

//定义方法进行用户注册每一步的验证
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
	        	"randomCode":{
	        	    required:true,
	        	    maxlength:4,
	        		remote : {
		                data: {
	                    	'randomCode': function () {
	                    		return $("input[id=regEmail]").val();
	                    	}
		                },
		                async : false,
		                url : baselocation + "/cus/cusweb!checkEmail.action",
		                type : "post"
	                }
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
	        	"randomCode":{
	        	    required:"请输入验证码",
	        	    maxlength:"验证码为4位"
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
	        	if(typeof(ipt) != "undefined" && ipt != null) {
	        		return;
	        	}
	        	if(loginMessageFlag == "new") {
	        		label.html("<img src='" + importURL + "/images/web/cn/index/duihao_new.jpg'/>");
	        		return;
	        	}else if(loginMessageFlag == "new2"){
	        		label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        		return ;
	        	}else{
	        		label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        		return ;
	        	}
	        }
		});
 	}
 	
 
//获取注册时选择的专业 
 	function getSubjectIndexFlag(subjectId) {
	if (subjectId == 1) {
		return "rl";
	} else if (subjectId == 2) {
		return "xl";
	} else if (subjectId == 3) {
		return "kjz";
	} else if (subjectId == 5) {
		return "sf";
	} else if (subjectId == 7) {
		return "cpa";
	} else if (subjectId == 8) {
		return "zq";
	} else if (subjectId == 9) {
		return "jz";
	} else if (subjectId == 10) {
		return "gk";
	} else if (subjectId == 11) {
		return "gwy";
	}
	return null;
}
 	
 
//定义用去注册请求方法
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
						
						//艾德思奇统计：注册。
						var subjectIndexFlag = getSubjectIndexFlag(getCookie("subjectId"));
						if(subjectIndexFlag == null || subjectIndexFlag == '') {
							toIndexPage("?registerSuccess=true");
						} else {
							window.location.href = baselocation + "/" + subjectIndexFlag + "?registerSuccess=true";
						}
					} else if(result.returnMessage == "emailInUsed") {
						$("#reg_email_msg").html("该邮箱已经注册过");
					} else if(result.returnMessage == "regDangerWord") {
						showErrorWin("请不要输入非法关键字。", "register_div");
					} else if(result.returnMessage == "err.randCode"){
						showErrorWin("验证码错误!","register_div") ;
					}else {
						showErrorWin("注册失败，请稍后再试。", "register_div");
					}
				},
				error : function(error) {
					alert('error');
				}
			});
 		}
 	}
 	
 	
//显示用户注册页面	
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
//显示用户登录页面 	
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
 	
//关闭登录或注册页面	
 	function closeRegLogDiv() {
 		forword = false;
 		$("#register_div").fadeOut();
 		$("#login_div").fadeOut();
		$("#login_message").html("").fadeOut();
 		$("#register_success").fadeOut();
 		$("#web_top_black").fadeOut();
 	}

//获得cookie
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
 	
//锁定页面
 	function lockingWindow(){ 
		document.getElementById("web_top_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight + "px";
		$("#web_top_black").fadeIn();
		//document.getElementById("web_top_black").style.display = "block"; 
	}
 	
//定义方法检查登陆者的cookie信息 	
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

//获取url
 	function getParameter(val) {
	var uri = window.location.search;
	var re = new RegExp("" + val + "=([^&?]*)", "ig");
	return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
}

//定义检查注册的结果
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
//			if(typeof(pageTracker) != "undefined" && pageTracker != null) {
//			pageTracker._trackPageview('/register/register.html');
//		}
		}
 	}

//获取cookie进行解析
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
 	
 	function getParameter(val) {
	var uri = window.location.search;
	var re = new RegExp("" + val + "=([^&?]*)", "ig");
	return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
}

var Url = {
	encode : function(string) {
		return escape(this._utf8_encode(string));
	},
	decode : function(string) {
		return this._utf8_decode(unescape(string));
	},
	_utf8_encode : function(string) {
		string = string.replace(/\r\n/g, "\n");
		var utftext = "";

		for (var n = 0; n < string.length; n++) {

			var c = string.charCodeAt(n);

			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}

		return utftext;
	},

	// private method for UTF-8 decoding
	_utf8_decode : function(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;

		while (i < utftext.length) {

			c = utftext.charCodeAt(i);

			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12)
						| ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}

		}
		return string;
	}
}
 	
//检查解析从cookie中获得的url 	
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
 	

//截取请求url中的选择专业的字段 	
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
 		return params;
 	}
 	
//验证用户登录信息是否正确 	
 	function login(i) {

	var params = processLoginParams(i);
	if (!params) {
		return;
	}

	processRemeberMe(i);
	$.ajax({
				url : baselocation + "/cus/cusweb!quickLogin.action",
				data : params,
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					
					if (result.returnMessage == "success") {
						// 艾德思奇统计：登陆。
						if (typeof(forword) != "undefined" && forword) {
							window.location.href = forwordURL;
							return;
						}
						if (i == 3) {
							if(typeof(_gaq)!="undefinde"&&_gaq!=null){
								_gaq.push(['_trackPageview', '/virtual/step_enter.html']);//谷歌统计  添加虚拟页面游览量统计
								_gaq.push(['_trackEvent', 'login_account_3', '3','account_3']);//谷歌统计  添加事件追踪统计
							}
							$('#reg_log_div').css("display", "none");
							if (checkCoursesBought()) {
								$('#buy_step2').css("display", "block");
							}

							return;
						}

						showLoginedMessage(result.entity.userName);

						if (!$("#remeber_me_" + i).attr("checked")) {
							$("#login_email_2").val("");
							$("#login_pwd_2").val("");
						}
						if (i == 2) {
							closeRegLogDiv();
						}
					} else if (result.returnMessage == "err.randCode") {
						showErrorWin("验证码错误。", "");
					} else {
						if (i == 3) {
							showErrorWin("用户名和密码不匹配。", "");
						} else {
							showErrorWin("用户名和密码不匹配。", "login_div");
						}
						$("#login_email_" + i).val("");
						$("#login_pwd_" + i).val("");
					}
				},
				error : function(error) {
					alert(error.responseText);
				}
			});
}

//验证用户登录输入时的信息 	
 	function processLoginParams(i) {
	var params = $("#login_form_" + i + " input").serialize();
	if (params == null && params == '') {
		if (i == 3) {
			showErrorWin("用户名和密码不匹配。", "");
		} else {
			showErrorWin("用户名和密码不匹配。", "login_div");
		}
		return false;
	}
	var paramsArray = params.split("&");
	for (var j = 0; j < paramsArray.length; j++) {
		var entry = paramsArray[j].split("=");
		if (entry != null
				&& entry.length > 1
				&& ((entry[0] == "customer.email" && entry[1] == '') || (entry[0] == "customer.cusPwd" && entry[1] == ''))) {
			if (i == 3) {
				showErrorWin("用户名和密码不匹配。", "");
			} else {
				showErrorWin("用户名和密码不匹配。", "login_div");
			}
			return false;
		}
	}
	return params;
}

//用户第一次登录后向客户端发送cookie
function SetCookie(name, value) {
	var Days = 2;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString() + ";path=/";
}

//分析用户的登录url
 	function processRemeberMe(i) {
	if ($("#remeber_me_" + i).attr("checked")) {
		SetCookie("remeberMe", "true," + $("#login_email_" + i).val() + ","
						+ $("#login_pwd_" + i).val());
	} else {
		DeleteCookie("remeberMe");
	}
}

//定义按键事件
function quickLogKeyUp(event, index) {
	var keyCode = event.keyCode ? event.keyCode : event.which
			? event.which
			: event.charCode;
	if (keyCode == 13) {
		login(index);
	}
}

//定义错误窗口
function showErrorWin(errorText, win) {
	if (win != null && win != '') {
		$("#" + win).css("display", "none");
		preWin = win;
	}
	lockingWindow();
	$("#error_win").fadeIn();
	$("#error_win").css(
			"top",
			document.documentElement.scrollTop
					+ document.documentElement.clientHeight * 0.15);
	$("#error_message").html(errorText);
}

//获取小写字母的判断
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

//关闭错误窗口
 	function closeErrorWin() {
	if (preWin != null && preWin != '') {
		$("#" + preWin).fadeIn();
	} else {
		$("#web_top_black").fadeOut();
	}
	$("#error_win").fadeOut();
	preWin = null;
}
 	
 	
 	