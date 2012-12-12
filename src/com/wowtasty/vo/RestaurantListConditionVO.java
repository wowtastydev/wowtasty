package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class RestaurantListConditionVO {
	
	/** restaurant list page's condition items*/
	private String cuisineType = "";
	private String city = "";
	private String restaurantType = "";
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
}