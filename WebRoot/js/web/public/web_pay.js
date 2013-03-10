  //各种支付标记位
  var iskq = true;
  var iszfb = false;
  var istruey = false;
  var iswyzx = false;
  var iskqsj = false;
  var iskqxy = false;
  var isunionpay = false;

	var _gaq = _gaq || [];
  _gaq.push(['_setAccount','UA-22194725-1']);
  _gaq.push(['_setDomainName','none']);
  _gaq.push(['_trackPageview']);
  _gaq.push(['_setAllowLinker',true]);
  _gaq.push(['_setAllowHash',false]);
  _gaq.push(['_setCampaignCookieTimeout',216000000]);
  _gaq.push(['_addOrganic','sogou','query']);
  _gaq.push(['_addOrganic','soso','w']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

var serialNumber = '';
var sendAgain = false;
var flag = false;
function addorder()
	{
	  var userid=getUserId(); //判断用户是否登录
	  var goodspage = getCookie("coursesbao");
	  if(userid!=null&&goodspage!=null)//已登录的操作
	  {
		  var src="<tr class='he_conRtit_2'><td>商品名称</td><td align='center'>主讲人</td><td align='center'>课时</td><td align='center'>价格</td><td align='center'>总计</td></tr>";
		  var baoname="";
		  var jiage=0;
		  
		  	if(goodspage!=null)
		  	{
		  		var courseOrderStr=getUserId()+"#";
			  	var bao=goodspage.split("/");
			  	for(var a=1; a<bao.length; a++ )
			  	{
				  	var goodbao = bao[a-1].split(".");
				  	var goodinfo=goodbao[4];
				  	var courrs = goodinfo.split("#");
				  	var teacher="";
				  	var timeslength=0;
				  	
				  	//baoname = goodbao[1];//goodbao[1]包名 、包总价goodbao[2]
					 var shuzi=getshuzi(goodbao[0]);
					  if(shuzi==0)
					  {
					  	for(var i=0; i<courrs.length; i++ )
					  	{	
					  		var kecheng=courrs[i];
					  		var kechengfen=kecheng.split(",");
					  		timeslength+=parseInt(kechengfen[3]);//课时
					  		teacher+=kechengfen[2]+"&nbsp;&nbsp;";
					  		courseOrderStr+=kechengfen[0]+","+kechengfen[5]+","+goodbao[0]+"#";
					
					  	}
					
					
					  	src+="<tr><td height=36>"+goodbao[1]+"</td><td height='36' align='center'>"+teacher+"</td><td height='36' align='center'>"+timeslength+"</td><td height='36' align='center'>"+goodbao[2]+"</td><td height='36' align='center'>"+goodbao[2]+"</td></tr>";
					  	jiage+=parseFloat(goodbao[2]);
					  }else
					  {
					  	for(var i=0; i<courrs.length; i++ )
					  	{	
					  		var kecheng=courrs[i];
					  		var kechengfen=kecheng.split(",");
					  		timeslength+=parseInt(kechengfen[3]);//课时
					  		teacher+=kechengfen[2]+"&nbsp;&nbsp;";
					
					  	}
					  	src+="<tr style='color:#FF0000'><td height=36>"+goodbao[1]+"</td><td height='36' align='center'>"+teacher+"</td><td height='36' align='center'>"+timeslength+"</td><td height='36' align='center'>"+goodbao[2]+"</td><td height='36' align='center'>"+goodbao[2]+"</td></tr>";
					  }
			  	}
		  	
		  	}
		  	$("#orderlist").html(src);
		  	
		  	var totalprice = parseFloat(jiage);//
			if(totalprice<0||totalprice==null)
			{
				totalprice=0;
			}
			document.getElementById("totalprice").innerHTML=totalprice;
			document.getElementById("payprice").innerHTML=totalprice;//减去优惠券儿后的金额
			SetCookie("courseOrder",courseOrderStr);
			SetCookie("totalPrice1",totalprice);
	  	}
	  	
	  	else//未登录的操作
	  	{
	  	 window.open(buyURL,'_self');
	  	}
	}
	
	
	//判断订单
	function isoder()
	{	
		 var userid=getUserId();
		var goodspage = getCookie("coursesbao");
		var no=0;
		if(userid!=null&&goodspage!=null)
		{
				var bao=goodspage.split("/");
			  	for(var a=1; a<bao.length; a++ )
			  	{
				  	var goodbao = bao[a-1].split(".");
				  	var goodinfo=goodbao[4];
				  	var courrs = goodinfo.split("#");
				  	var teacher="";
				  	var timeslength=0;
				  	
				  	//baoname = goodbao[1];//goodbao[1]包名 、包总价goodbao[2]
				  	
					  	if(goodbao[0]!=null&&goodbao[0]!='')
					  	{
						  var shuzi=getshuzi(goodbao[0]);
						  if(shuzi==0)
						  {
						  	no+=1;
						  }
						 }
					 
				}
				
				
				if(no!=0)
				{
					oder();//确定下订单
				}
		}	
	}
	
	//下订单
	function oder()
	{
		if(isLogin(baselocation + "/")==true)
		{
				var courseOrder = getCookie("courseOrder");
				var totalPrice = $("#totalprice").html();
				if((courseOrder!=null&&courseOrder!="")&&(totalPrice!=0&&totalPrice!="0")) {
					$.ajax({
							url: baselocation + "/alipay/zfb!makeContract.action",
							data:{},
							type:"post",
							dateType: "json",
							async : false,
							success: function(result)
							{
								var aa=	eval("("+result+")");
								var oderby=aa.returnMessage.split(",");
								$("#oderid").html(oderby[0]);
								$("#ode").val(oderby[0]);
								$("#jinee").val(oderby[1]);
								
								flag = true;
							},
							error : function()
							{
							alert("error");
							}
							
						 
						});
				}	
				else
				{
						flag= false;
				}
		}
	}
		
			//查询课程是否重复
	function getshuzi(couid)
	{
		var entity;
		var userid=getUserId();
		
			$.ajax({ 
			url : baselocation + "/finance/cashRecord!Getshu.action",
			data : {
				'couid' :couid ,
				'userid' : userid
			},
			type : "post",
			dataType : "json",
			async :false,
			cache : false,
			success : function (result) {
			entity = result.returnMessage;
				}
		});		
		
		return entity;			
	}
	
	//查看是否支付成功
	function issuess()
	{
	var entity = null;
	 var oderid=$("#oderid").html();
	 var  userid=getUserId();
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
						    vr[1],   				// category or variation
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


	
	$().ready(function() {
		/*
		if($("#defaultbank_1").attr("checked") == true){
			bank_div_hide() ;
		}
		if($("#defaultbank_2").attr("checked") == true){
			bank_div_show();
		}
		
		if($("#defaultbank_3").attr("checked") == true){
			bank_div_kq_show();
		}
		*/
//暂不开放优惠券功能
//		$.ajax({
//			url : baselocation + "/cus/cuslimit!hasMobile.action",
//			data : {},
//			type : "post",
//			dataType : "json",
//			//cache : false,
//			success : function (result) {
//				if(result.returnMessage=="no") {
//					$("#to_input_mobile").css("display", "block");
//				} else {
//					$("#input_sn").css("display", "block");
//				}
//			},
//			error : function () {				
//				isLogin(baselocation + "/");
//			}
//		});
	});


		function goToZFB() {
				if( iszfb == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4a', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.ZFForm.action = baselocation + "/alipay/zfb!goToZFB.action";
					document.ZFForm.submit();
				} else if(iswyzx == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4b', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.BKForm.action = baselocation + "/alipay/chinaBank!goToChinaBank.action";
					document.BKForm.submit();
				} else if(iskq == true && iskqsj == false && iskqxy == false) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
					document.KQForm.submit();
				}else if(iskq == true && iskqxy == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
					$("#KQPayType").val("15");
					document.KQForm.submit();
				}else if(iskq == true && iskqsj == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
					$("#KQPayType").val("19");
					document.KQForm.submit();
				}else if(istruey == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.trueuForm.action = baselocation + "/alipay/trueu!gotoTrueU.action";
					document.trueuForm.submit(); 
				}else if(isunionpay == true) {
					if(typeof(_gaq)!="undefinde"&&_gaq!=null){
						_gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
						_gaq.push(['_trackEvent', 'cheackout_shipping_4c', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
					}
					document.unionPayForm.action = baselocation + "/alipay/unionpay!goToUnionPay.action";
					document.unionPayForm.submit(); 
				}
				else {
					alert("请选择支付方式。");
					return;
				}
				locking();
	}
	
	function toInputMobile() {
		$("#to_input_mobile").css("display", "none");
		$("#input_mobile").css("display", "block");
		$("#mobile_msg").html("为了您的个人资料的安全，我们会给此手机发送验证码，请注意查收和回复！").css("color", "black");
		var mobile = $("#mobile").val("");
	}
	
	function cancelIptSn() {
		$("#input_mobile").css("display", "none");
		$("#to_input_mobile").css("display", "block");
	}
	
	function addMobile() {
		var mobile = $("#mobile").val();
		if(mobile == null || mobile == '') {
			$("#mobile_msg").html("手机号码不能为空").css("color", "red");
			return;
		}
		var pattern = /^1{1}[0-9]{10}$/;
		if(!pattern.test(mobile)) {
			$("#mobile_msg").html("请输入正确的手机号码").css("color", "red");
			return;
		}
		$.ajax({
			url : baselocation + "/cus/cuslimit!addMobile.action",
			data : {
				"customer.mobile" : mobile
			},
			type : "post",
			dataType : "json",
			cache : false,
			success : function (result) {
				if(result.returnMessage=="true") {
					$("#input_mobile").css("display", "none");
					$("#input_sn").css("display", "block");
					$("#cp_msg").html("<font>优惠券序列号以短信方式发生到手机，请查收。</font><br>30秒后，没有收到短信，<a href='javascript:sendSNAgain()'>点此再发一次</a>");
					serialNumber = result.entity;
				} else {
					
				}
				window.setTimeout(function () {sendAgain = true;}, 30000);
			},
			error : function (error) {
				alert('error');				
			}
		});
	}
	
	function sendSNAgain() {
		if(sendAgain) {
			$("#cp_msg").html("");
			$.ajax({
				url : baselocation + "/cus/cuslimit!addMobile.action",
				data : {
					"serialNumber" : serialNumber
				},
				type : "post",
				dataType : "json",
				cache : false,
				success : function (result) {
					if(result.returnMessage=="success") {
						$("#cp_msg").html("<font>优惠券序列号以短信方式发生到手机，请查收。</font><br>30秒后，没有收到短信，<a href='javascript:sendSNAgain()'>点此再发一次</a>");
					} else {
						$("#cp_msg").html("<font>发送失败，请稍后重试。</font><br><a href='javascript:sendSNAgain()'>点此再发一次</a>");
					}
				},
				error : function (error) {
					alert('error');				
				}
			});
		} else {
			$("#cp_msg").html("<font>请稍后再试。</font><br>30秒后，没有收到短信，<a href='javascript:sendSNAgain()'>点此再发一次</a>");
		}
	}

	function useCp() {
		var serialNumber = $("#serialNumber").val();
		if(serialNumber == null  &&  serialNumber == '') {
			$("#cp_msg").html("验证码不能为空");
			return;
		}
		$("#serialNumber").val(serialNumber);
		$.ajax({
			url : baselocation + "/cou/couponlimit!useCp.action",
			data : {
				//"queryCouponCondition.courses" : courses,
				"serialNumber" : serialNumber
				
			},
			type : "post",
			dataType : "json",
			cache : false,
			success : function (result) {
				if(result.returnMessage=="success") {
					$("#serialNumber").val("");
					$("#cp_msg").html("优惠券面值为：<font>" + result.entity + "</font>元");
					$("#cp_price").html("优惠券:" + result.entity + "元");
					$("#payprice").html(parseFloat($("#totalprice").html()) - parseFloat(result.entity));
					$("#input_sn_li").css("display", "none");
					$("#isUse").val("true");
					$("#cPrice").val(result.entity);
					$("#zfSerialNumber").val(serialNumber);
					SetCookie("payprice", result.entity);
					//$("#use_cp_div").html("优惠券面值：<strong class='font_ps' >"+result.entity+"</strong><a href='javascript:void(0)' onclick='cancelCp()'>取消使用</a>");
					//$("#youhuijuan").html("优惠券：<strong class='font_ps' >"+result.entity+"</strong>元");
					//$("#payprice").html(parseFloat(getCookie("totalPrice1")) - parseFloat(result.entity));
					//$("#cp_div").css("display", "none");
					//$("#cpMessage").html("");
					//$("#isUse").val("true");
					//$("#cPrice").val(result.entity);
					//$("#zfSerialNumber").val(serialNumber);
				} else if(result.returnMessage == "used") {
					$("#cp_msg").html("优惠券已经使用过").css("color", "red");
				}else {
					$("#cp_msg").html("验证码错误，请重新输入").css("color", "red");
				}
			},
			error : function () {				
				isLogin(baselocation + "/");
			}
		});
	}
	
	//弹窗效果
	function locking(){ 
		document.getElementById("he_black").style.display="block"; 
		document.getElementById("he_black").style.width = document.body.clientWidth + "px";
		document.getElementById("he_black").style.height = document.body.clientHeight + 10 + "px";
//		document.getElementById("he_black").style.width=document.body.clientWidth;
//		document.getElementById("he_black").style.height="1800px"; 
		document.getElementById("show_box").style.display='block'; 
	} 
	
	function outing(){ 
	    document.getElementById("show_box").style.display='none'; 
	    document.getElementById("he_black").style.display='none'; 
	    var stata = issuess();
	    if(stata==0)
	    {
	    	window.open(payURL,'_self');
	    }else
	    {
	    	window.open(payOkURL,'_self');
	    }
	    
	} 
	
	function closeWin(){ 
	    document.getElementById("he_black").style.display="none"; 
		document.getElementById("he_black").style.width = document.body.clientWidth + "px";
		document.getElementById("he_black").style.height = document.body.clientHeight + 10 + "px";
//		document.getElementById("he_black").style.width=document.body.clientWidth;
//		document.getElementById("he_black").style.height="1800px"; 
		document.getElementById("show_box").style.display='none'; 
	} 
	
	function bank_div_show(){
 		$("#bank_div1").hide();
		$("#bank_div3").hide();
		$("#bank_div2").show();
	}
	function zfb_div_show(){
 		$("#bank_div2").hide();
		$("#bank_div3").hide();
		$("#bank_div1").show();
 	}
	function bank_div_hide(){
		$("#bank_div2").hide();
		$("#bank_div3").hide();
		$("#bank_div1").show();
	}
	
	function bank_div_kq_show(){
		$("#bank_div2").hide();
		$("#bank_div1").hide();
		$("#bank_div3").show();
	}
	function showMoreKQBank(){
		$("#defaultbank_3").attr("checked",true);
		if($("#more_kq_bank_ul").css("display") == "none") {
			$("#more_kq_bank_ul").css("display", "block");
		} else {
			$("#more_kq_bank_ul").css("display", "none");
		}
	}
	
	function showMoreZfBank(){
		$("#defaultbank_1").attr("checked",true);
		if($("#more_zfb_bank_ul").css("display") == "none") {
			$("#more_zfb_bank_ul").css("display", "block");
		} else {
			$("#more_zfb_bank_ul").css("display", "none");
		}
	}
	function showMoreBank(){
		$("#defaultbank_1").attr("checked", "true");
		if($("#more_bank_ul").css("display") == "none") {
			$("#more_bank_ul").css("display", "block");
		} else {
			$("#more_bank_ul").css("display", "none");
		}
	}
 	function BK_checked(){
 		$("#defaultbank_2").attr("checked",true);
 	}
 	//快钱选择
 	function kqCheck(){
		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_2]").attr("checked",false);
		iskq = true ;
		iszfb = false;
		istruey = false;
		iswyzx = false;
		iskqxy = false;
		iskqsj = false;
		isunionpay = false;
	}
 	
 	//快钱信用选择
 	function kqxyCheck(){
		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_1]").attr("checked",false);
		iskq = true ;
		iskqxy = true;
		iszfb = false;
		istruey = false;
		iswyzx = false;
		iskqsj = false;
		isunionpay = false;
	}
 	
 	//快钱手机选择
 	function kqsjCheck(){
		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_1]").attr("checked",false);
		iskq = true ;
		iskqsj = true;
		iszfb = false;
		istruey = false;
		iswyzx = false;
		iskqxy = false;
		isunionpay = false;
	}
 	//快钱id先择
 	function kqidCheck(){
 		$("input[name=defaultbank_1]").attr("checked",false);
		$("input[name=defaultbank_2]").attr("checked",false);
		iskq = true ;
		iszfb = false;
		istruey = false;
		iswyzx = false;
		iskqxy = false;
		iskqsj = false;
		isunionpay = false;
 	}
 	//支付宝选择
 	function zfbCheck(){
 		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_2]").attr("checked",false);
		iszfb = true;
		istruey = false;
		iswyzx = false;
		iskq = false;
		iskqxy = false;
		iskqsj = false;
		isunionpay = false;
 	}
 	//真友选择
 	function trueyCheck(){
 		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_1]").attr("checked",false);
		istruey = true;
		iszfb = false;
		iswyzx = false;
		iskq = false;
		iskqxy = false;
		iskqsj = false;
		isunionpay = false;
 	}
 	//网银在线选择
 	function wyzxCheck(){
 		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_2]").attr("checked",false);
		iswyzx = true;
		istruey = false;
		iszfb = false;
		iskq = false;
		iskqxy = false;
		iskqsj = false;
		isunionpay = false;
 	}
 	//银联在线选择
 	function unionPayCheck(){
 		$("input[name=kQInfo.bankId]").attr("checked",false);
		$("input[name=defaultbank_2]").attr("checked",false);
		isunionpay = true;
		iswyzx = false;
		istruey = false;
		iszfb = false;
		iskq = false;
		iskqxy = false;
		iskqsj = false;
 	}
 	
