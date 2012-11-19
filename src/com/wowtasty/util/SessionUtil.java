package com.wowtasty.util;

import java.util.Map;
import java.util.HashMap;

/**
 * @author Hak C.
 *
 */
public class SessionUtil {
	
	/**Session for application*/
	private Map<String, Object> applicationSession = new HashMap<String, Object>();
	
	private SessionUtil() {}
	
	/**Create Single tone instance with inner class*/
	private static class SessionHolder {
		private final static SessionUtil instance = new SessionUtil();
	}
	
	/**
	 * @return SessionUtil : single tone instance
	 */
	public static SessionUtil getInstance() {
		return SessionHolder.instance;
	}
	/**
	 * Session for application: getter
	 * @param String key : session key
	 * @return Object : session value
	 */	
	public Object getApplicationAttribute(String key) {
		return applicationSession.get(key);
	}
	/**
	 * Session for application: putter
	 * @param String key : session key
	 * @param Object value : session value
	 */	
	public void setApplicationAttribute(String key, Object value) {
		applicationSession.put(key, value);
	}
	/**
	 * Session for application: clear value by key
	 * @param String key : session key
	 */	
	public void removeApplicationAttribute(String key) {
		applicationSession.put(key, null);
	}
	/**
	 * Session for application: clear all values
	 */	
	public void removeApplicationAll() {
		applicationSession.clear();
	}
}
