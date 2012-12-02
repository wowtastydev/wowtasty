<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<script src="../js/common.js"></script>

<script>
<!--	
	// Password Validation Check
	function check_pwd(pwd) {
		if(isvalid_password(pwd)){
			// Password is OK
			password_info.innerHTML = '<image src="../images/v.png">';
		} else {
			// Password is invalid
			password_info.innerHTML = '<image src="../images/x.png">';
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
	function save(){
		var pwd = document.getElementById("newPasswordStr").value;
		var confirm = document.getElementById('confirmPasswordStr').value;

		// Check password
		if (pwd != "") {
			if(!isvalid_password(pwd)){
				password_info.innerHTML = '<span style="color: #f00;">The type of New Password is invalid.</span>';
				return;
			}
		}
		
		// Match password and confirm
		if( pwd != confirm){
			confirm_info.innerHTML = '<span style="color: #f00;">Password and Confirm are not matched.</span>';
			return;
		}
		document.getElementById("frm").submit();
	}
	

	
// -->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="#" onClick="initUserList">User Account List</a>
					<div id="layer01" style="display:none;">
					</div>
				<a href="#" onClick="initUserAdd">Add User Account</a>
					<div id="layer02" style="display:none;">
					</div>
				<a href="#" class="active">Change Password</a>
					<div id="layer03" style="display:block;">
					</div>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="css_xhtml" name="frm" id="frm" action="changePassword">
			<table width="800">
				<tr>
					<td align="left" colspan="3"><h2>Change Password</h2></td>
				</tr>
				<tr>
					<td align="right">Current Password : </td>
					<td colspan="2"><s:password name="currentPasswordStr" id="currentPasswordStr" value="%{currentPasswordStr}" size="30" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td width="200" align="right" valign="top" rowspan="2">New Password : </td>
					<td width="200"><s:password name="newPasswordStr" id="newPasswordStr" value="%{newPasswordStr}" size="30" maxlength="20"  onKeyUp="javascript:check_pwd(this.value);"/>
					</td>
					<td align="left"><span id="password_info"><b></b></span>
					</td>
				</tr>
				<tr><td colspan="2">
					<b>More than 8 digits with alphabets or symbols</b>
					</td>
				</tr>
				<tr>
					<td align="right">Confirm : </td>
					<td><s:password name="confirmPasswordStr" id="confirmPasswordStr" value="%{confirmPasswordStr}" size="30" maxlength="20" onKeyUp="javascript:match_pwds(this.value);"/></td>
					<td align="left"><span id="confirm_info"><b></b></span>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="button" value="Save" onClick="javascript:save();" /></td>
				</tr>
			</table>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>