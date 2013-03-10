<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
	<div class="contenter">
			
       <!--考试开始-->
	     <div class="exam_con">
               	<div class="exam_left">	
            	<form action="<%=importURL%>/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1" method="post" name="examlist"> 
                  <div class="exam_sec">
                     <h4 class="exam_tt pb10">考试中心</h4>
                    <div class="exam_sectt" style="position:absolute;right:0px;top:10px;"><s:iterator value="subjectExampaperNum">
                    	<s:if test="subjectIdweb==subjectId">
                    	<s:property value="subjectName"/> 
                    	</s:if>
                    </s:iterator></div>
                    <div class="exam_sec_pro " style="display:none">
                      <s:iterator value="subjectExampaperNum" id="subject"><a href="javaScript:examlist('subjectIdweb','<s:property value="#subject.subjectId"/>')">	<s:property value="#subject.subjectName" /><em>(<s:property value="#subject.num"/>)</em></a></s:iterator> 
                    </div>
                   <!--  <div class="exam_sec_pro" style="display:block">
                      <s:iterator value="courseList" id="course"> 	<s:property value="#course.title" /> </s:iterator> 
                    </div> -->
          <div class="menuTab_box pb20">
          <ul class="menuTab">
            <li style="width:144px;"><div class="menuTab_a" style="width:144px;"><p class="menuTab_name">试卷</p><p class="menuTab_num"><a href="<%=contextPath %>/exam/qstManager!getExamHistroy.action?queryExampaperRecordCondition.currentPage=1&subjectIdweb=${subjectIdweb}">${myPaperCount }</a></p></div></li>
            <li style="width:144px;"><div class="menuTab_a" style="width:144px;">
                <p class="menuTab_name">做题</p><p><span class="menuTab_num">${myQstCount }</span></p></div></li>
            <li style="width:144px;"><div class="menuTab_a" style="width:144px;">
                <p class="menuTab_name">错题</p><p class="menuTab_num"><a href="<%=contextPath%>/exam/qstManager!getErrorExam.action?queryOptRecordCondition.currentPage=1&subjectIdweb=${subjectIdweb}">${wrongQstCount }</a></p></div></li>
            <li class="last" style="width:144px;"><div class="menuTab_a" style="width:144px;">
                <p class="menuTab_name">收藏</p>
                <p><span class="menuTab_num" style="cursor:pointer;"><a href="<%=contextPath %>/exam/qstManager!getMyFavoritesQuestion.action?queryFavoritesCondition.currentPage=1&subjectIdweb=${subjectIdweb}">${favoritesQstCount }</a></span>题&nbsp;+<span class="menuTab_num" style="cursor:pointer;"><a href="<%=contextPath %>/exam/qstManager!getMyFavoritesPaper.action?queryFavoritesCondition.currentPage=1&subjectIdweb=${subjectIdweb}">${favoritesPaperCount }</a></span>试卷</p></div></li>
          </ul>
        </div>
                    <div class="exam_sec_con">
                       <span class="exam_tp_jaio"></span>
                      
                      <ul>
                      <li><h5>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</h5><a href="javaScript:examlist('queryExampaperCondition.year','0')" <s:property value="queryExampaperCondition.year==null||queryExampaperCondition.year==0?'class=current':''"/>><span>全部</span></a>
                       <a href="javascript:examlist('queryExampaperCondition.year','2011')" <s:property value="queryExampaperCondition.year==2011?'class=current':''"/>><span>2011</span></a>
                       <a href="javascript:examlist('queryExampaperCondition.year','2012')" <s:property value="queryExampaperCondition.year==2012?'class=current':''"/>><span>2012</span></a>
                       <a href="javascript:examlist('queryExampaperCondition.year','2013')" <s:property value="queryExampaperCondition.year==2013?'class=current':''"/>><span>2013</span></a>
                       </li>
                        <li><h5>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</h5><a href="javaScript:examlist('queryExampaperCondition.eptype','0')" <s:property value="queryExampaperCondition.eptype==null||queryExampaperCondition.eptype==0?'class=current':''"/>><span>全部</span></a> 
						<a href="javaScript:examlist('queryExampaperCondition.eptype','1') " <s:property value="queryExampaperCondition.eptype==1?'class=current':''"/>><span>真题测试</span></a> <a href="javaScript:examlist('queryExampaperCondition.eptype','4')" <s:property value="queryExampaperCondition.eptype==4?'class=current':''"/>><span>专题测试</span></a> <a href="javaScript:examlist('queryExampaperCondition.eptype','3')" <s:property value="queryExampaperCondition.eptype==3?'class=current':''"/>><span>单元测试</span></a> <a href="javaScript:examlist('queryExampaperCondition.eptype','2')" <s:property value="queryExampaperCondition.eptype==2?'class=current':''"/>><span>仿真测试</span></a></li>
                        <li ><h5>科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目：</h5>
						
						<a href="javaScript:examlist('queryExampaperCondition.ksnId','0')"   <s:property value="queryExampaperCondition.ksnId==null||queryExampaperCondition.ksnId==0?'class=current':''"/>><span>全部</span></a>
						<!--<s:iterator value="coursesortList" id="coursesortList">
						<a href="javaScript:examlist('queryExampaperCondition.sortId','<s:property value="#coursesortList.coursesortId"/>')"   <s:property value="queryExampaperCondition.sortId==#coursesortList.coursesortId?'class=current':''"/>><span><s:property value="#coursesortList.coursesortName"/></span></a>
						<input  type="hidden"id="kemu" value='' />				
						</s:iterator>    -->
						<input type="hidden" id="sysNode" value=""/>
					   <font style="width:350px;float:left;padding-right:20px;">
						<s:iterator value="sysNodeList" id="sysNode" status ="it">
						 <a href="javascript:examlist('queryExampaperCondition.ksnId','<s:property value='#sysNode.ksnId' />')" <s:property value="queryExampaperCondition.ksnId==#sysNode.ksnId?'class=current' : ''"/>><span><s:property value="#sysNode.nodeName"/></span></a>
						</s:iterator>
						</font>
						
						</li>
						
						
                        <li><h5>排序方式：</h5><a href="javaScript:examlist('queryExampaperCondition.paixu','0')" <s:property value="queryExampaperCondition.paixu==null||queryExampaperCondition.paixu==0?'class=current':''"/>><span>全部</span></a><a href="javaScript:examlist('queryExampaperCondition.paixu','2')" <s:property value="queryExampaperCondition.paixu==2?'class=current':''"/>><span>人数从低到高</span></a> <a href="javaScript:examlist('queryExampaperCondition.paixu','1')" <s:property value="queryExampaperCondition.paixu==1?'class=current':''"/>><span>人数从高到低</span></a> <a href="javaScript:examlist('queryExampaperCondition.paixu','4')" <s:property value="queryExampaperCondition.paixu==4?'class=current':''"/>><span>难度从低到高</span></a> <a href="javaScript:examlist('queryExampaperCondition.paixu','3')" <s:property value="queryExampaperCondition.paixu==3?'class=current':''"/>><span>难度从高到低</span></a></li>
                      </ul>
                    </div>
                  </div>
                  </form>
                  <ul class="exam_list">
					<!--循环试卷-->
					<s:iterator value="page.pageResult" id="exampaper">
					
					<li>
                      <div class="exam_list_name">
                        <span class="exam_star<s:property value="#exampaper.coeffcient"/>"></span>
						<s:if test="#exampaper.coursesortName.trim()!=''">  【<span class="font_orange"> <s:property value="#exampaper.coursesortName"/></span>】<br />
						</s:if>
						<a  href=' <%=contextPath %>/exam/qstManager!getExamPaperInfo.action?epid=<s:property value="#exampaper.epId"/>' title='<s:property value="#exampaper.epName"/>'>
										  <s:property value="#exampaper.epName" /><s:if test="#exampaper.isDone>0"><span style="font-weight:bold;color:#FF6600;">(已做过)</span></s:if>
						</a><span class="exam_jifen"><img alt="积分" src="<%=importURL%>/images/usercenter/icon_jl.gif"><s:property value="jifen"/></span>
                      </div>
                     <div class="exam_list_people">
                      	<span class="e_ic04"><s:property value="#exampaper.joinNum"/>人参与</span>
                        <span class="e_ic05">
                        
                         <a id="add_<s:property value='#exampaper.epId'/>" href="javascript:void(0);"  onclick="addFavorites('<s:property value="#exampaper.epId"/>',1);"  style="display:${exampaper.flag==null||exampaper.flag==0||exampaper.flag==''?'block':'none' };">添加收藏</a>
                     	<a id="del_<s:property value='#exampaper.epId'/>" href="javascript:void(0);" onclick="delFavorites('<s:property value="#exampaper.epId"/>',0);"   style="display:${exampaper.flag==null||exampaper.flag==0||exampaper.flag==''?'none':'block' };">移除收藏</a>
                        
                        </span>
                      </div>
                    </li>
					</s:iterator>
					<!---循环试卷结束-->
					<!---zhushi---->
                  </ul>
                        <div class="bor_bdashed_hui"></div>
                        <!-- 分页页码 -->
                                        <div class="pager">
											<%--<div class="left wid-80 ptop-5">共 <span class="font_orange"><s:property value="page.totalRecord" /></span> 套试卷</div>--%>
											 <div class="pager_con"><jsp:include page="/static/usercenter/jsp/common/showPage.jsp" /></div>
										</div>
                                     <!-- 分页页码 //-->
                    </div>  
                      <div class="exam_right" style="padding-right:5px;">
                     <div class="exam_wrong">
                       <h5>最近被做错的题</h5>
                       <ul>
                       <s:iterator value="errorQstList" id="qsts">	
                       	
                        <li><em>
                        <s:if test="#qsts.epType==1">[真题]</s:if><s:if test="#qsts.epType==2">[仿真]</s:if><s:if test="#qsts.epType==3">[单元]</s:if><s:if test="#qsts.epType==4">[专题]</s:if>
                        </em>
                        <span class="font_orange">正确率：<s:property value="#qsts.percent" /></span><a href="<%=contextPath%>/exam/qstManager!getErrorQst.action?lastQstId=${qsts.qstId }&resultCur=&subjectweb=${qsts.subjectId}"><s:property value="#qsts.qstContent" /></a> </li>
                       
                       </s:iterator> 
                       		<!--  <s:property value="#subject.subjectName" />
                          <li><em>[真题]</em><span class="font_orange">正确率：34%</span>财产清查34%对主要用于连续输入若干大写字母的大写字母锁定键 </li>
                          <li><em>[仿真]</em><span class="font_orange">正确率：34%</span>会计职业道德34%对最关心企业的内在风险和报酬的会计报表使用者是</li>
                          <li><em>[单元测试]</em><span class="font_orange">正确率：34%</span>财务处理程序 30%对存款人因临时需要在规定期限内使用而开立的银行结算账户是</li>-->
                       </ul>
                     </div>
                   </div>
                    <!--
                     <div class="exam_right">

                      <div class="exam_my">

                     	<div>我的考试:<a href="<%=contextPath%>/exam/qstManager!getErrorExam.action?queryOptRecordCondition.currentPage=1">错题</a><a href="<%=contextPath %>/exam/qstManager!getExamHistroy.action?subjectIdweb=<s:property value="subjectId"/>&queryExampaperRecordCondition.currentPage=1">试卷</a></div>
                     	<div>我的收藏:<a href="<%=contextPath %>/exam/qstManager!getMyFavoritesQuestion.action?queryFavoritesCondition.currentPage=1">试题</a><a href="<%=contextPath %>/exam/qstManager!getMyFavoritesPaper.action?queryFavoritesCondition.currentPage=1">试卷</a></div>
                     </div>  
                   
                     
                     <div class="exam_study">
                       <dl>
                         <dt>最近一周学习情况</dt>
                         <dd><span class="exam_study_name">做题</span><span class="exam_study_result"><s:property value="qstRightPercent.weekTotalCount"/></span></dd>
                         <dd><span class="exam_study_name">正确率</span><span class="exam_study_result"><s:property value="qstRightPercent.weekRightCount==0?0:qstRightPercent.weekTotalCount==0?0:qstRightPercent.weekRightCount*100/qstRightPercent.weekTotalCount"/>%</span></dd>
                       </dl>
                       <dl>
                         <dt>总体学习情况</dt>
                         <dd><span class="exam_study_name">做题</span><span class="exam_study_result"><s:property value="qstRightPercent.totalCount"/></span></dd>
                         <dd><span class="exam_study_name">错题数</span><span class="exam_study_result"><s:property value="qstRightPercent.wrongTotalCount"/></span></dd>
                       </dl>
                     </div>
                   </div>
                   -->
             <div class="clear"></div>
      </div>
    </div>
  </div>