package com.wowtasty.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wowtasty.mybatis.dao.OrderDao;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.OrderMasterVO;
import com.wowtasty.mybatis.vo.OrderMenuVO;
import com.wowtasty.mybatis.vo.OrderRestaurantVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;

/**
 * @author Hak C.
 *
 */
public class OrderUtil {
	
	/**
	 * Make email context about order detail
	 * @param orderID : order ID
	 * @param restaurantID : order restaurant ID
	 * @return String : email html context about order detail
	 */	
	static public String contextOrderDetail(String orderID, String restaurantID, Map<String, List<CodeMasterVO>> codeMap) {
		//Get Order Information
		OrderDao dao = new OrderDao();
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap = dao.selectByRestaurantID(orderID, restaurantID);
		
		OrderMasterVO master = (OrderMasterVO)orderMap.get(Constants.KEY_ORDER_MASTER);
		OrderRestaurantVO restaurant = (OrderRestaurantVO)orderMap.get(Constants.KEY_ORDER_RESTAURANT);
		List<OrderMenuVO> menuList = (List<OrderMenuVO>)orderMap.get(Constants.KEY_ORDER_MENU);
		
		//Get Restaurant Information
		RestaurantMasterDao rdao = new RestaurantMasterDao();
		RestaurantMasterVO restaurantMaster = rdao.selectByID(restaurantID);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<table width='500' border='0' cellpadding='3' cellspacing='1' bgcolor='#ffae00'>");
		sb.append("<tr><td colspan='2' valign='bottom' bgcolor='#FF7800'><strong><font color='#FFFFFF'>[Order Basic Information]</font></strong></td></tr>");
		sb.append("<tr><td width='180' bgcolor='#FFFFFF'>Order ID : </td><td bgcolor='#FFFFFF'>").append(master.getOrderID()).append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Order Member Telephone : </td><td bgcolor='#FFFFFF'>").append(master.getOrderMemberTelephone()).append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Order Time : </td><td bgcolor='#FFFFFF'>").append(StringConvertUtil.convertDate2String(master.getOrderTime())).append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Delivery Time : </td><td bgcolor='#FFFFFF'>").append(StringConvertUtil.convertDate2String(master.getDeliveryTime())).append("</td></tr>");
		sb.append("<tr><td colspan='2' valign='bottom' bgcolor='#FF7800'><strong><font color='#FFFFFF'>[Order Detail Information]</font></strong></td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Restaurant Name: </td><td bgcolor='#FFFFFF'>").append(restaurant.getRestaurantName()).append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Restaurant Address: </td><td bgcolor='#FFFFFF'>").append(restaurantMaster.getSuite()).append(" ").append(restaurantMaster.getAddress())
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_CITY, restaurantMaster.getCity()))
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_PROVINCE, restaurantMaster.getProvince()))
			.append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Restaurant Telephone: </td><td bgcolor='#FFFFFF'>").append(restaurantMaster.getTelephone()).append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Delivery Type: </td><td bgcolor='#FFFFFF'>").append(CodeUtil.getCdName(codeMap, Constants.KEY_CD_DELIVERY_TYPE, restaurant.getDeliveryType())).append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Delivery/Take-out Address : </td><td bgcolor='#FFFFFF'>").append(restaurant.getDeliverySuite()).append(" ").append(restaurant.getDeliveryAddress())
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_CITY, restaurant.getDeliveryCity()))
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_PROVINCE, restaurant.getDeliveryProvince()))
			.append("</td></tr>");
		sb.append("<tr><td bgcolor='#FFFFFF'>Delivery/Take-out Telephone: </td><td bgcolor='#FFFFFF'>").append(restaurant.getDeliveryTelephone()).append("</td></tr>");
		sb.append("<tr><td valign='bottom' bgcolor='#FF7800'><strong><font color='#FFFFFF'>Menu Name </font></strong></td><td valign='bottom' bgcolor='#FF7800'><strong><font color='#FFFFFF'>Price </font></strong></td></tr>");
		for (OrderMenuVO vo : menuList) {
			sb.append("<tr><td bgcolor='#FFFFFF'>").append(vo.getMenuName()).append("</td><td align='right' bgcolor='#FFFFFF'>").append("  $").append(vo.getTotalPriceWithTax()).append("</td></tr>");
		}
		sb.append("<tr><td bgcolor='#FFFFFF'><strong>Total Price : </strong></td><td align='right' bgcolor='#FFFFFF'><strong>").append("  $").append(restaurant.getTotalPriceWithTax()).append("</strong></td></tr>");
		sb.append("</table>");
		
		return sb.toString();
	}
}
