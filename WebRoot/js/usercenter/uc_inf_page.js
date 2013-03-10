
	$().ready(function() {
		if($("#pre_font a").html() == '上一篇：') {
			$("#pre_font").css("display", "none");
		}
		if($("#after_font a").html() == '下一篇：') {
			$("#after_font").css("display", "none");
		}
		
		setNewsTitle();
	});
	
	function setNewsTitle() {
		if(subjectId == 3) {
			//$("#subject_a").html("会计资讯");
			$("#subject_a").html("考试指南资讯");
			$("#footer_banner_img").attr("src", importURL + "/images/web/public/kjz_810.jpg");
		} else if (subjectId == 1) {
			//$("#subject_a").html("人力资讯");
			$("#subject_a").html("考试指南资讯");
			$("#footer_banner_img").attr("src", importURL + "/images/web/public/rl_810.jpg");
		} else if (subjectId == 5) {
			//$("#subject_a").html("司法资讯");
			$("#subject_a").html("考试指南资讯");
			$("#footer_banner_img").attr("src", importURL + "/images/web/public/sf_810.jpg");
		} else if (subjectId == 7) {
			//$("#subject_a").html("注册会计师资讯");
			$("#subject_a").html("考试指南资讯");
			$("#footer_banner_img").attr("src", importURL + "/images/web/public/zk_810.jpg");
		} else if (subjectId == 8) {
			//$("#subject_a").html("证券资讯");
			$("#subject_a").html("考试指南资讯");
			$("#footer_banner_img").attr("src", importURL + "/images/web/public/zq_810.jpg");
		} else if (subjectId == 2) {
			//$("#subject_a").html("心理资讯");
			$("#subject_a").html("考试指南资讯");
			$("#footer_banner_img").attr("src", importURL + "/images/web/public/xl_810.jpg");
		} else if (subjectId == 0) {
			$("#subject_a").html("HighSo资讯");
			$("#footer_banner_img").attr("src", importURL + "/images/web/public/kjz_810.jpg");
			$("#subject_a").attr("href", baselocation + "/cus/cuslimit!toInfList.action?pageNo=1&subjectId=0");
		}
	}

	function toSubjectPage() {
		if(subjectId == 3 || subjectId == 0) {
			window.location.href = baselocation + "/kjz";
		} else if (subjectId == 1) {
			window.location.href = baselocation + "/rl";
		} else if (subjectId == 5) {
			window.location.href = baselocation + "/sf";
		} else if (subjectId == 7) {
			window.location.href = baselocation + "/cpa";
		} else if (subjectId == 8) {
			window.location.href = baselocation + "/zq";
		} else if (subjectId == 2) {
			window.location.href = baselocation + "/xl";
		}else if (subjectId == 9) {
			window.location.href = baselocation + "/jz";
		}else if (subjectId == 10) {
			window.location.href = baselocation + "/gk";
		}else if (subjectId == 11) {
			window.location.href = baselocation + "/gwy";
		}
	}