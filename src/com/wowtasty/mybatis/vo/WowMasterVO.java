package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;

/**
 * @author Hak C.
 *
 */
public class WowMasterVO {
	
	/** wow master columns*/
	private Integer seq = 0;
	private String name = "";
	private String memberID = "";
	private String telephone = "";
	private String fax = "";
	private String address = "";
	private String suite = "";
	private String city = "";
	private String province = "";
	private String postalCode = "";
	private String logoImagePath = "";
	private String website = "";
	private String facebook = "";
	private String email1 = "";
	private String email2 = "";
	private Integer ecashPerCent = 0;
	private Integer ecashBonus = 0;
	private Integer ecashPerCoupon = 0;
	private String holdingFlag = "1";
	private String taxNO = "";
	private Float tax1 = 0.00f;
	private Float tax2 = 0.00f;
	private String transactionKey = "";
	private String transactionLoginID = "";
	private Integer paymentValidPeriod = 0;
	private Integer paymentDate1 = 0;
	private Integer paymentDate2 = 0;
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
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
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
	 * @return the logoImagePath
	 */
	public String getLogoImagePath() {
		return logoImagePath;
	}
	/**
	 * @param logoImagePath the logoImagePath to set
	 */
	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return the facebook
	 */
	public String getFacebook() {
		return facebook;
	}
	/**
	 * @param facebook the facebook to set
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	/**
	 * @return the email1
	 */
	public String getEmail1() {
		return email1;
	}
	/**
	 * @param email1 the email1 to set
	 */
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	/**
	 * @return the email2
	 */
	public String getEmail2() {
		return email2;
	}
	/**
	 * @param email2 the email2 to set
	 */
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	/**
	 * @return the ecashPerCent
	 */
	public Integer getEcashPerCent() {
		return ecashPerCent;
	}
	/**
	 * @param ecashPerCent the ecashPerCent to set
	 */
	public void setEcashPerCent(Integer ecashPerCent) {
		this.ecashPerCent = ecashPerCent;
	}
	/**
	 * @return the ecashBonus
	 */
	public Integer getEcashBonus() {
		return ecashBonus;
	}
	/**
	 * @param ecashBonus the ecashBonus to set
	 */
	public void setEcashBonus(Integer ecashBonus) {
		this.ecashBonus = ecashBonus;
	}
	/**
	 * @return the ecashPerCoupon
	 */
	public Integer getEcashPerCoupon() {
		return ecashPerCoupon;
	}
	/**
	 * @param ecashPerCoupon the ecashPerCoupon to set
	 */
	public void setEcashPerCoupon(Integer ecashPerCoupon) {
		this.ecashPerCoupon = ecashPerCoupon;
	}
	/**
	 * @return the holdingFlag
	 */
	public String getHoldingFlag() {
		return holdingFlag;
	}
	/**
	 * @param holdingFlag the holdingFlag to set
	 */
	public void setHoldingFlag(String holdingFlag) {
		this.holdingFlag = holdingFlag;
	}
	/**
	 * @return the taxNO
	 */
	public String getTaxNO() {
		return taxNO;
	}
	/**
	 * @param taxNO the taxNO to set
	 */
	public void setTaxNO(String taxNO) {
		this.taxNO = taxNO;
	}
	/**
	 * @return the tax1
	 */
	public Float getTax1() {
		return tax1;
	}
	/**
	 * @param tax1 the tax1 to set
	 */
	public void setTax1(Float tax1) {
		this.tax1 = tax1;
	}
	/**
	 * @return the tax2
	 */
	public Float getTax2() {
		return tax2;
	}
	/**
	 * @param tax2 the tax2 to set
	 */
	public void setTax2(Float tax2) {
		this.tax2 = tax2;
	}
	/**
	 * @return the transactionKey
	 */
	public String getTransactionKey() {
		return transactionKey;
	}
	/**
	 * @param transactionKey the transactionKey to set
	 */
	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}
	/**
	 * @return the transactionLoginID
	 */
	public String getTransactionLoginID() {
		return transactionLoginID;
	}
	/**
	 * @param transactionLoginID the transactionLoginID to set
	 */
	public void setTransactionLoginID(String transactionLoginID) {
		this.transactionLoginID = transactionLoginID;
	}
	/**
	 * @return the paymentValidPeriod
	 */
	public Integer getPaymentValidPeriod() {
		return paymentValidPeriod;
	}
	/**
	 * @param paymentValidPeriod the paymentValidPeriod to set
	 */
	public void setPaymentValidPeriod(Integer paymentValidPeriod) {
		this.paymentValidPeriod = paymentValidPeriod;
	}
	/**
	 * @return the paymentDate1
	 */
	public Integer getPaymentDate1() {
		return paymentDate1;
	}
	/**
	 * @param paymentDate1 the paymentDate1 to set
	 */
	public void setPaymentDate1(Integer paymentDate1) {
		this.paymentDate1 = paymentDate1;
	}
	/**
	 * @return the paymentDate2
	 */
	public Integer getPaymentDate2() {
		return paymentDate2;
	}
	/**
	 * @param paymentDate2 the paymentDate2 to set
	 */
	public void setPaymentDate2(Integer paymentDate2) {
		this.paymentDate2 = paymentDate2;
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