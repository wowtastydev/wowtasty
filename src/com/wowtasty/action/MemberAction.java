package com.wowtasty.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.MemberMasterDao;
import com.wowtasty.mybatis.dao.MemberRestaurantDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.MemberRestaurantVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.util.CodeUtil;
import com.wowtasty.util.Constants;
import com.wowtasty.util.EncryptUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.ValidationUtil;

/**
 * @author Hak C.
 *
 */
public class MemberAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Integer MAX_LEN = 100;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(MemberAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> provinceList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> roleList = new ArrayList<CodeMasterVO>();
	private List<RestaurantMasterVO> restaurantList = new ArrayList<RestaurantMasterVO>();
	
	/** codemaster map */
	private Map<String, List<CodeMasterVO>> codeMap = new HashMap<String, List<CodeMasterVO>>();
	
	/** user information */
	private MemberMasterVO uservo = new MemberMasterVO();
	private List<MemberRestaurantVO> userRestList = new ArrayList<MemberRestaurantVO>();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name;at FoodDelivery WowTasty
	private String headTitle = "FoodDelivery WowStaty";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Keywords FoodDelivery,WowStaty,Vancouver";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Description FoodDelivery,WowStaty,Vancouver";
	
	/** Member_Master Table Columns */
	private MemberMasterVO mvo = new MemberMasterVO();
	private String newPasswordStr = "";
	private String memberPasswordStr = "";
	private String confirmPasswordStr = "";
	private String selectedMemberID = "";
	// In case of changing email, true
	private boolean emailChangeFlag = false;
	// In case of resseting password, true
	private boolean pwdResetFlag = false;
	private List<MemberRestaurantVO> memberRestaurantList = new ArrayList<MemberRestaurantVO>();
	
	// jsp accordion active index
	private Integer active = 0;
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);

		// set up dropdown menu from codes
		provinceList = codeMap.get(Constants.KEY_CD_PROVINCE);
		cityList = codeMap.get(Constants.KEY_CD_CITY);
		List<CodeMasterVO> orgList = codeMap.get(Constants.KEY_CD_ROLE);
		
		// set up restaurant list
		RestaurantMasterDao dao = new RestaurantMasterDao();
		restaurantList = dao.selectAvailable();
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		userRestList = (List<MemberRestaurantVO>)httpSession.getAttribute(Constants.KEY_SESSION_USER_REST_LIST);
		
		// set up roleList with only same or lower level of user's role
		roleList = CodeUtil.copyCode(orgList);
		int size = roleList.size();
		for (int i = 0; i < size; i++) {
			if (Integer.parseInt(roleList.get(i).getCode()) < Integer.parseInt(uservo.getAuth())) {
				// In case of the same or lower level of user's role
				roleList.remove(i--);
				size--;
			}
		}
		logger.info("<---prepare end --->");
	}
	
	/**
	 * Initiate MemberAdd page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");

		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate MemberEdit page
	 * @return SUCCESS
	 */
	public String initEdit() throws Exception {
		logger.info("<---initEdit start --->");
		
		// Get member master data
		MemberMasterDao dao = new MemberMasterDao();
		mvo = dao.selectByID(selectedMemberID);
		
		// Get member restaurant data
		MemberRestaurantDao mrdao = new MemberRestaurantDao();
		memberRestaurantList = mrdao.selectByID(selectedMemberID);

		logger.info("<---initEdit end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate MemberEdit page for restaurant users
	 * @return SUCCESS
	 */
	public String initEditRest() throws Exception {
		logger.info("<---initEditRest start --->");
		
		// Get member master data from current user info
		mvo = uservo;
		
		// Get member restaurant data from current user info
		memberRestaurantList = userRestList;

		logger.info("<---initEditRest end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert member data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		
		// Check Validation
		boolean hasError = checkValidation("INSERT");
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		//Take max memberID
		MemberMasterDao dao = new MemberMasterDao();
		//Set memberID,status
		mvo.setMemberID(dao.selectMaxID());
		mvo.setStatus(Constants.CODE_MEMBER_STATUS_CONFIRMED);
		
		//Insert MemberMasterdata
		dao = new MemberMasterDao();
		int returnCnt = dao.insert(mvo);
		if (returnCnt > 0) {
			addActionMessage("Member infomation has been inserted successfully");
			// Clear Member vo
			mvo = new MemberMasterVO();
		}
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Edit member data
	 * @return SUCCESS
	 */
	public String save() throws Exception {
		logger.info("<---save start --->");
		
		// Check Validation
		boolean hasError = checkValidation("UPDATE");

		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		// If new password inputted, change password
		if (!"".equals(newPasswordStr.trim())) {
			EncryptUtil en = new EncryptUtil();
			en.encrypt(newPasswordStr);
			mvo.setPassword(en.getPassword());
		}
		
		//Update MemberMasterdata
		MemberMasterDao dao = new MemberMasterDao();
		int returnCnt = dao.update(mvo);
		if (returnCnt > 0) {
			addActionMessage("Member infomation has been updated successfully");
			// Reload MemberMasterdata because postal code will be formatted.
			mvo = dao.selectByID(mvo.getMemberID());
			MemberRestaurantDao mrdao = new MemberRestaurantDao();
			memberRestaurantList = mrdao.selectByID(mvo.getMemberID());
		}
		//Clear Checkbox
		emailChangeFlag = false;
		pwdResetFlag = false;
		
		logger.info("<---save end --->");
		return SUCCESS;
	}
	
	/**
	 * save MemberRestaurant
	 * @return SUCCESS
	 */
	public String saveMemberRestaurant() throws Exception {
		logger.info("<---saveMemberRestaurant start --->");
		
		// Save Member Restaurant
		Map<String, String> hasRestMap = new HashMap<String, String>();
		int size = memberRestaurantList.size();
		MemberRestaurantDao mrdao = new MemberRestaurantDao();
		for (int i = 0; i < size; i++) {
			if (memberRestaurantList.get(i) == null || hasRestMap.containsKey(memberRestaurantList.get(i).getRestaurantID())) {
				// Delete the removed or duplicated restaurant
				memberRestaurantList.remove(i--);
				size--;
			} else {
				hasRestMap.put(memberRestaurantList.get(i).getRestaurantID(), memberRestaurantList.get(i).getRestaurantID());
			}
		}
		
		// Delete all and insert all
		mrdao.updateAll(memberRestaurantList, mvo.getMemberID());
		
		addActionMessage("Member restaurant list has been updated successfully");
		// Reload 
		MemberMasterDao dao = new MemberMasterDao();
		mvo = dao.selectByID(mvo.getMemberID());
		memberRestaurantList = mrdao.selectByID(mvo.getMemberID());
		
		// set Option accordion open
		active = 1;
		
		logger.info("<---saveMemberRestaurant end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check
	 * @param type : INSERT, UPDATE
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidation(String type) {
		
		boolean hasError = false;
		MemberMasterDao dao = new MemberMasterDao();
		MemberMasterVO vo = new MemberMasterVO();
		EncryptUtil en = new EncryptUtil();
		
		// Email Validation Check
		// Email blank
		if (ValidationUtil.isBlank(mvo.getEmail())) {
			addFieldError("mvo.email", getText("E0001_1", new String[]{"Email"}));
			hasError = true;
		} else {
			// Valid type
			if (!ValidationUtil.isEmail(mvo.getEmail())) {
				addFieldError("mvo.email", getText("E0003_1", new String[]{"Email"}));
				hasError = true;
			} else {
				//Email exists
				if ("INSERT".equals(type) || emailChangeFlag) {
					vo = dao.selectByEmail(mvo.getEmail());
					
					if ("INSERT".equals(type) && !"".equals(vo.getEmail())) {
						//Insert case : The inputted email exists
						addFieldError("mvo.email", getText("E0004", new String[]{"Email"}));
						hasError = true;
					}
					if ("UPDATE".equals(type) && !"".equals(vo.getEmail()) && !mvo.getMemberID().equals(vo.getMemberID())) {
						//Update case : The inputted email exists with different ID
						addFieldError("mvo.email", getText("E0004", new String[]{"Email"}));
						hasError = true;
					}
				}
			}
		}
		
		// Member Type Validation Check
		if (ValidationUtil.isBlank(mvo.getAuth())) {
			addFieldError("mvo.auth", getText("E0001_1", new String[]{"Member Type"}));
			hasError = true;
		}
		
		// Password Validation Check
		// Password Reset Case : No check
		if (!pwdResetFlag) {
			//Password/Current Password
			if (ValidationUtil.isBlank(memberPasswordStr)) {
				addFieldError("memberPasswordStr", getText("E0001_1", new String[]{"Password"}));
				hasError = true;
			} else {
				// Valid type
				if (!ValidationUtil.isPassword(memberPasswordStr)) {
					addFieldError("memberPasswordStr", getText("E0003_1", new String[]{"Password"}));
					hasError = true;
				} else {
					//Password Encryption
					en.encrypt(memberPasswordStr);
					mvo.setPassword(en.getPassword());
					if ("UPDATE".equals(type)) {
						//Compare inputted password and db password
						//Select db data
						dao = new MemberMasterDao();
						vo = dao.selectByID(mvo.getMemberID());
						
						//Match password with db password
						if (!vo.getPassword().equals(mvo.getPassword())) {
							// Password is not matched
							addFieldError("memberPasswordStr", getText("E0005", new String[]{"Password and DB Password"}));
							hasError = true;
						}
					}
				}
			}
			
			//New Password
			if ("UPDATE".equals(type) && !ValidationUtil.isBlank(newPasswordStr)) {
				// Type check
				if (!ValidationUtil.isPassword(newPasswordStr)) {
					addFieldError("newPasswordStr", getText("E0003_1", new String[]{"New Password"}));
					hasError = true;					
				}
				// Compare new pwd and confirm pwd
				if (!newPasswordStr.equals(confirmPasswordStr)) {
					addFieldError("confirmPasswordStr", getText("E0005", new String[]{"New Password and Confirm Password"}));
					hasError = true;						
				}
			}
			
			//Confirm Password
			if ("INSERT".equals(type)) {
				// Insert case
				// Requirement check
			    if (ValidationUtil.isBlank(confirmPasswordStr)) {
					addFieldError("confirmPasswordStr", getText("E0001_1", new String[]{"Confirm Password"}));
					hasError = true;			    	
			    } else {
			    	// Compare member pwd and confirm pwd
					if (!memberPasswordStr.equals(confirmPasswordStr)) {
						addFieldError("confirmPasswordStr", getText("E0005", new String[]{"Password and Confirm Password"}));
						hasError = true;
					}
			    }
			} else if ("UPDATE".equals(type) && !ValidationUtil.isBlank(confirmPasswordStr)) {
		    	// Compare member new pwd and confirm pwd
				if (!newPasswordStr.equals(confirmPasswordStr)) {
					addFieldError("confirmPasswordStr", getText("E0005", new String[]{"New Password and Confirm Password"}));
					hasError = true;
				}
			}
		} else {
			//Reset Password by memberID
			en.encrypt(mvo.getMemberID());
			mvo.setPassword(en.getPassword());
		}
			
		//First Name
		if (ValidationUtil.isBlank(mvo.getFirstName())) {
			addFieldError("mvo.firstName", getText("E0001_1", new String[]{"First Name of Contact Address"}));
			hasError = true;
		}
		
		//Last Name
		if (ValidationUtil.isBlank(mvo.getLastName())) {
			addFieldError("mvo.lastName", getText("E0001_1", new String[]{"Last Name of Contact Address"}));
			hasError = true;
		}
		
		//Telephone
		if (!ValidationUtil.isBlank(mvo.getTelephone()) && !ValidationUtil.isTelephone(mvo.getTelephone())) {
			addFieldError("mvo.telephone", getText("E0003_1", new String[]{"Telephone Number of Contact Address"}));
			hasError = true;
		}
		
		//Suite Number
		if (!ValidationUtil.isBlank(mvo.getSuite()) && !ValidationUtil.isNumEng(mvo.getSuite())) {
			addFieldError("mvo.suite", getText("E0003_1", new String[]{"Suite Number of Contact Address"}));
			hasError = true;
		}
		
		//Postal Code
		if (!ValidationUtil.isBlank(mvo.getPostalCode()) && !ValidationUtil.isPostalCode(mvo.getPostalCode())) {
			addFieldError("mvo.postalCode", getText("E0003_1", new String[]{"Postal Code of Contact Address"}));
			hasError = true;
		}
		
		//Delivery Telephone
		if (!ValidationUtil.isBlank(mvo.getDelivTelephone()) && !ValidationUtil.isTelephone(mvo.getDelivTelephone())) {
			addFieldError("mvo.delivTelephone", getText("E0003_1", new String[]{"Telephone Number of Delivery Address"}));
			hasError = true;
		}
		
		//Delivery Suite Number
		if (!ValidationUtil.isBlank(mvo.getDelivSuite()) && !ValidationUtil.isNumEng(mvo.getDelivSuite())) {
			addFieldError("mvo.delivSuite", getText("E0003_1", new String[]{"Suite Number of Delivery Address"}));
			hasError = true;
		}
		
		//Delivery Buzzer Number
		if (!ValidationUtil.isBlank(mvo.getDelivBuzzer()) && !ValidationUtil.isNumEng(mvo.getDelivBuzzer())) {
			addFieldError("mvo.delivBuzzer", getText("E0003_1", new String[]{"Buzzer Number of Delivery Address"}));
			hasError = true;
		}
		
		//Delivery Postal Code
		if (!ValidationUtil.isBlank(mvo.getDelivPostalCode()) && !ValidationUtil.isPostalCode(mvo.getDelivPostalCode())) {
			addFieldError("mvo.delivPostalCode", getText("E0003_1", new String[]{"Postal Code of Delivery Address"}));
			hasError = true;
		}
		
		//Special Instruction
		if (!ValidationUtil.isBlank(mvo.getDelivInstruction()) && mvo.getDelivInstruction().trim().length() > MAX_LEN) {
			addFieldError("mvo.delivInstruction", getText("E0006", new String[]{"Delivery Instruction", MAX_LEN.toString()}));
			hasError = true;
		}
		
		return hasError;
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
	 * @return the cityList
	 */
	public List<CodeMasterVO> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<CodeMasterVO> cityList) {
		this.cityList = cityList;
	}

	/**
	 * @return the provinceList
	 */
	public List<CodeMasterVO> getProvinceList() {
		return provinceList;
	}

	/**
	 * @param provinceList the provinceList to set
	 */
	public void setProvinceList(List<CodeMasterVO> provinceList) {
		this.provinceList = provinceList;
	}

	/**
	 * @return the mvo
	 */
	public MemberMasterVO getMvo() {
		return mvo;
	}

	/**
	 * @param mvo the mvo to set
	 */
	public void setMvo(MemberMasterVO mvo) {
		this.mvo = mvo;
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
	 * @return the uservo
	 */
	public MemberMasterVO getUservo() {
		return uservo;
	}

	/**
	 * @param uservo the uservo to set
	 */
	public void setUservo(MemberMasterVO uservo) {
		this.uservo = uservo;
	}

	/**
	 * @return the selectedMemberID
	 */
	public String getSelectedMemberID() {
		return selectedMemberID;
	}

	/**
	 * @param selectedMemberID the selectedMemberID to set
	 */
	public void setSelectedMemberID(String selectedMemberID) {
		this.selectedMemberID = selectedMemberID;
	}

	/**
	 * @return the emailChangeFlag
	 */
	public boolean isEmailChangeFlag() {
		return emailChangeFlag;
	}

	/**
	 * @param emailChangeFlag the emailChangeFlag to set
	 */
	public void setEmailChangeFlag(boolean emailChangeFlag) {
		this.emailChangeFlag = emailChangeFlag;
	}

	/**
	 * @return the pwdResetFlag
	 */
	public boolean isPwdResetFlag() {
		return pwdResetFlag;
	}

	/**
	 * @param pwdResetFlag the pwdResetFlag to set
	 */
	public void setPwdResetFlag(boolean pwdResetFlag) {
		this.pwdResetFlag = pwdResetFlag;
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
	 * @return the roleList
	 */
	public List<CodeMasterVO> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<CodeMasterVO> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the restaurantList
	 */
	public List<RestaurantMasterVO> getRestaurantList() {
		return restaurantList;
	}

	/**
	 * @return the memberRestaurantList
	 */
	public List<MemberRestaurantVO> getMemberRestaurantList() {
		return memberRestaurantList;
	}

	/**
	 * @param memberRestaurantList the memberRestaurantList to set
	 */
	public void setMemberRestaurantList(
			List<MemberRestaurantVO> memberRestaurantList) {
		this.memberRestaurantList = memberRestaurantList;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}
}
