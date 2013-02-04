package com.wowtasty.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.OrderAdjustmentDao;
import com.wowtasty.mybatis.dao.OrderDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.dao.WowMasterDao;
import com.wowtasty.mybatis.vo.BillDetailVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.OrderAdjustmentVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.mybatis.vo.WowMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.StringConvertUtil;
import com.wowtasty.util.ValidationUtil;
import com.wowtasty.vo.BalanceListConditionVO;
import com.wowtasty.vo.BalanceListVO;


/**
 * @author Hak C.
 *
 */
public class BalanceListAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Float MIN_PRICE = -999999.99f;
	private static final Float MAX_PRICE = 999999.99f;

	/** constants */
	private static final String DAY_PATTERN_MM = "MM/dd/yyyy";
	private static final String TIMESTAMP_PATTERN = "MM/dd/yyyy HH:mm";
	
	/** Logger */
	private static Logger logger = Logger.getLogger(BalanceListAction.class);
	
	/** dropdown box list */
	private List<RestaurantMasterVO> restaurantList = new ArrayList<RestaurantMasterVO>();
	
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
	private BalanceListConditionVO condition = new BalanceListConditionVO();
	
	/** Searched List */
	private List<BalanceListVO> list = new ArrayList<BalanceListVO>();
	
	/** Parameter */
	private String selectedFromDate = "";
	private String selectedToDate = "";
	private String selectedRestaurantID = "";
	private Integer selectedSeq = 0;
	private Double totolCurrentBalance = 0.00;
	private Double totolCurrentAvailable = 0.00;
	private Double totolTermAmount = 0.00;
	private String registerDate = "";
	private String registerTime = "00:00";
	private String adjustItem = "";
	private String price = "0.00";

	/** Jasper variable*/
	private Map<String, Object> pdfParameters = new HashMap<String, Object>();
	private List<BillDetailVO> pdfDataSource = new ArrayList<BillDetailVO>();
	private String pdfFileName = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");

		// set up restaurant list
		RestaurantMasterDao dao = new RestaurantMasterDao();
		restaurantList = dao.selectAvailable();
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Balance List page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");
		// Default FromDate ~ ToDate Setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_PATTERN_MM);
		
		// ToDate -> current date
		if (ValidationUtil.isBlank(condition.getToDate())) {
			condition.setToDate(sdf.format(cal.getTime()));
		}
		
		// FromDate -> 15 day back from the current date
		if (ValidationUtil.isBlank(condition.getFromDate())) {
			cal.add(Calendar.DATE, -15);
			condition.setFromDate(sdf.format(cal.getTime()));
		}
		list.clear();
		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Balance List
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		
		// Check Validation
		boolean hasError = false;
		hasError = checkValidation();
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		// Default FromDate ~ ToDate Setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_PATTERN_MM);
		
		// ToDate -> current date
		if (ValidationUtil.isBlank(condition.getToDate())) {
			condition.setToDate(sdf.format(cal.getTime()));
		}
		
		// FromDate -> 15 day back from the current date
		if (ValidationUtil.isBlank(condition.getFromDate())) {
			cal.add(Calendar.DATE, -15);
			condition.setFromDate(sdf.format(cal.getTime()));
		}
		
		// Search Balance
		searchBalance();
		
		// Set order list's from-date and to-date
		selectedFromDate = condition.getFromDate();
		selectedToDate = condition.getToDate();
		selectedRestaurantID = condition.getRestaurantID();
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	
	/**
	 * Add adjustment
	 * @return SUCCESS
	 */
	public String add() throws Exception {
		logger.info("<---add start --->");
		// Check Validation
		boolean hasError = false;
		hasError = checkValidationAdd();
		
		// In case of validation error, return INPUT
		if (hasError) {
			// Reload Balance
			searchBalance();
			return INPUT;
		}
		
		OrderAdjustmentVO oavo = new OrderAdjustmentVO();
		StringBuilder sb = new StringBuilder();
		oavo.setAdjustItem(adjustItem);
		oavo.setPrice(Float.parseFloat(price));
		sb.append(registerDate).append(" ").append(registerTime);
		oavo.setRegisterTime(StringConvertUtil.convertString2date(sb.toString(), TIMESTAMP_PATTERN));
		oavo.setRestaurantID(selectedRestaurantID);
		oavo.setUpdateID(uservo.getMemberID());
		
		OrderAdjustmentDao dao = new OrderAdjustmentDao();

		int returnCnt = dao.insert(oavo);
		if (returnCnt > 0) {
			addActionMessage("Adjustment has been inserted successfully");
		}
		
		// Clear adjustment data
		price = "";
		adjustItem = "";
		registerDate = "";
		registerTime = "";
		
		// Reload Balance
		searchBalance();
		
		logger.info("<---add end --->");
		return SUCCESS;
	}
	
	/**
	 * Delete adjustment
	 * @return SUCCESS
	 */
	public String delete() throws Exception {
		logger.info("<---delete start --->");
		
		OrderAdjustmentDao dao = new OrderAdjustmentDao();

		int returnCnt = dao.delete(selectedSeq);
		if (returnCnt > 0) {
			addActionMessage("Adjustment has been deleted successfully");
		}
		
		// Reload Balance
		searchBalance();
		
		logger.info("<---delete end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Balance ( Search selected balance & total balance, total available base on the current date
	 */
	private void searchBalance() {
		
		BalanceListConditionVO localCondition = new BalanceListConditionVO();
		// Copy selected condition to local conditions to search balance
		localCondition.setRestaurantID(condition.getRestaurantID());
		localCondition.setFromDate(condition.getFromDate());
		localCondition.setToDate(condition.getToDate());
		
		// Set Payment Valuable Period/ Tax rate
		WowMasterDao wdao = new WowMasterDao();
		WowMasterVO wvo = wdao.selectBySeq(1);
		localCondition.setPaymentValidPeriod(wvo.getPaymentValidPeriod());
		localCondition.setTax(wvo.getTax1() + wvo.getTax2());
		
		// Set Commission rate
		RestaurantMasterDao rdao = new RestaurantMasterDao();
		RestaurantMasterVO rwo = rdao.selectByID(localCondition.getRestaurantID());
		localCondition.setCashCommission(rwo.getCashCommission());
		localCondition.setCommission(rwo.getCommission());
		
		// Search Order list within selected period
		OrderDao dao = new OrderDao();
		Double totalBalance = 0.0;
		list = dao.selectBalance(localCondition);
	
		// Calculate balance, set note
		Double totalAmount = 0.0;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			// Except Adjustment data, Calculate totalAmount
			if ("Y".equals(list.get(size - i - 1).getAdjustmentFlag())) {
				totalAmount = list.get(size - i - 1).getTotalAmount();
			} else {
				totalAmount = list.get(size - i - 1).getFoodAmount() 
						+ list.get(size - i - 1).getCommissionAmount()
						+ list.get(size - i - 1).getDeliveryAmount()
						+ list.get(size - i - 1).getTipAmount();
				totalAmount = Math.round(totalAmount*100.0)/100.0;
				list.get(size - i - 1).setTotalAmount(totalAmount);	
			}

			// Calculate total balance
			if (!Constants.BALANCE_TYPE_NA.equals(list.get(size - i - 1).getAvailableFlag())) {
				// In case of Available/ Balance
				totalBalance += totalAmount;
			}
			totalBalance = Math.round(totalBalance*100.0)/100.0;
			list.get(size - i - 1).setBalance(totalBalance);
			
			totolTermAmount += totalAmount;
			
			// Set note			
			if (!Constants.CODE_ORDER_STATUS_COMPLETED.equals(list.get(size - i - 1).getOrderStatus())) {
				// In case of NOT completed
				list.get(size - i - 1).setNote(list.get(size - i - 1).getOrderStatusName());
			}
		}
		
		
		// Search Order list within current period(1st day of last month ~ current day)
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_PATTERN_MM);
		
		localCondition.setToDate(sdf.format(cal.getTime()));
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		localCondition.setFromDate(sdf.format(cal.getTime()));
		
		List<BalanceListVO> currentlist = dao.selectBalance(localCondition);
	
		// Calculate total balance, total available
		totolCurrentBalance = 0.0;
		size = currentlist.size();
		for (int i = 0; i < size; i++) {
			// Except Adjustment data, Calculate totalAmount
			if ("Y".equals(currentlist.get(i).getAdjustmentFlag())) {
				totalAmount = currentlist.get(i).getTotalAmount();
			} else {
				totalAmount = currentlist.get(i).getFoodAmount() 
						+ currentlist.get(i).getCommissionAmount()
						+ currentlist.get(i).getDeliveryAmount()
						+ currentlist.get(i).getTipAmount();
			}
			// Calculate total balance
			if (Constants.BALANCE_TYPE_AVAILABLE.equals(currentlist.get(i).getAvailableFlag())) {
				// In case of Available
				totolCurrentBalance += totalAmount;
				totolCurrentAvailable += totalAmount;
			} else if (Constants.BALANCE_TYPE_BALANCE.equals(currentlist.get(i).getAvailableFlag())) {
				// In case of Balance only
				totolCurrentBalance += totalAmount;
			}
		}
		
		// Adjustment double decimal error
		totolCurrentBalance = Math.round(totolCurrentBalance*100.0)/100.0;
		totolCurrentAvailable = Math.round(totolCurrentAvailable*100.0)/100.0;
		totolTermAmount = Math.round(totolTermAmount*100.0)/100.0;
	}
	
	/**
	 * Validation check(Search)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidation() {
		
		boolean hasError = false;
		
		// Restaurant ID Validation Check
		if (ValidationUtil.isBlank(condition.getRestaurantID())) {
			// Restaurant ID is required
			addFieldError("condition.restaurantID", getText("E0001_1", new String[]{"Restaurant"}));
			hasError = true;
		}
		return hasError;
	}
	
	/**
	 * Validation check(Add)
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidationAdd() {
		
		boolean hasError = false;
		registerDate = registerDate.trim();
		price = price.trim();
		
		// Register Date Validation Check
		if (ValidationUtil.isBlank(registerDate)) {
			// Register Date is required
			addFieldError("registerDate", getText("E0001_1", new String[]{"Adjustment Date"}));
			hasError = true;
		}
		
		// Adjust Item Validation Check
		if (ValidationUtil.isBlank(adjustItem)) {
			// Adjust Item is required
			addFieldError("adjustItem", getText("E0001_1", new String[]{"Adjustment Item"}));
			hasError = true;
		}
		
		//Price Validation Check
		if (ValidationUtil.isBlank(price)) {
			addFieldError("price", getText("E0001_1", new String[]{"Adjustment Price"}));
			hasError = true;
		} else {
			if (!ValidationUtil.isNum(price)) {
				addFieldError("netPrice", getText("E0003_1", new String[]{"Adjustment Price"}));
				hasError = true;
			} else {
				if (Float.parseFloat(price) < MIN_PRICE || Float.parseFloat(price) > MAX_PRICE ) {
					// -999999.99 <= price <= 9999999.99
					addFieldError("netPrice", getText("E0008", new String[]{"Adjustment Price", MIN_PRICE.toString(), MAX_PRICE.toString()}));
					hasError = true;
				}
			}
		}
		return hasError;
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
	public BalanceListConditionVO getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(BalanceListConditionVO condition) {
		this.condition = condition;
	}

	/**
	 * @return the list
	 */
	public List<BalanceListVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<BalanceListVO> list) {
		this.list = list;
	}

	/**
	 * @return the restaurantList
	 */
	public List<RestaurantMasterVO> getRestaurantList() {
		return restaurantList;
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
	 * @return the totolCurrentBalance
	 */
	public Double getTotolCurrentBalance() {
		return totolCurrentBalance;
	}

	/**
	 * @param totolCurrentBalance the totolCurrentBalance to set
	 */
	public void setTotolCurrentBalance(Double totolCurrentBalance) {
		this.totolCurrentBalance = totolCurrentBalance;
	}

	/**
	 * @return the totolCurrentAvailable
	 */
	public Double getTotolCurrentAvailable() {
		return totolCurrentAvailable;
	}

	/**
	 * @param totolCurrentAvailable the totolCurrentAvailable to set
	 */
	public void setTotolCurrentAvailable(Double totolCurrentAvailable) {
		this.totolCurrentAvailable = totolCurrentAvailable;
	}

	/**
	 * @return the totolTermAmount
	 */
	public Double getTotolTermAmount() {
		return totolTermAmount;
	}

	/**
	 * @param totolTermAmount the totolTermAmount to set
	 */
	public void setTotolTermAmount(Double totolTermAmount) {
		this.totolTermAmount = totolTermAmount;
	}

	/**
	 * @return the registerDate
	 */
	public String getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * @return the registerTime
	 */
	public String getRegisterTime() {
		return registerTime;
	}

	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * @return the adjustItem
	 */
	public String getAdjustItem() {
		return adjustItem;
	}

	/**
	 * @param adjustItem the adjustItem to set
	 */
	public void setAdjustItem(String adjustItem) {
		this.adjustItem = adjustItem;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the selectedSeq
	 */
	public Integer getSelectedSeq() {
		return selectedSeq;
	}

	/**
	 * @param selectedSeq the selectedSeq to set
	 */
	public void setSelectedSeq(Integer selectedSeq) {
		this.selectedSeq = selectedSeq;
	}
}
