<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/8
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="orderDetail" class="easyui-dialog" title="订单详情" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:600px;height:400px;padding:0px;">
  <form id="orderDetail-form" method="post" >
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="tb_placeOrder">
      <tr>
        <td class="textTd">标题:</td>
        <td class="textInput" colspan="3">
          <span id="order_title"></span>
        </td>
      </tr>

      <tr>
        <td class="textTd">数量</td>
        <td class="textInput">
          <span id="order_contentNum"></span>
        </td>
        <td class="textTd">订单编号</td>
        <td class="textInput"><span id="order_num"></span></td>
      </tr>
      <tr>
        <td class="textTd">手机号</td>
        <td class="textInput"><span id="order_telephone"></span></td>
        <td class="textTd">订单合计</td>
        <td class="textInput"><span id="order_realPaymentMoney"></span>&nbsp;元</td>
      </tr>
      <tr>
        <td class="textTd">支付方式</td>
        <td class="textInput">
          <span id="order_paymentType"></span>
        </td>
        <td class="textTd">下单时间</td>
        <td class="textInput"><span id="order_createTime"></span></td>
      </tr>
    </table>
  </form>
</div>

