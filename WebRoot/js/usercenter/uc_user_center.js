	var learnBuyFlag = false;
	var MyScorll = null;
	var MyMar = null;
	var SubjectNewsCount=0;
	var UcHighSoNewCount=0;
	     
	$().ready(function(){
		subjectId = getCookie("subjectId");
		
		if(subjectId == null || isNaN(subjectId) || parseInt(subjectId) < 1) {
			subjectId = 3;
		}
		//考试指南
		//getUcSubjectNews();
		//常见新闻
		//getUcHighSoNews();
		
		if(document.getElementById("now_happened_div")) {
			document.getElementById("now_happened_div").scrollTop += 70 * 2;
			var subjectIdTemp=getCookie("subjectIdTemp");
			if (subjectIdTemp!=null && subjectIdTemp!= '' && subjectIdTemp==0){
				var username = getCookieFromServer("sedu.cookie.ukey").split(',')[3];
				if(username.substring(username.length-1) == '"') {
					username = username.substring(0, username.length-1);
				}
				$("#disusernamepop").html(username);
				openBlackWin();
				$("#dissubjectpop").show();
			}else{
				checkActCou();
			}
		}
		
	});
	
	function displaynewer(){
		 /*
	     * a  未购买用户的弹窗 newer_nobuy
	     * b  已经购买 newer_buy
	     * c  我的课程 newer_mycourse
	     * d  问答  newer_wenda
	     * e  开始学习 newer_study
	     */ 
		
		if (isshitingflag ==true || isshitingflag == 'true'){
 		if(subid==1){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/rl'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a> 人力资源二级/三级考试课程<div class='clearline'></div>");
	 	}else if(subid==2){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/xl'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a> 心理咨询师二级/三级考试课程<div class='clearline'></div>");
	 	}else if(subid==3){	
	 		$("#shitingtitle").html("<a href='"+baselocation+"/kjz'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>会计从业资格证考试课程（2012）<div class='clearline'></div>");
	 	}else if(subid==5){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/sf'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>司法重点学科班课程<div class='clearline'></div>");
	 	}else if(subid==7){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/cpa'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>注册会计师专题解析班课程<div class='clearline'></div>");
	 	}else if(subid==8){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/zq'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>证券从业资格证<div class='clearline'></div>");
	 	}else if(subid==9){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/jz'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>一级建造师考试课程<div class='clearline'></div>");
	 	}else if(subid==10){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/gk'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>高级会计师全程通关保障班<div class='clearline'></div>");
	 	}else if(subid==11){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/gwy'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>公务员考试课程<div class='clearline'></div>");
	 	}else if(subid==12){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/jjs'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>经济师考试课程<div class='clearline'></div>");
	 	}else if(subid==13){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/ky'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>全国研究生统一入学考试<div class='clearline'></div>");
	 	}else if(subid==14){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/zlkjs'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>助理会计师<div class='clearline'></div>");
	 	}else if(subid==15){
	 		$("#shitingtitle").html("<a href='"+baselocation+"/kjs'><img id='con_left_bottom' src='"+importURL+"/images/web/public/buyTop_member.png' /></a>中级会计资格证<div class='clearline'></div>");
	 	}
 		}else{
 		$("#shitingtitle").hide();
 		}
		
	}
		 /*
	     * a  未购买用户的弹窗 newer_nobuy
	     * b  已经购买 newer_buy
	     * c  开始学习 newer_study
	     * d  问答  newer_wenda
	     * e  我的课程 newer_mycourse
	     */
	function close_newer_nobuy(isclose){//关闭 未购买 a
		document.getElementById("newer_nobuy").style.display="none";
		closeBlackWin();
		if ($("#checkbox_newer_nobuy").attr("checked")){
			updatenewerflag("a");
		}
		if (isclose==1){return};
		if (isshitingdownflag==0){return};
		if(newerflag.indexOf('c')== -1 ){ 
			document.getElementById("newer_study").style.display="block";
		}else if(newerflag.indexOf('d')== -1 ){
			document.getElementById("newer_wenda").style.display="block";
		}else if(newerflag.indexOf('e')== -1 ){
			document.getElementById("newer_mycourse").style.display="block";
		}
		if ($("#checkbox_newer_buy").attr("checked")){
			updatenewerflag("b");
		}
	}
	function close_newer_buy(isclose){//关闭 购买 
		document.getElementById("newer_buy").style.display="none";
		closeBlackWin();
		if (isclose==1){return};
		if(newerflag.indexOf('c')== -1 ){ 
			document.getElementById("newer_study").style.display="block";
		}else if(newerflag.indexOf('d')== -1 ){
			document.getElementById("newer_wenda").style.display="block";
		}else if(newerflag.indexOf('e')== -1 ){
			document.getElementById("newer_mycourse").style.display="block";
		}
		if ($("#checkbox_newer_buy").attr("checked")){
			updatenewerflag("b");
		}
				
	}
	function close_newer_study(isclose){//关闭 开始学习 c
		document.getElementById("newer_study").style.display="none";
		if (isclose==1){return};
		if(newerflag.indexOf('d')== -1 ){
			document.getElementById("newer_wenda").style.display="block";
		}else if(newerflag.indexOf('e')== -1 ){
			document.getElementById("newer_mycourse").style.display="block";
		}
		
		if ($("#checkbox_newer_study").attr("checked")){
			updatenewerflag("c");
		}
		
	}
	function close_newer_wenda(isclose){//关闭未 问答  d
		document.getElementById("newer_wenda").style.display="none";
		if (isclose==1){return};
		if(newerflag.indexOf('e')== -1 ){
			document.getElementById("newer_mycourse").style.display="block";
		}
		
		if ($("#checkbox_newer_wenda").attr("checked")){
			updatenewerflag("d");
		}
	}
	function close_newer_mycourse(isclose){//关闭 我的课程  c
		document.getElementById("newer_mycourse").style.display="none";
		if (isclose==1){return};
		if ($("#checkbox_newer_mycourse").attr("checked")){
			updatenewerflag("e");
		}
	}
	//更新新手引导标志
	function updatenewerflag(addflag){
		if  (newerflag.indexOf(addflag)== -1 ){
			newerflag=newerflag+addflag;
			//更新flag
			$.ajax({
				url : baselocation + "/cus/cuslimit!updatenewerflag.action",
				data : {"customer.newerflag":newerflag},
				type : "post",
				dataType : "json",
				cache : false,
				success : function(result){
				},
				error : function(error) {
				}
			});
			//更新flag
		}
	}
	//新增控制新手引导
	
	function checkActCou() {
		//个人中心弹出激活的窗口
		$.ajax({
			url : baselocation + "/finance/contract!getContractCount.action",
			data : {},
			type : "post",
			dataType : "json",
			cache : false,
			success : function(result){
				if(result.returnMessage != null && result.returnMessage != '') {
					var exp = result.returnMessage.split(",");
					if(exp != null && exp.length > 0 ) {
						nojihuoma=false;
						openActCouWin(exp[0]);//激活码
					}else{
						nojihuoma= true;
						displaynewer();
					}
				}else{
					displaynewer();
				}
			},
			error : function(error) {
				//alert('error');
			}
		});
	}
	
	function actCourse() {
		var cdk = $("#contractCDkey1").val() + $("#contractCDkey2").val() + $("#contractCDkey3").val() + $("#contractCDkey4").val();
		var contractId = $("#contractId").html();
		if(cdk == "") {
			alert("请输入激活码。");
			return;
		}
		$.ajax({
			url : baselocation + "/finance/contract!gotoCOD.action",
			data : {
				"queryContractCondition.contractId" : contractId,
				"contractCDkey" : cdk
			},
			type : "get",
			dataType : "json",
			cache : false,
			success : function(result){
				if(result.returnMessage == "success") {
					alert("课程已添加。");
					window.location.reload();
				} else if (result.returnMessage == "moreTimes") {
					alert("您今天已经输入过5次激活码，请明天重试。");
					closeBlackWin();
					closeActWin();
				} else if (result.returnMessage == "hasAct") {
					alert("此课程已经激活。");
					closeBlackWin();
					closeActWin();
				} else {
					alert("激活码错误。");
				}
			},
			error : function(error) {
				//alert('error');
			}
		});
	}
	
	function getUcSubjectNews() {
	    SubjectNewsCount=0;
		$.getJSON(baselocation + "/static/web/article/subject/" + subjectId + "/page_1.shtml",function(msg){
			if(msg == null) { 
				return;
			}
			var showCount = msg.length > 8 ? 8 : msg.length;
			SubjectNewsCount=showCount;
			for(var i=0; i<showCount; i++) {
				var showTitle = msg[i].title.length > 20 ? msg[i].title.substring(0, 20) + "..." : msg[i].title;
				$("<li><font class='left'><a href='" + baselocation + msg[i].url + "' target='_blank' title='" + msg[i].title + "'>" + showTitle + "</a></font><font class='right font_hui'>" + msg[i].time + "</font></li>").appendTo("#switchX0");
			}
		});
	}
	
	function getUcHighSoNews() {
		UcHighSoNewCount=0;
		$.getJSON(baselocation + "/static/web/article/subject/0/page_1.shtml",function(msg){
			if(msg == null) {
				return;
			}
			var showCount = msg.length > 8 ? 8 : msg.length;
			UcHighSoNewCount=showCount;
			if (SubjectNewsCount==0){
				$("#switchZ0").hide();
				$("#switchX0").hide(); 
				document.getElementById("switchX1").style.display="block";
				document.getElementById("switchZ1").style.background="url('" + importURL + "/images/usercenter/switchT_a2.gif')";
			}
			if (UcHighSoNewCount==0 && SubjectNewsCount==0){
				document.getElementById("highsozxH").style.display="none";
				document.getElementById("highsozxl1").style.display="none";
			}
			for(var i=0; i<showCount; i++) {
				var showTitle = msg[i].title.length > 20 ? msg[i].title.substring(0, 20) + "..." : msg[i].title;
				$("<li><font class='left'><a href='" + baselocation + msg[i].url + "' target='_blank' title='" + msg[i].title + "'>" + showTitle + "</a></font><font class='right font_hui'>" + msg[i].time + "</font></li>").appendTo("#switchX1");
			}
		});
	}
	/*HighSo问答切换*/
	function SwitchWeida(h){
		for(i=0;i<2;i++){
			document.getElementById("switchB"+i).style.display="none";
			document.getElementById("switchB"+h).style.display="block";
			document.getElementById("switchT"+i).style.color="#069";
			document.getElementById("switchT"+h).style.color="#fff";
			document.getElementById("switchT"+i).style.background="url('" + importURL + "/images/usercenter/switchT_a1.gif')";
			document.getElementById("switchT"+h).style.background="url('" + importURL + "/images/usercenter/switchT_a2.gif')";
		}
	}

	/*HighSo资讯*/
	function SwitchZixun(h){
		for(i=0;i<2;i++){
			document.getElementById("switchX"+i).style.display="none";
			document.getElementById("switchX"+h).style.display="block";
			document.getElementById("switchZ"+i).style.background="url('" + importURL + "/images/usercenter/switchT_a1.gif')";
			document.getElementById("switchZ"+h).style.background="url('" + importURL + "/images/usercenter/switchT_a2.gif')";
		}
		if(h == 0) {
			$("#more_inf").attr("href", baselocation + "/cus/cuslimit!toInfList.action?pageNo=1");
		} else {
			$("#more_inf").attr("href", baselocation + "/cus/cuslimit!toInfList.action?pageNo=1&subjectId=0");
		}
	}

	function closeHeadGuid(){
		$("#headGuid").css("display", "none");
		$("#headGuid_line").css("display", "none");
	}
	
	 function loadSWF(){
    	var courseSize = document.getElementById("courseSize").value;
    	if(!courseSize || parseInt(courseSize) == 0){//shiting
    		loadVideoSF(shiTingUrl);
    		loadVideoSF2(shiTingUrl);
    	}else{
    		loadVideo(vo_url);
    	}
		
	}
	
	var firstPlay = false;
	var isTryListen = false;
	
	var isFirstPlay = function (){
		return firstPlay;
	}
	
	var getCurrentVideoUrl = function (){
		return isTryListen ? shiTingUrl : vo_url;
	}
	
	var onImgClick = function (){
		firstPlay = true;
		userCenter = true;
		isTryListen = false;
		var oSwf = getSwfObject();
		if(oSwf){
			document.getElementById("teacherImgs").style.display = "none";
			
			$("#videoPlayer")[0].style.display = "block";
			oSwf.style.display = "block";
			loadVideo(vo_url);
			
		}
	}
	
	
	var onWxxImgClick = function (){
		if($("#1").val() != undefined){
			var htmlValue = $("#1").val();
			$(".corlor_g").html(htmlValue.substring(0,15));//默认设置视频名称	
			var param = "videoId="+subjectId;
		 	$.ajax({
		 		type:'post',
		 		url:baselocation+"/feed/stat/statistics!doUpdateActiveNum.action",
		 		data:param,
		 		success: function(data){
		 		}
		 	});
		}else{
			alert('没有微学习视频');
			return;
		}
		firstPlay = true;
		userCenter = true;
		isTryListen = false;
		var oSwf = getSwfObject();
		if(oSwf){
			document.getElementById("teacherImgs").style.display = "none";
			
			$("#videoPlayer")[0].style.display = "block";
			oSwf.style.display = "block";
			loadVideo(vo_url);
		}
	}
	
	function onVedioClick() {
		document.getElementById("shiTingImg").style.display = "block";
		$("#videoPlayer_SF")[0].style.display = "hidden";
	}
	
	var loadVideo = function (vedioUrl){
		var oSwf = getSwfObject();;
		try{
			if(oSwf) {
				oSwf.load(vedioUrl);
				firstPlay = false;
			}
		}catch(e){ }
	}
	
	var getSwfObject = function (){
		return swfobject.getObjectById("videoPlayer");
	}
	
	var onClickKpoint = function(pointId, subjectId){
		var vo_id = "vo_url_" + pointId;
     	vo_url = document.getElementById(vo_id).value;
     	if(vo_url != ''){
			var oSwf = getSwfObject();
			if(oSwf){
				try{
					oSwf.stopVideoPlay();
				}catch(e){}
			}
			$('#obj').remove();
			$('#videoPlayer').remove();
			$('#videoPlayer_FF').remove();
			var liId = '#link_' + pointId;
	 		currentId = pointId;
	 		$(obj).insertAfter(liId);
	 		onImgClick();
     	}
	}
	
	var onweixuexiClick = function(pointId, subjectId){
		var vo_id = pointId;
     	vo_url = $("#" + vo_id).html();
     	if(vo_url != ''){
			var oSwf = getSwfObject();
			if(oSwf){
				try{
					oSwf.stopVideoPlay();
				}catch(e){}
			}
			$('#obj').remove();
			$('#videoPlayer').remove();
			$('#videoPlayer_FF').remove();
			var liId = '#vedio_hide' ;
	 		$(obj).insertAfter(liId);
	 		onImgClick();
     	}
	}
	
	var mysellWayId;
	var lastlicksellc="";//上次点击的课程
    var thisclicksellc="";//本次点击的课程位置
    var onClickCourse = function(sellWayId,courseId,textObj,subjectId){
		var nowCourseLiId = "course_kpointlist_" +sellWayId +"_"+courseId;
		//**************************************
		thisclicksellc=sellWayId+"_"+courseId;
		if (lastlicksellc==""){
			lastlicksellc=thisclicksellc;
		}else if (lastlicksellc==thisclicksellc){
			//li列表展示
			var courseStyle = document.getElementById(nowCourseLiId).style.display;
			if(courseStyle != 'none'){
				var textObj_this = "textObj_"+thisclicksellc;
				$("#"+textObj_this).html("查看课程目录"); 
				$("#"+nowCourseLiId).html("");
				$("#"+nowCourseLiId).hide();
				return;
			}else{
				textObj.innerHTML="收起课程目录";
				$("#" + courseStyle).css('display','block');
			}
			//li列表展示
			//return;
		}else{
			var nowCourseLiId2 = "course_kpointlist_" +lastlicksellc;
			var textObj11 = "textObj_"+lastlicksellc;
			$("#"+nowCourseLiId2).hide();
			$("#"+textObj11).html("查看课程目录"); 
			lastlicksellc=thisclicksellc;
		
		}
	
		//***********************
		var kpon =0;
		var kponopen =0;
		var kponflag =0;
		mysellWayId = sellWayId;
		$.ajax({
				url : baselocation + "/cus/cuslimit!toCourseShu.action",
				data : {"course.courseId":courseId},
				type : "post",
				dataType : "json",
				cache : false,
				success : function(result){
					dree = new dTree('dree');
					dree.add(-2,-1,'');
					$.each(result.entity,function(name,value){
						if(value.vedioUrl ==null || value.vedioUrl == ''){
							dree.add(value.id,value.PId,value.name);
							kpon=kpon+1;
						}else if(value.vedioUrl !=null || value.vedioUrl != ''){
							
							if (kponflag==0){
								kponopen=kpon;
								kponflag=1;
							}
							kpon=kpon+1;
							var $vedioUrl="";
							var $vid="";
							if(value.playType==1&&value.ccUrl!=null){
								$vid=value.ccUrl;
							}else{
								
							}
							$vedioUrl=value.vedioUrl;
							//弹出新页面观看视频
							if(value.PId == -2){
							      dree.add(value.id,-1,"<input type='hidden' value='"+$vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+value.vedioUrl+"\"," + value.voId +",\""+$vid+"\")' class='fl'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><span id='link_"+value.id+"' class='fr'><a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+value.vedioUrl+"\"," + value.voId +",\""+$vid+"\")'>学习</a>|<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>|<a href='"+baselocation+"/cus/pblimit!getEveryOneProblemList.action?course.courseId=15&problem.pblSolv=1&queryProblemCondition.currentPage=1&location=1'>提问</a></span>");		
							}else{
							      dree.add(value.id,value.PId,"<input type='hidden' value='"+$vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+$vedioUrl+"\"," + value.voId +",\""+$vid+"\")' class='fl'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><span id='link_"+value.id+"'  class='fr'><a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+$vedioUrl+"\"," + value.voId +",\""+$vid+"\")'>学习</a>|<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>|<a href='"+baselocation+"/cus/pblimit!getEveryOneProblemList.action?course.courseId=15&problem.pblSolv=1&queryProblemCondition.currentPage=1&location=1'>提问</a></span>");
							}
							//当前页面观看视频
							/**
							if(value.PId == -2){
							      dree.add(value.id,-1,"<input type='hidden' value='"+value.vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")' class='left'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><font id='link_"+value.id+"' class='right'><a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")'>学习</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/cus/pblimit!getHighSoProblem.action'>提问</a>&nbsp;&nbsp;&nbsp;&nbsp;</font>")			
							}else{
							      dree.add(value.id,value.PId,"<input type='hidden' value='"+value.vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")' class='left'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><font id='link_"+value.id+"'  class='right'><a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")'>学习</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/cus/pblimit!getHighSoProblem.action'>提问</a>&nbsp;&nbsp;&nbsp;&nbsp;</font>")
							}
							*/
						}
					});
					$("#"+nowCourseLiId).html(dree.toString());
					if (kponopen>0){
						dree.closeAll();
	 					//dree.openTo(kponopen,true);
					}
					dree.openTo(2,true);
				},
				error : function(error){
				}			
			});

		//li列表展示
		var courseStyle = document.getElementById(nowCourseLiId).style.display;
		
		if(courseStyle != 'none'){
			textObj.innerHTML="查看课程目录";
			$("#" + nowCourseLiId).css('display','none');
			//$("#" + courseButtonId).removeClass("button_4").addClass("button_3");
		}else{
			textObj.innerHTML="收起课程目录";
			$("#" + nowCourseLiId).css('display','block');
			//$("#" + courseButtonId).removeClass("button_3").addClass("button_4");
		}
		//li列表展示
	} 
    
	var onClickCourse_course = function(sellWayId,courseId,textObj,subjectId){
	
		var nowCourseLiId = "course_kpointlist_" +sellWayId +"_"+courseId;
		//**************************************
		thisclicksellc=sellWayId+"_"+courseId;
		if (lastlicksellc==""){
			lastlicksellc=thisclicksellc;
		}else if (lastlicksellc==thisclicksellc){
			//li列表展示
			var courseStyle = document.getElementById(nowCourseLiId).style.display;
			if(courseStyle != 'none'){
				var textObj_this = "textObj_"+thisclicksellc;
				$("#"+textObj_this).html("查看课程目录"); 
				$("#"+nowCourseLiId).html("");
				$("#"+nowCourseLiId).hide();
				return;
				//$("#" + courseButtonId).removeClass("button_4").addClass("button_3");
			}else{
				textObj.innerHTML="收起课程目录";
				$("#" + courseStyle).css('display','block');
				//$("#" + courseButtonId).removeClass("button_3").addClass("button_4");
			}
			//li列表展示
			//return;
		}else{
			var nowCourseLiId2 = "course_kpointlist_" +lastlicksellc;
			var textObj11 = "textObj_"+lastlicksellc;
			//$("#"+nowCourseLiId2).html("");
			$("#"+nowCourseLiId2).hide();
			$("#"+textObj11).html("查看课程目录"); 
			lastlicksellc=thisclicksellc;
			//li列表展示
			//$("#"+textObj11).html("查看课程目录"); 
			//$("#" + courseButtonId).removeClass("button_4").addClass("button_3");
			//li列表展示
		
		}
	
		//***********************
		var kpon =0;
		var kponopen =0;
		var kponflag =0;
		mysellWayId = sellWayId;
		$.ajax({
				url : baselocation + "/cus/cuslimit!toCourseShu.action",
				data : {"course.courseId":courseId},
				type : "post",
				dataType : "json",
				cache : false,
				success : function(result){
					dree = new dTree('dree');
					dree.add(-2,-1,'');
					$.each(result.entity,function(name,value){
						if((value.vedioUrl ==null || value.vedioUrl == '') && (value.ccUrl ==null || value.ccUrl == '')){
							dree.add(value.id,value.PId,value.name);
							kpon=kpon+1;
						}else if(value.vedioUrl !=null || value.vedioUrl != '' || value.ccUrl != null || value.ccUrl != '' ){
							
							if (kponflag==0){
								kponopen=kpon;
								kponflag=1;
							}
							kpon=kpon+1;
							var $vedioUrl="";
							var $vid="";
							if(value.playType==1&&value.ccUrl!=null){
								$vid=value.ccUrl;
							}else{
								
							}
							$vedioUrl=value.vedioUrl;
							//弹出新页面观看视频
							if(value.PId == -2){
							      dree.add(value.id,-1,"<input type='hidden' value='"+$vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+value.vedioUrl+"\"," + value.voId +",\""+$vid+"\")' class='fl'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><span id='link_"+value.id+"' class='fr'><a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+value.vedioUrl+"\"," + value.voId +",\""+$vid+"\")'>学习</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/cus/pblimit!getEveryOneProblemList.action?course.courseId=15&problem.pblSolv=1&queryProblemCondition.currentPage=1&location=1'>提问</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>")			
							}else{
							      dree.add(value.id,value.PId,"<input type='hidden' value='"+$vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+$vedioUrl+"\"," + value.voId +",\""+$vid+"\")' class='fl'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><span id='link_"+value.id+"'  class='fr'><a href='javascript:void(0)' onclick='goToListenCourseByPointId("+courseId+","+subjectId+ "," +value.id+","+mysellWayId+",\""+value.name+"\",\""+$vedioUrl+"\"," + value.voId +",\""+$vid+"\")'>学习</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/cus/pblimit!getEveryOneProblemList.action?course.courseId=15&problem.pblSolv=1&queryProblemCondition.currentPage=1&location=1'>提问</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>")
							}
							//当前页面观看视频
							/**
							if(value.PId == -2){
							      dree.add(value.id,-1,"<input type='hidden' value='"+value.vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")' class='left'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><font id='link_"+value.id+"' class='right'><a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")'>学习</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/cus/pblimit!getHighSoProblem.action'>提问</a>&nbsp;&nbsp;&nbsp;&nbsp;</font>")			
							}else{
							      dree.add(value.id,value.PId,"<input type='hidden' value='"+value.vedioUrl+"' id='vo_url_"+value.id+"'/>"+"<a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")' class='left'><img src='"+importURL+"/images/usercenter/kc_listicon.png'/>"+value.name+"</a><font id='link_"+value.id+"'  class='right'><a href='javascript:void(0)' onclick='onClickKpoint("+value.id+","+subjectId+")'>学习</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1&subjectIdweb="+subjectId+"'>考试</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+baselocation+"/cus/pblimit!getHighSoProblem.action'>提问</a>&nbsp;&nbsp;&nbsp;&nbsp;</font>")
							}
							*/
						}
					});
					$("#"+nowCourseLiId).html(dree.toString());
					if (kponopen>0){
					dree.closeAll();
	 					//dree.openTo(kponopen,true);
					}
					dree.openTo(2,true);
				},
				error : function(error){
				}			
			});

		//li列表展示
		var courseStyle = document.getElementById(nowCourseLiId).style.display;
		
		if(courseStyle != 'none'){
			textObj.innerHTML="查看课程目录";
			$("#" + nowCourseLiId).css('display','none');
			//$("#" + courseButtonId).removeClass("button_4").addClass("button_3");
		}else{
			textObj.innerHTML="收起课程目录";
			$("#" + nowCourseLiId).css('display','block');
			//$("#" + courseButtonId).removeClass("button_3").addClass("button_4");
		}
		//li列表展示
		
		//xue学习分布图
		
		if(courseId && courseId != '0'){//学习图
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
                	 + "<div class='pleft-20 mtop-5'><br />"
	                 + "  共有"+ totalUserNum +"人和你一起学习"+ courseName +"<br />"
	                 + " 比你快一章的有<font class='strongb font_red'>"+ thanOneNum +"</font>人<br />"
	                 + " 比你快两章的有<font class='strongb font_red'>"+ thanTwoNum +"</font>人<br />"
	                  + " 比你快三章及以上的有<font class='strongb font_red'>"+ thanThreeNum +"</font>人<br />"
	                 + " 每天学习1小时，即可保持进度</div>");  
     	}
     	
		//分布图
	}
	
	
	//shi1
	var onSTImgClick = function (){
		firstPlay = true;
		userCenter = false;
		isTryListen = true;
		var oSwf = getSwfObjectST();
		if(oSwf){
			document.getElementById("shiTingImg").style.display = "none";
			$("#videoPlayer_SF")[0].style.display = "block";
			oSwf.style.display = "block";
			loadVideoSF(shiTingUrl);
		}
	}
	
	var userCenter = false;
	
	var isUserCenter = function (){
		return userCenter;
	}
	
	var loadVideoSF = function (vedioUrl){
		var oSwf = getSwfObjectST();;
		try{
			if(oSwf) {
				oSwf.load(vedioUrl);
				firstPlay = false;
			}
		}catch(e){ }
	}
	
	var getSwfObjectST = function (){
		return swfobject.getObjectById("videoPlayer_SF");
	}
	//shi1
	
	//2
	var onST2ImgClick = function (){
		firstPlay = true;
		userCenter = false;
		isTryListen = true;
		var oSwf = getSwfObjectST2();
		if(oSwf){
			document.getElementById("shiTingImg2").style.display = "none";
			$("#videoPlayer_SF2")[0].style.display = "block";
			oSwf.style.display = "block";
			loadVideoSF2(shiTingUrl);
		}
	}
	
	var loadVideoSF2 = function (vedioUrl){
		var oSwf = getSwfObjectST2();;
		try{
			if(oSwf) {
				oSwf.load(vedioUrl);
				firstPlay = false;
			}
		}catch(e){ }
	}
	
	var getSwfObjectST2 = function (){
		return swfobject.getObjectById("videoPlayer_SF2");
	}
	//2
	
	function scrollNowHappened() {
		var count = 0;
		var ul1 = document.getElementById("now_happened_ul_1");
		var now_happened_div = document.getElementById("now_happened_div");
		
		function Marquee(){
			if(now_happened_div.scrollTop <= 0) {
				now_happened_div.scrollTop += ul1.scrollHeight;
			}
			now_happened_div.scrollTop -= 2;
			count ++;
			if(count < 35) {
				MyMar = window.setTimeout(Marquee, 10);
			}
		}
		MyMar = window.setTimeout(Marquee, 1);
		now_happened_div.onmouseover = function() {window.clearInterval(MyScorll);}
		now_happened_div.onmouseout = function() {MyScorll = window.setInterval(scrollNowHappened, 5000);}
	}

	function openBlackWin(){
		$("#user_center_black").css("display", "block");
		document.getElementById("user_center_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("user_center_black").style.height = document.documentElement.scrollHeight + "px";
	}
	
	function closeBlackWin() {
		$("#user_center_black").css("display", "none");
	}
	
	function closeActWin(){
		$("#user_center_act").css("display", "none");
		closeBlackWin();
	}
	
	function getTjCourses() {
		var title = "";
		var buyMethod = "";
		var picPath="";
		var clickTimes = 19681;
	    var	info = "";
		if(getTryListenId() == 44) {
			title = "司法课程";
			buyMethod = "buySf1()";
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_sf_03.jpg";
			info="2套课程均针对2011年司法考试全国统考！";
			clickTimes = 22131;
		} else if(getTryListenId() == 43) {
			title = "会计资格证课程";
			buyMethod = "buyKJ()";
			clickTimes = 18795;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_kj_03.jpg";
			info="本套课程通过精准的考试要点分析，打造完善的知识体系，在保证过关的同时，更有对会计从业的实战培训。在有效期之前听课次数不限。";
		} else if(getTryListenId() == 80) {
			title = "注册会计师课程";
			buyMethod = "buyCpa()";
			clickTimes = 23843;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_cpa03.jpg";
			info="本套课程有效期至2011年10月31日止，在有效期之前听课次数不限。即使是零基础，也能用1年的时间通过考试。通过精准的考试要点分析，打造专门针对考试的知识体系，而且将每一个知识点都讲到透彻。";
		} else if(getTryListenId() == 107) {
			title = "一级建造师课程";
			buyMethod = "buyJz1()";
			clickTimes = 19925;
			picPath="http://import.highso.org.cn/images/web/public/st/jzs_0.jpg";
			info="6大经典模块，四大考试科目，共147课时当前四大最热门专业实务课程可供选择：建筑工程,机电,工程,公路工程,市政公用工程 ";
		} else if(getTryListenId() == 68) {
			title = "人力资源师课程";
			buyMethod = "buyRl2()";
			clickTimes = 20673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_rl01.jpg";
			info="业内8位泰斗级名师联袂出演>>雍容华贵的于玲艳，激情四射的谭巍，剑走偏锋的梁延辉...，一切，只因我们相信【老师就是力量】";
		}else if(getTryListenId() == 110) {
			title = "高级会计师";
			buyMethod = "buyGk()";
			clickTimes = 20673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_gk_03.gif";
			info="评卷专家传授答题技巧，提高答题效率和应试水平，模拟试题，全真演练，强化您的实战能力。帮助学员提高答题技巧，减少答题错误，熟悉考试题型。";
		}else if(getTryListenId() == 120) {
			title = "公务员考试";
			buyMethod = "buyGwy()";
			clickTimes = 20673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_gwy_03.gif";
			info="本阶段将近10年公考考点一网打尽，通过全面、细致、专题式的讲解与分析，保证学员达到对基本考点的系统掌握与灵活运用。";
		}else if(getTryListenId() == 130) {
			title = "心理咨询师";
			buyMethod = "buyXL3()";
			clickTimes = 18673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_gwy_03.gif";
			info="本课程针对2011年11月心理咨询师考试，通过兴趣》基础》冲刺串讲》真题精讲 4大阶段学习，拿下心理咨询证书不成问题。";
		}else if(getTryListenId() == 150) {
			title = "心理咨询师";
			buyMethod = "buyky85()";
			clickTimes = 18673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_gwy_03.gif";
			info="本课程针对2012年全国考研课程！。";
		}else {
			title = "证券从业资格证课程";
			buyMethod = "buyZq()";
			clickTimes = 17682;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_zqtzjj_01.jpg";
			info="本课程针对2011年考试，与尚德证券最高端班型课程设置完全一样，通过精准的考试要点分析，详细地讲解要点，全真题库，解析一次通过考试完全不成问题，在有效期之前听课次数不限。";
		}
		var tjCourses = {};
		tjCourses.recommendVedios = [];
		tjCourses.recommendVedios[0] = {
			"picPath" : "http://import.highso.org.cn/images/web/public/st/shiting_pt_03.gif",
			"title" : "平台介绍",
			"clickTimes" : "19735",
			"url" : "http://shipin.sunland.org.cn/web2011/xcp.mp4",
			"buyMethod" : buyMethod,
			"videoId" : getTryListenId(),
			"index" : 0,
			"info" : info
		};
		tjCourses.recommendVedios[1] = {
			"picPath" : picPath,
			"title" : title,
			"clickTimes" : clickTimes,
			"url" : shiTingUrl,
			"buyMethod" : buyMethod,
			"videoId" : getTryListenId(),
			"index" : 1,
			"info" : info
		};
		return tjCourses;
	}
	
	function addTryTimes() {
		$.ajax({
			url : baselocation + "/res/tryVedioWeb!createTempCustomer.action",
			data : {
				"queryTryvedioCondition.id" : getTryListenId()
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {},
			error : function(error) {}
		});
	}
	
	function showWin(winId) {
		preWin = winId;
 		lockingWindow();
 		$("#" + winId).fadeIn();
 		$("#" + winId).css("top", document.documentElement.scrollTop + document.body.scrollTop + document.documentElement.clientHeight * 0.15);
 	}
 	function closeWin(winId) {
		$("#web_top_black").fadeOut();
 		$("#" + winId).fadeOut();
 		preWin = null;
 	}
 	 
	function showErrorWin(errorText, win) {
 		if(win != null  && win !='') {
			$("#" + win).css("display", "block");
			preWin = win;
 		}
 		lockingWindow();
 		$("#error_win").fadeIn();
 		$("#error_win").css("top", document.documentElement.scrollTop + document.body.scrollTop + document.documentElement.clientHeight * 0.15);
 		$("#error_message").html(errorText);
 	 }
 	//黑底效果
	function lockingWindow(){ 
		document.getElementById("web_top_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight + "px";
		$("#web_top_black").fadeIn();
		//document.getElementById("web_top_black").style.display = "block"; 
	}

 	function showSuccessWin(successText, win) {
 		if(win != null  && win !='') {
			$("#" + win).css("display", "none");
			preWin = win;
 		}
 		lockingWindow();
 		$("#success_win").fadeIn();
 		$("#success_win").css("top", document.documentElement.scrollTop + document.body.scrollTop + document.documentElement.clientHeight * 0.15);
 		$("#success_message").html(successText);
 	}
 		
		function closeErrorWin() {
 		if(preWin != null  && preWin !='') {
			$("#" + preWin).fadeIn();
 		} else {
			$("#web_top_black").fadeOut();
 		}
 		$("#error_win").fadeOut();
 		preWin = null;
 	}
 	
 	function closeSuccessWin() {
 		if(preWin != null  && preWin !='') {
			$("#" + preWin).fadeIn();
 		} else {
			$("#web_top_black").fadeOut();
 		}
 		$("#success_win").fadeOut();
 		preWin = null;
 	}
 	
 	//修改用户所在项目（只针对注册时未填写的用户）
 	function updatecustomerSubject(){
 		var subjectIdTemp=$("#upsubject").val();
 		if (subjectIdTemp<=0){
 			alert("请选择您要学习的专业！");
 			return;
 		}else{
 			$.ajax({
 				url : baselocation + "/cus/cuslimit!updateCustomerSubject.action",
 				data : {"customer.subjectId":subjectIdTemp},
 				type : "post",
 				dataType : "json",
 				cache : false,
 				success : function(result){
 					if (result.returnMessage=="success"){
 						//关闭
 						var addr_href=location.href;
 						if(addr_href.indexOf("ss.haixue.com")!=-1)
 						{
 						SetCookie("subjectIdTemp",subjectIdTemp);
 						SetCookieDomain("subjectIdTemp",subjectIdTemp,"haixue.com");
 						}else{
 						SetCookieDomain("subjectIdTemp",subjectIdTemp,"highso.cn");
 						}
/* 						$("#dissubjectpop").hide();
 						closeBlackWin();*/
 						location.href=baselocation + "/cus/cuslimit!toUserCenter.action?customer.subjectId="+subjectIdTemp;
 					}
 				},
 				error : function(error) {
 				}

 			});
 			
 		}
		
	}
 	
	// BASE64 encode and decode. / Modified by ZHENG QIANG.
	var BASE64={
	
	enKey: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',
	
	deKey: new Array(
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
		52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
		-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
		15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
		-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
		41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1
	),
	
	encode: function(src){
		
		var str=new Array();
		var ch1, ch2, ch3;
		var pos=0;
		
		while(pos+3<=src.length){
		ch1=src.charCodeAt(pos++);
		ch2=src.charCodeAt(pos++);
		ch3=src.charCodeAt(pos++);
		str.push(this.enKey.charAt(ch1>>2), this.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
		str.push(this.enKey.charAt(((ch2<<2)+(ch3>>6))&0x3f), this.enKey.charAt(ch3&0x3f));
		}
		
		if(pos<src.length){
		ch1=src.charCodeAt(pos++);
		str.push(this.enKey.charAt(ch1>>2));
		if(pos<src.length){
		ch2=src.charCodeAt(pos);
		str.push(this.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
		str.push(this.enKey.charAt(ch2<<2&0x3f), '=');
		}else{
		str.push(this.enKey.charAt(ch1<<4&0x3f), '==');
		}
		}
		
		return str.join('');
	},
	
	decode: function(src){
	
		var str=new Array();
		var ch1, ch2, ch3, ch4;
		var pos=0;
		
		src=src.replace(/[^A-Za-z0-9\+\/]/g, '');
		
		while(pos+4<=src.length){
		ch1=this.deKey[src.charCodeAt(pos++)];
		ch2=this.deKey[src.charCodeAt(pos++)];
		ch3=this.deKey[src.charCodeAt(pos++)];
		ch4=this.deKey[src.charCodeAt(pos++)];
		str.push(String.fromCharCode(
		(ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2), (ch3<<6&0xff)+ch4));
		}
		
		if(pos+1<src.length){
		ch1=this.deKey[src.charCodeAt(pos++)];
		ch2=this.deKey[src.charCodeAt(pos++)];
		if(pos<src.length){
		ch3=this.deKey[src.charCodeAt(pos)];
		str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2)));
		}else{
		str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4)));
		}
		}
		return str.join('');
		}
	};

 	
