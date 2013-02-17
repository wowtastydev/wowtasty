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
import com.wowtasty.mybatis.vo.MemberRestaurantVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.vo.RestaurantListConditionVO;


/**
 * @author Hak C.
 *
 */
public class RestaurantListAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantListAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cuisineTypeList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> restaurantTypeList = new ArrayList<CodeMasterVO>();
	
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
	private RestaurantListConditionVO condition = new RestaurantListConditionVO();
	
	/** Searched List */
	private List<RestaurantMasterVO> list = new ArrayList<RestaurantMasterVO>();
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		// set up dropdown menu from codes
		cityList = codeMap.get(Constants.KEY_CD_CITY);
		cuisineTypeList = codeMap.get(Constants.KEY_CD_CUISINE_TYPE);
		restaurantTypeList = codeMap.get(Constants.KEY_CD_RESTAURANT_TYPE);
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		userRestList = (List<MemberRestaurantVO>)httpSession.getAttribute(Constants.KEY_SESSION_USER_REST_LIST);
		
		logger.info("<---prepare end --->");
	}
	
	/**
	 * Initiate Restaurant List page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		list = dao.selectAll();

		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate Restaurant List page for restaurant users
	 * @return SUCCESS
	 */
	public String initRest() throws Exception {
		logger.info("<---initRest start --->");
		
		//Select own restaurant List
		RestaurantMasterDao dao = new RestaurantMasterDao();
		//Set own restaurantID list
		List<String> restIDList = new ArrayList<String>();
		int size = userRestList.size();
		for (int i = 0; i < size; i++) {
			restIDList.add(userRestList.get(i).getRestaurantID());
		}
		if (size > 0) {
			// Only search when member has a restaurant list
			list = dao.selectRest(restIDList);
		}

		logger.info("<---initRest end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Restaurant List
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
	 * @param cuisineTypeList the cuisineTypeList to set
	 */
	public void setCuisineTypeList(List<CodeMasterVO> cuisineTypeList) {
		this.cuisineTypeList = cuisineTypeList;
	}

	/**
	 * @return the restaurantTypeList
	 */
	public List<CodeMasterVO> getRestaurantTypeList() {
		return restaurantTypeList;
	}

	/**
	 * @param restaurantTypeList the restaurantTypeList to set
	 */
	public void setRestaurantTypeList(List<CodeMasterVO> restaurantTypeList) {
		this.restaurantTypeList = restaurantTypeList;
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
