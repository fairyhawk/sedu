// JavaScript Document
var $hei=0;
$(window).scroll(function(){
	$hei = document.body.scrollHeight;
});
function gun(){
	$(".liuyanpop").css({top:document.documentElement.scrollTop+document.documentElement.clientHeight/2});
}
window.onscroll=gun;
$(function(){	
	var baselocation=top.location.hostname;
	$("#liuyanname").attr("value","请填写真实姓名或者昵称");
	$("#liuyanPhone").attr("value","请填写真实有效的手机号码");
	$("#liuyanPhone").attr("style","color: rgb(100, 100, 100);");
	$("#liuyanPhone").click(function(){
		if($(this).val()=="请填写真实有效的手机号码"){
			$("#liuyanPhone").attr("value","");			
			$("#liuyanPhone").attr("style",false);
		}
	});
	$("#liuyanPhone").blur(function(){
		if($(this).val()==""){
			$("#liuyanPhone").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanPhone02").attr("value","请填写真实有效的手机号码");			
		}
	});
	$("#liuyanname").click(function(){
		if($(this).val()=="请填写真实姓名或者昵称"){
			$("#liuyanname").attr("value","");			
			$("#liuyanname").attr("style",false);
		}
	});
	$("#liuyanname").blur(function(){
		if($(this).val()==""){
			$("#liuyanname").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanname").attr("value","请填写真实姓名或者昵称");			
		}
	});
		$("#liuyanSubmit").click(function(){			
			if(verify(1)){
				var name=$("#liuyanname").val();
				if(name=="请填写真实姓名或者昵称"){
					name="";
				}
				var phone=$("#liuyanPhone").val();
				$("#liuyanname").attr("value","");	
				$("#liuyanPhone").attr("value","");
				//alert(document.body.scrollWidth)
				$.ajax({
// 				url : baselocation + "/crm/crmChance!doAfterReg.action",
					url:"/crm/crmChance!verifyMessage.action",
					data:{
						"realName":name,
						"phoneNumber":phone,
						"baselocation":baselocation,
						"subid":$("#liuyanSubjectId").val()	
					},
					type : "post",
					cache : false,
					success : function(result) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 						
						if(result=="alreadySuccess"){
							$(".pop_message").append("您输入的电话号码已经定制");
						}
						if(result=="Success"){
							$(".pop_message").append("您的留言已成功提交！");
						}
					},
					error : function(error) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'>未定制成功，请稍后重试！</div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 
					}
				});				
			}
		});
		var j=0
		if($("#liuyanSubjectId").val()==7){
			j=8;			
		}
		if($("#liuyanSubjectId").val()==5){
			j=6;			
		}
		
		
	$("#liuyanname02").attr("value","请填写真实姓名或者昵称");
	$("#liuyanPhone02").attr("value","请填写真实有效的手机号码");
	$("#liuyanPhone02").attr("style","color: rgb(100, 100, 100);");
	$("#liuyanPhone02").click(function(){
		if($(this).val()=="请填写真实有效的手机号码"){
			$("#liuyanPhone02").attr("value","");			
			$("#liuyanPhone02").attr("style",false);
		}
	});
	$("#liuyanPhone02").blur(function(){
		if($(this).val()==""){
			$("#liuyanPhone02").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanPhone02").attr("value","请填写真实有效的手机号码");			
		}
	});
	$("#liuyanname02").click(function(){
		if($(this).val()=="请填写真实姓名或者昵称"){
			$("#liuyanname02").attr("value","");			
			$("#liuyanname02").attr("style",false);
		}
	});
	$("#liuyanname02").blur(function(){
		if($(this).val()==""){
			$("#liuyanname02").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanname02").attr("value","请填写真实姓名或者昵称");			
		}
	});
		$("#liuyanSubmit02").click(function(){			
			if(verify(2)){
				var name=$("#liuyanname02").val();
				if(name=="请填写真实姓名或者昵称"){
					name="";
				}
				var phone=$("#liuyanPhone02").val();
				$("#liuyanname02").attr("value","");	
				$("#liuyanPhone02").attr("value","");
				//alert(document.body.scrollWidth)
				$.ajax({
// 				url : baselocation + "/crm/crmChance!doAfterReg.action",
					url:"/crm/crmChance!verifyMessage.action",
					data:{
						"realName":name,
						"phoneNumber":phone,
						"baselocation":baselocation,
						"subid":$("#liuyanSubjectId").val()	
					},
					type : "post",
					cache : false,
					success : function(result) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 						
					
	
	if(result=="alreadySuccess"){
							$(".pop_message").append("您输入的电话号码已定制短信");
						}
						if(result=="Success"){
							$(".pop_message").append("您的留言已成功提交！");
						}
					},
					error : function(error) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'>未定制成功，请稍后重试！</div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 
					}
				});				
			}
		});

		
			
	$("#liuyanname03").attr("value","请填写真实姓名或者昵称");
	$("#liuyanPhone03").attr("value","请填写真实有效的手机号码");
	$("#liuyanPhone03").attr("style","color: rgb(100, 100, 100);");
	$("#liuyanPhone03").click(function(){
		if($(this).val()=="请填写真实有效的手机号码"){
			$("#liuyanPhone03").attr("value","");			
			$("#liuyanPhone03").attr("style",false);
		}
	});
	$("#liuyanPhone03").blur(function(){
		if($(this).val()==""){
			$("#liuyanPhone03").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanPhone03").attr("value","请填写真实有效的手机号码");			
		}
	});
	$("#liuyanname03").click(function(){
		if($(this).val()=="请填写真实姓名或者昵称"){
			$("#liuyanname03").attr("value","");			
			$("#liuyanname03").attr("style",false);
		}
	});
	$("#liuyanname03").blur(function(){
		if($(this).val()==""){
			$("#liuyanname03").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanname03").attr("value","请填写真实姓名或者昵称");			
		}
	});
		$("#liuyanSubmit03").click(function(){			
			if(verify(3)){
				var name=$("#liuyanname03").val();
				if(name=="请填写真实姓名或者昵称"){
					name="";
				}
				var phone=$("#liuyanPhone03").val();
				$("#liuyanname03").attr("value","");	
				$("#liuyanPhone03").attr("value","");
				//alert(document.body.scrollWidth)
				$.ajax({
// 				url : baselocation + "/crm/crmChance!doAfterReg.action",
					url:"/crm/crmChance!verifyMessage.action",
					data:{
						"realName":name,
						"phoneNumber":phone,
						"baselocation":baselocation,
						"subid":$("#liuyanSubjectId").val()	
					},
					type : "post",
					cache : false,
					success : function(result) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 						
						if(result=="alreadySuccess"){
							$(".pop_message").append("您输入的电话号码已经定制");
						}
							
						if(result=="Success"){
							$(".pop_message").append("您的留言已成功提交！");
						}
					},
					error : function(error) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'>未定制成功，请稍后重试！</div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 
					}
				});				
			}
		});
		
	$("#liuyanname04").attr("value","请填写真实姓名或者昵称");
	$("#liuyanPhone04").attr("value","请填写真实有效的手机号码");
	$("#liuyanPhone04").attr("style","color: rgb(100, 100, 100);");
	$("#liuyanPhone04").click(function(){
		if($(this).val()=="请填写真实有效的手机号码"){
			$("#liuyanPhone04").attr("value","");			
			$("#liuyanPhone04").attr("style",false);
		}
	});
	$("#liuyanPhone04").blur(function(){
		if($(this).val()==""){
			$("#liuyanPhone04").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanPhone04").attr("value","请填写真实有效的手机号码");			
		}
	});
	$("#liuyanname04").click(function(){
		if($(this).val()=="请填写真实姓名或者昵称"){
			$("#liuyanname04").attr("value","");			
			$("#liuyanname04").attr("style",false);
		}
	});
	$("#liuyanname04").blur(function(){
		if($(this).val()==""){
			$("#liuyanname04").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanname04").attr("value","请填写真实姓名或者昵称");			
		}
	});
		$("#liuyanSubmit04").click(function(){			
			if(verify(4)){
				var name=$("#liuyanname04").val();
				if(name=="请填写真实姓名或者昵称"){
					name="";
				}
				var phone=$("#liuyanPhone04").val();
				$("#liuyanname04").attr("value","");	
				$("#liuyanPhone04").attr("value","");
				//alert(document.body.scrollWidth)
				$.ajax({
// 				url : baselocation + "/crm/crmChance!doAfterReg.action",
					url:"/crm/crmChance!verifyMessage.action",
					data:{
						"realName":name,
						"phoneNumber":phone,
						"baselocation":baselocation,
						"subid":$("#liuyanSubjectId").val()	
					},
					type : "post",
					cache : false,
					success : function(result) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 						
						if(result=="alreadySuccess"){
							$(".pop_message").append("您输入的电话号码已经定制");
						}
						if(result=="Success"){
							$(".pop_message").append("您的留言已成功提交！");
						}
					},
					error : function(error) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'>未定制成功，请稍后重试！</div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 
					}
				});				
			}
		});
		
		$("#liuyanname05").attr("value","请填写真实姓名或者昵称");
	$("#liuyanPhone05").attr("value","请填写真实有效的手机号码");
	$("#liuyanPhone05").attr("style","color: rgb(100, 100, 100);");
	$("#liuyanPhone05").click(function(){
		if($(this).val()=="请填写真实有效的手机号码"){
			$("#liuyanPhone05").attr("value","");			
			$("#liuyanPhone05").attr("style",false);
		}
	});
	$("#liuyanPhone05").blur(function(){
		if($(this).val()==""){
			$("#liuyanPhone05").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanPhone05").attr("value","请填写真实有效的手机号码");			
		}
	});
	$("#liuyanname05").click(function(){
		if($(this).val()=="请填写真实姓名或者昵称"){
			$("#liuyanname05").attr("value","");			
			$("#liuyanname05").attr("style",false);
		}
	});
	$("#liuyanname05").blur(function(){
		if($(this).val()==""){
			$("#liuyanname05").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanname05").attr("value","请填写真实姓名或者昵称");			
		}
	});
		$("#liuyanSubmit05").click(function(){			
			if(verify(5)){
				var name=$("#liuyanname05").val();
				if(name=="请填写真实姓名或者昵称"){
					name="";
				}
				var phone=$("#liuyanPhone05").val();
				$("#liuyanname05").attr("value","");	
				$("#liuyanPhone05").attr("value","");
				//alert(document.body.scrollWidth)
				$.ajax({
// 				url : baselocation + "/crm/crmChance!doAfterReg.action",
					url:"/crm/crmChance!verifyMessage.action",
					data:{
						"realName":name,
						"phoneNumber":phone,
						"baselocation":baselocation,
						"subid":$("#liuyanSubjectId").val()	
					},
					type : "post",
					cache : false,
					success : function(result) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 						
						if(result=="alreadySuccess"){
							$(".pop_message").append("您输入的电话号码已经定制");
						}
						if(result=="Success"){
							$(".pop_message").append("您的留言已成功提交！");
						}
					},
					error : function(error) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'>未定制成功，请稍后重试！</div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 
					}
				});				
			}
		});
		

			$("#liuyanname06").attr("value","请填写真实姓名或者昵称");
	$("#liuyanPhone06").attr("value","请填写真实有效的手机号码");
	$("#liuyanPhone06").attr("style","color: rgb(100, 100, 100);");
	$("#liuyanPhone06").click(function(){
		if($(this).val()=="请填写真实有效的手机号码"){
			$("#liuyanPhone06").attr("value","");			
			$("#liuyanPhone06").attr("style",false);
		}
	});
	$("#liuyanPhone06").blur(function(){
		if($(this).val()==""){
			$("#liuyanPhone06").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanPhone06").attr("value","请填写真实有效的手机号码");			
		}
	});
	$("#liuyanname06").click(function(){
		if($(this).val()=="请填写真实姓名或者昵称"){
			$("#liuyanname06").attr("value","");			
			$("#liuyanname06").attr("style",false);
		}
	});
	$("#liuyanname06").blur(function(){
		if($(this).val()==""){
			$("#liuyanname06").attr("style","color: rgb(100, 100, 100);");
			$("#liuyanname06").attr("value","请填写真实姓名或者昵称");			
		}
	});
		$("#liuyanSubmit06").click(function(){			
			if(verify(6)){
				var name=$("#liuyanname06").val();
				if(name=="请填写真实姓名或者昵称"){
					name="";
				}
				var phone=$("#liuyanPhone06").val();
				$("#liuyanname06").attr("value","");	
				$("#liuyanPhone06").attr("value","");
				//alert(document.body.scrollWidth)
				$.ajax({
// 				url : baselocation + "/crm/crmChance!doAfterReg.action",
					url:"/crm/crmChance!verifyMessage.action",
					data:{
						"realName":name,
						"phoneNumber":phone,
						"baselocation":baselocation,
						"subid":$("#liuyanSubjectId").val()	
					},
					type : "post",
					cache : false,
					success : function(result) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 						
						if(result=="alreadySuccess"){
							$(".pop_message").append("您输入的电话号码已经定制");
						}
						if(result=="Success"){
							$(".pop_message").append("您的留言已成功提交！");
						}
					},
					error : function(error) {
						$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'>未定制成功，请稍后重试！</div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
						$(".mask_liuyan").css("height", $hei);
						$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 
					}
				});				
			}
		});
		
		
			$("#liuyanname07").attr("value","请填写真实姓名或者昵称");
		$("#liuyanPhone07").attr("value","请填写真实有效的手机号码");
		$("#liuyanPhone07").attr("style","color: rgb(100, 100, 100);");
		$("#liuyanPhone07").click(function(){
			if($(this).val()=="请填写真实有效的手机号码"){
				$("#liuyanPhone07").attr("value","");			
				$("#liuyanPhone07").attr("style",false);
			}
		});
		$("#liuyanPhone07").blur(function(){
			if($(this).val()==""){
				$("#liuyanPhone07").attr("style","color: rgb(100, 100, 100);");
				$("#liuyanPhone07").attr("value","请填写真实有效的手机号码");			
			}
		});
		$("#liuyanname07").click(function(){
			if($(this).val()=="请填写真实姓名或者昵称"){
				$("#liuyanname07").attr("value","");			
				$("#liuyanname07").attr("style",false);
			}
		});
		$("#liuyanname07").blur(function(){
			if($(this).val()==""){
				$("#liuyanname07").attr("style","color: rgb(100, 100, 100);");
				$("#liuyanname07").attr("value","请填写真实姓名或者昵称");			
			}
		});
			$("#liuyanSubmit07").click(function(){			
				if(verify(7)){
					var name=$("#liuyanname07").val();
					if(name=="请填写真实姓名或者昵称"){
						name="";
					}
					var phone=$("#liuyanPhone07").val();
					$("#liuyanname07").attr("value","");	
					$("#liuyanPhone07").attr("value","");
					//alert(document.body.scrollWidth)
					$.ajax({
	// 				url : baselocation + "/crm/crmChance!doAfterReg.action",
						url:"/crm/crmChance!verifyMessage.action",
						data:{
							"realName":name,
							"phoneNumber":phone,
							"baselocation":baselocation,
							"subid":$("#liuyanSubjectId").val()	
						},
						type : "post",
						cache : false,
						success : function(result) {
							$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
							$(".mask_liuyan").css("height", $hei);
							$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 						
							if(result=="alreadySuccess"){
								$(".pop_message").append("您输入的电话号码已经定制");
							}
							if(result=="Success"){
								$(".pop_message").append("您的留言已成功提交！");
							}
						},
						error : function(error) {
							$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'>未定制成功，请稍后重试！</div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
							$(".mask_liuyan").css("height", $hei);
							$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2); 
						}
					});				
				}
			});


	
});

function closeloginpop(num){
		num +="";
		if(num=="1"){
			$(".mask_liuyan").remove();
			$(".liuyanpop").remove();
		}
}
function verify(i){
	var phone=0;
	if(i==1){
	phone=$("#liuyanPhone").val();
	}else if(i==2){
		phone=$("#liuyanPhone02").val();
	}else if(i==3){
		phone=$("#liuyanPhone03").val();
	}else if(i==4){
		phone=$("#liuyanPhone04").val();
	}else if(i==5){
		phone=$("#liuyanPhone05").val();
	}else if(i==6){
		phone=$("#liuyanPhone06").val();
	}else if(i==7){
		phone=$("#liuyanPhone07").val();
	}
    if(Trim(phone).length!=0){
    	var tmp =/^1{1}[0-9]{10}$/;     //支持11位手机号码验证  
    	var flag=tmp.test(phone); 
    	if(!flag){  
    		$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
    		$(".mask_liuyan").css("height", $hei);
    		$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2);  
    		$(".pop_message").append("手机号输入不合法");
    		return false;  
    	}else{
    		return true;
    	}      	
    }else{
		$("body").append("<div class='mask_liuyan'></div><div class='liuyanpop'><div class='pop_message'></div><div class='liuyan_quedin'><input class='quedin_close' onclick='closeloginpop(1)' type='button' value='确 定' /></div></div>");
		$(".mask_liuyan").css("height", $hei);
		$(".liuyanpop").css("top",document.documentElement.scrollTop+document.documentElement.clientHeight/2);  
		$(".pop_message").append("手机号不能为空");
		return false;  
    }
}
function Trim(s) {
	return s.replace(/\s+$|^\s+/g,"");
}
