<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ page import="com.shangde.common.util.DateUtil" %>
			<div class="contenter">
				<div id="lea_wrap">
					 <div id="lea_top">
	                  <div id="lea_weather">
	                   <iframe src="http://m.weather.com.cn/m/pn8/weather.htm " width="255" height="20" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" allowtransparency="true" scrolling="no"></iframe>
	                  </div>
	                  <div id="lea_time">当前时间：<span>22:33:55</span></div>
	                </div>
					<!-- 找到了学习计划 -->
					<s:if test="currentPlan!= null">
						<div id="lea_left" class="iepng">
											<div class="lea_title2">
												<h2>
													<s:property value="currentPlan.plantitle"/>
												</h2>
												<div class="lea_mod">
													<a href="<%=contextPath%>/stu/calendar!getPlanListByCalendardList.action?checkDay=<%=DateUtil.formatDate(new java.util.Date())%>&queryPlanCondition.currentPage=1"><font>学习计划列表</font></a>&nbsp;&nbsp;&nbsp;&nbsp;
													发布时间: <s:date name="currentPlan.publish" format="yyyy-MM-dd HH:mm:ss" />
													<a href="<%=contextPath%>/stu/calendar!getListPlanByCalendardGotoUpdate.action?checkDay=<s:property value='checkDay'/>">编辑</a>|
													<a href="JavaScript:delPlan();">删除</a>
												</div>
											</div>
											
											<dl class="lea_listbox iepng">
												<dt class="iepng">今日心情</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[0]" /></p>
								                    </div>
							                  	</div>
							                </dl>
											
											<dl class="lea_listbox iepng">
												<dt class="iepng">学习计划</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[1]" /></p>
								                    </div>
							                  	</div>
							                </dl>
											 
											<dl class="lea_listbox iepng">
												<dt class="iepng">记忆力、注意力</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[2]" /></p>
								                    </div>
							                  	</div>
							                </dl>
			
											<dl class="lea_listbox iepng">
												<dt class="iepng">学习效率、强度</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[3]" /></p>
								                    </div>
							                  	</div>
							                </dl>
							                				                
							                <dl class="lea_listbox iepng">
												<dt class="iepng"><s:property value="checkDay"/></dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p class="iepng face_b_<s:property value='currentPlan.picon' />"></p></p>
								                    </div>
							                  	</div>
							                </dl>
							                
							                <dl class="lea_listbox iepng">
												<dt class="iepng">比起昨天的进步</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[4]" /></p>
								                    </div>
							                  	</div>
							                </dl>
							                
											<dl class="lea_listbox iepng">
												<dt class="iepng">我的书签、备忘</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[5]" /></p>
								                    </div>
							                  	</div>
							                </dl>
							                							                 
											<dl class="lea_listbox iepng">
												<dt class="iepng">学习心得、体会</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[6]" /></p>
								                    </div>
							                  	</div>
							                </dl>
							                				                 
											<dl class="lea_listbox iepng">
												<dt class="iepng">明日计划</dt>
							                  	<div class="lea_txbox">
								                    <div class="lea_ddend"><p><s:property value="strArr[7]" /></p>
								                    </div>
							                  	</div>
							                </dl>
										</div>
					</s:if>
					<s:else>
						<!-- 没找到了学习计划 -->
						<div class="iepng" id="lea_left">
							<div class="lea_title2">
								<h2><s:property value="checkDay"/>学习计划</h2>
								<div class="lea_mod">
									<a href="<%=contextPath%>/stu/calendar!getPlanListByCalendardList.action?checkDay=<%=DateUtil.formatDate(new java.util.Date())%>&queryPlanCondition.currentPage=1">学习计划列表</a>
								</div>
							</div>
							<form onsubmit="return validateAddForm(this);" method="post"
								name="addform" action="<%=contextPath %>/stu/studyplanWeb!addPlan.action">
								<dl class="lea_listbox iepng">
									<dt class="iepng">今日心情</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" name="planitem.itemtitle" class="iepng" id="xq">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="btn1"><a class="iepng" href="javascript:void(0)">还不错</a></li>
											<li id="btn2"><a class="iepng" href="javascript:void(0)">没有</a></li>
											<li id="btn3"><a class="iepng" href="javascript:void(0)">无聊</a></li>
											<li id="btn4"><a class="iepng" href="javascript:void(0)">开心</a></li>
											<li id="btn5"><a class="iepng" href="javascript:void(0)">累啊</a></li>
											<li id="btn6"><a class="iepng" href="javascript:void(0)">有点冷</a></li>
											<li id="btn7"><a class="iepng" href="javascript:void(0)">给力</a></li>
										</ul>
									</dd>
									<input type="hidden" value="0" name="plan.pstatus">
									<!-- 计划状态，默认是未审核  （1=发布 2=删除 0=未审核） -->
									<input type="hidden" value="<s:property value='checkDay'/>" name="plan.opendate">
								</dl>

								<dl class="lea_listbox iepng">
									<dt class="iepng">学习计划</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" name="planitem.itemtitle" class="iepng" id="jh">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="bt1"><a class="iepng" href="javascript:void(0)">看视频</a></li>
											<li id="bt2"><a class="iepng" href="javascript:void(0)">看书</a></li>
											<li id="bt3"><a class="iepng" href="javascript:void(0)">做试卷</a></li>
											<li id="bt4"><a class="iepng" href="javascript:void(0)">复习</a></li>
											<li id="bt5"><a class="iepng" href="javascript:void(0)">预习</a></li>
											<li id="bt6"><a class="iepng" href="javascript:void(0)">没计划</a></li>
											<li id="bt7"><a class="iepng" href="javascript:void(0)">今天我休息</a></li>
										</ul>
									</dd>
								</dl>

								<dl class="lea_listbox iepng">
									<dt class="iepng">记忆力、注意力</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" name="planitem.itemtitle" class="iepng" id="jyl">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="b1"><a class="iepng" href="javascript:void(0)">还行啊</a></li>
											<li id="b2"><a class="iepng" href="javascript:void(0)">老忘事</a></li>
											<li id="b3"><a class="iepng" href="javascript:void(0)">非常好</a></li>
											<li id="b4"><a class="iepng" href="javascript:void(0)">啥都记不住</a></li>
											<li id="b5"><a class="iepng" href="javascript:void(0)">没啥想说的</a></li>
										</ul>
									</dd>
								</dl>

								<dl class="lea_listbox iepng">
									<dt class="iepng">学习效率、强度</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" class="iepng" name="planitem.itemtitle" id="xlqd">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="bu1"><a class="iepng" href="javascript:void(0)">还行</a></li>
											<li id="bu2"><a class="iepng" href="javascript:void(0)">不错</a></li>
											<li id="bu3"><a class="iepng" href="javascript:void(0)">有点低</a></li>
											<li id="bu4"><a class="iepng" href="javascript:void(0)">很高效</a></li>
											<li id="bu5"><a class="iepng" href="javascript:void(0)">说不好</a></li>
											<li id="bu6"><a class="iepng" href="javascript:void(0)">没有</a></li>
										</ul>
									</dd>
								</dl>

								<dl class="lea_listbox iepng">
									<dt class="iepng">
										<s:property value="checkDay"/> <input type="hidden" value="1" id="pic"
											name="plan.picon">
									</dt>
									<dd class="lea_exp">
										<ul>
											<li id="pic1"><a title="开心" onclick="selectedclcik(1)"
												class="face_1 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp1"
													onclick="selectedclcik(1)"></span> </a></li>
											<li id="pic2"><a title="汗" onclick="selectedclcik(2)"
												class="face_2 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp2"
													onclick="selectedclcik(2)"></span> </a></li>
											<li id="pic3"><a title="流泪" onclick="selectedclcik(3)"
												class="face_3 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp3"
													onclick="selectedclcik(3)"></span> </a></li>
											<li id="pic4"><a title="不高兴" onclick="selectedclcik(4)"
												class="face_4 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp4"
													onclick="selectedclcik(4)"></span> </a></li>
											<li id="pic5"><a title="怒" onclick="selectedclcik(5)"
												class="face_5 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp5"
													onclick="selectedclcik(5)"></span> </a></li>
											<li id="pic6"><a title="笑" onclick="selectedclcik(6)"
												class="face_6 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp6"
													onclick="selectedclcik(6)"></span> </a></li>
											<li id="pic7"><a title="困" onclick="selectedclcik(7)"
												class="face_7 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp7"
													onclick="selectedclcik(7)"></span> </a></li>
											<li id="pic8"><a title="吓" onclick="selectedclcik(8)"
												class="face_8 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp8"
													onclick="selectedclcik(8)"></span> </a></li>
											<li id="pic9"><a title="酷" onclick="selectedclcik(9)"
												class="face_9 iepng" hidefocus="true"
												href="javascript:void(0)"><span id="lea_expsp9"
													onclick="selectedclcik(9)"></span> </a></li>
										</ul>
									</dd>
								</dl>
								<dl class="lea_listbox iepng">
									<dt class="iepng">比起昨天的进步</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" class="iepng"
											name="planitem.itemtitle" id="jb">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="but1"><a class="iepng" href="javascript:void(0)">看书了</a></li>
											<li id="but2"><a class="iepng" href="javascript:void(0)">看视频了</a></li>
											<li id="but3"><a class="iepng" href="javascript:void(0)">效率提高了</a></li>
											<li id="but4"><a class="iepng" href="javascript:void(0)">没啥想说的</a></li>
										</ul>
									</dd>
								</dl>
								<dl class="lea_listbox iepng">
									<dt class="iepng">我的书签、备忘</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" class="iepng"
											name="planitem.itemtitle" id="shuqian">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="butt1"><a class="iepng"
												href="javascript:void(0)">报名</a></li>
											<li id="butt2"><a class="iepng"
												href="javascript:void(0)">查成绩</a></li>
											<li id="butt3"><a class="iepng"
												href="javascript:void(0)">忘记了</a></li>
											<li id="butt4"><a class="iepng"
												href="javascript:void(0)">没有</a></li>
											<li id="butt5"><a class="iepng"
												href="javascript:void(0)">参加考试</a></li>
										</ul>
									</dd>
								</dl>
								<dl class="lea_listbox iepng">
									<dt class="iepng">学习心得、体会</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" class="iepng"
											name="planitem.itemtitle" id="xinde">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="btt1"><a class="iepng" href="javascript:void(0)">长知识</a></li>
											<li id="btt2"><a class="iepng" href="javascript:void(0)">太难了</a></li>
											<li id="btt3"><a class="iepng" href="javascript:void(0)">没明白</a></li>
											<li id="btt4"><a class="iepng" href="javascript:void(0)">效果不错</a></li>
											<li id="btt5"><a class="iepng" href="javascript:void(0)">没啥</a></li>
										</ul>
									</dd>
								</dl>
								<dl class="lea_listbox iepng">
									<dt class="iepng">明日计划</dt>
									<dd class="lea_textdd">
										<input type="text" value="请在此输入文字" class="iepng"
											name="planitem.itemtitle" id="tmrplan">
									</dd>
									<dd class="lea_uldd">
										<ul>
											<li id="buu1"><a class="iepng" href="javascript:void(0)">看视频</a></li>
											<li id="buu2"><a class="iepng" href="javascript:void(0)">做试卷</a></li>
											<li id="buu3"><a class="iepng" href="javascript:void(0)">复习</a></li>
											<li id="buu4"><a class="iepng" href="javascript:void(0)">预习</a></li>
											<li id="buu5"><a class="iepng" href="javascript:void(0)">好好学习</a></li>
											<li id="buu6"><a class="iepng" href="javascript:void(0)">没计划</a></li>
										</ul>
									</dd>
								</dl>
								<div id="len_btnbox">
									<input type="submit" value="提交" name="" class="iepng">
									<input type="button" onclick="clearForm('addform')" value="重置"
										name="" class="iepng">
								</div>
							</form>
						</div>
					</s:else>


					<div class="iepng" id="lea_right">
						<div>
							<s:if test="calendarBuffer != null">
								<div class="hasDatepicker iepng" id="datepicker">
									<div
										class="ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
										<div
											class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all iepng">
											<a class="ui-datepicker-prev ui-corner-all iepng"
												href="<%=contextPath%>/stu/calendar!getListPlanByCalendardChangMonth.action?year=<s:property value="year"/>&month=<s:property value="month"/>&prev=-1"
												title="&lt;上月"> <span
												class="ui-icon ui-icon-circle-triangle-w">&lt;上月</span>
											</a> <a class="ui-datepicker-next ui-corner-all iepng"
												href="<%=contextPath%>/stu/calendar!getListPlanByCalendardChangMonth.action?year=<s:property value="year"/>&month=<s:property value="month"/>&next=1"
												title="下月&gt;"> <span
												class="ui-icon ui-icon-circle-triangle-e">下月&gt;</span>
											</a>
											<div class="ui-datepicker-title">
												<s:property value="titleCalendar" />
											</div>
										</div>
										<table class="ui-datepicker-calendar">
											<thead>
												<tr>
													<th class="ui-datepicker-week-end"><span title="星期日">日</span>
													</th>
													<th><span title="星期一">一</span></th>
													<th><span title="星期二">二</span></th>
													<th><span title="星期三">三</span></th>
													<th><span title="星期四">四</span></th>
													<th><span title="星期五">五</span></th>
													<th class="ui-datepicker-week-end"><span title="星期六">六</span>
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
							<span><s:property value="checkDay.substring(5,10)" />日提醒</span>
						</h2>

						<s:if test="planclockListByDay!= null">
							<s:if test="planclockListByDay.size()>5">
								<ul id="list">
									<s:iterator value="planclockListByDay" id="planclock"
										status="num">
										<s:if test="%{#planclock.ccontent.length()>12}">
											<li class="ccontent"><s:property
													value="#planclock.ccontent.substring(0,12)" />
												<s:if test="#num.index < 2">
													<img src="<%=importURL%>/images/usercenter/len_hot.gif"
														alt="Hot" />
												</s:if></li>
										</s:if>
										<s:else>
											<li class="ccontent"><s:property
													value="#planclock.ccontent" />
												<s:if test="#num.index < 2">
													<img src="<%=importURL%>/images/usercenter/len_hot.gif"
														alt="Hot" />
												</s:if></li>
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
								<li id="coursedo">您这天学习课程了吗？ <span
									onclick="checkCourseNo('<s:property value="checkDay"/>');" class="len_no"><a
										href="javascript:void(0);">没有</a></span> <span
									onclick="checkCourseYes('<s:property value="checkDay"/>');" class="len_yes"><a
										href="javascript:void(0);">做了</a></span>
								</li>
							</ul>
						</s:if>
						<s:if test="examSummaryListByDay == null">
							<ul class="lea_rul3">
								<li id="examdo">
									<div>
										您这天作试卷了吗？ <span onclick="checkExamNo('<s:property value="checkDay"/>');"
											class="len_no"><a href="javascript:void(0);">没有</a></span> <span
											onclick="checkExamYes('<s:property value="checkDay"/>');" class="len_yes"><a
											href="javascript:void(0);">做了</a></span>
									</div>
								</li>
							</ul>
						</s:if>

						<s:if test="courseSummaryList == null">
							<ul style="color: black; display: none;" id="resCourseText">
								<!-- 课程总结 -->
							</ul>
						</s:if>

						<s:if test="courseSummaryList != null">
								<s:iterator value="courseSummaryList" id="courseSummary" status="courseNum">
									<s:if test="#courseNum.index < 5" >
										<s:if test="%{#courseSummary.pointName.length()>5}">
											<ul>
												<li><div id="resCourseText2">您这天学习了<font style="color:red;"><s:property value="#courseSummary.pointName.substring(0,5)+'...'"/></font>课程。</div></li>
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
												<li><div id="resCourseText2">您这天学习了<font style="color:red;"><s:property value="#courseSummary.pointName"/></font>课程。</div></li>
											</ul>  
										</s:else> 
									</s:if>
								</s:iterator>	
						</s:if>	
						
						<s:if test="examSummaryListByDay== null">
						<ul style="color: black; display: none;" id="resExamText">
							<!--试卷总结 -->
						</ul>
						</s:if>

						<s:if test="examSummaryListByDay != null">
							<s:iterator value="examSummaryListByDay" id="examSummary" status="examNum">
								<s:if test="#examNum.index < 4" >
									<s:if test="%{#examSummary.examname.length()>6}">
										<ul>
											<li><div id="resExamText2" style="position:relative;">您这天做了<font style="color:red;"><s:property value="#examSummary.examname.substring(0,6)+'...'"/></font>试卷。</div></li>
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
											<li><div id="resExamText2">您这天做了<font style="color:red;"><s:property value="#examSummary.examname"/></font>试卷。</div></li>
										</ul>   
									</s:else> 
												
								</s:if>
							</s:iterator>
						</s:if>
					</div>
				</div>
			</div>
		</div>
		</div>
