<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 15/7/27
  Time: 下午9:47
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
    <script src="<%=path%>/static/js/rmo/organQueryManage.js"></script>
    <script>
        var _context = '<%=path%>';
    </script>
    <style>
        body {
            font-family: verdana, helvetica, arial, sans-serif;
            padding: 10px;
            font-size: 12px;
            margin: 0;
        }

        #organInfo table {
            border-collapse: collapse;
        }

        #organInfo table td {
            height: 30px;
            padding: 5px;
            border: solid 1px lightgray;
            text-align: center
        }

        #organInfo .textTd {
            width: 15%;
            text-align: center;
        }

        #organInfo .textInput {
            width: 35%;
            text-align: left;
        }

        .selfcss ul {
            list-style: none;
        }

        .selfcss li {
            width: 150px;
            float: left;
            margin-right: 15px;
            line-height: 25px;
        }

    </style>
</head>
<body class="easyui-layout">

<div data-options="region:'west',title:'机构管理',split:true" style="width:200px;">
    <ul id="orgTree"></ul>
</div>
<div data-options="region:'center'"  >
    <div id="organInfo" class="easyui-panel" title="机构基本信息" style="width:99.5%;padding: 1px;">

        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="textTd">机构名称:</td>
                <td class="textInput" id="tb_org_name"></td>
                <td class="textTd">机构简称:</td>
                <td class="textInput" id="tb_org_short_name"></td>
            </tr>
            <tr>
                <td class="textTd">上级机构:</td>
                <td class="textInput" id="tb_parent_org_name"></td>
                <td class="textTd">创建时间:</td>
                <td class="textInput" id="tb_org_create_time"></td>
            </tr>
            <tr>
                <td class="textTd">机构介绍:</td>
                <td class="textInput" style="height: 50px;" colspan="3" id="tb_org_desc"></td>
            </tr>
        </table>
    </div>
    <div style="height: 5px;">&nbsp;</div>
    <table id="employee" style="width: 99.5%;height:60%;"></table>

</div>
<input type="hidden" id="selectOrganId" value="" />
<input type="hidden" id="selectOrganCode" value="" />
<input type="hidden" id="updateSign" value="" />
<input type="hidden" id="employeeUpdateSign" value="" />
<input type="hidden" id="userUpdateSign" value="" />
<input type="hidden" id="employeeId" value="" />
<input type="hidden" id="userId" value="" />
<input type="hidden" id="tb_org_code" value="">


</body>
</html>
