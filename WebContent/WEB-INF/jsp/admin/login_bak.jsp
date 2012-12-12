<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<link rel="shortcut icon" href="./images/wowicon.ico">
<meta name="keywords" content="FoodDelivery, WowStaty, Admin">
<meta name="description" content="Admin login at FoodDelivery WowStaty">
<link rel="shortcut icon" href="../images/wowicon.ico">
<link rel="stylesheet" href="../themes/redmond/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="../js/common.js"></script>
<script src="../js/jquery.blockUI.js"></script>

<head>
<title>FoodDelivery WowStaty Admin Login</title>
</head>
<script>
<!--	
	// login
	function login(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
//-->
</script>

<script>
<!--

	$(document).ready( function() {
		// Set up button		
		$( "input[type=button]" ).button();
	} );
	
//-->
</script>

<body>
<div id="page">
	<div id="header">
		<h1>WOW TASTY ADMIN</h1>
	</div>
	<center>
		<h2>Wow Tasty Admin Login</h2>
	
		<s:form theme="css_xhtml" name="frm" id="frm" action="login">
			<table width="820">
				<tr>
					<td width="235" align="right">E-mail : </td>
					<td><s:textfield name="memberEmail" id="memberEmail" value="%{memberEmail}" size="30" maxlength="256"/></td>
					<td rowspan="3" align="right"><img src="../images/wowlogo.jpg" width =200 height =50 border =0/></td>
				</tr>
				<tr>
					<td align="right">Password : </td>
					<td><s:password name="memberPasswordStr" id="memberPasswordStr" value="%{memberPasswordStr}" size="30" maxlength="20"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="button" value="Login" onClick="javascript:login();" />
					</td>
				</tr>
			</table>
		</s:form>
	
	<div id="footer">
		Copyright © 2012 Vovios Networks Inc. All rights reserved.
	</div>
</div>
</body>
</html>