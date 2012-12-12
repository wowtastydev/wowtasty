package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class OrderListConditionVO {
	
	/** order list page's condition items*/
	private String time = "";
	private String fromDate = "";
	private String toDate = "";
	private String orderMemberEmail = "";
	private String orderMemberTelephone = "";
	private String orderStatus = "";
	private String deliveryType = "";

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
	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType() {
		return deliveryType;
	}
	/**
	 * @param deliveryType the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
}