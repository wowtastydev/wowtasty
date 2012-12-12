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
	function add(){
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
		var pwd = document.getElementById("memberPasswordStr").value;
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
		content = obj.value;
		len_info.innerHTML = content.length ;
	}
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
		// Set up dropdown menu
        $("#city").combobox();
        $("#province").combobox();
        $("#delivCity").combobox();
        $("#delivProvince").combobox();
       
        // Set up accordion
        $( "#accordion" ).accordion({
        	collapsible: true,
            heightStyle: "content"
        });
        
     	// Set up botton
        $("input[type=button]").button();
	} );
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('A101');">User Account List</a>
				<a href="#" class="active">Add User Account</a>
				<a href="javascript:goPage('A104');">Change Password</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="css_xhtml" name="frm" id="frm" action="insertMember">
		<s:hidden name="mvo.memberID" />
		<s:hidden name="mvo.status" />
		<s:hidden name="mvo.totalOrderCnt" />
		<s:hidden name="mvo.naFlag" />
		<s:hidden name="mvo.registerDate" />
		
		<h2>Add User Account</h2>
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
								<th align="left" colspan=8>[General Information]</th>
							</tr>
							<tr>
								<td>E-mail<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="mvo.email" id="email" value ="%{mvo.email}" size="30" maxlength="256"/></td>
								<td>Member Type:</td>
								<td colspan=3><s:select name="mvo.auth" id="auth" list="roleList" listKey="code" listValue="name"  headerKey="" headerValue="" />
								</td>
		
							</tr>
							<tr>
								<td>Password<FONT color=red>*</FONT>:</td>
								<td width="120"><s:password name="memberPasswordStr" id="memberPasswordStr" value ="%{memberPasswordStr}" size="30" maxlength="20" onKeyUp="javascript:check_pwd(this.value);"/>
								<s:hidden name="mvo.password" />
								<td colspan=2><span id="password_info"></span></td>
								</td>
								<td>Confirm<FONT color=red>*</FONT>:</td>
								<td width="200"><s:password name="confirmPasswordStr" id="confirmPasswordStr" size="30" maxlength="20" onKeyUp="javascript:match_pwds(this.value);"/></td>
								<td colspan=2><span id="confirm_info"></span></td>
							</tr>
							<tr><td></td>
								<td colspan=7><FONT color=red>More than 8 digits with alphabets or symbols</FONT></td>
							</tr>
						</table>
					</div>
					<h3>Contact Address</h3>
				    <div>
						<table width="100%">
							<tr>
								<td colspan=4 width="50%"></td>
								<td colspan=4 width="50%"></td>
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
								<td><s:textfield name="mvo.postalCode" id="postalCode" size="7" maxlength="6"/></td>
							</tr>
						</table>
					</div>
					<h3>Delivery Address</h3>
				    <div>
						<table width="100%">
							<tr>
								<td colspan=4 width="50%"></td>
								<td colspan=4 width="50%"></td>
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
								<td><s:textfield name="mvo.delivPostalCode" id="delivPostalCode" size="7" maxlength="6"/></td>
							</tr>
							<tr>
								<td>Special Instruction:<br>Text Length: <span id="len_info">0</span><br><font color="#f00">(Max 100 char)</font></td>
								<td colspan=7><s:textarea rows="2" cols="75" name="mvo.delivInstruction" id="delivInstruction" onkeyup="javascript:countText(this);"></s:textarea></td>
							</tr>
						</table>
					</div>
					<h3>eCash</h3>
				    <div>
						<table width="100%">
							<tr>
								<td width="120">Current eCash:</td>
								<td>
									<s:property value="%{mvo.ecash}" />
									<s:hidden name="mvo.ecash" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<table width="100%">
					<tr>
						<td colspan=8><hr></td>
					</tr>
					<tr>
						<td>
							<input type="button" value="List" onClick="javascript:goPage('A101');" />
						</td>
						<td align="right" colspan=7>
							<input type="button" value="Add" onClick="javascript:add();" />
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