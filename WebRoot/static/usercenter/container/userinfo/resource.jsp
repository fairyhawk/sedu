<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_index.css?v=<%=version%>" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_pre.css?v=<%=version%>" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/popup.css?v=<%=version%>" />
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_jquery1.2.6.pack.js"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_ui.core.packed.js"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_ui.draggable.packed.js"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_CutPic.js?v=<%=version%>"></script>
<script type="text/javascript" language="JavaScript" src="<%=importURL%>/js/web/public/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=importURL%>/js/web/public/jQueryValidate/locallization/messages_cn.js"></script>
<script type="text/javascript" language="JavaScript" src="<%=importURL%>/js/web/public/jQueryValidate/lib/jquery.metadata.js"></script>

<script language="javascript" src="<%=importURL%>/js/usercenter/uc_user_info.js?v=<%=version%>"></script>

<link rel="stylesheet" type="text/css" media="screen" href="<%=importURL%>/uploadify/uploadify.css?v=<%=version%>" />
<script type="text/javascript" src="<%=importURL%>/uploadify/swfobject.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=<%=version%>"></script>
<script type="text/javascript">
			window.onload = function(){  
		        imgload();
		    }  
			var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
jQuery.ajaxSetup ({cache:false}) 
var loadn=0;
var cusid=${customer.cusId};
var iconElement1;
var imagedrag1 ;
var iit=0;
    $(document).ready(function()
    {
        $("#fileupload").uploadify({
                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
                    'script'  :'http://tp.highso.cn:8080/upload!userFace.action?cusid='+cusid,
                    'height': 45,
        			'width': 133, 
                    'queueID':'fileQueue',
                    'fileDataName':'fileupload',
                    'auto':true,
                    'multi':false,
                    'hideButton':false,
                    'buttonText':'Browse',
                    'buttonImg':'<%=importURL%>/images/usercenter/pre_browser.png',
                    'simUploadLimit' : 3,
                    'sizeLimit'      : 2048000,
                    'queueSizeLimit' : 2,
                    'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
                    'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
                    'folder' : '/upload',
                    'cancelImg':'<%=importURL%>/uploadify/cancel.png',
                    onSelect:function(){	$("#fileQueue").html("");},
                    onComplete: function (event, queueID, fileObj, response, data)
                    {    
                    	$("#fileQueue").html("");
                    	 $("#ImageDrag").attr("src",response);
                    	 $("#ImageIcon").attr("src",response);
                    	 $("#photoPath").val(response);
                    var img=new Image();
                    img.onload=function(){
                    	loadn=loadn+1;
                    	 iconElement1 =$("#ImageIcon");
                    	 imagedrag1 = $("#ImageDrag");
                    	imgload(loadn++);                    	
                        }
                   img.src=response;

                    $("#uploadId").hide();
                   $("#fileupload").hide();
                   $("#flashbutton").hide();
                 //  $("#updiv").hide();
                    },
                    onError: function(event, queueID, fileObj)
                    {
                    	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+" 上传失败</font>");
                       // alert("文件:" + fileObj.name + "上传失败");
                    	//}
                    }/* ,
                    onCancel: function(event, queueID, fileObj)
                    {
                        alert("取消了" + fileObj.name);
                    } */
                });

    });
//订单选项卡---贺立博
$(document).ready(function() {
	$("div.switch").find("div.switchD:gt(0)").hide();
	$("div.switch").find("ul.switchT a:first").addClass("current");
	$("div.switch").find("ul.switchT a").click(function() {
		$(this).addClass("current").parent("li").siblings("li").find("a")
				.removeClass("current");
		$("div.switchD:eq(" + $("ul.switchT a").index(this) + ")").show()
				.siblings("div.switchD").hide();
		return false;
	});
});

</script>
<script type="text/javascript"> 
  //必须的 
 function uploadifyUpload(){ 
    $('#fileupload').uploadifyUpload(); 
 } 
     
var params="";  //参数集

function catparams(){
	var pathnull=$("#ImageDrag").attr("src");
	if(pathnull.indexOf("man.GIF")>=0)
		{
		$("#save_message").html("请先上传图片");
		return ;
		}
var path=$("#photoPath").val(); 
var i=path.lastIndexOf("/")+1;
path=path.substr(i,path.length);
 params="photoPath="+path+"&txt_width="+$("#txt_width").val()+
 "&txt_height="+$("#txt_height").val()+"&txt_top="+$("#txt_top").val()+
 "&txt_left="+$("#txt_left").val()+"&txt_DropWidth="+$("#txt_DropWidth").val()+
 "&txt_DropHeight="+$("#txt_DropHeight").val()+"&txt_Zoom="+$("#txt_Zoom").val()+
 "&cusid="+<s:property value="customer.cusId" />;
 $.getJSON("http://tp.highso.cn:8080/upload!updatePhoto.action?"+params+"&callback=?",function(json){
	 $.ajax({
		   type: "POST",
		   url: "/cus/cuslimit!HeadPhotoDB.action",
		   data: "userId=<s:property value='customer.cusId' />&photoName="+json.src,
		   success: function(msg){
				  $("#ImageDrag").attr("src","<%=importURL%>/images/usercenter/man.GIF");
				  $("#ImageIcon").attr("src","<%=importURL%>/images/usercenter/man.GIF");
				  var img=new Image();
                  img.onload=function(){
                  	imgload(loadn++);    
                  	 $("#photoPath").val("http://import.highso.org.cn/upload/customer/photo/"+json.src);			
    				if(json.status==1)
    					{
    			          $("#success_tip").show();
    					}
                      }
                  img.src="<%=importURL%>/images/usercenter/man.GIF.png";
                  window.location.href="<%=contextPath%>/cus/cuslimit!toUserCenter.action";
		   }
		});
	
} );

 }
 </script>
<script type="text/javascript">
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ishavebuy="${ishavebuy}";
	var byear = '<s:date name="customer.birthday" format="yyyy"/>';
	var bmonth = '<s:date name="customer.birthday" format="MM"/>'; 
	var bday = '<s:date name="customer.birthday" format="dd"/>';
	<s:if test="userInfoState==\"updatePwd\"">
		$().ready(function() {
			$("#changePwd").addClass("current").parent("li").siblings("li").find("a")
				.removeClass("current");
			$("div.switchD:eq(" + $("ul.switchT a").index($('#changePwd')) + ")").show()
				.siblings("div.switchD").hide();
		});
	</s:if>
	<s:elseif test="userInfoState==\"updatePhoto\"">
		$().ready(function() {
			$("#changePhoto").addClass("current").parent("li").siblings("li").find("a")
				.removeClass("current");
			$("div.switchD:eq(" + $("ul.switchT a").index($('#changePhoto')) + ")").show()
				.siblings("div.switchD").hide();
		});
	</s:elseif>
	<s:if test="updateError!=null&&updateError!=''">
		$().ready(function() {
			//showErrorTip('<s:property value="updateError" escape="false"/>');
			showErrorWin('<s:property value="updateError" escape="false"/>');
		});
		
	</s:if>
	<s:if test="updateMessage!=null&&updateMessage!=''">
		$().ready(function() {
			//showSuccessTip('<s:property value="updateMessage" escape="false"/>');
			showSuccessWin('<s:property value="updateMessage" escape="false"/>');
		});
	</s:if>
	
	function showErrorTip(msg) {
		$("#error_tip").css("display", "block");
		$("#error_tip_content").html(msg);
	}
	
	function showSuccessTip(msg) {
		$("#success_tip").css("display", "block");
		$("#success_tip_content").html(msg);
	}
	
	function closeSuccessTip() {
		$("#success_tip").css("display", "none");
	}
	
	function closeErrorTip() {
		$("#error_tip").css("display", "none");
	}
	
</script>
