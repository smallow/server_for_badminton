<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 15/7/5
  Time: 下午6:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
%>
<html>
<head>
    <title></title>
    <jsp:include page="../../head.jsp"></jsp:include>
  <script src="<%=path%>/static/js/sys/loginlog.js" ></script>
</head>
<body>

<table id="loginlog" style="width: 99.9%;height: 100%;"></table>
</body>
</html>
