<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/5/11
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path=request.getContextPath();
  Object userIds=session.getAttribute("userId");
  Long userId=null;
  if(userIds!=null){
    userId=Long.valueOf(String.valueOf(userIds));
  }
%>
<html>
<head>
    <title>网站标题</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/demo.css">
  <script src="<%=path%>/static/jquery/jquery.min.js"></script>
  <script src="<%=path%>/static/jquery/jquery.easyui.min.js"></script>
  <style>
    body{ text-align:center}
    #userOrderData table{
      border-collapse:collapse;
      padding:10px;
      /*background:#eceadf;
      background-color: #FF8C69;*/
      -moz-border-radius: 15px;
      -webkit-border-radius: 15px;
      border-radius:15px;


    }
    #userOrderData table td{height:30px;padding:5px;border: solid 0px lightgray;text-align: center}

    .datagrid-cell a:link{text-decoration:none;}
    .datagrid-cell a:visited{text-decoration:none;}
    .datagrid-cell a:hover{text-decoration:underline;}


    #contentDetail-form table{ border-collapse:collapse;}
    #contentDetail-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #contentDetail-form .textTd{width: 10%;text-align: center;}
    #contentDetail-form .textInput{width: 10%;text-align: left;}

    #placeOrder-form table{ border-collapse:collapse;}
    #placeOrder-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #placeOrder-form .textTd{width: 15%;text-align: center;}
    #placeOrder-form .textInput{width: 35%;text-align: left;}


    #payOrder-form table{ border-collapse:collapse;}
    #payOrder-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #payOrder-form .textTd{width: 15%;text-align: center;}
    #payOrder-form .textInput{width: 35%;text-align: left;}

    #editOrder-form table{ border-collapse:collapse;}
    #editOrder-form table td{height:30px;padding:5px;border: solid 1px lightgray;text-align: center}
    #editOrder-form .textTd{width: 15%;text-align: center;}
    #editOrder-form .textInput{width: 35%;text-align: left;}

  </style>

</head>
<body>


<div id="tt" class="easyui-tabs" data-options="pill:true"   style="width:1280px;height:720px;margin:0 auto;">
  <div title="用户注册" style="padding:10px">
    <div class="easyui-panel" title="注册" style="width:400px">
      <div style="padding:10px 60px 20px 60px">
        <form id="ff" method="post" >
          <table cellpadding="5">
            <tr>
              <td>手机号:</td>
              <td><input class="easyui-textbox" type="text" id="telephone" data-options="required:true" /></td>
            </tr>
            <tr>
              <td>邮箱:</td>
              <td><input class="easyui-textbox" type="text" id="email" data-options="required:true,validType:'email'" /></td>
            </tr>
            <tr>
              <td>密码:</td>
              <td><input class="easyui-textbox"  id="pwd" data-options="type:'password'" data-options="required:true" /></td>
            </tr>
            <tr>
              <td>确认密码:</td>
              <td><input class="easyui-textbox" id="confirm_pwd" data-options="type:'password'"  /></td>
            </tr>
          </table>
        </form>
        <div style="text-align:center;padding:5px">
          <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
        </div>
      </div>
    </div>
  </div>
  <div title="登录" style="padding:10px">
    <div id="loginPanel" class="easyui-panel" title="登录" style="width:400px">
      <div style="padding:10px 60px 20px 60px">
        <form id="login" method="post" >
          <table cellpadding="5">
            <tr>
              <td>手机号:</td>
              <td><input class="easyui-textbox" type="text" id="login_telephone" data-options="required:true" /></td>
            </tr>
            <tr>
              <td>密码:</td>
              <td><input class="easyui-textbox"  id="login_pwd" data-options="type:'password'" data-options="required:true" /></td>
            </tr>

          </table>
        </form>
        <div style="text-align:center;padding:5px">
          <a href="javascript:void(0)" class="easyui-linkbutton" onclick="login()">登陆</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearLoginForm()">重置</a>
          <a href="javascript:void(0)" class="easyui-linkbutton c4" target="_blank" onclick="javascript:window.location.href='/smallowadmin/main'">管理后台</a>
        </div>
      </div>
    </div>
    <div id="userOrderData" class="easyui-panel" title="我的订单" data-options="closed:true"  style="width:95%;height:650px;padding:10px;">
    </div>
  </div>
  <div title="查看所有优惠信息" id="contentInfo" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
    <span id="notLoginMsg">请先登录!</span>
    <div id="dl"></div>
  </div>
</div>




<script>
  function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
  }
  function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
  }

  var _context='<%=path%>';

  $(function(){
    var userId='<%=userId%>';
    if(userId!=undefined &&userId!='' && userId!='null'){
      getContentData();
      getUserData(userId);
    }


    $("#order_contentNum").numberspinner({
      onSpinUp:function(){
        var price=$("#order_concessionalPrice").html();
        var num=$("#order_contentNum").numberspinner("getValue");
        // alert(price+" "+num);
        $("#order_totalMoney").html(num*parseInt(price));
        $("#order_realPaymentMoney").html(num*parseInt(price));
      },
      onSpinDown:function(){
        var price=$("#order_concessionalPrice").html();
        var num=$("#order_contentNum").numberspinner("getValue");
        // alert(price+" "+num);
        $("#order_totalMoney").html(num*parseInt(price));
        $("#order_realPaymentMoney").html(num*parseInt(price));
      },
      onChange:function(){
        var price=$("#order_concessionalPrice").html();
        var num=$("#order_contentNum").numberspinner("getValue");
        // alert(price+" "+num);
        $("#order_totalMoney").html(num*parseInt(price));
        $("#order_realPaymentMoney").html(num*parseInt(price));
      }

    });

    $("#edit_order_contentNum").numberspinner({
      onSpinUp:function(){
        var price=$("#edit_order_concessionalPrice").html();
        var num=$("#edit_order_contentNum").numberspinner("getValue");
        $("#edit_order_realPaymentMoney").html(num*parseInt(price));
        $("#edit_order_totalMoney").html(num*parseInt(price));
      },
      onSpinDown:function(){
        var price=$("#edit_order_concessionalPrice").html();
        var num=$("#edit_order_contentNum").numberspinner("getValue");
        $("#edit_order_realPaymentMoney").html(num*parseInt(price));
        $("#edit_order_totalMoney").html(num*parseInt(price));
      },
      onChange:function(){
        var price=$("#edit_order_concessionalPrice").html();
        var num=$("#edit_order_contentNum").numberspinner("getValue");
        $("#edit_order_realPaymentMoney").html(num*parseInt(price));
        $("#edit_order_totalMoney").html(num*parseInt(price));

      }
    });

  });

  function submitForm(){
    var telephone=$("#telephone").val();
    var email=$("#email").val();
    var pwd=$("#pwd").val();
    var confirm_pwd=$("#confirm_pwd").val();
    var obj={
      telephone:telephone,
      email:email,
      pwd:pwd,
      cmd:'new'
    }
    $.post(_context+"/smallowadmin/user/save",obj,function(msg){
      if(msg.success){
        $.messager.alert('成功提示','用户添加成功','info');
        clearForm();
      }
    },'json');
  }
  function clearForm(){
    $('#ff').form('clear');
  }


  function clearLoginForm(){
    $("#login").form('clear');
  }


  function login(){
    var obj={
      telephone:$("#login_telephone").val(),
      pwd:$("#login_pwd").val()
    };

    $.post(_context+"/frontuser/login.htm",obj,function(data){
      if(data.msg=='success'){
        $.messager.alert("提示",'登录成功','info');
        clearLoginForm();

        $("#tt").tabs("select",'查看所有优惠信息');
        getContentData();
        getUserData(data.userId);
      }else{
        $.messager.alert("提示",'用户名密码错误','info');
        clearLoginForm();
      }
    },'json');
  }

  function getUserData(userId){
    $("#loginPanel").panel("close");
    $.post(_context+"/smallowadmin/order/getOrdersByUserId",{userId:userId},function(data){
      $("#userOrderData").html("");
        if(data!=undefined && data.length>0){
          $.each(data,function(index,val){
            var status='';
            var paymentType='';
            if(val.status=='01'){
              status="<a href=\"javascript:void(0)\" class=\"easyui-linkbutton c4 l-btn l-btn-small\" onclick=\"editOrder('"+val.id+"')\" ><span class=\"l-btn-left\"><span class=\"l-btn-text\">等待付款</span></span></a>";
            }else if(val.status=='02'){
              status="已付款";
            }else if(val.status=='03'){
              status='交易结束';
            }

            if(val.paymentType=='01'){
              paymentType='支付宝';
            }else if(val.paymentType=='02'){
              paymentType='微信';
            }else if(val.paymentType=='04'){
              paymentType='货到付款';
            }


            var str="<table  border=\"0\" style=\"width:100%;margin-top: 10px;\">";
              str+="<tr><td  rowspan='3' width='10%'><img src='"+val.firstTitleImg+"' width='150' height='80' /></td><td style='width: 5%;'>订单编号: </td><td style='width: 5%;'>"+val.orderNum+"</td><td style='width: 5%;'>商品名称:</td><td colspan='2' style='width: 50%;text-align: left;'>"+val.title+"</td></tr>" +
                      "<tr><td >数量</td><td >"+val.contentNum+"</td><td >金额</td><td style='text-align: left;padding-left: 10px;'>"+val.realPaymentMoney+"</td><td width='150'>商户:</td><td style='text-align: left;padding-left: 10px;'>"+val.name+"</td></tr>" +
                      "<tr><td >订单状态</td><td >"+status+"</td><td >付款方式</td><td style='text-align: left;padding-left: 10px;'>"+paymentType+"</td><td width='150'>下单时间:</td><td style='text-align: left;padding-left: 5px;'>"+val.createTime+"</td></tr>" +
                      "</table>";
            var obj=$(str);
            obj.appendTo($("#userOrderData"));



          });
          var obj2=$("<a class=\"easyui-linkbutton c5 l-btn l-btn-small\" style='width: 150px;' href='javascript:void(0)' onclick='zhuxiao()' ><span class=\"l-btn-left\"><span class=\"l-btn-text\">退出登录</span></span></a>");
          obj2.appendTo($("#userOrderData"));
          $("#userOrderData").panel("open");
        }else{
          $("#userOrderData").panel("open");
          $("#userOrderData").html("该用户尚无订单!");
         // alert($("#userOrderData").html());
        }
    },'json');
  }

  function zhuxiao(){
    $.post(_context+"/quit.jsp",{},function(data){
      location.reload();
    });
  }

  function getContentData(){

    $.post(_context+"/smallowadmin/content/getTop10MerchantContents",{},function(data){
      var array=[];
      if(data!=undefined){
        $.each(data,function(index,val){
          if(val!=undefined && val.length>0){
            $.each(val,function(_index,_val){
              var obj={"text":"<a  href='javascript:void(0);' onclick=\"contentDetail('"+_val.id+"')\">"+_val.title+"</a>",group:index};
              array.push(obj);
            });
          }
        });
        $("#notLoginMsg").html("欢迎登陆");
      }
      $('#dl').datalist({
        data: array,
        groupField: 'group',
        title:"商户优惠信息"
      });
    },'json');
  }

  function contentDetail(id){
    if(id!=undefined && id!=null){
      var url="/smallowadmin/content/edit";
      $("#w").window('open');
      $("#contentDetail-form").form('clear');
      var obj={id:id};
      $.post(_context+url,obj,function(data){
        $("#title").textbox("setValue",data.title);
        $("#originalPrice").textbox("setValue",data.originalPrice);
        $("#concessionalPrice").textbox("setValue",data.concessionalPrice);
        $("#leftNum").textbox("setValue",data.leftNum);
        $('#effectDate').datebox('setValue',data.effectDate);
        $("#Content").html(data.content);
        //alert(data.content);
        var _needAppointment=data.needAppointment==true?"1":"0";
        $("input[name='needAppointment'][value='"+_needAppointment+"']").attr('checked',true);
        $("#titleImgDisplay").html("");
        var titleImgs=data.titleImg.split(",");
        $.each(titleImgs,function(index,obj){
          var img="<img  src='"+_context+obj+"' width='150' height='80' style='margin-left:5px;' />";
          $("#titleImgDisplay").append(img);
        });
        $("#contentId").val(data.id);
        $("#titleImg").val(data.titleImg);
        $("#merchantId").val(data.merchantId);

      },'json');
    }
  }



  function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
  }
  function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
      return new Date(y,m-1,d);
    } else {
      return new Date();
    }
  }



  function placeAnOrder(){
    var id=$("#contentId").val();
    var merchantId=$("#merchantId").val();
    var obj={contentId:id,merchantId:merchantId};
    $.post(_context+"/smallowadmin/order/placeOrder",obj,function(data){
      if(data!=undefined){
        $("#placeOrder").dialog('open');
        $("#placeOrder-form").form('clear');
        $("#w").window('close');

        $("#order_title").html(data.title);
        $("#order_originalPrice").html(data.originalPrice);
        $("#order_concessionalPrice").html(data.concessionalPrice);
        $("#order_contentId").val(data.id);
      }
    },'json');
  }

  function saveOrder(){

    var telephone=$("#order_telephone").val();
    var totalMoney=parseInt($("#order_totalMoney").html());
    var contentNum=$("#order_contentNum").numberspinner("getValue");
    var realPaymentMoney=parseInt($("#order_realPaymentMoney").html());
    var contentId=$("#order_contentId").val();
    var userId='<%=userId%>';
    var param={
      totalMoney:totalMoney,
      telephone:telephone,
      contentNum:contentNum,
      realPaymentMoney:realPaymentMoney,
      contentId:contentId,
      title:$("#order_title").html(),
      status:"01",
      userId:userId
    };
    ajaxLoading();
    $("#placeOrder").dialog('close');
    $.post(_context+"/smallowadmin/order/saveOrderFirstStep",param,function(data){
      ajaxLoadEnd();
      $("#payOrder").dialog('open');
      $("#payOrder-form").form('clear');
      if(data.msg=='success'){
        $("#pay_order_title").html(data.title);
        $("#pay_order_contentId").val(data.contentId);
        $("#pay_order_contentNum").html(data.contentNum);
        $("#pay_order_num").html(data.orderNum);
        $("#pay_order_telephone").html(data.telephone);
        $("#pay_order_realPaymentMoney").html(data.realPaymentMoney);


      }
    },'json');
  }

  function payOrder(){
    var orderNum=$("#pay_order_num").html();
    var paymentgType=$("input[name='pay_paymentType']:checked").val();
    $.post(_context+"/smallowadmin/order/payOrderSecondStep",{orderNum:orderNum,paymentType:paymentgType},function(data){
      if(data.msg=='success'){
        $.messager.alert('提示','订单已完成支付','info');
        $("#payOrder").dialog('close');
        $("#tt").tabs("select",'登录');
        var userId='<%=userId%>';
        getUserData(userId);
      }
    },'json');
  }


  function editOrder(id){


    var obj={orderId:id};
    $.post(_context+"/smallowadmin/order/getOrderDetail",obj,function(data){
      if(data!=undefined){
        $("#editOrder").dialog('open');
        $("#editOrder-form").form('clear');


        $("#edit_order_id").val(data.id);
        $("#edit_order_title").html(data.title);
        $("#edit_order_contentNum").numberspinner("setValue",data.contentNum);
        $("#edit_order_telephone").numberbox('setValue', data.telephone);
        $("#edit_order_realPaymentMoney").html(data.realPaymentMoney);
        $("#edit_order_totalMoney").html(data.totalMoney);
        $("#edit_order_num").html(data.orderNum);
        $("#edit_order_createTime").html(data.createTime);
        $("#edit_order_concessionalPrice").html(data.concessionalPrice);

      }
    },'json');

  }


  function updateOrder(){
    var id=$("#edit_order_id").val();
    if(id!=''){
      var contentNum=$("#edit_order_contentNum").numberspinner('getValue');
      var telephone=$("#edit_order_telephone").numberbox('getValue');
      var realPaymentMoney=parseInt($("#edit_order_realPaymentMoney").html());
      var totalMoney=parseInt($("#edit_order_totalMoney").html());
      var paymentgType=$("input[name='edit_paymentType']:checked").val();
      var userId='<%=userId%>';
      var param={
        id:id,
        contentNum:contentNum,
        telephone:telephone,
        realPaymentMoney:realPaymentMoney,
        totalMoney:totalMoney,
        paymentgType:paymentgType,
        status:'02'
      };

      $.post(_context+"/smallowadmin/order/updateOrder",param,function(data){
        if(data.msg=='success'){
          $.messager.alert('提示','订单更新成功','info');
          $("#editOrder").dialog('close');
          getUserData(userId);
        }
      },'json');
    }
  }




</script>



<div id="w" class="easyui-window" title="优惠信息详情" data-options="closed:true,footer:'#ft',top:($(window).height() - 1280) * 0.05,left:($(window).width() - 650) * 0.15,minimizable:false,maximizable:false,collapsible:false" style="width:1280px;height:650px;padding:5px;">
  <form id="contentDetail-form" method="post" >
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="tb_add_merchant">
      <tr>
        <td class="textTd">标题:</td>
        <td class="textInput" colspan="9"><input class="easyui-textbox" id="title" name="title" data-options="required:true" style="width:500px;height:24px"></td>

      </tr>
      <tr>
        <td class="textTd">原价:</td>
        <td class="textInput">
          <input class="easyui-numberbox" precision="2" id="originalPrice" name="originalPrice"style="width:100px;height:24px">
        </td>
        <td class="textTd">优惠价:</td>
        <td class="textInput">
          <input class="easyui-numberbox" precision="2" id="concessionalPrice" name="originalPrice"style="width:100px;height:24px">
        </td>
        <td class="textTd">是否需要预约:</td>
        <td class="textInput">
          <input type="radio" value="1" name="needAppointment"/>是
          <input type="radio" value="0" name="needAppointment"/>否
        </td>
        <td class="textTd">剩余件数:</td>
        <td class="textInput"><input class="easyui-textbox" id="leftNum" name="leftNum" style="width:100px;height:24px"></td>
        <td class="textTd">有效期:</td>
        <td class="textInput"><input id="effectDate" name="effectDate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" /></td>
      </tr>

      <tr>
        <td class="textTd">标题图:
          <input type="hidden" id="titleImg" value="" />
          <input type="hidden" id="contentId" value="" />
          <input type="hidden" id="merchantId" value="" />
        </td>

        <td colspan="9" id="titleImgDisplay" class="textInput">

        </td>
      </tr>
      <%--<tr>
        <td class="textInput" colspan="6" >
          <div style="width: 100%;height: 60px;">
            <div id="file_upload_1-queue" class="uploadify-queue"></div>
          </div>
        </td>
      </tr>--%>
      <tr>
        <td class="textInput" colspan="10">
          <div id="Content" style="width: 1120px;height: 350px;overflow: scroll;"></div>
        </td>
      </tr>
    </table>
  </form>
</div>
<div id="ft" style="padding: 5px;">
  <a href="javascript:void(0)" class="easyui-linkbutton c5"  style="width:120px;float: right;" onclick="placeAnOrder()">立即抢购</a>
</div>



<div id="placeOrder" class="easyui-dialog" title="下单" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#placeOrder-buttons',top:($(window).height() - 600) * 0.05,left:($(window).width() - 400) * 0.15" style="width:600px;height:400px;padding:0px;">
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
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveOrder()">下一步</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#placeOrder').dialog('close')">取消</a>
</div>



<div id="payOrder" class="easyui-dialog" title="下单" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#payOrder-buttons',top:($(window).height() - 600) * 0.05,left:($(window).width() - 400) * 0.15" style="width:600px;height:400px;padding:0px;">
  <form id="payOrder-form" method="post" >
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="">
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
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="payOrder()">完成订单</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#payOrder').dialog('close')">取消</a>
</div>



<div id="editOrder" class="easyui-dialog" title="修改订单" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#editOrder-buttons',top:($(window).height() - 600) * 0.05,left:($(window).width() - 400) * 0.15" style="width:600px;height:400px;padding:0px;">
  <form id="editOrder-form" method="post" >
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="">
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
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateOrder()">完成订单</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editOrder').dialog('close')">取消</a>
</div>
</body>
</html>
