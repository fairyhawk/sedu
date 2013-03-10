var forword = false;
var forwordURL = null;
var preWin = null;
var pageTracker = null;
var gwybuy=0;
function checkFromAndAgent(){
		var webFrom = getParameter("webFrom");
		var webAgent = getParameter("webAgent");
		//d7表示给cookie指定7天的失效时间
		if(webFrom != null && webFrom.trim() != "") {
			SetCookieOutTime("webFrom", webFrom,"d7");
		}
		if(webAgent != null && webAgent.trim() != "") {
			SetCookieOutTime("webAgent", webAgent,"d7");
		}
		var ga_kw = getParameter("kw");
		var ga_Cre = getParameter("Cre");
		//d7表示给cookie指定7天的失效时间
		if(ga_kw != null && ga_kw.trim() != "") {
			SetCookieOutTime("ga_kw", ga_kw,"d7");
		}
		if(ga_Cre != null && ga_Cre.trim() != "") {
			SetCookieOutTime("ga_Cre", ga_Cre,"d7");
		}
		
	}

function getUserId() {
	var name = 'sedu.cookie.ukey';
	var mycookie = document.cookie;
	var start1 = mycookie.indexOf(name + "=");
	if (start1 == -1)
		return null;
	else {
		start = mycookie.indexOf("=", start1) + 1;
		var end = mycookie.indexOf(";", start);
		if (end == -1) {
			end = mycookie.length;
		}
		var value = decodeURIComponent(mycookie.substring(start, end));
		if (value == null) {
			return null;
		} else {
			var rlt = value.replace(/^\"|\"$/g, '');
			var str = rlt.split(',');
			return str[0];
		}
	}
}

function isLogin(baseLocation) {
	var name = 'sedu.cookie.ukey';
	var mycookie = document.cookie;
	var start1 = mycookie.indexOf(name + "=");
	if (start1 == -1) {
		var url =window.location.href;
		//SetCookie("backURL",url);
		//window.location.href = baseLocation + "static/web/login/login.html?back=true&loginMessage=operatorLimit";
		return false;
	}
	else
	{
		return true;
	}
	
}

function getCookie(cookieName) {

	var cookieString = document.cookie;
	var start = cookieString.indexOf(cookieName + '=');
	// 加上等号的原因是避免在某些 Cookie 的值里有
	// 与 cookieName 一样的字符串。
	if (start == -1) // 找不到
		return null;
	start += cookieName.length + 1;
	var end = cookieString.indexOf(';', start);
	if (end == -1){
		return unescape(cookieString.substring(start));
	}else{
	return unescape(cookieString.substring(start, end));
	}
}

function getCookieFromServer(cookieName) {

	var cookieString = document.cookie;
	var start = cookieString.indexOf(cookieName + '=');
	// 加上等号的原因是避免在某些 Cookie 的值里有
	// 与 cookieName 一样的字符串。
	if (start == -1) // 找不到
		return null;
	start += cookieName.length + 1;
	var end = cookieString.indexOf(';', start);
	if (end == -1){
		return Url.decode(cookieString.substring(start));
	}else{
	return Url.decode(cookieString.substring(start, end));
	}
}

function DeleteCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	document.cookie = name + "=" + escape(cval) + ";expires="
			+ exp.toGMTString() + ";path=/";
}
function SetCookie(name, value) {
	var Days = 2;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString() + ";path=/";
}
//自定义cookies失效时间 s指秒 h指天数 d指天数 如s40代表40秒
function SetCookieOutTime(name, value, outTime) {
	var strsec = getsec(outTime);
	var exp = new Date();
	exp.setTime(exp.getTime() + strsec * 1);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString()+";path=/";
}
// 转换cookies时间
function getsec(str) {
	var str1 = str.substring(1, str.length) * 1;
	var str2 = str.substring(0, 1);
	if (str2 == "s") {
		return str1 * 1000;
	} else if (str2 == "h") {
		return str1 * 60 * 60 * 1000;
	} else if (str2 == "d") {
		return str1 * 24 * 60 * 60 * 1000;
	}
}

var buyURL_bak="";
var gwybuy_bak=0;
// 打包功能
// 参数: 包id 、包名称、总价、 所有课程用#隔开 id, title, teacher, lessontime, reprice, price,
// gmnum,jifen
function addbao(id,name,totalPrice,tupian,allclass,baselocation,isJump)
{
	gwybuy_bak=0;
	var packageInfo = getCookie("coursesbao");
	if (gwybuy>0){
		buyURL_bak="http://highso.org.cn/static/web/column/274/index_1.shtml?gwybuy="+gwybuy;
		gwybuy_bak=gwybuy;
	}
	gwybuy=0;
	if(packageInfo!=null) {
		var packageArray = packageInfo.split("/");
		for(var i=0; i<packageArray.length; i++) {
			var packageGood = packageArray[i];
			if(packageGood != "") {
				var packageGoodInfo = packageGood.split(".");
				if(packageGoodInfo[0] == id) {
					if ( isJump){
						if (gwybuy_bak>0){
							window.open(buyURL_bak,'_blank');
						}else{
							window.open(buyURL,'_blank');
						}
						return;
					}else{
						return;
					}
					
				}
			}
		}
	}
	var goods =  id+"."+name+"."+totalPrice+"."+tupian+"."+allclass+"/";
	var  oldCoursesDan = getCookie("coursesbao");
	if(oldCoursesDan == null) {
		oldCoursesDan = '';
	}
	SetCookie("coursesbao", oldCoursesDan+goods);
	if(isJump ) {
		if (gwybuy_bak>0){
			window.open(buyURL_bak,'_blank');
		}else{
			window.open(buyURL,'_blank');
		}
	}
}

 /*
  *	fanxin 2011-09-01 依据售卖方式Id 取得subjectId --> 取得subjectId下的所有 购物车中的"相关课程" 
  */
		function  getTuiJianSellWay(sellId,disPosition){
			$.ajax({
					url : baselocation+"/cou/sellwayweb!getTuiJianSellWay.action",
					type : "post",
					data : {
						"sellWay.sellId" : sellId,
						"sellWay.disPosition" : disPosition
					},
					dataType : "json",
					cache : false,
					success : function(result) {
						//显示 
						if(result.entity == null){
							
						}else{
							 var  oldTuijianDan = getCookie("tuijianbao");
							 if(oldTuijianDan == null) {
								 oldTuijianDan = '';
							 }
							 var tuijiangoods = '';
							 
							 $.each(result.entity,function(name,value){
							 	//设置 Cookie
							 	tuijiangoods +=  value.sellId+"."+value.sellName+"."+value.sellPrice+"."+"sf."+"140,司法考试-精品课,"+value.teacher+","+value.lessonNumber+",2580,258,1,10000"+"/";
								
							 });
							 
							 //先清空 原来的Cookie
							 SetCookie("tuijianbao", "");
							 //这里是否要 加入oldTuijianDan 的内容  oldTuijianDan+tuijiangoods
							 SetCookie("tuijianbao", tuijiangoods);
						}
					},
					error : function(error) {
				   }
			});
		}





function getParameter(val){
	var uri = window.location.search; 
	var re = new RegExp("" +val+ "=([^&?]*)", "ig"); 
	return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null); 
}

	var Url = { 
		encode : function (string) { 
			return escape(this._utf8_encode(string)); 
		}, 
		decode : function (string) { 
			return this._utf8_decode(unescape(string)); 
		}, 
		_utf8_encode : function (string) { 
			string = string.replace(/\r\n/g,"\n"); 
			var utftext = ""; 
		
			for (var n = 0; n < string.length; n++) { 
			
			var c = string.charCodeAt(n); 
			
			if (c < 128) { 
			utftext += String.fromCharCode(c); 
			} 
			else if((c > 127) && (c < 2048)) { 
			utftext += String.fromCharCode((c >> 6) | 192); 
			utftext += String.fromCharCode((c & 63) | 128); 
			} 
			else { 
			utftext += String.fromCharCode((c >> 12) | 224); 
			utftext += String.fromCharCode(((c >> 6) & 63) | 128); 
			utftext += String.fromCharCode((c & 63) | 128); 
			} 
			
			} 
			
			return utftext; 
			}, 
			
			// private method for UTF-8 decoding 
			_utf8_decode : function (utftext) { 
			var string = ""; 
			var i = 0; 
			var c = c1 = c2 = 0; 
			
			while ( i < utftext.length ) { 
			
			c = utftext.charCodeAt(i); 
			
			if (c < 128) { 
			string += String.fromCharCode(c); 
			i++; 
			} 
			else if((c > 191) && (c < 224)) { 
			c2 = utftext.charCodeAt(i+1); 
			string += String.fromCharCode(((c & 31) << 6) | (c2 & 63)); 
			i += 2; 
			} 
			else { 
			c2 = utftext.charCodeAt(i+1); 
			c3 = utftext.charCodeAt(i+2); 
			string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63)); 
			i += 3; 
			} 
			
			} 
			return string; 
		} 
	}
	
	function buyQuickly() {
		var indexURL = getCookie("indexURL");
		if(indexURL==null || indexURL == '') {
			buyKJ();
			return;
		}
		var urlFlag = indexURL.substring(21, indexURL.indexOf("?"));
		if(urlFlag == '') {
			buyKJ();
			return;
		} else if(urlFlag == 'rl') {
			buyRl2();
			return;
		} else if(urlFlag == 'sf') {
			buySf();
			return;
		}
	}
	
 	function showErrorWin(errorText, win) {
 		if(win != null  && win !='') {
			$("#" + win).css("display", "none");
			preWin = win;
 		}
 		lockingWindow();
 		$("#error_win").fadeIn();
 		$("#error_win").css("top", document.documentElement.scrollTop + document.documentElement.clientHeight * 0.15);
 		$("#error_message").html(errorText);
 	}
 	
 	function showSuccessWin(successText, win) {
 		if(win != null  && win !='') {
			$("#" + win).css("display", "none");
			preWin = win;
 		}
 		lockingWindow();
 		$("#success_win").fadeIn();
 		$("#success_win").css("top", document.documentElement.scrollTop + document.documentElement.clientHeight * 0.15);
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
	
	function toIndexPage(parms){
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
	
	function getSubjectIndexFlag(subjectId) {
		if(subjectId == 1) {
			return "rl";
		} else if(subjectId == 2) {
			return "xl";
		} else if(subjectId == 3) {
			return "kjz";
		} else if(subjectId == 5) {
			return "sf";
		} else if(subjectId == 7) {
			return "cpa";
		} else if(subjectId == 8) {
			return "zq";
		} else if(subjectId == 9){
			return "jz" ;
		}else if(subjectId == 10){
			return "gk" ;
		}else if(subjectId == 11){
			return "gwy" ;
		}else if(subjectId == 12){
			return "jjs" ;
		}else if(subjectId == 13){
			return "ky" ;
		}else if(subjectId == 14){
			return "zlkjs" ;
		}else if(subjectId == 15){
			return "kjs" ;
		}else if(subjectId == 16){
			return "jz02" ;
		}else if(subjectId == 17){
				return "jl" ;
		}else if(subjectId == 18){
				return "zjs" ;
		}else if(subjectId == 19){
				return "gct" ;
		}else if(subjectId == 20){
				return "zk" ;
		}else if(subjectId == 21){
				return "ys" ;
		}else if(subjectId == 22){
				return "fl" ;
		}else if(subjectId == 23){
				return "ck" ;
		}else if(subjectId == 24){
				return "zs" ;
		}else if(subjectId == 25){
				return "bg" ;
		}else if(subjectId == 26){
			return "gwy" ;
		}else if(subjectId == 27){
			return "zcyy" ;
		}else if(subjectId == 28){
			return "qh" ;
		}	
			return null;
		}
		
	function getSubjectIdByIndexPage() {
		var indexURL = getCookie("indexURL");
                indexURL=indexURL.replace("www.","");
		if(indexURL==null || indexURL == '') {
			return 3;
		}
		
		var temp = indexURL.substring(7);
		var flagIndex = temp.indexOf("highso")==-1?2:1;
		var strs = temp.split('/');
		var urlFlag = '';
		if(strs[flagIndex] != null || strs[flagIndex] != '') {
			if(strs[flagIndex].indexOf('?')>-1) {
				urlFlag = strs[flagIndex].substring(0, strs[flagIndex].indexOf("?"));
			} else {
				urlFlag = strs[flagIndex];
			}
		}
		if(urlFlag == null || urlFlag == '' || urlFlag == 'kjz') {
			return 3;
		} else if (urlFlag == 'rl') {
			return 1;
		} else if (urlFlag == 'sf') {
			return 5;
		} else if (urlFlag == 'cpa') {
			return 7;
		} else if (urlFlag == 'zq') {
			return 8;
		} else if (urlFlag == 'xl') {
			return 2;
		} else if (urlFlag == 'jz') {
			return 9;
		}else if (urlFlag == 'gk') {
			return 10;
		}else if (urlFlag == 'gwy') {
			return 11;
		}else if (urlFlag == 'jjs') {
			return 12;
		}else if (urlFlag == 'ky') {
			return 13;
		}else if(urlFlag == 'zlkjs'){
			return 14 ;
		}else if(urlFlag == 'kjs'){
			return 15 ;
		}else if(urlFlag == 'jz01'){
			return 9 ;
		}else if(urlFlag == 'jz02'){
			return 16 ;
		}else if(urlFlag == 'sccjkjzc'){
			return 14 ;
		}else if(urlFlag == 'jl'){
			return 17 ;
		}else if(urlFlag == 'zjs'){
			return 18 ;
		}else if(urlFlag == 'gct'){
			return 19 ;
		}else if(urlFlag == 'zk'){
			return 20 ;
		}else if(urlFlag == 'ys'){
			return 21 ;
		}else if(urlFlag == 'fl'){
			return 22 ;
		}else if(urlFlag == 'ck'){
			return 23 ;
		}else if(urlFlag == 'zs'){
			return 24 ;
		}else if(urlFlag == 'bg'){
			return 25 ;
		}else if(urlFlag == 'zcyy'){
			return 27 ;
		}else if(urlFlag == 'qh'){
			return 28 ;
		}	
		if (indexURL.indexOf('_gwy')>0){
			return 26 ;
		}
			return 3;
		}
	
 	
 	function login(i) {
// 		if(i == 3 && typeof(pageTracker) !="undefined" && pageTracker != null) {
// 			pageTracker._trackPageview('/buy/step_zhuce.html');
// 		}
 		var params = processLoginParams(i);
		if(!params) {
			return;
		}
		
 		processRemeberMe(i);
 		
		$.ajax({
			url : baselocation + "/cus/cusweb!quickLogin.action",
			data : params,
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					//艾德思奇统计：登陆。
					//adSageLogin(getUserId());
					if(typeof(forword) !="undefined" && forword) {
						window.location.href = forwordURL;
						return;
					}
					if(i == 3) {
						if(typeof(_gaq)!="undefined"&&_gaq!=null){
								_gaq.push(['_trackPageview', '/virtual/step_enter.html']);//谷歌统计  添加虚拟页面游览量统计
								_gaq.push(['_trackEvent', 'login_account_3', '3','account_3']);//谷歌统计  添加事件追踪统计
							}
						$('#reg_log_div').css("display", "none");
						if(checkCoursesBought()){
							showAddressListInbuyPage(0);
							$('#buy_step2').css("display", "block");
						}
						return;
					}
					showLoginedMessage(result.entity.userName);
					
					if(!$("#remeber_me_" + i).attr("checked")) {
						$("#login_email_2").val("");
						$("#login_pwd_2").val("");
					}
					if(i == 2) {
						closeRegLogDiv();
					}
					//window.open(baselocation + "/cus/cuslimit!toUserCenter.action");
					window.open(buyURL);
				} else if(result.returnMessage == "err.randCode"){
					showErrorWin("验证码错误。", "");
				} else if (result.returnMessage == "freezed") {
						showErrorWin("对不起，您的帐号出现异常登录行为，已被冻结。客服电话：4007-062-061", "");
				} else {
					if(i == 3) {
						showErrorWin("用户名和密码不匹配。", "");
					} else {
						showErrorWin("用户名和密码不匹配。", "login_div");
					}
					//$("#login_email_" + i).val("");
					$("#login_pwd_" + i).val("");
				}
			},
			error : function(error) {
				alert('error');
			}
		});
 	}
 	
 	function processRemeberMe(i) {
 		if($("#remeber_me_" + i).attr("checked")) {
			SetCookie("remeberMe", "true," + $("#login_email_" + i).val() + "," + $("#login_pwd_" + i).val());
		} else {
			DeleteCookie("remeberMe");
		}
 	}
 	
 	function processLoginParams(i) {
 		var params = $("#login_form_" + i + " input").serialize();
		if(params == null && params == '') {
			if(i == 3) {
				showErrorWin("用户名和密码不匹配。", "");
			} else {
				showErrorWin("用户名和密码不匹配。", "login_div");
			}
			return false;
		}
		var paramsArray = params.split("&");
		for(var j=0; j<paramsArray.length; j++) {
			var entry = paramsArray[j].split("=");
			if(entry != null && entry.length > 1 && ((entry[0] == "customer.email" && entry[1] == '') || (entry[0] == "customer.cusPwd" && entry[1] == ''))) {
				if(i == 3) {
					showErrorWin("用户名和密码不匹配。", "");
				} else {
					showErrorWin("用户名和密码不匹配。", "login_div");
				}
				return false;
			}
		}
		return params;
 	}
 	
 	function quickLogKeyUp(event, index) {
 		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;   
        if (keyCode == 13) {   
        	login(index);
        }   
 	}
 	
 	function  regEmailChg(event) {
	    var e = event||window.event;
	    var keyCode = e.keyCode||e.which;
	    if (keyCode >= 65 && keyCode <= 90){
	    	return false;
	    }
 	}
 	
	//黑底效果
	function lockingWindow(){ 
		document.getElementById("web_top_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("web_top_black").style.height = document.documentElement.scrollHeight + "px";
		$("#web_top_black").fadeIn();
		//document.getElementById("web_top_black").style.display = "block"; 
	}
 	
 	function  iptLowerCase(event) {
	    var e = event||window.event;
	    var keyCode = e.keyCode||e.which;
	    if (keyCode >= 65 && keyCode <= 90){
	    	e.keyCode = keyCode + 32;
	    }
	    if (keyCode==32){
	    	e.keyCode=0;
			e.returnvalue=false;
	    }
 	}
 	
 	var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
	var  clickcolor='#10b2f6';

	function  changeto(){
		source=event.srcElement;
		if(source.tagName=="TR"||source.tagName=="TABLE")
			return;
		while(source.tagName!="TD")
		source=source.parentElement;
		source=source.parentElement;
		cs  =  source.children;
		if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
	}

	function  changeback(){
		if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
			return
		if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}

	function  clickto(){
		source=event.srcElement;
		if  (source.tagName=="TR"||source.tagName=="TABLE")
			return;
		while(source.tagName!="TD")
			source=source.parentElement;
			source=source.parentElement;
			cs  =  source.children;

		if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
		else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
	
	String.prototype.trim=function(){
　　    return this.replace(/(^\s*)|(\s*$)/g, "");
　　 }
　　 String.prototype.ltrim=function(){
　　    return this.replace(/(^\s*)/g,"");
　　 }
　　 String.prototype.rtrim=function(){
　　    return this.replace(/(\s*$)/g,"");
　　 }
		
	function BuyNowSign(pid,Sign){ 
		gwybuy=Sign;
		BuyNow(pid);
	}
	//注意：pid 是字符串类型 传入多个Pid时，pid之间一逗号“’”隔开如："12,23,43"
	function BuyNow(pid){ 
		deleteordertmp();
		
		var id = pid;
		
		/*
		//--用户登入判断--start--
		if(!(isLogin(baselocation + "/"))) {
 			showRegisterDiv();
 			BuyAjax(id);
 			
 		}else{
		 	var p = window.open('about:blank');
		 	BuyAjax(id);
		 	if (gwybuy>0){
				p.location="http://highso.org.cn/static/web/column/274/index_1.shtml?gwybuy="+gwybuy;
				gwybuy=0;
			}else{
				p.location=buyURL;
			}
		} 
		//--用户登入判断--end--
		*/
		
		//--不做用户登入判断--start--
		 var p = window.open('about:blank');
		 BuyAjax(id);
		 
		 if (gwybuy>0){
			p.location="http://highso.org.cn/static/web/column/274/index_1.shtml?gwybuy="+gwybuy;
			gwybuy=0;
		}else{
			p.location=buyURL;
		}
		//--不做用户登入判断--end--
			
			
		if(typeof(_gaq)!="undefined"&&_gaq!=null){
			_gaq.push(['_trackEvent', 'shopping_1_Transaction', 'Order','1_'+pid]);//谷歌统计   
		}	
	}
	
		function BuyAjax(pid){
	
		$.ajax({  
			url : baselocation+"/cou/sellwayweb!BuyNow.action",  
			data : {sellIds : pid},  // 参数  
			type : "post",  
			cache : false,   
			async : false,   
			dataType : "json",  //返回json数据 
			error: function(){ 
				alert('error');      
			}, 
			success: function (result){
				//显示 
				if(result.entity == null){
					
				}else{
					var leng = result.entity.length;
					var i = 1;
					$.each(result.entity,function(name,value){
					 	//设置 Cookie
						if( i < leng){
							i = i+1;
							var s="23,司法考试-重点班,"+value.teacher+","+value.lessonNumber+",990,198,1,10000";
							addbaonew(value.sellId,value.sellName,value.sellPrice,value.subjectKey,s,baselocation,false);
						}else{
							var s="23,司法考试-重点班,"+value.teacher+","+value.lessonNumber+",990,198,1,10000";
							addbaonew(value.sellId,value.sellName,value.sellPrice,value.subjectKey,s,baselocation,false);
						}
					 });
				}
				
			}
		});
	
	}
	
	function addbaonew(id, name, totalPrice, tupian, allclass, baselocation, isJump) {
		var packageInfo = getCookie("coursesbao");
		if (packageInfo != null) {
			var packageArray = packageInfo.split("/");
			for (var i = 0; i < packageArray.length; i++) {
				var packageGood = packageArray[i];
				if (packageGood != "") {
					var packageGoodInfo = packageGood.split(".");
					if (packageGoodInfo[0] == id) {
							return;
					}
				}
			}
		}
		var goods = id + "." + name + "." + totalPrice + "." + tupian + "."
				+ allclass + "/";
		var oldCoursesDan = getCookie("coursesbao");
		if (oldCoursesDan == null) {
			oldCoursesDan = '';
		}
		SetCookie("coursesbao", oldCoursesDan + goods);
	}		

			/**删除订单信息**/
 	function deleteordertmp() {
 		SetCookie("coursesbao", "");
 	}
 	
 	function login_sy(i) {
 		  baselocation="";
// 		if(i == 3 && typeof(pageTracker) !="undefined" && pageTracker != null) {
// 			pageTracker._trackPageview('/buy/step_zhuce.html');
// 		}
 		var params = processLoginParams(i);
		if(!params) {
			return;
		}
		
 		processRemeberMe(i);
 		
		$.ajax({
			url : baselocation + "/cus/cusweb!quickLogin.action",
			data : params,
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					//艾德思奇统计：登陆。
					//adSageLogin(getUserId());
					if(typeof(forword) !="undefined" && forword) {
						window.location.href = forwordURL;
						return;
					}
					if(i == 3) {
						if(typeof(_gaq)!="undefined"&&_gaq!=null){
								_gaq.push(['_trackPageview', '/virtual/step_enter.html']);//谷歌统计  添加虚拟页面游览量统计
								_gaq.push(['_trackEvent', 'login_account_3', '3','account_3']);//谷歌统计  添加事件追踪统计
							}
						$('#reg_log_div').css("display", "none");
						if(checkCoursesBought()){
							showAddressListInbuyPage(0);
							$('#buy_step2').css("display", "block");
						}
						return;
					}
					showLoginedMessage(result.entity.userName);
					
					if(!$("#remeber_me_" + i).attr("checked")) {
						$("#login_email_2").val("");
						$("#login_pwd_2").val("");
					}
					if(i == 2) {
						closeRegLogDiv();
					}
					window.open(baselocation + "/cus/cuslimit!toUserCenter.action");
					//window.open(buyURL);
				} else if(result.returnMessage == "err.randCode"){
					showErrorWin("验证码错误。", "");
				} else if (result.returnMessage == "freezed") {
						showErrorWin("对不起，您的帐号出现异常登录行为，已被冻结。客服电话：4007-062-061", "");
				} else {
					if(i == 3) {
						showErrorWin("用户名和密码不匹配。", "");
					} else {
						showErrorWin("用户名和密码不匹配。", "login_div");
					}
					//$("#login_email_" + i).val("");
					$("#login_pwd_" + i).val("");
				}
			},
			error : function(error) {
				alert('error');
			}
		});
 	}