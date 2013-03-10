$(function(){
	$("#activateCardCourse").click(function(){
		$("#activateCardCourseDiv").show();
		$("#cardTabCon02").hide();
		$("#activateCardCourse").hide();
	});
	$("#activateNow").click(function(){
		var msg=checkData();
		if(msg!=''){
			alert(msg);
			return ;
		}
		$.ajax({
			url : baselocation + "/card/cardMain!activateCardCourse.action",
			data : {
				"cardCourse.cardCourseCode" : $.trim($("#cardCourseCode").val()),
				"cardCourse.cardCoursePassword" : $.trim($("#cardCoursePassword").val())
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				var msg="";
				if(result.entity=='passwordError'){
					msg="卡号或密码错误，请确认，谢谢！";
				}else if(result.entity=='dontActivate'){
					msg="该卡未被激活，请联系highso客服进行处理！谢谢";
				}else if(result.entity=='alreadyUse'){
					msg="该卡已被使用，不能再进行激活，请确认！谢谢";
				}else if(result.entity=='outDate'){
					msg="该卡已过期，不能进行激活，请确认！谢谢";
				}else if(result.entity=='alreadyAbolish'){
					msg="该卡已作废，不能进行激活，请确认！谢谢";
				}else{
					$("#activateCardCourseSuccessDiv").show();
					$("#activateInfoDiv").html("您的课程卡已激活成功；<br />此课程卡包含课程："+result.entity);
					$("#activateCardCourseDiv").hide();
				}
				if(msg!=''){
					$("#activateCardCourseFalseDiv").show();
					$("#activateFalseInfoDiv").html(msg);
					$("#activateCardCourseDiv").hide();
				}
			},
			error : function(error) {
				$("#activateCardCourseFalseDiv").show();
				$("#activateFalseInfoDiv").html("您的课程卡激活发生异常，请及时联系客服人员进行处理，谢谢！");
				$("#activateCardCourseDiv").hide();
			}
		});
	});
	$("#comeback").click(function(){
		$("#activateCardCourseDiv").show();
		$("#activateCardCourseFalseDiv").hide();
	});
});
function checkData(){
	var message="";
	if($("#cardCourseCode").val()==''){
		message+="卡号不能为空！\n";
	}
	if($("#cardCoursePassword").val()==''){
		message+="密码不能为空！\n";
	}
	return message;
}