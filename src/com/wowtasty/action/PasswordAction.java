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
import com.wowtasty.util.ValidationUtil;

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
		logger.info("<---prepare start --->");
	
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---prepare end --->");
	}
	
	/**
	 * Initiate Change password page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.debug("init start --->");
		
		logger.debug("init end --->");
		return SUCCESS;
	}
	
	/**
	 * Change password
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.debug("execute start --->");
		
		// Check Validation
		boolean hasError = checkValidation();
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		// New Password Encryption
		EncryptUtil en = new EncryptUtil();
		en.encrypt(newPasswordStr);
		
		// Update Session
		uservo.setPassword(en.getPassword());
		
		// Update DB
		MemberMasterDao dao = new MemberMasterDao();
		dao.update(uservo);
		
		addActionMessage("Changed password sucessfully");
		
		logger.debug("execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidation() {
		
		boolean hasError = false;
		EncryptUtil en = new EncryptUtil();
		String encryptedPassword = "";
		
		//Current Password
		if (ValidationUtil.isBlank(currentPasswordStr)) {
			addFieldError("currentPasswordStr", getText("E0001_1", new String[]{"Current Password"}));
			hasError = true;
		} else {
			// Valid type
			if (!ValidationUtil.isPassword(currentPasswordStr)) {
				addFieldError("currentPasswordStr", getText("E0003_1", new String[]{"Current Password"}));
				hasError = true;
			} else {
				// Current Password Encryption
				en.encrypt(currentPasswordStr);
				encryptedPassword = en.getPassword();

				// Match password with user data
				if (!encryptedPassword.equals(uservo.getPassword())) {
					// Password is not matched
					addFieldError("currentPasswordStr", getText("E0005", new String[]{"Current Password and DB Password"}));
					hasError = true;
				}
			}
		}
		
		//New Password
		if (ValidationUtil.isBlank(newPasswordStr)) {
			addFieldError("newPasswordStr", getText("E0001_1", new String[]{"New Password"}));
			hasError = true;
		} else {
			// Type check
			if (!ValidationUtil.isPassword(newPasswordStr)) {
				addFieldError("newPasswordStr", getText("E0003_1", new String[]{"New Password"}));
				hasError = true;					
			}
		}
		
		//Confirm Password
	    if (ValidationUtil.isBlank(confirmPasswordStr)) {
			addFieldError("confirmPasswordStr", getText("E0001_1", new String[]{"Confirm Password"}));
			hasError = true;			    	
	    } else {
	    	// Compare member pwd and confirm pwd
			if (!newPasswordStr.equals(confirmPasswordStr)) {
				addFieldError("confirmPasswordStr", getText("E0005", new String[]{"New Password and Confirm Password"}));
				hasError = true;
			}
	    }
		
		return hasError;
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

	/**
	 * @return the uservo
	 */
	public MemberMasterVO getUservo() {
		return uservo;
	}
}
