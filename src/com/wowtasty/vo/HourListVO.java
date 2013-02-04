package com.wowtasty.vo;


/**
 * @author Hak C.
 *
 */
public class HourListVO {
	
	/** open hours 1, 2 columns*/
	private String ID = "";
	private Integer weekDay = 0;
	private boolean closeFlag = false;
	private String startTime1 = "";
	private String endTime1 = "";
	private String startTime2 = "";
	private String endTime2 = "";
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
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
	 * @return the closeFlag
	 */
	public boolean isCloseFlag() {
		return closeFlag;
	}
	/**
	 * @param closeFlag the closeFlag to set
	 */
	public void setCloseFlag(boolean closeFlag) {
		this.closeFlag = closeFlag;
	}
	/**
	 * @return the startTime1
	 */
	public String getStartTime1() {
		return startTime1;
	}
	/**
	 * @param startTime1 the startTime1 to set
	 */
	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}
	/**
	 * @return the endTime1
	 */
	public String getEndTime1() {
		return endTime1;
	}
	/**
	 * @param endTime1 the endTime1 to set
	 */
	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
	/**
	 * @return the startTime2
	 */
	public String getStartTime2() {
		return startTime2;
	}
	/**
	 * @param startTime2 the startTime2 to set
	 */
	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}
	/**
	 * @return the endTime2
	 */
	public String getEndTime2() {
		return endTime2;
	}
	/**
	 * @param endTime2 the endTime2 to set
	 */
	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}
}