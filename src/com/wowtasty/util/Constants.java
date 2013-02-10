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
	static final public String KEY_CD_CITY_MAP = "009";
	static final public String KEY_CD_NAFLAG = "010";
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
	static final public String KEY_CD_SEMI_MONTH_TYPE = "401";
	
	/**Code Value */
	//Member Status
	static public final String CODE_MEMBER_STATUS_APPLIED = "1";
	static public final String CODE_MEMBER_STATUS_CONFIRMED = "2";
	static public final String CODE_MEMBER_STATUS_TERMINATED = "9";
	
	//Order Status
	static public final String CODE_ORDER_STATUS_ORDERED = "01";
	static public final String CODE_ORDER_STATUS_PENDING = "05";
	static public final String CODE_ORDER_STATUS_CONFIRMED = "11";
	static public final String CODE_ORDER_STATUS_DECLINED = "71";
	static public final String CODE_ORDER_STATUS_COMPLETED = "81";
	static public final String CODE_ORDER_STATUS_CANCELED = "91";
	static public final String CODE_ORDER_STATUS_REFUNDED = "92";
	
	//Delivery Type
	static public final String CODE_DELIVERY_TYPE_DELIVERY = "1";
	static public final String CODE_DELIVERY_TYPE_TAKEOUT = "2";
	
	//Delivery Company Type
	static public final String CODE_DELIVERY_COM_TYPE_SELFDELIVERY = "1";
	static public final String CODE_DELIVERY_COM_TYPE_DELIVERYCOMPANY = "2";
	
	//Payment Type
	static public final String CODE_PAYMENT_TYPE_CASH = "3";
	
	//Restaurant Status
	static public final String CODE_STATUS_OPEN = "1";
	static public final String CODE_STATUS_CONFIRMED = "3";
	static public final String CODE_STATUS_APPLIED = "5";
	static public final String CODE_STATUS_CLOSED = "7";
	static public final String CODE_STATUS_DECLINED = "8";
	static public final String CODE_STATUS_TERMINATED = "9";
	
	//NAFlag
	static public final String CODE_NAFLAG_NA = "1";
	static public final String CODE_NAFLAG_AVAILABLE = "0";
	
	//Bill Company Type
	static public final String CODE_BILL_COM_TYPE_RESTAURANT = "1";
	static public final String CODE_BILL_COM_TYPE_DELIVERYCOMPANY = "2";
	
	//ROLE
	static public final String CODE_ROLE_NONMEMBER = "99";
	static public final int CODE_ROLE_REST_MAX = 20;
	
	/**Mail Contents key */
	static public final Integer KEY_CONTENTS_SIGNUP_MEM = 1;
	static public final Integer KEY_CONTENTS_SIGNUP_REST = 2;
	static public final Integer KEY_CONTENTS_PWD_CHANGE = 3;
	static public final Integer KEY_CONTENTS_ORDERED_MEM = 4;
	static public final Integer KEY_CONTENTS_ORDERED_REST = 5;
	static public final Integer KEY_CONTENTS_CONFIRMED_MEM = 6;
	static public final Integer KEY_CONTENTS_CONFIRMED_REST = 7;
	static public final Integer KEY_CONTENTS_DECLINED_MEM = 8;
	static public final Integer KEY_CONTENTS_DECLINED_REST = 9;
	static public final Integer KEY_CONTENTS_CANCELED_MEM = 10;
	static public final Integer KEY_CONTENTS_CANCELED_REST = 11;
	static public final Integer KEY_CONTENTS_SIGNUP_CONFIRMED = 12;
	
	/**Session Key*/
	static public final String KEY_SESSION_USER = "KEY_SESSION_USER";
	static public final String KEY_SESSION_USER_REST_LIST = "KEY_SESSION_USER_REST_LIST";
	static public final String KEY_SESSION_CODE_LIST = "KEY_SESSION_CODE_LIST";
	static public final String KEY_SESSION_ACTIONAUTH_LIST = "KEY_SESSION_ACTIONAUTH_LIST";
	static public final String KEY_SESSION_CONFIG_PROPERTIES = "KEY_SESSION_CONFIG_PROPERTIES";
	
	/**Map Key*/
	static public final String KEY_ORDER_MASTER = "KEY_ORDER_MASTER";
	static public final String KEY_ORDER_RESTAURANT = "KEY_ORDER_RESTAURANT";
	static public final String KEY_ORDER_MENU = "KEY_ORDER_MENU";
	static public final String KEY_ORDER_MENUOPTION = "KEY_ORDER_MENUOPTION";
	
	static public final String KEY_BILL_MASTER = "KEY_BILL_MASTER";
	static public final String KEY_BILL_DETAIL = "KEY_BILL_DETAIL";
	
	/**Item Name*/
	static public final String NAME_FOOD_TAX = "Food Tax";
	static public final String NAME_DELIVERY_FEE = "Delivery Fee";
	static public final String NAME_DELIVERY_TAX = "Delivery Tax";
	static public final String NAME_SUBTOTAL = "Sub Total";
	static public final String NAME_TIP = "Tip";
	static public final String NAME_TOTAL = "Total";
	
	/**Common Constants*/
	static public final String BALANCE_TYPE_AVAILABLE = "A";
	static public final String BALANCE_TYPE_BALANCE = "B";
	static public final String BALANCE_TYPE_NA = "N";
	static public final String REPORT_TYPE_RESTAURANT = "RESTAURANT";
	static public final String REPORT_TYPE_DAY = "DAY";
	static public final String REPORT_TYPE_DATE = "DATE";
	static public final String REPORT_TYPE_HOUR = "HOUR";
	static public final String REPORT_TYPE_DELIVERY = "DELIVERY";
	static public final String CONSTANT_5DELIVERY_ID = "D00001";
	
}
