<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
	
				<script>
				$().ready(function (){
					var cl=  getCookie("myphoto");
                	if ( cl==null ||  cl =="" || $.trim(cl).length  < 5){
                		cl="<%=importURL%>/images/usercenter/leftnav_2.gif";
                	}else{
                		cl="<%=importURL%>/upload/customer/photo/"+cl;
                 	}
                	$("#cus_photo_img2").attr("src",""+cl);
				});
                </script>
	
<div class="contenter">
      <div class="main_con">
        <div class="user_data">
          <div class="user_data_left">
            <div class="user_data_pic"><img id="cus_photo_img2" src="<%=importURL%>/images/usercenter/user_pic1.jpg" width="130" height="120" /> </div>
            <p><a href="<%=contextPath%>/cus/cuslimit!initCusInfo.action">完善个人资料</a></p>
          </div>
          <div class="user_data_right">
            <div class="user_data_status">
            <s:if test="ishavebuy">
            	<div class="user_data_name"><s:property value="customer.email" /></div>
            	 <!--购买登录状态--> 
              	<ul class="user_data_statusList">
                  <li class="user_data_log"><i></i><span class="fl">第<em>${buyInfo.loginCount}</em>次登录</span> <span class="fr">站内排名<em>${buyInfo.loginRank == 0 ? '5000+':buyInfo.loginRank}</em>位</span> </li>
                  <li class="user_data_look"><i></i><span class="fl">看视频节数<em>${buyInfo.watchVideoTime}</em>节</span> <span class="fr">站内排名<em>${buyInfo.watchVideoRank == 0 ? '5000+' : buyInfo.watchVideoRank}</em>位</span></li>
                  <li class="user_data_exam"><i></i><span class="fl">做试题数<em>${buyInfo.workQuestionCount}</em>道</span> <span class="fr">站内排名<em>${buyInfo.workQuestionRank == 0 ? '5000+' : buyInfo.workQuestionRank}</em>位</span> </li>
                </ul>
             <!--end 购买登录状态--> 
            </s:if>
            <s:else>
            	<!--未购买登录状态-->
              	<div class="user_data_first"><s:property value="customer.email" />，这是您第一次登录，您有<em>${notBuyInfo.classmate}</em>名同班同学。</div>
              	<p>他们平均每人看<em>${notBuyInfo.avgVideo}</em>节视频，做<em>${notBuyInfo.avgExercises}</em>道练习题，问<em>${notBuyInfo.avgQuestions}</em>个问题，回答<em>${notBuyInfo.answerQuestions}</em>个问题哦，持续有效学习，会提升考试通过率！嗨学祝您顺利通过考试：）</p>
            	<!--end 未购买登录状态-->
            </s:else>
            </div>
             <div class="user_data_text">
             	<s:if test="customer.signature == null">
             		<p id="uc_data_textHolder">填写你的发表内容~~</p>
             	</s:if>
             	<div class="public" style="display:${customer.signature==null ?'block':'none'}"><span class="jiao"></span><textarea name="" cols="" rows="" id="content" onpropertychange="checkLength(this,30);" oninput="checkLength(this,30);">${customer.signature}</textarea>
                <div class="user_data_bt"><div class="user_data_word_enter"><img src="<%=importURL%>/images/usercenter/loading.gif" style="display:none;float:left;" class="loading"/><div id="msgContent" style="float:left;">还可以输入
                <em id="user_data_surplus_word"><script>var length = '${customer.signature}';var $maxtext=30;if(length != null && typeof(length) != undefined && length !=""){document.write(($maxtext - length.length));}else{document.write('30');}</script></em>个字</div></div>
                <a class="user_data_btn" id="postPublic">发表</a></div></div>
                <div class="user_data_textAfter" style="display:${customer.signature==null ?'none':'block'}"><span>最近发布</span><p id="showcontent">${customer.signature}</p><a class="user_data_btn" href="#" id="postUpdate">修改</a></div>  
             </div>
          </div>
        </div>
        <s:if test="ishavebuy">
        <div class="uc_learnRecodrs">
          <div class="uc_learnRecodrs_con"><span class="uc_lr_tt">您上次学习到：<img src="<%=importURL%>/images/usercenter/loading.gif" style="display:none;" class="learn"/></span>
          <p id="first">
          </p> 
	          <a class="lr_gostudy_btn" id="lr_gostudy_btn" href="#">继续学习</a> 
	          <a id="lr_look_btn" class="lr_look_btn" href="#">观看记录</a>
          </div>
          <div class="lr_look_list" style="display:none;">
            <span class="lr_look_jiao"></span>
            <ul class="con" id="con">
            </ul>
          </div>
        </div>
        <div class="all_look">
          <div class="title"><h2>我看他看</h2></div>
          <ul class="all_look_tab">
            <li><a href="#all_look_tabcon1">最新top10</a></li>
            <li><a href="#all_look_tabcon2">点击top10</a></li>
            <li class="mr0"><a href="#all_look_tabcon3">评价top10</a></li>
          </ul>
          <div id="all_look_tabcon1" class="all_look_tabcon">
            <table class="all_look_table">
              <thead>
                <tr>
                  <th class="tl" width="280">课程名称</th>
                  <th>名师</th>
                  <th>观看人次</th>
                  <th>上传日期</th>
                </tr>
              </thead>
              <tbody id="newContent">
              	<tr>
              		<td colspan="4" align="center"><img src="<%=importURL%>/images/usercenter/loading.gif"/></td>
              	</tr>
              </tbody>
            </table>
          </div>
          <div id="all_look_tabcon2" class="all_look_tabcon">
            <table class="all_look_table">
              <thead>
                <tr>
                  <th class="tl" width="280">课程名称</th>
                  <th>名师</th>
                  <th>观看人次</th>
                  <th>上传日期</th>
                </tr>
              </thead>
              <tbody id="topContent">
            	 <tr>
              		<td colspan="4" align="center"><img src="<%=importURL%>/images/usercenter/loading.gif"/></td>
              	</tr>
              </tbody>
            </table>
          </div>
          <div id="all_look_tabcon3" class="all_look_tabcon">
            <table class="all_look_table">
              <thead>
                <tr>
                  <th class="tl" width="280">课程名称</th>
                  <th>名师</th>
                  <th>观看人次</th>
                  <th>上传日期</th>
                </tr>
              </thead>
              <tbody id="evaluateContent">
            	 <tr>
              		<td colspan="4" align="center"><img src="<%=importURL%>/images/usercenter/loading.gif"/></td>
              	</tr>
              </tbody>
            </table>
          </div>
        </div>
        </s:if>
        <!-- 未购买用户显示开始 -->
        <s:if test="sellWayCourseListMore !=null && sellWayCourseListMore.size>0 "> 
        <div class="countdown_box">
               <div class="title" id="countdown_tt01"><h2>我的试听时间</h2></div>
               <div class="countdown" id="countdown_tt02"><span>距离失效还有</span><em id="regd_hh">00</em><span>小时</span><em id="regd_mm">00</em><span>分钟</span><em class="uc_countdown_second" id="regd_ss">00</em><span>秒</span></div>
                <script type="text/javascript">
		            var regtime="<s:date name="customer.regTime" format="yyyy/MM/dd HH:mm:ss" />";
					var urodz = new Date(regtime);
					urodz.setTime(urodz.getTime()+1000*60*60*24*2);
					function GetServerTimest() {
							var now = new Date();
							now.setTime(now.getTime()+1000);
							var days = (urodz -now) / 1000 / 60 / 60 / 24;
							var daysRound = Math.floor(days);
							var hours_t = (urodz -now) / 1000 / 60 / 60;
							var hoursRound_t = Math.floor(hours_t);
							var hours = (urodz -now) / 1000 / 60 / 60 - (24 * daysRound);
							var hoursRound = Math.floor(hours);
							var minutes = (urodz -now) / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound);
							var minutesRound = Math.floor(minutes);
							var seconds = (urodz -now) / 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound);
							var secondsRound = Math.round(seconds);
							if(hoursRound_t>9){
									$("#regd_hh").html(hoursRound_t);
							}else{
									$("#regd_hh").html("0"+hoursRound_t);
							}
							if(minutesRound>9){
								$("#regd_mm").html(minutesRound);
							}else{
								$("#regd_mm").html("0"+minutesRound);
							}
							if(secondsRound>9){
								$("#regd_ss").html(secondsRound);
							}else{
								$("#regd_ss").html("0"+secondsRound);
							}
					}
					var now_t = new Date();
					if((urodz-now_t.getTime())>0){
						setInterval("GetServerTimest()",1000);//用GetServerTimesf
					}else{
						$("#countdown_tt01").hide();
						$("#countdown_tt02").hide();
					}
	            </script>
        </div>
        
        <!-- 试听循环显示 开始 --> 
        <s:iterator value="sellWayCourseListMore" id ='sellWay_st' status="su_st">
	        <div class="sting_box">
	          <div class="title"><h2 id="countdown_tt03">正在试听</h2></div>
	         
	          <div class="sting">
	            <div class="b_video"><a href="javascript:goToListenCourseTmp(<s:property value='#sellWay_st.courseList.get(0).courseId'/>,<s:property value='#sellWay_st.sellWay.subjectId'/>,<s:property value='#sellWay_st.sellWay.sellId'/>)">
	           
	            <s:if test="#sellWay_st.courseList.get(0).kpointList.get(1).ccSmallPic != null">
	            	<img src="<s:property value='#sellWay_st.courseList.get(0).kpointList.get(1).ccSmallPic'/>" width="320" height="184" alt="" id="vIdObjBig"/>
	            </s:if>
	            <s:else>
	            	<img src="<%=importURL%>/images/usercenter/video_img.jpg" width="320" height="184" alt="" id="vIdObjBig"/>
	            </s:else>
	            </a>
	            </div>
	            <div class="b_video_msg">
	              <h5><a href="javascript:goToListenCourseTmp(<s:property value='#sellWay_st.courseList.get(0).courseId'/>,<s:property value='#sellWay_st.sellWay.subjectId'/>,<s:property value='#sellWay_st.sellWay.sellId'/>)"><s:property value='#sellWay_st.sellWay.sellName'/></a></h5>
	              <div class="b_video_intro"><strong>本节内容</strong><s:property value='#sellWay_st.sellWay.remark'/></div>
	              <div class="b_video_teacher"><strong>本课老师</strong><s:property value='#sellWay_st.sellWay.teacher'/></div>
	              <div class="b_video_num"><strong>观看人数</strong><s:property value='#sellWay_st.sellWay.sellId+866'/>人</div>
	              <div class="b_video_btn"><a class="go_study_btn" href="javascript:goToListenCourseTmp(<s:property value='#sellWay_st.courseList.get(0).courseId'/>,<s:property value='#sellWay_st.sellWay.subjectId'/>,<s:property value='#sellWay_st.sellWay.sellId'/>)"">立即学习</a> <a class="st_next_btn" href="javascript:goToListenCourseTmp(<s:property value='#sellWay_st.courseList.get(0).courseId'/>,<s:property value='#sellWay_st.sellWay.subjectId'/>,<s:property value='#sellWay_st.sellWay.sellId'/>)">试听下一课</a></div>
	              </div>
	           </div> 
	        </div>
         
	        <div class="popular_listen_box">
		          <div class="title"><h2>超人气视频</h2></div>
		          <s:iterator value="#sellWay_st.courseList" id ='couser_st' status="suc_st">
		          <ul class="popular_listen">
		            <li>
		              <div class="s_video"><a href="javascript:goToListenCourseTmp(<s:property value='#couser_st.courseId'/>,<s:property value='#sellWay_st.sellWay.subjectId'/>,<s:property value='#sellWay_st.sellWay.sellId'/>)">
		              
			           <s:if test="#couser_st.kpointList.get(1).ccSmallPic != null">
	            	<img src="<s:property value='#couser_st.kpointList.get(1).ccSmallPic'/>" width="123" height="82" alt="" id="vIdObjBig"/>
	            </s:if>
	            <s:else>
	            	<img src="<%=importURL%>/images/usercenter/video_img.jpg" width="123" height="82" alt="" id="vIdObjBig"/>
	            </s:else>
	               
	               </a>
		              </div>
		              <div class="s_video_msg">
		                <h5><s:property value='#couser_st.title'/></h5>
		                <div class="s_video_teacher"><strong>本课老师</strong><s:property value='#couser_st.teacherName'/></div>
		                <div class="s_video_class"><strong>课&nbsp;&nbsp;&nbsp;&nbsp;时</strong><s:property value='#couser_st.kpointList.size()'/>课时</div>
		                <div class="s_video_pj"><strong>综合评价</strong><span class="s_video_star">5星</span></div>
		              </div>
		            </li>
		          </ul>
		          </s:iterator>
	        </div>
        </s:iterator>	
         <!-- 试听循环显示 结束 -->
         </s:if>
         <!-- 未购买用户显示结束  -->
         <div class="tab">
        <ul class="uc_tab">
          <li><a href="#uc_tabcon1">用户反馈</a></li>		
          <li><a href="#uc_tabcon2">新鲜事</a></li>
          <!-- 临时隐藏
          <li><a href="#uc_tabcon3">我的动态</a></li> -->
          
        </ul> 
        	  
        	<!--用户反馈 start-->
            <div id="uc_tabcon1" class="uc_tabcon">
                <div class="userFeedback">
	                 <div class="uF_header">
		           		<p>您的意见会让HighSo做得更好</p>
		            	 <input type="button" value="我要提问" name="" class="userClick01" /> 
		            	<span class="userMore"><a href="javaScript:moreAdvice()">更多</a></span>
	           		</div>    
		            <div class="uF_content" style="display:none;">
		                	<h5>提问内容</h5> 
		                    <textarea  style="overflow-y:hidden" id="textInfo" rows="" cols="" name="cmtInfo" class="uF_con_area"></textarea>
		                    <div class="uF_content_bottom"><span class="left"><input type="hidden" value="2" id="indexMax" />已输入<span id="complete_word">0</span>个字，还可以输入<span id="surplus_word">140</span>字</span>
		                    	<input type="button" onclick="sucessAnswer()" value="提交问题" name="" class="userFeedback_tjbtn" />
		             		</div>
					</div>
	                <ul class="userFeedback_con" id="replyList">
	                </ul>
                </div>
            </div>
        	<!--用户反馈 end-->
        	
	        <!--新鲜事开始-->
	        <div id="uc_tabcon2" class="uc_tabcon">
	            <ul class="newsthings_list" id="freshnewsInfo">
	            </ul>
	            <div class="newthings_loading" id="newthings_loading"><span>正在加载，请稍候...</span></div>
	            <div class="newthings_more" id="newthings_more"><a style="cursor:pointer;" id="more_freshnews">更多</a></div>
	        </div>
	        <!--新鲜事结束-->
	        
	        <!--我的动态开始 临时隐藏-->
	        <!-- 
	        <div id="uc_tabcon3" class="uc_tabcon">
	        	<ul class="newsthings_list" id="newthings_loading_me">
	            </ul>
	            <div class="newthings_loading" id="newthings_loading_me"><span>正在加载，请稍候...</span></div>
	        </div> -->
	         <!--我的动态结束-->
	         
	         
        	
         </div>
      </div>
       <!--他们在嗨学-->
	<div class="user_pop" style="display:none">
	</div>
      