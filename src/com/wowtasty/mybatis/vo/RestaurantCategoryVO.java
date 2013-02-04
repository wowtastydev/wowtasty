package com.wowtasty.mybatis.vo;


/**
 * @author Hak C.
 *
 */
public class RestaurantCategoryVO {
	
	/** restaurant menu columns*/
	private String restaurantID = "";
	private Integer categoryID = 0;
	private String name = "";
	private Integer sort = 1;
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
	public Integer getCategoryID() {
		return categoryID;
	}
	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}