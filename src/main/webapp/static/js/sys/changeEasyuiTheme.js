function changeThemeFun(themeName) {/* 更换主题 */
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr('href', href);

    var $iframe = $(".panel iframe");
    if ($iframe.length > 0) {
        for ( var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }

    /*$.cookie('easyuiThemeName', themeName, {
        expires : 7
    });*/
    //var __cookieThemeName=$.cookie('easyuiThemeName');
    //alert("2放进去的是"+__cookieThemeName);

};
var _cookieThemeName=$.cookie('easyuiThemeName');
//alert("获取到的是:"+_cookieThemeName);
if (_cookieThemeName) {
    changeThemeFun(_cookieThemeName);
}