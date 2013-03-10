	var loginMessageFlag = "old";
	var isHomePage = false;
	$().ready(function() {
		/* 来源统计 */
		var wfaa = new WebFromAgentAnalysis();
		wfaa.analyse();
		$("img").lazyload({
			effect : "fadeIn"
		});
		checkRegisterPram();
		checkRegisterResult();
		checkBack();
		showLoginInfo();
		checkRemeberMe();
		initValidate();
//		if(typeof(_gat) !="undefined") {
//			pageTracker = _gat._getTracker('UA-19413742-8');
//		}
 	});
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
	            "	<option value='2'>心理咨询师</option>" + 
	            "	<option value='12'>经济师考试</option>" + 
	            "	<option value='13'>全国研究生统一入学考试</option>" +
	            "	<option value='14'>初级会计职称</option>" +
	            "	<option value='15'>中级会计职称</option>" +
	            "	<option value='16'>二级建造师</option>" +
	            "	<option value='17'>监理师课程</option>" +
	            "	<option value='18'>造价师课程</option>" +	 
	            "	<option value='20'>自考课程</option>" +	 
	            "	<option value='21'>临床执业医师考试</option>" +
	            "	<option value='22'>法律顾问考试</option>" +
	            "	<option value='23'>成考课程</option>" +
	            "	<option value='24'>注册税务师课程</option>" +
	            "	<option value='25'>报关员考试</option>" +
	            "	<option value='25'>报关员考试</option>" +
	            "	<option value='26'>地方公务员考试</option>" +
	            "	<option value='27'>职称英语课程</option>" +
	            "	<option value='28'>期货从业资格考试</option>" + 
	            "</select></td>" + 
	            "  </tr>" + 
	            "  <tr>" + 
	            "    <td>&nbsp;</td>" + 
	            "    <td><font>请选择您想学习的专业</font></td>" + 
	            "  </tr>").insertAfter("#register_mobile_tr");
 		}
 	}	
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
	        		mobile : true,
	        		maxlength : 20
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
	        	"randomCode":{
	        	    required:true,
	        	    maxlength:4,
	        	    remote : {
		                data: {
	                    	'randonCode': function () {
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
	    			remote : "该邮箱已经注册过 <a style='color:#333;' href='javascript:toforpwd()' >[找回密码?]</a>",			    		
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
	        		mobile : "请输入正确的手机号码",
	        		remote:"该手机已经注册过"
	        	},
	        	"customer.subjectId" : {
	        		min : "请选择一个专业"
	        	},
	        	"randomCode":{
	        	    required:"请输入验证码",
	        	    maxlength:"验证码是4位",
	        	    remote:"验证码错误"
	        	    
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
	        		return;
	        	}
	        	label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        		 
	        	 
	        }
		});
 	}
 	function toforpwd(){
 	 	window.open("http://highso.org.cn/static/web/column/91/index_1.shtml");
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
			$("#reg_suc_proName").html(getSubjectNameById(getCookie("subjectId")));
			disBuyAndCouser(getCookie("subjectId"));
			$("#register_success").fadeIn();
			lockingWindow();
			//注册成功的后续操作
			doafterreg();
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
				//$("#login_message_b").html("[<a href='javascript:showRegisterDiv()'>免费注册</a>] [<a href='javascript:showLoginDiv()'>登录</a>]");
				return ;
			}else if(loginMessageFlag == "new2"){
				//$("#n_userinfo").html("<p>[<a href='javascript:showRegisterDiv()'>免费注册</a>]  [<a href='javascript:showLoginDiv()'>登录</a>]</p>");
				//$("#login_message_b").html("<a class='btn_reg' href='javascript:showRegisterDiv()'> </a><a class='btn_login' href='javascript:showLoginDiv()'> </a>");
				return ;
				
			}else if(loginMessageFlag == "new3"){
				//$("#n_userinfo").html("<p>[<a href='javascript:showRegisterDiv()'>免费注册</a>]  [<a href='javascript:showLoginDiv()'>登录</a>]</p>");
				//$("#login_message_b").html("<a class='btn_reg' href='javascript:showRegisterPage()'> </a><a class='btn_login' href='javascript:showLoginPage()'> </a>");
				return;
			} 
			//$("#login_message_b").html("<a class='btn_reg' href='javascript:showRegisterDiv()'> </a><a class='btn_login' href='javascript:showLoginDiv()'> </a>");
			return ;
		}
 	}
 
 	var qq = 0;
 	function showNoPay(){
 		$.ajax({
			url : baselocation + "/finance/contract!showNopayCountContract.action",
			data : {},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result){
				var unpay = result.entity;
				qq = unpay;
			},
			error : function(error) {
			}
		});
 	}
 	
 function showLoginedMessage(username) {
	 	var unpay = showNoPay();
 		var loginfo1="<div class='headerHome_login headerHome_login02'>"+
        	"<div class='headerHome_login_top'><span class='fLeft'></span></div>"+
        	"<div class='headerHome_login_con'>"+
            	"<div>欢迎，"+username.substring(0,12)+"..."+"</div>"+
                "<div><span> <a href=\"javascript:checkLoginTo('" + baselocation + "/cus/cuslimit!toUserCenter.action')\" >开始学习</a>  </span>&nbsp;&nbsp;&nbsp;<span><a href='javascript:exit()'>退出</a></span></div>"+
            "</div>"+
        	"<div class='headerHome_login_bottom'><span class='fLeft'></span></div>"+
        "</div>";
        
        var loginfo2="<div class='headerHome_login headerHome_login02'>"+
        	"<div class='headerHome_login_top'><span class='fLeft'></span></div>"+
        	"<div class='headerHome_login_con'>"+
            	"<div>欢迎，"+username.substring(0,12)+"..."+"</div>"+
                "<div><span> <a href=\"javascript:checkLoginTo('" + baselocation + "/cus/cuslimit!toUserCenter.action')\" >开始学习</a>  </span>"+
                "&nbsp;&nbsp;<a href=\"javascript:checkLoginTo('" + baselocation + "/finance/contract!getContractList.action?queryContractCondition.currentPage=1')\">未付款订单(<strong>"+qq+"</strong>)</a>&nbsp;&nbsp;"+
                "<span><a href='javascript:exit()'>退出</a></span></div>"+
            "</div>"+
        	"<div class='headerHome_login_bottom'><span class='fLeft'></span></div>"+
        "</div>";
        
		if(loginMessageFlag == "new3") {//首页的登录显示loginMessageFlag == "new3"
			if(qq!=0){
				$("#login_message_b").html(loginfo2);
			}else{
				$("#login_message_b").html(loginfo1);
			}
		}else {//其他项目页面的显示
			/*
			if(qq!=0){
				$("#login_message_b").html("<div class='logbox02'>欢迎，<font>" + username.substring(0,12)+"..." + "</font><br/><a href='javascript:checkLoginTo(\"" + baselocation + "/cus/cuslimit!toUserCenter.action\")' class=\'ablue\'>开始学习</a>&nbsp;<span class='logbox02_order'><a href=\"javascript:checkLoginTo('" + baselocation + "/finance/contract!getContractList.action?queryContractCondition.currentPage=1')\">未付款订单(<span class='logbox02_size'>"+qq+"</span>)</a>&nbsp;</span> [<a href='javascript:exit()'>退出</a>]<div class='logbox02_right'></div></div>");
			}else{
				$("#login_message_b").html("<div class='logbox02'>欢迎，<font>" + username.substring(0,12)+"..." + "</font><br/><a href='javascript:checkLoginTo(\"" + baselocation + "/cus/cuslimit!toUserCenter.action\")' class=\'ablue\'>开始学习</a>[<a href='javascript:exit()'>退出</a>]<div class='logbox02_right'></div></div>");
			}*/
			if(qq!=0){
				$("#login_message_b").html(loginfo2);
			}else{
				$("#login_message_b").html(loginfo1);
			}
			
		}
 	}
 	
 	
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
						//adSageRegister();
						//注册后分配机会
						
						if(typeof(_gaq)!="undefinde"&&_gaq!=null){
							_gaq.push(['_trackEvent', 'Entry', 'Register','Confirmed']); //谷歌统计 添加事件追踪统
							_gaq.push(['gwo._setAccount', 'UA-22194725-5']);
  							_gaq.push(['gwo._trackPageview', '/1291626652/goal']);							
						}
						
						doafterregChance();
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
 		if (loginMessageFlag=="new3"){
 			params+="&customer.fromType=4";//首页注册
 		}else{
 			params+="&customer.fromType=1";//项目页面注册
 		}
 		return params;
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
 	//注册登录div居中
		$(function(){
			var $height = window.screen.availHeight					
			$(window).scroll(function(){							
				var cc = $(this).scrollTop()						
				var dd = cc + $height/2-320;
				$("#register_div").css({"top":dd+"px"});
				$("#login_div").css({"top":dd+"px"})
				})
			})

	//注册登录div居中 	
 	function showRegisterDiv(isForword) {
 		if(!(isLogin(baselocation + "/"))) {
 			
 			if(typeof(_gaq)!="undefinde"&&_gaq!=null){
			_gaq.push(['_trackEvent', 'Entry', 'Register','Product_Header']); //谷歌统计 添加事件追踪统
			}
	 		if(isForword == true) {
	 			forword = true;
	 		} else {
	 			forword = false;
	 		}
	 		$("#register_div").fadeIn();
	 		$("#login_div").fadeOut();
	 		lockingWindow();
 		
 		}else{
 			//跳转到个人中心
 			window.open(baselocation+"/cus/cuslimit!toUserCenter.action");
 		}
 	
 	}
 	
 	function showLoginDiv(isForword) {
 		if(typeof(_gaq)!="undefinde"&&_gaq!=null){
			_gaq.push(['_trackEvent', ' Entry', 'Login',' Product_Header']); //谷歌统计 添加事件追踪统
		} 
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
		if(typeof(_gaq)!="undefinde"&&_gaq!=null){
		_gaq.push(['_trackEvent', ' Entry', 'Login',' Index_Header']);//谷歌统计 添加事件追踪统计
		}
		forwordURL = url;
		if(hasLogin()) {
			window.location.href = url;
		} else {
			$("#login_message").html("请先登录。").css("display", "block");
			showLoginDiv(true);
		}
	}
	function checkLoginTo1(url) {
		if(typeof(_gaq)!="undefinde"&&_gaq!=null){
		_gaq.push(['_trackEvent', ' Entry', 'Order',' Index_Header']);//谷歌统计 添加事件追踪统计
		}
		forwordURL = url;
		if(hasLogin()) {
			window.location.href = url;
		} else {
			$("#login_message").html("请先登录。").css("display", "block");
			showLoginDiv(true);
		}
	}
	
	function toUserCenter() {
		checkLoginTo(baselocation + '/cus/cuslimit!toUserCenter.action');
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
	
	//黑底效果
	function lockingWindow(){ 
		document.getElementById("web_top_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight + "px";
		$("#web_top_black").fadeIn();
		//document.getElementById("web_top_black").style.display = "block"; 
	}


	function getSubjectNameById(subjectId) {
		if(subjectId == 1) {
			return "人力资源管理师课程";
		} else if(subjectId == 2) {
			return "心理咨询师课程";
		} else if(subjectId == 3) {
			return "会计资格证课程";
		} else if(subjectId == 5) {
			return "司法考试课程";
		} else if(subjectId == 7) {
			return "注册会计师课程";
		} else if(subjectId == 8) {
			return "证券从业课程";
		} else if(subjectId == 9){
			return "建造师考试课程" ;
		}else if(subjectId == 10){
			return "高级会计师课程" ;
		}else if(subjectId == 11){
			return "公务员课程" ;
		}else if(subjectId == 12){
			return "经济师考试课程" ;
		}else if(subjectId == 13){
			return "全国研究生统一入学考试" ;
		}else if(subjectId == 14){
			return "初级会计职称课程" ;
		}else if(subjectId == 15){
			return "中级会计职称课程" ;
		}else if(subjectId == 16){
			return "二级建造师考试课程" ;
		}else if(subjectId == 17){
			return "监理师课程" ;
		}else if(subjectId == 18){
			return "造价师课程" ;
		}else if(subjectId == 19){
			return "GCT考试" ;
		}else if(subjectId == 20){
			return "自考课程" ;
		}else if(subjectId == 21){
			return "临床执业医师" ;
		}else if(subjectId == 22){
			return "企业法律顾问" ;
		}else if(subjectId == 23){
			return "成考课程" ;
		}else if(subjectId == 24){
			return "注册税务师课程" ;
		}else if(subjectId == 25){
			return "报关员考试" ;
		}else if(subjectId == 26){
			return "地方公务员考试" ;
		}else if(subjectId == 27){
			return "职称英语课程" ;
		}else if(subjectId == 28){
			return "期货从业资格考试" ;
		}
		return "";
	}
	
	//试听课程
	function log_suc_trialLesson(){
		window.open(baselocation + "/cus/cuslimit!toUserCenter.action");
	}
	//测试中心
	function log_suc_testType(){
		window.open(baselocation + "/exam/qstManager!getExampaperAnalysisDTO.action");
	}
	//测试中心
	function log_suc_examPractice(){
		window.open(baselocation + "/exam/qstManager!getExampaperAnalysisDTO.action");
	}
	//讨论小组
	function log_suc_Discuss(){
		window.open(baselocation + "/dis/discussion!toDisHomepage.action");
	}
	//帮助页面跳转
	function openbupagfromhelp(){
		var usersubect =getSubjectIndexFlag(getCookie("subjectId"))
		if (usersubect==null || usersubect==""){
			window.open(baselocation);
		}else{
			window.open(baselocation + "/"+usersubect);
		}
	}
	//显示注册时购买的课程和试听课程
	function disBuyAndCouser(subectidd){
		if (subectidd==""){
			var regsubid=7;
		}else{
			var regsubid=subectidd;
		}
		
		//购买课程显示	
		getSellWayId(regsubid,"9");
		
		if (regsubid==1){ //人力资源管理师
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	$("#successListen_list4").show();
		 	
			$("#successListen_name1").html("二级基础知识");
			$("#successListen_name2").html("二级专业技能");
			$("#successListen_name3").html("三级基础知识");
			$("#successListen_name4").html("三级专业技能");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(244)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(244)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(244)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen4").html("<a href='javascript:tolessionforreg(244)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示
			 
		}else if (regsubid==2){//心理咨询师
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("二级专业技能");
			$("#successListen_name2").html("三级专业技能");
			$("#successListen_name3").html("三级基础知识");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(241)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(241)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(241)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			
			//试听课程显示			
		}else if (regsubid==3){//会计资格证
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("电算化课程");
			$("#successListen_name2").html("财经法规课程");
			$("#successListen_name3").html("会计基础课程");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(246)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(246)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(246)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			
			//试听课程显示			
		}else if (regsubid==5){//司法考试
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("国际私法");
			$("#successListen_name2").html("商法、经济法");
			$("#successListen_name3").html("民法 行政法");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(245)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(245)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(245)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示
		}else if (regsubid==7){//注册会计师
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("公司战略与风险管理");
			$("#successListen_name2").html("审计课程");
			$("#successListen_name3").html("经济法课程");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(238)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(238)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(238)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==8){//证券从业
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	$("#successListen_list4").show();
		 	$("#successListen_list5").show();
		 	
			$("#successListen_name1").html("证券交易");
			$("#successListen_name2").html("证券发行与承销");
			$("#successListen_name3").html("证券基础知识");
			$("#successListen_name4").html("证券投资基金");
			$("#successListen_name5").html("证券投资分析");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(243)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(243)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(243)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen4").html("<a href='javascript:tolessionforreg(243)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen5").html("<a href='javascript:tolessionforreg(243)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==9){//建造师考试课程
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("建筑工程管理与实务");
			$("#successListen_name2").html("机电工程管理与实务");
			$("#successListen_name3").html("建设工程项目管理");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(237)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(237)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(237)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==10){//高级会计师
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("高会财务战略");
			$("#successListen_name2").html("高会开篇导读");
			$("#successListen_name3").html("高会考试概况与复习方法介绍");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(239)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(239)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(239)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==11){//公务员
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("申论");
			$("#successListen_name2").html("数量关系数学运算");
			$("#successListen_name3").html("判断推理类比推理");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(240)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(240)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(240)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==12){//经济师考试课程
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("中级房地产专业知识与实务");
			$("#successListen_name2").html("中级经济基础－经济基础知识");
			$("#successListen_name3").html("初级人力资源专业知识与实务");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(242)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(242)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(242)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==13){//研究生统一入学考试
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("考研 英语");
			$("#successListen_name2").html("考研 政治");
			$("#successListen_name3").html("考研 数学");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(247)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(247)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(247)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示
		}else if (regsubid==14){//助理会计师
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	
		 	
			$("#successListen_name1").html("初级会计实务");
			$("#successListen_name2").html("经济法基础");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(266)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(266)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示
		}else if (regsubid==15){//中级会计资格证
			
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("中级会计实务");
			$("#successListen_name2").html("经济法");
			$("#successListen_name3").html("中级财务管理");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(267)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(267)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(267)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示
		}else if (regsubid==16){//二级建造师
			
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("建筑工程经济");
			$("#successListen_name2").html("机电工程管理与实务");
			$("#successListen_name3").html("市政公用工程管理与实务");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(325)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(325)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(325)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示
		}else if (regsubid==17){//监理工程师
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("建设工程监理案例分析");
			$("#successListen_name2").html("建设工程监理基本理论与相关法规");
			$("#successListen_name3").html("建设工程合同管理");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(409)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(409)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(409)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==18){//造价工程师
			//试听课程显示
			$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("工程造价计价与控制");
			$("#successListen_name2").html("工程造价案例分析");
			$("#successListen_name3").html("建设工程技术与计量（安装）");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(440)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(440)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(440)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示	
		}else if (regsubid==19){//工程硕士
			//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	$("#successListen_list4").show();
		 	
			$("#successListen_name1").html("数学");
			$("#successListen_name2").html("英语");
			$("#successListen_name3").html("语文");
			$("#successListen_name4").html("逻辑");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(644)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(644)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(644)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen4").html("<a href='javascript:tolessionforreg(644)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");

			//试听课程显示
		}else if (regsubid==20){//自考
			//试听课程显示
			$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("会计本科- 中国近现代史纲要");
			$("#successListen_name2").html("行政管理本科-社会学概论");
			$("#successListen_name3").html("法律专科-中国法制史");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(524)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(524)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(524)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示

		}else if (regsubid==21){//临床执业医师
			//试听课程显示
			//试听课程显示

		}else if (regsubid==22){//法律顾问考试
			//试听课程显示
			$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("综合法律知识");
			$("#successListen_name2").html("企业管理知识");
			$("#successListen_name3").html("企业法律顾问实务");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(528)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(528)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(528)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示

		}else if (regsubid==23){//成人高考
			//试听课程显示   
			$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("政治");
			$("#successListen_name2").html("数学");
			$("#successListen_name3").html("英语");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(526)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(526)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(526)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示

		}else if (regsubid==24){//注册税务师
			//试听课程显示
			$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("税务代理实务");
			$("#successListen_name2").html("税收相关法律");
			$("#successListen_name3").html("财务与会计");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(527)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(527)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(527)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示

		}else if (regsubid==25){//报关员
			//试听课程显示
			$("#successListen_list1").show();
		 	
			$("#successListen_name1").html("报关员");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(529)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示

		}else if (regsubid==26){//地方公务员
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("申论");
			$("#successListen_name2").html("数量关系数学运算");
			$("#successListen_name3").html("判断推理类比推理");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(240)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(240)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(240)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==27){//职称英语
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	$("#successListen_list3").show();
		 	
			$("#successListen_name1").html("理工类");
			$("#successListen_name2").html("综合类");
			$("#successListen_name3").html("卫生类");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(581)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(581)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen3").html("<a href='javascript:tolessionforreg(581)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示			
		}else if (regsubid==28){//期货从业资格证考试
		 	//试听课程显示
		 	$("#successListen_list1").show();
		 	$("#successListen_list2").show();
		 	
			$("#successListen_name1").html("法律法规");
			$("#successListen_name2").html("基础知识");
			
			$("#successListen_list_Listen1").html("<a href='javascript:tolessionforreg(652)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			$("#successListen_list_Listen2").html("<a href='javascript:tolessionforreg(652)'><img alt='立刻试听' src='"+importURL +"/images/web/public/logRegPic/success_33.jpg' /></a>");
			//试听课程显示		
		}
		
		
	}
	
	//注册成功 "推荐购买"
		function getSellWayId(subjectId,disPosition){
			$.ajax({
					url : baselocation+"/cou/sellwayweb!getSellWayDisproList.action",
					type : "post",
					data : {
					     "sellWay.subjectId" : subjectId,
					     "sellWay.disPosition" : disPosition
					},
					dataType : "json",
					cache : false,
					success : function(result) {
						//显示 
						if(result.entity == null){
							
						}else{
							 var i = 1;
							 $.each(result.entity,function(name,value){
							 	$("#successBuy_list"+i+"").show();
							 	
							 	//var tempSellName = value.sellName.substr(0,12);
							 	//$('#successBuy_courseName'+i+'').html(''+tempSellName+'');
							 	
							 	$("#successBuy_courseName"+i+"").html(""+value.sellName+"");
							 	if(subjectId==11){
						 			$("#successBuy_list_buy"+i+"").html("<strong class='f22 pL10 fYaHei fWhite fLeft'>"+"￥"+""+value.sellPrice+""+"</strong><a href="+"javascript:BuyNowSign('"+value.sellId+"',1) class='fRight fBold'>立刻购买</a>");
							 	}else{
							 		$("#successBuy_list_buy"+i+"").html("<strong class='f22 pL10 fYaHei fWhite fLeft'>"+"￥"+""+value.sellPrice+""+"</strong><a href="+"javascript:BuyNow('"+value.sellId+"') class='fRight fBold'>立刻购买</a>");
								}
							i++;
							 });
						}
					},
					error : function(error) {
						//showErrorWin('error==fanxin==getSellWayId','');
				   }
			});
		}

	
     function tolessionforreg(courseId){
		if(courseId){
			window.open ('/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId+"&course.subjectId="+getCookie("subjectId")+"&veri=0", "_blank", "resizable");
		}
	}
     
     //注册后分配机会
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
 	
 	
 		var loginMessageFlag = "new3";
	function showRegisterPage() {
 
 	   window.location.href="http://highso.org.cn/register";
 	   if(typeof(_gaq)!="undefinde"&&_gaq!=null){
			_gaq.push(['_trackEvent', 'Entry', 'Register','Index_Header']); //谷歌统计 添加事件追踪统
} 	}
 	
 	function showLoginPage() {
 	
 		window.location.href="http://highso.org.cn/login";
 		if(typeof(_gaq)!="undefinde"&&_gaq!=null){
	_gaq.push(['_trackEvent', ' Entry', 'Login',' Index_Header']); //谷歌统计 添加事件追踪统
}
	
 	}

/** 来源统计处理函数。 Author: ZhengQiang */
function WebFromAgentAnalysis() {
	/** 项目路径 */
	this.webPaths = {
		'sf': [5, '司法考试'],
		'sf01': [5, '司法考试'],
		'sf02': [5, '司法考试'],
		'fl': [22, '法律顾问'],
		'xl': [2, '心理咨询师'],
		'kjz': [3, '会计资格证'],
		'cpa': [7, '注册会计师'],
		'zlkjs': [14, '初级会计职称'],
		'sccjkjzc': [14, '初级会计职称'],
		'kjs': [15, '中级会计职称'],
		'gk': [10, '高级会计职称'],
		'zs': [24, '注册税务师'],
		'jz': [9, '一级建造师'],
		'jz02': [16, '二级建造师'],
		'zjs': [18, '造价工程师'],
		'jl': [17, '监理工程师'],
		'gwy': [11, '公务员考试'],
		'rl': [1, '人力资源师'],
		'zq': [8, '证券从业考试'],
		'qh': [28, '期货'],
		'jjs': [12, '经济师'],
		'yh': [31, '银行从业考试'],
		'ky': [13, '考研'],
		'zk': [20, '自考'],
		'ck': [23, '成考'],
		'ycxl': [30, '远程学历'],
		'zcyy': [27, '职称英语'],
		'bg': [25, '报关员考试'],
		'gct': [19, 'GCT'],
		'yxs': [32, '网络营销师'],
		'ys': [21, '临床执业医师'],
		'jsj': [33, '职称计算机'],
		'register': [0, '首页']
	};
}

/** 获取项目路径 */
WebFromAgentAnalysis.prototype.getWebPath = function() {
	var loc = location.href.replace('http://');
	var startPos = loc.indexOf('/');
	var endPos = loc.indexOf('?');
	var path = '';
	if (startPos != -1) {
		if (endPos != -1) {
			path = loc.substring(startPos + 1, endPos);
		} else {
			path = loc.substring(startPos + 1);
		}
	}
	if (path.charAt(path.length - 1) == '/') {
		path = path.substring(0, path.length - 1);
	}
	return path;
};

WebFromAgentAnalysis.prototype.getDomain = function() {
	var suffix = new Array('.com.cn', '.net.cn', '.org.cn', '.gov.cn', '.com', '.cn', '.net', '.cc', '.org', '.info', '.biz', '.tv');
	var domain = document.domain;
	var tmpdomain = '';
	var i;
	for (i = 0; i < suffix.length; i++) {
		tmpdomain = suffix[i];
		if (domain.indexOf(tmpdomain) != -1) {
			domain = domain.replace(tmpdomain, '');
			domain = domain.substring(domain.lastIndexOf('.') + 1, domain.length);
			domain = domain + tmpdomain;
			break;
		}
	}
	return domain;
};

/** 执行统计 */
WebFromAgentAnalysis.prototype.analyse = function() {
	var webAgent = getParameter('webAgent');
	var webFrom = getParameter('webFrom');
	var webPath = this.getWebPath();
	var domain = this.getDomain();
	var lastVisitedTime = getCookie('lastVisitedTime');
	var subjectId = -1;// 其它

	// 请求路径中是否包含webAgent和webFrom参数
	if (webAgent != null && webFrom != null && '' != webAgent && '' != webFrom) {
		// 从请求路径中获取webPath，并验证webPath是否合法
		if (this.webPaths[webPath] != null) {
			subjectId = this.webPaths[webPath][0];
			// 防止重复刷新导致多次计数。
			if (lastVisitedTime != null) {
				return;
			}
			// 执行统计
			else {
				// lastVisitedTime在Cookie中的有效期为1天
				SetCookieOutTime("lastVisitedTime", webAgent,"d1");
				$.ajax({
					type: 'POST',
					url: baselocation + '/sys/webFromAgentLog!saveWebFromAgentLog.action',
					data: {
						'log.webAgent': webAgent,
						'log.webFrom': webFrom,
						'log.webPath': webPath,
						'log.domain': domain,
						'log.subjectId': subjectId
					},
					cache: false,
					dataType: 'json',
					success: function(data) {
					},
					error: function() {
					}
				});
			}
		}
	}
};