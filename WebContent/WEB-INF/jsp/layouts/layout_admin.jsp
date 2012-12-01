<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<meta name="keywords" content="<s:property value='metaKeywords' />,FoodDelivery, WowTasty ">
<meta name="description" content="<s:property value='metaDescription' /> at FoodDelivery WowTasty ">
<link rel="shortcut icon" href="../images/wowicon.ico">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<head>
<title><s:property value='headTitle' /> at FoodDelivery WowTasty</title>
</head>
<body>
<s:if test="hasActionMessages()">
	<div id="errorMessageDiv" class="ui-widget" title = "Error">
	    <table id="actionMessageTable" class="ui-widget ui-widget-content" width="460">
			<tr>
				<td>
					<font color="BLUE"><b><s:actionmessage /></b></font>
				</td>
			</tr>
	    </table>
	</div>
</s:if>

<s:if test="hasActionErrors()">
	<div id="errorMessageDiv" class="ui-widget" title = "Error">
	    <table id="errorMessageTable" class="ui-widget ui-widget-content" width="460">
			<tr>
				<td>
					<font color="RED"><b><s:actionerror /></b></font>
				</td>
			</tr>
	    </table>
	</div>
</s:if>

<div id="page">
<tiles:insertAttribute name="header_admin" />
<tiles:insertAttribute name="main_admin" />
<tiles:insertAttribute name="footer_admin" />
</div>
</body>
</html>