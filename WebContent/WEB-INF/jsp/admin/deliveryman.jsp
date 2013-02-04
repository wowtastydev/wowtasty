<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/jquery.combobox.css" media="screen" />
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/jquery.combobox.js"></script>

<script>
<!--
	// Save deliveryman info
	function save(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
    // Initialize menu data to edit
	function initEdit(index){
		document.getElementById("deliveryCompanyID").value = document.getElementById("list[" + index + "].deliveryCompanyID").value;
		document.getElementById("deliverymanID").value = document.getElementById("list[" + index + "].deliverymanID").value;
		deliverymanIDHtml.innerHTML = document.getElementById("list[" + index + "].deliverymanID").value;
		document.getElementById("firstName").value = document.getElementById("list[" + index + "].firstName").value;
		document.getElementById("lastName").value = document.getElementById("list[" + index + "].lastName").value;
		document.getElementById("telephone").value = document.getElementById("list[" + index + "].telephone").value;
		document.getElementById("email").value = document.getElementById("list[" + index + "].email").value;
		document.getElementById("address").value = document.getElementById("list[" + index + "].address").value;
		document.getElementById("suite").value = document.getElementById("list[" + index + "].suite").value;
		document.getElementById("city").value = document.getElementById("list[" + index + "].city").value;
		$("#city").val(document.getElementById("list[" + index + "].city").value);
		$('#city').siblings('.ui-combobox').find('.ui-autocomplete-input').text("Coquitlam");
        
		document.getElementById("province").value = document.getElementById("list[" + index + "].province").value;
		document.getElementById("postalCode").value = document.getElementById("list[" + index + "].postalCode").value;
		document.getElementById("naFlag").value = document.getElementById("list[" + index + "].naFlag").value;
		// Open delivery man detail accordion
        $("#deliveryman").accordion('activate', 1 );
	}
	
	// Clear deliveryman info
	function clearMan(){
		document.getElementById("deliverymanID").value = "0";
		deliverymanIDHtml.innerHTML = "0";
		document.getElementById("firstName").value = "";
		document.getElementById("lastName").value = "";
		document.getElementById("telephone").value = "";
		document.getElementById("email").value = "";
		document.getElementById("address").value = "";
		document.getElementById("suite").value = "";
		document.getElementById("city").value = "";
		document.getElementById("province").value = "";
		document.getElementById("postalCode").value = "";
		document.getElementById("naFlag").value = "0";
		
	}
	
	// Delete deliveryman info
	function del(){
		if (document.getElementById("deliverymanID").value == 0) {
			// Show Error Message
			errorMessage.innerHTML = "Please select Delivery Man first.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "delDeliveryMan";
		document.getElementById("frm").submit();
	}
	
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
		// Set up list table
		$('#listTable').dataTable( {
			"bJQueryUI": true,
			"sPaginationType": "full_numbers"
		} );
        
        // Set up accordion
        $( "#deliveryman" ).accordion({
        	active: <s:property value="active"/>,
        	collapsible: true,
        	alwaysOpen: false,
            heightStyle: "content"
        });
        
        // Set up button
        $("input[type=button]").button();
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
		        <a href="#" class="active"><span>Delivery Man</span></a>
			</div>
		</div>
	
		<div id="contentarea">
		<h2>Delivery Man</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="detailarea">
				<s:form theme="simple" name="frm" id="frm" action="saveDeliveryMan">
				<div id="deliveryman">
					<h3>Delivery Man List</h3>
					<div>
						<table id="listTable" class="display"> 
							<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Telephone</th>
								<th>Email</th>
								<th>Address</th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="list" id="list" status="outerStat">
							<tr>
								<td>
									<s:a href="javascript:initEdit('%{#outerStat.index}');"><s:property value="%{firstName}"/></s:a>
									<s:hidden name ="deliveryCompanyID" id ="list[%{#outerStat.index}].deliveryCompanyID" />
									<s:hidden name ="deliverymanID" id ="list[%{#outerStat.index}].deliverymanID" />
									<s:hidden name ="naFlag" id ="list[%{#outerStat.index}].naFlag" />
									<s:hidden name ="firstName" id ="list[%{#outerStat.index}].firstName" />
								</td>
								<td>
									<s:a href="javascript:initEdit('%{#outerStat.index}');"><s:property value="%{lastName}"/></s:a>
									<s:hidden name ="lastName" id ="list[%{#outerStat.index}].lastName" />
								</td>
								<td align="center">
									<s:a href="javascript:initEdit('%{#outerStat.index}');"><s:property value="%{telephone}"/></s:a>
									<s:hidden name ="telephone" id ="list[%{#outerStat.index}].telephone" />
								</td>
								<td>
									<s:a href="javascript:initEdit('%{#outerStat.index}');"><s:property value="%{email}"/></s:a>
									<s:hidden name ="email" id ="list[%{#outerStat.index}].email" />
								</td>
								<td>
									<s:a href="javascript:initEdit('%{#outerStat.index}');"><s:property value="%{address}"/></s:a>
									<s:hidden name ="address" id ="list[%{#outerStat.index}].address" />
									<s:hidden name ="suite" id ="list[%{#outerStat.index}].suite" />
									<s:hidden name ="city" id ="list[%{#outerStat.index}].city" />
									<s:hidden name ="province" id ="list[%{#outerStat.index}].province" />
									<s:hidden name ="postalCode" id ="list[%{#outerStat.index}].postalCode" />
								</td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
					<h3>Delivery Man Detail</h3>
					<div>
						<table width="100%">
							<tr>
								<td colspan=4 width="50%"></td>
								<td colspan=4 width="50%"></td>
							</tr>
							<tr>
								<td>Compnay Name:</td>
								<td colspan=3>
									<s:iterator value="deliveryCompanyList" id="deliveryCompanyList" status="outerStat">
										<s:if test='%{vo.deliveryCompanyID.equals(deliveryCompanyID)}'>
											<s:property value="%{name}" />
										</s:if>
									</s:iterator>
									<s:hidden name="vo.deliveryCompanyID" id="deliveryCompanyID" />
									<s:hidden name="vo.deliverymanID"  id="deliverymanID" />
									<s:hidden name="vo.naFlag"  id="naFlag" />
								</td>
								<td>Delivery Man ID:</td>
								<td colspan=3><span id="deliverymanIDHtml"><s:property value="%{vo.deliverymanID}" /></span></td>
								
							</tr>
							<tr>
								<td>First Name<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.firstName" id="firstName" size="40" maxlength="50"/></td>
								<td>Last Name<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.lastName" id="lastName" size="40" maxlength="50"/></td>
							</tr>
							<tr>
								<td>Telephone<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.telephone" id="telephone" size="20" maxlength="20"/></td>
								<td>E-mail1<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.email" id="email" size="30" maxlength="256"/></td>
							</tr>
							<tr>
								<td>Address:</td>
								<td colspan=3><s:textfield name="vo.address" id="address" size="40" maxlength="100"/></td>
								<td>Suite Number:</td>
								<td colspan=3><s:textfield name="vo.suite" id="suite" size="7" maxlength="10"/></td>
							</tr>
							<tr>
								<td>City:</td>
								<td colspan=3><s:select name="vo.city" id="city" list ="cityList" listKey="code" listValue="shortName" /></td>
								<td>Province:</td>
								<td><s:select name="vo.province" id="province" list ="provinceList" listKey="code" listValue="shortName" /></td>
								<td>Postal Code:</td>
								<td><s:textfield name="vo.postalCode" id="postalCode" size="8" maxlength="7"/></td>
							</tr>
						</table>
						<table width="100%">
							<tr>
								<td><hr></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="Save" onClick="javascript:save();" />
									<input type="button" value="New" onClick="javascript:clearMan();" />
									<input type="button" value="Delete" onClick="javascript:del();" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				</s:form>
			</div>
		</div>
	</div>
</div>

</t:putAttribute>
</t:insertDefinition>