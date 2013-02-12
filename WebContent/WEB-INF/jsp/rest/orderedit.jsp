<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="rest.layout">
<t:putAttribute name="main_rest">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<script>
<!--	
	// Change Order Status
	function changeOrderStatus(status){
		if(confirm("Do you want to change the order status?")) {
			document.getElementById("selectedStatus").value = status;
			// In case of decline
			if (status == "71") {
				var radioList = document.getElementsByName("reasonSel");
				for(var i=0; i<radioList.length; i++){
					if (radioList[i].checked) {
						document.getElementById("declinedReason").value = radioList[i].value;
						break;
					}
				}
			}
			document.getElementById("frm").action = "changeOrderStatusEdit";
			$( "#declineReasonPanel" ).dialog( "close" );
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}
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
		document.getElementById("frm").action = "goOrderList";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
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
    $( "#declineReasonPanel" ).dialog({
        autoOpen: false,
        width: 480,
        height: 150
    });

    $( "#decline" ).click(function() {
        $( "#declineReasonPanel" ).dialog( "open" );
        return false;
    });
    
    $( "input[type=button]" ).button();
});
// -->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('R002');">Current Order</a>
				<a href="javascript:goPage('R003');">Order History</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="changeOrderStatus">
			<s:hidden name="selectedOrderID" id="selectedOrderID" value="%{selectedOrderID}"/>
			<s:hidden name="selectedRestaurantID" id="selectedRestaurantID" value="%{selectedRestaurantID}"/>
			<s:hidden name="selectedDeliverymanID" id="selectedDeliverymanID"/>
			<s:hidden name="selectedStatus" id="selectedStatus"/>
			<s:hidden name="selectedSeq" id="selectedSeq"/>
			<s:hidden name="forwardPage" id="forwardPage"/>
			<s:hidden name="declinedReason" id="declinedReason"/>
			
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
					<s:set var="isShow" value="0" />
					<s:iterator value="menuList" id="menuList" status="menuStat">
					<!-- Menu List -->
					<tr>
						<s:if test='%{0==#isShow}'>
							<td>
								<s:property value="%{restaurant.restaurantName}"/>
							</td>
							<td>
								<s:if test='%{"1".equals(restaurant.deliveryType)}'>
								Delivery
								</s:if>
								<s:if test='%{"2".equals(restaurant.deliveryType)}'>
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
					</s:iterator>
					<!-- Food Tax -->
					<tr>
						<td colspan="2"></td>
						<td>Food Tax</td>
						<td align="center"></td>
						<td align="right"></td>
						<td align="right">
							<s:property value="getText('{0,number,#,##0.00}',{restaurant.foodTaxPrice})"/>
						</td>
					</tr>
					<!-- Delivery Fee&Tax -->
					<s:if test='%{"1".equals(restaurant.deliveryType)}'>
					<tr>
						<td colspan="2"></td>
						<td>Delivery Fee</td>
						<td align="center"></td>
						<td align="right"></td>
						<td align="right">
							<s:property value="getText('{0,number,#,##0.00}',{restaurant.deliveryPrice})"/>
						</td>
					</tr>
					<tr>
						<td colspan="2"></td>
						<td>Delivery Tax</td>
						<td align="center"></td>
						<td align="right"></td>
						<td align="right">
							<s:property value="getText('{0,number,#,##0.00}',{restaurant.deliveryTaxPrice})"/>
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
							<s:property value="getText('{0,number,#,##0.00}',{restaurant.tipPrice})"/>
						</td>
					</tr>
					<!-- Sub Total -->
					<tr>
						<td colspan="2"></td>
						<td class ="bg">Sub Total</td>
						<td class ="bg" align="center"></td>
						<td class ="bg" align="right"></td>
						<td class ="bg" align="right">
							<s:property value="getText('{0,number,##,##0.00}',{restaurant.totalPriceWithTax})"/>
						</td>
					</tr>
				</table>
			</div>

			<div id="detailarea">
				<table class="tableborder" width="100%">
					<tr>
						<td class ="bg" width="350"><b>TOTAL</b></td>
						<td class ="bg" width="100" align="right"><b><s:property value="getText('{0,number,$###,##0.00}',{master.totalPriceWithTax})"/></b>
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
					<s:if test='%{"1".equals(master.paymentType)}'>
						<s:iterator value="creditCardTypeList" id="creditCardTypeList" status="outerStat">
							<s:if test='%{code.equals(master.paymentCreditType)}'>
								<td><s:property value="%{name}"/></td>
							</s:if>
						</s:iterator>
							<td><s:property value="%{master.paymentCreditNO}"/></td>
					</s:if>
					<s:elseif test='%{"2".equals(master.paymentType)}'>
						<td>DEBIT </td>
						<td><s:property value="%{master.paymentDebitNO}"/></td>
					</s:elseif>
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
							<th>Restaurant</th>
							<th width="100">Delivery Man</th>
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
							<td align="center">
								<s:property value="restaurant.deliverymanName"/>
							<td align="center">
								<s:iterator value="cityList" id="cityList" status="outerStat">
								<s:if test='%{code.equals(restaurant.deliveryCity)}'>
									<s:property value="%{name}"/>
								</s:if>
								</s:iterator>
							</td>
							<td>
								<s:property value="%{restaurant.deliveryAddress}"/>
							</td>
							<td align="center">
								<s:property value="%{restaurant.deliverySuite}"/>
							</td>
							<td align="center">
								<s:property value="%{restaurant.deliveryBuzzer}"/>
							</td>
							<td align="center">
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
							<th>Restaurant</th>
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
							<td align="center">
								<s:iterator value="cityList" id="cityList" status="outerStat">
								<s:if test='%{code.equals(restaurant.deliveryCity)}'>
									<s:property value="%{name}"/>
								</s:if>
								</s:iterator>
							</td>
							<td>
								<s:property value="%{restaurant.deliveryAddress}"/>
							</td>
							<td align="center">
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
				<!-- In case of Complete -->
				<s:if test='%{"81".equals(restaurant.orderStatus)}'>
					<input type="button" value="Refund" onClick="javascript:changeOrderStatus('92');" />
				</s:if>
				</td></tr>
			</table>
			</div>
		</s:form>
		</div>
	</div>
</div>

<!-- Google Map Panel -->
<div id="googleMap" title="Restaurant Location">
	<span id="mapHtml"></span>
</div>

<!-- Menu Detail Panel -->
<s:iterator value="menuList" id="menuList" status="menuStat">
<div id="menuDetail<s:property value='%{#menuStat.index}' />" title="Menu Detail">
	<h4><s:property value="%{menuName}"/></h4>
	<div class="div-table-noborder" style="width:100%;">
		<div class="div-table-row">
			<div class="div-table-col-noborder">
				Unit : <s:property value="%{unit}"/>
			</div>
			<div class="div-table-col-noborderr">
				Unit Price : <s:property value="getText('{0,number,$#,##0.00}',{unitPrice})"/>
			</div>
		</div>
	</div>
	<div class="div-table" style="width:100%;">
		<div class="div-table-row">
			<div class="div-table-colbg">
				[Options]
			</div>
			<div class="div-table-colbg">
				&nbsp;
			</div>
		</div>
		<div class="div-table-row">
			<div class="div-table-colh">
				Option Name
			</div>
			<div class="div-table-colh">
				Extra Charge
			</div>
		</div>
		<s:set var="restID" value="restaurantID" />
		<s:set var="menuSeq" value="seq" />
		<s:iterator value="menuoptionList" id="menuoptionList" status="optionStat">
			<s:if test='%{#restID.equals(restaurantID) && #menuSeq.equals(seq)}'>
			<div class="div-table-row">
				<div class="div-table-col">
					<s:property value="%{optionName}"/>
				</div>
				<div class="div-table-colr">
					<s:property value="getText('{0,number,$#,##0.00}',{extraCharge})"/>
				</div>
			</div>
			</s:if>
		</s:iterator>
	</div>
	
	<div class="div-table" style="width:100%;">
		<div class="div-table-row">
			<div class="div-table-colbg">
				[Special Instruction]
			</div>
		</div>
		<div class="div-table-row">
			<div class="div-table-col">
				<s:textarea cssClass="textarea100" name="instruction" readonly="true"></s:textarea>
			</div>
		</div>
	</div>
	<div class="div-table-noborder" style="width:100%;">
		<div class="div-table-row">
			<div class="div-table-col-noborderr">
				Sub Total:<s:property value="getText('{0,number,$###,##0.00}',{totalPrice})"/>
			</div>
		</div>
		<div class="div-table-row">
			<div class="div-table-col-noborderr">
				Tax:<s:property value="getText('{0,number,$###,##0.00}',{taxPrice})"/>
			</div>
		</div>
		<div class="div-table-rowbg">
			<div class="div-table-colbg-noborderr">
				Total Price:<s:property value="getText('{0,number,$###,##0.00}',{totalPriceWithTax})"/>
			</div>
		</div>
	</div>
</div>
</s:iterator> 

<!-- Decline Reason Panel -->
<div id="declineReasonPanel" title="Decline Reason">
	<p align="center"><s:radio name="reasonSel" list="declineReasonList" listKey="code" listValue="name" /></p>
	<p align="center"><input type="button" value="Decline" onClick="javascript:changeOrderStatus('71');" /></p>	
</div>
</t:putAttribute>
</t:insertDefinition>