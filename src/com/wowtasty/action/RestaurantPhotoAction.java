package com.wowtasty.action;

import java.io.File;
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
import com.wowtasty.mybatis.vo.RestaurantPictVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.ValidationUtil;




/**
 * @author Hak C.
 *
 */
public class RestaurantPhotoAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantPhotoAction.class);
	
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
	private RestaurantPictVO pictvo = new RestaurantPictVO();
	private String selectedID = "";
	
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
		} else {
			
			// Upload Images
			upload();
						
			// Update
			dao = new RestaurantMasterDao();
			returnCnt = dao.update(vo);
			if (returnCnt > 0) {
				addActionMessage("Restaurant infomation has been updated successfully");
			}
		}

		logger.info("<---execute end --->");
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
	 * @return the pictvo
	 */
	public RestaurantPictVO getPictvo() {
		return pictvo;
	}

	/**
	 * @param pictvo the pictvo to set
	 */
	public void setPictvo(RestaurantPictVO pictvo) {
		this.pictvo = pictvo;
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
	
}
