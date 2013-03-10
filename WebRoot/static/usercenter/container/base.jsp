<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8"/>
<%
response.setHeader("Pragrma","no-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires",0);
%>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link href="<%=importURL%>/styles/usercenter/uc_common.css?v=<%=version%>" rel="stylesheet"/>
<link href="<%=importURL%>/styles/usercenter/popup.css?v=<%=version%>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=importURL%>/js/web/public/web_jquery-jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/web/public/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=importURL%>/js/usercenter/uc_com.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=importURL%>/js/web/cn/web_util_cn.js?v=<%=version%>"></script>
	
<tiles:insertAttribute name="resource" />
</head>
<body>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="left"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="right"/>
<tiles:insertAttribute name="footer"/>
<script src="http://s25.cnzz.com/stat.php?id=4371840&web_id=4371840&show=pic1" language="JavaScript"></script>
</body>
</html>