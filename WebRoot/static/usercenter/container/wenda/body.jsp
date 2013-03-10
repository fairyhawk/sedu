<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/header.inc"%>
<div class="contenter">
	<div class="wd_left">
	 	<h2 class="myCourse_tt pb10">小嗨问答</h2>
		<div class="myAnswer_btn"><a href="<%=contextPath%>/cus/pblimit!getMyProblemList.action?problem.pblType=1&queryProblemCondition.currentPage=1&param_0=1&param_yesno=1">我的问答</a></div>
		<!-- 上面问答框 开始 -->
		<div class="question_area">
			<div class="menuTab_box">
				<ul class="menuTab">
					<li id="kaoshi"><a href="javascript:changeProType(1,'#kaoshi');" style="width:152px;"><p class="menuTab_name">考试问题</p><p class="menuTab_num"><s:property value="proTypeOneCount"/></p></a></li>
					<li id="kecheng"><a href="javascript:changeProType(2,'#kecheng');"  style="width:152px;"><p class="menuTab_name">课程问题</p><p class="menuTab_num"><s:property value="proTypeTwoCount"/></p></a></li>
					<li id="shipin"><a href="javascript:changeProType(3,'#shipin');"  style="width:152px;"><p class="menuTab_name">视频问题</p><p class="menuTab_num"><s:property value="proTypeThrCount"/></p></a></li>
					<li id="jingyi"><a href="javascript:changeProType(4,'#jingyi');"  style="width:152px;"><p class="menuTab_name">讲义问题</p><p class="menuTab_num"><s:property value="proTypeFouCount"/></p></a></li>			 
				</ul>
				<input type="hidden" value="<s:property value='problem.pblType'/>" id="problem_type" />
				<input type="hidden" value="<s:property value='problem.pblSolv'/>" id="problem_solv" />
				<input type="hidden" value="<s:property value='queryProblemCondition.officialCusId'/>" id="officeId" />
				<!-- 问答框 开始 -->
				<div id="uc_tabconQ" class="uc_tabcon">
					<div class="answer_text">
						<p class="answer_textHolder">
							请输入您提出的问题
						</p>
						<textarea class="textareaFocus" "name="problem.pblContent" cols=""
							rows="" id="problem.pblContent"></textarea>
					</div>
					<div class="answer_bt">
						<div class="QA_word_enter">
							请文明发言，还可以输入
							<em class="QA_surplus_word">300</em>个字  
						</div>
						<div >
							&nbsp;&nbsp;非学术性问题，请点击
						<a class="color_orange" href="/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&from=1">[反馈中心]</a>
						</div>
						
						<a class="answer_btn" href="javascript:showSubject();">我要提问</a>
					</div>
				</div>
				<!-- 问答框 结束 -->

				<!-- 项目列表 开始 -->
				<input type="hidden" value="<s:property value='subList.size'/>"
					id="sublistSize" />
				<s:if test="subList.size==1">
					<s:iterator value="subList" id="subject">
						<input type="hidden"
							value="<s:property value='#subject.subjectId'/>" id="onlySubId" />
					</s:iterator>
				</s:if>
				<s:else>
					<div class="pop_Qsec" style="display:none;" id="subjectDiv">
						<ul>
							<li class="last">
								<s:iterator value="subList" id="subject">
									<a
										href="javascript:saveProblem(<s:property value="#subject.subjectId" />)"><span><s:property
												value="#subject.subjectName" /> </span> </a>
								</s:iterator>
							</li>
						</ul>
						<span class="pop_Qsec_jiao"></span>
					</div>
				</s:else>
				<!-- 项目列表 结束 -->
			</div>
		</div>
		<!-- 上面问答框 结束 -->

		<!-- 下面tab显示 开始 -->
		<div class="answer_area">
			<div class="tab">
				<ul class="uc_tab">
					<li>
						<a
							href="javascript:changeProSolv(1,1)"
							class="current" id="location1"><span>已解决问题</span> </a>
					</li>
					<li>
						<a
							href="javascript:changeProSolv(2,0)"
							id="location0"><span>待解决问题</span> </a>
					</li>
				</ul>

				<!-- 下面显示问题列表 开始 -->
				<div id="uc_tabconA1" class="uc_tabcon">
					<table class="answer_table">
						<thead>
							<tr>
								<th class="tl" width="340">
									问题标题
								</th>
								<th>
									问题类型
								</th>
								<th>
									回答数
								</th>
								<th>
									提问日期
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="page.pageResult" id="problem">
								<tr>
									<td class="tl">
										<a
											href="<%=contextPath%>/cus/pblimit!getEveryOneContent.action?problem.pblId=<s:property value="#problem.pblId"/>"
											title="<s:property value="#problem.pblTitle"/>"><s:property
												value="#problem.pblTitle" /> <s:if test="#problem.officialReplyCount > 0"><span class="font_orange"><font color="red">【认证】</font></span></s:if></a>
									</td>
									<td>
										<s:if test="#problem.pblType==1">考试问题</s:if>
										<s:if test="#problem.pblType==2">课程问题</s:if>
										<s:if test="#problem.pblType==3">视频问题</s:if>
										<s:if test="#problem.pblType==4">讲义问题</s:if>
									</td>
									<td>
										<s:property value="#problem.newHidePblCount" />
									</td>
									<td>
										[
										<s:date name="#problem.createTime" format="yyyy-MM-dd" />
										]
									</td>
								</tr>
							</s:iterator>						
						</tbody>
					</table>
					<!-- 分页页码 开始-->
						<div class="pager">
							<%--<div class="left wid-80 ptop-5">共 <span class="font_orange"><s:property value="page.totalRecord" /></span> 套试卷</div>--%>
							<div class="pager_con">
								<jsp:include
													page="/static/usercenter/jsp/common/showPage.jsp" />
							</div>
						</div>
					<!-- 分页页码 结束-->

				</div>
				<!-- 下面显示问题列表 结束 -->

			</div>
		</div>
		<!-- 下面tab显示 结束 -->
	</div>
	<!-- right start -->
	<div class="side_con">
		<div class="side_otherUser">
			<h2 class="side_title">
				认证达人
			</h2>
			<div id="otherUser_box" class="otherUser_box">
				<ul class="otherUser_list">
					<s:iterator value="officialCustomerDTOList">
						<li>
							<div class="otherUser_pic">
								<img
									src="<%=importURL%>/images/usercenter/official_portrait/<s:property value="portrait"/>"
									alt="头像" width="67" height="63">
							</div>
							<div class="otherUser_name">
								<s:property value="cusName" />
							</div>
							<p>
								回复数量:
								<s:property value="replyCount" />
							</p>
							<p>
								<a
									href="<%=contextPath%>/cus/pblimit!getEveryOneProblemList.action?problem.pblType=1&problem.pblSolv=1&queryProblemCondition.currentPage=1&queryProblemCondition.officialCusId=<s:property value="cusId"/>"
									&location=1>查看他的回答&gt;&gt;</a>
							</p>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<!-- right end -->
</div>
</div>