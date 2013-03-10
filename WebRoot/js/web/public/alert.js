 function msg(info){
 var p=document.createElement("DIV");
 if (!info) var info='';
 p.id="p";
 p.style.position="absolute";
 p.style.width=document.body.scrollWidth;
 p.style.zIndex='998';
 p.style.top='0px';
  p.style.left='0%';
 p.style.backgroundColor="gray";
 p.style.opacity='0.5';
 p.style.filter="alpha(opacity=80)";
 document.body.appendChild(p);
 var p1=document.createElement("DIV");
 var top=parseInt(parseInt(document.body.scrollHeight)*0.25)+document.body.scrollTop;
 p1.style.position="absolute";
 p1.style.width="200px";
 p1.id="p1";
 var left=Math.ceil(((document.body.scrollWidth)-parseInt(p1.style.width.replace('px','')))/2)+document.body.scrollLeft;
 p1.style.height="100px";
 p1.style.zIndex='999';
 p1.style.top=top+'px';
  p1.style.left=left+'px';
 p1.style.border="0px solid #66CCFF";
 var html="";
  html+="<center>"
  html+="<div class='p3' style='height:1px;overflow:hidden;background:#66CCFF;width:200px;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF;'></div>"
  html+="<div class='p2' style='height:1px;overflow:hidden;background:#66CCFF;width:200px;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF;'></div>"
  html+="<div class='p2' style='height:1px;overflow:hidden;background:#66CCFF;width:200px;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF;'></div>"
  html+="<div class='p1' style='height:20px;overflow:hidden;background:#66CCFF;width:200px;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF;color:#fff;font-size:9pt;font-weight:bold;text-align:left;'> ⊙ 提示</div>"
 html+="<div id='c' style='height:80px;width:200px;background-color:#fff;overflow:hidden;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF;padding-top:40px;font-size:9pt;'>"+info+"<br><br><br>[ <a href='javascript:this.cancle()'>关闭</a> ]</div>"
  html+="<div class='p1' style='height:1px;overflow:hidden;background:#FEEACB;width:200px;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF;'></div>"
  html+="<div class='p2' style='height:1px;overflow:hidden;background:#FEEACB;width:200px;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF;'></div>"
  html+="<div class='p3' style='height:1px;overflow:hidden;background:#66CCFF;width:200px;border-left:1px solid #66CCFF;border-right:1px solid #66CCFF'></div>"
  html+="</center>"
 document.body.appendChild(p1);
 p1.innerHTML=html;
 var arr=document.getElementsByTagName("select");
 var i=0;
 while(i<arr.length){
   arr[i].style.visibility='hidden';
   i++;
 }
 this.cancle=function(){
  document.body.removeChild(document.getElementById('p'));
  document.body.removeChild(document.getElementById('p1'));
  var arr=document.getElementsByTagName("select");
   var i=0;
   while(i<arr.length){
   arr[i].style.visibility='visible';
   i++;
   }
 }
}