var xPos = 1000;
var yPos = 700; 
var step = 1;
var delay = 30; 
var height = 0;
var Hoffset = 0;
var Woffset = 0;
var yon = 0;
var xon = 0;
var pause = true;
var interval;
ad_ssUchome_float.style.top = yPos;
function changePos() 
{
	width = document.body.clientWidth;
	height = document.body.clientHeight;
	Hoffset = ad_ssUchome_float.offsetHeight;
	Woffset = ad_ssUchome_float.offsetWidth;
	ad_ssUchome_float.style.left = xPos + document.body.scrollLeft;
	ad_ssUchome_float.style.top = yPos + document.body.scrollTop;
	if (yon) 
		{yPos = yPos + step;}
	else 
		{yPos = yPos - step;}
	if (yPos < 0) 
		{yon = 1;yPos = 0;}
	if (yPos >= (height - Hoffset)) 
		{yon = 0;yPos = (height - Hoffset);}
	if (xon) 
		{xPos = xPos + step;}
	else 
		{xPos = xPos - step;}
	if (xPos < 0) 
		{xon = 1;xPos = 0;}
	if (xPos >= (width - Woffset)) 
		{xon = 0;xPos = (width - Woffset);   }
	}
	
	function start()
	 {
	 	ad_ssUchome_float.visibility = "visible";
		interval = setInterval('changePos()', delay);
	}
	function pause_resume() 
	{
		if(pause) 
		{
			clearInterval(interval);
			pause = false;}
		else 
		{
			interval = setInterval('changePos()',delay);
			pause = true; 
			}
		}
	start();
//关闭
$(function(){
	$(".ad_ssUchome_floatOut").click(function(){
		$("#ad_ssUchome_float").fadeOut();
	})
})
	
	
	