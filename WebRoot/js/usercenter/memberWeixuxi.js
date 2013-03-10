

/*//微公告滚动
$(function(){
		var $w_timer;
		var $w_height = $(".w_notice").height()
		$(".notice_micro").hover(function(){
			clearInterval($w_timer);	
		}, function(){
			$w_timer = setInterval(function(){
				$(".w_list_ul").animate({marginTop:-$w_height+"px"},500,function(){
						$(this).css({marginTop:0}).find("li:first").appendTo(this);
					});
				//$(".w_list_ul").append($li_first)
			},3000)
		}).trigger("mouseleave")
})*/


//微公告滚动

$(function(){
		$("#notice_more").click(function(){
				if(!$(".w_gg_list").is(":visible")){
					$(this).removeClass("w_notice_more_poz").addClass("w_notice_more_pozh");
					$(".w_gg_list").slideDown(400)
				}else{
					$(this).removeClass("w_notice_more_pozh").addClass("w_notice_more_poz")	
					$(".w_gg_list").slideUp(400)
				}
				return false
		})
})


//课程项鼠标效果
$(function(){
	$(".kecheng_item > li").hover(function(){
		$(this).css({"background":"#f4fbff","border-bottom":"1px solid #d1e7f5"}).siblings().css({"background":"#ffffff","border-bottom":"1px solid #dbdbdb"})
		$(this).find(".del_tuijian").show()
	}, function(){
		$(this).css({"background":"#ffffff","border-bottom":"1px solid #dbdbdb"}).siblings().css({"background":"#f4fbff","border-bottom":"1px solid #f4fbff"})
		$(this).find(".del_tuijian").hide()
	})
	
	$(".my_weixuxi").mouseleave(function(){
		$(".kecheng_item > li").css({"background":"#ffffff","border-bottom":"1px solid #dbdbdb"})
	})
	
	//删除当前课程
	$(".del_tuijian").click(function(){
			$(this).parents("li").slideUp("slow",function(){
				$(this).remove()	
			})
			return false
	})
})


//分享鼠标效果
$(function(){
	$(".tuijian_share").mouseover(function(){
		$(this).css({"overflow-y":"auto","height":"auto","border":"1px solid #dedede","background":"#ffffff"})
	})
	$(".tuijian_share").mouseout(function(e){
		$(this).css({"overflow-y":"hidden","height":"26px","border":"1px solid #ffffff","background":"none"})	
		return false
	})	
})

//




//显示推荐课程

$(function(){
	$(".tuijian_link").click(function(){
		var $showcnt = $(this).parent().next()
		if($showcnt.is(":visible")){
			$showcnt.hide()	
			$(this).removeClass("tl_kc_poz_h").addClass("tl_kc_poz")
		}else{
			$showcnt.show()	
			$(this).removeClass("tl_kc_poz").addClass("tl_kc_poz_h")
		}
		//推荐课程列表
		var $width_tj = $(".tuijian_cnt").width()
		var $i_tj = 0
		var $total = Math.ceil($(".tuijian_cnt li").length/4)
		//alert($total)
		$("#tuijian_r").click(function(){
				$i_tj++		
				if($i_tj+1>$total){
					$i_tj--;
					return false;
				}	
				
				if($(".tuijian_ul:not(:animated)") && $i_tj < $total){
					$(".tuijian_ul").animate({left:-($width_tj*$i_tj-15)+"px"})	
					if($i_tj == $total-1){
							$(this).addClass("arrow_tj_hide").removeClass("arrow_tj_r")	
							$i_tj = $total-1
					}
				}
				$("#tuijian_left").addClass("arrow_tj_l").removeClass("arrow_tj_l_hide")
				return false
		})	
		
		$("#tuijian_left").click(function(){
				$i_tj--		
				if($i_tj<0){
					$i_tj=0;
					return false;
				}	
				if($(".tuijian_ul:not(:animated)") && $i_tj >= 0){
					$(".tuijian_ul").animate({left:-($width_tj*$i_tj-15)+"px"})
					if($i_tj == 0){
							$(this).addClass("arrow_tj_l_hide").removeClass("arrow_tj_l")	
							$i_tj = 0
					}
				}
				$("#tuijian_r").addClass("arrow_tj_r").removeClass("arrow_tj_hide")
				return false
		})
	})
})

//视频下载隔行换色

$(function(){
		$(".down_list_ul li:even").css({"background":"#f5fafe"})
		//学习轨迹隔行换色
		$(".guji_ul li:even").css({"background-color":"#f5fafe"})
		//展开选择课程弹出层
		$("#choose_class_btn").mouseover(function(){
				$(".choose_kecheng").stop(true,false).show().mouseleave(function(){
					$(this).stop(true,false).hide()
				})
		})
})


$(function(){
	$(".sns_btn").mouseover(function(){
			$(".share_sns").animate({"right":"-10px"})	
	})	
	$(".share_sns").mouseleave(function(){
			$(this).animate({"right":"-118px"})	
	})
})