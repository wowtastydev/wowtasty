package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class OrderAdjustmentVO {
	
	/** order_adjuestment columns*/
	private Integer seq = 0;
	private String orderID = "";
	private String restaurantID = "";
	private Timestamp registerTime = null;
	private String adjustType = "";
	private String adjustItem = "";
	private Float price = 0.00f;
	private String updateID = "";
	private Timestamp updateTime = null;
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
	 * @return the registerTime
	 */
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * @return the adjustType
	 */
	public String getAdjustType() {
		return adjustType;
	}
	/**
	 * @param adjustType the adjustType to set
	 */
	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}
	/**
	 * @return the adjustItem
	 */
	public String getAdjustItem() {
		return adjustItem;
	}
	/**
	 * @param adjustItem the adjustItem to set
	 */
	public void setAdjustItem(String adjustItem) {
		this.adjustItem = adjustItem;
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