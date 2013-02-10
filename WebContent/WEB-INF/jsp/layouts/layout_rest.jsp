<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<meta name="keywords" content="<s:property value='metaKeywords' />,FoodDelivery, WowTasty ">
<meta name="description" content="<s:property value='metaDescription' /> at FoodDelivery WowTasty ">
<link rel="shortcut icon" href="../images/wowicon.ico">
<link rel="stylesheet" href="../themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/jquery-ui.js"></script>
<script src="../js/jquery.blockUI.js"></script>
<script src="../js/common.js"></script>

<head>
<title><s:property value='headTitle' /> at FoodDelivery WowTasty</title>
</head>
<body>

<s:if test="hasActionMessages()">
	<script>
	<!--
		var message = "<ul class='actionMessage'>";
		<s:iterator value="actionMessages">  
		message = message + "<li><span><s:property escape='false' /></li>";  
		</s:iterator>
		message = message + "</ul>";
		
		$(document).ready(function() { 
			$.growlUI("Wow Message", message, 3000, null, '#000', '#fff'); 
		});

	//-->
	</script>
</s:if>

<s:if test="hasActionErrors()">
	<script>
	<!--
		var message = "<ul class='actionError'>";
		<s:iterator value="actionErrors">  
		message = message + "<li><span><s:property escape='false' /></li>";  
		</s:iterator>
		message = message + "</ul>";
	
		$(document).ready(function() { 
			$.growlUI("Wow Error", message, 3000, null, '#EB2D4C', '#fff'); 
		});
	//-->
	</script>
</s:if>

<div id="page">
<tiles:insertAttribute name="header_rest" />
<tiles:insertAttribute name="main_rest" />
<tiles:insertAttribute name="footer_rest" />
</div>
</body>
</html>