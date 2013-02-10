<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="shortcut icon" href="./images/wowicon.ico">
<meta name="keywords" content="FoodDelivery, WowStaty, Restaurant">
<meta name="description" content="Restaurant success at FoodDelivery WowStaty">
<link rel="shortcut icon" href="../images/wowicon.ico">

<head>
<title>FoodDelivery WowStaty <s:property value="%{msgResult}"/></title>
</head>

<body>
<div id="page_s">
	<div id="header_s">
		<h1>WowStaty.com</h1>
	</div>
	<div id="contentarea_s">
		<h2><font color="#8dbdd8"><s:property value="%{msgResult}"/></font></h2>
		To find out the information about orders, login here. <input type="button" value="Login" onClick="javascript:window.location='<%=request.getContextPath()%>/rest/initLogin';" />
	</div>
	<div id="footer_s">
		Copyright © 2012 Vovios Networks Inc. All rights reserved.
	</div>
</div>
</body>
</html>