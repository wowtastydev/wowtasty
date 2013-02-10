package com.wowtasty.action;

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
import com.wowtasty.mybatis.dao.ContentsTextDao;
import com.wowtasty.mybatis.dao.DeliveryManDao;
import com.wowtasty.mybatis.dao.OrderDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.ContentsTextVO;
import com.wowtasty.mybatis.vo.DeliveryManVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.MemberRestaurantVO;
import com.wowtasty.mybatis.vo.OrderRestaurantVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.MailSender;
import com.wowtasty.util.OrderUtil;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.ValidationUtil;
import com.wowtasty.vo.OrderListConditionVO;
import com.wowtasty.vo.OrderListVO;


/**
 * @author Hak C.
 *
 */
public class OrderListAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Date pattern */
	private static final String DAY_PATTERN = "MM/dd/yyyy";
	
	/** Logger */
	private static Logger logger = Logger.getLogger(OrderListAction.class);
	
	/** dropdown box, radio button list */
	private List<CodeMasterVO> orderStatusList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> deliveryTypeList = new ArrayList<CodeMasterVO>();
	private List<DeliveryManVO> deliverymanList = new ArrayList<DeliveryManVO>();
	private Map<String, String> timeMap = new LinkedHashMap<String, String>();
	private Map<String, String> orderStatusMap = new LinkedHashMap<String, String>();
	private Map<String, String> orderStatusRestMap = new LinkedHashMap<String, String>();
	
	/** codemaster map */
	private Map<String, List<CodeMasterVO>> codeMap = new HashMap<String, List<CodeMasterVO>>();
	
	/** user information */
	private MemberMasterVO uservo = new MemberMasterVO();
	private List<MemberRestaurantVO> userRestList = new ArrayList<MemberRestaurantVO>();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name;at FoodDelivery WowTasty
	private String headTitle = "Food Order List";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Food Order List";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Food Order List";
	
	/** Search Condition */
	private OrderListConditionVO condition = new OrderListConditionVO();
	
	/** Searched List */
	private List<OrderListVO> list = new ArrayList<OrderListVO>();
	
	/** Parameter */
	private String selectedOrderID = "";
	private String selectedRestaurantID = "";
	private int selectedDeliverymanID = 0;
	private String selectedOrderMemberEmail = "";
	// The page to move back from detail page. Current Order List page or Order List page
	private String forwardPage = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---prepare start --->");

		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		userRestList = (List<MemberRestaurantVO>)httpSession.getAttribute(Constants.KEY_SESSION_USER_REST_LIST);
		
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		
		// set up dropdown menu from codes
		orderStatusList = codeMap.get(Constants.KEY_CD_ORDER_STATUS);
		int intAuth = Integer.parseInt(uservo.getAuth());
		if (intAuth >= Constants.CODE_ROLE_REST_MAX) {
			// If user is not Wow users. Don't need to show "Pending status"
			int size = orderStatusList.size();
			for (int i = 0; i < size; i++) {
				if (Constants.CODE_ORDER_STATUS_PENDING.equals(orderStatusList.get(i).getCode())) {
					orderStatusList.remove(i);
				}
			}
		}
			
		deliveryTypeList = codeMap.get(Constants.KEY_CD_DELIVERY_TYPE);
		
		// set up deliveryman dropdown menu 
		DeliveryManDao dao = new DeliveryManDao();
		deliverymanList = dao.selectByCompany(Constants.CONSTANT_5DELIVERY_ID);
		
		// set up conditions
		timeMap.put("", "All");
		timeMap.put("-0:30", "30 Minutes");
		timeMap.put("-1:00", "1 Hour");
		timeMap.put("-3:00", "3 Hours");
		
		// order status condition for admin user page
		orderStatusMap.put("", "All Status");
		orderStatusMap.put(Constants.CODE_ORDER_STATUS_PENDING, "Pending Only");
		
		// order status condition for Restaurant user page
		orderStatusRestMap.put("", "All Status");
		orderStatusRestMap.put(Constants.CODE_ORDER_STATUS_ORDERED, "Ordered Only");
		
		logger.info("<---prepare end --->");
	}
	
	/**
	 * Initiate Order History List page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");

		//Select All Order List
		OrderDao dao = new OrderDao();
		list = dao.select(condition);

		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate Order History List page for Restaurant user
	 * @return SUCCESS
	 */
	public String initRest() throws Exception {
		logger.info("<---initRest start --->");

		//Select own restaurant Current Order List
		OrderDao dao = new OrderDao();
		//Set own restaurant list
		int size = userRestList.size();
		for (int i = 0; i < size; i++) {
			condition.getRestaurantList().add(userRestList.get(i).getRestaurantID());
		}
		if (size > 0) {
			// Only search when member has a restaurant list
			list = dao.selectRest(condition);
		}

		logger.info("<---initRest end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate Current Order List page
	 * @return SUCCESS
	 */
	public String initCurrent() throws Exception {
		logger.info("<---initCurrent start --->");

		//Select All Current Order List
		OrderDao dao = new OrderDao();
		list = dao.selectCurrent(condition);

		logger.info("<---initCurrent end --->");
		return SUCCESS;
	}
	
	/**
	 * Initiate Current Order List page for Restaurant user
	 * @return SUCCESS
	 */
	public String initCurrentRest() throws Exception {
		logger.info("<---initCurrentRest start --->");
		
		//Select own restaurant Current Order List
		OrderDao dao = new OrderDao();
		//Set own restaurant list
		int size = userRestList.size();
		for (int i = 0; i < size; i++) {
			condition.getRestaurantList().add(userRestList.get(i).getRestaurantID());
		}
		if (size > 0) {
			// Only search when member has a restaurant list
			list = dao.selectCurrentRest(condition);
		}

		logger.info("<---initCurrentRest end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Order History List
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		
		String returnString = SUCCESS;
		
		// Check Validation
		boolean hasError = checkValidation();
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		//Select Order List
		OrderDao dao = new OrderDao();
		list = dao.select(condition);
		
		logger.info("<---execute end --->");
		return returnString;
	}
	
	/**
	 * Search Order History List for restaurant users
	 * @return SUCCESS
	 */
	public String searchRest() throws Exception {
		logger.info("<---searchRest start --->");
		
		String returnString = SUCCESS;
		
		// Check Validation
		boolean hasError = checkValidation();
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		//Select Order List
		OrderDao dao = new OrderDao();
		//Set own restaurant list
		int size = userRestList.size();
		for (int i = 0; i < size; i++) {
			condition.getRestaurantList().add(userRestList.get(i).getRestaurantID());
		}
		if (size > 0) {
			// Only search when member has a restaurant list
			list = dao.selectRest(condition);
		}
		
		logger.info("<---searchRest end --->");
		return returnString;
	}
	
	/**
	 * Search Current Order List
	 * @return SUCCESS
	 */
	public String searchCurrent() throws Exception {
		logger.info("<---searchCurrent start --->");

		//Select Current Order List
		OrderDao dao = new OrderDao();
		list = dao.selectCurrent(condition);

		logger.info("<---searchCurrent end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Current Order List for restaurant users
	 * @return SUCCESS
	 */
	public String searchCurrentRest() throws Exception {
		logger.info("<---searchCurrentRest start --->");

		//Select own restaurant Current Order List
		OrderDao dao = new OrderDao();
		//Set own restaurant list
		int size = userRestList.size();
		for (int i = 0; i < size; i++) {
			condition.getRestaurantList().add(userRestList.get(i).getRestaurantID());
		}
		if (size > 0) {
			// Only search when member has a restaurant list
			list = dao.selectCurrentRest(condition);
		}

		logger.info("<---searchCurrentRest end --->");
		return SUCCESS;
	}
	
	/**
	 * Change Order status Pending to Ordered
	 * @return SUCCESS
	 */
	public String changeOrderStatus() throws Exception {
		logger.info("<---changeOrderStatus start --->");
		
		// set up order status and update Information
		OrderRestaurantVO vo = new OrderRestaurantVO();
		vo.setOrderStatus(Constants.CODE_ORDER_STATUS_ORDERED);
		vo.setOrderID(selectedOrderID);
		vo.setRestaurantID(selectedRestaurantID);
		vo.setUpdateID(uservo.getMemberID());
		
		//Change order status
		OrderDao dao = new OrderDao();
		int returnCnt = dao.changeOrderStatus(vo);
		
		if (returnCnt > 0) {
			addActionMessage("Order Status has been changed successfully"); 
			
			//Get restaurant master data
			RestaurantMasterDao rdao = new RestaurantMasterDao();
			RestaurantMasterVO restaurantMaster = rdao.selectByID(selectedRestaurantID);
			
			String strOrderDetail = OrderUtil.contextOrderDetail(selectedOrderID, selectedRestaurantID, codeMap);
			// Send email -> Send Email to ONLY Restaurant
			// Get contents, subject for restaurant
			ContentsTextDao cdao = new ContentsTextDao();
			ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_REST);
			
			// Set restaurant's name and order detail into email contents
			String contents = cvo.getContents();
			contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurantMaster.getName());
			contents = contents.replace("<PARAM_ORDER_DETAIL>", strOrderDetail);
			contents = contents.replace("<PARAM_ORDER_ID>", selectedOrderID);
			contents = contents.replace("<PARAM_RESTAURANT_ID>", selectedRestaurantID);
			
			// Set up the mail contents for restaurant
			MailSender sender = new MailSender();
			sender.sendEmail(restaurantMaster.getEmail1(), cvo.getSubject(), contents);
			
			addActionMessage("Email has been sent to the restaurant successfully");
		}
		
		//Reload List
		dao = new OrderDao();
		if ("/WEB-INF/jsp/admin/currentorderlist.jsp".equals(forwardPage)) {
			// Current Order 
			list = dao.selectCurrent(condition);
		} else {
			// Order History
			list = dao.select(condition);
		}
		

		logger.info("<---changeOrderStatus end --->");
		return SUCCESS;
	}
	
	/**
	 * Set Delivery Man
	 * @return SUCCESS
	 */
	public String setDeliveryMan() throws Exception {
		logger.info("<---setDeliveryMan start --->");
		
		// set up delivery Man and update Information
		OrderRestaurantVO vo = new OrderRestaurantVO();
		vo.setOrderID(selectedOrderID);
		vo.setRestaurantID(selectedRestaurantID);
		vo.setDeliverymanID(selectedDeliverymanID);
		// Find DeliveryMan data
		int size = deliverymanList.size();
		for (int i = 0; i < size; i++) {
			if (selectedDeliverymanID == deliverymanList.get(i).getDeliverymanID()) {
				vo.setDeliverymanName(deliverymanList.get(i).getFirstName());
				vo.setDeliverymanTelephone(deliverymanList.get(i).getTelephone());
				break;
			}
		}
		vo.setUpdateID(uservo.getMemberID());
		
		//Set up delivery Man
		OrderDao dao = new OrderDao();
		int returnCnt = dao.setDeliveryMan(vo);
		
		if (returnCnt > 0) {
			addActionMessage("Delivery Man has been updated successfully"); 
		}
		
		//Reload List
		dao = new OrderDao();
		if ("/WEB-INF/jsp/admin/currentorderlist.jsp".equals(forwardPage)) {
			// Current Order 
			list = dao.selectCurrent(condition);
		} else {
			// Order History
			list = dao.select(condition);
		}

		logger.info("<---setDeliveryMan end --->");
		return SUCCESS;
	}
	
	/**
	 * Validation check
	 * @return true : validation error, false: no error
	 */	
	private boolean checkValidation() {
		
		boolean hasError = false;
		
		//Check date type
		if (!ValidationUtil.isBlank(condition.getFromDate()) && !ValidationUtil.isDate(condition.getFromDate(), DAY_PATTERN)) {
			// Date(from) is not datetype
			addFieldError("condition.fromDate", getText("E0003_1", new String[]{"From(MM/DD/YYYY)"}));
			hasError = true;
		}

		if (!ValidationUtil.isBlank(condition.getToDate()) && !ValidationUtil.isDate(condition.getToDate(), DAY_PATTERN)) {
			// Date(to) is not datetype
			addFieldError("condition.toDate", getText("E0003_1", new String[]{"To(MM/DD/YYYY)"}));
			hasError = true;
		}
		
		// Telephone
		if (!ValidationUtil.isBlank(condition.getOrderMemberTelephone()) && !ValidationUtil.isTelephone(condition.getOrderMemberTelephone())) {
			addFieldError("condition.orderMemberTelephone", getText("E0003_1", new String[]{"Customer Tel"}));
			hasError = true;
		}
		
		return hasError;
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
	 * @return the orderStatusList
	 */
	public List<CodeMasterVO> getOrderStatusList() {
		return orderStatusList;
	}

	/**
	 * @param orderStatusList the orderStatusList to set
	 */
	public void setOrderStatusList(List<CodeMasterVO> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}

	/**
	 * @return the condition
	 */
	public OrderListConditionVO getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(OrderListConditionVO condition) {
		this.condition = condition;
	}

	/**
	 * @return the list
	 */
	public List<OrderListVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<OrderListVO> list) {
		this.list = list;
	}

	/**
	 * @return the deliverymanList
	 */
	public List<DeliveryManVO> getDeliverymanList() {
		return deliverymanList;
	}

	/**
	 * @param deliverymanList the deliverymanList to set
	 */
	public void setDeliverymanList(List<DeliveryManVO> deliverymanList) {
		this.deliverymanList = deliverymanList;
	}

	/**
	 * @return the timeMap
	 */
	public Map<String, String> getTimeMap() {
		return timeMap;
	}

	/**
	 * @param timeMap the timeMap to set
	 */
	public void setTimeMap(Map<String, String> timeMap) {
		this.timeMap = timeMap;
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
	 * @return the selectedDeliverymanID
	 */
	public int getSelectedDeliverymanID() {
		return selectedDeliverymanID;
	}

	/**
	 * @param selectedDeliverymanID the selectedDeliverymanID to set
	 */
	public void setSelectedDeliverymanID(int selectedDeliverymanID) {
		this.selectedDeliverymanID = selectedDeliverymanID;
	}

	/**
	 * @return the selectedOrderMemberEmail
	 */
	public String getSelectedOrderMemberEmail() {
		return selectedOrderMemberEmail;
	}

	/**
	 * @param selectedOrderMemberEmail the selectedOrderMemberEmail to set
	 */
	public void setSelectedOrderMemberEmail(String selectedOrderMemberEmail) {
		this.selectedOrderMemberEmail = selectedOrderMemberEmail;
	}

	/**
	 * @return the orderStatusMap
	 */
	public Map<String, String> getOrderStatusMap() {
		return orderStatusMap;
	}

	/**
	 * @param orderStatusMap the orderStatusMap to set
	 */
	public void setOrderStatusMap(Map<String, String> orderStatusMap) {
		this.orderStatusMap = orderStatusMap;
	}

	/**
	 * @return the forwardPage
	 */
	public String getForwardPage() {
		return forwardPage;
	}

	/**
	 * @param forwardPage the forwardPage to set
	 */
	public void setForwardPage(String forwardPage) {
		this.forwardPage = forwardPage;
	}

	/**
	 * @return the deliveryTypeList
	 */
	public List<CodeMasterVO> getDeliveryTypeList() {
		return deliveryTypeList;
	}

	/**
	 * @param deliveryTypeList the deliveryTypeList to set
	 */
	public void setDeliveryTypeList(List<CodeMasterVO> deliveryTypeList) {
		this.deliveryTypeList = deliveryTypeList;
	}

	/**
	 * @return the orderStatusRestMap
	 */
	public Map<String, String> getOrderStatusRestMap() {
		return orderStatusRestMap;
	}

	/**
	 * @param orderStatusRestMap the orderStatusRestMap to set
	 */
	public void setOrderStatusRestMap(Map<String, String> orderStatusRestMap) {
		this.orderStatusRestMap = orderStatusRestMap;
	}
}
