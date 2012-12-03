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
import com.wowtasty.mybatis.dao.ContentsTextDao;
import com.wowtasty.mybatis.dao.DeliveryManDao;
import com.wowtasty.mybatis.dao.OrderDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.ContentsTextVO;
import com.wowtasty.mybatis.vo.DeliveryManVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.OrderRestaurantVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.MailSender;
import com.wowtasty.util.SessionUtil;
import com.wowtasty.util.StringConvertUtil;
import com.wowtasty.vo.OrderListConditionVO;
import com.wowtasty.vo.OrderListVO;
import com.wowtasty.vo.RadioButtonVO;


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
	private List<DeliveryManVO> deliverymanList = new ArrayList<DeliveryManVO>();
	private List<RadioButtonVO> timeList = new ArrayList<RadioButtonVO>();
	private List<RadioButtonVO> orderStatusRList = new ArrayList<RadioButtonVO>();
	
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
	private OrderListConditionVO condition = new OrderListConditionVO();
	
	/** Searched List */
	private List<OrderListVO> list = new ArrayList<OrderListVO>();
	
	/** Parameter */
	private String selectedOrderID = "";
	private String selectedRestaurantID = "";
	private int selectedDeliverymanID = 0;
	private String selectedOrderMemberEmail = "";
	private String selectedRestaurantEmail = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		
		// set up dropdown menu from codes
		orderStatusList = codeMap.get(Constants.KEY_CD_ORDER_STATUS);
		
		// set up deliveryman dropdown menu 
		DeliveryManDao dao = new DeliveryManDao();
		deliverymanList = dao.selectAll();
		
		// set up conditions
		timeList.add(new RadioButtonVO("", "All"));
		timeList.add(new RadioButtonVO("-0:30", "30 Minutes"));
		timeList.add(new RadioButtonVO("-1:00", "1 Hour&"));
		timeList.add(new RadioButtonVO("-3:00", "3 Hours"));
		
		orderStatusRList.add(new RadioButtonVO("", "All Status"));
		orderStatusRList.add(new RadioButtonVO(Constants.KEY_ORDER_STATUS_PENDING, "Pending Only"));
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Order List page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");

		//Select All Order List
		OrderDao dao = new OrderDao();
		list = dao.selectCurrent(condition);

		logger.info("<---Init end --->");
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
	 * Insert member data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---Execute start --->");
		
		String returnString = SUCCESS;
		
		//Check date type
		if (!"".equals(condition.getFromDate().trim())) {
			if (!StringConvertUtil.isDate(condition.getFromDate(), DAY_PATTERN)) {
				// Date(from) is not datetype
				addFieldError("condition.fromDate", getText("E0003_1", new String[]{"Date(from)"}));
				returnString = INPUT;
			}
		}

		if (!"".equals(condition.getToDate().trim())) {
			if (!StringConvertUtil.isDate(condition.getToDate(), DAY_PATTERN)) {
				// Date(to) is not datetype
				addFieldError("condition.toDate", getText("E0003_1", new String[]{"Date(to)"}));
				returnString = INPUT;
			}
		}
		
		//Select Order List
		OrderDao dao = new OrderDao();
		list = dao.select(condition);
		
		logger.info("<---Execute end --->");
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
	 * Change Order status Pending to Ordered
	 * @return SUCCESS
	 */
	public String changeOrderStatus() throws Exception {
		logger.info("<---changeOrderStatus start --->");
		
		// set up order status and update Information
		OrderRestaurantVO vo = new OrderRestaurantVO();
		vo.setOrderStatus(Constants.KEY_ORDER_STATUS_ORDERED);
		vo.setOrderID(selectedOrderID);
		vo.setRestaurantID(selectedRestaurantID);
		vo.setUpdateID(uservo.getMemberID());
		
		//Change order status
		OrderDao dao = new OrderDao();
		int returnCnt = dao.changeOrderStatus(vo);
		
		if (returnCnt > 0) {
			addActionMessage("Order Status has been changed successfully"); 
			// Send email -> Send Email to User and Restaurant
			// Get contents, subject for member
			ContentsTextDao cdao = new ContentsTextDao();
			ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_MEM);
			
			// Set up the mail contents for member
			MailSender sender = new MailSender();
			sender.sendEmail(selectedOrderMemberEmail, cvo.getSubject() + selectedOrderID, cvo.getContents());
			
			// Get contents, subject for restaurant
			cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_REST);
			
			// Set up the mail contents for restaurant
			sender.sendEmail(selectedRestaurantEmail, cvo.getSubject() + selectedOrderID, cvo.getContents());

			
		} else {
			addActionError("No data has been changed."); 
		}
		
		//Re-select Current Order List
		dao = new OrderDao();
		list = dao.selectCurrent(condition);

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
		} else {
			addActionError("No data has been changed."); 
		}
		
		//Re-select Current Order List
		dao = new OrderDao();
		list = dao.selectCurrent(condition);

		logger.info("<---setDeliveryMan end --->");
		return SUCCESS;
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
	 * @return the timeList
	 */
	public List<RadioButtonVO> getTimeList() {
		return timeList;
	}

	/**
	 * @param timeList the timeList to set
	 */
	public void setTimeList(List<RadioButtonVO> timeList) {
		this.timeList = timeList;
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
	 * @return the orderStatusRList
	 */
	public List<RadioButtonVO> getOrderStatusRList() {
		return orderStatusRList;
	}

	/**
	 * @param orderStatusRList the orderStatusRList to set
	 */
	public void setOrderStatusRList(List<RadioButtonVO> orderStatusRList) {
		this.orderStatusRList = orderStatusRList;
	}

	/**
	 * @return the selectedRestaurantEmail
	 */
	public String getSelectedRestaurantEmail() {
		return selectedRestaurantEmail;
	}

	/**
	 * @param selectedRestaurantEmail the selectedRestaurantEmail to set
	 */
	public void setSelectedRestaurantEmail(String selectedRestaurantEmail) {
		this.selectedRestaurantEmail = selectedRestaurantEmail;
	}
}
