<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/jquery.combobox.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../fancybox/source/jquery.fancybox.css?v=2.1.3" media="screen" />
<script src="../js/jquery.combobox.js"></script>
<script src="../fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>

<script>
<!--	
	//Save WowMaster
	function save(){
		if(confirm("Do you want to save it?")) {
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}
	}
	
	//Save TaxList
	function saveTaxList(){
		if(confirm("Do you want to save it?")) {
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").action = "saveTaxList";
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
        
		// Set up dropdown menu
        $("#city, #province").combobox();
        
     	// Set up accordion
        $("#wowmaster").accordion({
        	active: <s:property value="active"/>,
        	collapsible: true,
            heightStyle: "content"
        });
     	

        
     	// Set up spinner
        $( "#tax1, #tax2" ).spinner({
            min: 0.00,
            max: 99.99,
            step: 1,
            numberFormat: "n"
        });
        <s:iterator value="taxList" id="taxList" status="outerStat">
        $( "#foodtax<s:property value='%{#outerStat.index}'/>" ).spinner({
            min: 0.00,
            max: 99.99,
            step: 1,
            numberFormat: "n"
        });
        </s:iterator>
        
     	// Set up image
        $(".fancybox").fancybox();
	} );
//-->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<s:if test='%{uservo.auth < 15}'>
					<a href="#" class="active"><span>Admin Set-up</span></a>
					<a href="javascript:goPage('A312');"><span>Balance List</span></a>
			        <a href="javascript:goPage('A304');"><span>Billing List</span></a>
			        <a href="javascript:goPage('A307');"><span>Reporting</span></a>
		        </s:if>
		        <a href="javascript:goPage('A302');"><span>Delivery Company</span></a>
		        <a href="javascript:goPage('A303');"><span>Delivery Man</span></a>
			</div>
		</div>
	
		<div id="contentarea">
		<h2>Administration Setup</h2>
		<s:if test="hasFieldErrors()">
			<div class="error">
				<s:fielderror/>
			</div>
		</s:if>
		
			<div id="detailarea">
				<s:form theme="simple" name="frm" id="frm" action="saveWowMaster" method="POST" enctype="multipart/form-data" >
				<div id="wowmaster">
					<h3>Administration Information</h3>
					<div>
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
								<td height=30>Company Name<FONT color=red>*</FONT>:</td>
								<td colspan=7>
									<s:textfield name="vo.name" id="name" size="40" maxlength="50"/>
									<s:hidden name="vo.seq" />
								</td>
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
								<td>eCash bonus(Order):</td>
								<td colspan=3><s:textfield name="vo.ecashPerCent" id="ecashPerCent" size="2" maxlength="2"/></td>
								<td rowspan=5>Logo:</td>
								<td colspan=3 rowspan=5>
									<s:if test='%{!"".equals(vo.logoImagePath)}'>
										<a class="fancybox" href="../pictures/wowtasty/<s:property value='%{vo.logoImagePath}'/>"><img src="../pictures/wowtasty/thumb_<s:property value='%{vo.logoImagePath}'/>"></a>
										<s:hidden name="vo.logoImagePath" />
									</s:if>
									<s:else>
										<img src="../images/admin/noimage.jpg">
									</s:else>
									<br><s:file name="imgfile" id="imgfile" accept="image/*" />
								</td>
							</tr>
							<tr>
								<td>eCash deduction(Coupon):</td>
								<td colspan=3><s:textfield name="vo.ecashPerCoupon" id="ecashPerCoupon" size="2" maxlength="2"/></td>
							</tr>
							<tr>
								<td>eCash bonus(Topup):</td>
								<td colspan=3><s:textfield name="vo.ecashBonus" id="ecashBonus" size="2" maxlength="2"/></td>
							</tr>
							<tr>
								<td>Delivery holding:</td>
								<td colspan=3><s:select name="vo.holdingFlag" id="holdingFlag" list ="ynList" listKey="code" listValue="name" headerKey="" headerValue="" /></td>
							</tr>
							<tr>
								<td>TAX No<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.taxNO" id="taxNO" size="10" maxlength="9"/></td>
							</tr>
							<tr>
								<td>TAX1(%)<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.tax1" id="tax1" size="5" maxlength="5"/></td>
								<td>TAX2(%)<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:textfield name="vo.tax2" id="tax2" size="5" maxlength="5"/></td>
							</tr>
							<tr>
								<td>Payment Date1<FONT color=red>*</FONT>:</td>
								<td colspan=3><s:select name="vo.paymentDate1" id="paymentDate1" list ="dateMap" listKey="key" listValue="value" /></td>
								<td>Payment Date2:</td>
								<td colspan=3><s:select name="vo.paymentDate2" id="paymentDate2" list ="dateMap" listKey="key" listValue="value" /></td>
							</tr>
							<tr>
								<td>Balance Holding Period<FONT color=red>*</FONT>:</td>
								<td colspan=7><s:textfield name="vo.paymentValidPeriod" id="paymentValidPeriod" size="2" maxlength="2"/></td>
							</tr>
						</table>
						<table width="100%">
							<tr>
								<td colspan=2><hr></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="Save" onClick="javascript:save();" />
								</td>
							</tr>
						</table>
					</div>
					
					<h3>Food Tax List</h3>
					<div>
						<table class="tableborder">
							<thead>
							<tr>
								<th>Item Name</th>
								<th>Item Short Name</th>
								<th>Tax(%)</th>
							</tr>
							</thead>
							<tbody>
							<s:iterator value="taxList" id="taxList" status="outerStat">
							<tr>
								<td>
									<s:textfield name="taxList[%{#outerStat.index}].name" size="50" maxlength="50" />
									<s:hidden name="taxList[%{#outerStat.index}].groupCd"/>
									<s:hidden name="taxList[%{#outerStat.index}].code"/>
									<s:hidden name="taxList[%{#outerStat.index}].sort"/>
									<s:hidden name="taxList[%{#outerStat.index}].naFlag"/>
									<s:hidden name="taxList[%{#outerStat.index}].updateID"/>
									<s:hidden name="taxList[%{#outerStat.index}].updateTime"/>
								</td>
								<td>
									<s:textfield name="taxList[%{#outerStat.index}].shortName" size="20" maxlength="20" />
								</td>
								<td align="center">
									<s:textfield name="taxList[%{#outerStat.index}].value" id="foodtax%{#outerStat.index}" size="5" maxlength="5" />
								</td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
						<table width="100%">
							<tr>
								<td><hr></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="Save" onClick="javascript:saveTaxList();" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				</s:form>
			</div>
		</div>
	</div>
</div>

</t:putAttribute>
</t:insertDefinition>