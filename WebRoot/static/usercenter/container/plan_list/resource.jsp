<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<link href="<%=importURL%>/styles/usercenter/popup.css?v=<%=version%>" rel="stylesheet">
<link href="<%=importURL%>/styles/usercenter/uc_learn.css?v=<%=version%>" rel="stylesheet">
<link href="<%=importURL%>/styles/usercenter/calendar-ui2.css" rel="stylesheet">
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_highcharts.src.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_user_center.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_user_common.js?v=<%=version%>"></script>
<!--[if IE 6]>
	<style type="text/css">
	.iepng{ behavior: url("<%=contextPath%>/static/usercenter/jsp/studyplan/iepng/iepngfix.htc") }
	</style>
	<script type="text/javascript" src="<%=contextPath%>/static/usercenter/jsp/studyplan/iepng/iepngfix_tilebg.js"></script>
<![endif]-->

<script type="text/javascript">
	
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 9;
	
	var now,hours,minutes,seconds,timeValue; 
	function showtime(){
		now = new Date(); 
		
		hours = now.getHours(); 
		minutes = now.getMinutes(); 
		seconds = now.getSeconds(); 
		timeValue = ""; 
		timeValue += hours + ":"; 
		timeValue += ((minutes <10)?"0":"") + minutes+":"; 
		timeValue += ((seconds <10)?"0":"") + seconds; 
		$("#lea_time").html("当前时间："+timeValue); 
		setTimeout("showtime()",100); 
	} 
	showtime(); 
	
	function checkAll(){
		var check=document.getElementsByName('checkID');
		var sLength=document.getElementsByName('checkID').length;
		for(var i=0;i<sLength;i++){
			check[i].checked = true;
		}
	}
		
	function checkRev(){
		var check=document.getElementsByName('checkID');
		var sLength=document.getElementsByName('checkID').length;

		for(var i=0;i<sLength;i++){
			if(check[i].checked == true)
				check[i].checked = false;
			else
				check[i].checked = false;
		}
	}
		
	// 批量删除学习计划 
	function batchProcess(){
		var checkBath=document.getElementsByName('checkID');
		var sLengthBath=document.getElementsByName('checkID').length;
		// 拼接ID
		var batchParamsId='';
		for(var i=0;i<sLengthBath;i++){
			if(checkBath[i].checked==true){
				var tempId=checkBath[i].id.substring(7)+',';
				batchParamsId += tempId;
			}
		}
		if(batchParamsId == ""){
			$("#error_message").html("请选择学习计划！");
			showWin("error_win");
		}else{
			$("#popupContactDeleteInfoText").html("<b>确认要删除学习计划？</b><br>计划删除后将无法恢复。确定删除？按‘确定’继续，按‘取消’停留在当前页面");
			showWin("popupContactDeleteInfo");
		}
	}
		
	function batchProcessgo(){
		var checkBath=document.getElementsByName('checkID');
		var sLengthBath=document.getElementsByName('checkID').length;
		// 拼接ID
		var batchParamsId='';
		for(var i=0;i<sLengthBath;i++){
			if(checkBath[i].checked==true){
				var tempId=checkBath[i].id.substring(7)+',';
				batchParamsId += tempId;
			}
		}
		if(batchParamsId == ""){
			showErrorWin('请选择学习计划！','');
		}else{
			location.href="<%=contextPath%>/stu/studyplanWeb!batchProcessPlan.action?batchParamsId="+batchParamsId;
		}
	}
	
	$().ready(function() {
		// 滑动事件
		$(".ui-state-default").hover(function(){
			$(this).addClass("ui-state-hover");
		},function(){
			$(this).removeClass("ui-state-hover");
		});
	
		// 点击事件
		$(".ui-state-default iepng").click(function(){
			$(".markTip").addClass("tip iepng");
			$('#calendarUi a').removeClass('ui-state-active');
			$(this).removeClass("tip").addClass("ui-state-active");
		});
	})
	
	function checkExam(checkDay){
		$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getExampaperRecordList.action",
			type : "post",
			data : {
				"examSummary.summarydate" : checkDay
			},
			dataType : "json",
			success : function(result) {
				if(result.returnMessage == "success") {
					//alert(checkDay);
					showErrorWin('您这天做了试卷哦！','');
					checkExamYes(checkDay);
				}else {
					//向数据库插入一条计入
					//InsertNotWatchExam(checkDay);
					//showErrorWin('您这天没有做试卷哦！','');
								
					$("#examdo").hide();
					$("#resExamText").show();
					$("#resExamText").html("<li>您这天没有做试卷。</li>");
				} 
			},
			error : function(error) {
				showErrorWin('error==checkExam','');
			}
		});
	}

	function checkExamY(checkDay){
		$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getExampaperRecordList.action",
			type : "post",
			data : {
				"examSummary.summarydate" : checkDay
			},
			dataType : "json",
			success : function(result) {
				if(result.returnMessage == "success") {
					checkExamYes(checkDay);
				}else {
					showErrorWin('您这天没有做试卷哦！','');
				} 
			},
			error : function(error) {
				showErrorWin('error==checkExam','');
			}
		});
	}
			 
	function checkExamYes(checkDay){
		$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getExamSummaryLimtContent.action",
			type : "post",
			dataType : "json",
			data : {
				"examSummary.summarydate" : checkDay
			},
			success : function(result) {
				//显示 
				var epNameList = result.entity;
				if(epNameList == null){
					checkExamY(checkDay);
				}else{
					var displayexminfo="";	
					$.each(epNameList,function(key,val){
						displayexminfo+="<li>您这天做了"+"<font style='color:yellow;'>"+ val.substring(0,6)+"..."+"</font>"+"试卷。</li>";
						//alert("只显示4条计入"+key); //(只显示5条计入) 
		        		if(key=='3'){            
							return false; //跳出循环  
						}
					});
										 
					$("#examdo2").show();
					$("#examdo").hide();
					$("#resExamText").html(displayexminfo);
					$("#resExamText").show();
				}
			},
			error : function(error) {
				showErrorWin('error===checkExamYes','');
			}
		});
	}
			 
	function checkExamNo(checkDay){ 
		$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getExamSummaryLimtContent.action",
			type : "post",
			data : {
				"examSummary.summarydate" : checkDay
			},
			dataType : "json",
			success : function(result) {
						
				//显示 
				var epNameList = result.entity;
				if(epNameList == null){
					checkExam(checkDay);
				}else{
					showErrorWin('您这天做了试卷哦！','');
					var displayexminfo="";	
					$.each(epNameList,function(key,val){
								 
						//alert(key); 
        				if(key=='3'){            
							return false; //跳出循环  
						}
						displayexminfo+="<li>您这天做了"+"<font style='color:yellow;'>"+ val.substring(0,6)+"..."+"</font>"+"试卷。</li>";
					});
								 
					$("#examdo2").show();
					$("#examdo").hide();
					$("#resExamText").html(displayexminfo);
					$("#resExamText").show();
				}
			},
			error : function(error) {
				showErrorWin('error==checkExamNo','');
			}
		});
	}
			 
	function InsertNotWatchExam(checkDay){
		$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!InsertNotWatchExam.action",
			type : "post",
			data : {
				"examSummary.summarydate" : checkDay
			},
			success : function(result) {},
			error : function(error) {
				showErrorWin('error===InsertNotWatchExam','');
			}
		});
	}
	 
	function checkCourse(checkDay){
		$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getWatchCourseListByDate.action",
			type : "post",
			data : {
				"courseSummary.summarydate" : checkDay
			},
			dataType : "json",
			success : function(result) {
				if(result.returnMessage == "success") {
					showErrorWin('您这天学习课程哦！','');
					checkCourseYes(checkDay);
				}else {
					//showErrorWin('您这天没有学习课程哦！','');
									
					$("#coursedo2").show();
					$("#coursedo").hide();
					$("#resCourseText").html("<li>您这天没有学习课程。</li>");
					$("#resCourseText").show();
				} 
			},
			error : function(error) {
				showErrorWin('error==checkCourse','');
			}
		});
	}

	function checkCourseY(checkDay){
	 	$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getWatchCourseListByDate.action",
			type : "post",
			data : {
				"courseSummary.summarydate" : checkDay
			},
			dataType : "json",
			success : function(result) {
				if(result.returnMessage == "success") {
					//showErrorWin('您这天学习课程哦222！','');
					checkCourseYes(checkDay);
				}else {
					showErrorWin('您这天没有学习课程哦！','');
				} 
			},
			error : function(error) {
				showErrorWin('error==checkCourse','');
			}
		});
	}

	function checkCourseYes(checkDay){
	 	$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getCourseSummaryLimtContent.action",
			type : "post",
			data : {
				"courseSummary.summarydate" : checkDay
			},
			dataType : "json",
			success : function(result) {
				//显示 
				var pointNameList = result.entity;
				if(pointNameList == null){
					//alert("Course 做了？怎么没找到？我去找");
					checkCourseY(checkDay);
				}else{
					var displaycourseinfo="";	
					 $.each(pointNameList,function(key,val){
					 
			        //alert("跳出循环"+key); 
			        if(key=='5'){            
				    	return false; //跳出循环  
				    }
				         
					displaycourseinfo+="<li>您这天学习"+"<font style='color:yellow;'>"+ val.substring(0,6)+"..."+"</font>"+"课程。</li>";
					});
					 
					$("#coursedo2").show();
					$("#coursedo").hide();
					$("#resCourseText").html(displaycourseinfo);
					$("#resCourseText").show();
				
				}
			},
			error : function(error) {
				showErrorWin('error===checkCourseYes','');
					   }
		});
	}

	function checkCourseNo(checkDay){
			$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!getCourseSummaryLimtContent.action",
			type : "post",
			data : {
				"courseSummary.summarydate" : checkDay
			},
			dataType : "json",
			success : function(result) {
			
				//显示 
				var pointNameList = result.entity;
				if(pointNameList == null){
				checkCourse(checkDay);
		
		//$("#coursedo2").show();
		//$("#coursedo").hide();
		//$("#resCourseText").html("<li>您这天没有学习课程。</li>");
		//$("#resCourseText").show();
				}else{
					showErrorWin('您这天学习了课程哦！','');
					var displaycourseinfo="";	
					$.each(pointNameList,function(key,val){
					    //alert(key); 
						if(key=='5'){            
							return false; //跳出循环  
						}
						displaycourseinfo+="<li>您学习了"+"<font style='color:yellow;'>"+ val.substring(0,6)+"..."+"</font>"+"试卷。</li>";
					 });
					 
					$("#coursedo2").show();
					$("#coursedo").hide();
					$("#resCourseText").html(displaycourseinfo);
					$("#resCourseText").show();
				}
			},
			error : function(error) {
				showErrorWin('error===checkCourseYes','');
			}
		});
	 }
	 
	function InsertNotWatchCourse(checkDay){
		$.ajax({
			url : "<%=contextPath%>/stu/studyplanWeb!InsertNotWatchCourse.action",
			type : "post",
			data : {
				"courseSummary.summarydate" : checkDay
			},
			success : function(result) {},
			error : function(error) {
				showErrorWin('error===InsertNotWatchCourse','');
		   }
		});
	 }

</script>