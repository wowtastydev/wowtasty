package com.wowtasty.mybatis.vo;

/**
 * @author Hak C.
 *
 */
public class OrderMenuVO {
	
	/** order_menu columns*/
	private String orderID = "";
	private String restaurantID = "";
	private int seq = 0;
	private String menuID = "";
	private String menuName = "";
	private int unit = 0;
	private float unitPrice = 0.00f;
	private float totalExtraCharge = 0.00f;
	private String couponNO = "";
	private float couponPrice = 0.00f;
	private float taxPrice = 0.00f;
	private float totalPrice = 0.00f;
	private float totalPriceWithTax = 0.00f;
	private String Instruction = "";
	
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
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the totalExtraCharge
	 */
	public float getTotalExtraCharge() {
		return totalExtraCharge;
	}
	/**
	 * @param totalExtraCharge the totalExtraCharge to set
	 */
	public void setTotalExtraCharge(float totalExtraCharge) {
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
	public float getCouponPrice() {
		return couponPrice;
	}
	/**
	 * @param couponPrice the couponPrice to set
	 */
	public void setCouponPrice(float couponPrice) {
		this.couponPrice = couponPrice;
	}
	/**
	 * @return the taxPrice
	 */
	public float getTaxPrice() {
		return taxPrice;
	}
	/**
	 * @param taxPrice the taxPrice to set
	 */
	public void setTaxPrice(float taxPrice) {
		this.taxPrice = taxPrice;
	}
	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the totalPriceWithTax
	 */
	public float getTotalPriceWithTax() {
		return totalPriceWithTax;
	}
	/**
	 * @param totalPriceWithTax the totalPriceWithTax to set
	 */
	public void setTotalPriceWithTax(float totalPriceWithTax) {
		this.totalPriceWithTax = totalPriceWithTax;
	}
	/**
	 * @return the instruction
	 */
	public String getInstruction() {
		return Instruction;
	}
	/**
	 * @param instruction the instruction to set
	 */
	public void setInstruction(String instruction) {
		Instruction = instruction;
	}
}