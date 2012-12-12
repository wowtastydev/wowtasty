<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/jquery.combobox.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../fancybox/source/jquery.fancybox.css?v=2.1.3" media="screen" />
<script src="../js/jquery.combobox.js"></script>
<script src="../fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>

<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/timepicker.css" media="screen"  />
<script src="../js/bootstrap-timepicker.js"></script>


<script>
<!--	
	function save(form){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		form.submit();
	}
	
	// Instruction textcounter
	function countText(obj) {
		content = obj.value;
		len_info.innerHTML = content.length ;
	}
	
	// Close business hour
	function closeBiz(obj, idx) {
		if (obj.checked) {
			document.getElementById("openHour1list[" + idx + "].startTime").value = "00:00:00";
			document.getElementById("openHour1list[" + idx + "].endTime").value = "00:00:00";
			document.getElementById("openHour2list[" + idx + "].startTime").value = "00:00:00";
			document.getElementById("openHour2list[" + idx + "].endTime").value = "00:00:00";
		}
		
	}
	
	// Close Delivery business hour
	function closeBizDeliv(obj, idx) {
		if (obj.checked) {
			document.getElementById("openHourDeliv1list[" + idx + "].startTime").value = "00:00:00";
			document.getElementById("openHourDeliv1list[" + idx + "].endTime").value = "00:00:00";
			document.getElementById("openHourDeliv2list[" + idx + "].startTime").value = "00:00:00";
			document.getElementById("openHourDeliv2list[" + idx + "].endTime").value = "00:00:00";
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
		$("#cuisineType").combobox();
        $("#city").combobox();
        $("#province").combobox();
        $("#status").combobox();
        $("#restaurantType").combobox();

        // Set up accordion
        $( "#accordion" ).accordion({
        	collapsible: true,
            heightStyle: "content"
        });
        
        // Set up button
        $("input[type=button]").button();
        
        // Set up spinner
        $( "#averagePrice, #minPrice" ).spinner({
            min: 0.00,
            max: 100.00,
            step: 0.01,
            numberFormat: "n"
        });
        $( "#deliveryTime" ).spinner({
            min: 5,
            max: 100,
            step: 5,
            numberFormat: "n"
        });
        $( "#commission" ).spinner({
            min: 1,
            max: 100,
            step: 1,
            numberFormat: "n"
        });
        
        $('.timepicker').timepicker({
            minuteStep: 10,
            showInputs: false,
            disableFocus: false,
            defaultTime: false,
            showMeridian: false,
            showSeconds: true
        });
        
        // Set up image
        $(".fancybox").fancybox();
	} );
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('A203');">Restaurant List</a>
				<a href="javascript:goPage('A204');">Add Restaurant</a>
				<a href="javascript:goPage('A201');">Sign-up List</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:if test='%{"".equals(vo.restaurantID)}'>
		<h2>Add User Account</h2>
		</s:if>
		<s:else>
		<h2>Edit User Account</h2>
		</s:else>
		
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="detailarea">
				<div id="tabs">
					<ul>
				        <li><a href="#Info" onClick ="javascript:goPage('A204');">Information</a></li>
				        <li><a href="#Menu" onClick ="javascript:goPage('A208');">Menu</a></li>
				        <li><a href="#Photo" onClick ="javascript:goPage('A206');">Photo</a></li>
				    </ul>
				    

					<div id="accordion">
						<h3>General Information</h3>
						<div>
						<s:form theme="css_xhtml" name="frmInfo" id="frmInfo" action="saveRestaurant" method="POST" enctype="multipart/form-data">
							<s:hidden name="vo.memberID" />
							<s:hidden name="vo.deliveryCompanyID" />
							<s:hidden name="vo.totalReviewCnt" />
							<s:hidden name="vo.totalOrderCnt" />
							<s:hidden name="vo.naFlag" />
							<s:hidden name="vo.registerDate" />
							<table width="100%">
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
									<td colspan=3><s:textfield name="vo.name" id="name" size="40" maxlength="50"/></td>
									<td>Cuisine Type<FONT color=red>*</FONT>:</td>
									<td colspan=3><s:select name="vo.cuisineType" id="cuisineType" list="cuisineTypeList" listKey="code" listValue="shortName"  headerKey="" headerValue=""/></td>
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
									<td colspan=3><s:select name="vo.city" id="city" list ="cityList" listKey="code" listValue="shortName" headerKey="01001" headerValue="Vancouver" /></td>
									<td>Province<FONT color=red>*</FONT>:</td>
									<td><s:select name="vo.province" id="province" list ="provinceList" listKey="code" listValue="shortName" headerKey="01" headerValue="BC" /></td>
									<td>Postal Code<FONT color=red>*</FONT>:</td>
									<td><s:textfield name="vo.postalCode" id="postalCode" size="7" maxlength="6"/></td>
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
											<a class="fancybox" rel="group" href="../pictures/restaurant/<s:property value='%{vo.logoImagePath}'/>">
											<img src="../pictures/restaurant/thumb_<s:property value='%{vo.logoImagePath}'/>">
											</a>
											<s:hidden name="vo.logoImagePath" />
										</s:if>
										<s:file name="logofile" id="logofile" accept="image/*" />
									</td>
									<td>Main Image:</td>
									<td colspan=3>
										<s:if test='%{!"".equals(vo.mainImagePath)}'>
											<a class="fancybox" rel="group" href="../pictures/restaurant/<s:property value='%{vo.mainImagePath}'/>">
											<img src="../pictures/restaurant/thumb_<s:property value='%{vo.mainImagePath}'/>">
											</a>
											<s:hidden name="vo.mainImagePath" />
										</s:if>
										<s:file name="mainfile" id="mainfile" value="%{mainfile}" accept="image/*" />
									</td>
								</tr>
								<tr>
									<td>Google Map:<br>Text Length: <span id="google_len_info">0</span><br><font color="#f00">(Max 1500 char)</font></td>
									<td colspan=7>
										<s:textarea rows="3" cols="100" name="%{vo.googleMapURL}" id="googleMapURL"></s:textarea>
									</td>
								</tr>
								<tr>
									<td>Restaurant Profile:<br>Text Length: <span id="profile_len_info">0</span><br><font color="#f00">(Max 500 char)</font></td>
									<td colspan=7>
										<s:textarea rows="3" cols="100" name="%{vo.profile}" id="profile"></s:textarea>
									</td>
								</tr>
								<tr>
									<td>Search Key Words:<br>Text Length: <span id="keyword_len_info">0</span><br><font color="#f00">(Max 500 char)</font></td>
									<td colspan=7>
										<s:textarea rows="3" cols="100" name="%{vo.keyword}" id="keyword"></s:textarea>
									</td>
								</tr>
								<tr>
									<td>Delivery/Take-out:<FONT color=red>*</FONT>:</td>
									<td colspan=3><s:select name="vo.restaurantType" id="restaurantType" list ="restaurantTypeList" listKey="code" listValue="name" headerKey="" headerValue="" /></td>
									<td>Delivery time(Minutes):</td>
									<td colspan=3><s:textfield name="vo.deliveryTime" id="deliveryTime" size="3" maxlength="3"/></td>
								</tr>
								<tr>
									<td>Status:<FONT color=red>*</FONT></td>
									<td colspan=3><s:select name="vo.status" id="status" list ="restaurantStatusList" listKey="code" listValue="name" headerKey="" headerValue="" /></td>
									<td>Billing Account NO.<FONT color=red>*</FONT>:</td>
									<td colspan=3><s:textfield name="vo.billAccountNO" id="billAccountNO" size="10" maxlength="6"/></td>
								</tr>							
								<tr>
									<td>Commission(%):<FONT color=red>*</FONT>:</td>
									<td colspan=3><s:textfield name="vo.commission" id="commission" size="2" maxlength="2" value="5"/></td>
									<td>Ave. Price($):</td>
									<td><s:textfield name="vo.averagePrice" id="averagePrice" size="5" maxlength="5" value="0.00"/></td>
									<td>Min. Order($):</td>
									<td><s:textfield name="vo.minPrice" id="minPrice" size="5" maxlength="5" value="0.00"/></td>
								</tr>
								<tr>
									<td colspan=8><hr></td>
								</tr>
								<tr>
									<td align="right" colspan=8>
										<input type="button" value="Save" onClick="javascript:save(this.form);" />
									</td>
								</tr>
							</table>
						</s:form>
						</div>
						<h3>Business Hour</h3>
						<s:form theme="css_xhtml" name="frmBizHour" id="frmBizHour" action="saveRestaurantBizHour">
					    <s:hidden name="selectedID" />
					    <div>
					    	<div style="padding-bottom:10px;">
					    	<input type="radio" name="bizHourType" id="bizHourType" value="0"> Every day
					        <table class="tableborder" width="400">
								<tr>
									<th colspan=2>Open1(From ~ To) </th>
									<th colspan=2>Open2(From ~ To) </th>
								</tr>
								<tr>
									<td><s:textfield name="bizAll1Start" id="bizAll1Start" size="8" maxlength="8" value="11:00:00" cssClass="timepicker"/></td>
									<td><s:textfield name="bizAll1End" id="bizAll1End" size="8" maxlength="8" value="15:00:00" cssClass="timepicker"/></td>
									<td><s:textfield name="bizAll2Start" id="bizAll2Start" size="8" maxlength="8" value="17:00:00" cssClass="timepicker"/></td>
									<td><s:textfield name="bizAll2End" id="bizAll2End" size="8" maxlength="8" value="22:00:00" cssClass="timepicker"/></td>
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
								<s:iterator value="openHour1list" id="openHour1list" status="outerStat">
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
										<s:checkbox name="openHour1list[%{#outerStat.index}]Flag" id="openHour1list[%{#outerStat.index}]Flag" onclick="javascript:closeBiz(this, '%{#outerStat.index}');" />
									    <s:hidden name="openHour1list[%{#outerStat.index}].restaurantID"/>
									    <s:hidden name="openHour2list[%{#outerStat.index}].restaurantID"/>
										<s:hidden name="openHour1list[%{#outerStat.index}].weekDay"/>
									    <s:hidden name="openHour2list[%{#outerStat.index}].weekDay"/>
									    <s:hidden name="openHour1list[%{#outerStat.index}].seq"/>
									    <s:hidden name="openHour2list[%{#outerStat.index}].seq"/>
									</td>
									<td align="center"><s:textfield name="openHour1list[%{#outerStat.index}].startTime" id="openHour1list[%{#outerStat.index}].startTime" cssClass="timepicker" size="8" maxlength="8"/></td>
									<td align="center"><s:textfield name="openHour1list[%{#outerStat.index}].endTime" id="openHour1list[%{#outerStat.index}].endTime" cssClass="timepicker" size="8" maxlength="8"/></td>
									<td align="center"><s:textfield name="openHour2list[%{#outerStat.index}].startTime" id="openHour2list[%{#outerStat.index}].startTime" cssClass="timepicker" size="8" maxlength="8"/></td>
									<td align="center"><s:textfield name="openHour2list[%{#outerStat.index}].endTime" id="openHour2list[%{#outerStat.index}].endTime" cssClass="timepicker" size="8" maxlength="8"/></td>
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
										<input type="button" value="Save" onClick="javascript:save(this.form);" />
									</td>
								</tr>
							</table>

					    </div>
					    </s:form>
					    <h3>Delivery Hour</h3>
						<s:form theme="css_xhtml" name="frmBizHourDeliv" id="frmBizHourDeliv" action="saveRestaurantBizHourDeliv">
					    <s:hidden name="selectedID" />
					    <div>
					    	<div style="padding-bottom:10px;">
					    	<input type="radio" name="bizHourDelivType" id="bizHourDelivType" value="0"> Every day
					        <table class="tableborder" width="400">
								<tr>
									<th colspan=2>Open1(From ~ To) </th>
									<th colspan=2>Open2(From ~ To) </th>
								</tr>
								<tr>
									<td><s:textfield name="bizDelivAll1Start" id="bizDelivAll1Start" size="8" maxlength="8" value="11:00:00" cssClass="timepicker"/></td>
									<td><s:textfield name="bizDelivAll1End" id="bizDelivAll1End" size="8" maxlength="8" value="15:00:00" cssClass="timepicker"/></td>
									<td><s:textfield name="bizDelivAll2Start" id="bizDelivAll2Start" size="8" maxlength="8" value="17:00:00" cssClass="timepicker"/></td>
									<td><s:textfield name="bizDelivAll2End" id="bizDelivAll2End" size="8" maxlength="8" value="22:00:00" cssClass="timepicker"/></td>
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
								<s:iterator value="openHourDeliv1list" id="openHourDeliv1list" status="outerStat">
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
										<s:checkbox name="openHourDeliv1list[%{#outerStat.index}]Flag" id="openHourDeliv1list[%{#outerStat.index}]Flag" onclick="javascript:closeBiz(this, '%{#outerStat.index}');" />
									    <s:hidden name="openHourDeliv1list[%{#outerStat.index}].restaurantID"/>
									    <s:hidden name="openHourDeliv2list[%{#outerStat.index}].restaurantID"/>
										<s:hidden name="openHourDeliv1list[%{#outerStat.index}].weekDay"/>
									    <s:hidden name="openHourDeliv2list[%{#outerStat.index}].weekDay"/>
									    <s:hidden name="openHourDeliv1list[%{#outerStat.index}].seq"/>
									    <s:hidden name="openHourDeliv2list[%{#outerStat.index}].seq"/>
									</td>
									<td align="center"><s:textfield name="openHourDeliv1list[%{#outerStat.index}].startTime" id="openHourDeliv1list[%{#outerStat.index}].startTime" cssClass="timepicker" size="8" maxlength="8"/></td>
									<td align="center"><s:textfield name="openHourDeliv1list[%{#outerStat.index}].endTime" id="openHourDeliv1list[%{#outerStat.index}].endTime" cssClass="timepicker" size="8" maxlength="8"/></td>
									<td align="center"><s:textfield name="openHourDeliv2list[%{#outerStat.index}].startTime" id="openHourDeliv2list[%{#outerStat.index}].startTime" cssClass="timepicker" size="8" maxlength="8"/></td>
									<td align="center"><s:textfield name="openHourDeliv2list[%{#outerStat.index}].endTime" id="openHourDeliv2list[%{#outerStat.index}].endTime" cssClass="timepicker" size="8" maxlength="8"/></td>
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
										<input type="button" value="Save" onClick="javascript:save(this.form);" />
									</td>
								</tr>
							</table>

					    </div>
					    </s:form>
					    <h3>Delivery Area</h3>
					    <div>
					        <p>
					        Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
					        Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero
					        ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis
					        lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.
					        </p>
					        <ul>
					            <li>List item one</li>
					            <li>List item two</li>
					            <li>List item three</li>
					        </ul>
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
			</div>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>