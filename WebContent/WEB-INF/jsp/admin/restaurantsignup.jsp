<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">

<script>
<!--	
	//Change Restaurant Status
	function changeStatus(status){
		var msg = "";
		// Set message
		if (status == 3) {
			//　Approval
			msg="Do you want to approve the restaurants?";
		} else {
			//　Approval
			msg="Do you want to decline the restaurants?";
		}
		if(confirm(msg)) {
			document.getElementById("selectedStatus").value = status;
			document.getElementById("frm").action = "changeRestaurantStatus";
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}
	}
	
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
        // Set up button
        $("input[type=button]").button();
        
     	// Set up accordion
        $("#signupInfo").accordion({
             heightStyle: "content"
        });
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
		<h2>Sign up Detail</h2>
			
			<div id="detailarea">
				<div id="signupInfo">
					<h3>Restaurant Information</h3>
					<div>
						<s:form theme="simple" name="frm" id="frm" action="changeRestaurantStatus">
							<s:hidden name="selectedStatus" id="selectedStatus"/>
							
							<table width="100%">
								<tr>
									<td width="17%"></td>
									<td colspan=3 width="33%"></td>
									<td width="15%"></td>
									<td width="10%"></td>
									<td width="15%"></td>
									<td width="10%"></td>
								</tr>
								<tr>
									<td class="bgtd" height=30>Restaurant Name:</td>
									<td colspan=3>
										<s:property value="%{vo.name}" />
										<s:hidden name="vo.restaurantID" />
										<s:hidden name="vo.name" />
										<s:hidden name="vo.email1" />
									</td>
									<td class="bgtd">Cuisine Type:</td>
									<td colspan=3>
										<s:iterator value="cuisineTypeList" id="cuisineTypeList" status="outerStat">
											<s:if test='%{code.equals(vo.cuisineType)}'>
												<s:property value="%{name}"/>
											</s:if>
										</s:iterator>
									</td>
								</tr>
								<tr>
									<td class="bgtd" height=30>First Name:</td>
									<td colspan=3><s:property value="%{vo.firstName}"/></td>
									<td class="bgtd">Last Name:</td>
									<td colspan=3><s:property value="%{vo.lastName}"/></td>
								</tr>
								<tr>
									<td class="bgtd" height=30>Telephone:</td>
									<td colspan=3><s:property value="%{vo.telephone}"/></td>
									<td class="bgtd">FAX:</td>
									<td colspan=3><s:property value="%{vo.fax}"/></td>
								</tr>
								<tr>
									<td class="bgtd" height=30>Address:</td>
									<td colspan=3><s:property value="%{vo.address}"/></td>
									<td class="bgtd">Suite Number:</td>
									<td colspan=3><s:property value="%{vo.suite}"/></td>
								</tr>
								<tr>
									<td class="bgtd" height=30>City:</td>
									<td colspan=3>
										<s:iterator value="cityList" id="cityList" status="outerStat">
											<s:if test='%{code.equals(vo.city)}'>
												<s:property value="%{name}"/>
											</s:if>
										</s:iterator>
									</td>
									<td class="bgtd">Province:</td>
									<td>
										<s:iterator value="provinceList" id="provinceList" status="outerStat">
											<s:if test='%{code.equals(vo.province)}'>
												<s:property value="%{shortName}"/>
											</s:if>
										</s:iterator>
									</td>
									<td class="bgtd">Postal Code:</td>
									<td><s:property value="%{vo.postalCode}"/></td>
								</tr>
								<tr>
									<td class="bgtd" height=30>Web site:</td>
									<td colspan=3><s:property value="%{vo.website}"/></td>
									<td class="bgtd">Facebook:</td>
									<td colspan=3><s:property value="%{vo.facebook}"/></td>
								</tr>
								<tr>
									<td class="bgtd" height=30>E-mail1:</td>
									<td colspan=3><s:property value="%{vo.email1}"/></td>
									<td class="bgtd">E-mail2:</td>
									<td colspan=3><s:property value="%{vo.email2}"/></td>
								</tr>
								<tr>
									<td class="bgtd" height=30>Delivery/Take-out:</td>
									<td colspan=3>
										<s:iterator value="restaurantTypeList" id="restaurantTypeList" status="outerStat">
											<s:if test='%{code.equals(vo.restaurantType)}'>
												<s:property value="%{name}"/>
											</s:if>
										</s:iterator>
									</td>
									<td class="bgtd">Status:</td>
									<td colspan=3>
										<s:iterator value="restaurantStatusList" id="restaurantStatusList" status="outerStat">
											<s:if test='%{code.equals(vo.status)}'>
												<s:property value="%{name}"/>
											</s:if>
										</s:iterator>
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<td colspan=2><hr></td>
								</tr>
								<tr>
									<td>
										<input type="button" value="List" onClick="javascript:goPage('A201');" />
									</td>
									<td align="right">
										<s:if test='%{"5".equals(vo.status)}'>
											<input type="button" value="Approve" onClick="javascript:changeStatus(3);" />
											<input type="button" value="Decline" onClick="javascript:changeStatus(8);" />
										</s:if>
									</td>
								</tr>
							</table>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</t:putAttribute>
</t:insertDefinition>