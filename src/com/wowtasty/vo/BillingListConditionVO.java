package com.wowtasty.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hak C.
 *
 */
public class BillingListConditionVO {
	
	/** billing list page's condition items*/
	private String fromDate = "";
	private String toDate = "";
	private List<String> restaurantList = new ArrayList<String>();
	
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
	 * @return the restaurantList
	 */
	public List<String> getRestaurantList() {
		return restaurantList;
	}
	/**
	 * @param restaurantList the restaurantList to set
	 */
	public void setRestaurantList(List<String> restaurantList) {
		this.restaurantList = restaurantList;
	}
}