
<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 15/6/14
  Time: 下午9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
%>
<html>
<head>
    <title>岗位管理</title>
    <jsp:include page="../../head.jsp"></jsp:include>
  <script src="<%=path%>/static/js/rmo/role.js" ></script>
  <style type="text/css">
    #queryForm table{ border-collapse:collapse;}
    #queryForm table td{ width:180px; height:30px;padding:5px;border: solid 1px #b1c242;text-align: right}
    body {
      font-family:verdana,helvetica,arial,sans-serif;
      padding:10px;
      font-size:12px;
      margin:0;
    }
    #addRole-form table{ border-collapse:collapse;}
    #addRole-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #addRole-form .textTd{width: 15%;text-align: center;}
    #addRole-form .textInput{width: 35%;text-align: left;}
  </style>
</head>
<body>
<!--<div id="queryForm" class="easyui-panel" title="高级查询" style="width:99.9%;padding: 1px;">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <tr>
      <td>岗位类型</td>
      <td>
        <select class="easyui-combobox" name="state" style="width:200px;">
          <option value="AL">Alabama</option>
          <option value="AK">Alaska</option>
          <option value="AZ">Arizona</option>
        </select>
      </td>
      <td colspan="2">
        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="addMerchant()">查询</a>
      </td>
    </tr>
  </table>
</div>
<div style="height: 5px;">&nbsp;</div>-->

<div style="float: left;width: 49.95%;height:88%">
  <table id="role" style="width: 99.9%;height: 100%;"></table>
</div>

  <div id="rolePrivilege" class="easyui-layout"  style="float: left;width: 49.95%;height: 88%;">
    <div data-options="region:'west',title:'岗位权限菜单',split:true" style="width:100%;">
      <ul id="priTree"></ul>
    </div>
    <!--<div data-options="region:'center'">
      按钮权限(尚未实现功能)
    </div>-->
    <div data-options="region:'south',split:true" style="height:50px;padding: 10px;text-align: center;">
      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="saveRolePri()">保存</a>
    </div>
  </div>


<input type="hidden" id="updateSign" value="" />
<input type="hidden" id="roleId" value="" />
<jsp:include page="add.jsp"></jsp:include>
</body>
</html>
