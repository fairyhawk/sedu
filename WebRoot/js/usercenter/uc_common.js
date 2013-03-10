// JavaScript Document]

       /*财务中心*/
 function SwitchCaiwu(h){
	 for(i=0;i<3;i++){
		 document.getElementById("switchCD"+i).style.display="none";
		 document.getElementById("switchCD"+h).style.display="block";
		 document.getElementById("switchCT"+i).style.background="url('" + importURL + "/images/usercenter/switchT_a1.gif')";
		 document.getElementById("switchCT"+h).style.background="url('" + importURL + "/images/usercenter/switchT_a2.gif')";
	 	}
	 }


/*首页HighSo资讯*/
 function SwitchZixun(h){
	 for(i=0;i<2;i++){
		 document.getElementById("switchX"+i).style.display="none";
		 document.getElementById("switchX"+h).style.display="block";
		 document.getElementById("switchZ"+i).style.background="url('" + importURL + "/images/usercenter/switchT_a1.gif')";
		 document.getElementById("switchZ"+h).style.background="url('" + importURL + "/images/usercenter/switchT_a2.gif')";
	 	}
	 }

/*个人资料*/
 function SwitchPre(h){
	 for(i=0;i<2;i++){
		 document.getElementById("switchPD"+i).style.display="none";
		 document.getElementById("switchPD"+h).style.display="block";
		 document.getElementById("switchPT"+i).style.background="url('images/switchT_a1.gif')";
		 document.getElementById("switchPT"+h).style.background="url('images/switchT_a2.gif')";
	 	}
	 }

/*问答系统问题集合切换*/
 function WenDaJH(h){
	 for(i=0;i<3;i++){
		 document.getElementById("wenda_jhb"+i).style.display="none";
		 document.getElementById("wenda_jhb"+h).style.display="block";
		 document.getElementById("wenda_jht"+i).style.background="none";
		 document.getElementById("wenda_jht"+i).style.color="#006699";
		 document.getElementById("wenda_jht"+i).style.fontWeight="100";
		 document.getElementById("wenda_jht"+h).style.background="url('" + importURL + "/images/usercenter/title_bg2.gif')";
		 document.getElementById("wenda_jht"+h).style.color="#ffffff";
		 document.getElementById("wenda_jht"+h).style.fontWeight="600";
	 	}
	 }

/*问答系统我的问题切换*/
 function WenDaWD(h){
	 for(i=0;i<2;i++){
		 document.getElementById("wenda_wdb"+i).style.display="none";
		 document.getElementById("wenda_wdb"+h).style.display="block";
		 document.getElementById("wenda_wdt"+i).style.background="none";
		 document.getElementById("wenda_wdt"+i).style.color="#006699";
		 document.getElementById("wenda_wdt"+i).style.fontWeight="100";
		 document.getElementById("wenda_wdt"+h).style.background="url('" + importURL + "/images/usercenter/title_bg2.gif')";
		 document.getElementById("wenda_wdt"+h).style.color="#ffffff";
		 document.getElementById("wenda_wdt"+h).style.fontWeight="600";
	 	}
	 }
/*考试首页话题切换*/
 function ExamTag(h){
	 for(i=0;i<5;i++){
		 document.getElementById("ex_tabbox0"+i).style.display="none";
		 document.getElementById("ex_tabbox0"+h).style.display="block";
		 document.getElementById("ex_tag0"+i).style.background="url('images/ex_tag_01.png')";
		 document.getElementById("ex_tag0"+h).style.background="url('images/ex_tag_02.png')";
		 document.getElementById("ex_tag0"+i).style.color="#006699";
		 document.getElementById("ex_tag0"+h).style.color="#ffffff";
		 document.getElementById("ex_tag0"+i).style.fontWeight="100";
		 document.getElementById("ex_tag0"+h).style.fontWeight="600";
	 	}
	 }

/*考试首页话题切换*/
 function ExamTag(h){
 	SetCookie("kaoshi", h);
	 for(i=0;i<6;i++){
		
		 document.getElementById("ex_tag0"+i).style.background="url('" + importURL + "/images/usercenter/ex_tag_01.png')";
		 document.getElementById("ex_tag0"+h).style.background="url('" + importURL + "/images/usercenter/ex_tag_02.png')";
		 document.getElementById("ex_tag0"+i).style.color="#006699";
		 document.getElementById("ex_tag0"+h).style.color="#ffffff";
		 document.getElementById("ex_tag0"+i).style.fontWeight="100";
		 document.getElementById("ex_tag0"+h).style.fontWeight="600";
	 	}
	 }



 /*小组首页话题切换*/
 function Disht(h){
	 for(i=0;i<3;i++){
		 document.getElementById("dis_htd"+i).style.display="none";
		 document.getElementById("dis_htd"+h).style.display="block";

		 document.getElementById("dis_htt"+i).style.background="url('" + importURL + "/images/usercenter/dis_htbg_a1.gif')";
		 document.getElementById("dis_htt"+h).style.background="url('" + importURL + "/images/usercenter/dis_htbg_a2.gif')";
		 
		 document.getElementById("dis_htt"+i).style.color="#006699";
		 document.getElementById("dis_htt"+h).style.color="#006699";
		 document.getElementById("dis_htt"+i).style.fontWeight="100";
		 document.getElementById("dis_htt"+h).style.fontWeight="600";
	 	}
	 }

	/*完善昵称开始 2011 - 7- 25*/
	$(function(){
			var $timer;
			$(".close_nickname").click(function(){
					$(".nikname").slideUp("slow")
					$(".borderxu").slideUp("slow")
				})
			$(".nickname_send").click(function(){
				
				var cusName = $("#cusName").val();
				
				//验证昵称 ：只能包括中文字、英文字母、数字和下划线
				var val1 = /^[\u0391-\uFFE5\w]+$/;
                if (val1.test(cusName) == false) {
                    showErrorWin('昵称只能包括中文字、英文字母、数字和下划线','');
                }else{
                //验证昵称 通过
                
                	//alert("cusName="+cusName+"----"+cusName.length);
					//计算昵称中的中文个数
					var num = 0; 
					for(var i=0; i<cusName.length; i++){ 
					    if(cusName.charCodeAt(i) >= 127){
					    	num =  num + 1;
					    }
					} 
					
					//总长度
					var colength = cusName.length + num;
					
					//验证用户输入的昵称
					if(cusName == ""){
						showErrorWin('昵称不能为空','');
					}else if(colength > 20){
						showErrorWin('昵称最长20个英文或10个汉字','');
					}else{
						updatecusName(cusName);
						//隐藏昵称输入框
						$(".nick_input").hide();
						$(".nick_success").show()
						$timer = setInterval(function(){
										$(".nikname").slideUp("slow")
										$(".borderxu").slideUp("slow")
										},3000)
					}
                }
			})
		});
	
				 
		// 通过Ajax 修改用户昵称
		function updatecusName(cusName){
		 	$.ajax({
				url : baselocation+"/cus/cuslimit!updateCustomerName.action",
				type : "post",
				dataType : "json",
				data : {"customer.cusName" : cusName},
				success : function(result) {
					//修改成功
					if(result.returnMessage == "success") {
					}else{
					//修改失败
					}
				},
				error : function(error) {
					showErrorWin('error===updatecusName','');
			   }
			});
		 }
	/*完善昵称结束*/



//考试中心添加流程 2011-08-03
	$(function(){
		$(".ex_process_close").click(function(){
				$(".ex_process").hide();
			})
	})












