<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/include/header.inc" %>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_assess.css?v=<%=version%>" />
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<script language="javascript" src="<%=importURL%>/js/dis/ass_common.js?v=<%=version%>"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_user_common.js?v=<%=version%>"></script>
<script>
	//初始化变量
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 8;
    if(${status}==0)
    {
    	location.href="/cou/courselimit!noCourse.action";
    }

	//高亮显示当前专业
	function currSub(sid){
		if(sid!=null&&sid!=""){
			document.getElementById(sid).className="as_leibie_d";
		}
	}
	$(document).ready(
		function (){
			if(${status}==3){
				//用户满意度各星级比例图
				var total=${starInfo.kcAll};
				var scale1=${starInfo.kc1}/total;
				var scale2=${starInfo.kc2}/total;
				var scale3=${starInfo.kc3}/total;
				var scale4=${starInfo.kc4}/total;
				var scale5=${starInfo.kc5}/total;
				var scaleArr=[scale1,scale2,scale3,scale4,scale5];
				var totalL=$("#totalCount").width();
				for(var i=0;i<scaleArr.length;i++){
					$("#scale"+(i+1)).css("width",totalL*scaleArr[i]);
				}
				//求平均满意度
				var sum=scale1*1+scale2*2+scale3*3+scale4*4+scale5*5;
				var avg=sum/(scale1+scale2+scale3+scale4+scale5);
				avg=Math.round(parseFloat(avg)*10)/10;
				if(avg.toString().indexOf(".")<0)avg=avg.toString()+".0";
				$("#avg").html(avg);
				//是否满意
				var content;
				if(avg>4.0)content="很满意";
				else if(avg>3.0)content="满意";
				else if(avg>2.0)content="一般";
				else if(avg>1.0)content="不满意";
				else content="很不满意";
				$("#manyi").html(content);
				//满意星级图
				var curr_scale=avg/4.84;//由于长度显示不太精确，调整一下为4.84。
				var allstar=$("#allstar").width();
				var currstar=allstar*curr_scale;
				$("#currstar").css("width",currstar);
			}
			//好评星级图
			var topSize=$("#topSize").val();
			if(topSize>0){
				var topTotal=$("#topTotal0").width();
				for(var i=0;i<topSize;i++){
					var topScale=$("#top"+i).val()/5;
					var currTop=topTotal*topScale;
					$("#topStar"+i).width(currTop);
				}
			}
		}
	)
	function toMore(type,subId,currCount){
			if(currCount<10)return;
			if(type==1)
				window.location.href="<%=contextPath%>/ass/assweb!toMoreAssess.action?queryAssessCondition.currentPage=1&currSub.subjectId="+subId;
			else 
				window.location.href="<%=contextPath%>/ass/assweb!toMoreUnAssess.action?queryAssessCondition.currentPage=1&currSub.subjectId="+subId;
	}
	function tx(){
		showErrorWin("您尚未购买此课程，请先购买","");
	}
</script>