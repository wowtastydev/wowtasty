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
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.EncryptUtil;
import com.wowtasty.util.SessionUtil;


/**
 * @author Hak C.
 *
 */
public class MemberAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(MemberAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> provinceList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	
	/** codemaster map */
	private Map<String, List<CodeMasterVO>> codeMap = new HashMap<String, List<CodeMasterVO>>();
	
	/** user information */
	private MemberMasterVO uservo = new MemberMasterVO();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name;at FoodDelivery WowTasty
	private String headTitle = "FoodDelivery WowStaty";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Keywords FoodDelivery,WowStaty,Vancouver";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Description FoodDelivery,WowStaty,Vancouver";
	
	/** Member_Master Table Columns */
	private MemberMasterVO mvo = new MemberMasterVO();
	private String memberID = "";
	private String memberEmail = "";
	private String memberPassword = "";
	private String memberPasswordStr = "";
	private String confirmPasswordStr = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		// set up dropdown menu from codes
		provinceList = codeMap.get(Constants.KEY_CD_PROVINCE);
		cityList = codeMap.get(Constants.KEY_CD_CITY);
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate MemberList, MemberAdd, MemberEdit page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");

		logger.info("<---Init end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert member data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---Execute start --->");
		//Check the inputted email exists
		MemberMasterDao dao = new MemberMasterDao();
		MemberMasterVO vo = new MemberMasterVO();
		vo = dao.selectByEmail(mvo.getEmail());
		if (!"".equals(vo.getEmail())) {
			//The inputted email exists
			addFieldError("mvo.email", getText("E0004", new String[]{"The Email"}));
			return INPUT;
		}
		
		//Password Encryption
		EncryptUtil en = new EncryptUtil();
		if (en.encrypt(memberPasswordStr)) {
			memberPassword = en.getPassword();
		}
		
		//Take max memberID
		dao = new MemberMasterDao();
		memberID = dao.selectMaxID();
		
		//Set memberID,status,password
		mvo.setMemberID(memberID);
		mvo.setStatus(Constants.KEY_MEMBER_STATUS_CONFIRMED);
		mvo.setPassword(memberPassword);
		
		//Insert MemberMasterdata
		dao = new MemberMasterDao();
		dao.insert(mvo);
		
		logger.info("<---Execute end --->");
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
}
