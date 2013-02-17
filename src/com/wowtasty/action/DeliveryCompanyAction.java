package com.wowtasty.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.DeliveryAreaDao;
import com.wowtasty.mybatis.dao.DeliveryMasterDao;
import com.wowtasty.mybatis.dao.DeliveryOpenHourDao;
import com.wowtasty.mybatis.dao.RestaurantOpenHourDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.DeliveryAreaVO;
import com.wowtasty.mybatis.vo.DeliveryMasterVO;
import com.wowtasty.mybatis.vo.DeliveryOpenHourVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.StringConvertUtil;
import com.wowtasty.util.ValidationUtil;
import com.wowtasty.vo.HourListVO;

/**
 * @author Hak C.
 *
 */
public class DeliveryCompanyAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Long MAX_SIZE = 2*1024*1024l;
	private static final String EVERYDAY = "0";
	private static final Integer FIRST_OPEN_HOUR = 1;
	private static final Integer SECOND_OPEN_HOUR = 2;
	private static final Integer DAY_SIZE = 7;
	private static final Float MIN_PRICE = 0.00f;
	private static final Float MAX_PRICE = 999.99f;
	private static final Float MIN_COMMISSION = 0.00f;
	private static final Float MAX_COMMISSION = 100.00f;
	private static final Integer ALL = 9;
	private static final Integer MASTER = 0;
	private static final Integer OPENHOUR = 1;
	private static final Integer AREA = 2;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(DeliveryCompanyAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> provinceList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> dayTypeList = new ArrayList<CodeMasterVO>();
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
	
	/** Delivery Master Vos Columns */
	private DeliveryMasterVO vo = new DeliveryMasterVO();
	private List<HourListVO> openHourlist = new ArrayList<HourListVO>();
	private List<DeliveryAreaVO> delivArealist = new ArrayList<DeliveryAreaVO>();

	/** Parameter */
	private String selectedID = "D00001";
	private String bizHourType = ""; // 0:Every day 1:Set by day
	private String bizHourDelivType = ""; // 0:Every day 1:Set by day
	
	private String bizAll1Start = "";
	private String bizAll1End = "";
	private String bizAll2Start = "";
	private String bizAll2End = "";
	
	/** Files upload */
	private File imgfile = null;
	private String imgfileContentType = "";
	private String imgfileFileName = "";

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
		provinceList = codeMap.get(Constants.KEY_CD_PROVINCE);
		cityList = codeMap.get(Constants.KEY_CD_CITY);
		dayTypeList = codeMap.get(Constants.KEY_CD_DAY_TYPE);
		cityMapList = codeMap.get(Constants.KEY_CD_CITY_MAP);
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Delivery Company General Information page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");

		DeliveryMasterDao dao = new DeliveryMasterDao();
		vo = dao.selectByID(selectedID);
		
		// Select open hours
		DeliveryOpenHourDao dodao = new DeliveryOpenHourDao();
		openHourlist = dodao.selectHourListByID(selectedID);
		if (openHourlist.size() == 0) {
			// In case no open hours, add empty open hour list
			addOpenHours();
		}
		
		// Select Delivery areas
		DeliveryAreaDao dadao = new DeliveryAreaDao();
		delivArealist = dadao.selectByID(selectedID);
		
		// set MASTER accordion open
		active = MASTER;

		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert/Edit restaurant info data
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
			return INPUT;
		}

		DeliveryMasterDao dao = new DeliveryMasterDao();
		int returnCnt = 0;
		
		if ("".equals(vo.getDeliveryCompanyID())) {
			//　Insert
			// Take maxID
			vo.setDeliveryCompanyID(selectedID);
			
			// Upload Images
			upload();
			
			// Set default data
			vo.setStatus(Constants.CODE_STATUS_OPEN);
			vo.setUpdateID(uservo.getMemberID());
			
			// Insert Masterdata
			returnCnt = dao.insert(vo);
			if (returnCnt > 0) {
				addActionMessage("Delivery infomation has been inserted successfully");
			}
			// Add empty open hours
			addOpenHours();
		} else {
			// Update
			// Upload Images
			upload();
					
			// Set default data
			vo.setUpdateID(uservo.getMemberID());
			// Update
			returnCnt = dao.update(vo);
			if (returnCnt > 0) {
				addActionMessage("Delivery infomation has been updated successfully");
			}
			
			// Select open hours
			RestaurantOpenHourDao odao = new RestaurantOpenHourDao();
			openHourlist = odao.selectByID(vo.getDeliveryCompanyID());
			if (openHourlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addOpenHours();
			}
		}
		
		// Reload All Restaurant Information
		reload(ALL);
		
		// set MASTER accordion open
		active = MASTER;
		
		// set parameter restaurantID
		selectedID = vo.getDeliveryCompanyID();
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Save Business Hours
	 * @return SUCCESS
	 */
	public String saveBizHour() throws Exception {
		logger.info("<---saveBizHour start --->");
		if ("".equals(vo.getDeliveryCompanyID())) {
			addActionError("Please save Delivery General Information first.");
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
			return INPUT;
		}
				
		DeliveryOpenHourDao dao = new DeliveryOpenHourDao();
		boolean isInsert = true;
		String id = "";
		// In case of Every day 
		if (EVERYDAY.equals(bizHourType)) {
			DeliveryOpenHourVO ovo = null;
			
			for (int i = 0; i < DAY_SIZE; i++) {
				// Check wheather insert or update
				if ("".equals(openHourlist.get(i).getID())) {
					id = vo.getDeliveryCompanyID();
				} else {
					id = openHourlist.get(i).getID();
					isInsert = false;
				}
				
				// First Open Hour
				ovo = new DeliveryOpenHourVO();
				ovo.setDeliveryCompanyID(id);
				ovo.setSeq(FIRST_OPEN_HOUR);
				ovo.setWeekDay(i);
				ovo.setStartTime(StringConvertUtil.convertString2Time(bizAll1Start));
				ovo.setEndTime(StringConvertUtil.convertString2Time(bizAll1End));
				
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
				ovo.setStartTime(StringConvertUtil.convertString2Time(bizAll2Start));
				ovo.setEndTime(StringConvertUtil.convertString2Time(bizAll2End));
				
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
				if ("".equals(openHourlist.get(i).getID())) {
					id = vo.getDeliveryCompanyID();
				} else {
					id = openHourlist.get(i).getID();
					isInsert = false;
				}
				
				// First Open Hour
				ovo = new DeliveryOpenHourVO();
				ovo.setDeliveryCompanyID(id);
				ovo.setSeq(FIRST_OPEN_HOUR);
				ovo.setWeekDay(openHourlist.get(i).getWeekDay());
				ovo.setStartTime(StringConvertUtil.convertString2Time(openHourlist.get(i).getStartTime1()));
				ovo.setEndTime(StringConvertUtil.convertString2Time(openHourlist.get(i).getEndTime1()));

				if (isInsert) {
					// In case insert, Delivery Company ID is ""
					dao.insert(ovo);
				} else {
					// Update
					dao.update(ovo);
				}
				
				// Second Open Hour
				ovo = new DeliveryOpenHourVO();
				ovo.setDeliveryCompanyID(id);
				ovo.setSeq(SECOND_OPEN_HOUR);
				ovo.setWeekDay(openHourlist.get(i).getWeekDay());
				ovo.setStartTime(StringConvertUtil.convertString2Time(openHourlist.get(i).getStartTime2()));
				ovo.setEndTime(StringConvertUtil.convertString2Time(openHourlist.get(i).getEndTime2()));

				if (isInsert) {
					// In case insert, Delivery Company is ""
					dao.insert(ovo);
				} else {
					// Update
					dao.update(ovo);
				}
			}
		}

		addActionMessage("Delivery Company Business Hours has been updated successfully");
		
		// Reload All information
		reload(ALL);
		
		// set OPENHOUR accordion open
		active = OPENHOUR;
		
		// set parameter Delivery Company ID
		selectedID = vo.getDeliveryCompanyID();

		logger.info("<---saveBizHour end --->");
		return SUCCESS;
	}
	
	/**
	 * Save delivery areas
	 * @return SUCCESS
	 */
	public String saveDeliveryArea() throws Exception {
		logger.info("<---saveDeliveryArea start --->");
		
		// Set new Delivery Area
		int size = delivArealist.size();
		List<DeliveryAreaVO> newlist = new ArrayList<DeliveryAreaVO>();

		int sizeNew = 0;
		boolean hasPrefix = false;
		DeliveryAreaVO davo = null;
		
		for (int i = 0; i < size; i++) {
			hasPrefix = false;
			davo = delivArealist.get(i);
			if (davo != null) {
				// Compare same prefix with new list
				sizeNew = newlist.size();
				for (int j = 0; j < sizeNew; j++) {
					if (davo.getPostalPrefix().toUpperCase().equals(newlist.get(j).getPostalPrefix().toUpperCase())) {
						// If there is same prefix already, don't insert.
						hasPrefix = true;
						break;
					}
				}
				
				if (!hasPrefix) {
					// Add area to newlist
					newlist.add(davo);
				}
			}
		}
		
		// Validation Check
		// Check Delivery Area Validation
		boolean hasError = false;
		hasError = checkValidationDelivArea(newlist);
		
		// In case of validation error, return INPUT
		if (hasError) {
			// Reload Restaurant Information
			reload(AREA);
			return INPUT;
		}
		
		// Delete all areas and insert inputted areas
		DeliveryAreaDao dadao = new DeliveryAreaDao();
		dadao.updateAll(newlist, vo.getDeliveryCompanyID());
		
		addActionMessage("Delivery Areas has been updated successfully");

		// Reload Restaurant Information
		reload(ALL);
		
		// set AREA accordion open
		active = AREA;
		
		// set parameter Delivery company ID
		selectedID = vo.getDeliveryCompanyID();
		
		logger.info("<---saveDeliveryArea end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check(Delivery Company Information)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationInfo() {
		
		boolean hasError = false;
		Float tmpAmt = 0.00f;
	
		// Delivery Company Name Validation Check
		if (ValidationUtil.isBlank(vo.getName())) {
			addFieldError("vo.name", getText("E0001_1", new String[]{"Delivery Company Name"}));
			hasError = true;
		}
		
		// Telephone Validation Check
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

		// Address Validation Check
		if (ValidationUtil.isBlank(vo.getAddress())) {
			addFieldError("vo.address", getText("E0001_1", new String[]{"Address"}));
			hasError = true;
		}
		
		// City Validation Check
		if (ValidationUtil.isBlank(vo.getCity())) {
			addFieldError("vo.city", getText("E0001_1", new String[]{"City"}));
			hasError = true;
		}
		
		// Province Validation Check
		if (ValidationUtil.isBlank(vo.getProvince())) {
			addFieldError("vo.province", getText("E0001_1", new String[]{"Province"}));
			hasError = true;
		}
		
		// Suite Number Validation Check
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
		if (imgfile != null) {
			// File size MAX_SIZE
			if (imgfile.length() > MAX_SIZE) {
				addFieldError("vo.logoImagePath", getText("E0006", new String[]{"Logo File size", MAX_SIZE.toString()}));
				hasError = true;
			}
			// File type
			if (!imgfileContentType.startsWith("image/")) {
				addFieldError("vo.logoImagePath", getText("E0003_1", new String[]{"Logo File"}));
				hasError = true;
			}
		}
	
		//Commission Validation Check
		tmpAmt = vo.getCommission();
		if (tmpAmt != null) {
			if (tmpAmt < MIN_COMMISSION || tmpAmt > MAX_COMMISSION ) {
				// 0 < Commission < 99.99
				addFieldError("vo.commission", getText("E0008", new String[]{"Commission", MIN_COMMISSION.toString(), MAX_COMMISSION.toString()}));
				hasError = true;
			}
		} else {
			addFieldError("vo.commission", getText("E0001_1", new String[]{"Commission"}));
			hasError = true;
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
						addFieldError("openHourlist[" + i + "].startTime1", getText("E0001_1", new String[]{"Open1(From)"}));
						hasError = true;
					}
					if (ValidationUtil.isBlank(openHourlist.get(i).getEndTime1())) {
						// Open1(To) is required
						addFieldError("openHourlist[" + i + "].endTime1", getText("E0001_1", new String[]{"Open1(To)"}));
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
	private boolean checkValidationDelivArea(List<DeliveryAreaVO> list) {
		
		boolean hasError = false;
		int size = list.size();
		String tmpStr = "";
		Float tmpAmt = 0.00f;
		for (int i = 0; i < size; i++) {
			// Prefix Validation Check
			tmpStr = list.get(i).getPostalPrefix();
			if (ValidationUtil.isBlank(tmpStr)) {
				// Prefix is required
				addFieldError("delivArealist[" + i + "].postalPrefix", getText("E0001_1", new String[]{"Postal Code Prefix"}));
				hasError = true;
			} else {
				if (!ValidationUtil.isNumEng(tmpStr)) {
					// Prefix is NumEng
					addFieldError("delivArealist[" + i + "].postalPrefix", getText("E0003_1", new String[]{"Postal Code Prefix"}));
					hasError = true;
				}
			}
			
			// Min Price Validation Check
			tmpAmt = list.get(i).getMinPrice();
			if (tmpAmt != null) {
				if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
					// 0 < Min Price < 999.99
					addFieldError("delivArealist[" + i + "].minPrice", getText("E0008", new String[]{"Min. Price", MIN_PRICE.toString(), MAX_PRICE.toString()}));
					hasError = true;
				}
			} else {
				// Min Price is required
				addFieldError("delivArealist[" + i + "].minPrice", getText("E0001_1", new String[]{"Min. Price"}));
				hasError = true;
			}
			
			// Delivery Fee Validation Check
			tmpAmt = list.get(i).getDeliveryFee();
			if (tmpAmt != null) {
				if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
					// 0 < Delivery Fee < 999.99
					addFieldError("delivArealist[" + i + "].deliveryFee", getText("E0008", new String[]{"Delivery Fee", MIN_PRICE.toString(), MAX_PRICE.toString()}));
					hasError = true;
				}
			} else {
				// Delivery Fee is required
				addFieldError("delivArealist[" + i + "].deliveryFee", getText("E0001_1", new String[]{"Delivery Fee"}));
				hasError = true;
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
		if (imgfile != null) {
			sb.append(vo.getDeliveryCompanyID()).append("_logo.jpg");
			FileUtil.writePict(imgfile, FileUtil.DELIVERY_DIR, sb.toString());
			vo.setLogoImagePath(sb.toString());
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
	 *  Reload Delivery Company information
	 *  @param type: reload type ALL, AREA, DELIVHOUR, OPENHOUR, MASTER
	 */		
	private void reload(Integer type) {

		// Reload Delivery Master except case of saving master
		if (type != MASTER) {
			DeliveryMasterDao mdao = new DeliveryMasterDao();
			vo = mdao.selectByID(vo.getDeliveryCompanyID());		
		}

		
		// Reload open hours except case of saving open hours 
		if (type != OPENHOUR) {
			DeliveryOpenHourDao dodao = new DeliveryOpenHourDao();
			openHourlist = dodao.selectHourListByID(vo.getDeliveryCompanyID());
			if (openHourlist.size() == 0) {
				// In case no open hours, add empty open hour list
				addOpenHours();
			}
		}
		
		// Reload delivery areas except case of saving delivery areas	
		if (type != AREA) {
			// Select Delivery areas
			DeliveryAreaDao rddao = new DeliveryAreaDao();
			delivArealist = rddao.selectByID(vo.getDeliveryCompanyID());
		}
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
	public DeliveryMasterVO getVo() {
		return vo;
	}

	/**
	 * @param vo the vo to set
	 */
	public void setVo(DeliveryMasterVO vo) {
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
	 * @return the imgfile
	 */
	public File getImgfile() {
		return imgfile;
	}

	/**
	 * @param imgfile the imgfile to set
	 */
	public void setImgfile(File imgfile) {
		this.imgfile = imgfile;
	}

	/**
	 * @return the imgfileContentType
	 */
	public String getImgfileContentType() {
		return imgfileContentType;
	}

	/**
	 * @param imgfileContentType the imgfileContentType to set
	 */
	public void setImgfileContentType(String imgfileContentType) {
		this.imgfileContentType = imgfileContentType;
	}

	/**
	 * @return the imgfileFileName
	 */
	public String getImgfileFileName() {
		return imgfileFileName;
	}

	/**
	 * @param imgfileFileName the imgfileFileName to set
	 */
	public void setImgfileFileName(String imgfileFileName) {
		this.imgfileFileName = imgfileFileName;
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
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * @return the cityMapList
	 */
	public List<CodeMasterVO> getCityMapList() {
		return cityMapList;
	}
}
