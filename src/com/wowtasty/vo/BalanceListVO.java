package com.wowtasty.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class BalanceListVO {
	
	/** order list items on the billing page */
	private String orderID = "";
	private Timestamp deliveryTime = null;
	private String restaurantID = "";
	private String restaurantName = "";
	private String orderStatus = "";
	private String orderStatusName = "";
	private String totalMenuName = "";
	private Double salesAmount = 0.0;
	private Double foodAmount = 0.0;
	private Double commissionAmount = 0.0;
	private Double deliveryAmount = 0.0;
	private Double tipAmount = 0.0;
	private Double totalAmount = 0.0;
	private Double balance = 0.0;
	private String note = "";
	// A:Available(Available and Balance), B:Balance only, N:N/A and Not Balance
	private String availableFlag = "";
	private String adjustmentFlag = "";
	// adjustment seq
	private Integer seq = 0;
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
	 * @return the deliveryTime
	 */
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * @param deliveryTime the deliveryTime to set
	 */
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * @return the restaurantID
	 */
	public String getRestaurantID() {
		return restaurantID;
	}
	/**
	 * @param restaurantID the restaurantID to set
	 */
	public void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
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
	 * @return the orderStatusName
	 */
	public String getOrderStatusName() {
		return orderStatusName;
	}
	/**
	 * @param orderStatusName the orderStatusName to set
	 */
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	/**
	 * @return the totalMenuName
	 */
	public String getTotalMenuName() {
		return totalMenuName;
	}
	/**
	 * @param totalMenuName the totalMenuName to set
	 */
	public void setTotalMenuName(String totalMenuName) {
		this.totalMenuName = totalMenuName;
	}
	/**
	 * @return the salesAmount
	 */
	public Double getSalesAmount() {
		return salesAmount;
	}
	/**
	 * @param salesAmount the salesAmount to set
	 */
	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}
	/**
	 * @return the foodAmount
	 */
	public Double getFoodAmount() {
		return foodAmount;
	}
	/**
	 * @param foodAmount the foodAmount to set
	 */
	public void setFoodAmount(Double foodAmount) {
		this.foodAmount = foodAmount;
	}
	/**
	 * @return the commissionAmount
	 */
	public Double getCommissionAmount() {
		return commissionAmount;
	}
	/**
	 * @param commissionAmount the commissionAmount to set
	 */
	public void setCommissionAmount(Double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	/**
	 * @return the deliveryAmount
	 */
	public Double getDeliveryAmount() {
		return deliveryAmount;
	}
	/**
	 * @param deliveryAmount the deliveryAmount to set
	 */
	public void setDeliveryAmount(Double deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}
	/**
	 * @return the tipAmount
	 */
	public Double getTipAmount() {
		return tipAmount;
	}
	/**
	 * @param tipAmount the tipAmount to set
	 */
	public void setTipAmount(Double tipAmount) {
		this.tipAmount = tipAmount;
	}
	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the availableFlag
	 */
	public String getAvailableFlag() {
		return availableFlag;
	}
	/**
	 * @param availableFlag the availableFlag to set
	 */
	public void setAvailableFlag(String availableFlag) {
		this.availableFlag = availableFlag;
	}
	/**
	 * @return the adjustmentFlag
	 */
	public String getAdjustmentFlag() {
		return adjustmentFlag;
	}
	/**
	 * @param adjustmentFlag the adjustmentFlag to set
	 */
	public void setAdjustmentFlag(String adjustmentFlag) {
		this.adjustmentFlag = adjustmentFlag;
	}
	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}