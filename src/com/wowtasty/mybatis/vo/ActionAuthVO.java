package com.wowtasty.mybatis.vo;

/**
 * @author Hak C.
 *
 */
public class ActionAuthVO {
	
	/** action_auth columns*/
	private String actionName = "";
	private String auth = "";
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return the auth
	 */
	public String getAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(String auth) {
		this.auth = auth;
	}
}