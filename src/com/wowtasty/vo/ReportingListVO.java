package com.wowtasty.vo;


/**
 * @author Hak C.
 *
 */
public class ReportingListVO {
	
	/** reporting list columns*/
	private String type = "";
	private String restaurantID = "";
	private String restaurantName = "";
	private Integer day = -1;
	private Integer date = 0;
	private Integer hour = 0;
	private Double revenue = 0.0;
	private Double commissionDelivery = 0.0;
	private Double commissionTakeout = 0.0;
	private Double commissionTotal = 0.0;
	private Double deliveryPrice = 0.0;
	private Double tipPrice = 0.0;
	private Integer deliveryCount = 0;
	private Integer takeoutCount = 0;
	private Double totalIncome = 0.0;
	private Integer rateRevenue = 0;
	private Integer rateIncome = 0;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}
	/**
	 * @param restaurantName the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * @return the date
	 */
	public Integer getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Integer date) {
		this.date = date;
	}
	/**
	 * @return the hour
	 */
	public Integer getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	/**
	 * @return the revenue
	 */
	public Double getRevenue() {
		return revenue;
	}
	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	/**
	 * @return the commissionDelivery
	 */
	public Double getCommissionDelivery() {
		return commissionDelivery;
	}
	/**
	 * @param commissionDelivery the commissionDelivery to set
	 */
	public void setCommissionDelivery(Double commissionDelivery) {
		this.commissionDelivery = commissionDelivery;
	}
	/**
	 * @return the commissionTakeout
	 */
	public Double getCommissionTakeout() {
		return commissionTakeout;
	}
	/**
	 * @param commissionTakeout the commissionTakeout to set
	 */
	public void setCommissionTakeout(Double commissionTakeout) {
		this.commissionTakeout = commissionTakeout;
	}
	/**
	 * @return the commissionTotal
	 */
	public Double getCommissionTotal() {
		return commissionTotal;
	}
	/**
	 * @param commissionTotal the commissionTotal to set
	 */
	public void setCommissionTotal(Double commissionTotal) {
		this.commissionTotal = commissionTotal;
	}

	/**
	 * @return the deliveryPrice
	 */
	public Double getDeliveryPrice() {
		return deliveryPrice;
	}
	/**
	 * @param deliveryPrice the deliveryPrice to set
	 */
	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	/**
	 * @return the tipPrice
	 */
	public Double getTipPrice() {
		return tipPrice;
	}
	/**
	 * @param tipPrice the tipPrice to set
	 */
	public void setTipPrice(Double tipPrice) {
		this.tipPrice = tipPrice;
	}
	/**
	 * @return the deliveryCount
	 */
	public Integer getDeliveryCount() {
		return deliveryCount;
	}
	/**
	 * @param deliveryCount the deliveryCount to set
	 */
	public void setDeliveryCount(Integer deliveryCount) {
		this.deliveryCount = deliveryCount;
	}
	/**
	 * @return the takeoutCount
	 */
	public Integer getTakeoutCount() {
		return takeoutCount;
	}
	/**
	 * @param takeoutCount the takeoutCount to set
	 */
	public void setTakeoutCount(Integer takeoutCount) {
		this.takeoutCount = takeoutCount;
	}
	/**
	 * @return the totalIncome
	 */
	public Double getTotalIncome() {
		return totalIncome;
	}
	/**
	 * @param totalIncome the totalIncome to set
	 */
	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}
	/**
	 * @return the rateRevenue
	 */
	public Integer getRateRevenue() {
		return rateRevenue;
	}
	/**
	 * @param rateRevenue the rateRevenue to set
	 */
	public void setRateRevenue(Integer rateRevenue) {
		this.rateRevenue = rateRevenue;
	}
	/**
	 * @return the rateIncome
	 */
	public Integer getRateIncome() {
		return rateIncome;
	}
	/**
	 * @param rateIncome the rateIncome to set
	 */
	public void setRateIncome(Integer rateIncome) {
		this.rateIncome = rateIncome;
	}
}