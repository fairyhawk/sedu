<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<script type="text/javascript">
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 2;
	<s:if test='tabType == null || tabType=="msg"'>
		$().ready(
			function(){
				$('#infoId').html("消息通知");
			//	SwitchWeida(0);
			}
		);
	</s:if>
	<s:elseif test='tabType=="task"'>
		$().ready(
			function(){
				$('#infoId').html("消息通知");
				///SwitchWeida(1);
			}
		);
	</s:elseif> 
	function getbollvie(){
		if(ishavebuy==true || ishavebuy=="true"){
			 return(1);
		}else{
			return(0);
		}
	}
	function getEncourage(taskCusId){ //第一个节点修改
		$.ajax({  
			url : "<%=contextPath%>/task/taskweb!getTaskEncourage.action",  
			data : {"taskCus.id" : taskCusId},  // 参数  
			type : "post",  
			cache : false,  
			dataType : "json",  //返回json数据 
			error: function(){ 
				alert('error');      
			}, 
			success:onchangecallback  
		}); 
	}
			
	function onchangecallback(result){ //处理返回的课程JSON  
	
		if(result.returnMessage == 'false'){
			alert("您已领取过奖品");
		}else{
			var task = eval(result.returnMessage);
			$("#taskUrl").attr("src",baselocation + '' + task[0].iconUrl);
			$("#taskName").html(task[0].taskName);
			$("#jifen").html('奖励积分：×' + task[0].jifen);
			$("#experience").html('奖励经验：×' + task[0].experience);
			
			$("#finishtask").css("display", "block");
			lockingWindow();
		}
		
  	}
  	
  	function closeDiv() {
 		$("#finishtask").css("display", "none");
 		$("#web_top_black").css("display", "none");
 	}
  	
  	//黑底效果
	function lockingWindow(){ 
		document.getElementById("web_top_black").style.display = "block"; 
		document.getElementById("web_top_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight + "px";
	}  
  	function swichMsg(h){
  		for(var i=0;i<2;i++){
			document.getElementById("switch"+i).style.display="none";
			document.getElementById("switch"+h).style.display="block";
			$("#switchT"+i).attr("class","");
			$("#switchT"+h).attr("class","current");
  		}
  	}
</script>
<script language="javascript" src="<%=importURL%>/js/usercenter/ucenter.js"></script>