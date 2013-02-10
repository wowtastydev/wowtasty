<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script>
<!--	
	function goPage(pageID){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		// Order Menus
		if (pageID =="R001") { 
			document.location = "initLogin";
		}
		if (pageID =="R002") { 
			document.location = "initCurrentOrderList";
		}
		if (pageID =="R003") { 
			document.location = "initOrderList";
		}
		// Account Menus
		if (pageID =="R101") { 
			document.location = "initMemberEdit";
		}
		// Restaurant Menus
		if (pageID =="R201") { 
			document.location = "initRestaurantList";
		}

		// Billing Menus
		if (pageID =="R301") { 
			document.location = "initBillingList";
		}
		if (pageID =="R303") { 
			document.location = "initBalanceList";
		}
		
		// Logout
		if (pageID =="R999") { 
			document.location = "logout";
		}
	}
	
	function goPageParam(pageID, param){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		
		if (pageID =="R202") { 
			document.location = "initRestaurant?selectedID=" + param;
		}
		if (pageID =="R203") { 
			document.location = "initRestaurantPhoto?selectedID=" + param;
		}
		if (pageID =="R204") { 
			document.location = "initRestaurantMenu?selectedID=" + param;
		}
	}

    // JQuery
	$(document).ready( function() {
		// Error Message Panel
        $( "#errorMessagePanel" ).dialog({
            autoOpen: false,
            width: 500
        });
		
	} );

//-->
</script>

<!-- Error Message Panel -->
<div id="errorMessagePanel" title="Error Message" style="display:table-cell; vertical-align:middle">
	<span id="errorMessage"></span>
</div>

<div id="header">
	<table width="95%">
		<tr><td>
			<h1><a href="javascript:goPage('R002');">WOW TASTY RESTAURANT</a></h1>
			</td>
			<td align="right">
				Welcome <s:property value="%{uservo.firstName}"/><br>
				<a href="javascript:goPage('R101');">Change Account</a> | <a href="javascript:goPage('R999');">Log out</a>
			</td>
		</tr>
	</table>
	
</div>

<div id='cssmenu'>
	<ul>
	   <li class='has-sub '><a href="javascript:goPage('R002');"><span>&nbsp;&nbsp;&nbsp;&nbsp;Order&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
	      <ul>
	         <li><a href="javascript:goPage('R002');"><span>Current Order</span></a></li>
	         <li><a href="javascript:goPage('R003');"><span>Order History</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href="javascript:goPage('R201');"><span>Restaurant</span></a>
	      <ul>
	      	<li><a href="javascript:goPage('R201');"><span>Restaurant List</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href="javascript:goPage('R101');"><span>User Account</span></a>
	      <ul>
	         <li><a href="javascript:goPage('R101');"><span>User Account</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href="javascript:goPage('R303');"><span>Billing</span></a>
	      <ul>
	         <li><a href="javascript:goPage('R303');"><span>Balance List</span></a></li>
	         <li><a href="javascript:goPage('R301');"><span>Billing List</span></a></li>
	      </ul>
	   </li>
	</ul>
</div>

		
