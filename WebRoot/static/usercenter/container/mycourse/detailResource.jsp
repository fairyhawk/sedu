<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<%=importURL%>/styles/usercenter/uc_mycourse.css?v=<%=version%>" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=importURL%>/js/web/public/jqplot/jquery.jqplot.css" />
<script type="text/javascript" src="<%=importURL%>/js/web/public/jqplot/jquery-1.4.2.min.js"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/cusdtree.js?v=<%=version%>"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_user_center.js?v=<%=version%>"></script>
<script type="text/javascript">
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 4;
	var goToListenCourseTmp = function goToListenCourseTmp (courseId,subjectId,sellWayId) {
		// we need to get the first video of the course. because the player should play it as soon as itself has been loaded.
		var paramFirstPlayVideoId;
		var firestPlayVideoName;
		var firstPlayVedioUrl;
		var firstPlayVideoVoid;
		var firstPlayVideoCCuUrl = "";
		// we need to get the first video of the course. because the player should play it as soon as itself has been loaded.
		//win = window.open("about:blank","_blank", "screenX="+(window.screen.availWidth - 1099)/2+",screenY="+(window.screen.availHeight - 608)/2);
	  	$.ajax({
			url : baselocation + "/cus/cuslimit!toCourseShu.action",
			data : {"course.courseId":courseId},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				var i, firstPlayVideo;
				for (i = 0; i < result.entity.length; i++) {
					if (result.entity[i].vedioUrl != null && result.entity[i].vedioUrl != "") {
						firstPlayVideo = result.entity[i];
						paramFirstPlayVideoId = firstPlayVideo.id;
						firestPlayVideoName = firstPlayVideo.name;
						firstPlayVedioUrl = firstPlayVideo.vedioUrl;
						firstPlayVideoVoid = firstPlayVideo.voId;
						if(typeof(firstPlayVideo.playType) != 'undefine' && firstPlayVideo.playType != null && firstPlayVideo.playType == 1){
							firstPlayVideoCCuUrl = firstPlayVideo.ccUrl;
						}
						//goToListenCourseByPointId(courseId, subjectId, firstPlayVideo.id, sellWayId, firstPlayVideo.name, firstPlayVideo.vedioUrl, firstPlayVideo.voId);
						break;
					}
				}
			}
		});
	  	
	  	if(courseId && paramFirstPlayVideoId){
			window.open ('<%=contextPath%>/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId="+subjectId + "&sellid=" + sellWayId + "&kpointId=" + paramFirstPlayVideoId + "&veri=" +getbollvie() + "&title=" + firestPlayVideoName + "&videoURL=" + BASE64.encode(firstPlayVedioUrl) + "&videoId=" + firstPlayVideoVoid +"&vid=" + firstPlayVideoCCuUrl, "_blank", "width="+$('html').width()+",height="+$('html').height()+",screenX="+(window.screen.availWidth - 1099)/2+",screenY="+(window.screen.availHeight - 608)/2);
		}
	}
	//判断是否是购买用户
	function getbollvie(){
	       return(1);
	}

	function goToListenCourseByPointId(courseId,subjectIdweb,kpointId,sellid,title,videoURL,videoId,vid){
		if(courseId && kpointId){
			window.open ('<%=contextPath%>/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId="+subjectIdweb + "&sellid=" + sellid + "&kpointId=" + kpointId + "&veri=" +getbollvie() + "&title=" + title + "&videoURL=" + BASE64.encode(videoURL) + "&videoId=" + videoId+"&vid="+vid, "_blank", "width="+$('html').width()+",height="+$('html').height()+",screenX="+(window.screen.availWidth - 1099)/2+",screenY="+(window.screen.availHeight - 608)/2);
		}
	}

	function showTreeDate(){
		dree = new dTree('dree');
		dree.add(-2,-1,'');
		<c:forEach var="node" items="${kpoints}" varStatus="index">
			if('${node.vedioUrl}' == null || typeof('${node.vedioUrl}') == "undefined" 
				&& '${node.ccUrl}' == null || typeof ('${node.ccUrl}') == "undefined"){
				dree.add('${node.id}','${node.PId}','${node.name}');
			}else{
				var $vedioUrl = "";
				var $vid = "";
				var max = 0;
				if('${node.playType}' == 1 && 
						'${node.ccUrl}' != null){
					$vid = '${node.ccUrl}';
				}
				$vedioUrl = '${node.vedioUrl}';
				
				var rdState="<a href='javascript:void(0)' onclick='goToListenCourseByPointId(${course.courseId},"+subjectId+ ",${node.id},${sellIds},\"${node.name}\",\"${node.vedioUrl}\",\"${node.voId}\",\""+$vid+"\")'>开始学习</a>";
				
				if('${node.rdState}' == 1){
				  rdState = "<a href='javascript:void(0)' onclick='goToListenCourseByPointId(${course.courseId},"+subjectId+ ",${node.id},${sellIds},\"${node.name}\",\"${node.vedioUrl}\",\"${node.voId}\",\""+$vid+"\")' class='dTree_study_continue'>继续学习</a>";
				}

				var max = '${node.hasRdCount}'/'${maxnumber}' * 100;
				if(max > 100){
					max = 100;
				}
				if('${node.PId}' == -2){
					if('${node.leaf}' != 0){
					if(('${node.mpUrl}'!= null && '${node.mpUrl}'!= '') || ('${node.ccUrl}'!= null && '${node.ccUrl}'!= '')){
						dree.add('${node.id}',-1,"<img src='<%=importURL%>/images/usercenter/reduce_icon.png' id='jdree${index.index+1}'></a><a href='javascript:void(0)' onclick='goToListenCourseByPointId(${course.courseId},"+subjectId+ ",${node.id},${sellIds},\"${node.name}\",\"${node.vedioUrl}\",\"${node.voId}\",\""+$vid+"\")' class='node dTree_name'>${node.name}</a><span class='dTree_study'>"+rdState+"</span><span class='dTree_people'><span class='dTree_people_bar' style='width:"+max+"px;'></span><em>${node.hasRdCount *7}</em>人学习</span>")
					}
					}else{
						dree.add('${node.id}',-1,"<img src='<%=importURL%>/images/usercenter/reduce_icon.png' id='jdree${index.index+1}' onclick='dree.o(${index.index+1});'></a><a href='javascript:void(0)' onclick='dree.o(${index.index+1});'  class='node dTree_name'>${node.name}</a>")
					}
				}else{
					//dree.add('${node.id}','${node.PId}',"<a class='dTree_name ml20' href='javascript:void(0)' onclick='goToListenCourseByPointId(${course.courseId},"+subjectId+ ",${node.id},${sellIds},\"${node.name}\",\"${node.vedioUrl}\",\"${node.voId}\",\""+$vid+"\")'>${node.name}</a><span class='dTree_study'>"+rdState+"</span><span class='dTree_people'><span class='dTree_people_bar' style='width:"+max+"px;'></span><em>${node.hasRdCount *7}</em>人学习</span>")
					
					if('${node.leaf}' != 0){
						dree.add('${node.id}','${node.PId}',"<a class='dTree_name ml20' href='javascript:void(0)' onclick='goToListenCourseByPointId(${course.courseId},"+subjectId+ ",${node.id},${sellIds},\"${node.name}\",\"${node.vedioUrl}\",\"${node.voId}\",\""+$vid+"\")'>${node.name}</a><span class='dTree_study'>"+rdState+"</span><span class='dTree_people'><span class='dTree_people_bar' style='width:"+max+"px;'></span><em>${node.hasRdCount *7}</em>人学习</span>")
					}else{
						dree.add('${node.id}','${node.PId}',"<img src='<%=importURL%>/images/usercenter/reduce_icon.png' id='jdree${index.index+1}' onclick='dree.o(${index.index+1});'></a><a href='javascript:void(0)' onclick='dree.o(${index.index+1});'  class='node dTree_name'>${node.name}</a>")
					}
							
				}
			}
		</c:forEach>
		$("#ddree-20223").html(dree.toString());
	}
	$().ready(function(){
		var mysellWayId = '${sellIds}';
		var subjectId = getCookie("subjectId");
		showTreeDate();
	});
</script>
