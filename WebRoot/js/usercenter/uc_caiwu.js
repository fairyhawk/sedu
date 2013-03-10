	
	$().ready(function(){
		initFormValid();
		initArea(1, 0);
		initArea($("#sel_province").find("option:eq(0)").val(), 1);
	});

	function deleteAddress(id) {
		if(window.confirm("您确定要删除这个收货地址吗？")) {
			window.location.href = baselocation + "/cus/addrlimit!deleteAddress.action?address.id=" + id;
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
					$(html).appendTo("#sel_province");
				} else if(index == 1) {
					$("#sel_city").html("");
					$("#sel_town").html("");
					$("<option value='0'>--------</option>" + html).appendTo("#sel_city");
					$("<option value='0'>--------</option>").appendTo("#sel_town");
				} else {
					try{
						$("#sel_town")[0].innerHTML="";
						$("#sel_town").html("<option value='0'>--------</option>" + html);
					}catch(e){};
				}
			},
			error : function(error) {
				alert('error'+error.responseText);
			}
		});
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
		$("#addressForm").validate({
	        rules: {
	        	"address.receiver" : {
	        		required : true,
	        		maxlength : 30,
	        		minlength : 2
	        	},
	        	"address.address" : {
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
	            	error.appendTo(element.removeClass("error").parent().find("font"));
	            else 
	            	error.appendTo(element.removeClass("error").parent().find("font"));
	        }, 
	        success: function(label,element) {
	        	label.html("<img src='" + importURL + "/images/web/public/duihao.png'/>");
	        }
		});
	}
	
	function saveAddress() {
		if($("#addressForm").valid()) {
			$("#area_message").html("");
			if($("#sel_province").val() < 1) {
				$("#area_message").html("请选择省份。");
			} else if($("#sel_city").val() < 1) {
				$("#area_message").html("请选择城市。");
			} else if($("#sel_town").val() < 1) {
				$("#area_message").html("请选择地区。");
			} else if($("#addressMobile").val().trim() == "" && $("#addressTel").val().trim() == "") {
				$("#error_tip").css("display", "block");
				$("#error_tip_content").html("请输入手机号码或固定电话。");
			} else {
				if($("#addressMobile").val() != "") {
					$("#address_mobile").val($("#addressMobile").val());
				} else {
					$("#address_mobile").val($("#addressTel").val());
				}
				document.addressForm.submit();
			}
		}
	}
	
	function closeErrorTip() {
		$("#error_tip").css("display", "none");
	}
	
	function changeFirst(ckbox) {
		if(ckbox.checked) {
			$("#address_isFirst").val(1);
		} else {
			$("#address_isFirst").val(0);
		}
	}
	
	function initUpdate(id, receiver, address, postCode, mobile, createTime, provinceId, cityId, townId, isFirst, sendTime){
	    $("#addressId").val(id);
	    $("#receiverName").val(receiver);
	    $("#detailAddress").val(address);
	    $("#postCode").val(postCode);
	    $("#address_createTime").val(createTime);
	    $("#sel_province").val(provinceId);
	    initArea(provinceId, 1);
	    $("#sel_city").val(cityId);
	    initArea(cityId, 2);
	    try{
	    $("#sel_town").val(townId);
	    }catch(e){}
	    $("#sendTime").val(sendTime);
	    $("#address_isFirst").val(isFirst);
	    
	 	if(mobile.length != 11 || mobile.indexOf("-")>-1) {;
			$("#addressTel").val(mobile);
		} else {
			$("#addressMobile").val(mobile);
		}
		if(isFirst == 1) {
			$("#isFirstCheckbox").attr("checked", true);
		} else {
			$("#isFirstCheckbox").attr("checked", false);
		}
	}
    
    /*财务中心*/
 	function SwitchCaiwu(h){
 		$("#switchCD"+h).show();
	 	$("#switchCT"+h).attr("class","current");
	 	for(var i=0;i<2;i++){
	 		if(h!=i)
	 			{
	 		$("#switchCD"+i).hide();
			$("#switchCT"+i).attr("class","");
	 			}
	 	}
	 	
	 }
 	