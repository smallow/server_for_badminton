$(function () {


    $("#loginlog").datagrid({
        title: "日志管理",
        url: _context + '/smallowadmin/sys/log/getLoginLogs',
        loadMsg: '正在加载数据,请稍后...',
        columns: [[
            {field: 'id', title: '选择', width: 40, checkbox: true},
            {field: 'loginCode', title: '登陆账号', width: 120,align: 'center'
            },
            {field: 'loginName', title: '登陆人姓名', width: 120, align: 'center'},
            {field: 'loginTime', title: '登陆时间', width: 150, align: 'center'}
            /*{field: 'logoutTime', title: '登出时间', width: 150, align: 'center'}*/
        ]],
        pagination: true,
        rownumbers: true,
        pageSize: 20,//每页显示的记录条数，默认为10
        pageList: [20, 30, 40],//可以设置每页记录条数的列表
        toolbar: [
            /*{
             text: '添加人员',
             iconCls: 'icon-add',
             handler: function () {
             addEmployee();
             }
             }, '-',
             {
             text: '修改',
             iconCls: 'icon-edit',
             handler: function () {
             editEmployee();
             }
             }, '-',*/
            {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    delLoginLog();
                }
            }, '-',

            {
                text: '日期:<input id="dd" type="text">'
            },
            {
                text: '查询',
                iconCls: 'icon-search',
                handler: function () {
                    var date = $('#dd').datebox('getValue');
                    //alert(date);
                    var param={date:date};
                    $("#loginlog").datagrid("load", param);
                }
            }
        ]
    });
    var p_p = $('#loginlog').datagrid('getPager');
    $(p_p).pagination({
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

    $('#dd').datebox({
        formatter:myformatter,
        parser:myparser
    });

});

function delLoginLog(){
    var objs= $("#loginlog").datagrid("getChecked");
    if(objs.length<1){
        $.messager.alert("提示","请选择至少一条数据进行删除",'info');
        return false;
    }

    var ids="";
    $.each(objs,function(index,obj){
        ids+=obj.id+",";
    });
    ids=ids.replace(/,$/gi,"");
    $.messager.confirm('删除提示', '你确定删除下列数据吗', function(r){
        if(r){
            $.ajax({
                type: 'POST',
                url: _context+'/smallowadmin/sys/log/delLoginLogs',
                data: {ids:ids},
                beforeSend:ajaxLoading,
                dataType: "json",
                success: function(msg){
                    if(msg.msg=="success"){
                        $.messager.alert('成功提示','信息删除成功!','info');
                    }else{
                        $.messager.alert('出错提示','信息删除失败!','error');
                    }
                    $('#loginlog').datagrid("reload");
                    ajaxLoadEnd();
                }
            });
        }
    });
}


function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
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
    }else{
        return new Date();
    }
}