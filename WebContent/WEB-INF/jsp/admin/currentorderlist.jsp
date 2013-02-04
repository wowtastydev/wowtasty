<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" />
<script src="../js/jquery.dataTables.min.js"></script>

<script>
<!--	
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}

	// Change Pending Order Status into Ordered Status
	function changeOrderStatus(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("selectedOrderMemberEmail").value = document.getElementById("list[" + index + "].orderMemberEmail").value;
		document.getElementById("forwardPage").value = "/WEB-INF/jsp/admin/currentorderlist.jsp";
		document.getElementById("frm").action = "changeOrderStatus";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	// Set Delivery Man
	function setDeliveryMan(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("selectedDeliverymanID").value = document.getElementById("list[" + index + "].deliverymanID").value;
		document.getElementById("forwardPage").value = "/WEB-INF/jsp/admin/currentorderlist.jsp";
		document.getElementById("frm").action = "setDeliveryMan";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Go Order Edit
	function goEdit(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("forwardPage").value = "initCurrentOrderList";
		document.getElementById("frm").action = "initOrderEdit";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
// -->
</script>

<script>
<!--
	
	$(document).ready( function() {
		// Set up List table
		$('#listTable').dataTable( {
			"bJQueryUI": true,
			"bStateSave": true,
			"sPaginationType": "full_numbers"
		} );
		
		// Set up button
		$( "input[type=button]" ).button();
	} );
//-->
</script>
<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="#" class="active">Current Order</a>
				<a href="javascript:goPage('A003');">Order History</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="searchCurrentOrder">
			<s:hidden name="selectedOrderID" id="selectedOrderID"/>
			<s:hidden name="selectedRestaurantID" id="selectedRestaurantID"/>
			<s:hidden name="selectedDeliverymanID" id="selectedDeliverymanID"/>
			<s:hidden name="selectedOrderMemberEmail" id="selectedOrderMemberEmail"/>
			<s:hidden name="forwardPage" id="forwardPage"/>
			
			<h2>Current Order List</h2>
			
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea">
				<table width="100%">
					<tr>
						<th>Order Time : </th>
						<td>
							<s:radio name="condition.time" id="condition.time" list="timeMap" listKey="key" listValue="value" />
						</td>
						<th>Order Status : </th>
						<td>
							<s:radio name="condition.orderStatus" id="condition.orderStatus" list="orderStatusMap" listKey="key" listValue="value" />
						</td>
						<td align="right"><input type="button" value="Search" onClick="javascript:search();" /></td>
					</tr>
				</table>
			</div>
			
			<div id="searchlistarea">
				<table id="listTable" class="display"> 
					<thead>
					<tr align="center">
						<th>Order ID</th>
						<th>Deliv Time</th>
						<th>Restaurant</th>
						<th>Type</th>
						<th>Resv</th>
						<th>Address</th>
						<th>Status</th>
						<th>Delivery Man</th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{orderID}" /></s:a>
							<s:hidden name="list[%{#outerStat.index}].orderID" id="list[%{#outerStat.index}].orderID"/>
							<s:hidden name="list[%{#outerStat.index}].orderMemberEmail" id="list[%{#outerStat.index}].orderMemberEmail" />
						</td>
						<td align="center">
							<s:date name="%{deliveryTime}" var="deliveryTimeFormatted" format="MM/dd HH:mm"/>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{deliveryTimeFormatted}"/></s:a>
						</td>
						<td align="left">	
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{restaurantName}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].restaurantID" id="list[%{#outerStat.index}].restaurantID" />
							<s:hidden name="list[%{#outerStat.index}].restaurantEmail" id="list[%{#outerStat.index}].restaurantEmail" />
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{deliveryTypeName}"/></s:a>
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{orderTypeName}"/></s:a>
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{deliveryAddress}"/></s:a>
						</td>
						<td align="center">
							<s:if test='%{"05".equals(orderStatus)}'>
								<div style="background-color:#ff9933;">								
								<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{orderStatusName}"/></s:a>
								<a href="javascript:changeOrderStatus('<s:property value="%{#outerStat.index}" />');"><img src="../images/admin/edit.png"></a>
								</div>
							</s:if>
							<s:else>
								<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{orderStatusName}"/></s:a>
							</s:else>
						</td>
						<td align="center">
							<s:if test='%{"1".equals(deliveryType)}'>
								<s:select name="list[%{#outerStat.index}].deliverymanID" id="list[%{#outerStat.index}].deliverymanID" list="deliverymanList" listKey="deliverymanID" listValue="firstName" headerKey="" headerValue="" onchange="javascript:setDeliveryMan('%{#outerStat.index}');"/>
							</s:if>
						</td>
					</tr>
					</s:iterator>
					</tbody>
				</table>
			</div>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>