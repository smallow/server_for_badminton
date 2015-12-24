<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/4/28
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="addOrgan" class="easyui-dialog" title="创建机构" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#addOrgan-buttons'" style="width:500px;height:250px;padding:0px;">


    <form id="addOrgan-form" method="post">

        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb_add_organ">
            <tr>
                <td class="textTd">机构名称:</td>
                <td class="textInput" colspan="3"><input class="easyui-textbox" id="form_org_name"   data-options="required:true" style="width:200px;height:24px"></td>
            </tr>
            <tr>
                <td class="textTd">机构代码:</td>
                <td class="textInput">
                    <input class="easyui-textbox" id="form_org_code"   data-options="required:true" style="width:150px;height:24px">
                </td>
                <td class="textTd">机构简称:</td>
                <td class="textInput">
                    <input class="easyui-textbox" id="form_org_short_name"    style="width:150px;height:24px">
                </td>
            </tr>
            <tr>
                <td class="textTd">机构介绍:</td>
                <td class="textInput" colspan="3" style="height: 80px;">
                    <textarea id="form_org_desc" class="easyui-validatebox"   style="width:100%;height: 100%;"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="addOrgan-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveOrgan()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addOrgan').dialog('close')">取消</a>
</div>