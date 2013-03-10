<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_order.css?v=<%=version%>" />
<link href="<%=importURL%>/styles/usercenter/uc_popup.css?v=<%=version%>" rel="stylesheet" type="text/css" /> 
<link href="<%=importURL%>/styles/usercenter/popup.css?v=<%=version%>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=importURL%>/js/web/public/jquery.blockUI.js"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_user_common.js?v=<%=version%>"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_caiwu.js?v=<%=version%>"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_card.js?v=<%=version%>"></script>
<script type="text/javascript">
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 3;
	var tsecond = 3; 
	$().ready(function() {
		SwitchCaiwu(<s:property value="location"/>);
		var username2 = getCookieFromServer("sedu.cookie.ukey").split(',')[3];
		$("#uc_header_username2").html(username2);
		$("#uc_header_username3").html(username2);
		$("#uc_header_username4").html(username2);
		$("span.order_prompt_close").click(function() {
		        $("div.order_prompt").hide();
		 });
	});
			 
	function locking(k){ 
		if($.browser.msie ){
			$("#obj").css("display","none");
		}else if($.browser.safari){
			$("#obj").css("display","none");
		}
	}
			 
	function gotoPay(cid,price,cno,ispaid){
		$("#orderId").val(cid);
		$("#orderno").val(cno);
		if(ispaid == 0){
			if(price > 450){
				if($.browser.msie) { 
					var browser=navigator.appName;
					var b_version=navigator.appVersion;
					var version=b_version.split(";");
					var trim_Version=version[1].replace(/[ ]/g,"");
					if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE6.0")
					{
						$("#closeBtnX").css("left","173px");
					}
					else if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE7.0")
					{
						$("#closeBtnX").css("left","173px");
					}
					else{
						$("#closeBtnX").css("left","346px");
					}
				}else{
					$("#closeBtnX").css("left","346px");
				} 
			jQuery.blockUI({ 
				message: $("#pwin"), 
				css: {
					color:'#aaa',
					width: '50px',
					border:'0px solid #aaa',
					cursor:"default",
					backgroundColor:'#CC3300'
					},
				overlayCSS: { opacity:'0.5',cursor:"default"}
			});
		}else{
			window.location.href='<%=contextPath%>/finance/cashRecord!payContract.action?contract.id=' + cid;
		}
	}else{
		window.location.href = "<%=contextPath%>/alipay/subsc!genSC.action?cno=" + cno;
					 /*
					 jQuery.blockUI({ 
					    	message: $("#childWin"), 
					    	css: {
					    		color:'#aaa',
					    		width: 400,
					    		border:'0px solid #aaa',
					    		cursor:"default",
					    		backgroundColor:'#CC3300'
					    		},
					    		overlayCSS: { opacity:'0.5',cursor:"default"}
					    });
					 */
					 //setInterval("timerCharChange()",1000);
			}
	}
			 
	function gotoSubContract(){
		var cno = $("#orderno").val();
		window.location.href = "<%=contextPath%>/alipay/subsc!genSC.action?cno=" + cno;
	}
		    
	function gotoOriPay(){
		 var cid = $("#orderId").val();
		 window.location.href='<%=contextPath%>/finance/cashRecord!payContract.action?contract.id=' + cid;
	}
		    
	function closeStepWin(){
		 jQuery.unblockUI();
	}
		    
	function timerCharChange(){
		 var cno = $("#orderno").val();
		 $("#second").text(tsecond);
		 tsecond = tsecond - 1;
		 if(tsecond <= -1){
			closeStepWin();
			window.location.href = "<%=contextPath%>/alipay/subsc!genSC.action?cno=" + cno;
		}
    }
		
	function showProtocal(obj){
		$(".orderPaul").fadeOut(200);
		$("#"+obj).fadeIn(200);
	}
		
	function closeProtocal(obj){
		$("#"+obj).fadeOut(200);
	}
	
	function CardClose(){
		$("#activateCardCourseDiv").hide();
		$("#activateCardCourse").show();
	}
		
	function successClose(){
		$('#activateCardCourseSuccessDiv').hide();
		$("#activateCardCourse").show();
	}
	function closeError(){
		$("#activateCardCourseFalseDiv").hide();
		$("#activateCardCourse").show();
	}
</script>