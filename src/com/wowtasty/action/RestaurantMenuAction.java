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
import com.wowtasty.mybatis.dao.RestaurantCategoryDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.dao.RestaurantMenuDao;
import com.wowtasty.mybatis.dao.RestaurantMenuOptionDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.RestaurantCategoryVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.mybatis.vo.RestaurantMenuOptionVO;
import com.wowtasty.mybatis.vo.RestaurantMenuVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.ValidationUtil;


/**
 * @author Hak C.
 *
 */
public class RestaurantMenuAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Long MAX_SIZE = 2*1024*1024l;
	private static final String DELIMITER = ",";
	private static final Float MIN_PRICE = 0.00f;
	private static final Float MAX_PRICE = 999.99f;
	private static final Integer MIN_INT = 1;
	private static final Integer MAX_INT = 999;
	private static final Integer MAX_LEN = 100;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantMenuAction.class);
	
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
	
	/** Parameter */
	private String selectedID = "";
	private Integer selectedMenuID = 0;
	private String categorySortStr = "";
	private String menuSortStr = "";

	/** Restaurant VO*/
	private RestaurantMasterVO restVO = new RestaurantMasterVO();
	
	/** Menu Data*/
	private RestaurantMenuVO vo = new RestaurantMenuVO();
	// the category list on category tree
	private List<RestaurantCategoryVO> categoryList = new ArrayList<RestaurantCategoryVO>();
	// the category list in the menu category dropdown
	private List<RestaurantCategoryVO> menuCategoryList = new ArrayList<RestaurantCategoryVO>();
	private List<RestaurantMenuVO> menuList = new ArrayList<RestaurantMenuVO>();
	private List<RestaurantMenuOptionVO> optionList = new ArrayList<RestaurantMenuOptionVO>();
	private List<RestaurantMenuOptionVO> menuOptionList = new ArrayList<RestaurantMenuOptionVO>();
	
	/** dropdown box list */
	private List<CodeMasterVO> taxRateList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> statusList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> naFlagList = new ArrayList<CodeMasterVO>();
	
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
		taxRateList = codeMap.get(Constants.KEY_CD_TAX_RATE);
		statusList = codeMap.get(Constants.KEY_CD_MENU_STATUS);
		naFlagList = codeMap.get(Constants.KEY_CD_NAFLAG);
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Restaurant Menu Add page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");
		if ("".equals(selectedID)) {
			return INPUT;
		}
		// load category/menu/option
		loadList();
		
		// Restaurant Master
		RestaurantMasterDao dao = new RestaurantMasterDao();
		restVO = dao.selectByID(selectedID);

		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate Restaurant Menu Edit page
	 * @return SUCCESS
	 */
	public String initEdit() throws Exception {
		logger.info("<---initEdit start --->");
		
		if ("".equals(selectedID)) {
			return INPUT;
		}
		
		// select menu info
		RestaurantMenuDao rmdao = new RestaurantMenuDao();
		vo = rmdao.selectByID(selectedID, selectedMenuID);
		
		// select menuoption list
		RestaurantMenuOptionDao rmodao = new RestaurantMenuOptionDao();
		menuOptionList = rmodao.selectByID(selectedID, selectedMenuID);
		
		logger.info("<---initEdit end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert/Edit restaurant menu data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---Execute start --->");
		
		if ("".equals(selectedID)) {
			return INPUT;
		}
		
		// Check Menu Information Validation
		boolean hasError = false;
		hasError = checkValidationMenu();
		
		// In case of validation error, return INPUT
		if (hasError) {
			// load category/menu/option
			loadList();
			return INPUT;
		}

		RestaurantMenuDao dao = new RestaurantMenuDao();
		int returnCnt = 0;
		if (selectedMenuID == 0) {
			//　Insert
			// Take maxID
			vo.setRestaurantID(selectedID);
			vo.setMenuID(dao.selectMaxID(selectedID));
			
			// Upload Images
			upload();
			
			// Set default data
			vo.setUpdateID(uservo.getMemberID());
			// Insert
			returnCnt = dao.insert(vo);
			if (returnCnt > 0) {
				addActionMessage("Restaurant menu has been inserted successfully");
			}
		} else {
			// Update
			// Upload Images
			upload();
			
			// Set default data
			vo.setUpdateID(uservo.getMemberID());
			// Update
			returnCnt = dao.update(vo);
			if (returnCnt > 0) {
				addActionMessage("Restaurant menu has been updated successfully");
			}
		}
		
		// load category/menu/option
		loadList();
		
		// In case of insert, set menuID
		selectedMenuID = vo.getMenuID();
		
		logger.info("<---Execute end --->");
		return SUCCESS;
	}
	
	/**
	 * copy restaurant menu data
	 * @return SUCCESS
	 */
	public String copyMenu() throws Exception {
		logger.info("<---copyMenu start --->");
		
		// If restaurant or menu is not selected, return
		if ("".equals(selectedID) || selectedMenuID == 0) {
			return INPUT;
		}

		RestaurantMenuDao dao = new RestaurantMenuDao();
		int returnCnt = 0;
		
		// select menu info
		vo = dao.selectByID(selectedID, selectedMenuID);

		// Take maxID
		Integer newMenuID = dao.selectMaxID(vo.getRestaurantID());
		vo.setMenuID(newMenuID);
		
		if (!"".equals(vo.getImagePath())) {
			// Copy image
			upload(selectedMenuID);
		}
		
		// Set default data
		vo.setUpdateID(uservo.getMemberID());
		
		// Insert
		returnCnt = dao.insert(vo);
		if (returnCnt > 0) {
			addActionMessage("Restaurant menu has been copied successfully");
		}
		
		// Copy Options
		RestaurantMenuOptionDao rmodao = new RestaurantMenuOptionDao();
		int size = menuOptionList.size();
		// Set menuID with new menuID
		for (int i = 0; i < size; i++) {
			menuOptionList.get(i).setMenuID(newMenuID);
		}
		rmodao.updateAll(menuOptionList, selectedID, newMenuID);
		
		// load category/menu/option
		loadList();
		
		// set menuID
		selectedMenuID = vo.getMenuID();
		
		logger.info("<---copyMenu end --->");
		return SUCCESS;
	}
	
	/**
	 * Delete restaurant menu data
	 * @return SUCCESS
	 */
	public String delMenu() throws Exception {
		logger.info("<---delMenu start --->");
		
		// If restaurant or menu is not selected, return
		if ("".equals(selectedID) || selectedMenuID == 0) {
			return INPUT;
		}

		RestaurantMenuDao dao = new RestaurantMenuDao();
		int returnCnt = 0;

		// Update delFlag = 1
		// Delete image
		deleteImg();
		
		// Set default data
		vo.setUpdateID(uservo.getMemberID());
		vo.setNaFlag(Constants.CODE_NAFLAG_NA);
		
		// Update
		returnCnt = dao.delete(vo);
		if (returnCnt > 0) {
			addActionMessage("Restaurant menu has been deleted successfully");
		}

		// load category/menu/option
		loadList();
	
		// Clear Menu info
		vo = new RestaurantMenuVO();
		
		logger.info("<---delMenu end --->");
		return SUCCESS;
	}
	
	/**
	 * save Restaurant Menu Tree
	 * @return SUCCESS
	 */
	public String saveMenuTree() throws Exception {
		logger.info("<---saveMenuTree start --->");
		
		if ("".equals(selectedID)) {
			return INPUT;
		}
		
		String[] categorySortArray = categorySortStr.split(DELIMITER);
		String[] menuSortArray = menuSortStr.split(DELIMITER);
		String id = "";
		Integer categorySort = 1;
		Integer menuSort = 1;
		
		// Set category sort
		for (int i = 0; i < categorySortArray.length; i++) {
			id = categorySortArray[i];
			if (!"".equals(id) && ValidationUtil.isWholeNum(id)) {
				// Set category sort
				categoryList.get(Integer.parseInt(id)).setSort(categorySort++);
			}				
		}
		
		// Check Category Information Validation
		boolean hasError = false;
		hasError = checkValidationCategory(categoryList);
		
		// In case of validation error, return INPUT
		if (hasError) {
			// load category list 
			RestaurantCategoryDao rcdao = new RestaurantCategoryDao();
			menuCategoryList = rcdao.selectByRestaurant(selectedID);
			return INPUT;
		}
		
		// Set menu sort
		for (int i = 0; i < menuSortArray.length; i++) {
			id = menuSortArray[i];
			if (!"".equals(id) && ValidationUtil.isWholeNum(id)) {
				// Set menu sort
				menuList.get(Integer.parseInt(id)).setSort(menuSort++);
			}				
		}
		
		// Update(Delete and Insert) Category
		int size = categoryList.size();
		RestaurantCategoryDao rcdao = new RestaurantCategoryDao();
		for (int i = 0; i < size; i++) {
			if (categoryList.get(i) == null) {
				// Delete removed category
				categoryList.remove(i--);
				size--;
			}
		}
		// Update(Delete and Insert)
		rcdao.updateAll(categoryList, selectedID);
		
		// Update Menu
		size = menuList.size();
		RestaurantMenuDao rmdao = new RestaurantMenuDao();
		for (int i = 0; i < size; i++) {
			menuList.get(i).setUpdateID(uservo.getMemberID());
			rmdao.update(menuList.get(i));
		}
		
		// load category/menu/option
		loadList();
		
		addActionMessage("Restaurant category and menu has been updated successfully");
		
		logger.info("<---saveMenuTree end --->");
		return SUCCESS;
	}
	
	/**
	 * save Restaurant Menu Option
	 * @return SUCCESS
	 */
	public String saveMenuOption() throws Exception {
		logger.info("<---saveMenuOption start --->");
	
		if ("".equals(selectedID)) {
			return INPUT;
		}
		
		// Check Category Information Validation
		boolean hasError = false;
		hasError = checkValidationOption(menuOptionList);
		
		// In case of validation error, return INPUT
		if (hasError) {
			// load category list 
			RestaurantCategoryDao rcdao = new RestaurantCategoryDao();
			menuCategoryList = rcdao.selectByRestaurant(selectedID);
			active = 1;
			return INPUT;
		}
		
		// Save Options
		int size = menuOptionList.size();
		RestaurantMenuOptionDao rmodao = new RestaurantMenuOptionDao();
		for (int i = 0; i < size; i++) {
			if (menuOptionList.get(i) != null) {
				menuOptionList.get(i).setUpdateID(uservo.getMemberID());
				if ("".equals(menuOptionList.get(i).getRestaurantID())) {
					// Set up RestaurantID, MenuID
					menuOptionList.get(i).setRestaurantID(selectedID);
					menuOptionList.get(i).setMenuID(selectedMenuID);
				}				
			} else {
				// Delete removed menu option
				menuOptionList.remove(i--);
				size--;
			}
		}
		
		// Delete all and insert all
		rmodao.updateAll(menuOptionList, selectedID, selectedMenuID);
		
		// load category/menu/option
		loadList();
		
		// set Option accordion open
		active = 1;
		
		addActionMessage("Restaurant menu options have been updated successfully");
		
		logger.info("<---saveMenuOption end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check(Menu information)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationMenu() {
		
		boolean hasError = false;
		Float tmpAmt = 0.00f;
		Integer tmpInt = 0;
		// Category Validation Check
		if (vo.getCategoryID() == 0) {
			// Category is required
			addFieldError("vo.categoryID", getText("E0001_1", new String[]{"Category"}));
			hasError = true;
		}
		
		// Name Validation Check
		if (ValidationUtil.isBlank(vo.getName())) {
			// Name is required
			addFieldError("vo.name", getText("E0001_1", new String[]{"Name"}));
			hasError = true;
		}
		
		// Price Validation Check
		tmpAmt = vo.getPrice();
		if (tmpAmt != null) {
			if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
				// 0 < Price < 99.99
				addFieldError("vo.price", getText("E0008", new String[]{"Price", MIN_PRICE.toString(), MIN_PRICE.toString()}));
				hasError = true;
			}
		} else {
			addFieldError("vo.price", getText("E0001_1", new String[]{"Price"}));
			hasError = true;
		}
		
		// Sort Validation Check
		tmpInt = vo.getSort();
		if (tmpInt != null) {
			if (tmpInt < MIN_INT || tmpInt > MAX_INT ) {
				// 0 < Price < 999
				addFieldError("vo.sort", getText("E0008", new String[]{"Sort", MIN_PRICE.toString(), MAX_PRICE.toString()}));
				hasError = true;
			}
		} else {
			// Sort is required
			addFieldError("vo.sort", getText("E0001_1", new String[]{"Sort"}));
			hasError = true;
		}
		
		//Description Validation Check
		if (!ValidationUtil.isBlank(vo.getDescription()) && vo.getDescription().length() > MAX_LEN) {
			addFieldError("vo.description", getText("E0006", new String[]{"Menu Description", MAX_LEN.toString()}));
			hasError = true;
		}
	
		//Logo Image Validation
		if (imgfile != null) {
			// File size MAX_SIZE
			if (imgfile.length() > MAX_SIZE) {
				addFieldError("vo.imagePath", getText("E0006", new String[]{"Image File File size", MAX_SIZE.toString()}));
				hasError = true;
			}
			// File type
			if (!imgfileContentType.startsWith("image/")) {
				addFieldError("vo.imagePath", getText("E0003_1", new String[]{"Image File File"}));
				hasError = true;
			}
		}
		return hasError;
	}
	
	/**
	 * Validation check(Category information)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationCategory(List<RestaurantCategoryVO> list) {
		boolean hasError = false;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				// Category Name Validation Check
				if (ValidationUtil.isBlank(list.get(i).getName())) {
					// Category Name is required
					addFieldError("categoryName[" + i + "]", getText("E0001_1", new String[]{"Category Name[" + (i + 1) + "]"}));
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
	 * Validation check(Menu Option information)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationOption(List<RestaurantMenuOptionVO> list) {
		boolean hasError = false;
		int size = list.size();
		Float tmpAmt = 0.00f;
		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				// Menu Option Name Validation Check
				if (ValidationUtil.isBlank(list.get(i).getName())) {
					// Option Name is required
					addFieldError("menuOptionList[" + i + "].name", getText("E0001_1", new String[]{"Option Name[" + (i + 1) + "]"}));
					hasError = true;
				}
				
				// Extra Charge Validation Check
				tmpAmt = list.get(i).getExtraCharge();
				if (tmpAmt != null) {
					if (tmpAmt < MIN_PRICE || tmpAmt > MAX_PRICE ) {
						// 0 < Extra Charge < 999.99
						addFieldError("menuOptionList[" + i + "].extraCharge", getText("E0008", new String[]{"Option Extra Charge[" + (i + 1) + "]", MIN_PRICE.toString(), MAX_PRICE.toString()}));
						hasError = true;
					}
				} else {
					// Extra Charge is required
					addFieldError("menuOptionList[" + i + "].extraCharge", getText("E0001_1", new String[]{"Option Extra Charge[" + (i + 1) + "]"}));
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
		// Image file
		if (imgfile != null) {
			sb.append(vo.getRestaurantID()).append("_").append(vo.getMenuID()).append(".jpg");
			FileUtil.writePict(imgfile, FileUtil.MENU_DIR, sb.toString());
			vo.setImagePath(sb.toString());
		}
	}
	
	/**
	 * Copy Image
	 * @param id : original menu ID
	 */	
	private void upload(Integer id) {
		StringBuilder sb = new StringBuilder();
		String inFileName = "";
		String copyFileName = "";
		// Original menu path
		inFileName = sb.append(vo.getRestaurantID()).append("_").append(id).append(".jpg").toString();
		// Copy menu path
		sb.setLength(0);
		copyFileName = sb.append(vo.getRestaurantID()).append("_").append(vo.getMenuID()).append(".jpg").toString();
		FileUtil.copyPict(inFileName, FileUtil.MENU_DIR, copyFileName);
		vo.setImagePath(sb.toString());
	}
	
	/**
	 * Delete Menu Image
	 */	
	private void deleteImg() {
		StringBuilder sb = new StringBuilder();
		sb.append(vo.getRestaurantID()).append("_").append(vo.getMenuID()).append(".jpg");
		FileUtil.deletePict(FileUtil.MENU_DIR, sb.toString());
	}
	
	/**
	 * Category/Menu/Option List
	 */	
	private void loadList() {
		// select category list 
		RestaurantCategoryDao rcdao = new RestaurantCategoryDao();
		categoryList = rcdao.selectByRestaurant(selectedID);
		// Copy category list
		RestaurantCategoryVO copyVO = null;
		for (RestaurantCategoryVO vo : categoryList) {
			copyVO = new RestaurantCategoryVO();
			copyVO.setCategoryID(vo.getCategoryID());
			copyVO.setSort(vo.getSort());
			copyVO.setName(vo.getName());
			copyVO.setRestaurantID(vo.getRestaurantID());
			menuCategoryList.add(copyVO);
		}
		
		// select menu list 
		RestaurantMenuDao rmdao = new RestaurantMenuDao();
		menuList = rmdao.selectByRestaurant(selectedID);
	
		// select option list 
		RestaurantMenuOptionDao rmodao = new RestaurantMenuOptionDao();
		optionList = rmodao.selectByRestaurant(selectedID);
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
	 * @return the selectedMenuID
	 */
	public Integer getSelectedMenuID() {
		return selectedMenuID;
	}

	/**
	 * @param selectedMenuID the selectedMenuID to set
	 */
	public void setSelectedMenuID(Integer selectedMenuID) {
		this.selectedMenuID = selectedMenuID;
	}

	/**
	 * @return the vo
	 */
	public RestaurantMenuVO getVo() {
		return vo;
	}

	/**
	 * @param vo the vo to set
	 */
	public void setVo(RestaurantMenuVO vo) {
		this.vo = vo;
	}

	/**
	 * @return the categoryList
	 */
	public List<RestaurantCategoryVO> getCategoryList() {
		return categoryList;
	}

	/**
	 * @return the optionList
	 */
	public List<RestaurantMenuOptionVO> getOptionList() {
		return optionList;
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
	 * @return the taxRateList
	 */
	public List<CodeMasterVO> getTaxRateList() {
		return taxRateList;
	}

	/**
	 * @return the statusList
	 */
	public List<CodeMasterVO> getStatusList() {
		return statusList;
	}

	/**
	 * @return the menuList
	 */
	public List<RestaurantMenuVO> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<RestaurantMenuVO> menuList) {
		this.menuList = menuList;
	}

	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(List<RestaurantCategoryVO> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @param optionList the optionList to set
	 */
	public void setOptionList(List<RestaurantMenuOptionVO> optionList) {
		this.optionList = optionList;
	}

	/**
	 * @param categorySortStr the categorySortStr to set
	 */
	public void setCategorySortStr(String categorySortStr) {
		this.categorySortStr = categorySortStr;
	}

	/**
	 * @param menuSortStr the menuSortStr to set
	 */
	public void setMenuSortStr(String menuSortStr) {
		this.menuSortStr = menuSortStr;
	}

	/**
	 * @return the naFlagList
	 */
	public List<CodeMasterVO> getNaFlagList() {
		return naFlagList;
	}

	/**
	 * @return the menuOptionList
	 */
	public List<RestaurantMenuOptionVO> getMenuOptionList() {
		return menuOptionList;
	}

	/**
	 * @param menuOptionList the menuOptionList to set
	 */
	public void setMenuOptionList(List<RestaurantMenuOptionVO> menuOptionList) {
		this.menuOptionList = menuOptionList;
	}

	/**
	 * @return the restVO
	 */
	public RestaurantMasterVO getRestVO() {
		return restVO;
	}

	/**
	 * @param restVO the restVO to set
	 */
	public void setRestVO(RestaurantMasterVO restVO) {
		this.restVO = restVO;
	}

	/**
	 * @return the active
	 */
	public Integer getActive() {
		return active;
	}

	public List<RestaurantCategoryVO> getMenuCategoryList() {
		return menuCategoryList;
	}

	public void setMenuCategoryList(List<RestaurantCategoryVO> menuCategoryList) {
		this.menuCategoryList = menuCategoryList;
	}
}
