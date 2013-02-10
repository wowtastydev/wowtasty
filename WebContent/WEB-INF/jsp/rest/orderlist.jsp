<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="rest.layout">
<t:putAttribute name="main_rest">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/jquery.combobox.css" type="text/css" media="screen" />
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/jquery.combobox.js"></script>

<script>
<!--	
	// Search Current Order
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Go Order Edit
	function goEdit(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("forwardPage").value = "initOrderList";
		document.getElementById("frm").action = "initOrderEdit";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
// -->
</script>

<script>
<!--
    // JQuery
	// Set up List table
	$(document).ready( function() {
		
	    $( "#fromDate" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: true
        });
	    
	    $( "#toDate" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: true
        });
		
		$('#listTable').dataTable( {
			"bJQueryUI": true,
			"bStateSave": true,
			"sPaginationType": "full_numbers"
		} );
		
		// Set up Combobox
		$("#deliveryType").combobox();
		$("#orderStatus").combobox();
		
		// Set up Button
		$( "input[type=button]" ).button();
	} );
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('R002');">Current Order</a>
				<a href="#" class="active">Order History</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="searchOrder">
			<s:hidden name="selectedOrderID" id="selectedOrderID"/>
			<s:hidden name="selectedRestaurantID" id="selectedRestaurantID"/>
			<s:hidden name="selectedDeliverymanID" id="selectedDeliverymanID"/>
			<s:hidden name="selectedOrderMemberEmail" id="selectedOrderMemberEmail"/>
			<s:hidden name="forwardPage" id="forwardPage"/>

			<h2>Order List</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea">
				<table width="100%">
					<tr>
						<th width="130">Customer Email : </th>
						<td>
							<s:textfield name="condition.orderMemberEmail" id="orderMemberEmail" size="20" maxlength="256"/>
						</td>
						<th width="130">Customer Tel : </th>
						<td>
							<s:textfield name="condition.orderMemberTelephone" id="orderMemberTelephone" size="20" maxlength="20"/>
						</td>
						<th width="130">Delivery Type : </th>
						<td>
						<s:select name="condition.deliveryType" id="deliveryType" list="deliveryTypeList" listKey="code" listValue="name" headerKey="" headerValue="All" />
						</td>
						<td align="right"><input type="button" value="Search" onClick="javascript:search();" /></td>
					</tr>
					<tr>
						<th>From(MM/DD/YYYY) : </th>
						<td>
							<s:textfield name="condition.fromDate" id="fromDate" size="12" maxlength="10"/>
						</td>
						<th>To(MM/DD/YYYY) : </th>
						<td>
							<s:textfield name="condition.toDate" id="toDate" size="12" maxlength="10"/>
						</td>
						<th>Order Status : </th>
						<td colspan=2>
							<s:select name="condition.orderStatus" id="orderStatus" list ="orderStatusList" listKey="code" listValue="shortName" headerKey="" headerValue="All" />
						</td>
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
						<th>Order by</th>
						<th>Status</th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{orderID}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].orderID" id="list[%{#outerStat.index}].orderID" />
							<s:hidden name="list[%{#outerStat.index}].orderMemberEmail" id="list[%{#outerStat.index}].orderMemberEmail" />
							<s:hidden name="list[%{#outerStat.index}].deliveryTime"/>
							<s:hidden name="list[%{#outerStat.index}].restaurantName"/>
							<s:hidden name="list[%{#outerStat.index}].deliveryType"/>
							<s:hidden name="list[%{#outerStat.index}].deliveryTypeName"/>
							<s:hidden name="list[%{#outerStat.index}].orderTypeName"/>
							<s:hidden name="list[%{#outerStat.index}].deliveryAddress"/>
							<s:hidden name="list[%{#outerStat.index}].orderMemberEmail"/>
							<s:hidden name="list[%{#outerStat.index}].orderStatus"/>
							<s:hidden name="list[%{#outerStat.index}].orderStatusName"/>
							<s:hidden name="list[%{#outerStat.index}].deliverymanID"/>
						</td>
						<td align="center">
							<s:date name="%{deliveryTime}" var="deliveryTimeFormatted" format="MM/dd HH:mm"/>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{deliveryTimeFormatted}"/></s:a>
						</td>
						<td align="left">	
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{restaurantName}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].restaurantID" id="list[%{#outerStat.index}].restaurantID"/>
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
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{orderMemberEmail}"/></s:a>
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{orderStatusName}"/></s:a>
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