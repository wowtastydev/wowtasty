package com.wowtasty.vo;

import com.wowtasty.util.Constants;

/**
 * Restaurant search option
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class RestaurantSearchConditionVO {
    
    private String restaurantID = "";
    private String location = "";
    private String keyword = "";
    private String postalCode = "";
    private String postalPrefix = "";
    private String restaurantType = "1";
    private String city = "";
    private String cityName = "";
    private String cuisineType = "";
    private String orderBy = "";
    private String preOrderDate = "";
    private String preOrderTime = "";
    //Group code
    private String cityGCD = Constants.KEY_CD_CITY;
    private String provinceGCD = Constants.KEY_CD_PROVINCE;
    private String cuisineGCD = Constants.KEY_CD_CUISINE_TYPE;

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the restaurantType
     */
    public String getRestaurantType() {
        return restaurantType;
    }

    /**
     * @param restaurantType the restaurantType to set
     */
    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
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
     * @return the cuisineType
     */
    public String getCuisineType() {
        return cuisineType;
    }

    /**
     * @param cuisineType the cuisineType to set
     */
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the preOrderTime
     */
    public String getPreOrderTime() {
        return preOrderTime;
    }

    /**
     * @param preOrderTime the preOrderTime to set
     */
    public void setPreOrderTime(String preOrderTime) {
        this.preOrderTime = preOrderTime;
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
        if (null!=this.postalCode&&!"".equals(this.postalCode)) {
            this.setPostalPrefix(this.postalCode.substring(0, 3));
        }
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

    /**
     * @return the cityGCD
     */
    public String getCityGCD() {
        return cityGCD;
    }

    /**
     * @param cityGCD the cityGCD to set
     */
    public void setCityGCD(String cityGCD) {
        this.cityGCD = cityGCD;
    }

    /**
     * @return the provinceGCD
     */
    public String getProvinceGCD() {
        return provinceGCD;
    }

    /**
     * @param provinceGCD the provinceGCD to set
     */
    public void setProvinceGCD(String provinceGCD) {
        this.provinceGCD = provinceGCD;
    }

    /**
     * @return the cuisineGCD
     */
    public String getCuisineGCD() {
        return cuisineGCD;
    }

    /**
     * @param cuisineGCD the cuisineGCD to set
     */
    public void setCuisineGCD(String cuisineGCD) {
        this.cuisineGCD = cuisineGCD;
    }

    /**
     * @return the preOrderDate
     */
    public String getPreOrderDate() {
        return preOrderDate;
    }

    /**
     * @param preOrderDate the preOrderDate to set
     */
    public void setPreOrderDate(String preOrderDate) {
        this.preOrderDate = preOrderDate;
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
}
