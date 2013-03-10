/*首页切换*/
 function ShowpicTag(h){
	 for(i=0;i<4;i++){
		 document.getElementById("showpic_img0"+i).style.display="none";
		 document.getElementById("showpic_img0"+h).style.display="block";
		 document.getElementById("showpic_text0"+i).style.background="url('http://import.highso.org.cn/images/web/public/jjs/show_off.gif')";
		 document.getElementById("showpic_text0"+h).style.background="url('http://import.highso.org.cn/images/web/public/jjs/show_on.gif')";
		 document.getElementById("showpic_text0"+i).style.fontWeight="100";
		 document.getElementById("showpic_text0"+h).style.fontWeight="600";
		 document.getElementById("showpic_text0"+i).style.color="#595959";
		 document.getElementById("showpic_text0"+h).style.color="#17313f";
	 	}
	 }// JavaScript Document