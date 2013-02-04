package com.wowtasty.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.CodeMasterDao;
import com.wowtasty.mybatis.dao.WowMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.WowMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.ValidationUtil;

/**
 * @author Hak C.
 *
 */
public class WowMasterAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Long MAX_SIZE = 2*1024*1024l;
	private static final Float MIN_PERCENT = 0.00f;
	private static final Float MAX_PERCENT = 99.99f;
	private static final String LOGO_FILENAME = "Wowtasty_logo.jpg";
	
	/** Logger */
	private static Logger logger = Logger.getLogger(WowMasterAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> provinceList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> taxList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> ynList = new ArrayList<CodeMasterVO>();
	private Map<Integer, String> dateMap = new LinkedHashMap<Integer, String>();
	
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
	
	/** Wow Master VO */
	private WowMasterVO vo = new WowMasterVO();
	
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
		ynList = codeMap.get(Constants.KEY_CD_YN);
		
		// set up payment date dropdown menu
		dateMap.put(0, "N/A");
		for (int i = 1; i < 29 ; i++) {
			dateMap.put(new Integer(i), new Integer(i).toString());
		}
		dateMap.put(99, "Last Day");
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Administrator setup page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");
		
		WowMasterDao dao = new WowMasterDao();
		vo = dao.selectBySeq(1);
		taxList = codeMap.get(Constants.KEY_CD_TAX_RATE);

		logger.info("<---Init end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert/Edit Wow Master data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");

		// Check Validation
		boolean hasError = checkValidation();
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		// Logo file upload
		upload();
		
		WowMasterDao dao = new WowMasterDao();
		int returnCnt = 0;
		if (vo.getSeq() == null || vo.getSeq() == 0) {
			// Insert
			returnCnt = dao.insert(vo);
			if (returnCnt > 0) {
				addActionMessage("Administration infomation has been inserted successfully");
			}
		} else {
			// Update
			returnCnt = dao.update(vo);
			if (returnCnt > 0) {
				addActionMessage("Administration infomation has been updated successfully");
			}
		}
		
		// Reload
		taxList = codeMap.get(Constants.KEY_CD_TAX_RATE);

		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Save Tax List
	 * @return SUCCESS
	 */
	public String saveTaxList() throws Exception {
		logger.info("<---saveTaxList start --->");
		
		// Check Validation
		boolean hasError = checkValidationTaxList();
		
		// In case of validation error, return INPUT
		if (hasError) {
			// set Tax List accordion open
			active = 1;
			return INPUT;
		}
		
		CodeMasterDao dao = new CodeMasterDao();
		int size = taxList.size();
		for (int i = 0; i < size; i++) {
			dao.update(taxList.get(i));
		}
		
		// Update Code map in the session
		Map<String, List<CodeMasterVO>> map = (Map<String, List<CodeMasterVO>>)dao.selectAll();
		// Put the code list on the single tone session
		SessionUtil.getInstance().setApplicationAttribute(Constants.KEY_SESSION_CODE_LIST, map);
		
		// Reload
		WowMasterDao wmdao = new WowMasterDao();
		vo = wmdao.selectBySeq(1);
		taxList = map.get(Constants.KEY_CD_TAX_RATE);
		
		// set Tax List accordion open
		active = 1;

		logger.info("<---saveTaxList end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidation() {
		
		boolean hasError = false;
		Float tmpFloat = 0.00f;
		Integer tmpInt = 0;
		
		// Company Name Validation Check
		if (ValidationUtil.isBlank(vo.getName())) {
			addFieldError("vo.name", getText("E0001_1", new String[]{"Company Name"}));
			hasError = true;
		}
		
		//Telephone Validation Check
		if (ValidationUtil.isBlank(vo.getTelephone())) {
			addFieldError("vo.telephone", getText("E0001_1", new String[]{"Telephone Number"}));
			hasError = true;
		} else {
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
		
		// Email1 Validation Check
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
		
		// TaxNO Validation Check
		if (ValidationUtil.isBlank(vo.getTaxNO())) {
			addFieldError("vo.taxNO", getText("E0001_1", new String[]{"Tax NO."}));
			hasError = true;
		} else {
			if (!ValidationUtil.isEmail(vo.getEmail1())) {
				addFieldError("vo.taxNO", getText("E0003_1", new String[]{"Tax NO."}));
				hasError = true;
			}
		}
		
		// Tax1 Validation Check
		tmpFloat = vo.getTax1();
		if (tmpFloat != null) {
			if (tmpFloat < MIN_PERCENT || tmpFloat > MAX_PERCENT ) {
				// 0.00 < Tax1 < 99.99
				addFieldError("vo.tax1", getText("E0008", new String[]{"Tax1", MIN_PERCENT.toString(), MAX_PERCENT.toString()}));
				hasError = true;
			}
		} else {
			addFieldError("vo.tax1", getText("E0001_1", new String[]{"Tax1"}));
			hasError = true;
		}
		
		// Tax2 Validation Check
		tmpFloat = vo.getTax2();
		if (tmpFloat != null) {
			if (tmpFloat < MIN_PERCENT || tmpFloat > MAX_PERCENT) {
				// 0.00 < Tax2 < 99.99
				addFieldError("vo.tax2", getText("E0008", new String[]{"Tax2", MIN_PERCENT.toString(), MAX_PERCENT.toString()}));
				hasError = true;
			}
		} else {
			addFieldError("vo.tax2", getText("E0001_1", new String[]{"Tax2"}));
			hasError = true;
		}
		
		// Payment Date1 Validation Check
		tmpInt = vo.getPaymentDate1();
		if (tmpInt == 0) {
			// In case of N/A
			addFieldError("vo.paymentDate1", getText("E0001_1", new String[]{"Payment Date1"}));
			hasError = true;
		}

		// Balance Holding Period Validation Check
		tmpInt = vo.getPaymentValidPeriod();
		if (tmpInt != null) {
			if (!ValidationUtil.isWholeNum(tmpInt.toString())) {
				addFieldError("vo.paymentValidPeriod", getText("E0003_1", new String[]{"Balance Holding Period"}));
				hasError = true;
			}
		} else {
			// In case of no input
			addFieldError("vo.paymentValidPeriod", getText("E0001_1", new String[]{"Balance Holding Period"}));
			hasError = true;
		}
		return hasError;
	}
	
	/**
	 * Tax List Validation check
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationTaxList() {
		boolean hasError = false;
		Float tmpNum = 0.00f;
		// Food Tax List
		int size = taxList.size();
		for (int i = 0; i < size; i++) {
			if (ValidationUtil.isBlank(taxList.get(i).getValue())) {
				addFieldError("taxList[" + i + "].value", getText("E0001_1", new String[]{"Food Tax" + (i + 1)}));
				hasError = true;
			} else {
				if (!ValidationUtil.isNum(taxList.get(i).getValue())) {
					addFieldError("taxList[" + i + "].value", getText("E0003_1", new String[]{"Food Tax" + (i + 1)}));
					hasError = true;
				} else {
					tmpNum = Float.parseFloat(taxList.get(i).getValue());
					if (tmpNum < MIN_PERCENT || tmpNum > MAX_PERCENT ) {
						// 0.00 < Tax < 99.99
						addFieldError("taxList[" + i + "].value", getText("E0008", new String[]{"Food Tax" + (i + 1), MIN_PERCENT.toString(), MAX_PERCENT.toString()}));
						hasError = true;
					}
				}
			}
		}
		return hasError;
	}
	
	/**
	 * File upload
	 */	
	private void upload() {
		// Image file
		if (imgfile != null) {
			FileUtil.writePict(imgfile, FileUtil.WOWTASTY_DIR, LOGO_FILENAME);
			vo.setLogoImagePath(LOGO_FILENAME);
		}
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
	public WowMasterVO getVo() {
		return vo;
	}

	/**
	 * @param vo the vo to set
	 */
	public void setVo(WowMasterVO vo) {
		this.vo = vo;
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
	 * @return the taxList
	 */
	public List<CodeMasterVO> getTaxList() {
		return taxList;
	}

	/**
	 * @param taxList the taxList to set
	 */
	public void setTaxList(List<CodeMasterVO> taxList) {
		this.taxList = taxList;
	}

	/**
	 * @return the ynList
	 */
	public List<CodeMasterVO> getYnList() {
		return ynList;
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
	 * @return the dateMap
	 */
	public Map<Integer, String> getDateMap() {
		return dateMap;
	}

	/**
	 * @param dateMap the dateMap to set
	 */
	public void setDateMap(Map<Integer, String> dateMap) {
		this.dateMap = dateMap;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Integer active) {
		this.active = active;
	}
}
