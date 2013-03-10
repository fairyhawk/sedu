<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ page import="com.shangde.common.util.DateUtil" %>
<link href="<%=importURL%>/styles/usercenter/uc_learn.css" rel="stylesheet">
<link href="<%=importURL%>/styles/usercenter/calendar-ui2.css?d=2012" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/popup.css" />
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_highcharts.src.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_user_center.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_user_common.js"></script>
<script type="text/javascript">
		var baselocation = '<%=contextPath%>';
		var importURL = '<%=importURL%>';
		var ucLeftIndex = 9;

		$().ready(function() {
			// 滑动事件
			$(".ui-state-default").hover(function(){
				$(this).addClass("ui-state-hover");
			},function(){
				$(this).removeClass("ui-state-hover");
		});
					
		// 点击事件
		$(".ui-state-default").click(function(){
			$(".markTip").addClass("tip iepng");
			$('#calendarUi a').removeClass('ui-state-active');
			$(this).removeClass("tip").addClass("ui-state-active");
		});
					
		$("#xq").focus(function(){
			 var xq_value = $(this).val();
			 if(xq_value=="没有"){
			 	$(this).val("");
			 }
		});
			 		
		$("#jh").focus(function(){
			 var jh_value = $(this).val();
			 if(jh_value==" 没啥计划"){
			 	$(this).val("");
			 }
		});
			 		
		$("#jyl").focus(function(){
			 var jyl_value = $(this).val();
			 if(jyl_value==" 没啥想说的"){
			 	$(this).val("");
			 }
		});
			 		
		$("#xlqd").focus(function(){
			 var xlqd_value = $(this).val();
			 if(xlqd_value==" 没有"){
			 	$(this).val("");
			 }
		});
			 		
		$("#jb").focus(function(){
			 var jb_value = $(this).val();
			 if(jb_value==" 没啥想说的"){
			 	$(this).val("");
			 }
		});
			 		
		$("#shuqian").focus(function(){
			 var shuqian_value = $(this).val();
			 if(shuqian_value==" 没有"){
			 	$(this).val("");
			 }
		});
			 		
		$("#xinde").focus(function(){
			 var xinde_value = $(this).val();
			 if(xinde_value==" 没啥"){
			 	$(this).val("");
			 }
		});
			 		
		$("#tmrplan").focus(function(){
			var tmrplan_value = $(this).val();
			if(tmrplan_value==" 没计划"){
			 	$(this).val("");
			}
		});
				 
				 
		//今日心情
		$("#btn1").click(function() {
			var oldxq = $("#xq")[0].value;
			if(oldxq!="" && oldxq!="没有" && oldxq.length < 20){
				$("#xq").val(oldxq+";还不错");
			}else if(oldxq=="" ||oldxq=="没有"){
				$("#xq").val("还不错");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#xq").val(oldxq);
			}
		});
					 
		$("#btn2").click(function() {
			var oldxq = $("#xq")[0].value;
			if(oldxq!="" && oldxq.length < 20){
				$("#xq").val(oldxq+";没有");
			}else if(oldxq==""){
				$("#xq").val("没有");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#xq").val(oldxq);
			}
		});
				
		$("#btn3").click(function() {
			var oldxq = $("#xq")[0].value;
			if(oldxq!="" && oldxq!="没有" && oldxq.length < 20){
				$("#xq").val(oldxq+";无聊");
			}else if(oldxq=="" ||oldxq=="没有"){
				$("#xq").val("无聊");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#xq").val(oldxq);
			}
		});
		
		$("#btn4").click(function() {
			var oldxq = $("#xq")[0].value;
			if(oldxq!="" && oldxq!="没有" && oldxq.length < 20){
				$("#xq").val(oldxq+";开心");
			}else if(oldxq=="" ||oldxq=="没有"){
				$("#xq").val("开心");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#xq").val(oldxq);
			}
		});
					 
		$("#btn5").click(function() {
			var oldxq = $("#xq")[0].value;
			if(oldxq!="" && oldxq!="没有" && oldxq.length < 20){
				$("#xq").val(oldxq+";累啊");
			}else if(oldxq=="" ||oldxq=="没有"){
				$("#xq").val("累啊");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#xq").val(oldxq);
			}
		});
					 
		$("#btn6").click(function() {
			var oldxq = $("#xq")[0].value;
			if(oldxq!="" && oldxq!="没有" && oldxq.length < 20){
				$("#xq").val(oldxq+";有点冷");
			}else if(oldxq=="" ||oldxq=="没有"){
				$("#xq").val("有点冷");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#xq").val(oldxq);
			}
		});
					 
		$("#btn7").click(function() {
			var oldxq = $("#xq")[0].value;
			if(oldxq!="" && oldxq!="没有" && oldxq.length < 20){
				$("#xq").val(oldxq+";给力");
			}else if(oldxq=="" ||oldxq=="没有"){
				$("#xq").val("给力");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#xq").val(oldxq);
			}
		});	
					 
		//学习计划
		$("#bt1").click(function() {
			var oldjh = $("#jh")[0].value;
			if(oldjh!="" && oldjh!=" 没啥计划" && oldjh.length < 20){
				$("#jh").val(oldjh+";看视频");
			}else if(oldjh=="" || oldjh==" 没啥计划"){
				$("#jh").val("看视频");
			}else{
				showErrorWin("最多填写20个字符","");
				$("#jh").val(oldjh);
			}
		});
					 
					 $("#bt2").click(function() {
					 	var oldjh = $("#jh")[0].value;
					 	if(oldjh!="" && oldjh!=" 没啥计划" && oldjh.length < 20){
					 		$("#jh").val(oldjh+";看书");
					 	}else if(oldjh=="" || oldjh==" 没啥计划"){
					 		$("#jh").val("看书");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jh").val(oldjh);
					 	}
					 });
				
					 $("#bt3").click(function() {
					 	var oldjh = $("#jh")[0].value;
					 	if(oldjh!="" && oldjh!=" 没啥计划" && oldjh.length < 20){
					 		$("#jh").val(oldjh+";做试卷");
					 	}else if(oldjh=="" || oldjh==" 没啥计划"){
					 		$("#jh").val("做试卷");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jh").val(oldjh);
					 	}
					 });
		
					 $("#bt4").click(function() {
					 	var oldjh = $("#jh")[0].value;
					 	if(oldjh!="" && oldjh!=" 没啥计划" && oldjh.length < 20){
					 		$("#jh").val(oldjh+";复习");
					 	}else if(oldjh=="" || oldjh==" 没啥计划"){
					 		$("#jh").val("复习");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jh").val(oldjh);
					 	}
					 });
					 
					 $("#bt5").click(function() {
					 	var oldjh = $("#jh")[0].value;
					 	if(oldjh!="" && oldjh!=" 没啥计划" && oldjh.length < 20){
					 		$("#jh").val(oldjh+";预习");
					 	}else if(oldjh=="" || oldjh==" 没啥计划"){
					 		$("#jh").val("预习");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jh").val(oldjh);
					 	}
					 });
					 
					 $("#bt6").click(function() {
					 	var oldjh = $("#jh")[0].value;
					 	if(oldjh!="" && oldjh.length < 20){
					 		$("#jh").val(oldjh+";没计划");
					 	}else if(oldjh==""){
					 		$("#jh").val("没计划");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jh").val(oldjh);
					 	}
					 });
					 
					 $("#bt7").click(function() {
					 	var oldjh = $("#jh")[0].value;
					 	if(oldjh!="" && oldjh!=" 没啥计划" && oldjh.length < 20){
					 		$("#jh").val(oldjh+";今天我休息");
					 	}else if(oldjh=="" || oldjh==" 没啥计划"){
					 		$("#jh").val("今天我休息");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jh").val(oldjh);
					 	}
					 });	
					 
					 // 记忆力注意力
					 $("#b1").click(function() {
					 	var oldjyl = $("#jyl")[0].value;
					 	if(oldjyl!="" && oldjyl!=" 没啥想说的" && oldjyl.length < 20){
					 		$("#jyl").val(oldjyl+";还行啊");
					 	}else if(oldjyl=="" || oldjyl==" 没啥想说的"){
					 		$("#jyl").val("还行啊");
					 	}else{
					 		$("#jyl").val("还行啊");
					 		showErrorWin("最多填写20个字符","");
					 		$("#jyl").val(oldjyl);
					 	}
					 });
					 
					 $("#b2").click(function() {
					 	var oldjyl = $("#jyl")[0].value;
					 	if(oldjyl!="" && oldjyl!=" 没啥想说的" && oldjyl.length < 20){
					 		$("#jyl").val(oldjyl+";老忘事");
					 	}else if(oldjyl=="" || oldjyl==" 没啥想说的"){
					 		$("#jyl").val("老忘事");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jyl").val(oldjyl);
					 	}
					 });
				
					 $("#b3").click(function() {
					 	var oldjyl = $("#jyl")[0].value;
					 	if(oldjyl!="" && oldjyl!=" 没啥想说的" && oldjyl.length < 20){
					 		$("#jyl").val(oldjyl+";非常好");
					 	}else if(oldjyl=="" || oldjyl==" 没啥想说的"){
					 		$("#jyl").val("非常好");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jyl").val(oldjyl);
					 	}
					 });
		
					 $("#b4").click(function() {
					 	var oldjyl = $("#jyl")[0].value;
					 	if(oldjyl!="" && oldjyl!=" 没啥想说的" && oldjyl.length < 20){
					 		$("#jyl").val(oldjyl+";啥都记不住");
					 	}else if(oldjyl=="" || oldjyl==" 没啥想说的"){
					 		$("#jyl").val("啥都记不住");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jyl").val(oldjyl);
					 	}
					 });
					 
					 $("#b5").click(function() {
					 	var oldjyl = $("#jyl")[0].value;
					 	if(oldjyl!="" && oldjyl.length < 20){
					 		$("#jyl").val(oldjyl+";没啥想说的");
					 	}else if(oldjyl=="" || oldjyl==" 没啥想说的"){
					 		$("#jyl").val("没啥想说的");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jyl").val(oldjyl);
					 	}
					 });					 
					 
					// 学习效率，强度
					 $("#bu1").click(function() {
					 	var oldxlqd = $("#xlqd")[0].value;
					 	if(oldxlqd!="" &&oldxlqd!=" 没有" && oldxlqd.length < 20){
					 		$("#xlqd").val(oldxlqd+";还行");
					 	}else if(oldxlqd=="" || oldxlqd==" 没有"){
					 		$("#xlqd").val("还行");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xlqd").val(oldxlqd);
					 	}
					 });
					 
					 $("#bu2").click(function() {
					 	var oldxlqd = $("#xlqd")[0].value;
					 	if(oldxlqd!="" &&oldxlqd!=" 没有" && oldxlqd.length < 20){
					 		$("#xlqd").val(oldxlqd+";不错");
					 	}else if(oldxlqd=="" || oldxlqd==" 没有"){
					 		$("#xlqd").val("不错");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xlqd").val(oldxlqd);
					 	}
					 });
				
					 $("#bu3").click(function() {
					 	var oldxlqd = $("#xlqd")[0].value;
					 	if(oldxlqd!="" &&oldxlqd!=" 没有" && oldxlqd.length < 20){
					 		$("#xlqd").val(oldxlqd+";有点低");
					 	}else if(oldxlqd=="" || oldxlqd==" 没有"){
					 		$("#xlqd").val("有点低");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xlqd").val(oldxlqd);
					 	}
					 });
		
					 $("#bu4").click(function() {
					 	var oldxlqd = $("#xlqd")[0].value;
					 	if(oldxlqd!="" &&oldxlqd!=" 没有" && oldxlqd.length < 20){
					 		$("#xlqd").val(oldxlqd+";很高效");
					 	}else if(oldxlqd=="" || oldxlqd==" 没有"){
					 		$("#xlqd").val("很高效");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xlqd").val(oldxlqd);
					 	}
					 });
					 
					 $("#bu5").click(function() {
					 	var oldxlqd = $("#xlqd")[0].value;
					 	if(oldxlqd!="" &&oldxlqd!=" 没有" && oldxlqd.length < 20){
					 		$("#xlqd").val(oldxlqd+";说不好");
					 	}else if(oldxlqd=="" || oldxlqd==" 没有"){
					 		$("#xlqd").val("说不好");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xlqd").val(oldxlqd);
					 	}
					 });
					 
					 $("#bu6").click(function() {
					 	var oldxlqd = $("#xlqd")[0].value;
					 	if(oldxlqd!="" && oldxlqd.length < 20){
					 		$("#xlqd").val(oldxlqd+";没有");
					 	}else if(oldxlqd=="" || oldxlqd==" 没有"){
					 		$("#xlqd").val("没有");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xlqd").val(oldxlqd);
					 	}
					 });
					 
					 //比起昨天的进步
					 $("#but1").click(function() {
					 	var oldjb = $("#jb")[0].value;
					 	if(oldjb!="" && oldjb!=" 没啥想说的" && oldjb.length < 20){
					 		$("#jb").val(oldjb+";看书了");
					 	}else if(oldjb=="" ||oldjb==" 没啥想说的"){
					 		$("#jb").val("看书了");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jb").val(oldjb);
					 	}
					 });
					 
					 $("#but2").click(function() {
					 	var oldjb = $("#jb")[0].value;
					 	if(oldjb!="" && oldjb!=" 没啥想说的" && oldjb.length < 20){
					 		$("#jb").val(oldjb+";看视频了");
					 	}else if(oldjb=="" ||oldjb==" 没啥想说的"){
					 		$("#jb").val("看视频了");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jb").val(oldjb);
					 	}
					 });
				
					 $("#but3").click(function() {
					 	var oldjb = $("#jb")[0].value;
					 	if(oldjb!="" && oldjb!=" 没啥想说的" && oldjb.length < 20){
					 		$("#jb").val(oldjb+";效率提高了");
					 	}else if(oldjb=="" ||oldjb==" 没啥想说的"){
					 		$("#jb").val("效率提高了");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jb").val(oldjb);
					 	}
					 });
		
					 $("#but4").click(function() {
					 	var oldjb = $("#jb")[0].value;
					 	if(oldjb!="" && oldjb.length < 20){
					 		$("#jb").val(oldjb+";没啥想说的");
					 	}else if(oldjb==""){
					 		$("#jb").val("没啥想说的");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#jb").val(oldjb);
					 	}
					 });
					 
					 //我的书签，备忘
					 $("#butt1").click(function() {
					 	var oldshuqian = $("#shuqian")[0].value;
					 	if(oldshuqian!="" && oldshuqian!=" 没有" && oldshuqian.length < 20){
					 		$("#shuqian").val(oldshuqian+";报名");
					 	}else if(oldshuqian=="" ||oldshuqian==" 没有"){
					 		$("#shuqian").val("报名");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#shuqian").val(oldshuqian);
					 	}
					 });
					 
					 $("#butt2").click(function() {
					 	var oldshuqian = $("#shuqian")[0].value;
					 	if(oldshuqian!="" && oldshuqian!=" 没有" && oldshuqian.length < 20){
					 		$("#shuqian").val(oldshuqian+";查成绩");
					 	}else if(oldshuqian=="" ||oldshuqian==" 没有"){
					 		$("#shuqian").val("查成绩");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#shuqian").val(oldshuqian);
					 	}
					 });
				
					 $("#butt3").click(function() {
					 	var oldshuqian = $("#shuqian")[0].value;
					 	if(oldshuqian!="" && oldshuqian!=" 没有" && oldshuqian.length < 20){
					 		$("#shuqian").val(oldshuqian+";忘记了");
					 	}else if(oldshuqian=="" ||oldshuqian==" 没有"){
					 		$("#shuqian").val("忘记了");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#shuqian").val(oldshuqian);
					 	}
					 });
		
					 $("#butt4").click(function() {
					 	var oldshuqian = $("#shuqian")[0].value;
					 	if(oldshuqian!="" && oldshuqian.length < 20){
					 		$("#shuqian").val(oldshuqian+";没有");
					 	}else if(oldshuqian==""){
					 		$("#shuqian").val("没有");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#shuqian").val(oldshuqian);
					 	}
					 });
					 
					  $("#butt5").click(function() {
					 	var oldshuqian = $("#shuqian")[0].value;
					 	if(oldshuqian!="" && oldshuqian!=" 没有" && oldshuqian.length < 20){
					 		$("#shuqian").val(oldshuqian+";参加考试");
					 	}else if(oldshuqian=="" ||oldshuqian==" 没有"){
					 		$("#shuqian").val("参加考试");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#shuqian").val(oldshuqian);
					 	}
					 });
					 
					 //学习心得，体会
					 $("#btt1").click(function() {
					 	var oldxinde = $("#xinde")[0].value;
					 	if(oldxinde!="" && oldxinde!=" 没啥" && oldxinde.length < 20){
					 		$("#xinde").val(oldxinde+";长知识");
					 	}else if(oldxinde=="" || oldxinde==" 没啥"){
					 		$("#xinde").val("长知识");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xinde").val(oldxinde);
					 	}
					 });
					 
					 $("#btt2").click(function() {
					 	var oldxinde = $("#xinde")[0].value;
					 	if(oldxinde!="" && oldxinde!=" 没啥" && oldxinde.length < 20){
					 		$("#xinde").val(oldxinde+";太难了");
					 	}else if(oldxinde=="" || oldxinde==" 没啥"){
					 		$("#xinde").val("太难了");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xinde").val(oldxinde);
					 	}
					 });
				
					 $("#btt3").click(function() {
					 	var oldxinde = $("#xinde")[0].value;
					 	if(oldxinde!="" && oldxinde!=" 没啥" && oldxinde.length < 20){
					 		$("#xinde").val(oldxinde+";没明白");
					 	}else if(oldxinde=="" || oldxinde==" 没啥"){
					 		$("#xinde").val("没明白");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xinde").val(oldxinde);
					 	}
					 	
					 });
		
					 $("#btt4").click(function() {
					 	var oldxinde = $("#xinde")[0].value;
					 	if(oldxinde!="" && oldxinde!=" 没啥" && oldxinde.length < 20){
					 		$("#xinde").val(oldxinde+";效果不错");
					 	}else if(oldxinde=="" || oldxinde==" 没啥"){
					 		$("#xinde").val("效果不错");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xinde").val(oldxinde);
					 	}
					 	
					 });
					 
					 $("#btt5").click(function() {
					 	var oldxinde = $("#xinde")[0].value;
					 	if(oldxinde!=""  && oldxinde.length < 20){
					 		$("#xinde").val(oldxinde+";没啥");
					 	}else if(oldxinde=="" || oldxinde==" 没啥"){
					 		$("#xinde").val("没啥");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#xinde").val(oldxinde);
					 	}
					 	
					 });
					 
					 //明日 计划
					 $("#buu1").click(function() {
					 	var oldtmrplan = $("#tmrplan")[0].value;
					 	if(oldtmrplan!="" &&oldtmrplan!=" 没计划" && oldtmrplan.length < 20){
					 		$("#tmrplan").val(oldtmrplan+";看视频");
					 	}else if(oldtmrplan=="" || oldtmrplan==" 没计划"){
					 		$("#tmrplan").val("看视频");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#tmrplan").val(oldtmrplan);
					 	}
					 });
					 
					 $("#buu2").click(function() {
					 	var oldtmrplan = $("#tmrplan")[0].value;
					 	if(oldtmrplan!="" &&oldtmrplan!=" 没计划" && oldtmrplan.length < 20){
					 		$("#tmrplan").val(oldtmrplan+";做试卷");
					 	}else if(oldtmrplan=="" || oldtmrplan==" 没计划"){
					 		$("#tmrplan").val("做试卷");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#tmrplan").val(oldtmrplan);
					 	}
					 });
				
					 $("#buu3").click(function() {
					 	var oldtmrplan = $("#tmrplan")[0].value;
					 	if(oldtmrplan!="" &&oldtmrplan!=" 没计划" && oldtmrplan.length < 20){
					 		$("#tmrplan").val(oldtmrplan+";复习");
					 	}else if(oldtmrplan=="" || oldtmrplan==" 没计划"){
					 		$("#tmrplan").val("复习");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#tmrplan").val(oldtmrplan);
					 	}
					 });
		
					 $("#buu4").click(function() {
					 	var oldtmrplan = $("#tmrplan")[0].value;
					 	if(oldtmrplan!="" &&oldtmrplan!=" 没计划" && oldtmrplan.length < 20){
					 		$("#tmrplan").val(oldtmrplan+";预习");
					 	}else if(oldtmrplan=="" || oldtmrplan==" 没计划"){
					 		$("#tmrplan").val("预习");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#tmrplan").val(oldtmrplan);
					 	}
					 });
					 
					 $("#buu5").click(function() {
					 	var oldtmrplan = $("#tmrplan")[0].value;
					 	if(oldtmrplan!="" &&oldtmrplan!=" 没计划" && oldtmrplan.length < 20){
					 		$("#tmrplan").val(oldtmrplan+";好好学习");
					 	}else if(oldtmrplan=="" || oldtmrplan==" 没计划"){
					 		$("#tmrplan").val("好好学习");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#tmrplan").val(oldtmrplan);
					 	}
					 });
					 
					 $("#buu6").click(function() {
					 	var oldtmrplan = $("#tmrplan")[0].value;
					 	if(oldtmrplan!="" && oldtmrplan.length < 20){
					 		$("#tmrplan").val(oldtmrplan+";没计划");
					 	}else if(oldtmrplan=="" || oldtmrplan==" 没计划"){
					 		$("#tmrplan").val("没计划");
					 	}else{
					 		showErrorWin("最多填写20个字符","");
					 		$("#tmrplan").val(oldtmrplan);
					 	}
					 });					 
					 
					  //今日图片
					 $("#pic1").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(1);
					 });
					 
					 $("#pic2").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(2);
					 });
					 
					 $("#pic3").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(3);
					 });
					 
					 $("#pic4").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(4);
					 });
					 
					 $("#pic5").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(5);
					 });
					 
					 $("#pic6").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(6);
					 });
					 
					 $("#pic7").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(7);
					 });
					 
					 $("#pic8").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(8);
					 });
					 
					 $("#pic9").click(function(){
					 	var oldPic = $("#pic")[0].value;
					 	$("#pic").val(9);
					 });
			 }); 
		
		</script>
		
		<script type="text/javascript">
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
								 	
									displayexminfo+="<li>您这天做了"+"<font style='color:red;'>"+ val.substring(0,6)+"..."+"</font>"+"试卷。</li>";
									
									//alert(key); (只显示5条计入) 
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
									displayexminfo+="<li>您这天做了"+"<font style='color:red;'>"+ val.substring(0,6)+"..."+"</font>"+"试卷。</li>";
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
							success : function(result) {
							
							},
							error : function(error) {
								showErrorWin('error===InsertNotWatchExam','');
						   }
						});
				 }
	 
//======================华 丽 的 分 界 线===================================== 
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
								 
        							//alert(key); 
        							if(key=='5'){            
							            return false; //跳出循环  
							         }
							         
									displaycourseinfo+="<li>您这天学习"+"<font style='color:red;'>"+ val.substring(0,6)+"..."+"</font>"+"课程。</li>";
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
								         
										displaycourseinfo+="<li>您学习了"+"<font style='color:red;'>"+ val.substring(0,6)+"..."+"</font>"+"试卷。</li>";
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
							success : function(result) {
							
							},
							error : function(error) {
								showErrorWin('error===InsertNotWatchCourse','');
						   }
						});
				 }

		</script>
	
	
	<script language="JavaScript" type="text/javascript">  
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
	</script>
	
  	<script language="JavaScript" type="text/javascript">  
		function validateUpdateForm(){
			
			var xq = document.getElementById("xq").value.trim();
			var jh = document.getElementById("jh").value.trim();
			var jyl = document.getElementById("jyl").value.trim();
			var xlqd = document.getElementById("xlqd").value.trim();
			var jb = document.getElementById("jb").value.trim();
			var shuqian = document.getElementById("shuqian").value.trim();
			var xinde = document.getElementById("xinde").value.trim();
			var tmrplan = document.getElementById("tmrplan").value.trim();
			
			if(xq == "" ){
				$("#xq").val("没有");
			}
			if(jh == "" ){
				$("#jh").val("没啥计划");
			}
			if(jyl == "" ){
				$("#jyl").val("没啥想说的");
			}
			if(xlqd == "" ){
				$("#xlqd").val("没有");
			}
			if(jb == "" ){
				$("#jb").val("没啥想说的");
			}
			if(shuqian == "" ){
				$("#shuqian").val("没有");
			}
			if(xinde == "" ){
				$("#xinde").val("没啥");
			}
			if(tmrplan == "" ){
				$("#tmrplan").val("没计划");
			}
			
			
			if(xq == ""&& jh== "" && jyl == "" && xlqd == "" && jb == ""&&shuqian == "" &&xinde == "" && tmrplan == ""){
				showErrorWin('请至少选择一项','');
				return false;
			}else if(xq.length>20 || jh.length>20 || jyl.length>20 || xlqd.length>20 || jb.length>20 || shuqian.length>20 || xinde.length>20 || tmrplan.length>20){
				showErrorWin('最多填写20个字符','');
				return false;
			}else{
				return true;
			}
		}
		
		function selectedclcik(num){
		 	var i =1;
		 	for (i=1;i<=9;i++){
		 		$("#lea_expsp"+i).removeClass("selected");
		 	}
		 	$("#lea_expsp"+num).addClass("selected");
		 }
		 
		 //关闭
		function clocs(){
			$("#canclePopupContactText").html("<b>确认离开该页面？</b><br /><font size='2'>你正在编辑的计划尚未保存，离开会使内容丢失，确定离开吗？按'确定'继续，或按'取消'留在当前页面</font>");
			showWin("canclePopupContact");
		}	
	</script>
</head>
			<div class="contenter">
				<div id="lea_wrap">
					 <div id="lea_top">
	                  <div id="lea_weather">
	                   <iframe src="http://m.weather.com.cn/m/pn8/weather.htm " width="255" height="20" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" allowtransparency="true" scrolling="no"></iframe>
	                  </div>
	                  <div id="lea_time">当前时间：<span>22:33:55</span></div>
	                </div>				
						<!-- 没找到了学习计划 -->
						<div class="iepng" id="lea_left">
							<div class="lea_title2">
								<h2><s:property value="checkDay"/>学习计划</h2>
								<div class="lea_mod">
									<a href="<%=contextPath%>/stu/calendar!getPlanListByCalendardList.action?checkDay=<%=DateUtil.formatDate(new java.util.Date())%>&queryPlanCondition.currentPage=1">学习计划列表</a>
								</div>
							</div>
							<form onsubmit="return validateUpdateForm(this);" method="post"
								name="updateform" action="<%=contextPath %>/stu/studyplanWeb!updatePlanitem.action?planitem.planId=<s:property value='planitem.planId'/>">
								<dl class="lea_listbox iepng">
									<dt class="iepng">今日心情</dt>
									<dd class="lea_textdd">
										<input type="text" value="<s:property value='strArr[0]' />" name="planitem.itemtitle" class="iepng" id="xq">
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
								</dl>

								<dl class="lea_listbox iepng">
									<dt class="iepng">学习计划</dt>
									<dd class="lea_textdd">
										<input type="text" value="<s:property value='strArr[1]' />" name="planitem.itemtitle" class="iepng" id="jh">
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
										<input type="text" value="<s:property value='strArr[2]' />" name="planitem.itemtitle" class="iepng" id="jyl">
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
										<input type="text" value="<s:property value='strArr[3]' />" class="iepng" name="planitem.itemtitle" id="xlqd">
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
										<s:property value="checkDay"/> <input type="hidden" value="<s:property value='currentPlan.picon'/>" id="pic"
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
										<input type="text" value="<s:property value='strArr[4]' />" class="iepng"
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
										<input type="text" value="<s:property value='strArr[5]' />" class="iepng"
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
										<input type="text" value="<s:property value='strArr[6]' />" class="iepng"
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
										<input type="text" value="<s:property value='strArr[7]' />" class="iepng"
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
									<input type="submit" value="修改" name="" class="iepng">
									<input type="button" onclick="clocs();" value="取消"
										name="" class="iepng">
								</div>
							</form>
						</div>

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
											<li><div id="resExamText2">您这天做了<font style="color:red;"><s:property value="#examSummary.examname.substring(0,6)+'...'"/></font>试卷。</div></li>
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
