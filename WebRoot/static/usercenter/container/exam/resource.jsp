<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/exam.css?v=<%=version%>" />
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<!-- 小嗨的广告 08-29 -->
<!--<script type="text/javascript" src=" <%=importURL%>/js/web/public/higsorobot.js"></script>-->

<script type="text/javascript">
	var baselocation ='<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 10;
	$().ready(function() {
		$(".exam_sectt").click(function(){
			$(".exam_sec_pro").toggle(200);
		}) 
	});
	
	
	function examlist(name,value){
	var url = "${pageUrlParms}";
	var param = url.substring(url.indexOf("?"), url.length-1);
	var pageNoReg = new RegExp("\\.currentPage=[0-9]*");
	param=param.replace(pageNoReg,".currentPage=1");
	if(name!=''){
			if(param.indexOf(name)==-1){
				param+="&"+name+"="+value;
			}
			else {
				if(param.indexOf(name)+name.length+9>param.length)
				param=param.substring(0,param.indexOf(name)+name.length+1)+value;
				else
				param=param.substring(0,param.indexOf(name)+name.length+1)+value+param.substring(param.indexOf('&',param.indexOf(name)+name.length));
			}
	}
	if(name=='subjectIdweb'&value!='${subjectIdweb}'&param.indexOf('queryExampaperCondition.sortId')>0){
				if(param.indexOf('queryExampaperCondition.sortId')+'queryExampaperCondition.sortId'.length+5>param.length)
				param=param.substring(0,param.indexOf('queryExampaperCondition.sortId')-1);
				else
				param=param.substring(0,param.indexOf('queryExampaperCondition.sortId'))+param.substring(param.indexOf('&',param.indexOf('queryExampaperCondition.sortId')+'queryExampaperCondition.sortId'.length)+1);
	}
	document.examlist.action="<%=contextPath %>/exam/qstManager!getExamPaperAllList.action"+param;
	document.examlist.submit();
	}
	
	//添加收藏 add by yanghaibo 2012-07-10 15:04
	function addFavorites(epId,flag){
									
		$.ajax ({
			url: "<%=contextPath%>/exam/qstManager!changeFavoritesStatus.action",
			data :{"flag": flag,"exam.epId":epId},
			type :"post",
			dataType:"json",
			async : false,
			success : function(result){
				if(result.returnMessage == "success"){
					var displayId = "add_"+epId;
					var hiddenId = "del_"+epId;
					$('#'+displayId).hide();
					$('#'+hiddenId).show();
					alert("添加收藏成功！");
				}
			},
			error : function(error) {
				alert("添加失败！");
			}
	    });
	}
	
	//移除收藏 add by yanghaibo 2012-07-10 15:14
	function delFavorites(epId,flag){
									
		$.ajax ({
			url: "<%=contextPath%>/exam/qstManager!changeFavoritesStatus.action",
			data :{"flag": flag,"exam.epId":epId},
			type :"post",
			dataType:"json",
			async : false,
			success : function(result){
				if(result.returnMessage == "success"){
					var displayId = "add_"+epId;
					var hiddenId = "del_"+epId;
					$('#'+displayId).show();
					$('#'+hiddenId).hide();
					alert("移除收藏成功！");
				}
			},
			error : function(error) {
				alert("移除失败！");
			}
	    });
	}
</script>
