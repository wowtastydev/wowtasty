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
import com.wowtasty.mybatis.dao.BillMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.MemberRestaurantVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.vo.BillingListConditionVO;
import com.wowtasty.vo.BillingListVO;


/**
 * @author Hak C.
 *
 */
public class BillingListAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(BillingListAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> semiMonthTypeList = new ArrayList<CodeMasterVO>();
	
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
	
	/** Search Condition */
	private BillingListConditionVO condition = new BillingListConditionVO();
	
	/** Searched List */
	private List<BillingListVO> list = new ArrayList<BillingListVO>();
	
	/** Parameter */
	private String selectedID = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);

		// set up dropdown menu from codes
		semiMonthTypeList = codeMap.get(Constants.KEY_CD_SEMI_MONTH_TYPE);
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		userRestList = (List<MemberRestaurantVO>)httpSession.getAttribute(Constants.KEY_SESSION_USER_REST_LIST);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Billing List page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");
		
		BillMasterDao dao = new BillMasterDao();
		list = dao.select(condition);

		logger.info("<---Init end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate Billing List page for restaurant users
	 * @return SUCCESS
	 */
	public String initRest() throws Exception {
		logger.info("<---initRest start --->");
		
		BillMasterDao dao = new BillMasterDao();
		//Set own restaurant list
		int size = userRestList.size();
		for (int i = 0; i < size; i++) {
			condition.getRestaurantList().add(userRestList.get(i).getRestaurantID());
		}
		if (size > 0) {
			// Only search when member has a restaurant list
			list = dao.select(condition);
		}

		logger.info("<---initRest end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Billing List
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---Execute start --->");
		
		BillMasterDao dao = new BillMasterDao();
		list = dao.select(condition);
		
		logger.info("<---Execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Billing List for restaurant users
	 * @return SUCCESS
	 */
	public String searchRest() throws Exception {
		logger.info("<---searchRest start --->");
		
		BillMasterDao dao = new BillMasterDao();
		//Set own restaurant list
		int size = userRestList.size();
		for (int i = 0; i < size; i++) {
			condition.getRestaurantList().add(userRestList.get(i).getRestaurantID());
		}
		if (size > 0) {
			// Only search when member has a restaurant list
			list = dao.select(condition);
		}

		logger.info("<---searchRest end --->");
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
	 * @return the condition
	 */
	public BillingListConditionVO getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(BillingListConditionVO condition) {
		this.condition = condition;
	}

	/**
	 * @return the list
	 */
	public List<BillingListVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<BillingListVO> list) {
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
	 * @return the semiMonthTypeList
	 */
	public List<CodeMasterVO> getSemiMonthTypeList() {
		return semiMonthTypeList;
	}

	/**
	 * @return the userRestList
	 */
	public List<MemberRestaurantVO> getUserRestList() {
		return userRestList;
	}

	/**
	 * @param userRestList the userRestList to set
	 */
	public void setUserRestList(List<MemberRestaurantVO> userRestList) {
		this.userRestList = userRestList;
	}
}
