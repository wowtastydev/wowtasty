package com.wowtasty.mybatis.vo;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * @author Hak C.
 *
 */
public class RestaurantMasterVO {
	
	/** restaurant master columns*/
	private String restaurantID = "";
	private String restaurantType = "";
	private String name = "";
	private String cuisineType = "";
	private String memberID = "";
	private String firstName = "";
	private String lastName = "";
	private String telephone = "";
	private String fax = "";
	private String address = "";
	private String suite = "";
	private String city = "";
	private String province = "";
	private String postalCode = "";
	private String website = "";
	private String facebook = "";
	private String email1 = "";
	private String email2 = "";
	private String logoImagePath = "";
	private String mainImagePath = "";
	private String googleMapURL = "";
	private String profile = "";
	private String keyword = "";
	private String commissionType = "";
	private Float commission = 0.00f;
	private Float cashCommission = 0.00f;
	private Float averagePrice = 0.00f;
	private Float minPrice = 0.00f;
	private String deliveryCompanyID = "";
	private Integer deliveryTime = 0;
	private String billAccountNO = "";
	private Float averageRate = 0.0f;
	private Integer totalReviewCnt = 0;
	private Integer totalOrderCnt = 0;
	private String status = "";
	private Date registerDate = null;
	private String naFlag = "0";
	private String updateID = "";
	private Timestamp updateTime = null;
    private String restaurantOpenStatus = "";
    private String deliveryOpenStatus = "";
    private String cuisineName = "";
    private String cityName = "";
    private String provinceName = "";
    private String startTime = "";
    private String endTime = "";
	
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
	 * @return the mainImagePath
	 */
	public String getMainImagePath() {
		return mainImagePath;
	}
	/**
	 * @param mainImagePath the mainImagePath to set
	 */
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}
	/**
	 * @return the googleMapURL
	 */
	public String getGoogleMapURL() {
		return googleMapURL;
	}
	/**
	 * @param googleMapURL the googleMapURL to set
	 */
	public void setGoogleMapURL(String googleMapURL) {
		this.googleMapURL = googleMapURL;
	}
	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
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
	 * @return the commissionType
	 */
	public String getCommissionType() {
		return commissionType;
	}
	/**
	 * @param commissionType the commissionType to set
	 */
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
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
	 * @return the averagePrice
	 */
	public Float getAveragePrice() {
		return averagePrice;
	}
	/**
	 * @param averagePrice the averagePrice to set
	 */
	public void setAveragePrice(Float averagePrice) {
		this.averagePrice = averagePrice;
	}
	/**
	 * @return the minPrice
	 */
	public Float getMinPrice() {
		return minPrice;
	}
	/**
	 * @param minPrice the minPrice to set
	 */
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
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
	 * @return the deliveryTime
	 */
	public Integer getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * @param deliveryTime the deliveryTime to set
	 */
	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * @return the billAccountNO
	 */
	public String getBillAccountNO() {
		return billAccountNO;
	}
	/**
	 * @param billAccountNO the billAccountNO to set
	 */
	public void setBillAccountNO(String billAccountNO) {
		this.billAccountNO = billAccountNO;
	}
	/**
	 * @return the averageRate
	 */
	public Float getAverageRate() {
		return averageRate;
	}
	/**
	 * @param averageRate the averageRate to set
	 */
	public void setAverageRate(Float averageRate) {
		this.averageRate = averageRate;
	}
	/**
	 * @return the totalReviewCnt
	 */
	public Integer getTotalReviewCnt() {
		return totalReviewCnt;
	}
	/**
	 * @param totalReviewCnt the totalReviewCnt to set
	 */
	public void setTotalReviewCnt(Integer totalReviewCnt) {
		this.totalReviewCnt = totalReviewCnt;
	}
	/**
	 * @return the totalOrderCnt
	 */
	public Integer getTotalOrderCnt() {
		return totalOrderCnt;
	}
	/**
	 * @param totalOrderCnt the totalOrderCnt to set
	 */
	public void setTotalOrderCnt(Integer totalOrderCnt) {
		this.totalOrderCnt = totalOrderCnt;
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

}