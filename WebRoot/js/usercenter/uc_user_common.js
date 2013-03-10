﻿	
	$().ready(function() {
		setUserNameAndPhoto();
		dislevelName();//经验显示级别和名称
		//insertCourseMsgs();//最新上传课程消息
		//SendMsgByCus();//最新上传试卷消息
		getMsgByCus();//消息提醒
	});
	
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
	
	function exit() {
		$.ajax({
			url : baselocation + "/cus/cusweb!ajaxExit.action",
			data : {},
			type : "post",
			dataType : "json",
			cache : false,
			success : function(result) {
				
			},
			error : function(error) {
				//alert('error');
			}
		});
		var currenturl=window.location.href;
		if(currenturl.indexOf("highso.cn")>-1){
			DeleteCookieDomain("sedu.cookie.ukey","highso.cn");
		}else if(currenturl.indexOf("ss.haixue.com")!=-1)
		{
			DeleteCookieDomain("sedu.cookie.ukey","haixue.com");
		}
		DeleteCookie("sedu.cookie.ukey");
		toIndexPageInUC();
	}
		

	function toColumn(keyWord) {
		var columnId = getColumnId(keyWord);
		if(columnId != -1) {
			window.open( baselocation + "/static/web/column/" + columnId + "/index_1.shtml");
		}
	}
	

	function getColumnId(keyWord) {
		var columnId = null;
		var v = window.location.href ;
		if(v.indexOf("http://ga.highso.org")==0){
			keyWord+="_org";
		}
		else if(v.indexOf("http://highso.org.cn")==0){
			keyWord+="_org_cn";
		}
		else if(v.indexOf("http://highso.org")==0){
			keyWord+="_org";
		}
		else if(v.indexOf("http://highso.com.cn")==0){
			keyWord+="_com_cn";
		}
		else if(v.indexOf("http://highso.net.cn")==0){
			keyWord+="_net_cn";
		}else{
			keyWord+="_cn" ;
		}
		$.ajax({
			url : baselocation + "/cus/cusweb!getColumnId.action",
			data : {
				"queryColumnsCondition.keyWord" : keyWord
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					columnId = result.entity;
				} else {
					columnId = -1;
				}
			},
			error : function(error) {
				//alert('error');
			}
		});
		return columnId;
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
						flashInfo="您有新消息("+ result.returnMessage +")";
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
	
		//经验显示级别和名称
		function dislevelName(){
			$.ajax({
				url : baselocation + "/cus/totolsScoreweb!getExperienceLevel.action",
				data : {},
				type : "post",
				dataType : "json",
				cache : false,
				success : function(result){
					var exp = eval(result.returnMessage);
					//$("#expname").html((exp[0].expName==''||exp[0].expName==null)?'新手上路':exp[0].expName);
					//document.getElementById("expvalue").innerHTML=exp[0].expValue+"/"+exp[0].nextExpValue;
					$("#expLevel").html(exp[0].expLevel);
					$("#expValue").html(exp[0].expValue);
					$("#tsCurrent").html(exp[0].tsCurrent);
					
				},
				error : function(error) {
					//alert('error');
				}
			});
		}
	
	function toIndexPageInUC(parms){
		if(location.href.indexOf("ss.haixue.com")!=-1)
		{
		location.href="http://haixue.com";
		return;
		}
		var indexURL = getCookie("indexURL");
		if(indexURL==null || indexURL == '') {
			window.location.href = baselocation + "/";
			return;
		}
		
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
		indexURL = "http://";
		for(var i=0; i<flagIndex; i++) {
			indexURL += strs[i] + "/";
		}
		indexURL += urlFlag;
		if(parms != null) {
			indexURL += parms.indexOf("?") == 0 ? parms : "?" + parms;
		}
		window.location.href = indexURL;
	}
	/* 考试页面cookie设置*/
	function examcookie(){
		SetCookie("kaoshi", 0);
	}
	
	//积分选项卡切换
	 $(function() {
           var $index
		   $(".jifen_tab li").click(function(){
			   $index = $(".jifen_tab li").index(this)
			   $(this).siblings().removeClass("jifen_hover").end().addClass("jifen_hover")
			   $(".jifen_tabcon > div").eq($index).removeClass("hidejife").siblings().addClass("hidejife")
			   }).hover(function(){
		$(this).addClass("jifen_se")
		}, function(){
			$(this).removeClass("jifen_se")
			})
        })
	//积分选项卡切换 end
