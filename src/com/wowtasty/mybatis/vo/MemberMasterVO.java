package com.wowtasty.mybatis.vo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class MemberMasterVO {
	
	/** member_master columns*/
	private String memberID = "";
	private String email = "";
	private String password = "";
	private String auth = "";
	private String firstName = "";
	private String lastName = "";
	private String telephone = "";
	private String address = "";
	private String suite = "";
	private String city = "";
	private String province = "";
	private String postalCode = "";
	private String delivFirstName = "";
	private String delivLastName = "";
	private String delivTelephone = "";
	private String delivAddress = "";
	private String delivSuite = "";
	private String delivBuzzer = "";
	private String delivCity = "";
	private String delivProvince = "";
	private String delivInstruction = "";
	private String delivPostalCode = "";
	private int ecash = 0;
	private String status = "";
	private int totalOrderCnt = 0;
	private Date registerDate = null;
	private String naFlag = "0";
	private String updateID = "";
	private Timestamp updateTime = null;
	/**
	 * @return the memberID
	 */
	public String getMemberID() {
		return memberID;
	}
	/**
	 * @param memberID the memberID to set
	 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
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
	 * @return the delivFirstName
	 */
	public String getDelivFirstName() {
		return delivFirstName;
	}
	/**
	 * @param delivFirstName the delivFirstName to set
	 */
	public void setDelivFirstName(String delivFirstName) {
		this.delivFirstName = delivFirstName;
	}
	/**
	 * @return the delivLastName
	 */
	public String getDelivLastName() {
		return delivLastName;
	}
	/**
	 * @param delivLastName the delivLastName to set
	 */
	public void setDelivLastName(String delivLastName) {
		this.delivLastName = delivLastName;
	}
	/**
	 * @return the delivTelephone
	 */
	public String getDelivTelephone() {
		return delivTelephone;
	}
	/**
	 * @param delivTelephone the delivTelephone to set
	 */
	public void setDelivTelephone(String delivTelephone) {
		this.delivTelephone = delivTelephone;
	}
	/**
	 * @return the delivAddress
	 */
	public String getDelivAddress() {
		return delivAddress;
	}
	/**
	 * @param delivAddress the delivAddress to set
	 */
	public void setDelivAddress(String delivAddress) {
		this.delivAddress = delivAddress;
	}
	/**
	 * @return the delivSuite
	 */
	public String getDelivSuite() {
		return delivSuite;
	}
	/**
	 * @param delivSuite the delivSuite to set
	 */
	public void setDelivSuite(String delivSuite) {
		this.delivSuite = delivSuite;
	}
	/**
	 * @return the delivBuzzer
	 */
	public String getDelivBuzzer() {
		return delivBuzzer;
	}
	/**
	 * @param delivBuzzer the delivBuzzer to set
	 */
	public void setDelivBuzzer(String delivBuzzer) {
		this.delivBuzzer = delivBuzzer;
	}
	/**
	 * @return the delivCity
	 */
	public String getDelivCity() {
		return delivCity;
	}
	/**
	 * @param delivCity the delivCity to set
	 */
	public void setDelivCity(String delivCity) {
		this.delivCity = delivCity;
	}
	/**
	 * @return the delivProvince
	 */
	public String getDelivProvince() {
		return delivProvince;
	}
	/**
	 * @param delivProvince the delivProvince to set
	 */
	public void setDelivProvince(String delivProvince) {
		this.delivProvince = delivProvince;
	}

	/**
	 * @return the delivInstruction
	 */
	public String getDelivInstruction() {
		return delivInstruction;
	}
	/**
	 * @param delivInstruction the delivInstruction to set
	 */
	public void setDelivInstruction(String delivInstruction) {
		this.delivInstruction = delivInstruction;
	}
	/**
	 * @return the delivPostalCode
	 */
	public String getDelivPostalCode() {
		return delivPostalCode;
	}
	/**
	 * @param delivPostalCode the delivPostalCode to set
	 */
	public void setDelivPostalCode(String delivPostalCode) {
		this.delivPostalCode = delivPostalCode;
	}
	/**
	 * @return the ecash
	 */
	public int getEcash() {
		return ecash;
	}
	/**
	 * @param ecash the ecash to set
	 */
	public void setEcash(int ecash) {
		this.ecash = ecash;
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
	 * @return the totalOrderCnt
	 */
	public int getTotalOrderCnt() {
		return totalOrderCnt;
	}
	/**
	 * @param totalOrderCnt the totalOrderCnt to set
	 */
	public void setTotalOrderCnt(int totalOrderCnt) {
		this.totalOrderCnt = totalOrderCnt;
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
	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}
	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

}