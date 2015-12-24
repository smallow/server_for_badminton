$(function(){

    $("#user").datagrid({
        title:"注册用户信息",
        url:_context+'/smallowadmin/user/_list',
        loadMsg:'正在加载数据,请稍后...',
        columns:[[
            {field:'id',title:'选择',width:40,checkbox:true},
            {field:'telephone',title:'手机号',width:120},
            {field:'email',title:'邮箱',width:180},
            {field:'pwd',title:'密码',width:180,align:'center'},
            {field:'createTime',title:'注册时间',width:180,align:'center'}
        ]],
        pagination:true,
        rownumbers:true,
        pageSize: 20,//每页显示的记录条数，默认为10
        pageList: [20,30,40],//可以设置每页记录条数的列表
        toolbar:[
            {
                text:'添加用户',
                iconCls:'icon-add',
                handler:function(){
                    $("#addUser").dialog('open');
                    $("#addUser-form").form('clear');
                }
            },'-',
            {
                text:'修改',
                iconCls:'icon-edit',
                handler:function(){
                    edit();
                }
            },'-',
            {
                text:'删除',
                iconCls:'icon-remove',
                handler:function(){
                    del();
                }
            },'-',
            {
                text: '手机号:<input class="easyui-textbox" id="query_telephone"/>'
            },
            {
                text:'查询',
                iconCls:'icon-search',
                handler:function(){
                    var telephone=$('#query_telephone').val();
                    var param={telephone:telephone};
                    $("#user").datagrid("load",param);
                }
            }
        ]
    });

});


function saveUser(){
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
            $("#addUser").dialog('close');
            $('#user').datagrid("reload");
        }
    },'json');
}
function edit(){

}
function del(){
    var objs= $("#user").datagrid("getChecked");

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
                url: _context+'/smallowadmin/user/del',
                data: {ids:ids},
                beforeSend:ajaxLoading,
                dataType: "json",
                success: function(msg){
                    if(msg.msg=="success"){
                        $.messager.alert('成功提示','用户删除成功!','info');
                    }else{
                        $.messager.alert('出错提示','用户删除失败!','error');
                    }
                    $('#user').datagrid("reload");
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