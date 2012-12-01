package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class OrderListConditionVO {
	
	/** order list page's condition items*/
	private String orderID = "";
	private String restaurantName = "";
	private String time = "";
	private String fromDate = "";
	private String toDate = "";
	private String orderMemberEmail = "";
	private String orderMemberTelephone = "";
	private String orderStatus = "";
	/**
	 * @return the orderID
	 */
	public String getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}
	/**
	 * @param restaurantName the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the orderMemberEmail
	 */
	public String getOrderMemberEmail() {
		return orderMemberEmail;
	}
	/**
	 * @param orderMemberEmail the orderMemberEmail to set
	 */
	public void setOrderMemberEmail(String orderMemberEmail) {
		this.orderMemberEmail = orderMemberEmail;
	}
	/**
	 * @return the orderMemberTelephone
	 */
	public String getOrderMemberTelephone() {
		return orderMemberTelephone;
	}
	/**
	 * @param orderMemberTelephone the orderMemberTelephone to set
	 */
	public void setOrderMemberTelephone(String orderMemberTelephone) {
		this.orderMemberTelephone = orderMemberTelephone;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}