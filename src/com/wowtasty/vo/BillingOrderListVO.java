package com.wowtasty.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class BillingOrderListVO {
	
	/** order list items on the billing page */
	private String orderID = "";
	private String invoiceNO = "";
	private Timestamp orderTime = null;
	private Timestamp deliveryTime = null;
	private String restaurantID = "";
	private String restaurantName = "";
	private String deliveryType = "";
	private String deliveryTypeName = "";
	private String orderMemberID = "";
	private String orderMemberEmail = "";
	private String orderStatus = "";
	private String orderStatusName = "";
	private String totalMenuName = "";
	private Integer totalUnit = 0;
	private Integer menuCnt = 0;
	private String paymentType = "";
	private String paymentTypeName = "";
	private Float deliveryPrice = 0.00f;
	private Float deliveryTaxPrice = 0.00f;
	private Float foodTotalPrice = 0.00f;
	private Float foodTaxPrice = 0.00f;
	private Float totalPrice = 0.00f;
	private Float tipPrice = 0.00f;
	private Float totalPriceWithTax = 0.00f;
	private Float adjustPrice = 0.00f;
	private Float billPrice = 0.00f;
	private String remark = "";
	private String delFlag = "";
	
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
	 * @return the invoiceNO
	 */
	public String getInvoiceNO() {
		return invoiceNO;
	}
	/**
	 * @param invoiceNO the invoiceNO to set
	 */
	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
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
	public String getOrderMemberEmail() {
		return orderMemberEmail;
	}
	public void setOrderMemberEmail(String orderMemberEmail) {
		this.orderMemberEmail = orderMemberEmail;
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
	 * @return the deliveryPrice
	 */
	public Float getDeliveryPrice() {
		return deliveryPrice;
	}
	/**
	 * @param deliveryPrice the deliveryPrice to set
	 */
	public void setDeliveryPrice(Float deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	/**
	 * @return the deliveryTaxPrice
	 */
	public Float getDeliveryTaxPrice() {
		return deliveryTaxPrice;
	}
	/**
	 * @param deliveryTaxPrice the deliveryTaxPrice to set
	 */
	public void setDeliveryTaxPrice(Float deliveryTaxPrice) {
		this.deliveryTaxPrice = deliveryTaxPrice;
	}
	/**
	 * @return the foodTotalPrice
	 */
	public Float getFoodTotalPrice() {
		return foodTotalPrice;
	}
	/**
	 * @param foodTotalPrice the foodTotalPrice to set
	 */
	public void setFoodTotalPrice(Float foodTotalPrice) {
		this.foodTotalPrice = foodTotalPrice;
	}
	/**
	 * @return the foodTaxPrice
	 */
	public Float getFoodTaxPrice() {
		return foodTaxPrice;
	}
	/**
	 * @param foodTaxPrice the foodTaxPrice to set
	 */
	public void setFoodTaxPrice(Float foodTaxPrice) {
		this.foodTaxPrice = foodTaxPrice;
	}
	/**
	 * @return the totalPrice
	 */
	public Float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the tipPrice
	 */
	public Float getTipPrice() {
		return tipPrice;
	}
	/**
	 * @param tipPrice the tipPrice to set
	 */
	public void setTipPrice(Float tipPrice) {
		this.tipPrice = tipPrice;
	}
	/**
	 * @return the totalPriceWithTax
	 */
	public Float getTotalPriceWithTax() {
		return totalPriceWithTax;
	}
	/**
	 * @param totalPriceWithTax the totalPriceWithTax to set
	 */
	public void setTotalPriceWithTax(Float totalPriceWithTax) {
		this.totalPriceWithTax = totalPriceWithTax;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @return the totalUnit
	 */
	public Integer getTotalUnit() {
		return totalUnit;
	}
	/**
	 * @param totalUnit the totalUnit to set
	 */
	public void setTotalUnit(Integer totalUnit) {
		this.totalUnit = totalUnit;
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
	 * @return the adjustPrice
	 */
	public Float getAdjustPrice() {
		return adjustPrice;
	}
	/**
	 * @param adjustPrice the adjustPrice to set
	 */
	public void setAdjustPrice(Float adjustPrice) {
		this.adjustPrice = adjustPrice;
	}
	/**
	 * @return the menuCnt
	 */
	public Integer getMenuCnt() {
		return menuCnt;
	}
	/**
	 * @param menuCnt the menuCnt to set
	 */
	public void setMenuCnt(Integer menuCnt) {
		this.menuCnt = menuCnt;
	}
	/**
	 * @return the delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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
	 * @return the billPrice
	 */
	public Float getBillPrice() {
		return billPrice;
	}
	/**
	 * @param billPrice the billPrice to set
	 */
	public void setBillPrice(Float billPrice) {
		this.billPrice = billPrice;
	}
}