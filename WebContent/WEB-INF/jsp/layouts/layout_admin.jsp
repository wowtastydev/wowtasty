<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<meta name="keywords" content="<s:property value='metaKeywords' />">
<meta name="description" content="<s:property value='metaDescription' />">
<link rel="shortcut icon" href="../images/wowicon.ico">
<link rel="stylesheet" type="text/css" href="../css/admin_style.css" media="screen" />
<head>
<title><s:property value='headTitle' /></title>
</head>
<body>
<font color="White"><s:actionmessage /></font>
<font color="White"><s:actionerror /></font>
<div id="page">
<tiles:insertAttribute name="header_admin" />
<tiles:insertAttribute name="main_admin" />
<tiles:insertAttribute name="footer_admin" />
</div>
</body>
</html>