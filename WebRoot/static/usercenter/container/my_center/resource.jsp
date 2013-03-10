<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/newthings.css?v=<%=version%>" />
<link href="<%=importURL%>/styles/usercenter/uc_index.css?v=<%=version%>" rel="stylesheet"/>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/ucenter.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_userFeed.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_freshnew.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/ucenter_check.js?v=<%=version%>"></script>
<script type="text/javascript">
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 1;
	var ishavebuy="${ishavebuy}";//只为显示用
	
	var ordersize=1;
	if(ishavebuy==true || ishavebuy=="true"){
		ordersize=1;
		SetCookie("ishavebuy",1);
	}else{
		ordersize=0;
		SetCookie("ishavebuy",0);
	}
	
	function getbollvie(){
	
		if(ishavebuy==true || ishavebuy=="true"){
			 return(1);
		}else{
			return(0);
		}
	}
</script>
<script>
   $().ready(function() {
		checkCusInfo();//视频观看记录
        getNewSellRecord('${sellIds}');//获取商品最新数据
        getClickSellRecord('${sellIds}');//获取商品点击数据
        getEvaluateRecord('${sellIds}');//获取评价TOP数据
        getAcmentTop('${subjectIds}');//获取专业公告信息
		lazyloadfresh();//新鲜事
		load();
		//lazyloadfresh_me();//我的动态
		//load_me();
		commentView();//用户反馈
		//进行数据加载
		loadData();//
		getScore();
		insertCourseMsgs();//最新上传课程消息
		SendMsgByCus();//最新上传试卷消息
		getMsgByCus();//消息提醒
		gettamne();//他们在嗨学
	});

</script>  

