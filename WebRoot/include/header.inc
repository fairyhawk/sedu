<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath();%>
<%String importURL = "http://172.16.123.150:8080/sedu"; %>
<%String version = "201207191342"; %>
<%
	//Back
	//String keImageUploadJsonBackAction = "http://tp.highso.cn:8080/upload!img.action";// Online Address
	String keImageUploadJsonBackAction = contextPath + "/dis/uploadBack!img.action";// Local Address
	
	//Front
	//String keImageUploadJsonAction = "http://tp.highso.cn:8080/upload!img.action";// Online Address
	String keImageUploadJsonAction = contextPath + "/dis/upload!img.action";// Local Address 
%>