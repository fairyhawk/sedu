
	function changeImg(picName) {
		$("#cus_photo").attr("src", baselocation + "static/images/photos/" + picName);
		$("input[name=customer.photo]").val(picName);
	}
	
	$().ready(function() {
		jQuery.validator.addMethod("mobile", function(value, element) {
			var pattern = /^1{1}[0-9]{10}$/;
			return this.optional(element) || pattern.test(value);
		});
		jQuery.validator.addMethod("userName", function(value, element) {
			var pattern = /^1{1}[0-9]{10}$/;
			return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
		});
		$("#updateForm").validate({
	        rules: {
	        	"customer.cusName" : {
	        		required : true,
	        		userName : true,
	        		maxlength : 20
	        	},
	        	"customer.mobile" : {
	        		required : true,
	        		mobile : true
	        	},
	        	"customer.qq" : {
	        		digits : true,
	        		maxlength : 20,
	        		minlength : 4
	        	},
	        	"customer.MSN" : {
	        		email : true
	        	}
	        },
	    	messages : {
	        	"customer.cusName" : {
	        		required : "请输入昵称",
	        		userName : "昵称只能包括中文字、英文字母、数字和下划线",
	        		maxlength : "昵称不能超过10个汉字或20个英文字母"
	        	},
	        	"customer.mobile" : {
	        		required : "请输入手机号码",
	        		mobile : "请输入正确的手机号码",
	        		maxlength : "手机号过长"
	        	},
	        	"customer.qq" : {
	        		digits : "请输入正确的QQ号码",
	        		maxlength : "请输入正确的QQ号码",
	        		minlength : "请输入正确的QQ号码"
	        	},
	        	"customer.MSN" : {
	        		email : "请输入正确的MSN地址",
	        		maxlength : "MSN不能超过30位"
	        	},
	        	"customer.address" : {
	        		maxlength : "联系地址不能超过30个汉字或60个英文字母"
	        	}
	    	},
	    	 errorPlacement: function(error, element) {
	            if ( element.is(":radio") )
	                error.appendTo( element.parent().next().next() );   
	            else if ( element.is(":checkbox") )   
	                error.appendTo ( element.next() );   
	            else 
	            	element.removeClass('error').parent().next().find("font:last").html(error.html());
	        }, 
	        success: function(label) { }
		});
		initBirthday();
		$("#pwdForm").validate({
	        rules: {
	        	"customer.cusPwd" : {
	        		required : true,
	        		maxlength : 20,
	        		minlength : 6
	        	},
	        	"newPwd" : {
	        		required : true,
	        		maxlength : 20,
	        		minlength : 6
	        	}
	        },
	    	messages : {
	        	"customer.cusPwd" : {
	        		required : "请输入当前密码",
	        		maxlength : "密码必须是6-20位",
	        		minlength : "密码必须是6-20位"
	        	},
	        	"newPwd" : {
	        		required : "请输入新密码",
	        		maxlength : "密码必须是6-20位",
	        		minlength : "密码必须是6-20位"
	        	},
	        	"newPwdConfirm" : {
	        		equalTo : "密码不一致"
	        	}
	    	},
	    	 errorPlacement: function(error, element) {
	            if ( element.is(":radio") )
	                error.appendTo( element.parent().next().next() );   
	            else if ( element.is(":checkbox") )   
	                error.appendTo( element.next() );   
	            else 
	            	element.removeClass('error').parent().next().find("font:last").html(error.html());
	        }, 
	        success: function(label) { }
		});
	});
	
	function initBirthday() {
		byear = byear==''?-1:byear;
		bmonth = bmonth==''?-1:bmonth;
		bday = bday==''?-1:bday;
		var date = new Date();
		for(var i=date.getFullYear(); i>=1900; i--) {
			if(i == byear) {
				$("<option value=" + i + " selected >" + i + "</option>").appendTo("#year");
			} else {
				$("<option value=" + i + ">" + i + "</option>").appendTo("#year");
			}
		}
		for(var i=1; i<=12; i++) {
			if(i == bmonth) {
				$("<option value=" + i + " selected >" + i + "</option>").appendTo("#month");
			} else {
				$("<option value=" + i + ">" + i + "</option>").appendTo("#month");
			}
		}
		var dayCount = getDayCount(byear, bmonth);
		setDay(dayCount, bday);
	}
	
	function getDayCount(year, month) {
		var dayCount = 30;
		switch(parseInt(month)) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8: 
			case 10:
			case 12:
				dayCount = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				dayCount = 30;
				break;
			case 2:
				if((year%4==0&&year%100!=0) || year%400==0) {
					dayCount = 29;
				} else {
					dayCount = 28;
				}
				break;
		}
		return dayCount;
	}
	
	function setDay(dayCount, day) {
		for(var i=1; i<=dayCount; i++) {
			if(i == day) {
				$("<option value=" + i + " selected >" + i + "</option>").appendTo("#day");
			} else {
				$("<option value=" + i + ">" + i + "</option>").appendTo("#day");
			}
		}
	}
	
	function changeDate() {
		var yearVal = $("#year").val();
		var monthVal = $("#month").val();
		var dayVal = $("#day").val();
		var dayCount = $("#day option:last").val();
		if(dayCount != getDayCount(yearVal, monthVal)) {
			$("#day").empty().append("<option value=-1>请选择</option>");
			setDay(getDayCount(yearVal, monthVal), dayVal);
		}
	}
	
	/*个人资料*/
 	function SwitchPre(h){
	 	for(i=0;i<3;i++){
			 document.getElementById("switchPD"+i).style.display="none";
			 document.getElementById("switchPD"+h).style.display="block";
		 	 document.getElementById("switchT"+i).style.color="#069";
		 	 document.getElementById("switchT"+h).style.color="#fff";
			 document.getElementById("switchPT"+i).style.background="url('" + importURL + "/images/usercenter/switchT_a1.gif')";
			 document.getElementById("switchPT"+h).style.background="url('" + importURL + "/images/usercenter/switchT_a2.gif')";
	 	}
	 }
	 
	 function checkFile() {
	 	if($("#newPhoto").val() == null || $("#newPhoto").val() == '') {
	 		$("#upload_message").html("请先选择图片");
	 	} else {
	 		document.tempPicForm.submit();
	 	}
	 }
	
	function updatePhoto() {
		if($("#photoPath").val() == "") {
			$("#save_message").html("请先上传图片");
		} else {
			document.photoForm.submit();
		}
	}