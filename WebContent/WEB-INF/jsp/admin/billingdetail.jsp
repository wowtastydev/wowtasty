<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<script src="../js/jquery.dataTables.min.js"></script>

<script>
<!--
    // Delete
	function deleteBill(){
		if(confirm("Do you want to delete the bill data?")) {
			document.getElementById("frm").action = "deleteBilling";
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}
	}
	
	// Print report
	function report(){
		//$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Download Excel
	function download(){
		document.getElementById("frm").action = "downloadBilling";
		//$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// delete billing bill detail list rows
	function delRow(){
		var tbody = document.getElementById("listTable").getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		
        for(var i=0; i<rowCount; i++) {
            var row = tbody.rows[i];
         	// In case strust itterator, checkbox is node[1]
            var chkbox = row.cells[0].childNodes[1];
            if(null != chkbox && true == chkbox.checked) {
            	tbody.deleteRow(i);
                rowCount--;
                i--;
            }
        }
	}
	
	// Show Order detail panel
	function viewDetail(index) {
		var orderID = document.getElementById("billList["+ index +"].orderID").value;
		var restaurantID = document.getElementById("billList[" + index + "].restaurantID").value;
		
		if (orderID == "" || restaurantID == "") {
			// Show Error Message
			errorMessage.innerHTML = "There is no order detail.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
	
		$.ajax({
			type: "POST",
	        url: "initBillingOrderDetail",
	        dataType: "json",
	        data: { "selectedOrderID": orderID, "selectedRestaurantID": restaurantID},
		})
	    // callback handler that will be called on success
	    .done(function(response){
            // Set Order detail panel
            setOrderDetailPanel(response);
            // Open Order detail panel
            $( "#orderDetailPanel" ).dialog( "open" );
	        
	    })
	    .fail(function(request, status, error){
        	// In case of request session time out, show login page
        	document.getElementById("frm").action = "initLogin";
        	document.getElementById("frm").submit();
	    })   
	    ;
	}
	
	// Set Order detail panel
	function setOrderDetailPanel(response) {
		var master = response.master;
		var restaurant = response.restaurant;
		var menuList = response.menuList;
		
		// Set up general order info
		detailOrderID.innerHTML = master.orderID;
		detailOrderMemberEmail.innerHTML = master.orderMemberEmail;
		detailRestaurantName.innerHTML =  restaurant.restaurantName;
		// Format DeliveryTime MM/dd/yyyy HH:mm:ss
		var d = new Date(master.deliveryTime),
		    dformat = [d.getMonth()+1,
		               d.getDate(),
		               d.getFullYear()].join('/')+
		              ' ' +
		              [d.getHours(),
		               d.getMinutes(),
		               d.getSeconds()].join(':');
		detailDeliveryTime.innerHTML = dformat;
		
		// Set up menu list
		var tbody = document.getElementById('detailListTable');
		var obj_row;
		var totalTax = 0.0;
		var td_item;
		var txt_item;
		var td_unit;
		var txt_unit;
		var td_unitPrice;
		var txt_unitPrice;
		var td_totalPrice;
		var txt_totalPrice;
		
		// Clear all rows
		while ( tbody.firstChild ) tbody.removeChild( tbody.firstChild );
		
		// Set Header
		obj_row = document.createElement("DIV");
		obj_row.setAttribute("class", "div-table-row");
		tbody.appendChild(obj_row);
		
		td_item = document.createElement("DIV");
		td_item.setAttribute("class", "div-table-colh");
		obj_row.appendChild(td_item);
		
		txt_item = document.createTextNode("Item");
		td_item.appendChild(txt_item);
	 
		td_unit = document.createElement("DIV");
		td_unit.setAttribute("class", "div-table-colh");
		obj_row.appendChild(td_unit);
		
		txt_unit = document.createTextNode("Unit");
		td_unit.appendChild(txt_unit);
		
		td_unitPrice = document.createElement("DIV");
		td_unitPrice.setAttribute("class", "div-table-colh");
		obj_row.appendChild(td_unitPrice);
		
		txt_unitPrice = document.createTextNode("Unit Price($)");
		td_unitPrice.appendChild(txt_unitPrice);
		
		td_totalPrice = document.createElement("DIV");
		td_totalPrice.setAttribute("class", "div-table-colh");
		obj_row.appendChild(td_totalPrice);
		
		txt_totalPrice = document.createTextNode("Total Price($)");
		td_totalPrice.appendChild(txt_totalPrice);
		
		// Set body
		for (var i=0; i<menuList.length; i++) {
			obj_row = document.createElement("DIV");
			obj_row.setAttribute("class", "div-table-row");
			tbody.appendChild(obj_row);
			
			td_item = document.createElement("DIV");
			td_item.setAttribute("class", "div-table-col");
			obj_row.appendChild(td_item);
			
			txt_item = document.createTextNode(menuList[i].menuName);
			td_item.appendChild(txt_item);
		 
			td_unit = document.createElement("DIV");
			td_unit.setAttribute("class", "div-table-col");
			td_unit.setAttribute("style", "text-align:center");
			obj_row.appendChild(td_unit);
			
			txt_unit = document.createTextNode(menuList[i].unit);
			td_unit.appendChild(txt_unit);
			
			td_unitPrice = document.createElement("DIV");
			td_unitPrice.setAttribute("class", "div-table-col");
			td_unitPrice.setAttribute("style", "text-align:right");
			obj_row.appendChild(td_unitPrice);
			
			txt_unitPrice = document.createTextNode(menuList[i].unitPrice);
			td_unitPrice.appendChild(txt_unitPrice);
			
			td_totalPrice = document.createElement("DIV");
			td_totalPrice.setAttribute("class", "div-table-col");
			td_totalPrice.setAttribute("style", "text-align:right");
			obj_row.appendChild(td_totalPrice);
			
			txt_totalPrice = document.createTextNode(menuList[i].totalPrice);
			td_totalPrice.appendChild(txt_totalPrice);
			
			totalTax =+ menuList[i].taxPrice;
		}
		
		// Set up Delivery
		obj_row = document.createElement("DIV");
		obj_row.setAttribute("class", "div-table-row");
		tbody.appendChild(obj_row);
		
		td_item = document.createElement("DIV");
		td_item.setAttribute("class", "div-table-col");
		obj_row.appendChild(td_item);
		
		txt_item = document.createTextNode("Delivery");
		td_item.appendChild(txt_item);
	 
		td_unit = document.createElement("DIV");
		td_unit.setAttribute("class", "div-table-col");
		td_unit.setAttribute("style", "text-align:center");
		obj_row.appendChild(td_unit);
		
		td_unitPrice = document.createElement("DIV");
		td_unitPrice.setAttribute("class", "div-table-col");
		td_unitPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_unitPrice);
		
		td_totalPrice = document.createElement("DIV");
		td_totalPrice.setAttribute("class", "div-table-col");
		td_totalPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_totalPrice);
		
		txt_totalPrice = document.createTextNode(restaurant.deliveryPrice);
		td_totalPrice.appendChild(txt_totalPrice);
		
		totalTax =+ restaurant.deliveryTaxPrice;
		
		// Set up Tax
		obj_row = document.createElement("DIV");
		obj_row.setAttribute("class", "div-table-row");
		tbody.appendChild(obj_row);
		
		td_item = document.createElement("DIV");
		td_item.setAttribute("class", "div-table-col");
		obj_row.appendChild(td_item);
		
		txt_item = document.createTextNode("Tax");
		td_item.appendChild(txt_item);
	 
		td_unit = document.createElement("DIV");
		td_unit.setAttribute("class", "div-table-col");
		td_unit.setAttribute("style", "text-align:center");
		obj_row.appendChild(td_unit);
		
		td_unitPrice = document.createElement("DIV");
		td_unitPrice.setAttribute("class", "div-table-col");
		td_unitPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_unitPrice);
		
		td_totalPrice = document.createElement("DIV");
		td_totalPrice.setAttribute("class", "div-table-col");
		td_totalPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_totalPrice);
		
		txt_totalPrice = document.createTextNode(totalTax);
		td_totalPrice.appendChild(txt_totalPrice);
		
		// Set up Tip
		obj_row = document.createElement("DIV");
		obj_row.setAttribute("class", "div-table-row");
		tbody.appendChild(obj_row);
		
		td_item = document.createElement("DIV");
		td_item.setAttribute("class", "div-table-col");
		obj_row.appendChild(td_item);
		
		txt_item = document.createTextNode("Tip");
		td_item.appendChild(txt_item);
	 
		td_unit = document.createElement("DIV");
		td_unit.setAttribute("class", "div-table-col");
		td_unit.setAttribute("style", "text-align:center");
		obj_row.appendChild(td_unit);
		
		td_unitPrice = document.createElement("DIV");
		td_unitPrice.setAttribute("class", "div-table-col");
		td_unitPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_unitPrice);
		
		td_totalPrice = document.createElement("DIV");
		td_totalPrice.setAttribute("class", "div-table-col");
		td_totalPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_totalPrice);
		
		txt_totalPrice = document.createTextNode(restaurant.tipPrice);
		td_totalPrice.appendChild(txt_totalPrice);
		
		// Set up Total price
		obj_row = document.createElement("DIV");
		obj_row.setAttribute("class", "div-table-rowbg");
		tbody.appendChild(obj_row);
		
		td_item = document.createElement("DIV");
		td_item.setAttribute("class", "div-table-col");
		obj_row.appendChild(td_item);
		
		txt_item = document.createTextNode("Total Price");
		td_item.appendChild(txt_item);
	 
		td_unit = document.createElement("DIV");
		td_unit.setAttribute("class", "div-table-col");
		td_unit.setAttribute("style", "text-align:center");
		obj_row.appendChild(td_unit);
		
		td_unitPrice = document.createElement("DIV");
		td_unitPrice.setAttribute("class", "div-table-col");
		td_unitPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_unitPrice);
		
		td_totalPrice = document.createElement("DIV");
		td_totalPrice.setAttribute("class", "div-table-col");
		td_totalPrice.setAttribute("style", "text-align:right");
		obj_row.appendChild(td_totalPrice);
		
		txt_totalPrice = document.createTextNode(restaurant.totalPriceWithTax);
		td_totalPrice.appendChild(txt_totalPrice);
	}
	
	// Close Order detail panel
	function closeOrderDetailPanel() {
		$( "#orderDetailPanel" ).dialog( "close" );
	}

// -->
</script>

<script>
<!--
	// Set up List table
	$(document).ready( function() {
		$('#listTable').dataTable( {
			"bJQueryUI": true,
			"bSort": false,
			"bPaginate": false,
			"bFilter": false
		} );
		
		// Set up button
		$( "input[type=button]" ).button();
		
		// Order Detail Panel
        $( "#orderDetailPanel" ).dialog({
            autoOpen: false,
            width: 600,
            height: 450
        });
	} );
	
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<s:if test='%{uservo.auth < 15}'>
					<a href="javascript:goPage('A301');"><span>Admin Set-up</span></a>
					<a href="javascript:goPage('A312');"><span>Balance List</span></a>
			        <a href="javascript:goPage('A304');"><span>Billing List</span></a>
			        <a href="javascript:goPage('A307');"><span>Reporting</span></a>
		        </s:if>
		        <a href="javascript:goPage('A302');"><span>Delivery Company</span></a>
		        <a href="javascript:goPage('A303');"><span>Delivery Man</span></a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="printBilling">
			<s:hidden name="selectedOrderID" id="selectedOrderID"/>
			<s:hidden name="selectedRestaurantID" id="selectedRestaurantID"/>
			<s:hidden name="billVO.billingID"/>
			<s:hidden name="billVO.companyType"/>
			<s:hidden name="billVO.companyID"/>
			<s:hidden name="billVO.companyName"/>
			<s:hidden name="billVO.yearMonth"/>
			<s:hidden name="billVO.semiMonthType"/>
			<s:hidden name="billVO.billFrom"/>
			<s:hidden name="billVO.billTo"/>
			<s:hidden name="billVO.issueDate"/>
			<s:hidden name="billVO.billAddress"/>
			<s:hidden name="billVO.billSuite"/>
			<s:hidden name="billVO.billCity"/>
			<s:hidden name="billVO.billProvince"/>
			<s:hidden name="billVO.billPostalCode"/>
			<s:hidden name="billVO.billAccountNO"/>
			<s:hidden name="billVO.salesAmount"/>
			<s:hidden name="billVO.foodAmount"/>
			<s:hidden name="billVO.commissionAmount"/>
			<s:hidden name="billVO.cashCommissionAmount"/>
			<s:hidden name="billVO.deliveryAmount"/>
			<s:hidden name="billVO.tipAmount"/>
			<s:hidden name="billVO.adjustmentAmount"/>
			
			<h2>Bill Detail</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="detailarea">
				<table width="100%">
					<tr>
						<th width="100">Billing ID :</th>
						<td>
							<s:property value="%{billVO.billingID}" />
						</td>
						<th width="100">Restaurant :</th>
						<td>
							<s:property value="%{billVO.companyName}" />
						</td>
						<th width="100">Billing Month :</th>
						<td>
							<s:property value="%{billVO.yearMonth}" />
							<s:iterator value="semiMonthTypeList" id="semiMonthTypeList" status="outerStat2">
								<s:if test='%{code.equals(billVO.semiMonthType)}'>
									(<s:property value="%{name}"/>)
								</s:if>
							</s:iterator>
						
						</td>
					</tr>
					<tr>
						<th>Terms: </th>
						<td>
							<s:date name="%{billVO.billFrom}" var="billFromFormatted" format="MM/dd/yyyy"/>
							<s:property value="%{billFromFormatted}"/>~
							<s:date name="%{billVO.billTo}" var="billToFormatted" format="MM/dd/yyyy"/>
							<s:property value="%{billToFormatted}"/>
						</td>
						<th>Issue Date: </th>
						<td>
							<s:date name="%{billVO.issueDate}" var="issueDateFormatted" format="MM/dd/yyyy"/>
							<s:property value="%{issueDateFormatted}"/>
						</td>
						<td colspan=2 align="right">
							<FONT color=red>* Including TAX</FONT>
						</td>
					</tr>
				</table>
				<table id="listTable" class="display"> 
					<thead>
					<tr align="center">
						<th>Date</th>
						<th>Time</th>
						<th>Type</th>
						<th>Pay</th>
						<th>Items</th>
						<th>Food Amt<FONT color=red>*</FONT></th>
						<th>Fees<FONT color=red>*</FONT></th>
						<th>Delivery<FONT color=red>*</FONT></th>
						<th>Tip</th>
						<th>Total<FONT color=red>*</FONT></th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="billList" id="billList" status="outerStat">
					<tr>
						<td align="center">
							<s:date name="%{deliveryTime}" var="deliveryDateFormatted" format="MM/dd/yyyy"/>
							<s:a href="javascript:viewDetail('%{#outerStat.index}');"><s:property value="%{deliveryDateFormatted}"/></s:a>
							<s:hidden name="billList[%{#outerStat.index}].billingID" id="billList[%{#outerStat.index}].billingID"/>
							<s:hidden name="billList[%{#outerStat.index}].orderID" id="billList[%{#outerStat.index}].orderID"/>
							<s:hidden name="billList[%{#outerStat.index}].restaurantID" id="billList[%{#outerStat.index}].restaurantID"/>
							<s:hidden name="billList[%{#outerStat.index}].seq"/>
							<s:hidden name="billList[%{#outerStat.index}].orderTime"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryTime"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryType"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryTypeName"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryCompanyID"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryCompanyType"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryCompanyTypeName"/>
							<s:hidden name="billList[%{#outerStat.index}].deliverymanID"/>
							<s:hidden name="billList[%{#outerStat.index}].paymentType"/>
							<s:hidden name="billList[%{#outerStat.index}].paymentTypeName"/>
							<s:hidden name="billList[%{#outerStat.index}].orderMemberID"/>
							<s:hidden name="billList[%{#outerStat.index}].totalMenuName"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryPrice"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryTaxPrice"/>
							<s:hidden name="billList[%{#outerStat.index}].deliveryPriceWithTax"/>
							<s:hidden name="billList[%{#outerStat.index}].foodTotalPrice"/>
							<s:hidden name="billList[%{#outerStat.index}].foodTaxPrice"/>
							<s:hidden name="billList[%{#outerStat.index}].foodPriceWithTax"/>
							<s:hidden name="billList[%{#outerStat.index}].totalPrice"/>
							<s:hidden name="billList[%{#outerStat.index}].tipPrice"/>
							<s:hidden name="billList[%{#outerStat.index}].totalPriceWithTax"/>
							<s:hidden name="billList[%{#outerStat.index}].commissionPrice"/>
							<s:hidden name="billList[%{#outerStat.index}].orderStatus"/>
						</td>
						<td align="center">
							<s:date name="%{deliveryTime}" var="deliveryTimeFormatted" format="HH:mm"/>
							<s:a href="javascript:viewDetail('%{#outerStat.index}');"><s:property value="%{deliveryTimeFormatted}"/></s:a>
						</td>
						<td align="center">
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="%{deliveryTypeName}"/>
							</s:a>
						</td>
						<td align="center">
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="%{paymentTypeName}"/>
							</s:a>
						</td>
						<td>
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="%{totalMenuName}"/>
							</s:a>
						</td>
						<td align="right">
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="getText('{0,number,##,##0.00}',{foodPriceWithTax})"/>
							</s:a>
						</td>
						<td align="right">
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="getText('{0,number,##,##0.00}',{commissionPrice})"/>
							</s:a>
						</td>
						<td align="right">
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="getText('{0,number,##0.00}',{deliveryPriceWithTax})"/>
							</s:a>
						</td>
						<td align="right">
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="getText('{0,number,##0.00}',{tipPrice})"/>
							</s:a>
						</td>
						<td align="right">
							<s:a href="javascript:viewDetail('%{#outerStat.index}');">
								<s:property value="getText('{0,number,##,##0.00}',{totalPrice})"/>
							</s:a>
						</td>
					</tr>
					</s:iterator>
					</tbody>
				</table>
				<table width="100%">
					<tr>
						<td> 
							<input type="button" value="List" onClick ="javascript:goPage('A304');"/>
						</td>
						<td align="right">
							<input type="button" value="Excel" onClick="javascript:download();" />
							<input type="button" value="Report" onClick="javascript:report();" />
							<input type="button" value="Delete" onClick="javascript:deleteBill();" />
						</td>
					</tr>
				</table>
			</div>
			
		</s:form>
		</div>
	</div>
</div>

<!-- Order Detail Panel -->
<div id="orderDetailPanel" title="Order Detail">
	<div class="div-table" style="width:550px;">
		<div class="div-table-row">
			<div class="div-table-colh">
	  			Order ID :
			</div>
		  	<div class="div-table-col">
		  		<span id="detailOrderID"></span>
		  	</div>
	
		  	<div class="div-table-colh">
	  			Order By :
			</div>
		  	<div class="div-table-col">
		  		<span id="detailOrderMemberEmail"></span>
		  	</div>
	  	</div>
	  	<div class="div-table-row">
			<div class="div-table-colh">
	  			Restaurant :
			</div>
		  	<div class="div-table-col">
		  		<span id="detailRestaurantName"></span>
		  	</div>
	
		  	<div class="div-table-colh">
	  			Delivery Time :
			</div>
		  	<div class="div-table-col">
		  		<span id="detailDeliveryTime"></span>
		  	</div>
	  	</div>
	  </div>
	<div class="div-table" id="detailListTable"  style="width:550px;">
 	</div>
	
	<div style="width:550px;text-align:right;margin: 0px 0px 0px 7px;">
		<input type="button" value="Close" onClick="javascript:closeOrderDetailPanel();" />
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>