
	$().ready(function () {
		var forgotMessage = getParameter("forgotMessage");
		if(forgotMessage == "forgotSuccessMail") {
			$("#message_email").html("邮件已发送，请尽快查收");
		} else if (forgotMessage == "forgotFailEmail") {
			$("#message_email").html("该注册邮箱不存在");
		}
		if(forgotMessage == "forgotSuccessMobile"){
			$("message_mobile").html("短信已发送，请尽快查收");
		}else if(forgotMessage == "forgotFailMobile"){
			$("message_mobile").html("该手机号不存在");
		}
	});
	
	function forgotPwdEmail () {
		var code=$("#regRandomcode2").val();
		if(code===null || code===""){
			$("#email_Randomcode").html("请输入验证码!");
					return;
		}else{
	    getEcode();
		$.ajax({
			url : baselocation + "/cus/cusweb!getPwdByEmail.action",
			data : {
				"customer.email" : $("#forgot_email").val(),
				"randomCode" : $("#regRandomcode2").val()
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if(result.returnMessage == "forgotSuccessMail") {
					  showEmail();
					  change();
				} else if (result.returnMessage == "forgotFailMail") {
					$("#message_email").html("该注册邮箱不存在，请核实;");
				}else if (result.returnMessage == "failEmail"){
					$("#message_email").html("请输入邮箱");
				}else if(result.returnMessage == "err.randCode"){
					$("#email_Randomcode").html("验证码错误!");
					changecode();
					}
			},
			error : function(error) {alert(error);}
		});
		}
		}
	
	function showEmail(){
		
		$("#retrieve_pwd").hide();
		$("#getmail").show();
	
	}
	
	function forgotPwdMobile () {
		var code=$("#regRandomcode").val();
		if(code===null||code===""){
			$("#mobile_Randomcode").html("请输入验证码!");
					return;
		}else{
		  getMcode();
			$.ajax({
			url : baselocation + "/cus/cusweb!getPwdByMobile.action",
			data : {
				"customer.mobile" : $("#forgot_mobile").val(),
				"randomCode" : $("#regRandomcode").val(),
				"email2" : $("input:radio[name='email2']:checked").val()
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if(result.returnMessage == "forgotSuccessMobile") {
					showMobile();
					change();
				} else if (result.returnMessage == "forgotFailMobile") {
					$("#message_mobile").html("该手机号不存在");
				}else if (result.returnMessage == "failMobile"){
					$("#message_mobile").html("请输入手机号");
				}else if (result.returnMessage == "forgotManyMobile") {
				
					var emailList = result.entity;
					if(emailList!=null){
						var displayInfo="您的手机对应以下邮箱，请选择：<br>";	
							$.each(emailList,function(key,val){
							
								 displayInfo+="<input type='radio' id='radio"+key+"' name='email2' value = '"+val+"'/>"+val+"<br>"
								 	
					    	})
					}
					
					$("#message_mobile").html(displayInfo);
					
					//防止用户修改手机号和验证码
					$("#forgot_mobile").attr("readonly","readonly");
					$("#regRandomcode").attr("readonly","readonly");
					
					//$("#message_mobile").html("该手机号对应多个注册邮箱，请使用邮箱找回密码");
				}else if (result.returnMessage == "forgotMobile") {
					$("#message_mobile").html("该手机号格式不对");
				}else if(result.returnMessage == "err.randCode"){
					$("#mobile_Randomcode").html("验证码错误!");
					changecode();
					}
			},
			error : function(error) {alert(error);}
		});
		}
	}
	
	function showMobile(){
		
		$("#retrieve_pwd").hide();
		$("#getphone").show();

	}
	
	function showLogin(){
		
		$("#login_div").show();
		
	}
	
	
	function changecode(){
			window.setTimeout("location.reload(false)",3000);
			//$("#code").attr("src", baselocation + "/util/randomCode.action?random="+Math.random());
	}
	
	function change(){
			$("#code").attr("src", baselocation + "/util/randomCode.action?random="+Math.random());
	}
	
	function getEcode(){
		var ecode = $("#forgot_email").val();
		$("#getecode").html(ecode);
	}
	
	function getMcode(){
		var mcode = $("#forgot_mobile").val();
		$("#getmcode").html(mcode);
	}
	
	function twoforgotPwdEmail(){
		forgotPwdEmail ();
		alert("已发送");
		window.location.href=baselocation;
	}
	
	function twoforgotPwdMobile(){
		forgotPwdMobile ();
		alert("已发送");
		window.location.href=baselocation;
	}
	
	function myemail(){
		var mail = $("#forgot_email").val();
		var arry = mail.split("@");
		if( arry[1] == "163.com"){
			window.open("http://mail.163.com/");
		}else if (arry[1] == "126.com"){
			window.open("http://mail.126.com/");
		}else if (arry[1] == "qq.com"){
			window.open("http://mail.qq.com/");
		}else if (arry[1] == "sina.com"){
			window.open("http://mail.sina.com.cn/");
		}else if (arry[1] == "sohu.com"){
			window.open("http://mail.sohu.com/");
		}else if (arry[1] == "Gmail.com"){
			window.open("http://mail.Gmail.com/");
		}else if (arry[1] == "yahoo.com.cn"){
			window.open("http://mail.cn.yahoo.com//");
		}else if (arry[1] == "yahoo.cn"){
			window.open("http://mail.cn.yahoo.com//");
		}else if (arry[1] == "139.com"){
			window.open("http://mail.139.com/");
		}else if (arry[1] == "yeah.net"){
			window.open("http://www.yeah.net/");
		}else if( arry[1] == "263.com"){
			window.open("http://mail.263.com/");
		}else if( arry[1] == "21cn.com"){
			window.open("http://mail.21cn.com/");
		}else if( arry[1] == "sogou.com"){
			window.open("http://mail.sogou.com/");
		}else if( arry[1] == "189.cn"){
			window.open("http://webmail1.189.cn/webmail/");
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	