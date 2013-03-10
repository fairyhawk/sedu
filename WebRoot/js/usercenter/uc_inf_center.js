
	var totalPage = 0;
	
	$().ready(function() {
		//subjectId = getCookie("subjectId");
		//subjectId=${customer.subjectId};alert(subjectId);
		//if(subjectId == null || isNaN(subjectId) || parseInt(subjectId) < 1) {
		//	subjectId = 3;
		//}
		
		//setNewsTitle();
		getHighSoNews();
		//getSubjectNews();
	});
	
	function setNewsTitle() {
		if(subjectId == 3) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />会计资讯");
		} else if (subjectId == 1) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />人力资讯");
		} else if (subjectId == 5) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />司法资讯");
		} else if (subjectId == 7) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />注册会计师资讯");
		} else if (subjectId == 8) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />证券资讯");
		} else if (subjectId == 2) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />心理资讯");
		} else if (subjectId == 0) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />HighSo资讯");
		}
	}
	
	function getSubjectNews() {
		$.getJSON(baselocation + "/static/web/article/subject/" + subjectId + "/page_1.shtml",function(msg){
			if(msg == null) {
				return;
			}
			var showCount = msg.length > 8 ? 8 : msg.length;
			for(var i=0; i<showCount; i++) {
				var showTitle = msg[i].title.length > 35 ? msg[i].title.substring(0, 35) + "..." : msg[i].title;
				$("<li><font class='right font_hui'>" + msg[i].time + "</font><a href='" + baselocation + msg[i].url + "' target='_blank' title='" + msg[i].title + "'>" + showTitle + "</a></li>").appendTo("#highso_inf_ul");
			}
		});
	}
	
	function getHighSoNews() {
		$.getJSON(baselocation + "/static/web/article/subject/" + subjectId + "/page_1.shtml",function(msg){
			if(msg == null) {
				return;
			}
			var showCount = msg.length > 8 ? 8 : msg.length;
			for(var i=0; i<showCount; i++) {
				var showTitle = msg[i].title.length > 35 ? msg[i].title.substring(0, 35) + "..." : msg[i].title;
				$("<li><font class='right font_hui'>" + msg[i].time + "</font><a href='" + baselocation + msg[i].url + "' target='_blank' title='" + msg[i].title + "'>" + showTitle + "</a></li>").appendTo("#abcd");
			}
		});
	}

	function showBar(parms) {
		$("#uc_studyTypeTip").html("您属于" + getType(parms.studyType) + "，其中四项元素比");
		var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'uc_barChart',
					defaultSeriesType: 'bar'
				},
				title: {
					text: ''
				},
				xAxis: {
					categories: ['自信心', '执行力', '周密度', '适应性'],
					title: {
						text: null
					}
				},
				tooltip: {
					formatter: function() {
						return this.y +' %';
					}
				},
				plotOptions: {
					bar: {
						cursor: 'pointer',
						showInLegend:false
					}
				},series: [{
					name: '学习类型',
					data: [parms.results[0], parms.results[1], parms.results[2], parms.results[3]]
				}]
			});
		document.getElementById("highcharts-0")
		$("#highcharts-0").css("left", "-10px");
	}
	
	function getType(index) {
		switch(index) {
			case 1:
				return '带有习惯性拖延的类型';
				break;
			case 2:
				return '带有明显强迫症的类型';
				break;
			case 3:
				return '精密计划严格执行的类型';
				break;
			case 4:
				return '随心所欲追求适度的类型';
				break;
		}
	}
	