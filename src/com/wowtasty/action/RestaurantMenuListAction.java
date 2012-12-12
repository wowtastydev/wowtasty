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
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.vo.RestaurantListConditionVO;


/**
 * @author Hak C.
 *
 */
public class RestaurantMenuListAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantMenuListAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> cuisineTypeList = new ArrayList<CodeMasterVO>();
	
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
	private RestaurantListConditionVO condition = new RestaurantListConditionVO();
	
	/** Searched List */
	private List<RestaurantMasterVO> list = new ArrayList<RestaurantMasterVO>();
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		// set up dropdown menu from codes
		cuisineTypeList = codeMap.get(Constants.KEY_CD_CUISINE_TYPE);
		
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
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		list = dao.selectAll();

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
		RestaurantMasterDao dao = new RestaurantMasterDao();
		
		list = dao.select(condition);
		
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
	 * @return the cuisineTypeList
	 */
	public List<CodeMasterVO> getCuisineTypeList() {
		return cuisineTypeList;
	}

	/**
	 * @param cuisineTypeList the cuisineTypeList to set
	 */
	public void setCuisineTypeList(List<CodeMasterVO> cuisineTypeList) {
		this.cuisineTypeList = cuisineTypeList;
	}

	/**
	 * @return the condition
	 */
	public RestaurantListConditionVO getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(RestaurantListConditionVO condition) {
		this.condition = condition;
	}

	/**
	 * @return the list
	 */
	public List<RestaurantMasterVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<RestaurantMasterVO> list) {
		this.list = list;
	}
}
