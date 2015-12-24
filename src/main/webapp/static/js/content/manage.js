var editor = null;
$(function(){
    $('#content').datagrid({
        title:'发布信息',
        url:_context+'/smallowadmin/content/_list?merchantId='+merchantId,
        loadMsg:'正在加载数据,请稍后...',
        columns:[[
            {field:'id',title:'选择',width:40,checkbox:true},
            {field:'title',title:'标题',width:300},
            {field:'originalPrice',title:'原价',width:100},
            {field:'concessionalPrice',title:'优惠价',width:100,align:'center'},
            {field:'needAppointment',title:'是否需要预约',width:100,align:'center',
                formatter:function(val,row){
                        if(val){
                            return "是";
                        }  else{
                            return "否";
                        }
                }
            },
            {field:'effectDate',title:'有效期',width:150,align:'center'},
            {field:'leftNum',title:'剩余件数',width:100,align:'center'}
        ]],
        pagination:true,
        rownumbers:true,
        toolbar:[
            {
                text:'发布信息',
                iconCls:'icon-add',
                handler:function(){
                    $("#addContent").dialog('open');
                    $("#addContent-form").form('clear');
                    $("#titleImgDisplay").html("");
                    editor.setData("");
                    $('#file_upload_1').uploadify('cancel', '*');
                    $("#file_upload_1-queue").html("");
                }
            },'-',{
                text:'修改',
                iconCls:'icon-edit',
                handler:function(){

                    editContent();
                }
            },'-',{
                text:'删除',
                iconCls:'icon-remove',
                handler:function(){
                    delContent();
                 }
            },'-',{
                text:'查看订单',
                iconCls:'icon-help',
                handler:function(){
                    findOrders();
                }
            },'-',{
                text:'下单',
                iconCls:'icon-redo',
                handler:function(){
                    placeAnOrder();
                }
            }

        ],
        pageSize: 15,//每页显示的记录条数，默认为10
        pageList: [5,10,15]//可以设置每页记录条数的列表
    });
    var p_p = $('#content').datagrid('getPager');
    $(p_p).pagination({
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    editor=CKEDITOR.replace( 'Content',{height:'260px'} );
    CKFinder.setupCKEditor( editor, _context+'/static/ckfinder/' );

    $("#file_upload_1").uploadify({
        height        : 24,
        swf           : _context+'/static/uploadify/uploadify.swf',
        uploader      : _context+'/smallowadmin/content/upload',
        width         : 120,
        auto     : false,
        buttonText: '<div>选择文件</div>',
        removeCompleted: true,
        onSelect:function(file){
            $("#startUpload").linkbutton('enable');
        },
        onUploadSuccess:function(file, data, response){
            $('#' + file.id).find('.data').html(' 上传完毕');
            var json=JSON.parse(data);
            var str=$("#titleImg").val();
            if(str==""){
                str+=json.filepath;
            }else{
                str+=","+json.filepath;
            }
            $("#titleImg").val(str);
            $("#startUpload").linkbutton('disable');
            var img="<img  src='"+_context+json.filepath+"' width='150' height='80' style='margin-left:5px;' />";
            $("#titleImgDisplay").append(img);
        },
        onClearQueue:function(queueItemCount){
           // alert(queueItemCount);
        }
    });


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
});


function editContent(){
    var url="/smallowadmin/content/edit";
    var objs= $("#content").datagrid("getChecked");
    if(objs.length!=1){
        $.messager.alert("提示","请选择一条数据进行修改",'info');
        return false;
    }
    $("#addContent").dialog('open');
    $("#addContent-form").form('clear');
    var id=objs[0].id;
    var obj={id:id};
    $.post(_context+url,obj,function(data){
        $("#title").textbox("setValue",data.title);
        $("#originalPrice").textbox("setValue",data.originalPrice);
        $("#concessionalPrice").textbox("setValue",data.concessionalPrice);
        $("#leftNum").textbox("setValue",data.leftNum);
        $('#effectDate').datebox('setValue',data.effectDate);
        editor.setData(data.content);
        var _needAppointment=data.needAppointment==true?"1":"0";
       // alert(_needAppointment);
        $("input[name='needAppointment'][value='"+_needAppointment+"']").attr('checked',true);
        $("#titleImgDisplay").html("");
        var titleImgs=data.titleImg.split(",");
        $.each(titleImgs,function(index,obj){
            var img="<img  src='"+_context+obj+"' width='150' height='80' style='margin-left:5px;' />";
            $("#titleImgDisplay").append(img);
        });
        $("#contentId").val(data.id);
        $("#titleImg").val(data.titleImg);
    },'json');
}




function saveContent(){
    var content=editor.getData();
    var titleImg=$("#titleImg").val();
    var title=$("#title").val();
    var originalPrice=$("#originalPrice").val();
    var concessionalPrice=$("#concessionalPrice").val();
    var needAppointment=$("input[name='needAppointment']:checked").val();
    var leftNum=$("#leftNum").val();
    var effectDate=$('#effectDate').datebox('getValue');

    var contentId=$("#contentId").val();
    var cmd="new";
    if(contentId!=""){
        cmd="edit";
    }
    var param={
        title:title,
        originalPrice:originalPrice,
        concessionalPrice:concessionalPrice,
        needAppointment:needAppointment,
        leftNum:leftNum,
        content:content,
        titleImg:titleImg,
        cmd:cmd,
        merchantId:merchantId,
        effectDate:effectDate,
        id:contentId
    }
   // alert(merchantId);
    $.post(_context+"/smallowadmin/content/save",param,function(msg){
        if(msg.success){
            $.messager.alert('成功提示','操作成功','info');
            $("#addContent").dialog('close');
            $('#content').datagrid("reload");
            $("#titleImgDisplay").html("");
        }
    },"json");
}


function delContent(){
    var objs= $("#content").datagrid("getChecked");
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
                url: _context+'/smallowadmin/content/del',
                data: {ids:ids},
                beforeSend:ajaxLoading,
                dataType: "json",
                success: function(msg){
                    if(msg.msg=="success"){
                        $.messager.alert('成功提示','信息删除成功!','info');
                    }else{
                        $.messager.alert('出错提示','信息删除失败!','error');
                    }
                    $('#content').datagrid("reload");
                    ajaxLoadEnd();
                }
            });
        }
    });
}

function findOrders(){
    window.location.href=_context+"/smallowadmin/order/queryOrder?merchantId="+merchantId;
}

function placeAnOrder(){
    var objs=$("#content").datagrid("getChecked");
    if(objs.length!=1){
        $.messager.alert("提示","请选择一条数据进行下单操作",'info');
        return false;
    }
    $("#placeOrder").dialog('open');
    $("#placeOrder-form").form('clear');
    var id=objs[0].id;
    var obj={contentId:id,merchantId:merchantId};
    $.post(_context+"/smallowadmin/order/placeOrder",obj,function(data){
        if(data!=undefined){
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
    var param={
        totalMoney:totalMoney,
        telephone:telephone,
        contentNum:contentNum,
        realPaymentMoney:realPaymentMoney,
        contentId:contentId,
        title:$("#order_title").html(),
        status:"01"
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
        }
    },'json');
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
function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}