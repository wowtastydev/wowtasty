package com.wowtasty.vo;

import com.wowtasty.mybatis.vo.RestaurantMasterVO;

/**
 * Extends Restaurant Mapping VO
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class RestaurantEVO extends RestaurantMasterVO {

    private String restaurantOpenStatus = "";
    private String deliveryOpenStatus = "";
    private String cuisineName = "";
    private String cityName = "";
    private String provinceName = "";
    private String startTime = "";
    private String endTime = "";
    private String dayName = "";
    private String openHour1 = "";
    private String openHour2 = "";
    
    /**
     * @return the restaurantOpenStatus
     */
    public String getRestaurantOpenStatus() {
        return restaurantOpenStatus;
    }

    /**
     * @param restaurantOpenStatus the restaurantOpenStatus to set
     */
    public void setRestaurantOpenStatus(String restaurantOpenStatus) {
        this.restaurantOpenStatus = restaurantOpenStatus;
    }

    /**
     * @return the deliveryOpenStatus
     */
    public String getDeliveryOpenStatus() {
        return deliveryOpenStatus;
    }

    /**
     * @param deliveryOpenStatus the deliveryOpenStatus to set
     */
    public void setDeliveryOpenStatus(String deliveryOpenStatus) {
        this.deliveryOpenStatus = deliveryOpenStatus;
    }

    /**
     * @return the cuisineName
     */
    public String getCuisineName() {
        return cuisineName;
    }

    /**
     * @param cuisineName the cuisineName to set
     */
    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the dayName
     */
    public String getDayName() {
        return dayName;
    }

    /**
     * @param dayName the dayName to set
     */
    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    /**
     * @return the openHour1
     */
    public String getOpenHour1() {
        return openHour1;
    }

    /**
     * @param openHour1 the openHour1 to set
     */
    public void setOpenHour1(String openHour1) {
        this.openHour1 = openHour1;
    }

    /**
     * @return the openHour2
     */
    public String getOpenHour2() {
        return openHour2;
    }

    /**
     * @param openHour2 the openHour2 to set
     */
    public void setOpenHour2(String openHour2) {
        this.openHour2 = openHour2;
    }

}
