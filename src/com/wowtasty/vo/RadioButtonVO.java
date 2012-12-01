package com.wowtasty.vo;

/**
 * @author Hak C.
 *
 */
public class RadioButtonVO {
	
	/** radio button key, name*/
	private String key;
	private String name;
	
	public RadioButtonVO (String key, String name) {
		this.key = key;
		this.name = name;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	
}