<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>团购订单详情</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
	<div class="formTableTitle" align="left" style="height:auto;">当前订单状态：${tradeOrder.status.value }
		
		<c:if test="${tradeOrder.status.name == 'HAS_BEEN_SIGNED' }">
		    </br><span style="font-weight:normal;padding-left: 20px;">成交时间：<fmt:formatDate value='${tradeOrder.updateTime }' pattern='yyyy-MM-dd HH:mm:ss'/></span>
		</c:if>
		<c:if test="${tradeOrder.status.name == 'TRADE_CLOSED' }">
			</br><span style="font-weight:normal;padding-left: 20px;">关闭原因：${tradeOrder.reason }</span>
			</br><span style="font-weight:normal;padding-left: 20px;">关闭时间：<fmt:formatDate value='${tradeOrder.updateTime }' pattern='yyyy-MM-dd HH:mm:ss'/></span>
		</c:if>
		<c:if test="${tradeOrder.status.name == 'TO_BE_SIGNED' }">
		    </br><span style="font-weight:normal;padding-left: 20px;">发货时间：<fmt:formatDate value='${tradeOrder.deliveryTime }' pattern='yyyy-MM-dd HH:mm:ss'/></span>
		</c:if>
		<c:if test="${tradeOrder.status.name == 'REFUSED' || tradeOrder.status.name == 'REFUSING'}">
			</br><span style="font-weight:normal;padding-left: 20px;">拒收原因：${tradeOrder.reason }</span>
			</br><span style="font-weight:normal;padding-left: 20px;">拒收时间：<fmt:formatDate value='${tradeOrder.updateTime }' pattern='yyyy-MM-dd HH:mm:ss'/></span>
		</c:if>
		<c:if test="${tradeOrder.status.name == 'CANCELED' || tradeOrder.status.name == 'CANCELING' }">
		    </br><span style="font-weight:normal;padding-left: 20px;">取消原因：${tradeOrder.reason }</span>
		    </br><span style="font-weight:normal;padding-left: 20px;">取消时间：<fmt:formatDate value='${tradeOrder.updateTime }' pattern='yyyy-MM-dd HH:mm:ss'/></span>
		</c:if>
	</div>

    <div>
    <table align="left" style="margin-left:10px;">
       <!--------------------------收货信息-------------------- -->
       <c:if test="${tradeOrderLogistics!=null}">
            <tr height="30px">
	          <td style="font-weight: bold;font-size: 14px;">收货信息</td>
	        </tr>
	        <c:if test="${tradeOrderLogistics.mobile!=null}">
	                <tr height="30px">
			          <td>配送时间：${tradeOrderLogistics.mobile}</td>
			        </tr>
	          </c:if>
	          <c:if test="${tradeOrderLogistics.mobile!=null}">
	                <tr height="30px">
	                <!-- 姓名和电话 -->
			          <td>联系方式：${tradeOrderLogistics.mobile}</td>
			        </tr>
	          </c:if>
	        <c:if test="${tradeOrderLogistics.area!=null}">
		        <tr height="30px">
		          <td>收货地址：${tradeOrderLogistics.area}${tradeOrderLogistics.address}</td>
		        </tr>
	        </c:if>
	         <c:if test="${tradeOrder.remark!=null && tradeOrder.remark!=''}">
	             <tr height="30px">
		          <td>留言：${tradeOrder.remark}</td>
		        </tr>
	        </c:if>
       </c:if>
        
       <!--------------------------订单信息-------------------- -->
        <tr height="30px">
          <td style="font-weight: bold;font-size: 14px;">订单信息</td>
        </tr>
        
        <tr height="30px">
          <td>订单编号：${tradeOrder.orderNo}</td>
        </tr>
        <c:if test="${tradeOrder.tradeOrderPay.payType.value!=null}">        
	        <tr height="30px">
	          <td>支付方式：${tradeOrder.tradeOrderPay.payType.value}</td>
	        </tr>
        </c:if>
        
        <c:if test="${tradeOrder.createTime!=null}">
            <tr height="30px">
              <td>下单时间：<fmt:formatDate value='${tradeOrder.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
            </tr>
        </c:if>
        <c:if test="${tradeOrder.tradeOrderPay.payTime!=null}">
            <tr height="30px">
               <td>支付时间：<fmt:formatDate value='${tradeOrder.tradeOrderPay.payTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
            </tr>
        </c:if>
        <c:if test="${tradeOrder.deliveryTime !=null}">
           <tr height="30px">
              <td>成团时间：<fmt:formatDate value='${tradeOrder.deliveryTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
           </tr>
        </c:if>
        <c:if test="${tradeOrder.deliveryTime !=null}">
           <tr height="30px">
              <td>发货时间：<fmt:formatDate value='${tradeOrder.deliveryTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
           </tr>
        </c:if>
        <c:if test="${tradeOrder.deliveryTime !=null}">
           <tr height="30px">
              <td>收货时间：<fmt:formatDate value='${tradeOrder.deliveryTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
           </tr>
        </c:if>
        <c:if test="${tradeOrder.deliveryTime !=null}">
           <tr height="30px">
              <td>取消时间：<fmt:formatDate value='${tradeOrder.deliveryTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
           </tr>
        </c:if>
      </table>
      </div>
      
     <!--所有订单-->
    <table id="itemDg"></table>
<script type="text/javascript">
var itemDg;
var orderId = '${tradeOrder.id}';
var storeImagePrefix = '${storeImagePrefix}';
$(function () {
	itemDg = $('#itemDg').datagrid({
		method: "post",
	    url:'${ctx}/trade/order/serviceItemList/',
	    queryParams:{orderId:orderId},
	    fit : true,
		fitColumns : true,
		idField : 'id',
		rownumbers:true,
		columns:[[    
	       {field:'mainPicPrl',title:'商品',width:150 ,
	        	formatter:function(value,rowData,index){
	        		if (value == null || value == '') {
	        			return '<img height="40" width="50" src="${ctx}/crop/images/UploadLight.png" />'+rowData.skuName+"  "+rowData.propertiesIndb;
	        		}else{
	        			return '<img src="'+storeImagePrefix+value+'?imageView2/0/w/50/h/50" />'+rowData.skuName+"  "+rowData.propertiesIndb;
	        		}
	        	} 
	        }, 
	        {field:'unitPrice',title:'单价',width:50,
	        	formatter:function(value,rowData,index){
	        		return value.toFixed(2);
	        	}
	        }, 
	        {field:'quantity',title:'数量',width:50},  
	        {field:'totalAmount',title:'小计（元）',width:50,
	        	formatter:function(value,rowData,index){
	        		return value.toFixed(2);
	        	}
	        }, 
	    ]],
	    enableHeaderClickMenu: false,
	    enableHeaderContextMenu: false,
	    enableRowContextMenu: false
		});
});
</script>
</body>
</html>