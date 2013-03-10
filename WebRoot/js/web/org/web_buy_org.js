var isBackToData = false;
//是否跳转到支付页面的标识
var flag = false;
var hasSendCourse = false;
//快钱支付标识
var isKQZL = false ;
var zongjia = 0;
var packageCount = 0;
var sellWayCount=0;
var goodspage = getCookie("coursesbao");
var couponId=0;
var sellId=0;
var couponCode="";
var sellIds="";
var issteppay = false;
var lastTime = null;
var protocalArray = new Array();
var isSearched = false;
var addrObjArray;
//易联支付时是否点击过
var voicePayClickedNum = 0;
var paytype = 4;
var isVoAdressShowed = false;


function StringBuffer()
{
    this.data = [];
}
StringBuffer.prototype.append = function(){
    this.data.push(arguments[0]);
    return this;
}
StringBuffer.prototype.toString = function(){
    return this.data.join("");
}

$().ready(function() {
    if(getBuyTypeValue("buyType")){
        invokeBuyMethord(GetArgsFromHref("methord"));
    }
    addordertmp();
    getorder();
    checkRemeberMe() ;
    initFormValid();
    $("#bank_radio").attr("checked", "true");
    showPayType();
});

function getorder(){
    goodspage = getCookie("coursesbao");
    zongjia = 0;
    var cutprice = 0;
    var oritotalPrice = 0;
    packageCount = 0;
    sellWayCount=0;
    sellIds="";
    var htmlSb = new StringBuffer();
    protocalArray.length = 0;
    htmlSb.append("<tbody>");
    if(goodspage == null || goodspage.length == 0) {
        htmlSb .append("<tr><td colspan='7' align='center'>购物车为空</td></tr>");
    }
    if(goodspage != null) {
        var bao=goodspage.split("/");
        packageCount = bao.length-1;
        for(var a=0; a<bao.length-1; a++ ) {
            var goodbao = bao[a].split(".");
            var courrs = goodbao[4].split("#");
            var teacher = "";
            var timeslength = 0;

            var remark = "";
            var oriprice = 0;
            var rebateprice = 0;
            var teacherHtml = "";
            var isprotocal = "false";

            for(var i=0; i<courrs.length; i++ ) {
                var kechengfen = courrs[i].split(",");

                remark = courrs[i].split("^*")[1];
                oriprice = courrs[i].split("^*")[2];
                isprotocal = courrs[i].split("^*")[3];

                if(isprotocal == "true"){
                    protocalArray.push(goodbao[0]);
                }

                if(remark == null || remark == 'null'){
                    remark = "";
                }
                if(oriprice == null || oriprice == 'null' || oriprice == 0){
                    oriprice = parseInt(goodbao[2]);
                }

                oritotalPrice +=  parseInt(oriprice);
                rebateprice = oriprice - goodbao[2];
                if(rebateprice <= 0){
                    rebateprice = 0;
                }
                cutprice += rebateprice;

                timeslength += parseInt(courrs[i].split(",")[3]);//课时
                teacher += kechengfen[2] ;
            }
            htmlSb.append("<td class=\"first\">"+goodbao[1]+"</td>");
            if(teacher != null && teacher.length > 0){
                var teachers = teacher.split(" ");
                if(teachers != null && teachers.length > 0){
                    for(var t = 0; t < teachers.length; t++){
                        if(teachers[t] != null && $.trim(teachers[t]).length > 0){
                            teacherHtml +=  teachers[t] + "  ";
                        }

                    }
                }
            }
            htmlSb.append("<td>"+teacherHtml+"</td>");
            htmlSb.append("<td >"+timeslength+"</td>");
            htmlSb.append("<td >￥"+fixNumber(goodbao[2],2)+"</td>");
            htmlSb.append("<td ><a class=\"n_del\" href=\"javascript:deleteid("+goodbao[0]+")\">删除</a></td></tr>");
            zongjia+=parseInt(goodbao[2]);
            sellWayCount=sellWayCount+1;
            sellId=goodbao[0];
            sellIds=sellIds+goodbao[0]+",";
        }
    }
    htmlSb.append("</tbody>");
    $("#orderlist").html(htmlSb.toString());
    $("#cutprice").html("￥"+fixNumber(cutprice,2));
    $("#oldmoney").html("￥"+ fixNumber(oritotalPrice,2));
    $("#subtotal").html("￥"+ fixNumber(zongjia,2));
    $("#yhmoney").html("￥"+0.00);
    $("#payprice").html("￥"+ fixNumber(zongjia,2));
    $("#paySumPrice").html("￥"+ fixNumber(zongjia,2));
    $("#paypriceRecv").html("￥"+ fixNumber(zongjia+10,2));
    $("#paySumPriceRecv").html("￥"+ fixNumber(zongjia+10,2));
    $("#buyCount").html(""+packageCount+"件");
}

function fixNumber(money,n){
    if(typeof(money) == 'undefined' || money == null || money ==""|| isNaN(money) ||money == Infinity){
        return money;
    }else{
        return parseFloat(money).toFixed(n);
    }
};

/**
 * 检测支付金额
 * @returns {Boolean}
 */
function checkPayMoney(){
    var payprice = parseInt($("#payprice").html().substring(1,$("#payprice").html().length));
    if(isNaN(payprice) || payprice <= 0){
        showErrorWin("订单金额不可为0");
        deleteorder();
        return false;
    }else{
        return true;
    }
}

/**
 * 显示选择支付类型
 */
function showPayType(){
    if(checkPayMoney()){
        if(isLogin(baselocation + "/")){
            $("#buy_step2").css("display", "block");
            $('#buyReg').css("display", "none");
            $("#yhjdiv").show();
            $("#totalDetail").show();
            $("#totalTitle").show();
            $("#buyStepimg").attr("src", importURL + "/images/web/public/buy/buyStep02.jpg");
            $("#submitBtnShowPay").hide();
            $("#submitBtnContract").show();
            checkCoursesBought();
        }else{
            locking();
            $("#buyReg").css("display", "block");
            $("#totalDetail").hide();
            $("#totalTitle").hide();
            try{
                BuyLogAjax(getBuySubjectId(),4);
            }catch(err){}
        }
    }
}



function checkCoursesBought() {
    if(flag){
        createContractSuccess(1);
        return;
    }
    var goodspage = getCookie("coursesbao");
    var html = "";
    var zongjia = 0;
    var availableCount = 0;
    var cutprice = 0;
    var oritotalPrice = 0;
    if(goodspage != null && $.trim(goodspage).length > 0) {
        var courseOrderStr = getUserId() + "#";
        var bao=goodspage.split("/");
        var sb = "";
        if(bao != null && bao.length > 0){
            sb = "(";
            for(var i = 0;i<bao.length-1;i++){
                var goods = bao[i].split(".");
                sb = sb + goods[0];
                sb = sb + ",";
            }
            sb = sb.substring(0,sb.length -1) + ")";
        }
        //var buyCount = getBuyCountByIds(sb);
        for(var a=0; a<bao.length-1; a++ ) {
            var goodbao = bao[a].split(".");
            var courrs = goodbao[4].split("#");
            var goodbao = bao[a].split(".");
            var courrs = goodbao[4].split("#");
            var teacher = "";
            var timeslength = 0;
            var remark = "";
            var oriprice = 0;
            var rebateprice = 0;
            var teacherHtml = "";
            //if(buyCount == 0) {
            for(var i=0; i<courrs.length; i++ ) {
                var kechengfen = courrs[i].split(",");
                remark = courrs[i].split("^*")[1];
                oriprice = courrs[i].split("^*")[2];
                isprotocal = courrs[i].split("^*")[3];

                if(remark == null || remark == 'null'){
                    remark = "";
                }
                if(oriprice == null || oriprice == 'null' || oriprice == 0){
                    oriprice = parseInt(goodbao[2]);
                }

                oritotalPrice +=  parseInt(oriprice);
                rebateprice = oriprice - goodbao[2];
                if(rebateprice <= 0){
                    rebateprice = 0;
                }
                cutprice += rebateprice;

                timeslength += parseInt(courrs[i].split(",")[3]);//课时
                teacher += kechengfen[2] + "";

                courseOrderStr += kechengfen[0] + "," + kechengfen[5] + "," + goodbao[0] + "#";
            }
            if(teacher != null && teacher.length > 0){
                var teachers = teacher.split(" ");
                teacherHtml += teachers[0];
            }
            availableCount ++;
            zongjia+=parseInt(goodbao[2]);
            /*
             }
             else {
             for(var i=0; i<courrs.length; i++ ) {
             var kechengfen = courrs[i].split(",");
             timeslength += parseInt(courrs[i].split(",")[3]);//课时
             teacher += kechengfen[2] + "";
             }


             }
             */
        }
        SetCookie("courseOrder", courseOrderStr);
        SetCookie("totalPrice1", zongjia);
        $("#buyStepimg").attr("src", importURL + "/images/web/public/buy/buyStep02.jpg");
        $("#submitBtnShowPay").hide();
        $("#submitBtnContract").show();
        $('#buy_step2').css("display", "block");
        $("#submitBtnShowPay").hide();
        $("#submitBtnContract").show();
        $("#yhjdiv").show();
        $("#totalDetail").show();
        $("#totalTitle").show();
        if(zongjia != 0) {
            return true;
        }else {
            //谢添加
            if(goodspage != null && $.trim(goodspage).length == 0){
                showErrorWin("购物车内课程为空。");
            }else{
                showErrorWin("购物车内的课程已购买。");
                getorder();
            }
            deleteorder();
            //谢结束
            return false;
        }
    }
}

//货到付款
function confirmContract() {
    var payprice = parseInt($("#payprice").html().substring(1,$("#payprice").html().length - 1));

    if(isNaN(payprice) || payprice <= 0){
        showErrorWin("订单金额不能为0。");
        return;
    }

    //Yang 页面调整
    /*
     if(payprice!= null && payprice >= 450){
     $("#subPayDiv").show();
     }else{
     $("#subPayDiv").hide();
     }

     //Yaning 2012/03/26
     if(protocalArray.length > 0){
     $("#protocalForm2Div").show();
     if(!$("#protocalForm").valid()){
     return;
     }
     }
     */
    if(isBackToData) {
        $("#buy_step_div").css("display", "block");
        $("#data_div").css("display", "none");
        processReceiverInfo();
        return;
    }
    if(isLogin(baselocation + "/")) {
        locking();
        $("#buy_step_div").css("display", "block");
        $('#buy_step2').css("display", "block");
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

function fixWinPosition() {
    $("#data_div").css("display", "block");

    if(($("#data_div").height() + $("#data_div").offset().top + 25 + $("#buy_footer").height()) < document.documentElement.clientHeight) {
        $("#buy_footer").css("position", "absolute");
        $("#buy_footer").css("top", document.documentElement.clientHeight - $("#buy_footer").height() +150 );
    }

    $("#buy_footer").css("display", "block");
    if(($("#data_div").height() + $("#data_div").offset().top + 25 + $("#buy_footer").height()) < document.documentElement.clientHeight - 50) {
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
                    $('#buyReg').css("display", "none");
                    $("#buyStepimg").attr("src", importURL + "/images/web/public/buy/buyStep02.jpg");
                    $("#yhjdiv").show();
                    $("#totalDetail").show();
                    $("#totalTitle").show();
                    if(checkPayMoney()){
                        outing();
                        $('#buy_step2').css("display", "block");
                        $("#submitBtnShowPay").hide();
                        $("#submitBtnContract").show();						}
                    doafterreg();
                    try{
                        BuyLogAjax(getBuySubjectId(),2);
                    }catch(err){}
                    return;
                } else if(result.returnMessage == "emailInUsed") {
                    showErrorWin("该邮箱已经注册过。");
                } else if(result.returnMessage == "regDangerWord") {
                    showErrorWin("请不要输入非法关键字。");
                } else if(result.returnMessage == "err.randCode"){
                    showErrorWin("验证码错误!") ;
                }else {
                    showErrorWin("注册失败，请稍后再试。");
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
    getorder();
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
    $("#viewProBtnDiv").hide();
    $("#orderlist").html("");
    $("#agrFormDiv").hide();
    $("#agreeCon").hide();
    isSearched = false;
    getorder();
    resetCoupon();//谢添加
}

/**显示更多银行**/
function showMoreKQBank(){
    if($("#more_kq_bank_ul").css("display") == "none") {
        $("#more_kq_bank_ul").css("display", "block");
        $("#showMoreKQBankLink").html("更多银行请点击 >>");
    } else {
        $("#more_kq_bank_ul").css("display", "none");
        $("#showMoreKQBankLink").html("收起银行请点击 >>");
    }
}
/**
 * 选择块钱支付
 * @param type  块钱支付类型,用于支付接口传递参数
 */
function check_kqbank(type){
    if(type == 1){
        $("#default_kq").attr("checked",true);
    }else if(type == 2){
        $("#default_voice").attr("checked",true);
    }else{
        $("#voicePayUl").hide();
    }
    $("input[name=zhifb]").attr("checked",false);
    $("input[name=receive]").attr("checked",false);
    $("input[name=offline]").attr("checked",false);
    $("input[name=chinabank]").attr("checked",false);
    $("input[name=unpay]").attr("checked",false);
    $("input[name=unionVoice]").attr("checked",false);
    paytype = 4;
}
/**
 * 网银在线支付
 * @param type用于支付接口传递参数, 此处只用到0
 */
function check_cnb(type){
    if(type == 0){
        $("input[name=zhifb]").attr("checked",false);
        $("input[name=receive]").attr("checked",false);
        $("input[name=offline]").attr("checked",false);
        $("input[name=kQInfo.bankId]").attr("checked",false);
        $("input[name=unpay]").attr("checked",false);
        $("input[name=unionVoice]").attr("checked",false);
        paytype = 3;
    }
}

/**
 * 选择支付宝支付
 * @param type 用于支付接口传递参数, 此处只用到0
 */
function check_zfb(type){
    if(type == 0){
        $("input[name=chinabank]").attr("checked",false);
        $("input[name=receive]").attr("checked",false);
        $("input[name=offline]").attr("checked",false);
        $("input[name=kQInfo.bankId]").attr("checked",false);
        $("input[name=unpay]").attr("checked",false);
        $("input[name=unionVoice]").attr("checked",false);
        paytype = 1;
    }
}
function check_receive(type){
    if(type == 1){
        $("#default_recv").attr("checked",true);
    }
    $("input[name=zhifb]").attr("checked",false);
    $("input[name=chinabank]").attr("checked",false);
    $("input[name=offline]").attr("checked",false);
    $("input[name=kQInfo.bankId]").attr("checked",false);
    $("input[name=unpay]").attr("checked",false);
    $("input[name=unionVoice]").attr("checked",false);
    paytype = 2;
}

function check_offline(type){
    if(type == 1){
        $("#default_offline").attr("checked",true);
    }
    $("input[name=zhifb]").attr("checked",false);
    $("input[name=chinabank]").attr("checked",false);
    $("input[name=receive]").attr("checked",false);
    $("input[name=kQInfo.bankId]").attr("checked",false);
    $("input[name=unpay]").attr("checked",false);
    $("input[name=unionVoice]").attr("checked",false);
    paytype = 7;
}

function check_union(type){
    if(type == 1){
        $("#default_union").attr("checked",true);
    }
    $("input[name=zhifb]").attr("checked",false);
    $("input[name=chinabank]").attr("checked",false);
    $("input[name=offline]").attr("checked",false);
    $("input[name=kQInfo.bankId]").attr("checked",false);
    $("input[name=receive]").attr("checked",false);
    $("input[name=unionVoice]").attr("checked",false);
    paytype = 8;
}

function check_unv(type){
    $("#voicePayUl").show();
    if(type == 1){
        $("#defaultUnionVoice").attr("checked",true);
    }
    $("input[name=zhifb]").attr("checked",false);
    $("input[name=chinabank]").attr("checked",false);
    $("input[name=offline]").attr("checked",false);
    $("input[name=kQInfo.bankId]").attr("checked",false);
    $("input[name=receive]").attr("checked",false);
    $("input[name=unpay]").attr("checked",false);
    paytype = 10;
}

function goToZFB() {
    var payprice = parseInt($("#payprice").html().substring(1,$("#payprice").html().length));
    if(!isNaN(payprice) && payprice > 0) {
        if(hasSendCourse && !confirm("订单中包含已通过货到付款方式购买的课程，您确定要继续吗？")){
            return;
        }
        if(flag) {
            try{
                BuyLogAjax(getBuySubjectId(),5);
            }catch(err){}
            if(paytype == 1) {//支付宝
                if(typeof(_gaq)!="undefinde"&&_gaq!=null){
                    _gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
                    _gaq.push(['_trackEvent', 'cheackout_shipping_4a1', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
                    _gaq.push(['_trackEvent', 'Transaction', 'Pay','OL-AliPay']);
                }
                document.ZFForm.action = baselocation + "/alipay/zfb!goToZFB.action";
                document.ZFForm.submit();
            } else if(paytype == 3) {//网银在线
                if(typeof(_gaq)!="undefinde"&&_gaq!=null){
                    _gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
                    _gaq.push(['_trackEvent', 'cheackout_shipping_4b2', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
                    _gaq.push(['_trackEvent', 'Transaction', 'Pay','OL-CreditCardPay']);
                }
                document.BKForm.action = baselocation + "/alipay/chinaBank!goToChinaBank.action";
                document.BKForm.submit();
            } else if(paytype == 4) {//快钱银行支付
                if(typeof(_gaq)!="undefinde"&&_gaq!=null){
                    _gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
                    _gaq.push(['_trackEvent', 'cheackout_shipping_4c3', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
                    _gaq.push(['_trackEvent', 'Transaction', 'Pay','OL-KuaiQian']);
                }
                document.KQForm.action = baselocation + "/alipay/KQ!goToKQ.action";
                var kqPay_type = $("input[name=kQInfo.bankId][checked]").val();
                if(kqPay_type != null && kqPay_type == '00'){
                    $("#KQPayType").val("00");
                    $("#KQBankType").attr("disabled","disabled");
                }else if(kqPay_type != null && kqPay_type=="voice"){
                    $("#KQPayType").val("19");
                    $("#KQBankType").attr("disabled","disabled");
                }else if(kqPay_type != null && kqPay_type=="borrow"){
                    $("#KQPayType").val("19");
                    $("#KQBankType").attr("disabled","disabled");
                }else if(kqPay_type != null && kqPay_type=="credit"){
                    $("#KQPayType").val("15");
                    $("#KQBankType").attr("disabled","disabled");
                }
                else{
                    $("#KQBankType").val(kqPay_type);
                    $("#KQBankType").removeAttr("disabled");
                }
                document.KQForm.submit();
            } else if(paytype == 8){
                document.UnionPayForm.action = baselocation + "/alipay/unionpay!goToUnionPay.action";
                document.UnionPayForm.submit();
            }else {
                showErrorWin("请选择支付方式。");
                return;
            }
            /*
             $("#show_box").css("display", "block");
             $("#buy_step_div").css("display", "none");
             $("#send_header_div").css("display", "none");
             $("#send_div").css("display", "none");
             $(".p-wiidow").css("display","none");
             */
        }
    } else {
        showErrorWin("订单金额不能为0。");
    }
}

/*
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
 */
//判断订单
function checkContract() {
    if(checkCoursesBought()){
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
                    //var buyCount = getBuyCount(packageArrays[0]);
                    //if(buyCount == 0) {
                    try{
                        BuyLogAjax(getBuySubjectId(),5);
                    }catch(err){}
                    if(paytype == 2){
                        confirmAddress();
                        return;
                    }else if(paytype == 7){
                        createOffLineContract();
                        return;
                    }else if(paytype == 10 ){
                        if(voicePayClickedNum == 0){
                            chkUnionVoicePay();
                        }else if(voicePayClickedNum == 1){
                            goToUnionVoiceCreditPay();
                        }else if(voicePayClickedNum == 2){
                            goToUnionVoiceBorrowPay();
                        }
                        return;
                    }else{
                        if(flag){
                            createContractSuccess(1);
                        }else{
                            makeContract();
                        }
                        return;
                    }
                    /*
                     }
                     else{
                     showErrorWin("此商品已购买");
                     return;
                     }
                     */
                }
            }
        }
    }
}

function makeContract(){
    if(isLogin(baselocation + "/")) {
        var courseOrder = getCookie("courseOrder");
        var payprice = $("#payprice").html().substring(0, $("#payprice").html().length-1);
        if(courseOrder != null && courseOrder != "" && payprice !=0 && payprice != "0") {
            $.ajax({
                url: baselocation + "/alipay/zfb!makeContract.action",
                data:{//谢添加
                    "payType" : paytype,"couponCode":$("#couponCode").val()
                },
                type:"post",
                dateType: "json",
                async : false,
                success: function(result) {
                    if(result==''){
                        showErroWin("对不起,您没有购买升级课程的权限！") ;
                    }
                    else{
                        try{
                            BuyLogAjax(getBuySubjectId(),8);
                        }catch(err){}
                        var aa = eval("("+result+")");
                        var contractInfo = aa.returnMessage.split(",");
                        //发送订单成功至用户
                        sendMsgToUserMobile(contractInfo[0]);
                        //sendAsyncMsgToUserMobile(1);
                        //支付宝参数
                        $("#oderid").html(contractInfo[0]);
                        $("#ode").val(contractInfo[0]);
                        $("#jinee").val(contractInfo[1]);
                        //网银在线参数
                        $("#chinaBankOid").val(contractInfo[0]);
                        //快钱参数
                        $("#KQOrderId").val(contractInfo[0]);
                        //银联在线
                        $("#unionPayOid").val(contractInfo[0]);
                        //银联语音支付参数
                        $("#contractVoiceCNo").val(contractInfo[0]);
                        $("#contractVoiceBNo").val(contractInfo[0]);
                        $("#orderonline").html(contractInfo[0]);
                        $("#orderpriceonline").html("￥"+fixNumber(contractInfo[1], 2));
                        $("#ordervoice").html(contractInfo[0]);
                        $("#ordervoicePrice").html("￥"+fixNumber(contractInfo[1], 2));
                        if(paytype == 10){
                            createContractSuccess(4);
                        }else{
                            createContractSuccess(1);
                        }
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

function selectOther(){
    try{
        BuyLogAjax(getBuySubjectId(),9);
    }catch(err){}
    $(".n_del").hide();
    $("#paytab4").hide();
    $("#paytab5").hide();
    $("#yhjdiv").hide();
    $("#buySettle").show();
    $("#priceinfo").show();
    $("#buy_step2").show();
    $("#buy_step3").hide();
    $("#shopingcart").show();
    $("#voicePayUl").hide();
    $("#voicePaySelecter").hide();
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

//弹窗效果
function locking(){
    $("#buy_black").css("display", "block");
    document.getElementById("buy_black").style.width = document.documentElement.clientWidth + "px";
    document.getElementById("buy_black").style.height = 1500 + "px";
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
    $("#buy_step_div").show();
    $("#buy_step2").show();
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
    var checkAddr = $("input[name=addradio]:checked").val();
    if( typeof(checkAddr) != 'undefined' && checkAddr != null && checkAddr > 0){
        createSendContract(checkAddr);
    }else{
        if($("#sendForm_sf").valid()) {
            if($("#sel_province_sf").val() < 1) {
                showErrorWin("请选择省。");
            } else if($("#sel_city_sf").val() < 1) {
                showErrorWin("请选择城市。");
            }
            else if($("#addressMobile_sf").val().trim() == "" && $("#addressTel_sf").val().trim() == "") {
                showErrorWin("请输入手机号码或固定电话号码。");
            } else {
                if(hasSendCourse && !confirm("订单中包含已通过货到付款方式购买的课程，您确定要继续吗？")){
                    return;
                }
                if(typeof(_gaq)!="undefinde"&&_gaq!=null){
                    _gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
                    _gaq.push(['_trackEvent', 'cheackout_shipping_4d', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
                }
                createSendContract(0);
            }
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

/*
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
 */

function backToData() {
    $("#buy_step_div").css("display", "none");
    $("#data_div").css("display", "block");
    outing();
    isBackToData = true;
}

function processAddress() {
    initArea_sf(1, 0);
    $.ajax({
        url : baselocation + "/cus/addrWeb!getFirstAddressByCusId.action",
        data : { },
        type : "post",
        dataType : "json",
        cache : false,
        async : true,
        success : function(result) {
            if(result.entity == null) {
                $("#addressUl").hide();
                $("#receiverName_sf").val("");
                $("#detailAddress_sf").val("");
                $("#postCode_sf").val("");
                $("#addressMobile_sf").val("");
                initArea($("#sel_province_sf").find("option:eq(0)").val(), 1);
                initArea($("#sel_city_sf").find("option:eq(1)").val(), 2);
                initArea_sf($("#sel_province_sf").find("option:eq(0)").val(), 1);
                initArea_sf($("#sel_city_sf").find("option:eq(1)").val(), 2);
            } else {
                $("#addressUl").show();
                $("#addressId").val(result.entity.id);
                $("#sel_province_sf").val(result.entity.provinceId);
                initArea(result.entity.provinceId, 1);
                $("#sel_city_sf").val(result.entity.cityId);
                initArea(result.entity.cityId, 2);
                $("#sel_town_sf").val(result.entity.townId);
                $("#receiverName_sf").val(result.entity.receiver);
                $("#detailAddress_sf").val(result.entity.address);
                $("#postCode_sf").val(result.entity.postCode);
                if(typeof(result.entity.mobile) != 'undefined' && result.entity.mobile != null){
                    if(result.entity.mobile.length != 11 || result.entity.mobile.indexOf("-")>-1) {
                        $("#addressTel_sf").val(result.entity.mobile);
                    } else {
                        $("#addressMobile_sf").val(result.entity.mobile);
                    }
                }
                $("#sendTime_sf").val(result.entity.sendTime);

                $("#sel_province_sf").val(result.entity.provinceId);
                initArea_sf(result.entity.provinceId, 1);
                $("#sel_city_sf").val(result.entity.cityId);
                initArea_sf(result.entity.cityId, 2);
                $("#sel_town_sf").val(result.entity.townId);
            }
        },
        error : function(error) {
        }
    });
    showAddressListInbuyPage(0);
}

function toforpwd(){
    window.open("http://highso.cn/static/web/column/68/index_1.shtml");
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
    jQuery.validator.addMethod("chinese", function(value, element) {
        var pattern = /^[\u4e00-\u9fa5]+$/;
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
                required : true,
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
                required : "请输入手机号码。",
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

    $("#voBorrowForm").validate({
        rules: {
            "voBorrowMobile" : {
                required : true,
                mobile : true,
                maxlength : 11
            },
            "voBorrowCardNo" : {
                maxlength : 19,
                digits : true,
                minlength : 16,
                required : true
            },
            "voBorrowCardName" : {
                required : true,
                maxlength : 40
            },
            "voBorrowIdCard" : {
                required : true,
                maxlength : 18,
                digits : true,
                minlength: 15
            },
            "city" : {
                min : 0
            },
            "voBorrowRandomCode":{
                required:true,
                maxlength:4,
                remote : {
                    data: {
                        'randomCode': function () {
                            return $("input[id=voBorrowRandomCode]").val();
                        }
                    },
                    async : false,
                    url : baselocation + "/cus/cusweb!checkRandomCode.action",
                    type : "post"
                }
            }
        },
        messages : {
            "voBorrowMobile" : {
                required : "手机号必须输入。",
                maxlength : "手机号码不能大于12个字。",
                mobile : "收机号码不正确。"
            },
            "voBorrowCardNo" : {
                required:"银行卡号必须输入。",
                digits : "银行卡号必须为数字。",
                minlength : "银行卡号为16-19位",
                maxlength : "银行卡号为16-19位"
            },
            "voBorrowCardName" : {
                required : "请输入卡主姓名。",
                maxlength : "卡主姓名不能大于20个中文字符。"
            },
            "voBorrowIdCard" : {
                required : "请输入身份证号码。",
                maxlength : "身份证号码为15-18位。",
                digits : "身份证必须号码为数字。",
                minlength : "身份证号码为15-18位。"
            },
            "city" : {
                min : "请输入开户地址"
            },
            "voBorrowRandomCode" : {
                required : "请输入验证码。",
                maxlength : "验证码为4位。",
                remote : "验证码输入不正确。"
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
    $("#voCreditForm").validate({
        rules: {
            "voCreditMobile" : {
                required : true,
                mobile : true,
                maxlength : 11
            },
            "voCreditCardNo" : {
                maxlength : 19,
                digits : true,
                minlength : 16,
                required : true
            },
            "voCreditCardName" : {
                required : true,
                maxlength : 40
            },
            "voCreditIdCard" : {
                required : true,
                digits : true,
                maxlength : 18,
                minlength : 15
            },
            "voCreditRandomCode":{
                required:true,
                maxlength:4,
                remote : {
                    data: {
                        'randomCode': function () {
                            return $("input[id=voCreditRandomCode]").val();
                        }
                    },
                    async : false,
                    url : baselocation + "/cus/cusweb!checkRandomCode.action",
                    type : "post"
                }
            }

        },

        messages : {
            "voCreditMobile" : {
                required : "手机号必须输入。",
                maxlength : "手机号码不能大于12个字。",
                mobile : "手机号码不正确。"
            },
            "voCreditCardNo" : {
                required:"银行卡号必须输入。",
                digits : "银行卡号必须为数字。",
                minlength : "银行卡号为16-19位",
                maxlength : "银行卡号为16-19位"
            },
            "voCreditCardName" : {
                required : "请输入卡主姓名。",
                maxlength : "卡主姓名不能大于20个中文字符。"
            },
            "voCreditIdCard" : {
                required : "请输入身份证号码。",
                maxlength : "身份证号码为15-18位。",
                digits : "身份证必须号码为数字。",
                minlength : "身份证号码为15-18位。"
            },
            "voCreditRandomCode" : {
                required : "请输入验证码。",
                maxlength : "验证码为4位。",
                remote : "验证码输入不正确。"
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

/**生成货到付款订单**/
function createSendContract(checkAddr){
    var changeAddress = true;
    if(checkAddr > 0){
        updateAddrByid();
        changeAddress = false;
        $.ajax({
            url : baselocation + "/finance/contract!makeContractAndCashRecord.action",
            data : {
                "changeAddress" : changeAddress,
                "addressId" : checkAddr,
                "couponCode":$("#couponCode").val(),
                "address.sendTime" : $("#sendTime_sf").val(),
                "sellids" : sellIds
            },
            type : "post",
            dataType : "json",
            cache : false,
            async : false,
            success : function(result) {
                if(result.returnMessage == "success") {
                    $("#ordereceive").html(result.entity[0]);
                    var recvMoney = parseFloat(result.entity[1]) + 10.00;
                    $("#orderpricerecv").html("￥"+fixNumber(recvMoney,2));
                    createContractSuccess(2);
                    try{
                        BuyLogAjax(getBuySubjectId(),6);
                    }catch(err){}
                }
                if(typeof(_gaq)!="undefinde"&&_gaq!=null){
                    _gaq.push(['_trackPageview', '/virtual/step_pay2.html']);//谷歌统计  添加虚拟页面游览量统计
                }
            },
            error : function(error) {
                alert('error');
            }
        });
    }else{
        $.ajax({
            url : baselocation + "/finance/contract!makeContractAndCashRecord.action",
            data : {
                "address.mobile" : $("#addressMobile_sf").val(),
                "changeAddress" : changeAddress,
                "address.receiver" : $("#receiverName_sf").val(),
                "address.provinceId" : $("#sel_province_sf").val(),
                "address.cityId" : $("#sel_city_sf").val(),
                "address.townId" : $("#sel_town_sf").val(),
                "address.address" : $("#detailAddress_sf").val(),
                "address.postCode" : $("#postCode_sf").val(),
                "address.sendTime" : $("#sendTime_sf").val(),
                "addressId" : checkAddr,
                "couponCode":$("#couponCode").val(),
                "sellids" : sellIds
            },
            type : "post",
            dataType : "json",
            cache : false,
            async : false,
            success : function(result) {
                if(result.returnMessage == "success") {
                    $("#ordereceive").html(result.entity[0]);
                    var recvMoney = parseFloat(result.entity[1]) + 10.00;
                    $("#orderpricerecv").html("￥"+fixNumber(recvMoney,2));
                    createContractSuccess(2);
                    try{
                        BuyLogAjax(getBuySubjectId(),6);
                    }catch(err){}
                }
                if(typeof(_gaq)!="undefinde"&&_gaq!=null){
                    _gaq.push(['_trackPageview', '/virtual/step_pay2.html']);//谷歌统计  添加虚拟页面游览量统计
                }
            },
            error : function(error) {
                alert('error');
            }
        });
    }
}

/**
 * 订单生成成功后操作
 * @param type
 */
function createContractSuccess(type){
    if(type == 1){
        $("#thirdPartySuccessDiv").show();
        $("#thridPartyTo").show();
    }else if(type == 2){
        $("#recvSuccessDiv").show();
    }else if(type == 3){
        $("#offlineDiv").show();
        $("#offlineDivComment").show();
        sendAsyncMsgToUserMobile(2);
    }else if(type == 4){
        $("#voidPaySuccessDiv").show();
    }
    $("#shopingcart").hide();
    $("#buy_step2").hide();
    $("#buy_step3").show();
    $("#priceinfo").hide();
    $("#buyStepimg").attr("src", importURL + "/images/web/public/buy/buyStep03.jpg");
    outing();
}
/**关闭购买页面显示订单层**/
function closeContractWin(){
    $("#buy_step_div").css("display", "none");
    outing();
}
/**记住我**/
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


/*
 *函数功能：从href获得参数是否为美工页面跳转来。
 * 美工页面需要添加购买的课程到cookies中
 */
function   getBuyTypeValue(name){
    var   str=window.location.href;
    if   (str.indexOf(name)!=-1){
        var   pos_start=str.indexOf(name)+name.length+1;
        var   pos_end=str.indexOf( "&",pos_start);
        if   (pos_end==-1){
            pos_end=str.length;
        }
        if (str.substring(pos_start,pos_end)=="11"){
            return   true;
        }else{
            return   false;
        }
    } else {
        return   false;
    }
}

/*
 *函数功能：从href获得参数
 *sHref:   http://highso.org.cn/?arg1=d&arg2=re
 *sArgName:sHref, arg2
 *return:  the value of arg. d, re
 * 为美工页面提供的
 */
function GetArgsFromHref(sArgName){
    var  sHref=window.location.href;
    var retval = "";
    var args  = sHref.split("?");
    /*参数为空*/
    if(args[0] == sHref){
        return retval; /*返回空*/
    }
    var str = args[1];
    args = str.split("&");
    for(var i = 0; i < args.length; i ++){
        str = args[i];
        var arg = str.split("=");
        if(arg.length <= 1) continue;
        if(arg[0] == sArgName) retval = arg[1];
    }
    return retval;
}

/*
 *函数说明，根据html页面传来的mehord参数，确定购买的那个授卖方式
 *调用相应的buy方法
 *为美工页面提供的
 function invokeBuyMethord(methord){
 methord= methord.toLowerCase();
 if 	(methord==""){
 return;
 }else if(methord=="buySf1".toLowerCase()){
 buySf1();
 }else if(methord=="buySf2".toLowerCase()){
 buySf2();
 }else if(methord=="buySf3".toLowerCase()){
 buySf3();
 }else if(methord=="buyCpa".toLowerCase()){
 buyCpa();
 }else if(methord=="buyCpa2".toLowerCase()){
 buyCpa2();
 }else if(methord=="buyCpa1".toLowerCase()){
 buyCpa1();
 }else if(methord=="buyCpa3".toLowerCase()){
 buyCpa3();
 }else if(methord=="buyCpa4".toLowerCase()){
 buyCpa4();
 }else if(methord=="buyRl3".toLowerCase()){
 buyRl3();
 }else if(methord=="buyRl2".toLowerCase()){
 buyRl2();
 }else if(methord=="buyKJ".toLowerCase()){
 buyKJ();
 }else if(methord=="buyZq".toLowerCase()){
 buyZq();
 }else if(methord=="buyJz1".toLowerCase()){
 buyJz1();
 }else if(methord=="buyJz2".toLowerCase()){
 buyJz2();
 }else if(methord=="buyGk".toLowerCase()){
 buyGk();
 }else if(methord=="buyGwy".toLowerCase()){
 buyGwy();
 }
 }
 */

function addToAddress(){
    if($("#sendForm_sf").valid()) {
        if($("#sel_province_sf").val() < 1) {
            showErrorWin("请选择省。");
        } else if($("#sel_city_sf").val() < 1) {
            showErrorWin("请选择城市。");
        }
        else if($("#addressMobile_sf").val().trim() == "" && $("#addressTel_sf").val().trim() == "") {
            showErrorWin("请输入手机号码或固定电话号码。");
        } else {
            if(typeof(_gaq)!="undefinde"&&_gaq!=null){
                _gaq.push(['_trackPageview', '/virtual/step_zhifu.html']);//谷歌统计  添加虚拟页面游览量统计
                _gaq.push(['_trackEvent', 'cheackout_shipping_4d', '4','checkout_shipping_4']);//谷歌统计  添加事件追踪统计
            }
            $.ajax({
                url : baselocation + "/finance/contract!saveAddressSF.action",
                data : {
                    "address.mobile" : $("#addressMobile_sf").val(),
                    "changeAddress" : $("#changeAddress_sf").val(),
                    "address.receiver" : $("#receiverName_sf").val(),
                    "address.provinceId" : $("#sel_province_sf").val(),
                    "address.cityId" : $("#sel_city_sf").val(),
                    "address.townId" : $("#sel_town_sf").val(),
                    "address.address" : $("#detailAddress_sf").val(),
                    "address.postCode" : $("#postCode_sf").val(),
                    "addressId" : 0
                },
                type : "post",
                dataType : "json",
                cache : false,
                async : false,
                success : function(result) {
                    if(result.returnMessage == "success") {
                        showAddressListInbuyPage(0);
                    }
                },
                error : function(error) {
                    alert('error');
                }
            });
        }
    }

}

/*
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
 */

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

//页面初试化显示地址
var ishaveaddress=0;//是否存在地址
function showAddressListInbuyPage(isadd){
    $.ajax({
        url : baselocation + "/cus/addrWeb!showAddressListInbuyPage.action",
        dataType : "json",
        cache : false,
        success : function(result) {
            var alladdressinfo="";
            ishaveaddress=0;
            if (result.returnMessage=="success"){
                $.each(result.entity,function(name,value){
                    addrObjArray = result.entity;
                    ishaveaddress=ishaveaddress+1;
                    var tmpinf="";
                    if(name==0){//常用地址
                        tmpinf="<li><span class=\"fl\"><input type=\"radio\" value=\""+value.id+"\" name=\"addradio\" checked=\"checked\" onclick=\"changeRecvAddr("+value.id+")\">"+value.receiver+"&nbsp;"+value.provinceName+""+value.cityName+""+value.townName+  ""+value.address+"</span><a class=\"fr\" href=\"javascript:deladdressbyid("+value.id+")\">删除</a></li>";
                    }else{
                        tmpinf="<li><span class=\"fl\"><input type=\"radio\" value=\""+value.id+"\" onclick=\"changeRecvAddr("+value.id+")\" name=\"addradio\">"+value.receiver+"&nbsp;"+value.provinceName+""+value.cityName+""+value.townName+  ""+value.address+"</span><a class=\"fr\" href=\"javascript:deladdressbyid("+value.id+")\">删除</a></li>";
                    }
                    alladdressinfo=alladdressinfo+tmpinf;
                });
                $("#addressUl").html(alladdressinfo);
                addnewaddshowflag=0;
            }
        },
        error : function(error) {
            alert("error");
        }
    });
}

function changeRecvAddr(addrId){
    if(typeof(addrObjArray) != 'undefined' && addrObjArray != null){
        $.each(addrObjArray,function(name,value){
            if(addrId == value.id){
                $("#addressId").val(value.id);
                initArea_sf(1, 0);
                initArea_sf(value.provinceId, 1);
                initArea_sf(value.cityId, 2);
                $("#sel_province_sf").val(value.provinceId);
                $("#receiverName_sf").val(value.receiver);
                $("#detailAddress_sf").val(value.address);
                $("#postCode_sf").val(value.postCode);
                $("#sel_city_sf").val(value.cityId);
                $("#sel_town_sf").val(value.townId);
                if(typeof(value.mobile) != 'undefined' && value.mobile != null && value.mobile != ""){
                    if(value.mobile.length != 11 ||value.mobile.indexOf("-")>-1) {
                        $("#addressTel_sf").val(value.mobile);
                    } else {
                        $("#addressMobile_sf").val(value.mobile);
                    }
                }else{
                    $("#addressMobile_sf").val("");
                }
                $("#sendTime_sf").val(value.sendTime);
            }
        });
    }
}


function deladdressbyid(id){
    if(window.confirm("确认删除该收货地址？")) {
        $.ajax({
            url : baselocation + "/cus/addrlimit!deleteAddresssf.action",
            data:{"address.id":id},
            type : "post",
            dataType : "json",
            cache : false,
            success : function(result) {
                processAddress();
            },
            error : function(error) {
            }
        });
    }
}

function updateAddrByid(){
    if($("#addressId").val() != null && $("#addressId").val() > 0){
        if($("#sendForm_sf").valid()) {
            $.ajax({
                url : baselocation + "/cus/addrlimit!updateOrAddAddr.action",
                data:{
                    "address.mobile" : $("#addressMobile_sf").val(),
                    "changeAddress" : $("#changeAddress_sf").val(),
                    "address.receiver" : $("#receiverName_sf").val(),
                    "address.provinceId" : $("#sel_province_sf").val(),
                    "address.cityId" : $("#sel_city_sf").val(),
                    "address.townId" : $("#sel_town_sf").val(),
                    "address.address" : $("#detailAddress_sf").val(),
                    "address.postCode" : $("#postCode_sf").val(),
                    "address.id" : $("#addressId").val()
                },
                type : "post",
                dataType : "json",
                cache : false,
                success : function(result) {
                },
                error : function(error) {
                }
            });
        }
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
        showErrorWin("订单金额为0时不能使用优惠券");
        return;
    }
    var str=$("#couponCode").val().trim();

    if(str==""||str==null){
        showErrorWin("请输入优惠券编号");
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
                                showErrorWin("此优惠券无效");
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
                                showErrorWin("只适用于"+obj.couponType.subjectName);
                                return;
                            }
                            couponCodeJS(obj);
                            return;
                        }else{
                            canclecode();
                            showErrorWin("此优惠券无效");
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
        showErrorWin("消费金额不足最低消费"+obj.couponType.leastPrice+"元");
        return;
    }
    $("#codeName").html(obj.title);
    var zhe=obj.couponType.preferentialPrice;
    var  yf=$("#freight").html().replace("￥","");
    var yunfei=parseFloat(yf);
    if(obj.couponType.cType==1)
    {
        $("#codeMoney").html(zhe+"折");
        $("#yhmoney").html((zongjia-((zongjia*(zhe*10/100)))).toFixed(2)+"元");
        $("#payprice").html("￥"+(((zongjia)*(zhe*10/100)).toFixed(2))+"元");
        $("#paySumPrice").html("￥"+(((zongjia)*(zhe*10/100)).toFixed(2))+"元");
        $("#paypriceRecv").html("￥"+((((zongjia)*(zhe*10/100)+ 10) ).toFixed(2))+"元");
        $("#paySumPriceRecv").html("￥"+((((zongjia)*(zhe*10/100)+10)).toFixed(2))+"元");
    }
    if(obj.couponType.cType==2)
    {
        $("#codeMoney").html(obj.couponType.preferentialPrice+"元");
        $("#yhmoney").html((zongjia-(zongjia-zhe))+"元");
        $("#payprice").html("￥"+(zongjia-zhe)+"元");
        $("#paySumPrice").html("￥"+(zongjia-zhe)+"元");;
        $("#paypriceRecv").html("￥"+(zongjia+10-zhe)+"元");
        $("#paySumPriceRecv").html("￥"+(zongjia+10-zhe)+"元");
    }
    $("#codesucess").show();
    //setTimeout(function(){$("#codesucess").hide();},3000);
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
        $('#tjcode').show();
        $("#buyCoupon_dis").hide();
    }
    else{
        $('#tjcode').hide();
        $("#buyCoupon_dis").show();
        $("#canclecode").hide();
    }
}

function canclecode()
{
    $("#couponCode").val("");
    $("#couponCode").removeAttr('disabled');
    $("#tjcode").show();
    //$("#tjcode").attr("class","coupon_btn coupon_btn_02");
    $("#codesucess").hide();
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
                window.location.href="http://highso.cn/static/web/column/67/index_1.shtml";
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
/**Yn**/
function createOffLineContract(){
    if(hasSendCourse && !confirm("订单中包含已通过货到付款方式购买的课程，您确定要继续吗？")){
        return;
    }
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
                $("#orderoffline").html(result.entity[0]);
                $("#orderpriceoffline").html("￥"+fixNumber(result.entity[1],2));
                createContractSuccess(3);
                try{
                    BuyLogAjax(getBuySubjectId(),7);
                }catch(err){}
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

/**Yn**/
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

/**Yn**/
function sendSms2Customer(){
    var mobile = $.trim($("#mobileNOS").val());
    var message = "";
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
    var selectedObj = $("input[name='comType']:checked").parent();
    selectedObj.siblings().each(function(i) {
        switch(i){
            case 0:
                message += "银行名称:" + $(this).html() + "   ";
                break;
            case 1:
                message += "开户行:" + $(this).html() + "   ";
                break;
            case 2:
                message += "卡号:" + $(this).html() + "   ";
                break;
            case 3:
                message += "开户名:" + $(this).html() + "   ";
                break;
        }
    });
    $.ajax({
        url : baselocation + "/finance/contract!sendSmsOffLineCompanyBank.action",
        data : {
            "comType" : comType,
            "mobileNO" : mobile,
            "contractno": $("#orderoffline").html(),
            "paymoney" : $("#orderpriceoffline").html(),
            "message" : message
        },
        type : "post",
        dataType : "json",
        cache : false,
        async : false,
        success : function(result) {
            if(result.returnMessage == "success") {
                setTimeout("closeSmsSuccessWin()",4000);
                $(".buySubmit3").unbind("click");
                $(".buySubmit3").hide();
            }
        },
        error : function(error) {
            alert('error');
        }
    });
}

function closeSmsSuccessWin(){
    $("#buyPhone_pop02").hide();
}

/*--Yn--*/
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
/*--Yn--*/
function changeImg(obj){
    var path = "http://testimport.highso.org.cn/images/web/public/buy/gzyh"+obj.value+".jpg";
    $("#yhimg").attr("src",path);
}

/*--Yangning --*/
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


/*---Yangning----*/
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
/*--Yang check is exist pkgs user have brought*
 function getBuyCountByIds(pkgIds){
 if(pkgIds != null && $.trim(pkgIds).length >0){
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
 }
 return 0;
 }
 */

function addordertmp(){
    var shortcarsinfo= getCookie("shortcars");
    if(shortcarsinfo!= null && shortcarsinfo!= ''){
        BuyAjax(shortcarsinfo);
        DeleteCookie(shortcarsinfo);
    }
}
/*Yagning 2012/02/27*/
function showStepWin(){
    $("#buy_step2").hide();
    var $width;
    var $hei;
    $width = $(window).width()/2 - 187;
    $hei = $(window).height()/2 - 20;
    $(".p-wiidow").css({"left":$width+"px","top":$hei+"px","z-index":1000}).show();
}
/*Yagning 2012/02/27 生成子订单*/
function genSubConract(){
    var cno = $("#KQOrderId").val();
    window.location.href = baselocation + "/alipay/subsc!genSC.action?cno=" + cno;
}
//Yangning add 2012/02/16 删除单个商品
function deleteids() {
    var goodspage = getCookie("coursesbao");
    var ids = "";
    if(goodspage != null) {
        $("input[name='n_che']").each(function(i) {
            if($(this).attr("checked")== true){
                ids += $(this).val() + "&";
            };
        });

        if(ids != null && $.trim(ids).length > 0){
            var arr = ids.split("&");
            for(var i=0;i<arr.length;i++){
                if(arr[i] != null && $.trim(ids).length > 0){
                    deleteid(arr[i]);
                }
            }
        }
    }
}

/*-YangNing 2012/02/17-*/
function getTeacherByName(obj){
    var name = $(obj).html();
    $.ajax({
        url : baselocation + "/cou/teacherweb!getTeacherByName.action",
        data : {
            'queryTeacherCondition.name' : name
        },
        type : "post",
        dataType : "json",
        async :false,
        cache : false,
        success : function (result) {
            if(result.returnMessage != null && result.returnMessage == "success"){
                var career = result.entity.career;
                $("#tips").html("<em class=\"n_tips_arrow\"></em>" + career);
            }
        }
    });
}
/*-YangNing 2012/02/17-*/
function chkInterval(obj){
    if(lastTime == null){
        lastTime = new Date();
        getTeacherByName(obj);
        var p = $(obj).offset();
        $(".n_teacher_tips").show().css({left:p.left-210+"px",top:p.top-8+"px"});
    }else{
        var interval = new Date().getTime() - lastTime.getTime();
        if(interval > 1500){
            lastTime = new Date();
            getTeacherByName(obj);
            var p = $(obj).offset();
            $(".n_teacher_tips").show().css({left:p.left-210+"px",top:p.top-8+"px"});
        }else{
            lastTime = new Date();
        }
    }
}

var checkedFlag = false;
function chkedAll(){
    if(!checkedFlag){
        $("input[name='n_che']").each(function()
        {
            $(this).attr("checked",true);
        });
        checkedFlag = true;
    }else{
        $("input[name='n_che']").each(function()
        {
            $(this).attr("checked",false);
        });
        checkedFlag = false;
    }
}

//Yangning 2012/04/12
function gotoSub(){
    issteppay = true;
    goToZFB();
}

function changeBankTitle(){
    if($("#moreKQBank").css("display") == "none") {
        $("#bankChangeBtn").attr("title","收起更多银行 >>");
    }else{
        $("#bankChangeBtn").attr("title","显示更多银行 >>");
    }
}

//---------Yangning 其它3种支付跳转   货到付款   ,分批次，银行汇款------------//
function gotoOtherPay(){
    if($("#otherPay_Offline").attr("checked") == true){
        createOffLineContract();
        return;
    }
    if($("#otherPay_Sub").attr("checked") == true){
        issteppay = true;
        goToZFB();
        return;
    }
    //如货到付款隐藏，则显示，并处理地址信息
    if($("#otherPay_recv").attr("checked") == true){
        if($("div.goods_payment").css("display") == "none"){
            $("div.goods_payment").show();
            processAddress();
            return;
        }else{
            confirmAddress();
            return;
        }
    }
    showErrorWin("请选择支付方式");
}

//切换收货地址
function changeAddressDiv(index,count){
    var domOldHtml;
    for(var i=1;i<=count;i++){
        domOldHtml = $("#addrBox"+i).html();
        if(domOldHtml != null && $.trim(domOldHtml).length > 0){
            if(i != index){
                $("#addrBox"+index).html(domOldHtml);
                $("#addrBox"+i).html("");
                var linkHtml = $("#addrBox"+i).parent().find("div.modifyaddress").html();
                $("#addrBox"+index).parent().find("div.modifyaddress").html(linkHtml);
                $("#addrBox"+i).parent().find("div.modifyaddress").html("");
                break;
            }
        }
    }
    addnewaddshow(0);
}

function showCreateofflineAddressDiv(){
    $("#createofflineAddressDiv").show();
    $("#createsendAddressDiv").hide();
}

function showCreatesendAddressDiv(){
    $("#createofflineAddressDiv").hide();
    $("#createsendAddressDiv").show();
}

//打开优惠券
$(function(){
    var i = 0
    $(".buyCoupon_tit").click(function(){
        i++
        if(i%2 != 0){
            $(".buyCoupon_con").show()
            $(".buyCoupon").css("width","385px")
            $(".buyCoupon_tit").addClass("buyCoupon_tit2")
        }else{
            $(".buyCoupon_con").hide()
            $(".buyCoupon").css("width","170px")
            $(".buyCoupon_tit").removeClass("buyCoupon_tit2")
        }
        return false
    })
})
//支付方式切换
$(function(){
    $("div.buyPay_con:gt(0)").hide();
    $("ul.buyPayTit li").click(function() {
        var value = $(this).attr("id");
        if(value == "paytab1"){
            $("#payprice").show();
            $("#paypriceRecv").hide();
            $("#paySumPriceRecv").hide();
            $("#paySumPrice").show();
            $("#freight").html("￥0");
            check_kqbank(1);

        }else if(value == "paytab2"){
            $("#paySumPriceRecv").hide();
            $("#paySumPrice").show();
            $("#payprice").show();
            $("#paypriceRecv").hide();
            $("#freight").html("￥0");
            check_union(1);
        }else if(value == "paytab3"){
            $("#paySumPriceRecv").hide();
            $("#paySumPrice").show();
            $("#payprice").show();
            $("#paypriceRecv").hide();
            $("#freight").html("￥0");
            if(!flag){
                check_unv(1);
                if(!isVoAdressShowed){
                    initArea_vo(1,0);
                    isVoAdressShowed = true;
                }
            }else{
                check_kqbank(2);
            }
        }else if(value == "paytab4"){
            $("#paySumPriceRecv").show();
            $("#paySumPrice").hide();
            $("#payprice").hide();
            $("#paypriceRecv").show();
            $("#freight").html("￥10.00");
            if(!isSearched){
                processAddress();
                isSearched = true;
            }
            check_receive(1);
        }else if(value == "paytab5"){
            $("#paySumPriceRecv").hide();
            $("#paySumPrice").show();
            $("#payprice").show();
            $("#paypriceRecv").hide();
            $("#freight").html("￥0");
            check_offline(1);
        }
        $(this).addClass("current").siblings("li").removeClass("current");
        $("div.buyPay_con:eq("+$("ul.buyPayTit li").index(this)+")").show().siblings("div.buyPay_con").hide();
    });
});
//网上支付---更多银行打开
$(function(){
    $(".buyB_bankMoreClick1").click(function(){
        $(".buyB_payPlatNone").fadeIn(100);
        $(".buyB_bankMoreClick1B").show();
        $(".buyB_bankMoreClick2B").hide();
    })
    $(".buyB_bankMoreClick2").click(function(){
        $(".buyB_payPlatNone").fadeOut(100);
        $(".buyB_bankMoreClick2B").show();
        $(".buyB_bankMoreClick1B").hide();
    })
})
//登录注册切换
$(function(){
    $("div.buyReg_con:gt(0)").hide();
    $("ul.buyReg_tit li").click(function() {
        $(this).addClass("current").siblings("li").removeClass("current");
        $("div.buyReg_con:eq("+$("ul.buyReg_tit li").index(this)+")").show().siblings("div.buyReg_con").hide();
    });
});

$(function(){
    $(".buyReg_close").click(function(){
        $(".buyReg").css("display", "none");
        $("#buy_black").css("display", "none");
    })
})
//发送到手机
$(function(){
    $(".buyB_bankMoreClick1").click(function(){
        $(".buyB_payPlatNone").fadeIn(100);
        $(".buyB_bankMoreClick1B").toggle();
        $(".buyB_bankMoreClick2B").toggle();
    })
    $(".buyB_bankMoreClick2").click(function(){
        $(".buyB_payPlatNone").fadeOut(100);
        $(".buyB_bankMoreClick2B").toggle();
        $(".buyB_bankMoreClick1B").toggle();
    })
})
$(function(){
    $(".buySubmit3").click(function(){
        $("#buyPhone_pop01").fadeIn();
    })
    $(".buyPhone_close").click(function(){
        $(this).parent("h6").parent(".buyPhone_pop").fadeOut();
    })
    $(".buySubmit4").click(function(){
        $("#buyPhone_pop01").css({"display":"none"});
        $("#buyPhone_pop02").fadeIn();
    })
})

function getBuySubjectId(){
    var buySubjectid = getCookie("buySubjectid");
    if(buySubjectid != null){
        return buySubjectid;
    }else{
        return 0;
    }
}

function chkUnionVoicePay(){
    if(	$("input[name=voCardType]:checked").val() == 14){
        if($("#voBorrowForm").valid()){
            sendUnionVoiceMessage($("#voBorrowCardNo").val(),$("#voBorrowMobile").val(),2);
        }
    }else{
        if($("#voCreditForm").valid()){
            sendUnionVoiceMessage($("#voCreditCardNo").val(),$("#voCreditMobile").val(),1);
        }
    }
}

function sendUnionVoiceMessage(card,mobile,type){
    $.ajax({
        url : baselocation + "/alipay/unionVoice!chkCard.action",
        data : {
            'cardNumber' : card,
            'cardType' : $("input[name=voCardType]:checked").val(),
            'cardMobile' : mobile
        },
        type : "post",
        dataType : "json",
        async :false,
        cache : false,
        success : function (result) {
            if(result != null){
                if(result.returnMessage == "success"){
                    var message = result.entity;
                    if(typeof(message) != 'undefined' && message != null ){
                        var resCode = message.respCode;
                        $("#trascCode").val(message.transData);
                        if(resCode == "T404"){
                            showErrorWin("系统不支持此卡，交易拒绝");
                            return;
                        }else if(resCode == "0000"){
                            if($("input[name=voCardType]:checked").val() == "21"){
                                goToUnionVoiceCreditPay();
                            }else if($("input[name=voCardType]:checked").val() == "14"){
                                goToUnionVoiceBorrowPay();
                            }
                            $("#voCreditCardNo").attr("readonly","readonly");
                            $("#voCreditMobile").attr("readonly","readonly");
                            $("#voBorrowCardNo").attr("readonly","readonly");
                            $("#voBorrowMobile").attr("readonly","readonly");
                        }else if(resCode == "T437" || resCode=="T438"){
                            alert("您需要提供部分开户信息完成支付！");
                            $("#voCreditCardNo").attr("readonly","readonly");
                            $("#voCreditMobile").attr("readonly","readonly");
                            $("#voBorrowCardNo").attr("readonly","readonly");
                            $("#voBorrowMobile").attr("readonly","readonly");
                            voicePayClickedNum = type;
                            //此卡为新卡需要用户提交信息
                            showVoOtherInfo(type);

                        }else if(resCode == "T436"){
                            showErrorWin("该卡交易时间受限，拒绝交易");
                            return;
                        }else if(resCode == "T432"){
                            showErrorWin("黑名单银行卡号，拒绝交易");
                            return;
                        }
                    }
                }else if(result.returnMessage == "faild"){
                    showErrorWin("输入信息有误");
                }else{
                    showErrorWin("系统繁忙，请稍后重试");
                }
            }
        },
        error : function(error){
            showErrorWin("系统繁忙，请稍后重试");
        }
    });
}



function goToUnionVoiceCreditPay(){
    if($("#voCreditForm").valid()){
        makeContract();
        var contractNo = $("#contractVoiceCNo").val();
        var cardMobile = $("#voCreditMobile").val();
        var ownerName = $("#voCreditCardName").val();
        var voCreditCardNo = $("#voCreditCardNo").val();
        var voCreditCardId = $("#voCreditIdCard").val();

        $.ajax({
            url : baselocation + "/alipay/unionVoice!goToUnionPay.action",
            data : {
                'contractNo' : contractNo,
                'cardType' : $("input[name=voCardType]:checked").val(),
                'cardMobile' : cardMobile,
                'ownerName'  : ownerName,
                'cardNumber' : voCreditCardNo,
                'ownerCardId' : voCreditCardId,
                'transData'  : $("#trascCode").val()
            },
            type : "post",
            dataType : "json",
            async :false,
            cache : false,
            success : function (result) {
                if(result != null){
                    if(result.returnMessage == "success"){
                        var entity = result.entity;
                        if(entity != null){
                            var respCode = entity.respCode;
                            $("#voiceComu").hide();
                            if(respCode == '00A3'){
                                $("#voiceSuccess").show();
                            }else{
                                $("#voiceFailed").show();
                            }
                        }
                    }else if(result.returnMessage == "faild"){
                        showErrorWin("输入信息有误");
                    }else{
                        showErrorWin("系统繁忙，请稍后重试");
                    }
                }
            },
            error : function(error){
                showErrorWin("系统繁忙，请稍后重试");
            }
        });
    }
}

function goToUnionVoiceBorrowPay(){
    if($("#voBorrowForm").valid()){
        makeContract();
        var contractNo = $("#contractVoiceCNo").val();
        var cardMobile = $("#voBorrowMobile").val();
        var ownerName = $("#voBorrowCardName").val();
        var voBorrowCardNo = $("#voBorrowCardNo").val();
        var voBorrowCardId = $("#voBorrowIdCard").val();
        var strProvince = $('#voProvince option:selected').text();
        var strCity = $('#voCity option:selected').text();

        var ownerAddr = strProvince + "," + strCity;
        $.ajax({
            url : baselocation + "/alipay/unionVoice!goToUnionPay.action",
            data : {
                'contractNo' : contractNo,
                'cardType' : $("input[name=voCardType]:checked").val(),
                'cardMobile' : cardMobile,
                'ownerName'  : ownerName,
                'cardNumber' : voBorrowCardNo,
                'ownerAddress' : ownerAddr,
                'ownerCardId'  : voBorrowCardId,
                'transData'  : $("#trascCode").val()
            },
            type : "post",
            dataType : "json",
            async :false,
            cache : false,
            success : function (result) {
                if(result != null){
                    if(result.returnMessage == "success"){
                        var entity = result.entity;
                        if(entity != null){
                            var respCode = entity.respCode;
                            $("#voiceComu").hide();
                            if(respCode == '00A3'){
                                $("#voiceSuccess").show();
                            }else{
                                $("#voiceFailed").show();
                            }
                        }
                    }else if(result.returnMessage == "faild"){
                        showErrorWin("输入信息有误");
                    }else{
                        showErrorWin("系统繁忙，请稍后重试");
                    }
                }
            },
            error : function(error){
                showErrorWin("系统繁忙，请稍后重试");
            }
        });
    }
}


function showVoOtherInfo(type){
    if(type == 1){
        $("#voCreditCardNameli").show();
        $("#voCreditIdCardli").show();
        $("#borrowSpan").hide();
    }else{
        $("#voBorrowAddrli").show();
        $("#voBorrowCardNameli").show();
        $("#voBorrowIdCardli").show();
        $("#creditSpan").hide();
    }
}


function initArea_vo(id, index){
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
                var  sb = new StringBuffer();
                sb.append("<option value='0'>-请选择-</option>").append(html);
                $("#voProvince").html(sb.toString());
            }else if(index == 1) {
                $("#voCity").html("");
                $(html).appendTo("#voCity");
            }
        },
        error : function(error) {
        }
    });
}
