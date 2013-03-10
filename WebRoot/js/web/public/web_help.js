
	$().ready(function() {
		var loc = getParameter('loc');
		if(loc != null  &&  loc != '') {
			$("#help_" + loc.substring(0,1)).click();
			if(loc.substring(0, 1) == 2) {
				HelpPartH(loc.substring(1));
			}
			window.location.hash = "help_" + loc;
		}
	});

	/*常见问题关于课程*/
 	function HelpPartH(h){
	 	for(i=1;i<7;i++){
			document.getElementById("help_part"+i).style.display="none";
			document.getElementById("help_part"+h).style.display="block";
	 	}
	 }