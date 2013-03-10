
function centerLocation(appId){
	var date = new Date();
	if(parseInt(appId) > 0){
		var address="";
		switch (parseInt(appId)) {
			case 1:
				address = '/cus/cuslimit!toUserCenter.action';
				break;
			case 2:
				address = '/finance/contract!getContractList.action?queryContractCondition.currentPage=1';
				break;
			case 3:
				address = '/msg/webmsg!listReceiveMsgs.action?queryUserMsgCondition.currentPage=1';
				break;
			case 4:
				address = '/cou/courselimit!toMyCourse.action';
				break;
			case 5:
				address = '/exam/qstManager!getExampaperAnalysisDTO.action';
				break;
			case 6:
				address = '/exam/qstManager!getExamPaperAllList.action?queryExampaperCondition.currentPage=1';
				break;
			case 7:
				address = '/cus/pblimit!getEveryOneProblemList.action?course.courseId=15&problem.pblSolv=1&queryProblemCondition.currentPage=1&location=1';
				break;
			case 8:
				address = '/stu/calendar!getListPlanByCalendardGoto.action?checkDay=' + date.pattern("yyyy-MM-dd");
				break;
			case 9:
				address = '/dis/discussion!toDisHomepage.action';
				break;
			case 10:
				address = '/cus/cuslimit!toAssess.action';
				break;
			case 12:
				address = '/freshnews/fna!toFreshNews.action';
				break;
			case 13 :
			   address = '/exam/qstManager!toRandomExam.action';
			   break;
		}
		window.location.href = baselocation + address;
	}
}

Date.prototype.pattern=function(fmt) {        
    var o = {        
    "M+" : this.getMonth()+1, 
    "d+" : this.getDate(),
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12,   
    "H+" : this.getHours(),
    "m+" : this.getMinutes(),
    "s+" : this.getSeconds(),   
    "q+" : Math.floor((this.getMonth()+3)/3),  
    "S" : this.getMilliseconds()
    };        
    var week = {        
    "0" : "\u65e5",        
    "1" : "\u4e00",        
    "2" : "\u4e8c",        
    "3" : "\u4e09",        
    "4" : "\u56db",        
    "5" : "\u4e94",        
    "6" : "\u516d"       
    };        
    if(/(y+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
    }        
    if(/(E+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
    }        
    for(var k in o){        
        if(new RegExp("("+ k +")").test(fmt)){        
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
        }        
    }        
    return fmt;        
};

$().ready(function() {
	if (typeof(ucLeftIndex)!="undefined" && ucLeftIndex!="" && ucLeftIndex!="" ){
		var ucLeftIndexId ="uc_left_"+ucLeftIndex;
		 $("#"+ucLeftIndexId).addClass("current");//左侧默认选择样式
	}
});
