package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class DeliveryManVO {
	
	/** delivery_man columns*/
	private String deliveryCompanyID = "";
	private Integer deliverymanID = 0;
	private String firstName = "";
	private String lastName = "";
	private String telephone = "";
	private String email = "";
	private String address = "";
	private String suite = "";
	private String city = "";
	private String province = "";
	private String postalCode = "";
	private String naFlag = "0";
	private String updateID = "";
	private Timestamp updateTime = null;
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
	 * @return the deliverymanID
	 */
	public Integer getDeliverymanID() {
		return deliverymanID;
	}
	/**
	 * @param deliverymanID the deliverymanID to set
	 */
	public void setDeliverymanID(Integer deliverymanID) {
		this.deliverymanID = deliverymanID;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the suite
	 */
	public String getSuite() {
		return suite;
	}
	/**
	 * @param suite the suite to set
	 */
	public void setSuite(String suite) {
		this.suite = suite;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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