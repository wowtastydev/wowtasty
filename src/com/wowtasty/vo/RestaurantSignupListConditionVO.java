package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class RestaurantSignupListConditionVO {
	
	/** restaurant signup list page's condition items*/
	private String fromDate = "";
	private String toDate = "";
	
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
}