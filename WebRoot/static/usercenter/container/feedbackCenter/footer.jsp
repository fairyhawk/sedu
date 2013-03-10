<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
</div>
<!--start 反馈中心-->
<div id="sideFeedback" class="sideFeedback">
  <h3>反馈中心</h3>
  <div id="sideFeedback_con" class="sideFeedback_con" style="display:none;">
    <ul id="sideFeedback_tab" class="sideFeedback_tab">
      <li class="sideFeedback_tab1"><a href="#" id="5">建议</a></li>
      <li class="sideFeedback_tab2"><a href="#" id="7">咨询</a></li>
      <li class="sideFeedback_tab3"><a href="#" id="8">投诉</a></li>
      <li class="sideFeedback_tab4"><a href="#" id="9">表扬</a></li>
    </ul>
    <div id="sideFeedback_form" class="sideFeedback_form" style="display:none;">
      <span class="sideFeedback_close"></span>
      <h4>用户反馈</h4>
      <div class="sideFeedback_text"><p class="sideFeedbackHolder">填写你的发表内容~~</p>
      <textarea class="textareaFocus" name="cmtInfo" cols="" rows="" id="textInfo1" style="overflow-y:hidden" maxlength="140" ></textarea>
      </div>
      <div class="sideFeedback_bt"><div class="sideFeedback_word_enter wordEnter">请文明发言，还可以输入<em class="sideFeedback_surplus_word surplusWord">140</em>个字</div> <a class="sideFeedback_btn" href="javascript:sucessAnswer1(this)">提交</a></div>
      <span class="sideFeedback_jiao"></span>
    </div>
  </div>
</div>
<script type="text/javascript">
//侧栏问答输入框焦点
$(document).ready(function() {
	$("div.sideFeedback_text").find("textarea").focus(function(){
	$("p.sideFeedbackHolder").hide();
  }).bind("blur",function(){
	 if($(this).val()=='')
		$("p.sideFeedbackHolder").show();
	});

   $("#sideFeedback h3").toggle(function(){
		if($("#sideFeedback_con").is(":visible")){
			$("#sideFeedback_con").hide();
		}else{
			$("#sideFeedback_con").show();
		}
	},function(){
		if($("#sideFeedback_con").is(":visible")){
			$("#sideFeedback_con").hide();
		}else{
			$("#sideFeedback_con").show();
		}
	});
	
	$("#sideFeedback h3").hover(function (){
		$("#sideFeedback_con").show();
	});
	
	$("#sideFeedback_tab a").click(function() {
		$(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
		var text = "#"+$(this).text()+"#";
		$("#sideFeedback_form .sideFeedbackHolder").text(text);
		var feedbackType = $(this).attr("id");
		$("#sideFeedback_form .sideFeedback_btn").attr("id",feedbackType);
		$("#sideFeedback_form").show();
		return false;
    });
	$("span.sideFeedback_close").click(function() {
        $("#sideFeedback_con").hide();
		$("#sideFeedback_form").hide();
    });
	
});
//输入字数提示
$(document).ready(function() {
	$("textarea.textareaFocus").keyup(function(){
	var $maxtext=140;
    var curr=$(this).val().length;
	var $surplusWord=$("em.surplusWord");
	$surplusWord.text(curr);
	if($maxtext-curr>0){
	$surplusWord.text($maxtext-curr).css("color","#999");
	}else{
	$surplusWord.text(0).css("color","#F00");
		};
	if(curr>$maxtext){
		$surplusWord.css("color","#F00");
		}else{$surplusWord.css("color","#999");}
	});
});


function sucessAnswer1(btn){
	if(ishavebuy=='true'){
		var content=$("textarea[id=textInfo1]").val();
		var len=content.trim().length;
		if(len==0||content==""){
			showErrorWin("请填写要提问的内容！");
			return;
		}else if(len>140){
			showErrorWin("请将内容控制在140字以内");
			return;
		} 

		$.ajax({
			url : baselocation+"/cms/cmtlimit!saveAdvice4ajax.action",
			type : "post",
			data : {
				"comment.cmtInfo" : $("textarea[id=textInfo1]").val(),
				"needConfirmCode" : false,
				"feedBackType" : $("#sideFeedback_form .sideFeedback_btn").attr("id")
				},
			dataType : "json",
			success : function(result) {
				if(result.returnMessage == "success") {
					$("textarea[id=textInfo1]").val("");
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
					});
//					showSuccessTip("感谢您，提交成功。<br>您的意见会让HighSo做得更好");
//					alert("感谢您，提交成功。 您的意见会让HighSo做的更好");
					window.location.href = baselocation+"/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&feedBackType="+$("#sideFeedback_form .sideFeedback_btn").attr("id");
					} else if(result.returnMessage == "adviceDangerWord") {
						
					} 
					}
				});
			}else{
				showErrorWin("只有购买课程后才能提问");
			}
		}
</script>
<!--end 反馈中心-->
<div class="footer">
  <p>尚德机构远程教育</p>
  <p>京ICP备10217787号 京公网安备110105012436 Copyright © 2010-2012 highso.cn</p>
</div>
</div>