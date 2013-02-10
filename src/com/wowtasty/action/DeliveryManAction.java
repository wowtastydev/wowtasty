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
import com.wowtasty.mybatis.dao.DeliveryManDao;
import com.wowtasty.mybatis.dao.DeliveryMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.DeliveryManVO;
import com.wowtasty.mybatis.vo.DeliveryMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.ValidationUtil;

/**
 * @author Hak C.
 *
 */
public class DeliveryManAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(DeliveryManAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> provinceList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<DeliveryMasterVO> deliveryCompanyList = new ArrayList<DeliveryMasterVO>();
	
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
	
	/** Delivery Man VO Columns */
	private DeliveryManVO vo = new DeliveryManVO();
	private List<DeliveryManVO> list = new ArrayList<DeliveryManVO>();
	
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

		// Search delivery company list 
		DeliveryMasterDao dmdao = new DeliveryMasterDao();
		deliveryCompanyList = dmdao.selectAll();
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Delivery Man Information page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");

		DeliveryManDao dao = new DeliveryManDao();
		list = dao.selectByCompany(Constants.CONSTANT_5DELIVERY_ID);
		
		// Default Company is 5delivery
		vo.setDeliveryCompanyID("D00001");
		
		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert/Edit Delivery Man data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		
		DeliveryManDao dao = new DeliveryManDao();
		
		// Check Delivery Man Validation
		boolean hasError = false;
		hasError = checkValidation();
		
		// In case of validation error, return INPUT
		if (hasError) {
			// Reload Delivery Man list
			list = dao.selectByCompany(Constants.CONSTANT_5DELIVERY_ID);
			// set Delivery man detail accordion open
			active = 1;
			return INPUT;
		}

		int returnCnt = 0;
		
		if (vo.getDeliverymanID() == 0) {
			//　Insert
			// Take maxID
			vo.setDeliverymanID(dao.selectMaxID(vo.getDeliveryCompanyID()));
			
			// Set default data
			vo.setUpdateID(uservo.getMemberID());
			
			// Insert Masterdata
			returnCnt = dao.insert(vo);
			if (returnCnt > 0) {
				addActionMessage("DeliveryMan infomation has been inserted successfully");
			}
		} else {
			// Update
			// Set default data
			vo.setUpdateID(uservo.getMemberID());
			
			// Update
			returnCnt = dao.update(vo);
			if (returnCnt > 0) {
				addActionMessage("DeliveryMan infomation has been updated successfully");
			}
		}
		
		// Reload Delivery Man list
		list = dao.selectByCompany(Constants.CONSTANT_5DELIVERY_ID);
		
		// set Delivery man detail accordion open
		active = 1;
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Delete Delivery Man
	 * @return SUCCESS
	 */
	public String delete() throws Exception {
		logger.info("<---delete start --->");
		
		DeliveryManDao dao = new DeliveryManDao();

		vo.setNaFlag(Constants.CODE_NAFLAG_NA);
		int returnCnt = dao.update(vo);
		
		if (returnCnt > 0) {
			addActionMessage("DeliveryMan infomation has been deleted successfully");
		}
		
		// Reload Delivery Man list
		list = dao.selectByCompany(Constants.CONSTANT_5DELIVERY_ID);
		
		// Clear data
		vo = new DeliveryManVO();
		
		logger.info("<---delete end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check(DeliveryMan Information)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidation() {
		
		boolean hasError = false;
	
		// Delivery Man First Name Validation Check
		if (ValidationUtil.isBlank(vo.getFirstName())) {
			addFieldError("vo.firstName", getText("E0001_1", new String[]{"DeliveryMan first Name"}));
			hasError = true;
		}
		
		// Delivery Man Last Name Validation Check
		if (ValidationUtil.isBlank(vo.getLastName())) {
			addFieldError("vo.lastName", getText("E0001_1", new String[]{"DeliveryMan last Name"}));
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
		
		//Email1 Validation Check
		if (ValidationUtil.isBlank(vo.getEmail())) {
			addFieldError("vo.email", getText("E0001_1", new String[]{"Email"}));
			hasError = true;
		} else {
			if (!ValidationUtil.isEmail(vo.getEmail())) {
				addFieldError("vo.email", getText("E0003_1", new String[]{"Email"}));
				hasError = true;
			}			
		}
		
		//Suite Number Validation Check
		if (!ValidationUtil.isBlank(vo.getSuite()) && !ValidationUtil.isNumEng(vo.getSuite())) {
			addFieldError("vo.suite", getText("E0003_1", new String[]{"Suite Number"}));
			hasError = true;
		}
		
		//Postal Code Validation Check
		if (!ValidationUtil.isBlank(vo.getPostalCode()) && !ValidationUtil.isPostalCode(vo.getPostalCode())) {
			addFieldError("vo.postalCode", getText("E0003_1", new String[]{"Postal Code"}));
			hasError = true;
		}
		return hasError;
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
	public DeliveryManVO getVo() {
		return vo;
	}

	/**
	 * @param vo the vo to set
	 */
	public void setVo(DeliveryManVO vo) {
		this.vo = vo;
	}

	/**
	 * @return the list
	 */
	public List<DeliveryManVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<DeliveryManVO> list) {
		this.list = list;
	}

	/**
	 * @return the deliveryCompanyList
	 */
	public List<DeliveryMasterVO> getDeliveryCompanyList() {
		return deliveryCompanyList;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}
}
