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
  <title>后台登陆</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/jquery/demo.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/sys/hrms.css">
  <script src="<%=path%>/static/jquery/jquery.min.js"></script>
  <script src="<%=path%>/static/jquery/jquery.easyui.min.js"></script>
  <script src="<%=path%>/static/js/sys/mask.js"></script>
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
<body class="loginbg">

<div class="loginArea">
  <div class="loginLogo"></div>
  <div class="loginFormArea">
    <table width="320" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="70" height="50" align="right">账号：</td>
        <td><input type="text" name="loginCode" id="loginCode" class="inputStyle" /></td>
      </tr>
      <tr>
        <td height="50" align="right">密码：</td>
        <td><input type="password" name="login_pwd" id="login_pwd" class="inputStyle"/></td>
      </tr>
      <tr>
        <td height="70">&nbsp;</td>
        <td>
          <a href="javascript:void(0)" class="loginBtn" onclick="login()"></a>
      </tr>
    </table>
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







  function clearLoginForm(){
    $("#login").form('clear');
  }


  function login(){
    var obj={
      loginCode:$("#loginCode").val(),
      pwd:$("#login_pwd").val()
    };

    if($("#loginCode").val()=="" || $("#login_pwd").val()==""){
      $.messager.alert("提示",'用户名密码不能为空','info');
      return false;
    }
    ajaxLoading("");
    $.post(_context+"/smallowadmin/login",obj,function(data){
      if(data.msg=='success'){
        window.location.href='/smallowadmin/main';
      }else{
        $.messager.alert("提示",'用户名密码错误','info');
        clearLoginForm();
      }

      ajaxLoadEnd();
    },'json');
  }
</script>




</body>
</html>
