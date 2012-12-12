package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class RestaurantMenuOptionVO {
	
	/** restaurant menu option columns*/
	private String restaurantID = "";
	private Integer menuID = 0;
	private Integer optionID = 0;
	private String optionType = "";
	private Integer optionGroup = 0;
	private String Name = "";
	private Float extraCharge = 0.00f;
	private String naFlag = "";
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
	 * @return the optionID
	 */
	public Integer getOptionID() {
		return optionID;
	}
	/**
	 * @param optionID the optionID to set
	 */
	public void setOptionID(Integer optionID) {
		this.optionID = optionID;
	}
	/**
	 * @return the optionType
	 */
	public String getOptionType() {
		return optionType;
	}
	/**
	 * @param optionType the optionType to set
	 */
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	/**
	 * @return the optionGroup
	 */
	public Integer getOptionGroup() {
		return optionGroup;
	}
	/**
	 * @param optionGroup the optionGroup to set
	 */
	public void setOptionGroup(Integer optionGroup) {
		this.optionGroup = optionGroup;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the extraCharge
	 */
	public Float getExtraCharge() {
		return extraCharge;
	}
	/**
	 * @param extraCharge the extraCharge to set
	 */
	public void setExtraCharge(Float extraCharge) {
		this.extraCharge = extraCharge;
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