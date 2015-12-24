<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/8
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="editOrder" class="easyui-dialog" title="修改订单" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#editOrder-buttons'" style="width:600px;height:400px;padding:0px;">
  <form id="editOrder-form" method="post" >
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="tb_placeOrder">
      <tr>
        <td class="textTd">标题:</td>
        <td class="textInput" colspan="3">
          <span id="edit_order_title"></span>
          <input type="hidden" id="edit_order_id" value="">
        </td>
      </tr>

      <tr>
        <td class="textTd">数量</td>
        <td class="textInput">
          <input class="easyui-numberspinner"  id="edit_order_contentNum" data-options="value:1,increment:1" style="width:120px;" />
        </td>
        <td class="textTd">订单编号</td>
        <td class="textInput"><span id="edit_order_num"></span></td>
      </tr>
      <tr>
        <td class="textTd">手机号</td>
        <td class="textInput"><input class="easyui-numberbox"   id="edit_order_telephone" name="edit_order_telephone" data-options="required:true" style="width:180px;height:24px"></td>
        <td class="textTd">优惠单价</td>
        <td class="textInput"><span id="edit_order_concessionalPrice"></span>&nbsp;元</td>
      </tr>
      <tr>
        <td class="textTd">小计</td>
        <td class="textInput"><span id="edit_order_totalMoney"></span>&nbsp;元</td>
        <td class="textTd">订单合计</td>
        <td class="textInput"><span id="edit_order_realPaymentMoney"></span>&nbsp;元</td>
      </tr>
      <tr>
        <td class="textTd">选择支付方式</td>
        <td class="textInput" colspan="3">
          <input type="radio" name="edit_paymentType" value="01" checked="checked" />支付宝
          <input type="radio" name="edit_paymentType" value="02" />微信
          <input type="radio" name="edit_paymentType" value="04" />货到付款
        </td>
      </tr>
    </table>
  </form>
</div>
<div id="editOrder-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateOrder()">完成订单</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editOrder').dialog('close')">取消</a>
</div>
