$(document).ready(function() {
	robotShow();
});

function robotShow(){
         var popupDiv = document.createElement("div");
         var isIE=!!window.ActiveXObject;
         var isIE6=isIE&&!window.XMLHttpRequest;
         //给这个元素设置属性与样式
         popupDiv.setAttribute("id","robotShowDiv")
         document.body.appendChild(popupDiv);
		 
         if(isIE6){
                   document.getElementById("robotShowDiv").innerHTML = "<div id='robotsubdiv' style='z-index:9;top:450px;height:94px;width: 196px;position: absolute;top:expression((document).documentElement.scrollTop+450);right: 0px;cursor:pointer;'><a href='javascript:openRobot()'><image src='http://import.highso.org.cn/images/web/public/sf/xiaohai.gif' border='0px' width='196px' height='94px'></image></a></div>";
         }else{
                   document.getElementById("robotShowDiv").innerHTML = "<div id='robotsubdiv' style='z-index:9;top:450px;height:94px;width: 196px;position:fixed;top: 450px;right: 0px;cursor:pointer;'><a href='javascript:openRobot()'><image src='http://import.highso.org.cn/images/web/public/sf/xiaohai.gif' border='0px' width='196px' height='94px'></image></a></div>";
         }

}

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