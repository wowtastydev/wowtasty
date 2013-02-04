package com.wowtasty.vo;


/**
 * @author Hak C.
 *
 */
public class BillingListVO {
	
	/** billing list columns*/
	private String billingID = "";
	private String yearMonth = "";
	private String semiMonthType = "";
	private String companyID = "";
	private String companyName = "";
	
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
}