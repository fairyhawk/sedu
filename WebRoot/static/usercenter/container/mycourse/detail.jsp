<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="contenter">
	<div class="course_catalog_wrap">
		<h2 class="myCourse_tt">
			我的课程
			<span class="crumbs_br">&gt;&gt;</span>
			<span class="crumbs_name">${course.title}</span>
		</h2>
		<a class="back" href="<%=contextPath%>/cou/courselimit!toMyCourse.action">返回上一页</a>
		<div class="course_xq_top">
			<dl class="x_course">
				<dt class="pic">
					<a href="#">
					<s:if test="course.vedioPicUrl!=null && course.vedioPicUrl!=''">
					<img src="${course.vedioPicUrl}" width="120" height="89" alt="我的课程"><span class="play_mask"></span>
					</s:if>
					<s:else>
					<img src="http://import.highso.org.cn/images/usercenter/video_img2.jpg" width="120" height="89" alt="我的课程"><span class="play_mask"></span>
					</s:else>						
					</a>
				</dt>
				<dd class="name">
					${course.title}
				</dd>
				<dd class="teacher">
					主讲：
					<em>${course.teachername}</em>	
				</dd>
				<dd class="teacher">
					总课时：
					<em>${course.lessionTime}</em>
					已上传：
					<em>${course.realUpNum}</em>
					上传进度：
					<em>
					<s:if test="course.realUpNum >= course.lessionTime">
					100%
					</s:if>
					<s:else>
					<fmt:formatNumber value="${course.realUpNum/course.lessionTime*100} " pattern="##.#" minFractionDigits="2" ></fmt:formatNumber>%
					</s:else>				
					</em>
					
				</dd>
				<dd class="down">
				<s:if test="course.dpdfUrl != null && course.dpdfUrl !=  '' ">
					<a class="downloadPDF" href="<s:property value="course.dpdfUrl"/>">下载PDF讲义</a>
				</s:if>
				<s:if test="course.dpptUrl != null && course.dpptUrl !=  '' ">
					<a class="downloadPPT" href="<s:property value="course.dpptUrl"/>">下载PPT讲义</a>
				</s:if>
				<s:if test="course.dmp3Url != null && course.dmp3Url !=  '' ">
					<a class="downloadMP3" href="<s:property value="course.dmp3Url"/>">下载音频课件</a>
				</s:if>				
					<a class="goStudy_btn" href="javascript:void(0);" onclick="goToListenCourseTmp(${course.courseId},${course.subjectId},${sellIds});">继续学习</a>
					<a class="goPj_btn" target="_blank" href="<%=contextPath%>/ass/assweb!toAddAssess.action?assess.subjectId=${course.subjectId}&assess.courseId=${course.courseId}&assess.sellwayId=${sellIds}&queryAssessCondition.currentPage=1">我要评价</a>
				</dd>
			</dl>
		</div>
		<div class="course_catalog">
			<ul class="course_catalog_th">
				<li class="course_th_mulu">
					课程目录
				</li>
				<li class="course_th_jindu">
					我的学习进度
				</li>
				<li class="course_th_redu">
					课程学习热度
				</li>
			</ul>
			<div id="course_kpointlist_223_157" class="home_couml mtop-10">
				<div class="dtree">
					<div class="clip" id="ddree-20223">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>