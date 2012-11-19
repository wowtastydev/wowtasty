package com.wowtasty.util;

public class Constants {

	/**Code Master Key*/
	static final public String KEY_CD_GROUPCD = "000";
	static final public String KEY_CD_PROVINCE = "001";
	static final public String KEY_CD_CITY = "002";
	static final public String KEY_CD_YN = "003";
	static final public String KEY_CD_GENDER = "004";
	static final public String KEY_CD_ROLE = "005";
	
	/**Code Value */
	static public final String KEY_MEMBER_STATUS_ACTIVE = "1";
	static public final String KEY_MEMBER_STATUS_APPLY = "8";
	static public final String KEY_MEMBER_STATUS_DELETE = "9";
	

	/**Session Key*/
	static public final String KEY_SESSION_USER = "KEY_SESSION_USER";
	static public final String KEY_SESSION_CODE_LIST = "KEY_SESSION_CODE_LIST";
	static public final String KEY_SESSION_CONFIG_PROPERTIES = "KEY_SESSION_CONFIG_PROPERTIES";
	
	/**Action name to skip user information from session */
	static public final String[] ACTION_NAME_SKIP_USERCHECK = {"initLogin", "login"};
	
}
