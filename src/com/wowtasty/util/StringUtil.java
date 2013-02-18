package com.wowtasty.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

/**
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class StringUtil {

    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(StringUtil.class);

    /**
     * substring by limit
     *
     * @param src source string
     * @param limit limit of result string
     * @return
     */
    public static String substringLimit(String src, int limit) {
        return substringLimit(src, limit, "...");
    }

    /**
     * substring by limit and attach mark
     *
     * @param src source string
     * @param limit limit of result string
     * @param mark mark string to attach the end of result
     * @return
     */
    public static String substringLimit(String src, int limit, String mark) {
        String str = src;
        try {
            if (!str.isEmpty() && str.length() > limit) {
                str = str.substring(0, limit) + mark;
            }
        } catch (Exception e) {
            logger.error("!!!!!StringUtil substringLimit occurs error:" + e);
            throw new RuntimeException(e);
        }
        return str;
    }
    
	/**
	 * Convert String to Timestamp with Timestamp format type
	 * 
	 * @param value: Timestamp String
	 * @param type: Timestamp format
	 * @return Timestamp 
	 */
	public static Timestamp convertString2Timestamp(String value, String type) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(type); 
	    try {  
			if (value == null || "".equals(value)) {
				return new Timestamp(sdf.parse("1000-01-01 00:00:00").getTime());
			}
	        return new Timestamp(sdf.parse(value).getTime());
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringUtil convertString2date occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert String to Time with Time format type
	 * 
	 * @param value : Time
	 * @param type : Time format type 
	 * @return Time 
	 */
	public static Time convertString2Time(String value, String type) {
	    SimpleDateFormat sdf = new SimpleDateFormat(type);
	    try {  
			if (value == null || "".equals(value)) {
				return new Time(sdf.parse("00:00").getTime());
			}
	        return new Time(sdf.parse(value).getTime());
	    } catch (Exception e) {  
	    	logger.error("!!!!!StringUtil convertString2Time occurs error:" + e);
        	throw new RuntimeException(e);
	    }  
	}
	
	/**
	 * Convert Date to String with Date format type
	 * 
	 * @param value : java.sql.Date
	 * @param type : Date format type 
	 * @return String Date
	 */
	public static String convertDate2String(Date value, String type) {  
		if (value == null) {
			return "";
		}
		return (new SimpleDateFormat(type)).format(value);
	}
	
	/**
	 * Convert Timestamp to String with Timestamp format type
	 * 
	 * @param value : Timestamp 
	 * @param type : Timestamp format type
	 * @return String 
	 */
	public static String convertTimestamp2String(Timestamp value, String type) {
		if (value == null) {
			return "";
		}
		return (new SimpleDateFormat(type)).format(value);
	}

	/**
	 * Put a space in the middle of postal code
	 * 
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
