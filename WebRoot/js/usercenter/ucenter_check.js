//输入字数提示
$(document).ready(function() {
	$("#content").keyup(function(){
		var $maxtext=30;
	    var curr=$(this).val().length;
		var $surplusWord=$("#user_data_surplus_word");
		$surplusWord.text(curr);
			if($maxtext-curr>0){
				$surplusWord.html("&nbsp;" + ($maxtext-curr)).css("color","#FC8019");
			}else{
				$surplusWord.html("&nbsp;" + 0).css("color","#FC8019");
			};
	});
	$("#content").focus(function(){
		var $maxtext=30;
		var curr=$(this).val().length;
		if($maxtext-curr>0){
			$("#msgContent").html("还可以输入<em id='user_data_surplus_word'>&nbsp;"+($maxtext-curr)+"</em>个字");
		}else{
			$("#msgContent").html("还可以输入<em id='user_data_surplus_word'>&nbsp;"+0+"</em>个字");
		}
		
	});
});

function checkLength(obj,maxlength){
	if(obj.value.length > maxlength){
		obj.value = obj.value.substring(0,maxlength);
	}
}
function checkCusInfo(){
	$("#postPublic").click(function(){
		var content = $.trim($("#content").val());
		if(content.is("empty")){
			$("#msgContent").html("<em id='user_data_surplus_word'>写句个性签名吧……</em>");
			return false;
		}
		$(".user_data_btn").hide();
		$(".loading").show();
		$.ajax({
			url : baselocation + "/cus/cusweb!ajaxContent.action",
			data : {"content":content},
			type : "post",
			dataType : "json",
			cache : false,
			success : function(result) {
				if(result.returnMessage == "success"){
					$(".user_data_btn").show();
					$(".loading").hide();
					$("#content").val(result.jumpUrl);
					$("#showcontent").html(result.jumpUrl);
					$(".public").hide();
					$(".user_data_textAfter").show();
				}else{
					alert("系统出现异常,请稍后重试!");
				}
			},
			error : function(error) {
				//alert('error');
			}
		});
	});
	$("#postUpdate").click(function(){
		$(".public").show();
		$(".user_data_textAfter").hide();
	});
	$(".lr_look_more").click(function(){
		$("div.lr_look_list").slideUp();
	});
	$("#lr_look_btn").toggle(function() {
        $("div.lr_look_list").slideDown();
		$(this).addClass("lr_look_btn_open");
		return false;
    },function(){
		$("div.lr_look_list").slideUp();
		$(this).removeClass("lr_look_btn_open");
		return false;
	});
	getCusLastVideoRecord();
	
}
var newSellIndex = 0;
var newClickIndex = 0;
var newEvaluateIndex = 1;
/**
 * 用户商品最新数据
 * @param sellIds
 * @return
 */
function getNewSellRecord(sellIds){
	if(sellIds != null && typeof(sellIds) != undefined && sellIds !=""){
		var value = sellIds.substring(0,sellIds.length-1);
		var ids = value.split(",");
		if(ids == null || ids ==""){
			return;
		}
		$("#newContent").empty();
		for(var i=0;i<ids.length;i++){
			if(newSellIndex == 10){break;}
			getNewSellData(ids[i]);
		}
		
	}
}
/**
 * 用户评价Top
 * @param sellIds
 * @return
 */
function getEvaluateRecord(sellIds){
	if(sellIds != null && typeof(sellIds) != undefined && sellIds !=""){
		var value = sellIds.substring(0,sellIds.length-1);
		var ids = value.split(",");
		if(ids == null || ids ==""){
			return;
		}
		$("#evaluateContent").empty();
		for(var i=0;i<ids.length;i++){
			if(newEvaluateIndex == 10){break;}
			getNewEvaluateData(ids[i]);
		}
		
	}
}

function getNewEvaluateData(id){
	var jsonArray = new Array();
	$.ajax({
		url : baselocation + "/static/web/sellClick/"+id+"/index_1.shtml",
		type : "get",
		dataType : "json",
		cache : false,
		async:false,
		success : function(result){
			$.each(result,function(name,value){
				jsonArray.push(value);
				if(newEvaluateIndex == 10){
					showNewEvaluateData(jsonArray);
					return newEvaluateIndex;
				}
				newEvaluateIndex++;
			}); 
		}
	});
}

function showNewEvaluateData(value){
	dataSort(value);
	for(var i=0;i<value.length;i++){
		var obj = eval('(' + "{Date: new Date("+value[i].startTime.time+")}" + ')');  
		var count = (i +1);
		if(count>3){
			$("#evaluateContent").append("<tr><td class='tl'><em class='top_number'>"+(i+1)+"</em><a href='#' onclick='goToListenCourseKpoint("+value[i].courseId+","+value[i].subjectId+","+value[i].sellId+","+value[i].kpointId+");' title='"+value[i].subjectName+"+"+value[i].videoName+"'>"+value[i].subjectName+">"+value[i].videoName+"</a></td><td>"+value[i].teacherName+"</td><td>"+(value[i].count*7)+"</td><td>"+new Date(obj["Date"]).format("yyyy-MM-dd")+"</td>");
		}else{
			$("#evaluateContent").append("<tr><td class='tl'><em class='top_number"+count+"'>"+(i+1)+"</em><a href='#' onclick='goToListenCourseKpoint("+value[i].courseId+","+value[i].subjectId+","+value[i].sellId+","+value[i].kpointId+");' title='"+value[i].subjectName+"+"+value[i].videoName+"'>"+value[i].subjectName+">"+value[i].videoName+"</a></td><td>"+value[i].teacherName+"</td><td>"+(value[i].count*7)+"</td><td>"+new Date(obj["Date"]).format("yyyy-MM-dd")+"</td>");
		}
	}
}

function dataSort(value){
	var t;
	for(var i=1;i<value.length;i++){
		for(var j = 1; j < value.length; j++) {
			if(i%2 ==0){
				t = value[i];
				value[i] = value[j];
				value[j] = t;
			}
		}
	}
}

function getNewSellData(id){
	$.ajax({
		url : baselocation + "/static/web/sellNew/"+id+"/index_1.shtml",
		type : "get",
		dataType : "json",
		cache : false,
		async:false,
		success : function(result){
			$.each(result,function(name,value){
				if(newSellIndex == 10){return newSellIndex;}
				var obj = eval('(' + "{Date: new Date("+value.startTime.time+")}" + ')');  
				var count = (name +1);
				if(count>3){
					$("#newContent").append("<tr><td class='tl'><em class='top_number'>"+(name+1)+"</em><a href='#' onclick='goToListenCourseKpoint("+value.courseId+","+value.subjectId+","+value.sellId+","+value.kpointId+");' title='"+value.subjectName+"+"+value.videoName+"'>"+value.subjectName+">"+value.videoName+"</a></td><td>"+value.teacherName+"</td><td>"+(value.count*7)+"</td><td>"+new Date(obj["Date"]).format("yyyy-MM-dd")+"</td>");
				}else{
					$("#newContent").append("<tr><td class='tl'><em class='top_number"+count+"'>"+(name+1)+"</em><a href='#' onclick='goToListenCourseKpoint("+value.courseId+","+value.subjectId+","+value.sellId+","+value.kpointId+");' title='"+value.subjectName+"+"+value.videoName+"'>"+value.subjectName+">"+value.videoName+"</a></td><td>"+value.teacherName+"</td><td>"+(value.count*7)+"</td><td>"+new Date(obj["Date"]).format("yyyy-MM-dd")+"</td>");
				}
				newSellIndex++;
			}); 
		}
	});
}

/**
 * 用户商品点击数据
 * @param sellIds
 * @return
 */
function getClickSellRecord(sellIds){
	if(sellIds != null && typeof(sellIds) != undefined && sellIds!=""){
		var value = sellIds.substring(0,sellIds.length-1);
		var ids = value.split(",");
		if(ids == null || ids ==""){
			return;
		}
		$("#topContent").empty();
		for(var i=0;i<ids.length;i++){
			if(newClickIndex ==10){break;}
			getClickSellData(ids[i]);
		}
	}
}

function getClickSellData(id){
	$.ajax({
		url : baselocation + "/static/web/sellClick/"+id+"/index_1.shtml",
		type : "get",
		dataType : "json",
		cache : false,
		async:false,
		success : function(result){
			$.each(result,function(name,value){
					if(newClickIndex == 10){return false;}
					var obj = eval('(' + "{Date: new Date("+value.startTime.time+")}" + ')');  
					var count = (name +1);
					if(count>3){
						$("#topContent").append("<tr><td class='tl'><em class='top_number'>"+(name+1)+"</em><a href='#' onclick='goToListenCourseKpoint("+value.courseId+","+value.subjectId+","+value.sellId+","+value.kpointId+");' title='"+value.subjectName+"+"+value.videoName+"'>"+value.subjectName+">"+value.videoName+"</a></td><td>"+value.teacherName+"</td><td>"+(value.count*7)+"</td><td>"+new Date(obj["Date"]).format("yyyy-MM-dd")+"</td>");
					}else{
						$("#topContent").append("<tr><td class='tl'><em class='top_number"+count+"'>"+(name+1)+"</em><a href='#' onclick='goToListenCourseKpoint("+value.courseId+","+value.subjectId+","+value.sellId+","+value.kpointId+");' title='"+value.subjectName+"+"+value.videoName+"'>"+value.subjectName+">"+value.videoName+"</a></td><td>"+value.teacherName+"</td><td>"+(value.count*7)+"</td><td>"+new Date(obj["Date"]).format("yyyy-MM-dd")+"</td>");
					}
					newClickIndex++;
			}); 
		},
		error:function(error){
		}//
	});
}

function getCusLastVideoRecord(){
	$(".learn").show();
	$("#lr_gostudy_btn").hide();
	$("#lr_look_btn").hide();
	$.ajax({
		url : baselocation + "/cus/cusweb!ajaxVideoRecord.action",
		type : "post",
		dataType : "json",
		cache : true,
		success : function(result) {
			if(result.returnMessage == "success"){
				if(result.jumpUrl != ""){
					var data = eval("("+result.jumpUrl+")");
					$("#con").empty();
					$.each(data,function(i,value){
						if(i==0){
							var watchRecord = getCookie("watchRecord");
							if(watchRecord != null && $.trim(watchRecord).length > 0){
								obj = eval("(" + watchRecord +")");
								$("#first").empty();
								$("#first").append("《" + getSubjectNameById(obj.subjectId)+ "》" + obj.videoName + "[" + time_To_hhmmss(obj.watchTime) + "]");
								$("#lr_gostudy_btn").bind("click",function(){
									goToListenCourseKpoint(obj.courseId,obj.subjectId,obj.sellId,obj.kpointId);
								});
							}else{
								$("#first").empty();
								$("#first").append("《" + value.subjectName + "》" + value.videoName + "[" + new Date(value.endTime.replace(/-/g,"/").substring(0,value.endTime.length-2)).format("mm:hh:ss") + "]");
								$("#lr_gostudy_btn").bind("click",function(){
									goToListenCourseKpoint(obj.courseId,obj.subjectId,obj.sellId,obj.kpointId);
								});
							}
						}else{
							$("#con").append("<li class='record'><span class='lr_look_time'>"+new Date(value.startTime.replace(/-/g,"/").substring(0,value.startTime.length-2)).format("yyyy-MM-dd mm:hh:ss")+"</span>《"+value.subjectName+"》"+value.videoName+"  ["+new Date(value.endTime.replace(/-/g,"/").substring(0,value.endTime.length-2)).format("mm:hh:ss")+"] <a class='lr_look_gostudy' href='#' onclick='goToListenCourseKpoint("+value.courseId+","+value.subjectId+","+value.sellId+","+value.kpointId+");'>继续学习</a></li>");
						}
					});
					$(".learn").hide();
					$("#lr_gostudy_btn").show();
					$("#lr_look_btn").show();
				}else{
					$(".uc_lr_tt").empty();
					$(".uc_lr_tt").append("您最近没有学习课程!");
					$("#lr_gostudy_btn").hide();
					$("#lr_look_btn").hide();
				}
			}else{
				alert("系统出现异常,请稍后重试!");
			}
		},
		error : function(error) {
			//alert('error');
		}
	});
}

validaReg = {
		empty:/^.{0}$/
};

/**
 * 判断长度
 */
String.prototype.isLen=function(min,max){
	return (this.length >= min && this.length <= max);
};

String.prototype.isNotLen=function(min,max){
	return !this.isLen(min,max);
};

String.prototype.is = function(validaType){
	if(validaType instanceof RegExp){
		return validaType.test(this);
	}
	if(validaReg[validaType]){
		return validaReg[validaType].test(this);
	}else{
		alert("未找到 : " + validaType + " 对应的表达式");
		return false;
	}
};
Date.prototype.format = function(format){        
	var o = {        
	"M+" : this.getMonth()+1, //month        
	"d+" : this.getDate(),    //day        
	"h+" : this.getHours(),   //hour        
	"m+" : this.getMinutes(), //minute        
	"s+" : this.getSeconds(), //second        
	"q+" : Math.floor((this.getMonth()+3)/3),  //quarter        
	"S" : this.getMilliseconds() //millisecond        
	};        
	if(/(y+)/.test(format)) 
	format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));        
	for(var k in o)if(new RegExp("("+ k +")").test(format))        
	format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));  
	return format;    
};

$(document).ready(function() {
         $("div.uc_tabcon:gt(0)").hide();
 		$("ul.uc_tab a:first").addClass("current");
 		$("ul.uc_tab a").click(function() {
             $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
 				$("div.uc_tabcon:eq("+$("ul.uc_tab a").index(this)+")").show().siblings("div.uc_tabcon").hide();
 				return false;
         });
});

/**获取Cookies方法**/
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

function time_To_hhmmss(seconds){
	   var hh;
	   var mm;
	   var ss;
	   //传入的时间为空或小于0
	   if(seconds==null||seconds<0){
	       return;
	   }
	   //得到小时
	   hh=seconds/3600|0;
	   seconds=parseInt(seconds)-hh*3600;
	   if(parseInt(hh)<10){
	          hh="0"+hh;
	   }
	   //得到分
	   mm=seconds/60|0;
	   //得到秒
	   ss=parseInt(seconds)-mm*60;
	   if(parseInt(mm)<10){
	         mm="0"+mm;    
	   }
	   if(ss<10){
	       ss="0"+ss;      
	   }
	   return hh+":"+mm+":"+ss;
}



 

 