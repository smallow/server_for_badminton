$(function () {
    //getRoles();
    initMainPanels();
    /*var themes = [
     {value:'default',text:'Default',group:'Base'},
     {value:'gray',text:'Gray',group:'Base'},
     {value:'metro',text:'Metro',group:'Base'},
     {value:'bootstrap',text:'Bootstrap',group:'Base'},
     {value:'black',text:'Black',group:'Base'},
     {value:'metro-blue',text:'Metro Blue',group:'Metro'},
     {value:'metro-gray',text:'Metro Gray',group:'Metro'},
     {value:'metro-green',text:'Metro Green',group:'Metro'},
     {value:'metro-orange',text:'Metro Orange',group:'Metro'},
     {value:'metro-red',text:'Metro Red',group:'Metro'},
     {value:'ui-cupertino',text:'Cupertino',group:'UI'},
     {value:'ui-dark-hive',text:'Dark Hive',group:'UI'},
     {value:'ui-pepper-grinder',text:'Pepper Grinder',group:'UI'},
     {value:'ui-sunny',text:'Sunny',group:'UI'}
     ];

     $('#cb-theme').combobox({
     groupField:'group',
     data: themes,
     editable:false,
     panelHeight:'auto',
     onChange:onChangeTheme
     onLoadSuccess:function(){
     $(this).combobox('setValue', 'default');
     }
     });*/
    initLeftPanels();
    //绑定右键关闭事件
    bindRightKey();
});
/**
 * 主题修改
 * @param theme
 */
function onChangeTheme(theme){
    // var link = $('#content').find('link:first');
    // link.attr('href', '/static/jquery/themes/'+theme+'/easyui.css');
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + theme + '/easyui.css';
    $easyuiTheme.attr('href', href);
    var iframes=$(".panel iframe");
    if(iframes.length>0){
        for(var i=0;i<iframes.length;i++){
            var ifr=iframes[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }
    $.cookie('easyuiThemeName', theme, {
        expires : 7
    });
    //var __cookieThemeName=$.cookie('easyuiThemeName');
    //alert("1放进去的是:"+__cookieThemeName);
}

//权限处理
function getRoles() {
    var obj = jQuery.parseJSON(_priJson);
    if(obj){
        var businessModule=obj.businessModule;
        var privilegeList=obj.privilegeList;
        //alert(businessModule.length);
        $.each(businessModule,function(index,node){
            var obj={
                code:node.code,
                name:node.name
            };
            //alert(node.code+" "+node.name);
            menus.push(obj);
        });
    }
}

function initLeftPanels(){
    getRoles();

    $.each(menus,function(index,obj){
        /*var menulist='<ul class="navlist">';
         $.each(obj.menus,function(_index,_obj){
         menulist+="<li><a id='"+_obj.id+"' style='cursor:hand;' onclick='addTabs(\""+_obj.name+"\",\""+_obj.url+"\",\""+_obj.icon+"\")' ><span class='icon "+_obj.icon+"' style='height:18px;'></span><span>"+_obj.name+"</span></a></li>";
         });
         menulist+="</ul>";*/

        var menuTree="<ul  id=menuTree_"+obj.code+"></ul>";
        $('#nav').accordion('add', {
            title: obj.name,
            content:menuTree,
            selected: false

        });

        var data=findBusinessModuleData(obj.code);
        var treeId="#menuTree_"+obj.code;
        var _priTree=$(treeId);
        _priTree.tree({
            data:data,
            animate:true,
            lines:true,
            onClick:function(node){
                //alert(node.attributes.url);
                if(node.attributes.url!="" && node.attributes.url!=null ){
                    addTabs(node.text,_context+node.attributes.url,'icon-group');
                }

            }
        });


    })
}

/**
 * 查找当前业务环节下的权限菜单
 * @param businessModuleCode
 * @returns {Array}
 */
function findBusinessModuleData(businessModuleCode){
    var obj = jQuery.parseJSON(_priJson);
    var data=[];
    if(obj){
        var privilegeList=obj.privilegeList;
        $.each(privilegeList,function(index,node){
            if(node.parentPriCode==businessModuleCode){
                var menu={
                    id:node.id,
                    text:node.priName,
                    attributes:{code:node.priCode,parentCode:node.parentPriCode,url:node.url}
                };
                recursion(node,menu,privilegeList);
                data.push(menu);
            }
        });
    }
    return data;
}

function recursion(privilege,map,privilegeList){
    var children=findChildren(privilege,privilegeList);
    var _children=[];
    if(children.length>0){
        $.each(children,function(index,node){
            var obj={
                id:node.id,
                text:node.priName,
                attributes:{code:node.priCode,parentCode:node.parentPriCode,url:node.url}
            };
            _children.push(obj);
            recursion(node,obj,privilegeList);
        });
    }
    map.children=_children;
}

function findChildren(privilege,privilegeList){
    var children=[];
    $.each(privilegeList,function(index,node){
        if(node.parentPriCode==privilege.priCode)
            children.push(node);
    });
    return children;
}
//面板初始化
function initMainPanels() {
    var mainTabs = $('#tabs')
        .tabs({
            border: false,
            onSelect: function (title) {
                //alert(title + ' is selected');
            },
            fit:true,
            pill:true
        });
}
function addTabs(subtitle, url, icon){
    if(!$('#tabs').tabs('exists', subtitle)){
        //如果是打开新tab
        $('#tabs').tabs('add',{
            title : subtitle,
            content : createFrame(url),
            closable : true,
            iconCls: icon

        })

    }else{
        //如果是已经打开
        $('#tabs').tabs('select', subtitle);
    }
}

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
        + '" style="width:100%;height:100%;"></iframe>';
    return s;
}


function addMerchant(){
    var name="南阳生炝烩面国际陆店";
    var address="河南省郑州市金水区国际陆";
    var regno="78562220";
    var bigtype="40001";
    var midtype="40001001";
    var pwd="123456";
    var obj={
        name:name,
        address:address,
        regno:regno,
        bigtype:bigtype,
        midtype:midtype,
        pwd:pwd,
        cmd:'new'
    };
    $.post(_context+'/merchant/save',obj,function(msg){
        alert(msg.success);
    },'json');


}

function zhuxiao(){
    $.post(_context+"/quit.jsp",{},function(data){
        window.location.href="/login.htm";
    });
}

function bindRightKey(){
    //监听右键事件，创建右键菜单
    $('#tabs').tabs({
        onContextMenu:function(e, title,index){
            e.preventDefault();
            if(index>0){
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        }
    });
    //右键菜单click
    $("#mm").menu({
        onClick : function (item) {
            closeTab(this, item.name);
        }
    });
}

//删除Tabs
function closeTab(menu, type){
    var allTabs = $("#tabs").tabs('tabs');
    var allTabtitle = [];
    $.each(allTabs,function(i,n){
        var opt=$(n).panel('options');
        if(opt.closable)
            allTabtitle.push(opt.title);
    });

    switch (type){
        case "1" :
            var curTabTitle = $(menu).data("tabTitle");
            $("#tabs").tabs("close", curTabTitle);
            return false;
            break;
        case "2" :
            for(var i=0;i<allTabtitle.length;i++){
                $('#tabs').tabs('close', allTabtitle[i]);
            }
            break;
        case "3" :
            var curTabTitle = $(menu).data("tabTitle");
            for(var i=0;i<allTabtitle.length;i++){
                var tt=allTabtitle[i];
                if(tt!=curTabTitle){
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
            }
            break;
        case "4" :
            var curTabTitle = $(menu).data("tabTitle");
            var isDel=false;
            for(var i=0;i<allTabtitle.length;i++){
                var tt=allTabtitle[i];
                if(isDel){
                    $('#tabs').tabs('close', allTabtitle[i]);
                }
                if(tt==curTabTitle){
                    isDel=true;
                }
            }
            break;
        case "5" :
            var curTabTitle = $(menu).data("tabTitle");
            for(var i=0;i<allTabtitle.length;i++){
                var tt=allTabtitle[i];
                if(tt!=curTabTitle){
                    $('#tabs').tabs('close', allTabtitle[i]);
                }else{
                    break;
                }
            }
            break;
    }

}
