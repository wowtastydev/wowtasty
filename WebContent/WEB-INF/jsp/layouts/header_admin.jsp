<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script>
<!--	
	function goPage(pageID){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		// Order Menus
		if (pageID =="A001") { 
			document.location = "initLogin";
		}
		if (pageID =="A002") { 
			document.location = "initCurrentOrderList";
		}
		if (pageID =="A003") { 
			document.location = "initOrderList";
		}
		// Account Menus
		if (pageID =="A101") { 
			document.location = "initMemberList";
		}
		if (pageID =="A102") { 
			document.location = "initMember";
		}
		if (pageID =="A104") { 
			document.location = "initPassword";
		}
		// Restaurant Menus
		if (pageID =="A201") { 
			document.location = "initRestaurantSignupList";
		}
		if (pageID =="A203") { 
			document.location = "initRestaurantList";
		}
		if (pageID =="A204") { 
			document.location = "initRestaurant";
		}
		if (pageID =="A206") { 
			document.location = "initRestaurantPhoto";
		}
		if (pageID =="A208") { 
			document.location = "initRestaurantMenuList";
		}

		// Administration Setup Menus
		if (pageID =="A301") { 
			document.location = "initAdmin";
		}
		if (pageID =="A304") { 
			document.location = "initBillList";
		}
		if (pageID =="A307") { 
			document.location = "initReport";
		}
		if (pageID =="A302") { 
			document.location = "initDeliveryCompany";
		}
		if (pageID =="A303") { 
			document.location = "initDeliveryMan";
		}
		
	}

//-->
</script>

<div id="header">
	<table width="95%">
		<tr><td>
			<h1><a href="javascript:goPage('A002');">WOW TASTY ADMIN</a></h1>
			</td>
			<td align="right">
				Welcome <s:property value="%{uservo.firstName}"/><br>
				<a href="javascript:goPage('A104');">Change Password</a> | <a href="javascript:goPage('A001');">Log out</a>
			</td>
		</tr>
	</table>
	
</div>

<div id='cssmenu'>
	<ul>
	   <li class='has-sub '><a href="javascript:goPage('A002');"><span>&nbsp;&nbsp;&nbsp;&nbsp;Order&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
	      <ul>
	         <li><a href="javascript:goPage('A002');"><span>Current Order</span></a></li>
	         <li><a href="javascript:goPage('A003');"><span>Order History</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href="javascript:goPage('A203');"><span>Restaurant</span></a>
	      <ul>
	         <li><a href="javascript:goPage('A203');"><span>Restaurant List</span></a></li>
	         <li><a href="javascript:goPage('A204');"><span>Add Restaurant</span></a></li>
	         <li><a href="javascript:goPage('A201');"><span>Sign-up List</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href="javascript:goPage('A101');"><span>User Account</span></a>
	      <ul>
	         <li><a href="javascript:goPage('A101');"><span>User Account List</span></a></li>
	         <li><a href="javascript:goPage('A102');"><span>Add User Account</span></a></li>
	         <li><a href="javascript:goPage('A104');"><span>Change Password</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href="javascript:goPage('A301');"><span>Administration</span></a>
	      <ul>
	         <li><a href="javascript:goPage('A301');"><span>Admin Set-up</span></a></li>
	         <li><a href="javascript:goPage('A304');"><span>Billing List</span></a></li>
	         <li><a href="javascript:goPage('A307');"><span>Reporting</span></a></li>
	         <li><a href="javascript:goPage('A302');"><span>Delivery Company</span></a></li>
	         <li><a href="javascript:goPage('A303');"><span>Delivery Man</span></a></li>
	      </ul>
	   </li>
	</ul>
</div>

		
