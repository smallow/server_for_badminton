<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/4/28
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<styl type="text/css">


</styl>
<div id="addMerchant" class="easyui-dialog" title="添加商户" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#addMerchant-buttons'" style="width:800px;height:600px;padding:0px;">
  <form id="addMerchant-form" method="post">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tb_add_merchant">
      <tr>
        <td class="textTd">商户名称:</td>
        <td class="textInput" colspan="4"><input class="easyui-textbox" id="merchant_name" name="merchant_name" data-options="required:true" style="width:500px;height:24px"></td>

      </tr>
      <tr>
        <td class="textTd">行业分类:</td>
        <td class="textInput">
          <select class="easyui-combobox" name="merchant_bigtype" id="merchant_bigtype" data-options="required:true" style="width:200px;">
            <option value="40001">美食</option>
            <option value="20001">孕婴生活</option>
            <option value="30001">美容美发</option>
            <option value="10001">超市</option>
            </select>
        </td>
        <td class="textTd">行业细类:</td>
        <td class="textInput">
          <select class="easyui-combobox" name="merchant_midtype" style="width:200px;">
          </select>
        </td>
      </tr>
      <tr>
        <td class="textTd">联系人名称:</td>
        <td class="textInput"><input class="easyui-textbox" id="contact_person_name" name="contact_person_name"style="width:180px;height:24px"></td>
        <td class="textTd">联系电话:</td>
        <td class="textInput"><input class="easyui-textbox" id="telphone" name="telphone" style="width:180px;height:24px"></td>
      </tr>
      <tr>
        <td class="textTd">企业类型:</td>
        <td class="textInput">
          <select class="easyui-combobox" name="merchant_type" id="merchant_type" data-options="required:true" style="width:200px;">
            <option value="001">企业公司</option>
            <option value="002">个体工商户</option>
            <option value="003">农村信用合作社</option>
          </select>
         </td>
        <td class="textTd">登记注册证号:</td>
        <td class="textInput"><input class="easyui-textbox" id="merchant_regno" name="merchant_regno" style="width:180px;height:24px"></td>
      </tr>
      <tr>
        <td class="textTd">首页推荐:</td>
        <td class="textInput" ><input type="checkbox" id="sfTop10" name="sfTop10" /></td>
        <td class="textTd">推荐次序:</td>
        <td class="textInput" ><input class="easyui-numberspinner" id="top10Order" value="1" data-options="increment:1" style="width:50px;" /></td>
      </tr>
      <tr>
        <td class="textTd">商户地址:</td>
        <td class="textInput" colspan="4"><input class="easyui-textbox" id="merchant_address" name="merchant_address" data-options="" style="width:500px;height:24px"></td>
      </tr>
    </table>
  </form>
</div>
<div id="addMerchant-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMerchant()">保存</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addMerchant').dialog('close')">取消</a>
</div>