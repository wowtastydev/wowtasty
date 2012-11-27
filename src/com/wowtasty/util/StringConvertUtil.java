package com.wowtasty.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

/**
 * @author Hak C.
 *
 */
public class StringConvertUtil {
	/** Logger */	
	private static Logger logger = Logger.getLogger(StringConvertUtil.class);
	
	static final public String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	static final public String DAY_PATTERN = "yyyy-MM-dd";
	static final public String TIME_PATTERN = "HH:mm";
	
	/**
	 * Convert String(yyyy-MM-dd HH:mm:ss) to Timestamp
	 * @return Timestamp 
	 */
	public static Timestamp convertString2date(String value) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);  
	    try {  
	    	Timestamp tempDate = new Timestamp(sdf.parse(value).getTime());
	        return tempDate;  
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertString2date occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert Timestamp to String(yyyy-MM-dd HH:mm:ss)
	 * @return String 
	 */
	public static String convertDate2String(Timestamp datetime) {
		if (datetime == null) {
			return "";
		}
		return (new SimpleDateFormat(DATE_PATTERN)).format(datetime);
	}

	/**
	 * Convert Timestamp to String(yyyy-MM-dd)
	 * @return String 
	 */
	public static String convertDate2Day(Timestamp datetime) {
		if (datetime == null) {
			return "";
		}
		return (new SimpleDateFormat(DAY_PATTERN)).format(datetime);
	}
	
	/**
	 * Convert Timestamp to String(HH:mm)
	 * @return String 
	 */
	public static String convertDate2Time(Timestamp datetime) {
		if (datetime == null) {
			return "";
		}
		return (new SimpleDateFormat(TIME_PATTERN)).format(datetime);
	}
	
	/**
	 * check if string is blank/null/trim blank
	 * @return boolean: true-blank, false-not blank 
	 */
	public static boolean isBlank(String value) {
		if (value == null) {
			return true;
		}
		if (value.trim().length() == 0) {
			return true;
		}
		return false;
	}

}
