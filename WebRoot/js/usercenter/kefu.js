$(document).ready(function() {
    $("div.kefu_tc").hover(function(){
		$("div.kefu_con").show();
		},function(){
		$("div.kefu_con").hide();	
	});
	
	$TopFun = function() {
       var st = $(document).scrollTop(), winh = $(window).height();
        //IE6下的定位
        if (!window.XMLHttpRequest) {
           $("div.kefu_tc").css("top", st + winh - 280);
        }
    };
    
    $(function() { $TopFun(); });
});