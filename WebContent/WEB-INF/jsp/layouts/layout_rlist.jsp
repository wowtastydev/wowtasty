<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Wowtasty</title>
        <meta name="keywords" content="<s:property value='metaKeywords' />,FoodDelivery, WowTasty "/>
        <meta name="description" content="<s:property value='metaDescription' /> at FoodDelivery WowTasty "/>
        <link href="../css/general.css" type="text/css" rel="stylesheet"  />
        <link href="../css/warning.css" type="text/css" rel="stylesheet"  />
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/sunny/jquery-ui.css" />

        <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <script src="../js/common.js"></script>
        <script src="../js/warning.js"></script>
        <script src="../js/jquery.blockUI.js"></script>
        <script src="../js/jquery.dimensions.js"></script>
        <script src="../js/quick.js"></script>
        <script>
            $(document).ready(function(){
                hideAllMessages();
            <s:if test="hasActionMessages()||hasFieldErrors()">
                    $('.warning').animate({top:"0"}, 500);   
            </s:if>
                });
        </script>
    </head>
    <body>
        <img src="../images/bg2.jpg" id="full-screen-background-image" />
        <div id="container-sub">
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
                    </div>

                    <div class="success message">
                        <h3>Congrats, you did it!</h3>
                        <p>This is just a success notification message.</p>
                    </div>
                </div>

                <div style="position: absolute; height:143px; width:100%; background:url(../images/top-bar-x.png) repeat-x;"></div>
                <div id="wrapper-sub-out">
                    <div id="wrapper-sub">

                        <tiles:insertAttribute name="header" />
                        <tiles:insertAttribute name="main" />
                        <tiles:insertAttribute name="footer" />

                    </div>
                    <div id="rightpanel">
                        <div id="rightpanel-in">
                            <!--id="secRight"-->
                            <h3 class="titlePanel">Order Panel</h3>
                            <div class="rporderrestaurnat clearfix">
                                <p class="fleft">[Pizzalita]</p>
                                <select name="">
                                    <option value="5Delivery ($5.00)">5Delivery ($5.00)</option>
                                    <option value="5Delivery ($5.00)">5Delivery ($5.00)</option>
                                </select>
                            </div>
                            <div class="rporderlist">
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>

                                <dl class="total">
                                    <dt class="clearfix"><span class="txt">Tax</span> <span class="price">$12.25</span></dt>
                                    <dd class="clearfix"><span class="txt">Sub Total</span> <span class="price">$78.75</span></dd>
                                </dl>

                            </div>

                            <div class="rporderrestaurnat clearfix">
                                <p class="fleft">[Pizzalita]</p>
                                <select name="">
                                    <option value="5Delivery ($5.00)">5Delivery ($5.00)</option>
                                    <option value="5Delivery ($5.00)">5Delivery ($5.00)</option>
                                </select>
                            </div>
                            <div class="rporderlist">
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>
                                <dl class="clearfix">
                                    <dd class="clearfix"><span class="fleft"><a href="#" title="Lita's meat special">Lita's Meat S...(L)</a> 1 x</span> <span class="delete"><a href="#"><img src="../images/icon-delete.gif" width="11" height="11" align="absmiddle" /></a></span><span class="price">$8.75</span></dd>
                                </dl>

                                <dl class="total">
                                    <dt class="clearfix"><span class="txt">Tax</span> <span class="price">$12.25</span></dt>
                                    <dd class="clearfix"><span class="txt">Sub Total</span> <span class="price">$78.75</span></dd>
                                </dl>

                            </div>
                            <div class="btn-checkout"><a href="#">Check out! ></a></div>


                            <div style="margin:20px 0 0 0">
                                <h3 class="titlePanel">WOW CASH</h3>
                                <p style="padding:10px 0 0 10px">Your current wow cash is</p>
                                <p style="font: italic 25px Georgia, 'Times New Roman', Times, serif; letter-spacing:-1px; color:#4f4f4f; text-align:center"><span style="color:#900; font-size:28px">250</span>Wow</p>

                            </div>

                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </body>
</html>