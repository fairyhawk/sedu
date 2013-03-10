		function saveSignUp(){
			if(validateFun()!=""){
				alert(validateFun());
				return false;
			}
			$.ajax({  
				url : "<%=contextPath%>/sys/signUp!addSignUp.action",  
				data : {"signUp.education" : $("#education").val(),
						"signUp.age"	: $("#age").val(),
						"signUp.phoneNumber" : $("#phoneNumber").val(),
						"signUp.subjectId" : $("#subjectId").val()},  // 参数  
				type : "post",  
				cache : false,  
				dataType : "json",  //返回json数据 
				error: function(){ 
					alert('error');      
				}, 
				success:function(result){
					if(result.entity=='success'){
						$("#education").val("");
						$("#age").val("");
						$("#phoneNumber").val("");
						alert("提交成功！");
					}
				} 
			});
		}
		function validateFun(){
			var message="";
			if($("#education").val()==""){
				message +="您需要填写您的学历！\n";
			}
			if($("#age").val()==""){
				message +="您需要填写您的年龄！\n";
			}
			if(/^1{1}[0-9]{10}$/.test($("#phoneNumber").val())==false)
			{
				message +="您需要填写您的手机号码！ \n";
			}
			return message;
		}