package com.wowtasty.util;

import java.sql.Date;
import java.sql.Time;
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
	 * @param value: String(yyyy-MM-dd HH:mm:ss)
	 * @return Timestamp 
	 */
	public static Timestamp convertString2date(String value) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN); 
	    try {  
			if (value == null || "".equals(value)) {
				return new Timestamp(sdf.parse("1000-01-01 00:00:00").getTime());
			}
	        return new Timestamp(sdf.parse(value).getTime());
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertString2date occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert String to Timestamp
	 * @param value: Timestamp String
	 * @param type: Timestamp format
	 * @return Timestamp 
	 */
	public static Timestamp convertString2date(String value, String type) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(type); 
	    try {  
			if (value == null || "".equals(value)) {
				return new Timestamp(sdf.parse("1000-01-01 00:00:00").getTime());
			}
	        return new Timestamp(sdf.parse(value).getTime());
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertString2date occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert String(HH:mm) to Time
	 * @return Time 
	 */
	public static Time convertString2Time(String value) { 
	    SimpleDateFormat sdf = new SimpleDateFormat(TIME_PATTERN);
	    try {  
			if (value == null || "".equals(value)) {
				return new Time(sdf.parse("00:00").getTime());
			}
	        return new Time(sdf.parse(value).getTime());
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertString2Time occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert Date String to Date
	 * @param value : Date String
	 * @param type : Date type 
	 * @return java.sql.Date 
	 */
	public static Date convertString2day(String value, String type) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(type); 
	    try {  
			if (value == null || "".equals(value)) {
				return new Date(sdf.parse("01/01/1000").getTime());
			}
	        return new Date(sdf.parse(value).getTime());
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertString2day occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert Date to String with Date format type
	 * @param value : java.sql.Date
	 * @param type : Date type 
	 * @return String Date
	 */
	public static String convertDay2String(Date value, String type) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(type); 
	    try {  
			if (value == null) {
				return "";
			}
	        return sdf.format(value);
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertString2day occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert Time to String(HH:mm)
	 * @return String 
	 */
	public static String convertTime2String(Time value) {
		if (value == null) {
			return "00:00";
		}
	    SimpleDateFormat sdf = new SimpleDateFormat(TIME_PATTERN);  
	    try {  
	        return sdf.format(value);
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertTime2String occurs error:" + e);
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
	 * @param value : postal code without " "
	 * @return String : postal code with " "
	 */
	public static String setPostalCode(String value) {
		if (value == null || value.length() < 4) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		return sb.append(value.substring(0, 3)).append(" ").append(value.substring(3)).toString();
	}
}
