package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class RestaurantMenuVO {
	
	/** restaurant menu columns*/
	private String restaurantID = "";
	private Integer menuID = 0;
	private Integer categoryID = 0;
	private String name = "";
	private Float price = 0.00f;
	private String description = "";
	private String imagePath = "";
	private Integer sort = 1;
	private Float taxRate = 0.00f;
	private String status = "";
	private Integer minUnit = 1;
	private String naFlag = "0";
	private String updateID = "";
	private Timestamp updateTime = null;
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
	 * @return the menuID
	 */
	public Integer getMenuID() {
		return menuID;
	}
	/**
	 * @param menuID the menuID to set
	 */
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
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
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	/**
	 * @return the taxRate
	 */
	public Float getTaxRate() {
		return taxRate;
	}
	/**
	 * @param taxRate the taxRate to set
	 */
	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the minUnit
	 */
	public Integer getMinUnit() {
		return minUnit;
	}
	/**
	 * @param minUnit the minUnit to set
	 */
	public void setMinUnit(Integer minUnit) {
		this.minUnit = minUnit;
	}
	/**
	 * @return the naFlag
	 */
	public String getNaFlag() {
		return naFlag;
	}
	/**
	 * @param naFlag the naFlag to set
	 */
	public void setNaFlag(String naFlag) {
		this.naFlag = naFlag;
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