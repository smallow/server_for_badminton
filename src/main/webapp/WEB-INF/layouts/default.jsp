<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery/themes/color.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery/demo.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/sys/main.css">
	<script src="${ctx}/static/jquery/jquery.min.js"></script>
	<script src="${ctx}/static/jquery/jquery.easyui.min.js"></script>
<sitemesh:head/>
</head>

<body>
<sitemesh:body/>
</body>
</html>