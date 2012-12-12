package com.wowtasty.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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
	 * Convert String(HH:mm) to Time
	 * @return Time 
	 */
	public static Time convertString2Time(String value) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(TIME_PATTERN);  
	    try {  
	        return new Time(sdf.parse(value).getTime());
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringConvertUtil convertString2Time occurs error:" + e);
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
	 * @param value : price
	 * @return String : ###,##0.00 type price
	 */
	public static String decimalFormat(Float value) {
		return new DecimalFormat("###,##0.00").format(value);
	}
}
