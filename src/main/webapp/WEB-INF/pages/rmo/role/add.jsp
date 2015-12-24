<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="addRole" class="easyui-dialog" title="添加岗位" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#addRole-buttons'" style="width:400px;height:350px;padding:0px;">
    <form id="addRole-form" method="post">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" >
            <tr>
                <td class="textTd">岗位名称:</td>
                <td class="textInput" ><input class="easyui-textbox" id="form_role_name"  data-options="required:true" style="width:180px;height:24px"></td>
            </tr>
            <tr>
                <td class="textTd">岗位代码:</td>
                <td class="textInput">
                    <input class="easyui-textbox" id="form_role_code"  style="width:180px;height:24px">
                </td>
            </tr>


            <tr>
                <td class="textTd">岗位描述:</td>
                <td class="textInput"  style="height: 80px;">
                    <textarea  id="form_role_desc"  style="width:100%;height: 100%;"></textarea>
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="addRole-buttons">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRole()">保存</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addRole').dialog('close')">取消</a>
</div>