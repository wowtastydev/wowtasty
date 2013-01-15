<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="rlist.layout">
    <t:putAttribute name="main">
        <link rel="stylesheet"  href="../css/webwidget_tab.css" type="text/css" />
        <script src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
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

                google.maps.event.addListener(autocomplete, 'place_changed', function() {
                    var place = autocomplete.getPlace();
                    var address = '';
                    var postalCode = '';
                    if (place.address_components) {
                        address = [
                            (place.address_components[0] && place.address_components[0].short_name || ''),
                            (place.address_components[1] && place.address_components[1].short_name || ''),
                            (place.address_components[2] && place.address_components[2].short_name || '')
                        ].join(' ');

                        for (var i=0; i<place.address_components.length;i++) {
                            for (var j=0;j<place.address_components[i].types.length;j++) {
                                if (place.address_components[i].types[j] == "postal_code"){
                                    postalCode = place.address_components[i].long_name;
                                    document.getElementById('postalCode').value=postalCode;
                                }
                            }
                        }
                    }
                    
                });
            }
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>

        <!-- keyword autocomplete -->
        <script src="../js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/jquery.autocomplete.css" />

        <script>
            function viewRestaurant(resID){
                if ($('#preOrderDate').val() == 'DD/MM/YY') $('#preOrderDate').val('');
                if ($('#preOrderTime').val() == 'HH:MM') $('#preOrderTime').val('');
                $('#restaurantID').val(resID);
                $('#sfrm2').attr('action','viewRestaurant');
                $('#sfrm2').submit();
                return false;
            };
            
            function searchRestaurant(){
                if ($('#preOrderDate').val() == 'DD/MM/YY') $('#preOrderDate').val('');
                if ($('#preOrderTime').val() == 'HH:MM') $('#preOrderTime').val('');
                $('#sfrm2').attr('action','searchRestaurant');
                $('#sfrm2').submit();
                return false;
            }
            
            $(document).ready(function(){ 
                $('#submitBtn1').click(function(){
                    $('#sfrm1').submit();
                    return false;
                });
                $('#submitBtn2').click(function(){
                    if ($('#preOrderDate').val() == 'DD/MM/YY') $('#preOrderDate').val('');
                    if ($('#preOrderTime').val() == 'HH:MM') $('#preOrderTime').val('');
                    $('#sfrm2').attr('action','searchRestaurant');
                    $('#sfrm2').submit();
                    return false;
                });
            });
        </script>

        <!-- body start-->
        <div id="subbody">
            <div id="breadcrumb">Home >> Order > <span class="current">Search Restaurant</span></div>
            <div class="titlearea">
                <h2>Restaurant List<span class="small">Here’s the restaurants near you!</span></h2>
            </div>
            <div>
                <div id="map" style="width:638px; height:278px;"></div>
                <s:form theme="simple" cssClass="field" name="sfrm1" id="sfrm1" action="searchRestaurant" method="POST">
                    <div id="currentlocation">
                        <h3 class="titlePanel">YOUR CURRENT LOCATION</h3>
                        <div class="address">
                            <s:if test="rscVO.location != ''"><s:property value="%{rscVO.location}"/></s:if>
                            <s:if test="rscVO.city != ''"><s:property value="%{rscVO.cityName}"/></s:if>
                            </div>
                            <div class="resetlocation clearfix">
                                <h3>Reset Location</h3>
                            <s:textfield name="rscVO.location" id="location" value="Location (Postal code or Address)" cssClass="input_text" onclick="if(this.value == 'Location (Postal code or Address)') { this.value = ''; }" />
                            <s:hidden name="rscVO.postalCode" id="postalCode" value="" />
                            <s:textfield name="rscVO.keyword" id="keyword" value="Restaurant/Cuisine (Option)" cssClass="input_text" onclick="if(this.value == 'Restaurant/Cuisine (Option)') { this.value = ''; }"  />
                            <p class="clearfix">
                                <span class="col1"><input type="radio" name="rscVO.restaurantType" id="restaurantType" value="1" class="radio" checked="true" />Delivery</span> 
                                <span class="col2"><input type="radio" name="rscVO.restaurantType" id="restaurantType" value="2" class="radio" />Takeout</span>
                            </p>
                            <script>
                                $("#keyword").autocomplete("order/searchKeyword");
                            </script>
                            <p class="btnsubmit"><a href="#" id="submitBtn1">Search now!</a></p>
                        </div>
                    </div>
                </s:form>
                <div class="clear"></div>
            </div>

            <!--list start-->
            <div  id="restaurant-list" > 
                <!--search start-->
                <s:form theme="simple" cssClass="field" name="sfrm2" id="sfrm2" action="searchRestaurant" method="POST">
                    <s:hidden id="rscVO.location" name="rscVO.location" value="%{rscVO.location}"/>
                    <s:hidden id="rscVO.keyword" name="rscVO.keyword" value="%{rscVO.keyword}"/>
                    <s:hidden id="rscVO.restaurantType" name="rscVO.restaurantType" value="%{rscVO.restaurantType}"/>
                    <s:hidden id="rscVO.postalCode" name="rscVO.postalCode" value="%{rscVO.postalCode}"/>
                    <s:hidden id="rscVO.city" name="rscVO.city" value="%{rscVO.city}"/>
                    <s:hidden id="cityName" name="rscVO.cityName" value="%{rscVO.cityName}"/>
                    <s:hidden id="restaurantID" name="rscVO.restaurantID"/>
                    <div>
                        <div class="order" style="width:435px">
                            <p>Pre-order time<span>
                                    <s:textfield id="preOrderDate" name="rscVO.preOrderDate" cssStyle="width:100px" value="DD/MM/YY" onclick="if(this.value == 'DD/MM/YY') { this.value = ''; }" />
                                </span><span>
                                    <s:textfield id="preOrderTime" name="rscVO.preOrderTime" cssStyle="width:100px" value="HH:MM" onclick="if(this.value == 'HH:MM') { this.value = ''; }" />
                                </span></p>
                            <p class="btn"><a href="#" id="submitBtn2">Search</a></p>
                        </div>
                        <div class="order" style="padding:0 0 0 30px; width:222px">
                            <p>Cuisine Type <span>
                                    <s:select name="rscVO.cuisineType" id="rscVO.cuisineType" list="cuisineListVO" listKey="code" listValue="name+' ('+count+')'" headerKey="" headerValue="All" onchange="javascript:searchRestaurant();"/>
                                </span> </p>
                        </div>
                        <div class="order" style="padding:0 0 0 10px; width:191px">
                            <p>Sort By<span>
                                    <s:select name="rscVO.orderBy" id="rscVO.orderBy" list="#{'':'None','name':'Restaurant Name','averagePrice':'Average Price','deliveryTime':'Delivery Time','deliveryFee':'Delivery Fee'}" onchange="$('#sfrm2').submit();"/>
                                </span></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </s:form>
                <!--search End-->

                <div>
                    <div class="listheader">
                        <p class="thumb">&nbsp;</p>
                        <p class="name">Restaurant Name</p>
                        <p class="cusine">Cuisine</p>
                        <p class="price">Average Price</p>
                        <p class="time">Delivery Time</p>
                        <p class="minorder">Minimum Order</p>
                        <p class="option">Option</p>
                        <div class="clear"></div>
                    </div>
                    <ul class="listup">
                        <s:iterator value="restListVO" id="restListVO" status="stat">
                            <li class="clearfix" <s:if test="deliveryOpenStatus!='OPEN'">style="background-color:#E8E8E8"</s:if>>
                                <p class="thumb" data-gmapping='{"id":"<s:property value="%{restaurantID}"/>","address":"<s:property value="%{address}"/>","city":"<s:property value="%{cityName}"/>","province":"<s:property value="%{provinceName}"/>","telephone":"<s:property value="%{telephone}"/>","tags":"<s:property value='%{(name).replaceAll("\'","")}'/>","image":"../pictures/restaurant/thumb_<s:property value="%{logoImagePath}"/>"}'>
                                    <img src="../pictures/restaurant/thumb_<s:property value='%{logoImagePath}'/>" width="115" height="59" /></p>
                                <p class="name"><a href="javascript:viewRestaurant('<s:property value="%{restaurantID}"/>');" ><span class="title"><s:property value="%{name}"/></span><span class="describe">
                                            <s:property value="%{address}"/>, <s:property value="%{cityName}"/> <s:property value="%{provinceName}"/> <s:property value="%{postalCode}"/> 
                                            <s:property value="@com.wowtasty.util.StringUtil@substringLimit(profile,35)"/></span></a></p>
                                <p class="cusine"><s:property value="%{cuisineName}"/></p>
                                <p class="price">$<s:property value="%{averagePrice}"/></p>
                                <p class="time">
                                    <s:if test="deliveryOpenStatus=='OPEN'"><s:property value="%{deliveryTime}"/> mins</s:if>
                                    <s:else>Open: <s:property value="%{openHour1}"/> / <s:property value="%{openHour2}"/></s:else>
                                </p>
                                <p class="minorder">$<s:property value="%{minPrice}"/> CAD</p>
                                <p class="option"><s:if test="restaurantType==1">Delivery / Takeout</s:if><s:else>Take out</s:else></p>
                                    <div class="clear"></div>
                                </li>
                        </s:iterator>
                    </ul>
                </div>
            </div>
            <!--list end--> 
            <!-- google map -->

            <script type="text/javascript" src="../js/jquery.ui.map.js"></script>
            <script type="text/javascript" src="../js/jquery.ui.map.services.js"></script>
            <script type="text/javascript">
                $(function() {
                    $('#map').gmap().bind('init', function(ev, map) {
                        $("[data-gmapping]").each(function(i,el) {
                            var data = $(el).data('gmapping');
                            var lat = '';
                            var lng = '';
                            var restID = data.id;
                            var imagePath = data.image;
                            var placeName = data.tags;
                            var street = data.address;
                            var city = data.city;
                            var state = data.province;
                            var telephone = data.telephone;
                            var country = 'CA';
                            var address = [placeName, street, city, state, country].join(', ');
                            
                            // Run the Geocoder request for the address.
                            $('#map').gmap('search', {'address': address } , function(result, status) {
                                if (status == google.maps.GeocoderStatus.OK) {
                                    // Create a LatLng object.
                                    var lat = result[0].geometry.location.lat();
                                    var lng = result[0].geometry.location.lng();
                                    var latlng = new google.maps.LatLng(lat, lng);

                                    var infobox = '';
                                    infobox += '<div class="maps">';
                                    infobox += '<div class="close"><a href="#"></a></div>';
                                    infobox += '<div style="float:left; width:140px"><img src="'+imagePath+'" width="130" height="95"></div>';
                                    infobox += '<div style="float:right; width:350px" class="mapcont">';
                                    infobox += '<h2 style="font-size:12px">'+placeName+'</h2>';
                                    infobox += '<p>'+street+', '+city+', '+state+'</p>';
                                    infobox += '<p>'+telephone+'</p>';
                                    infobox += '<p style="padding:5px 0 0 0"><a href="javascript:viewRestaurant(\''+restID+'\');" class="btn"><img src="../images/btn-maporder-r.gif"/></a></p>';
                                    infobox += '</div>';
                                    infobox += '<div class="clear" ></div>';
                                    infobox += '</div>';
    

                                    $('#map').gmap('addMarker', {'id': data.id, 'tags':data.tags, 'position': new google.maps.LatLng(lat, lng), 'bounds':true }, function(map,marker) {
                                        $(el).click(function() {
                                            $(marker).triggerEvent('click');
                                        });
                                    }).click(function() {
                                        $('#map').gmap('openInfoWindow', { 'content': infobox }, this);
                                    });
                                } else {
                                    alert("Geocode was not successful for the following reason: " + status);
                                }
                            });
                            

                        });	
                    });//bind
                });
            </script>
        </div>

    </t:putAttribute>
</t:insertDefinition>