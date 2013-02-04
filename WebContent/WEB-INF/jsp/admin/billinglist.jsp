<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/jquery.mtz.monthpicker.js"></script>

<script>
<!--
   // Search
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Go Detail Page
	function goDetail(index){
		document.getElementById("selectedID").value = document.getElementById("list[" + index + "].billingID").value;
		document.getElementById("frm").action = "initBillingDetail";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
// -->
</script>

<script>
<!--
	// Set up List table
	$(document).ready( function() {
		$('#listTable').dataTable( {
			"bJQueryUI": true,
			"bStateSave": true,
			"sPaginationType": "full_numbers"
		} );
		
		// Set up monthpicker
		$( "#fromDate, #toDate" ).monthpicker({
			pattern: 'yyyymm'
		});
		
		$( "input[type=button]" ).button();
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
			        <a href="#" class="active"><span>Billing List</span></a>
			        <a href="javascript:goPage('A307');"><span>Reporting</span></a>
		        </s:if>
		        <a href="javascript:goPage('A302');"><span>Delivery Company</span></a>
		        <a href="javascript:goPage('A303');"><span>Delivery Man</span></a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="searchBilling">
			<s:hidden name="selectedID" id="selectedID"/>
			
			<h2>Billing List</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea" style="width:600px;">
				<table width="100%">
					<tr>
						<th width="100">From : </th>
						<td>
							<s:textfield name="condition.fromDate" id="fromDate" size="8" maxlength="7"/>
						</td>
						<th width="100">To : </th>
						<td>
							<s:textfield name="condition.toDate" id="toDate" size="8" maxlength="7"/>
						</td>
						<td align="right">
							<input type="button" value="Search" onClick="javascript:search();" />
						</td>
					</tr>
				</table>
			</div>
			
			<div id="searchlistarea" style="width:600px;">
				<table id="listTable" class="display"> 
					<thead>
					<tr align="center">
						<th>Billing Month</th>
						<th>Type</th>
						<th>Invoice #</th>
						<th>Restaurant</th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:a href="javascript:goDetail('%{#outerStat.index}');"><s:property value="%{yearMonth}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].billingID" id="list[%{#outerStat.index}].billingID"/>
						</td>
						<td align="center">
							<s:iterator value="semiMonthTypeList" id="semiMonthTypeList" status="outerStat2">
								<s:if test='%{code.equals(semiMonthType)}'>
									<s:a href="javascript:goDetail('%{#outerStat.index}');"><s:property value="%{name}"/></s:a>
								</s:if>
							</s:iterator>
						</td>
						<td align="center">
							<s:a href="javascript:goDetail('%{#outerStat.index}');"><s:property value="%{billingID}"/></s:a>
						</td>
						<td>	
							<s:a href="javascript:goDetail('%{#outerStat.index}');"><s:property value="%{companyName}"/></s:a>
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