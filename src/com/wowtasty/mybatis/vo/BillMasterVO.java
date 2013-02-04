package com.wowtasty.mybatis.vo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class BillMasterVO {
	
	/** bill_master columns*/
	private String billingID = "";
	private String yearMonth = "";
	private String semiMonthType = "";
	private Date billFrom = null;
	private Date billTo = null;
	private Date issueDate = null;
	private String companyType = "";
	private String companyID = "";
	private String companyName = "";
	private String billAddress = "";
	private String billSuite = "";
	private String billCity = "";
	private String billProvince = "";
	private String billPostalCode = "";
	private String billAccountNO = "";
	private Double salesAmount = 0.00;
	private Double foodAmount = 0.00;
	private Double commissionAmount = 0.00;
	private Double cashCommissionAmount = 0.00;
	private Double deliveryAmount = 0.00;
	private Double tipAmount = 0.00;
	private Double adjustmentAmount = 0.00;
	private String updateID = "";
	private Timestamp updateTime = null;
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
	 * @return the yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}
	/**
	 * @param yearMonth the yearMonth to set
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	/**
	 * @return the semiMonthType
	 */
	public String getSemiMonthType() {
		return semiMonthType;
	}
	/**
	 * @param semiMonthType the semiMonthType to set
	 */
	public void setSemiMonthType(String semiMonthType) {
		this.semiMonthType = semiMonthType;
	}
	/**
	 * @return the billFrom
	 */
	public Date getBillFrom() {
		return billFrom;
	}
	/**
	 * @param billFrom the billFrom to set
	 */
	public void setBillFrom(Date billFrom) {
		this.billFrom = billFrom;
	}
	/**
	 * @return the billTo
	 */
	public Date getBillTo() {
		return billTo;
	}
	/**
	 * @param billTo the billTo to set
	 */
	public void setBillTo(Date billTo) {
		this.billTo = billTo;
	}
	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}
	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	/**
	 * @return the companyID
	 */
	public String getCompanyID() {
		return companyID;
	}
	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * @return the billAccountNO
	 */
	public String getBillAccountNO() {
		return billAccountNO;
	}
	/**
	 * @param billAccountNO the billAccountNO to set
	 */
	public void setBillAccountNO(String billAccountNO) {
		this.billAccountNO = billAccountNO;
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
	 * @return the cashCommissionAmount
	 */
	public Double getCashCommissionAmount() {
		return cashCommissionAmount;
	}
	/**
	 * @param cashCommissionAmount the cashCommissionAmount to set
	 */
	public void setCashCommissionAmount(Double cashCommissionAmount) {
		this.cashCommissionAmount = cashCommissionAmount;
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
	 * @return the adjustmentAmount
	 */
	public Double getAdjustmentAmount() {
		return adjustmentAmount;
	}
	/**
	 * @param adjustmentAmount the adjustmentAmount to set
	 */
	public void setAdjustmentAmount(Double adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
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
	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}
	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
}