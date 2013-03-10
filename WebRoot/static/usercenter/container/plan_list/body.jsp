<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<div class="contenter">
    <div id="lea_wrap">
	<!-- 没找到了学习计划 -->
		<div class="iepng" id="lea_left">
			<div class="iepng" id="lea_left">
				<div class="lea_tabbox">
					<ul>
						<li id="ntd1" class="tab_current iepng">
							学习计划
						</li>
						<!-- 
						<a href="/stu/calendar!getPlanListByCalendardList.action?checkDay=2012-06-29&queryPlanCondition.currentPage=1"><font color="FFFFF">学习计划列表</font></a>
						<li id=ntd2 onClick="setTab('ntd',2,2)">
							学习轨迹
						</li>
						 -->
					</ul>
					<span id="back"><a href="javascript:history.go(-1);">&lt;&lt;返回上一页</a></span>
				</div>
		<div class="lea_listdiv2">
			<div id="con_ntd_1">
				<ul class="lea_jh_ul">
					<s:if test="page.pageResult != null">
						<s:iterator value="page.pageResult" id="planList">
							<li>
								<input type="checkbox" name="checkID" id="checkID<s:property value="#planList.planId"/>"  value="<s:property value="#planList.planId" />" />
								<a href="<%=contextPath%>/stu/calendar!getListPlanByCalendardGoto.action?checkDay=<s:property value="#planList.plantitle.substring(0,10)"/>"><s:property value="#planList.plantitle" /></a><span
						class="lea_list_t"><s:date name="#planList.publish" format="yyyy-MM-dd HH:mm:ss" /></span>
							</li>
						</s:iterator>
					</s:if>
				</ul>
				<div class="lea_allcheck">
					<!-- <input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll()" value="" /> -->
					<a href="javascript:checkAll();">全选</a> |
					<!-- <input type="checkbox" name="checkRev" id="checkRev" onclick="checkRev()" value="" /> -->
					<a href="javascript:checkRev();">全不选</a> |
					<a href="javascript:void(0);" onclick="batchProcess()">删除</a>
				</div>
			
				<div class="lea_switch_page">
					<!-- 分页页码 -->
				        <div class="manu">
						<jsp:include page="/static/usercenter/jsp/common/showPage.jsp" />
					</div>
				    <!-- 分页页码 //-->
				</div>
			</div>
		<div id="con_ntd_2" style="display: none">
			<div class="lea_checkmw">
				<select name="">
					<option>
						本月
					</option>
					<option>
						一月
					</option>
				</select>
			</div>
	   		<div class="lea_mw_datetable"></div>
		</div>

	</div>
	</div>
</div>
	
	<!-- 右边部分 -->
	<div id="lea_right" class="iepng">
		<div>
		<s:if test="calendarBuffer != null">
			<div id="datepicker" class="hasDatepicker iepng">
			<div class="ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
				<div class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all iepng">
					<a title="&lt;上月"
			href="<%=contextPath%>/stu/calendar!getPlanListByCalendardChangMonth.action?year=<s:property value="year"/>&month=<s:property value="month"/>&prev=-1"
			class="ui-datepicker-prev ui-corner-all iepng">
			<span class="ui-icon ui-icon-circle-triangle-w">&lt;上月</span>
					</a>
					<a title="下月&gt;"
			href="<%=contextPath%>/stu/calendar!getPlanListByCalendardChangMonth.action?year=<s:property value="year"/>&month=<s:property value="month"/>&next=1"
			class="ui-datepicker-next ui-corner-all iepng">
			<span class="ui-icon ui-icon-circle-triangle-e">下月&gt;</span>
					</a>
					<div class="ui-datepicker-title">
			<s:property value="titleCalendar" />
					</div>
				</div>
				<table class="ui-datepicker-calendar">
					<thead>
			<tr>
				<th class="ui-datepicker-week-end">
					<span title="星期日">日</span>
				</th>
				<th>
					<span title="星期一">一</span>
				</th>
				<th>
					<span title="星期二">二</span>
				</th>
				<th>
					<span title="星期三">三</span>
				</th>
				<th>
					<span title="星期四">四</span>
				</th>
				<th>
					<span title="星期五">五</span>
				</th>
				<th class="ui-datepicker-week-end">
					<span title="星期六">六</span>
				</th>
			</tr>
					</thead>
					<tbody>
			<%=request.getAttribute("calendarBuffer")%>
					</tbody>
				</table>
			</div>
					</div>
		</s:if>
		</div>
		<h2 class="lea_rem">
			<span><s:property value="checkDay.substring(5,10)"/>日提醒</span>
		</h2>
			<s:if test="planclockListByDay!= null">
				<s:if test="planclockListByDay.size()>5">
					<ul id="list" style="height:113px;width:190px;position:relative;overflow:hidden;">
						<s:iterator value="planclockListByDay" id="planclock" status="num" >
							<s:if test="%{#planclock.ccontent.length()>12}">
								<li class="ccontent"><s:property value="#planclock.ccontent.substring(0,12)"/><s:if test="#num.index < 2" ><img src="<%=importURL%>/images/usercenter/len_hot.gif" alt="Hot" /></s:if></li>
							</s:if>
							<s:else>
								<li class="ccontent"><s:property value="#planclock.ccontent"/><s:if test="#num.index < 2" ><img src="<%=importURL%>/images/usercenter/len_hot.gif" alt="Hot" /></s:if></li>
							</s:else>
						</s:iterator>
					</ul>
					<script type="text/javascript">  
					function scroll(element, delay, speed, lineHeight) {
						var numpergroup = 5;
						var slideBox = (typeof element == 'string')?document.getElementById(element):element;
						var delay = delay||1000;
						var speed=speed||20;
						var lineHeight = lineHeight||20;
						var tid = null, pause = false;
						var liLength = slideBox.getElementsByTagName('li').length;
						var lack = numpergroup-liLength%numpergroup;
						
						for(i=0;i<lack;i++){
							//slideBox.appendChild(document.createElement("li"));
						}
						var start = function() {
							 tid=setInterval(slide, speed);
						}
						var slide = function() {
							 if (pause) return;
							 slideBox.scrollTop += 2;
							 if ( slideBox.scrollTop % lineHeight == 0 ) {
						clearInterval(tid);
						for(i=0;i<numpergroup;i++){
							slideBox.appendChild(slideBox.getElementsByTagName('li')[0]);
						}
						slideBox.scrollTop = 0;
						setTimeout(start, delay);
							 }
						}
						slideBox.onmouseover=function(){pause=true;}
						slideBox.onmouseout=function(){pause=false;}
						setTimeout(start, delay);
					}
					scroll('list', 60000, 1, 20 );//停留时间，相对速度（越小越快）,每次滚动多少，最好和Li的Line-height一致。 
					
				</script>  
				</s:if>
				<s:else>
					<ul style="height:113px;width:190px;position:relative;overflow:hidden;">
						<s:iterator value="planclockListByDay" id="planclock" status="num" >
							<s:if test="%{#planclock.ccontent.length()>12}">
								<li class="ccontent"><s:property value="#planclock.ccontent.substring(0,12)"/><s:if test="#num.index < 2" ><img src="<%=importURL%>/images/usercenter/len_hot.gif" alt="Hot" /></s:if></li>
							</s:if>
							<s:else>
								<li class="ccontent"><s:property value="#planclock.ccontent"/><s:if test="#num.index < 2" ><img src="<%=importURL%>/images/usercenter/len_hot.gif" alt="Hot" /></s:if></li>
							</s:else>
						</s:iterator>
					</ul>
				</s:else>
			</s:if>
		<s:else>
			<ul>
				<li>暂无</li>
			</ul>
		</s:else>
		
		<h2 class="lea_sum">
			<span><s:property value="checkDay.substring(5,10)"/>日总结</span>
		</h2>
		
		<s:if test="courseSummaryList == null">
		<ul class="lea_rul3">
			<li id="coursedo">
				您这天学习课程了吗？
				<span class="len_no"  onclick="checkCourseNo('<s:property value="checkDay"/>');" ><a href="javascript:void(0);">没有</a></span>
				<span class="len_yes"  onclick="checkCourseYes('<s:property value="checkDay"/>');" ><a href="javascript:void(0);">做了</a></span>
			</li>
		</ul>	
		</s:if>
		<s:if test="examSummaryListByDay == null">
			<ul class="lea_rul3">
				<li id="examdo">
					<div>您这天作试卷了吗？
			<span class="len_no" onclick="checkExamNo('<s:property value="checkDay"/>');"><a href="javascript:void(0)">没有</a></span>
			<span class="len_yes" onclick="checkExamYes('<s:property value="checkDay"/>');"><a href="javascript:void(0)">做了</a></span>
					</div>
				</li>
			</ul>	
		</s:if>	
		
		<s:if test="courseSummaryList == null">
			<ul  id="resCourseText" style="color:black; display:none;">
				<!-- 课程总结 -->
			</ul>
		</s:if>
		
		<s:if test="courseSummaryList != null">
			<s:iterator value="courseSummaryList" id="courseSummary" status="courseNum">
				<s:if test="#courseNum.index < 5" >
					<s:if test="%{#courseSummary.pointName.length()>5}">
			<ul>
				<li><div id="resCourseText2">您这天学习了<font style="color:yellow;"><s:property value="#courseSummary.pointName.substring(0,5)+'...'"/></font>课程。</div></li>
			</ul>
					</s:if>
					<!-- 第二次点击时 -->
					<s:elseif test="%{#courseSummary.pointName == '没有学习'}">
			<ul>
				<li><div id="resCourseText2">您这天<s:property value="#courseSummary.pointName"/>课程。</div></li>
			</ul>
					</s:elseif>
					 <!-- 否则全部显示 -->       
					<s:else>
			<ul>         
				<li><div id="resCourseText2">您这天学习了<font style="color:yellow;"><s:property value="#courseSummary.pointName"/></font>课程。</div></li>
			</ul>  
					</s:else> 
				</s:if>
			</s:iterator>	
		</s:if>	
	 
	<s:if test="examSummaryListByDay== null">
		<ul id="resExamText" style="color:black; display:none;">
		<!--试卷总结 -->
		</ul>
	</s:if>
	
	<s:if test="examSummaryListByDay != null">
		<s:iterator value="examSummaryListByDay" id="examSummary" status="examNum">
			<s:if test="#examNum.index < 4" >
				<s:if test="%{#examSummary.examname.length()>6}">
					<ul>
			<li><div id="resExamText2">您这天做了<font style="color:yellow;"><s:property value="#examSummary.examname.substring(0,6)+'...'"/></font>试卷。</div></li>
					</ul>
				</s:if>
				
				<!-- 	第二次点击时 -->
				<s:elseif test="%{#examSummary.examname == '没有做'}">
					<ul>
			<li><div id="resExamText2">您这天<s:property value="#examSummary.examname"/>试卷。</div></li>
					</ul>
				</s:elseif>
				
				<!-- 否则全部显示 -->       
				<s:else>       
				    <ul>
			<li><div id="resExamText2">您这天做了<font style="color:yellow;"><s:property value="#examSummary.examname"/></font>试卷。</div></li>
					</ul>   
				</s:else> 
			
			</s:if>
		</s:iterator>
	</s:if>
	</div>

	</div>
	</div>
	</td>
	</tr>
	</table>
</div>