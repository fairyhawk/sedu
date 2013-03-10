<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<div class="contenter">
	<div class="pre_con">
		<div class="mb30">
			<img src="<%=importURL%>/images/usercenter/pre_icon.png" />
			<span class="pt5 pl10">个人资料</span>
		</div>

		<div class="switch">
			<ul class="switchT">
				<li>
					<a href="#switchD1" class="current">基本资料</a>
				</li>
				<li>
					<a href="#switchD2" id="changePwd">修改密码</a>
				</li>
				<li>
					<a href="#switchD3" id="changePhoto">修改头像</a>
				</li>
			</ul>
			<div id="switchD1" class="switchD">
				<form action="<%=contextPath%>/cus/cuslimit!updateCustomer.action" method="post" name="updateForm" id="updateForm">
					<table width="95%" border="0" cellspacing="20" cellpadding="0" class="pre_text mt5 pt10">
						<tr>
							<td width="80" align="left">
								邮箱地址：
							</td>
							<td width="160">
								<input type="text" name="customer.email" readonly id="email" maxlength="30" value="<s:property value='customer.email'/>" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								昵&nbsp;&nbsp;&nbsp;&nbsp;称：
							</td>
							<td width="160">
								<input type="text" name="customer.cusName" id="cusName" value="<s:property value='customer.cusName'/>" maxlength="20" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								手机号码：
							</td>
							<td width="160">
								<input type="text" class="pre_input_text" name="customer.mobile" id="mobile" maxlength="20" value="<s:property value='customer.mobile'/>" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								QQ&nbsp;号&nbsp;码：
							</td>
							<td width="160">
								<input type="text" name="customer.qq" id="qq" maxlength="15" value="<s:property value='customer.qq'/>" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								MSN：
							</td>
							<td width="160">
								<input type="text" name="customer.MSN" id="MSN" value="<s:property value='customer.MSN'/>" maxlength="30" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								联系方式：
							</td>
							<td width="160">
								<input type="text" name="customer.address" id="address" value="<s:property value='customer.address'/>" maxlength="60" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								性&nbsp;&nbsp;&nbsp;&nbsp;别：
							</td>
							<td colspan="2">
								<input type="radio" name="customer.sex" id="sex1" value="1" <s:property value="customer.sex==1?'checked':''"/> />
								男&nbsp;&nbsp;
								<input type="radio" name="customer.sex" id="sex0" value="0" <s:property value="customer.sex==0?'checked':''"/> />
								女
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								生&nbsp;&nbsp;&nbsp;&nbsp;日：
							</td>
							<td colspan="2">
								<select name="queryCustomerCondition.year" id="year" onchange="changeDate()">
									<option value="-1">
										请选择
									</option>
								</select>
								年&nbsp;
								<select name="queryCustomerCondition.month" id="month" onchange="changeDate()">
									<option value="-1">
										请选择
									</option>
								</select>
								月&nbsp;
								<select name="queryCustomerCondition.day" id="day">
									<option value="-1">
										请选择
									</option>
								</select>
								日
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								&nbsp;
							</td>
							<td width="160">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>

						<tr>
							<td width="80" align="left">
								&nbsp;
							</td>
							<td width="160">
								<input type="submit" class="button_6" value="保存" />
							</td>
						</tr>

					</table>
				</form>
			</div>

			<div id="switchD2" class="switchD" style="display: none;">
				<form action="<%=contextPath%>/cus/cuslimit!updatePwd.action" method="post" name="pwdForm" id="pwdForm">
					<table width="75%" border="0" align="center" cellpadding="0" cellspacing="20" class="pre_text mt5 pt10">
						<tr>
							<td width="80" align="left">
								当前密码：
							</td>
							<td width="160">
								<input type="password" name="customer.cusPwd" id="oldPwd" maxlength="20" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>
						<tr>
							<td width="80" align="left">
								新密码：
							</td>
							<td width="160">
								<input type="password" name="newPwd" id="newPwd" maxlength="20" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>
						<tr>
							<td width="80" align="left">
								确认新密码：
							</td>
							<td width="160">
								<input type="password" name="newPwdConfirm" id="newPwdConfirm" equalTo="#newPwd" maxlength="20" class="pre_input_text" />
							</td>
							<td>
								<font class="font_hui"></font><font class="font_red"></font>
							</td>
						</tr>
						<tr>
							<td width="80" align="left">
								&nbsp;
							</td>
							<td width="160">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="80" align="left">
								&nbsp;
							</td>
							<td width="160">
								<input type="submit" class="button_6" value="保存" />
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div id="switchD3" class="switchD" style="display: none;">
				<div class="pl10">
					<form name="tempPicForm" id="tempPicForm" method="post" action="<%=contextPath%>/cus/cuslimit!uploadPhoto.action" enctype="multipart/form-data">

						<div class="pre_primg pt15">


							<div class="broswe pt10">

								<input type="file" name="fileupload" id="fileupload" class="button_7" />
								<div class="pt15">
									请选择头像图片（图片分辨率在1200*900像素以下，小于2M）
								</div>
							</div>

							<div id="Canvas" class="uploaddiv">

								<div id="ImageDragContainer">

									<img id="ImageDrag" class="imagePhoto" src="<%=importURL%>/images/usercenter/man.GIF" style="border-width: 0px;" />

								</div>

								<div id="IconContainer">



									<img id="ImageIcon" class="imagePhoto" src="<%=importURL%>/images/usercenter/man.GIF" style="border-width: 0px; top: 50px; left: 110px;" />



								</div>

							</div>


							<div class="uploaddiv">

								<table>

									<tr>

										<td id="Min">
											<img alt="缩小" src="<%=importURL%>/images/usercenter/_c.gif" onmouseover="this.src='<%=importURL%>/images/usercenter/_c.gif';"
												onmouseout="this.src='<%=importURL%>/images/usercenter/_h.gif';" id="moresmall" class="smallbig" />

										</td>

										<td>

											<div id="bar">

												<div class="child"></div>

											</div>

										</td>

										<td id="Max">
											<img alt="放大" src="<%=importURL%>/images/usercenter/c.gif" onmouseover="this.src='<%=importURL%>/images/usercenter/c.gif';" onmouseout="this.src='<%=importURL%>/images/usercenter/h.gif';"
												id="morebig" class="smallbig" />

										</td>

									</tr>

								</table>

								<div class="pre_img_bt">

									<div class="pt5">
										您可以拖动照片以裁剪您满意的头像
										<span></span>
										<input name="" type="button" class="button_6" value="取消" onclick="javascript:location.href='<%=contextPath%>/cus/cuslimit!SelectBQ.action'" />
										<input name="" type="button" class="button_6" value="保存" onclick="catparams()" />
									</div>

								</div>
								<div class="font_red left mleft-10 mtop-5" id="save_message"></div>
							</div>
						</div>
					</form>


					<div style="display: none">
						<form action="<%=contextPath%>/cus/cuslimit!SelectBQ.action" method="post" name="photoForm">
							<s:if test="newPhotoFileName==null||newPhotoFileName.size==0||newPhotoFileName[0]==\"\"">
								<input id="photoPath" name="photoPrams.photoPath" style="display: none" />
							</s:if>
							<s:else>
								<input id="photoPath" style="display: none" name="photoPrams.photoPath" value="/upload/customer/tempPic/<s:property value="newPhotoFileName[0]"/>" />
							</s:else>
							<input name="photoPrams.txt_width" value="1" id="txt_width" style="display: none; background: none" />
							<br />
							<input name="photoPrams.txt_height" value="1" id="txt_height" style="display: none" />
							<br />
							<input name="photoPrams.txt_top" value="82" id="txt_top" style="display: none" />
							<br />
							<input name="photoPrams.txt_left" value="73" id="txt_left" style="display: none" />
							<br />
							<input name="photoPrams.txt_DropWidth" value="120" id="txt_DropWidth" style="display: none" />
							<br />
							<input name="photoPrams.txt_DropHeight" value="120" id="txt_DropHeight" style="display: none" />
							<br />
							<input name="photoPrams.txt_Zoom" id="txt_Zoom" style="display: none" />
						</form>
					</div>


				</div>
			</div>
		</div>
	</div>
</div>
</div>

<!--提示开始-->
<div id="web_top_black"
	style="display: none; width: 100%; height: 100%; position: fixed; _position: absolute; filter: alpha(opacity = 50); opacity: 0.5; -khtml-opacity: 0.5; -moz-opacity: 0.5; left: 0; top: 0; z-index: 99; background: #000;"></div>
<!--成功提示-->
<div style="display: none" id="success_win">
	<div class="popupbc" id="popbc1"></div>
	<div class="popup" id="popbc2">
		<div class="title pl20 f14 lh200 bld">
			成功提示
			<a href="javascript:closeSuccessWin();"><div class="close_div"></div>
			</a>
		</div>
		<div class="con">
			<div class="ml fl ml20 mt30">
				<img src="<%=importURL%>/images/usercenter/att_pic2.jpg" />
			</div>
			<div class="mr fr">
				<b id="success_message"></b>
				<br />
			</div>
		</div>
		<div class="pop_but" style="margin-top: 0px;">
			<a href="javascript:closeSuccessWin()"><div class="popbut_jh fl ml150" ></div>
			</a>
		</div>
	</div>
</div>
<!--成功提示-->
<!--错误提示-->
<div style="display: none" id="error_win">
	<div class="popupbc" id="popbc1"></div>
	<div class="popup" id="popbc2">
		<div class="title pl20 f14 lh200 bld">
			错误提示
			<a href="javascript:closeErrorWin();"><div class="close_div"></div>
			</a>
		</div>
		<div class="con">
			<div class="ml fl ml20 mt30">
				<img src="<%=importURL%>/images/usercenter/att_pic3.jpg" />
			</div>
			<div class="mr fr">
				<b id="error_message"></b>
				<br />
			</div>
		</div>
		<div class="pop_but" style="margin-top: 0px;">
			<a href="javascript:closeErrorWin()">
				<div class="popbut_jh fl ml150" ></div>
			</a>
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