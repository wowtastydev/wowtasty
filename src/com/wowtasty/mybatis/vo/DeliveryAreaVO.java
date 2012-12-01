package com.wowtasty.mybatis.vo;

/**
 * @author Hak C.
 *
 */
public class DeliveryAreaVO {
	
	/** delivery_area columns*/
	private String deliveryCompanyID = "";
	private String postalPrefix = "";
	private int seq = 1;
	private float minPrice = 0.00f;
	private float deliveryFee = 0.00f;
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
	 * @return the minPrice
	 */
	public float getMinPrice() {
		return minPrice;
	}
	/**
	 * @param minPrice the minPrice to set
	 */
	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * @return the deliveryFee
	 */
	public float getDeliveryFee() {
		return deliveryFee;
	}
	/**
	 * @param deliveryFee the deliveryFee to set
	 */
	public void setDeliveryFee(float deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
}