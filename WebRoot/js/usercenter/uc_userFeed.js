				
		
	/*用户反馈js开始*/
	
	/*用户反馈----给偶数行加背景色*/
	$(document).ready(function(){
		$("ul.userFeedback_con li.userFeedback_cons:odd").addClass("current");
		//给class为stripe的表格的偶数行添加class值为alt
	});
	/*用户反馈----打开提交内容*/
	$(function(){
		$(".userClick01").click(function(){
			//$(".uF_content").toggle(200);
			window.location.href="/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&from=1";
		});
	});
	/*用户反馈----打开回复*/
	$(function(){
		$(".uF_answer2_info").click(function(){
			$(this).next(".uF_ans_info").toggle(200);
		});
	});
	
	//用户反馈----输入字数提示
	$(document).ready(function() {
		$(".uF_con_area").keyup(function(){
		var $maxtext=140;
	    var curr=$(this).val().length;
		$("#complete_word").text(curr);
		if($maxtext-curr>0){
		$("#surplus_word").text($maxtext-curr);
		$("#surplus_word").css("color","#999");
		}else{
		$("#surplus_word").text(0);
		$("#surplus_word").css("color","#F00");
			};
		if(curr>$maxtext){
			$("#complete_word").css("color","#F00");
			}else{$("#complete_word").css("color","#999");}
		});
	});
	function sucessAnswer(){
			if(ishavebuy=='true'){
				var content=$("textarea[name=cmtInfo]").val();
				var len=content.trim().length;
				if(len==0||content==""){
					showErrorWin("请填写要提问的内容！","error_win");
					return;
				}else if(len>140){
					showErrorWin("请将内容控制在140字以内","error_win");
					return;
				} 

			$.ajax({
				url : baselocation+"/cms/cmtlimit!saveAdvice4ajax.action",
				type : "post",
				data : {
					"comment.cmtInfo" : $("textarea[name=cmtInfo]").val(),
					"needConfirmCode" : false
					},
				dataType : "json",
				success : function(result) {
					if(result.returnMessage == "success") {
					$("textarea[name=cmtInfo]").val("");
					$(".uF_content").fadeOut();
					var entity=result.entity;
					var s='';
					s+='<li class="userFeedback_cons">';
					s+='<div><span style="font-weight:bold">';
					s+=entity.visitorName+'&nbsp;&nbsp;</span>'+new Date().format('yyyy-MM-dd hh:mm:ss'); 
					s+='<img src="'+importURL +'/images/usercenter/user_icon02.png"/>';
					s+='<div>';
					s+='<p class="uF_question">'+entity.cmtInfo+'</p>';
					s+='<p class="uF_answer">&nbsp;</p>';
					s+='<div class="uF_answer2">';
					s+='<a class="uF_answer2_info">';
					s+='回复（';
					var index=$('#indexMax').val();
					$('#indexMax').val(parseInt(index)+1);
					s+='<span id="count'+index+'">0</span>';
					s+='）';
					s+='</a>';
					s+='<div class="uF_ans_info">';
					s+='<div class="uF_ans_info_head">';
					s+='<textarea id="textarea'+index+'" class="uF_con_area2" rows="" cols="" name=""></textarea>';
					s+='<a href="javascript:reply('+"'"+index+"','"+entity.cmtId+"'"+')">回复</a>';
					s+='</div>';
					s+='<ul id="ul'+index+'"> </ul>';
					s+='</div>';
					s+='</div>';
					s+='</li>';
					$('#replyList').html(s+$('#replyList').html());
					$(".uF_answer2_info").click(function(){
						$(this).next(".uF_ans_info").toggle(200);
					});
					showSuccessWin("感谢您，提交成功。<br>您的意见会让HighSo做得更好","success_win");
					} else if(result.returnMessage == "adviceDangerWord") {
						
					} 
					}
				});
			}else{
				showErrorWin("只有购买课程后才能提问","error_win");
			}
		}
	
		function reply(id,cmtId){
			if(ishavebuy=='true'){
				var info=$('#textarea'+id).val();
				if(info.indexOf("回复@")!=-1){
					info=info.substring(info.indexOf(":")+1);
				}
				 if(info.trim()==''){
					showErrorWin("请填写要回复的内容","error_win");
					return;
				}
				if(info.length>140){
					showErrorWin("请将内容控制在140字以内","error_win");
					return;
				}
				info=$('#textarea'+id).val();
				var html=$('#ul'+id).html();
				var url=baselocation+"/cms/cmtlimit!replyAdvice.action";
				$.post(url,{'comment.sourceId':cmtId,'comment.cmtInfo':info,"needConfirmCode" : false},function(json){
					html+='<li><p>'+json.entity.visitorName+'：'+json.entity.cmtInfo+'（'+json.entity.createTime+'）</p><div><a href="javaScript:toReply('+"'"+id+"','"+json.entity.visitorName+"'"+')">回复</a></div></li>';
					$('#ul'+id).html(html);
					$('#textarea'+id).val('');
					$('#count'+id).text(parseInt($('#count'+id).text())+1);
				},'json');
				$('#'+id).focus();
			}else showErrorWin("只有购买课程后才能回复","error_win");
		}
		
		function toReply(id,visitorName){
			if(ishavebuy=='true')
				$('#textarea'+id).val('回复@'+visitorName+':');
			else showErrorWin("只有购买课程后才能回复","error_win");
		}				
		
		function moreAdvice(){
			window.location.href=baselocation+"/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1";
		}
		function commentView(){
			$.ajax({
				url : baselocation+"/cms/cmtlimit!adviceList.action",
				type : "post",
				dataType : "json",
				success : function(result) {
					if(result.jumpType == "true" || result.jumpType == true) {
						var entity=result.entity;
						var s='';
						for(var j=0;j<entity.length;j++){ 
							s+='<li class="userFeedback_cons">';
							 s+='<div><span style="font-weight:bold">';
							 s+='<a href="/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&queryCommentCondition.cusId='+entity[j].checkmanId+'">' ;
							s+=entity[j].visitorName+'  </a><a href="/cms/cmtweb!moreAdvice.action?queryCommentCondition.currentPage=1&queryCommentCondition.subjectId='+entity[j].subjectId+'">'+entity[j].subjectName+'</a></span>'+entity[j].createTime;
							 s+='<img src="'+importURL +'/images/usercenter/user_icon02.png"/>';
							 s+='<div>';
							 s+='<p class="uF_question">'+entity[j].cmtInfo+'</p>';
							 s+='<p class="uF_answer">';
							 if(entity[j].mgr_info!=''&&entity[j].mgr_info!=null)s+='回复： '+entity[j].mgr_info+'('+entity[j].mgr_creatime+') ';
							 s+='</p>';
							 s+='<div class="uF_answer2">';
							 s+='<a class="uF_answer2_info">';
							 s+='回复（';
							 var index=$('#indexMax').val();
							 $('#indexMax').val(parseInt(index)+1);
							 s+='<span id="count'+index+'">'+entity[j].replyList.length+'</span>';
							 s+='）';
							 s+='</a>';
							 s+='<div class="uF_ans_info">';
							 s+='<div class="uF_ans_info_head">';
							 s+='<textarea id="textarea'+index+'" class="uF_con_area2" rows="" cols="" name=""></textarea>';
							 s+='<a href="javascript:reply('+"'"+index+"','"+entity[j].cmtId+"'"+')">回复</a>';
							 s+='</div>';
							 s+='<ul id="ul'+index+'">';
							 for(var i=0;i<entity[j].replyList.length;i++){ 
							 s+='<li><p>'+entity[j].replyList[i].visitorName+'：'+entity[j].replyList[i].cmtInfo+'（'+(entity[j].replyList[i].createTime)+'）</p><div><a href="javaScript:toReply('+i+',\''+entity[j].replyList[i].visitorName+'\')">回复</a></div></li>';
							 } s+='</ul>';
							 s+='</div>';
							 s+='</div>';
							 s+='</li>';
	
						}
						$('#replyList').html(s);
						$(".uF_answer2_info").click(function(){
							$(this).next(".uF_ans_info").toggle(200);
						});
					}  
				}
			});
		}
		/*用户反馈js开始*/