$(document).ready(function() {
	robotShow();
});

	
	function robotShow(){
	var popupDiv = document.createElement("div");
	//给这个元素设置属性与样式
	popupDiv.setAttribute("id","robotShowDiv")
	popupDiv.style.position = "absolute";
	popupDiv.style.background = "";
	popupDiv.style.width="50px";
	popupDiv.style.height="132px";
	popupDiv.style.left="1024px";
	popupDiv.style.top="65px";
	popupDiv.style.zIndex = 99;
	//openRobot()是打开的机器人的函数， http://XXXX.com/images/kefu.gif，代表显示的图片
	popupDiv.innerHTML = "<a href='javascript:openRobot()'><image src='http://import.highso.org.cn/images/web/public/sf/xiaohai.gif' border='0px' width='192'></image></a>";
	document.body.appendChild(popupDiv);
	robotPosition();
}
//调整图标的显示位置
function robotPosition(){
var sTop = window.pageYOffset
            || document.documentElement && document.documentElement.scrollTop
            || document.body.scrollTop;
var sLeft= window.pageXOffset
            || document.documentElement && document.documentElement.scrollLeft
            || document.body.scrollLeft;
	$("#robotShowDiv").css("top", (sTop + 450) + "px");
	$("#robotShowDiv").css("left", (sLeft+document.body.clientWidth -200) + "px");
}

$(window).scroll(function(){
	robotPosition();
});

$(window).resize(function(){
	robotPosition();
});
function openRobot(faqType){
	var url = "http://sunland.uwen.com/";
	var width='1024px';
	var height='700px';

	if(faqType != "" && faqType != null && faqType != undefined){
		url = url + "&FaqType=" + faqType;
	}else{
		if( typeof(robotFaqType) != "undefined" && robotFaqType != undefined && robotFaqType != ""){
			url = url + "&FaqType=" + robotFaqType;
		}
	}

	popWin(url, width, height);
}

//弹出窗口的代码
function popWin(url, width, height){
	window.open(url, "", "menubar=no, location=no, resizable=no, scrollbars=no, status=no, width=" + width + ", height=" + height + ", left=80, top=80");

}
