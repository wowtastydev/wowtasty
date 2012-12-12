package com.wowtasty.mybatis.vo;


/**
 * @author Hak C.
 *
 */
public class RestaurantDeliveryAreaVO {
	
	/** restaurant delivery areas columns*/
	private String restaurantID = "";
	private String postalPrefix = "";
	private Integer seq = 0;
	private String deliveryCompanyID= "";
	private Float minPrice = 0.00f;
	private Float deliveryFee = 0.00f;
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
	 * @return the postalPrefix
	 */
	public String getPostalPrefix() {
		return postalPrefix;
	}
	/**
	 * @param postalPrefix the postalPrefix to set
	 */
	public void setPostalPrefix(String postalPrefix) {
		this.postalPrefix = postalPrefix;
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
	 * @return the deliveryCompanyID
	 */
	public String getDeliveryCompanyID() {
		return deliveryCompanyID;
	}
	/**
	 * @param deliveryCompanyID the deliveryCompanyID to set
	 */
	public void setDeliveryCompanyID(String deliveryCompanyID) {
		this.deliveryCompanyID = deliveryCompanyID;
	}
	/**
	 * @return the minPrice
	 */
	public Float getMinPrice() {
		return minPrice;
	}
	/**
	 * @param minPrice the minPrice to set
	 */
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * @return the deliveryFee
	 */
	public Float getDeliveryFee() {
		return deliveryFee;
	}
	/**
	 * @param deliveryFee the deliveryFee to set
	 */
	public void setDeliveryFee(Float deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
}