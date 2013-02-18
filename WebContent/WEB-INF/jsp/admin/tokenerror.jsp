<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="shortcut icon" href="./images/wowicon.ico">
<meta name="keywords" content="FoodDelivery, WowStaty, Admin">
<meta name="description" content="Admin login at FoodDelivery WowStaty">
<link rel="shortcut icon" href="../images/wowicon.ico">
<link rel="stylesheet" href="../themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/jquery-ui.js"></script>
<head>
<title>FoodDelivery WowStaty Admin Error</title>
</head>
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
		<h1><a href="initLogin">WOW TASTY ADMIN</a></h1>
	</div>
	<div id="mainarea">
		<div id="contentarea">
			<h2><font color="#f00">Wow Tasty Token Error</font></h2>
			<div class="error">
				<p>Refresh function is not allowed on this page.</p>
			</div>
			<input type="button" value="Go to Main Page" onClick="javascript:window.location='initCurrentOrderList';" />
			
		</div>
	</div>
	<div id="footer">
		Copyright © 2012 Vovios Networks Inc. All rights reserved.
	</div>
</div>
</body>
</html>