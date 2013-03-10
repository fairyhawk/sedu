<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<link rel="stylesheet" type="text/css"
	href="<%=importURL%>/styles/usercenter/uc_QA.css" />
<script type="text/javascript"
	src="<%=importURL%>/js/usercenter/uc_qa.js"></script>
<script type="text/javascript"
	src="<%=importURL%>/js/web/public/web_swfobject.js"></script>
<script type="text/javascript"
	src="<%=importURL%>/js/web/public/jQueryValidate/jquery.validate.js"></script>
<script language="javascript"
	src="<%=importURL%>/js/usercenter/uc_user_common.js"></script>
<script language="javascript"
	src="<%=importURL%>/js/usercenter/uc_user_center.js"></script>
<script language="javascript"
	src="<%=importURL%>/js/usercenter/uc_common.js"></script>
<script type="text/javascript">
		var baselocation = '<%=contextPath%>';
		var importURL = '<%=importURL%>';
	var ucLeftIndex = 7;
	
	function onClick(fzbk_id, href) {
		var div = document.getElementById(fzbk_id);
		var visible = div.style.display;
		if (visible == "none") {
			document.getElementById(fzbk_id).style.display = "block";
			//href.innerHTML = "补充问题";
		} else {
			document.getElementById(fzbk_id).style.display = "none";
			//href.innerHTML = "补充问题";
		}
	}


	//补充问题
	function toReply(ObjectForm){
   		if(document.readyState == "interactive"){
			alert("页面加载中...");
			return false;
		}
   		var oEditor = FCKeditorAPI.GetInstance("content"); 
   		var rbContent = FCKeditorAPI.GetInstance("content").GetXHTML(true);
		if($("#addProblem").valid()) {
			if(rbContent.toString().indexOf('img src="file:')!=-1){
					$("#advice_message").html("请删除外部图片！");
					oEditor.EditorDocument.body.innerHTML=""; 
					return false;
				}else if(GetLength("content")<=0) {
				$("#advice_message").html("请输入您的回答内容！");
				oEditor.Focus(); 
					return false;  
	    	}else if(GetLength("content")>=300){
	    		$("#advice_message").html("对不起，您输入的内容过多！");
				oEditor.Focus(); 
				return false;  
	    	}else{
	    		ObjectForm.submit();
	    	}
    	}
    }
	
	//回复问题
	function toReplyAn(ObjectForm){
		var oEditor = FCKeditorAPI.GetInstance("reProblem.reInfo"); 
		var rbContent = FCKeditorAPI.GetInstance("reProblem.reInfo").GetXHTML(true);
	    if(GetLength("reProblem.reInfo")<=0) {
			$("#advice_message").html("请输入您的回答内容！");
			oEditor.Focus(); 
  			return false;  
	    }else if(rbContent.toString().indexOf('img src="file:')!=-1){
  				$("#advice_message").html("请删除外部图片！");
  				oEditor.EditorDocument.body.innerHTML=""; 
  				return false;
  		}else if(GetLength("reProblem.reInfo")>=2000){
		    	$("#advice_message").html("对不起，您输入的内容过多！");
				oEditor.Focus(); 
				return false;  
		}else {
	   		ObjectForm.submit();
	    }
	 }
	
function GetLength(str){

	var oEditor = FCKeditorAPI.GetInstance(str) ;
    var oDOM = oEditor.EditorDocument ;
	
   if(document.all){
       iLength = trim(oDOM.body.innerText).length ;
   }
   else {
       var r = oDOM.createRange() ;
       r.selectNodeContents( oDOM.body ) ;
       iLength = trim(r.toString()).length ;
   }
	return iLength;
}
    
    
    
    
 function taFocus(ta) {
	if(trim($(ta).val())=="请输入问题内容……") {
		$(ta).val("");
	}
}

function taBlur(ta) {
	if(trim($(ta).val())=="") {
		$(ta).val("请输入问题内容……");
	}
}
function textCounter(filed, length) {
	if(filed.value.length>length) {
		filed.value = filed.value.substring(0, length);
	}
}
function trim(str){ //删除左右两端的空格
   return str.replace(/(^\s*)|(\s*$)/g, "");
}
   	function onUpdate(reId){
		$.ajax({
			url: "<%=contextPath%>/cus/pblimit!toUpdateMyAnswer.action",
			data : {reId : reId},
			type : "post",
			cache : false,
			dataType : "json",
			error : function(){
			alert('error');
			},
			success:onchangecallback
		});
	  }
function addProblems(){	
	var pId=document.getElementById("problem.pblId").value;
	$.ajax({
		url:"<%=contextPath%>/cus/pblimit!addProblemallback.action",
			data : {
				pblId : pId
			},
			type : "post",
			cache : false,
			dataType : "json",
			error : function() {
				alert('error');
			},
			success : addProblemallback
		});
	}
function addProblemallback(result) {
		var pbl = eval(result.returnMessage);
		if (pbl[0].pblSolv == 1) {
			alert("该问题已解决！");
		} else {
			toReplyAn(document.addProblem);
		}
	}
</script>