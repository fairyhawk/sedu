<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<link href="<%=importURL%>/styles/usercenter/uc_test.css?v=<%=version%>" rel="stylesheet"/>
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_highcharts.src.js"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_user_common.js?v=<%=version%>"></script>
<script language="javascript" src="<%=importURL%>/js/usercenter/uc_test.js?v=<%=version%>"></script>
<script type="text/javascript" language="javascript">
	var baselocation = '<%=contextPath%>';
	var importURL = '<%=importURL%>';
	var ucLeftIndex = 5;
	var testedStudyType = false;
	function ceshi(valueid)
		{
			if(valueid==1)
			{
				$("#ceshiwordname").html("选择试卷：  ");
				shijuan(document.getElementById('kid').value,1);
			}
			if(valueid==2)
			{
				$("#ceshiwordname").html("选择试卷：  ");
				shijuan(document.getElementById('kid').value,2);
			}
			if(valueid==3)
			{
				$("#ceshiwordname").html("知识单元：  ");
				zhishi(document.getElementById('kid').value,3);
			}
			if(valueid==4)
			{
				$("#ceshiwordname").html("选择专题：  ");
				zhishi(document.getElementById('kid').value,4);
			}
			if(valueid==-1)
			{
				$("#ceshiwordname").html("关键字：  ");
				$("#eptypekeyword").html("<option selected='selected' value='-1'>请选测关键字</option>");
			}
		}
		function bian(slt)
		{	
			var opt = slt.options[slt.selectedIndex];
			var optvalue= slt.options[slt.selectedIndex].value;
			var bianliang=opt.getAttribute("subjectId");
			document.getElementById('kid').value=optvalue;
			if(bianliang==null)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option>");
			}
			if(bianliang==3)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='2'>仿真自测</option><option value='3'>单元练习</option>");
			}
			if(bianliang==1)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='2'>仿真自测</option><option value='1'>真题测试</option><option value='3'>单元练习</option>");
			}
			if(bianliang==2)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='2'>仿真自测</option>");
			}
			if(bianliang==5)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='1'>真题测试</option><option value='2'>仿真自测</option><option value='3'>单元练习</option>");
			}
			if(bianliang==6)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='2'>仿真自测</option>");
			}
			if(bianliang==7)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='1'>真题测试</option><option value='4'>专题练习</option><option value='3'>单元练习</option>");
			}
			if(bianliang==8)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='2'>仿真自测</option><option value='1'>真题测试</option>");
			}
			if(bianliang==9){
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='1'>真题测试</option><option value='3'>单元练习</option>");
			}
			if(bianliang==11)
			{
				$("#eptype").html("<option selected='selected' value='0'>请选择测试类型</option><option value='3'>单元练习</option>");
			}
		}
		function zhishi(value,type)
		{
			var cusid=value;
			var eptype=type;
			$.ajax ({
				url: "<%=contextPath%>/cou/kpointweb!getKpointlistjilian.action",
				data :{"cusid": cusid,
					   "eptype":eptype
					  },
					type :"post",
					dataType:"json",
					async : false,
					success : function(result){
						var array = result.entity;
						if(array.length>0)
						{
							document.getElementById('eptypekeyword').options.length = 0;  //清空原有的option 
							//$("#kecheng").html("<option value=-1>请选择知识点</option>");
							var str="<option value=0>请选择知识点</option>";  
							for(var i=0;i<array.length;i++){  
							str+="<option value='"+array[i].pointId+"'>"+array[i].name+"</option>"  
							}  
						$("#eptypekeyword").html(str);  
						}
						else
						{
							$("#eptypekeyword").html("<option value=0>请选择</option>");
						}
					},
					error : function(error) {
						alert(error.responseText);
					}
				});
			}
			
		function shijuan(value,type)
		{
			var cusid=value;
			var eptype=type;
			$.ajax ({
					url: "<%=contextPath%>/exam/qstManager!getExamlist.action",
					data :{"cusid": cusid,
							"eptype":eptype
					},
					type :"post",
					dataType:"json",
					async : false,
					success : function(result){
						var array = result.entity;
						if(array.length>0)
						{
							document.getElementById('eptypekeyword').options.length = 0;  //清空原有的option 
							//$("#kecheng").html("<option value=-1>请选择知识点</option>");
							var str="<option value=0>请选择试卷</option>";  
							for(var i=0;i<array.length;i++){  
								str+="<option value='"+array[i].epId+"'>"+array[i].epName+"</option>"  
							}  
							$("#eptypekeyword").html(str);  
						}
						else
						{
							$("#eptypekeyword").html("<option value=0>请选择</option>");
						}
					},
					error : function(error) {
						alert(error.responseText);
					}
				});
			}
		function dosubmit1()
		{
			var eptype=document.getElementById('eptype1').value;
			var ee=document.getElementById('ee1').value;
			if(ee==0){
				alert("请选择专业");
				return false;
			}
			if(eptype==0)
			{
				alert("请选择测试类型");
				return false;
			}
			document.forms['examfrom1'].submit();
		}
			
		function dosubmit()
		{
			var subjectId=document.getElementById('subjectId').value;
			var eptype=document.getElementById('eptype').value;
			var eptypekeyword=document.getElementById('eptypekeyword').value;
			if(subjectId==0)
			{
				alert("请选择课程");
				return false;
			}
				
			if(eptype==0)
			{
				alert("请选择测试类型");
				return false;
			}
				
			if(eptypekeyword==0)
			{
				alert("请选测试类型关键字或试卷");
				return false;
			}
				document.forms['examfrom'].submit();
		}
			
		$().ready(function() {
			<s:if test="studyTypePram==null">
				initTest();
			</s:if>
			<s:else>
				testedStudyType = true;
				showResult({
					studyType : <s:property value="studyTypePram.studyType"/>,
					results : [<s:property value="studyTypePram.results[0]"/>, 
								<s:property value="studyTypePram.results[1]"/> ,
								<s:property value="studyTypePram.results[2]"/>, 
								<s:property value="studyTypePram.results[3]"/>]
					});
			</s:else>
		});
</script>