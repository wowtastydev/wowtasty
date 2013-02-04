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
		sb.append("<table border=1>");
		sb.append("<tr><td colspan=2 bgcolor='gray'>[Order Basic Information]</td></tr>");
		sb.append("<tr><td>Order ID : </td><td>").append(master.getOrderID()).append("</td></tr>");
		sb.append("<tr><td>Order Member Telephone : </td><td>").append(master.getOrderMemberTelephone()).append("</td></tr>");
		sb.append("<tr><td>Order Time : </td><td>").append(StringConvertUtil.convertDate2String(master.getOrderTime())).append("</td></tr>");
		sb.append("<tr><td>Delivery Time : </td><td>").append(StringConvertUtil.convertDate2String(master.getDeliveryTime())).append("</td></tr>");
		sb.append("<tr><td colspan=2 bgcolor='gray'>[Order Detail Information]</td></tr>");
		sb.append("<tr><td>Restaurant Name: </td><td>").append(restaurant.getRestaurantName()).append("</td></tr>");
		sb.append("<tr><td>Restaurant Address: </td><td>").append(restaurantMaster.getSuite()).append(" ").append(restaurantMaster.getAddress())
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_CITY, restaurantMaster.getCity()))
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_PROVINCE, restaurantMaster.getProvince()))
			.append("</td></tr>");
		sb.append("<tr><td>Restaurant Telephone: </td><td>").append(restaurantMaster.getTelephone()).append("</td></tr>");
		sb.append("<tr><td>Delivery Type: </td><td>").append(CodeUtil.getCdName(codeMap, Constants.KEY_CD_DELIVERY_TYPE, restaurant.getDeliveryType())).append("</td></tr>");
		sb.append("<tr><td>Delivery/Take-out Address : </td><td>").append(restaurant.getDeliverySuite()).append(" ").append(restaurant.getDeliveryAddress())
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_CITY, restaurant.getDeliveryCity()))
			.append(", ").append(CodeUtil.getCdShortName(codeMap, Constants.KEY_CD_PROVINCE, restaurant.getDeliveryProvince()))
			.append("</td></tr>");
		sb.append("<tr><td>Delivery/Take-out Telephone: </td><td>").append(restaurant.getDeliveryTelephone()).append("</td></tr>");
		sb.append("<tr bgcolor='gray'><td>Menu Name </td><td>Price</td></tr>");
		for (OrderMenuVO vo : menuList) {
			sb.append("<tr><td>").append(vo.getMenuName()).append("</td><td align='right'>").append("  $").append(vo.getTotalPriceWithTax()).append("</td></tr>");
		}
		sb.append("<tr bgcolor='gray'><td>Total Price : </td><td align='right'>").append("  $").append(restaurant.getTotalPriceWithTax()).append("</td></tr>");
		sb.append("</table>");
		
		return sb.toString();
	}
}
