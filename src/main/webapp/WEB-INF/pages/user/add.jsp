<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/11
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="addUser" class="easyui-dialog" title="添加用户" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#addUser-buttons'" style="width:400px;height:350px;padding:0px;">
  <form id="addUser-form" method="post">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" >
      <tr>
        <td class="textTd">用户手机号:</td>
        <td class="textInput" ><input class="easyui-textbox" id="telephone" name="telephone" data-options="required:true" style="width:180px;height:24px"></td>
      </tr>
      <tr>
        <td class="textTd">邮箱:</td>
        <td class="textInput">
          <input class="easyui-textbox" id="email"  style="width:180px;height:24px">
        </td>
      </tr>


      <tr>
        <td class="textTd">密码:</td>
        <td class="textInput" ><input class="easyui-textbox" id="pwd"  data-options="type:'password'" style="width:180px;height:24px"></td>
      </tr>
      <tr>
        <td class="textTd">确认密码:</td>
        <td class="textInput" ><input class="easyui-textbox" id="confirm_pwd"  data-options="type:'password'" style="width:180px;height:24px"></td>
      </tr>
    </table>
  </form>
</div>
<div id="addUser-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addUser').dialog('close')">取消</a>
</div>
