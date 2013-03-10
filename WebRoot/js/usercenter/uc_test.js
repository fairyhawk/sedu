
	var firstAnswer = 0;
	var answers = [0, 0, 0, 0];
	
	function exitTest() {
		if(testedStudyType) {
			$("#studyTypeResult").css("display", "block");
			$("#falshDiv").css("display", "hidden");
		}
	}
	
	function initTest() {
		if(!testedStudyType) {
			$("#studyTypeResult").css("display", "none");
		} else {
			$("#falshDiv").css("display", "hidden");
		}
	}
	
	function startTest() {
		$("#falshDiv").css("display", "block");
		$("#studyTypeResult").css("display", "none");
	}
	
	function changeTestedStudyType(){
		testedStudyType = true;
	}
	
	function showDetailDesc() {
		if($('#detailDesc').css("display") == "none") {
			$('#detailDesc').slideDown(500);
			window.location.hash = "detailDesc";
		} else {
			closeDetailDesc();
		}
		//$("#detailDesc").css("display", "block");
	}
	
	function closeDetailDesc() {
		$('#detailDesc').slideUp(500, function (){window.location.hash = "show_desc_a";});
		//$("#detailDesc").css("display", "none");
	}
	
	function showResult(parms) {
		try{
			$("#falshDiv").css("display", "hidden");
		}catch(e){};
		$("#studyTypeResult").css("display", "block");
		addRecommendCourse();
		showBar(parms);
		showCircle(parms);
	}
	
	function addRecommendCourse() {
		var subjectId = 3;
		var temp = getCookie("subjectId");
		if(temp != null && temp != '' && !isNaN(temp) && parseInt(temp) > 0) {
			subjectId = temp;
		}
		
		$("#recommendCourse").html("");
		if(subjectId == 3) {
			$("<li><a href='" + baselocation + "/kjz'>会计取证专业课程</a></li>").appendTo("#recommendCourse");
		} else if (subjectId == 1) {
			$("<li><a href='" + baselocation + "/rl'>人力资源师三级课程</a></li>").appendTo("#recommendCourse");
			$("<li><a href='" + baselocation + "/rl'>人力资源师二级课程</a></li>").appendTo("#recommendCourse");
		} else if (subjectId == 5) {
			$("<li><a href='" + baselocation + "/sf'>司法专业课程</a></li>").appendTo("#recommendCourse");
		} else if (subjectId == 7) {
			$("<li><a href='" + baselocation + "/cpa'>CPA专题课程</a></li>").appendTo("#recommendCourse");
		} else if (subjectId == 8) {
			$("<li><a href='" + baselocation + "/zq'>证券专题课程</a></li>").appendTo("#recommendCourse");
		} else if (subjectId == 2) {
			$("<li><a href='" + baselocation + "/xl'>心理专题课程</a></li>").appendTo("#recommendCourse");
		}
	}
	
	function showBar(parms) {
		$("#studyTypeTip").html("您属于" + getType(parms.studyType) + "，其中四项元素比");
		var chart = new Highcharts.Chart({
				chart: {
					renderTo: 'barChart',
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
	}
	
	function showCircle(parms) {
		var data = [];
		//$("#studyTypeSpan" + parms.studyType).html("您所在的类型:");
		//$("#studyTypeDiv" + parms.studyType).addClass("strongb");
		//data.push([getType(parms.studyType), parms.results[parms.studyType-1]]);
		for(var i=0; i<parms.results.length; i++) {
			if(i != parms.studyType-1) {
				data.push([getType(i+1), parms.results[i]]);
			} else {
				data.push({
					name: getType(i+1),    
					y: parms.results[i],
					sliced: true,
					selected: true
				});
			}
		}			
	
		var chart = new Highcharts.Chart({
			chart: {
				renderTo: 'circleChart',
				plotBackgroundColor: '#E9E9F1',
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text: ''
			},
			tooltip: {
				formatter: function() {
					return this.point.name +':'+ this.y +' %';
				}
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: false
					}
				}
			},
		    series: [{
				type: 'pie',
				name: 'Browser share',
				data: data
			}]
		});
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