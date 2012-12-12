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
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
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
			$.growlUI("Wow Message", message); 
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
			$.blockUI({ message: '<h4><font color="#f00"> ' + message + '</font></h4>', timeout: 2000 });
		});
	//-->
	</script>
</s:if>

<div id="page">
<tiles:insertAttribute name="header_admin" />
<tiles:insertAttribute name="main_admin" />
<tiles:insertAttribute name="footer_admin" />
</div>
</body>
</html>