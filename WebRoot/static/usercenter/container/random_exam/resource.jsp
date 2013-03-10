<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_exam.css?v=<%=version%>" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/exam.css?v=<%=version%>" />
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<!-- 小嗨的广告 08-29 -->
<script type="text/javascript">
	var baselocation ='<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 10;
    var difficult_set=1;
	function quick_select(set_v){
	
	 if(set_v==1){
	 $("input[name='difficult_set']").val(1);
	 $("#grade_1").click();
	$(".exam_random_con4 > a").eq(0).addClass("quickred");
	$(".exam_random_con4 > a").eq(1).removeClass("quickred");
	$(".exam_random_con4 > a").eq(2).removeClass("quickred");
	
	 }
	 else if(set_v==2){	 
	  $("input[name='difficult_set']").val(2);
	  difficult_set=2;
	  $("#grade_2").click();
	 $(".exam_random_con4 > a").eq(1).addClass("quickred");
	 $(".exam_random_con4 > a").eq(0).removeClass("quickred");
	 $(".exam_random_con4 > a").eq(2).removeClass("quickred");
	 }else{
	   $("input[name='difficult_set']").val(3);
	   difficult_set=3;
	  $("#grade_3").click();
	 $(".exam_random_con4 > a").eq(2).addClass("quickred");
	$(".exam_random_con4 > a").eq(1).removeClass("quickred");
	$(".exam_random_con4 > a").eq(0).removeClass("quickred");
	 }
	}
	$().ready(function() {
	  $("#grade_1,#grade_2,#grade_3").bind("click",function(a){
	  $("#loading_tr > td").css("background","#848484");
	  $(this).css("background","#76EE00");
	   $("input[name='difficult_set']").val($(this).attr('name'));
	   difficult_set=$(this).attr('name');
	 	 
	});
		quick_select('1');
	});
	function subm(){
	if($("input[name='subjectIdweb']:checked").val()==null){
	 alert("请选择一个专业开始做题！");
	 return false;
	}
	subject
	if($("input[name='qst_type']:checked").val()==null){
	 alert("请选择覆盖题型范围！");
	 return false;
	}
		 $("input[name='difficult_set']").val(difficult_set);
		 return true;
	}
</script>