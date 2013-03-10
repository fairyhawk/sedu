//问答输入框焦点
$(document).ready(function() {
	$("div.answer_text").find("textarea").focus(function(){
	$("p.answer_textHolder").hide();
	$("div.QA_word_enter").html("请文明发言，还可以输入<em class='QA_surplus_word'>300</em>个字").css("color","#909191");
	var $maxtext=300;
    var curr=$("textarea.textareaFocus").val().length;
	var $surplusWord=$("em.QA_surplus_word");
		$surplusWord.text(curr);
		if($maxtext-curr>0){
		$surplusWord.text($maxtext-curr).css("color","#FC8019");
		}else{
			$surplusWord.text(0).css("color","#F00");
		}
  }).bind("blur",function(){
	 if($(this).val()=='')
		$("p.answer_textHolder").show();
	});
});
//输入字数提示
$(document).ready(function() {
	$("textarea.textareaFocus").keyup(function(){
	var $maxtext=300;
    var curr=$(this).val().length;
	var $surplusWord=$("em.QA_surplus_word");
		$surplusWord.text(curr);
		if($maxtext-curr>0){
		$surplusWord.text($maxtext-curr).css("color","#999");
		}else{
			$surplusWord.text(0).css("color","#F00");
		};
	});
});

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

$(function(){	
		//问题类型tab样式(所有解决问题，未解决问题)
		var webFrom = getParameter("location");
		if(webFrom==1){
		$("#location1").addClass("current");
		$("#location0").removeClass("current");
		}else if(webFrom==0){
		$("#location0").addClass("current");
		$("#location1").removeClass("current");
		}
		
		//我的问答tab样式(我的问题，我的回答)
		var myParam0 = getParameter("param_0");
		if(myParam0==1){
		$("#param1").addClass("current");
		$("#param0").removeClass("current");
		}else if(myParam0==0){
		$("#param0").addClass("current");
		$("#param1").removeClass("current");
		}
		//我的问答（是否解决）
		var myparamyesno = getParameter("param_yesno");
		if(myparamyesno==1){
		$("#param11").addClass("current");
		$("#param10").removeClass("current");
		}else if(myparamyesno==0){
		$("#param10").addClass("current");
		$("#param11").removeClass("current");
		}
		
		//选择问题类型的样式
		var pro_type = getParameter("problem.pblType");
		 $("#kaoshi").removeClass("current");
		 $("#kecheng").removeClass("current");
		 $("#shipin").removeClass("current");
		 $("#jingyi").removeClass("current");
		 var text="#考试问题#";
		if(pro_type==1){
			$("#kaoshi").addClass("current");
			 text = "#"+$("#kaoshi").find(".menuTab_name").text()+"#";
		}else if(pro_type==2){			
			$("#kecheng").addClass("current");
			text = "#"+$("#kecheng").find(".menuTab_name").text()+"#";
		}else if(pro_type==3){
			$("#shipin").addClass("current");
			text = "#"+$("#shipin").find(".menuTab_name").text()+"#";
		}else if(pro_type==4){
			$("#jingyi").addClass("current");
			text = "#"+$("#jingyi").find(".menuTab_name").text()+"#";
		}else{
			$("#kaoshi").addClass("current");
			text = "#"+$("#kaoshi").find(".menuTab_name").text()+"#";
		}
	 	 $(".answer_text .answer_textHolder").text(text);
		});
		
	//选择问答类型(所有问题列表)
	function changeProType(pro_type,id){
	 $("#problem_type").val(pro_type);
		 
		 $("#kaoshi").removeClass("current");
		 $("#kecheng").removeClass("current");
		 $("#shipin").removeClass("current");
		 $("#jingyi").removeClass("current");
		 $(id).addClass("current");
		 text = "#"+$(id).find(".menuTab_name").text()+"#";
		 $(".answer_text .answer_textHolder").text(text);
	 	var webFrom = getParameter("location");
		if(webFrom==1){
		$("#location1").addClass("current");
		$("#location0").removeClass("current");
		}else if(webFrom==0){
		$("#location0").addClass("current");
		$("#location1").removeClass("current");
		}
		if(webFrom !=1 && webFrom!=0){
			webFrom=1;
		}
	 var pro_type = document.getElementById("problem_type").value;
	 var pro_solv = document.getElementById("problem_solv").value;
	 var officeid = document.getElementById("officeId").value;
	 if(officeid !=null && officeid!="" && officeid !=0){
	 	window.location.href = baselocation+"/cus/pblimit!getEveryOneProblemList.action?problem.pblType="+pro_type+"&problem.pblSolv="+pro_solv+"&queryProblemCondition.currentPage=1&location="+webFrom+"&queryProblemCondition.officialCusId="+officeid;
	 }else{
	 	window.location.href = baselocation+"/cus/pblimit!getEveryOneProblemList.action?problem.pblType="+pro_type+"&problem.pblSolv="+pro_solv+"&queryProblemCondition.currentPage=1&location="+webFrom;
	 }
	}
	
	//首页交替查询 已解决未解决问题
	function changeProSolv(pblSolv,location){
	 var pro_type = document.getElementById("problem_type").value;
	 window.location.href=baselocation+"/cus/pblimit!getEveryOneProblemList.action?problem.pblType="+pro_type+"&problem.pblSolv="+pblSolv+"&queryProblemCondition.currentPage=1&location="+location;
	 }
	 
	 //我的问题和我的回答（问题类型样式切换）
	 function changeProTypeMyProblem(pro_type,id){
	 $("#problem_type").val(pro_type);
	 $("#kaoshi").removeClass("current");
	 $("#kecheng").removeClass("current");
	 $("#shipin").removeClass("current");
	 $("#jingyi").removeClass("current");
	 $(id).addClass("current");
	 var text = "#"+$(id).find(".menuTab_name").text()+"#";
	 $(".answer_text .answer_textHolder").text(text);
	  }
	
	//保存问题
	function saveProblem(subid) {
		var problemcontent = document.getElementById("problem.pblContent").value;
		var problem_type = $("#problem_type").val();
		    var url= baselocation+"/cus/pblimit!addProblemNewList.action";
		    $.ajax({
			url: url,
			data : {
			subject_id : subid,
			pro_content : problemcontent,
			problem_type:problem_type
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
				//alert('添加问题失败');
				$("div.QA_word_enter").text("新增提问失败").css("color","#FC8019");
			},
			success:function(){
			//alert('问题新增成功');
			$("div.QA_word_enter").text("新增提问成功").css("color","#FC8019");
			document.getElementById("problem.pblContent").value="";
			window.location.reload();
			}
			});
			
	}
	
	function GetLength(str){
	   iLength = trim(str).length ;
		return iLength;
	}
	
	function trim(str){ //删除左右两端的空格
　　     return str.replace(/(^\s*)|(\s*$)/g, "");
　　 }
	
	//显示项目列表
function	showSubject(){
   var subsize = $("#sublistSize").val();
   var onlysubid = $("#onlySubId").val();
     		var problemcontent = document.getElementById("problem.pblContent").value;
			if(GetLength(problemcontent)<=0){
				$("div.QA_word_enter").text("请输入问题内容...").css("color","#FC8019");
  			}else if(GetLength(problemcontent)>=300){
		    	$("div.QA_word_enter").text("对不起，您输入的内容过多...").css("color","#FC8019");
		    }else{
			    if(subsize==1 && onlysubid!=null && onlysubid!=""){			    
			      saveProblem(onlysubid);
			    }else{			    
			    document.getElementById("subjectDiv").style.display = 'block';			    
			    }
		    }
}