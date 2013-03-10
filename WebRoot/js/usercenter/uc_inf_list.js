	var subjectId = null;
	var totalPage = 0;
	var pageSize = 24;
	var pageNo = 1;
	
	$().ready(function() {
		pageNo = parseInt(getParameter("pageNo"));
		if(pageNo == null || isNaN(pageNo) || parseInt(pageNo) < 1) {
			pageNo = 1;
		}
		subjectId = getParameter("subjectId");
		if(subjectId == null || isNaN(subjectId) || parseInt(subjectId) < 0) {
			subjectId = getCookie("subjectId");
			if(subjectId == null || isNaN(subjectId) || parseInt(subjectId) < 1) {
				subjectId = 3;
			}
		}
		
		setNewsTitle();
		getDate();
	});
	
	function setNewsTitle() {
		if(subjectId == 0) {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />HighSo资讯");
		} else {
			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />考试指南资讯");
		}
		return;
//		if(subjectId == 3) {
//			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />会计资讯");
//		} else if (subjectId == 1) {
//			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />人力资讯");
//		} else if (subjectId == 5) {
//			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />司法资讯");
//		} else if (subjectId == 7) {
//			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />注册会计师资讯");
//		} else if (subjectId == 8) {
//			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />证券资讯");
//		} else if (subjectId == 2) {
//			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />心理资讯");
//		} else if (subjectId == 0) {
//			$("#head_inf_font").html("<img src='" + importURL + "/images/usercenter/zx_icon1.gif' />HighSo资讯");
//		}
	}
	
	function getDate() {
		$.getJSON(baselocation + "/static/web/article/subject/" + subjectId + "/totalPage.shtml",function(msg){
			totalPage = msg.totalPage;
			if(totalPage == null || isNaN(totalPage) || totalPage < 1) {
				totalPage = 1;
			}
			showPages(pageNo, parseInt((parseInt(totalPage)+pageSize-1)/pageSize));
			$.getJSON(baselocation + "/static/web/article/subject/" + subjectId + "/page_" + pageNo + ".shtml",function(msg){
				if(msg == null || msg == '') {
					$("#inf_list_ul1").html("最近没有相关资讯");
				} else {
					var ulCount = 1;
					for(var i=0; i<msg.length; i++) {
						if(i % 8 == 0) {
							$("#inf_xuxian_" + (parseInt(i / 8) + 1)).css("display", "block");
						}
						var showTitle = msg[i].title.length > 35 ? msg[i].title.substring(0, 35) + "..." : msg[i].title;
						$("<li><font class='right font_hui'>" + msg[i].time + "</font><a href='" + baselocation + msg[i].url + "' target='_blank' title='" + msg[i].title + "'>" + showTitle + "</a></li>").appendTo("#inf_list_ul" + (parseInt(i / 8) + 1));
					}
				}
			});
		});
	}

	function showPages(currentPage, maxPage) {
  		maxPage = maxPage < 1 ? 1 : maxPage;
  		if(currentPage > maxPage) {
  			currentPage = maxPage;
  		}
		var maxNum = currentPage > 4 ? 6 : 11 - currentPage;
		var html = '';
		if(currentPage == 1) {
			html += '<span class=disabled> <  上一页</span>';
		} else {
			html += '<a href=javascript:void(0); onclick=goPage(' + (currentPage-1 ) + ')> <  上一页</a>';
		}
		if(currentPage > 5) {
   			html += '... ';
   		}
   		for(var i = 4; i > 0; i--) {
   			if(currentPage > i) {
   				html += '<a href=javascript:void(0); onclick=goPage(' + (currentPage-i ) + ')>' + (currentPage-i) + '</a>';
   			}
   		}
  	 	html += '<span class=current>'+currentPage+'</span>';
	   	for(var i = 1; i < maxNum; i++) {
	   		if(currentPage + i <= maxPage) {
	   			html += '<a href=javascript:void(0); onclick=goPage(' + (currentPage+i) + ')>' + (currentPage+i) + '</a>';
	   		} else {
	   			break;
	   		}
	   	}
   		if(currentPage + maxNum <= maxPage) {
	   		html += '... ';
	   	}
	   	if(currentPage == maxPage) {
			html += '<span class=disabled>下一页  > </span>';
		} else {
			html += '<a href=javascript:void(0); onclick=goPage(' + (currentPage+1) + ')>下一页  > </a>';
	   	}
	   	
	   	if(currentPage == 1) {
			html += '<span class=disabled> 首页 </span>';
		} else {
			html += '<a href=javascript:void(0); onclick=goPage(' + 1 + ')> 首页 </a>';
		}
	   	if(currentPage == maxPage) {
			html += '<span class=disabled> 尾页 </span>';
		} else {
			html += '<a href=javascript:void(0); onclick=goPage(' + maxPage + ')> 尾页 </a>';
	   	}
		html += ' 共' + maxPage + '页';
		$('#page_div').html(html);
	}
	
	function goPage(pageNum){
        document.location = baselocation + "/cus/cuslimit!toInfList.action?pageNo=" + pageNum + "&subjectId=" + subjectId;
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