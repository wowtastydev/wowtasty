<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/table_jui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/jquery.combobox.css" type="text/css" media="screen" />
<script src="../js/jquery.dataTables.min.js"></script>
<script src="../js/jquery.combobox.js"></script>

<script>
<!--
   // Search
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Go Edit Page
	function goEdit(index){
		document.getElementById("selectedID").value = document.getElementById("list[" + index + "].restaurantID").value;
		document.getElementById("frm").action = "initRestaurant";
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
			"bStateSave": true,
			"bAutoWidth": false,
			"sPaginationType": "full_numbers"
		} );
		
		// Set up Combobox
		$("#cuisineType").combobox();
		$("#city").combobox();
		$("#restaurantType").combobox();
		
		$( "input[type=button]" ).button();
	} );
	
//-->
</script>
<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="#" class="active">Restaurant List</a>
				<a href="javascript:goPage('A204');">Add Restaurant</a>
				<a href="javascript:goPage('A201');">Sign-up List</a>
			</div>
		</div>
	
		<div id="contentarea">
		<s:form theme="simple" name="frm" id="frm" action="searchRestaurant">
			<s:hidden name="selectedID" id="selectedID"/>
			
			<h2>Restaurant List</h2>
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="searchconditionarea">
				<table width="100%">
					<tr>
						<th>Cuisine Type : </th>
						<td width="190">
							<s:select name="condition.cuisineType" id="cuisineType" list="cuisineTypeList" listKey="code" listValue="shortName"  headerKey="" headerValue="All"/>
						</td>
						<th>City : </th>
						<td>
							<s:select name="condition.city" id="city" list="cityList" listKey="code" listValue="shortName" headerKey="" headerValue="All" />
						</td>
						<th>Restaurant Type : </th>
						<td>
							<s:select name="condition.restaurantType" id="restaurantType" list="restaurantTypeList" listKey="code" listValue="name" headerKey="" headerValue="All" />
						</td>
						<td align="right">
							<input type="button" value="Search" onClick="javascript:search();" />
							<input type="button" value="Add" onClick="javascript:goPage('A204');" />
						</td>
					</tr>
				</table>
			</div>
			
			<div id="searchlistarea">
				<table id="listTable" class="display"> 
					<thead>
					<tr align="center">
						<th>ID</th>
						<th width="200">Name</th>
						<th>Cuisine</th>
						<th>City</th>
						<th>Address</th>
						<th>Del/Take</th>
						<th>Tel</th>
					</tr>
					</thead>		
					<tbody>
					<s:iterator value="list" id="list" status="outerStat">
					<tr>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{restaurantID}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].restaurantID" id="list[%{#outerStat.index}].restaurantID"/>
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{name}"/></s:a>
							<s:hidden name="list[%{#outerStat.index}].name" id="list[%{#outerStat.index}].name"/>
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');">
							<s:iterator value="cuisineTypeList" id="cuisineTypeList" status="outerStat">
								<s:if test='%{code.equals(cuisineType)}'>
									<s:property value="%{shortName}"/>
								</s:if>
							</s:iterator>
							</s:a>
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');">
							<s:iterator value="cityList" id="cityList" status="outerStat">
								<s:if test='%{code.equals(city)}'>
									<s:property value="%{shortName}"/>
								</s:if>
							</s:iterator>
							</s:a>
						</td>
						<td>	
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{address}"/></s:a>
						</td>
						<td>
							<s:a href="javascript:goEdit('%{#outerStat.index}');">
							<s:iterator value="restaurantTypeList" id="restaurantTypeList" status="outerStat">
								<s:if test='%{code.equals(restaurantType)}'>
									<s:property value="%{shortName}"/>
								</s:if>
							</s:iterator>
							</s:a>
						</td>
						<td align="center">
							<s:a href="javascript:goEdit('%{#outerStat.index}');"><s:property value="%{telephone}"/></s:a>
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