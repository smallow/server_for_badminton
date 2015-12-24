<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/7
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="placeOrder" class="easyui-dialog" title="下单" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#placeOrder-buttons'" style="width:600px;height:400px;padding:0px;">
  <form id="placeOrder-form" method="post" >
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="tb_placeOrder">
      <tr>
        <td class="textTd">标题:</td>
        <td class="textInput" colspan="3">
          <span id="order_title"></span>
          <input type="hidden" id="order_contentId" value="">
        </td>
      </tr>
      <tr>
        <td class="textTd">原价</td>
        <td class="textInput"><span id="order_originalPrice"></span>&nbsp;元</td>
        <td class="textTd">优惠价</td>
        <td class="textInput"><span id="order_concessionalPrice"></span>&nbsp;元</td>
      </tr>
      <tr>
        <td class="textTd">数量</td>
        <td class="textInput">
          <input class="easyui-numberspinner"  id="order_contentNum" data-options="value:1,increment:1" style="width:120px;" /></td>
        <td class="textTd">小计</td>
        <td class="textInput"><span id="order_totalMoney"></span>&nbsp;元</td>
      </tr>
      <tr>
        <td class="textTd">手机号</td>
        <td class="textInput"><input class="easyui-numberbox"   id="order_telephone" name="order_telephone" data-options="required:true" style="width:180px;height:24px"></td>
        <td class="textTd">订单合计</td>
        <td class="textInput"><span id="order_realPaymentMoney"></span>&nbsp;元</td>
      </tr>
    </table>
    </form>
</div>
<div id="placeOrder-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveOrder()">下一步</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#placeOrder').dialog('close')">取消</a>
</div>
