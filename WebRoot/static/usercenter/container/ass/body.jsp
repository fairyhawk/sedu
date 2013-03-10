<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="/include/header.inc" %>
<div class="contenter">
      <div class="as_allclass">
        <h2>所有专业</h2>
        <div class="as_allclass_con">
         	<s:iterator value="subList" id="sub" >
        <a href="<%=contextPath%>/ass/assweb!toAssess.action?assess.subjectId=${sub.subjectId }" id="${sub.subjectId }"><span>${sub.subjectName }</span></a>
        </s:iterator>
      </div>
      </div>
       <s:if test="status==2 || status==1">
	      <div class="as_nodiv">
			  <p>您还未对任何课程做出评价</p>
			  <a class="as_btn" href="javascript:toMore(0,${currSub.subjectId },15)">我要评价</a>
		  </div>
	  </s:if>
	<%--   <s:if test="status==1">
		  <div class="as_nodiv">
			  <p>您目前还未观看任何课程</p>
			  <a class="as_btn"  href="<%=contextPath %>/cou/courselimit!toMyCourse.action">开始学习</a>
		  </div>
	  </s:if> --%>
	  <s:if test="status==0">
		  <div class="as_nodiv">
			  <p>您目前还未购买任何课程</p>
			  <a class="as_btn"  href="<%=contextPath %>/cou/courselimit!noCourse.action">查看课程</a>
		  </div>
      </s:if>  
      <div class="as_list">
      <div class="tab">
          <ul class="uc_tab">
            <li><a href="#uc_tabcon_goodas"><span>好评课程TOP10</span></a></li>
          </ul>
          <div id="uc_tabcon_goodas" class="uc_tabcon">
            <ul class="as_listTop">
                <s:iterator value="top10list" id="topAss" status="status">
              <li>
                <em class="top_number"><s:property value="#status.index+1"/></em>
                <a href="<%=contextPath%>/ass/assweb!toAddAssess.action?assess.subjectId=${topAss.subjectId }&assess.courseId=${topAss.courseId }&assess.sellwayId=${topAss.sellwayId }&assess.kpointId=${topAss.kpointId }&queryAssessCondition.currentPage=1" title="${topAss.kpoint.name }" >
                			<c:choose>
                        	<c:when test="${fn:length(topAss.kpoint.name)>28}">
                        	${fn:substring(topAss.kpoint.name,0,28)}……
                        	</c:when>
                        	<c:otherwise>
                        	${topAss.kpoint.name }
                        	</c:otherwise>
                        	</c:choose>
                </a>
                <div class="as_starS_box"><span class="as_starS"><span class="as_star_mask as_star_mask${fn:substring(topAss.levelAvg,0,1) }"></span></span>${topAss.levelAvg }分</div>
              </li>
              </s:iterator>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>