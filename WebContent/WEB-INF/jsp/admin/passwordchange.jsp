<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />

<script>
<!--	
	//Password Validation Check
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
	
	// Change Password
	function changePassword() {
		if(confirm("Do you want to change the password?")) {
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}
	}
// -->
</script>


<script>
<!--
    // JQuery
	$(document).ready( function() {
        $("input[type=button]").button();
	} );
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('A101');">User Account List</a>
				<a href="javascript:goPage('A102');">Add User Account</a>
				<a href="#" class="active">Change Password</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="changePassword">

		<h2>Change Password</h2>
		
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<table width="100%">
				<tr>
					<td align="right">Current Password<FONT color=red>*</FONT> : </td>
					<td colspan="2"><s:password name="currentPasswordStr" id="currentPasswordStr" value="%{currentPasswordStr}" size="30" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td width="200" align="right" valign="top" rowspan="2">New Password<FONT color=red>*</FONT> : </td>
					<td width="200"><s:password name="newPasswordStr" id="newPasswordStr" value="%{newPasswordStr}" size="30" maxlength="20"  onKeyUp="javascript:check_pwd(this.value);"/>
					</td>
					<td align="left"><span id="password_info"><b></b></span>
					</td>
				</tr>
				<tr><td colspan="2">
					<FONT color=red>More than 8 digits with alphabets or symbols(e.g: !@#$%^&*?_~)</FONT>
					</td>
				</tr>
				<tr>
					<td align="right">Confirm<FONT color=red>*</FONT> : </td>
					<td><s:password name="confirmPasswordStr" id="confirmPasswordStr" value="%{confirmPasswordStr}" size="30" maxlength="20" onKeyUp="javascript:match_pwds(this.value);"/></td>
					<td align="left"><span id="confirm_info"><b></b></span>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="button" value="Save" onClick="javascript:changePassword();" /></td>
				</tr>
			</table>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>