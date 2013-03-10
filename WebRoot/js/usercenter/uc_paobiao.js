// JavaScript Document
function  chaoshicha(fen)
{	
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline',
						plotBorderWidth: null,
						plotShadow: false
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: ['优秀', '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%' 
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
						
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100, 95, 80,70,{
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						},]
				
					}]
				});	
				
}


function  chaoshijige(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: ['优秀', '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%' 
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100, 95, 80,{
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						}, 59]
				
					}]
				});	
				
}

function  chaoshiputong(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: ['优秀', '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%' 
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100, 95, {
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						},70, 59]
				
					}]
				});	
				
}

function  chaoshilianghao(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: ['优秀', '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%' 
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100,  {
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						}, 80,70,59]
				
					}]
				});	
				
}
function  chaoshiyouxiu(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: [' ','优秀', '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%' 
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [120,{
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						},95, 80,70, 59]
				
					}]
				});	
				
}

function  yichaoshicha(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: [ '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%' 
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100,95,80,{
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						},]
				
					}]
				});	
				
}

function  yichaoshijige(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: [ '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%'
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100,95,{
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						},70]
				
					}]
				});	
				
}

function  yichaoshiputong(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: [ '良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%' 
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100,{
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						},80,70]
				
					}]
				});	
				
}
function  yichaoshilianghao(fen)
{
		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'spline'
					},
					title: {
						text: ''
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories: [ ' ','良好', '普通', '及格', '很差']
					},
					yAxis: {
						title: {
							text: ''
						},
						labels: {
							formatter: function() {
								return this.value+'%'
							}
						}
					},
					tooltip: {
						crosshairs: false,
						shared: false
					},
					plotOptions: {
						spline: {
							showInLegend:false
						}
					},
					series: [{
						name: '考试状态',
						marker: {
							symbol: ''
						},
						
						data: [100,{
							y: fen,
							marker: {
								symbol: 'url('+importURL+'/images/usercenter/biao.png)'
							}
						},95,80,70]
				
					}]
				});	
				
}