var baselocation='';
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
	
	function toIndexPageInUC(parms){
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
	
//BASE64 encode and decode. / Modified by ZHENG QIANG.
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

function StringBuffer()
{ 
	this.data = []; 
}
StringBuffer.prototype.append = function(){ 
	this.data.push(arguments[0]); 
	return this; 
}; 
StringBuffer.prototype.toString = function(){ 
	return this.data.join(""); 
};


function getSubjectNameById(subjectId) {
	if(subjectId == 1) {
		return "人力资源管理师课程";
	} else if(subjectId == 2) {
		return "心理咨询师课程";
	} else if(subjectId == 3) {
		return "会计资格证课程";
	} else if(subjectId == 5) {
		return "司法考试课程";
	} else if(subjectId == 7) {
		return "注册会计师课程";
	} else if(subjectId == 8) {
		return "证券从业课程";
	} else if(subjectId == 9){
		return "一级建造师考试课程" ;
	}else if(subjectId == 10){
		return "高级会计师课程" ;
	}else if(subjectId == 11){
		return "公务员课程" ;
	}else if(subjectId == 12){
		return "经济师考试课程" ;
	}else if(subjectId == 13){
		return "全国研究生统一入学考试" ;
	}else if(subjectId == 14){
		return "初级会计职称课程" ;
	}else if(subjectId == 15){
		return "中级会计职称课程" ;
	}else if(subjectId == 16){
		return "二级建造师考试课程" ;
	}else if(subjectId == 17){
		return "监理师课程" ;
	}else if(subjectId == 18){
		return "造价师课程" ;
	}else if(subjectId == 19){
		return "GCT考试" ;
	}else if(subjectId == 20){
		return "自考课程" ;
	}else if(subjectId == 21){
		return "临床执业医师" ;
	}else if(subjectId == 22){
		return "企业法律顾问" ;
	}else if(subjectId == 23){
		return "成考课程" ;
	}else if(subjectId == 24){
		return "注册税务师课程" ;
	}else if(subjectId == 25){
		return "报关员考试" ;
	}else if(subjectId == 26){
		return "地方公务员考试" ;
	}else if(subjectId == 27){
		return "职称英语课程" ;
	}else if(subjectId == 28){
		return "期货从业资格考试" ;
	}else if(subjectId == 30){
		return "远程学历" ;
	}else if(subjectId == 31){
		return "银行从业考试" ;
	}else if(subjectId == 32){
		return "网络营销师" ;
	}else if(subjectId == 33){
		return "职称计算机" ;
	}else if(subjectId == 34){
		return "咨询工程师" ;
	}else if(subjectId == 35){
		return "安全工程师" ;
	}else if(subjectId == 36){
		return "招标师" ;
	}else if(subjectId == 37){
		return "护师考试" ;
	}else if(subjectId == 38){
		return "护士考试" ;
	}else if(subjectId == 39){
		return "英语四六级";
	}else if(subjectId == 40){
		return "中医执业医师";
	}else if(subjectId == 41){
		return "英语雅思";
	}else if(subjectId == 42){
		return "口腔执业医师";
	}else if(subjectId == 43){
		return "公共卫生执业医师";
	}else if(subjectId == 44){
		return "执业药师";
	}else if(subjectId == 45){
		return "物业管理师";
	}else if(subjectId == 46){
		return "保险从业";
	}else if(subjectId == 47){
		return "保险代理人";
	}else if(subjectId == 48){
		return "地方二级建造师";
	}
}

function centerLocation(appId){
	var date = new Date();
	if(parseInt(appId) > 0){
		var address="";
		switch (parseInt(appId)) {
			case 1:
				address = '/cus/cuslimit!toUserCenter.action';
				break;
			case 2:
				address = '/finance/contract!getContractList.action?queryContractCondition.currentPage=1';
				break;
			case 3:
				address = '/msg/webmsg!listReceiveMsgs.action?queryUserMsgCondition.currentPage=1';
				break;
			case 4:
				address = '/cou/courselimit!toMyCourse.action';
				break;
			case 5:
				address = '/exam/qstManager!getExampaperAnalysisDTO.action';
				break;
			case 6:
				address = '/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1';
				break;
			case 7:
				address = '/cus/pblimit!getEveryOneProblemList.action?problem.pblType=1&problem.pblSolv=1&queryProblemCondition.currentPage=1&location=1';
				break;
			case 8:
				address = '/stu/calendar!getListPlanByCalendardGoto.action?checkDay=' + date.pattern("yyyy-MM-dd");
				break;
			case 9:
				address = '/dis/discussion!toDisHomepage.action';
				break;
			case 10:
				address = '/cus/cuslimit!toAssess.action';
				break;
			case 12:
				address = '/freshnews/fna!toFreshNews.action';
				break;
			case 13 :
			   address = '/exam/qstManager!toRandomExam.action';
			   break;
		}
		window.location.href = baselocation + address;
	}
}

Date.prototype.pattern=function(fmt) {        
    var o = {        
    "M+" : this.getMonth()+1, 
    "d+" : this.getDate(),
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12,   
    "H+" : this.getHours(),
    "m+" : this.getMinutes(),
    "s+" : this.getSeconds(),   
    "q+" : Math.floor((this.getMonth()+3)/3),  
    "S" : this.getMilliseconds()
    };        
    var week = {        
    "0" : "\u65e5",        
    "1" : "\u4e00",        
    "2" : "\u4e8c",        
    "3" : "\u4e09",        
    "4" : "\u56db",        
    "5" : "\u4e94",        
    "6" : "\u516d"       
    };        
    if(/(y+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
    }        
    if(/(E+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
    }        
    for(var k in o){        
        if(new RegExp("("+ k +")").test(fmt)){        
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
        }        
    }        
    return fmt;        
};

$().ready(function() {
	if (typeof(ucLeftIndex)!="undefined" && ucLeftIndex!="" && ucLeftIndex!="" ){
		var ucLeftIndexId ="uc_left_"+ucLeftIndex;
		 $("#"+ucLeftIndexId).addClass("current");//左侧默认选择样式
	}
	var username = getCookieFromServer("sedu.cookie.ukey").split(',')[3];//加载学员名称
	$("#top_username").html(username);
	loadNotification();
	
});
function loadNotification(){
     		$.ajax({  
				url : "/msg/webmsg!loadNotification.action",  
				data : { },  // 参数  
				type : "post",  
				cache : false,    
				dataType : "json",  //返回json数据 
				success : function(result) {
					var allNum = result.entity;
					$("#order").attr("style","display: none");
					$("#order").empty();
					$("#msg").attr("style","display: none");
					$("#msg").empty();
					if(allNum.orderNum!='0'){
						$("#order").removeAttr("style");
						$("#order").append("<em>"+allNum.orderNum+"</em>");		
					}
					if(allNum.msgNum!='0'){
						$("#msg").removeAttr("style");
						$("#msg").append("<em>"+allNum.msgNum+"</em>");
						$("#topmsg").html(allNum.msgNum+"+");
						$("#topmsg").show();
					}else{
						$("#topmsg").hide();
					}
				},
				error: function(){ 
//					alert("阿喔，出错喽，请联系管理员。");     
				}
			});
}
//返回顶部
$(function() {
    var $backToTopEle = $('<a class="goToTop" href="#">返回顶部</a>').appendTo("body")
        .click(function() {
            $("html, body").animate({ scrollTop: 0 }, 400);
			return false;
    }), $backToTopFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 1200)? $backToTopEle.show(): $backToTopEle.hide();
        //IE6下的定位
        if (!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 280);
        }
    };
    $(window).bind("scroll", $backToTopFun);
    $(function() { $backToTopFun(); });
});
	Date.prototype.format = function(format)    
		 {        
			var o = {        
			"M+" : this.getMonth()+1, //month        
			"d+" : this.getDate(),    //day        
			"h+" : this.getHours(),   //hour        
			"m+" : this.getMinutes(), //minute        
			"s+" : this.getSeconds(), //second        
			"q+" : Math.floor((this.getMonth()+3)/3),  //quarter        
			"S" : this.getMilliseconds() //millisecond        
			};       
			if(/(y+)/.test(format)) 
			format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));        
			for(var k in o)if(new RegExp("("+ k +")").test(format))        
			format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] :("00"+ o[k]).substr((""+ o[k]).length));  
	        return format;    
	};
	

	//头部下拉框
	$(document).ready(function() {
	    $("ul.ucTop_menu li").hover(function(){
			$(this).find("a.ucTop_menu_a").addClass("current");
			$(this).find("div.s_menu").show();
		},function(){
			$(this).find("a.ucTop_menu_a").removeClass("current");
			$(this).find("div.s_menu").hide();	
		});
	});
	//头部ie6
	$(document).ready(function() {
		$(window).bind("scroll",function(){
			var $ucTop=$("#ucTop");
			var $docscrollTop=$(document).scrollTop();
			if (!window.XMLHttpRequest) {
	            $ucTop.css("top",$docscrollTop);
	        }
		});
	});