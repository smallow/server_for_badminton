<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/4/28
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="addUser" class="easyui-dialog" title="操作员维护" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#addUser-buttons'" style="width:400px;height:300px;padding:0px;">


    <form id="addUser-form" method="post">

        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb_add_organ">
            <tr>
                <td class="textTd">登录代码:</td>
                <td class="textInput" ><input class="easyui-textbox" id="form_user_code"   data-options="required:true" style="width:180px;height:24px"></td>
            </tr>
            <tr>
                <td class="textTd">密码:</td>
                <td class="textInput" ><input class="easyui-textbox" id="form_user_pwd"  data-options="type:'password'" style="width:180px;height:24px"></td>
            </tr>
            <tr>
                <td class="textTd">确认密码:</td>
                <td class="textInput" ><input class="easyui-textbox" id="form_confirm_pwd"  data-options="type:'password'" style="width:180px;height:24px"></td>
            </tr>
            <tr>
                <td class="textTd">姓名:</td>
                <td class="textInput" id="form_user_name"></td>
            </tr>

        </table>
    </form>
</div>
<div id="addUser-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addUser').dialog('close')">取消</a>
</div>