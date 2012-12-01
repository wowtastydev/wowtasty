package com.wowtasty.util;

public class Constants {

	/**Code Master Key*/
	static final public String KEY_CD_GROUPCD = "000";
	static final public String KEY_CD_PROVINCE = "001";
	static final public String KEY_CD_CITY = "002";
	static final public String KEY_CD_GENDER = "003";
	static final public String KEY_CD_YN = "004";
	static final public String KEY_CD_ZONE = "005";
	static final public String KEY_CD_ROLE = "006";
	static final public String KEY_CD_COMMISION_TYPE = "007";
	static final public String KEY_CD_DAY_TYPE = "008";
	static final public String KEY_CD_DELIVERY_TYPE = "101";
	static final public String KEY_CD_ORDER_TYPE = "102";
	static final public String KEY_CD_ORDER_STATUS = "103";
	static final public String KEY_CD_PAYMENT_TYPE = "104";
	static final public String KEY_CD_CREDITCARD_TYPE = "105";
	static final public String KEY_CD_PAYMENT_STATUS = "106";
	static final public String KEY_CD_DECLINEREASON_TYPE = "107";
	static final public String KEY_CD_CUISINE_TYPE = "108";
	static final public String KEY_CD_RESTAURANT_STATUS = "109";
	static final public String KEY_CD_MENU_STATUS = "110";
	static final public String KEY_CD_TAX_RATE = "111";
	static final public String KEY_CD_RESTAURANT_TYPE = "112";
	static final public String KEY_CD_MEMBER_STATUS = "201";
	static final public String KEY_CD_DELIVERYCOMPANY_TYPE = "301";
	
	/**Code Value */
	static public final String KEY_MEMBER_STATUS_APPLIED = "1";
	static public final String KEY_MEMBER_STATUS_CONFIRMED = "2";
	static public final String KEY_MEMBER_STATUS_TERMINATED = "9";
	
	static public final String KEY_ORDER_STATUS_ORDERED = "01";
	static public final String KEY_ORDER_STATUS_PENDING = "05";
	static public final String KEY_ORDER_STATUS_CONFIRMED = "11";
	static public final String KEY_ORDER_STATUS_DECLINED = "71";
	static public final String KEY_ORDER_STATUS_COMPLETED = "81";
	static public final String KEY_ORDER_STATUS_CANCELED = "91";
	static public final String KEY_ORDER_STATUS_REFUNDED = "92";
	
	static public final Integer KEY_CONTENTS_ORDERED_CUST = 1;
	static public final Integer KEY_CONTENTS_ORDERED_REST = 2;
	

	/**Session Key*/
	static public final String KEY_SESSION_USER = "KEY_SESSION_USER";
	static public final String KEY_SESSION_CODE_LIST = "KEY_SESSION_CODE_LIST";
	static public final String KEY_SESSION_ACTIONAUTH_LIST = "KEY_SESSION_ACTIONAUTH_LIST";
	static public final String KEY_SESSION_CONFIG_PROPERTIES = "KEY_SESSION_CONFIG_PROPERTIES";
	
	/**Action name to skip user information from session */
	static public final String[] ACTION_NAME_SKIP_USERCHECK = {"initLogin", "login"};
	
}
