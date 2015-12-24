<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/4/29
  Time: 16:40
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
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/uploadify/uploadify.css">
  <script src="<%=path%>/static/jquery/jquery.min.js"></script>
  <script src="<%=path%>/static/jquery/jquery.easyui.min.js"></script>
  <script src="<%=path%>/static/js/content/manage.js" ></script>
  <script src="<%=path%>/static/ckeditor/ckeditor.js"></script>
  <script src="<%=path%>/static/ckfinder/ckfinder.js"></script>
  <script src="<%=path%>/static/uploadify/jquery.uploadify.min.js"></script>
  <script src="<%=path%>/static/js/sys/jquery.cookie.js"></script>
  <script src="<%=path%>/static/js/sys/changeEasyuiTheme.js"></script>
  <script>
    var _context='<%=path%>';
    var merchantId='${merchantId}';
  </script>
  <style>
    #queryForm table{ border-collapse:collapse;}
    #queryForm table td{ width:180px; height:30px;padding:5px;border: dotted 0px green;text-align: right}

    #addContent-form table{ border-collapse:collapse;}
    #addContent-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #addContent-form .textTd{width: 10%;text-align: center;}
    #addContent-form .textInput{width: 10%;text-align: left;}


    #placeOrder-form table{ border-collapse:collapse;}
    #placeOrder-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #placeOrder-form .textTd{width: 15%;text-align: center;}
    #placeOrder-form .textInput{width: 35%;text-align: left;}

    #payOrder-form table{ border-collapse:collapse;}
    #payOrder-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #payOrder-form .textTd{width: 15%;text-align: center;}
    #payOrder-form .textInput{width: 35%;text-align: left;}

    body {
      font-family:verdana,helvetica,arial,sans-serif;
      padding:10px;
      font-size:12px;
      margin:0;
    }
  </style>

</head>
<body>
<div id="queryForm" class="easyui-panel" title="高级查询" style="width:90%;padding: 1px;">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td >标题:</td>
      <td><input class="easyui-textbox" style="width:180px;height:24px"></td>
      <td>有效期:</td>
      <td><input class="easyui-textbox" style="width:180px;height:24px"></td>
    </tr>
    <tr><td colspan="8"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="">查询</a></td></tr>
  </table>
</div>
<div style="height: 5px;">&nbsp;</div>
<table id="content" style="width: 90%;height:495px;"></table>
<jsp:include page="add.jsp"></jsp:include>
<jsp:include page="placeOrder.jsp"></jsp:include>
<jsp:include page="pay.jsp"></jsp:include>

</body>
</html>
