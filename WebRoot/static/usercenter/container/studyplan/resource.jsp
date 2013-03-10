<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<link href="<%=importURL%>/styles/usercenter/uc_learn.css?v=<%=version%>" rel="stylesheet">
<link href="<%=importURL%>/styles/usercenter/calendar-ui2.css?v=<%=version%>" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/popup.css?v=<%=version%>" />
<script type="text/javascript" src="<%=importURL%>/js/web/public/web_jquery-jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_highcharts.src.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_user_center.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_user_common.js?v=<%=version%>"></script>
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
	$(".ui-state-default iepng").click(function(){
		$(".markTip").addClass("tip iepng");
		$('#calendarUi a').removeClass('ui-state-active');
		$(this).removeClass("tip").addClass("ui-state-active");
	});


		$("#xq").focus(function(){
			var xq_value = $(this).val();
			if(xq_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		$("#jh").focus(function(){
			var jh_value = $(this).val();
			if(jh_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		$("#jyl").focus(function(){
			var jyl_value = $(this).val();
			if(jyl_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		$("#xlqd").focus(function(){
			var xlqd_value = $(this).val();
			if(xlqd_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		$("#jb").focus(function(){
			var jb_value = $(this).val();
			if(jb_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		$("#shuqian").focus(function(){
			var shuqian_value = $(this).val();
			if(shuqian_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		$("#xinde").focus(function(){
			var xinde_value = $(this).val();
			if(xinde_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		$("#tmrplan").focus(function(){
			var tmrplan_value = $(this).val();
			if(tmrplan_value=="请在此输入文字"){
				$(this).val("");
			}
		});
		
		//今日心情
	 $("#btn1").click(function() {
	 	var oldxq = $("#xq")[0].value;
	 	if(oldxq!="" && oldxq!="请在此输入文字" && oldxq.length < 20){
	 		$("#xq").val(oldxq+";还不错");
	 	}else if(oldxq=="" ||oldxq=="请在此输入文字"){
	 		$("#xq").val("还不错");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xq").val(oldxq);
	 	}
	 });
	 
	 $("#btn2").click(function() {
	 	var oldxq = $("#xq")[0].value;
	 	if(oldxq!="" && oldxq!="请在此输入文字" && oldxq.length < 20){
	 		$("#xq").val(oldxq+";没有");
	 	}else if(oldxq=="" ||oldxq=="请在此输入文字"){
	 		$("#xq").val("没有");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xq").val(oldxq);
	 	}
	 });

	 $("#btn3").click(function() {
	 	var oldxq = $("#xq")[0].value;
	 	if(oldxq!="" && oldxq!="请在此输入文字" && oldxq.length < 20){
	 		$("#xq").val(oldxq+";无聊");
	 	}else if(oldxq=="" ||oldxq=="请在此输入文字"){
	 		$("#xq").val("无聊");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xq").val(oldxq);
	 	}
	 });

	 $("#btn4").click(function() {
	 	var oldxq = $("#xq")[0].value;
	 	if(oldxq!="" && oldxq!="请在此输入文字" && oldxq.length < 20){
	 		$("#xq").val(oldxq+";开心");
	 	}else if(oldxq=="" ||oldxq=="请在此输入文字"){
	 		$("#xq").val("开心");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xq").val(oldxq);
	 	}
	 });
	 
	 $("#btn5").click(function() {
	 	var oldxq = $("#xq")[0].value;
	 	if(oldxq!="" && oldxq!="请在此输入文字" && oldxq.length < 20){
	 		$("#xq").val(oldxq+";累啊");
	 	}else if(oldxq=="" ||oldxq=="请在此输入文字"){
	 		$("#xq").val("累啊");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xq").val(oldxq);
	 	}
	 	
	 });
	 
	 $("#btn6").click(function() {
	 	var oldxq = $("#xq")[0].value;
	 	if(oldxq!="" && oldxq!="请在此输入文字" && oldxq.length < 20){
	 		$("#xq").val(oldxq+";有点冷");
	 	}else if(oldxq=="" ||oldxq=="请在此输入文字"){
	 		$("#xq").val("有点冷");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xq").val(oldxq);
	 	}
	 	
	 });
	 
	 $("#btn7").click(function() {
	 	var oldxq = $("#xq")[0].value;
	 	if(oldxq!="" && oldxq!="请在此输入文字" && oldxq.length < 20){
	 		$("#xq").val(oldxq+";给力");
	 	}else if(oldxq=="" ||oldxq=="请在此输入文字"){
	 		$("#xq").val("给力");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xq").val(oldxq);
	 	}
	 });	
	 
	 //学习计划
	 $("#bt1").click(function() {
	 	var oldjh = $("#jh")[0].value;
	 	if(oldjh!="" && oldjh!="请在此输入文字" && oldjh.length < 20){
	 		$("#jh").val(oldjh+";看视频");
	 	}else if(oldjh=="" || oldjh=="请在此输入文字"){
	 		$("#jh").val("看视频");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jh").val(oldjh);
	 	}
	 });
	 
	 $("#bt2").click(function() {
	 	var oldjh = $("#jh")[0].value;
	 	if(oldjh!="" && oldjh!="请在此输入文字" && oldjh.length < 20){
	 		$("#jh").val(oldjh+";看书");
	 	}else if(oldjh=="" || oldjh=="请在此输入文字"){
	 		$("#jh").val("看书");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jh").val(oldjh);
	 	}
	 });

	 $("#bt3").click(function() {
	 	var oldjh = $("#jh")[0].value;
	 	if(oldjh!="" && oldjh!="请在此输入文字" && oldjh.length < 20){
	 		$("#jh").val(oldjh+";做试卷");
	 	}else if(oldjh=="" || oldjh=="请在此输入文字"){
	 		$("#jh").val("做试卷");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jh").val(oldjh);
	 	}
	 });

	 $("#bt4").click(function() {
	 	var oldjh = $("#jh")[0].value;
	 	if(oldjh!="" && oldjh!="请在此输入文字" && oldjh.length < 20){
	 		$("#jh").val(oldjh+";复习");
	 	}else if(oldjh=="" || oldjh=="请在此输入文字"){
	 		$("#jh").val("复习");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jh").val(oldjh);
	 	}
	 });
	 
	 $("#bt5").click(function() {
	 	var oldjh = $("#jh")[0].value;
	 	if(oldjh!="" && oldjh!="请在此输入文字" && oldjh.length < 20){
	 		$("#jh").val(oldjh+";预习");
	 	}else if(oldjh=="" || oldjh=="请在此输入文字"){
	 		$("#jh").val("预习");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jh").val(oldjh);
	 	}
	 });
	 
	 $("#bt6").click(function() {
	 	var oldjh = $("#jh")[0].value;
	 	if(oldjh!="" && oldjh!="请在此输入文字" && oldjh.length < 20){
	 		$("#jh").val(oldjh+";没计划");
	 	}else if(oldjh=="" || oldjh=="请在此输入文字"){
	 		$("#jh").val("没计划");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jh").val(oldjh);
	 	}
	 });
	 
	 $("#bt7").click(function() {
	 	var oldjh = $("#jh")[0].value;
	 	if(oldjh!="" && oldjh!="请在此输入文字" && oldjh.length < 20){
	 		$("#jh").val(oldjh+";今天我休息");
	 	}else if(oldjh=="" || oldjh=="请在此输入文字"){
	 		$("#jh").val("今天我休息");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jh").val(oldjh);
	 	}
	 });	
	 
	 // 记忆力注意力
	 $("#b1").click(function() {
	 	var oldjyl = $("#jyl")[0].value;
	 	if(oldjyl!="" && oldjyl!="请在此输入文字" && oldjyl.length < 20){
	 		$("#jyl").val(oldjyl+";还行啊");
	 	}else if(oldjyl=="" || oldjyl=="请在此输入文字"){
	 		$("#jyl").val("还行啊");
	 	}else{
	 		$("#jyl").val("还行啊");
	 		showErrorWin("最多填写20个字符","");
	 		$("#jyl").val(oldjyl);
	 	}
	 });
	 
	 $("#b2").click(function() {
	 	var oldjyl = $("#jyl")[0].value;
	 	if(oldjyl!="" && oldjyl!="请在此输入文字" && oldjyl.length < 20){
	 		$("#jyl").val(oldjyl+";老忘事");
	 	}else if(oldjyl=="" || oldjyl=="请在此输入文字"){
	 		$("#jyl").val("老忘事");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jyl").val(oldjyl);
	 	}
	 });

	 $("#b3").click(function() {
	 	var oldjyl = $("#jyl")[0].value;
	 	if(oldjyl!="" && oldjyl!="请在此输入文字" && oldjyl.length < 20){
	 		$("#jyl").val(oldjyl+";非常好");
	 	}else if(oldjyl=="" || oldjyl=="请在此输入文字"){
	 		$("#jyl").val("非常好");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jyl").val(oldjyl);
	 	}
	 });

	 $("#b4").click(function() {
	 	var oldjyl = $("#jyl")[0].value;
	 	if(oldjyl!="" && oldjyl!="请在此输入文字" && oldjyl.length < 20){
	 		$("#jyl").val(oldjyl+";啥都记不住");
	 	}else if(oldjyl=="" || oldjyl=="请在此输入文字"){
	 		$("#jyl").val("啥都记不住");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jyl").val(oldjyl);
	 	}
	 });
	 
	 $("#b5").click(function() {
	 	var oldjyl = $("#jyl")[0].value;
	 	if(oldjyl!="" && oldjyl!="请在此输入文字" && oldjyl.length < 20){
	 		$("#jyl").val(oldjyl+";没啥想说的");
	 	}else if(oldjyl=="" || oldjyl=="请在此输入文字"){
	 		$("#jyl").val("没啥想说的");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jyl").val(oldjyl);
	 	}
	 });
	 
	// 学习效率，强度
	 $("#bu1").click(function() {
	 	var oldxlqd = $("#xlqd")[0].value;
	 	if(oldxlqd!="" &&oldxlqd!="请在此输入文字" && oldxlqd.length < 20){
	 		$("#xlqd").val(oldxlqd+";还行");
	 	}else if(oldxlqd=="" || oldxlqd=="请在此输入文字"){
	 		$("#xlqd").val("还行");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xlqd").val(oldxlqd);
	 	}
	 });
	 
	 $("#bu2").click(function() {
	 	var oldxlqd = $("#xlqd")[0].value;
	 	if(oldxlqd!="" &&oldxlqd!="请在此输入文字" && oldxlqd.length < 20){
	 		$("#xlqd").val(oldxlqd+";不错");
	 	}else if(oldxlqd=="" || oldxlqd=="请在此输入文字"){
	 		$("#xlqd").val("不错");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xlqd").val(oldxlqd);
	 	}
	 });

	 $("#bu3").click(function() {
	 	var oldxlqd = $("#xlqd")[0].value;
	 	if(oldxlqd!="" &&oldxlqd!="请在此输入文字" && oldxlqd.length < 20){
	 		$("#xlqd").val(oldxlqd+";有点低");
	 	}else if(oldxlqd=="" || oldxlqd=="请在此输入文字"){
	 		$("#xlqd").val("有点低");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xlqd").val(oldxlqd);
	 	}
	 });

	 $("#bu4").click(function() {
	 	var oldxlqd = $("#xlqd")[0].value;
	 	if(oldxlqd!="" &&oldxlqd!="请在此输入文字" && oldxlqd.length < 20){
	 		$("#xlqd").val(oldxlqd+";很高效");
	 	}else if(oldxlqd=="" || oldxlqd=="请在此输入文字"){
	 		$("#xlqd").val("很高效");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xlqd").val(oldxlqd);
	 	}
	 });
	 
	 $("#bu5").click(function() {
	 	var oldxlqd = $("#xlqd")[0].value;
	 	if(oldxlqd!="" &&oldxlqd!="请在此输入文字" && oldxlqd.length < 20){
	 		$("#xlqd").val(oldxlqd+";说不好");
	 	}else if(oldxlqd=="" || oldxlqd=="请在此输入文字"){
	 		$("#xlqd").val("说不好");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xlqd").val(oldxlqd);
	 	}
	 });
	 
	 $("#bu6").click(function() {
	 	var oldxlqd = $("#xlqd")[0].value;
	 	if(oldxlqd!="" &&oldxlqd!="请在此输入文字" && oldxlqd.length < 20){
	 		$("#xlqd").val(oldxlqd+";没有");
	 	}else if(oldxlqd=="" || oldxlqd=="请在此输入文字"){
	 		$("#xlqd").val("没有");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xlqd").val(oldxlqd);
	 	}
	 });					 
	 
	 //比起昨天的进步
	 $("#but1").click(function() {
	 	var oldjb = $("#jb")[0].value;
	 	if(oldjb!="" && oldjb!="请在此输入文字" && oldjb.length < 20){
	 		$("#jb").val(oldjb+";看书了");
	 	}else if(oldjb=="" ||oldjb=="请在此输入文字"){
	 		$("#jb").val("看书了");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jb").val(oldjb);
	 	}
	 });
	 
	 $("#but2").click(function() {
	 	var oldjb = $("#jb")[0].value;
	 	if(oldjb!="" && oldjb!="请在此输入文字" && oldjb.length < 20){
	 		$("#jb").val(oldjb+";看视频了");
	 	}else if(oldjb=="" ||oldjb=="请在此输入文字"){
	 		$("#jb").val("看视频了");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jb").val(oldjb);
	 	}
	 });

	 $("#but3").click(function() {
	 	var oldjb = $("#jb")[0].value;
	 	if(oldjb!="" && oldjb!="请在此输入文字" && oldjb.length < 20){
	 		$("#jb").val(oldjb+";效率提高了");
	 	}else if(oldjb=="" ||oldjb=="请在此输入文字"){
	 		$("#jb").val("效率提高了");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jb").val(oldjb);
	 	}
	 });

	 $("#but4").click(function() {
	 	var oldjb = $("#jb")[0].value;
	 	if(oldjb!="" && oldjb!="请在此输入文字" && oldjb.length < 20){
	 		$("#jb").val(oldjb+";没啥想说的");
	 	}else if(oldjb=="" ||oldjb=="请在此输入文字"){
	 		$("#jb").val("没啥想说的");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#jb").val(oldjb);
	 	}
	 });
	 
	 //我的书签，备忘
	 $("#butt1").click(function() {
	 	var oldshuqian = $("#shuqian")[0].value;
	 	if(oldshuqian!="" && oldshuqian!="请在此输入文字" && oldshuqian.length < 20){
	 		$("#shuqian").val(oldshuqian+";报名");
	 	}else if(oldshuqian=="" ||oldshuqian=="请在此输入文字"){
	 		$("#shuqian").val("报名");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#shuqian").val(oldshuqian);
	 	}
	 });
	 
	 $("#butt2").click(function() {
	 	var oldshuqian = $("#shuqian")[0].value;
	 	if(oldshuqian!="" && oldshuqian!="请在此输入文字" && oldshuqian.length < 20){
	 		$("#shuqian").val(oldshuqian+";查成绩");
	 	}else if(oldshuqian=="" ||oldshuqian=="请在此输入文字"){
	 		$("#shuqian").val("查成绩");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#shuqian").val(oldshuqian);
	 	}
	 });

	 $("#butt3").click(function() {
	 	var oldshuqian = $("#shuqian")[0].value;
	 	if(oldshuqian!="" && oldshuqian!="请在此输入文字" && oldshuqian.length < 20){
	 		$("#shuqian").val(oldshuqian+";忘记了");
	 	}else if(oldshuqian=="" ||oldshuqian=="请在此输入文字"){
	 		$("#shuqian").val("忘记了");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#shuqian").val(oldshuqian);
	 	}
	 });

	 $("#butt4").click(function() {
	 	var oldshuqian = $("#shuqian")[0].value;
	 	if(oldshuqian!="" && oldshuqian!="请在此输入文字" && oldshuqian.length < 20){
	 		$("#shuqian").val(oldshuqian+";没有");
	 	}else if(oldshuqian=="" ||oldshuqian=="请在此输入文字"){
	 		$("#shuqian").val("没有");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#shuqian").val(oldshuqian);
	 	}
	 });
	 
	  $("#butt5").click(function() {
	 	var oldshuqian = $("#shuqian")[0].value;
	 	if(oldshuqian!="" && oldshuqian!="请在此输入文字" && oldshuqian.length < 20){
	 		$("#shuqian").val(oldshuqian+";参加考试");
	 	}else if(oldshuqian=="" ||oldshuqian=="请在此输入文字"){
	 		$("#shuqian").val("参加考试");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#shuqian").val(oldshuqian);
	 	}
	 });
	 
	 //学习心得，体会
	 $("#btt1").click(function() {
	 	var oldxinde = $("#xinde")[0].value;
	 	if(oldxinde!="" && oldxinde!="请在此输入文字" && oldxinde.length < 20){
	 		$("#xinde").val(oldxinde+";长知识");
	 	}else if(oldxinde=="" || oldxinde=="请在此输入文字"){
	 		$("#xinde").val("长知识");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xinde").val(oldxinde);
	 	}
	 });
	 
	 $("#btt2").click(function() {
	 	var oldxinde = $("#xinde")[0].value;
	 	if(oldxinde!="" && oldxinde!="请在此输入文字" && oldxinde.length < 20){
	 		$("#xinde").val(oldxinde+";太难了");
	 	}else if(oldxinde=="" || oldxinde=="请在此输入文字"){
	 		$("#xinde").val("太难了");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xinde").val(oldxinde);
	 	}
	 });

	 $("#btt3").click(function() {
	 	var oldxinde = $("#xinde")[0].value;
	 	if(oldxinde!="" && oldxinde!="请在此输入文字" && oldxinde.length < 20){
	 		$("#xinde").val(oldxinde+";没明白");
	 	}else if(oldxinde=="" || oldxinde=="请在此输入文字"){
	 		$("#xinde").val("没明白");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xinde").val(oldxinde);
	 	}
	 	
	 });

	 $("#btt4").click(function() {
	 	var oldxinde = $("#xinde")[0].value;
	 	if(oldxinde!="" && oldxinde!="请在此输入文字" && oldxinde.length < 20){
	 		$("#xinde").val(oldxinde+";效果不错");
	 	}else if(oldxinde=="" || oldxinde=="请在此输入文字"){
	 		$("#xinde").val("效果不错");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xinde").val(oldxinde);
	 	}
	 	
	 });

	 $("#btt5").click(function() {
	 	var oldxinde = $("#xinde")[0].value;
	 	if(oldxinde!="" && oldxinde!="请在此输入文字" && oldxinde.length < 20){
	 		$("#xinde").val(oldxinde+";没啥");
	 	}else if(oldxinde=="" || oldxinde=="请在此输入文字"){
	 		$("#xinde").val("没啥");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#xinde").val(oldxinde);
	 	}
	 	
	 });					 
	 //明日 计划
	 $("#buu1").click(function() {
	 	var oldtmrplan = $("#tmrplan")[0].value;
	 	if(oldtmrplan!="" &&oldtmrplan!="请在此输入文字" && oldtmrplan.length < 20){
	 		$("#tmrplan").val(oldtmrplan+";看视频");
	 	}else if(oldtmrplan=="" || oldtmrplan=="请在此输入文字"){
	 		$("#tmrplan").val("看视频");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#tmrplan").val(oldtmrplan);
	 	}
	 });
	 
	 $("#buu2").click(function() {
	 	var oldtmrplan = $("#tmrplan")[0].value;
	 	if(oldtmrplan!="" &&oldtmrplan!="请在此输入文字" && oldtmrplan.length < 20){
	 		$("#tmrplan").val(oldtmrplan+";做试卷");
	 	}else if(oldtmrplan=="" || oldtmrplan=="请在此输入文字"){
	 		$("#tmrplan").val("做试卷");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#tmrplan").val(oldtmrplan);
	 	}
	 });

	 $("#buu3").click(function() {
	 	var oldtmrplan = $("#tmrplan")[0].value;
	 	if(oldtmrplan!="" &&oldtmrplan!="请在此输入文字" && oldtmrplan.length < 20){
	 		$("#tmrplan").val(oldtmrplan+";复习");
	 	}else if(oldtmrplan=="" || oldtmrplan=="请在此输入文字"){
	 		$("#tmrplan").val("复习");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#tmrplan").val(oldtmrplan);
	 	}
	 });

	 $("#buu4").click(function() {
	 	var oldtmrplan = $("#tmrplan")[0].value;
	 	if(oldtmrplan!="" &&oldtmrplan!="请在此输入文字" && oldtmrplan.length < 20){
	 		$("#tmrplan").val(oldtmrplan+";预习");
	 	}else if(oldtmrplan=="" || oldtmrplan=="请在此输入文字"){
	 		$("#tmrplan").val("预习");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#tmrplan").val(oldtmrplan);
	 	}
	 });
	 
	 $("#buu5").click(function() {
	 	var oldtmrplan = $("#tmrplan")[0].value;
	 	if(oldtmrplan!="" &&oldtmrplan!="请在此输入文字" && oldtmrplan.length < 20){
	 		$("#tmrplan").val(oldtmrplan+";好好学习");
	 	}else if(oldtmrplan=="" || oldtmrplan=="请在此输入文字"){
	 		$("#tmrplan").val("好好学习");
	 	}else{
	 		showErrorWin("最多填写20个字符","");
	 		$("#tmrplan").val(oldtmrplan);
	 	}
	 });
	 
	 $("#buu6").click(function() {
	 	var oldtmrplan = $("#tmrplan")[0].value;
	 	if(oldtmrplan!="" &&oldtmrplan!="请在此输入文字" && oldtmrplan.length < 20){
	 		$("#tmrplan").val(oldtmrplan+";没计划");
	 	}else if(oldtmrplan=="" || oldtmrplan=="请在此输入文字"){
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
					//showErrorWin('您这天学习课程哦！','');
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
						showErrorWin('error===InsertNotWatchCourse', '');
					}
				});
	}
	var now, hours, minutes, seconds, timeValue;
	function showtime() {
		now = new Date();

		hours = now.getHours();
		minutes = now.getMinutes();
		seconds = now.getSeconds();
		timeValue = "";
		timeValue += hours + ":";
		timeValue += ((minutes < 10) ? "0" : "") + minutes + ":";
		timeValue += ((seconds < 10) ? "0" : "") + seconds;
		$("#lea_time").html("当前时间：" + timeValue);
		setTimeout("showtime()", 100);
	}
	showtime();
	function selectedclcik(num) {
		var i = 1;
		for (i = 1; i <= 9; i++) {
			$("#lea_expsp" + i).removeClass("selected");
		}
		$("#lea_expsp" + num).addClass("selected");
	}

	function clearForm(formName) {
		var formObj = document.forms[formName];
		var formEl = formObj.elements;
		for ( var i = 0; i < formEl.length; i++) {
			var element = formEl[i];
			if (element.type == 'submit') {
				continue;
			}
			if (element.type == 'button') {
				continue;
			}
			if (element.type == 'hidden') {
				continue;
			}
			if (element.type == 'text') {
				element.value = '';
			}
		}
	}

	function validateAddForm() {

		var xq = document.getElementById("xq").value.trim();
		var jh = document.getElementById("jh").value.trim();
		var jyl = document.getElementById("jyl").value.trim();
		var xlqd = document.getElementById("xlqd").value.trim();
		var jb = document.getElementById("jb").value.trim();
		var shuqian = document.getElementById("shuqian").value.trim();
		var xinde = document.getElementById("xinde").value.trim();
		var tmrplan = document.getElementById("tmrplan").value.trim();

		if (xq == "请在此输入文字" || xq == "") {
			$("#xq").val("没有");
		}
		if (jh == "请在此输入文字" || jh == "") {
			$("#jh").val("没啥计划");
		}
		if (jyl == "请在此输入文字" || jyl == "") {
			$("#jyl").val("没啥想说的");
		}
		if (xlqd == "请在此输入文字" || xlqd == "") {
			$("#xlqd").val("没有");
		}
		if (jb == "请在此输入文字" || jb == "") {
			$("#jb").val("没啥想说的");
		}
		if (shuqian == "请在此输入文字" || shuqian == "") {
			$("#shuqian").val("没有");
		}
		if (xinde == "请在此输入文字" || xinde == "") {
			$("#xinde").val("没啥");
		}
		if (tmrplan == "请在此输入文字" || tmrplan == "") {
			$("#tmrplan").val("没计划");
		}

		if (xq == "" && jh == "" && jyl == "" && xlqd == "" && jb == ""
				&& shuqian == "" && xinde == "" && tmrplan == "") {
			showErrorWin('请至少选择一项', '');
			return false;
		} else if (xq.length > 20 || jh.length > 20 || jyl.length > 20
				|| xlqd.length > 20 || jb.length > 20 || shuqian.length > 20
				|| xinde.length > 20 || tmrplan.length > 20) {
			showErrorWin('最多填写20个字符', '');
			return false;
		} else {
			return true;
		}
	}
	function delPlan() {
		$("#popupContactDeleteInfoText").html("<b>确认要删除学习计划？</b><br /><font size='2'>删除学习计划后将无法恢复。按'确定'继续，按'取消'停留在当前页面</font>");
		showWin("popupContactDeleteInfo");
	}
</script>