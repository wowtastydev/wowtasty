<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">

<script language="javascript">
<!--	
	function list(){
		document.getElementById("frm").action = "goList";
		document.getElementById("frm").submit();
	}
	
	function add(){
		var pwd = document.getElementById("memberPasswordStr").value;
		var confirm = document.getElementById('memberPasswordStr').value;

		// Check password
		if( pwd.length < 8){
			alert("Password must be at least 8 charaters");
			return;
		}
		
		if(!pwd.match(/[a-zA-Z]/) && !pwd.match(/[!,@,#,$,%,^,&,*,?,_,~]/)){
			alert("Password must include alphabets or symbols");
			return;
		}
		
		// Match password and confirm
		if( pwd != confirm){
			alert("Password and confirm must be matched");
			return;
		}
		
		// Check special Instruction's text length
		var taLength = document.getElementById("delivIntruction").value.length;
		var max =100;

		if (taLength > max) {
			alert("Special Instruction's text length is " + taLength + ". Maximun text is up to " + max);
			return;
		}
		document.getElementById("frm").submit();
	}
	
	// Password checker
	function check_password_safety(pwd) {

	var msg = "";
	var points = pwd.length;
	var password_info = document.getElementById('password_info');
	
	var has_letter		= new RegExp("[a-z]");
	var has_caps		= new RegExp("[A-Z]");
	var has_numbers		= new RegExp("[0-9]");
	var has_symbols		= new RegExp("\\W");
	
	if(has_letter.test(pwd)) 	{ points += 4; }
	if(has_caps.test(pwd)) 		{ points += 4; }
	if(has_numbers.test(pwd)) 	{ points += 4; }
	if(has_symbols.test(pwd)) 	{ points += 4; }
	
	if( points >= 24 ) {
		msg = '<span style="color: #0f0;">Strong!</span>';
	} else if( points >= 16 ) {
		msg = '<span style="color: #00f;">Medium!</span>';
	} else if( points >= 12 ) {
		msg = '<span style="color: #fa0;">Weak!</span>';
	} else {
		msg = '<span style="color: #f00;">Very weak!</span>';
	}

	password_info.innerHTML = msg ;
	}
	
	// Match password and confirm
	function match_passwords(confirm) {
		var confirm_info = document.getElementById('confirm_info');
		if (confirm == document.getElementById('memberPasswordStr').value) {
			confirm_info.innerHTML = '<span style="color: #0f0;">Matched</span>' ;
		} else {
			confirm_info.innerHTML = '<span style="color: #f00;">Not Matched</span>' ;
		}
	}
	
// -->
</script>

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="#" onClick="javascript:movePage(11);">Order</a>
					<div id="layer01" style="display:none;">
					</div>
				<a href="#" onClick="javascript:movePage(21);">Restaurant</a>
					<div id="layer02" style="display:none;">
					</div>
				<a href="#" class="active" onClick="javascript:movePage(31);">User Account</a>
					<div id="layer03" style="display:block;">
					</div>
				<a href="#" onClick="javascript:movePage(41);">Administration</a>
					<div id="layer04" style="display:none;">
					</div>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="css_xhtml" name="frm" id="frm" action="insertMember">
			<table border=0 cellpadding=1 cellspacing=2 width=800>
				<tr>
					<td align="left" colspan=8><h2>Add User Account</h2></td>
				</tr>
				<tr>
					<td align="left" colspan=8><hr></td>
				</tr>
				<tr>
					<th align="left" colspan=8>[General Information]</th>
				</tr>
				<tr>
					<td align="left">E-mail<FONT color=red>*</FONT>:</td>
					<td align="left" colspan=7><s:textfield name="mvo.email" id="email" value="%{mvo.email}" size="30" maxlength="256"/></td>
				</tr>
				<tr>
					<td align="left">Password<FONT color=red>*</FONT>:</td>
					<td align="left" colspan=3><s:password name="memberPasswordStr" id="memberPasswordStr" value="%{memberPasswordStr}" size="20" maxlength="20" onKeyUp="javascript:check_password_safety(this.value);"/><span id="password_info"><b>More than 8 digits with alphabets or symbols</b></span>
					</td>
					<td align="left">Confirm<FONT color=red>*</FONT>:</td>
					<td align="left" colspan=3><s:password name="confirmPasswordStr" id="confirmPasswordStr" value="%{confirmPasswordStr}" size="20" maxlength="20" onKeyUp="javascript:match_passwords(this.value);"/><span id="confirm_info"><b></b></span></td>
				</tr>
				<tr>
					<td align="left" colspan=8><hr></td>
				</tr>
				<tr>
					<th align="left" colspan=8>[Contact Address]</th>
				</tr>
				<tr>
					<td align="left">First Name:</td>
					<td align="left" colspan=3><s:textfield name="mvo.firstName" id="firstName" value="%{mvo.firstName}" size="20" maxlength="50"/></td>
					<td align="left">Last Name:</td>
					<td align="left" colspan=3><s:textfield name="mvo.lastName" id="lastName" value="%{mvo.lastName}" size="20" maxlength="50"/></td>
				</tr>
				<tr>
					<td align="left">Telephone:</td>
					<td align="left" colspan=7><s:textfield name="mvo.telephone" id="telephone" value="%{mvo.telephone}" size="20" maxlength="20"/></td>
				</tr>
				<tr>
					<td align="left">Address:</td>
					<td align="left" colspan=3><s:textfield name="mvo.address" id="address" value="%{mvo.address}" size="40" maxlength="100"/></td>
					<td align="left">Suite Number:</td>
					<td align="left" colspan=3><s:textfield name="mvo.suite" id="suite" value="%{mvo.suite}" size="5" maxlength="10"/></td>
				</tr>
				<tr>
					<td align="left">City:</td>
					<td align="left"><s:select name="mvo.city" id="city" list ="cityList" listKey="code" listValue="shortName" headerKey="" headerValue="" /></td>
					<td align="left">Province:</td>
					<td align="left"><s:select name="mvo.province" id="province" list ="provinceList" listKey="code" listValue="shortName" headerKey="" headerValue="" /></td>
					<td align="left">Postal Code:</td>
					<td align="left" colspan=3><s:textfield name="mvo.postalCode" id="postalCode" value="%{mvo.postalCode}" size="5" maxlength="7"/></td>
				</tr>
				<tr>
					<td align="left" colspan=8><hr></td>
				</tr>
				<tr>
					<th align="left" colspan=8>[Delivery Address]</th>
				</tr>
				<tr>
					<td align="left">First Name:</td>
					<td align="left" colspan=3><s:textfield name="mvo.delivFirstName" id="delivFirstName" value="%{mvo.delivFirstName}" size="20" maxlength="50"/></td>
					<td align="left">Last Name:</td>
					<td align="left" colspan=3><s:textfield name="mvo.delivLastName" id="delivLastName" value="%{mvo.delivLastName}" size="20" maxlength="50"/></td>
				</tr>
				<tr>
					<td align="left">Telephone:</td>
					<td align="left" colspan=7><s:textfield name="mvo.delivTelephone" id="delivTelephone" value="%{mvo.delivTelephone}" size="20" maxlength="20"/></td>
				</tr>
				<tr>
					<td align="left">Address:</td>
					<td align="left" colspan=3><s:textfield name="mvo.delivAddress" id="delivAddress" value="%{mvo.delivAddress}" size="40" maxlength="100"/></td>
					<td align="left">Suite Number:</td>
					<td align="left"><s:textfield name="mvo.delivSuite" id="delivSuite" value="%{mvo.delivSuite}" size="5" maxlength="10"/></td>
					<td align="left">Buzzer Number:</td>
					<td align="left"><s:textfield name="mvo.delivBuzzer" id="delivBuzzer" value="%{mvo.delivBuzzer}" size="5" maxlength="10"/></td>
				</tr>
				<tr>
					<td align="left">City:</td>
					<td align="left"><s:select name="mvo.delivCity" id="delivCity" list ="cityList" listKey="code" listValue="shortName" headerKey="" headerValue="" /></td>
					<td align="left">Province:</td>
					<td align="left"><s:select name="mvo.delivProvince" id="delivProvince" list ="provinceList" listKey="code" listValue="shortName" headerKey="" headerValue="" /></td>
					<td align="left">Postal Code:</td>
					<td align="left" colspan=3><s:textfield name="mvo.delivPostalCode" id="delivPostalCode" value="%{mvo.delivPostalCode}" size="5" maxlength="7"/></td>
				</tr>
				<tr>
					<td align="left">Special Instruction:</td>
					<td align="left" colspan=7><s:textarea rows="3" cols="75" name="mvo.delivIntruction" id="delivIntruction"></s:textarea></td>
				</tr>
				<tr>
					<td align="left" colspan=8><hr></td>
				</tr>
				<tr>
					<td align="right" colspan=8>
					<input type="button" value="List" onClick="javascript:list();" />
					<input type="button" value="Add" onClick="javascript:add();" />
					</td>
				</tr>
				
			</table>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>