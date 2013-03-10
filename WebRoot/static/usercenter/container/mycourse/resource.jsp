<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<%=importURL%>/styles/usercenter/uc_mycourse.css?v=<%=version%>" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=importURL%>/js/web/public/jqplot/jquery.jqplot.css" />
<script type="text/javascript" src="<%=importURL%>/js/web/public/web_swfobject.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/web/public/jqplot/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=importURL%>/js/web/public/jqplot/jquery.jqplot.js"></script>
<script language="javascript" type="text/javascript" src="<%=importURL%>/js/web/public/jqplot/jqplot.pieRenderer.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/web/public/jqplot/jquery-ui-1.8.1.custom.min.js"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/cusdtree.js?v=<%=version%>"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_user_center.js?v=<%=version%>"></script>
<!--[if IE]><script language="javascript" type="text/javascript" src="<%=importURL%>/js/web/public/jqplot/excanvas.js"></script><![endif]-->
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_user_common.js?v=<%=version%>"></script>
<script type="text/javascript">
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var vo_url;
	var currentId = 0;
	var currentCourseId = 0;
	var urodz;
	var ucLeftIndex = 4;
	var obj = '<li class="pbottom-5 limg" id="obj">'
        + '	<a onclick="onImgClick()" style="cursor: hand" mce_style="cursor: hand">'
		+ '	<img id="teacherImgs" src="'+ importURL +'/images/usercenter/xykc_500.jpg" />'
		+ '    </a>'
		+ '	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'
		+ '		width="500" height="295" id="videoPlayer" style="display:none">'
		+ '		<param name="movie"'
		+ '			value="'+ baselocation +'/static/flex/AdobeVideoPlayer.swf?width=500&height=295&url='+ baselocation +'&importURL=<%=importURL%>" />'
		+ '		<param name="quality" value="high" />'
		+ '		<param name="bgcolor" value="#ffffff" />'
		+ '		<param name="allowScriptAccess" value="always" />'
		+ '		<param name="allowFullScreen" value="true" />'
		+ '		<param name="wmode" value="transparent" />'
		+ '		<!--[if !IE]>-->'
		+ '		<object type="application/x-shockwave-flash" style="display:none" wmode="window"'
		+ '			data="'+ baselocation +'/static/flex/AdobeVideoPlayer.swf?width=500&height=295&url='+ baselocation +'&importURL=<%=importURL%>"'
		+ '			width="500" height="295" id="videoPlayer_FF">'
		+ '			<param name="quality" value="high" />'
		+ '			<param name="bgcolor" value="#ffffff" />'
		+ '			<param name="allowScriptAccess" value="always" />'
		+ '			<param name="allowFullScreen" value="true" />'
		+ '			<param name="wmode" value="transparent" />'
		+ '		<!--<![endif]-->'
		+ '			<!--[if gte IE 6]>-->'
		+ '			<p>'
		+ '				Either scripts and active content are not permitted to run or'
		+ '				Adobe Flash Player version 10.1.0 or greater is not installed.'
		+ '			</p>'
		+ '			<!--<![endif]-->'
		+ '			<a href=" http://www.adobe.com/go/getflashplayer"> <img'
		+ '					src=" http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"'
		+ '					alt="Get Adobe Flash Player" /> </a>'
		+ '			<!--[if !IE]>-->'
		+ '		</object>'
		+ '		<!--<![endif]-->'
		+ '	</object>'
        + '</li>';
     
    	//menuTab
    	 $().ready(function() {
    		//menuTab2
    		 $(document).ready(function() {
    		 $("ul.menuTab .menuTab_a").click(function() {
    		 $(this).parent("li").addClass("current").siblings("li").removeClass("current");
    		 if($(this).parent().attr("id") == 'allli'){
    			 $(".home_cou").show();
    			 $(".home_coutit").show();
    		 }else if($(this).parent().attr("id") == 'wathcedli'){
    			 
    		 }else if($(this).parent().attr("id") == 'freeli'){
    			 switch2Free();
    		 }
    		 return false;
    		 });
    		 }); 
    		
    		 $("dl.m_course").hover(function(){
    			 $(this).addClass("m_course_hover");
    			 },function(){
    			 $(this).removeClass("m_course_hover");
    			 }); 
    	
    	}); 
     function GetServerTime() {
     	now = new Date();
     	var hour;
     	var minute;
     	var second;
     	
		now.setTime(now.getTime()+1000);
		days = (urodz - now) / 1000 / 60 / 60 / 24;
		daysRound = Math.floor(days);
		hours = (urodz - now) / 1000 / 60 / 60 - (24 * daysRound);
		hoursRound = Math.floor(hours);
		
		if(hoursRound < 10){
			hour = "0" + hoursRound;
		}else{
			hour = hoursRound;
		}
		
		minutes = (urodz - now) / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound);
		minutesRound = Math.floor(minutes);
		
		if(minutesRound < 10){
			minute = "0" + minutesRound;
		}else{
			minute = minutesRound;
		}
		seconds = (urodz - now) / 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound);
		secondsRound = Math.round(seconds);
		
		if(secondsRound < 10){
			second = "0" + secondsRound;
		}else{
			second = secondsRound;
		}
		
		$("#runTime").text(daysRound + ":" + hour + ":"+ minute + ":"+ second);
		$("#releaseDays").text(daysRound);
	}
	   
     $().ready(
    	
     	function(){
     		buyURL = baselocation + "/static/web/column/" + getColumnId('buy') + "/index_1.shtml";
	     	var indexURL = getCookie("indexURL");
			
			if(indexURL && indexURL != null){
				$("#indexUrl").attr("href",indexURL);
			}else{
				$("#indexUrl").attr("href","http://highso.org.cn/");
			}
     		//倒计时
     		urodz = new Date($("[id = subject.testTime]").val());
     		//if(urodz && !isNaN(urodz)){
     			//GetServerTime();
				//setInterval("GetServerTime()",1000);	
     		//}
     		//倒计时
		     		
     		var pointId;
     		var courseId = document.getElementById("fristc").value;
     		if(document.getElementById("kpoint.pointId")){
     			pointId = document.getElementById("kpoint.pointId").value;
     		}
     		
     		if(document.getElementById("course.courseId")){
     			courseId = document.getElementById("course.courseId").value;
     		}
     		
     		currentId = pointId;
     		currentCourseId = courseId;
     		
     		if(pointId && pointId !='0'){
     			var id = '#li_' + pointId;
     		
     			$(obj).insertAfter(id);
	     		//update pic
				if(document.getElementById("course.subjectId")){
	     			subjectId = document.getElementById("course.subjectId").value;
	     		}
	     		
	     		if(subjectId){
		     			changeImage(subjectId);
		     	}
		     	//update pic
		     	
	     		var vo_id = "vo_url_" + pointId;
	     		if(document.getElementById(vo_id)){
	     			vo_url = document.getElementById(vo_id).value;
	     		}
     		}
     		
     		if(courseId && courseId != '0'){
     			var totalUserNum = parseInt(document.getElementById("course_totalUserNum_" + courseId).value);
     			var thanOneNum = parseInt(document.getElementById("course_thanOneNum_" + courseId).value);
     			var thanTwoNum = parseInt(document.getElementById("course_thanTwoNum_" + courseId).value);
     			var thanThreeNum = parseInt(document.getElementById("course_thanThreeNum_" + courseId).value);
     			var userSelfNum = parseInt(document.getElementById("course_userSelfNum_" + courseId).value);
     
	     		s1 = [['与你保持同一进度的人',userSelfNum], ['比你快1节的人',thanOneNum], ['比你快2节的人',thanTwoNum], ['比你快3节及以上的人',thanThreeNum]];
			    plot1 = $.jqplot('chart1', [s1], {
			        grid: {
			            drawBorder: false, 
			            drawGridlines: false,
			            background: '#ffffff',
			            shadow:false
			        },
			        axesDefaults: {
			            
			        },
			        seriesDefaults:{
			            renderer:$.jqplot.PieRenderer,
			            rendererOptions: {
			                showDataLabels: true
			            }
			        },
			        legend: {
			            show: true,
			            rendererOptions: {
			                numberRows: 4
			            },
			            location: 's',
			            placement: 'inside',
			            xoffset: 0,       
	        			yoffset: 0,
	        			fontSize:'12px'
			        }
			    });
			
			    var courseName = document.getElementById("course_name_" + courseId).value;
			    $("#studyintroduce").html("<div class='kc_righttit font_blue'>"
	                   	 + "学习进度说明"
	                     + "</div>"
	                	 + "<div class='pleft-20 mtop-5'><p>"
		                 + "  共有"+ totalUserNum +"人和你一起学习"+ courseName +"<p/><ul>"
		                 + "<li>比你快一章的有<font class='strongb font_red'>"+ thanOneNum +"</font>人</li>"
		                 + "<li>比你快两章的有<font class='strongb font_red'>"+ thanTwoNum +"</font>人</li>"
		                 + "<li>比你快三章及以上的有<font class='strongb font_red'>"+ thanThreeNum +"</font>人</li>"
		                 + "<li>每天学习1小时，即可保持进度</li></ul></div>");  
     		}
     	}
     ); 
       
    function loadSWF(){
		loadVideo(vo_url);
	}
	
	var firstPlay = false;
	
	var isFirstPlay = function (){
		return firstPlay;
	}
	
	var getCurrentVideoUrl = function (){
		return vo_url;
	}
	
	var isUserCenter = function (){
		return true;
	}
	
	var onImgClick = function (){
		//firstPlay = true; //判断是否播放广告和平台简介宣传片
		var oSwf = swfobject.getObjectById("videoPlayer");
		if(oSwf){
			document.getElementById("teacherImgs").style.display = "none";
			$("#videoPlayer")[0].style.display = "block";
			oSwf.style.display = "block";
			loadVideo(vo_url);
		}
	}
	
	var loadVideo = function (vedioUrl){
		var oSwf = swfobject.getObjectById("videoPlayer");;
		try{
			if(oSwf) {
				oSwf.load(vedioUrl);
			}
		}catch(e){ }
	}
	
	
	var onClickKpoint = function(pointId,subjectId){
		var vo_id = "vo_url_" + pointId;
    	vo_url = document.getElementById(vo_id).value;
    	
    	if(vo_url != ''){
			var oSwf = swfobject.getObjectById("videoPlayer");
			if(oSwf){
				try{
					oSwf.stopVideoPlay();
				}catch(e){}
			}
		
			$('#obj').remove();
			$('#videoPlayer').remove();
			$('#videoPlayer_FF').remove();
			var liId = '#li_' + pointId;
     		currentId = pointId;
     		$(obj).insertAfter(liId);
     		
     		changeImage(subjectId);//update pic
		}
	}
	
	
	var selectSubject =  function (subjectId){
		if(subjectId){
			location.href="<%=contextPath%>/cou/courselimit!toMyCourse.action?subject.subjectId=" + subjectId;
		}
	}
	
	function goToListenCourse(courseId,subjectIdweb){
		if(courseId && subjectIdweb){
		getbollvie();
			window.open ('<%=contextPath%>/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId=" + subjectIdweb + "&veri=" +getbollvie() , "_blank", "resizable");
		}
	}
	
	function goToListenCourseByPointId(courseId,subjectIdweb,kpointId,sellid,title,videoURL,videoId,vid){
		if(courseId && kpointId){
		//win.location.href="<%=contextPath%>/cou/courselimit!toListenMyCourse.action?course.courseId=" + courseId + "&course.subjectId="+subjectIdweb + "&sellid=" + sellid + "&kpointId=" + kpointId + "&veri=" +getbollvie() + "&title=" + title + "&videoURL=" + BASE64.encode(videoURL) + "&videoId=" + videoId;
		window.open ('<%=contextPath%>/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId="+subjectIdweb + "&sellid=" + sellid + "&kpointId=" + kpointId + "&veri=" +getbollvie() + "&title=" + title + "&videoURL=" + BASE64.encode(videoURL) + "&videoId=" + videoId+"&vid="+vid, "_blank", "width="+$('html').width()+",height="+$('html').height()+",screenX="+(window.screen.availWidth - 1099)/2+",screenY="+(window.screen.availHeight - 608)/2);
		}
	}
		
	var win = null;
	//临时点击课程事件
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
				if ((result.entity[i].vedioUrl != null && result.entity[i].vedioUrl != "") || (result.entity[i].ccUrl != null && result.entity[i].ccUrl != "")) {
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
		window.open ('<%=contextPath%>/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId="+subjectId + "&sellid=" + sellWayId + "&kpointId=" + paramFirstPlayVideoId + "&veri=" +getbollvie() + "&title=" + firestPlayVideoName + "&videoURL=" + BASE64.encode(firstPlayVedioUrl) + "&videoId=" + firstPlayVideoVoid +"&vid=" + firstPlayVideoCCuUrl, "_blank", "width="+$('html').width()+",height="+$('html').height());
	}
}
	//判断是否是购买用户
	function getbollvie(){
	       return(1);
	}
	
	function changeImage(subjectId){
     	var subjectImage;
     	if(subjectId == 3){
     		subjectImage = 'kj_500.jpg';
     	}else if(subjectId == 1){//ren
     		subjectImage = 'rl_500.jpg';
     	}else if(subjectId == 5){//sifa
     		subjectImage = 'sf_500.jpg';
     	}else if(subjectId == 8){//zq
     		subjectImage = 'zq_500.jpg';
     	}else if(subjectId == 7){//cpa
     		subjectImage = 'zk_500.jpg';
     	}else if(subjectId == 9){
     		subjectImage = "jzs_558.jpg" ;
     	}else if(subjectId == 10){
     		subjectImage = "gk_500.jpg" ;
     	}else if(subjectId == 11){
     		subjectImage = "gwy_500.jpg" ;
     	}else{
     		subjectImage = 'xykc_500.jpg';
     	}
     	
		$('#teacherImgs').attr('src',importURL +'/images/usercenter/' + subjectImage);
	 }
	 function noCouserMsg(){
	 	showErrorWin("Highso温馨提示：课程暂未上传，请耐心等待！",""); 
	 }

	/**查看更多课程目录**/

    function StringBuffer()
    { 
		this.data = []; 
	}
	StringBuffer.prototype.append = function(){ 
		this.data.push(arguments[0]); 
		return this; 
	} 
	StringBuffer.prototype.toString = function(){ 
		return this.data.join(""); 
	}
	

	 function checkMoreMyCourse(){
	 	$("#morMyless").html("");
	 	$("#morMyless").append("<img id='loadinfopic' src='<%=importURL%>/images/usercenter/loadinfo_m.gif' style='vertical-align: middle;'/>&nbsp;&nbsp;查询中...");
	 	var sb = new StringBuffer();
	 	$.ajax({
				url : "<%=contextPath%>/cou/courselimit!toMyCourseMoreLesson.action",
				type : "post",
				data : {},
				dataType : "json",
				success : function(result) {
					var sellWayMyMoreCourseList = result.entity;
					var inf = ""; 
					$.each(sellWayMyMoreCourseList,function(key,val){
						sb.append( "<div class='home_coubox'><div class='home_coutit'>");
						sb.append( "<h3>"+val.sellWay.sellName + "</h3>");
						
						sb.append("<p>");
						//sb.append("<span>总课时：<em>50</em></span><span>已上传：<em>26</em></span><span>上传进度：<em>50%</em></span>");
						sb.append("<a href='<%=contextPath%>/cou/courseweb!getCoursePlan.action?queryCourseCondition.sellWayId="+val.sellWay.sellId);
						sb.append("' class='view_schedule'>[查看课程进度]</a></p>");
						
						//---内部地址下载---
						if(val.sellWay.sellId==1){
							sb.append( "<a href='http://down.sunland.org.cn/zlxzzq/rlzybbx_1109.rar'  class='font_orange fontsize-14' >［500强企业内部名师培养课程下载］</a>");
						}
						if(val.sellWay.sellId==2){
							sb.append( "<a href='http://down.sunland.org.cn/zlxzzq/rlzybbx_1109.rar'  class='font_orange fontsize-14' >［500强企业内部名师培养课程下载］</a>");
						}
						if(val.sellWay.sellId==476){
							sb.append( "<a href='http://down.sunland.org.cn/zlxzzq/rlzybbx_1109.rar'  class='font_orange fontsize-14' >［500强企业内部名师培养课程］</a>");
						}
						
						//-----课程循环-------
						sb.append( "</div><div class='clear'></div>");
						$.each(val.courseList,function(courKey,courVal){
							sb.append( "<div class='home_cou'><h4>"+courVal.title+"</h4>");
							sb.append( "<p><span>主 讲 人:<em>");
							
							$.each(courVal.teacherList,function(teacherKey,teacherVal){
								sb.append(" "+teacherVal.name+"&nbsp;");
							});
							sb.append("</em></span>");
							if (courVal.vedioSize>=courVal.lessionTime){
								sb.append("<span>总课时：<em>" + courVal.vedioSize + "</em></span>");
								sb.append("<span>&nbsp;已上传：<em>"+courVal.vedioSize+"</em></span>");
								sb.append("<span>&nbsp;进度：<em> 100%</em></span>");
							}
							else{
								sb.append("<span>总课时：<em>" + Math.round(courVal.lessionTime) + "</em></span>");
								sb.append("<span>&nbsp;已上传：<em>"+courVal.vedioSize+"</em></span>");
								sb.append("<span>&nbsp;进度：<em> "+ Math.round(courVal.vedioSize/courVal.lessionTime)+"%</em></span>");
							}
							
							sb.append("</p>");
							
							//-----收起展开课程目录-------
							if(courVal.lastKpointId == courVal.lastKpointId){
								<%-- sb.append("<img src='<%=importURL%>/images/usercenter/empty1.gif' />"); --%>
								sb.append("<p><a class='view_mulu' href='javascript:void(0)' id='textObj_"+val.sellWay.sellId+"_"+courVal.courseId+"'");
								sb.append("onclick='onClickCourse_course("+val.sellWay.sellId+","+courVal.courseId+",this,"+courVal.subjectId+")'>收起课程目录</a>&nbsp;&nbsp");
							}else{
								sb.append("<p><a class='view_mulu' href='javascript:void(0)' id='textObj_"+val.sellWay.sellId+"_"+courVal.courseId+"' ");
								sb.append("onclick='onClickCourse_course("+val.sellWay.sellId+","+courVal.courseId+",this,"+courVal.subjectId+")'>查看课程目录</a>&nbsp;&nbsp");
							}
							
							var isshitingdownflag = 0;
							//------PPT，MP3，讲义下载------
							if(isshitingdownflag == 0){
								if(courVal.dpdfUrl != null && courVal.dpdfUrl != ''){
									sb.append("<a class='downloadPDF' href="+courVal.dpdfUrl+" ");
									sb.append("id='downloadPDF_"+courVal.courseId+"'>下载PDF讲义</a>&nbsp;&nbsp</font>");
								}if(courVal.dpptUrl != null && courVal.dpptUrl != ''){
									sb.append("<a class='downloadPPT' href="+courVal.dpptUrl+" ");
									sb.append("id='downloadPPT_"+courVal.courseId+"'>下载PPT讲义</a>&nbsp;&nbsp</font>");
								}if(courVal.dmp3Url != null && courVal.dmp3Url != ''){
									sb.append("<a class='downloadMP3' href="+courVal.dmp3Url+" ");
									sb.append("id='downloadMP3_"+courVal.courseId+"'>下载音频课件</a>&nbsp;&nbsp</font>");
								}
							}
							sb.append("</p>");
							
							//------开始学习按钮------
							if(courVal.vedioSize == 0){
								sb.append("<input name='' type='button' value='开始学习' class='button_4' onclick='noCouserMsg()' />");
							}else{
								sb.append("<input name='' onclick='goToListenCourseTmp( "+courVal.courseId+","+courVal.subjectId+","+val.sellWay.sellId+")'")
								sb.append("type='button' value='开始学习' class='button_3' id='course_button_"+courVal.courseId+"'/>");
							}
							sb.append("<div class='clearline'></div>");
							if(courVal.lastKpointId != courVal.lastKpointId ){
								sb.append( "<div class='home_couml mtop-10' style='display: block'");
								sb.append( "id=course_kpointlist_"+val.sellWay.sellId+"_"+courVal.courseId+">");
							}else{
								sb.append("<div class='home_couml mtop-10' style='display: none'");
								sb.append("id=course_kpointlist_"+val.sellWay.sellId+"_"+courVal.courseId+">");
							}
							sb.append( "<div class='clear'></div>");	
							sb.append( "</div>");
							sb.append( "</div>");
							
						});
						sb.append( "</div>");
						
						$(sb.toString()).insertBefore("#bufferMycourse");
						sb = new StringBuffer();
					});
					
					//隐藏更多课程按钮
					$("#more_Mylesson").hide();
				},
				error : function(error) {
					alert("error");
				}
		});
		
	 }
	 
	 function getupsizeper(psizeper){
	 	if(psizeper.indexOf(".")<0){
	 		return psizeper;
	 	}else{
	 		return psizeper.substring(0,psizeper.indexOf("."));
	 	}
	 }
	 
	 function switch2Free(){
		 $(".home_cou").hide();
		 $(".home_coutit").hide();
		 $("#freeSell").children().show();
	 }
</script>