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
import com.wowtasty.mybatis.dao.ContentsTextDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.ContentsTextVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.MailSender;
import com.wowtasty.util.SessionUtil;


/**
 * @author Hak C.
 *
 */
public class RestaurantSignupAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantSignupAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> cuisineTypeList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> restaurantTypeList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> provinceList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> restaurantStatusList = new ArrayList<CodeMasterVO>();
	
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
	
	/** Restaurant VO */
	private RestaurantMasterVO vo = new RestaurantMasterVO();
	
	/** Parameter */
	private String selectedID = "";
	private String selectedStatus = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		// set up dropdown menu from codes
		cuisineTypeList = codeMap.get(Constants.KEY_CD_CUISINE_TYPE);
		restaurantTypeList = codeMap.get(Constants.KEY_CD_RESTAURANT_TYPE);
		provinceList = codeMap.get(Constants.KEY_CD_PROVINCE);
		cityList = codeMap.get(Constants.KEY_CD_CITY);
		restaurantStatusList = codeMap.get(Constants.KEY_CD_RESTAURANT_STATUS);

		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Restaurant Signup Detail page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		vo = dao.selectByID(selectedID);

		logger.info("<---Init end --->");
		return SUCCESS;
	}
	
	/**
	 * Change Status of restaurant: Comfirmed/Terminate
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---Execute start --->");
		
		RestaurantMasterDao dao = new RestaurantMasterDao();

		vo.setUpdateID(uservo.getMemberID());
		vo.setStatus(selectedStatus);
		// Set up naFlag to N/A when declined
		String message = "";
		if (Constants.CODE_STATUS_DECLINED.equals(selectedStatus)) {
			// Declined
			vo.setNaFlag(Constants.CODE_NAFLAG_NA);
			message = "Signup request has been declined successfully. You can find status on Restaurant page";
		} else {
			// Confirmed
			vo.setNaFlag(Constants.CODE_NAFLAG_AVAILABLE);
			message = "Signup request has been confirmed successfully. You can find status on Restaurant page";
		}
		// Update status
		dao.updateStatus(vo);
		addActionMessage(message);
		
		if (!Constants.CODE_STATUS_DECLINED.equals(selectedStatus)) {
			// Confirmed
			// Send email -> Send Email to Restaurant
			// Get contents, subject for member
			ContentsTextDao cdao = new ContentsTextDao();
			ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_SIGNUP_CONFIRMED);
			
			// Set restaurant's name and order detail into email contents
			String contents = cvo.getContents();
			contents = contents.replace("<PARAM_RESTAURANT_NAME>", vo.getName());
			
			// Set up the mail contents for restaurant
			MailSender sender = new MailSender();
			sender.sendEmail(vo.getEmail1(), cvo.getSubject(), contents);
			
			addActionMessage("Email has been sent to the restaurant successfully");
		}		
		// Reload Restaurant Master
		vo = dao.selectByID(vo.getRestaurantID());
		
		logger.info("<---Execute end --->");
		return SUCCESS;
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
	 * @return the cuisineTypeList
	 */
	public List<CodeMasterVO> getCuisineTypeList() {
		return cuisineTypeList;
	}

	/**
	 * @return the restaurantTypeList
	 */
	public List<CodeMasterVO> getRestaurantTypeList() {
		return restaurantTypeList;
	}

	/**
	 * @return the provinceList
	 */
	public List<CodeMasterVO> getProvinceList() {
		return provinceList;
	}

	/**
	 * @return the selectedID
	 */
	public String getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(String selectedID) {
		this.selectedID = selectedID;
	}

	/**
	 * @return the selectedStatus
	 */
	public String getSelectedStatus() {
		return selectedStatus;
	}

	/**
	 * @param selectedStatus the selectedStatus to set
	 */
	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	/**
	 * @return the vo
	 */
	public RestaurantMasterVO getVo() {
		return vo;
	}

	/**
	 * @param vo the vo to set
	 */
	public void setVo(RestaurantMasterVO vo) {
		this.vo = vo;
	}

	/**
	 * @return the restaurantStatusList
	 */
	public List<CodeMasterVO> getRestaurantStatusList() {
		return restaurantStatusList;
	}
}
