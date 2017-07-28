<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>社区发布团购商品</title>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
	
	<link rel="stylesheet" href="${ctx}/kindeditor/themes/default/default.css"  type="text/css"/>
	<link rel="stylesheet" href="${ctx}/kindeditor/kindeditor_plugins/prettify.css"  type="text/css"/>
	<script charset="utf-8" src="${ctx}/kindeditor/js/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${ctx}/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${ctx}/kindeditor/kindeditor_plugins/code/prettify.js"></script>
</head>
<body>
	<div id="tb" style="padding: 5px; height: auto;">
		<div>
			<form id="mainform"  action="${ctx}/goodsManager/add" method="post" enctype="multipart/form-data">
			<input type="hidden" id="picUrl" name="picUrl"/>
			<table class="formTable" style="margin-left: 0; line-height: 3;">
					<tr>
						<td style="width: 150px;" align="right"><span style="color: red;">*</span>商品标题：</td>
						<td colspan=5><input type="text" id="name"
							name="name" class="easyui-validatebox" maxlength="40"
							data-options="width:300,required:true,validType:'length[1,40]'" />
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right">商品副标题：</td>
						<td colspan=5><input type="text" id="alias"
							name="alias" class="easyui-validatebox" maxlength="40"
							data-options="width:300,validType:'length[0,40]'" /> 
							
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"> 
						<span style="color: red;">*</span>是否允许单人团：</td>
						<td colspan=5>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input  type="radio" name="singleBuy" value="0" checked=checked"/>&nbsp;否 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input  type="radio" name="singleBuy" value="1"/>&nbsp;是 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span style="color: red;margin-left:20">注：开启单人团后，用户可以以市场价直接单独购买</span>
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"><span style="color: red;">*</span>市场价： </td>
						<td colspan=5>
							<input  type="text"	id="marketPrice" name="marketPrice" class="easyui-validatebox" maxlength="10"
								data-options="width:300,required:true,validType:'currency'" /> 元
							<span style="color: red;">注：开启单人团后，用户可以以市场价直接单独购买</span>
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"> <span style="color: red;">*</span>团购价：</td>
						<td colspan=5><input  type="text"
							id="groupPrice" name="groupPrice" class="easyui-validatebox" maxlength="10"
							data-options="width:300,required:true,validType:'currency'" /> 元
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right">
						<span style="color: red;">*</span>开团数量：
						</td>
						<td colspan=5>
						<input  type="text"
							id="num" name="num" class="easyui-validatebox" maxlength="10"
							data-options="width:300,required:true,validType:'integer',prompt: '满多少开团'" />
							 <span style="color: red;">注：一个用户一次性买10件，则拼团数量增加10</span> 
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"> <span style="color: red;">*</span>拼团时间：</td>
						<td colspan=5>
						  <input id="startTime" name="startTime" type="text"  class="Wdate" 
						 onFocus="var startDate=getStartDate();var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}',minDate:startDate,disabledDates:['#F{setDateMinutes()}'],startDate:startDate,dateFmt:'yyyy-MM-dd HH:mm',hmsMenuCfg:{H:[1,6],m:[30,1],s:[15,4]}})"
		                 readOnly="readOnly"/>
						至：<input id="endTime" name="endTime" type="text"  class="Wdate"
						 onFocus="var startDate=getStartDate();WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',disabledDates:['#F{setDateMinutes()}'],startDate:startDate,dateFmt:'yyyy-MM-dd H:mm',hmsMenuCfg:{H:[1,6],m:[30,1],s:[15,4]}})"
						  readOnly="readOnly"/>
						</td>
					</tr>
					<!-- 图片上传开始 -->
					<tr>
						<td style="width: 150px;" align="right" ><span style="color: red;">*</span>商品图片： </td>
						<td style="width: 80px; margin-left: 10; line-height: 3;margin-right:10;border:1px dashed">
						<div >
							<img  width="65px" height="65px" src="${ctx}/crop/images/UploadLight.png?imageView2/0/w/65/h/65"></img>
							<span style="text-align:left;display:block;">
							   <input type="button" value="上传图片"  class="upload-input-btn" style="width: 65px"/>
							   <input type="file" name="inputFile" value="" accept="image/jpg,image/jepg,image/png" onchange="checkImage(this)" style="width: 10px;display:none" />
							   <input type="button" value="删除图片"  class="del" style="width: 65px;display:none"/>
							</span>
						</div>
						</td>
						<td  style="width:80px;margin-left: 10; line-height: 3;margin-right:10;border:1px dashed">
						<div>
							<img  width="65px" height="65px" src="${ctx}/crop/images/UploadLight.png?imageView2/0/w/65/h/65"></img>
							<span style="text-align:left;display:block;">
							   <input type="button" value="上传图片"  class="upload-input-btn" style="width: 65px"/>
							   <input type="file" name="inputFile" value="" accept="image/jpg,image/jepg,image/png" onchange="checkImage(this)" style="width: 10px;display:none" />
							   <input type="button" value="删除图片"  class="del" style="width: 65px;display:none"/>
							</span>
						</div>
						</td>
						<td style="width: 80px; margin-left: 10; line-height: 3;margin-right:10;border:1px dashed">
						<div >
							<img  width="65px" height="65px" src="${ctx}/crop/images/UploadLight.png?imageView2/0/w/65/h/65"></img>
							<span style="text-align:left;display:block;">
							   <input type="button" value="上传图片"  class="upload-input-btn" style="width: 65px"/>
							   <input type="file" name="inputFile" value="" accept="image/jpg,image/jepg,image/png" onchange="checkImage(this)" style="width: 10px;display:none" />
							   <input type="button" value="删除图片"  class="del" style="width: 65px;display:none"/>
							</span>
						</div>
						</td>
						<td style="width: 80px; margin-left: 10; line-height: 3;margin-right:10;border:1px dashed">
						<div >
							<img  width="65px" height="65px" src="${ctx}/crop/images/UploadLight.png?imageView2/0/w/65/h/65"></img>
							<span style="text-align:left;display:block;">
							   <input type="button" value="上传图片"  class="upload-input-btn" style="width: 65px"/>
							   <input type="file" name="inputFile" value="" accept="image/jpg,image/jepg,image/png" onchange="checkImage(this)" style="width: 10px;display:none" />
							   <input type="button" value="删除图片"  class="del" style="width: 65px;display:none"/>
							</span>
						</div>
						</td>
						<td style="width: 80px; margin-left: 10; line-height: 3;margin-right:10;border:1px dashed">
						<div >
							<img  width="65px" height="65px" src="${ctx}/crop/images/UploadLight.png?imageView2/0/w/65/h/65"></img>
							<span style="text-align:left;display:block;">
							   <input type="button" value="上传图片"  class="upload-input-btn" style="width: 65px"/>
							   <input type="file" name="inputFile" value="" accept="image/jpg,image/jepg,image/png" onchange="checkImage(this)" style="width: 10px;display:none" />
							   <input type="button" value="删除图片"  class="del" style="width: 65px;display:none"/>
							</span>
						</div>
						</td>
					</tr>
					<!-- 图片上传结束 -->
					<tr>
						<td></td>
						<td >
						<span style="text-align:left;display:block;color: #B6B6B6;">请上传每张图片1M以内的jpg、png格式的图片 建议使用宽600像素*高600像素图片 </span>
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"><h2>图文详情：</h2></td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"></td>
						<td>
							<textarea name="content" id="content" cols="30" readonly="readonly" rows="8" style="width:400px;height:300px;"
							 data-options="required:'required',validType:'length[2,50000]'"></textarea>
						</td>
					</tr>
				</table>
				
			</form>
		</div>

	</div>
<script type="text/javascript">

//发布团购商品和取消 begin
$('#mainform').form({    
    onSubmit: function(){ 
    	debugger
    	$('#add_comfirm').linkbutton('disable');
    	var isValid = $(this).form('validate');
    	if(!isValid){
    		$('#add_comfirm').linkbutton('enable');
    	}
    	//富文本编辑内容 非必填
    	$("#content").val(editor.text());
    	//获取图片
    	var inputFile =""; 
    	$("input[name='inputFile']").each(  
   			function(){  
   				inputFile =inputFile+$(this).val()+",";  
   			}  
  		)   
   		//按照上面的拼接规则 只有5个逗号时未上传图片
    	if(inputFile.length == 5){
    		$.messager.alert("商品图片不能为空");
    		return false;
    	}else{
    		
    	}
    	$("#picUrl").val(inputFile);
 	    return isValid;
    },    
    success:function(data){ 
    	$('#add_comfirm').linkbutton('enable');
    	if(successTip(data,dg,d))
    		dg.datagrid('reload'); 
    }    
});
//发布团购商品和取消 end 

	// 图片操作事件 begin
	$(document).ready(function() {
		$(".upload-input-btn").click(function(){
			$(this).next().click();
		});
		
		$(".del").click(function(){
			$(this).parent().prev().attr("src","${ctx}/crop/images/UploadLight.png?imageView2/0/w/65/h/65");
			$(this).prev().attr("value","");
			$(this).hide();
			$(this).prev().prev().show();
		});
	});
		
	//图片上传格式大小检测 begin
	 var flag = true;
	 function checkImage(file) {
		 
		 if(file.value == '') {
	  		$(file).next().attr("src","").hide();
	  		$(file).attr("value","");
	  		return false;
  		 }else{
  			var filesize = file.files[0].size;
	  		if (!/.(jpg|jpeg|png)$/.test(file.value)) {
	  			$.messager.alert('请上传jpeg、jpg、png格式的图片!');
	  			flag = false;
	  			return;
	  		}
	  		if(filesize > 1024 * 1024){
				$.messager.alert("上传的图片大小要求不能超过1M");  
				flag = false;
            	return; 
			} 
	  		var uploadImg = new Image();
	  		uploadImg.src = getFullPath(file);
	  		$(file).parent().prev().attr("src",uploadImg.src);
			setTimeout(function(){
				var width_img=uploadImg.width;   
				var height_img=uploadImg.height;
				//图片比例
				var value= width_img/height_img;
				 if(value != 1){
					$.messager.alert("上传的图片宽与高比例需符合1:1");  
					$(file).prev().attr("src","").hide();
					$(file).val("");
					flag = false;
	            	return; 
				}
				$(file).attr("value",$(file).val());
				$(file).prev().hide();
				$(file).next().show();
			},100);
	  		flag = true;
  		}
	 }
	//图片操作事件 end
	
	//富文本编辑begin
	var  editor = KindEditor.create('#content', {
	cssPath : '${ctx}/easyui-kindeditor/plugins/code/prettify.css',
	uploadJson : '${ctx}/easyui-kindeditor/jsp/upload_json.jsp',
	fileManagerJson : '${ctx}/easyui-kindeditor/jsp/file_manager_json.jsp',
	allowFileManager : true,
	readonlyMode:false,
	afterCreate : function() {
		var self = this;
		KindEditor.ctrl(document, 13, function() {
			self.sync();
			document.forms['example'].submit();
		});
		KindEditor.ctrl(self.edit.doc, 13, function() {
			self.sync();
			document.forms['example'].submit();
		});
	}
});
//富文本编辑end

// 货币和整数 数据校验
$.extend($.fn.validatebox.defaults.rules, {    
    currency : {// 验证货币
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '货币格式不正确'
    },
    integer : {// 验证整数
        validator : function(value) {
            return /^[+]?[1-9]+\d*$/i.test(value);
        },
        message : '请输入整数'
    }
});
</script>
</body>
</html>