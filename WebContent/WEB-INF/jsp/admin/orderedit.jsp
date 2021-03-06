<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<script>
<!--	
	// Change Pending Order Status into Ordered Status
	function changeOrderStatus(status){
		document.getElementById("selectedStatus").value = status;
		document.getElementById("frm").action = "changeOrderStatusEdit";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Set Delivery Man
	function setDeliveryMan(){
		document.getElementById("selectedDeliverymanID").value = document.getElementById("restaurant.deliverymanID").value;
		document.getElementById("frm").action = "setDeliveryManEdit";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Go back to List page
	function goList() {
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "goOrderList";
		document.getElementById("frm").submit();
	}

	// -->
</script>

<script>
<!--
$(function() {
	// Google Map Panel
    $( "#googleMap" ).dialog({
        autoOpen: false,
        width: 480,
        height: 450
    });

    $( "#showMap" ).click(function() {
    	// HTML decode
		mapHtml.innerHTML = $("<div/>").html("<s:property value='%{googleMapUrl}'/>").text();
        $( "#googleMap" ).dialog( "open" );
        return false;
    });
    
    // Menu detail Panel(Set up as many as the number of menus)
    <s:iterator value="menuList" id="menuList" status="outerStat">
    $( "#menuDetail<s:property value='%{#outerStat.index}' />" ).dialog({
        autoOpen: false,
        width: 450,
        height: 450
    });
    
    $( "#showMenu<s:property value='%{#outerStat.index}' />" ).click(function() {
        $( "#menuDetail<s:property value='%{#outerStat.index}' />" ).dialog( "open" );
        return false;
    });
    </s:iterator>
    
    // Decline Reason Panel
    $( "#declineReason" ).dialog({
        autoOpen: false,
        width: 480,
        height: 150
    });

    $( "#decline" ).click(function() {
        $( "#declineReason" ).dialog( "open" );
        return false;
    });
    
    $( "input[type=button]" ).button();
});
// -->
</script>

<!-- Google Map Panel -->
<div id="googleMap" title="Restaurant Location">
	<span id="mapHtml"></span>
</div>

<!-- Google Map Panel -->
<s:iterator value="menuList" id="menuList" status="menuStat">
<div id="menuDetail<s:property value='%{#menuStat.index}' />" title="Menu Detail">
	<h4><s:property value="%{menuName}"/></h4>
	<table width="400">
	<tr>
		<td width="250">Unit : <s:property value="%{unit}"/></td>
		<td align="right">Unit Price : <s:property value="getText('{0,number,#,##0.00}',{unitPrice})"/></td>
	</tr>
	<tr><td colspan="2"></td></tr>
	<tr><td colspan="2"><b>[Options]</b></td></tr>
	<tr><td colspan="2">
		<table class="tableborder" width="380">
			<tr>
				<th><SPAN style="FONT-SIZE: 10pt">Option Name</SPAN></th>
				<th><SPAN style="FONT-SIZE: 10pt">Extra Charge</SPAN></th>
			</tr>

		<s:set var="restID" value="restaurantID" />
		<s:set var="menuSeq" value="seq" />
		<s:iterator value="menuoptionList" id="menuoptionList" status="optionStat">
			<s:if test='%{#restID.equals(restaurantID) && #menuMenuID.equals(seq)}'>
			<tr>
				<td><s:property value="%{optionName}"/></td>
				<td><s:property value="getText('{0,number,#,##0.00}',{extraCharge})"/></td>
			</tr>
			</s:if>
		</s:iterator>
		</table>
		</td>
	</tr>
	<tr><td colspan="2"></td></tr>
	<tr><td colspan="2"><b>[Special Instruction]</b></td></tr>
	<tr><td colspan="2">
	<table><tr><td align="left">
	<s:textarea rows="3" cols="30" name="%{instruction}"></s:textarea>
	</td></tr></table>
	</td></tr>
	<tr><td colspan="2"></td></tr>
	<tr><td></td><td align="right">Sub Total:<s:property value="getText('{0,number,###,##0.00}',{totalPrice})"/></td></tr>
	<tr><td></td><td align="right">Tax:<s:property value="getText('{0,number,###,##0.00}',{taxPrice})"/></td></tr>
	<tr><td></td><td align="right"><b>Total Price:<s:property value="getText('{0,number,###,##0.00}',{totalPriceWithTax})"/></b></td></tr>
	</table>
</div>
</s:iterator> 


<!-- Decline Reason Panel -->
<div id="declineReason" title="Decline Reason">
	<p align="center"><s:radio name="declinedReason" id="declinedReason" list="declineReasonList" listKey="code" listValue="name" value="declineReason" /></p>
	<p align="center"><input type="button" value="Decline" onClick="javascript:changeOrderStatus('71');" /></p>	
</div>


<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('A002');">Current Order</a>
				<a href="javascript:goPage('A003');">Order History</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="css_xhtml" name="frm" id="frm" action="changeOrderStatus">
			<s:hidden name="selectedOrderID" id="selectedOrderID" value="%{selectedOrderID}"/>
			<s:hidden name="selectedRestaurantID" id="selectedRestaurantID" value="%{selectedRestaurantID}"/>
			<s:hidden name="selectedDeliverymanID" id="selectedDeliverymanID"/>
			<s:hidden name="selectedStatus" id="selectedStatus"/>
			<s:hidden name="selectedSeq" id="selectedSeq"/>
			<s:hidden name="page" id="page"/>
			
			<h2>Restaurant Order Edit</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			<div id="detailarea">
				<table width="100%">
					<tr><td>
						<b>[General Information]</b>
						</td>
						<td align="right">
						<b>Order Status :</b>
							<s:iterator value="orderStatusList" id="orderStatusList" status="outerStat">
								<s:if test='%{code.equals(restaurant.orderStatus)}'>
									<s:property value="%{name}"/>
								</s:if>
							</s:iterator>
						</td>
					</tr>
				</table>

				<table class="tableborder" width="100%">
					<tr>
						<th>Order ID : </th>
						<td><s:property value="%{master.orderID}"/></td>
						<th>Order By : </th>
						<td><s:property value="%{master.orderMemberEmail}"/></td>
					</tr>
					<tr>
						<th>Order Time : </th>
						<td>
							<s:date name="%{master.orderTime}" var="orderTimeFormatted" format="yyyy/MM/dd HH:mm"/>
							<s:property value="%{orderTimeFormatted}"/>
						</td>
						<th>Delivery Time : </th>
						<td>
							<s:date name="%{master.deliveryTime}" var="deliveryTimeFormatted" format="yyyy/MM/dd HH:mm"/>
							<s:property value="%{deliveryTimeFormatted}"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="detailarea">
				<b>[Order Detail]</b>
				<table class="tableborder" width="100%">
					<tr>
						<th width="200">Restaurant</th>
						<th width="120">Dev/Takeout</th>
						<th width="200">Item</th>
						<th width="50">Unit</th>
						<th width="100">Unit Price($)</th>
						<th width="100">Total Price($)</th>
					</tr>
					
					<s:iterator value="restaurantList" id="restaurantList" status="restStat">
						<s:set var="restID" value="restaurantID" />
						<s:set var="isShow" value="0" />
						<s:iterator value="menuList" id="menuList" status="menuStat">
						<s:if test='%{restaurantID.equals(#restID)}'>
						<!-- Menu List -->
						<tr>
							<s:if test='%{0==#isShow}'>
								<td>
									<s:property value="%{restaurantName}"/>
								</td>
								<td>
									<s:if test='%{"1".equals(deliveryType)}'>
									Delivery
									</s:if>
									<s:if test='%{"2".equals(deliveryType)}'>
									Take-out
									</s:if>
								</td>
								<s:set var="isShow" value="1" />
							</s:if>
							<s:else>
								<td colspan="2"></td>
							</s:else>
							<td>
								<a href="#" id="showMenu<s:property value='%{#menuStat.index}' />"><s:property value="%{menuName}"/></a>
							</td>
							<td align="center">
								<s:property value="%{unit}"/>
							</td>
							<td align="right">
								<s:property value="getText('{0,number,#,##0.00}',{unitPrice})"/>
							</td>
							<td align="right">
								<s:property value="getText('{0,number,#,##0.00}',{totalPrice})"/>
							</td>
						</tr>
						</s:if>
						</s:iterator>
						<!-- Food Tax -->
						<tr>
							<td colspan="2"></td>
							<td>Food Tax</td>
							<td align="center"></td>
							<td align="right"></td>
							<td align="right">
								<s:property value="getText('{0,number,#,##0.00}',{foodTaxPrice})"/>
							</td>
						</tr>
						<!-- Delivery Fee&Tax -->
						<s:if test='%{"1".equals(deliveryType)}'>
						<tr>
							<td colspan="2"></td>
							<td>Delivery Fee</td>
							<td align="center"></td>
							<td align="right"></td>
							<td align="right">
								<s:property value="getText('{0,number,#,##0.00}',{deliveryPrice})"/>
							</td>
						</tr>
						<tr>
							<td colspan="2"></td>
							<td>Delivery Tax</td>
							<td align="center"></td>
							<td align="right"></td>
							<td align="right">
								<s:property value="getText('{0,number,#,##0.00}',{deliveryTaxPrice})"/>
							</td>
						</tr>
						</s:if>
						<!-- Tip -->
						<tr>
							<td colspan="2"></td>
							<td>Tip</td>
							<td align="center"></td>
							<td align="right"></td>
							<td align="right">
								<s:property value="getText('{0,number,#,##0.00}',{tipPrice})"/>
							</td>
						</tr>
						<!-- Sub Total -->
						<tr>
							<td colspan="2"></td>
							<td class ="bg">Sub Total</td>
							<td class ="bg" align="center"></td>
							<td class ="bg" align="right"></td>
							<td class ="bg" align="right">
								<s:property value="getText('{0,number,##,##0.00}',{totalPriceWithTax})"/>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>

			<div id="detailarea">
				<table class="tableborder" width="100%">
					<tr>
						<td class ="bg" width="350"><b>TOTAL</b></td>
						<td class ="bg" width="100" align="right"><b><s:property value="getText('{0,number,###,##0.00}',{master.totalPriceWithTax})"/></b>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="detailarea">
				<b>[Payment Information]</b>
				<!-- Credit Card Payment -->
				<table class="tableborder" width="100%">
					<tr>
						<th width="100">Payment Type</th>
						<th width="200">Card No.</th>
						<th width="100">Amount</th>
					</tr>
					<tr>
					<s:if test='%{"1".equals(paymentType)}'>
						<s:iterator value="creditCardTypeList" id="creditCardTypeList" status="outerStat">
						<s:if test='%{code.equals(paymentCreditType)}'>
							<td><s:property value="%{name}"/><td>
						</s:if>
						</s:iterator>
							<td><s:property value="%{master.paymentCreditNO}"/></td>
					</s:if>
					<s:if test='%{"2".equals(paymentType)}'>
						<td>DEBIT </td>
						<td><s:property value="%{master.paymentDebitNO}"/></td>
					</s:if>
					<s:else>
						<td>CASH </td><td align="center">-</td>
					</s:else>
					<td align="right">$<s:property value="getText('{0,number,###,##0.00}',{master.paymentAmount})"/></td>
					</tr>
					<tr>
					 <td>eCASH </td><td align="center">-</td>
					 <td align="right">$<s:property value="getText('{0,number,###,##0.00}',{master.paymentEcash})"/></td>
				</table>
			</div>
			
			
			<div id="detailarea">
				<!-- In case of delivery -->
				<s:if test='%{"1".equals(restaurant.deliveryType)}'>
					<b>[Delivery Information]</b>
					<table class="tableborder" width="100%">
						<tr>
							<th width="200">Restaurant</th>
							<th width="120" colspan="2">Delivery Man</th>
							<th width="100">City</th>
							<th width="200">Address</th>
							<th width="60">Suite #</th>
							<th width="60">Buzzer #</th>
							<th width="100">Tel</th>
						</tr>
						<tr>
							<td>
								<s:property value="%{restaurant.restaurantName}"/>
							</td>
							<td>
								<s:select name="restaurant.deliverymanID" id="restaurant.deliverymanID" list ="deliverymanList" listKey="deliverymanID" listValue="firstName" headerKey="" headerValue="" />
							</td>
							<td>
								<a href="javascript:setDeliveryMan();"><img src="../images/admin/edit.png"></a>
							</td>
							<td>
								<s:iterator value="cityList" id="cityList" status="outerStat">
								<s:if test='%{code.equals(restaurant.deliveryCity)}'>
									<s:property value="%{name}"/>
								</s:if>
								</s:iterator>
							</td>
							<td>
								<s:property value="%{restaurant.deliveryAddress}"/>
							</td>
							<td>
								<s:property value="%{restaurant.deliverySuite}"/>
							</td>
							<td>
								<s:property value="%{restaurant.deliveryBuzzer}"/>
							</td>
							<td>
								<s:property value="%{restaurant.deliveryTelephone}"/>
							</td>
						</tr>
					</table>
				</s:if>
				<!-- In case of Takeout -->
				<s:if test='%{"2".equals(restaurant.deliveryType)}'>
					<b>[Take-out Information]</b>
					<table class="tableborder" width="100%">
						<tr>
							<th width="250">Restaurant</th>
							<th width="50">Map</th>
							<th width="100">City</th>
							<th width="250">Address</th>
							<th width="100">Tel</th>
						</tr>
						<tr>
							<td>
								<s:property value="%{restaurant.restaurantName}"/>
							</td>
							<td align="center">
								<a id="showMap" href="#"><img src="../images/admin/map.png" alt="Show Google Map"></a>
							</td>
							<td>
								<s:iterator value="cityList" id="cityList" status="outerStat">
								<s:if test='%{code.equals(restaurant.deliveryCity)}'>
									<s:property value="%{name}"/>
								</s:if>
								</s:iterator>
							</td>
							<td>
								<s:property value="%{restaurant.deliveryAddress}"/>
							</td>
							<td>
								<s:property value="%{restaurant.deliveryTelephone}"/>
							</td>
						</tr>
					</table>
				</s:if>
			</div>
			
			<div id="detailarea">
			<table width="100%">
				<tr>
				<td>
				<input type="button" value="List" onClick="javascript:goList();" />
				</td>
				<td align="right">
				<!-- In case of Pending -->
				<s:if test='%{"05".equals(restaurant.orderStatus)}'>
					<input type="button" value="Order" onClick="javascript:changeOrderStatus('01');" />
					<input type="button" value="Cancel Order" onClick="javascript:changeOrderStatus('91');" />
				</s:if>
				<!-- In case of Ordered -->
				<s:if test='%{"01".equals(restaurant.orderStatus)}'>
					<input type="button" value="Accept Order" onClick="javascript:changeOrderStatus('11');" />
					<input type="button" value="Decline Order" id="decline" />
					<input type="button" value="Cancel Order" onClick="javascript:changeOrderStatus('91');" />
				</s:if>
				<!-- In case of Confirmed -->
				<s:if test='%{"11".equals(restaurant.orderStatus)}'>
					<input type="button" value="Complete Order" onClick="javascript:changeOrderStatus('81');" />
				</s:if>
				</td></tr>
			</table>
			</div>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>