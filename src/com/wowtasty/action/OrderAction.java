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
import com.wowtasty.mybatis.dao.MemberMasterDao;
import com.wowtasty.mybatis.dao.OrderDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.ContentsTextVO;
import com.wowtasty.mybatis.vo.DeliveryManVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.OrderMasterVO;
import com.wowtasty.mybatis.vo.OrderMenuOptionVO;
import com.wowtasty.mybatis.vo.OrderMenuVO;
import com.wowtasty.mybatis.vo.OrderRestaurantVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.MailSender;
import com.wowtasty.util.SessionUtil;


/**
 * @author Hak C.
 *
 */
public class OrderAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(OrderAction.class);
	
	/** dropdown box, radio button list */
	private List<DeliveryManVO> deliverymanList = new ArrayList<DeliveryManVO>();
	private List<CodeMasterVO> creditCardTypeList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> cityList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> orderStatusList = new ArrayList<CodeMasterVO>();
	private List<CodeMasterVO> declineReasonList = new ArrayList<CodeMasterVO>();
	
	/** codemaster map */
	private Map<String, List<CodeMasterVO>> codeMap = new HashMap<String, List<CodeMasterVO>>();
	
	/** user information */
	private MemberMasterVO uservo = new MemberMasterVO();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name;at FoodDelivery WowTasty
	private String headTitle = "Food Order Edit";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Food Order Edit";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Food Order Edit";
	
	/** Parameter */
	private String selectedOrderID = "";
	private String selectedRestaurantID = "";
	private String selectedStatus = "";
	private int selectedDeliverymanID = 0;
	private String googleMapUrl = "";
	private String declinedReason = "";
	private OrderMasterVO master = new OrderMasterVO();
	private OrderRestaurantVO restaurant = new OrderRestaurantVO();
	private List<OrderRestaurantVO> restaurantList = new ArrayList<OrderRestaurantVO>();
	private List<OrderMenuVO> menuList = new ArrayList<OrderMenuVO>();
	private List<OrderMenuOptionVO> menuoptionList = new ArrayList<OrderMenuOptionVO>();
	private String page = "";

	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		
		// set up dropdown menu from codes
		creditCardTypeList = codeMap.get(Constants.KEY_CD_CREDITCARD_TYPE);
		cityList = codeMap.get(Constants.KEY_CD_CITY);
		orderStatusList = codeMap.get(Constants.KEY_CD_ORDER_STATUS);
		declineReasonList = codeMap.get(Constants.KEY_CD_DECLINEREASON_TYPE);
				
		// set up deliveryman dropdown menu 
		DeliveryManDao dao = new DeliveryManDao();
		deliverymanList = dao.selectAll();
		
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

		// Get Order Detail 
		getOrderDetail();

		logger.info("<---Init end --->");
		return SUCCESS;
	}
	
	/**
	 * Insert member data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---Execute start --->");
	
		logger.info("<---Execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Change Order status
	 * @return SUCCESS
	 */
	public String changeOrderStatus() throws Exception {
		logger.info("<---changeOrderStatus start --->");
		
		// set up order status and update Information
		OrderRestaurantVO vo = new OrderRestaurantVO();
		vo.setOrderStatus(selectedStatus);
		vo.setOrderID(selectedOrderID);
		vo.setRestaurantID(selectedRestaurantID);
		if (Constants.CODE_ORDER_STATUS_DECLINED.equals(selectedStatus)) {
			vo.setDeclinedReason(declinedReason);
		}
		vo.setUpdateID(uservo.getMemberID());
		
		//Change order status
		OrderDao dao = new OrderDao();
		int returnCnt = dao.changeOrderStatus(vo);
		
		if (returnCnt > 0) {
			addActionMessage("Order Status has been changed successfully");
			
			// Get Order Detail to reload detail
			getOrderDetail();
			
			//Get order member's name 
			MemberMasterDao mdao = new MemberMasterDao();
			MemberMasterVO mvo = new MemberMasterVO();
			mvo = mdao.selectByEmail(master.getOrderMemberEmail());
			
			//STATUS ORDERED
			if (Constants.CODE_ORDER_STATUS_ORDERED.equals(selectedStatus)) {
				// Send email -> Send Email to User and Restaurant
				// Get contents, subject for member
				ContentsTextDao cdao = new ContentsTextDao();
				ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_MEM);
				
				// Set restaurant's name and order member's name into email contents
				String contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());

				// Set up the mail contents for member
				MailSender sender = new MailSender();
				sender.sendEmail(master.getOrderMemberEmail(), cvo.getSubject() + selectedOrderID, contents);
				
				// Get contents, subject for restaurant
				cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_REST);
				
				// Set restaurant's name and member's name into email contents
				contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());
				
				// Set up the mail contents for restaurant
				sender.sendEmail(restaurant.getRestaurantEmail(), cvo.getSubject() + selectedOrderID, contents);
				
				addActionMessage("Email has been sent to the customer and the restaurant successfully");
			}
			
			//STATUS CONFIRMED
			if (Constants.CODE_ORDER_STATUS_CONFIRMED.equals(selectedStatus)) {
				// Send email -> Send Email to User and Restaurant
				// Get contents, subject for member
				ContentsTextDao cdao = new ContentsTextDao();
				ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_CONFIRMED_MEM);
				
				// Set restaurant's name and order member's name into email contents
				String contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());

				// Set up the mail contents for member
				MailSender sender = new MailSender();
				sender.sendEmail(master.getOrderMemberEmail(), cvo.getSubject() + selectedOrderID, contents);
				
				// Get contents, subject for restaurant
				cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_REST);
				
				// Set restaurant's name and member's name into email contents
				contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());
				
				// Set up the mail contents for restaurant
				sender.sendEmail(restaurant.getRestaurantEmail(), cvo.getSubject() + selectedOrderID, contents);
				
				addActionMessage("Email has been sent to the customer and the restaurant successfully");
			}
			
			//STATUS DECLINED
			if (Constants.CODE_ORDER_STATUS_DECLINED.equals(selectedStatus)) {
				// Send email -> Send Email to User and Restaurant
				// Get contents, subject for member
				ContentsTextDao cdao = new ContentsTextDao();
				ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_DECLINED_MEM);
				
				// Set restaurant's name and order member's name into email contents
				String contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());

				// Set up the mail contents for member
				MailSender sender = new MailSender();
				sender.sendEmail(master.getOrderMemberEmail(), cvo.getSubject() + selectedOrderID, contents);
				
				// Get contents, subject for restaurant
				cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_REST);
				
				// Set restaurant's name and member's name into email contents
				contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());
				
				// Set up the mail contents for restaurant
				sender.sendEmail(restaurant.getRestaurantEmail(), cvo.getSubject() + selectedOrderID, contents);

				addActionMessage("Email has been sent to the customer and the restaurant successfully");
			}
			
			//STATUS CANCELED
			if (Constants.CODE_ORDER_STATUS_CANCELED.equals(selectedStatus)) {
				// Send email -> Send Email to User and Restaurant
				// Get contents, subject for member
				ContentsTextDao cdao = new ContentsTextDao();
				ContentsTextVO cvo = cdao.selectByID(Constants.KEY_CONTENTS_CANCELED_MEM);
				
				// Set restaurant's name and order member's name into email contents
				String contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());

				// Set up the mail contents for member
				MailSender sender = new MailSender();
				sender.sendEmail(master.getOrderMemberEmail(), cvo.getSubject() + selectedOrderID, contents);
				
				// Get contents, subject for restaurant
				cvo = cdao.selectByID(Constants.KEY_CONTENTS_ORDERED_REST);
				
				// Set restaurant's name and member's name into email contents
				contents = cvo.getContents();
				contents = contents.replace("<PARAM_MEMBER_NAME>", mvo.getFirstName() + " " + mvo.getLastName());
				contents = contents.replace("<PARAM_RESTAURANT_NAME>", restaurant.getRestaurantName());
				
				// Set up the mail contents for restaurant
				sender.sendEmail(restaurant.getRestaurantEmail(), cvo.getSubject() + selectedOrderID, contents);
				
				addActionMessage("Email has been sent to the customer and the restaurant successfully");
			}
			
		} else {
			addActionError("No data has been changed."); 
			
			// Get Order Detail to reload detail
			getOrderDetail();
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
		} else {
			addActionError("No data has been changed."); 
		}
		
		// Get Order Detail to reload detail
		getOrderDetail();
		
		logger.info("<---setDeliveryMan end --->");
		return SUCCESS;
	}
	
	/**
	 * Go back to Order list or Current order list
	 * @return SUCCESS
	 */
	public String goOrderList() throws Exception {
		logger.info("<---goOrderList start --->");
	
		logger.info("<---goOrderList end --->");
		return SUCCESS;
	}
	
	/**
	 * Get order detail data from DB
	 * @return SUCCESS
	 */	
	private void getOrderDetail() {
		//Select Order Information
		OrderDao dao = new OrderDao();
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap = dao.selectByID(selectedOrderID);
		
		master = (OrderMasterVO)orderMap.get(Constants.KEY_ORDER_MASTER);
		restaurantList = (List<OrderRestaurantVO>)orderMap.get(Constants.KEY_ORDER_RESTAURANT);
		menuList = (List<OrderMenuVO>)orderMap.get(Constants.KEY_ORDER_MENU);
		menuoptionList = (List<OrderMenuOptionVO>)orderMap.get(Constants.KEY_ORDER_MENUOPTION);
		
		//Set Selected Order Restaurant
		for (int i = 0; i < restaurantList.size(); i++) {
			if (selectedRestaurantID.equals(restaurantList.get(i).getRestaurantID())) {
				restaurant = restaurantList.get(i);
				
				//If Delivery Type is Take out, get GoogleMap URL
				if (Constants.CODE_DELIVERY_TYPE_TAKEOUT.equals(restaurant.getDeliveryType())) {
					RestaurantMasterDao rdao = new RestaurantMasterDao();
					RestaurantMasterVO rvo = rdao.selectByID(selectedRestaurantID);
					if (rvo != null) {
						googleMapUrl = rvo.getGoogleMapURL();
					}
				}
				break;
			}
		}
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
	 * @return the selectedStatus
	 */
	public String getSelectedStatus() {
		return selectedStatus;
	}

	/**
	 * @param selectedStatus the selectedStatus to set
	 */
	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
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
	 * @return the creditCardTypeList
	 */
	public List<CodeMasterVO> getCreditCardTypeList() {
		return creditCardTypeList;
	}

	/**
	 * @param creditCardTypeList the creditCardTypeList to set
	 */
	public void setCreditCardTypeList(List<CodeMasterVO> creditCardTypeList) {
		this.creditCardTypeList = creditCardTypeList;
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
	 * @return the menuoptionList
	 */
	public List<OrderMenuOptionVO> getMenuoptionList() {
		return menuoptionList;
	}

	/**
	 * @param menuoptionList the menuoptionList to set
	 */
	public void setMenuoptionList(List<OrderMenuOptionVO> menuoptionList) {
		this.menuoptionList = menuoptionList;
	}

	/**
	 * @return the googleMapUrl
	 */
	public String getGoogleMapUrl() {
		return googleMapUrl;
	}

	/**
	 * @param googleMapUrl the googleMapUrl to set
	 */
	public void setGoogleMapUrl(String googleMapUrl) {
		this.googleMapUrl = googleMapUrl;
	}

	/**
	 * @return the restaurantList
	 */
	public List<OrderRestaurantVO> getRestaurantList() {
		return restaurantList;
	}

	/**
	 * @param restaurantList the restaurantList to set
	 */
	public void setRestaurantList(List<OrderRestaurantVO> restaurantList) {
		this.restaurantList = restaurantList;
	}

	/**
	 * @return the declineReasonList
	 */
	public List<CodeMasterVO> getDeclineReasonList() {
		return declineReasonList;
	}

	/**
	 * @param declineReasonList the declineReasonList to set
	 */
	public void setDeclineReasonList(List<CodeMasterVO> declineReasonList) {
		this.declineReasonList = declineReasonList;
	}

	/**
	 * @return the declinedReason
	 */
	public String getDeclinedReason() {
		return declinedReason;
	}

	/**
	 * @param declinedReason the declinedReason to set
	 */
	public void setDeclinedReason(String declinedReason) {
		this.declinedReason = declinedReason;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
}
