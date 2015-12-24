<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/11
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
%>
<html>
<head>
    <title></title>
  <link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/demo.css">
  <script src="<%=path%>/static/jquery/jquery.min.js"></script>
  <script src="<%=path%>/static/jquery/jquery.easyui.min.js"></script>
  <script src="<%=path%>/static/js/user/user.js" ></script>
  <script src="<%=path%>/static/js/sys/jquery.cookie.js"></script>
  <script src="<%=path%>/static/js/sys/changeEasyuiTheme.js"></script>
  <script>
    var _context='<%=path%>';
  </script>
  <style>
    body {
      font-family:verdana,helvetica,arial,sans-serif;
      padding:10px;
      font-size:12px;
      margin:0;
    }

    #addUser-form table{ border-collapse:collapse;}
    #addUser-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #addUser-form .textTd{width: 15%;text-align: center;}
    #addUser-form .textInput{width: 35%;text-align: left;}
  </style>
</head>

<body>
<table id="user" style="width: 90%;height:650px;"></table>
<jsp:include page="add.jsp"></jsp:include>
</body>
</html>
