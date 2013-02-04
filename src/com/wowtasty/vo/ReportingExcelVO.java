package com.wowtasty.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class ReportingExcelVO {
	
	/** reporting excel columns*/
	private String billingID = "";
	private Integer seq = 1;
	private String orderID = "";
	private String restaurantID = "";
	private String restaurantName = "";
	private Timestamp orderTime = null;
	private Timestamp deliveryTime = null;
	private String deliveryDay = "";
	private Integer deliveryDate = 0;
	private Integer deliveryHour = 0;
	private String deliveryType = "";
	private String deliveryTypeName = "";
	private String deliveryCompanyID = "";
	private String deliveryCompanyName = "";
	private String deliveryCompanyType = "";
	private String deliveryCompanyTypeName = "";
	private String deliverymanID = "";
	private String deliverymanName = "";
	private String paymentType = "";
	private String paymentTypeName = "";
	private String orderMemberID = "";
	private String totalMenuName = "";
	private Double deliveryPrice  = 0.00;
	private Double deliveryTaxPrice  = 0.00;
	private Double deliveryPriceWithTax  = 0.00;
	private Double foodTotalPrice  = 0.00;
	private Double foodTaxPrice  = 0.00;
	private Double foodPriceWithTax  = 0.00;
	private Double totalPrice  = 0.00;
	private Double tipPrice  = 0.00;
	private Double totalPriceWithTax  = 0.00;
	private Double commissionPrice  = 0.00;
	private String orderStatus = "";
	private String orderStatusName = "";
	
	/**
	 * @return the billingID
	 */
	public String getBillingID() {
		return billingID;
	}
	/**
	 * @param billingID the billingID to set
	 */
	public void setBillingID(String billingID) {
		this.billingID = billingID;
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
	 * @return the orderTime
	 */
	public Timestamp getOrderTime() {
		return orderTime;
	}
	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
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
	 * @return the deliveryDay
	 */
	public String getDeliveryDay() {
		return deliveryDay;
	}
	/**
	 * @param deliveryDay the deliveryDay to set
	 */
	public void setDeliveryDay(String deliveryDay) {
		this.deliveryDay = deliveryDay;
	}
	/**
	 * @return the deliveryDate
	 */
	public Integer getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Integer deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the deliveryHour
	 */
	public Integer getDeliveryHour() {
		return deliveryHour;
	}
	/**
	 * @param deliveryHour the deliveryHour to set
	 */
	public void setDeliveryHour(Integer deliveryHour) {
		this.deliveryHour = deliveryHour;
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
	/**
	 * @return the deliveryTypeName
	 */
	public String getDeliveryTypeName() {
		return deliveryTypeName;
	}
	/**
	 * @param deliveryTypeName the deliveryTypeName to set
	 */
	public void setDeliveryTypeName(String deliveryTypeName) {
		this.deliveryTypeName = deliveryTypeName;
	}
	/**
	 * @return the deliveryCompanyID
	 */
	public String getDeliveryCompanyID() {
		return deliveryCompanyID;
	}
	/**
	 * @param deliveryCompanyID the deliveryCompanyID to set
	 */
	public void setDeliveryCompanyID(String deliveryCompanyID) {
		this.deliveryCompanyID = deliveryCompanyID;
	}
	/**
	 * @return the deliveryCompanyName
	 */
	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}
	/**
	 * @param deliveryCompanyName the deliveryCompanyName to set
	 */
	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}
	/**
	 * @return the deliveryCompanyType
	 */
	public String getDeliveryCompanyType() {
		return deliveryCompanyType;
	}
	/**
	 * @param deliveryCompanyType the deliveryCompanyType to set
	 */
	public void setDeliveryCompanyType(String deliveryCompanyType) {
		this.deliveryCompanyType = deliveryCompanyType;
	}
	/**
	 * @return the deliveryCompanyTypeName
	 */
	public String getDeliveryCompanyTypeName() {
		return deliveryCompanyTypeName;
	}
	/**
	 * @param deliveryCompanyTypeName the deliveryCompanyTypeName to set
	 */
	public void setDeliveryCompanyTypeName(String deliveryCompanyTypeName) {
		this.deliveryCompanyTypeName = deliveryCompanyTypeName;
	}
	/**
	 * @return the deliverymanID
	 */
	public String getDeliverymanID() {
		return deliverymanID;
	}
	/**
	 * @param deliverymanID the deliverymanID to set
	 */
	public void setDeliverymanID(String deliverymanID) {
		this.deliverymanID = deliverymanID;
	}
	/**
	 * @return the deliverymanName
	 */
	public String getDeliverymanName() {
		return deliverymanName;
	}
	/**
	 * @param deliverymanName the deliverymanName to set
	 */
	public void setDeliverymanName(String deliverymanName) {
		this.deliverymanName = deliverymanName;
	}
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return the paymentTypeName
	 */
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	/**
	 * @param paymentTypeName the paymentTypeName to set
	 */
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	/**
	 * @return the orderMemberID
	 */
	public String getOrderMemberID() {
		return orderMemberID;
	}
	/**
	 * @param orderMemberID the orderMemberID to set
	 */
	public void setOrderMemberID(String orderMemberID) {
		this.orderMemberID = orderMemberID;
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
	 * @return the deliveryPrice
	 */
	public Double getDeliveryPrice() {
		return deliveryPrice;
	}
	/**
	 * @param deliveryPrice the deliveryPrice to set
	 */
	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	/**
	 * @return the deliveryTaxPrice
	 */
	public Double getDeliveryTaxPrice() {
		return deliveryTaxPrice;
	}
	/**
	 * @param deliveryTaxPrice the deliveryTaxPrice to set
	 */
	public void setDeliveryTaxPrice(Double deliveryTaxPrice) {
		this.deliveryTaxPrice = deliveryTaxPrice;
	}
	/**
	 * @return the deliveryPriceWithTax
	 */
	public Double getDeliveryPriceWithTax() {
		return deliveryPriceWithTax;
	}
	/**
	 * @param deliveryPriceWithTax the deliveryPriceWithTax to set
	 */
	public void setDeliveryPriceWithTax(Double deliveryPriceWithTax) {
		this.deliveryPriceWithTax = deliveryPriceWithTax;
	}
	/**
	 * @return the foodTotalPrice
	 */
	public Double getFoodTotalPrice() {
		return foodTotalPrice;
	}
	/**
	 * @param foodTotalPrice the foodTotalPrice to set
	 */
	public void setFoodTotalPrice(Double foodTotalPrice) {
		this.foodTotalPrice = foodTotalPrice;
	}
	/**
	 * @return the foodTaxPrice
	 */
	public Double getFoodTaxPrice() {
		return foodTaxPrice;
	}
	/**
	 * @param foodTaxPrice the foodTaxPrice to set
	 */
	public void setFoodTaxPrice(Double foodTaxPrice) {
		this.foodTaxPrice = foodTaxPrice;
	}
	/**
	 * @return the foodPriceWithTax
	 */
	public Double getFoodPriceWithTax() {
		return foodPriceWithTax;
	}
	/**
	 * @param foodPriceWithTax the foodPriceWithTax to set
	 */
	public void setFoodPriceWithTax(Double foodPriceWithTax) {
		this.foodPriceWithTax = foodPriceWithTax;
	}
	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the tipPrice
	 */
	public Double getTipPrice() {
		return tipPrice;
	}
	/**
	 * @param tipPrice the tipPrice to set
	 */
	public void setTipPrice(Double tipPrice) {
		this.tipPrice = tipPrice;
	}
	/**
	 * @return the totalPriceWithTax
	 */
	public Double getTotalPriceWithTax() {
		return totalPriceWithTax;
	}
	/**
	 * @param totalPriceWithTax the totalPriceWithTax to set
	 */
	public void setTotalPriceWithTax(Double totalPriceWithTax) {
		this.totalPriceWithTax = totalPriceWithTax;
	}
	/**
	 * @return the commissionPrice
	 */
	public Double getCommissionPrice() {
		return commissionPrice;
	}
	/**
	 * @param commissionPrice the commissionPrice to set
	 */
	public void setCommissionPrice(Double commissionPrice) {
		this.commissionPrice = commissionPrice;
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
}