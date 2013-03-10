<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
 <div class="contenter">
      <div class="test_con">
      <div style="position:relative;" id="" class="bor1blue xxlx">
        <h4 class="test_tit">学习类型测试</h4>
        <!--学习类型测试题 开始-->
        <div id="falshDiv">
											<div class="tsinfo" id="test_info">
												<div class="tsinfoml"></div>
												<font class="close"></font>这不是游戏，也不仅仅是心理测试。这是HighSo根据每年数万学员的学习特点总结出的
												<span class="font_red">类型测试</span>，请认真参与！
											</div>
											<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
												width="778" height="250" id="videoPlayer" style="padding:10px 0px 0px 0px;position:absolute;">
												<param name="movie"
													value="<%=contextPath%>/static/flex/LearningTypeTest.swf?tested=<s:property value="studyTypePram"/>&url=<%=contextPath%>&importURL=<%=importURL%>" />
												<param name="quality" value="high" />
												<param name="bgcolor" value="#ffffff" />
												<param name="allowScriptAccess" value="always" />
												<param name="allowFullScreen" value="true" />
												<param name="wmode" value="transparent" />
												<!--[if !IE]>-->
												<object type="application/x-shockwave-flash"
													wmode="transparent"
													data="<%=contextPath%>/static/flex/LearningTypeTest.swf?tested=<s:property value="studyTypePram"/>&url=<%=contextPath%>&importURL=<%=importURL%>"
													width="778" height="250" id="videoPlayer_FF">
													<param name="quality" value="high" />
													<param name="bgcolor" value="#ffffff" />
													<param name="allowScriptAccess" value="always" />
													<param name="allowFullScreen" value="true" />
													<param name="wmode" value="transparent" />
													<!--<![endif]-->
													<!--[if gte IE 6]>-->
													<p>
														Either scripts and active content are not permitted to run
														or Adobe Flash Player version 10.1.0 or greater is not
														installed.
													</p>
													<!--<![endif]-->
													<a href=" http://www.adobe.com/go/getflashplayer"> <img
															src=" http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"
															alt="Get Adobe Flash Player" /> </a>
													<!--[if !IE]>-->
												</object>
												<!--<![endif]-->
											</object>
										</div>
        <!--学习类型测试题 结束-->
        
        <!--学习类型测试 测试结果 开始-->
          <div id="studyTypeResult" style="position: absolute; background-image: initial; background-attachment: initial; background-origin: initial; background-clip: initial; background-color: rgb(255, 255, 255); display: block; background-position: initial initial; background-repeat: initial initial; ">
											<!-- 学习类型测试 测试结果 开始-->
											<div class="zsboard1 mr10 xxff">
												<div class="title"></div>
												<p class="ml10" id="studyTypeTip">您属于带有习惯性拖延的类型，其中四项元素比</p>
												<div class="cent">
													<div id="barChart" style="width: 370px; height: 150px; margin: 0 auto"><div class="highcharts-container" id="highcharts-0" style="position: relative; overflow-x: hidden; overflow-y: hidden; width: 370px; height: 150px; text-align: left; font-family: 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif; font-size: 12px; "><svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="370" height="150"><defs><clipPath id="highcharts-1"><rect x="0" y="0" width="107" height="306" fill="none"></rect></clipPath></defs><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-grid" zIndex="1"><path d="M 54.5 10 L 54.5 117" fill="none" stroke="#C0C0C0" stroke-width="1"></path><path d="M 131.5 10 L 131.5 117" fill="none" stroke="#C0C0C0" stroke-width="1"></path><path d="M 207.5 10 L 207.5 117" fill="none" stroke="#C0C0C0" stroke-width="1"></path><path d="M 284.5 10 L 284.5 117" fill="none" stroke="#C0C0C0" stroke-width="1"></path><path d="M 360.5 10 L 360.5 117" fill="none" stroke="#C0C0C0" stroke-width="1"></path></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series" width="306" height="107" transform="translate(360,117) rotate(90) scale(-1,1)" clip-path="url(http://highso.cn/exam/qstManager!getExampaperAnalysisDTO.action#highcharts-1)" visibility="visible"><rect x="87.205" y="31" width="12" height="275" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true" stroke-opacity="0.05" transform="translate(-1,-1)"></rect><rect x="87.205" y="31" width="12" height="275" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true" stroke-opacity="0.1" transform="translate(-1,-1)"></rect><rect x="87.205" y="31" width="12" height="275" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true" stroke-opacity="0.15000000000000002" transform="translate(-1,-1)"></rect><rect x="87.205" y="31" width="12" height="275" r="0" rx="0" ry="0" fill="#4572A7" stroke="#FFFFFF" stroke-width="1" fill-opacity="1"></rect><rect x="60.455" y="169" width="12" height="137" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true" stroke-opacity="0.05" transform="translate(-1,-1)"></rect><rect x="60.455" y="169" width="12" height="137" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true" stroke-opacity="0.1" transform="translate(-1,-1)"></rect><rect x="60.455" y="169" width="12" height="137" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true" stroke-opacity="0.15000000000000002" transform="translate(-1,-1)"></rect><rect x="60.455" y="169" width="12" height="137" r="0" rx="0" ry="0" fill="#4572A7" stroke="#FFFFFF" stroke-width="1" fill-opacity="1"></rect><rect x="33.705" y="238" width="12" height="68" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true" stroke-opacity="0.05" transform="translate(-1,-1)"></rect><rect x="33.705" y="238" width="12" height="68" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true" stroke-opacity="0.1" transform="translate(-1,-1)"></rect><rect x="33.705" y="238" width="12" height="68" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true" stroke-opacity="0.15000000000000002" transform="translate(-1,-1)"></rect><rect x="33.705" y="238" width="12" height="68" r="0" rx="0" ry="0" fill="#4572A7" stroke="#FFFFFF" stroke-width="1" fill-opacity="1"></rect><rect x="6.955000000000001" y="23" width="12" height="283" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="5" isShadow="true" stroke-opacity="0.05" transform="translate(-1,-1)"></rect><rect x="6.955000000000001" y="23" width="12" height="283" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="3" isShadow="true" stroke-opacity="0.1" transform="translate(-1,-1)"></rect><rect x="6.955000000000001" y="23" width="12" height="283" r="0" rx="0" ry="0" fill="none" stroke="rgb(0, 0, 0)" stroke-width="1" isShadow="true" stroke-opacity="0.15000000000000002" transform="translate(-1,-1)"></rect><rect x="6.955000000000001" y="23" width="12" height="283" r="0" rx="0" ry="0" fill="#4572A7" stroke="#FFFFFF" stroke-width="1" fill-opacity="1"></rect></g></g><g class="highcharts-legend" zIndex="7" transform="translate(180,143)"><rect x="0.5" y="0.5" width="9" height="0" rx="5" ry="5" fill="none" stroke="#909090" stroke-width="1" visibility="hidden"></rect></g><g class="highcharts-axis" zIndex="7"><text x="46" y="27.975" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="end"><tspan x="46">自信心</tspan></text><text x="46" y="54.725" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="end"><tspan x="46">执行力</tspan></text><text x="46" y="81.475" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="end"><tspan x="46">周密度</tspan></text><text x="46" y="108.225" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="end"><tspan x="46">适应性</tspan></text></g><g class="highcharts-axis" zIndex="7"><text x="54" y="131" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="middle"><tspan x="54">0</tspan></text><text x="130.5" y="131" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="middle"><tspan x="130.5">10</tspan></text><text x="207" y="131" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="middle"><tspan x="207">20</tspan></text><text x="283.5" y="131" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="middle"><tspan x="283.5">30</tspan></text><text x="360" y="131" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#666;font-size:12px;line-height:14px;fill:#666;" text-anchor="middle"><tspan x="360">40</tspan></text></g><g class="highcharts-tooltip" zIndex="8" visibility="hidden" transform="translate(132,37)"><rect x="7" y="7" width="38" height="26" rx="5" ry="5" fill="none" fill-opacity="0.85" stroke-width="5" isShadow="true" stroke="rgb(0, 0, 0)" stroke-opacity="0.05" transform="translate(1,1)"></rect><rect x="7" y="7" width="38" height="26" rx="5" ry="5" fill="none" fill-opacity="0.85" stroke-width="3" isShadow="true" stroke="rgb(0, 0, 0)" stroke-opacity="0.1" transform="translate(1,1)"></rect><rect x="7" y="7" width="38" height="26" rx="5" ry="5" fill="none" fill-opacity="0.85" stroke-width="1" isShadow="true" stroke="rgb(0, 0, 0)" stroke-opacity="0.15000000000000002" transform="translate(1,1)"></rect><rect x="7" y="7" width="38" height="26" rx="5" ry="5" fill="rgb(255,255,255)" fill-opacity="0.85" stroke-width="2" stroke="#4572A7"></rect><text x="12" y="24" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;font-size:12px;padding:0;white-space:nowrap;fill:#333333;" zIndex="1"><tspan x="12">18 %</tspan></text></g><g class="highcharts-tracker" zIndex="9" transform="translate(360,117) rotate(90) scale(-1,1)" width="306" height="107"><rect x="87.205" y="31" width="12" height="275" r="0" rx="0" ry="0" fill="rgb(192,192,192)" isTracker="1341906900614" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></rect><rect x="60.455" y="169" width="12" height="137" r="0" rx="0" ry="0" fill="rgb(192,192,192)" isTracker="1341906900614" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></rect><rect x="33.705" y="238" width="12" height="68" r="0" rx="0" ry="0" fill="rgb(192,192,192)" isTracker="1341906900614" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></rect><rect x="6.955000000000001" y="23" width="12" height="283" r="0" rx="0" ry="0" fill="rgb(192,192,192)" isTracker="1341906900614" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></rect></g></svg></div></div>
													<div class="button_11 testReset_btn"><a href="javascript:startTest();">重新进行测试</a></div>
													<div class="button_11 testXq_btn"><a href="javascript:showDetailDesc();" id="show_desc_a">查看详细说明</a></div>
												</div>
											</div>
											<!-- 学习类型测试 测试结果 结束-->
											<!-- 学习类型测试 测试结果 学员分布 开始-->
											<div class="zsboard1 xyfb">
												<div class="title"></div>
												<div class="nr">
													<!-- 
						<div id="circleChart" style="width: 370px; height: 120px; margin: 0 auto"></div>
						 -->
													<div class="test_bt" style="height: 120px;line-height: 20px;overflow: hidden; ">
														<div class="fl pl10" id="circleChart" style="height: 140px; width: 180px;">
														<div class="highcharts-container" id="highcharts-2" style="position: relative; overflow-x: hidden; overflow-y: hidden; width: 190px; height: 140px; text-align: left; font-family: 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif; font-size: 12px; "><svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="190" height="140">
														<defs>
														</defs>
														<rect x="10" y="10" width="170" height="113" fill="#E9E9F1">
														</rect><g class="highcharts-series-group" zIndex="3"></g>
														<g class="highcharts-point" zIndex="5" transform="translate(19,6)">
														<path d="M 84.99136934799341 14.125000878916282 A 42.375 42.375 0 0 1 117.65458729204252 83.50589849989025 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="#4572A7" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round"></path></g><g class="highcharts-point" zIndex="5" transform="translate(10,10)"><path d="M 117.65456028612769 83.50593115446404 A 42.375 42.375 0 0 1 74.4668858682544 97.54502566313755 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="#AA4643" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" fill-opacity="1"></path></g><g class="highcharts-point" zIndex="5" transform="translate(10,10)"><path d="M 74.466844823234 97.54501513000291 A 42.375 42.375 0 0 1 54.098684844848094 85.49567806901537 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="#89A54E" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round"></path></g><g class="highcharts-point" zIndex="5" transform="translate(10,10)"><path d="M 54.09865584918547 85.49564716768572 A 42.375 42.375 0 0 1 84.98347458161763 14.125003222294552 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="#80699B" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" fill-opacity="1"></path></g><g class="highcharts-legend" zIndex="7" transform="translate(90,133)"><rect x="0.5" y="0.5" width="9" height="0" rx="5" ry="5" fill="none" stroke="#909090" stroke-width="1" visibility="hidden"></rect></g><g class="highcharts-tooltip" zIndex="8" visibility="hidden" transform="translate(0,52)"><rect x="7" y="7" width="162" height="26" rx="5" ry="5" fill="none" fill-opacity="0.85" stroke-width="5" isShadow="true" stroke="rgb(0, 0, 0)" stroke-opacity="0.05" transform="translate(1,1)"></rect><rect x="7" y="7" width="162" height="26" rx="5" ry="5" fill="none" fill-opacity="0.85" stroke-width="3" isShadow="true" stroke="rgb(0, 0, 0)" stroke-opacity="0.1" transform="translate(1,1)"></rect><rect x="7" y="7" width="162" height="26" rx="5" ry="5" fill="none" fill-opacity="0.85" stroke-width="1" isShadow="true" stroke="rgb(0, 0, 0)" stroke-opacity="0.15000000000000002" transform="translate(1,1)"></rect><rect x="7" y="7" width="162" height="26" rx="5" ry="5" fill="rgb(255,255,255)" fill-opacity="0.85" stroke-width="2" stroke="#AA4643"></rect><text x="12" y="24" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;font-size:12px;padding:0;white-space:nowrap;fill:#333333;" zIndex="1"><tspan x="12">带有明显强迫症的类型:18 %</tspan></text></g><g class="highcharts-tracker" zIndex="9" transform="translate(10,10)"><path d="M 84.99136934799341 14.125000878916282 A 42.375 42.375 0 0 1 117.65458729204252 83.50589849989025 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="rgb(192,192,192)" isTracker="1341906900625" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></path><path d="M 117.65456028612769 83.50593115446404 A 42.375 42.375 0 0 1 74.4668858682544 97.54502566313755 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="rgb(192,192,192)" isTracker="1341906900625" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></path><path d="M 74.466844823234 97.54501513000291 A 42.375 42.375 0 0 1 54.098684844848094 85.49567806901537 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="rgb(192,192,192)" isTracker="1341906900625" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></path><path d="M 54.09865584918547 85.49564716768572 A 42.375 42.375 0 0 1 84.98347458161763 14.125003222294552 L 85 56.5 A 0 0 0 0 0 85 56.5 Z" fill="rgb(192,192,192)" isTracker="1341906900625" fill-opacity="0.000001" visibility="visible" zIndex="1" style="cursor:pointer;"></path></g></svg></div></div>
														<div class="fr pt10" style="padding-right: 30px;">
															<!-- 
			                	<div id="studyTypeDiv2"><img src="/static/usercenter/images/test_bt_img2.jpg" />B&nbsp;<span id="studyTypeSpan2"></span>强迫型</div>
			                	 -->
															<div id="studyTypeDiv1">
																<img src="<%=importURL%>/images/usercenter/test_bt_img1.jpg"/>
																带有习惯性拖延的类型
															</div>
															<div id="studyTypeDiv2">
																<img src="<%=importURL%>/images/usercenter/test_bt_img2.jpg"/>
																带有明显强迫症的类型
															</div>
															<div id="studyTypeDiv3">
																<img src="<%=importURL%>/images/usercenter/test_bt_img3.jpg"/>
																精密计划严格执行的类型
															</div>
															<div id="studyTypeDiv4">
																<img src="<%=importURL%>/images/usercenter/test_bt_img4.jpg"/>
																随心所欲追求适度的类型
															</div>
														</div>
														<div class="clear"></div>
													</div>
													<p>基于您的学习特点，HighSo向您推荐</p>
													<ul class="kcname" id="recommendCourse"></ul>
												</div>
											</div>
											<!-- 学习类型测试 测试结果 学员分布 结果-->
											<div class="clear"></div>
											<!-- 学习类型测试 详细说明 开始 -->

											
											<!-- 
						<div class="xxinfo mtop-10" id="detailDesc" style="display:none">
						<font class="close" onclick="closeDetailDesc();"></font>
						<h4 class="font_orange">详细说明</h4>
					     <P class="xxsmnr jg">正文说明。就是说明拖延型人物是什么样子的。拖延症，英文Procrastination。取意”将之前的事情放置明天”。拖延症总是表现在各种小事上，但日积月累，特别影响个人发展。拖延现象现已成为管理学家和心理学家研究的一个重要课题。</P>
						 <h4 class="font_blue mleft-10">类型分析</h4>
						 <P class="xxsmnr">对四个类型的解释。就是建议一下用要怎么学习。</P>
						</div>
						 -->
											<!-- 学习类型测试 详细说明 结束-->
										</div>
        <!--学习类型测试 测试结果 结束-->
        </div>
        <div class="test_tk mt10" id="detailDesc" style="display: none; ">
									<h3>
										类型详细说明
									</h3>
									<div class="fontsize-14 pleft-20 pbottom-10">
										A.带有习惯性拖延的类型
										<br/>
										B.带有明显强迫症的类型
										<br/>
										C.精密计划严格执行的类型
										<br/>
										D.随心所欲追求适度的类型判断。
									</div>
									<div class="fontsize-14 pleft-20 pbottom-10">
										<font class="strongb">1、当用户为A类型时</font>
										<br/>
										<font class="strongb">人群特点：</font>所谓拖延症，取意"将之前的事情放置明天"。拖延症总是表现在各种小事上，但日积月累，特别影响个人发展。现代社会随着信息的膨胀和无限增多，人们在接受信息或者学习过程中，因为选择过多，无计划性，很容易产生的拖延现象。拖延现象虽然不会导致严重的疾病，但是对于工作、学习、生活的发展有着非常不利的影响。尤其是远程教育因为自由度较高，约束力不强的特点，人们在学习过程中更容易产生拖延行为。此种类型为大多数用户所有，它可以改变、纠正或者加强。
										<br/>
										<font class="strongb">产生原因：</font>选择过多、没有计划，片面的追求完美主义，恐惧心理、麻烦心理等
										<br/>
										<font class="strongb">合理建议：</font>制订学习计划，严格约束自身，可以找朋友或者亲人来监督自己的学习状态。将学习分解成任务，每天执行任务，定期进行学习效果检测。利用模拟考试，加强效果检查。
										<br/>
										<font class="strongb">课程推荐：</font>
									</div>
									<div class="fontsize-14 pleft-20 pbottom-10">
										<font class="strongb">2、当用户为B类型时</font>
										<br/>
										<font class="strongb">人群特点：</font>有些人在遇到事情或者问题时，会非常焦急或者急迫。他们通常想一口气将手边的任务全部结束，似乎不结束就无法继续其它任务事情，但通常又因为某些因素而造成问题的耽搁，从而造成焦虑的循环反复。通常我们把这种情形称之为带有强近症特点的类型。强迫症型学习特点的人，并不能迅速达成学习目的，反正比较容易造成学习的拖延。所以通常情况下，人们的学习是拖延与强迫相结合的。一方面我们不断要求自己去学习，另一方面我们的学习始终落后于想要达到的进度。从而产生焦虑等情绪问题，最终造成学习的失败。这些都不是严重的疾病，而是没有良好的规则计划造成的坏习惯。为了获得更好的学习状态，我们必须克服这种习惯，使自己达到一个良好的学习状态。
										<br/>
										<font class="strongb">产生原因：</font>过分追求细节、强调自我、易怒、无良好计划、执著于琐碎的细节，忽视客观环境等
										<br/>
										<font class="strongb">合理建议：</font>制订良好的学习计划，学习自我心理调节，建立大局观，将注意力适当分布在各个需要的环节中。加强定期的效果自测，总结与反思并行。
										<br/>
										<font class="strongb">推荐课程：</font>
									</div>
									<div class="fontsize-14 pleft-20 pbottom-10">
										<font class="strongb">3、当用户为C类型时</font>
										<br/>
										<font class="strongb">人群特点：</font>精密计划并严格执行类型的人并不多，不是谁都能做到有计划有实施。大多数的人或者在学习中习惯拖延，或者在学习中不断制订计划但不能执行。此类型的人们有着较强的自我控制能力，对事情、问题的总体把控能力也比较强。知道什么时候是解决问题的时机，而不至于浪费时间和精力。通常情况下，这种类型的人一旦进入学习状态，会有得心应手的感觉。他们就是那种我们生活中看到的，并不怎么苦读，但是效果非常好，成绩始终排在前列的人。原因就在于前期的计划制订得好，中间执行得有效果，自然后期会获得良好的结果。精密计划并非天生不可企及的，它可以通过训练和自我约束形成。
										<br/>
										<font class="strongb">产生原因：</font>对事物看待较为清晰、认真思考、自我约束力强等
										<br/>
										<font class="strongb">合理建议：</font>保持好的习惯，继续执行下去。
										<br/>
										<font class="strongb">推荐课程：</font>
									</div>
									<div class="fontsize-14 pleft-20 pbottom-10">
										<font class="strongb">4、当用户为D类型时</font>
										<br/>
										<font class="strongb">人群特点：</font>随心所欲适度型的人自我调整能力比较强。他们也许也会产生拖延现象，但并不会由此产生压力，而是找到适当的办法去解决它。即使没有找到适当的方法解决，也不会在心理生产愧疚感，更多的仍然是乐观向前看和继续执行。这样的类型的人在学习上收获会多于失去，也许他们达不到像精密计划严格执行类型的人那样高效率和高成绩，但却能体验到学习的快乐和保证基本效果，而不至于浪费光阴。随心所逧适度型由于自我调解比较好，有时候在自我监督方面也会有反作用，对于考试型的专业或者急近型的学习方法来讲就不太合适了。所以，不论如何类型如何，重点还是要做好计划，并能保证执行下去。
										<br/>
										<font class="strongb">产生原因：</font>乐天派的性格，宽厚的心理，对事情追求较为淡薄等
										<br/>
										<font class="strongb">合理建议：</font>更好的执行计划，稍稍加强对自我的约束会让自己做得更好
										<br/>
										<font class="strongb">推荐课程：</font>
									</div>
									<div class="rightlist">
										<a href="javascript:closeDetailDesc();"><img src="<%=importURL%>/images/usercenter/test_tk_shouqi.gif" />
										</a>
									</div>
								</div>
      </div>
    </div>
  </div>