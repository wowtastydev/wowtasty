<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/jquery.multiselect.css" type="text/css" media="screen" />
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/jquery.multiselect.js"></script>

<script>
<!--
	// Search
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Go Edit Page
	function goEdit(index){
		document.getElementById("selectedMemberID").value = document.getElementById("list[" + index + "].memberID").value;
		document.getElementById("frm").action = "initMemberEdit";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
// -->
</script>

<script>
<!--
	// Set up List table
	$(document).ready( function() {
		$('#listTable').dataTable( {
			"bJQueryUI": true,
			"sPaginationType": "full_numbers"
		} );
		
		// Set up multiselect
		$("#conditionRoles").multiselect({ 
			selectedList:3,
			minWidth:400,
			noneSelectedText: "Select Memeber's Types"
		});
		
		$( "input[type=button]" ).button();
	} );
	
//-->
</script>
<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="#" class="active">User Account List</a>
				<a href="javascript:goPage('A102');">Add User Account</a>
				<a href="javascript:goPage('A104');">Change Password</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="css_xhtml" name="frm" id="frm" action="searchMember">
			<s:hidden name="selectedMemberID" id="selectedMemberID"/>
			
			<h2>User Account List</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea">
				<table>
					<tr>
						<th>Member Type : </th>
						<td>
							<s:select name="conditionRoles" id="conditionRoles" list="roleList" listKey="code" listValue="name" multiple="true"/>
						</td>
						<td>
							<input type="button" value="Search" onClick="javascript:search();" />
							<input type="button" value="Add" onClick="javascript:goPage('A102');" />
						</td>
					</tr>
				</table>
			</div>
			
			<div id="searchlistarea">
				<table id="listTable" class="display"> 
					<thead>
					<tr align="center">
						<th>Type</th>
						<th>Email</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Telephone</th>
						<th>Total Order</th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');">
							<s:iterator value="roleList" id="roleList" status="outerStat">
								<s:if test='%{code.equals(auth)}'>
									<s:property value="%{shortName}"/>
								</s:if>
							</s:iterator>
							</s:a>
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{email}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].memberID" id="list[%{#outerStat.index}].memberID"/>
						</td>
						<td>	
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{firstName}"/></s:a>
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{lastName}"/></s:a>
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{telephone}"/></s:a>
						</td>
						<td align="right">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="getText('{0,number,#,###,###}',{totalOrderCnt})"/></s:a>
						</td>
					</tr>
					</s:iterator>
					</tbody>
				</table>
			</div>
		</s:form>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>