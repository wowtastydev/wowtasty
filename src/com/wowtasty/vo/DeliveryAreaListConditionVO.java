package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class DeliveryAreaListConditionVO {
	
	/** delivery area list condition items*/
	private String name = "";
	private String postalPrefix = "";
	
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
}