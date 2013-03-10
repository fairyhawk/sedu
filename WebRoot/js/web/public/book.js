<!-- 谷歌统计 START -->
var _gaq = _gaq || [];
  _gaq.push(['_setAccount','UA-22194725-1']);
  _gaq.push(['_addOrganic', 'soso', 'w']);
  _gaq.push(['_addOrganic', '3721', 'name']); 
  _gaq.push(['_addOrganic', 'yodao', 'q']); 
  _gaq.push(['_addOrganic', 'sogou', 'query']); 
 _gaq.push(['_setDomainName','none']);
  _gaq.push(['_setAllowLinker',true]);
  _gaq.push(['_setAllowHash',false]);
  _gaq.push(['_trackPageview']);
  _gaq.push(['_trackPageLoadTime']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
<!-- 谷歌统计 END //-->


function initArea(id, index){
	//$("h1").html($("h1").html()+"<span>（送考前独家内部资料！）</span>");
	$("#addyan").hide();
		var parentId = 1;
		if(id != null && id != 0 && !isNaN(id)) {
			parentId = id;
		}
       if(id==0&&index!=0){
	   if(index==1)
		{
		$("#sel_city").html("<option value='0'>-------</option>");
		$("#sel_town").html("<option value='0'>-------</option>");
		}
	    if(index==2){
		$("#sel_town").html("<option value='0'>-------</option>");
	    }
	return false;
      }
		$.ajax({
			url :  baselocation+"/cus/areaWeb!getAreasByParentId.action",
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
                   $("<option value='0'>-------</option>").appendTo("#sel_province");
					$(html).appendTo("#sel_province");
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
				alert('error');
			}
		});
	}
function addOrder()
{
/*var buy_num=$("#buy_num").val();
if(buy_num == null ||buy_num == "")
{
alert("购买数量不能为空");
return false;
}
if(/^\d+$/.test(buy_num )==false || isNaN(buy_num))
{
alert("请输入正确定的购买数量");
return false;
}
if(buy_num>9999||buy_num<1)
{
alert("购买数量请输入1-9999整数");
return false;
}
else{
$("#buySum").val(buy_num);
}
var cusName=$("#cusName").val();
if(cusName == null ||cusName == "")
{
alert("收件人姓名不能为空");
return false;
}
var province=$("#sel_province").val();
if(province==0){
alert("请选择省");
return false;
}
var city=$("#sel_city").val();
if(city==0){
alert("请选择市");
return false;
}
var town=$("#sel_town").val();
if(town==0){
alert("请选择县");
return false;
}
var addr=$("#detailAddress").val();
if(addr == null ||addr == "")
{
alert("送货地址不能为空");
return false;
}
var tel=$("#addressTel").val()+"";
var mobilephone=$("#addressMobile").val()+"";
if(tel == "" && mobilephone == "")
{
alert("请填写手机或电话");
return false;
}
if(mobilephone!="")
{
if(/^1{1}[0-9]{10}$/.test(mobilephone)==false)
{
alert("请输入正确的手机号");
return false;
}
}

if(tel!="")
{
if(/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(tel)==false)
{
alert("请输入正确的电话号码");
return false;
}
}
var postCode=$("#postCode").val();
if(postCode == null ||postCode == "")
{
alert("邮编不能为空");
return false;
}
if(/[0-9]{6}$/.test(postCode)==false)
{
alert("请输入正确的邮编");
return false;
}
if($("#remarkcontnet").val().length>250){
alert("备注信息不能超过250字");
return false;
}*/
if($("#myform").valid()){
var province=$("#sel_province").val();
if(province==0){
$("#addyan").html("请选择省");
$("#addyan").show();
return false;
}
var city=$("#sel_city").val();
if(city==0){
	$("#addyan").html("请选择市");
	$("#addyan").show();
return false;
}
var town=$("#sel_town").val();
/*if(town==0){//验证县去掉
	$("#addyan").html("请选择县");
	$("#addyan").show();
return false;
}*/
if(($("#addressMobile").val()=="")&&($("#addressTel").val()==""))
{
	$("#telyan").html("请输入手机或电话号码");
	$("#telyan").show();
	return false;
}
addr=$("#sel_province").find('option:selected').text()+","+$("#sel_city").find('option:selected').text()+","+$("#sel_town").find('option:selected').text()+$("#detailAddress").val();
$("#buyaddr").val(addr);
//$("#myform").submit();

	add();
}
//添加到数据库
return false;
}
function closeContractWin()
{
$("#buy_step4").hide();
}
function add(){
	var str="";
	 $('input').each(function(){ 
			str+=$(this).attr("name")+"="+encodeURIComponent(encodeURIComponent($(this).val()))+"&";
	  });
    str+="buyInfo.remark="+encodeURIComponent(encodeURIComponent($("#remarkcontnet").val()));
	$.ajax({
		url :  baselocation+"/book/buyInfo!addBuyInfo.action?"+str,
		type : "post",
		cache : false,
		async : false,
		success : function(result) {
		if(result=="success")
			{
			$("#web_top_black").show();
			$("#tan").show();
			}else{
				alert("返回异常");
			}
		},
		error : function(error) {
			alert('提交失败');
		}
	});
}
function closeTan(){
	$("#web_top_black").hide();
	$("#tan").hide();
}

function toColumn(keyWord) {
	var columnId = getColumnId(keyWord);
	if(columnId != -1) {
		location.href = baselocation + "/static/web/column/" + columnId + "/index_1.shtml";
	}
}


function getColumnId(keyWord) {
	var columnId = null;
	var v =baselocation;
	if(v.indexOf("ga.highso.org")!=-1){
		keyWord+="_org";
	}
	else if(v.indexOf("highso.org.cn")!=-1){
		keyWord+="_org_cn";
	}
	else if(v.indexOf("highso.org")!=-1){
		keyWord+="_org";
	}
	else if(v.indexOf("highso.com.cn")!=-1){
		keyWord+="_com_cn";
	}
	else if(v.indexOf("highso.net.cn")!=-1){
		keyWord+="_net_cn";
	}else{
		keyWord+="_cn" ;
	}
	$.ajax({
		url : baselocation + "/cus/cusweb!getColumnId.action",
		data : {
			"queryColumnsCondition.keyWord" : keyWord
		},
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			if(result.returnMessage == "success") {
				columnId = result.entity;
			} else {
				columnId = -1;
			}
		},
		error : function(error) {
			alert('error');
		}
	});
	return columnId;
}

function returnUrl(){
	location.href =baselocation;
}
function returnCourseInfo(keyword){
	var courseUrl="";
	if(keyword=="cjjjs"||keyword=="zjjjs" || keyword=="jjs"){
		courseUrl=baselocation+"/jjs";
	}
	else if(keyword=="xl")
	{
		courseUrl=baselocation+"/xl";
	}
	else if(keyword=="zq")
	{
	courseUrl=baselocation+"/zq";
	}
	else if(keyword=="kjz")
	{
	courseUrl=baselocation+"/kjz";
	}
	else if(keyword=="jz")
	{
	courseUrl=baselocation+"/jz";
	}
	else if(keyword=="zlkjs")
	{
	courseUrl=baselocation+"/zlkjs";
	}
	else if(keyword=="gk")
	{
	courseUrl=baselocation+"/gk";
	}
	else if(keyword=="gwy")
	{
	courseUrl=baselocation+"/gwy";
	}
	else if(keyword=="ky")
	{
	courseUrl=baselocation+"/ky";
	}
	else if(keyword=="jz2")
	{
	courseUrl=baselocation+"/jz2";
	}
	else if(keyword=="cpa")
	{
	courseUrl=baselocation+"/cpa";
	}
	else if(keyword=="kjs")
	{
	courseUrl=baselocation+"/kjs";
	}
	else if(keyword=="sf")
	{
	courseUrl=baselocation+"/sf";
	}
	else if(keyword=="rl")
	{
	courseUrl=baselocation+"/rl";
	}
	location.href = courseUrl;
}

$().ready(function() {
	jQuery.validator.addMethod("mobile", function(value, element) {
		var pattern = /^1{1}[0-9]{10}$/;
		return this.optional(element) || pattern.test(value);
	});
	jQuery.validator.addMethod("telephone", function(value, element) {
		var pattern = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
		return this.optional(element) || pattern.test(value);
	});

	
$("#myform").validate({
rules: {
	"buyInfo.cusName" : {
		required : true,
		maxlength : 30,
		minlength : 2
	},
	"address" : {
		required : true,
		maxlength : 100,
		minlength : 3
	},
	"buyInfo.postalcode" : {
		required : true,
		digits : true,
		maxlength : 6,
		minlength : 6
	},
	"buyInfo.mobilephone" : {
		mobile : true
	},
	"buyInfo.tel" : {
		telephone : true
	},
	"buyInfo.remark" :{
		maxlength : 500
	},
	"buy_num":{
		required : true,
		digits : true,
		max : 9999,
		min : 1
	}
},
messages : {
	"buyInfo.cusName" : {
		required : "请输入收货人名称。",
		maxlength : "收货人名称不能超过30个字。",
		minlength : "收货人名称不能少于两个字。"
	},
	"address" : {
		required : "请输入地址。",
		maxlength : "您输入的地址过长。",
		minlength:"地址最少不能低于3个字"
	},
	"buyInfo.postalcode" : {
		required : "请输入邮编。",
		digits : "请输入正确的邮编。",
		maxlength : "请输入正确的邮编。",
		minlength : "请输入正确的邮编。"
	},
	"buyInfo.mobilephone" : {
		mobile : "手机号码不正确。"
	},
	"buyInfo.tel" : {
		telephone : "电话号码不正确。"
	},
	"buyInfo.remark" : {
		maxlength : "备注信息不能超250字"
	},
	"buy_num":{
		required : "请输入购买数量",
		digits : "请输入正确的购买数量。",
		max : "购买数量请输入1-9999整数",
		min : "购买数量请输入1-9999整数"
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
	$("#telyan").hide();
	label.html("<img src='http://import.highso.org.cn/images/web/public/duihao.png'/>");
	label.addClass("success");
}
});
});


function gun(){
	var cc=document.body.scrollHeight;
	$("#web_top_black").height(cc);
	$("#tan").css({top:document.documentElement.scrollTop+200});
}
window.onscroll=gun;


