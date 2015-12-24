$(function () {
    initOrgTree();

    $("#employee").datagrid({
        title: "人员管理",
        url: _context + '/smallowadmin/organ/getEmployee',
        loadMsg: '正在加载数据,请稍后...',
        columns: [[
            {field: 'empInfoId', title: '选择', width: 40, checkbox: true},
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
            {field: 'birthday', title: '出生年月', width: 120, align: 'center'},
            {field: 'telephone', title: '办公电话', width: 150, align: 'center'},
            {field: 'mobileNo', title: '手机号', width: 150, align: 'center'}

            /*{field: 'sfUser', title: '人员状态', width: 60, align: 'center',
                formatter:function(val,row){
                    if(val){
                        return "操作员";
                    }  else{
                        return "普通职员";
                    }
                }

            }*/
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
            /*{
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    delEmployee();
                }
            }, '-',*/
            {
                text: '操作员维护',
                iconCls: 'icon-edit',
                handler: function () {
                    addUser();
                }
            },
            '-',
            {
                text: '岗位分配',
                iconCls: 'icon-edit',
                handler: function () {
                    roleAssign();
                }
            },
            '-',
            {
                text: '岗位分配记录跟踪',
                iconCls: 'icon-edit',
                handler: function () {
                    userRoleAssignLog();
                }
            },
            '-',
            {
                text: '手机号:<input class="easyui-textbox" id="query_telephone"/>'
            },
            {
                text: '查询',
                iconCls: 'icon-search',
                handler: function () {
                    var telephone = $('#query_telephone').val();
                    var param = {telephone: telephone};
                    $("#employee").datagrid("load", param);
                }
            }
        ]
    });
    var p_p = $('#employee').datagrid('getPager');
    $(p_p).pagination({
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    
    
    
    $("#roleAssignLog").datagrid({
	 	title: "人员管理",
        url: _context + '/smallowadmin/organ/getUserRoleAssignLog',
        loadMsg: '正在加载数据,请稍后...',
        columns: [[
            {field: 'userName', title: '姓名', width: 100,align: 'center'},
            {field: 'operateName', title: '操作员姓名', width: 100, align: 'center'},
            {field: 'assignTime', title: '分配时间', width: 150},
            {field: 'roleName', title: '岗位集合', width: 180},
            {field: 'assignReason', title: '备注', width: 400}
        ]],
        pagination: true,
        rownumbers: true,
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [10, 20, 30],//可以设置每页记录条数的列表
 });
    
    var p_p2 = $('#roleAssignLog').datagrid('getPager');
    $(p_p2).pagination({
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
            //ajaxLoading();
            $.post(_context + "/smallowadmin/organ/getOrgan", {id: node.id}, function (data) {
                dealOrganInfo(data);
               // ajaxLoadEnd();
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


function addOrgan(){
    var selectOrganId=$("#selectOrganId").val();
    if(selectOrganId==""){
        $.messager.alert("提示","请选择一个机构节点!","info");
        return false;
    }
    $("#addOrgan").dialog('open');
    $("#addOrgan-form").form('clear');
    $("#updateSign").val("new");
    $("#form_org_code").textbox("enable");
}
function saveOrgan(){
    var updateSign=$("#updateSign").val();
    var selectOrganId=$("#selectOrganId").val();
    var selectOrganCode=$("#selectOrganCode").val();
    if(selectOrganId=="" || selectOrganCode==""){
        $.messager.alert("提示","请选择一个上级机构节点!",'info');
        return false;
    }
    var orgName=$("#form_org_name").val();
    var orgCode=$("#form_org_code").val();
    if(orgName=="" || orgCode==""){
        $.messager.alert("提示","机构名称和代码都不能为空!",'info');
        return false;
    }

    var orgShortName=$("#form_org_short_name").val();

    var orgDesc=$("#form_org_desc").val();
    //alert(orgDesc);
    //return false;
    var param={
        cmd:updateSign,
        orgName:orgName,
        orgCode:orgCode,
        parentCode:selectOrganCode,
        orgShortName:orgShortName,
        orgDesc:orgDesc
    }
    if(updateSign=="edit"){
        param.id=selectOrganId;
    }
    ajaxLoading("正在保存数据,请稍后。。。");
    $.post(_context+"/smallowadmin/organ/saveOrgan",param,function(msg){
        if(msg.success){
            $.messager.alert("提示","机构创建成功!",'info');
            $("#addOrgan").dialog('close');
            $("#orgTree").tree("reload");
            if(updateSign=="edit"){
                $("#tb_org_name").html(orgName);
            }
            ajaxLoadEnd();
        }
    },'json');
}

function editOrgan(){
    var selectOrganId=$("#selectOrganId").val();
    if(selectOrganId==""){
        $.messager.alert("提示","请选择一个机构节点!","info");
        return false;
    }
    $("#addOrgan").dialog('open');
    $("#addOrgan-form").form('clear');
    $("#updateSign").val("edit");
    $("#form_org_name").textbox("setValue",$("#tb_org_name").html());
    $("#form_org_code").textbox("setValue",$("#tb_org_code").val());
    $("#form_org_code").textbox("disable");
    $("#form_org_short_name").textbox("setValue",$("#tb_org_short_name").html());
    $("#form_org_desc").val($("#tb_org_desc").html());

}

function delOrgan(){
    var selectOrganId=$("#selectOrganId").val();
    if(selectOrganId==""){
        $.messager.alert("提示","请选择一个机构节点!","info");
        return false;
    }
    $.messager.confirm('删除提示', '你确定删除下列数据吗'+'\n'+'机构:'+$("#tb_org_name").html(), function(r){
        if(r){
            $.post(_context+"/smallowadmin/organ/delOrgan",{id:selectOrganId},function(msg){
                if(msg.success){
                    $.messager.alert('提示',"删除成功",'info');
                    $("#orgTree").tree("reload");
                    $("#tb_org_name").html("");
                    $("#tb_org_code").html("");
                    $("#tb_org_create_time").html("");
                    $("#tb_parent_org_name").html("");

                }
            },'json');
        }
    });

}



function addEmployee(){
    var selectOrganId=$("#selectOrganId").val();
    if(selectOrganId==""){
        $.messager.alert("提示","请选择一个机构节点!","info");
        return false;
    }
    $("#addEmployee").dialog('open');
    $("#addEmployee-form").form('clear');
    $("#employeeUpdateSign").val("new");
    $("#form_employee_orgName").html($("#tb_org_name").html());
}

function editEmployee(){
    var selectOrganId=$("#selectOrganId").val();
    if(selectOrganId==""){
        $.messager.alert("提示","请选择一个机构节点!","info");
        return false;
    }

    var employee=$("#employee").datagrid("getChecked");
    if(employee.length!=1){
        $.messager.alert("提示","请选择一条数据进行修改",'info');
        return false;
    }
    $("#addEmployee").dialog('open');
    $("#addEmployee-form").form('clear');
    var id=employee[0].id;
    var obj={id:id};

    $.post(_context+"/smallowadmin/organ/editEmployee",obj,function(data){
        $("#form_employee_name").textbox("setValue",data.name);
        //alert(data.sex);
        $("input[name='form_employee_sex'][value='"+data.sex+"']").prop('checked',true);
        $("#form_employee_age").textbox("setValue",data.age);
        $("#form_employee_telephone").textbox("setValue",data.telephone);
        $("#form_employee_mobile").textbox("setValue",data.mobile);
        $("#employeeUpdateSign").val("edit");
        $("#form_employee_orgName").html($("#tb_org_name").html());
    },'json');
}


function saveEmployee(){
    var selectOrganId=$("#selectOrganId").val();
    if(selectOrganId==""){
        $.messager.alert("提示","请选择一个机构节点!","info");
        return false;
    }

    var name=$("#form_employee_name").val();
    var sex=$("input[name='form_employee_sex']:checked").val();
   // alert(sex);
    var age=$("#form_employee_age").val();
    var telephone=$("#form_employee_telephone").val();
    var mobile=$("#form_employee_mobile").val();

    var employeeUpdateSign=$("#employeeUpdateSign").val();
    var param={
        name:name,
        sex:sex,
        age:age,
        telephone:telephone,
        mobile:mobile,
        orgId:selectOrganId,
        cmd:employeeUpdateSign,
        sfUser:false
    };
    if(employeeUpdateSign=="edit"){
        var employee=$("#employee").datagrid("getChecked");
        var id=employee[0].id;
        param.id=id;
    }

    ajaxLoading1($("#addEmployee"),"正在保存数据,请稍后。。。");
    $.post(_context+"/smallowadmin/organ/saveEmployee",param,function(msg){
        if(msg.success){
            $.messager.alert('提示','人员添加成功','info');
            $("#addEmployee").dialog('close');
            $("#employee").datagrid("reload");
            ajaxLoadEnd();
        }
    },'json');

}


function delEmployee(){
    var employees=$("#employee").datagrid("getChecked");
    if(employees.length<1){
        $.messager.alert("提示","请选择至少一条数据进行删除",'info');
        return false;
    }
    var ids="";
    var names="";
    $.each(employees,function(index,obj){
        ids+=obj.empInfoId+",";
        names+=obj.name+",";
    });
//alert(ids);
    //return false;
    $.messager.confirm('删除提示', '你确定删除下列数据吗'+'\n'+'人员:'+names, function(r){
        if(r){
            //alert();
            ajaxLoading("正在删除数据,请稍后。。。");
            $.post(_context+"/smallowadmin/organ/delEmployee",{ids:ids},function(msg){
                if(msg.msg=='success'){
                    $.messager.alert('提示','数据删除成功!','info');
                    $("#employee").datagrid("reload");
                    ajaxLoadEnd();
                }
            },'json');
        }
    });


}

function addUser(){
    var employee=$("#employee").datagrid("getChecked");
    if(employee.length!=1){
        $.messager.alert("提示","请选择一条数据进行修改",'info');
        return false;
    }

    $("#addUser").dialog('open');
    $("#addUser-form").form('clear');

    $.post(_context+"/smallowadmin/organ/editUser",{employeeId:employee[0].empInfoId},function(data){
        if(data && data!=undefined&& data.id){
            $("#form_user_code").textbox("setValue",data.loginCode);
            $("#form_user_code").textbox("disable");
            $("#form_user_pwd").textbox("setValue","");
            $("#form_confirm_pwd").textbox("setValue","");
            $("#userUpdateSign").val("edit");
            $("#userId").val(data.id);
            //alert($("#userUpdateSign").val());
        }else{
            $("#userUpdateSign").val("new");
           // alert($("#userUpdateSign").val());
            $("#form_user_code").textbox("enable");
            $("#userId").val("");
        }
    },'json');

    $("#form_user_name").html(employee[0].name);
    $("#employeeId").val(employee[0].empInfoId);
}

function saveUser(){
    var employeeId=$("#employeeId").val();
    if(employeeId==""){
        return false;
    }
    var loginCode=$("#form_user_code").val();
    var pwd=$("#form_user_pwd").val();
    var confirm_pwd=$("#form_confirm_pwd").val();
    if(pwd!=confirm_pwd){
        $.messager.alert('提示','两次密码不一致',"提示");
        return false;
    }
    var cmd=$("#userUpdateSign").val();
   // alert($("#userUpdateSign").val());
    var param={
        loginCode:loginCode,
        pwd:pwd,
        employeeId:employeeId,
        cmd:cmd
    }
    if(cmd=="edit"){
        param.id=$("#userId").val();
    }
    ajaxLoading1($("#addUser"),"正在保存数据,请稍后。。。");
    $.post(_context+"/smallowadmin/organ/saveUser",param,function(msg){
        if(msg.success){
            $.messager.alert("","操作员变更成功!",'info');
            $("#addUser").dialog('close');
            $("#employee").datagrid("reload");
            ajaxLoadEnd();
        }
    },'json');
}

function roleAssign(){
    var employee=$("#employee").datagrid("getChecked");
    if(employee.length!=1){
        $.messager.alert("提示","请选择一个操作员角色进行岗位分配",'info');
        return false;
    }
    /*if(!employee[0].sfUser){
        $.messager.alert("提示","非操作员不能分配岗位",'info');
        return false;
    }*/




    $("#roleAssign").dialog("open");
    $("#roleList").html("");
    $("#form_assign_reason").val("");
    
    /*$.messager.progress({
        title:'请稍后',
        msg:'页面加载中...'
    });*/
    ajaxLoading1($("#roleAssign"),'');
    $.post(_context+"/smallowadmin/role/getRoleByUserId",{employeeId:employee[0].empInfoId},function(data){
        if(data){
            if(data.msg=='faile'){
                ajaxLoadEnd();
                $.messager.alert('提示','非操作员不能进行岗位分配','info');
                $("#roleAssign").dialog("close");
                return false;
            }


            var str="";
            var allRoles=data.allRoles;
            var userRoles=data.userRoles;
            $.each(allRoles,function(index,role){
                if(roleCheck(role,userRoles)){
                    str+="<li><input name='roleCode' value='"+role.roleCode+"' type='checkbox' checked='checked' ><font color='red'>"+role.roleName+"</font></li>";
                }else{
                    str+="<li><input name='roleCode' value='"+role.roleCode+"' type='checkbox' >"+role.roleName+"</li>";
                }
            });
            //alert(str)

            $("#roleList").html(str);
            //$.messager.progress('close');
            ajaxLoadEnd();

        }
    },'json');

    //$('input[name="roleCode"]:checkbox').attr('checked','false');
}


function saveRoleAssign(){
    var data="";
    $('input[name="roleCode"]:checked').each(function(){
        data+=$(this).val()+",";
    })

    data=data.replace(/,$/gi,"");
    var params = {
        //userId :$("#employee").datagrid("getChecked")[0].userId,
        employeeId:$("#employee").datagrid("getChecked")[0].empInfoId,
        roles : data,
        operateName:_operateName,
        assignReason:$("#form_assign_reason").val()
    };
    ajaxLoading1($("#roleAssign"),'正在保存数据,请稍后。。。');
    $.post(_context+"/smallowadmin/organ/saveUserRole",params,function(msg){
        if(msg.msg=='success'){
            $.messager.alert('提示','岗位分配成功!','info');
        }
        $("#roleAssign").dialog("close");
        ajaxLoadEnd();
    },'json');

}

function roleCheck(allRolesItem,userRoles){
   var b=false;
    $.each(userRoles,function(index,role){
        //alert(allRolesItem.roleCode==role.roleCode);
        if(allRolesItem.roleCode==role.roleCode){
           b=true;
            return false;
        }
    });
    return b;
}

function userRoleAssignLog(){
	var employeeId=$("#employee").datagrid("getChecked")[0].empInfoId;
	$("#roleAssignLogWindow").dialog("open");
	$('#roleAssignLog').datagrid('load', {
		employeeId: employeeId
    });
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