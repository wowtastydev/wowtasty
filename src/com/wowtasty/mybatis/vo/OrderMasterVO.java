package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class OrderMasterVO {
	
	/** order_master columns*/
	private String orderID = "";
	private String invoiceNO = "";
	private Timestamp orderTime = null;
	private Timestamp deliveryTime = null;
	private String orderType = "";
	private String paymentType = "";
	private String paymentCreditType = "";
	private String paymentCreditNO = "";
	private String paymentDebitNO = "";
	private Float paymentAmount = 0.00f;
	private String paymentRefNO = "";
	private String paymentStatus = "";
	private Timestamp paymentTime = null;
	private Integer paymentEcash = 0;
	private Float paymentGiftCard = 0.00f;
	private Float totalPrice = 0.00f;
	private Float totalPriceWithTax = 0.00f;
	private String orderMemberID = "";
	private String orderMemberEmail = "";
	private String orderMemberTelephone = "";
	private String billFirstName = "";
	private String billLastName = "";
	private String billTelephone = "";
	private String billAddress = "";
	private String billSuite = "";
	private String billCity = "";
	private String billProvince = "";
	private String billPostalCode = "";
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
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	 * @return the paymentCreditType
	 */
	public String getPaymentCreditType() {
		return paymentCreditType;
	}
	/**
	 * @param paymentCreditType the paymentCreditType to set
	 */
	public void setPaymentCreditType(String paymentCreditType) {
		this.paymentCreditType = paymentCreditType;
	}
	/**
	 * @return the paymentCreditNO
	 */
	public String getPaymentCreditNO() {
		return paymentCreditNO;
	}
	/**
	 * @param paymentCreditNO the paymentCreditNO to set
	 */
	public void setPaymentCreditNO(String paymentCreditNO) {
		this.paymentCreditNO = paymentCreditNO;
	}
	/**
	 * @return the paymentDebitNO
	 */
	public String getPaymentDebitNO() {
		return paymentDebitNO;
	}
	/**
	 * @param paymentDebitNO the paymentDebitNO to set
	 */
	public void setPaymentDebitNO(String paymentDebitNO) {
		this.paymentDebitNO = paymentDebitNO;
	}
	/**
	 * @return the paymentAmount
	 */
	public Float getPaymentAmount() {
		return paymentAmount;
	}
	/**
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(Float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	/**
	 * @return the paymentRefNO
	 */
	public String getPaymentRefNO() {
		return paymentRefNO;
	}
	/**
	 * @param paymentRefNO the paymentRefNO to set
	 */
	public void setPaymentRefNO(String paymentRefNO) {
		this.paymentRefNO = paymentRefNO;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the paymentTime
	 */
	public Timestamp getPaymentTime() {
		return paymentTime;
	}
	/**
	 * @param paymentTime the paymentTime to set
	 */
	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}
	/**
	 * @return the paymentEcash
	 */
	public Integer getPaymentEcash() {
		return paymentEcash;
	}
	/**
	 * @param paymentEcash the paymentEcash to set
	 */
	public void setPaymentEcash(Integer paymentEcash) {
		this.paymentEcash = paymentEcash;
	}
	/**
	 * @return the paymentGiftCard
	 */
	public Float getPaymentGiftCard() {
		return paymentGiftCard;
	}
	/**
	 * @param paymentGiftCard the paymentGiftCard to set
	 */
	public void setPaymentGiftCard(Float paymentGiftCard) {
		this.paymentGiftCard = paymentGiftCard;
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
	 * @return the billFirstName
	 */
	public String getBillFirstName() {
		return billFirstName;
	}
	/**
	 * @param billFirstName the billFirstName to set
	 */
	public void setBillFirstName(String billFirstName) {
		this.billFirstName = billFirstName;
	}
	/**
	 * @return the billLastName
	 */
	public String getBillLastName() {
		return billLastName;
	}
	/**
	 * @param billLastName the billLastName to set
	 */
	public void setBillLastName(String billLastName) {
		this.billLastName = billLastName;
	}
	/**
	 * @return the billTelephone
	 */
	public String getBillTelephone() {
		return billTelephone;
	}
	/**
	 * @param billTelephone the billTelephone to set
	 */
	public void setBillTelephone(String billTelephone) {
		this.billTelephone = billTelephone;
	}
	/**
	 * @return the billAddress
	 */
	public String getBillAddress() {
		return billAddress;
	}
	/**
	 * @param billAddress the billAddress to set
	 */
	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}
	/**
	 * @return the billSuite
	 */
	public String getBillSuite() {
		return billSuite;
	}
	/**
	 * @param billSuite the billSuite to set
	 */
	public void setBillSuite(String billSuite) {
		this.billSuite = billSuite;
	}
	/**
	 * @return the billCity
	 */
	public String getBillCity() {
		return billCity;
	}
	/**
	 * @param billCity the billCity to set
	 */
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	/**
	 * @return the billProvince
	 */
	public String getBillProvince() {
		return billProvince;
	}
	/**
	 * @param billProvince the billProvince to set
	 */
	public void setBillProvince(String billProvince) {
		this.billProvince = billProvince;
	}
	/**
	 * @return the billPostalCode
	 */
	public String getBillPostalCode() {
		return billPostalCode;
	}
	/**
	 * @param billPostalCode the billPostalCode to set
	 */
	public void setBillPostalCode(String billPostalCode) {
		this.billPostalCode = billPostalCode;
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