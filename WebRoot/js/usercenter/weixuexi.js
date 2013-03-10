//sns分享鼠标效果
$(function(){
	$("#fenxiang_sns").hover(function(){
			$(this).removeClass("hei26")
		},function(){
		
		$(this).addClass("hei26")
		})	
})
//视频停止切换效果
$(function(){
		$("#nor_img").click(function(){
			$("#bofang_tips").hide();
			$(this).hide();
			$("#cur_bofang").show();
			$("#vedio_hide").html(obj);
			$("#vedio_hide").show();
			})
})


//查看解析按钮显示或隐藏
/*
$(function(){
	$('.chos_ra').click(function(){		
			if($(this).attr("checked")){
				$(".jiexi_btn").show()
			}
	});
	$(".jiexi_btn").click(function(){
		$(".anser_ana").slideDown()
		})
})*/

function chos_ra_click(jiexi_btn_id,anser_ana_id){
	$("#" + jiexi_btn_id).show();
}

/**
 * 微学习页面中使用，解析按钮
 * 
 * @param {} anser_ana_id
 */
function jxShow(anser_ana_id){
	var displayValue = $("#" + anser_ana_id)[0].style.display;
	if(displayValue == "none" || displayValue == ""){
		$("#" + anser_ana_id).slideDown();
	}else if(displayValue == "block"){
		$("#" + anser_ana_id).slideUp();
	}
}


//推荐试题切换隐藏
$(function(){
	var i = 0
	$(".recom_head").click(function(){
		i++
		if( i%2 == 0){
			$(".tuijian_exe").stop(false,true).slideDown()	
		}else{
			$(".tuijian_exe").stop(false,true).slideUp()
		}
	})
})


var shitiCount = 1;
var total = 0;

//推荐试题 切换
$(function(){
		
		total = $(".shiti_item").length;
		var i = 0;
		var marl;
		var n = 565
		$("#nex").click(function(){
			
			shitiCount++;//下一题
			
			var beforeNanduNum = $("#nandu_" + (shitiCount - 1)).html();//取得当前项题，难度值
			var nextNanduNum = $("#nandu_" + shitiCount).html();//取得下一个题难度值
			//先删除原有的难度星级样式值，再追加新的试题样式值
			if(nextNanduNum == null){
				shitiCount--;//这里取消业务，由于上面++将会失效，所以这里需要--，还原数值
				$(this).attr("class","next_page_d");
				return;
			}
			$("#difficultyItem").removeClass("w_star_pos" + beforeNanduNum).addClass("w_star_pos" + nextNanduNum);
			
			
			$("#pre").attr("class","prav_page");
			var last = total - 1;
			if(i == last){
				return
			}
			i++	
			marl = -n*i
			$(".xiayiti").stop(true,false).animate({left:marl+"px"})
			if(i == last){
				$(this).attr("class","next_page_d");
			}
		})
		$("#pre").click(function(){
			
			$("#nex").attr("class","next_page");
			shitiCount--;//上一题
			
			var beforeNanduNum = $("#nandu_" + (shitiCount + 1)).html();//取得当前项题，难度值
			var nextNanduNum = $("#nandu_" + shitiCount).html();//取得下一个题难度值
			//先删除原有的难度星级样式值，再追加新的试题样式值
			if(nextNanduNum == null){
				shitiCount++;//这里取消业务，由于上面--将会失效，所以这里需要++，还原数值
				return;
			}
			
			$("#difficultyItem").removeClass("w_star_pos" + beforeNanduNum).addClass("w_star_pos" + nextNanduNum);
			
			if(i == 0){
				return;
			}
			i--	
			marl = -n*i
			$(".xiayiti").stop(true,false).animate({left:marl+"px"})
			if(i == 0){
				$(this).attr("class","prav_page_d")
			}
		})

})

//评论输入框回车自动增加高度

$(function(){
	$("#pinlun_txt").focus(function(){
		if($("#pinlun_txt").val() == this.defaultValue){
			$(this).val(" ")
			$(this).removeClass("co_w3").addClass("co_w2")
		}
	}).blur(function(){
		if($(this).val() ==  " "){
			//alert("ssss")
			$(this).removeClass("co_w2").addClass("co_w3")	
			$(this).val(this.defaultValue);
		}	
	})
	
	$(".huifu_link").click(function(){
		$("#pinlun_txt").focus()
	})

	$("#pinlun_txt").keyup(function(){
		if($(this).val() == null){
			//alert("ssss")
			$(this).removeClass("co_w2").addClass("co_w3")	
			$(this).val(this.defaultValue);
		}	
	})

	//回复框高度增加和减小效果
	$("#pinlun_txt").keydown(function(e){
		var $hei = $(this).height()
		if(e.keyCode == 13){
			$(this).css({"height":+$hei+28+"px"})
		}
		if(e.keyCode == 8){
			$(this).css({"height":($hei-28)+"px"})	
			if($(this).height() <= 28){
				$(this).css({"height":"28px"})
			}
		}
	})	
})

function reviewAddTextLoad(){
	$("#pinlun_txt").focus(function(){
		if($("#pinlun_txt").val() == this.defaultValue){
			$(this).val(" ")
			$(this).removeClass("co_w3").addClass("co_w2")
		}
	}).blur(function(){
		if($(this).val() ==  " "){
			//alert("ssss")
			$(this).removeClass("co_w2").addClass("co_w3")	
			$(this).val(this.defaultValue);
		}	
	})
	
	$(".huifu_link").click(function(){
		$("#pinlun_txt").focus()
	})

	$("#pinlun_txt").keyup(function(){
		if($(this).val() == null){
			//alert("ssss")
			$(this).removeClass("co_w2").addClass("co_w3")	
			$(this).val(this.defaultValue);
		}	
	})

	//回复框高度增加和减小效果
	$("#pinlun_txt").keydown(function(e){
		var $hei = $(this).height()
		if(e.keyCode == 13){
			$(this).css({"height":+$hei+28+"px"})
		}
		if(e.keyCode == 8){
			$(this).css({"height":($hei-28)+"px"})	
			if($(this).height() <= 28){
				$(this).css({"height":"28px"})
			}
		}
	})	
}


/*目录点击效果*/
$(function(){
	$(".kecheng_list li a").click(function(){
			//遍历 查找所有有上标
			$(this).addClass("new_col")
			$(".kecheng_list li").find(".serial").find(".sup").each(function(){
				//清除上标元素
				$(this).parent().html($(this).parent().text());
			});
			//alert('11');
			var $par = $(this).parent();
			$par.attr("id","cur_play").siblings().attr("id"," ")
			$(this).parents("ul").siblings("ul").find("li").attr("id"," ")
			//延迟0.01秒执行
			setTimeout(function(){$par.children("em").append("<b class='rec_iconn'></b>").parent().siblings().find("b").remove(".rec_iconn")}, 10);
			//查找点击数
			var length_num = $par.find(".serial").text();
			var $maxhtml;
			//判断是否大于2位数
			if(length_num.length>1){
				//如果2位数 分拆成数组
				arr = (length_num+'').split('')	
				$maxhtml = arr[0]
				for(i=1;i<arr.length;i++){
					//如果是最后一个则 插入上标
					if(i==arr.length-1){
						$maxhtml = $maxhtml+"<sup class='sup'>"+arr[i]+'</sup>';
					}else{
						$maxhtml = $maxhtml+arr[i]
					}					
				}
				$par.find(".serial").html($maxhtml);
			}
			
			
	})	
	//目录伸缩折叠效果

	var $n = 0
			$(".sec_head").live('click',function(){
			if(!$(this).next("ul").is(":animated")){
				if($(this).next("ul").is(":visible")){
				$(this).css({"background":"#dceec5"})
				$(this).children("em").attr("class","arrow_down")
			}else{
				$(this).css({"background":"#b7e7da"})
				$(this).children("em").attr("class","arrow")
			}
				$(this).next("ul").stop(false,false).slideToggle(200)
				
			}
			})
	
	
	/*
	//难度提示框
	$(".kecheng_list li a").mouseover(function(e){
		//var hardTip = "<div id='imporant_star'></div>";
		//alert(e.pageX)
		$(this).parent().addClass("posr")
		var $chi= $(this).parent().children(".imporant_star")
		//alert($chi.attr("class"))
		$chi.show();
	}).mouseout(function(){
		$(this).parent().removeClass("posr")
		$(this).parent().children(".imporant_star").hide()
	});*/
	
	//难度提示框
	$(".kecheng_list li a").mouseover(function(e){
		//var hardTip = "<div id='imporant_star'></div>";
		//alert(e.pageX)
		var $chi= $(this).parent().children(".imporant_star")
		//alert($chi.attr("class"))
		$chi.show();
	}).mouseout(function(){
		$(this).parent().children(".imporant_star").hide()
	});

})

$(function(){
	$("#fx_sns_outer").hover(
		function(){
			$(this).stop(true,false).animate({right:0})
		}
	, function(){
			$(this).stop(true,false).animate({right:-105})
		})	
})


$(function(){
		$(".kecheng_list li").hover(function(){
			$(this).addClass("backcolor")
			}, function(){
				$(this).removeClass("backcolor")
		})
})
