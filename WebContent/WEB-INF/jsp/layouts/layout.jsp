<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>
<html lang="en">
<link rel="stylesheet" type="text/css" href="./css/admin_style.css" media="screen" />
<head>
<title>Wow Tasty</title>
<link rel="shortcut icon" href="./images/wowicon.ico">
</head>
<body>

<p><tiles:insertAttribute name="header" /></p>
<p><tiles:insertAttribute name="main" /></p>
<p><tiles:insertAttribute name="footer" /></p>

</body>
</html>