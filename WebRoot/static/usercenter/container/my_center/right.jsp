<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>

<div class="side_con">
	<div class="phone_ad mb20"><h3>课程咨询热线</h3><p id="hotline">4007-062-061</p></div>
	<script type="text/javascript">
		if(ishavebuy=='true'){
			$("#hotline").parent().remove();
			$("#hotline").prev().remove();
			$("#hotline").remove();
		}
	</script>
    
	<div class="side_jifen">
        <div class="date"><span id="jfMonth" class="date_a">0</span><span class="date_a">月</span><span id="jfDay" class="date_a">0</span><span class="date_a">日</span></div>
        <a class="jifen_btn"  onclick="signin()" id="jifenhref" href="javascript:void(0)" >打卡赚取积分</a>
        <p class="f18 fl mt10">我的积分：<em id="score">0</em>分</p>
        <span id="jifen_numMask" class="jifen_numMask">+5</span>
    </div>
      <script type="text/javascript">
        		var _ttr_now = new Date();
				var _ttr_month = _ttr_now.getMonth()+1;
				var _ttr_days = _ttr_now.getDate();
				
				$("#jfMonth").html(_ttr_month);
				$("#jfDay").html(_ttr_days);
				
          </script>   
	    <div class="side_ann" id="side_ann">
	    </div>
        <div class="side_otherUser">
          <h2 class="side_title">他们在嗨学</h2>
          <div id="otherUser_box" class="otherUser_box">
            <ul class="otherUser_list" id="otherUser_list">
            </ul>
          </div>
        </div>
        <div class="side_studyTest">
          <h2 class="side_title">学习类型测试</h2>
          <div class="side_studyTest_con">
            <p>了解自己的学习类型更高效率学习</p>
            <a href="<%=contextPath%>/exam/qstManager!getExampaperAnalysisDTO.action" class="side_studyTest_btn">点击测试</a></div>
        </div>
      </div>
     </div>
   </div>
