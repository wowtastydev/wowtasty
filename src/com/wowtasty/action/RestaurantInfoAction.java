package com.wowtasty.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.DeliveryAreaDao;
import com.wowtasty.mybatis.dao.DeliveryMasterDao;
import com.wowtasty.mybatis.dao.DeliveryOpenHourDao;
import com.wowtasty.mybatis.dao.RestaurantDeliveryAreaDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.dao.RestaurantOpenHourDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.DeliveryAreaVO;
import com.wowtasty.mybatis.vo.DeliveryMasterVO;
import com.wowtasty.mybatis.vo.DeliveryOpenHourVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.RestaurantDeliveryAreaVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.mybatis.vo.RestaurantOpenHourVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.StringUtil;
import com.wowtasty.util.ValidationUtil;
import com.wowtasty.vo.DeliveryAreaListConditionVO;
import com.wowtasty.vo.HourListVO;

/**
 * @author Hak C.
 *
 */
public class RestaurantInfoAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Long MAX_SIZE = 2*1024*1024l;
	private static final Integer MAX_LEN = 500;
	private static final Integer MAX_LEN_MAP = 1500;
	private static final String EVERYDAY = "0";
	private static final Integer FIRST_OPEN_HOUR = 1;
	private static final Integer SECOND_OPEN_HOUR = 2;
	private static final String DEFAULT_FIRST_HOUR_FR = "11:00";
	private static final String DEFAULT_FIRST_HOUR_TO = "15:00";
	private static final String DEFAULT_SECOND_HOUR_FR = "17:00";
	private static final String DEFAULT_SECOND_HOUR_TO = "20:00";
	private static final Integer DAY_SIZE = 7;
	private static final Float MIN_PRICE = 0.00f;
	private static final Float MAX_PRICE = 999.99f;
	private static final Float MIN_COMMISSION = 0.00f;
	private static final Float MAX_COMMISSION = 100.00f;
	private static final Integer ALL = 9;
	private static final Integer MASTER = 0;
	private static final Integer OPENHOUR = 1;
	private static final Integer DELIVHOUR = 2;
	private static final Integer AREA = 3;
	private static final String DELIMITER = ",";
	private static final String TIME_PATTERN = "HH:mm";
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantInfoAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> cuisineTypeList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> restaurantTypeList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> provinceList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> restaurantStatusList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> dayTypeList = new ArrayList<CodeMasterVO>();
	private List<DeliveryMasterVO> deliveryCompanyList = new ArrayList<DeliveryMasterVO>();
	private List<CodeMasterVO> cityMapList = new ArrayList<CodeMasterVO>();
	
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
	
	/** Restaurant Vos Columns */
	private RestaurantMasterVO vo = new RestaurantMasterVO();
	private List<HourListVO> openHourlist = new ArrayList<HourListVO>();
	private List<HourListVO> openHourDelivlist = new ArrayList<HourListVO>();
	private List<RestaurantDeliveryAreaVO> restDelivArealist = new ArrayList<RestaurantDeliveryAreaVO>();
	private List<DeliveryAreaVO> delivArealist = new ArrayList<DeliveryAreaVO>();

	/** Parameter */
	private String selectedID = "";
	private String bizHourType = ""; // 0:Every day 1:Set by day
	private String bizHourDelivType = ""; // 0:Every day 1:Set by day
	
	private String bizAll1Start = "";
	private String bizAll1End = "";
	private String bizAll2Start = "";
	private String bizAll2End = "";
	private String bizDelivAll1Start = "";
	private String bizDelivAll1End = "";
	private String bizDelivAll2Start = "";
	private String bizDelivAll2End = "";
	private boolean naDelivFlag = true;
	
	/** Files upload */
	private File logofile = null;
	private String logofileContentType = "";
	private String logofileFileName = "";
	
	private File mainfile = null;
	private String mainfileContentType = "";
	private String mainfileFileName = "";
	
	/** Delivery Area Parameter */
	private DeliveryAreaListConditionVO condition = new DeliveryAreaListConditionVO();
	// jsp accordion active index
	private Integer active = 0;
	
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
		dayTypeList = codeMap.get(Constants.KEY_CD_DAY_TYPE);
		cityMapList = codeMap.get(Constants.KEY_CD_CITY_MAP);

		// Search delivery company list 
		DeliveryMasterDao dmdao = new DeliveryMasterDao();
		deliveryCompanyList = dmdao.selectAll();
		
		// Search Delivery area list
		DeliveryAreaDao dadao = new DeliveryAreaDao();
		delivArealist = dadao.selectAll();
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Restraurant Information page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");
		
		if ("".equals(selectedID)) {
			// Add Restaurant
			// Add empty open hour list
			addOpenHours();
			addDelivOpenHours();
		} else {
			// Edit Restaurant
			RestaurantMasterDao dao = new RestaurantMasterDao();
			vo = dao.selectByID(selectedID);
			
			// Select open hours
			RestaurantOpenHourDao odao = new RestaurantOpenHourDao();
			openHourlist = odao.selectByID(selectedID);
			if (openHourlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addOpenHours();
			}
			
			// Select Delivery open hours		
			DeliveryOpenHourDao ddao = new DeliveryOpenHourDao();
			openHourDelivlist = ddao.selectHourListByID(vo.getDeliveryCompanyID());
			if (openHourDelivlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addDelivOpenHours();
			} else {
				// Set N/A checkbox off
				naDelivFlag = false;
			}
			
			// Select Delivery areas
			RestaurantDeliveryAreaDao rddao = new RestaurantDeliveryAreaDao();
			restDelivArealist = rddao.selectByID(selectedID);
		}
		
		// Set up default value
		bizHourType = EVERYDAY;
		bizAll1Start = DEFAULT_FIRST_HOUR_FR;
		bizAll1End = DEFAULT_FIRST_HOUR_TO;
		bizAll2Start = DEFAULT_SECOND_HOUR_FR;
		bizAll2End = DEFAULT_SECOND_HOUR_TO;
		bizHourDelivType = EVERYDAY;
		bizDelivAll1Start = DEFAULT_FIRST_HOUR_FR;
		bizDelivAll1End = DEFAULT_FIRST_HOUR_TO;
		bizDelivAll2Start = DEFAULT_SECOND_HOUR_FR;
		bizDelivAll2End = DEFAULT_SECOND_HOUR_TO;
		
		// set MASTER accordion open
		active = MASTER;

		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert/Edit Restraurant Information data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		
		// Check Information Validation
		boolean hasError = false;
		hasError = checkValidationInfo();
		
		// In case of validation error, return INPUT
		if (hasError) {
			// Reload Restaurant Information
			reload(MASTER);
			active = MASTER;
			return INPUT;
		}

		RestaurantMasterDao dao = new RestaurantMasterDao();
		int returnCnt = 0;
		
		if ("".equals(vo.getRestaurantID())) {
			//　Insert
			// Take maxID
			vo.setRestaurantID(dao.selectMaxID());
			
			// Upload Images
			upload();
			
			// Set default data
			vo.setStatus(Constants.CODE_STATUS_APPLIED);
			vo.setUpdateID(uservo.getMemberID());
			//refine keywords
			vo.setKeyword(refineKeywords(vo.getKeyword()));
			
			// Insert Masterdata
			returnCnt = dao.insert(vo);
			if (returnCnt > 0) {
				addActionMessage("Restaurant infomation has been inserted successfully");
			}
			// Add empty open hours
			addOpenHours();
			addDelivOpenHours();
		} else {
			// Update
			// Upload Images
			upload();
					
			// Set default data
			vo.setUpdateID(uservo.getMemberID());
			//refine keywords
			vo.setKeyword(refineKeywords(vo.getKeyword()));
			// Update
			returnCnt = dao.update(vo);
			if (returnCnt > 0) {
				addActionMessage("Restaurant infomation has been updated successfully");
			}
			
			// Select open hours
			RestaurantOpenHourDao odao = new RestaurantOpenHourDao();
			openHourlist = odao.selectByID(vo.getRestaurantID());
			if (openHourlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addOpenHours();
			}
			
			// Select Delivery open hours		
			DeliveryOpenHourDao ddao = new DeliveryOpenHourDao();
			openHourDelivlist = ddao.selectHourListByID(vo.getDeliveryCompanyID());
			if (openHourDelivlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addDelivOpenHours();
			}
		}
		
		// Reload All Restaurant Information
		reload(ALL);
		
		// set MASTER accordion open
		active = MASTER;
		
		// set parameter restaurantID
		selectedID = vo.getRestaurantID();
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Save Restaurant Business Hours
	 * @return SUCCESS
	 */
	public String saveBizHour() throws Exception {
		logger.info("<---saveBizHour start --->");
		if ("".equals(vo.getRestaurantID())) {
			addActionError("Please save Restaurant General Information first.");
			return INPUT;
		}
		
		
		// Validation Check
		// Check Information Validation
		boolean hasError = false;
		hasError = checkValidationOpenHour();
		
		// In case of validation error, return INPUT
		if (hasError) {
			// Reload Restaurant Information
			reload(OPENHOUR);
			active = OPENHOUR;
			return INPUT;
		}
				
		RestaurantOpenHourDao dao = new RestaurantOpenHourDao();
		boolean isInsert = true;
		String id = "";
		// In case of Every day 
		if (EVERYDAY.equals(bizHourType)) {
			RestaurantOpenHourVO ovo = null;
			
			for (int i = 0; i < DAY_SIZE; i++) {
				// Check wheather insert or update
				if ("".equals(openHourlist.get(i).getID())) {
					id = vo.getRestaurantID();
				} else {
					id = openHourlist.get(i).getID();
					isInsert = false;
				}
				
				// First Open Hour
				ovo = new RestaurantOpenHourVO();
				ovo.setRestaurantID(id);
				ovo.setSeq(FIRST_OPEN_HOUR);
				ovo.setWeekDay(i);
				ovo.setStartTime(StringUtil.convertString2Time(bizAll1Start, TIME_PATTERN));
				ovo.setEndTime(StringUtil.convertString2Time(bizAll1End, TIME_PATTERN));
				
				if (isInsert) {
					dao.insert(ovo);
				} else {
					dao.update(ovo);
				}
				
				// Second Open Hour
				ovo = new RestaurantOpenHourVO();
				ovo.setRestaurantID(id);
				ovo.setSeq(SECOND_OPEN_HOUR);
				ovo.setWeekDay(i);
				ovo.setStartTime(StringUtil.convertString2Time(bizAll2Start, TIME_PATTERN));
				ovo.setEndTime(StringUtil.convertString2Time(bizAll2End, TIME_PATTERN));
				
				if (isInsert) {
					dao.insert(ovo);
				} else {
					dao.update(ovo);
				}
			}
		} else {
			// In case of Set by day 
			RestaurantOpenHourVO ovo = null;
			for (int i = 0; i < DAY_SIZE; i++) {
				// Check wheather insert or update
				if ("".equals(openHourlist.get(i).getID())) {
					id = vo.getRestaurantID();
				} else {
					id = openHourlist.get(i).getID();
					isInsert = false;
				}
				
				// First Open Hour
				ovo = new RestaurantOpenHourVO();
				ovo.setRestaurantID(id);
				ovo.setSeq(FIRST_OPEN_HOUR);
				ovo.setWeekDay(openHourlist.get(i).getWeekDay());
				ovo.setStartTime(StringUtil.convertString2Time(openHourlist.get(i).getStartTime1(), TIME_PATTERN));
				ovo.setEndTime(StringUtil.convertString2Time(openHourlist.get(i).getEndTime1(), TIME_PATTERN));

				if (isInsert) {
					// In case insert, Restaurant ID is ""
					dao.insert(ovo);
				} else {
					// Update
					dao.update(ovo);
				}
				
				// Second Open Hour
				ovo = new RestaurantOpenHourVO();
				ovo.setRestaurantID(id);
				ovo.setSeq(SECOND_OPEN_HOUR);
				ovo.setWeekDay(openHourlist.get(i).getWeekDay());
				ovo.setStartTime(StringUtil.convertString2Time(openHourlist.get(i).getStartTime2(), TIME_PATTERN));
				ovo.setEndTime(StringUtil.convertString2Time(openHourlist.get(i).getEndTime2(), TIME_PATTERN));

				if (isInsert) {
					// In case insert, Restaurant ID is ""
					dao.insert(ovo);
				} else {
					// Update
					dao.update(ovo);
				}
			}
		}

		addActionMessage("Restaurant Business Hours has been updated successfully");
		
		// Reload All information
		reload(ALL);
		
		// set OPENHOUR accordion open
		active = OPENHOUR;
		
		// set parameter restaurantID
		selectedID = vo.getRestaurantID();

		logger.info("<---saveBizHour end --->");
		return SUCCESS;
	}
	
	/**
	 * Save Delivery Business Hours
	 * @return SUCCESS
	 */
	public String saveBizHourDeliv() throws Exception {
		logger.info("<---saveBizHourDeliv start --->");
		if ("".equals(vo.getRestaurantID())) {
			addActionError("Please save Restaurant General Information first.");
			return INPUT;
		}
		
		if (naDelivFlag) {
			// If Delivery Hour is NOT Available and the restaurant has self delivery comapny id -> Delete delivery company and hour
			if (!"".equals(vo.getDeliveryCompanyID())) {
				// Delete the current delivery master data
				DeliveryMasterDao dmdao = new DeliveryMasterDao();
				dmdao.delete(vo.getDeliveryCompanyID());
				
				// Delete the current delivery hour data
				DeliveryOpenHourDao dodao = new DeliveryOpenHourDao();
				dodao.delete(vo.getDeliveryCompanyID());
				
				// Delete the restaurant delivery area by self delivery comapny id
				RestaurantDeliveryAreaDao rddao = new RestaurantDeliveryAreaDao();
				rddao.deleteByDeliveryID(vo.getDeliveryCompanyID());
				
				// Remove the self delivery company id in Restaurant master data
				RestaurantMasterDao rmdao = new RestaurantMasterDao();
				vo = rmdao.selectByID(vo.getRestaurantID());
				vo.setDeliveryCompanyID("");
				vo.setUpdateID(uservo.getMemberID());
				rmdao.update(vo);
				
				addActionMessage("Delivery Business Hours has been deleted successfully");
			}
		} else {
			// In case of Delivery hour available
			
			// If there is no delivery company saved, add delivery comapny master with Restaurant info
			if ("".equals(vo.getDeliveryCompanyID())) {
				// Get Restaurant information
				RestaurantMasterDao rmdao = new RestaurantMasterDao();
				vo = rmdao.selectByID(vo.getRestaurantID());
				
				DeliveryMasterVO dmvo = new DeliveryMasterVO();
				DeliveryMasterDao dmdao = new DeliveryMasterDao();
				
				// Copy restaurant data to delivery master
				dmvo.setDeliveryCompanyID(dmdao.selectMaxID());
				dmvo.setDeliveryCompanyType(Constants.CODE_DELIVERY_COM_TYPE_SELFDELIVERY);
				dmvo.setName(vo.getName());
				dmvo.setMemberID(vo.getMemberID());
				dmvo.setFirstName(vo.getFirstName());
				dmvo.setLastName(vo.getLastName());
				dmvo.setTelephone(vo.getTelephone());
				dmvo.setFax(vo.getFax());
				dmvo.setAddress(vo.getAddress());
				dmvo.setSuite(vo.getSuite());
				dmvo.setCity(vo.getCity());
				dmvo.setProvince(vo.getProvince());
				dmvo.setPostalCode(vo.getPostalCode());
				dmvo.setWebsite(vo.getWebsite());
				dmvo.setFacebook(vo.getFacebook());
				dmvo.setEmail1(vo.getEmail1());
				dmvo.setEmail2(vo.getEmail2());
				dmvo.setStatus(Constants.CODE_STATUS_APPLIED);
				dmvo.setUpdateID(uservo.getMemberID());

				//Insert Delivery Masterdata
				int returnCnt = dmdao.insert(dmvo);
				if (returnCnt > 0) {
					// Update Restaurant Masterdata with delivery company ID
					vo.setDeliveryCompanyID(dmvo.getDeliveryCompanyID());
					vo.setUpdateID(uservo.getMemberID());
					rmdao.update(vo);
					
					addActionMessage("Delivery master infomation has been inserted successfully");
				}
			}
			
			// Validation Check
			// Check Information Validation
			boolean hasError = false;
			hasError = checkValidationDelivOpenHour();
			
			// In case of validation error, return INPUT
			if (hasError) {
				// Reload Restaurant Information
				reload(DELIVHOUR);
				active = DELIVHOUR;
				return INPUT;
			}
					
			DeliveryOpenHourDao dao = new DeliveryOpenHourDao();
			boolean isInsert = true;
			String id = "";
			// In case of Every day 
			if (EVERYDAY.equals(bizHourDelivType)) {
				DeliveryOpenHourVO ovo = null;
				
				for (int i = 0; i < DAY_SIZE; i++) {
					// Check wheather insert or update
					if ("".equals(openHourDelivlist.get(i).getID())) {
						id = vo.getDeliveryCompanyID();
					} else {
						id = openHourDelivlist.get(i).getID();
						isInsert = false;
					}
					
					// First Open Hour
					ovo = new DeliveryOpenHourVO();
					ovo.setDeliveryCompanyID(id);
					ovo.setSeq(FIRST_OPEN_HOUR);
					ovo.setWeekDay(i);
					ovo.setStartTime(StringUtil.convertString2Time(bizDelivAll1Start, TIME_PATTERN));
					ovo.setEndTime(StringUtil.convertString2Time(bizDelivAll1End, TIME_PATTERN));
					
					if (isInsert) {
						dao.insert(ovo);
					} else {
						dao.update(ovo);
					}
					
					// Second Open Hour
					ovo = new DeliveryOpenHourVO();
					ovo.setDeliveryCompanyID(id);
					ovo.setSeq(SECOND_OPEN_HOUR);
					ovo.setWeekDay(i);
					ovo.setStartTime(StringUtil.convertString2Time(bizDelivAll2Start, TIME_PATTERN));
					ovo.setEndTime(StringUtil.convertString2Time(bizDelivAll2End, TIME_PATTERN));
					
					if (isInsert) {
						dao.insert(ovo);
					} else {
						dao.update(ovo);
					}
				}
			} else {
				// In case of Set by day 
				DeliveryOpenHourVO ovo = null;
				for (int i = 0; i < DAY_SIZE; i++) {
					// Check wheather insert or update
					if ("".equals(openHourDelivlist.get(i).getID())) {
						id = vo.getDeliveryCompanyID();
					} else {
						id = openHourDelivlist.get(i).getID();
						isInsert = false;
					}
					
					// First Open Hour
					ovo = new DeliveryOpenHourVO();
					ovo.setDeliveryCompanyID(id);
					ovo.setSeq(FIRST_OPEN_HOUR);
					ovo.setWeekDay(openHourDelivlist.get(i).getWeekDay());
					ovo.setStartTime(StringUtil.convertString2Time(openHourDelivlist.get(i).getStartTime1(), TIME_PATTERN));
					ovo.setEndTime(StringUtil.convertString2Time(openHourDelivlist.get(i).getEndTime1(), TIME_PATTERN));

					if (isInsert) {
						dao.insert(ovo);
					} else {
						dao.update(ovo);
					}
					
					// Second Open Hour
					ovo = new DeliveryOpenHourVO();
					ovo.setDeliveryCompanyID(id);
					ovo.setSeq(SECOND_OPEN_HOUR);
					ovo.setWeekDay(openHourDelivlist.get(i).getWeekDay());
					ovo.setStartTime(StringUtil.convertString2Time(openHourDelivlist.get(i).getStartTime2(), TIME_PATTERN));
					ovo.setEndTime(StringUtil.convertString2Time(openHourDelivlist.get(i).getEndTime2(), TIME_PATTERN));

					if (isInsert) {
						dao.insert(ovo);
					} else {
						dao.update(ovo);
					}
				}
			}

			addActionMessage("Delivery Business Hours has been updated successfully");
		}
		
		// Reload All information
		reload(ALL);
		
		// set DELIVHOUR accordion open
		active = DELIVHOUR;
		
		// set parameter restaurantID
		selectedID = vo.getRestaurantID();

		logger.info("<---saveBizHourDeliv end --->");
		return SUCCESS;
	}
	

	/**
	 * Save delivery areas
	 * @return SUCCESS
	 */
	public String saveDeliveryArea() throws Exception {
		logger.info("<---saveDeliveryArea start --->");

		if ("".equals(vo.getRestaurantID())) {
			addActionError("Please save Restaurant General Information first.");
			return INPUT;
		}

		// Validation Check
		// Check Delivery Area Validation
		boolean hasError = false;
		hasError = checkValidationDelivArea(restDelivArealist);
		
		// In case of validation error, return INPUT
		if (hasError) {
			// Reload Restaurant Information
			reload(AREA);
			active = AREA;
			return INPUT;
		}
		
		// Set new Delivery Area
		int size = restDelivArealist.size();
		List<RestaurantDeliveryAreaVO> newlist = new ArrayList<RestaurantDeliveryAreaVO>();
		
		int sizeNew = 0;
		String deliveryCompanyID = "";
		String postalPrefix = "";
		boolean hasPrefix = false;
		RestaurantDeliveryAreaVO rdvo = null;
		
		for (int i = 0; i < size; i++) {
			hasPrefix = false;
			rdvo = restDelivArealist.get(i);
			if (rdvo != null) {
				deliveryCompanyID = rdvo.getDeliveryCompanyID();
				postalPrefix = rdvo.getPostalPrefix().toUpperCase();
				// Compare same delivery id and same prefix with new area
				sizeNew = newlist.size();
				for (int j = 0; j < sizeNew; j++) {
					if (deliveryCompanyID.equals(newlist.get(j).getDeliveryCompanyID())
							&& postalPrefix.equals(newlist.get(j).getPostalPrefix().toUpperCase())) {
						// If there is same delivery id and same prefix already, don't insert.
						hasPrefix = true;
						break;
					}
				}
				
				if (!hasPrefix) {
					// Add area to newlist
					newlist.add(rdvo);
				}
			}
		}
		
		// Delete all areas and insert inputted areas
		RestaurantDeliveryAreaDao rddao = new RestaurantDeliveryAreaDao();
		rddao.updateAll(newlist, vo.getRestaurantID());
		
		addActionMessage("Delivery Areas has been updated successfully");

		// Reload Restaurant Information
		reload(ALL);
		
		// set AREA accordion open
		active = AREA;
		
		// set parameter restaurantID
		selectedID = vo.getRestaurantID();

		logger.info("<---saveDeliveryArea end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check(Restaurant Information)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationInfo() {
		
		boolean hasError = false;
		Float tmpAmt = 0.00f;
		Integer tmpInt = 0;
	
		// Restaurant Name Validation Check
		if (ValidationUtil.isBlank(vo.getName())) {
			addFieldError("vo.name", getText("E0001_1", new String[]{"Restaurant Name"}));
			hasError = true;
		}
		
		// Cuisine Type Validation Check
		if (ValidationUtil.isBlank(vo.getCuisineType())) {
			addFieldError("vo.cuisineType", getText("E0001_1", new String[]{"Cuisine Type"}));
			hasError = true;
		}
		
		//Telephone Validation Check
		if (ValidationUtil.isBlank(vo.getTelephone())) {
			addFieldError("vo.telephone", getText("E0001_1", new String[]{"Telephone Number"}));
			hasError = true;
		} else {
			// Valid check
			if (!ValidationUtil.isTelephone(vo.getTelephone())) {
				addFieldError("vo.telephone", getText("E0003_1", new String[]{"Telephone Number"}));
				hasError = true;
			}
		}
		
		// Fax Validation Check
		if (!ValidationUtil.isBlank(vo.getFax()) && !ValidationUtil.isTelephone(vo.getFax())) {
			addFieldError("vo.fax", getText("E0003_1", new String[]{"Fax Number"}));
			hasError = true;
		}

		//Address Validation Check
		if (ValidationUtil.isBlank(vo.getAddress())) {
			addFieldError("vo.address", getText("E0001_1", new String[]{"Address"}));
			hasError = true;
		}
		
		//City Validation Check
		if (ValidationUtil.isBlank(vo.getCity())) {
			addFieldError("vo.city", getText("E0001_1", new String[]{"City"}));
			hasError = true;
		}
		
		//Province Validation Check
		if (ValidationUtil.isBlank(vo.getProvince())) {
			addFieldError("vo.province", getText("E0001_1", new String[]{"Province"}));
			hasError = true;
		}
		
		//Suite Number Validation Check
		if (!ValidationUtil.isBlank(vo.getSuite()) && !ValidationUtil.isNumEng(vo.getSuite())) {
			addFieldError("vo.suite", getText("E0003_1", new String[]{"Suite Number"}));
			hasError = true;
		}
		
		//Postal Code Validation Check
		if (ValidationUtil.isBlank(vo.getPostalCode())) {
			addFieldError("vo.postalCode", getText("E0001_1", new String[]{"Postal Code"}));
			hasError = true;
		} else {
			if (!ValidationUtil.isPostalCode(vo.getPostalCode())) {
				addFieldError("vo.postalCode", getText("E0003_1", new String[]{"Postal Code"}));
				hasError = true;
			}			
		}
		
		//Email1 Validation Check
		if (ValidationUtil.isBlank(vo.getEmail1())) {
			addFieldError("vo.email1", getText("E0001_1", new String[]{"Email1"}));
			hasError = true;
		} else {
			if (!ValidationUtil.isEmail(vo.getEmail1())) {
				addFieldError("vo.email1", getText("E0003_1", new String[]{"Email1"}));
				hasError = true;
			}			
		}
		
		//Email2 Validation Check
		if (!ValidationUtil.isBlank(vo.getEmail2()) && !ValidationUtil.isEmail(vo.getEmail2())) {
			addFieldError("vo.email2", getText("E0003_1", new String[]{"Email2"}));
			hasError = true;
		}
		
		//Logo Image Validation
		if (logofile != null) {
			// File size MAX_SIZE
			if (logofile.length() > MAX_SIZE) {
				addFieldError("vo.logoImagePath", getText("E0006", new String[]{"Logo File size", MAX_SIZE.toString()}));
				hasError = true;
			}
			// File type
			if (!logofileContentType.startsWith("image/")) {
				addFieldError("vo.logoImagePath", getText("E0003_1", new String[]{"Logo File"}));
				hasError = true;
			}
		}

		//Main Image Image File size MAX_SIZE
		if (mainfile != null) {
			// File size MAX_SIZE
			if (mainfile.length() > MAX_SIZE) {
				addFieldError("vo.mainImagePath", getText("E0006", new String[]{"Main Image File size", MAX_SIZE.toString()}));
				hasError = true;
			}
			// File type
			if (!mainfileContentType.startsWith("image/")) {
				addFieldError("vo.mainImagePath", getText("E0003_1", new String[]{"Main Image File"}));
				hasError = true;
			}
		}
		
		//Google Map Validation Check
		if (!ValidationUtil.isBlank(vo.getGoogleMapURL()) && vo.getGoogleMapURL().length() > MAX_LEN_MAP) {
			addFieldError("vo.googleMapURL", getText("E0006", new String[]{"Google Map", MAX_LEN_MAP.toString()}));
			hasError = true;
		}
		
		//Restaurant Profile Validation Check
		if (!ValidationUtil.isBlank(vo.getProfile()) && vo.getProfile().length() > MAX_LEN) {
			addFieldError("vo.profile", getText("E0006", new String[]{"Restaurant Profile", MAX_LEN.toString()}));
			hasError = true;
		}
		
		//Search Key Words Validation Check
		if (!ValidationUtil.isBlank(vo.getKeyword()) && vo.getKeyword().length() > MAX_LEN) {
			addFieldError("vo.keyword", getText("E0006", new String[]{"Search Key Words", MAX_LEN.toString()}));
			hasError = true;
		}
		
		//Commission Validation Check
		tmpAmt = vo.getCommission();
		if (tmpAmt != null) {
			if (tmpAmt < MIN_COMMISSION || tmpAmt > MAX_COMMISSION ) {
				// 0 < Commission < 99.99
				addFieldError("vo.commission", getText("E0008", new String[]{"NonCash Commission", MIN_COMMISSION.toString(), MAX_COMMISSION.toString()}));
				hasError = true;
			}
		} else {
			addFieldError("vo.commission", getText("E0001_1", new String[]{"NonCash Commission"}));
			hasError = true;
		}
		
		//Cash Commission Validation Check
		tmpAmt = vo.getCashCommission();
		if (tmpAmt != null) {
			if (tmpAmt < MIN_COMMISSION || tmpAmt > MAX_COMMISSION ) {
				// 0 < Cash Commission < 99.99
				addFieldError("vo.cashCommission", getText("E0008", new String[]{"Cash Commission", MIN_COMMISSION.toString(), MAX_COMMISSION.toString()}));
				hasError = true;
			}
		} else {
			addFieldError("vo.cashCommission", getText("E0001_1", new String[]{"Cash Commission"}));
			hasError = true;
		}

		//Delivery/Take-out Validation Check
		if (ValidationUtil.isBlank(vo.getRestaurantType())) {
			addFieldError("vo.restaurantType", getText("E0001_1", new String[]{"Delivery/Take-out"}));
			hasError = true;
		}
		
		//Delivery Time Validation Check
		tmpInt = vo.getDeliveryTime();
		if (tmpInt != null) {
			if (!ValidationUtil.isWholeNum(tmpInt.toString())) {
				addFieldError("vo.deliveryTime", getText("E0003_1", new String[]{"Delivery time"}));
				hasError = true;
			}
		}
		
		//Min. Order Validation Check
		tmpAmt = vo.getMinPrice();
		if (tmpAmt != null) {
			if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
				// 0 < Min. Order < 999.99
				addFieldError("vo.minPrice", getText("E0008", new String[]{"Min. Order", MIN_PRICE.toString(), MAX_PRICE.toString()}));
				hasError = true;
			}
		}
		
		//Ave. Price Validation Check
		tmpAmt = vo.getAveragePrice();
		if (tmpAmt != null) {
			if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
				// 0 < Delivery Fee < 999.99
				addFieldError("vo.averagePrice", getText("E0008", new String[]{"Ave. Price", MIN_PRICE.toString(), MAX_PRICE.toString()}));
				hasError = true;
			}
		}
		
		//Billing Account Validation Check
		if (ValidationUtil.isBlank(vo.getBillAccountNO())) {
			addFieldError("vo.billAccountNO", getText("E0001_1", new String[]{"Billing Account No"}));
			hasError = true;
		} else {
			if (!ValidationUtil.isNumEng(vo.getBillAccountNO())) {
				addFieldError("vo.billAccountNO", getText("E0003_1", new String[]{"Billing Account No"}));
				hasError = true;
			}			
		}	
		
		return hasError;
	}
	
	/**
	 * Validation check(Restaurant Open Hour)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationOpenHour() {
		
		boolean hasError = false;
		if (EVERYDAY.equals(bizHourType)) {
			if (ValidationUtil.isBlank(bizAll1Start)) {
				// Open1(From) is required
				addFieldError("bizAll1Start", getText("E0001_1", new String[]{"Open1(From)"}));
				hasError = true;
		
			}
			if (ValidationUtil.isBlank(bizAll1End)) {
				// Open1(To) is required
				addFieldError("bizAll1End", getText("E0001_1", new String[]{"Open1(To)"}));
				hasError = true;
			}
		} else {
			for (int i = 0; i < DAY_SIZE; i++) {
				if (!openHourlist.get(i).isCloseFlag()) {
					if (ValidationUtil.isBlank(openHourlist.get(i).getStartTime1())) {
						// Open1(From) is required
						addFieldError("openHourlist[" + i + "].startTime1", getText("E0001_1", new String[]{"Open1(From)[" + (i + 1) + "]"}));
						hasError = true;
					}
					if (ValidationUtil.isBlank(openHourlist.get(i).getEndTime1())) {
						// Open1(To) is required
						addFieldError("openHourlist[" + i + "].endTime1", getText("E0001_1", new String[]{"Open1(To)[" + (i + 1) + "]"}));
						hasError = true;
					}
				}
			}
		}
		return hasError;
	}
	
	/**
	 * Validation check(Delivery Open Hour)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationDelivOpenHour() {
		
		boolean hasError = false;
		if (EVERYDAY.equals(bizHourDelivType)) {
			if (ValidationUtil.isBlank(bizDelivAll1Start)) {
				// Open1(From) is required
				addFieldError("bizDelivAll1Start", getText("E0001_1", new String[]{"Open1(From)"}));
				hasError = true;
		
			}
			if (ValidationUtil.isBlank(bizDelivAll1End)) {
				// Open1(To) is required
				addFieldError("bizDelivAll1End", getText("E0001_1", new String[]{"Open1(To)"}));
				hasError = true;
			}
		} else {
			for (int i = 0; i < DAY_SIZE; i++) {
				if (!openHourDelivlist.get(i).isCloseFlag()) {
					if (ValidationUtil.isBlank(openHourDelivlist.get(i).getStartTime1())) {
						// Open1(From) is required
						addFieldError("openHourDelivlist[" + i + "].startTime1", getText("E0001_1", new String[]{"Open1(From)[" + (i + 1) + "]"}));
						hasError = true;
					}
					if (ValidationUtil.isBlank(openHourDelivlist.get(i).getEndTime1())) {
						// Open1(To) is required
						addFieldError("openHourDelivlist[" + i + "].endTime1", getText("E0001_1", new String[]{"Open1(To)[" + (i + 1) + "]"}));
						hasError = true;
					}
				}
			}
		}
		return hasError;
	}
	
	/**
	 * Validation check(Delivery Area)
	 * @param list : delivery area to insert
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationDelivArea(List<RestaurantDeliveryAreaVO> list) {
		
		boolean hasError = false;
		int size = list.size();
		String tmpStr = "";
		Float tmpAmt = 0.00f;
		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				// Prefix Validation Check
				tmpStr = list.get(i).getPostalPrefix();
				if (ValidationUtil.isBlank(tmpStr)) {
					// Prefix is required
					addFieldError("restDelivArealist[" + i + "].postalPrefix", getText("E0001_1", new String[]{"Postal Code Prefix[" + (i + 1) + "]"}));
					hasError = true;
				} else {
					if (!ValidationUtil.isPostalCodePrefix(tmpStr)) {
						// Prefix is prefix postal code
						addFieldError("restDelivArealist[" + i + "].postalPrefix", getText("E0003_1", new String[]{"Postal Code Prefix[" + (i + 1) + "]"}));
						hasError = true;
					}
				}
				
				// Min Price Validation Check
				tmpAmt = list.get(i).getMinPrice();
				if (tmpAmt != null) {
					if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
						// 0 < Min Price < 999.99
						addFieldError("restDelivArealist[" + i + "].minPrice", getText("E0008", new String[]{"Min. Price[" + (i + 1) + "]", MIN_PRICE.toString(), MAX_PRICE.toString()}));
						hasError = true;
					}
				} else {
					// Min Price is required
					addFieldError("restDelivArealist[" + i + "].minPrice", getText("E0001_1", new String[]{"Min. Price[" + (i + 1) + "]"}));
					hasError = true;
				}
				
				// Delivery Fee Validation Check
				tmpAmt = list.get(i).getDeliveryFee();
				if (tmpAmt != null) {
					if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
						// 0 < Delivery Fee < 999.99
						addFieldError("restDelivArealist[" + i + "].deliveryFee", getText("E0008", new String[]{"Delivery Fee[" + (i + 1) + "]", MIN_PRICE.toString(), MAX_PRICE.toString()}));
						hasError = true;
					}
				} else {
					// Delivery Fee is required
					addFieldError("restDelivArealist[" + i + "].deliveryFee", getText("E0001_1", new String[]{"Delivery Fee[" + (i + 1) + "]"}));
					hasError = true;
				}
			} else {
				// Delete removed row
				list.remove(i--);
				size--;
			}
		}
		return hasError;
	}
	
	/**
	 * File upload
	 */	
	private void upload() {
		StringBuilder sb = new StringBuilder();
		// Logo file
		if (logofile != null) {
			sb.append(vo.getRestaurantID()).append("_logo.jpg");
			FileUtil.writePict(logofile, FileUtil.RESTAURANT_DIR, sb.toString());
			vo.setLogoImagePath(sb.toString());
		}
		sb.setLength(0);
		// Main Image file
		if (mainfile != null) {
			sb.append(vo.getRestaurantID()).append("_main.jpg");
			FileUtil.writePict(mainfile, FileUtil.RESTAURANT_DIR, sb.toString());
			vo.setMainImagePath(sb.toString());
		}

	}
	/**
	 *  Input empty open hour VOs
	 */		
	private void addOpenHours() {
		// Input empty open hour VOs
		openHourlist.clear();
		HourListVO vo = null;
		for (int i =0; i < DAY_SIZE; i++) {
			vo = new HourListVO();
			vo.setWeekDay(i);
			openHourlist.add(vo);
		}
	}
	
	/**
	 *  Input empty delivery open hour VOs
	 */		
	private void addDelivOpenHours() {

		// Input empty open hour VOs
		openHourDelivlist.clear();
		HourListVO vo = null;
		for (int i =0; i < DAY_SIZE; i++) {
			vo = new HourListVO();
			vo.setWeekDay(i);
			openHourDelivlist.add(vo);
		}
	}
	
	/**
	 *  reload restaurant information
	 *  @param type: reload type ALL, AREA, DELIVHOUR, OPENHOUR, MASTER
	 */		
	private void reload(Integer type) {

		// Reload Restaurant Master except case of saving master
		if (type != MASTER) {
			RestaurantMasterDao mdao = new RestaurantMasterDao();
			vo = mdao.selectByID(vo.getRestaurantID());		
		}

		
		// Reload open hours except case of saving open hours 
		if (type != OPENHOUR) {
			RestaurantOpenHourDao rodao = new RestaurantOpenHourDao();
			openHourlist = rodao.selectByID(vo.getRestaurantID());
			if (openHourlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addOpenHours();
			}
		}
		
		// Reload delivery open hours except case of saving delivery open hours	
		if (type != DELIVHOUR) {
			DeliveryOpenHourDao dodao = new DeliveryOpenHourDao();
			openHourDelivlist = dodao.selectHourListByID(vo.getDeliveryCompanyID());
			if (openHourDelivlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addDelivOpenHours();
			}
		}
		
		// Reload delivery areas except case of saving delivery areas	
		if (type != AREA) {
			// Select Delivery areas
			RestaurantDeliveryAreaDao rddao = new RestaurantDeliveryAreaDao();
			restDelivArealist = rddao.selectByID(vo.getRestaurantID());
		}
	}
	
	/**
	 *  refine keywords :trim spaces and split by comma
	 *  @param value: inputted keywords String
	 *  @return String: refined keywords
	 */		
	private String refineKeywords(String value) {
		if (value == null || "".equals(value)) {
			return "";
		}
		// refine keywords : trim spaces
		String[] arrayKeywords = value.split(DELIMITER);
		for (int i = 0; i < arrayKeywords.length; i++) {
			arrayKeywords[i] = arrayKeywords[i].trim();
		}
		return StringUtils.join(arrayKeywords, DELIMITER);
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
	 * @return the cityList
	 */
	public List<CodeMasterVO> getCityList() {
		return cityList;
	}

	/**
	 * @return the restaurantStatusList
	 */
	public List<CodeMasterVO> getRestaurantStatusList() {
		return restaurantStatusList;
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
	 * @return the logofile
	 */
	public File getLogofile() {
		return logofile;
	}

	/**
	 * @param logofile the logofile to set
	 */
	public void setLogofile(File logofile) {
		this.logofile = logofile;
	}

	/**
	 * @return the logofileContentType
	 */
	public String getLogofileContentType() {
		return logofileContentType;
	}

	/**
	 * @param logofileContentType the logofileContentType to set
	 */
	public void setLogofileContentType(String logofileContentType) {
		this.logofileContentType = logofileContentType;
	}

	/**
	 * @return the logofileFileName
	 */
	public String getLogofileFileName() {
		return logofileFileName;
	}

	/**
	 * @param logofileFileName the logofileFileName to set
	 */
	public void setLogofileFileName(String logofileFileName) {
		this.logofileFileName = logofileFileName;
	}

	/**
	 * @return the mainfile
	 */
	public File getMainfile() {
		return mainfile;
	}

	/**
	 * @param mainfile the mainfile to set
	 */
	public void setMainfile(File mainfile) {
		this.mainfile = mainfile;
	}

	/**
	 * @return the mainfileContentType
	 */
	public String getMainfileContentType() {
		return mainfileContentType;
	}

	/**
	 * @param mainfileContentType the mainfileContentType to set
	 */
	public void setMainfileContentType(String mainfileContentType) {
		this.mainfileContentType = mainfileContentType;
	}

	/**
	 * @return the mainfileFileName
	 */
	public String getMainfileFileName() {
		return mainfileFileName;
	}

	/**
	 * @param mainfileFileName the mainfileFileName to set
	 */
	public void setMainfileFileName(String mainfileFileName) {
		this.mainfileFileName = mainfileFileName;
	}

	/**
	 * @return the dayTypeList
	 */
	public List<CodeMasterVO> getDayTypeList() {
		return dayTypeList;
	}

	/**
	 * @return the bizHourType
	 */
	public String getBizHourType() {
		return bizHourType;
	}

	/**
	 * @param bizHourType the bizHourType to set
	 */
	public void setBizHourType(String bizHourType) {
		this.bizHourType = bizHourType;
	}

	/**
	 * @return the bizHourDelivType
	 */
	public String getBizHourDelivType() {
		return bizHourDelivType;
	}

	/**
	 * @param bizHourDelivType the bizHourDelivType to set
	 */
	public void setBizHourDelivType(String bizHourDelivType) {
		this.bizHourDelivType = bizHourDelivType;
	}
	/**
	 * @return the bizAll1Start
	 */
	public String getBizAll1Start() {
		return bizAll1Start;
	}

	/**
	 * @param bizAll1Start the bizAll1Start to set
	 */
	public void setBizAll1Start(String bizAll1Start) {
		this.bizAll1Start = bizAll1Start;
	}

	/**
	 * @return the bizAll1End
	 */
	public String getBizAll1End() {
		return bizAll1End;
	}

	/**
	 * @param bizAll1End the bizAll1End to set
	 */
	public void setBizAll1End(String bizAll1End) {
		this.bizAll1End = bizAll1End;
	}

	/**
	 * @return the bizAll2Start
	 */
	public String getBizAll2Start() {
		return bizAll2Start;
	}

	/**
	 * @param bizAll2Start the bizAll2Start to set
	 */
	public void setBizAll2Start(String bizAll2Start) {
		this.bizAll2Start = bizAll2Start;
	}

	/**
	 * @return the bizAll2End
	 */
	public String getBizAll2End() {
		return bizAll2End;
	}

	/**
	 * @param bizAll2End the bizAll2End to set
	 */
	public void setBizAll2End(String bizAll2End) {
		this.bizAll2End = bizAll2End;
	}

	/**
	 * @return the bizDelivAll1Start
	 */
	public String getBizDelivAll1Start() {
		return bizDelivAll1Start;
	}

	/**
	 * @param bizDelivAll1Start the bizDelivAll1Start to set
	 */
	public void setBizDelivAll1Start(String bizDelivAll1Start) {
		this.bizDelivAll1Start = bizDelivAll1Start;
	}

	/**
	 * @return the bizDelivAll1End
	 */
	public String getBizDelivAll1End() {
		return bizDelivAll1End;
	}

	/**
	 * @param bizDelivAll1End the bizDelivAll1End to set
	 */
	public void setBizDelivAll1End(String bizDelivAll1End) {
		this.bizDelivAll1End = bizDelivAll1End;
	}

	/**
	 * @return the bizDelivAll2Start
	 */
	public String getBizDelivAll2Start() {
		return bizDelivAll2Start;
	}

	/**
	 * @param bizDelivAll2Start the bizDelivAll2Start to set
	 */
	public void setBizDelivAll2Start(String bizDelivAll2Start) {
		this.bizDelivAll2Start = bizDelivAll2Start;
	}

	/**
	 * @return the bizDelivAll2End
	 */
	public String getBizDelivAll2End() {
		return bizDelivAll2End;
	}

	/**
	 * @param bizDelivAll2End the bizDelivAll2End to set
	 */
	public void setBizDelivAll2End(String bizDelivAll2End) {
		this.bizDelivAll2End = bizDelivAll2End;
	}

	/**
	 * @return the restDelivArealist
	 */
	public List<RestaurantDeliveryAreaVO> getRestDelivArealist() {
		return restDelivArealist;
	}

	/**
	 * @param restDelivArealist the restDelivArealist to set
	 */
	public void setRestDelivArealist(
			List<RestaurantDeliveryAreaVO> restDelivArealist) {
		this.restDelivArealist = restDelivArealist;
	}

	/**
	 * @return the openHourlist
	 */
	public List<HourListVO> getOpenHourlist() {
		return openHourlist;
	}

	/**
	 * @param openHourlist the openHourlist to set
	 */
	public void setOpenHourlist(List<HourListVO> openHourlist) {
		this.openHourlist = openHourlist;
	}

	/**
	 * @return the openHourDelivlist
	 */
	public List<HourListVO> getOpenHourDelivlist() {
		return openHourDelivlist;
	}

	/**
	 * @param openHourDelivlist the openHourDelivlist to set
	 */
	public void setOpenHourDelivlist(List<HourListVO> openHourDelivlist) {
		this.openHourDelivlist = openHourDelivlist;
	}

	/**
	 * @return the naDelivFlag
	 */
	public boolean isNaDelivFlag() {
		return naDelivFlag;
	}

	/**
	 * @param naDelivFlag the naDelivFlag to set
	 */
	public void setNaDelivFlag(boolean naDelivFlag) {
		this.naDelivFlag = naDelivFlag;
	}

	/**
	 * @return the delivArealist
	 */
	public List<DeliveryAreaVO> getDelivArealist() {
		return delivArealist;
	}

	/**
	 * @param delivArealist the delivArealist to set
	 */
	public void setDelivArealist(List<DeliveryAreaVO> delivArealist) {
		this.delivArealist = delivArealist;
	}

	/**
	 * @return the deliveryCompanyList
	 */
	public List<DeliveryMasterVO> getDeliveryCompanyList() {
		return deliveryCompanyList;
	}

	/**
	 * @return the condition
	 */
	public DeliveryAreaListConditionVO getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(DeliveryAreaListConditionVO condition) {
		this.condition = condition;
	}

	/**
	 * @return the cityMapList
	 */
	public List<CodeMasterVO> getCityMapList() {
		return cityMapList;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}
}
