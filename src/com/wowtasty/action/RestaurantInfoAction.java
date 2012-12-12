package com.wowtasty.action;

import java.io.File;
import java.sql.Time;
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
import com.wowtasty.mybatis.dao.RestaurantOpenHourDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.DeliveryOpenHourVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.RestaurantDeliveryAreaVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.mybatis.vo.RestaurantOpenHourVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.StringConvertUtil;
import com.wowtasty.util.ValidationUtil;




/**
 * @author Hak C.
 *
 */
public class RestaurantInfoAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Integer MAX_LEN = 100;
	private static final String EVERYDAY = "0";
	private static final String SET_BY_DAY = "1";
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
	private List<RestaurantOpenHourVO> openHour1list = new ArrayList<RestaurantOpenHourVO>();
	private List<DeliveryOpenHourVO> openHourDeliv1list = new ArrayList<DeliveryOpenHourVO>();
	private List<RestaurantOpenHourVO> openHour2list = new ArrayList<RestaurantOpenHourVO>();
	private List<DeliveryOpenHourVO> openHourDeliv2list = new ArrayList<DeliveryOpenHourVO>();
	private List<RestaurantDeliveryAreaVO> restDelivArealist = new ArrayList<RestaurantDeliveryAreaVO>();

	/** Parameter */
	private String selectedID = "";
	private String bizHourType = ""; // 0:Every day 1:Set by day
	private String bizHourDelivType = ""; // 0:Every day 1:Set by day
	
	private Time bizAll1Start = null;
	private Time bizAll1End = null;
	private Time bizAll2Start = null;
	private Time bizAll2End = null;
	private Time bizDelivAll1Start = null;
	private Time bizDelivAll1End = null;
	private Time bizDelivAll2Start = null;
	private Time bizDelivAll2End = null;
	
	
	/** Files upload */
	private File logofile = null;
	private String logofileContentType = "";
	private String logofileFileName = "";
	
	private File mainfile = null;
	private String mainfileContentType = "";
	private String mainfileFileName = "";
	
	
	
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
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate MemberAdd page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");
		
		addOpenHours();
		
		logger.info("<---Init end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate MemberEdit page
	 * @return SUCCESS
	 */
	public String initEdit() throws Exception {
		logger.info("<---initEdit start --->");
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		vo = dao.selectByID(selectedID);
		
		// Select open hours
		RestaurantOpenHourDao odao = new RestaurantOpenHourDao();
		List<RestaurantOpenHourVO> hourlist = new ArrayList<RestaurantOpenHourVO>();
		hourlist = odao.selectByID(selectedID);
		setHourList(hourlist);

		logger.info("<---initEdit end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert/Edit restaurant data
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
			return INPUT;
		}
		
		RestaurantMasterDao dao = new RestaurantMasterDao();
		int returnCnt = 0;
		if ("".equals(vo.getRestaurantID())) {
			//　Insert
			// Take maxID
			// Set memberID,status
			vo.setRestaurantID(dao.selectMaxID());
			
			// Upload Images
			upload();
			
			//Insert MemberMasterdata
			dao = new RestaurantMasterDao();
			returnCnt = dao.insert(vo);
			if (returnCnt > 0) {
				addActionMessage("Restaurant infomation has been inserted successfully");
			}
			// Add empty open hours
			addOpenHours();
		} else {
			
			// Upload Images
			upload();
						
			// Update
			dao = new RestaurantMasterDao();
			returnCnt = dao.update(vo);
			if (returnCnt > 0) {
				addActionMessage("Restaurant infomation has been updated successfully");
			}
			
			// Select open hours
			RestaurantOpenHourDao odao = new RestaurantOpenHourDao();
			List<RestaurantOpenHourVO> hourlist = new ArrayList<RestaurantOpenHourVO>();
			hourlist = odao.selectByID(vo.getRestaurantID());
			setHourList(hourlist);
		}
		selectedID = vo.getRestaurantID();
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Save Business Hours
	 * @return SUCCESS
	 */
	public String saveBizHour() throws Exception {
		logger.info("<---saveBizHour start --->");
		if ("".equals(selectedID)) {
			addActionError("Please save Restaurant General Information first.");
			return INPUT;
		}
		
		
		// Validation Check
		// Check Information Validation
		boolean hasError = false;
		hasError = checkValidationOpenHour();
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		int daySize = 7;
				
		RestaurantOpenHourDao dao = new RestaurantOpenHourDao();
		// In case of Every day 
		if (EVERYDAY.equals(bizHourType)) {
			// Set business hours
			// First Open Hour
			RestaurantOpenHourVO vo = new RestaurantOpenHourVO();
			vo.setRestaurantID(selectedID);
			vo.setSeq(1);
			vo.setStartTime(bizAll1Start);
			vo.setEndTime(bizAll1End);
			
			for (int i = 0; i < daySize; i++) {
				if ("".equals(openHour1list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					vo.setWeekDay(i);
					dao.insert(vo);
				} else {
					// Update
					vo.setWeekDay(i);
					dao.update(vo);
				}
			}
			
			// Second Open Hour
			vo = new RestaurantOpenHourVO();
			vo.setRestaurantID(selectedID);
			vo.setSeq(2);
			vo.setStartTime(bizAll2Start);
			vo.setEndTime(bizAll2End);
			
			for (int i = 0; i < daySize; i++) {
				if ("".equals(openHour2list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					vo.setWeekDay(i);
					dao.insert(vo);
				} else {
					// Update
					vo.setWeekDay(i);
					dao.update(vo);
				}
			}
			
		} else {
			// In case of Set by day 
			for (int i = 0; i < daySize; i++) {
				// First Open Hour
				if ("".equals(openHour1list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					openHour1list.get(i).setRestaurantID(selectedID);
					dao.insert(openHour1list.get(i));
				} else {
					// Update
					dao.update(openHour1list.get(i));
				}
				
				// Second Open Hour
				if ("".equals(openHour2list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					openHour2list.get(i).setRestaurantID(selectedID);
					dao.insert(openHour2list.get(i));
				} else {
					// Update
					dao.update(openHour2list.get(i));
				}
			}
		}

		addActionMessage("Restaurant Business Hours has been updated successfully");
		
		// Select open hours
		RestaurantOpenHourDao odao = new RestaurantOpenHourDao();
		List<RestaurantOpenHourVO> hourlist = new ArrayList<RestaurantOpenHourVO>();
		hourlist = odao.selectByID(vo.getRestaurantID());
		setHourList(hourlist);

		logger.info("<---saveBizHour end --->");
		return SUCCESS;
	}
	
	/**
	 * Save Delivery Business Hours
	 * @return SUCCESS
	 */
	public String saveBizHourDeliv() throws Exception {
		logger.info("<---saveBizHourDeliv start --->");
		if ("".equals(selectedID)) {
			addActionError("Please save Restaurant General Information first.");
			return INPUT;
		}
		
		// See if the company has Delivery Company Data already
		RestaurantMasterDao mdao = new RestaurantMasterDao();
		vo = mdao.selectByID(selectedID);
		if ("".equals(vo.getDeliveryCompanyID())) {
			// Insert Delivery Company
		}

		int daySize = 7;
				
		RestaurantOpenHourDao dao = new RestaurantOpenHourDao();
		// In case of Every day 
		if (EVERYDAY.equals(bizHourType)) {
			// Set business hours
			// First Open Hour
			RestaurantOpenHourVO vo = new RestaurantOpenHourVO();
			vo.setRestaurantID(selectedID);
			vo.setSeq(1);
			vo.setStartTime(bizAll1Start);
			vo.setEndTime(bizAll1End);
			
			for (int i = 0; i < daySize; i++) {
				if ("".equals(openHour1list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					vo.setWeekDay(i);
					dao.insert(vo);
				} else {
					// Update
					vo.setWeekDay(i);
					dao.update(vo);
				}
			}
			
			// Second Open Hour
			vo = new RestaurantOpenHourVO();
			vo.setRestaurantID(selectedID);
			vo.setSeq(2);
			vo.setStartTime(bizAll2Start);
			vo.setEndTime(bizAll2End);
			
			for (int i = 0; i < daySize; i++) {
				if ("".equals(openHour2list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					vo.setWeekDay(i);
					dao.insert(vo);
				} else {
					// Update
					vo.setWeekDay(i);
					dao.update(vo);
				}
			}
			
		} else {
			// In case of Set by day 
			for (int i = 0; i < daySize; i++) {
				// First Open Hour
				if ("".equals(openHour1list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					openHour1list.get(i).setRestaurantID(selectedID);
					dao.insert(openHour1list.get(i));
				} else {
					// Update
					dao.update(openHour1list.get(i));
				}
				
				// Second Open Hour
				if ("".equals(openHour2list.get(i).getRestaurantID())) {
					// In case insert, Restaurant ID is ""
					openHour2list.get(i).setRestaurantID(selectedID);
					dao.insert(openHour2list.get(i));
				} else {
					// Update
					dao.update(openHour2list.get(i));
				}
			}
		}

		addActionMessage("Restaurant Business Hours has been updated successfully");
		
		// Select open hours
		RestaurantOpenHourDao odao = new RestaurantOpenHourDao();
		List<RestaurantOpenHourVO> hourlist = new ArrayList<RestaurantOpenHourVO>();
		hourlist = odao.selectByID(vo.getRestaurantID());
		setHourList(hourlist);

		logger.info("<---saveBizHourDeliv end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check(Restaurant Information)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationInfo() {
		
		boolean hasError = false;
	
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
			if (!ValidationUtil.isNumEng(vo.getPostalCode())) {
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
		
		//Commission Validation Check
		if (ValidationUtil.isBlank(vo.getCommission().toString())) {
			addFieldError("vo.commission", getText("E0001_1", new String[]{"Commission"}));
			hasError = true;
		} else {
			if (!ValidationUtil.isNum(vo.getCommission().toString())) {
				addFieldError("vo.commission", getText("E0003_1", new String[]{"Commission"}));
				hasError = true;
			}			
		}
		
		//Delivery/Take-out Validation Check
		if (ValidationUtil.isBlank(vo.getRestaurantType())) {
			addFieldError("vo.restaurantType", getText("E0001_1", new String[]{"Delivery/Take-out"}));
			hasError = true;
		}

		//Min. Order Validation Check
		if (!ValidationUtil.isBlank(vo.getMinPrice().toString()) && !ValidationUtil.isNum(vo.getMinPrice().toString())) {
			addFieldError("vo.minPrice", getText("E0003_1", new String[]{"Min. Order"}));
			hasError = true;
		}
		
		//Ave. Price Validation Check
		if (!ValidationUtil.isBlank(vo.getAveragePrice().toString()) && !ValidationUtil.isNum(vo.getAveragePrice().toString())) {
			addFieldError("vo.averagePrice", getText("E0003_1", new String[]{"Ave. Price"}));
			hasError = true;
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
		if (!ValidationUtil.isTime("23:59", TIME_PATTERN)) {
			// Date(from) is not datetype
			addFieldError("condition.fromDate", getText("E0003_1", new String[]{"Date(from)"}));
			hasError = false;
	
		}
		
		return hasError;
	}
	
	/**
	 * File upload
	 */	
	private void upload() {
		FileUtil util = new FileUtil();
		StringBuilder sb = new StringBuilder();
		// Logo file
		if (logofile != null) {
			sb.append(vo.getRestaurantID()).append("_logo.jpg");
			util.writePict(logofile, "RESTAURANT", sb.toString());
			vo.setLogoImagePath(sb.toString());
		}
		sb.setLength(0);
		// Main Image file
		if (mainfile != null) {
			sb.append(vo.getRestaurantID()).append("_main.jpg");
			util.writePict(mainfile, "RESTAURANT", sb.toString());
			vo.setMainImagePath(sb.toString());
		}

	}
	/**
	 *  Input empty open hour VOs
	 */		
	private void addOpenHours() {

		RestaurantOpenHourVO rvo = null;
		DeliveryOpenHourVO dvo = null;
		for (int i = 0; i < 7; i++) {
			rvo = new RestaurantOpenHourVO();
			rvo.setSeq(1);
			rvo.setWeekDay(i);
			rvo.setStartTime(StringConvertUtil.convertString2Time("11:00"));
			rvo.setEndTime(StringConvertUtil.convertString2Time("15:00"));
			openHour1list.add(rvo);
			rvo = new RestaurantOpenHourVO();
			rvo.setSeq(2);
			rvo.setWeekDay(i);
			rvo.setStartTime(StringConvertUtil.convertString2Time("17:00"));
			rvo.setEndTime(StringConvertUtil.convertString2Time("22:00"));
			openHour2list.add(rvo);
			
			dvo = new DeliveryOpenHourVO();
			dvo.setSeq(1);
			dvo.setWeekDay(i);
			rvo.setStartTime(StringConvertUtil.convertString2Time("11:00"));
			rvo.setEndTime(StringConvertUtil.convertString2Time("15:00"));
			openHourDeliv1list.add(dvo);
			dvo = new DeliveryOpenHourVO();
			dvo.setSeq(2);
			dvo.setWeekDay(i);
			rvo.setStartTime(StringConvertUtil.convertString2Time("17:00"));
			rvo.setEndTime(StringConvertUtil.convertString2Time("22:00"));
			openHourDeliv2list.add(dvo);
		}
	}
	
	/**
	 *  Set Hour list to list1 and list2
	 *  @param list : all restaurant open hour list
	 */		
	private void setHourList(List<RestaurantOpenHourVO> hourlist) {
		int size = hourlist.size();
		for (int i =0; i < size; i++) {
			if (hourlist.get(i).getSeq() == 1) {
				openHour1list.add(hourlist.get(i));
			} else {
				openHour2list.add(hourlist.get(i));
			}
		}
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
	 * @return the openHour1list
	 */
	public List<RestaurantOpenHourVO> getOpenHour1list() {
		return openHour1list;
	}

	/**
	 * @param openHour1list the openHour1list to set
	 */
	public void setOpenHour1list(List<RestaurantOpenHourVO> openHour1list) {
		this.openHour1list = openHour1list;
	}
	/**
	 * @return the openHour2list
	 */
	public List<RestaurantOpenHourVO> getOpenHour2list() {
		return openHour2list;
	}

	/**
	 * @param openHour2list the openHour2list to set
	 */
	public void setOpenHour2list(List<RestaurantOpenHourVO> openHour2list) {
		this.openHour2list = openHour2list;
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
	public Time getBizAll1Start() {
		return bizAll1Start;
	}

	/**
	 * @param bizAll1Start the bizAll1Start to set
	 */
	public void setBizAll1Start(Time bizAll1Start) {
		this.bizAll1Start = bizAll1Start;
	}

	/**
	 * @return the bizAll1End
	 */
	public Time getBizAll1End() {
		return bizAll1End;
	}

	/**
	 * @param bizAll1End the bizAll1End to set
	 */
	public void setBizAll1End(Time bizAll1End) {
		this.bizAll1End = bizAll1End;
	}

	/**
	 * @return the bizAll2Start
	 */
	public Time getBizAll2Start() {
		return bizAll2Start;
	}

	/**
	 * @param bizAll2Start the bizAll2Start to set
	 */
	public void setBizAll2Start(Time bizAll2Start) {
		this.bizAll2Start = bizAll2Start;
	}

	/**
	 * @return the bizAll2End
	 */
	public Time getBizAll2End() {
		return bizAll2End;
	}

	/**
	 * @param bizAll2End the bizAll2End to set
	 */
	public void setBizAll2End(Time bizAll2End) {
		this.bizAll2End = bizAll2End;
	}

	/**
	 * @return the bizDelivAll1Start
	 */
	public Time getBizDelivAll1Start() {
		return bizDelivAll1Start;
	}

	/**
	 * @param bizDelivAll1Start the bizDelivAll1Start to set
	 */
	public void setBizDelivAll1Start(Time bizDelivAll1Start) {
		this.bizDelivAll1Start = bizDelivAll1Start;
	}

	/**
	 * @return the bizDelivAll1End
	 */
	public Time getBizDelivAll1End() {
		return bizDelivAll1End;
	}

	/**
	 * @param bizDelivAll1End the bizDelivAll1End to set
	 */
	public void setBizDelivAll1End(Time bizDelivAll1End) {
		this.bizDelivAll1End = bizDelivAll1End;
	}

	/**
	 * @return the bizDelivAll2Start
	 */
	public Time getBizDelivAll2Start() {
		return bizDelivAll2Start;
	}

	/**
	 * @param bizDelivAll2Start the bizDelivAll2Start to set
	 */
	public void setBizDelivAll2Start(Time bizDelivAll2Start) {
		this.bizDelivAll2Start = bizDelivAll2Start;
	}

	/**
	 * @return the bizDelivAll2End
	 */
	public Time getBizDelivAll2End() {
		return bizDelivAll2End;
	}

	/**
	 * @param bizDelivAll2End the bizDelivAll2End to set
	 */
	public void setBizDelivAll2End(Time bizDelivAll2End) {
		this.bizDelivAll2End = bizDelivAll2End;
	}

	/**
	 * @return the openHourDeliv1list
	 */
	public List<DeliveryOpenHourVO> getOpenHourDeliv1list() {
		return openHourDeliv1list;
	}

	/**
	 * @param openHourDeliv1list the openHourDeliv1list to set
	 */
	public void setOpenHourDeliv1list(List<DeliveryOpenHourVO> openHourDeliv1list) {
		this.openHourDeliv1list = openHourDeliv1list;
	}

	/**
	 * @return the openHourDeliv2list
	 */
	public List<DeliveryOpenHourVO> getOpenHourDeliv2list() {
		return openHourDeliv2list;
	}

	/**
	 * @param openHourDeliv2list the openHourDeliv2list to set
	 */
	public void setOpenHourDeliv2list(List<DeliveryOpenHourVO> openHourDeliv2list) {
		this.openHourDeliv2list = openHourDeliv2list;
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
}
