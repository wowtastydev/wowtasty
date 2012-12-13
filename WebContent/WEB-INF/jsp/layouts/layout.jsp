<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
    <head>
        <title>Wowtasty</title>
        <meta name="keywords" content="<s:property value='metaKeywords' />,FoodDelivery, WowTasty ">
        <meta name="description" content="<s:property value='metaDescription' /> at FoodDelivery WowTasty ">
        <link href="../css/general.css" type="text/css" rel="stylesheet"  />
        <link href="../css/warning.css" type="text/css" rel="stylesheet"  />
        <link rel="stylesheet" href="../themes/sunny/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

        <script src="../js/common.js"></script>
        <script src="../js/warning.js"></script>
        <script src="../js/jquery.blockUI.js"></script>
    </head>
    <body>
        <img src="../images/bg1.jpg" id="full-screen-background-image" /> 
        <div id="container">

            <div id="wrapper">

                <p><tiles:insertAttribute name="header" /></p>
                <p><tiles:insertAttribute name="main" /></p>
                <p><tiles:insertAttribute name="footer" /></p>

            </div>
        </div>
    </body>
</html>