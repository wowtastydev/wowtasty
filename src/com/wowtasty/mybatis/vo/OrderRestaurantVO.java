package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class OrderRestaurantVO {
	
	/** order_restaurant columns*/
	private String orderID = "";
	private String restaurantID = "";
	private String restaurantName = "";
	private String deliveryType = "";
	private float deliveryPrice = 0.00f;
	private float deliveryTaxPrice = 0.00f;
	private float foodTotalPrice = 0.00f;
	private float foodTaxPrice = 0.00f;
	private float totalPrice = 0.00f;
	private float tipPrice = 0.00f;
	private float totalPriceWithTax = 0.00f;
	private String orderStatus = "";
	private String declinedReason = "";
	private String deliveryCompanyID = "";
	private int deliverymanID = 0;
	private String deliverymanName = "";
	private String deliverymanTelephone = "";
	private String deliveryGoogleMapURL = "";
	private String deliveryFirstName = "";
	private String deliveryLastName = "";
	private String deliveryTelephone = "";
	private String deliveryAddress = "";
	private String deliverySuite = "";
	private String deliveryBuzzer = "";
	private String deliveryCity = "";
	private String deliveryProvince = "";
	private String deliveryPostalCode = "";
	private String deliveryInstruction = "";
	private int reviewRate = 0;
	private String updateID = "";
	private Timestamp updateTime = null;
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
	 * @return the deliveryPrice
	 */
	public float getDeliveryPrice() {
		return deliveryPrice;
	}
	/**
	 * @param deliveryPrice the deliveryPrice to set
	 */
	public void setDeliveryPrice(float deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	/**
	 * @return the deliveryTaxPrice
	 */
	public float getDeliveryTaxPrice() {
		return deliveryTaxPrice;
	}
	/**
	 * @param deliveryTaxPrice the deliveryTaxPrice to set
	 */
	public void setDeliveryTaxPrice(float deliveryTaxPrice) {
		this.deliveryTaxPrice = deliveryTaxPrice;
	}
	/**
	 * @return the foodTotalPrice
	 */
	public float getFoodTotalPrice() {
		return foodTotalPrice;
	}
	/**
	 * @param foodTotalPrice the foodTotalPrice to set
	 */
	public void setFoodTotalPrice(float foodTotalPrice) {
		this.foodTotalPrice = foodTotalPrice;
	}
	/**
	 * @return the foodTaxPrice
	 */
	public float getFoodTaxPrice() {
		return foodTaxPrice;
	}
	/**
	 * @param foodTaxPrice the foodTaxPrice to set
	 */
	public void setFoodTaxPrice(float foodTaxPrice) {
		this.foodTaxPrice = foodTaxPrice;
	}
	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the tipPrice
	 */
	public float getTipPrice() {
		return tipPrice;
	}
	/**
	 * @param tipPrice the tipPrice to set
	 */
	public void setTipPrice(float tipPrice) {
		this.tipPrice = tipPrice;
	}
	/**
	 * @return the totalPriceWithTax
	 */
	public float getTotalPriceWithTax() {
		return totalPriceWithTax;
	}
	/**
	 * @param totalPriceWithTax the totalPriceWithTax to set
	 */
	public void setTotalPriceWithTax(float totalPriceWithTax) {
		this.totalPriceWithTax = totalPriceWithTax;
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
	 * @return the declinedReason
	 */
	public String getDeclinedReason() {
		return declinedReason;
	}
	/**
	 * @param declinedReason the declinedReason to set
	 */
	public void setDeclinedReason(String declinedReason) {
		this.declinedReason = declinedReason;
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
	 * @return the deliverymanID
	 */
	public int getDeliverymanID() {
		return deliverymanID;
	}
	/**
	 * @param deliverymanID the deliverymanID to set
	 */
	public void setDeliverymanID(int deliverymanID) {
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
	 * @return the deliverymanTelephone
	 */
	public String getDeliverymanTelephone() {
		return deliverymanTelephone;
	}
	/**
	 * @param deliverymanTelephone the deliverymanTelephone to set
	 */
	public void setDeliverymanTelephone(String deliverymanTelephone) {
		this.deliverymanTelephone = deliverymanTelephone;
	}
	/**
	 * @return the deliveryGoogleMapURL
	 */
	public String getDeliveryGoogleMapURL() {
		return deliveryGoogleMapURL;
	}
	/**
	 * @param deliveryGoogleMapURL the deliveryGoogleMapURL to set
	 */
	public void setDeliveryGoogleMapURL(String deliveryGoogleMapURL) {
		this.deliveryGoogleMapURL = deliveryGoogleMapURL;
	}
	/**
	 * @return the deliveryFirstName
	 */
	public String getDeliveryFirstName() {
		return deliveryFirstName;
	}
	/**
	 * @param deliveryFirstName the deliveryFirstName to set
	 */
	public void setDeliveryFirstName(String deliveryFirstName) {
		this.deliveryFirstName = deliveryFirstName;
	}
	/**
	 * @return the deliveryLastName
	 */
	public String getDeliveryLastName() {
		return deliveryLastName;
	}
	/**
	 * @param deliveryLastName the deliveryLastName to set
	 */
	public void setDeliveryLastName(String deliveryLastName) {
		this.deliveryLastName = deliveryLastName;
	}
	/**
	 * @return the deliveryTelephone
	 */
	public String getDeliveryTelephone() {
		return deliveryTelephone;
	}
	/**
	 * @param deliveryTelephone the deliveryTelephone to set
	 */
	public void setDeliveryTelephone(String deliveryTelephone) {
		this.deliveryTelephone = deliveryTelephone;
	}
	/**
	 * @return the deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	/**
	 * @return the deliverySuite
	 */
	public String getDeliverySuite() {
		return deliverySuite;
	}
	/**
	 * @param deliverySuite the deliverySuite to set
	 */
	public void setDeliverySuite(String deliverySuite) {
		this.deliverySuite = deliverySuite;
	}
	/**
	 * @return the deliveryBuzzer
	 */
	public String getDeliveryBuzzer() {
		return deliveryBuzzer;
	}
	/**
	 * @param deliveryBuzzer the deliveryBuzzer to set
	 */
	public void setDeliveryBuzzer(String deliveryBuzzer) {
		this.deliveryBuzzer = deliveryBuzzer;
	}
	/**
	 * @return the deliveryCity
	 */
	public String getDeliveryCity() {
		return deliveryCity;
	}
	/**
	 * @param deliveryCity the deliveryCity to set
	 */
	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}
	/**
	 * @return the deliveryProvince
	 */
	public String getDeliveryProvince() {
		return deliveryProvince;
	}
	/**
	 * @param deliveryProvince the deliveryProvince to set
	 */
	public void setDeliveryProvince(String deliveryProvince) {
		this.deliveryProvince = deliveryProvince;
	}
	/**
	 * @return the deliveryPostalCode
	 */
	public String getDeliveryPostalCode() {
		return deliveryPostalCode;
	}
	/**
	 * @param deliveryPostalCode the deliveryPostalCode to set
	 */
	public void setDeliveryPostalCode(String deliveryPostalCode) {
		this.deliveryPostalCode = deliveryPostalCode;
	}
	/**
	 * @return the deliveryInstruction
	 */
	public String getDeliveryInstruction() {
		return deliveryInstruction;
	}
	/**
	 * @param deliveryInstruction the deliveryInstruction to set
	 */
	public void setDeliveryInstruction(String deliveryInstruction) {
		this.deliveryInstruction = deliveryInstruction;
	}
	/**
	 * @return the reviewRate
	 */
	public int getReviewRate() {
		return reviewRate;
	}
	/**
	 * @param reviewRate the reviewRate to set
	 */
	public void setReviewRate(int reviewRate) {
		this.reviewRate = reviewRate;
	}
	/**
	 * @return the updateID
	 */
	public String getUpdateID() {
		return updateID;
	}
	/**
	 * @param updateID the updateID to set
	 */
	public void setUpdateID(String updateID) {
		this.updateID = updateID;
	}
	/**
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}