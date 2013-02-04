<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<script src="../js/jquery.mtz.monthpicker.js"></script>
<script src="../js/jquery.dataTables.min.js"></script>

<script>
<!--	
	//Search Reporting
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Download Excel
	function download(){
		document.getElementById("frm").action = "downloadReporting";
		//$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
        // Set up button
        $("input[type=button]").button();
        
        // Set up table
		$('#summaryTable, #detailTable1, #detailTable2, #detailTable3, #detailTable4, #detailTable5').dataTable( {
			"bJQueryUI": true,
			"bSort": false,
			"bPaginate": false,
			"bFilter": false
		} );
		
		// Set up tabs
		$( "#tabs" ).tabs();
		
		// Set up monthpicker
		$( "#selectedYearMonth" ).monthpicker({
			pattern: 'yyyymm'
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
			        <a href="#" class="active"><span>Reporting</span></a>
		        </s:if>
		        <a href="javascript:goPage('A302');"><span>Delivery Company</span></a>
		        <a href="javascript:goPage('A303');"><span>Delivery Man</span></a>
			</div>
		</div>
	
		<div id="contentarea">
		<h2>Monthly Report</h2>
		<s:if test="hasFieldErrors()">
			<div class="error">
				<s:fielderror/>
			</div>
		</s:if>
		
			<div id="detailarea">
				<s:form theme="simple" name="frm" id="frm" action="searchReporting">
				<div id="reporting">
					<div>
						<table width="100%">
							<tr>
								<td><h5>[Summary]</h5></td>
								<td align="right">
									Year Month: <s:textfield name="selectedYearMonth" id="selectedYearMonth" size="10" maxlength="6"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Search" onClick="javascript:search();" />
								</td>
							</tr>
						</table>
						<table id="summaryTable" class="display">
							<thead>
							<tr>
								<th rowspan=2 width="15%">Type</th>
								<th rowspan=2 width="13%">Revenue</th>
								<th rowspan=2 width="12%">Delivery Fee</th>
								<th rowspan=2 width="10%">Tip</th>
								<th rowspan=2 width="10%">Delivery Count</th>
								<th rowspan=2 width="10%">Takeout Count</th>
								<th colspan=3 width="30%"><font color="#2e6e9e">Commission</font></th>
							</tr>
							<tr>
								<th width="10%">Delivery</th>
								<th width="10%">Takeout</th>
								<th width="10%">Total</th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="summaryList" id="summaryList" status="outerStat">
								<tr>
									<td align="center"><s:property value="%{type}"/></td>
									<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{revenue})"/></td>
									<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{deliveryPrice})"/></td>
									<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{tipPrice})"/></td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryCount})"/></td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{takeoutCount})"/></td>
									<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionDelivery})"/></td>
									<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTakeout})"/></td>
									<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTotal})"/></td>
								</tr>
							</s:iterator>
							<s:iterator value="summaryRateList" id="summaryRateList" status="outerStat">
								<tr>
									<td align="center"><s:property value="%{type}"/></td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{revenue})"/>%</td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryPrice})"/>%</td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{tipPrice})"/>%</td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryCount})"/>%</td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{takeoutCount})"/>%</td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{commissionDelivery})"/>%</td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{commissionTakeout})"/>%</td>
									<td align="right"><s:property value="getText('{0,number,###,###}',{commissionTotal})"/>%</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
					<br>
					<table width="100%">
						<tr>
							<td><h5>[Detail]</h5></td>
							<td align="right">
								<input type="button" value="Excel" onClick="javascript:download();" />
							</td>
						</tr>
					</table>
					<div id="tabs">
						<ul>
							<li><a href="#tabRestaurant">Restaurant</a></li>
							<li><a href="#tabDay">Day</a></li>
							<li><a href="#tabDate">Date</a></li>
							<li><a href="#tabHour">Hour</a></li>
							<li><a href="#tabDelivery">Delivery</a></li>
						</ul>
						<div id="tabRestaurant">
							<table id="detailTable1" class="display" width="100%">
								<thead>
								<tr>
									<th rowspan=2>Restaurant</th>
									<th rowspan=2>Revenue</th>
									<th rowspan=2>Delivery Fee</th>
									<th rowspan=2>Tip</th>
									<th rowspan=2>Delivery Count</th>
									<th rowspan=2>Takeout Count</th>
									<th colspan=3><font color="#2e6e9e">Commission</font></th>
									<th colspan=2><font color="#2e6e9e">Rate Last Month</font></th>
								</tr>
								<tr>
									<th>Delivery</th>
									<th>Takeout</th>
									<th>Total</th>
									<th>Revenue</th>
									<th>Income</th>
								</tr>
								</thead>
								<tbody>
								<s:iterator value="detailList" id="detailList" status="outerStat">
									<tr>
										<td><s:property value="%{type}"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{revenue})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{deliveryPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{tipPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{takeoutCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionDelivery})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTakeout})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTotal})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateRevenue})"/>%</td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateIncome})"/>%</td>
									</tr>
								</s:iterator>
								</tbody>
							</table>
						</div>
						
						<div id="tabDay">
							<table id="detailTable2" class="display" width="100%">
								<thead>
								<tr>
									<th rowspan=2>Day</th>
									<th rowspan=2>Revenue</th>
									<th rowspan=2>Delivery Fee</th>
									<th rowspan=2>Tip</th>
									<th rowspan=2>Delivery Count</th>
									<th rowspan=2>Takeout Count</th>
									<th colspan=3><font color="#2e6e9e">Commission</font></th>
									<th colspan=2><font color="#2e6e9e">Rate Last Month</font></th>
								</tr>
								<tr>
									<th>Delivery</th>
									<th>Takeout</th>
									<th>Total</th>
									<th>Revenue</th>
									<th>Income</th>
								</tr>
								</thead>
								<tbody>
								<s:iterator value="detailListDay" id="detailList" status="outerStat">
									<tr>
										<td><s:property value="%{type}"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{revenue})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{deliveryPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{tipPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{takeoutCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionDelivery})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTakeout})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTotal})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateRevenue})"/>%</td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateIncome})"/>%</td>
									</tr>
								</s:iterator>
								</tbody>
							</table>
						</div>
						
						<div id="tabDate">
							<table id="detailTable3" class="display" width="100%">
								<thead>
								<tr>
									<th rowspan=2>Date</th>
									<th rowspan=2>Revenue</th>
									<th rowspan=2>Delivery Fee</th>
									<th rowspan=2>Tip</th>
									<th rowspan=2>Delivery Count</th>
									<th rowspan=2>Takeout Count</th>
									<th colspan=3><font color="#2e6e9e">Commission</font></th>
									<th colspan=2><font color="#2e6e9e">Rate Last Month</font></th>
								</tr>
								<tr>
									<th>Delivery</th>
									<th>Takeout</th>
									<th>Total</th>
									<th>Revenue</th>
									<th>Income</th>
								</tr>
								</thead>
								<tbody>
								<s:iterator value="detailListDate" id="detailList" status="outerStat">
									<tr>
										<td><s:property value="%{type}"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{revenue})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{deliveryPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{tipPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{takeoutCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionDelivery})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTakeout})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTotal})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateRevenue})"/>%</td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateIncome})"/>%</td>
									</tr>
								</s:iterator>
								</tbody>
							</table>
						</div>
						
						<div id="tabHour">
							<table id="detailTable4" class="display" width="100%">
								<thead>
								<tr>
									<th rowspan=2>Hour</th>
									<th rowspan=2>Revenue</th>
									<th rowspan=2>Delivery Fee</th>
									<th rowspan=2>Tip</th>
									<th rowspan=2>Delivery Count</th>
									<th rowspan=2>Takeout Count</th>
									<th colspan=3><font color="#2e6e9e">Commission</font></th>
									<th colspan=2><font color="#2e6e9e">Rate Last Month</font></th>
								</tr>
								<tr>
									<th>Delivery</th>
									<th>Takeout</th>
									<th>Total</th>
									<th>Revenue</th>
									<th>Income</th>
								</tr>
								</thead>
								<tbody>
								<s:iterator value="detailListHour" id="detailList" status="outerStat">
									<tr>
										<td><s:property value="%{type}"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{revenue})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{deliveryPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{tipPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{takeoutCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionDelivery})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTakeout})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTotal})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateRevenue})"/>%</td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateIncome})"/>%</td>
									</tr>
								</s:iterator>
								</tbody>
							</table>
						</div>
						
						<div id="tabDelivery">
							<table id="detailTable5" class="display" width="100%">
								<thead>
								<tr>
									<th rowspan=2>Delivery</th>
									<th rowspan=2>Revenue</th>
									<th rowspan=2>Delivery Fee</th>
									<th rowspan=2>Tip</th>
									<th rowspan=2>Delivery Count</th>
									<th rowspan=2>Takeout Count</th>
									<th colspan=3><font color="#2e6e9e">Commission</font></th>
									<th colspan=2><font color="#2e6e9e">Rate Last Month</font></th>
								</tr>
								<tr>
									<th>Delivery</th>
									<th>Takeout</th>
									<th>Total</th>
									<th>Revenue</th>
									<th>Income</th>
								</tr>
								</thead>
								<tbody>
								<s:iterator value="detailListDelivery" id="detailList" status="outerStat">
									<tr>
										<td><s:property value="%{type}"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{revenue})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{deliveryPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{tipPrice})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{deliveryCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{takeoutCount})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionDelivery})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTakeout})"/></td>
										<td align="right"><s:property value="getText('{0,number,$###,##0.00}',{commissionTotal})"/></td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateRevenue})"/>%</td>
										<td align="right"><s:property value="getText('{0,number,###,###}',{rateIncome})"/>%</td>
									</tr>
								</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				</s:form>
			</div>
		</div>
	</div>
</div>

</t:putAttribute>
</t:insertDefinition>