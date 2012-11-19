package com.wowtasty.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;


import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.EncryptUtil;
import com.wowtasty.util.SessionUtil;

import com.wowtasty.mybatis.dao.CodeMasterDao;
import com.wowtasty.mybatis.dao.MemberMasterDao;




/**
 * @author Hak C.
 *
 */
public class LoginAction extends ActionSupport {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(LoginAction.class);
	
	/** code Map */
	private Map<String, List<CodeMasterVO>> codeMap = new HashMap<String, List<CodeMasterVO>>();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name;at FoodDelivery WowStaty
	private String headTitle = "FoodDelivery WowStaty";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Keywords FoodDelivery,WowStaty,Vancouver";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Description FoodDelivery,WowStaty,Vancouver";
	
	/** Member_Master Table Columns */
	private String memberID = "";
	private String memberEmail = "";
	private String memberPassword = "";
	private String memberPasswordStr = "";
	
	public String init() throws Exception {
		logger.debug("<---LoginAction init start --->");
		
		logger.debug("<---LoginAction init end --->");
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		logger.debug("<---LoginAction execute start --->");
		
		//Password Encryption
		EncryptUtil en = new EncryptUtil();
		if (en.encrypt(memberPasswordStr)) {
			memberPassword = en.getPassword();
			//Select db data by inputted email
			MemberMasterDao mdao = new MemberMasterDao();
			MemberMasterVO mvo = new MemberMasterVO();
			mvo = mdao.selectByEmail(memberEmail);

			if ("".equals(mvo.getMemberID())) {
				// Email is not exists
				addFieldError("memberEmail", getText("errors.0002", new String[]{"The Email"}));
				return INPUT;
			}
			
			//Match password with db data
			if (memberPassword.equals(mvo.getPassword())) {
				// Set user info to http session
				HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
				httpSession.setAttribute(Constants.KEY_SESSION_USER, mvo);
			} else {
				// Password is not matched
				addFieldError("memberPasswordStr", getText("errors.0005", new String[]{"Passwords"}));
				return INPUT;
			}
			
		} else {
			return INPUT;
		}
		
		logger.debug("<---LoginAction execute end --->");
		return SUCCESS;
	}
	
	/**
	 * @return the memberID
	 */
	public String getMemberID() {
		return memberID;
	}

	/**
	 * @param memberID the memberID to set
	 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	/**
	 * @return the memberEmail
	 */
	public String getMemberEmail() {
		return memberEmail;
	}

	/**
	 * @param memberEmail the memberEmail to set
	 */
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	/**
	 * @return the memberPassword
	 */
	public String getMemberPassword() {
		return memberPassword;
	}

	/**
	 * @param memberPassword the memberPassword to set
	 */
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	/**
	 * @return the memberPasswordStr
	 */
	public String getMemberPasswordStr() {
		return memberPasswordStr;
	}

	/**
	 * @param memberPasswordStr the memberPasswordStr to set
	 */
	public void setMemberPasswordStr(String memberPasswordStr) {
		this.memberPasswordStr = memberPasswordStr;
	}

	/**
	 * @return the headTitle
	 */
	public String getHeadTitle() {
		return headTitle;
	}

	/**
	 * @param headTitle the headTitle to set
	 */
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}

	/**
	 * @return the metaKeywords
	 */
	public String getMetaKeywords() {
		return metaKeywords;
	}

	/**
	 * @param metaKeywords the metaKeywords to set
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	/**
	 * @return the metaDescription
	 */
	public String getMetaDescription() {
		return metaDescription;
	}

	/**
	 * @param metaDescription the metaDescription to set
	 */
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	
}
