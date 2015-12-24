<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/4/23
  Time: 13:53
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
  <script src="<%=path%>/static/js/merchant/manage.js" ></script>
    <script src="<%=path%>/static/js/sys/jquery.cookie.js"></script>
    <script src="<%=path%>/static/js/sys/changeEasyuiTheme.js"></script>
  <script>
    var _context='<%=path%>';
  </script>
  <style type="text/css">
   #queryForm table{ border-collapse:collapse;}
   #queryForm table td{ width:180px; height:30px;padding:5px;border: dotted 0px green;text-align: right}

   #addMerchant-form table{ border-collapse:collapse;}
   #addMerchant-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
   #addMerchant-form .textTd{width: 15%;text-align: center;}
   #addMerchant-form .textInput{width: 35%;text-align: left;}

   #editMerchant-form table{ border-collapse:collapse;}
   #editMerchant-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
   #editMerchant-form .textTd{width: 15%;text-align: center;}
   #editMerchant-form .textInput{width: 35%;text-align: left;}
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
        <td >商户名称:</td>
        <td><input class="easyui-textbox" style="width:180px;height:24px"></td>
        <td>注册证号:</td>
        <td><input class="easyui-textbox" style="width:180px;height:24px"></td>
        <td>大类:</td>
        <td>
          <select class="easyui-combobox" name="state" style="width:180px;">
          </select>
        </td>
        <td>小类:</td>
        <td>
          <select class="easyui-combobox" name="state" style="width:180px;">
          </select>
        </td>
      </tr>
      <tr><td colspan="8"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="addMerchant()">查询</a></td></tr>
    </table>
  </div>
<div style="height: 5px;">&nbsp;</div>
  <table id="merchant" style="width: 90%;height:495px;"></table>


  <jsp:include page="add.jsp"></jsp:include>
  <jsp:include page="edit.jsp"></jsp:include>


</body>
</html>
