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
	function save(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frmPhoto").submit();
	}
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
        // Set up tab
        $( "#tabs" ).tabs({
        	active: -1
        });
		
        // Set up button
        $("input[type=button]").button();
        
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
					<s:form theme="css_xhtml" name="frmInfo" id="frmInfo" action="saveRestaurant" method="POST" enctype="multipart/form-data">
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
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>