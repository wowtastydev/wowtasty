<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/jquery.combobox.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<script src="../js/jquery.combobox.js"></script>

<script>
<!--	
	// Save Member master
	function save(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Save Member restaurant
	function saveRest(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "saveMemberRestaurant";
		document.getElementById("frm").submit();
	}
	
	// Password Validation Check
	function check_pwd(pwd) {
		if(isvalid_password(pwd)){
			// Password is OK
			password_info.innerHTML = '<image src="../images/admin/v.png">';
		} else {
			// Password is invalid
			password_info.innerHTML = '<image src="../images/admin/x.png">';
		}
	
	}

	// Passwords Matching Check
	function match_pwds(confirm) {
		var pwd = document.getElementById("newPasswordStr").value;
		if(pwd == confirm){
			// Passwords are matched
			confirm_info.innerHTML = '<span style="color: #0f0;">Matched</span>';
		} else {
			// Passwords are NOT matched
			confirm_info.innerHTML = '<span style="color: #f00;">Not Matched</span>';
		}
	}
	
	// Instruction textcounter
	function countText(obj) {
		var content = obj.value;
		len_info.innerHTML = content.length ;
	}
	
	// Disable/Enable newpassword & confirm password field
	function changePassword(obj) {
		if (obj.checked) {
			// checked
			document.getElementById("memberPasswordStr").value = "";
			document.getElementById("newPasswordStr").value = "";
			document.getElementById("confirmPasswordStr").value = "";
			password_info.innerHTML = "";
			confirm_info.innerHTML = "";
			document.getElementById("memberPasswordStr").disabled = true;
			document.getElementById("newPasswordStr").disabled = true;
			document.getElementById("confirmPasswordStr").disabled = true;
		} else {
			// unchecked
			document.getElementById("memberPasswordStr").disabled = false;
			document.getElementById("newPasswordStr").disabled = false;
			document.getElementById("confirmPasswordStr").disabled = false;
		}
	}
	
	// Disable/Enable email field
	function changeEmail(obj) {
		if (obj.checked) {
			// checked
			document.getElementById("email").readOnly = false;
		} else {
			// unchecked
			document.getElementById("email").readOnly = true;
		}
	}
	
	// Add restaurant row
	function addRow() {
		var id = document.getElementById("memberID").value;
		var restID = document.getElementById("listRstaurantID").value;
		var restName = document.getElementById("listRstaurantName").value;

		if (id == "") {
			// Show Error Message
			errorMessage.innerHTML = "Please save Member Information first.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		
		if (restID == "") {
			// Show Error Message
			errorMessage.innerHTML = "Please choose a restaurant.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		
		var tbody = document.getElementById('restTbl').getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		
		var obj_row;
		if(/*@cc_on!@*/true){
			obj_row = document.createElement("TR");
			tbody.appendChild(obj_row);
		}else{
			obj_row = tbody.insertRow();
		}
		
		var td_del = document.createElement("TD");
		td_del.setAttribute("align", "center");
		obj_row.appendChild(td_del);
		
		var input_del = document.createElement("input"); 
		input_del.setAttribute("type", "checkbox");
		input_del.setAttribute("name", "chk");
		td_del.appendChild(input_del);
		
		var td_name = document.createElement("TD");
		obj_row.appendChild(td_name);
		
		var txt_restName = document.createTextNode(restName);
		td_name.appendChild(txt_restName);
		
		var input_memberID = document.createElement("input");
		input_memberID.setAttribute("type", "hidden");
		input_memberID.setAttribute("name", "memberRestaurantList[" + rowCount + "].memberID");
		input_memberID.setAttribute("value", id);
		td_name.appendChild(input_memberID);
		
		var input_restID = document.createElement("input");
		input_restID.setAttribute("type", "hidden");
		input_restID.setAttribute("name", "memberRestaurantList[" + rowCount + "].restaurantID");
		input_restID.setAttribute("value", restID);
		td_name.appendChild(input_restID);
		
		var input_role = document.createElement("input");
		input_role.setAttribute("type", "hidden");
		input_role.setAttribute("name", "memberRestaurantList[" + rowCount + "].role");
		input_role.setAttribute("value", "");
		td_name.appendChild(input_role);
	}
	
	// delete restaurant row
	function delRow(){
		var tbody = document.getElementById("restTbl").getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		
        for(var i=0; i<rowCount; i++) {
            var row = tbody.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && undefined == chkbox.checked) {
                // In case menuOptionList, checkbox is node[1]
                chkbox = row.cells[0].childNodes[1];
                if(null != chkbox && true == chkbox.checked) {
                	tbody.deleteRow(i);
                    rowCount--;
                    i--;
                }
            } else {
                if(null != chkbox && true == chkbox.checked) {
                	tbody.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        }
	}
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
		// Set up dropdown menu
        $("#city, #province, #delivCity, #delivProvince, #auth, #listRstaurantID").combobox();
        
        // Set up accordion
        $( "#accordion" ).accordion({
        	active: <s:property value="active"/>,
        	collapsible: true,
            heightStyle: "content"
        });
        
        // Set up botton
        $("input[type=button]").button();
        
        $('#listRstaurantID').change(function () {
        	document.getElementById("listRstaurantName").value = $(this).find('option:selected').text();
       	});
	} );
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('A101');">User Account List</a>
				<a href="javascript:goPage('A102');">Add User Account</a>
				<a href="javascript:goPage('A104');">Change Password</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="saveMember">
		<s:hidden name="mvo.status" />
		<s:hidden name="mvo.totalOrderCnt" />
		<s:hidden name="mvo.naFlag" />
		<s:hidden name="mvo.registerDate" />
		
		<h2>Edit User Account</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="detailarea">
				<div id="accordion">
					<h3>General Information</h3>
					<div>
						<table width="100%">
							<tr>
								<td colspan=4 width="50%"></td>
								<td colspan=4 width="50%"></td>
							</tr>
							<tr>
								<td>User ID:</td>
								<td colspan=3><s:property value="%{mvo.memberID}"/>
								<s:hidden name="mvo.memberID" id="memberID" />
								</td>
								<td>Member Type:</td>
								<td colspan=3><s:select name="mvo.auth" id="auth" list="roleList" listKey="code" listValue="name"  headerKey="" headerValue="" />
								</td>
							</tr>
							<tr>
								<td>E-mail<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="mvo.email" id="email" size="30" maxlength="256" readOnly="true"/></td>
								<td>E-mail Change:</td>
								<td colspan=3><s:checkbox name="emailChangeFlag" id="emailChangeFlag" onclick="javascript:changeEmail(this);" /></td>
		
							</tr>
							<tr>
								<td>Current Password<FONT color=red>*</FONT>:</td>
								<td width="120"><s:password name="memberPasswordStr" id="memberPasswordStr" size="30" maxlength="20"/>
								<s:hidden name="mvo.password" />
								</td>
								<td colspan=2></td>
								<td>Password Reset:</td>
								<td colspan=3>
									<s:checkbox name="pwdResetFlag" id="pwdResetFlag" onclick="javascript:changePassword(this);" />
									(Reseted to User ID)
								</td>
							</tr>
							<tr>
								<td>New Password:</td>
								<td width="120"><s:password name="newPasswordStr" id="newPasswordStr" size="30" maxlength="20" onKeyUp="javascript:check_pwd(this.value);"/></td>
								<td colspan=2><span id="password_info"></span></td>
								<td>Confirm:</td>
								<td width="200"><s:password name="confirmPasswordStr" id="confirmPasswordStr" size="30" maxlength="20" onKeyUp="javascript:match_pwds(this.value);"/></td>
								<td colspan=2><span id="confirm_info"></span></td>
							</tr>
							<tr><td></td>
								<td colspan=7><FONT color=red>More than 8 digits with alphabets or symbols(e.g: !@#$%^&*?_~)</FONT></td>
							</tr>
							<tr>
								<td>Current eCash:</td>
								<td colspan=7>
									<s:property value="%{mvo.ecash}" />
									<s:hidden name="mvo.ecash" />
								</td>
							</tr>
							<tr>
								<td colspan=8><br><b>[Contact Address]</b></td>
							</tr>
							<tr>
								<td>First Name<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="mvo.firstName" id="firstName" size="40" maxlength="50"/></td>
								<td>Last Name<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="mvo.lastName" id="lastName" size="40" maxlength="50"/></td>
							</tr>
							<tr>
								<td>Telephone:</td>
								<td colspan=7><s:textfield name="mvo.telephone" id="telephone" size="20" maxlength="20"/></td>
							</tr>
							<tr>
								<td>Address:</td>
								<td colspan=3><s:textfield name="mvo.address" id="address" size="40" maxlength="100"/></td>
								<td>Suite Number:</td>
								<td colspan=3><s:textfield name="mvo.suite" id="suite" size="7" maxlength="10"/></td>
							</tr>
							<tr>
								<td>City:</td>
								<td colspan=3><s:select name="mvo.city" id="city" list ="cityList" listKey="code" listValue="shortName" headerKey="01001" headerValue="Vancouver" /></td>
								<td>Province:</td>
								<td><s:select name="mvo.province" id="province" list ="provinceList" listKey="code" listValue="shortName" headerKey="01" headerValue="BC" /></td>
								<td>Postal Code:</td>
								<td><s:textfield name="mvo.postalCode" id="postalCode" size="8" maxlength="7"/></td>
							</tr>
							<tr>
								<td colspan=8><br><b>[Delivery Address]</b></td>
							</tr>
							<tr>
								<td>First Name:</td>
								<td colspan=3><s:textfield name="mvo.delivFirstName" id="delivFirstName" size="20" maxlength="50"/></td>
								<td>Last Name:</td>
								<td colspan=3><s:textfield name="mvo.delivLastName" id="delivLastName" size="20" maxlength="50"/></td>
							</tr>
							<tr>
								<td>Telephone:</td>
								<td colspan=7><s:textfield name="mvo.delivTelephone" id="delivTelephone" size="20" maxlength="20"/></td>
							</tr>
							<tr>
								<td>Address:</td>
								<td colspan=3><s:textfield name="mvo.delivAddress" id="delivAddress" size="40" maxlength="100"/></td>
								<td>Suite Number:</td>
								<td><s:textfield name="mvo.delivSuite" id="delivSuite" size="7" maxlength="10"/></td>
								<td>Buzzer Number:</td>
								<td><s:textfield name="mvo.delivBuzzer" id="delivBuzzer" size="7" maxlength="10"/></td>
							</tr>
							<tr>
								<td>City:</td>
								<td colspan="3"><s:select name="mvo.delivCity" id="delivCity" list ="cityList" listKey="code" listValue="shortName" headerKey="01001" headerValue="Vancouver" /></td>
								<td>Province:</td>
								<td><s:select name="mvo.delivProvince" id="delivProvince" list ="provinceList" listKey="code" listValue="shortName" headerKey="01" headerValue="BC" /></td>
								<td>Postal Code:</td>
								<td><s:textfield name="mvo.delivPostalCode" id="delivPostalCode" size="7" maxlength="7"/></td>
							</tr>
							<tr>
								<td>Special Instruction:<br>Text Length: <span id="len_info">0</span><br><font color="#f00">(Max 100 char)</font></td>
								<td colspan=7><s:textarea rows="2" cols="100" name="mvo.delivInstruction" id="delivInstruction" onkeyup="javascript:countText(this);"></s:textarea></td>
							</tr>
						</table>
						
						<table width="100%">
							<tr>
								<td><hr></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="Save" onClick="javascript:save();" />
								</td>
							</tr>
						</table>
					</div>

					<!-- Only Restaurant users can add restaurant List -->					
					<s:if test='%{mvo.auth >= 20 && mvo.auth < 30}'>
					<h3>Restaurant List</h3>
					<div>
					    <div>
							<div>
								<table>
									<tr>
										<td>
											<s:select name="listRstaurantID" id="listRstaurantID" list="restaurantList" listKey="restaurantID" listValue="name" headerKey="" headerValue="" />
											<s:hidden id="listRstaurantName"/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Add Restaurant" onClick ="javascript:addRow();"/>
											<input type="button" value="Del Row" onClick ="javascript:delRow();"/>
										</td>
									</tr>
								</table>
						    
								<div>
								<table class="tableborder" id="restTbl" width="400">
									<thead>
									<tr>
										<th width="20"></th>
										<th width="380">Restaurant Name</th>
									</tr>
									</thead>
									<tbody>
									<s:iterator value="memberRestaurantList" id="memberRestaurantList" status="outerStat">
									<tr>
										<td align="center">
											<s:checkbox name="chk" id="chk"/>
										</td>
										<td>
											<s:property value="restaurantName"/>
											<s:hidden name="memberRestaurantList[%{#outerStat.index}].restaurantName"/>
											<s:hidden name="memberRestaurantList[%{#outerStat.index}].memberID"/>
											<s:hidden name="memberRestaurantList[%{#outerStat.index}].restaurantID"/>
											<s:hidden name="memberRestaurantList[%{#outerStat.index}].role"/>
										</td>
									</tr>
									</s:iterator>
									</tbody>
								</table>
								</div>
							</div>
							<table width="100%">
								<tr>
									<td><hr></td>
								</tr>
								<tr>
									<td>
										<input type="button" value="Save" onClick="javascript:saveRest();" />
									</td>
								</tr>
							</table>

					    </div>
					</div>
					</s:if>
					
				</div>
				<table width="100%">
					<tr>
						<td><hr></td>
					</tr>
					<tr>
						<td>
							<input type="button" value="List" onClick="javascript:goPage('A101');" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>