<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/6/10
  Time: 10:21
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
    <script src="<%=path%>/static/js/rmo/orgManage.js"></script>
    <script>
        var _context='<%=path%>';
        var _operateName='<%=session.getAttribute("employeeName")%>';
    </script>
    <style>
        body {
            font-family:verdana,helvetica,arial,sans-serif;
            padding:10px;
            font-size:12px;
            margin:0;
        }
        #organInfo table{ border-collapse:collapse;}
        #organInfo table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
        #organInfo .textTd{width: 15%;text-align: center;}
        #organInfo .textInput{width: 35%;text-align: left;}


        #addOrgan-form table{ border-collapse:collapse;}
        #addOrgan-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
        #addOrgan-form .textTd{width: 15%;text-align: center;}
        #addOrgan-form .textInput{width: 35%;text-align: left;}

        #addEmployee-form table{ border-collapse:collapse;}
        #addEmployee-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
        #addEmployee-form .textTd{width: 15%;text-align: center;}
        #addEmployee-form .textInput{width: 35%;text-align: left;}

        #addUser-form table{ border-collapse:collapse;}
        #addUser-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
        #addUser-form .textTd{width: 15%;text-align: center;}
        #addUser-form .textInput{width: 35%;text-align: left;}
        .selfcss ul {
            list-style: none;
        }

        .selfcss li {
            width:150px;float:left;margin-right:15px;line-height:25px;
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
            <tr>
                <td colspan="4">
                    <a href="#" class="easyui-linkbutton" style="width: 80px;font-weight: bold;"data-options="" onclick="addOrgan();">创建下级</a>
                    <a href="#" class="easyui-linkbutton" style="width: 50px;font-weight: bold;" data-options="" onclick="editOrgan();">修&nbsp;&nbsp;改</a>
                    <a href="#" class="easyui-linkbutton" style="width: 50px;font-weight: bold;"data-options="" onclick="delOrgan();">删&nbsp;&nbsp;除</a>
                </td>
            </tr>

        </table>
    </div>
    <div style="height: 5px;">&nbsp;</div>
    <table id="employee" style="width: 99.5%;height:400px;"></table>

</div>
<input type="hidden" id="selectOrganId" value="" />
<input type="hidden" id="selectOrganCode" value="" />
<input type="hidden" id="updateSign" value="" />
<input type="hidden" id="employeeUpdateSign" value="" />
<input type="hidden" id="userUpdateSign" value="" />
<input type="hidden" id="employeeId" value="" />
<input type="hidden" id="userId" value="" />
<input type="hidden" id="tb_org_code" value="">

<jsp:include page="addOrgan.jsp"></jsp:include>
<jsp:include page="addEmployee.jsp"></jsp:include>
<jsp:include page="addUser.jsp"></jsp:include>
<jsp:include page="roleAssign.jsp"></jsp:include>
<jsp:include page="roleAssignLog.jsp"></jsp:include>
</body>
</html>
