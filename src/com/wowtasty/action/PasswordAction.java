package com.wowtasty.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.MemberMasterDao;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.EncryptUtil;

/**
 * @author Hak C.
 *
 */
public class PasswordAction extends ActionSupport implements Preparable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(PasswordAction.class);
	
	/** user information */
	private MemberMasterVO uservo = new MemberMasterVO();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name
	private String headTitle = "Change password";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Change password";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Change password";
	
	/** Password information */
	private String currentPasswordStr = "";
	private String newPasswordStr = "";
	private String confirmPasswordStr = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
	
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	public String init() throws Exception {
		logger.debug("init start --->");
		
		logger.debug("init end --->");
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		logger.debug("execute start --->");
		
		String encryptedPassword = "";
		
		// Current Password Encryption
		EncryptUtil en = new EncryptUtil();
		if (en.encrypt(currentPasswordStr)) {
			encryptedPassword = en.getPassword();

			// Match password with user data
			if (!encryptedPassword.equals(uservo.getPassword())) {
				// Password is not matched
				addFieldError("currentPasswordStr", getText("E0005", new String[]{"Current Passwords"}));
				return INPUT;
			}
			
			// New Password Encryption
			if (en.encrypt(newPasswordStr)) {
				encryptedPassword = en.getPassword();
				// Update Session
				uservo.setPassword(encryptedPassword);
				
				// Update DB
				MemberMasterDao dao = new MemberMasterDao();
				dao.update(uservo);
				
				addActionMessage("Changed password sucessfully");
			} else {
				addActionMessage("Encryption sucessfully");
			}
		} else {
			return INPUT;
		}
		
		logger.debug("execute end --->");
		return SUCCESS;
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

	/**
	 * @return the currentPasswordStr
	 */
	public String getCurrentPasswordStr() {
		return currentPasswordStr;
	}

	/**
	 * @param currentPasswordStr the currentPasswordStr to set
	 */
	public void setCurrentPasswordStr(String currentPasswordStr) {
		this.currentPasswordStr = currentPasswordStr;
	}

	/**
	 * @return the newPasswordStr
	 */
	public String getNewPasswordStr() {
		return newPasswordStr;
	}

	/**
	 * @param newPasswordStr the newPasswordStr to set
	 */
	public void setNewPasswordStr(String newPasswordStr) {
		this.newPasswordStr = newPasswordStr;
	}

	/**
	 * @return the confirmPasswordStr
	 */
	public String getConfirmPasswordStr() {
		return confirmPasswordStr;
	}

	/**
	 * @param confirmPasswordStr the confirmPasswordStr to set
	 */
	public void setConfirmPasswordStr(String confirmPasswordStr) {
		this.confirmPasswordStr = confirmPasswordStr;
	}
	
	
}
