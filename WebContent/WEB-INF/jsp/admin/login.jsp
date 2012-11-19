<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<link rel="shortcut icon" href="./images/wowicon.ico">
<meta name="keywords" content="FoodDelivery, WowStaty, Admin">
<meta name="description" content="Admin login at FoodDelivery WowStaty">
<head>
<title>FoodDelivery WowStaty Admin Login</title>
</head>
<body>
<div id="page">
	<div id="header">
		<h1><a href="initLogin">WOW TASTY ADMIN</a></h1>
	</div>
	<center>
		<h2>Wow Tasty Admin Login</h2>
	
		<s:form theme="css_xhtml" name="frm" id="frm" action="login" method="POST" validate="true">
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
					<td><s:submit value="Login" /></td>
				</tr>
			</table>
		</s:form>
	
	<div id="footer">
		Copyright © 2012 Vovios Networks Inc. All rights reserved.
	</div>
</div>
</body>
</html>