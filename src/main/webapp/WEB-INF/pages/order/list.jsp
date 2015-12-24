<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/6
  Time: 10:52
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
  <script src="<%=path%>/static/js/order/order.js" ></script>
  <script src="<%=path%>/static/js/sys/jquery.cookie.js"></script>
  <script src="<%=path%>/static/js/sys/changeEasyuiTheme.js"></script>
  <script>
    var _context='<%=path%>';
    var merchantId='${merchantId}';
  </script>
  <style>
    #queryForm table{ border-collapse:collapse;}
    #queryForm table td{ width:100px; height:30px;padding:5px;border: dotted 0px green;text-align: right}
    body {
      font-family:verdana,helvetica,arial,sans-serif;
      padding:10px;
      font-size:12px;
      margin:0;
    }

    #orderDetail-form table{ border-collapse:collapse;}
    #orderDetail-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #orderDetail-form .textTd{width: 15%;text-align: center;}
    #orderDetail-form .textInput{width: 35%;text-align: left;}

    #editOrder-form table{ border-collapse:collapse;}
    #editOrder-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #editOrder-form .textTd{width: 15%;text-align: center;}
    #editOrder-form .textInput{width: 35%;text-align: left;}
  </style>
</head>
<body>
<div id="queryForm" class="easyui-panel"  style="width:100%;padding: 1px;">
  <table   border="0" cellpadding="0" cellspacing="0"  style="float: left;">
    <tr>
      <td >订单编号:</td>
      <td><input class="easyui-textbox" style="width:120px;height:24px"></td>
      <td>有效期:</td>
      <td><input class="easyui-textbox" style="width:120px;height:24px"></td>
      <td colspan="5"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="">查询</a></td>

    </tr>
  </table>
  <div style="float: right;padding: 5px;">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="javascript:window.location.href=_context+'/smallowadmin/merchant/contentManage?merchantId='+merchantId">返回</a>
  </div>
</div>
<div style="height: 5px;">&nbsp;</div>

<div id="orderTabs" class="easyui-tabs" style="width:100%;height:640px;">
  <div title="未处理订单" style="padding: 5px;">
    <table id="wcl_orders" style="width: 99.5%;height:99.5%;"></table>
  </div>
  <div title="已完成订单" style="padding: 5px;">
    <table id="ywc_orders"></table>
  </div>
</div>


<jsp:include page="orderDetail.jsp"></jsp:include>
<jsp:include page="orderEdit.jsp"></jsp:include>
</body>
</html>

