<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>社区发布团购商品</title>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
	<link rel="stylesheet" href="${ctx}/js/editor/easyui-kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${ctx}/js/editor/easyui-kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${ctx}/js/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${ctx}/js/editor/easyui-kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${ctx}/js/editor/easyui-kindeditor/plugins/code/prettify.js"></script>
</head>

<body>
	<div id="tb" style="padding: 5px; height: auto;">
		<div>
			<form id="addForm"  action="${ctx}/groupBuy/add" method="post" enctype="multipart/form-data">
			<table class="formTable" style="margin-left: 0; line-height: 3;">
					<tr>
						<td style="width: 150px;" align="right"><span style="color: red;">*</span>商品标题：</td>
						<td colspan= "3"><input type="text" id="name"
							name="name" class="easyui-validatebox" maxlength="40"
							data-options="width:300,required:true,validType:'length[1,40]'" />
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right">商品副标题：</td>
						<td colspan= "3"><input type="text" id="alias"
							name="alias" class="easyui-validatebox" maxlength="40"
							data-options="width:300,validType:'length[0,40]'" /> 
							
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"> 
						<span style="color: red;">*</span>是否允许单人团：</td>
						<td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input  type="radio" name="singleBuy" value="0"/>&nbsp;否 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input  type="radio" name="singleBuy" value="1"/>&nbsp;是 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span style="color: red;margin-left:20">注：开启单人团后，用户可以以市场价直接单独购买</span>
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"><span style="color: red;">*</span>市场价： </td>
						<td><input  type="text"
							id="marketPrice" name="marketPrice" class="easyui-validatebox" maxlength="10"
							data-options="width:300,required:true,validType:'currency'" /> 元
						<span style="color: red;">注：开启单人团后，用户可以以市场价直接单独购买</span>
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"> <span style="color: red;">*</span>团购价：</td>
						<td><input  type="text"
							id="groupPrice" name="groupPrice" class="easyui-validatebox" maxlength="10"
							data-options="width:300,required:true,validType:'currency'" /> 元
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right">
						<span style="color: red;">*</span>开团数量：
						</td>
						<td>
						<input  type="text"
							id="num" name="num" class="easyui-validatebox" maxlength="10"
							data-options="width:300,required:true,validType:'integer',prompt: '满多少开团'" />
							 <span style="color: red;">注：一个用户一次性买10件，则拼团数量增加10</span> 
						</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"> <span style="color: red;">*</span>拼团时间：</td>
						<td colspan="4">
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
						<td style="margin-left: 10; line-height: 3;margin-right:10;border:1px dashed" >
						<div id="preview">
							<img id="img" width="65px" height="65px" src="${ctx}/crop/images/UploadLight.png?imageView2/0/w/65/h/65"></img>
							<span style="text-align:left;display:block;">
							   <input type="button" value="上传图片" id="addImages" class="upload-input-btn"/>
							   <input type="file" name="inputFile" id="inputFile" value="${columnOperation.picUrl}" accept="image/jpg,image/jepg,image/png" onchange="checkImage(this)" style="width: 10px;" />
							</span>
						</div>
						</td>
					</tr>
					
					<tr>
					<td></td>
					<td >
					<span style="text-align:left;display:block;color: #B6B6B6;">(支持格式:Jpeg,Jpg,Png 小于200K);</span>
					
					</td>
					</tr>
					<tr>
					<td></td>
					<td >
					<span style="text-align:left;display:block;color: #B6B6B6;">请上传每张图片1M以内的jpg、png格式的图片 建议使用宽0像素*高600像素图片 </span>
					</td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"><h2>图文详情：</h2></td>
					</tr>
					<tr>
						<td style="width: 150px;" align="right"></td>
						<td>
						<textarea name="context" id="context" cols="30" readonly="readonly" rows="8" style="width:400px;height:300px;"
					 		data-options="required:'required',validType:'length[2,50000]'">
					 	</textarea>
						</td>
					</tr>
					<!-- <tr >
						<td style="width: 150px;" align="right"></td>
						<td >
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
								onclick="cannel();">取消</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" 
								onclick="add_button();">提交</a>
						</td>
					</tr> -->
				</table>
				
			</form>
		</div>

	</div>
	
	<script type="text/javascript">
	var  editor = KindEditor.create('#context', {
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
	function add_button(){
		//富文本编辑
		$("#context").val(editor.text());
		$('#addForm').submit();
	}

	function cannel(){
		$('#addForm').attr("action","${ctx }/store/storeProtocol/view");
		$('#addForm').submit();
	}
	
	
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
	 
	//图片上传格式大小检测
	 var scale = 0;
	 var minWidth = 0;
	 var minHeight = 0;
	 var flag = true;
	 var type="";
	 function checkImage(file) {
		 debugger
	 	var filesize = file.files[0].size;
	 	if (!/.(jpg|jpeg|png)$/.test(file.value) || filesize > 1024 * 1024) {
	 		$.messager.alert('请上传1M以内jpeg、jpg、png格式中的图片!');
	 		flag = false;
	 		return;
	 	}
	 }
	 var previewImageBeforeUploadModule = (function() {
			
			var options = null;
			
			var init = function(opt) {
				if (opt.previewClass === undefined)		opt.previewClass = "fileUploadPreview";
				if (opt.maxFiles === undefined)			opt.maxFiles = 1;
				//if (opt.thumbnailWidth === undefined)	opt.thumbnailWidth = "auto";
				options = opt;
				
				$(options.inputSelector).css("display", "none");
				$(options.inputSelector).change(function() { preview(this); });
				$(options.clickableSelector).on("click", function() { $(options.inputSelector).trigger("click"); });
			};
			
			var preview = function(input) {
				if (flag) {
					var count = 0, reader = null;
						reader = new FileReader();
						reader.readAsDataURL(input.files[0]);
						reader.onload = function(e) {
						var image = new Image();//构造JS的Image对象
						image.src=  e.target.result;
						var width = image.width;
						var height = image.height;
						if(0 != minWidth && 0 != minHeight ){
							var widthScale = (width/minWidth).toFixed(3);
							var heightScale = (height/minHeight).toFixed(3);
							console.log((width/minWidth), (height/minHeight));
							console.log(widthScale, heightScale);
							if(minWidth > width || minHeight > height){
								mindWidth = minHeight = 0;
								$.messager.alert('请上传的图片宽与高最小比例需符合360X360的图片!');
								return false
							} else if(widthScale != heightScale){
								mindWidth = minHeight = 0;
								$.messager.alert('请上传的图片宽与高最小比例需符合1:1的图片!');
								return false
							}
						} else {
							console.log(width, height);
							console.log((800 > width), (600 > height));
							if(800 > width || 600 > height){
								$.messager.alert('请上传的图片宽与高最小比例需符合800x600的图片!');
								return false
							}
						}
						$(options.previewSelector).html("");
						$(options.previewSelector).append('<div class="'+options.previewClass+'" style="border:1px solid #dcdcdc;width:80px;height:80px;"><img src="" id="fileUploadModulePreview" width="80" height="80"></div>');
						console.log(input.value);
						$("#picUrl").attr("value", input.value);
						$("#fileUploadModulePreview").attr("src", e.target.result);
					};
				}
				
			};	 
			return {
				init: init
			};
			
		})();

		$(document).ready(function() {
			previewImageBeforeUploadModule.init({
				inputSelector: "#inputFile",
				clickableSelector: "#addImages",
				previewSelector: "#preview",
				previewClass: "fileUploadPreview",	// Default fileUploadPreview
				maxFiles: 4,						// Default 1
				thumbnailWidth: 150					// Default auto
			});
		});
</script>
</body>
</html>