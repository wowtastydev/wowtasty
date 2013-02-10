<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="rest.layout">
<t:putAttribute name="main_rest">
<link rel="stylesheet" type="text/css" href="../css/jquery.combobox.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/table_jui.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../fancybox/source/jquery.fancybox.css?v=2.1.3" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/timepicker.css" media="screen"  />
<script src="../js/jquery.combobox.js"></script>
<script src="../fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>
<script src="../js/bootstrap-timepicker.js"></script>
<script src="../js/jquery.dataTables.min.js"></script>

<script>
<!--	
	window.onload = function() {
		// Initialize Delivery Business hours
		// If naDelivFlag is on, disable delivery hours textfields
		naDeliv(document.getElementById("naDelivFlag"));
	};
	
    // Restaurant delivery area max index
	var maxAreaIndex = <s:property value="restDelivArealist.size"/>;

	// Save Restaurant General Info
	function save(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Save Restaurant Business hours
	function saveBizHour(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "saveRestaurantBizHour";
		document.getElementById("frm").submit();
	}
	
	// Save Restaurant Delivery Business hours
	function saveBizHourDeliv(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "saveRestaurantBizHourDeliv";
		document.getElementById("frm").submit();
	}
	
	// Save Restaurant Delivery areas
	function saveArea(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "saveDeliveryArea";
		document.getElementById("frm").submit();
	}
	
	// Instruction textcounter
	function countText(obj, label) {
		var content = obj.value;
		document.getElementById(label).innerHTML = content.length ;
	}
	
	// Close business hour
	function closeBiz(obj, idx) {
		if (obj.checked) {
			document.getElementById("openHourlist[" + idx + "].startTime1").disabled = true;
			document.getElementById("openHourlist[" + idx + "].endTime1").disabled = true;
			document.getElementById("openHourlist[" + idx + "].startTime2").disabled = true;
			document.getElementById("openHourlist[" + idx + "].endTime2").disabled = true;
		} else {
			// unchecked
			document.getElementById("openHourlist[" + idx + "].startTime1").disabled = false;
			document.getElementById("openHourlist[" + idx + "].endTime1").disabled = false;
			document.getElementById("openHourlist[" + idx + "].startTime2").disabled = false;
			document.getElementById("openHourlist[" + idx + "].endTime2").disabled = false;
		}
		
	}
	
	// Close Delivery business hour
	function closeBizDeliv(obj, idx) {
		if (obj.checked) {
			document.getElementById("openHourDelivlist[" + idx + "].startTime1").disabled = true;
			document.getElementById("openHourDelivlist[" + idx + "].endTime1").disabled = true;
			document.getElementById("openHourDelivlist[" + idx + "].startTime2").disabled = true;
			document.getElementById("openHourDelivlist[" + idx + "].endTime2").disabled = true;
		} else {
			// unchecked
			document.getElementById("openHourDelivlist[" + idx + "].startTime1").disabled = false;
			document.getElementById("openHourDelivlist[" + idx + "].endTime1").disabled = false;
			document.getElementById("openHourDelivlist[" + idx + "].startTime2").disabled = false;
			document.getElementById("openHourDelivlist[" + idx + "].endTime2").disabled = false;
		}
	}
	
	// Not available Delivery business hour
	function naDeliv(obj) {
		
		if (obj.checked) {

			// Disable Every day dropdown menu
			document.getElementById("bizDelivAll1Start").disabled = true;
			document.getElementById("bizDelivAll1End").disabled = true;
			document.getElementById("bizDelivAll2Start").disabled = true;
			document.getElementById("bizDelivAll2End").disabled = true;
			
			// Disable Set by day dropdown menu
			for (var i = 0; i < 7; i++) {
				document.getElementById("openHourDelivlist[" + i + "].startTime1").disabled = true;
				document.getElementById("openHourDelivlist[" + i + "].endTime1").disabled = true;
				document.getElementById("openHourDelivlist[" + i + "].startTime2").disabled = true;
				document.getElementById("openHourDelivlist[" + i + "].endTime2").disabled = true;
			}
			// Disable save button
			document.getElementById("btnDelivOpenHour").disabled = true;
		} else {
			// Enable Every day dropdown menu
			document.getElementById("bizDelivAll1Start").disabled = false;
			document.getElementById("bizDelivAll1End").disabled = false;
			document.getElementById("bizDelivAll2Start").disabled = false;
			document.getElementById("bizDelivAll2End").disabled = false;
			
			// Enable Set by day dropdown menu
			for (var i = 0; i < 7; i++) {
				document.getElementById("openHourDelivlist[" + i + "].startTime1").disabled = false;
				document.getElementById("openHourDelivlist[" + i + "].endTime1").disabled = false;
				document.getElementById("openHourDelivlist[" + i + "].startTime2").disabled = false;
				document.getElementById("openHourDelivlist[" + i + "].endTime2").disabled = false;
			}
			// Enable save button
			document.getElementById("btnDelivOpenHour").disabled = false;
		}
	}
	
	function openSearchDeliveryArea() {
		var restid = document.getElementById("selectedID").value;
		if (restid == "") {
			// Show Error Message
			errorMessage.innerHTML = "Please save Restaurant General Information first.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		
		$( "#searchDeliveryCompany" ).dialog( "open" );
		
	}
	
	// Change Postal code map
	function changeMap(obj) {
		document.getElementById("map_info").innerHTML = "<img src='../images/map/" + obj.value + ".gif' >" ;
	}
	
	// Select delivery area from delivery panel
	function addRow(i){
		var restid = document.getElementById("selectedID").value;
		var prefix = document.getElementById("delivArealist[" + i + "].postalPrefix").value;
		var id = document.getElementById("delivArealist[" + i + "].deliveryCompanyID").value;
		var companyName = document.getElementById("delivArealist[" + i + "].deliveryCompanyName").value;
		var min = document.getElementById("delivArealist[" + i + "].minPrice").value;
		var fee = document.getElementById("delivArealist[" + i + "].deliveryFee").value;
		var tbody = document.getElementById('areaTbl').getElementsByTagName("tbody")[0];
		var obj_row;
		if(/*@cc_on!@*/true){
			obj_row = document.createElement("TR");
			tbody.appendChild(obj_row);
		}else{
			obj_row = tbody.insertRow();
		}
		
		var td_del = document.createElement("TD");
		td_del.setAttribute("align", "center");
		obj_row.appendChild(td_del);
		
		var input_del = document.createElement("input"); 
		input_del.setAttribute("type", "checkbox");
		input_del.setAttribute("name", "chk");
		td_del.appendChild(input_del);
		
		var td_prefix = document.createElement("TD");
		td_prefix.setAttribute("align", "center");
		obj_row.appendChild(td_prefix);
		
		var input_prefix = document.createElement("input"); 
		input_prefix.setAttribute("type", "text");
		input_prefix.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].postalPrefix");
		input_prefix.setAttribute("value", prefix);
		input_prefix.setAttribute("size", "3");
		input_prefix.setAttribute("maxlength", "3");
		input_prefix.setAttribute("readonly", "true");
		td_prefix.appendChild(input_prefix);
		
		var td_name = document.createElement("TD");
		obj_row.appendChild(td_name);
		
		var input_name = document.createElement("input"); 
		input_name.setAttribute("type", "text");
		input_name.setAttribute("value", companyName);
		input_name.setAttribute("readonly", "true");
		td_name.appendChild(input_name);
		
		var input_id = document.createElement("input"); 
		input_id.setAttribute("type", "hidden");
		input_id.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].deliveryCompanyID");
		input_id.setAttribute("value", id);
		td_name.appendChild(input_id);
		
		var input_restid = document.createElement("input"); 
		input_restid.setAttribute("type", "hidden");
		input_restid.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].restaurantID");
		input_restid.setAttribute("value", restid);
		td_name.appendChild(input_restid);
		
		var td_min = document.createElement("TD");
		td_min.setAttribute("align", "center");
		obj_row.appendChild(td_min);
		
		var input_min = document.createElement("input"); 
		input_min.setAttribute("type", "text");
		input_min.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].minPrice");
		input_min.setAttribute("value", min);
		input_min.setAttribute("size", "6");
		input_min.setAttribute("maxlength", "6");
		input_min.setAttribute("readonly", "true");
		td_min.appendChild(input_min);
		
		var td_fee = document.createElement("TD");
		td_fee.setAttribute("align", "center");
		obj_row.appendChild(td_fee);
		
		var input_fee = document.createElement("input"); 
		input_fee.setAttribute("type", "text");
		input_fee.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].deliveryFee");
		input_fee.setAttribute("value", fee);
		input_fee.setAttribute("size", "6");
		input_fee.setAttribute("maxlength", "6");
		input_fee.setAttribute("readonly", "true");
		td_fee.appendChild(input_fee);
		
		maxAreaIndex++;
	}
	
	// Area add row
	function addRowBlank() {
		var restid = document.getElementById("selectedID").value;
		if (restid == "") {
			// Show Error Message
			errorMessage.innerHTML = "Please save Restaurant General Information first.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		
		var id = document.getElementById("deliveryCompanyID").value;
		if (id == "") {
			// Show Error Message
			errorMessage.innerHTML = "Please save Delivery Hour first or Click the button 'Search From Delivery Company'.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		
		var companyName = document.getElementById("name").value;
		var tbody = document.getElementById('areaTbl').getElementsByTagName("tbody")[0];
		var obj_row;
		if(/*@cc_on!@*/true){
			obj_row = document.createElement("TR");
			tbody.appendChild(obj_row);
		}else{
			obj_row = tbody.insertRow();
		}
		
		var td_del = document.createElement("TD");
		td_del.setAttribute("align", "center");
		obj_row.appendChild(td_del);
		
		var input_del = document.createElement("input"); 
		input_del.setAttribute("type", "checkbox");
		input_del.setAttribute("name", "chk");
		td_del.appendChild(input_del);
		
		var td_prefix = document.createElement("TD");
		td_prefix.setAttribute("align", "center");
		obj_row.appendChild(td_prefix);
		
		var input_prefix = document.createElement("input"); 
		input_prefix.setAttribute("type", "text");
		input_prefix.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].postalPrefix");
		input_prefix.setAttribute("value", "");
		input_prefix.setAttribute("size", "3");
		input_prefix.setAttribute("maxlength", "3");
		td_prefix.appendChild(input_prefix);
		
		var td_name = document.createElement("TD");
		obj_row.appendChild(td_name);
		
		var input_name = document.createElement("input"); 
		input_name.setAttribute("type", "text");
		input_name.setAttribute("value", companyName);
		input_name.setAttribute("readonly", "true");
		td_name.appendChild(input_name);
		
		var input_id = document.createElement("input"); 
		input_id.setAttribute("type", "hidden");
		input_id.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].deliveryCompanyID");
		input_id.setAttribute("value", id);
		td_name.appendChild(input_id);
		
		var input_restid = document.createElement("input"); 
		input_restid.setAttribute("type", "hidden");
		input_restid.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].restaurantID");
		input_restid.setAttribute("value", restid);
		td_name.appendChild(input_restid);
		
		var td_min = document.createElement("TD");
		td_min.setAttribute("align", "center");
		obj_row.appendChild(td_min);
		
		var input_min = document.createElement("input"); 
		input_min.setAttribute("type", "text");
		input_min.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].minPrice");
		input_min.setAttribute("value", "0.00");
		input_min.setAttribute("size", "6");
		input_min.setAttribute("maxlength", "6");
		td_min.appendChild(input_min);
		
		var td_fee = document.createElement("TD");
		td_fee.setAttribute("align", "center");
		obj_row.appendChild(td_fee);
		
		var input_fee = document.createElement("input"); 
		input_fee.setAttribute("type", "text");
		input_fee.setAttribute("name", "restDelivArealist[" + maxAreaIndex + "].deliveryFee");
		input_fee.setAttribute("value", "0.00");
		input_fee.setAttribute("size", "6");
		input_fee.setAttribute("maxlength", "6");
		td_fee.appendChild(input_fee);
		
		maxAreaIndex++;
	}
	
	// Area delete rows
	function delRow(){
		var tbody = document.getElementById("areaTbl").getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		
        for(var i=0; i<rowCount; i++) {
            var row = tbody.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && undefined == chkbox.checked) {
                // In case restDelivArealist, checkbox is node[1]
                chkbox = row.cells[0].childNodes[1];
                if(null != chkbox && true == chkbox.checked) {
                	tbody.deleteRow(i);
                    rowCount--;
                    i--;
                }
            } else {
                if(null != chkbox && true == chkbox.checked) {
                	tbody.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        }
	}
	
	// Add restaurant name, cuisine type etc to keywords
	function copy2Keywords(value) {
		var keywords = keyword.value;
		if (keywords == "") {
			keyword.value = value;
		} else {
			// Only if there is not the same value, add the text
			if (keywords.indexOf(value) < 0) {
				keyword.value = keyword.value + "," + value;
			}
		}
		
	}
	
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
        // Set up tab
        $( "#tabs" ).tabs();
        
		// Set up dropdown menu
		$("#cuisineType, #city, #province, #status, #restaurantType").combobox();
        
        $('#cuisineType').change(function () {
        	copy2Keywords($(this).find('option:selected').text());
       	});

        // Set up accordion
        $( "#restaurantInfo" ).accordion({
        	active: <s:property value="active"/>,
        	collapsible: true,
        	alwaysOpen: false,
            heightStyle: "content"
        });
        
        // Set up button
        $("input[type=button]").button();
        
        // Set up spinner
        $( "#averagePrice, #minPrice, #minPrice1" ).spinner({
            min: 0.00,
            max: 999.99,
            step: 0.25,
            numberFormat: "n"
        });
        $( "#deliveryTime" ).spinner({
            min: 5,
            max: 100,
            step: 5,
            numberFormat: "n"
        });
        $( "#commission, #cashCommission" ).spinner({
            min: 1,
            max: 100,
            step: 1,
            numberFormat: "n"
        });
        
        $('.timepicker').timepicker({
            minuteStep: 15,
            showInputs: false,
            disableFocus: false,
            defaultTime: false,
            showMeridian: false,
            showSeconds: false
        });
        
        // Set up image
        $(".fancybox").fancybox();
        
        // Search Delivery Company
        $( "#searchDeliveryCompany" ).dialog({
            autoOpen: false,
            width: 480,
            height: 450
        });
        
		$('#listTable').dataTable( {
			"bJQueryUI": true,
			"bSort": false,
			"bPaginate": false,
			"bFilter": false
		} );
        
    	// Google Map Panel
        $( "#googleMap" ).dialog({
            autoOpen: false,
            width: 480,
            height: 450
        });
    	
        // How to get Google Map Link Panel
        $( "#googleMapLink" ).dialog({
            autoOpen: false,
            width: 1020,
            height: 650
        });
    	
        $( "#showMap" ).click(function() {
        	// HTML decode
    		mapHtml.innerHTML = $("<div/>").html("<s:property value='%{vo.googleMapURL}'/>").text();
            $( "#googleMap" ).dialog( "open" );
            return false;
        });
        
        $( "#showLink" ).click(function() {
        	// HTML decode
    		mapLink.innerHTML = "<img src='../images/admin/howtofindgooglemap.jpg' />";
            $( "#googleMapLink" ).dialog( "open" );
            return false;
        });
        
	} );
//-->
</script>
    
<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('R201');">Restaurant List</a>
			</div>
		</div>
	
		<div id="contentarea">
		<h2>Edit Restaurant - <s:property value="vo.name"/></h2>
		
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="detailarea">
				<div id="tabs">
					<ul>
				        <li><a href="#Info" onClick ="javascript:goPageParam('R202','<s:property value="selectedID"/>');">Information</a></li>
				        <li><a href="#Menu" onClick ="javascript:goPageParam('R204','<s:property value="selectedID"/>');">Menu</a></li>
				        <li><a href="#Photo" onClick ="javascript:goPageParam('R203','<s:property value="selectedID"/>');">Photo</a></li>
				    </ul>
				    <s:form theme="simple" name="frm" id="frm" action="saveRestaurant" method="POST" enctype="multipart/form-data">
				    <div id="Info">
						<div id="restaurantInfo">
							<h3>General Information</h3>
							<div>
								<s:hidden name="selectedID" id="selectedID"/>
								<s:hidden name="vo.memberID" />
								<s:hidden name="vo.deliveryCompanyID" id="deliveryCompanyID" />
								<s:hidden name="vo.totalReviewCnt" />
								<s:hidden name="vo.totalOrderCnt" />
								<s:hidden name="vo.naFlag" />
								<s:hidden name="vo.registerDate" />
								<table width="auto">
									<s:if test='%{"".equals(vo.restaurantID)}'>
									<!-- Insert Case -->
									<tr>
										<td colspan=4 width="50%"><s:hidden name="vo.restaurantID" /></td>
										<td colspan=4 width="50%"></td>
									</tr>
									</s:if>
									<s:else>
									<!-- Edit Case -->
									<tr>
										<td>Restaurant ID:</td>
										<td colspan=3>
											<s:property value="vo.restaurantID"/></td>
											<s:hidden name="vo.restaurantID" />
										<td colspan=4 width="50%"></td>
									</tr>
									</s:else>
									<tr>
										<td>Restaurant Name<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:textfield name="vo.name" id="name" size="40" maxlength="50"  onblur="javascript:copy2Keywords(this.value);" /></td>
										<td>Cuisine Type<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:select name="vo.cuisineType" id="cuisineType" list="cuisineTypeList" listKey="code" listValue="shortName"  headerKey="" headerValue="" /></td>
									</tr>
									<tr>
										<td>First Name:</td>
										<td colspan=3><s:textfield name="vo.firstName" id="firstName" size="40" maxlength="50"/></td>
										<td>Last Name:</td>
										<td colspan=3><s:textfield name="vo.lastName" id="lastName" size="40" maxlength="50"/></td>
									</tr>
									<tr>
										<td>Telephone<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:textfield name="vo.telephone" id="telephone" size="20" maxlength="20"/></td>
										<td>FAX:</td>
										<td colspan=3><s:textfield name="vo.fax" id="fax" size="20" maxlength="20"/></td>
									</tr>
									<tr>
										<td>Address<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:textfield name="vo.address" id="address" size="40" maxlength="100"/></td>
										<td>Suite Number:</td>
										<td colspan=3><s:textfield name="vo.suite" id="suite" size="7" maxlength="10"/></td>
									</tr>
									<tr>
										<td>City<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:select name="vo.city" id="city" list ="cityList" listKey="code" listValue="shortName" /></td>
										<td>Province<FONT color=red>*</FONT>:</td>
										<td width="100"><s:select name="vo.province" id="province" list ="provinceList" listKey="code" listValue="shortName" /></td>
										<td>Postal Code<FONT color=red>*</FONT>:</td>
										<td><s:textfield name="vo.postalCode" id="postalCode" size="8" maxlength="7"/></td>
									</tr>
									<tr>
										<td>Web site:</td>
										<td colspan=3><s:textfield name="vo.website" id="website" size="30" maxlength="100"/></td>
										<td>Facebook:</td>
										<td colspan=3><s:textfield name="vo.facebook" id="facebook" size="30" maxlength="100"/>
										</td>
									</tr>
									<tr>
										<td>E-mail1<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:textfield name="vo.email1" id="email1" size="30" maxlength="256"/></td>
										<td>E-mail2:</td>
										<td colspan=3><s:textfield name="vo.email2" id="email2" size="30" maxlength="256"/></td>
									</tr>
									<tr>
										<td>Logo:</td>
										<td colspan=3>
											<s:if test='%{!"".equals(vo.logoImagePath)}'>
												<a class="fancybox" href="../pictures/restaurant/<s:property value='%{vo.logoImagePath}'/>">
												<img src="../pictures/restaurant/thumb_<s:property value='%{vo.logoImagePath}'/>">
												</a>
												<s:hidden name="vo.logoImagePath" />
											</s:if>
											<s:else>
												<img src="../images/admin/noimage.jpg">
											</s:else>
											<br><s:file name="logofile" id="logofile" accept="image/*" />
										</td>
										<td>Main Image:</td>
										<td colspan=3>
											<s:if test='%{!"".equals(vo.mainImagePath)}'>
												<a class="fancybox" href="../pictures/restaurant/<s:property value='%{vo.mainImagePath}'/>">
												<img src="../pictures/restaurant/thumb_<s:property value='%{vo.mainImagePath}'/>">
												</a>
												<s:hidden name="vo.mainImagePath" />
											</s:if>
											<s:else>
												<img src="../images/admin/noimage.jpg">
											</s:else>
											<br><s:file name="mainfile" id="mainfile" value="%{mainfile}" accept="image/*" />
										</td>
									</tr>
									<tr>
										<td valign="top">Google Map:<br>Text Length: <span id="google_len_info">0</span><br><font color="#f00">(Max 1500 char)</font></td>
										<td colspan=7>
											<s:textarea rows="3" cols="100" name="vo.googleMapURL" id="googleMapURL" onkeyup="javascript:countText(this, 'google_len_info');"></s:textarea>
											<br><a id="showLink">How to find Google Map Link</a>
											<s:if test='%{!"".equals(vo.googleMapURL)}'>
											&nbsp;&nbsp;<a id="showMap">Show Google Map</a>
											</s:if>
										</td>
									</tr>
									<tr>
										<td valign="top">Restaurant Profile:<br>Text Length: <span id="profile_len_info">0</span><br><font color="#f00">(Max 500 char)</font></td>
										<td colspan=7>
											<s:textarea rows="3" cols="100" name="vo.profile" id="profile" onkeyup="javascript:countText(this, 'profile_len_info');"></s:textarea>
										</td>
									</tr>
									<tr>
										<td valign="top">Search Key Words:<br>Text Length: <span id="keyword_len_info">0</span><br><font color="#f00">(Max 500 char)</font></td>
										<td colspan=7>
											<s:textarea rows="3" cols="100" name="vo.keyword" id="keyword" onkeyup="javascript:countText(this, 'keyword_len_info');"></s:textarea>
											<div id="instruction" class="ui-state-highlight ui-corner-all" style="margin-top: 5px; margin-bottom: 5px; padding: 0 .7em;">
											<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
												Input keywords splitted by <strong>comma(,)</strong>. Some of Restaurant information will be typed automatically.</p>
										</div>
										</td>
									</tr>
									<tr>
										<td>Delivery/Take-out<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:select name="vo.restaurantType" id="restaurantType" list ="restaurantTypeList" listKey="code" listValue="name" headerKey="" headerValue="" /></td>
										<td>Delivery time(Minutes):</td>
										<td colspan=3><s:textfield name="vo.deliveryTime" id="deliveryTime" size="3" maxlength="3"/></td>
									</tr>
									<tr>
										<td>Status<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:select name="vo.status" id="status" list ="restaurantStatusList" listKey="code" listValue="name" headerKey="" headerValue="" /></td>
										<td>Billing Account NO.<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:textfield name="vo.billAccountNO" id="billAccountNO" size="10" maxlength="6"/></td>
									</tr>							
									<tr>
										<td>NonCash Commission(%)<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:textfield name="vo.commission" id="commission" size="2" maxlength="2"/></td>
										<td>Cash Commission(%)<FONT color=red>*</FONT>:</td>
										<td colspan=3><s:textfield name="vo.cashCommission" id="cashCommission" size="2" maxlength="2"/></td>
									</tr>
									<tr>
										<td>Ave. Price($):</td>
										<td colspan=3><s:textfield name="vo.averagePrice" id="averagePrice" size="6" maxlength="6"/></td>
										<td>Min. Order($):</td>
										<td colspan=3><s:textfield name="vo.minPrice" id="minPrice" size="6" maxlength="6"/></td>
									</tr>
								</table>
								<table width="100%">
									<tr>
										<td><hr></td>
									</tr>
									<tr>
										<td>
											<input type="button" value="Save" onClick="javascript:save();" />
										</td>
									</tr>
								</table>
							</div>
							<h3>Business Hour</h3>
						    <div>
						    	<div style="padding-bottom:10px;">
						    	<input type="radio" name="bizHourType" id="bizHourType" value="0"> Every day
						        <table class="tableborder" width="400">
									<tr>
										<th colspan=2>Open1(From ~ To) </th>
										<th colspan=2>Open2(From ~ To) </th>
									</tr>
									<tr>
										<td align="center"><s:textfield name="bizAll1Start" id="bizAll1Start" value="11:00" size="5" maxlength="5" cssClass="timepicker"/></td>
										<td align="center"><s:textfield name="bizAll1End" id="bizAll1End" value="15:00" size="5" maxlength="5" cssClass="timepicker"/></td>
										<td align="center"><s:textfield name="bizAll2Start" id="bizAll2Start" value="17:00" size="5" maxlength="5" cssClass="timepicker"/></td>
										<td align="center"><s:textfield name="bizAll2End" id="bizAll2End" value="20:00" size="5" maxlength="5" cssClass="timepicker"/></td>
									</tr>
								</table>
								</div>
								<div>
								<input type="radio" name="bizHourType" id="bizHourType" value="1"  checked="true"> Set by day
						        <table class="tableborder" width="500">
									<tr>
										<th>Day </th>
										<th>Closed </th>
										<th colspan=2>Open1(From ~ To) </th>
										<th colspan=2>Open2(From ~ To) </th>
									</tr>
									<s:iterator value="openHourlist" id="openHourlist" status="outerStat">
									<tr>
										<td align="center">
										<s:if test="%{weekDay==0}">
										   MON
										</s:if>
										<s:elseif test="%{weekDay==1}">
										   TUE
										</s:elseif>
										<s:elseif test="%{weekDay==2}">
										   WED
										</s:elseif>
										<s:elseif test="%{weekDay==3}">
										   THU
										</s:elseif>
										<s:elseif test="%{weekDay==4}">
										   FRI
										</s:elseif>
										<s:elseif test="%{weekDay==5}">
										   SAT
										</s:elseif>
										<s:elseif test="%{weekDay==6}">
										   SUN
										</s:elseif>
										</td>
										<td align="center">
											<s:checkbox name="openHourlist[%{#outerStat.index}].closeFlag" id="openHourlist[%{#outerStat.index}].closeFlag" onclick="javascript:closeBiz(this, '%{#outerStat.index}');" />
										    <s:hidden name="openHourlist[%{#outerStat.index}].ID"/>
											<s:hidden name="openHourlist[%{#outerStat.index}].weekDay"/>
										    <s:hidden name="openHourlist[%{#outerStat.index}].seq"/>
										</td>
										<td align="center">
											<s:textfield name="openHourlist[%{#outerStat.index}].startTime1" id="openHourlist[%{#outerStat.index}].startTime1" size="5" maxlength="5" cssClass="timepicker" />
										</td>
										<td align="center">
											<s:textfield name="openHourlist[%{#outerStat.index}].endTime1" id="openHourlist[%{#outerStat.index}].endTime1" size="5" maxlength="5" cssClass="timepicker" />
										</td>
										<td align="center">
											<s:textfield name="openHourlist[%{#outerStat.index}].startTime2" id="openHourlist[%{#outerStat.index}].startTime2" size="5" maxlength="5" cssClass="timepicker" />
										</td>
										<td align="center">
											<s:textfield name="openHourlist[%{#outerStat.index}].endTime2" id="openHourlist[%{#outerStat.index}].endTime2" size="5" maxlength="5" cssClass="timepicker" />
										</td>
									</tr>
									</s:iterator>
								</table>
								</div>
								<table width="100%">
									<tr>
										<td><hr></td>
									</tr>
									<tr>
										<td>
											<input type="button" value="Save" onClick="javascript:saveBizHour();" />
										</td>
									</tr>
								</table>
	
						    </div>
						    <h3>Delivery Hour</h3>
						    <div>
						    	<div style="padding-bottom:10px;">
						    	<table>
									<tr>
										<td width="10"><s:checkbox name="naDelivFlag" id="naDelivFlag" onclick="javascript:naDeliv(this);" /></td>
										<td valign="middle">Not Available</td>
										<td>
											<div id="instruction" class="ui-state-highlight ui-corner-all" style="width:320; padding: 0 .7em;">
												<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
													<strong>In case of self-delivery service available, uncheck it to save delivery hours</strong></p>
											</div>
										</td>
									</tr>
								</table>
						    	
						    	<input type="radio" name="bizHourDelivType" id="bizHourDelivType" value="0"> Every day
						        <table class="tableborder" width="500">
									<tr>
										<th colspan=2>Open1(From ~ To) </th>
										<th colspan=2>Open2(From ~ To) </th>
									</tr>
									<tr>
										<td align="center"><s:textfield name="bizDelivAll1Start" id="bizDelivAll1Start" size="5" maxlength="5" value="11:00" cssClass="timepicker"/></td>
										<td align="center"><s:textfield name="bizDelivAll1End" id="bizDelivAll1End" size="5" maxlength="5" value="15:00" cssClass="timepicker"/></td>
										<td align="center"><s:textfield name="bizDelivAll2Start" id="bizDelivAll2Start" size="5" maxlength="5" value="17:00" cssClass="timepicker"/></td>
										<td align="center"><s:textfield name="bizDelivAll2End" id="bizDelivAll2End" size="5" maxlength="5" value="22:00" cssClass="timepicker"/></td>
									</tr>
								</table>
								</div>
								<div>
								<input type="radio" name="bizHourDelivType" id="bizHourDelivType" value="1"  checked="true"> Set by day
						        <table class="tableborder" width="500">
									<tr>
										<th>Day </th>
										<th>Closed </th>
										<th colspan=2>Open1(From ~ To) </th>
										<th colspan=2>Open2(From ~ To) </th>
									</tr>
									<s:iterator value="openHourDelivlist" id="openHourDelivlist" status="outerStat">
									<tr>
										<td align="center">
										<s:if test="%{weekDay==0}">
										   MON
										</s:if>
										<s:elseif test="%{weekDay==1}">
										   TUE
										</s:elseif>
										<s:elseif test="%{weekDay==2}">
										   WED
										</s:elseif>
										<s:elseif test="%{weekDay==3}">
										   THU
										</s:elseif>
										<s:elseif test="%{weekDay==4}">
										   FRI
										</s:elseif>
										<s:elseif test="%{weekDay==5}">
										   SAT
										</s:elseif>
										<s:elseif test="%{weekDay==6}">
										   SUN
										</s:elseif>
										</td>
										<td align="center">
											<s:checkbox name="openHourDelivlist[%{#outerStat.index}].closeFlag" id="openHourDelivlist[%{#outerStat.index}].closeFlag" onclick="javascript:closeBizDeliv(this, '%{#outerStat.index}');" />
										    <s:hidden name="openHourDelivlist[%{#outerStat.index}].ID"/>
											<s:hidden name="openHourDelivlist[%{#outerStat.index}].weekDay"/>
										    <s:hidden name="openHourDelivlist[%{#outerStat.index}].seq"/>
										</td>
										<td align="center">
											<s:textfield name="openHourDelivlist[%{#outerStat.index}].startTime1" id="openHourDelivlist[%{#outerStat.index}].startTime1" size="5" maxlength="5" cssClass="timepicker" />
										</td>
										<td align="center">
											<s:textfield name="openHourDelivlist[%{#outerStat.index}].endTime1" id="openHourDelivlist[%{#outerStat.index}].endTime1" size="5" maxlength="5" cssClass="timepicker" />
										</td>
										<td align="center">
											<s:textfield name="openHourDelivlist[%{#outerStat.index}].startTime2" id="openHourDelivlist[%{#outerStat.index}].startTime2" size="5" maxlength="5" cssClass="timepicker" />
										</td>
										<td align="center">
											<s:textfield name="openHourDelivlist[%{#outerStat.index}].endTime2" id="openHourDelivlist[%{#outerStat.index}].endTime2" size="5" maxlength="5" cssClass="timepicker" />
										</td>								
									</tr>
									</s:iterator>
								</table>
								</div>
								<table width="100%">
									<tr>
										<td><hr></td>
									</tr>
									<tr>
										<td>
											<input type="button" id="btnDelivOpenHour" value="Save" onClick="javascript:saveBizHourDeliv();" />
										</td>
									</tr>
								</table>
	
						    </div>
						    <h3>Delivery Area</h3>
						    <div>
								<div>
									<table width="auto">
										<tr>
											<td>
												<input type="button" value="Add Row" onClick ="javascript:addRowBlank();"/>&nbsp;
												<input type="button" value="Del Row" onClick ="javascript:delRow();"/>
											</td>
											<td align="right">
												<input type="button" value="Search From Delivery Company" onClick ="javascript:openSearchDeliveryArea();"/>
											</td>
										</tr>
									</table>
							    
									<div>
									<table>
										<tr><td valign="top">
											<table class="tableborder" id="areaTbl" width="auto">
												<thead>
												<tr>
													<th>Del</th>
													<th>Postal Code Prefix</th>
													<th>Delivery Company</th>
													<th>Min. Order </th>
													<th>Delivery Fee</th>
												</tr>
												</thead>
												<tbody>
												<s:iterator value="restDelivArealist" id="restDelivArealist" status="outerStat">
												<tr>
													<td align="center">
														<s:checkbox name="chk" id="restDelivArealist[%{#outerStat.index}].chk"/>
													</td>
													<td align="center">
														<s:property value="postalPrefix"/>
														<s:hidden name="restDelivArealist[%{#outerStat.index}].postalPrefix" id="restDelivArealist[%{#outerStat.index}].postalPrefix"/>
														<s:hidden name="restDelivArealist[%{#outerStat.index}].restaurantID"/>
														<s:hidden name="restDelivArealist[%{#outerStat.index}].seq"/>
													</td>
													<td>
														<s:set var="delivID" value="deliveryCompanyID" />
														<s:iterator value="deliveryCompanyList" id="deliveryCompanyList" status="outerStat">
															<s:if test='%{#delivID.equals(deliveryCompanyID)}'>
																<s:property value="%{name}"/>
															</s:if>
														</s:iterator>
													    <s:hidden name="restDelivArealist[%{#outerStat.index}].deliveryCompanyID"/>
													</td>
													<td align="center">
														<s:textfield name="restDelivArealist[%{#outerStat.index}].minPrice" id="restDelivArealist[%{#outerStat.index}].minPrice" size="6" maxlength="6" />
													</td>
													<td align="center">
														<s:textfield name="restDelivArealist[%{#outerStat.index}].deliveryFee" id="restDelivArealist[%{#outerStat.index}].deliveryFee" size="6" maxlength="6" />
													</td>
												</tr>
												</s:iterator>
												</tbody>
											</table>
											</td>
											<!-- Search Area Code Map -->
											<td valign="top">
												<table width="380">
													<tr>
														<td>
															<s:select name="cityMap" id="cityMap" list ="cityMapList" listKey="code" listValue="shortName" onchange="javascript:changeMap(this);"/>
														</td>
													</tr>
													<tr>
														<td>
															<span id="map_info"><img src="../images/map/010011.gif"></span>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									</div>
								</div>
								<table width="100%">
									<tr>
										<td><hr></td>
									</tr>
									<tr>
										<td>
											<input type="button" value="Save" onClick="javascript:saveArea();" />
										</td>
									</tr>
								</table>
	
						    </div>
						    
						</div>
						<table width="100%">
							<tr>
								<td><hr></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="List" onClick="javascript:goPage('A203');" />
								</td>
							</tr>
						</table>
					</div>
					<div id="Menu">
					</div>
					<div id="Photo">
					</div>
					
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- Google Map Panel -->
<div id="googleMap" title="Restaurant Location">
	<span id="mapHtml"></span>
</div>

<!-- Google Map Link Panel -->
<div id="googleMapLink" title="How to find Google Map Link">
	<span id="mapLink"></span>
</div>

<!-- Search Delivery Company Panel -->
<div id="searchDeliveryCompany" title="Search Delivery Company">
	<s:form theme="simple" name="frmInfo" id="frmInfo" action="searchDeliveryArea" >
	<table width="100%">
		<tr>
		<!-- Search company -->
		<td valign="top">
			<div style="font-size: 10px!important;">
				<table id="listTable" class="display">
					<thead>
					<tr>
						<th>Prefix</th>
						<th>Company Name</th>
						<th>Min</th>
						<th>Fee</th>
						<th>Add</th>
					</tr>
					</thead>
					<tbody>
					<s:iterator value="delivArealist" id="delivArealist" status="outerStat">
					<tr>
						<td align="center">
							<s:property value="postalPrefix"/>
							<s:hidden name="delivArealist[%{#outerStat.index}].postalPrefix" id="delivArealist[%{#outerStat.index}].postalPrefix"/>
							<s:hidden name="delivArealist[%{#outerStat.index}].seq"/>
						</td>
						<td>
							<s:set var="delivID" value="deliveryCompanyID" />
							<s:iterator value="deliveryCompanyList" id="deliveryCompanyList" status="outerStat2">
								<s:if test='%{#delivID.equals(deliveryCompanyID)}'>
									<s:property value="%{name}"/>
									<s:hidden id="delivArealist[%{#outerStat.index}].deliveryCompanyName" value="%{name}"/>
								</s:if>
							</s:iterator>
						    <s:hidden name="delivArealist[%{#outerStat.index}].deliveryCompanyID" id="delivArealist[%{#outerStat.index}].deliveryCompanyID"/>
						</td>
						<td align="center">
							<s:textfield name="delivArealist[%{#outerStat.index}].minPrice" id="delivArealist[%{#outerStat.index}].minPrice" size="6" maxlength="6" />
						</td>
						<td align="center">
							<s:textfield name="delivArealist[%{#outerStat.index}].deliveryFee" id="delivArealist[%{#outerStat.index}].deliveryFee" size="6" maxlength="6" />
						</td>
						<td align="center">
							<input type="button" value="Add" onClick ="javascript:addRow('<s:property value="%{#outerStat.index}"/>');" />
						</td>
					</tr>
					</s:iterator>
					</tbody>
				</table>
				</div>
		</td>
		</tr>

	</table>
	</s:form>
</div>

</t:putAttribute>
</t:insertDefinition>