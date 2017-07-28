<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>社区团购商品管理</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body style="padding-left: 2px;">
	<!-- easyui-layout 布局 start -->
	<div id="box-main" class="easyui-layout" data-options="fit:true">
		<!-- north start -->
		<div data-options="region:'north'" style="height: 80px;">
			<form id="searchForm" action="">
				<!-- 设置默认查询上架中的状态 在未切换选项卡查询的时候使用 -->
				<input type="hidden" id="groupStatus" name="status" value="0"></input>
				 商品名称：<input type="text"  name="name" class="easyui-validatebox" data-options="width:150,prompt: '商品名称'" />
			             商品ID：<input type="text"  name="id" class="easyui-validatebox" data-options="width:150,prompt: '商品ID',validType:'integer'" />
				 发布时间： <input name="createTimeStart"  type="text" class="easyui-datetimebox" data-options="width:130,showSeconds:false" />
				  至<input name="createTimeEnd"  type="text" class="easyui-datetimebox" data-options="width:130,showSeconds:false" />
			     
			     <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryGroup()">搜索</a>
				 <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="resetFrom()">重置</a>
			   <div>
			    <shiro:hasPermission name="goodsManager:edit"> 
		          <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="addGroup()">发布团购</a>
		          <span class="toolbar-item dialog-tool-separator"></span>
		       </shiro:hasPermission> 
		       </div>
		   </form>
		</div>
		<!-- north end -->

		<!-- center start -->
		<div data-options="region:'center'"style="background: #eee;">

			<div id="tt" class="easyui-tabs" data-options="fit:true">

				<div title="上架中" style="" data-options="fit:true">

					<div id="box-item" class="easyui-layout" data-options="fit:true">
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							<span id="operator0" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="shelveDg"></table>
						</div>
					</div>

				</div>
				<div title="已下架" style="">
					<div id="box-item" class="easyui-layout" data-options="fit:true">
											
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							 <span id="operator1" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="unShelveDg" ></table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- center end -->
	</div>
	<!-- easyui-layout 布局 end -->
	<div id="dlg"></div>
<script type="text/javascript">
    var dg;
    var d;
    $(function () {
   	  $('#tt').tabs({
   		    onSelect:function(title,index){
   		    	var divID;
   		    	//给团购商品状态赋值
   		    	$("#groupStatus").val(index);
   		    	
   		    	switch(index){
  	    		    	case 0:divID="shelve";break;
  	    		    	case 1:divID="unShelve";break;
  	    		    	default:divID= null;
   		    	}
   		    	
   		    	if(index==0){
   		    		dg = $('#'+divID+"Dg").datagrid({
   		    			method: "post",
   	   		     	    url:'${ctx}/goodsManager/list',
   	   		     	    queryParams :{"status":index},
   	   		     	    fit : true,
   	   		     		fitColumns : false,
   	   		     		border : false,
   	   		     		idField : 'id',
   	   		     		striped:true,
   	   		     		pagination:true,
   	   		     		rownumbers:true,
   	   		     		pageNumber:1,
   	   		     		pageSize : 10,
   	   		     		pageList : [ 10, 20, 30, 40, 50 ],
   	   		     		singleSelect:false,
   	   		     		SelectOnCheck:true,
   	   		     		columns:[[    
   	   		     	        {field:'name',title:'商品',width:200,
   	   		     	        	formatter : function(value, rowData, rowIndex) {
	   	   		     	        	 var htmlStr = "";
		   	                         if(rowData.storeSkuUrl != null){
		   	                              htmlStr = "<img width=50 height=50 src='"+rowData.picUrl+"?imageView2/0/w/100/h/100' /><span style='display: inline-block;vertical-align: 18px;'>&nbsp;"+value+"</span>";
		   	                         } else{
		   	                              htmlStr = "<img width=50 height=50 src='#' /><span style='display: inline-block;vertical-align: 18px;'>&nbsp;"+value+ "</span>";
		   	                         }
		   	                          return htmlStr;
   	   		      				}	
   	   		     	        }, 
   	   		     	        {field:'createTime',title:'发布时间',width:300,
   	   		     	        	formatter : function(value, rowData, rowIndex) {
   	   		      	        		if(null != value){
   	   		      	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
   	   		      	        		}
   	   		      	        		return value;
   	   		      				}	
   	   		     	        }, 
   	   		     	        {field:'startTime',title:'拼团开始时间',width:150,
   	   		      	        	formatter : function(value, rowData, rowIndex) {
   	   		      	        		if(null != value){
   	   		      	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
   	   		      	        		}
   	   		      	        		return value;
   	   		      				}
   	   		      	        }, 
   	   		      	       {field:'endTime',title:'拼团结束时间',width:150,
   	   		      	        	formatter : function(value, rowData, rowIndex) {
   	   		      	        		if(null != value){
   	   		      	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
   	   		      	        		}
   	   		      	        		return value;
   	   		      				}
   	   		      	        }, 
   	   		     	        {field:'id',title:'商品ID',width:100},
   	   		     	        {field:'groupPrice',title:'团购价（元）',width:150}, 
   	   		     	        {field:'marketPrice',title:'市场价（元）',width:150}, 
   	   		     	        {field:'status',title:'拼团状态',width:150,
   	   		     	        	formatter : function(value, rowData, rowIndex) {
   	   		 						if (value == 0 || value==1) {
   	   		 							return "上架中";
   	   		 						}
   	   		 						return "已下架";
   	   		 				    }
   	   		     	        }, 
   	   		     	        {field:'operator',title:'操作',sortable:false,align:'center',
   	   		     				formatter : function(value, rowData, rowIndex) {
   	   		     					var operator =$('#operator0').html();
   	   		     					operator=operator.format({id:rowData.id});
   	   		     	  				return operator; 
   	   		     				}
   	   		     			},
   	   		     	    ]],
   	   		     	    enableHeaderClickMenu: false,
   	   		     	    enableHeaderContextMenu: false,
   	   		     	    enableRowContextMenu: false,
   	   		     		});
   		    	}
   		    	if(index==1){
   		    		dg = $('#'+divID+"Dg").datagrid({
   		    		method: "post",
   		     	    url:'${ctx}/goodsManager/list', 
   		     		queryParams :{"status":index},
   		     	    fit : true,
   		     		fitColumns : false,
   		     		border : false,
   		     		idField : 'id',
   		     		striped:true,
   		     		pagination:true,
   		     		rownumbers:true,
   		     		pageNumber:1,
   		     		pageSize : 10,
   		     		pageList : [ 10, 20, 30, 40, 50 ],
   		     		singleSelect:false,
   		     		SelectOnCheck:true,
   		     		columns:[[    
   		     	        {field:'name',title:'商品',width:200,
   		     	        	formatter : function(value, rowData, rowIndex) {
	   		     	        	var htmlStr = "";
	   	                       if(rowData.storeSkuUrl != null){
	   	                            htmlStr = "<img width=50 height=50 src='"+rowData.picUrl+"?imageView2/0/w/100/h/100' /><span style='display: inline-block;vertical-align: 18px;'>&nbsp;"+value+"</span>";
	   	                       } else{
	   	                            htmlStr = "<img width=50 height=50 src='#' /><span style='display: inline-block;vertical-align: 18px;'>&nbsp;"+value+ "</span>";
	   	                       }
	   	                        return htmlStr;
   		      				}	
   		     	        }, 
   		     	        {field:'createTime',title:'发布时间',width:300,
   		     	        	formatter : function(value, rowData, rowIndex) {
   		      	        		if(null != value){
   		      	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
   		      	        		}
   		      	        		return value;
   		      				}	
   		     	        }, 
   		     	        {field:'updateTime',title:'下架时间',width:150,
   		      	        	formatter : function(value, rowData, rowIndex) {
   		      	        		if(null != value){
   		      	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
   		      	        		}
   		      	        		return value;
   		      				}
   		      	        }, 
   		     	        {field:'id',title:'商品ID',width:100},
   		     	        {field:'groupPrice',title:'团购价（元）',width:150}, 
   		     	        {field:'marketPrice',title:'市场价（元）',width:150}, 
   		     	    	{field:'reason',title:'下架原因',width:150}, 
   		     	        {field:'status',title:'拼团状态',width:150,
   		     	        	formatter : function(value, rowData, rowIndex) {
   		 						if (value == 0 || value==1) {
   		 							return "上架中";
   		 						}
   		 						return "已下架";
   		 				    }
   		     	        }, 
   		     	        
   		     	        {field:'operator',title:'操作',sortable:false,align:'center',
   		     				formatter : function(value, rowData, rowIndex) {
   		     					var operator =$('#operator1').html();
   		     					operator=operator.format({id:rowData.id});
   		     	  				return operator; 
   		     				}
   		     			},
   		     	    ]],
   		     	    enableHeaderClickMenu: false,
   		     	    enableHeaderContextMenu: false,
   		     	    enableRowContextMenu: false,
   		     		});
   		    	}
   		    }
   		}); 
   	 
   	 
    $(function () {
    	dg = $('#shelveDg').datagrid({
        	method: "post",
    	    url:'${ctx}/goodsManager/list',
    	    queryParams :{"status":0},
    	    fit : true,
    		fitColumns : false,
    		border : false,
    		idField : 'id',
    		striped:true,
    		pagination:true,
    		rownumbers:true,
    		pageNumber:1,
    		pageSize : 10,
    		pageList : [ 10, 20, 30, 40, 50 ],
    		singleSelect:false,
    		columns:[[    
    	        {field:'name',title:'商品',width:200,
     	        	formatter : function(value, rowData, rowIndex) {
     	        	   var htmlStr = "";
                       if(rowData.storeSkuUrl != null){
                            htmlStr = "<img width=50 height=50 src='"+rowData.picUrl+"?imageView2/0/w/100/h/100' /><span style='display: inline-block;vertical-align: 18px;'>&nbsp;"+value+"</span>";
                       } else{
                            htmlStr = "<img width=50 height=50 src='#' /><span style='display: inline-block;vertical-align: 18px;'>&nbsp;"+value+ "</span>";
                       }
                        return htmlStr;
  		      		}	
    	        }, 
    	        {field:'createTime',title:'发布时间',width:300,
    	        	formatter : function(value, rowData, rowIndex) {
     	        		if(null != value){
     	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
     	        		}
     	        		return value;
     				}	
    	        }, 
    	        {field:'startTime',title:'拼团开始时间',width:150,
     	        	formatter : function(value, rowData, rowIndex) {
     	        		if(null != value){
     	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
     	        		}
     	        		return value;
     				}
     	        }, 
     	        {field:'endTime',title:'拼团结束时间',width:150,
     	        	formatter : function(value, rowData, rowIndex) {
     	        		if(null != value){
     	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
     	        		}
     	        		return value;
     				}
     	        }, 
    	        {field:'id',title:'商品ID',width:100},
    	        {field:'groupPrice',title:'团购价（元）',width:150}, 
    	        {field:'marketPrice',title:'市场价（元）',width:150}, 
    	        {field:'status',title:'拼团状态',width:150,
    	        	formatter : function(value, rowData, rowIndex) {
						if (value == 0 || value==1) {
							return "上架中";
						}
						return "已下架";
				    }
    	        }, 
    	        
    	        {field:'operator',title:'操作',sortable:false,align:'center',
    				formatter : function(value, rowData, rowIndex) {
    					var operator =$('#operator0').html();
    					operator=operator.format({id:rowData.id});
    	  				return operator; 
    				}
    			},
    	    ]],
    	    enableHeaderClickMenu: false,
    	    enableHeaderContextMenu: false,
    	    enableRowContextMenu: false,
    		});
    });
})
    //创建查询对象并查询
    function queryGroup() {
    	var fromObjStr = $("#searchForm").serializeObject();
        dg.datagrid('options').queryParams = fromObjStr;
        dg.datagrid('options').method = "post";
        dg.datagrid('load');       
    }
    
  //重置
    var resetFrom = function(){
    	 $("#searchForm").form('clear');
    } 
    //发布团购
    function addGroup() {
    	d = $("#dlg").dialog({
            title: '新增活动',
            width: 900,
            height: 650,
            href: '${ctx}/goodsManager/toAdd',
            maximizable: false,
            modal: true,
            buttons: [{
            	text: '取消',
                id:'cancle_comfirm',
                handler: function () {
                    d.panel('close');
                }
            },{
	           	text: '提交',
                id:'add_comfirm',
                handler: function () {
                    $("#mainform").submit();
                }  
            }]
        });
    }
  //查看团购商品
    function view(id) {
    	
        d = $("#dlg").dialog({
            title: '查看团购商品详细',
            width: 850,
            height: 700,
            href: '${ctx}/goodsManager/viewGroup/' + id,
            maximizable: false,
            modal: true,
            buttons: [{
                text: '返回',
                handler: function () {
                    d.panel('close');
                }
            }]
        });
    }
    
    //下架团购商品
    function unShelve(id) {
    	var reason="";
		$.messager.confirm('提示', '下架本商品后，所有拼团中的订单自动取消！您确定要下架团购商品吗？', function(data){
			if (data){
				$.ajax({
					type:'post',
					url:"${ctx}/goodsManager/unShelveGroup",
					data:{id:id,reason:reason},
					success: function (data) {
				    	if(successTip(data,dg,d)){
				    		dg.datagrid('reload');
				    	}
	                }
				});
			} 
		});
    }
    
</script>
</body>
</html>