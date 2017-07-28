<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>社区团购订单管理</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>

<body style="padding-left: 2px;">
	<!-- easyui-layout 布局 start -->
	<div id="box-main" class="easyui-layout" data-options="fit:true">

		<!-- north start -->
		<div data-options="region:'north'" style="height: 80px;">
		<div>
			 <form id="searchForm0">
				 订单编号：<input type="text" id="orderId" name="orderId"
					class="easyui-validatebox" maxlength="20"
					data-options="width:200,prompt: '订单编号'" /> 
				商品名称：<input type="text" id="name" name="name"
					class="easyui-validatebox" maxlength="20"
					data-options="width:200,prompt: '订单编号'" /> 
				买家账号：<input type="text" id="account" name="account"
					class="easyui-validatebox" maxlength="20"
					data-options="width:200,prompt: '订单编号'" /> 
				时间排序：<select class="easyui-validatebox" name="status"  style="width: 100px;height:20px;">
						    <option value="" selected = "selected">请选择</option>
					  		<option value="0">下单时间由近到远排列</option>
					  		<option value="1">下单时间由远到近排列</option>
					  </select>
				下单时间： <input
					name="createTimeStart" type="text" class="easyui-datetimebox"
					data-options="width:150,showSeconds:true" readOnly="readOnly" />
				至 <input name="createTimeEnd" type="text"
					class="easyui-datetimebox"
					data-options="width:150,showSeconds:true" readOnly="readOnly" />
				<shiro:hasPermission name="blackManager:seek">
					<a href="javascript(0)" class="easyui-linkbutton"
						iconCls="icon-search" plain="true" onclick="query(0)">搜索</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="blackManager:seek">
					<a href="javascript(0)" class="easyui-linkbutton" plain="true"
						iconCls="icon-remove" onclick="reset(0)">重置</a>
				</shiro:hasPermission>
			</form>
		</div>
		</div>
		<!-- north end -->

		<!-- center start -->
		<div data-options="region:'center'"style="background: #eee;">

			<div id="tt" class="easyui-tabs" data-options="fit:true">

				<div title="所有订单" style="" data-options="fit:true">

					<div id="box-item" class="easyui-layout" data-options="fit:true">
						
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							<div>
								<span id="operator0" style="display: none;"> 
									<shiro:hasPermission name="blackManager:delete">
											<a href="javascript:void(0)" class="easyui-linkbutton"
												iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
											<span class="toolbar-item dialog-tool-separator"></span>
									</shiro:hasPermission>
								</span>
							</div>
								<table id="allOrderDg"></table>
						</div>
					</div>

				</div>
				<div title="待付款" style="">
					<div id="box-item" class="easyui-layout" data-options="fit:true">
										
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							 <span id="operator1" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="waitPayOrderDg" ></table>
						</div>
					</div>
				</div>
				
				<div  id="inGroupOrder" title="拼团中" style="">
					<div id="box-item" class="easyui-layout" data-options="fit:true">
											
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							 <span id="operator2" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="inGroupOrderDg"></table>
						</div>
					</div>	
				</div>
				
				<div  id="undeliveredOrder" title="待发货" style="">
					<div id="box-item" class="easyui-layout" data-options="fit:true">
												
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							<span id="operator3" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="undeliveredOrderDg"></table>
						</div>
					</div>
				</div>
				<div  id="deliveredOrder" title="已发货" style="">
					<div id="box-item" class="easyui-layout" data-options="fit:true">
											
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							 <span id="operator4" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="deliveredOrderDg"></table>
						</div>
					</div>	
				</div>
				<div  id="successOrder" title="交易完成" style="">
					<div id="box-item" class="easyui-layout" data-options="fit:true">
											
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							 <span id="operator5" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="successOrderDg"></table>
						</div>
					</div>	
				</div>
				<div  id="cancelOrder" title="已取消" style="">
					<div id="box-item" class="easyui-layout" data-options="fit:true">
											
						<div data-options="region:'center'"style="padding: 5px; background: #eee;">
							 <span id="operator6" style="display: none;"> <shiro:hasPermission
									name="blackManager:delete">
									<a href="javascript:void(0)" class="easyui-linkbutton"
										iconCls="icon-remove" plain="true" onclick="del('{id}')">删除</a>
									<span class="toolbar-item dialog-tool-separator"></span>
								</shiro:hasPermission>
							</span>
							<table id="cancelOrderDg"></table>
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
 		    	switch(index){
	    		    	case 0:divID="allOrder";break;
	    		    	case 1:divID="waitPayOrder";break;
	    		    	case 2:divID="inGroupOrder";break;
	    		    	case 3:divID="undeliveredOrder";break;
	    		    	case 4:divID="deliveredOrder";break;
	    		    	case 5:divID="successOrder";break;
	    		    	case 6:divID="cancelOrder";break;
	    		    	default:divID= null;
 		    	}
 		    	if(index==0){
 		    		dg = $('#'+divID+"Dg").datagrid({
 		        	method: "post",
 		    	    url:'${ctx}/orderManager/list', 
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
 		    	        {field:'account',title:'订单编号',align:'center',width:150},
 		    	        {field:'account',title:'商品',align:'center',width:150},
 		    	        {field:'createTime',title:'下单时间',width:150,align:'center',
 		    	        	formatter : function(value, rowData, rowIndex) {
 		    	        		if(null != value){
 		    	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 		    	        		}
 		    	        		return value;
 		    				}
 		    	        },
 		    	       {field:'account',title:'用户账号',align:'center',width:150},
 		    	       {field:'account',title:'单价（元）',align:'center',width:150},
 		    	       {field:'account',title:'数量',align:'center',width:150},
 		    	       {field:'account',title:'实付款（元）',align:'center',width:150},
 		    	       {field:'account',title:'订单状态',align:'center',width:150},
 		    	       {field:'operater',title:'操作',sortable:false,align:'center',
 		    				 formatter : function(value, rowData, rowIndex) {
 		    					var operator =$('#operator'+index).html();
 		    					operator=operator.format({id:rowData.id});
 		    	  				return operator;    
 		    	  				
 		    				} 
 		    			}
 		    	    ]],
 		    	    enableHeaderClickMenu: false,
 		    	    enableHeaderContextMenu: false,
 		    	    enableRowContextMenu: false,
 		    		});
 		    	}
 		    	if(index==1){
 		    		dg = $('#'+divID+"Dg").datagrid({
 	 		        	method: "post",
 	 		    	    url:'${ctx}/risk/black/list', 
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
 	 		    		SelectOnCheck:true,
 	 		    		columns:[[    
 	 		    	        {field:'id',title:'id',checkbox:true},
 	 		    	        {field:'account',title:'支付账号',width:150,align:'center',
 	 		    	        	formatter : function(value, rowData, rowIndex) {
 	 		    	        		if(null != value && value !="" && rowData.accountType==4){
 	 		    	        			value="支付宝："+value;
 	 		    	        		}
 	 		    	        		if(null != value && value !="" && rowData.accountType==1){
 	 		    	        			value="微信："+value;
 	 		    	        		}
 	 		    	        		return value;
 	 		    				}
 	 		    	        },
 	 		    	      {field:'createTime',title:'创建时间',width:150,align:'center',
 	 		    	        	formatter : function(value, rowData, rowIndex) {
 	 		    	        		if(null != value){
 	 		    	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 	 		    	        		}
 	 		    	        		return value;
 	 		    				}
 	 		    	        },
 	 		    	       {field:'updateTime',title:'更新时间',width:150,align:'center',
 	 		     	        	formatter : function(value, rowData, rowIndex) {
 	 		     	        		if(null != value){
 	 		     	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 	 		     	        		}
 	 		     	        		return value;
 	 		     				}
 	 		     	        },
 	 		    	        {field:'operater',title:'操作',sortable:false,align:'center',
 	 		    				 formatter : function(value, rowData, rowIndex) {
 	 		    					var operator =$('#operator'+index).html();
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
 		    	if(index==2){
 		    		dg = $('#'+divID+"Dg").datagrid({
 	 		        	method: "post",
 	 		    	    url:'${ctx}/risk/black/list', 
 	 		    	    queryParams :{"status":1},
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
 	 		    	        {field:'id',title:'id',checkbox:true},
 	 		    	        {field:'account',title:'充值号码',align:'center',width:150},
 	 		    	     	 {field:'createTime',title:'创建时间',width:150,align:'center',
 	 		    	        	formatter : function(value, rowData, rowIndex) {
 	 		    	        		if(null != value){
 	 		    	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 	 		    	        		}
 	 		    	        		return value;
 	 		    				}
 	 		    	        },
 	 		    	       {field:'updateTime',title:'更新时间',width:150,align:'center',
 	 		     	        	formatter : function(value, rowData, rowIndex) {
 	 		     	        		if(null != value){
 	 		     	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 	 		     	        		}
 	 		     	        		return value;
 	 		     				}
 	 		     	        },
 	 		    	        {field:'operater',title:'操作',sortable:false,align:'center',
 	 		    				 formatter : function(value, rowData, rowIndex) {
 	 		    					var operator =$('#operator'+index).html();
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
 		    	if(index==3){
 		    		dg = $('#'+divID+"Dg").datagrid({
 	 		        	method: "post",
 	 		    	    url:'${ctx}/risk/black/list', 
 	 		    	    queryParams :{"status":4},
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
 	 		    	        {field:'id',title:'id',checkbox:true},
 	 		    	        {field:'account',title:'设备id',align:'center',width:150},
 	 		    	     	 {field:'createTime',title:'创建时间',width:150,align:'center',
 	 		    	        	formatter : function(value, rowData, rowIndex) {
 	 		    	        		if(null != value){
 	 		    	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 	 		    	        		}
 	 		    	        		return value;
 	 		    				}
 	 		    	        },
 	 		    	       {field:'updateTime',title:'更新时间',width:150,align:'center',
 	 		     	        	formatter : function(value, rowData, rowIndex) {
 	 		     	        		if(null != value){
 	 		     	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 	 		     	        		}
 	 		     	        		return value;
 	 		     				}
 	 		     	        },
 	 		    	        {field:'operater',title:'操作',sortable:false,align:'center',
 	 		    				 formatter : function(value, rowData, rowIndex) {
 	 		    					var operator =$('#operator'+index).html();
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
 	 
 	 dg = $('#allOrderDg').datagrid({
     	method: "post",
 	    url:'${ctx}/risk/black/list', 
 	    queryParams :{"accountType":"0"},
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
 	        {field:'id',title:'id',checkbox:true},
 	        {field:'account',title:'登录账号id',sortable:false,align:'center',width:150},
 	       {field:'createTime',title:'创建时间',width:150,align:'center',
 	        	formatter : function(value, rowData, rowIndex) {
 	        		if(null != value){
 	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
 	        		}
 	        		return value;
 				}
 	        },
 	       {field:'updateTime',title:'更新时间',width:150,align:'center',
  	        	formatter : function(value, rowData, rowIndex) {
  	        		if(null != value){
  	        			return new Date(value).format('yyyy-MM-dd hh:mm:ss')
  	        		}
  	        		return value;
  				}
  	        },
 	        {field:'operator',title:'操作',sortable:false,align:'center',
 				formatter : function(value, rowData, rowIndex) {
 					var operator =$('#operator1').html();
 					operator=operator.format({id:rowData.id});
 	  				return operator; 
 				} 
 	        }
 	    ]],
 	    enableHeaderClickMenu: false,
 	    enableHeaderContextMenu: false,
 	    enableRowContextMenu: false,
 		}); 
 	 
 	 
    }); 
  //新增
  function add(){
		d=$("#dlg").dialog({   
		    title: '添加黑名单',    
		    width: 550,    
		    height: 550,    
		    href:'${ctx}/risk/black/create',
		    maximizable:false,
		    modal:true,
		    buttons:[{
		    	id:"add_comfirm",
				text:'添加',
				handler:function(){
				      $("#addForm").submit();
				}
			
			}]
		});
	}
  
  //查看团购订单详情
  function view(id) {
  	
      d = $("#dlg").dialog({
          title: '团购商品订单详细',
          width: 850,
          height: 700,
          href: '${ctx}/orderManager/viewOrder/' + id,
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
  
  //删除
  function del(id){
	  $.messager.confirm('提示', '是否确定删除黑名单？', function(data){
  		if (data){
  			$.ajax({
  				type:'post',
  				url:"${ctx}/risk/black/delete",
  				data:{accountId:id},
  				success: function (data) {
  					if(successTip(data,dg,d))
  			    		dg.datagrid('reload');
  					dg.datagrid('unselectAll');
                  }
  			});
  		} 
  	});
  } 
  //创建查询对象并查询
  function query(num) {
  	var fromObjStr = $("#searchForm"+num).serializeObject();
      dg.datagrid('options').queryParams = fromObjStr;
      dg.datagrid('options').method = "post";
      dg.datagrid('load');
  }
  //重置查询条件
  function reset(num){
	  $('#searchForm'+num).form('reset');
  }
  
  </script>
</body>
</html>