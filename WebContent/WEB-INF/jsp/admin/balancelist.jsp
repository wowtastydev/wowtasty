<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/jquery.combobox.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/timepicker.css" media="screen"  />
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/bootstrap-timepicker.js"></script>
<script src="../js/jquery.combobox.js"></script>

<script>
<!--
   // Search Balance List
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	// Add adjustment
	function add(){
		document.getElementById("frm").action = "addAdjustment";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// delete adjustment
	function del(index){
		if(confirm("Do you want to delete the adjustment record?")) {
			document.getElementById("selectedSeq").value = document.getElementById("list[" + index + "].seq").value;
			document.getElementById("frm").action = "delAdjustment";
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}
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
	    
		// Set up datepicker
	    $( "#fromDate, #toDate, #registerDate" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: true
        });
	    
	 	// Set up Combobox
		$("#restaurantID").combobox();
		
		// Set up button
		$( "input[type=button]" ).button();
		
		// Set up timepicker
		$('.timepicker').timepicker({
            minuteStep: 15,
            showInputs: false,
            disableFocus: false,
            defaultTime: false,
            showMeridian: false,
            showSeconds: false
        });
		
		// Set up spinner
        $( "#price" ).spinner({
            min: -999999.99,
            max: 999999.99,
            step: 0.01,
            numberFormat: "n"
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
					<a href="#" class="active"><span>Balance List</span></a>
			        <a href="javascript:goPage('A304');"><span>Billing List</span></a>
			        <a href="javascript:goPage('A307');"><span>Reporting</span></a>
		        </s:if>
		        <a href="javascript:goPage('A302');"><span>Delivery Company</span></a>
		        <a href="javascript:goPage('A303');"><span>Delivery Man</span></a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="searchBalanceList">
			<s:hidden name="selectedFromDate"/>
			<s:hidden name="selectedToDate"/>
			<s:hidden name="selectedRestaurantID"/>
			<s:hidden name="selectedSeq" id="selectedSeq" />
			
			<h2>Balance List</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea">
				<table width="100%">
					<tr>
						<td colspan=2  width="50%"></td>
						<td colspan=3  width="50%"></td>
					</tr>
					<tr>
						<th width="120">Restaurant<FONT color=red>*</FONT>: </th>
						<td>
							<s:select name="condition.restaurantID" id="restaurantID" list="restaurantList" listKey="restaurantID" listValue="name" headerKey="" headerValue="" />
						</td>
						<th width="120">From ~ To: </th>
						<td>
							<s:textfield name="condition.fromDate" id="fromDate" size="12" maxlength="10"/> ~ <s:textfield name="condition.toDate" id="toDate" size="12" maxlength="10"/>
						</td>
						<td align="right">
							<input type="button" value="Search" onClick="javascript:search();" />
						</td>
					</tr>
					<tr>
						<th>Current Balance Total: </th>
						<td>$<s:property value="getText('{0,number,###,###,##0.00}',{totolCurrentBalance})"/></td>
						<th>Current Avalable Total: </th>
						<td colspan=2>$<s:property value="getText('{0,number,###,###,##0.00}',{totolCurrentAvailable})"/></td>
					</tr>
					<tr>
						<td colspan=7><hr></td>
					</tr>
				</table>
				
			</div>
			
			<div id="searchlistarea">
				<table width="100%">
					<tr>
						<td>
						<b>
							<s:if test='"".equals(selectedFromDate) && "".equals(selectedToDate) '>
								Total: 
							</s:if>
							<s:else>
								Total(<s:property value="%{selectedFromDate}"/>~<s:property value="%{selectedToDate}"/>): 
							</s:else>
							$<s:property value="getText('{0,number,###,###,##0.00}',{totolTermAmount})"/>
						</b>
						</td>
						<td align="right">
							<FONT color=red>* Including TAX</FONT>
						</td>
					</tr>
				</table>
				<table id="listTable" class="display"> 
					<thead>
					<tr align="center">
						<th>Date</th>
						<th>Time</th>
						<th>Items</th>
						<th>Food Amt<FONT color=red>*</FONT></th>
						<th>Fees<FONT color=red>*</FONT></th>
						<th>Delivery<FONT color=red>*</FONT></th>
						<th>Tip</th>
						<th>Total<FONT color=red>*</FONT></th>
						<th>Balance<FONT color=red>*</FONT></th>
						<th>Note</th>
					</tr>
					</thead>		
					<tbody>
					<s:if test='%{!"".equals(selectedRestaurantID)}'>
						<tr>
							<td align="center">
								<s:textfield name="registerDate" id="registerDate" size="12" maxlength="10"/>
							</td>
							<td align="center">
								<s:textfield name="registerTime" id="registerTime" size="5" maxlength="5" cssClass="timepicker"/>
							</td>
							<td>
								<s:textfield name="adjustItem" id="adjustItem" size="40" maxlength="50"/>
							</td>
							<td>
								&nbsp;
							</td>
							<td>	
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td align="center">	
								<s:textfield name="price" id="price" size="5" maxlength="10"/>
							</td>
							<td align="center">	
								&nbsp;
							</td>
							<td align="center">	
								<input type="button" height="10" value="Add" onClick="javascript:add();" />
							</td>
						</tr>
					</s:if>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:date name="%{deliveryTime}" var="deliveryDateFormatted" format="MM/dd/yyyy"/>
							<s:property value="%{deliveryDateFormatted}"/>
						</td>
						<td align="center">
							<s:date name="%{deliveryTime}" var="deliveryTimeFormatted" format="HH:mm"/>
							<s:property value="%{deliveryTimeFormatted}"/>
						</td>
						<td>
							<s:property value="%{totalMenuName}"/>
						</td>
						<td align="center">	
							<s:property value="getText('{0,number,##,###,##0.00}',{foodAmount})"/>
						</td>
						<td align="center">	
							<s:property value="getText('{0,number,##,###,##0.00}',{commissionAmount})"/>
						</td>
						<td align="center">	
							<s:property value="getText('{0,number,##,###,##0.00}',{deliveryAmount})"/>
						</td>
						<td align="center">	
							<s:property value="getText('{0,number,##,###,##0.00}',{tipAmount})"/>
						</td>
						<td align="center">	
							<s:property value="getText('{0,number,##,###,##0.00}',{totalAmount})"/>
						</td>
						<td align="center">	
							<s:property value="getText('{0,number,###,###,##0.00}',{balance})"/>
						</td>
						<td align="center">	
							<s:if test='%{"Y".equals(adjustmentFlag)}'>
								<a href="javascript:del(<s:property value='%{#outerStat.index}' />);"><img src="../images/icon-delete.gif" title="delete adjustment"/></a>
								<s:hidden name="list[%{#outerStat.index}].seq" id="list[%{#outerStat.index}].seq"/>
							</s:if>
							<s:else>
								<s:property value="%{note}"/>
							</s:else>
							
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