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
        <link rel="stylesheet" href="../css/themes/sunny/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <script src="../js/warning.js"></script>
        <script src="../js/common.js"></script>
        <script src="../js/jquery.blockUI.js"></script>
        <script>
            $(document).ready(function(){
                hideAllMessages();
            <s:if test="hasActionMessages()||hasFieldErrors()||hasActionErrors()">
                    $('.warning').animate({top:"0"}, 500);   
            </s:if>
                });
        </script>
    </head>
    <body>
        <img src="../images/bg1.jpg" id="full-screen-background-image" /> 
        <div id="container">
            <div id="warning">
                <div class="info message">
                    <h3>FYI, something just happened!</h3>
                    <p>We can't find your address. Please choose your city</p>
                </div>

                <div class="error message">
                    <h3>Ups, an error ocurred</h3>
                    <p>This is just an error notification message.</p>
                </div>

                <div class="warning message">
                    <h3>Wait, I must warn you!</h3>
                    <s:if test="hasActionMessages()">
                        <s:iterator value="actionMessages"><p><s:property escape='false' /></p></s:iterator>
                    </s:if>
                    <s:if test="hasFieldErrors()">
                        <s:iterator value="fieldErrors"><p><s:property escape='false' /></p></s:iterator>
                    </s:if>
                    <s:if test="hasActionErrors()">
                        <s:iterator value="actionErrors"><p><s:property escape='false' /></p></s:iterator>
                    </s:if>
                </div>

                <div class="success message">
                    <h3>Congrats, you did it!</h3>
                    <p>This is just a success notification message.</p>
                </div>
            </div>

            <div id="wrapper">

                <p><tiles:insertAttribute name="header" /></p>
                <p><tiles:insertAttribute name="main" /></p>
                <p><tiles:insertAttribute name="footer" /></p>

            </div>
        </div>
    </body>
</html>