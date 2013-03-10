<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="contenter">
				<div class="kc_left">
					<div class="sec_class">
						请选择您要学习的课程&nbsp;
						<s:select name="subject.subjectId" id="subject.subjectId"
							list="subjectList" headerKey="0" headerValue="请选择" theme="simple"
							listKey="subjectId" listValue="subjectName"
							onchange="selectSubject(this.value);"></s:select>
					</div>
					<div class="part2_C">
						<!-- 重复 专业-->
						<input type='hidden' id='fristc'
							value="<s:property value='course.courseId'/>" />
						<s:iterator value="sellWayCourseList" id='sellWay' status="su">
							<s:property value='#sellWay.sellName' />
							<s:if test='#su.index == 0'>
								<div class="home_coubox">
							</s:if>
							<s:else>
								<div class="home_coubox mtop-10">
							</s:else>
							<div class="home_coutit">
								<h3>
									<s:property value='#sellWay.sellWay.sellName' />
								</h3>
								<p>
									<!-- <span>总课时：<em>50</em></span> <span>已上传：<em>26</em></span> <span>上传进度：<em>50%</em></span> -->
									<a class="view_schedule"
										href="<%=contextPath%>/cou/courseweb!getCoursePlan.action?queryCourseCondition.sellWayId=<s:property value='#sellWay.sellWay.sellId'/>">[查看课程进度]</a>
								</p>
							</div>
							<div class="clear"></div>
							<!-- <input type="hidden" value="0" id="course_last_id_107"> -->
							<!-- 重复课程 -->
							<s:iterator value="#sellWay.courseList" id='course' status="sust">
								<input type='hidden'
									id='course_last_id_<s:property value="#course.courseId"/>'
									value="<s:property value='#course.lastKpointId'/>" />
								<!-- 最后观看视频ID -->
								<input type='hidden'
									id='course_name_<s:property value="#course.courseId"/>'
									value="<s:property value='#course.title'/>" />
								<!-- 课程名称 -->
								<input type='hidden'
									id='course_totalUserNum_<s:property value="#course.courseId"/>'
									value="<s:property value='#course.studyStatisticsDTO.totalUserNum'/>" />
								<input type='hidden'
									id='course_thanOneNum_<s:property value="#course.courseId"/>'
									value="<s:property value='#course.studyStatisticsDTO.thanOneNum'/>" />
								<input type='hidden'
									id='course_thanTwoNum_<s:property value="#course.courseId"/>'
									value="<s:property value='#course.studyStatisticsDTO.thanTwoNum'/>" />
								<input type='hidden'
									id='course_thanThreeNum_<s:property value="#course.courseId"/>'
									value="<s:property value='#course.studyStatisticsDTO.thanThreeNum'/>" />
								<input type='hidden'
									id='course_userSelfNum_<s:property value="#course.courseId"/>'
									value="<s:property value='#course.studyStatisticsDTO.userSelfNum'/>" />
								<!-- 试听的隐藏灰色 -->
								<s:if test='#course.uploadedSize == 0 && isshitingdown==1'>
								</s:if>
								<s:else>

									<input type='hidden'
										id='course_last_id_<s:property value="#course.courseId"/>'
										value="<s:property value='#course.lastKpointId'/>" />
									<div class="home_cou">
										<h4>
											<s:property value='#course.title' />
										</h4>
										<p>
											<span>主讲人：<em><s:property
														value='#course.teacherName' /></em></span>&nbsp;
										<span>总课时：<em> 
											<s:if test="#course.vedioSize >= #course.lessionTime"><s:property value='#course.vedioSize' /></s:if>
											<s:else><s:property value='#course.lessionTime'  /> 
											</s:else>
											</em></span>
										<span>&nbsp;已上传：
											<em> 
											<s:property value='#course.vedioSize'/>
											</em>
										</span> 
											
										<span>&nbsp;进度：<em> 
											<s:if test="#course.vedioSize >= #course.lessionTime">100%</s:if>
											<s:else>
												<script type="text/javascript">
												document.write(getupsizeper("<s:property value='100.0 * #course.vedioSize /#course.lessionTime'/>")+"%");
												</script>
											</s:else>
											</em>
										</span>					 
										 
										</p>
										<p>
											<s:if test="#course.lastKpointId == kpoint.pointId">
												<a class="view_mulu" href="javascript:void(0)"
													id="textObj_<s:property value="#sellWay.sellWay.sellId"/>_<s:property value="#course.courseId"/>"
													onclick='onClickCourse_course(<s:property value="#sellWay.sellWay.sellId"/>,<s:property value="#course.courseId"/>,this,<s:property value="#course.subjectId"/>)'>收起课程目录</a>
											</s:if>
											<s:else>
												<a class="view_mulu" href="javascript:void(0)"
													id="textObj_<s:property value="#sellWay.sellWay.sellId"/>_<s:property value='#course.courseId'/>"
													onclick='onClickCourse_course(<s:property value="#sellWay.sellWay.sellId"/>,<s:property value="#course.courseId"/>,this,<s:property value="#course.subjectId"/>)'>查看课程目录</a>
											</s:else>
											<s:if
												test="#course.dpdfUrl != null && #course.dpdfUrl != ''  ">
												<a class="downloadPDF"
													href="<s:property value="#course.dpdfUrl"/>"
													id="downloadPDF_<s:property value='#course.courseId'/>">下载PDF讲义</a>
											</s:if>
											<s:if
												test="#course.dpptUrl != null && #course.dpptUrl !=  ''   ">
												<a class="downloadPPT"
													href="<s:property value="#course.dpptUrl"/>"
													id="downloadPPT_<s:property value='#course.courseId'/>">下载PPT讲义</a>
											</s:if>
											<s:if
												test="#course.dmp3Url != null && #course.dmp3Url !=  '' ">
												<a class="downloadMP3"
													href="<s:property value="#course.dmp3Url"/>"
													id="downloadMP3_<s:property value='#course.courseId'/>">下载音频课件</a>
											</s:if>
										</p>
										<s:if test='#course.vedioSize == 0'>
											<input type="button" class="button_4" value="开始学习" name="">
										</s:if>
										<s:else>
											<a href="<%=contextPath%>/cus/cuslimit!toKpoint.action?course.courseId=<s:property value="#course.courseId"/>&sellIds=<s:property value="#sellWay.sellWay.sellId"/>">开始学习</a>
											<input name="" type="button" value="开始学习" class="button_3"
												id="course_button_<s:property value="#course.courseId"/>" />
										</s:else>
										<div class="clearline"></div>
										<s:if test="#course.lastKpointId == kpoint.pointId">
				                    		<div class="home_couml mtop-10" style="display: block" id="course_kpointlist_<s:property value="#sellWay.sellWay.sellId"/>_<s:property value='#course.courseId'/>">
					                    </s:if>
					                    <s:else>
					                    	<div class="home_couml mtop-10" style="display: none" id="course_kpointlist_<s:property value="#sellWay.sellWay.sellId"/>_<s:property value='#course.courseId'/>">
					                    </s:else>
										 <div class="clear"></div>
                       					 </div>
									</div>
							
								</s:else>
							</s:iterator>
							<!-- 重复课程 -->
					</div>
					</s:iterator>
					<!-- 更多 -->
					<s:if test="butSellWayList !=null && butSellWayList.size > 3">
						<div
							style="clear: both; width: 100%; text-align: right; padding-top: 10px;"
							id="more_Mylesson">
							<div id="morMyless" class="moreLess"
								onclick="checkMoreMyCourse();" style="cursor: pointer;">查看更多我的课程↓</div>
						</div>
						<div id="bufferMycourse"></div>
					</s:if>
					<!-- 重复 专业 -->
				</div>
			</div>