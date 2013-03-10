$().ready(function(){
	initValidate();
	initArea(1, 0);
	initArea($("#sel_province").find("option:eq(0)").val(), 1);
}); 
/**第一次点击清空文本**/
var flagSelect = true;
/**全局变量倒计时**/
var tsecond = 3;

function clearDescription(){
	if(flagSelect == true){
		$("#description").val("");
		flagSelect = false;
	}
}
/**验证表单**/ 
function validateForm() {
	if($("#feedForm").valid()) {
	var params = getRegParams();
	$("#feedForm")[0].reset();
	$.ajax({
	url : baselocation + "/fb/feedback!addFeed.action",
	data : params,
	type : "post",
	dataType : "json",
	cache : false,
	async : false,
	success : function(result) {
		if(result.returnMessage == "success"){
			showSuccessWin();
		}else{
			alert("留言失败请重新留言");
			location.href = baselocation; 
		}
	},
	error : function(error) {
		alert('error');
	}
	});
	}
	}

/**留言成功弹出小窗口**/
function showSuccessWin(){
	if($.browser.mozilla) { 
		$("#showMes").text("秒后页面跳转");
	}
    jQuery.blockUI({ 
    	message: $("#successInfo"), 
    	css: {
    		color:'#fff',
    		width: 300,
    		border:'3px solid #aaa',
    		cursor:"default"
    		},
    		overlayCSS: { opacity:'0.5',cursor:"default"}
    });
    setInterval("timerCharChange()",1000);//每一秒执行一次更改数字
}

/**倒计时数字更改**/
function timerCharChange(){
		$("#second").text(tsecond);
		tsecond = tsecond - 1;
		if(tsecond <= -1){
			closeWin();
		}
}

/**针对不同浏览器关闭窗口**/
function closeWin(){
	if($.browser.msie) { 
			window.opener=null;
			window.open("","_self");
			window.close();
		} 
		else if($.browser.safari) 
		{ 
			window.opener=null;
			window.open("","_self");
			window.close();
		} 
		else if($.browser.mozilla) 
		{ 
			location.href = baselocation;
		} 
		else if($.browser.opera) { 
			location.href = baselocation;
		} 
		else { 
			location.href = baselocation;
		}
}

/**得到表单参数**/
function getRegParams() {
	var params = $("#feedForm input,select,textarea").serialize();
	return params;
}

/**验证表单初始化**/
function initValidate() {
 		jQuery.validator.addMethod("telephone", function(value, element) {
 			var pattern = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
 			return this.optional(element) || pattern.test(value);
 			}); 
		
		$("#feedForm").validate({
			errorClass: "error",
	        rules: {
	        	"feedback.email" : {
	        		required : true,
	        		email : true,
	        		maxlength : 30
	        	},
	        	"feedback.qq" : {
	        		required : true,
	        		maxlength : 12
	        	},
	        	"feedback.mobile" : {
	        		required : true,
	        		telephone : true,
	        		maxlength : 20
	        	},
	        	"feedback.province" : {
	        		min : 1
	        	},
	        	"feedback.city" : {
	        		min : 1
	        	},
	        	"feedback.subject_id" : {
	        		min : 1
	        	},
	        	"feedback.sp" : {
	        		min : 1
	        	},
	        	"feedback.bandwidth" : {
	        		min : 1
	        	},
	        	"feedback.description" : {
	        		required : true,
	        		maxlength : 100
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
	        	}
	        },
	    	messages : {
	    		"feedback.email" : {			                
	        		required : "请填写邮箱",
	        		email : "请输入正确的邮箱地址",
	        		maxlength : "邮箱号码不能多于30"
	        	},
	        	"feedback.qq" : {
	        		required : "请输入QQ号码",
	        		maxlength : "QQ号码不可超过12位"
	        	},
	        	"feedback.mobile" : {
	        		required : "请输入电话号码",
	        		telephone : "请输入正确的电话号码",
	        		maxlength : "请输入正确的电话号码"
	        	},
	        	"feedback.province" : {
	        		min : "请选择省份"
	        	},
	        	"feedback.city" : {
	        		min : "请选择城市"
	        	},
	        	"feedback.subject_id" : {
	        		min : "请选择一个专业"
	        	},
	        	"feedback.sp" : {
	        		min : "请选择运营商"
	        	},
	        	"feedback.bandwidth" : {
	        		min : "请选择带宽"
	        	},
	        	"feedback.description" : {
	        		required : "问题描述必须填写",
	        		maxlength : "最多50个汉字或100个英文字母"
	        	},
	        	"randomCode":{
	        	    required:"请输入验证码",
	        	    maxlength:"验证码是4位",
	        	    remote:"验证码错误"
	        	}
	    	},
	    	errorPlacement: function(error, element) {
	    		error.appendTo(element.removeClass("error").parent().find("font"));
	    	}, 
	        success: function(label) {
	        	var ipt = label.parent().parent().prev().find("input[name=randomCode]")[0];
	        	if(typeof(ipt) != "undefined" && ipt != null) {
	        		return;
	        	}
	        	return;
	        }
		});
 }
/**城市字典初始化**/
function initArea(id, index){
	var parentId = 1;
	if(id != null && id != 0 && !isNaN(id)) {
		parentId = id;
	}
	$.ajax({
		url : baselocation + "/cus/areaWeb!getAreasByParentId.action",
	data : {
		"queryAreaCondition.parentId" : parentId
	},
	type : "post",
	dataType : "json",
	cache : false,
	async : false,
	success : function(result) {
	if(result == null || result.entity == null) {
		return;
	}
	var provinces = result.entity;
	var html = '';
	for(var i=0; i<provinces.length; i++) {
		html += "<option value='" + provinces[i].id + "'>" + provinces[i].areaName + "</option>";
	}
	if(index == 0) {
	$(html).appendTo("#sel_province");
	} else if(index == 1) {
	$("#sel_city").html("");
	$("<option value='0'>-请选择-</option>" + html).appendTo("#sel_city");
	} else {
	}
	},
	error : function(error) {
		alert('error'+error.responseText);
	}
	});
	} 
