$(function(){
    $('#merchant').datagrid({
        title:'商户信息',
        url:_context+'/smallowadmin/merchant/_list',
        loadMsg:'正在加载数据,请稍后...',
        columns:[[
            {field:'id',title:'选择',width:40,checkbox:true},
            {field:'name',title:'商户名称',width:300},
            {field:'contactpersonname',title:'联系人',width:100},
            {field:'type',title:'商户类型',width:100,align:'center'},
            {field:'cn_bigType',title:'所属分类',width:100,align:'center'},
            {field:'sfTop10',title:'首页推荐',width:100,align:'center',formatter:function(val,row){
                if(val){
                    return "是";
                }  else{
                    return "否";
                }
            }},
            {field:'top10Order',title:'推荐排序',width:100,align:'center'}
        ]],
        pagination:true,
        rownumbers:true,
        toolbar:[
            {
                text:'添加商户',
                iconCls:'icon-add',
                handler:function(){
                    $("#addMerchant").dialog('open');
                    $("#addMerchant-form").form('clear');
                }
            },'-',{
                text:'修改',
                iconCls:'icon-edit',
                handler:function(){

                    editMerchant();
                }
            },'-',{
                text:'删除',
                iconCls:'icon-remove',
                handler:function(){delMerchant();}
            },'-',{
                text:'查看商户优惠信息',
                iconCls:'icon-help',
                handler:function(){
                    merchantContentManage();
                }
            }

        ],
        pageSize: 15,//每页显示的记录条数，默认为10
        pageList: [5,10,15]//可以设置每页记录条数的列表
    });
    var p_p = $('#merchant').datagrid('getPager');
    $(p_p).pagination({
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
});

function saveMerchant(){
    var url="/smallowadmin/merchant/save";
    var name=$("#merchant_name").val();
    var address=$("#merchant_address").val();
    var regno=$("#merchant_regno").val();
    var bigtype=$("#merchant_bigtype").combobox("getValue");
    var midtype="40001001";
    var pwd="123456";
    var type=$("#merchant_type").combobox("getValue");
    var contact_person_name=$("#contact_person_name").val();
    var telphone=$("#telphone").val();
    var top10Order=$("#top10Order").numberspinner("getValue");
    var sfTop10=0;
    if($('#sfTop10').is(':checked')){
        sfTop10=1;
    }

    var obj={
        name:name,
        address:address,
        regno:regno,
        bigtype:bigtype,
        midtype:midtype,
        pwd:pwd,
        type:type,
        contactpersonname:contact_person_name,
        telphone:telphone,
        cmd:'new',
        top10Order:top10Order,
        sfTop10:sfTop10
    };
    ajaxLoading();
    $.post(_context+url,obj,function(msg){
        if(msg.success){
            $.messager.alert('成功提示','商户添加成功,原始登录密码为123456','info');
            $("#addMerchant").dialog('close');
            $('#merchant').datagrid("reload");
        }
        ajaxLoadEnd();
    },'json');

}


function editMerchant(){

    var url="/smallowadmin/merchant/edit";
    var objs= $("#merchant").datagrid("getChecked");
    if(objs.length!=1){
        $.messager.alert("提示","请选择一条数据进行修改",'info');
        return false;
    }
    ajaxLoading();
    $("#editMerchant").dialog('open');
    $("#editMerchant-form").form('clear');
    var id=objs[0].id;
    var obj={id:id};
    $.post(_context+url,obj,function(data){
        $("#e_merchant_name").textbox("setValue",data.name);
        $("#e_merchant_address").textbox("setValue",data.address);
        $("#e_merchant_regno").textbox("setValue",data.regno);
        $("#e_merchant_bigtype").combobox("setValue",data.bigtype);
        $("#e_merchant_type").combobox("setValue",data.type);
        $("#e_contact_person_name").textbox("setValue",data.contactpersonname);
        $("#e_telphone").textbox("setValue",data.telphone);
        $("#e_merchant_id").val(data.id);
        $("#e_top10Order").numberspinner("setValue",data.top10Order);
       // alert(data.sfTop10);
        if(data.sfTop10){
            $("#e_sfTop10").prop("checked",true);
        }else{
            $("#e_sfTop10").prop("checked",false);
        }
        //alert($("#e_sfTop10").is(':checked'));
        ajaxLoadEnd();
    },'json');
}
function updateMerchant(){
    var url="/smallowadmin/merchant/save";
    var name=$("#e_merchant_name").val();
    var address=$("#e_merchant_address").val();
    var regno=$("#e_merchant_regno").val();
    var bigtype=$("#e_merchant_bigtype").combobox("getValue");
    var type=$("#e_merchant_type").combobox("getValue");
    var contact_person_name=$("#e_contact_person_name").val();
    var telphone=$("#e_telphone").val();
    var id=$("#e_merchant_id").val();
    var top10Order=$("#e_top10Order").numberspinner("getValue");
    var sfTop10=0;
    if($('#e_sfTop10').is(':checked')){
        sfTop10=1;
    }
    var obj={
        id:id,
        name:name,
        address:address,
        regno:regno,
        bigtype:bigtype,
        type:type,
        contactpersonname:contact_person_name,
        telphone:telphone,
        cmd:'edit',
        top10Order:top10Order,
        sfTop10:sfTop10
    };
    ajaxLoading();
    $.post(_context+url,obj,function(msg){
        if(msg.success){
            $.messager.alert('成功提示','商户修改成功','info');
            $("#editMerchant").dialog('close');
            $('#merchant').datagrid("reload");
        }
        ajaxLoadEnd();
    },'json');
}

function delMerchant(){
    var objs= $("#merchant").datagrid("getChecked");

    if(objs.length<1){
        $.messager.alert("提示","请选择至少一条数据进行修改",'info');
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
                url: _context+'/smallowadmin/merchant/del',
                data: {ids:ids},
                beforeSend:ajaxLoading,
                dataType: "json",
                success: function(msg){
                    if(msg.msg=="success"){
                        $.messager.alert('成功提示','商户删除成功!','info');
                    }else{
                        $.messager.alert('出错提示','商户删除失败!','error');
                    }
                    $('#merchant').datagrid("reload");
                    ajaxLoadEnd();
                }
            });
        }
    });
}



function merchantContentManage(){
    var obj=$("#merchant").datagrid("getChecked");
    if(obj.length!=1){
        $.messager.alert('提示','请选择一家商户','info');
        return false;
    }
    var title=obj[0].name;

    parent.addTabs(title,_context+'/smallowadmin/merchant/contentManage?merchantId='+obj[0].id,'icon-group');
}



function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}