<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="contenter">
      <div class="kc_left">
        <h2 class="myCourse_tt">我的课程</h2>
        <div class="sec_class">请选择您要学习的课程&nbsp;
				<s:select name="subject.subjectId" id="subject.subjectId"
							list="subjectList" headerKey="0" headerValue="请选择" theme="simple"
							listKey="subjectId" listValue="subjectName"
							onchange="selectSubject(this.value);"></s:select>
        </div>
        
        <div class="menuTab_box">
          <ul class="menuTab">
            <li style="width:202px;" class="current" id="allli"><div class="menuTab_a"><p class="menuTab_name">课时</p><p class="menuTab_num"><a href="javascript:void(0)">${courseTime}</a></p></div></li>
            <li style="width: 202px;" id="wathcedli" class=""><div style="width:202px;" class="menuTab_a"><p class="menuTab_name" >已经学习课时</p><p class="menuTab_num"><a href="javascript:void(0)">${learningTime}</a></p></div></li>
            <li style="width: 204px;" id="freeli" class="last"><div style="width:202px;" class="menuTab_a"><p class="menuTab_name">免费课时</p><p class="menuTab_num"><a href="javascript:void(0)">${freeTime}</a></p></div></li>
          </ul>
        </div>
        <div class="part2_C">
          <div class="home_coubox">
          <s:iterator id="sellWayPkg" value="sellWayCourseList" status="indexSell">
          		 <div class="home_coutit">
              	 <h3>${sellWayPkg.sellWay.sellName}</h3>
                 <!-- <p><span>总课时：<em>50</em></span><span>已上传：<em>26</em></span><span>上传进度：<em>50%</em></span><a href="#" class="view_schedule">[查看课程进度]</a></p> -->
                 </div>
            	 <div class="clear"></div>
            	 <s:iterator id="course" value="#sellWayPkg.courseList" status="indexCourse" >
            	 	<s:if test="#indexCourse.index==1">
        				<input id="fristc" type="hidden" value="${course.courseId}">
        			</s:if>
	        			<input type='hidden' id='course_last_id_<s:property value="#course.courseId"/>' value="<s:property value='#course.lastKpointId'/>" />
						<input type='hidden' id='course_name_<s:property value="#course.courseId"/>' value="<s:property value='#course.title'/>" />
						<input type='hidden' id='course_totalUserNum_<s:property value="#course.courseId"/>' value="<s:property value='#course.studyStatisticsDTO.totalUserNum'/>" />
						<input type='hidden' id='course_thanOneNum_<s:property value="#course.courseId"/>' value="<s:property value='#course.studyStatisticsDTO.thanOneNum'/>" />
						<input type='hidden' id='course_thanTwoNum_<s:property value="#course.courseId"/>' value="<s:property value='#course.studyStatisticsDTO.thanTwoNum'/>" />
						<input type='hidden' id='course_thanThreeNum_<s:property value="#course.courseId"/>' value="<s:property value='#course.studyStatisticsDTO.thanThreeNum'/>" />
						<input type='hidden' id='course_userSelfNum_<s:property value="#course.courseId"/>' value="<s:property value='#course.studyStatisticsDTO.userSelfNum'/>" />
	            	<div class="home_cou" id="course${course.courseId}">
	                <div class="pic"><a href="<%=contextPath%>/cus/cuslimit!toKpoint.action?course.courseId=${course.courseId}&sellIds=${course.sellId}">
	                <s:if test="#course.vedioPicUrl!=null&&#course.vedioPicUrl!=''"><img width="120" height="89" alt="我的课程" src="${course.vedioPicUrl}"></s:if><s:else><img width="120" height="89" alt="我的课程" src="<%=importURL%>/images/usercenter/video_img2.jpg"></s:else><span class="play_mask"></span></a></div>
	                <h4>${course.title}</h4>
	                <p><span>主讲人：<em><s:if test="#course.teacherName!=null&&#course.teacherName.trim().length() > 3"><s:property value="#course.teacherName.trim().substring(0,3)+'..'" /></s:if><s:else>${course.teacherName}</s:else></em></span>
	                <span>课时：<em><s:if test="#course.allCourseURL >= #course.lessionTime">${course.allCourseURL}</s:if><s:else>${course.lessionTime}</s:else></em></span>&nbsp;<span>已上传：<em>${course.allCourseURL}</em></span></p>
	                <p>
	                <s:if test="#course.allCourseURL > 0">
	                	<a href="<%=contextPath%>/cus/cuslimit!toKpoint.action?course.courseId=${course.courseId}&sellIds=${course.sellId}" class="view_mulu">查看课程目录</a>
	                </s:if>
	                <s:else>
	                	课程上传中
	                </s:else>
	                <s:if test="#course.dpdfUrl != null&&#course.dpdfUrl!=''"><a href="${course.dpdfUrl}" class="downloadPDF">下载PDF</a></s:if> <s:if test="#course.dpptUrl != null&&#course.dpptUrl!=''"> <a href="${course.dpptUrl}" class="downloadPPT">下载PPT</a></s:if> <s:if test="#course.dmp3Url!=null&&#course.dmp3Url!=''"> <a href="${course.dmp3Url}" class="downloadMP3">下载音频课件</a></s:if></p>
	                <s:if test="#course.allCourseURL > 0">	
	                	<input type="button" name="" onclick="goToListenCourseTmp(${course.courseId},${course.subjectId},${course.sellId})" value="开始学习" class="button_3">
	                </s:if>
	                <s:else>
	                	<input type="button" name=""  value="开始学习" class="button_4">
	                </s:else>
	                <div class="clear"></div>
	                </div>
	              </s:iterator>
          </s:iterator>
            </div>
            <div id="freeSell">
            	 <div class="home_coutit" id="freeCourse">
              	 <h3>免费课程</h3>
                 <!-- <p><span>总课时：<em>50</em></span><span>已上传：<em>26</em></span><span>上传进度：<em>50%</em></span><a href="#" class="view_schedule">[查看课程进度]</a></p> -->
                 </div>
                 <div class="clear"></div>
             <s:iterator id="course" value="freeList">
                 <div class="home_cou">
	                <div class="pic">
	                <s:if test="#course.allCourseURL > 0">
	                <a href="<%=contextPath%>/cus/cuslimit!toKpoint.action?course.courseId=${course.courseId}&sellIds=${course.sellId}">
	                </s:if>
	                <s:else>
	                <a href="javascript:void(0)">
	                </s:else>
	                <s:if test="#course.vedioPicUrl!=null&&#course.vedioPicUrl!=''"><img width="120" height="89" alt="我的课程" src="${course.vedioPicUrl}"></s:if><s:else><img width="120" height="89" alt="我的课程" src="<%=importURL%>/images/usercenter/video_img2.jpg"></s:else><span class="play_mask"></span></a></div>
	                <h4>${course.title}</h4>
	                <p><span>主讲人：<em><s:if test="#course.teacherName!=null&&#course.teacherName.trim().length() > 3"><s:property value="#course.teacherName.trim().substring(0,3)+'..'" /></s:if><s:else>${course.teacherName}</s:else></em></span>
	                <span>课时：<em><s:if test="#course.allCourseURL >= #course.lessionTime">${course.allCourseURL}</s:if><s:else>${course.lessionTime}</s:else></em></span>&nbsp;<span>已上传：<em>${course.allCourseURL}</em></span></p>
	                <p>
	                <s:if test="#course.allCourseURL > 0">
	                	<a href="<%=contextPath%>/cus/cuslimit!toKpoint.action?course.courseId=${course.courseId}&sellIds=${course.sellId}" class="view_mulu">查看课程目录</a>
	                </s:if>
	                <s:else>
	                	课程上传中
	                </s:else>
	                <s:if test="#course.dpdfUrl != null&&#course.dpdfUrl!=''"><a href="${course.dpdfUrl}" class="downloadPDF">下载PDF</a></s:if> <s:if test="#course.dpptUrl != null&&#course.dpptUrl!=''"> <a href="${course.dpptUrl}" class="downloadPPT">下载PPT</a></s:if> <s:if test="#course.dmp3Url!=null&&#course.dmp3Url!=''"> <a href="${course.dmp3Url}" class="downloadMP3">下载音频课件</a></s:if></p>
	                <input type="button" name="" onclick="goToListenCourseTmp(${course.courseId},${course.subjectId},${course.sellId})" value="开始学习" class="button_3">
	                <div class="clear"></div>
	                </div>
	      </s:iterator>
	      </div>
          </div>
        </div>   

