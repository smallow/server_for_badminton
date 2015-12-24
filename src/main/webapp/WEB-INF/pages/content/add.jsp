
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="addContent" class="easyui-dialog" title="发布信息" data-options="modal:true,closed:true,iconCls:'icon-save',buttons:'#addContent-buttons'" style="width:1200px;height:680px;padding:0px;">
  <form id="addContent-form" method="post" enctype="multipart/form-data">
    <table width="99.5%" border="0" cellpadding="0" cellspacing="0" id="tb_add_merchant">
      <tr>
        <td class="textTd">标题:</td>
        <td class="textInput" colspan="9"><input class="easyui-textbox" id="title" name="title" data-options="required:true" style="width:500px;height:24px"></td>

      </tr>
      <tr>
        <td class="textTd">原价:</td>
        <td class="textInput">
          <input class="easyui-numberbox" precision="2" id="originalPrice" name="originalPrice"style="width:100px;height:24px">
        </td>
        <td class="textTd">优惠价:</td>
        <td class="textInput">
          <input class="easyui-numberbox" precision="2" id="concessionalPrice" name="originalPrice"style="width:100px;height:24px">
        </td>
        <td class="textTd">是否需要预约:</td>
        <td class="textInput">
          <input type="radio" value="1" name="needAppointment"/>是
          <input type="radio" value="0" name="needAppointment"/>否
        </td>
        <td class="textTd">剩余件数:</td>
        <td class="textInput"><input class="easyui-textbox" id="leftNum" name="leftNum" style="width:100px;height:24px"></td>
        <td class="textTd">有效期:</td>
        <td class="textInput"><input id="effectDate" name="effectDate" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser" /></td>
      </tr>

      <tr>
        <td class="textTd">标题图:</td>
        <td class="textInput" colspan="2">
          <input type="file" name="file_upload_1" id="file_upload_1" /><a href="#" onclick="javascript:$('#file_upload_1').uploadify('upload','*')" data-options="disabled:true" id="startUpload" class="easyui-linkbutton">开始上传</a>
          <input type="hidden" id="titleImg" value="" />
          <input type="hidden" id="contentId" value="" />
        </td>
        <td colspan="7" id="titleImgDisplay" class="textInput">

        </td>
      </tr>
      <%--<tr>
        <td class="textInput" colspan="6" >
          <div style="width: 100%;height: 60px;">
            <div id="file_upload_1-queue" class="uploadify-queue"></div>
          </div>
        </td>
      </tr>--%>
      <tr>
        <td class="textInput" colspan="10">

          <textarea class="easyui-validatebox" id="Content" name="Content" style="width:100%;height: 100%;"></textarea>
        </td>
      </tr>
    </table>
  </form>
</div>
<div id="addContent-buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveContent()">保存</a>
  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addContent').dialog('close')">取消</a>
</div>
