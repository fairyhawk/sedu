<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<div class="contenter">
	<h2 class="m_title m_title_QA">
		反馈中心
	</h2>
	<div class="answer_area">
			<ul class="uc_tab">
				<li>
					<a href="#" id="5">建 议</a>
				</li>
				<li>
					<a href="#" id="7">咨 询</a>
				</li>
				<li>
					<a href="#" id="8">投 诉</a>
				</li>
				<li>
					<a href="#" id="9">表 扬</a>
				</li>
			</ul>
			<div class="uc_tabcon">
				<!--start 快速问答回复部分-->
				<div class="fastQA">
					<ul class="fastQA_list">
						<s:iterator var="commentList" value="page.pageResult" status="index">
							<li>
								<div class="fastQA_header">
									<span> <s:if test="#commentList.customer.cusName!=null">
											<a href="<%=contextPath%>/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&queryCommentCondition.cusId=<s:property value="#commentList.checkmanId"/>&feedBackType=<s:property value="feedBackType"/>"> <s:property
													value="#commentList.customer.cusName" />&nbsp;&nbsp;</a>
										</s:if> <s:else>
											<a href="<%=contextPath%>/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&queryCommentCondition.cusId=<s:property value="#commentList.checkmanId"/>&feedBackType=<s:property value="feedBackType"/>"><s:property
													value="#commentList.customer.email" />&nbsp;&nbsp;</a>
										</s:else> <s:else>
											<s:property value="#commentList.customer.cusName" />
										</s:else> <a href='<%=contextPath%>/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&queryCommentCondition.subjectId=<s:property value="#commentList.subjectId"/>&feedBackType=<s:property value="feedBackType"/>'> <s:property
												value="#commentList.subjectName" />&nbsp;&nbsp;</a> </span>
									<span> <s:set name="nowTime" value="new java.util.Date()"></s:set> <s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /> </span>
									<span> <s:if test="#nowTime.getYear()==createTime.getYear()&&#nowTime.getMonth()==createTime.getMonth()&&#nowTime.getDate()==createTime.getDate()">
											<img src="<%=importURL%>/images/usercenter/new_icon.png" />
										</s:if> </span>
								</div>
								<div class="fastQA_ask">
									<s:property value="cmtInfo" />
								</div>
								<div class="fastQA_answer">
									<s:if test="#commentList.mgr_info!=null">
										<s:property value="#commentList.mgrName" />回复： <s:property value="#commentList.mgr_info" />(<s:date name="mgr_creatime" format="yyyy-MM-dd HH:mm" />)</s:if>
									&nbsp;
								</div>
								<div class="fastQA_bt">
									<!-- 
                      <span class="fastQA_goodNum">好评<em>（2）</em></span>
                      <span class="fastQA_poorNum">差评<em>（?）</em></span>
                       -->
									<span class="fastQA_replyNum"><a class="uF_answer2_info">回复（<em id="count${index.index }"><s:property value="#commentList.replyList.size()" />
										</em>）</a>
									</span>


									<div class="uF_ans_info">
										<div class="uF_ans_info_head">
											<textarea class="uF_con_area2" name="" id="textarea${index.index }" cols="" rows="" style="overflow-y: hidden" maxlength="140"></textarea>
											<a href="javascript:reply('${index.index }','<s:property value="#commentList.cmtId"/>')">回复</a>

										</div>
										<ul id="ul${index.index }">
											<s:iterator value="#commentList.replyList" id="comment">
												<li>
													<p>
														<s:property value="#comment.visitorName" />
														：
														<s:property value="#comment.cmtInfo" />
														（
														<s:date name="#comment.createTime" format="yyyy-MM-dd HH:mm:ss" />
														）
													</p>
													<div>
														<a href="javaScript:toReply('${index.index}','<s:property value="#comment.visitorName"/>')">回复</a>
													</div>
												</li>
											</s:iterator>
										</ul>
									</div>

								</div>
							</li>
						</s:iterator>
					</ul>
				</div>
				<!--end 快速问答回复部分-->
			</div>
		<!--start 分页-->
		<div class="pager">
			<div class="pager_con">
				<span class="disabled"> <jsp:include page="/static/usercenter/jsp/common/showPage.jsp" /> </span>
			</div>
		</div>
		<!--end 分页-->
	</div>
</div>


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