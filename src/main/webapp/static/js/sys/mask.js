function ajaxLoading(msg) {
    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: $(window).height()
    }).appendTo("body");
    $(".datagrid-mask").attr("z-index",99999);

    if(msg=="" || msg==undefined || msg==null){
        msg="正在加载数据，请稍候。。。";
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