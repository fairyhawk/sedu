$(document).ready(function(){
	$(".statement span").click(function(){
		$(this).parent(".statement").slideUp("slow")
	});
});

$(document).ready(function(){
	$(".help_tab li:first").addClass("current");
	$(".help_tab_div:gt(0)").hide();
	$(".help_tab li").click(function(){
		$(this).addClass("current").siblings("li").removeClass("current");
		$(this).prev("li").addClass("prevli").siblings("li").removeClass("prevli");
	    $(".help_tab_div:eq("+$('.help_tab > li').index(this)+")").show().siblings(".help_tab_div").hide();
	});
	$(".help_tab li").hover(
  function () {
    $(this).addClass("hover");
  },
  function () {
    $(this).removeClass("hover");
  });
});