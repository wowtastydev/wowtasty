package com.wowtasty.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.validator.validators.EmailValidator;

/**
 * @author Hak C.
 *
 */
public class ValidationUtil {

	/**
	 * check if string is blank/null/trim blank
	 * @return boolean: true-blank, false-not blank 
	 */
	public static boolean isBlank(String value) {
		if (value == null || value.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * check if string is date type
	 * @param datetype String 
	 * @param datetype 'yyyy-MM-dd' or 'MM/dd/yyyy'
	 * @return boolean: true-blank, false-not blank 
	 */
	public static boolean isDate(String value, String type) {
		if (value == null || value.trim().length() == 0) {
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		Date parsedDate = null;

	    try {  
	    	parsedDate = sdf.parse(value);
	    	
	    	// If the date is not the same as input's value
	    	// ex) sdf.parse(11/31/2012) change into 12/01/2012 without exception
	    	if (!sdf.format(parsedDate).equals(value)) {
	    		return false;
	    	}

	        return true;  
	    } catch (ParseException e) {
	    	// If exception occurs, not date type
	    	return false;
	    } 
	}
	
	/**
	 * check if string is date type
	 * @param datetype String 
	 * @param datetype 'HH:mm' or 'HH/mm'
	 * @return boolean: true-blank, false-not blank 
	 */
	public static boolean isTime(String value, String type) {
		if (value == null || value.trim().length() == 0) {
			return true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		Time parsedTime = null;

	    try {  
	    	parsedTime = new Time(sdf.parse(value).getTime());
	    	
	    	// If the date is not the same as input's value
	    	// ex) sdf.parse(25:61) change into 02:01 without exception
	    	if (!sdf.format(parsedTime).equals(value)) {
	    		return false;
	    	}

	        return true;  
	    } catch (ParseException e) {
	    	// If exception occurs, not date type
	    	return false;
	    } 
	}

	/**
	 * check if string is valid email
	 * @param value email string
	 * @return boolean: true-valid, false-invalid 
	 */
	public static boolean isEmail(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		if(!value.matches(EmailValidator.emailAddressPattern)) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if string is valid password : More than 8 digits with alphabets or symbols
	 * @param value password string
	 * @return boolean: true-valid, false-invalid 
	 */
	public static boolean isPassword(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		if (value.length() < 8) {
			return false;
		}
		if(!value.matches("[a-zA-Z]+") && !value.matches("[!,@,#,$,%,^,&,*,?,_,~]+")) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if string is valid telephone number
	 * @param value telephone number string
	 * @return boolean: true-valid, false-invalid 
	 */
	public static boolean isTelephone(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		if(!value.matches("[0-9- ]+")) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if string is valid number
	 * @param value number string(-,float type including)
	 * @return boolean: true-valid, false-invalid 
	 */
	public static boolean isNum(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		if(!value.matches("[-]?[0-9]+(.[0-9]+)?")) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if string is valid number&english 
	 * @param value number&english string
	 * @return boolean: true-valid, false-invalid 
	 */
	public static boolean isNumEng(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		if(!value.matches("[a-zA-Z0-9 ]+")) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if string is valid Whole Number
	 * @param value integer string
	 * @return boolean: true-valid, false-invalid 
	 */
	public static boolean isWholeNum(String value) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		if(!value.matches("[0-9]+")) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if string is valid pattern
	 * @param value string to check
	 * @param pattern pattern to check 
	 * @return boolean: true-valid, false-invalid 
	 */
	public static boolean isValid(String value, String pattern) {
		if (value == null || value.trim().length() == 0) {
			return false;
		}
		if(!value.matches(pattern)) {
			return false;
		}
		return true;
	}
}
