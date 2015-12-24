$(function(){
    initOrgTree();
    $("#employee").datagrid({
        title: "人员管理",
        url: _context + '/smallowadmin/organ/getEmployee',
        loadMsg: '正在加载数据,请稍后...',
        columns: [[
            {field: 'empInfoId', title: '选择', width: 40, checkbox: true},
            
            {field: 'roomNum', title: '房间号', width: 80,align: 'center'},
            {field: 'name', title: '姓名', width: 120,align: 'center',

                styler: function(value,row,index){
                    /*if (row.sfUser){
                     return 'background-color:#ffee00;color:red;';
                     }*/
                }
            },
            {field: 'sex', title: '性别', width: 30, align: 'center'},
            {field: 'nation', title: '民族', width: 50},
            {field: 'placeOrigin', title: '籍贯', width: 180},
            {field: 'cardNo', title: '身份证号', width: 180},
            {field: 'administrativePost', title: '行政职务', width: 120, align: 'center'},
            {field: 'innerLine', title: '内线', width: 150, align: 'center'},
            {field: 'outLine', title: '外线', width: 150, align: 'center'},
            {field: 'zfLine', title: '政法线', width: 150, align: 'center'},
            {field: 'mobileNo', title: '手机号', width: 150, align: 'center'}

        ]],
        pagination: true,
        rownumbers: true,
        pageSize: 20,//每页显示的记录条数，默认为10
        pageList: [20, 30, 40],//可以设置每页记录条数的列表
        toolbar: [
        ]
    });
    var p_p = $('#employee').datagrid('getPager');
    $(p_p).pagination({
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

});



function initOrgTree() {
    $('#orgTree').tree({
        url: _context + "/smallowadmin/organ/orgTree",
        onBeforeLoad: function (node, param) {
            ajaxLoading("正在加载数据请稍后...");
        },
        onLoadSuccess: function (node, data) {
            ajaxLoadEnd();
        },
        onSelect: function (node) {
            $.post(_context + "/smallowadmin/organ/getOrgan", {id: node.id}, function (data) {
                dealOrganInfo(data);
            }, 'json');
            //alert(node.text);
            $('#employee').datagrid('load', {
                organId: node.id,
                organName:node.text
            });
        }

    });
}

function dealOrganInfo(data) {
    if (data && data != undefined) {
        var org = data.entity;
        $("#tb_org_name").html(org.orgName);
        $("#tb_org_short_name").html(org.orgShortName);
        $("#tb_org_create_time").html(org.createTime);
        var parent = data.parent;
        if (parent && parent != undefined) {
            $("#tb_parent_org_name").html(parent.orgName);
        }
        $("#tb_org_desc").html(org.orgDesc);
        $("#tb_org_code").val(org.orgCode);
        $("#selectOrganId").val(org.id);
        $("#selectOrganCode").val(org.orgCode);

    }
}


function ajaxLoading(msg) {
    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: $(window).height()
    }).appendTo("body");
    $(".datagrid-mask").attr("z-index",99999);
    if(msg==""){
        msg="正在加载数据,请稍后。。。";
    }
    $("<div class=\"datagrid-mask-msg\"></div>").html(msg).appendTo("body").css({
        display: "block",
        left: ($(document.body).outerWidth(true) - 190) / 2,
        top: ($(window).height() - 45) / 2
    });
}
function ajaxLoading1(obj,msg) {

    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: obj.height()
    }).appendTo(obj);
    $(".datagrid-mask").attr("z-index",99999);
    if(msg==""){
        msg="正在加载数据,请稍后。。。";
    }
    $("<div class=\"datagrid-mask-msg\"></div>").html(msg).appendTo(obj).css({
        display: "block",
        left: (obj.outerWidth(true) - 190) / 2,
        top: (obj.height() - 45) / 2
    });
}

function ajaxLoadEnd() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}