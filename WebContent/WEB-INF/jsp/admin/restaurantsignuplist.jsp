<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<script src="../js/jquery.dataTables.min.js"></script>

<script>
<!--
   // Search
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	//Change Restaurant Status
	function changeStatus(status){
		var msg = "";
		// Set message
		if (status == 3) {
			//　Approval
			msg="Do you want to approve the restaurants?";
		} else {
			//　Approval
			msg="Do you want to decline the restaurants?";
		}
		if(confirm(msg)) {
			document.getElementById("selectedStatus").value = status;
			document.getElementById("frm").action = "changeRestaurantStatusAll";
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}

	}
	
	// Go Edit Page
	function goEdit(index){
		document.getElementById("selectedID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("frm").action = "initRestaurantSignupEdit";
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
			"sPaginationType": "full_numbers"
		} );
		
		// Set up datepicker
		$( "#fromDate" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: true
        });
	    
	    $( "#toDate" ).datepicker({
            showOtherMonths: true,
            selectOtherMonths: true
        });
		
		$( "input[type=button]" ).button();
	} );
	
//-->
</script>
<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('A203');">Restaurant List</a>
				<a href="javascript:goPage('A204');">Add Restaurant</a>
				<a href="#" class="active">Sign-up List</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="searchRestaurantSignup">
			<s:hidden name="selectedID" id="selectedID"/>
			<s:hidden name="selectedStatus" id="selectedStatus"/>
			
			<h2>Sign up List</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea">
				<table width="100%">
					<tr>
						<th width="100">From : </th>
						<td>
							<s:textfield name="condition.fromDate" id="fromDate" size="10" maxlength="10"/>
						</td>
						<th width="100">To : </th>
						<td>
							<s:textfield name="condition.toDate" id="toDate" size="10" maxlength="10"/>
						</td>
						<td>
							<input type="button" value="Search" onClick="javascript:search();" />
						</td>
						<td align="right">
							<input type="button" value="Approve" onClick="javascript:changeStatus(3);" />
							<input type="button" value="Decline" onClick="javascript:changeStatus(8);" />
						</td>
					</tr>
				</table>
			</div>
			
			<div id="searchlistarea">
				<table id="listTable" class="display"> 
					<thead>
					<tr align="center">
						<th>Chk</th>
						<th>Restaurant Name</th>
						<th>Email</th>
						<th>Register Date</th>
						<th>City</th>
						<th>Address</th>
						<th>Tel</th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:checkbox name="list[%{#outerStat.index}].chk" id="chk"/>
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{name}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].restaurantID" id="list[%{#outerStat.index}].restaurantID"/>
							<s:hidden name="list[%{#outerStat.index}].name" />
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{email}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].email" />
						</td>
						<td align="center">
							<s:date name="%{registerDate}" var="registerDateFormatted" format="MM/dd/yyyy"/>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{registerDateFormatted}"/></s:a>
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');">
							<s:iterator value="cityList" id="cityList" status="outerStat">
								<s:if test='%{code.equals(city)}'>
									<s:property value="%{shortName}"/>
								</s:if>
							</s:iterator>
							</s:a>
						</td>
						<td>	
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{address}"/></s:a>
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{telephone}"/></s:a>
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