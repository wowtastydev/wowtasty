<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="default.layout">
    <t:putAttribute name="main">
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
        <script>
            //location field autocomplete script
            function initialize() {
                var defaultBounds = new google.maps.LatLngBounds(
                new google.maps.LatLng(49.338994,-123.204117),
                new google.maps.LatLng(49.029765,-122.514038));
                var input = document.getElementById('location');
                var options = {
                    bounds: defaultBounds,
                    types: [],
                    componentRestrictions: {country: 'ca'}
                };
                var autocomplete = new google.maps.places.Autocomplete(input, options);
                autocomplete.setBounds(defaultBounds);

                google.maps.event.addListener(autocomplete, 'place_changed', function() {
                    infowindow.close();
                    marker.setVisible(false);
                    var place = autocomplete.getPlace();
                    if (!place.geometry) {
                        // Inform the user that the place was not found and return.
                        input.value = 'not found';
                        return;
                    }

                    var address = '';
                    if (place.address_components) {
                        address = [
                            (place.address_components[0] && place.address_components[0].short_name || ''),
                            (place.address_components[1] && place.address_components[1].short_name || ''),
                            (place.address_components[2] && place.address_components[2].short_name || '')
                        ].join(' ');
                    }
                    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
                });
            }
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script src="../js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/jquery.autocomplete.css" />
        <div id="mainsearch">

            <s:form theme="simple" cssClass="field" name="frm" id="frm" action="searchRestaurant" method="POST" >
                <s:if test="hasActionMessages()">
                    <div class="searcharea2">
                        <div class="searchareain">
                            <h2>START YOUR ORDER HERE</h2>
                            <div class="field">
                                <p>Browse by Location</p>
                                <p>
                                    <select name="select" id="select">
                                        <option value="Vancouver">Vancouver</option>
                                        <option value="New Westminster">New Westminster</option>
                                        <option value="Coquitlam">Coquitlam</option>
                                        <option value="Surrey">Surrey</option>
                                        <option value="Notrh Vancouver">Notrh Vancouver</option>
                                        <option value="West Vancouver">West Vancouver</option>
                                        <option value="Burnaby">Burnaby</option>
                                    </select>
                                </p>
                            </s:if>
                            <s:else>
                                <div class="searcharea">
                                    <div class="searchareain">
                                        <h2>START YOUR ORDER HERE</h2>
                                        <div class="field">
                                            <p>Location</p>
                                            <p><s:textfield id="location" value="Location (Postal code or Address)" cssClass="field" onclick="if(this.value == 'Location (Postal code or Address)') { this.value = ''; }" /></p>
                                            <p style="margin-top:7px">Restaurant</p>
                                            <p><s:textfield id="keyword" value="Restaurant/Cuisine (Option)" cssClass="field" onclick="if(this.value == 'Restaurant/Cuisine (Option)') { this.value = ''; }"  /></p>
                                            <script>
                                                $("#keyword").autocomplete("/order/searchKeyword");
                                            </script>
                                            <p style="margin-top:7px;">
                                                <span class="col1"><input type="radio" name="restaurantType" id="restaurantType" value="1" class="radio" />Delivery</span>
                                                <span class="col2"><input type="radio" name="restaurantType" id="restaurantType" value="2" class="radio" />Takeout</span>
                                            </p>
                                        </s:else>
                                        <div class="clear" style="padding:12px 0 0 0"><a href="#" class="submit"></a></div>
                                    </div>
                                </div>
                            </div>
                        </s:form>

                    </div>

                    <!-- body start-->

                    <div id="mainbody">
                        <div class="topborder"></div>
                        <div id="maincontent">
                            <div class="maincontent-in">
                                <div class="container clearfix" style="padding:35px 0 70px 0">
                                    <div class="blockleft">
                                        <h2>Join in our networks!</h2>
                                        <div class="sns clearfix">
                                            <a href="#" class="twit"></a>
                                            <a href="#" class="fb"></a>
                                            <a href="#" class="rss"></a>
                                            <a href="#" class="google"></a>
                                        </div>
                                    </div>
                                    <div class="blockright">
                                        <h2>Join in to be a partner with us Today!</h2>
                                        <p>Find out how we can help you grow your business and reach over a million individual and corporate members.</p>
                                        <p><a href="#">Learn more about joining our network »</a></p>
                                    </div>
                                </div>
                                <div class="container clearfix" style="padding:0 0 70px 0">
                                    <div class="blockleft">
                                        <h3>New Restaurants</h3>
                                        <ul>
                                            <s:iterator value="newListVO" id="newListVO" status="stat">
                                                <li><s:property value="#stat.index+1"/>.<a href="#"> <s:property value="name"/></a></li>
                                            </s:iterator>
                                        </ul>
                                    </div>
                                    <div class="blockright">
                                        <h3>Most ordered Restaurants</h3>
                                        <ul>
                                            <s:iterator value="mostListVO" id="mostListVO" status="stat">
                                                <li><s:property value="#stat.index+1"/>.<a href="#"> <s:property value="name"/></a></li>
                                            </s:iterator>
                                        </ul>
                                    </div>
                                </div>

                                <div class="container clearfix" style="padding:0 0 60px 0">
                                    <div class="block">
                                        <h3>Restaurants by Cuisine</h3>
                                        <s:iterator value="cuisineListVO" id="cuisineListVO" status="stat">
                                            <s:if test="#stat.isFirst()==true"><ul></s:if>
                                                <li><a href="#"><s:property value="name"/> (<s:property value="count"/>)</a></li>
                                                <s:if test="#stat.isLast()==true"></ul></s:if>
                                            <s:elseif test="(#stat.index+1)%3==0"></ul><ul></s:elseif>
                                            </s:iterator>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>

                </t:putAttribute>
            </t:insertDefinition>
