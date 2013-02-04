package com.wowtasty.mybatis.vo;

/**
 * @author Hak C.
 *
 */
public class OrderMenuVO {
	
	/** order_menu columns*/
	private String orderID = "";
	private String restaurantID = "";
	private Integer seq = 1;
	private String menuID = "";
	private String menuName = "";
	private Integer unit = 0;
	private Float unitPrice = 0.00f;
	private Float totalExtraCharge = 0.00f;
	private String couponNO = "";
	private Float couponPrice = 0.00f;
	private Float taxPrice = 0.00f;
	private Float totalPrice = 0.00f;
	private Float totalPriceWithTax = 0.00f;
	private String instruction = "";
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
	public Integer getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
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
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return the unit
	 */
	public Integer getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	/**
	 * @return the unitPrice
	 */
	public Float getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the totalExtraCharge
	 */
	public Float getTotalExtraCharge() {
		return totalExtraCharge;
	}
	/**
	 * @param totalExtraCharge the totalExtraCharge to set
	 */
	public void setTotalExtraCharge(Float totalExtraCharge) {
		this.totalExtraCharge = totalExtraCharge;
	}
	/**
	 * @return the couponNO
	 */
	public String getCouponNO() {
		return couponNO;
	}
	/**
	 * @param couponNO the couponNO to set
	 */
	public void setCouponNO(String couponNO) {
		this.couponNO = couponNO;
	}
	/**
	 * @return the couponPrice
	 */
	public Float getCouponPrice() {
		return couponPrice;
	}
	/**
	 * @param couponPrice the couponPrice to set
	 */
	public void setCouponPrice(Float couponPrice) {
		this.couponPrice = couponPrice;
	}
	/**
	 * @return the taxPrice
	 */
	public Float getTaxPrice() {
		return taxPrice;
	}
	/**
	 * @param taxPrice the taxPrice to set
	 */
	public void setTaxPrice(Float taxPrice) {
		this.taxPrice = taxPrice;
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
	 * @return the instruction
	 */
	public String getInstruction() {
		return instruction;
	}
	/**
	 * @param instruction the instruction to set
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}