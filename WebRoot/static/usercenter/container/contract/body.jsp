<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/include/header.inc" %>
		<div class="p-wiidow" style="display: none" id="pwin">
			<input type="hidden" id="orderId" value=""></input>
			<input type="hidden" id="orderno" value=""></input>
			<span class="close-pop" id="closeBtnX" onclick="closeStepWin()"></span>
			<div class="pop-words">
					<p class="f">
						是否选择<strong>分批支付</strong>？
					</p>
					<p>
						<span class="f12">针对网银支付限额</span><span class="fc">￥450</span>
					</p>
			</div>
				<div class="contrl-bar">
					<a class="pop-qd" href="javascript:gotoSubContract()"></a>
					<a class="pop-qx" href="javascript:gotoOriPay()"></a>
				</div>
		</div>
	       <%--  <div class="p-wiidow" style="display:none" id="childWin">
	        		<img src="<%=importURL%>/images/web/public/buy/pop-ico.gif" alt="弹出层" />
	                <div class="pop-words">
	                	<p><span>您已经进行过分批支付，即将为您转入分批支付页面</span></p>
	                	<p><span id="second"></span>秒页面跳转</p>
	                </div>
	        </div> --%>
			<!--左部结束-->
			<div class="contenter">
				<div class="order_con">
								<div class="switch">
									<ul class="switchT">
										<li><a
											href="<%=contextPath%>/finance/contract!getContractList.action?queryContractCondition.currentPage=1&location=0"
											id="switchCT0" class="current">订单管理</a></li>
										<li><a
											href="<%=contextPath%>/cus/addrlimit!showAddressList.action?location=1"
											id="switchCT1">收货地址</a></li>
										<li><a
											href="<%=contextPath%>/card/cardMain!getCardCourseInfo.action?location=2&queryCardCourseCondition.currentPage=1"
											id="switchCT2">卡管理</a></li> 
									</ul>
								</div>

								<div id="switchCD0" class="switchD">
										<div class="order_prompt">你有<span class="color2"> <s:property value="sumCount" /></span>条订单信息 ,
										<a href="/finance/contract!getContractList.action?queryContractCondition.currentPage=1&payStatus=pay" class="color2"> ${paySum }个已支付订单,</a>
										<a href="/finance/contract!getContractList.action?queryContractCondition.currentPage=1&payStatus=no" class="color2"><s:property value="sumCount-paySum-cancelSum"/>个未支付</a>,
										<a href="/finance/contract!getContractList.action?queryContractCondition.currentPage=1&payStatus=cancel" class="color2">${cancelSum }个 已取消 </a>
										,点击订单号可以看订单详情
									<span class="order_prompt_close">关闭</span>
									</div>
									 <table class="order_table mt10" cellspacing="0">
				                        <thead>
				                          <tr>
				                            <th class="order_name">课程名称</th>
				                      <!--       <th class="order_class">班级</th> -->
				                            <th class="order_teacher">老师</th>
				                            <th class="order_price">价格</th>
				                            <th class="order_classNum">课时</th>
				                             <!--  <th class="order_class">保过协议</th>  -->
				                          </tr>
				                        </thead>
				                      </table>
									<s:iterator value="page.pageResult" id="contract">
										<div class="order_part">
											<ul class="order_list">
												<li>订单号：<a href="<%=contextPath%>/finance/cashRecord!getCashRecordList.action?cashRecord.ctId=<s:property value="#contract.id"/>"><s:property value="#contract.contractId" /></a></li>
												<li>下单时间：<s:date name="#contract.createTime"
														format="yyyy-MM-dd HH:mm:ss" /></li>
												<li class="order_status">状态:<span>
													<s:if test="#contract.payType==2">
														<s:if test="#contract.status==0">未激活</s:if>
														<s:elseif test="#contract.status==1">已激活</s:elseif>
														<s:elseif test="#contract.status==3">已退费</s:elseif>
														<s:elseif test="#contract.status==4">已取消</s:elseif>
													</s:if>
													<s:elseif
														test="#contract.payType==1||#contract.payType==3||#contract.payType==4||#contract.payType==6 ||#contract.payType==7||#contract.payType==8">
														<s:if test="#contract.status==0">未付款</s:if>
														<s:elseif test="#contract.status==1">已付款</s:elseif>
														<s:elseif test="#contract.status==3">已退费</s:elseif>
														<s:elseif test="#contract.status==4">已取消</s:elseif>
													</s:elseif>
													<s:elseif test="#contract.payType==0">
														<s:if test="#contract.status==2">赠送</s:if>
														<s:elseif test="#contract.status==4">赠送/修复</s:elseif>
													</s:elseif>
													<s:elseif test="#contract.payType==5">
															代理商开通
														</s:elseif>
														</span>
													</li>
												<%-- <li>用户邮箱：<s:property value="#contract.customer.email" /></li> --%>
												<!-- <li>总金额:￥<s:if test="#contract.payType==5"><s:property value="#contract.contractSumMoney" /></s:if>	<s:else><s:property value="#contract.contractCutSumMoney" /></s:else></font></li> -->
											</ul>
											<table class="order_table" cellspacing="0">
												<s:iterator value="#contract.cashList" id="cashRecord">
													<tr class="order_odd">
														<td class="order_name">
															<h4>
																<s:property value="#cashRecord.packName" />
																<s:if test="#cashRecord.isProtocal == true">
																<span class="order_baoguo"><a href="javascript:void(0)">(保过协议)</a></span>
															</s:if>
															</h4>
															<p>
																<s:property value="#cashRecord.remark" />
															</p>
														</td>
														<td class="order_teacher">
															${fn:replace(fn:replace(cashRecord.teacherNamesHTML,"<p>","<span>"),"</p>","&nbsp;</span>")}
														</td>
														<td class="order_price tc">
															<p class="new_price">
																优惠价￥
																<em><s:property value="#cashRecord.sellPrice" /></em>
															</p>
															<p>
																节省
																<s:if
																	test="(#cashRecord.oriSellPrice-#cashRecord.sellPrice) >= 0">￥<s:property
																		value="#cashRecord.oriSellPrice-#cashRecord.sellPrice" />
																</s:if>
																<s:else>￥0</s:else>
															</p>
														</td>
														<td  class="order_classNum tc"><strong class="font_orange"><s:property
																	value="#cashRecord.lessonNumber" /></strong>课时</td>
														<%-- <td><s:if test="#cashRecord.isProtocal == true">
																<a href="javascript:void(0)">保过协议</a>
															</s:if>
															<s:else></s:else></td> --%>
													</tr>
													<%--   <s:if test="#cashRecord.isProtocal == true">
                                                       <div class="orderPaul" style="display:none;"  id="cash<s:property value="#cashRecord.id"/>" >
             												<div class="orderPaul_out"><span><a href="javascript:void(0)" onclick="closeProtocal('cash'+<s:property value="#cashRecord.id"/>)">关闭</a></span></div>
             													<s:property value="#cashRecord.protocalContent" escape="false"/>
       			 									   </div>
       			 									   </s:if> --%>
												</s:iterator>
												<tr class="order_odd">
													<td colspan="4" class="order_pay"><span
														class="order_pay_z">总金额： <em class="fo">￥<s:if
																	test="#contract.payType==5">
																	<s:property value="#contract.contractSumMoney" />
																</s:if> <s:elseif test="#contract.payType==2">
																	<script>
														var lin=<s:property value="#contract.contractCutSumMoney" />+<s:property value="#contract.freight" />;
														document.write(lin);
													</script>
																</s:elseif> <s:else>
																	<s:property value="#contract.contractCutSumMoney" /> 
																</s:else>
														</em> 
													</span>
													 <s:if test="#contract.payType==2">
															<s:if test="#contract.status==0">
																<a class="order_pay_sub"
																	href="javascript:openActCouWin('<s:property value="contractId"/>')">激活</a> | <a
																	class="order_pay_cancel"
																	href="<%=contextPath%>/finance/contract!getContractList.action?queryContractCondition.currentPage=1&contract.id=<s:property value="#contract.id"/>&ty=0">取消</a>
															</s:if>
															<s:else>--- | ---</s:else>
														</s:if> <s:else>
															<s:if test="#contract.status==0">
																<s:if test="#contract.payType == 7">
																			 --- | <a class="order_pay_cancel"
																		href="<%=contextPath%>/finance/contract!getContractList.action?queryContractCondition.currentPage=1&contract.id=<s:property value="#contract.id"/>&ty=0">取消</a>
																</s:if>
																<s:elseif test="#contract.payType != 7">
																	<s:if
																		test="#contract.childCount==null||#contract.childCount==0">
																		<a class="order_pay_sub"
																			href="javascript:gotoPay(<s:property value="#contract.id"/>,<s:property value="#contract.contractCutSumMoney"/>,'<s:property value="#contract.contractId"/>',0)">付款</a>
																	</s:if>
																	<s:else>
																		<a class="order_pay_sub"
																			href="javascript:gotoPay(<s:property value="#contract.id"/>,<s:property value="#contract.contractCutSumMoney"/>,'<s:property value="#contract.contractId"/>',1)">付款</a>
																	</s:else>
																	|
																	<a class="order_pay_cancel"
																		href="<%=contextPath%>/finance/contract!getContractList.action?queryContractCondition.currentPage=1&contract.id=<s:property value="#contract.id"/>&ty=0">取消</a>
																</s:elseif>
															</s:if>
															<s:else>--- | ---</s:else>
														</s:else> | <a class="order_pay_view"
														href="<%=contextPath%>/finance/cashRecord!getCashRecordList.action?cashRecord.ctId=<s:property value="#contract.id"/>">查看</a>

													</td>
												</tr>
											</table>
										</div>
									</s:iterator>
									<!-- 分页页码 -->

									<s:if test="location==0">
										<div class="pager">
											<div class="pager_con">
												<jsp:include
														page="/static/usercenter/jsp/common/showPage.jsp" />
											</div>
										</div>
									</s:if> 
									<!-- 分页页码 //-->
								</div>

								<div id="switchCD1" class="switchD" style="display: none;">
									<h4 class="pt10 pb10 f14">收货地址簿</h4>
									<table border="0" cellspacing="1" cellpadding="0"
										style="background: none repeat scroll 0 0 #A9CCDE; width: 782px;"
										class="addressTable">
										<tr class="cw_tabletit">
											<td width="10%">收货人</td>
											<td>收货地址</td>
											<td width="9%">邮编</td>
											<td width="13%">电话</td>
											<td width="9%">地址性质</td>
											<td width="9%" valign="middle"><img
												src="<%=importURL%>/images/usercenter/cw_icon_4.png" /> 操作
											</td>
										</tr>
										<s:iterator value="addressDTOList" id="addressDTO">
											<tr>
												<td title="<s:property value="receiver"/>"><s:property
														value="receiver" /></td>
												<td><s:property value="provinceName" /> , <s:property
														value="cityName" /> , <s:property value="townName" /> ,
													<s:property value="address" /></td>
												<td><s:property value="postCode" /></td>
												<td><s:property value="mobile" /></td>
												<td><s:if test="isFirst==1">常用地址</s:if> <s:else>
														<a
															href="<%=contextPath%>/cus/addrlimit!updateAddrFirst.action?address.id=<s:property value="id"/>">设为常用</a>
													</s:else></td>
												<td><a
													href="javascript:initUpdate(<s:property value="id"/>, 
	                                									'<s:property value="receiver"/>',
	                                									'<s:property value="address"/>',
	                                									'<s:property value="postCode"/>',
	                                									'<s:property value="mobile"/>',
	                                									'<s:property value="createTime"/>',
	                                									<s:property value="provinceId"/>,
	                                									<s:property value="cityId"/>,
	                                									<s:property value="townId"/>,
	                                									<s:property value="isFirst"/>,
	                                									<s:property value="sendTime"/>)">修改</a>
													| <a
													href="javascript:deleteAddress(<s:property value="id"/>)">删除</a>
												</td>
											</tr>
										</s:iterator>
									</table>
									<h4 class="pt20 pb10 f14">
										添加新地址/修改地址 <font class="font_12 font_hui">（手机和固定电话任选一项，其他为必填项）</font>
									</h4>
									<div class="cw_add">
										<form
											action="<%=contextPath%>/cus/addrlimit!updateOrAddAddr.action"
											id="addressForm" name="addressForm" method="post">
											<input type="hidden" name="address.id" id="addressId"
												value="0" /> <input type="hidden" name="address.isFirst"
												id="address_isFirst" value="1" /> <input type="hidden"
												name="address.mobile" id="address_mobile" /> <input
												type="hidden" name="address.createTime"
												id="address_createTime" />
											<div class="cw_addlist">
												<div class="left wid-60">收货人：</div>
												<div class="left">
													<input name="address.receiver" id="receiverName"
														maxlength="30" type="text" /> &nbsp; <font
														class="font_red"></font>
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">地区：</div>
												<div class="left">
													<select name="address.provinceId" id="sel_province"
														onchange="initArea(this.value, 1)">
													</select> <select id="sel_city" name="address.cityId"
														onchange="initArea(this.value, 2)">
													</select> <select id="sel_town" name="address.townId">
														<option value='0'>--------</option>
													</select> &nbsp; <font class="font_red" id="area_message"></font>
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">街道地址：</div>
												<div class="left">
													<input name="address.address" id="detailAddress"
														maxlength="100" type="text" /> &nbsp; <font
														class="font_red"></font>
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">邮编：</div>
												<div class="left">
													<input name="address.postCode" id="postCode" maxlength="10"
														type="text" /> &nbsp; <font class="font_red"></font>
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">手机：</div>
												<div class="left">
													<input name="addressMobile" id="addressMobile" type="text"
														maxlength="15" /> &nbsp; <font class="font_red"></font>
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">固定电话：</div>
												<div class="left">
													<input name="addressTel" id="addressTel" maxlength="15"
														type="text" />
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">送达时间：</div>
												<div class="left">
													<select name="address.sendTime" id="sendTime">
														<option value="1">时间不限</option>
														<option value="2">周一至周五</option>
														<option value="3">周六日及公众假期</option>
													</select>
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">&nbsp;</div>
												<div class="left">
													<input name="isFirstCheckbox" id="isFirstCheckbox"
														onclick="changeFirst(this)" type="checkbox"
														checked="checked" /> &nbsp;设为常用地址
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">&nbsp;</div>
												<div class="left">
													<input name="saveAddrBtn" onclick="saveAddress();"
														class="order_saveAdd" type="button" value="保存" />
												</div>
											</div>
											<div class="cw_addlist">
												<div class="left wid-60">&nbsp;</div>
												<div class="left">
												</div>
											</div>
										</form>
									</div>
								</div>
								
								<div id="switchCD2" class="switchD" style="display:none;">
             			<div class="pb20"><a class="activation_btn m10"  id="activateCardCourse"  href="javascript:void(0)" style="float:right">激活</a></div>
                      <table class="card_mas mt30" border="0" cellspacing="1" cellpadding="0" style="background: none repeat scroll 0 0 #A9CCDE; width: 810px;">
                      	<tr >
                        <th>编号</th>
                        <th>卡类型</th>
                        <th>卡号</th>
                        <th>卡密码</th>
                        <th>金额</th>
                        <th>有效期</th>
                        <th>订单号码</th>
                        <th>使用时间</th>
                        <th>卡状态</th>
                        </tr>
                          <s:if test="cardCoursePage.pageResult.size()!=0">
                            	<s:iterator value="cardCoursePage.pageResult" id="cardCourse" status="status">
		                            <tr>
		                                <td>
		                                    <s:property value="(cardCoursePage.currentPage-1)*cardCoursePage.pageSize+#status.count" />
		                                </td>
		                                <td>
		                                                                                                            课程卡
		                                </td>
		                                <td>
		                                    <s:property value="#cardCourse.cardCourseCode"/>
		                                </td>
		                                <td>
		                                    <s:property value="#cardCourse.cardCoursePassword"/>
		                                </td>
		                                <td>
		                                    <s:property value="#cardCourse.cardMoney"/>
		                                </td>
		                                <td>
		                                    <s:date name="#cardCourse.validBeginTime" format="yyyy-MM-dd"/> - <s:date name="#cardCourse.validEndTime" format="yyyy-MM-dd"/>
		                                </td>
		                                <td>
		                                    <s:property value="#cardCourse.orderCode"/>
		                                </td>
		                                <td>
		                                    <s:date name="#cardCourse.updateTime" format="yyyy-MM-dd HH:mm:ss"/>
		                                </td>
		                                <td>
		                                    <s:property value="#cardCourse.cardCourseUseStatusContent"/>
		                                </td>
		                           </tr>
                              </s:iterator>
                           </s:if>
                      </table>
                  <s:if test="location==2">
                  <div class="pager">
                    <div class="pager_con"><jsp:include page="/static/usercenter/jsp/common/showCardCoursePage.jsp" /></div>
                 </div>
                   </s:if>
                  </div>
	</div>
	</div>
	<div class="popup" id="activateCardCourseDiv" style="display: none">
<div class="title  pl20 f14 lh200 bld">卡激活
	<a href="javascript:CardClose();"><div class="close_div"></div></a>
</div>
<div class="con">
<div class="cent fontsize-14">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th width="32%" height="50" align="right"><font class="strongb">输入卡号：</font></th>
            <td width="68%" height="35" align="left"><input name="cardCourse.cardCourseCode" id="cardCourseCode" /></td>
          </tr>
         <tr>
            <th width="32%" height="50" align="right"><font class="strongb">输入密码：</font></th>
            <td width="68%" height="35" align="left"><input nname="cardCourse.cardCoursePassword" id="cardCoursePassword" type="password" /></td>
          </tr>
        </table>
</div>
<div class="clear"></div>
</div>
<div class="pop_but"><a href="javascript:void(0)" id="activateNow" ><div class="popbut_jh fl ml150"></div></a></div>
</div>

<!--成功提示-->
<div style="display:none" id="activateCardCourseSuccessDiv">
	<div class="popup" id="popbc2">
		<div class="title pl20 f14 lh200 bld">
			激活成功提示 <a href="javascript:void(0);" onclick="successClose()">
			<div class="close_div"></div></a>
		</div>
		<div class="con">
			<div class="ml fl ml20 mt30">
				<img src="<%=importURL%>/images/usercenter/att_pic2.jpg" />
			</div>
			<div class="mr fr">
				<b id="activateInfoDiv"></b><br />
			</div>
		</div>
		<div class="pop_but">
			<a href="<%=contextPath%>/cou/courselimit!toMyCourse.action">
			<div class="popbut_jh fl ml150"></div></a>
		</div>
	</div>
</div>
   
   <div id="activateCardCourseFalseDiv" style="display:none">
	<div class="popup" id="popbc2">
		<div class="title pl20 f14 lh200 bld">
			激活失败提示 <a href="javascript:closeError();"><div class="close_div"></div></a>
		</div>
		<div class="con">
			<div class="ml fl ml20 mt30">
				<img src="<%=importURL%>/images/usercenter/att_pic3.jpg" />
			</div>
			<div class="mr fr" id="activateFalseInfoDiv">
				
			</div>
		</div>
	    <div class="pop_but" style="padding-bottom: 10px">
			<a href="javascript:CardClose();">
			<div id="comeback" class="popbut_jh fl ml150" > </div></a>
		</div>
	</div>
</div>   
</div>