<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="header">
	<table width="1100">
		<tr><td>
			<h1><a href="initCurrentOrderList">WOW TASTY ADMIN</a></h1>
			</td>
			<td align="right">
				Welcome <s:property value="%{uservo.firstName}"/><br>
				<a href="initPassword">Change Password</a> | <a href="initLogin">Log out</a>
			</td>
		</tr>
	</table>
	
</div>

<div id='cssmenu'>
	<ul>
	   <li class='has-sub '><a href='initCurrentOrderList'><span>&nbsp;&nbsp;&nbsp;&nbsp;Order&nbsp;&nbsp;&nbsp;&nbsp;</span></a>
	      <ul>
	         <li><a href='initCurrentOrderList'><span>Current Order</span></a></li>
	         <li><a href='initOrderList'><span>Order History</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href='RestaurantList.html'><span>Restaurant</span></a>
	      <ul>
	         <li><a href='RestaurantList.html'><span>Restaurant List</span></a></li>
	         <li><a href='AddRestaurant.html'><span>Add Restaurant</span></a></li>
	         <li><a href='SignupList.html'><span>Sign-up List</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href='UserAccountList.html'><span>User Account</span></a>
	      <ul>
	         <li><a href='UserAccountList.html'><span>User Account List</span></a></li>
	         <li><a href="initMember"><span>Add User Account</span></a></li>
	         <li><a href="initPassword"><span>Change Password</span></a></li>
	      </ul>
	   </li>
	   <li class='has-sub '><a href='AdminSetup.html'><span>Administration</span></a>
	      <ul>
	         <li><a href='AdminSetup.html'><span>Admin Set-up</span></a></li>
	         <li><a href='BillingList.html'><span>Billing List</span></a></li>
	         <li><a href='Reporting.html'><span>Reporting</span></a></li>
	         <li><a href='DeliveryCompany.html'><span>Delivery Company</span></a></li>
	         <li><a href='Deliveryman.html'><span>Delivery Man</span></a></li>
	      </ul>
	   </li>
	</ul>
</div>

		
