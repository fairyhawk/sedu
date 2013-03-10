		var loginMessageFlag = "old";
	$().ready(function() {
		$("img").lazyload({
			effect : "fadeIn"
		});
		
		checkRegisterResult();
		checkBack();
		checkRemeberMe();
		initValidate();
 	});
 	
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
			//艾德思奇统计：注册成功。
			//adSageRegisterSuccess(getUserId());
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
	        		minlength : 6,
	        	},
	        	"customer.cusName" : {
	        		required : true,
	        		maxlength : 20
	        	},
	        	"customer.mobile" : {
	        		required : true,
	        		mobile : true,
	        		maxlength : 20,
	        		remote : {
		                data: {
	                    	'customer.mobile': function () {
	                    		return $("input[id=regMobile]").val();
	                    	}
		                },
		                async : false,
		                url : baselocation + "/cus/cusweb!checkmoblie.action",
		                type : "post"
	                }
	        	},
	        	"customer.subjectId" : {
	        		min : 1
	        	},
	        	"randomCode" : {
	        	    required : true,
	        	    maxlength : 4,
	        	    remote : {
		                data: {
	                    	'randomCode': function () {
	                    		return $("input[id=regRandomcode]").val();
	                    	}
		                },
		                async : false,
		                url : baselocation + "/cus/cusweb!checkRandomCode.action",
		                type : "post"
	                }
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
	        		email : "邮箱格式不正确，请重新填写"
	        	},
	        	"customer.cusName" : {
	        		required : "请输入昵称",
	        		maxlength : "昵称不能超过20个字符"
	        	},
	        	"customer.cusPwd" : {
	        		required : "密码必须填写",
	        		maxlength : "密码不能低于6位，不能多于20",
	        		minlength : "密码不能低于6位，不能多于20"
	        		
	        	},
	        	"cusPwdConfirm" : {
	        		equalTo : "两次输入密码不一致"
	        	},
	        	"customer.mobile" : {
	        		required : "请输入手机号码",
	        		mobile : "请输入正确的手机号码",
	        		remote:"该手机已经注册过"
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
	    		
	            if ( element.is(":radio") ){
	                error.appendTo( element.parent().next().next() );
	            }else if ( element.is(":checkbox") ){
	            	error.appendTo(element.parent().find("span")).css("color", "red");
	            }else{
	            	error.appendTo(element.parent().parent().next().find("td:last").html("")).css("color", "red");
	            }
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
 		return params;
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
	

 	