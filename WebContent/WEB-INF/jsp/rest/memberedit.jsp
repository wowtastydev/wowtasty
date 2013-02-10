<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="rest.layout">
<t:putAttribute name="main_rest">
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
				<a href="#" class="active">User Account</a>
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
								<td colspan=3>
									<s:iterator value="roleList" id="roleList" status="outerStat">
										<s:if test='%{code.equals(mvo.auth)}'>
											<s:property value="%{name}"/>
										</s:if>
									</s:iterator>
									<s:hidden name="mvo.auth" id="auth" />
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
								<td>Current eCash:</td>
								<td colspan=7>
									<s:property value="%{mvo.ecash}" />
									<s:hidden name="mvo.ecash" />
								</td>
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
					<h3>Restaurant List</h3>
					<div>
						<table class="tableborder" id="restTbl" width="400">
							<thead>
							<tr>
								<th>Restaurant Name</th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="memberRestaurantList" id="memberRestaurantList" status="outerStat">
							<tr>
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
			</div>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>