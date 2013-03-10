var ishavebuy=false;
//加载400电话客服QQ	
function loadData(){

	 if(ishavebuy=='true'){
 		$("#qqOnline").remove();
 		return;
	 }
	 
 		$.ajax({  
			url : baselocation+"/cus/cuslimit!getCustomerServiceMsg.action",  
			data : { },  // 参数  
			type : "post",  
			cache : false,    
			dataType : "json",  //返回json数据 
			success : function(result) {
				var subject = result.entity;
				//$("#hotline").empty();
				if(subject.hotline!=null && subject.hotline!=""){
					$("#hotline").html(subject.hotline);
				}
				
				if((subject.courseConsultNumber==""||subject.courseConsultNumber==null) && (subject.customerServiceNumber==""||subject.customerServiceNumber==null) && (subject.complaintServiceNumber==""||subject.complaintServiceNumber==null)){
					$("#qqOnline").remove();
 					return;
				}
				 
				$("#courseConsultNumber1").attr("href","http://wpa.qq.com/msgrd?v=3&uin="+subject.courseConsultNumber+"&site=嗨学网&menu=yes");
				$("#courseConsultNumber2").attr("href","http://wpa.qq.com/msgrd?v=3&uin="+subject.courseConsultNumber+"&site=嗨学网&menu=yes");
				$("#courseConsultNumber2").append(subject.courseConsultName);
				$("#customerServiceNumber1").attr("href","http://wpa.qq.com/msgrd?v=3&uin="+subject.customerServiceNumber+"&site=嗨学网&menu=yes");
				$("#customerServiceNumber2").attr("href","http://wpa.qq.com/msgrd?v=3&uin="+subject.customerServiceNumber+"&site=嗨学网&menu=yes");
				$("#customerServiceNumber2").append(subject.customerServiceName);
				$("#complaintServiceNumber1").attr("href","http://wpa.qq.com/msgrd?v=3&uin="+subject.complaintServiceNumber+"&site=嗨学网&menu=yes");
				$("#complaintServiceNumber2").attr("href","http://wpa.qq.com/msgrd?v=3&uin="+subject.complaintServiceNumber+"&site=嗨学网&menu=yes");
				$("#complaintServiceNumber2").append(subject.complaintServiceName);
			},
			error: function(){ 
			}
		});
     }
     	
     	function getScore(){
     		$.ajax({  
				url : baselocation+"/cus/cuslimit!getScore.action",  
				data : { },  // 参数  
				type : "post",  
				cache : false,    
				dataType : "json",  //返回json数据 
				success : function(result) {
					var scoreAndFlag = result.entity;
					var score = scoreAndFlag.split(",")[0];
					$("#score").empty();
					$("#score").append(score);
					if(scoreAndFlag.split(",")[1]==1){
						$("#jifenhref").attr("style","background-position:-71px -350px;");
						$("#jifenhref").unbind('click').removeAttr('onclick');
					}
				},
				error: function(){ 
//					alert("阿喔，出错喽，请联系管理员。");
				}
			});
     	}
     	
     	function signin(){
     		$("#jifenhref").attr("style","background-position:-71px -350px;");
     		$("#jifenhref").unbind('click').removeAttr('onclick');
     		$.ajax({  
				url : baselocation+"/cus/cuslimit!signin.action",  
				data : { },  // 参数  
				type : "post",  
				cache : false,    
				dataType : "json",  //返回json数据 
				success : function(result) {
					if(result.returnMessage=='first'){
						var score = result.entity;
						$("#score").empty().append(score);
						
						 $("#jifen_numMask").show().animate({top:'-40px',opacity:'hide'},2100,function(){
						  	$(this).css("top","40px");
						  });
					}else{
						var msg = result.entity;
						showSuccessWin("您今天已经打过卡，明天再来吧:)","error_win");
					}
				},
				error: function(){ 
//					alert("阿喔，出错喽，请联系管理员。");
				}
			});
     	}
     	
     	
     	
	function setUserNameAndPhoto() {
		var username = getCookieFromServer("sedu.cookie.ukey").split(',')[3];
		if(username.substring(username.length-1) == "\"") {
			username = username.substring(0, username.length-1);
		}
		$("#uc_header_username").html(username);
		$("#uc_header_username_newer").html(username);
		$("#uc_header_username_newer_buy").html(username);
		var photo = getCookie("sedu.cookie.ukey").split(',')[4];
		if(photo != null  &&  photo != "null" && photo != "" && photo !="\"") {
			if(photo.substring(photo.length-1) == "\"") {
				photo = photo.substring(0, photo.length-1);
			}
			$("#cus_photo_img").attr("src", importURL + "/upload/customer/photo/" + photo);
		} else {
			$("#cus_photo_img").attr("src", importURL + "/images/usercenter/leftnav_2.gif");
		}
	}
	
	function getUserName() {
		var username = getCookieFromServer("sedu.cookie.ukey").split(',')[3];
		if(username.substring(username.length-1) == "\"") {
			username = username.substring(0, username.length-1);
		}
		return username;
	}
	
	
	var flashStatus=true;
	var flashInfo='';
	
	/**
	 * <br>
	 * <b>功能：</b>消息提醒前插入消息，消息为关于3天内更新的课程<br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.019 <br>
	 */
	function insertCourseMsgs(){
		//判断cookie中当天是否已经进行了插入，如果执行了插入则不在进行第二次 标示为insertFlag 0否1是
		var insertFlag = getCookie("insertFlag");
		if(insertFlag == null || insertFlag.trim() == "") {
			SetCookieOutTime("insertFlag", 1,"d1");
			var dayOfWeek = new Date().getDay();
			if(dayOfWeek=='1' || dayOfWeek=='4'){
				$.ajax({
				url : baselocation + "/msg/webmsg!insertCourseMsgs.action",
				data : {},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
				},
				error : function(error) {
				}
				});
			} 
		}
	}
	
	//发送试卷更新消息
	function SendMsgByCus(){
		//判断cookie中当天是否已经进行了插入，如果执行了插入则不在进行第二次 标示为insertFlag 0否1是
		var insertFlag2 = getCookie("insertFlag2");
		if(insertFlag2 == null || insertFlag2.trim() == "") {
			SetCookieOutTime("insertFlag2", 1,"d1");
			var dayOfWeek = new Date().getDay();
			if(dayOfWeek=='1' || dayOfWeek=='4'){
				$.ajax({
				url : baselocation + "/cus/cusweb!SendMsgByCus.action",
				data : {},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
				},
				error : function(error) {
				}
				});
			}
			
		}
	} 
	

	//消息提醒
	function getMsgByCus(){
			
			$.ajax({
				url : baselocation + "/msg/webmsg!getCountUnreadMsgs.action",
				data : {},
				type : "post",
				dataType : "json",
				cache : false,
				success : function(result) {
					if(result.returnMessage != '0'){
						$('#infoId').html("您有新消息（"+ result.returnMessage +"）");
						flashInfo="您有新消息（"+ result.returnMessage +"）";
						flash();
					}
				},
				error : function(error) {
					//alert('error');
				}
			});
	}
		
		function flash() {
		   if (flashStatus) {
		    document.title = flashInfo;
		   } else {
		    document.title = "HighSo嗨学网_个人中心 远程网络职业教育领跑者 尚德机构旗下";
		   }
		   flashStatus = !flashStatus;
		   setTimeout(flash, 750);
		  }
		
	
	/*首页新手上路*/
	function NewPrBlock(){
		document.getElementById("newprbox").style.display="block";	
	}
	function NewPrNone(){
		document.getElementById("newprbox").style.display="none";	
	}
	
	
	
	//积分选项卡切换
	 $(function() {
           var $index;
		   $(".jifen_tab li").click(function(){
			   $index = $(".jifen_tab li").index(this);
			   $(this).siblings().removeClass("jifen_hover").end().addClass("jifen_hover");
			   $(".jifen_tabcon > div").eq($index).removeClass("hidejife").siblings().addClass("hidejife");
			   }).hover(function(){
		$(this).addClass("jifen_se");
		}, function(){
			$(this).removeClass("jifen_se");
			});
        });
	//积分选项卡切换 end


//选项卡一
$(document).ready(function() {
		$("div.tab").find("div.uc_tabcon:gt(0)").hide();
		$("div.tab").find("ul.uc_tab a:first").addClass("current");
		$("div.tab").find("ul.uc_tab a").click(function() {
            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
			$("div.uc_tabcon:eq("+$("ul.uc_tab a").index(this)+")").show().siblings("div.uc_tabcon").hide();
			return false;
        });
});

//选项卡二
$(document).ready(function() {
		$("div.tab2").find("div.uc_tabcon2:gt(0)").hide();
		$("div.tab2").find("ul.uc_tab2 a:first").addClass("current");
		$("div.tab2").find("ul.uc_tab2 a").click(function() {
            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
			$("div.uc_tabcon2:eq("+$("ul.uc_tab2 a").index(this)+")").show().siblings("div.uc_tabcon2").hide();
			return false;
        });
});

//首页我看他看选项卡
$(document).ready(function() {
        $("div.all_look_tabcon:gt(0)").hide();
		$("ul.all_look_tab a:first").addClass("current");
		$("ul.all_look_tab a").click(function() {
            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
			$("div.all_look_tabcon:eq("+$("ul.all_look_tab a").index(this)+")").show().siblings("div.all_look_tabcon").hide();
			return false;
        });
});
//qq在线咨询
$(document).ready(function() {
    $("#qqClose").click(function() {
        $("#qqOnline").hide();
		return false;
    });
	
	var $qqonlineFun=function(){
	var st = $(document).scrollTop();// winh = $(window).height();
	if (!window.XMLHttpRequest) {
	$("#qqOnline").css("top", st + 180);
	}
	};
	$(window).bind("scroll", $qqonlineFun);
	$(function() { $qqonlineFun(); });
	
});
//首页完善个人资料框焦点
$(document).ready(function() {
	$("div#.user_data_text").find("textarea").focus(function(){
	$("p#uc_data_textHolder").hide();
  }).bind("blur",function(){
	 if($(this).val()=='')
		$("p#uc_data_textHolder").show();
	});
});
//问答输入框焦点
$(document).ready(function() {
	$("div.answer_text").find("textarea").focus(function(){
	$("p.answer_textHolder").hide();
  }).bind("blur",function(){
	 if($(this).val()=='')
		$("p.answer_textHolder").show();
	});
});
//获取用户专业公告
function getAcmentTop(subjectId){
	$("#side_ann").empty();
	if(subjectId == null || typeof(subjectId) == undefined || subjectId==""){
		subjectId=getCookie("subjectId");
	}
	$.ajax({
			url : baselocation + "/cus/cusweb!ajaxAcment.action",
			type : "get",
			dataType : "json",
			data : {"subjectIds":subjectId},
			cache : true,
			success : function(result){
				var data = eval("("+result.jumpUrl+")");
				var buffer = new StringBuffer();
				buffer.append("<h2 class='side_title'>网站公告</h2>");
				$.each(data,function(name,value){
					var subtitle = value.title;
					buffer.append("<ul><li>");
					buffer.append("<a href='"+baselocation+"/cms/acmentweb!view.action?id="+value.id+"&subjectIds="+subjectId+"' title="+value.title+" target='_blank'>");
					if(subtitle.length>10){
						subtitle = subtitle.substring(0,10);
					}else if(subtitle.length>12){
						subtitle = subtitle.substring(0,12);
					}
					buffer.append(""+subtitle+"");
					buffer.append("</a>");
					if(name<3){
						buffer.append("<img alt='"+value.title+"' src='"+importURL+"/images/usercenter/new_icon.png' />");
					}
					buffer.append("</li></ul>");
				});
				$("#side_ann").html(buffer.toString());
			}
		});
		
}

function gettamne(){
	var subjectIdother=getCookie("subjectId");
	var subjectIdothername = getSubjectNameById(subjectIdother);
	
	$.ajax({
		url : baselocation + "/static/web/lastLearnBuyInfo/"+subjectIdother+"/index.shtml",
		type : "get",
		dataType : "json",
		cache : false,
		success : function(result){
			$.each(result,function(name,value){
				var buf= new StringBuffer();
				var userName=value.userName;
				if(userName==''){
					userName=value.email;
				}
				var sex="";
				
				if(value.sex=="1"){
					sex="男";
					
				}else if(value.sex=="0"){
					sex="女";
				}
				
				buf.append("<li> <div sex='"+sex+"' phone='http://import.highso.org.cn/upload/customer/photo/"+value.photo+"'' otherName='"+subjectIdothername+"'  userName='"+userName+"' cusId='"+value.cusId+"' id='otherUser_pic"+name+"' class='otherUser_pic'><img src='http://import.highso.org.cn/upload/customer/photo/"+value.photo+"' width='67' height='63'></div>");
				buf.append("<div class='otherUser_name'>"+userName+"</div>");
				buf.append("<p>"+sex+"</p>");
				buf.append("<p>"+subjectIdothername+"</p> </li>");
				
				$("#otherUser_list").append(buf.toString());
				
				$("#otherUser_pic" + name).bind("click", {index: name}, clickHandler);
			}); 
			
		},
		error:function(error){
		}//
	});
}

function clickHandler(event){
	$("div.user_pop").empty();
	var input = new StringBuffer();
	input.append("<span class='user_pop_jiao'></span><span class='user_pop_close'>关闭</span>");
	input.append("<dl class='user_pop_info'><dt class='user_pop_pic'><img src="+$(this).attr('phone')+" width='67' height='63'></dt>");
	input.append("<dd>昵 称："+$(this).attr('userName')+"</dd>");
	input.append("<dd>性 别："+$(this).attr('sex')+"</dd>");
	input.append("<dd>课 程："+$(this).attr('otherName')+"</dd></dl>");
	
	$.ajax({
		url : baselocation + "/cus/cusweb!ajaxOtherCusInfo.action",
		type : "get",
		data : {"id":$(this).attr('cusId')},
		dataType : "json",
		cache : true,
		async:false,
		success : function(result){
			if(result.returnMessage == "success"){
				var data = eval("("+result.entity+")");
				input.append(getSellResult(data['sellResult'],data['sellSize']));
				input.append(getCourseResult(data['courseResult'],data['courseSize']));
			}
		},
		error:function(error){
		}//
	});
	
	$("div.user_pop").append(input.toString());
	$("div.user_pop").css({"top":(event.pageY-60)+"px"}).fadeIn();
	hidenHandler();
	
}

function getSellResult(result,size){
	var dataResult = new StringBuffer();
	dataResult.append("<div class='user_pop_course'>");
	dataResult.append("<h4>所学课程</h4>");
	dataResult.append("<p>");
	$.each(result,function(name,value){
		var symbol="";
		if(name  == 4 ){
			return false;
		}
		if((name+1)%2!=1){
			symbol = "</br>";
		}else{
			if(size>1){
				symbol = "&nbsp;|&nbsp;";
			}
		}
		dataResult.append("<a href='javascript:void(0)'>"+value.publicName+"</a> "+symbol+"");
	});
	dataResult.append("</p></div>");
	return dataResult.toString();
}

function getCourseResult(result,size){
	var dataResult = new StringBuffer();
	dataResult.append("<div class='user_pop_exam'>");
	dataResult.append("<h4>在做试卷</h4>");
	dataResult.append("<p>");
	$.each(result,function(name,value){
		var symbol="";
		if(name ==4 ){
			return false;
		}
		if((name+1)%2!=1){
			symbol = "</br>";
		}else{
			if(size>1){
				symbol = "&nbsp;|&nbsp;";
			}
		}
		dataResult.append("<a target='_blank' href='"+baselocation+"/exam/qstManager!getExamPaperInfo.action?epid="+value.id+"'>"+value.publicName+"</a> "+symbol+"");
	});
	dataResult.append("</p></div>");
	return dataResult.toString();
}

function hidenHandler(){
	$(".user_pop_close").click(function() {
        $(this).parents("div.user_pop").hide();
    });
}

//选项卡一
$(document).ready(function() {
		$("div.tab").find("div.uc_tabcon:gt(0)").hide();
		$("div.tab").find("ul.uc_tab a:first").addClass("current");
		$("div.tab").find("ul.uc_tab a").click(function() {
            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
			$("div.uc_tabcon:eq("+$("ul.uc_tab a").index(this)+")").show().siblings("div.uc_tabcon").hide();
			return false;
        });
});
//选项卡二
$(document).ready(function() {
		$("div.tab2").find("div.uc_tabcon2:gt(0)").hide();
		$("div.tab2").find("ul.uc_tab2 a:first").addClass("current");
		$("div.tab2").find("ul.uc_tab2 a").click(function() {
            $(this).addClass("current").parent("li").siblings("li").find("a").removeClass("current");
			$("div.uc_tabcon2:eq("+$("ul.uc_tab2 a").index(this)+")").show().siblings("div.uc_tabcon2").hide();
			return false;
        });
});
//问答输入框焦点
$(document).ready(function() {
	$("div.answer_text").find("textarea").focus(function(){
	$("p.answer_textHolder").hide();
  }).bind("blur",function(){
	 if($(this).val()=='')
		$("p.answer_textHolder").show();
	});
});


//点击查看课程
var goToListenCourseTmp = function goToListenCourseTmp (courseId,subjectId,sellWayId) {
	if(courseId ==0 && subjectId ==0 && sellWayId ==0){
		return false;
	}
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
		window.open (baselocation+'/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId="+subjectId + "&sellid=" + sellWayId + "&kpointId=" + paramFirstPlayVideoId + "&veri=" +getbollvie() + "&title=" + firestPlayVideoName + "&videoURL=" + BASE64.encode(firstPlayVedioUrl) + "&videoId=" + firstPlayVideoVoid +"&vid=" + firstPlayVideoCCuUrl, "_blank", "screenX="+(window.screen.availWidth - 1099)/2+",screenY="+(window.screen.availHeight - 608)/2);
	}
}
//点击查看课程
var goToListenCourseKpoint = function goToListenCourseKpoint (courseId,subjectId,sellWayId,kpointId) {
	if(courseId ==0 && subjectId ==0 && sellWayId ==0){
		return false;
	}
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
					if(typeof(kpointId) != 'undefine' && kpointId != null){
						if(firstPlayVideo.id == kpointId){
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
					}else{
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
		}
	});
  	
  	if(courseId && paramFirstPlayVideoId){
		window.open (baselocation+'/cou/courselimit!toListenMyCourse.action?course.courseId=' + courseId + "&course.subjectId="+subjectId + "&sellid=" + sellWayId + "&kpointId=" + paramFirstPlayVideoId + "&veri=" +getbollvie() + "&title=" + firestPlayVideoName + "&videoURL=" + BASE64.encode(firstPlayVedioUrl) + "&videoId=" + firstPlayVideoVoid +"&vid=" + firstPlayVideoCCuUrl, "_blank", "screenX="+(window.screen.availWidth - 1099)/2+",screenY="+(window.screen.availHeight - 608)/2);
	}
}
//右侧学员滚动展示
$(document).ready(function() {
	var scrollUsertime;
	$("ul.otherUser_list").hover(function(){
		clearInterval(scrollUsertime);
	},function(){
		scrollUsertime= setInterval(scrollUser,3000);
	}).trigger("mouseleave");
    function scrollUser(){
		$("ul.otherUser_list li").eq(0).animate({
			height:0,paddingBottom:0,paddingTop:0},1000,function(){
		    $(this).appendTo("ul.otherUser_list").css({"height":"auto","padding-bottom":"10px","padding-top":"10px","border-width":"1px"});
		})
	}
});


