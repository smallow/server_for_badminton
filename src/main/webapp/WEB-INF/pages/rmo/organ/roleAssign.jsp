<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/6/16
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="roleAssign" class="easyui-dialog" title="操作员岗位分配"
	data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#roleAssign-buttons'"
	style="width: 1024px; height: 500px; padding: 5px;">


	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'east'" style="width: 400px; padding: 10px">
			<div id="organInfo" class="easyui-panel" title="权限分配记录"
				style="width: 99.5%; padding: 1px;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="textTd">操作员姓名:</td>
						<td class="textInput" id="tb_org_name"><%=request.getSession().getAttribute("employeeName")%>
						</td>
					</tr>
					
					<tr>
						<td class="textTd">分配备注:</td>
						<td class="textInput" id="tb_parent_org_name"><textarea
								id="form_assign_reason" class="easyui-validatebox"
								style="width: 100%; height: 100px;"></textarea></td>
					</tr>
				</table>
			</div>
		</div>
		<div data-options="region:'center'"
			style="padding: 10px; width: 400px;">
			<fieldset style="border: 1px solid #d2dfe6; width: 95%; height: 95%;">
				<legend>岗位分配</legend>
				<div class="selfcss">
					<ul id="roleList">

					</ul>
				</div>
				<div></div>
			</fieldset>
		</div>
	</div>




</div>
<div id="roleAssign-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
		onclick="saveRoleAssign()">保存</a> <a href="#"
		class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#roleAssign').dialog('close')">取消</a>
</div>
