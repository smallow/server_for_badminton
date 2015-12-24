<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/7
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="payOrder" class="easyui-dialog" title="下单" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#payOrder-buttons'" style="width:600px;height:400px;padding:0px;">
  <form id="payOrder-form" method="post" >
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="tb_placeOrder">
      <tr>
        <td class="textTd">标题:</td>
        <td class="textInput" colspan="3">
          <span id="pay_order_title"></span>
          <input type="hidden" id="pay_order_contentId" value="">
        </td>
      </tr>

      <tr>
        <td class="textTd">数量</td>
        <td class="textInput">
          <span id="pay_order_contentNum"></span>
        </td>
        <td class="textTd">订单编号</td>
        <td class="textInput"><span id="pay_order_num"></span></td>
      </tr>
      <tr>
        <td class="textTd">手机号</td>
        <td class="textInput"><span id="pay_order_telephone"></span></td>
        <td class="textTd">订单合计</td>
        <td class="textInput"><span id="pay_order_realPaymentMoney"></span>&nbsp;元</td>
      </tr>
      <tr>
        <td class="textTd">选择支付方式</td>
        <td class="textInput">
          <input type="radio" name="pay_paymentType" value="01" checked="checked" />支付宝
          <input type="radio" name="pay_paymentType" value="02" />微信
          <input type="radio" name="pay_paymentType" value="04" />货到付款
        </td>
      </tr>
    </table>
  </form>
</div>
<div id="payOrder-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="payOrder()">完成订单</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#payOrder').dialog('close')">取消</a>
</div>
