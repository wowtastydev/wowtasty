<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../themes/blue/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<script src="../js/jquery.tablesorter.js"></script>

<script>
<!--	
	// Search Current Order
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}

	// Change Pending Order Status into Ordered Status
	function changeOrderStatus(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("selectedOrderMemberEmail").value = document.getElementById("list[" + index + "].orderMemberEmail").value;
		document.getElementById("frm").action = "changeOrderStatus";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	// Set Delivery Man
	function setDeliveryMan(index){
		document.getElementById("selectedOrderID").value = document.getElementById("list[" + index + "].orderID").value;
		document.getElementById("selectedRestaurantID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("selectedDeliverymanID").value = document.getElementById("list[" + index + "].deliverymanID").value;
		document.getElementById("frm").action = "setDeliveryMan";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
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
			<s:hidden name="selectedRestaurantID" id="selectedRestaurantID"/>
			<s:hidden name="selectedDeliverymanID" id="selectedDeliverymanID"/>
			<s:hidden name="selectedOrderMemberEmail" id="selectedOrderMemberEmail"/>

			<h2>Order List</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea">
				<table>
					<tr>
						<td  width="120">Restaurant Name : </td>
						<td width="230" colspan="2">
							<s:textfield name="condition.restaurantName" id="condition.restaurantName" value="%{condition.restaurantName}" size="30" maxlength="50"/>
						</td>
						<td width="120">Order ID : </td>
						<td width="200">
							<s:textfield name="condition.orderID" id="condition.orderID" value="%{condition.orderID}" size="20" maxlength="13"/>
						</td>
						<td><input type="button" value="Search" onClick="javascript:search();" /></td>
						
					</tr>
					<tr>
						<td>Order by : </td>
						<td width="50">Email : </td>
						<td>
							<s:textfield name="condition.orderMemberEmail" id="condition.orderMemberEmail" value="%{condition.orderMemberEmail}" size="20" maxlength="256"/>
						</td>
						<td>Telephone : </td>
						<td colspan="2">
							<s:textfield name="condition.orderMemberTelephone" id="condition.orderMemberTelephone" value="%{condition.orderMemberTelephone}" size="20" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<td>From : </td>
						<td colspan="2">
							<s:textfield name="condition.fromDate" id="fromDate" value="%{condition.fromDate}" size="10" maxlength="10"/>
						</td>
						<td>To : </td>
						<td colspan="2">
							<s:textfield name="condition.toDate" id="toDate" value="%{condition.toDate}" size="10" maxlength="10"/>
						</td>
					</tr>
					<tr>
						<td>Order Status : </td>
						<td colspan="4">
							<s:select name="condition.orderStatus" id="condition.orderStatus" list ="orderStatusList" listKey="code" listValue="shortName" headerKey="" headerValue="All" />
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
					<!-- If there is field Errors, Don't show list -->
					<s:if test="!hasFieldErrors()">
						<s:iterator value="list" id="list" status="outerStat">
						<tr>
							<td align="center">
								<s:property value="%{orderID}"/>
								<s:hidden name="list[%{#outerStat.index}].orderID" id="list[%{#outerStat.index}].orderID"/>
								<s:hidden name="list[%{#outerStat.index}].orderMemberEmail" id="list[%{#outerStat.index}].orderMemberEmail"  value="%{orderMemberEmail}"/>
							</td>
							<td align="center">
								<s:date name="%{deliveryTime}" var="deliveryTimeFormatted" format="MM/dd HH:mm"/>
								<s:property value="%{deliveryTimeFormatted}"/>
							</td>
							<td align="left">	
								<s:property value="%{restaurantName}"/>
								<s:hidden name="list[%{#outerStat.index}].restaurantID" id="list[%{#outerStat.index}].restaurantID"/>
							</td>
							<td align="center">
								<s:property value="%{deliveryTypeName}"/>
							</td>
							<td align="center">
								<s:property value="%{orderTypeName}"/>
							</td>
							<td align="center">
								<s:property value="%{deliveryAddress}"/>
							</td>
							<td align="center">
								<s:if test='%{"05".equals(orderStatus)}'>
									<div style="background-color:#ff9933;">								
									<s:property value="%{orderStatusName}"/>
									<a href="javascript:changeOrderStatus('<s:property value="%{#outerStat.index}" />');"><img src="../images/admin/edit.png"></a>
									</div>
								</s:if>
								<s:else>
									<s:property value="%{orderStatusName}"/>
								</s:else>
							</td>
							<td align="right">
								<s:select name="list[%{#outerStat.index}].deliverymanID" id="list[%{#outerStat.index}].deliverymanID" list ="deliverymanList" listKey="deliverymanID" listValue="firstName" headerKey="" headerValue="" />
							</td>
							<td align="left">
								<a href="javascript:setDeliveryMan('<s:property value="%{#outerStat.index}" />');"><img src="../images/admin/edit.png"></a>
							</td>
						</tr>
						</s:iterator>
					</s:if>
					</tbody>
				</table>
			</div>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>