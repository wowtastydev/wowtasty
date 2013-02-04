package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class BalanceListConditionVO {
	
	/** order list's condition items on the balance mangement page */
	private String fromDate = "";
	private String toDate = "";
	private String term = "";
	private String restaurantID = "";
	private Integer paymentValidPeriod = 0;
	private Float commission = 0.00f;
	private Float cashCommission = 0.00f;
	private Float tax = 0.00f;
	
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the commission
	 */
	public Float getCommission() {
		return commission;
	}
	/**
	 * @param commission the commission to set
	 */
	public void setCommission(Float commission) {
		this.commission = commission;
	}
	/**
	 * @return the cashCommission
	 */
	public Float getCashCommission() {
		return cashCommission;
	}
	/**
	 * @param cashCommission the cashCommission to set
	 */
	public void setCashCommission(Float cashCommission) {
		this.cashCommission = cashCommission;
	}
	/**
	 * @return the tax
	 */
	public Float getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(Float tax) {
		this.tax = tax;
	}
}