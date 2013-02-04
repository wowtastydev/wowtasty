package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class MenuListConditionVO {
	
	/** menu list page's condition items*/
	private String restaurantID = "";
	private String categoryID = "";
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
	 * @return the categoryID
	 */
	public String getCategoryID() {
		return categoryID;
	}
	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

}