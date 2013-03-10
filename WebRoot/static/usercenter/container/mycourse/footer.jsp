<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>

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
	var ishavebuy = getCookie("ishavebuy");
	if(ishavebuy=='1'){
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

<!--提示开始-->
<div id="web_top_black" style=" display:none; width:100%; height:100%; position: fixed; _position:absolute; filter:alpha(opacity=50); opacity: 0.5; -khtml-opacity: 0.5; -moz-opacity:0.5; left:0;top:0;z-index:99; background:#000;"></div>
<!--成功提示-->
<div style="display: none" id="success_win">
	<div class="popupbc" id="popbc1"></div>
	<div class="popup" id="popbc2">
		<div class="title pl20 f14 lh200 bld">
			成功提示 <a href="javascript:closeSuccessWin();"><div
					class="close_div"></div></a>
		</div>
		<div class="con">
			<div class="ml fl ml20 mt30">
				<img src="<%=importURL%>/images/usercenter/att_pic2.jpg" />
			</div>
			<div class="mr fr">
				<b id="success_message"></b><br />
			</div>
		</div>
		<div class="pop_but" style="margin-top:0px;">
			<a href="javascript:closeSuccessWin()"><div
					class="popbut_jh fl ml150" ></div></a>
		</div>
	</div>
</div>
<!--成功提示-->
<!--错误提示-->
<div style="display: none" id="error_win">
	<div class="popupbc" id="popbc1"></div>
	<div class="popup" id="popbc2">
		<div class="title pl20 f14 lh200 bld">
			错误提示 <a href="javascript:closeErrorWin();"><div class="close_div"></div></a>
		</div>
		<div class="con">
			<div class="ml fl ml20 mt30">
				<img src="<%=importURL%>/images/usercenter/att_pic3.jpg" />
			</div>
			<div class="mr fr">
				<b id="error_message"></b><br />
			</div>
		</div>
		<div class="pop_but" style="margin-top:0px;">
			<a href="javascript:closeErrorWin()">
				<div class="popbut_jh fl ml150"  ></div></a>
		</div>
	</div>
</div>
<!--错误提示-->
<script type="text/javascript">
	function openActCouWin(contractId) {
		$("#contractId").html(contractId);
		$("#user_center_act").css("display", "block");
		openBlackWin();
		$("#contractCDkey1").focus();
	}

	function actCourse() {
		var cdk = $("#contractCDkey1").val() + $("#contractCDkey2").val()
				+ $("#contractCDkey3").val() + $("#contractCDkey4").val();
		var contractId = $("#contractId").html();
		if (cdk == "") {
			alert("请输入激活码。");
			return;
		}
		$.ajax({
			url : baselocation + "/finance/contract!gotoCOD.action",
			data : {
				"queryContractCondition.contractId" : contractId,
				"contractCDkey" : cdk
			},
			type : "post",
			dataType : "json",
			cache : false,
			success : function(result) {
				if (result.returnMessage == "success") {
					alert("课程已添加。");
					window.location.reload();
				} else if (result.returnMessage == "moreTimes") {
					alert("您今天已经输入过5次激活码，请明天重试。");
					closeBlackWin();
					closeActWin();
				} else if (result.returnMessage == "hasAct") {
					alert("此课程已经激活。");
					closeBlackWin();
					closeActWin();
				} else {
					alert("激活码错误。");
					$("#contractCDkey1").val("");
					$("#contractCDkey2").val("");
					$("#contractCDkey3").val("");
					$("#contractCDkey4").val("");
					$("#contractCDkey1").focus();
				}
			},
			error : function(error) {
				alert(error.responseText);
			}
		});
	}

	function openBlackWin() {
		$("#web_top_black").css("display", "block");
		document.getElementById("web_top_black").style.width = document.documentElement.clientWidth
				+ "px";
		if (navigator.userAgent.indexOf("MSIE 8.0") != -1) {
			document.getElementById("web_top_black").style.height = 4090 + "px";
		} else if (navigator.userAgent.indexOf("MSIE 6.0") != -1) {
			document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight
					+ "px";
		} else {
			document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight
					+ "px";
		}
		window.scrollTo(0,0);
	}

	function closeBlackWin() {
		$("#web_top_black").css("display", "none");
	}

	function closeActWin() {
		$("#user_center_act").css("display", "none");
		closeBlackWin();
	}
	
	function showErrorWin(errorText, win) {
		if (win != null && win != '') {
			$("#" + win).css("display", "black");
		}
		openBlackWin();
		$("#error_win").fadeIn();
		$("#error_win").css(
				"top",
				document.documentElement.scrollTop
						+ document.documentElement.clientHeight * 0.15);
		$("#error_message").html(errorText);
	}

	function showSuccessWin(successText, win) {
		if (win != null && win != '') {
			$("#" + win).css("display", "black");
		
		}
		openBlackWin();
		$("#success_win").fadeIn();
		$("#success_win").css(
				"top",
				document.documentElement.scrollTop
						+ document.documentElement.clientHeight * 0.15);
		$("#success_message").html(successText);
	}

	function closeErrorWin() {
		closeBlackWin()
		$("#error_win").hide();
	}

	function closeSuccessWin() {
		closeBlackWin()
		$("#success_win").hide();
	}
</script>
<!--提示结束-->

<!--start footer-->
	<div class="footer">
		<p>尚德机构远程教育</p>
		<p>京ICP备10217787号 京公网安备110105012436 Copyright © 2010-2012
			highso.cn</p>
	</div>
	<!--end footer-->
	</div>