var ordersize=1;


//新鲜事开始
var ucLeftIndex = 12; // 导航索引
/*html编码处理*/
function HtmlDecode(text){
	return text.replace(/&amp;/g, '&').replace(/&quot;/g, '\"').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/(<br>)|(<br\t*\s*\/>)/g,'');
}
/*时间处理函数*/
function verifyTime(curr_time,str_curr_time){
	var up_date = new Date(curr_time);
	var curr_date = new Date();
	if((curr_date.getFullYear() - up_date.getFullYear()) == 0 && (curr_date.getMonth() - up_date.getMonth()) == 0){
		var diff_d = curr_date.getDate() - up_date.getDate();
		if(diff_d == 0){ // 今天
			var diff_h = curr_date.getHours() -  up_date.getHours();
			if(diff_h == 0){ // 同小时
				var diff_m = curr_date.getMinutes() - up_date.getMinutes();
				if(diff_m == 0){
					return "(" + (curr_date.getSeconds() - up_date.getSeconds()) + "秒前)";
				}else{
					return "(" + diff_m + "分钟前)";
				}
			}else if(diff_h <= 3){ // 3小时内
				return "(" + diff_h + '小时前)';
			}else{ // 3小时前
				return '(今天' + up_date.getHours() + ":" + up_date.getMinutes() + ")";
			}
		}else if(diff_d == 1){ // 昨天
			return '(昨天' + up_date.getHours() + ":" + up_date.getMinutes()+ ")";
		}else if(diff_d == 2){ // 前天
			return '(前天' + up_date.getHours() + ":" + up_date.getMinutes()+ ")";
		}else{
			return str_curr_time;
		}
	}else{
		return str_curr_time; 
	}
}
/*字符串转化为时间*/
function StringToDate(DateStr)   
{    
   
    var converted = Date.parse(DateStr);   
    var myDate = new Date(converted);   
    if (isNaN(myDate))   
    {    
        var arys= DateStr.split('-');   
        myDate = new Date(arys[0],--arys[1],arys[2]);   
    }   
    return myDate;   
}
/*时间相减*/
Date.prototype.DateDiff = function(strInterval, dtEnd) {    
    var dtStart = this;   
    if (typeof dtEnd == 'string')//如果是字符串转换为日期型   
    {    
        dtEnd = StringToDate(dtEnd);   
    }   
    switch (strInterval) {    
        case 's' :return parseInt((dtEnd.getTime() - dtStart.getTime()) / 1000);   
        case 'n' :return parseInt((dtEnd - dtStart) / 60000);   
        case 'h' :return parseInt((dtEnd - dtStart) / 3600000);   
        case 'd' :return parseInt((dtEnd - dtStart) / 86400000);   
        case 'w' :return parseInt((dtEnd - dtStart) / (86400000 * 7));   
        case 'm' :return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);   
        case 'y' :return dtEnd.getFullYear() - dtStart.getFullYear();   
    }   
};  
/*lazyload 新鲜事*/
var currentPage = 1; // 默认当前页
var totalPage = 3; // 默认总页数
var flag = true; // 防止连续触发滚动事件（滚动事件同步）
var load = function(){
		$.ajax({
			type: "post",
			dataType: "json",
			cache : false,
			data:  "queryActionRecordCondition.currentPage="+currentPage+"&queryActionRecordCondition.subjectId="+getCookie("subjectId")+"&queryActionRecordCondition.cusId=0",
			
			url: baselocation+"/freshnews/fna!ajaxFreshNews.action",
			success: function (data) {
				if(data==null || data==""){
					return;
				}
				$("#newthings_loading").css("display","none");
				$("#newthings_more").css("display","block");
				var obj = eval(data.returnMessage);
				for(var i =0; i < obj.length; i++){
					if(!(obj[i].currentType == 1 && ordersize==0)){
					var curr_title = HtmlDecode(obj[i].content);
					var curr_date = verifyTime(obj[i].upTime,obj[i].updateTime);
					var content="";
					if(i ==  obj.length-1)
						 content = '<li style="border-bottom:none;">';
					else
						 content = '<li>';
					if(obj[i].currentType == 1 && ordersize>0){ // 问答
						content += '<dl class="nt_ask">';
						content += '<dt>问</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;提出了一个问题&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;提出了一个问题&nbsp;</span>';
						}
					}else if(obj[i].currentType == 2){ // 视频
						content += '<dl class="nt_look">';
						content += '<dt>看</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;观看了&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;观看了&nbsp;</span>';
						}
					}else if(obj[i].currentType == 3){ // 注册
						content += '<dl class="nt_register">';
						content += '<dt>注</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;注册了&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;注册了&nbsp;</span>';
						}
					}else if(obj[i].currentType == 4){ // 下单
						content += '<dl class="nt_order">';
						content += '<dt>单</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;购买了&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;购买了&nbsp;</span>';
						}
					}else if(obj[i].currentType == 5){ // 考试
						content += '<dl class="nt_practice">';
						content += '<dt>练</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;做&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;做&nbsp;</span>';
						}
					}
					
					content += '<span class="newthings_title" title="' + curr_title + '" >' + curr_title + '&nbsp;&nbsp; </span>';
					content += '<span class="nt_time"> ' + curr_date + '&nbsp;&nbsp;</span>';
					content += "</dd></li>";
					$("#freshnewsInfo").append(content);
					}
				}
				totalPage = data.jumpUrl;
				currentPage++;
				flag = true;
				if(currentPage > totalPage) $("#newthings_more").remove();
			},
			beforeSend:function(){
				$("#newthings_loading").css("display","block");
				$("#newthings_more").css("display","none");
			},
			error: function () {
				$("#newthings_more").css("display","none");
				$("#newthings_loading").css("display","block").html("<font color='red'>加载失败，尝试刷新页面</font>");
			}
		});
	};
		
function lazyloadfresh() {
	
	//if(<s:property value="page.totalRecord"/>){
		//totalPage = 10;//parseInt(<s:property value="page.totalRecord"/>/20) + 1;
	//}
	if(totalPage == 1){
		$("#newthings_more").remove();
	}
	
	/*加载新鲜事*/
	
	/*更多加载新鲜事 */
	$("#more_freshnews").click(function(){
		if (currentPage > totalPage || !flag) {
			return; 
		}
		flag = false;
		load();
	});
	/*滚动加载新鲜事*/
	$(window).bind("scroll", function (event) {
		var top = document.documentElement.scrollTop + document.body.scrollTop;
		var textheight = $(document).height();
		if (textheight - top - $(window).height() <= 40) {
			if (currentPage > totalPage || !flag) {
				return; 
			}
			
			if($("#uc_tabcon2").is(":hidden")==false){
				flag = false;
				load();
			}
			if($("#uc_tabcon3").is(":hidden")==false){
				flag_me = false;
				load_me();
			}
			
		 }
 	});
}

//新鲜事切换
$(document).ready(function() {
   $("div.newthings_tabcon:gt(0)").hide();
	$("ul.newthings_tab a:first").addClass("current");
	$("ul.newthings_tab a").click(function() {
       $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
		$("div.newthings_tabcon:eq("+$("ul.newthings_tab a").index(this)+")").show().siblings("div.newthings_tabcon").hide();
		return false;
   });
});
$(document).ready(function() {
   $("ul.newsthings_list li:odd").addClass("odd");
 
});

//新鲜事结束

//我的动态开始
/*lazyload 我的动态 */
var currentPage_me = 1; // 默认当前页
var totalPage_me = 3; // 默认总页数
var flag_me = true; // 防止连续触发滚动事件（滚动事件同步）
var load_me = function(){
		$.ajax({
			type: "post",
			dataType: "json",
			cache : false,
			data:  "queryActionRecordCondition.currentPage="+currentPage_me+"&queryActionRecordCondition.subjectId=0&queryActionRecordCondition.cusId="+getUserId(),
			//data:  "queryActionRecordCondition.currentPage="+currentPage+"&queryActionRecordCondition.subjectId="+getCookie("subjectId")+"&queryActionRecordCondition.cusId=0",
			url: baselocation+"/freshnews/fna!ajaxFreshNews.action",
			success: function (data) {
				if(data==null || data==""){
					return;
				}
				$("#newthings_loading_me").css("display","none");
				var obj = eval(data.returnMessage);
				for(var i =0; i < obj.length; i++){
					if(!(obj[i].currentType == 1 && ordersize==0)){
					var curr_title = HtmlDecode(obj[i].content);
					var curr_date = verifyTime(obj[i].upTime,obj[i].updateTime);
					var content="";
					if(i ==  obj.length-1)
						 content = '<li style="border-bottom:none;">';
					else
						 content = '<li>';
					if(obj[i].currentType == 1 && ordersize>0){ // 问答
						content += '<dl class="nt_ask">';
						content += '<dt>问</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;提出了一个问题&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;提出了一个问题&nbsp;</span>';
						}
					}else if(obj[i].currentType == 2){ // 视频
						content += '<dl class="nt_look">';
						content += '<dt>看</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;观看了&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;观看了&nbsp;</span>';
						}
					}else if(obj[i].currentType == 3){ // 注册
						content += '<dl class="nt_register">';
						content += '<dt>注</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;注册了&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;注册了&nbsp;</span>';
						}
					}else if(obj[i].currentType == 4){ // 下单
						content += '<dl class="nt_order">';
						content += '<dt>单</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;购买了&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;购买了&nbsp;</span>';
						}
					}else if(obj[i].currentType == 5){ // 考试
						content += '<dl class="nt_practice">';
						content += '<dt>练</dt>';
						if(obj[i].cusName != null && obj[i].cusName!= ''){
							content += '<dd><span class="newthings_nice">' + obj[i].cusName + '&nbsp;做&nbsp;</span>';
						}else{
							content += '<dd><span class="newthings_nice">' + obj[i].cusEmail + '&nbsp;做&nbsp;</span>';
						}
					}
					
					content += '<span class="newthings_title" title="' + curr_title + '" >' + curr_title + '&nbsp;&nbsp; </span>';
					content += '<span class="nt_time"> ' + curr_date + '&nbsp;&nbsp;</span>';
					content += "</dd></li>";
					$("#freshnewsInfo_me").append(content);
					}
				}
				totalPage_me = data.jumpUrl;
				currentPage_me++;
				flag_me = true;
			},
			beforeSend:function(){
				$("#newthings_loading_me").css("display","block");
			},
			error: function () {
				$("#newthings_loading_me").css("display","block").html("<font color='red'>加载失败，尝试刷新页面</font>");
			}
		});
	};
function lazyloadfresh_me() {
	if(totalPage_me == 1){
		$("#newthings_more").remove();
	}
	/*更多加我的动态 */
	$("#more_freshnews_me").click(function(){
		if (currentPage_me > totalPage_me || !flag_me) {
			return; 
		}
		flag_me = false;
		load();
	});
};
//我的动态结束	