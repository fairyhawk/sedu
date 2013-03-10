<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<div class="contenter">
	<s:if test="problem==null">
		<div class="QA_xqWrap">
			<div class="QA_myAnswer">
				<div class="crumbs">
					<a
						href="<%=contextPath%>/cus/pblimit!getEveryOneProblemList.action?problem.pblType=1&problem.pblSolv=1&queryProblemCondition.currentPage=1&location=1">问题集合</a>
					&gt; 问题详细
				</div>
				<a class="QA_xqBack"
					href="<%=contextPath%>/cus/pblimit!getEveryOneProblemList.action?problem.pblType=1&problem.pblSolv=2&queryProblemCondition.currentPage=1&location=0">返回上一页</a>
				<div class="QA_not">对不起！此问题不存在或者已被删除</div>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="QA_xqWrap">
			<div class="QA_myAnswer">
				<div class="crumbs">
					<a
						href="<%=contextPath%>/cus/pblimit!getMyProblemList.action?problem.pblType=${problem.pblType }&problem.pblSolv=1&queryProblemCondition.currentPage=1&sw=0&param_0=1&param_yesno=1">我的问题</a>
					&gt;
					<s:if test="problem.pblSolv==1">
						<a
							href="<%=contextPath%>/cus/pblimit!getMyProblemList.action?problem.pblType=${problem.pblType }&problem.pblSolv=1&queryProblemCondition.currentPage=1&sw=0&param_0=1&param_yesno=1">已解决问题</a>
					</s:if>
					<s:else>
						<a
							href="<%=contextPath%>/cus/pblimit!getMyProblemList.action?problem.pblType=${problem.pblType }&problem.pblSolv=2&queryProblemCondition.currentPage=1&sw=1&param_0=1&param_yesno=0">待解决问题</a>
					</s:else>
					&gt; 问题详情
				</div>
				<s:if test="problem.pblSolv==1">
				<a class="QA_xqBack"
					href="<%=contextPath%>/cus/pblimit!getMyProblemList.action?problem.pblType=${problem.pblType }&problem.pblSolv=1&queryProblemCondition.currentPage=1&sw=0&param_0=1&param_yesno=1">返回上一页</a>
				</s:if>
				<s:if test="problem.pblSolv!=1">
				<a class="QA_xqBack"
					href="<%=contextPath%>/cus/pblimit!getMyProblemList.action?problem.pblType=${problem.pblType }&problem.pblSolv=2&queryProblemCondition.currentPage=1&sw=1&param_0=1&param_yesno=0">返回上一页</a>
				</s:if>
				
				<h2>
					<s:property value="problem.pblTitle" />
				</h2>
				<div class="QA_myAnswer_info">
					<span>类别： [<a href="#"> <s:if test="problem.pblType==1">考试问题</s:if>
							<s:if test="problem.pblType==2">课程问题</s:if> <s:if
								test="problem.pblType==3">视频问题</s:if> <s:if
								test="problem.pblType==4">讲义问题</s:if>
					</a>]
					</span> <span>回答数：<s:property value="problem.reProblemList.size" />
					</span> <span>提问时间：[<s:date name="problem.createTime"
							format="yyyy-MM-dd HH:mm:ss" />]
					</span> <span>提问者：<s:property
							value="problem.customer.cusName==null?problem.customer.email:problem.customer.cusName" />
					</span> <font class="right"> <s:if test="takeResultList.size==0">
							<a
								href="<%=contextPath%>/cus/pblimit!deleteProblem.action?problem.pblId=<s:property value="problem.pblId"/>&queryProblemCondition.currentPage=1"
								onclick="return confirm('会连同答案一起删除？')">[删除]</a>
						</s:if>
					</font>
				</div>
				<p>
					<s:property value="problem.pblContent" escape="flase" />
				</p>
				<s:if test="problem.pblSolv!=1">
					<div class="QA_myAnswer_bt">
						<input type="button" onclick="onClick('fzbk',this);" value="补充问题"
							class="QA_myAnswer_btn">
					</div>
				</s:if>
			</div>
			<!--补充问题 开始-->
			<s:if test="problem.pblSolv!=1">
				<form action="pblimit!addMyProblemContent.action" method="post"
					name="addProblem" id="addProblem">
					<div id="fzbk" style="display: none"
						class="wen_info_sr mtop-10 bor1blue font_hui">
						<div class="fontsize-14 font_blue strongb">补充问题</div>
						<!--  <textarea name="content" id="content" class="wd_area2 mtop-10" cols="80" rows="6" onblur="taBlur(this)" onfocus="taFocus(this)">输入你的补充内容……</textarea>-->
						<FCK:editor instanceName="content" toolbarSet="wenda" width="780"
							height="200">
							<jsp:attribute name="value"></jsp:attribute>
							<jsp:body>
								<FCK:config AutoDetectLanguage="true" SkinPath="skins/silver/" />
							</jsp:body>
						</FCK:editor>
						<font color="red" id="advice_message"></font> <input type="button"
							class="QA_myAnswer_btn" value="确定" name="huifu"
							onclick="toReply(document.addProblem)" /> <input type="hidden"
							name="problem.pblId" id="problem.pblId"
							value="<s:property value="problem.pblId"/>" /> <input
							type="hidden" name="href" id="href" value="" />
						<s:hidden id="totolsScore.tsCurrent" name="totolsScore.tsCurrent"></s:hidden>
						<div class="clear"></div>
					</div>
				</form>
			</s:if>
			<!--补充问题 结束-->
			<!--最佳答案开始-->
			<s:iterator value="takeResultList" id="reblem">
				<div class="QA_bestAnswer">
					<h3>最佳答案</h3>
					<p>
						<s:property value="#reblem.reInfo" escape="flase" />
					</p>

					<div class="QA_bestAnswer_bt">
						<s:if test="#reblem.reManType==1">
							<span>回答者：<em>Highso问答</em>
							</span>
							<span>回答时间：<s:date name="#reblem.reTime"
									format="yyyy-MM-dd HH:mm:ss" />
							</span>
						</s:if>
						<s:if test="#reblem.reManType==0">
							<span>回答者：<em><s:property
										value="#reblem.customer.cusName==null?#reblem.customer.email:#reblem.customer.cusName" />
							</em>
							</span>
							<span>回答时间：<s:date name="#reblem.reTime"
									format="yyyy-MM-dd HH:mm:ss" />
							</span>
						</s:if>
					</div>
				</div>
			</s:iterator>
			<!--最佳答案结束-->
			<!--相关答案开始-->
			<div class="QA_relatedAnswer">
				<h3>相关答案</h3>
				<ul class="QA_relatedAnswer_list">
					<s:iterator value="resultList" id="reProblem">
						<li>
							<p>
								<s:property value="#reProblem.reInfo" escape="flase" />
							</p>
							<div class="QA_relatedAnswer_info">
								<s:if test="#reProblem.reManType==1">
									<span>回答者：Highso问答</span>
									<span>回答时间：<s:date name="#reProblem.reTime"
											format="yyyy-MM-dd HH:mm:ss" />
									</span>
								</s:if>
								<s:if test="#reProblem.reManType==0">
									<span>回答者：<s:property
											value="#reProblem.customer.cusName==null?#reProblem.customer.email:#reProblem.customer.cusName" />
									</span>
									<span>回答时间：<s:date name="#reProblem.reTime"
											format="yyyy-MM-dd HH:mm:ss" />
									</span>
								</s:if>
							</div> <s:if test="takeResultList.size==0">
								<div class="QA_myAnswer_bt">
									<input type="button"
										onclick="if( confirm('是否采纳？')) {window.location.href='<%=contextPath%>/cus/pblimit!myProblemIsResult.action?reProblem.reId=<s:property value="#reProblem.reId"/>&problem.pblId=<s:property value="problem.pblId"/>'}"
										value="采纳为答案" class="QA_myAnswer_btn" />
								</div>
							</s:if>
						</li>

					</s:iterator>
				</ul>
			</div>
			<!--相关答案结束-->
		</div>
	</s:else>
</div>
</div>