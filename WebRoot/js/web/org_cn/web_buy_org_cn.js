	var isBackToData = false;
	var flag = false;
	var hasSendCourse = false;
	var isKQZL = false ;
	var zongjia = 0;
	var packageCount = 0;
	var sellWayCount=0;
	var goodspage = getCookie("coursesbao");
	var couponId=0;
	var sellId=0;
	var couponCode="";
	var sellIds="";
	function getorder(){
		goodspage = getCookie("coursesbao");
		zongjia = 0;
		packageCount = 0;
		sellWayCount=0;
		sellIds="";
		var html = "<tr class='title'><th class='th_left buylist_e01'>课程名称</th><th class='buylist_e02'>主讲人</th><th class='buylist_e03'>课时</th><th class='buylist_e04'>价格</th><th class='buylist_e05'>小计</th><th class='th_right buylist_e06'>操作</th></tr>";
		if(goodspage == null) {
			html += "<tr><td colspan='6' align='center'>订单为空</td></tr>";
		}
		
	  	if(goodspage != null) {
	  		var bao=goodspage.split("/");
	  		packageCount = bao.length-1;
	  		
		  	for(var a=0; a<bao.length-1; a++ ) {
		  		var goodbao = bao[a].split(".");
			  	var courrs = goodbao[4].split("#");
			  	var teacher = "";
			  	var timeslength = 0;
			  	
				for(var i=0; i<courrs.length; i++ ) {	
			  		var kechengfen = courrs[i].split(",");
			  		timeslength += parseInt(courrs[i].split(",")[3]);//课时
			  		teacher += kechengfen[2] + "&nbsp;&nbsp;";
			  	}
			  	
			  	if(a % 2 == 0) {
			  		html += "<tr>"
			  	} else {
			  		html += "<tr class='bj'>"
			  	}
				html += "<td>"
						+ goodbao[1] + "</td><td>"
						+ teacher + "</td><td>"
						+ timeslength + "</td><td>￥"
						+ goodbao[2] + "</td><td>￥"
						+ goodbao[2] + "</td><td><a onclick='deleteid("
						+ goodbao[0] + ")' style='cursor:pointer'>删除</a></td></tr>";
						
				zongjia+=parseInt(goodbao[2]);
				sellWayCount=sellWayCount+1;
				sellId=goodbao[0];
				sellIds=sellIds+goodbao[0]+",";
		  	}	
	  	}
	  	
	  	//谢更改
	  	$("#orderlist").html(html);
	  	$("#oldmoney").html(zongjia + "元");
	  	$("#yhmoney").html(0.00 + "元");
	  	$("#course_number").html(packageCount );
	  	$("#payprice").html(zongjia + "元");
	}
	
	function checkCoursesBought() {
		var goodspage = getCookie("coursesbao");
		var html = "<tr class='title'><th class='th_left'>课程名称</th><th>主讲人</th><th>课时</th><th>价格</th><th>小计</th><th class='th_right'>操作</th></tr>";
		var zongjia = 0;
		var availableCount = 0;
		
		if(goodspage == null) {
			html += "<tr><td colspan='6' align='center'>订单为空</td></tr>";
		}
		
	  	if(goodspage != null) {
	  		var courseOrderStr = getUserId() + "#";
	  		var bao=goodspage.split("/");
	  		
	  		var sb = "(";
	  		for(var i = 0;i<bao.length-1;i++){
	  			var goods = bao[i].split(".");
	  			sb = sb + goods[0];
	  			sb = sb + ",";
	  		}
	  		sb = sb.substring(0,sb.length -1) + ")";
	  		var buyCount = getBuyCountByIds(sb);
	  		
		  	for(var a=0; a<bao.length-1; a++ ) {
		  		var goodbao = bao[a].split(".");
			  	var courrs = goodbao[4].split("#");
			  	var teacher = "";
			  	var timeslength = 0;
			  	
				if(buyCount == 0) {
					for(var i=0; i<courrs.length; i++ ) {	
				  		var kechengfen = courrs[i].split(",");
				  		timeslength += parseInt(courrs[i].split(",")[3]);//课时
				  		teacher += kechengfen[2] + "&nbsp;&nbsp;";
				  		courseOrderStr += kechengfen[0] + "," + kechengfen[5] + "," + goodbao[0] + "#";
				  	}
				  	
				  	if(a % 2 == 0) {
				  		html += "<tr>"
				  	} else {
				  		html += "<tr class='bj'>"
				  	}
				  	
					html += "<td>"
							+ goodbao[1] + "</td><td>"
							+ teacher + "</td><td>"
							+ timeslength + "</td><td>￥"
							+ goodbao[2] + "</td><td>￥"
							+ goodbao[2] + "</td><td><a onclick='deleteid("
							+ goodbao[0] + ")' style='cursor:pointer'>删除</a></td></tr>";
							
					availableCount ++;
					zongjia+=parseInt(goodbao[2]);
				} else {
					for(var i=0; i<courrs.length; i++ ) {	
						var kechengfen = courrs[i].split(",");
						timeslength += parseInt(courrs[i].split(",")[3]);//课时
						teacher += kechengfen[2] + "&nbsp;&nbsp;";
					}
					
					html += "<tr><td style='color:#FF0000'>"
							+ goodbao[1] + "</td><td style='color:#FF0000'>"
							+ teacher + "</td><td style='color:#FF0000'>"
							+ timeslength + "</td><td style='color:#FF0000'>￥"
							+ goodbao[2] + "</td><td style='color:#FF0000'>￥"
							+ goodbao[2] + "</td><td><a onclick='deleteid("
							+ goodbao[0] + ")' style='cursor:pointer'>删除</a></td></tr>";
				}
		  	}	
	  	}
	  	
	 	$("#course_number").html(availableCount + " 件");
	  	$("#orderlist").html(html);
	  //	$("#payprice").html(zongjia + "元");alert(zongjia);去掉不能显示 显示就麻烦了
		SetCookie("courseOrder", courseOrderStr);
		SetCookie("totalPrice1", zongjia);
		if(zongjia != 0) {
			return true;
		} else {
			//谢添加	
			getorder();
			alert("购物车内的课程已购买。");
                        deleteorder();
			 //谢结束
			return false;
		}
	}
	
	function confirmContract() {
		
		if($("#payprice").html().substring(0, $("#payprice").html().length-1) == 0) {
			alert("订单金额不能为0。");
			return;
		}
		if(isBackToData) {
			$("#buy_step_div").css("display", "block");
			$("#data_div").css("display", "none");
			processReceiverInfo();
			return;
		}
		if(isLogin(baselocation + "/")) {
			if(checkCoursesBought()){
				locking();
				$("#buy_step_div").css("display", "block");
				$('#buy_step2').css("display", "block");
			}
		} else {
			locking();
			$("#buy_step_div").css("display", "block");
			$('#reg_log_div').css("display", "block");
		}
		if(typeof(_gaq)!="undefinde"&&_gaq!=null){
			_gaq.push(['_trackPageview', '/virtual/step_qty.html']);//谷歌统计  添加虚拟页面游览量统计
			_gaq.push(['_trackEvent', 'shopping_cart_2', '2','shopping_cart_2']);//谷歌统计  添加事件追踪统计
		}
	}
	
	$().ready(function() {
		getorder();
		checkRemeberMe() ;
		initFormValid();
		$("#bank_radio").attr("checked", "true");
//		if(typeof(_gat) !="undefined") {
//			pageTracker = _gat._getTracker('UA-19413742-8');
//		}
		fixWinPosition();
 	});
 	
 	function fixWinPosition() {
		$("#data_div").css("display", "block");
		if(($("#data_div").height() + $("#data_div").offset().top + 25 + $("#buy_footer").height()) < document.documentElement.clientHeight) {
			$("#buy_footer").css("position", "absolute");
			$("#buy_footer").css("top", document.documentElement.clientHeight - $("#buy_footer").height() + 250);
		}
		
		$("#buy_footer").css("display", "block");
		if(($("#data_div").height() + $("#data_div").offset().top + 25 + $("#buy_footer").height()) < document.documentElement.clientHeight - 50) {
			$("#data_div").css("position", "absolute");
			$("#data_div").css("left", (document.documentElement.clientWidth - $("#data_div").width()) / 2);
			$("#data_div").css("top", (document.documentElement.clientHeight - $("#buy_header").height() - $("#buy_footer").height() - $("#data_div").height()) / 2);
		}
 	}
 	
 	function udregister() {
 		if($("#udRegForm").valid()) {
	 		var params = $("#udRegForm input").serialize();
			params += ("&customer.subjectId=" + getSubjectIdByIndexPage());
			params+="&customer.fromType=2";
			
			$.ajax({
				url : baselocation + "/cus/cusweb!register4ajax.action",
				data : params,
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					if(result.returnMessage == "success") {
						if(typeof(_gaq)!="undefinde"&&_gaq!=null){
							_gaq.push(['_trackPageview', '/virtual/step_enter.html']);//谷歌统计  添加虚拟页面游览量统计
							_gaq.push(['_trackEvent', 'create_account_3', '3','account_3']);//谷歌统计  添加事件追踪统计
						}
						//艾德思奇统计：注册成功。
						//adSageRegisterSuccess(getUserId());
						$('#reg_log_div').css("display", "none");
						if(checkCoursesBought()){
							$('#buy_step2').css("display", "block");
						}
						doafterreg();//注册成功的后续操作
						//注册后分配机会谢添加
						//doafterregChance();
					} else if(result.returnMessage == "emailInUsed") {
						showErrorWin("该邮箱已经注册过。", "");
					} else if(result.returnMessage == "regDangerWord") {
						showErrorWin("请不要输入非法关键字。", "");
					} else if(result.returnMessage == "err.randCode"){
						showErrorWin("验证码错误!","register_div") ;
					}else {
						showErrorWin("注册失败，请稍后再试。", "");
					}
					locking();
				},
				error : function(error) {
					alert('error');
				}
			});
			
 		}
 	}
 	//注册成功的后续操作
	function doafterreg(){
		$.ajax({
				url : baselocation + "/cus/cusweb!doAfterReg.action",
				data : {},
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
				},
				error : function(error) {
				}
			});
	}
 	/**删除订单**/
 	function deleteorder() {
 		SetCookie("coursesbao", "");
 		window.location.reload();
 	}
 	
 	/**单个删除*/
 	
 	function deleteid(id) {
 		var coursesbao = "";
 		var goodspage = getCookie("coursesbao");
 		if(goodspage != null) {
	  	var bao = goodspage.split("/");
		  	for(var a=0; a<bao.length; a++ ) {
			  	var goodbao = bao[a].split(".");
			  	var goodinfo=goodbao[0];
			  	if(id!=goodinfo&&bao[a]!='') {
			  		coursesbao+=bao[a]+"/";
			  	}
		  	}
	  	}
	  	SetCookie("coursesbao", coursesbao);
 		$("#orderlist").html("");
 		getorder();
 		 resetCoupon();//谢添加
 	}

	/**显示更多银行**/
	function showMoreKQBank(){
		if($("#more_kq_bank_ul").css("display") == "none") {
			$("#more_kq_bank_ul").css("display", "block");
			$("#showMoreKQBankLink").html("收起银行请点击 >>");
		} else {
			$("#more_kq_bank_ul").css("display", "none");
			$("#showMoreKQBankLink").html("更多银行请点击 >>");
		}
	}
	
	function off_check(){
		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_2]").attr("checked",false);
		isKQZL = false ;
	}
	
	function off_bank(){
		$("input[name=defaultbank_1]").attr("checked",false);
		$("input[name=defaultbank_2]").attr("checked",false);
		isKQZL = true ;
	}
	function off_checkKQ(PayType){
		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_1]").attr("checked",false);
		isKQZL = true ;
	}
	//真友信用支付
	function off_checkTrueU(){
		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_1]").attr("checked",false);
		isKQZL = false ;
	}	
	function goToZFB() {
//		if(typeof(pageTracker) !="undefined" && pageTracker != null) {
//			pageTracker._trackPageview('/buy/step_zhifu.html');
//		}
		if($("#payprice").html().substring(0, $("#payprice").html().length-1) != 0) {
			if(hasSendCourse && !confirm("订单中包含已通过货到付款方式购买的课程，您确定要继续吗？")){
				return;
			}
			checkContract();
			if(flag) {
				if($("#defaultbank_1").attr("checked") == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4a1', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','OL-AliPay']);
					}
					document.ZFForm.action = baselocation + "/alipay/zfb!goToZFB.action";
					document.ZFForm.submit();
				} else if($("#defaultbank_2").attr("checked") == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4b2', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','OL-CreditCardPay']);
					}
					document.BKForm.action = baselocation + "/alipay/chinaBank!goToChinaBank.action";
					document.BKForm.submit();				
				} else if($("#defaultbank_3").attr("checked") == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c3', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','OL-KuaiQian']);
					}
					document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
					$("#KQPayType").val("00");
					document.KQForm.submit();
				} else if($("#defaultbank_4").attr("checked") == true) {//快钱的信用卡支付
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c4', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','Offline-CreditCard']);
					}
					document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
					$("#KQPayType").val("15");
					document.KQForm.submit();
				} else if($("#defaultbank_5").attr("checked") == true) {//快钱的手机语音支付
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c5', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','Offline-DebitCard']);
					}
					document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
					$("#KQPayType").val("19");
					document.KQForm.submit();
				}else if($("#defaultbank_6").attr("checked") == true) {//快钱的手机语音支付
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c6', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','Offline-DebitCard']);
					}
					document.trueuForm.action = baselocation + "/alipay/trueu!gotoTrueU.action";
					document.trueuForm.submit();
				} else if($("#defaultbank_8").attr("checked") == true) {//银联支付
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c6', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','Offline-DebitCard']);
					}
					document.UnionPayForm.action = baselocation + "/alipay/unionpay!goToUnionPay.action";
					document.UnionPayForm.submit();
				}else if(isKQZL){
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c3', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
					document.KQForm.submit();
				} else {
					alert("请选择支付方式。");
					return;
				}
				
				locking();
				$("#show_box").css("display", "block");
				$("#buy_step_div").css("display", "none");
				$("#send_header_div").css("display", "none");
				$("#send_div").css("display", "none");
			}
		} else {
			alert("订单金额不能为0。");
		}
	}
	
	function getBuyCount(packageId) {
		var entity;
		var userid = getUserId();
		$.ajax({ 
			url : baselocation + "/finance/cashRecord!Getshu.action",
			data : {
				'couid' : packageId,
				'userid' : userid
			},
			type : "post",
			dataType : "json",
			async :false,
			cache : false,
			success : function (result) {
				entity = result.entity;
				if(entity[1] > 0) {
					hasSendCourse = true;
				}
			}
		});		
		return entity[0];			
	}
	
	//判断订单
	function checkContract() {	
		var userId = getUserId();
		var packagesInfo = getCookie("coursesbao");
		if(userId == null) {
			alert("请重新登录。");
		}
		if(packagesInfo != null && packagesInfo != '') {
			var packageStr = packagesInfo.split("/");
		  	for(var a=1; a<packageStr.length; a++ ) {
			  	var packageArrays = packageStr[a-1].split(".");
			  	if(packageArrays[0] != null && packageArrays[0] != '') {
					var buyCount = getBuyCount(packageArrays[0]);
					if(buyCount == 0) {
						makeContract();//确定下订单
						return;
					}
				}
			}
		}
	}
	
	//下订单
	function makeContract() {
		if(isLogin(baselocation + "/")) {
			var courseOrder = getCookie("courseOrder");
			var payprice = $("#payprice").html().substring(0, $("#payprice").html().length-1);
			var payType = 1;
			if($("#defaultbank_2").attr("checked") == true) {
				payType = 3;
			}
			if($("#defaultbank_3").attr("checked") == true){
				payType= 4 ;
			}
			if($("#defaultbank_4").attr("checked") == true){
				payType= 4 ;
			}
			if($("#defaultbank_5").attr("checked") == true){
				payType= 4 ;
			}
			if($("#defaultbank_6").attr("checked") == true){
				payType= 6 ;
			}
			if($("#defaultbank_8").attr("checked") == true){
				payType= 8 ;
			}
			if(isKQZL){
				payType= 4 ;
			} 
			if(courseOrder != null && courseOrder != "" && payprice !=0 && payprice != "0") {
				$.ajax({
					url: baselocation + "/alipay/zfb!makeContract.action",
					data:{//谢添加
						"payType" : payType,"couponCode":$("#couponCode").val()
					},
					type:"post",
					dateType: "json",
					async : false,
					success: function(result) {
						if(result==''){
							alert("对不起,您没有购买升级课程的权限！") ;
						}
						else{
							var aa = eval("("+result+")");
							var contractInfo = aa.returnMessage.split(",");
							//发送订单成功至用户
							sendMsgToUserMobile(contractInfo[0]);
							//支付宝参数
							$("#oderid").html(contractInfo[0]);
							$("#ode").val(contractInfo[0]);
							$("#jinee").val(contractInfo[1]);
							//网银在线参数
							$("#chinaBankOid").val(contractInfo[0]);
													
							//快钱参数
							$("#KQOrderId").val(contractInfo[0]);
							//真友参数
							$("#trueuorderId").val(contractInfo[0]);
							//银联在线
							$("#unionPayOid").val(contractInfo[0]);
							flag = true;
						}
					},
					error : function() {
						alert("error");
					}
				});
			} else {
				flag= false;
			}
		}
	}
	
	//查看是否支付成功
	function isPaySuccess() {
		var entity = null;
	 	var oderid = $("#oderid").html();
	 	var userid = getUserId();
		$.ajax({
		 	url: baselocation + "/finance/contract!getIsoder.action",
		 	data: {
		 		'oderid' :oderid,
		 		'userid' : userid
		 	},
		 	type : "post",
		 	dataType :"json",
		 	async : false,
		 	cache : false,
		 	success : function (result){
		 		entity = result.returnMessage;
				var contractInfo = entity.split(";");
				if(contractInfo[0]==0){
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_fail1.html']);//谷歌统计  添加虚拟页面游览量统计
					}
				}else{
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackEvent', 'Transaction', 'Pay','Confirmed']);
						_gaq.push(['_trackPageview', '/virtual/step_pay1.html']);//谷歌统计  添加虚拟页面游览量统计
					
					var str1 = contractInfo[0];
					var str2 = contractInfo[1];
				
					var ctInfo = str1.split(",");
					var ctId = ctInfo[0];
					var ctPrice = ctInfo[1];
					
					_gaq.push(['_addTrans',
						    ctId,           		// order ID - required
						    '',  					// affiliation or store name
						    ctPrice,          		// total - required
						    '',           			// tax
						    '',              		// shipping
						    '',       				// city
						    '',     				// state or province
						    ''             			// country
				  		]);
					
					
					var crInfoList = str2.split("#");	
				
					for(var i = 0; i  < crInfoList.length; i++){
						if(crInfoList[i]!=''){
							var vr = crInfoList[i].split(",");
							_gaq.push(['_addItem',
						    ctId,           	// order ID - required
						    vr[0],           	// SKU/code - required
						    vr[1],       		// product name
						    vr[1], 				// category or variation
						    vr[2],          	// unit price - required
						    '1'               	// quantity - required
						  ]);
						}
					}
					_gaq.push(['_trackTrans']); //submits transaction to the Analytics servers
		 		}
			}
		 	}
		});
	 	return entity;
	}
		
	//弹窗效果
	function locking(){
		$("#buy_black").css("display", "block");
		document.getElementById("buy_black").style.width = document.documentElement.clientWidth + "px";
		document.getElementById("buy_black").style.height = document.documentElement.scrollHeight + "px";
	} 
	
	function outing(){
		$("#web_top_black").css("display", "none");
		$("#show_box").css("display", "none");
		$("#buy_black").css("display", "none");
	}
	
	function exitPay(){
		outing();
	    if(isPaySuccess() == 0) {
	    	showErrorWin("付款尚未成功，请重新或继续付款。", "buy_step_div");
	    } else {
	    	window.open(baselocation + '/cus/cuslimit!toPayOk.action', '_self');
	    }
	}
	
	function repay() {
		closeWin();
		$("#buy_step_div").css("display", "block");
		$("#buy_black").css("display", "block");
	}
	
	function closeWin(){ 
		$("#buy_black").css("display", "none");
		$("#show_box").css("display", "none");
		$("#web_top_black").css("display", "none");
		isPaySuccess() ;
	}
	
	
	function choseBuyWay() { 
		if($("#bank_radio").attr("checked")) {
			$("#send_div").hide();
			$("#bank_div").show();
		} else {
			$("#send_div").show();
			$("#bank_div").hide();
			window.setTimeout(function () {$("#defaultbank_1").attr("checked", "true");$("#more_bank_ul").css("display", "none");}, 500);
		}
	}

	
	function confirmAddress() {
		if($("#sendForm").valid()) {
			if($("#sel_province").val() < 1) {
				$("#area_message").html("请选择省份。");
			} else if($("#sel_city").val() < 1) {
				$("#area_message").html("请选择城市。");
			} 
			//else if($("#sel_town").val() < 1) {
				//$("#area_message").html("请选择地区。");
			//} 
			else if($("#addressMobile").val().trim() == "" && $("#addressTel").val().trim() == "") {
				showErrorWin("请输入手机号码或固定电话号码。", "");
			} else {
				//$("#area_message").html("");
				//$("#buy_step2").css("display", "none");
				//$("#buy_step3").css("display", "block");
				if(hasSendCourse && !confirm("订单中包含已通过货到付款方式购买的课程，您确定要继续吗？")){
					return;
				}
				if(typeof(_gaq)!="undefinde"&&_gaq!=null){
					_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
					_gaq.push(['_trackEvent', 'cheackout_shipping_4d', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
				}
				processReceiverInfo();
				createSendContract();
			}
		}
	}
	
	function initArea(id, index){
		var parentId = 1;
		if(id != null && id != 0 && !isNaN(id)) {
			parentId = id;
		}
		$.ajax({
			url : baselocation + "/cus/areaWeb!getAreasByParentId.action",
			data : {
				"queryAreaCondition.parentId" : parentId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result == null || result.entity == null) {
					return;
				}
				var provinces = result.entity;
				var html = '';
				for(var i=0; i<provinces.length; i++) {
					html += "<option value='" + provinces[i].id + "'>" + provinces[i].areaName + "</option>";
				}
				if(index == 0) {
					//$(html).appendTo("#sel_province");
					$("#sel_province").html(html);
				} else if(index == 1) {
					$("#sel_city").html("");
					$("#sel_town").html("");
					$("<option value='0'>--------</option>" + html).appendTo("#sel_city");
					$("<option value='0'>--------</option>").appendTo("#sel_town");
				} else {
					$("#sel_town").html("");
					$("<option value='0'>--------</option>" + html).appendTo("#sel_town");
				}
			},
			error : function(error) {
				//alert('error');
			}
		});
	}
	
	function backToStep2() {
		$("#bank_div").css("display", "none");
		$("#buy_step2").css("display", "block");
		$("#buy_step3").css("display", "none");
	}
	
	function processReceiverInfo(){
		var receiverInfo = $("#receiverName").val()
							+ ", " + $("#sel_province option:selected").text()
							+ ", " + $("#sel_city option:selected").text()
							+ ", " + $("#sel_town option:selected").text()
							+ ", " + $("#detailAddress").val()
							+ ", " + $("#postCode").val();;
		if($("#addressMobile").val() != "") {
			$("#address_mobile").val($("#addressMobile").val());
			receiverInfo += ", " + $("#addressMobile").val();
		} else {
			$("#address_mobile").val($("#addressTel").val());
			receiverInfo += $("#addressTel").val();
		}
		$("#receiver_message").html(receiverInfo);
		$("#buy_way_message").html("快递送货上门，" + $("#sendTime option:selected").text() + "（您将在48小时内收到我们的快递）");
		//checkCoursesBoughtForUd();
	}
	
	function backToData() {
		$("#buy_step_div").css("display", "none");
		$("#data_div").css("display", "block");
		outing();
		isBackToData = true;
	}
	
	function checkCoursesBoughtForUd() {
		var goodspage = getCookie("coursesbao");
		var html = "<tr class='title'><th class='th_left'>课程名称</th><th>主讲人</th><th>课时</th><th>价格</th><th>小计</th></tr>";
		var zongjia = 0;
		var availableCount = 0;
		
		if(goodspage == null) {
			html += "<tr><td colspan='6' align='center'>订单为空</td></tr>";
		}
		
	  	if(goodspage != null) {
	  		var courseOrderStr = getUserId() + "#";
	  		var bao=goodspage.split("/");
	  		
		  	for(var a=0; a<bao.length-1; a++ ) {
		  		var goodbao = bao[a].split(".");
			  	var courrs = goodbao[4].split("#");
			  	var teacher = "";
			  	var timeslength = 0;
			  	
			  	var buyCount = getBuyCount(goodbao[0]);
				if(buyCount == 0) {
					for(var i=0; i<courrs.length; i++ ) {	
				  		var kechengfen = courrs[i].split(",");
				  		timeslength += parseInt(courrs[i].split(",")[3]);//课时
				  		teacher += kechengfen[2] + "&nbsp;&nbsp;";
				  		courseOrderStr += kechengfen[0] + "," + kechengfen[5] + "," + goodbao[0] + "#";
				  	}
				  	
				  	if(a % 2 == 0) {
				  		html += "<tr>"
				  	} else {
				  		html += "<tr class='bj'>"
				  	}
				  	
					html += "<td>"
							+ goodbao[1] + "</td><td>"
							+ teacher + "</td><td>"
							+ timeslength + "</td><td>￥"
							+ goodbao[2] + "</td><td>￥"
							+ goodbao[2] + "</td></tr>";
							
					availableCount ++;
					zongjia+=parseInt(goodbao[2]);
				} else {
					for(var i=0; i<courrs.length; i++ ) {	
						var kechengfen = courrs[i].split(",");
						timeslength += parseInt(courrs[i].split(",")[3]);//课时
						teacher += kechengfen[2] + "&nbsp;&nbsp;";
					}
					
					html += "<tr><td style='color:#FF0000'>"
							+ goodbao[1] + "</td><td style='color:#FF0000'>"
							+ teacher + "</td><td style='color:#FF0000'>"
							+ timeslength + "</td><td style='color:#FF0000'>￥"
							+ goodbao[2] + "</td><td style='color:#FF0000'>￥"
							+ goodbao[2] + "</td></tr>";
				}
		  	}	
	  	}
	  	
	  	$("#ud_course_number").html(availableCount + " 件");
	  	$("#ud_orderlist").html(html);
	  	$("#ud_course_price").html(zongjia + "元");
	  	$("#ud_total_price").html((zongjia + 10) + "元");
		SetCookie("courseOrder", courseOrderStr);
		SetCookie("totalPrice1", zongjia);
		if(zongjia != 0) {
			return true;
		} else {
			alert("订单金额不能为0。");
			return false;
		}
	}
	
	function processAddress() {
		initArea(1, 0);
		initArea_sf(1, 0);
		
		$.ajax({
			url : baselocation + "/cus/addrWeb!getFirstAddressByCusId.action",
			dataType : "json",
			success : function(result) {
				if(result.entity == null) {
					changedAddress();
					initArea($("#sel_province").find("option:eq(0)").val(), 1);
					initArea($("#sel_city").find("option:eq(1)").val(), 2);
					//只添加地址作用
					initArea_sf($("#sel_province_sf").find("option:eq(0)").val(), 1);
					initArea_sf($("#sel_city_sf").find("option:eq(1)").val(), 2);
					//只添加地址作用
				} else {
					$("#addressId").val(result.entity.id);
					$("#sel_province").val(result.entity.provinceId);
					initArea(result.entity.provinceId, 1);
					$("#sel_city").val(result.entity.cityId);
					initArea(result.entity.cityId, 2);
					$("#sel_town").val(result.entity.townId);
					$("#receiverName").val(result.entity.receiver);
					$("#detailAddress").val(result.entity.address);
					$("#postCode").val(result.entity.postCode);
					if(result.entity.mobile.length != 11 || result.entity.mobile.indexOf("-")>-1) {;
						$("#addressTel").val(result.entity.mobile);
					} else {
						$("#addressMobile").val(result.entity.mobile);
					}
					$("#sendTime").val(result.entity.sendTime);
					
					//只添加地址用
					
					$("#sel_province_sf").val(result.entity.provinceId);
					initArea_sf(result.entity.provinceId, 1);
					$("#sel_city_sf").val(result.entity.cityId);
					initArea_sf(result.entity.cityId, 2);
					$("#sel_town_sf").val(result.entity.townId);
					//$("#receiverName_sf").val(result.entity.receiver);
					//$("#detailAddress_sf").val(result.entity.address);
					//$("#postCode_sf").val(result.entity.postCode);
					if(result.entity.mobile.length != 11 || result.entity.mobile.indexOf("-")>-1) {;
						//$("#addressTel_sf").val(result.entity.mobile);
					} else {
						//$("#addressMobile_sf").val(result.entity.mobile);
					}
					//只添加地址作用
				} 
			},
			error : function(error) {
				//alert('error');
			}
		});
	}
	function toforpwd(){
 	 	window.open("http://highso.org.cn/static/web/column/91/index_1.shtml");
 	}
	function initFormValid() {
		jQuery.validator.addMethod("mobile", function(value, element) {
			var pattern = /^1{1}[0-9]{10}$/;
			return this.optional(element) || pattern.test(value);
		});
		jQuery.validator.addMethod("telephone", function(value, element) {
			var pattern = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
			return this.optional(element) || pattern.test(value);
		});
		
		$("#udRegForm").validate({
	        rules: {
	        	"customer.cusPwd" : {
	        		required : true,
	        		maxlength : 20,
	        		minlength : 6
	        	},
	        	"customer.cusName" : {
	        		required : true,
	        		maxlength : 20
	        	},
	        	"customer.mobile" : {
	        		required : true,
	        		mobile : true
	        	},
	        	"customer.email" : {
	        		required : true,
	        		email : true,
	        		maxlength : 50,
	        		remote : {
		                data: {
	                    	'customer.email': function () {
	                    		return $("input[id=udRegEmail]").val();
	                    	}
		                },
		                async : false,
		                url : baselocation + "/cus/cusweb!checkEmail.action",
		                type : "post"
	                }
	        	},
	        	"randomCode":{
	        	    required:true,
	        	    maxlength:4,
	        	    remote : {
		                data: {
	                    	'randonCode': function () {
	                    		return $("input[id=randomcode]").val();
	                    	}
		                },
		                async : false,
		                url : baselocation + "/cus/cusweb!checkRandomCode.action",
		                type : "post"
	                }
	        	},	        	
	        	"highsoAgreement" : {
	        		required : true
	        	}
	        },
	    	messages : {
	    		"customer.email" : {			                
	    			remote : "该邮箱已经注册过 <a style='color:#333;' href='javascript:toforpwd()' >[找回密码?]</a>",			    		
	        		required : "请填写邮箱",
	        		email : "请输入正确的邮箱地址"
	        	},
	        	"customer.cusName" : {
	        		required : "请输入昵称",
	        		maxlength : "昵称不能超过20个字"
	        	},
	        	"customer.cusPwd" : {
	        		required : "密码必须填写",
	        		maxlength : "密码为6到20位",
	        		minlength : "密码为6到20位"
	        	},
	        	"udCusPwdConfirm" : {
	        		equalTo : "密码不一致"
	        	},
	        	"customer.mobile" : {
	        		required : "请输入手机号码",
	        		mobile : "请输入正确的手机号码"
	        	},
	        	"randomCode":{
	        	    required:"请输入验证码",
	        	    maxlength:"验证码是4位",
	        	    remote:"验证码错误"
	        	    
	        	},
	        	"highsoAgreement" : {
	        		required : "请接受HighSo协议"
	        	}
	    	},
	    	errorPlacement: function(error, element) {
	            if ( element.is(":radio") )
	                error.appendTo( element.parent().next().next() );   
	            else if ( element.is(":checkbox") )
	            	error.appendTo(element.parent().find("span")).css("color", "red");
	            else
	            	error.appendTo(element.parent().parent().next().find("td:last").html("")).css("color", "red");
	        }, 
	        success: function(label) {
	        	label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        }
		});
		
		$("#sendForm").validate({
	        rules: {
	        	"address.receiver" : {
	        		required : true,
	        		maxlength : 30,
	        		minlength : 2
	        	},
	        	"address.address" : {
	        		maxlength : 100,
	        		required : true
	        	},
	        	"address.postCode" : {
	        		required : true,
	        		digits : true,
	        		maxlength : 6,
	        		minlength : 6
	        	},
	        	"addressMobile" : {
	        		mobile : true
	        	},
	        	"addressTel" : {
	        		telephone : true
	        	}
	        },
	    	messages : {
	        	"address.receiver" : {
	        		required : "请输入收货人名称。",
	        		maxlength : "收货人名称不能超过30个字。",
	        		minlength : "收货人名称不能少于两个字。"
	        	},
	        	"address.address" : {
	        		maxlength : "您输入的地址过长。",
	        		required : "请输入地址。"
	        	},
	        	"address.postCode" : {
	        		required : "请输入邮编。",
	        		digits : "请输入正确的邮编。",
	        		maxlength : "请输入正确的邮编。",
	        		minlength : "请输入正确的邮编。"
	        	},
	        	"addressMobile" : {
	        		mobile : "手机号码不正确。"
	        	},
	        	"addressTel" : {
	        		telephone : "电话号码不正确。"
	        	}
	    	},
	    	errorPlacement: function(error, element) {
	            if ( element.is(":radio") )
	                error.appendTo( element.parent().next().next() );   
	            else if ( element.is(":checkbox") )
	            	error.css("color", "red").appendTo(element.parent());
	            else
	            	error.css("color", "red").appendTo(element.parent());
	        }, 
	        success: function(label,element) {
	        	label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        	label.addClass("success");
	        }
		});
		
		$("#sendForm_sf").validate({
	        rules: {
	        	"address.receiver_sf" : {
	        		required : true,
	        		maxlength : 30,
	        		minlength : 2
	        	},
	        	"address.address_sf" : {
	        		maxlength : 100,
	        		required : true
	        	},
	        	"address.postCode_sf" : {
	        		required : true,
	        		digits : true,
	        		maxlength : 6,
	        		minlength : 6
	        	},
	        	"addressMobile_sf" : {
	        		mobile : true,
	        		maxlength : 15
	        	},
	        	"addressTel_sf" : {
	        		telephone : true,
	        		maxlength : 15
	        	}
	        },
	    	messages : {
	        	"address.receiver_sf" : {
	        		required : "请输入收货人名称。",
	        		maxlength : "收货人名称不能超过30个字。",
	        		minlength : "收货人名称不能少于两个字。"
	        	},
	        	"address.address_sf" : {
	        		maxlength:"您输入的地址过长",
	        		required : "请输入地址。"
	        	},
	        	"address.postCode_sf" : {
	        		required : "请输入邮编。",
	        		digits : "请输入正确的邮编。",
	        		maxlength : "请输入正确的邮编。",
	        		minlength : "请输入正确的邮编。"
	        	},
	        	"addressMobile_sf" : {
	        		mobile : "手机号码不正确。",
	        		maxlength : "请输入正确的手机号码"
	        	},
	        	"addressTel_sf" : {
	        		telephone : "电话号码不正确。",
	        		maxlength : "请输入正确的电话号码"
	        	}
	    	},
	    	errorPlacement: function(error, element) {
	            if ( element.is(":radio") )
	                error.appendTo( element.parent().next().next() );   
	            else if ( element.is(":checkbox") )
	            	error.css("color", "red").appendTo(element.parent());
	            else
	            	error.css("color", "red").appendTo(element.parent());
	        }, 
	        success: function(label,element) {
	        	label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        	label.addClass("success");
	        }
		});
		
	}
	
	function changedAddress() {
		$("#changeAddress").val("true");
	}
	
	function createSendContract(){
//		if(typeof(pageTracker) !="undefined" && pageTracker != null) {
//			pageTracker._trackPageview('/buy/step_qrxx.html');
//		}
		$.ajax({
			url : baselocation + "/finance/contract!makeContractAndCashRecord.action",
			data : {
				"address.mobile" : $("#address_mobile").val(),
				"changeAddress" : $("#changeAddress").val(),
				"address.receiver" : $("#receiverName").val(),
				"address.provinceId" : $("#sel_province").val(),
				"address.cityId" : $("#sel_city").val(),
				"address.townId" : $("#sel_town").val(),
				"address.address" : $("#detailAddress").val(),
				"address.postCode" : $("#postCode").val(),
				"address.sendTime" : $("#sendTime").val(),
				"addressId" : $("#addressId").val(),
				"couponCode":$("#couponCode").val()
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					$("#buy_step2").css("display", "none");
					$("#buy_step4").css("display", "block");
					//adSageGetOrderInfo(result.entity[1], result.entity[0]);
					/*-发送短信至用户-*/
					sendAsyncMsgToUserMobile(1);
				}
				if(typeof(_gaq)!="undefinde"&&_gaq!=null){
					_gaq.push(['_trackPageview', '/virtual/step_pay2.html']);//谷歌统计  添加虚拟页面游览量统计
				}
			},
			error : function(error) {
				alert('error_createSendContract');
			}
		});
	}
	
	function closeContractWin(){
		$("#buy_step_div").css("display", "none");
		outing();
	}
	
	function checkRemeberMe() {
		var remeberMe = getCookie("remeberMe");
		if(remeberMe != null && remeberMe != '') {
			var myInfo = remeberMe.split(",");
			if(myInfo != null && myInfo != '' && myInfo[0] == "true") {
				$("#login_email_3").val(myInfo[1]);
				$("#login_pwd_3").val(myInfo[2]);
			}
		}
 	}
 

	function createSendContract_SF(){
		$.ajax({
			url : baselocation + "/finance/contract!saveAddressSF.action",
			data : {
				"address.mobile" : $("#address_mobile_sf").val(),
				"changeAddress" : $("#changeAddress_sf").val(),
				"address.receiver" : $("#receiverName_sf").val(),
				"address.provinceId" : $("#sel_province_sf").val(),
				"address.cityId" : $("#sel_city_sf").val(),
				"address.townId" : $("#sel_town_sf").val(),
				"address.address" : $("#detailAddress_sf").val(),
				"address.postCode" : $("#postCode_sf").val(),
				//"address.sendTime" : $("#sendTime_sf").val(),
				"addressId" : $("#addressId_sf").val()
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					updateAddrFirstsf(result.entity);
				}
			},
			error : function(error) {
				alert('error');
			}
		});
	}
	
	function processReceiverInfo_SF(){
		var receiverInfo = $("#receiverName_sf").val()
							+ ", " + $("#sel_province_sf option:selected").text()
							+ ", " + $("#sel_city_sf option:selected").text()
							+ ", " + $("#sel_town_sf option:selected").text()
							+ ", " + $("#detailAddress_sf").val()
							+ ", " + $("#postCode_sf").val();;
		if($("#addressMobile_sf").val() != "") {
			$("#address_mobile_sf").val($("#addressMobile_sf").val());
			receiverInfo += ", " + $("#addressMobile_sf").val();
		} else {
			$("#address_mobile_sf").val($("#addressTel_sf").val());
			receiverInfo += $("#addressTel_sf").val();
		}
	}
		
 	
 	function initArea_sf(id, index){
		var parentId = 1;
		if(id != null && id != 0 && !isNaN(id)) {
			parentId = id;
		}
		$.ajax({
			url : baselocation + "/cus/areaWeb!getAreasByParentId.action",
			data : {
				"queryAreaCondition.parentId" : parentId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
			
				if(result == null || result.entity == null) {
					return;
				}
				var provinces = result.entity;
				var html = '';
				for(var i=0; i<provinces.length; i++) {
					html += "<option value='" + provinces[i].id + "'>" + provinces[i].areaName + "</option>";
				}
				if(index == 0) {
					//$(html).appendTo("#sel_province_sf");
					$("#sel_province_sf").html(html);
				} else if(index == 1) {
					$("#sel_city_sf").html("");
					$("#sel_town_sf").html("");
					$("<option value='0'>--------</option>" + html).appendTo("#sel_city_sf");
					$("<option value='0'>--------</option>").appendTo("#sel_town_sf");
				} else {
					$("#sel_town_sf").html("");
					$("<option value='0'>--------</option>" + html).appendTo("#sel_town_sf");
				}
			},
			error : function(error) {
				//alert('error');
			}
		});
	}
	
 	//添加到常用地址
 	function confirmAddress_SF() {
		if($("#sendForm_sf").valid()) {
			if($("#sel_province_sf").val() < 1) {
				$("#area_message_sf").html("请选择省份。");
			} else if($("#sel_city_sf").val() < 1) {
				$("#area_message_sf").html("请选择城市。");
			}else if($("#addressMobile_sf").val().trim() == "" && $("#addressTel_sf").val().trim() == "") {
				showErrorWin("请输入手机号码或固定电话号码。", "");
			} else {
				$("#area_message_sf").html("");
				processReceiverInfo_SF();
				createSendContract_SF();
				//showAddressListInbuyPage(1);
				showAddressListInbuyPage(0);
			}
		}
	}
		

	//页面初试化显示地址
	var ishaveaddress=0;//是否存在地址
	function showAddressListInbuyPage(isadd){
	   	 $.ajax({
			url : baselocation + "/cus/addrWeb!showAddressListInbuyPage.action",
			dataType : "json",
			cache : false,
			success : function(result) {
				 var alladdressinfo="";
				 var ishavefirst=0;
				 ishaveaddress=0;
				 $("#disp_shouhuoren").html("");
				 $("#disp_sheng").html("");
				 $("#disp_jie").html("");
				 $("#disp_phone").html("");
				 $("#addressTel_sf").html("");	
				 if (result.returnMessage=="success"){
				 	alladdressinfo="<h4>收货地址</h4>";
				 	$.each(result.entity,function(name,value){
				 		ishaveaddress=ishaveaddress+1;
			        	var tmpinf="";
				 		if(value.isFirst==1){//常用地址
				 			tmpinf="<p><input name='addradio'  type='radio' checked value='"+value.id+"' /><strong>"+value.receiver+" "+value.provinceName+""+value.cityName+""+value.townName+  ""+value.address+"</strong> <a href='javascript:deladdressbyid("+value.id+")'>删除</a></p>"
			           		alladdressinfo=alladdressinfo+tmpinf; 
				 			ishavefirst=1;
				 			$("#disp_shouhuoren").html(value.receiver);
				 	 	 	$("#disp_sheng").html(value.provinceName+""+value.cityName+""+value.townName);
				 	 	 	$("#disp_jie").html(value.address);
				 	 	 	$("#disp_phone").html(value.mobile);
				 		}else{
				 			tmpinf="<p><input name='addradio'  type='radio'  value='"+value.id+"' /><strong>"+value.receiver+" "+value.provinceName+""+value.cityName+""+value.townName+  ""+value.address+"</strong> <a href='javascript:deladdressbyid("+value.id+")'>删除</a></p>"
			            	alladdressinfo=alladdressinfo+tmpinf; 
				 		}
				 	 });
				 	 addnewaddshowflag=0;
				 	 $("#modifyaddressbtn").show();//显示修改按钮
				 	 $("#modiralink").html("修改");
				 	 
				 	 if (ishavefirst==1){ 
				 	 	 if (isadd!=1){
				 	 	 	$("#disaddnewaddresstable").show();//显示默认收货地址
				 	 	 }
				 	 	 $("#addnewaddress").hide();//新添加地址的div隐藏
				 	 	 $("#usedAdress").hide();////已经存在地址列表radio隐藏
				 	 }else{
					 	 //$("#usedAdress").show();
					 	 $("#addnewaddress").show();//添加新的地址div
					 	 $("#add_addr_addtoc").show();
					 	 $("#addr_btn_confir").show();//确认收货人信息显示
					 	 $("#newaddresstable").show()//增加的表格
					 	 $("#modifyaddressbtn").hide();//显示修改按钮
				 	 }
				 	 if (isadd==1){
				 	 	 $("#addnewaddress").show();//新添加地址的div隐藏
				 	 	 $("#newaddresstable").show()//增加的表格
				 	 	 //$("#usedAdress").show();//已经存在地址列表radio隐藏
				 	 }
				 	$("#addressaddr_box").show();
				 }else{//没有收获地址
				 	$("#usedAdress").html(alladdressinfo);//已经存在地址列表radio
				 	if(isadd!=1){
					 	$("#addressaddr_box").hide();
					 	$("#usedAdress").hide();//已经存在地址列表radio
					 	$("#modifyaddressbtn").hide();//显示修改按钮
				 	}else{
					 	 $("#addnewaddress").show();//添加新的地址div
					 	 $("#usedAdress").hide();//已经存在地址列表radio
					 	 $("#addr_btn_confir").show();//确认收货人信息显示
					 	 $("#add_addr_addtoc").show();
					 	 $("#newaddresstable").show()//增加的表格
					 	 $("#modifyaddressbtn").hide();//显示修改按钮
				 	}
				 }
				 //$("#usedAdress").html(alladdressinfo);//已经存在地址列表radio
			},
			error : function(error) {
				alert("error");
			}
		});
		 
	}

	
	//删除收获地址
	function deladdressbyid(id) {
		if(window.confirm("确认删除该收货地址？")) {
			$.ajax({
				url : baselocation + "/cus/addrlimit!deleteAddresssf.action",
				data:{"address.id":id},
				type : "post",
				dataType : "json",
				cache : false,
				success : function(result) {
					showAddressListInbuyPage(1);
				},
				error : function(error) {
					showAddressListInbuyPage(0);
				}
			});
		}
	}
	
	//设置为常用地址
	function updateAddrFirstsf(id) {
		$.ajax({
			url : baselocation + "/cus/addrlimit!updateAddrFirstsf.action",
			data:{"address.id":id},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
			},
			error : function(error) {
				alert("error");
			}
		});
	}
	
	//确认收货地址
	function confirmDefaultAdd(){
		var ischeched =$("input[@type=radio][name=addradio][checked]").val();
		if(typeof(ischeched)=="undefined"){
			showErrorWin("请先选择收货地址！");
		}else{
			updateAddrFirstsf(ischeched);
			showAddressListInbuyPage(0);
		}
	}
	
	//点击填写收货人信息
	var addnewaddshowflag=0;
	function addnewaddshow(imodify){
		 $("#disp_shouhuoren").html("");
		 $("#disp_sheng").html("");
		 $("#disp_jie").html("");
		 $("#disp_phone").html("");
		 $("#addressTel_sf").html("");
		 
		 
		var addnewaddshowstyle= document.getElementById("addressaddr_box").style.display;
		var modiralinkstyle= document.getElementById("modifyaddressbtn").style.display;
		
		if(addnewaddshowflag==1){//addnewaddshowstyle != 'none'
			addnewaddshowflag=0;
			
			if(modiralinkstyle!='none'){
				$("#modiralink").html("修改");
			}
			if (imodify==1){
				showAddressListInbuyPage(0);
			}else{
				$("#addressaddr_box").hide();//整个div
				$("#addnewaddress").hide();
				$("#disaddnewaddresstable").hide();//显示默认收货地址
			}
			$("#usedAdress").hide();//已经存在地址列表radio
		}else{
			addnewaddshowflag=1;
			//$("#modiralink").html("退出修改状态");
			$("#addressaddr_box").show();//整个div
			
			if (ishaveaddress>0){
				//$("#usedAdress").hide(); //已经存在的地址radio
			}else{
				$("#usedAdress").hide(); //已经存在的地址radio
			}
			if(modiralinkstyle!='none'){
				$("#modiralink").html("退出修改状态");
			}
			$("#addnewaddress").show(); //新添加收货地址div框
			$("#newaddresstable").show()//增加的表格
			$("#add_addr_addtoc").show();//添加地址按钮
			$("#addr_btn_confir").show();//确认收货地址
			$("#disaddnewaddresstable").hide();//显示默认收货地址
		}
	}
	
	//谢 开始
 	function jiajian(){
 		var str=$("#jiajian").html();
 		if(str=="(-)")
 			{
 			$("#jiajian").html("(+)");
            $("#coupondiv").hide();
 			}
 		if(str=="(+)")
			{
			$("#jiajian").html("(-)");
        $("#coupondiv").show();
			}
 	}
 	function addcode(){
 		if(sellWayCount==0){
 			alert("订单金额为0时不能使用优惠券");
 			return;
 		}
 		var str=$("#couponCode").val().trim();
 		 			
 		if(str==""||str==null){
 		alert("请输入优惠券编号");
 		return;
 		}
 		 $.ajax({
 			   type: "POST",
 			   url: baselocation+"/alipay/zfb!getCouponByCode.action",
 			  dataType : "json",
 			   data: "couponCode="+str+"&sellIds="+sellIds,
 			   success: function(msg){
 				   var obj=msg.entity;
 				   if(obj==null){
 					  canclecode();
 					   alert(msg.returnMessage);//判断优惠券状态
 					   return;
 				   }
 				  var fanwei=obj.couponType.subjectId;
 				  if(sellWayCount>1){
 					  if((msg.jumpType==false ||obj.couponType.subjectId!=msg.jumpUrl)&&fanwei!=0){//说明有多个项目提示不可以
	 	 					 alert("只适用于"+obj.couponType.subjectName);	 				    		 
	 	 					 return;
	 				      }else{	 				    	
 						 $.ajax({
 	 			 			   type: "POST",
 	 			 			   url: baselocation+"/alipay/zfb!GetSellWayByCodeForId.action",
 	 			 			  dataType : "json",
 	 			 			   data: "couponId="+obj.id,
 	 			 			   success: function(msg){
 	 			 				   var zj=msg.entity;
 	 			 				   if(zj)
 	 			 					   {
 	 			 					 couponCodeJS(obj);
 	 			 					 return;
 	 			 					   }
 	 			 				    else{
 	 			 				    	canclecode();
 	 			 					   alert("此优惠券无效");
 	 			 					 return;
 	 			 				       }
 	 			 			   }
 	 					 });
 	 					 return;
				  }
 					 
 				  }else{
 					 $.ajax({
 			 			   type: "POST",
 			 			   url: baselocation+"/alipay/zfb!GetSellWayByCode.action",
 			 			  dataType : "json",
 			 			   data: "subjectId="+fanwei+"&sellId="+sellId+"&couponId="+obj.id,
 			 			   success: function(msg2){
 			 				   var zj=msg2.entity;
 			 				   if(zj)
 			 					   {
 			 					 if((msg.jumpType==false ||obj.couponType.subjectId!=msg.jumpUrl)&&fanwei!=0){//说明有多个项目提示不可以
 			 	 					 alert("只适用于"+obj.couponType.subjectName);	 				    		 
 			 	 					 return;
 			 				      }
 			 					 couponCodeJS(obj);
 			 					 return;
 			 					   }else{
 			 				    canclecode();
 			 					   alert("此优惠券无效");
 			 					 return;
 			 					 }
 			 			   }
 					 });
 					 return;
 				  }
 				
 				 return;
 			   },
 		    error : function (error) {
 	   				 alert(error.responseText);
 				}
 			});
 	}
 	function couponCodeJS(obj){
 		if(obj.couponType.leastPrice>zongjia)
 			{
 			alert("消费金额不足最低消费"+obj.couponType.leastPrice+"元");
 			return;
 			}
 	   $("#codeName").html(obj.title);
       var zhe=obj.couponType.preferentialPrice;
       if(obj.couponType.cType==1)
    	   {           	  
           $("#codeMoney").html(zhe+"折"); 
           $("#yhmoney").html((zongjia-((zongjia*(zhe*10/100)))).toFixed(2)+"元");
           $("#payprice").html(((zongjia*(zhe*10/100)).toFixed(2))+"元");  
    	   }
        if(obj.couponType.cType==2)
        	{
             $("#codeMoney").html(obj.couponType.preferentialPrice+"元");
             $("#yhmoney").html((zongjia-(zongjia-zhe)).toFixed(2)+"元");
             $("#payprice").html((zongjia-zhe)+"元");
        	}
        $("#codesucess").show();  
        setTimeout(function(){$("#codesucess").hide();},3000);  
         couponId=obj.id;couponCode=obj.title; 
         onlycanclecode();
 	}
 	function resetCoupon(){
 		if(couponId==0)
 			{
 			return;
 			}
 		else{
 			 $.ajax({
 				   type: "POST",
 				   url: baselocation+"/alipay/zfb!updateCouponForStateReset.action",
 				   data: "couponId="+couponId,
 				   success: function(msg){addcode();}
 			 });
 			
 		}
 	}
 	function inputcode()
 	{
 	if($("#couponCode").val()!="")
 	{
    $('#tjcode').removeAttr('disabled');
 	$("#tjcode").attr("class","coupon_btn");
 	$("#canclecode").hide();
 	}
 	else{
 		$("#tjcode").attr("class","coupon_btn coupon_btn_02");
 		$("#tjcode").attr("disabled","disabled");
 	 	$("#canclecode").hide();
 	}
 	}
 	function canclecode()
 	{
 		$("#couponCode").val("");
 		$("#couponCode").removeAttr('disabled');
 		$("#tjcode").show();
 		$("#tjcode").attr("class","coupon_btn coupon_btn_02"); 		
 		$("#canclecode").hide();
 		getorder();
 	}
 	function onlycanclecode()
 	{
 		$("#tjcode").hide();
	$("#canclecode").show();
 		$("#couponCode").attr("disabled","disabled");
 	}
 	//谢结束

	//公务员收货地址
 	function confirmAddress_gwy() {
		if($("#sendForm").valid()) {
			if($("#sel_province").val() < 1) {
				$("#area_message").html("请选择省份。");
			} else if($("#sel_city").val() < 1) {
				$("#area_message").html("请选择城市。");
			}else if($("#addressMobile").val().trim() == "" && $("#addressTel").val().trim() == "") {
				showErrorWin("请输入手机号码或固定电话号码。", "");
			} else {
				processReceiverInfo_gwy();
				createSendContract_gwy();
			}
		}
	}
	
	function createSendContract_gwy(){
		$.ajax({
			url : baselocation + "/finance/contract!saveAddressSF.action",
			data : {
				"address.mobile" : $("#address_mobile").val(),
				"changeAddress" : $("#changeAddress").val(),
				"address.receiver" : $("#receiverName").val(),
				"address.provinceId" : $("#sel_province").val(),
				"address.cityId" : $("#sel_city").val(),
				"address.townId" : $("#sel_town").val(),
				"address.address" : $("#detailAddress").val(),
				"address.postCode" : $("#postCode").val(),
				"address.sendTime" : $("#sendTime").val(),
				"addressId" : $("#addressId").val()
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					updateAddrFirstsf(result.entity);
					window.location.href="http://highso.org.cn/static/web/column/90/index_1.shtml";
				}
			},
			error : function(error) {
				alert('error');
			}
		});
	}
	
	function processReceiverInfo_gwy(){
		var receiverInfo = $("#receiverName").val()
							+ ", " + $("#sel_province option:selected").text()
							+ ", " + $("#sel_city option:selected").text()
							+ ", " + $("#sel_town option:selected").text()
							+ ", " + $("#detailAddress").val()
							+ ", " + $("#postCode").val();;
		if($("#addressMobile").val() != "") {
			$("#address_mobile").val($("#addressMobile").val());
			receiverInfo += ", " + $("#addressMobile").val();
		} else {
			$("#address_mobile").val($("#addressTel").val());
			receiverInfo += $("#addressTel").val();
		}
		$("#receiver_message").html(receiverInfo);
		$("#buy_way_message").html("快递送货上门，" + $("#sendTime option:selected").text() + "（您将在48小时内收到我们的快递）");
	}
	//公务员收货地址
	//注册后分配机会谢添加
 	function doafterregChance(){
 		$.ajax({
 				url : baselocation + "/crm/crmChance!doAfterReg.action",
 				data : {},
 				type : "post",
 				dataType : "json",
 				cache : false,
 				async : false,
 				success : function(result) {
 				},
 				error : function(error) {
 				}
 			});
 	}
 	 	/**Yn生成银行汇款订单**/
	function createOffLineContract(){
		$.ajax({
			url : baselocation + "/finance/contract!makeOffLineContractAndCashRecord.action",
			data : {
				"couponCode":$("#couponCode").val()
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					/*--弹出确认窗口--*/
					showPayBank(result.entity[1],result.entity[0]);
					/*--发送短信给用户--*/
					sendAsyncMsgToUserMobile(2);
				}
				if(typeof(_gaq)!="undefinde"&&_gaq!=null){
					_gaq.push(['_trackPageview', '/virtual/step_pay6.html']);//谷歌统计  添加虚拟页面游览量统计
				}
			},
			error : function(error) {
				alert('error');
			}
		});
	}
	
	/**Yn银行汇款发送订单信息至用户**/
	function sendCSms2Customer(){
		var mobile = $.trim($("#mobileNOC").val());
		if(mobile == null || mobile == ''){
			showErrorWin("请输入手机号码","");
			return false;
		}
		var pattern = /^1{1}[0-9]{10}$/;
		if(!pattern.test(mobile)){
			showErrorWin("请输入正确手机号码","");
			return false;
		}
		$.ajax({
			url : baselocation + "/finance/contract!sendSmsOffLineContractInfo.action",
			data : {
				"contractno" : $("#contractOffNO").html(),
				"mobileNO" : mobile,
				"paytotal" : $("#contractOffPrice").html()
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					/*--弹出确认窗口--*/
					$("#scmtitle").html("发送成功");
					$("#scmtitle").css("cursor","default");
					$(".submit_Phone").unbind("click");
				}
			},
			error : function(error) {
				alert('error');
			}
		});
	}
	
	/**Yn银行汇款发送公司账号信息至用户**/
	function sendSms2Customer(){
		var mobile = $.trim($("#mobileNOS").val());
		if(mobile == null || mobile == ''){
			showErrorWin("请输入手机号码","");
			return false;
		}
		var pattern = /^1{1}[0-9]{10}$/;
		if(!pattern.test(mobile)){
			showErrorWin("请输入正确手机号码","");
			return false;
		}
		var comType = $("input[name='comType']:checked").val();
		$.ajax({
			url : baselocation + "/finance/contract!sendSmsOffLineCompanyBank.action",
			data : {
				"comType" : comType,
				"mobileNO" : mobile
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if(result.returnMessage == "success") {
					/*--弹出确认窗口--*/
					$("#scctitle").html("发送成功");
					$("#scctitle").css("cursor","default");
					$(".submit_Phone02").unbind("click");
				}
			},
			error : function(error) {
				alert('error');
			}
		});
	}
 	/*--Yn银行汇款回调显示--*/
 	function showPayBank(price,contractNO){
		outing();
 		$("#contractOffPrice").html("￥"+price);
 		$("#contractOffNO").html(contractNO);
 		$("#tdPayTotal").html("￥"+price);
 		$("#tdContractNO").html(contractNO);
		$("#data_div").css("display", "none");
		$("#buy_step2").css("display", "none");
		$("#buy_step5").css("display", "block");
	}
 	/*--Yn银行汇款点击图片更改--*/
 	function changeImg(obj){
 		var path = "http://import.highso.org.cn/images/web/public/buy/gzyh"+obj.value+".jpg";
		$("#yhimg").attr("src",path);
 	}
 	
 	 	/*--Yangning 网银发送下单短信至用户--*/
 	function sendMsgToUserMobile(contractid){
 		$.ajax({
			url : baselocation + "/alipay/zfb!sendMsgToUser.action",
			data : {
				"get_order" : contractid
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : true,
			success : function(result) {
			},
			error : function(error) {
			}
		});
 	}
 	
 	
 	/*---Yangning 货到付款与银行汇款发送短信----*/
 	function sendAsyncMsgToUserMobile(type){
 		$.ajax({
			url : baselocation + "/finance/contract!sendAsyncMsgToUserMobile.action",
			data : {
				"type" : type
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : true,
			success : function(result) {
			},
			error : function(error) {
			}
		});
 	}
 	
 	/*--Yang check is exist pkgs user have brought**/
 	function getBuyCountByIds(pkgIds){
		var entity;
		var userid = getUserId();
		$.ajax({ 
			url : baselocation + "/finance/cashRecord!getShus.action",
			data : {
				'pkgIds' : pkgIds,
				'userid' : userid
			},
			type : "post",
			dataType : "json",
			async :false,
			cache : false,
			success : function (result) {
				entity = result.entity;
				if(entity[1] > 0) {
					hasSendCourse = true;
				}
			}
		});
		return entity[0]; 
	}