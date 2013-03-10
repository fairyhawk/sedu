<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/header.inc"%>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_common.css?v=<%=version%>" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_QA.css?v=<%=version%>" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_fk.css?v=<%=version%>" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/popup.css?v=<%=version%>" />
<script type="text/javascript" src="<%=importURL%>/js/web/public/web_jquery-jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/ucenter.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_userFeed.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/web/cn/web_util_cn.js?v=<%=version%>"></script>


<script type="text/javascript">
		var baselocation = '<%=contextPath%>';
		var importURL = '<%=importURL%>';
		var ishavebuy='<s:property value="ishavebuy"/>';
		Date.prototype.format = function(format)    
	 {        
		var o = {        
		"M+" : this.getMonth()+1, //month        
		"d+" : this.getDate(),    //day        
		"h+" : this.getHours(),   //hour        
		"m+" : this.getMinutes(), //minute        
		"s+" : this.getSeconds(), //second        
		"q+" : Math.floor((this.getMonth()+3)/3),  //quarter        
		"S" : this.getMilliseconds() //millisecond        
		}        
		if(/(y+)/.test(format)) 
		format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));        
		for(var k in o)if(new RegExp("("+ k +")").test(format))        
		format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));  
        return format;    
      }
function ssFixedClose(){
	document.getElementById("ss_fixed").style.display="none";
	}
/*用户反馈----给偶数行加背景色*/
$(document).ready(function(){
	$("ul.userFeedback_con li.userFeedback_cons:odd").addClass("current");
	//给class为stripe的表格的偶数行添加class值为alt
});
/*用户反馈----打开提交内容*/
$(function(){
	$(".userClick01").click(function(){
		$(".uF_content").toggle(200);
	})
})
/*用户反馈----打开回复*/
$(function(){
	$(".uF_answer2_info").click(function(){
		$(this).parent().next(".uF_ans_info").toggle(200);
	})
})

//用户反馈----输入字数提示
$(document).ready(function() {
	$(".uF_con_area").keyup(function(){
	var $maxtext=140;
    var curr=$(this).val().length;
	$("#complete_word").text(curr);
	if($maxtext-curr>0){
	$("#surplus_word").text($maxtext-curr);
	$("#surplus_word").css("color","#999");
	}else{
	$("#surplus_word").text(0);
	$("#surplus_word").css("color","#F00");
		};
	if(curr>$maxtext){
		$("#complete_word").css("color","#F00");
		}else{$("#complete_word").css("color","#999");}
	});
});
function sucessAnswer(){
				if(ishavebuy=='true'){
					var content=$("textarea[name=cmtInfo]").val();
					var len=content.trim().length;
					if(len==0||content==""){
						showErrorWin("请填写要提问的内容！");
						return;
					}else if(len>140){
						showErrorWin("请将内容控制在140字以内");
						return;
					} 

				$.ajax({
					url : "<%=contextPath%>/cms/cmtlimit!saveAdvice4ajax.action",
					type : "post",
					data : {
						"comment.cmtInfo" : $("textarea[name=cmtInfo]").val(),
						"needConfirmCode" : false
						},
					dataType : "json",
					success : function(result) {
						if(result.returnMessage == "success") {
						$("textarea[name=cmtInfo]").val("");
						$(".uF_content").fadeOut();
						var entity=result.entity;
						var s='';
						s+='<li class="userFeedback_cons">';
						s+='<div><span style="font-weight:bold">';
						s+=entity.visitorName+'&nbsp;&nbsp;</span>'+new Date().format('yyyy-MM-dd hh:mm:ss'); 
						s+='<img src="<%=importURL%>/images/usercenter/user_icon02.png">';
						s+='<div>';
						s+='<p class="uF_question">'+entity.cmtInfo+'</p>';
						s+='<p class="uF_answer">&nbsp;</p>';
						s+='<div class="uF_answer2">';
						s+='<a class="uF_answer2_info">';
						s+='回复（';
						var index=$('#indexMax').val();
						$('#indexMax').val(parseInt(index)+1);
						s+='<span id="count'+index+'">0</span>';
						s+='）';
						s+='</a>';
						s+='<div class="uF_ans_info">';
						s+='<div class="uF_ans_info_head">';
						s+='<textarea id="textarea'+index+'" class="uF_con_area2" rows="" cols="" name=""></textarea>';
						s+='<a href="javascript:reply('+"'"+index+"','"+entity.cmtId+"'"+')">回复</a>';
						s+='</div>';
						s+='<ul id="ul'+index+'"> </ul>';
						s+='</div>';
						s+='</div>';
						s+='</li>';
						$('#replyList').html(s+$('#replyList').html());
						$(".uF_answer2_info").click(function(){
							$(this).next(".uF_ans_info").toggle(200);
						})
						showErrorWin("感谢您，提交成功。<br>您的意见会让HighSo做得更好");
						} else if(result.returnMessage == "adviceDangerWord") {
							
						} 
						}
					});
				}else{
					showErrorWin("只有购买课程后才能提问");
				}
	}

	function reply(id,cmtId){
		if(ishavebuy=='true'){
			var info=$('#textarea'+id).val();
			if(info.indexOf("回复@")!=-1){
				info=info.substring(info.indexOf(":")+1);
			}
			 if(info.trim()==''){
				showErrorWin("请填写要回复的内容");
				return;
			}
			if(info.length>140){
				showErrorWin("请将内容控制在140字以内");
				return;
			}
			info=$('#textarea'+id).val();
			var html=$('#ul'+id).html();
			var url="<%=contextPath%>/cms/cmtlimit!replyAdvice.action";
			$.post(url,{'comment.sourceId':cmtId,'comment.cmtInfo':info,"needConfirmCode" : false},function(json){
				html+='<li><p>'+json.entity.visitorName+'：'+json.entity.cmtInfo+'（'+json.entity.createTime+'）</p><div><a href="javaScript:toReply('+"'"+id+"','"+json.entity.visitorName+"'"+')">回复</a></div></li>';
				$('#ul'+id).html(html);
				$('#textarea'+id).val('');
				$('#count'+id).text(parseInt($('#count'+id).text())+1);
			},'json');
			$('#'+id).focus();
		}else showErrorWin("只有购买课程后才能回复");
	}
	
	function toReply(id,visitorName){
		if(ishavebuy=='true')
			$('#textarea'+id).val('回复@'+visitorName+':');
		else showErrorWin("只有购买课程后才能回复");
	}		
	
	
	$().ready(function (){
		var feedBackType='<s:property value="feedBackType"/>';
		if(feedBackType=='' || feedBackType=='5'){
			$("#5").addClass("current");
		}else if(feedBackType=='7'){
			$("#7").addClass("current");
		}else if(feedBackType=='8'){
			$("#8").addClass("current");
		}else if(feedBackType=='9'){
			$("#9").addClass("current");
		}
	});
	$().ready(function(){
		$("div.answer_area").find("ul.uc_tab a").click(function() {
			window.location.href = '<%=contextPath%>/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&feedBackType='+$(this).attr("id");
		});
		var from_t =getParameter("from");
		if (from_t!=null && from_t!="" && from_t==1){
			$("#sideFeedback_form").show();
			$("#sideFeedback_con").show();
		}
	});


//用户反馈  结束
</script>

