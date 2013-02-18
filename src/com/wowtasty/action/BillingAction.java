package com.wowtasty.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
import com.wowtasty.mybatis.dao.OrderDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.dao.WowMasterDao;
import com.wowtasty.mybatis.vo.BillDetailVO;
import com.wowtasty.mybatis.vo.BillMasterVO;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.OrderMasterVO;
import com.wowtasty.mybatis.vo.OrderMenuVO;
import com.wowtasty.mybatis.vo.OrderRestaurantVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.mybatis.vo.WowMasterVO;
import com.wowtasty.util.CodeUtil;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.StringUtil;
import com.wowtasty.vo.BillingOrderListConditionVO;
import com.wowtasty.vo.BillingOrderListVO;


/**
 * @author Hak C.
 *
 */
public class BillingAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** constants */
	private static final String DAY_PATTERN_MMM = "dd MMM yyyy";
	
	/** Logger */
	private static Logger logger = Logger.getLogger(BillingAction.class);
	
	/** dropdown box list */
	private List<RestaurantMasterVO> restaurantList = new ArrayList<RestaurantMasterVO>();
	private List<CodeMasterVO> semiMonthTypeList = new ArrayList<CodeMasterVO>();
	
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
	private BillingOrderListConditionVO condition = new BillingOrderListConditionVO();
	
	/** Searched List */
	private List<BillingOrderListVO> list = new ArrayList<BillingOrderListVO>();
	
	/** Parameter */
	private String selectedID = "";
	private String selectedFromDate = "";
	private String selectedToDate = "";
	private String selectedRestaurantID = "";
	private String selectedOrderID = "";
	private String yearMonth = "";
	private String semiMonthType = "";
	private String dueDate = "";
	private OrderMasterVO master = new OrderMasterVO();
	private OrderRestaurantVO restaurant = new OrderRestaurantVO();
	private List<OrderMenuVO> menuList = new ArrayList<OrderMenuVO>();
	private BillMasterVO billVO = new BillMasterVO();
	
	private List<BillDetailVO> billList = new ArrayList<BillDetailVO>();
	/** Jasper variable*/
	private Map<String, Object> pdfParameters = new HashMap<String, Object>();
	private List<BillDetailVO> pdfDataSource = new ArrayList<BillDetailVO>();
	private String pdfFileName = "";
	
	/** Excel variable*/
	private InputStream inputStream = null;
	private String excelFileName = "";
	
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);

		// set up dropdown menu from codes
		semiMonthTypeList = codeMap.get(Constants.KEY_CD_SEMI_MONTH_TYPE);

		// set up restaurant list
		RestaurantMasterDao dao = new RestaurantMasterDao();
		restaurantList = dao.selectAvailable();
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initialize Bill Detail
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");
		
		//Select Bill Information
		BillMasterDao dao = new BillMasterDao();
		Map<String, Object> billMap = new HashMap<String, Object>();
		billMap= dao.selectByID(selectedID);
		
		billVO = (BillMasterVO)billMap.get(Constants.KEY_BILL_MASTER);
		billList = (List<BillDetailVO>)billMap.get(Constants.KEY_BILL_DETAIL);
		
		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Initialize Billing Order Detail Panel
	 * @return SUCCESS
	 */
	public String initOrderDetail() throws Exception {
		logger.info("<---initDetail start --->");
		
		//Select Order Information
		OrderDao dao = new OrderDao();
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap = dao.selectByRestaurantID(selectedOrderID, selectedRestaurantID);
		
		master = (OrderMasterVO)orderMap.get(Constants.KEY_ORDER_MASTER);
		restaurant = (OrderRestaurantVO)orderMap.get(Constants.KEY_ORDER_RESTAURANT);
		menuList = (List<OrderMenuVO>)orderMap.get(Constants.KEY_ORDER_MENU);

		logger.info("<---initDetail end --->");
		return SUCCESS;
	}
	
	/**
	 * Download Bill Detail as a pdf file
	 * @return SUCCESS
	 */
	public String print() throws Exception {
		logger.info("<---print start --->");

		// Get Wow Master data
		WowMasterDao wdao = new WowMasterDao();
		WowMasterVO wvo = wdao.selectBySeq(1);
		
		// Get the last payment data
		BillMasterDao dao = new BillMasterDao();
		BillMasterVO lastVO = dao.selectLastPayment(billVO.getYearMonth(), billVO.getSemiMonthType(), billVO.getCompanyID());
		
		StringBuilder sb = new StringBuilder();
		
		// Set pdf pamameters
		pdfParameters.put("BillingID", billVO.getBillingID());
		pdfParameters.put("CompanyName", billVO.getCompanyName());
		pdfParameters.put("BillAccountNO", billVO.getBillAccountNO());
		
		pdfParameters.put("BillAddress", billVO.getBillAddress());
		// Add city name, province name, postal code
		sb.append(CodeUtil.getCdName(codeMap, Constants.KEY_CD_CITY, billVO.getBillCity()));
		sb.append(" ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_PROVINCE, billVO.getBillProvince()));
		sb.append(" ").append(StringUtil.setPostalCode(billVO.getBillPostalCode()));
		pdfParameters.put("BillAddress2", sb.toString());
		
		if (Constants.CODE_BILL_COM_TYPE_RESTAURANT.equals(billVO.getCompanyType())) {
			// In case of restaurant
			pdfParameters.put("PaymentAmount", 
					billVO.getFoodAmount() + billVO.getCommissionAmount() + billVO.getCashCommissionAmount()
				  + billVO.getDeliveryAmount() + billVO.getTipAmount() + billVO.getAdjustmentAmount());
		} else {
			// In case of delivery company
			pdfParameters.put("PaymentAmount", 
					billVO.getCommissionAmount() + billVO.getCashCommissionAmount()
				  + billVO.getDeliveryAmount() + billVO.getTipAmount() + billVO.getAdjustmentAmount());
		}
		pdfParameters.put("IssuedDate", StringUtil.convertDate2String(billVO.getIssueDate(), DAY_PATTERN_MMM));
		pdfParameters.put("BillFrom", StringUtil.convertDate2String(billVO.getBillFrom(), DAY_PATTERN_MMM));
		pdfParameters.put("BillTo", StringUtil.convertDate2String(billVO.getBillTo(), DAY_PATTERN_MMM));
		pdfParameters.put("Food", billVO.getFoodAmount());
		pdfParameters.put("CashCommission", billVO.getCashCommissionAmount());
		pdfParameters.put("Commission", billVO.getCommissionAmount());
		pdfParameters.put("Delivery", billVO.getDeliveryAmount());
		pdfParameters.put("Tip", billVO.getTipAmount());
		pdfParameters.put("Adjustment", billVO.getAdjustmentAmount());
		
		// Last Payment data
		if (lastVO != null) {
			pdfParameters.put("LastPaymentAmount", 
					lastVO.getFoodAmount() + lastVO.getCommissionAmount() + lastVO.getCashCommissionAmount()
				  + lastVO.getDeliveryAmount() + lastVO.getTipAmount() + lastVO.getAdjustmentAmount());
			pdfParameters.put("LastIssuedDate", StringUtil.convertDate2String(lastVO.getIssueDate(), DAY_PATTERN_MMM));
		} else {
			// There is no last payment data
			pdfParameters.put("LastPaymentAmount", 0.0);
			pdfParameters.put("LastIssuedDate", "");
		}

		// Wow TAX NO
		pdfParameters.put("TaxNO", wvo.getTaxNO());
		
		// Bill detail list
		pdfDataSource = billList;
		
		// Set pdf file name
		pdfFileName = billVO.getBillingID();
		
		logger.info("<---print end --->");
		return SUCCESS;
	}
	
	/**
	 * Download Bill Detail as a excel file
	 * @return SUCCESS
	 */
	public String download() throws Exception {
		logger.info("<---download start --->");
		
		// download excel file 
		File downloadFile = FileUtil.downloadBillList(billVO, billList);
		inputStream = new FileInputStream(downloadFile);
		// Set excel file name
		excelFileName = billVO.getBillingID();
		
		logger.info("<---download end --->");
		return SUCCESS;
	}
	
	/**
	 * Delete Bill Data
	 * @return SUCCESS
	 */
	public String delete() throws Exception {
		logger.info("<---delete start --->");
		
		//Delete bill Information
		BillMasterDao dao = new BillMasterDao();
		dao.delete(billVO.getBillingID());
		
		logger.info("<---delete end --->");
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
	public BillingOrderListConditionVO getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(BillingOrderListConditionVO condition) {
		this.condition = condition;
	}

	/**
	 * @return the list
	 */
	public List<BillingOrderListVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<BillingOrderListVO> list) {
		this.list = list;
	}

	/**
	 * @return the restaurantList
	 */
	public List<RestaurantMasterVO> getRestaurantList() {
		return restaurantList;
	}

	/**
	 * @return the semiMonthTypeList
	 */
	public List<CodeMasterVO> getSemiMonthTypeList() {
		return semiMonthTypeList;
	}
	/**
	 * @return the selectedFromDate
	 */
	public String getSelectedFromDate() {
		return selectedFromDate;
	}

	/**
	 * @param selectedFromDate the selectedFromDate to set
	 */
	public void setSelectedFromDate(String selectedFromDate) {
		this.selectedFromDate = selectedFromDate;
	}

	/**
	 * @return the selectedToDate
	 */
	public String getSelectedToDate() {
		return selectedToDate;
	}

	/**
	 * @param selectedToDate the selectedToDate to set
	 */
	public void setSelectedToDate(String selectedToDate) {
		this.selectedToDate = selectedToDate;
	}

	/**
	 * @return the yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}

	/**
	 * @param yearMonth the yearMonth to set
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	/**
	 * @return the semiMonthType
	 */
	public String getSemiMonthType() {
		return semiMonthType;
	}

	/**
	 * @param semiMonthType the semiMonthType to set
	 */
	public void setSemiMonthType(String semiMonthType) {
		this.semiMonthType = semiMonthType;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the selectedRestaurantID
	 */
	public String getSelectedRestaurantID() {
		return selectedRestaurantID;
	}

	/**
	 * @param selectedRestaurantID the selectedRestaurantID to set
	 */
	public void setSelectedRestaurantID(String selectedRestaurantID) {
		this.selectedRestaurantID = selectedRestaurantID;
	}

	/**
	 * @return the selectedOrderID
	 */
	public String getSelectedOrderID() {
		return selectedOrderID;
	}

	/**
	 * @param selectedOrderID the selectedOrderID to set
	 */
	public void setSelectedOrderID(String selectedOrderID) {
		this.selectedOrderID = selectedOrderID;
	}

	/**
	 * @return the master
	 */
	public OrderMasterVO getMaster() {
		return master;
	}

	/**
	 * @param master the master to set
	 */
	public void setMaster(OrderMasterVO master) {
		this.master = master;
	}

	/**
	 * @return the restaurant
	 */
	public OrderRestaurantVO getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(OrderRestaurantVO restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the menuList
	 */
	public List<OrderMenuVO> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<OrderMenuVO> menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return the billVO
	 */
	public BillMasterVO getBillVO() {
		return billVO;
	}

	/**
	 * @param billVO the billVO to set
	 */
	public void setBillVO(BillMasterVO billVO) {
		this.billVO = billVO;
	}

	/**
	 * @return the billList
	 */
	public List<BillDetailVO> getBillList() {
		return billList;
	}

	/**
	 * @param billList the billList to set
	 */
	public void setBillList(List<BillDetailVO> billList) {
		this.billList = billList;
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
	 * @return the pdfFileName
	 */
	public String getPdfFileName() {
		return pdfFileName;
	}

	/**
	 * @param pdfFileName the pdfFileName to set
	 */
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	/**
	 * @return the pdfParameters
	 */
	public Map<String, Object> getPdfParameters() {
		return pdfParameters;
	}

	/**
	 * @param pdfParameters the pdfParameters to set
	 */
	public void setPdfParameters(Map<String, Object> pdfParameters) {
		this.pdfParameters = pdfParameters;
	}

	/**
	 * @return the pdfDataSource
	 */
	public List<BillDetailVO> getPdfDataSource() {
		return pdfDataSource;
	}

	/**
	 * @param pdfDataSource the pdfDataSource to set
	 */
	public void setPdfDataSource(List<BillDetailVO> pdfDataSource) {
		this.pdfDataSource = pdfDataSource;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the excelFileName
	 */
	public String getExcelFileName() {
		return excelFileName;
	}

	/**
	 * @param excelFileName the excelFileName to set
	 */
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
}
