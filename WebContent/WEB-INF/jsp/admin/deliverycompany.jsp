<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/jquery.combobox.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../fancybox/source/jquery.fancybox.css?v=2.1.3" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/timepicker.css" media="screen"  />
<script src="../js/jquery.combobox.js"></script>
<script src="../fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>
<script src="../js/bootstrap-timepicker.js"></script>

<script>
<!--
    // Delivery company area max index
	var maxAreaIndex = <s:property value="delivArealist.size"/>;
		
	// Save Delivery company
	function save(form){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		form.submit();
	}
	
	//Close business hour
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
	
	
	// Area add row
	function addRowBlank() {
		var id = document.getElementById("deliveryCompanyID").value;
		if (id == "") {
			alert("Please save Delivery Company General Information first.");
			return;
		}
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
		input_prefix.setAttribute("name", "delivArealist[" + maxAreaIndex + "].postalPrefix");
		input_prefix.setAttribute("value", "");
		input_prefix.setAttribute("size", "3");
		input_prefix.setAttribute("maxlength", "3");
		td_prefix.appendChild(input_prefix);
		
		var input_id = document.createElement("input"); 
		input_id.setAttribute("type", "hidden");
		input_id.setAttribute("name", "delivArealist[" + maxAreaIndex + "].deliveryCompanyID");
		input_id.setAttribute("value", id);
		td_prefix.appendChild(input_id);
		
		var td_min = document.createElement("TD");
		td_min.setAttribute("align", "center");
		obj_row.appendChild(td_min);
		
		var input_min = document.createElement("input"); 
		input_min.setAttribute("type", "text");
		input_min.setAttribute("name", "delivArealist[" + maxAreaIndex + "].minPrice");
		input_min.setAttribute("value", "0.00");
		input_min.setAttribute("size", "6");
		input_min.setAttribute("maxlength", "6");
		td_min.appendChild(input_min);
		
		var td_fee = document.createElement("TD");
		td_fee.setAttribute("align", "center");
		obj_row.appendChild(td_fee);
		
		var input_fee = document.createElement("input"); 
		input_fee.setAttribute("type", "text");
		input_fee.setAttribute("name", "delivArealist[" + maxAreaIndex + "].deliveryFee");
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
                // In case delivArealist, checkbox is node[1]
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
	
	// Change Postal code map
	function changeMap(obj) {
		document.getElementById("map_info").innerHTML = "<img src='../images/map/" + obj.value + ".gif' >" ;
	}
	
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
        
		// Set up dropdown menu
        $("#city").combobox();
        $("#province").combobox();
        
        // Set up accordion
        $( "#deliveryInfo" ).accordion({
        	active: <s:property value="active"/>,
        	collapsible: true,
        	alwaysOpen: false,
            heightStyle: "content"
        });
        
        // Set up button
        $("input[type=button]").button();
        
        // Set up image
        $(".fancybox").fancybox();
        
        // Set up spinner
        $('.timepicker').timepicker({
            minuteStep: 15,
            showInputs: false,
            disableFocus: false,
            defaultTime: false,
            showMeridian: false,
            showSeconds: false
        });
        
        // Set up spinner
        $( "#commission" ).spinner({
            min: 1,
            max: 100,
            step: 1,
            numberFormat: "n"
        });
	} );
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<s:if test='%{uservo.auth < 15}'>
					<a href="javascript:goPage('A301');"><span>Admin Set-up</span></a>
					<a href="javascript:goPage('A312');"><span>Balance List</span></a>
			        <a href="javascript:goPage('A304');"><span>Billing List</span></a>
			        <a href="javascript:goPage('A307');"><span>Reporting</span></a>
		        </s:if>
		        <a href="#" class="active"><span>Delivery Company</span></a>
		        <a href="javascript:goPage('A303');"><span>Delivery Man</span></a>
			</div>
		</div>
	
		<div id="contentarea">
		<h2>Delivery Company</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="detailarea">
				<div id="deliveryInfo">
					<h3>General Information</h3>
					<div>
					<s:form theme="simple" name="frmInfo" id="frmInfo" action="saveDeliveryCompany" method="POST" enctype="multipart/form-data">
						<s:hidden name="vo.deliveryCompanyID" id="deliveryCompanyID" />
						<s:hidden name="vo.deliveryCompanyType" value="2"/>
						<s:hidden name="vo.memberID" />
						<s:hidden name="vo.naFlag" />
						<s:hidden name="vo.registerDate" />
						<table width="100%">
							<tr>
								<td colspan=4 width="50%"></td>
								<td colspan=4 width="50%"></td>
							</tr>
							<tr>
								<td>Compnay Name<FONT color=red>*</FONT>:</td>
								<td colspan=7><s:textfield name="vo.name" id="name" size="40" maxlength="50"  onblur="javascript:copy2Keywords(this.value);" /></td>
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
								<td><s:select name="vo.province" id="province" list ="provinceList" listKey="code" listValue="shortName" /></td>
								<td>Postal Code<FONT color=red>*</FONT>:</td>
								<td><s:textfield name="vo.postalCode" id="postalCode" size="8" maxlength="7"/></td>
							</tr>
							<tr>
								<td>Web site:</td>
								<td colspan=3><s:textfield name="vo.website" id="website" size="30" maxlength="100"/></td>
								<td rowspan=5>Logo:</td>
								<td colspan=3 rowspan=5>
									<s:if test='%{!"".equals(vo.logoImagePath)}'>
										<a class="fancybox" href="../pictures/delivery/<s:property value='%{vo.logoImagePath}'/>">
										<img src="../pictures/delivery/thumb_<s:property value='%{vo.logoImagePath}'/>">
										</a>
										<s:hidden name="vo.logoImagePath" />
									</s:if>
									<s:else>
										<img src="../images/admin/noimage.jpg">
									</s:else>
									<br><s:file name="imgfile" id="imgfile" accept="image/*" />
								</td>
							</tr>
							<tr>
								<td>Facebook:</td>
								<td colspan=3><s:textfield name="vo.facebook" id="facebook" size="30" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<td>E-mail1<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.email1" id="email1" size="30" maxlength="256"/></td>
							</tr>
							<tr>
								<td>E-mail2:</td>
								<td colspan=3><s:textfield name="vo.email2" id="email2" size="30" maxlength="256"/></td>
							</tr>
							<tr>
								<td>Commission(%)<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.commission" id="commission" size="2" maxlength="2"/></td>
							</tr>
						</table>
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
					</s:form>
					</div>
					<h3>Business Hour</h3>
					<s:form theme="css_xhtml" name="frmBizHour" id="frmBizHour" action="saveDeliveryCompanyBizHour">
				    <s:hidden name="vo.deliveryCompanyID" />
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
									<input type="button" value="Save" onClick="javascript:save(this.form);" />
								</td>
							</tr>
						</table>

				    </div>
				    </s:form>
				    <h3>Delivery Area</h3>
				    <s:form theme="simple" name="frmArea" id="frmArea" action="saveDeliveryCompanyDeliveryArea">
				    <s:hidden name="vo.restaurantID" />
				    <s:hidden name="vo.deliveryCompanyID" />
				    <div>
						<div>
							<table width="auto">
								<tr>
									<td>
										<input type="button" value="Add Row" onClick ="javascript:addRowBlank();"/>&nbsp;
										<input type="button" value="Del Row" onClick ="javascript:delRow();"/>
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
											<th>Min. Order </th>
											<th>Delivery Fee</th>
										</tr>
										</thead>
										<tbody>
										<s:iterator value="delivArealist" id="delivArealist" status="outerStat">
										<tr>
											<td align="center">
												<s:checkbox name="chk" id="delivArealist[%{#outerStat.index}].chk"/>
											</td>
											<td align="center">
												<s:property value="postalPrefix"/>
												<s:hidden name="delivArealist[%{#outerStat.index}].postalPrefix" id="delivArealist[%{#outerStat.index}].postalPrefix"/>
												<s:hidden name="delivArealist[%{#outerStat.index}].deliveryCompanyID"/>
												<s:hidden name="delivArealist[%{#outerStat.index}].seq"/>
											</td>
											<td align="center">
												<s:textfield name="delivArealist[%{#outerStat.index}].minPrice" id="delivArealist[%{#outerStat.index}].minPrice" size="6" maxlength="6" />
											</td>
											<td align="center">
												<s:textfield name="delivArealist[%{#outerStat.index}].deliveryFee" id="delivArealist[%{#outerStat.index}].deliveryFee" size="6" maxlength="6" />
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
									</td></tr>
								</table>
							</div>
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

</t:putAttribute>
</t:insertDefinition>