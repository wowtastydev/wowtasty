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
import com.wowtasty.vo.RestaurantSignupListConditionVO;
import com.wowtasty.vo.RestaurantSignupListVO;


/**
 * @author Hak C.
 *
 */
public class RestaurantSignupListAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantSignupListAction.class);
	
	/** dropdown box list */
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
	
	/** Search Condition */
	private RestaurantSignupListConditionVO condition = new RestaurantSignupListConditionVO();
	
	/** Searched List */
	private List<RestaurantSignupListVO> list = new ArrayList<RestaurantSignupListVO>();
	
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
		cityList = codeMap.get(Constants.KEY_CD_CITY);
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Restaurant Signup List page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		list = dao.selectSignupList(condition);

		logger.info("<---Init end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Restaurant Signup List
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---Execute start --->");
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		list = dao.selectSignupList(condition);
		
		logger.info("<---Execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Change Status of restaurant: Comfirmed/Terminate
	 * @return SUCCESS
	 */
	public String changeStatus() throws Exception {
		logger.info("<---changeStatus start --->");
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		String naFlag = Constants.CODE_NAFLAG_AVAILABLE;
		String message = "";
		// Set up naFlag to N/A when declined
		if (Constants.CODE_STATUS_DECLINED.equals(selectedStatus)) {
			// Declined
			naFlag = Constants.CODE_NAFLAG_NA;
			message = "Signup request has been declined successfully. You can find status on Restaurant page";
		} else {
			message = "Signup request has been confirmed successfully. You can find status on Restaurant page";
		}
		
		// Get email contents, subject for member
		ContentsTextDao cdao = new ContentsTextDao();
		ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_SIGNUP_CONFIRMED);
		
		// Set restaurant's name and order detail into email contents
		String contents = cvo.getContents();
		String tempContents = "";
		MailSender sender = new MailSender();
		
		int size = list.size();
		RestaurantMasterVO rmvo = new RestaurantMasterVO();
		rmvo.setUpdateID(uservo.getMemberID());
		for (int i = 0; i < size; i++) {
			// Change status only checked row
			if (list.get(i).isChk()) {
				rmvo.setRestaurantID(list.get(i).getRestaurantID());
				rmvo.setStatus(selectedStatus);
				rmvo.setNaFlag(naFlag);
				dao.updateStatus(rmvo);
				
				if (!Constants.CODE_STATUS_DECLINED.equals(selectedStatus)) {
					// In case of Confirm, send email
					tempContents = new String(contents);
					tempContents = tempContents.replace("<PARAM_RESTAURANT_NAME>", list.get(i).getName());
					sender.sendEmail(list.get(i).getEmail(), cvo.getSubject(), tempContents);
				}
			}
		}
		
		// Reload list
		list = dao.selectSignupList(condition);
		
		addActionMessage(message);
		
		if (!Constants.CODE_STATUS_DECLINED.equals(selectedStatus)) {
			// In case of Confirm, add message about sending email
			addActionMessage("Email has been sent to the restaurant successfully");
		}
		logger.info("<---changeStatus end --->");
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
	 * @return the condition
	 */
	public RestaurantSignupListConditionVO getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(RestaurantSignupListConditionVO condition) {
		this.condition = condition;
	}

	/**
	 * @return the list
	 */
	public List<RestaurantSignupListVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<RestaurantSignupListVO> list) {
		this.list = list;
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
}
