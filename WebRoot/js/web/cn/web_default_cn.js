	checkFromAndAgent();
	var currentURL = window.location.href;
	if(currentURL.indexOf("http://highso.sunland.org.cn")==0) {
		window.location.href = "http://highso." + currentURL.substring(currentURL.indexOf("cn"));
	}
	var initRandomCode = true;
	
	var tryVedioURL = "http://shipin.sunland.org.cn/web2011/xcp.mp4";
	var voUrl = ["http://shipin.sunland.org.cn/index-kuaiji-kuaijijichu-shiting.mp4","http://shipin.sunland.org.cn/index-kuaiji-kuaijijichu-shiting.mp4","http://shipin.sunland.org.cn/index-kuaiji-kuaijijichu-shiting.mp4"];
	
	var picUrl = ["static/web/pic/index/ls1.jpg","static/web/pic/index/ls3.jpg","static/web/pic/index/ls2.jpg"];
	var pic_now = "static/web/pic/index/ls1.jpg";
	
	function getContent(){
		var url = id + "_" + pageNo + ".shtml";
		$.getJSON(url,function(msg){
			showArticle(msg);
		});
	}
	
	var id = 0;
	
	function loadSWF(){
		loadVideo(tryVedioURL);
	}
	
	var onImgClick = function (){
		firstPlay = true;
		var oSwf = getSwfObject();
		if(oSwf){
			$("#banner_img").css("display", "none");
			$("#banner_div").css("display", "none");
			$("#videoPlayer")[0].style.display = "block";
			oSwf.style.display = "block";
			loadVideo(tryVedioURL);
		}
	}
	
	var firstPlay = false;
	
	var isFirstPlay = function (){
		return firstPlay;
	}
	
	var getCurrentVideoUrl = function (){
		return tryVedioURL;
	}
	
	var isUserCenter = function (){
		return false;
	}
	
	function onVedioClick() {
		$("#banner_img").css("display", "block");
		$("#banner_div").css("display", "block");
		$("#videoPlayer")[0].style.display = "hidden";
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
	
	//试听结束后点我要购买，触发此方法
	function getUrl(){
		var indexURL = window.location.href;
		var temp = indexURL.substring(7);
		var flagIndex = temp.indexOf("highso")==0?1:2;
		var strs = temp.split('/');
		var urlFlag = '';
		if(strs[flagIndex] != null || strs[flagIndex] != '') {
			if(strs[flagIndex].indexOf('?')>-1) {
				urlFlag = strs[flagIndex].substring(0, strs[flagIndex].indexOf("?"));
			} else {
				urlFlag = strs[flagIndex];
			}
		}
		if(urlFlag == 'kjz') {
			buyKJ();
		} else if(urlFlag == 'cpa') {
			buyCpa();
		} else if(urlFlag == 'zq') {
			buyZq();
		} else if(urlFlag == 'sf') {
			buySf1();
		} else if(urlFlag == 'rl') {
			buyRl3();
		}
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
		} else if(getTryListenId() == 83) {
			title = "证券从业资格证课程";
			buyMethod = "buyZq()";
			clickTimes = 17682;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_zqtzjj_01.jpg";
			info="本课程针对2011年考试，与尚德证券最高端班型课程设置完全一样，通过精准的考试要点分析，详细地讲解要点，全真题库，解析一次通过考试完全不成问题，在有效期之前听课次数不限。";
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
		}else if(getTryListenId() == 140) {
			title = "经济师考试课程";
			buyMethod = "buyJjsZ1()";
			clickTimes = 16673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_jjs_01.gif";
			info="中级经济师通关班（金融实务方向）：此课程包含2科，4大学习模块，共102课时";
		}else if(getTryListenId() == 150) {
			title = "全国研究生统一入学考试";
			buyMethod = "buykaoy()";
			clickTimes = 16673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_jjs_01.gif";
			info="本课程为2012年全国研究生统一入学考试公共课网络课程";
		}else if(getTryListenId() == 114) {
			title = "初级会计职称课程";
			buyMethod = "buycjkjs1()";
			clickTimes = 16673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_jjs_01.gif";
			info="本课程针对2012年助理会计师考试全国统考资深名师精心录制，可反复听反复看,有效期至2012年5月31日";
		}else if(getTryListenId() == 115) {
			title = "中级会计职称课程";
			buyMethod = "buyzjkjs1()";
			clickTimes = 17682;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_zqtzjj_01.jpg";
			info="本课程针对2012年会计师考试全国统考,资深名师精心录制，可反复听反复看,有效期至2012年5月31日";
		}else if(getTryListenId() == 116) {
			title = "二级建造师课程";
			buyMethod = "buyjzs1()";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/jzs_0.jpg";
			info="本课程针对2012年6月20日全国二级建造师考试，全国通用！课程根据尚德机构二级建造师最高端班型录制，通过率93%以上";
		} else if(getTryListenId() == 117) {
			title = "工程硕士考试";
			buyMethod = "buyjzs1()";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/as_kcpic_gcss.png";
			info="课程根据尚德机构最高端班型录制，通过率93%以上";
		}else if(getTryListenId() == 118) {
			title = "造价师课程";
			buyMethod = "BuyNow('396')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/as_kcpic_zjgcs.png";
			info="本课程针对2012年10月造价师考试，全国通用！课程根据尚德机构造价师最高端班型录制，通过率93%以上";
		}else if(getTryListenId() == 119) {
			title = "监理师课程";
			buyMethod = "BuyNow('395')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/as_kcpic_jlgcs.png";
			info="本课程针对2012年6月监理造价师考试，全国通用！课程根据尚德机构监理造价师最高端班型录制，通过率93%以上";
		}else if(getTryListenId() == 120) {
			title = "自考";
			buyMethod = "BuyNow('401')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/as_zkpic_ky.png";
			info="我们的目标是：一次通自学考试！";
		}else if(getTryListenId() == 121) {
			title = "临床执业医师";
			buyMethod = "BuyNow('401')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/as_kcpic_ys.png";
			info="我们的目标是：一次通临床执业医师考试！";
		}else if(getTryListenId() == 122) {
			title = "法律顾问考试";
			buyMethod = "BuyNow('395')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/fl_kcpic.png";
			info="我们的目标是：一次通过法律顾问考试！";
		}else if(getTryListenId() == 123) {
			title = "成人高考";
			buyMethod = "BuyNow('395')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/as_kcpic_ck.png";
			info="我们的目标是：一次通过成人高考！";
		} else if(getTryListenId() == 124) {
			title = "注税考试";
			buyMethod = "BuyNow('395')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/zs_kcpic_ky.png";
			info="我们的目标是：一次通过注税考试！";
		}
		else if(getTryListenId() == 125) {
			title = "报关员考试";
			buyMethod = "BuyNow('395')";
			clickTimes = 19682;
			picPath="http://import.highso.org.cn/images/web/public/st/bg_kcpic_ky.png";
			info="我们的目标是：一次通过报关员考试！";
		}else if(getTryListenId() == 126) {
			title = "地方公务员考试";
			buyMethod = "buyGwy()";
			clickTimes = 20673;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_gwy_03.gif";
			info="本阶段将近10年公考考点一网打尽，通过全面、细致、专题式的讲解与分析，保证学员达到对基本考点的系统掌握与灵活运用。";
		}else if(getTryListenId() == 127) {
			title = "职称英语课程";
			buyMethod = "BuyNow('395')";
			clickTimes = 20673;
			picPath="http://import.highso.org.cn/images/web/public/st/as_zkpic_zcyy.png";
			info="我们的目标是：一次通过职称英语考试！";
		}else if(getTryListenId() == 128) {
			title = "期货从业资格考试";
			buyMethod = "BuyNow('395')";
			clickTimes = 20673;
			picPath="http://import.highso.org.cn/images/web/public/st/as_kcpic_qh.png";
			info="我们的目标是：一次通过期货从业资格考试！";
		}else {
			title = "二级建造师考试";
			buyMethod = "buyZq()";
			clickTimes = 17682;
			picPath="http://import.highso.org.cn/images/web/public/st/shiting_zqtzjj_01.jpg";
			info="本课程针对2012年6月20日全国二级建造师考试，全国通用！课程根据尚德机构二级建造师最高端班型录制，通过率93%以上！";
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
			"url" : vo_now,
			"buyMethod" : buyMethod,
			"videoId" : getTryListenId(),
			"index" : 1,
			"info" : info
		};
		return tjCourses;
	}
	function addTryTimes() {
		$.ajax({
			url : baselocation + "/res/tryVedioWeb!addTryTimes.action",
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
	
	function goToClazz(clazzId){
		window.open ('static/web/column/32/index.shtml?clazzId=' + clazzId, "_blank");
	}
	
	var getSwfObject = function (){
		return swfobject.getObjectById("videoPlayer");
	}
	
	$().ready(function() {
		SetCookie("indexURL", window.location.href);
		//startChangeBanner();
		GetServerTime();
		setInterval("GetServerTime()",1000);
		getadd();
		//onImgClick();
	});
	
	
	// JavaScript Document
	
	
	/*首页tab切换*/
	function setTab(m,n){
	 var tli=document.getElementById("menu"+m).getElementsByTagName("li");
	 var mli=document.getElementById("main"+m).getElementsByTagName("ul");
	 for(i=0;i<tli.length;i++){
	  tli[i].className=i==n?"hover":"";
	  mli[i].style.display=i==n?"block":"none";
	 }
	}
	
	
	//banner
	var h=1
	function HomeBannerH(h){
			for(i=0;i<3;i++){
			document.getElementById("hbanner_ad"+i).style.display="none";
			document.getElementById("hbanner_ad"+h).style.display="block";
			document.getElementById("hbanner_text"+i).style.color="#ffffff";
			document.getElementById("hbanner_text"+i).style.background="#000000";
			document.getElementById("hbanner_text"+h).style.color="#000000";
			document.getElementById("hbanner_text"+h).style.background="#cccccc";
			}
		}
	var t
	
	function startChangeBanner() {
		t=setTimeout("HomeLoad()",8000);
	}
	
	function HomeLoad(){
	    h++
	    if(h>4){h=1}
		changeBanner(h);
		t=setTimeout("HomeLoad()",8000)
		}
	function stopCount(){
		clearTimeout(t)
		}
		
	function changeBanner(index) {
		h = index;
		$("#banner_img").attr("src", bannerSrc[parseInt(index)-1]);
		for(var i = 1; i < 5; i++) {
			$("#banner_li_" + i).removeClass("on");
		}
		$("#banner_li_" + index).addClass("on");
	}
	
	function locking(k){ 
		if($.browser.msie ){
			$("#obj").css("display","none");
		}else if($.browser.safari){
			$("#obj").css("display","none");
		}
		
		
		for(i=0;i<8;i++){
			document.getElementById("he_black").style.display="block"; 
			document.getElementById("he_black").style.width=document.body.clientWidth;
			document.getElementById("he_black").style.height="2200px"; 
			document.getElementById("he_window").style.display='block'; 
			document.getElementById("he_win_"+i).style.display="none";
			document.getElementById("he_win_"+k).style.display="block";
		}
	} 
	
	function outing(){ 
		if($.browser.msie ){
			$("#obj").css("display","block");
		}else if($.browser.safari){
			$("#obj").css("display","block");
		}
		
	    document.getElementById("he_black").style.display="none"; 
	    document.getElementById("he_window").style.display='none'; 
	} 
	
	function changeShowPic(liIndex) {
		$("#showpic_img").attr("src", showPicSrc[parseInt(liIndex)-1]);
		for(var i=1; i<=4; i++) {
			$("#show_pic_li" + i).removeClass("on");
		}
		$("#show_pic_li" + liIndex).addClass("on");
	}
	/*切换评论样式 START*/
	function checked_sty(index){
		for(var i=1;i<5;i++){
			$("#sp"+i).addClass("sb");
			$("#sp"+i).removeClass("sp");
		}
		$("#sp"+index).removeClass("sb");
		$("#sp"+index).addClass("sp");
	}
	function checked_sty1(index){
		for(var i=5;i<7;i++){
			$("#sp"+i).addClass("sb");
			$("#sp"+i).removeClass("sp");
		}
		$("#sp"+index).removeClass("sb");
		$("#sp"+index).addClass("sp");
	}
	/*切换评论样式 END*/
	function GetServerTime() {
		
		if(document.getElementById("time_last")!=null){
			now.setTime(now.getTime()+1000);
			days = (urodz - now) / 1000 / 60 / 60 / 24;
			daysRound = Math.floor(days);
			hours = (urodz - now) / 1000 / 60 / 60 - (24 * daysRound);
			hoursRound = Math.floor(hours);
			minutes = (urodz - now) / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound);
			minutesRound = Math.floor(minutes);
			seconds = (urodz - now) / 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound);
			secondsRound = Math.round(seconds);
			document.getElementById("time_last").innerHTML = Math.abs(daysRound) + "天"  + hoursRound +"小时"+minutesRound+"分"+secondsRound+"秒";
		}
	}
	
	var buyCountEndTime = new Date();
	
	function getadd() {
		
		if(document.getElementById("consumer_count")!=null){
			var goumairenshu= parseInt((buyCountEndTime.getTime()-starttime.getTime())/1000/900);
			document.getElementById("consumer_count").innerHTML = goumairenshu;
		}
	}
	
	function toCreateTempCus() {
		var mobile = $("#tempCusMobile").val();
		if(!/^1{1}[0-9]{10}$/.test(mobile)) {
			showErrorWin("请输入正确的手机号码。", "");
			return;
		}
		
		var randomCode = $("#tempCusRandomCode").val();
		
		if(randomCode == null || randomCode.trim() == "") {
			showErrorWin("请输入验证码。", "");
			$("#tempCusImg").attr("src", baselocation + "/util/randomCode!tempCusRandomCode.action?random=" + Math.random());
			return;
		}
		
		if(!checkApplyTimes()) {
			return;
		}
		
		$.ajax({
			url : baselocation + "/cus/cusweb!createTempCustomer.action",
			data : {
				"customer.mobile" : mobile,
				"tempCusRandomCode" : randomCode,
				"customer.subjectId" : getSubjectIdByIndexPage()
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					//艾德思奇统计：注册。
					//showErrorWin("体验账号已发送至您的手机。", "");
					showSuccessWin("体验账号已发送至您的手机。", "");
				} else if(result.returnMessage == "emailInUsed") {
					showErrorWin("该手机已体验过。", "");
				} else if(result.returnMessage == "randoCodeError") {
					showErrorWin("验证码错误。", "");
				} else {
					showErrorWin("操作失败，请重试。", "");
				}
				$("#tempCusMobile").val("");
				$("#tempCusRandomCode").val("");
				$("#tempCusImg").attr("src", baselocation + "/util/randomCode!tempCusRandomCode.action?random=" + Math.random());
			},
			error : function(error) {
				alert('error');
			}
		});
	}
	
	function changeRandomCode(isChange) {
		if(initRandomCode || isChange) {
			var mobile = $("#tempCusMobile").val();
			if(!/^1{1}[0-9]{10}$/.test(mobile)) {
				showErrorWin("请输入正确的手机号码。", "");
				return;
			}
			initRandomCode = false;
			$("#tempCusImg").attr("src", baselocation + "/util/randomCode!tempCusRandomCode.action?random=" + Math.random());
		}
	}
	
	function checkApplyTimes() {
		var applyTCTimes = getCookie("applyTCTimes");
		var date = new Date();
		if(applyTCTimes != null && applyTCTimes != "") {
			if(applyTCTimes.split(",")[0] != ("" + date.getYear() + (date.getMonth() + 1) + date.getDate())) {
				SetCookie("applyTCTimes", "" + date.getYear() + (date.getMonth() + 1) + date.getDate() + ",1");
			} else if(parseInt(applyTCTimes.split(",")[1]) >= 3) {
				showErrorWin("您已申请三次，请明天重试。", "");
				return false;
			} else {
				SetCookie("applyTCTimes", "" + date.getYear() + (date.getMonth() + 1) + date.getDate() + "," + (parseInt(applyTCTimes.split(",")[1]) + 1));
			}
		} else {
			SetCookie("applyTCTimes", "" + date.getYear() + (date.getMonth() + 1) + date.getDate() + ",1");
		}
		return true;
	}
	
		function  tempCusMobileChg(event) {
		    var e = event || window.event;
		    var keyCode = e.keyCode || e.which;
		    if (keyCode < 48 || keyCode > 57){
		    	if (keyCode==8 || keyCode==9 || keyCode==37 || keyCode==39 || keyCode==46){
		    		return true;
		    	}
		    	return false;
		    }
 		}
