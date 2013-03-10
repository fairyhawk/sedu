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
						href="<%=contextPath%>/cus/pblimit!myAnswerProblemList.action?problem.pblType=${problem.pblType }&queryProblemCondition.currentPage=1&param_0=0"
						id="param0">我的回答</a> &gt; 问题详细
				</div>
				<a class="QA_xqBack"
					href="<%=contextPath%>/cus/pblimit!myAnswerProblemList.action?problem.pblType=${problem.pblType }&queryProblemCondition.currentPage=1&param_0=0">返回上一页</a>
				<h2>
					<s:property value="problem.pblTitle" />
				</h2>
				<div class="QA_myAnswer_info">
					<span>类别： [<a href="#"> <s:if test="problem.pblType==1">考试问题</s:if>
							<s:if test="problem.pblType==2">课程问题</s:if> <s:if
								test="problem.pblType==3">视频问题</s:if> <s:if
								test="problem.pblType==4">讲义问题</s:if> </a>] </span>
					<span>回答数：<s:property value="problem.reProblemList.size" />
					</span>
					<span>提问时间：[<s:date name="problem.createTime"
							format="yyyy-MM-dd HH:mm:ss" />]</span>
					<span>提问者：<s:property
							value="problem.customer.cusName==null?problem.customer.email:problem.customer.cusName" />
					</span>
				</div>
				<p>
					<s:property value="problem.pblContent" escape="flase" />
				</p>
				<s:if test="problem.pblSolv!=1">
					<div class="QA_myAnswer_bt">
						<input type="button" onclick="onClick('fzbk');" value="我要回答"
							class="QA_myAnswer_btn">
					</div>
				</s:if>
			</div>
			<!--编辑器开始-->
			<s:if test="problem.pblSolv!=1">
				<form action="pblimit!problemContent.action" method="post"
					name="addProblem" id="addProblem">
					<div id="fzbk" style="display: none"
						class="wen_info_sr mtop-10 bor1blue font_hui">
						<!--  <textarea name="reProblem.reInfo" class="wd_area2 mtop-10" id="reProblem.reInfo" cols="80" rows="6" onblur="taBlur(this)" onfocus="taFocus(this)">请输入问题内容……</textarea>-->
						<FCK:editor instanceName="reProblem.reInfo" toolbarSet="wenda"
							width="780" height="200">
							<jsp:attribute name="value"></jsp:attribute>
							<jsp:body>
								<FCK:config AutoDetectLanguage="true" SkinPath="skins/silver/" />
							</jsp:body>
						</FCK:editor>
						<font color="red" id="advice_message"></font>
						<input type="button" class="QA_myAnswer_btn" value="回复"
							name="huifu" onclick="addProblems()" />
						<input type="hidden" name="problem.pblId" id="problem.pblId"
							value="<s:property value="problem.pblId"/>" />
						<input type="hidden" name="course.courseId" id="course.courseId"
							value="15" />
						<div class="clear"></div>
					</div>
				</form>
			</s:if>
			<!--编辑器结束-->
			<!--最佳答案开始-->
			<s:if test="problem.pblSolv==1">
				<div class="QA_bestAnswer">
					<h3>
						最佳答案
					</h3>
					<s:iterator value="problem.reProblemList" id="reP">
						<s:if test="#reP.isResult==1">
							<p>
								<s:property value="#reP.reInfo" escape="false" />
							</p>
							<div class="QA_bestAnswer_bt">
								<s:if test="#reP.reManType==1">
									<span>回答者：<em>Highso问答</em> </span>
									<span>回答时间：<s:date name="#reP.reTime"
											format="yyyy-MM-dd HH:mm:ss" /> </span>
								</s:if>
								<s:if test="#reP.reManType==0">
									<span>回答者：<em><s:property
												value="#reP.customer.cusName==null?#reP.customer.email:#reP.customer.cusName" />
									</em> </span>
									<span>回答时间：<s:date name="#reP.reTime"
											format="yyyy-MM-dd HH:mm:ss" /> </span>
								</s:if>
								</font>
							</div>
						</s:if>
					</s:iterator>
				</div>
			</s:if>
			<!--最佳答案结束-->
			<!--相关答案开始-->
			<div class="QA_relatedAnswer">
				<h3>
					相关答案
				</h3>
				<ul class="QA_relatedAnswer_list">
					<s:iterator value="problem.reProblemList" id="reProblem">
						<s:if test="#reProblem.isResult!=1">
							<li>
								<p>
									<s:property value="#reProblem.reInfo" escape="false" />
								</p>
								<div class="QA_relatedAnswer_info">
									<s:if test="#reProblem.reManType==1">
										<span>回答者：Highso问答</span>
										<span>回答时间：<s:date name="#reProblem.reTime"
												format="yyyy-MM-dd HH:mm:ss" /> </span>
										<s:if test="#reProblem.reManId==cusId">
											<a href="#"
												onclick="onUpdate(<s:property value="#reProblem.reId"/>)">
												<!--  [编辑]--> </a>
											<a
												href="<%=contextPath%>/cus/pblimit!deleteMyAnswer.action?reProblem.reId=<s:property value="#reProblem.reId"/>&problem.pblId=<s:property value="#reProblem.pblId"/>"
												onclick="return confirm('确定删除？')">[删除]</a>
										</s:if>
									</s:if>
									<s:if test="#reProblem.reManType==0">
										<span>回答者：<s:property
												value="#reProblem.customer.cusName==null?#reProblem.customer.email:#reProblem.customer.cusName" />
										</span>
										<span>回答时间：<s:date name="#reProblem.reTime"
												format="yyyy-MM-dd HH:mm:ss" /> </span>
										<s:if test="#reProblem.reManId==cusId">
											<a href="#"
												onclick="onUpdate(<s:property value="#reProblem.reId"/>)">
												<!--  [编辑]--> </a>
											<a
												href="<%=contextPath%>/cus/pblimit!deleteMyAnswer.action?reProblem.reId=<s:property value="#reProblem.reId"/>&problem.pblId=<s:property value="#reProblem.pblId"/>"
												onclick="return confirm('确定删除？')">[删除]</a>
										</s:if>
									</s:if>
								</div>
							</li>
						</s:if>
					</s:iterator>
				</ul>
			</div>
			<!--相关答案结束-->
		</div>
	</s:else>
</div>
</div>