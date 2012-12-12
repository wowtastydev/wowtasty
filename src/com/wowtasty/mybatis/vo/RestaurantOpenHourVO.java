package com.wowtasty.mybatis.vo;

import java.sql.Time;

/**
 * @author Hak C.
 *
 */
public class RestaurantOpenHourVO {
	
	/** restaurant open hours columns*/
	private String restaurantID = "";
	private Integer weekDay = 0;
	private Integer seq = 0;
	private Time startTime = null;
	private Time endTime = null;
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