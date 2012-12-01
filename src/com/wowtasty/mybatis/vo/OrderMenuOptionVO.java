package com.wowtasty.mybatis.vo;

/**
 * @author Hak C.
 *
 */
public class OrderMenuOptionVO {
	
	/** order_menu_option columns*/
	private String orderID = "";
	private String restaurantID = "";
	private int seq = 0;
	private String optionID = "";
	private String optionName = "";
	private String menuID = "";
	private int unit = 0;
	private float extraCharge = 0.00f;
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
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	/**
	 * @return the optionID
	 */
	public String getOptionID() {
		return optionID;
	}
	/**
	 * @param optionID the optionID to set
	 */
	public void setOptionID(String optionID) {
		this.optionID = optionID;
	}
	/**
	 * @return the optionName
	 */
	public String getOptionName() {
		return optionName;
	}
	/**
	 * @param optionName the optionName to set
	 */
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	/**
	 * @return the menuID
	 */
	public String getMenuID() {
		return menuID;
	}
	/**
	 * @param menuID the menuID to set
	 */
	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}
	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}
	/**
	 * @return the extraCharge
	 */
	public float getExtraCharge() {
		return extraCharge;
	}
	/**
	 * @param extraCharge the extraCharge to set
	 */
	public void setExtraCharge(float extraCharge) {
		this.extraCharge = extraCharge;
	}

}