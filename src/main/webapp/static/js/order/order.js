$(function(){

    $("#orderTabs").tabs({
        onSelect:function(title){
        }
    });


    $("#wcl_orders").datagrid({
        title:"未处理订单",
        url:_context+'/smallowadmin/order/_list?merchantId='+merchantId+"&status=02",
        loadMsg:'正在加载数据,请稍后...',
        columns:[[
            {field:'id',title:'选择',width:40,checkbox:true},
            {field:'title',title:'标题',width:300},
            {field:'orderNum',title:'订单编号',width:150},
            {field:'createTime',title:'下单时间',width:150,align:'center'},
            {field:'status',title:'状态',width:80,align:'center',
                formatter:function(val,row){
                    if(val=="01"){
                        return "已提交";
                    }  else if(val=="02"){
                        return "已付款";
                    } else if(val=="03"){
                        return "已结束"
                    }
                }
            },
            {field:'paymentType',title:'支付方式',width:80,align:'center',
                formatter:function(val,row){
                    if(val=="01"){
                        return "支付宝";
                    }  else if(val=="02"){
                        return "微信";
                    } else if(val=="03"){
                        return "银联"
                    } else if(val=="04"){
                        return "到付"
                    }
                }

            },
            {field:'telephone',title:'手机号',width:100,align:'center'},
            {field:'contentNum',title:'份数',width:50,align:'center'},
            {field:'totalMoney',title:'总计',width:50,align:'center'},
            {field:'realPaymentMoney',title:'实付款',width:50,align:'center'},
            {field:'userVoucher',title:'用户凭证',width:100,align:'center'}
        ]],
        pagination:true,
        rownumbers:true,
        pageSize: 15,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        toolbar:[
            {
                text:'订单详情',
                iconCls:'icon-help',
                handler:function(){
                    findOrderDetail();
                }
            },'-',
            {
                text:'修改订单',
                iconCls:'icon-edit',
                handler:function(){
                    editOrder();
                }
            }
        ]

    });
    var p_p = $('#wcl_orders').datagrid('getPager');
    $(p_p).pagination({
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });


    $("#ywc_orders").datagrid({

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


function findOrderDetail(){
    var ids=$("#wcl_orders").datagrid("getChecked");
    if(ids.length!=1){
        $.messager.alert('提示','请选择一条订单查看','info');
        return false;
    }
    var id=ids[0].id;
    var obj={orderId:id};
    //alert(id);
    $("#orderDetail").dialog('open');
    $("#orderDetail-form").form('clear');
    $.post(_context+"/smallowadmin/order/getOrderDetail",obj,function(data){
        if(data!=undefined){
            $("#order_title").html(data.title);
            $("#order_contentNum").html(data.contentNum);
            $("#order_num").html(data.orderNum);
            $("#order_telephone").html(data.telephone);
            $("#order_realPaymentMoney").html(data.realPaymentMoney);
            $("#order_paymentType").html(data.paymentType);
            $("#order_createTime").html(data.createTime);
        }
    },'json');
}

function editOrder(){
    var ids=$("#wcl_orders").datagrid("getChecked");
    if(ids.length!=1){
        $.messager.alert('提示','请选择一条订单查看','info');
        return false;
    }

    var status=ids[0].status;
    if(status=='02' || status=='03'){
        $.messager.alert('提示','订单已完成,不能修改','info');
        return false;
    }
    var id=ids[0].id;
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
                $("#wcl_orders").datagrid("reload");
            }
        },'json');
    }
}

