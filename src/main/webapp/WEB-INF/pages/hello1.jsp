<%@ page import="com.hhkj.rmo.model.Privilege" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 15-4-21
  Time: 下午3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String priJson= (String) request.getAttribute("priJson");
    String loginName= (String) request.getAttribute("loginName");
    String loginTime= (String) request.getAttribute("loginTime");

%>
<html>
<head>
    <title></title>
</head>
<body class="easyui-layout">
<div id="content">
    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/ui-cupertino/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/color.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/sys/main.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/sys/hrms.css">
    <script src="<%=path%>/static/jquery/jquery.min.js"></script>
    <script src="<%=path%>/static/jquery/jquery.easyui.min.js"></script>
    <script src="<%=path%>/static/js/sys/mainInit.js" ></script>
    <script src="<%=path%>/static/js/sys/menutree.js" ></script>
    <script src="<%=path%>/static/js/sys/jquery.cookie.js"></script>
    <script>
        var _context='<%=path%>';
        var _priJson='<%=priJson%>';
        var loginName='<%=loginName%>';
        var loginName='<%=loginTime%>';
    </script>
</div>



<div data-options="region:'north',title:'郑州市二七区检察院人事管理系统'" style="height: 80px">

    <div class="sysTop">
        <div class="sysRight">
            <span>[管理用户]:<%=loginName%>   登陆时间:<%=loginTime%> </span>
            <a href="javascript:void(0);" class="exitSys" onclick="zhuxiao()">退出系统</a>
        </div>
        <div class="sysLogo"></div>
    </div>

    <%-- <select id="cb-theme" style="width:120px"></select>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addMerchant()">添加用户</a>
<span id="result"></span>
<div style="float: right;">
 <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="zhuxiao()">注销登录</a>
</div>--%>
</div>
<%-- <div data-options="region:'south',split:true" style="height:5px;padding: 2px;">
 </div>--%>
<%--<div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>--%>
<div data-options="region:'west',title:'我的权限',split:true" style="width:200px;">
    <div id="nav" class="easyui-accordion" data-options="fit:true,border:false">
    </div>
</div>
<div data-options="region:'center'" style="background:#eee;">
    <div id="tabs" style="width:100%;">
        <!-- 欢迎页开始 -->
        <div style="background:#fff;" title="欢迎页">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="homepageTitle"></td>
                    <td class="homepageTitleBg">&nbsp;</td>
                </tr>
            </table>
            <div class="titleBorder"></div>

            <div class="iconArea">
                <div class="icon1"><a href="#" onclick="addTabs('机构及账号信息管理','<%=path %>/smallowadmin/organ/manage','icon-group');">账号信息管理</a></div>
                <div class="icon2"><a href="#" onclick="addTabs('班子成员花名册','<%=path %>/smallowadmin/teamMembers/init');">班子成员花名册</a></div>
                <div class="icon3"><a href="#" onclick="addTabs('在业务科室时间查询','<%=path %>/smallowadmin/emp/query_list?url=eblist','icon-group');">在科室时间查询</div>
                <div class="icon4"><a href="#" onclick="addTabs('人员添加','<%=path %>/smallowadmin/emp/manager/singleImport','icon-group');" >人员添加</a></div>
                <div class="icon5"><a href="#" onclick="addTabs('行政职务一览表','<%=path %>/smallowadmin/administration/init','icon-group');">行政职务一览表</a></div>
                <div class="icon6"><a href="#" onclick="addTabs('各级检察官信息查询','<%=path %>/smallowadmin/emp/query_list?url=prolist','icon-group');">检察官信息查询</a></div>
            </div>
            <div class="homepageBg"></div>
        </div>
        <!-- 欢迎页结束 -->

    </div>
</div>

<div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabclose" name="1">关闭</div>
    <div id="mm-tabcloseall" name="2">全部关闭</div>
    <div id="mm-tabcloseother" name="3">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright" name="4">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft" name="5">当前页左侧全部关闭</div>

</div>



</body>

﻿
</html>
