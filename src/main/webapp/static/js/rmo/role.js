$(function(){
    initRoleList();
    initPriTree();
});


function initRoleList(){
    $("#role").datagrid({
        title: "岗位管理",
        url: _context + '/smallowadmin/role/roleList',
        loadMsg: '正在加载数据,请稍后...',
        columns: [[
            {field: 'id', title: '选择', width: 40, checkbox: true},
            {field: 'roleName', title: '岗位名称', width: 120,align: 'center',

                styler: function(value,row,index){

                }
            },
            {field: 'description', title: '描述', width: 300}

        ]],
        pagination: true,
        rownumbers: true,
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [10, 20, 30],//可以设置每页记录条数的列表
        toolbar: [
            {
                text: '添加岗位',
                iconCls: 'icon-add',
                handler: function () {
                    addRole();
                }
            }, '-',
            {
                text: '修改',
                iconCls: 'icon-edit',
                handler: function () {
                    editRole();
                }
            }, '-',
            {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    delRole();
                }
            }
        ],
        singleSelect:true,
        onCheck:function(index,row){
            getRolePri(row.roleCode);
        }
    });

    var p_p = $('#role').datagrid('getPager');
    $(p_p).pagination({
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
}
function initPriTree(){
    $("#priTree").tree(
        {
            url: _context + "/smallowadmin/role/priTree",
            checkbox:true,
            onSelect: function (node) {
                //alert(node.text);
            },
            onBeforeLoad: function (node, param) {
                ajaxLoading();
            },
            onLoadSuccess: function (node, data) {
                ajaxLoadEnd();
            }
        }
    );
}

function getRolePri(roleCode){
    $("#priTree").tree({
        queryParams:{roleCode:roleCode}
    });
}
function saveRolePri(){
    var objs= $("#role").datagrid("getChecked");
    if(objs.length!=1){
        $.messager.alert("提示","请选择一个岗位",'info');
        return false;
    }

    var selectedRoleCode=objs[0].roleCode;
    var selectedPris=[];
    var checkedPris=$("#priTree").tree('getChecked');
    $.each(checkedPris,function(index,node){
        var b = $('#priTree').tree('isLeaf', node.target);
        //alert(node.attributes.code+" " +b);
        if(b){
            selectedPris.push(node.attributes.code);
        }
    });

    var param={roleCode:selectedRoleCode,priCodes:selectedPris.join(",")};
    ajaxLoading("正在保存数据,请稍后。。。");
    $.post(_context+"/smallowadmin/role/saveRolePri",param,function(msg){
        if(msg.msg=='success'){
            $.messager.alert('提示','权限保存成功!','info');
            ajaxLoadEnd();
        }
    },'json');
}


function addRole(){
    $("#addRole").dialog('open');
    $("#addRole-form").form('clear');
    $("#updateSign").val("new");
}

function editRole(){
    var objs= $("#role").datagrid("getChecked");
    if(objs.length!=1){
        $.messager.alert("提示","请选择一个岗位",'info');
        return false;
    }

    var id=objs[0].id;
    $("#updateSign").val("edit");
    $("#roleId").val(id);
    $("#addRole").dialog('open');
    $("#form_role_name").textbox("setValue",objs[0].roleName);
    $("#form_role_code").textbox("setValue",objs[0].roleCode);
    $("#form_role_desc").val(objs[0].description);
}

function delRole(){
    var objs= $("#role").datagrid("getChecked");
    if(objs.length!=1){
        $.messager.alert("提示","请选择一个岗位",'info');
        return false;
    }

    var id=objs[0].id;
    var roleCode=objs[0].roleCode;

    $.messager.confirm('删除提示', '你确定删除下列数据吗'+'\n'+'岗位:'+objs[0].roleName, function(r){
        if(r){
            $.post(_context+"/smallowadmin/role/delRole",{id:id,roleCode:roleCode},function(msg){
                if(msg.success){
                    $.messager.alert('提示',"删除成功",'info');
                    $("#role").datagrid("reload");
                    var root = $('#priTree').tree('getRoot');
                    alert(root.length);
                    $("#priTree").tree("uncheck",root.target);
                }
            },'json');
        }
    });

}
function saveRole(){
    var cmd=$("#updateSign").val();
    var roleName=$("#form_role_name").val();
    var roleCode=$("#form_role_code").val();
    var description=$("#form_role_desc").val();
    var param={
        roleName:roleName,
        roleCode:roleCode,
        description:description,
        cmd:cmd
    };

    if(cmd=='edit'){
        param.id=$("#roleId").val();
    }

    $.post(_context+"/smallowadmin/role/saveRole",param,function(msg){
        if(msg.success){
            $.messager.alert('提示','岗位保存成功!','info');
            $("#addRole").dialog('close');
            $("#role").datagrid("reload");

        }
    },'json');
}



function ajaxLoading(msg) {
    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: $(window).height()
    }).appendTo("body");

    if(msg=="" || msg==null || msg==undefined){
        msg="正在加载数据,请稍后。。。";
    }
    $("<div class=\"datagrid-mask-msg\"></div>").html(msg).appendTo("body").css({
        display: "block",
        left: ($(document.body).outerWidth(true) - 190) / 2,
        top: ($(window).height() - 45) / 2
    });
}

function ajaxLoadEnd() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
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