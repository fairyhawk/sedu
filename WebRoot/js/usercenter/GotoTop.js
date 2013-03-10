// JavaScript Document]
		
				
//滚动到页头的方法 2011-08-03
$(function() {
    var $backToTopEle = $('<div class="goto_top"></div>').appendTo($(".container.con_top_bg2.con_pos"))
        .click(function() {
            $("html, body").animate({ scrollTop: 0 }, 400);
    }), $backToTopFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 0)? $backToTopEle.show(): $backToTopEle.hide();
        //IE6下的定位
        if (!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 280);
        }
    };
    $(window).bind("scroll", $backToTopFun);
    $(function() { $backToTopFun(); });
})





