(function(){
	$.fn.fixtext=function(options){
	var defaults={
	ellipsis:true,
	maxLen:"15"
	}
var options = $.extend(defaults, options);
this.each(function(){
var fixobj=$(this);
var currentStr="";
var text=$(fixobj).html();
var textlen=text.length;
//判断是否保留省略号
options.maxLen=(options.ellipsis)?options.maxLen-4:options.maxLen;
for(var i=0,len=textlen;i<len; i++){
	currentStr+=text.charAt(i);
	if(getCharLength(currentStr)>options.maxLen){
	//不保留里省略号的文字内容
	text2=text.substr(0,i);
	//保留里省略号的文字内容
	if(options.ellipsis){text2=text2+"…"}
	$(fixobj).html(text2);
	return;
	}
	else{
	$(fixobj).html(currentStr);
	}
	}
//计算截取字符的长度
	function getCharLength(str){
	var charLen=0;
	for (var i=0,len=str.length; i<len; i++){
		if(str.charCodeAt(i)>255){
		charLen+=2;
		}
		else{
		charLen+=1
		}
	}
	return charLen;
	}
})
}
})(jQuery)