package com.wowtasty.mybatis.vo;

import java.sql.Time;

/**
 * @author Hak C.
 *
 */
public class DeliveryOpenHourVO {
	
	/** delivery open hours columns*/
	private String deliveryCompanyID = "";
	private Integer weekDay = 0;
	private Integer seq = 1;
	private Time startTime = null;
	private Time endTime = null;

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
	 * @return the weekDay
	 */
	public Integer getWeekDay() {
		return weekDay;
	}
	/**
	 * @param weekDay the weekDay to set
	 */
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
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
	 * @return the startTime
	 */
	public Time getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Time getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}