<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../themes/blue/style.css" type="text/css" media="print, projection, screen" />
<script src="../js/jquery.tablesorter.js"></script>

<script>
<!--	
	// Search Current Order
	function search(){
		$.blockUI({ message: '<h4><img src="../images/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}

	// Change Pending Order Status into Ordered Status
	function changeOrderStatus(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("selectedOrderMemberEmail").value = document.getElementById("list[" + index + "].orderMemberEmail").value;
		document.getElementById("frm").action = "changeOrderStatus";
		$.blockUI({ message: '<h4><img src="../images/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	// Set Delivery Man
	function setDeliveryMan(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("selectedDeliverymanID").value = document.getElementById("list[" + index + "].deliverymanID").value;
		document.getElementById("frm").action = "setDeliveryMan";
		$.blockUI({ message: '<h4><img src="../images/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// JQuery
	$(function() {
	    $( "#fromDate" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: true
        });
	    
	    $( "#toDate" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: true
        });

	    $("#listTable").tablesorter({sortList:[[0,0],[2,1]], widgets: ['zebra']});
	});

// -->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="initCurrentOrderList">Current Order</a>
				<a href="#" class="active">Order History</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="css_xhtml" name="frm" id="frm" action="searchOrder">
			<s:hidden name="selectedOrderID" id="selectedOrderID"/>
			<s:textfield name="selectedRestaurantID" id="selectedRestaurantID"/>
			<s:hidden name="selectedDeliverymanID" id="selectedDeliverymanID"/>
			<s:hidden name="selectedOrderMemberEmail" id="selectedOrderMemberEmail"/>

			<div id="searchconditionarea">
				<table border=1>
					<tr>
						<td colspan="7"><h2>Order List</h2></td>
					</tr>
					<tr>
						<td align="left" width="120">Restaurant Name : </td>
						<td width="230" colspan="3">
							<s:textfield name="condition.restaurantName" id="condition.restaurantName" value="%{condition.restaurantName}" size="30" maxlength="50"/>
						</td>
						<td align="left" width="120">Order ID : </td>
						<td width="230">
							<s:textfield name="condition.orderID" id="condition.orderID" value="%{condition.orderID}" size="20" maxlength="13"/>
						</td>
						<td rowspan="2" valign="top" width="100"><input type="button" value="Search" onClick="javascript:search();" /></td>
					</tr>
					<tr>
						<td align="left">Order by : </td>
						<td align="left" width="50">Email : </td>
						<td colspan="2">
							<s:textfield name="condition.orderMemberEmail" id="condition.orderMemberEmail" value="%{condition.orderMemberEmail}" size="20" maxlength="256"/>
						</td>
						<td align="left">Telephone : </td>
						<td width="200">
							<s:textfield name="condition.orderMemberTelephone" id="condition.orderMemberTelephone" value="%{condition.orderMemberTelephone}" size="20" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<td align="left">Order Status : </td>
						<td colspan="3">
							<s:select name="condition.orderStatus" id="condition.orderStatus" list ="orderStatusList" listKey="code" listValue="shortName" headerKey="" headerValue="All" />
						</td>
						<td align="left">Date : </td>
						<td align="left" colspan="3">
							<s:textfield name="condition.fromDate" id="fromDate" value="%{condition.fromDate}" size="10" maxlength="10"/>~<s:textfield name="condition.toDate" id="toDate" value="%{condition.toDate}" size="10" maxlength="10"/>
						</td>

					</tr>
				</table>
			</div>
			
			<div id="searchlistarea">
				<table id="listTable" class="tablesorter" border="0" cellpadding="0"> 
					<thead>
					<tr align="center">
						<th>Order ID</th>
						<th>Deliv Time</th>
						<th>Restaurant</th>
						<th>Type</th>
						<th>Resv</th>
						<th>Address</th>
						<th>Status</th>
						<th colspan="2">Delivery Man</th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:property value="%{orderID}"/>
							<s:hidden name="list[%{#outerStat.index}].orderID" id="list[%{#outerStat.index}].orderID"/>
							<s:hidden name="list[%{#outerStat.index}].orderMemberEmail" id="list[%{#outerStat.index}].orderMemberEmail"  value="%{orderMemberEmail}"/>
						</td>
						<td align="center">
							<s:hidden name="list[%{#outerStat.index}].deliveryTime"/>
							<s:date name="%{deliveryTime}" var="deliveryTimeFormatted" format="MM/dd HH:mm"/>
							<s:property value="%{deliveryTimeFormatted}"/>
						</td>
						<td align="left">	
							<s:property value="%{restaurantName}"/>
							<s:hidden name="list[%{#outerStat.index}].restaurantName"/>
							<s:hidden name="list[%{#outerStat.index}].restaurantID" id="list[%{#outerStat.index}].restaurantID"/>
						</td>
						<td align="center">
							<s:property value="%{deliveryTypeName}"/>
							<s:hidden name="list[%{#outerStat.index}].deliveryTypeName"/>
						</td>
						<td align="center">
							<s:property value="%{orderTypeName}"/>
							<s:hidden name="list[%{#outerStat.index}].orderTypeName"/>
						</td>
						<td align="center">
							<s:property value="%{deliveryAddress}"/>
							<s:hidden name="list[%{#outerStat.index}].deliveryAddress"/>
						</td>
						<td align="center">
							<s:if test='%{"05".equals(orderStatus)}'>
								<div style="background-color:#ff9933;">								
								<s:property value="%{orderStatusName}"/>
								<s:hidden name="list[%{#outerStat.index}].orderStatusName"/>
								<a href="javascript:changeOrderStatus('<s:property value="%{#outerStat.index}" />');"><img src="../images/edit.png"></a>
								</div>
							</s:if>
							<s:else>
								<s:property value="%{orderStatusName}"/>
								<s:hidden name="list[%{#outerStat.index}].orderStatusName"/>
							</s:else>
						</td>
						<td align="right">
							<s:select name="list[%{#outerStat.index}].deliverymanID" id="list[%{#outerStat.index}].deliverymanID" list ="deliverymanList" listKey="deliverymanID" listValue="firstName" headerKey="" headerValue="" />
						</td>
						<td align="left">
							<a href="javascript:setDeliveryMan('<s:property value="%{#outerStat.index}" />');"><img src="../images/edit.png"></a>
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