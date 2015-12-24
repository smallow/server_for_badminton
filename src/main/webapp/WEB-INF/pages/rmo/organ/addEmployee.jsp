<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/4/28
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="addEmployee" class="easyui-dialog" title="添加人员" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#addEmployee-buttons'" style="width:550px;height:300px;padding:0px;">


    <form id="addEmployee-form" method="post">

        <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb_add_employee">
            <tr>
                <td class="textTd">姓名:</td>
                <td class="textInput" ><input class="easyui-textbox" id="form_employee_name"   data-options="required:true" style="width:180px;height:24px"></td>
                <td class="textTd">年龄:</td>
                <td class="textInput">
                    <input class="easyui-textbox" id="form_employee_age"   data-options="" style="width:150px;height:24px">
                </td>
            </tr>
            <tr>
                <td class="textTd">性别:</td>
                <td class="textInput">
                    <input type="radio" value="男" name="form_employee_sex"/>男
                    <input type="radio" value="女" name="form_employee_sex"/>女
                </td>
                <td class="textTd">职务:</td>
                <td class="textInput">
                </td>
            </tr>
            <tr>
                <td class="textTd">电话:</td>
                <td class="textInput"  >
                    <input class="easyui-textbox" id="form_employee_telephone"     data-options="" style="width:180px;height:24px">
                </td>
                <td class="textTd">手机号:</td>
                <td class="textInput"  >
                    <input class="easyui-textbox" id="form_employee_mobile"    data-options="required:true" style="width:180px;height:24px">
                </td>
            </tr>
            <tr>
                <td class="textTd">所属机构:</td>
                <td class="textInput" colspan="3" id="form_employee_orgName" >

                </td>
            </tr>
        </table>
    </form>
</div>
<div id="addEmployee-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEmployee()">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addEmployee').dialog('close')">取消</a>
</div>