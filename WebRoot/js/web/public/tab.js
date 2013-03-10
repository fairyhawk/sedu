/*切换*/
function setTab(name, cursel, n) {
	for (var i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "tab_current" : "";
		con.style.display = i == cursel ? "block" : "none";
	}
}

//Yagninig 2012/04/12用户地址
function setTabNew(name, cursel, n) {
	for (var i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "tab_current" : "";
		con.style.display = i == cursel ? "block" : "none";
		// Yagninig 2012/04/12用户地址
		if (cursel == 1 || cursel == 4 || cursel == 5) {
			var htmlId = $("#"+"sendFor_Div_" + i);
			if(typeof(htmlId) != 'undefined'){
				var htmlContent = htmlId.html();
				if(typeof(htmlContent) != 'undefined' && htmlContent != null && $.trim(htmlContent).length > 0){
					htmlId.html("");
					$("#"+"sendFor_Div_" + cursel).html(htmlContent);
				}
			}
			
		}
	}
}