package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.OrderMasterVO;
import com.wowtasty.mybatis.vo.OrderMenuOptionVO;
import com.wowtasty.mybatis.vo.OrderMenuVO;
import com.wowtasty.mybatis.vo.OrderRestaurantVO;
import com.wowtasty.util.Constants;
import com.wowtasty.vo.BalanceListConditionVO;
import com.wowtasty.vo.BalanceListVO;
import com.wowtasty.vo.OrderListConditionVO;
import com.wowtasty.vo.OrderListVO;



/**
 * @author Hak C.
 *
 */
public class OrderDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(OrderDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public OrderDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @param condition: Order List Search Condition
	 * @return List<OrderListVO>: Order list
	 */
	public List<OrderListVO> select(OrderListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<OrderListVO> returnObject = new ArrayList<OrderListVO>();
		try {
			returnObject = sqlSession.selectList("ordermaster.select", condition);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao select occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao select occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param condition: Order List Search Condition for restaurant users
	 * @return List<OrderListVO>: Order list
	 */
	public List<OrderListVO> selectRest(OrderListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<OrderListVO> returnObject = new ArrayList<OrderListVO>();
		try {
			returnObject = sqlSession.selectList("ordermaster.selectRest", condition);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectRest occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectRest occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param condition: Order List Search Condition on the balance management page
	 * @return List<BalanceListVO>: Order list on the balance management page
	 */
	public List<BalanceListVO> selectBalance(BalanceListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<BalanceListVO> returnObject = new ArrayList<BalanceListVO>();
		try {
			returnObject = sqlSession.selectList("ordermaster.selectBalance", condition);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectBalance occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectBalance occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param condition: Order List Search Condition for current balance on the balance management page
	 * @return List<BalanceListVO>: Order list for current balance on the balance management page
	 */
	public List<BalanceListVO> selectCurrentBalance(BalanceListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<BalanceListVO> returnObject = new ArrayList<BalanceListVO>();
		try {
			returnObject = sqlSession.selectList("ordermaster.selectCurrentBalance", condition);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectCurrentBalance occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectCurrentBalance occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param condition: Current Order List Search Condition
	 * @return List<OrderListVO>: Current Order list
	 */
	public List<OrderListVO> selectCurrent(OrderListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<OrderListVO> returnObject = new ArrayList<OrderListVO>();
		try {
			returnObject = sqlSession.selectList("ordermaster.selectCurrent", condition);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectCurrent occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectCurrent occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * Current Order List for restaurant users
	 * @param condition: Current Order List Search Condition
	 * @return List<OrderListVO>: Current Order list
	 */
	public List<OrderListVO> selectCurrentRest(OrderListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<OrderListVO> returnObject = new ArrayList<OrderListVO>();
		try {
			returnObject = sqlSession.selectList("ordermaster.selectCurrentRest", condition);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectCurrentRest occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectCurrentRest occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}

	/**
	 * @param orderID: orderID
	 * @return Map<Object>: OrderMaster, List<OrderRestaurantVO>, List<OrderMenuVO>, List<OrderMenuOptionVO>
	 */
	public Map<String, Object> selectByID(String orderID) {
		SqlSession sqlSession = factory.openSession();
		Map<String, Object> returnObject = new HashMap<String, Object>();
		OrderMasterVO master = new OrderMasterVO();
		List<OrderRestaurantVO> restaurantList = new ArrayList<OrderRestaurantVO>();
		List<OrderMenuVO> menuList = new ArrayList<OrderMenuVO>();
		List<OrderMenuOptionVO> menuoptionList = new ArrayList<OrderMenuOptionVO>();
		try {
			//Get order master data
			master = sqlSession.selectOne("ordermaster.selectByID", orderID);
			returnObject.put(Constants.KEY_ORDER_MASTER, master);
			
			//Get order restaurant data
			restaurantList = sqlSession.selectList("orderrestaurant.selectByOrderID", orderID);
			returnObject.put(Constants.KEY_ORDER_RESTAURANT, restaurantList);
			
			//Get order menu data
			menuList = sqlSession.selectList("ordermenu.selectByOrderID", orderID);
			returnObject.put(Constants.KEY_ORDER_MENU, menuList);
			
			//Get order menuoption data
			menuoptionList = sqlSession.selectList("ordermenuoption.selectByOrderID", orderID);
			returnObject.put(Constants.KEY_ORDER_MENUOPTION, menuoptionList);
			
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param orderID: orderID
	 * @param restaurantID: restaurantID
	 * @return Map<Object>: OrderMaster, OrderRestaurant, List<OrderMenuVO>
	 */
	public Map<String, Object> selectByRestaurantID(String orderID, String restaurantID) {
		SqlSession sqlSession = factory.openSession();
		Map<String, Object> returnObject = new HashMap<String, Object>();
		Map<String, String> paramMap = new HashMap<String, String>();
		OrderMasterVO master = new OrderMasterVO();
		OrderRestaurantVO restaurant = new OrderRestaurantVO();
		List<OrderMenuVO> menuList = new ArrayList<OrderMenuVO>();
		List<OrderMenuOptionVO> menuoptionList = new ArrayList<OrderMenuOptionVO>();
		try {
			//Get order master data
			master = sqlSession.selectOne("ordermaster.selectByID", orderID);
			returnObject.put(Constants.KEY_ORDER_MASTER, master);
			
			//Get order restaurant data
			paramMap.put("orderID", orderID);
			paramMap.put("restaurantID", restaurantID);
			restaurant = sqlSession.selectOne("orderrestaurant.selectByID", paramMap);
			returnObject.put(Constants.KEY_ORDER_RESTAURANT, restaurant);
			
			//Get order menu list
			menuList = sqlSession.selectList("ordermenu.selectByOrderID", orderID);
			returnObject.put(Constants.KEY_ORDER_MENU, menuList);
			
			//Get order menuoption data
			menuoptionList = sqlSession.selectList("ordermenuoption.selectByOrderID", orderID);
			returnObject.put(Constants.KEY_ORDER_MENUOPTION, menuoptionList);
			
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectByRestaurantID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectByRestaurantID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return String: OrderID
	 */
	public String selectMaxID() {
		SqlSession sqlSession = factory.openSession();
		String returnObject = "";
		try {
			returnObject = sqlSession.selectOne("ordermaster.selectMaxID");
		} catch (Exception e) {
			logger.error("!!!!!OrderDao selectMaxID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao selectMaxID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param OrderMasterVO: Order master data
	 * @return int: inserted row cnt
	 */
	public int insert(OrderMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("ordermaster.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param OrderMasterVO: Order master data
	 * @return int: updated row cnt
	 */
	public int updateMaster(OrderMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("ordermaster.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao updateMaster occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao updateMaster occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param OrderRestaurantVO: Order Restaurant master data to update status
	 * @return int: updated row cnt
	 */
	public int changeOrderStatus(OrderRestaurantVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("orderrestaurant.changeOrderStatus", vo);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao changeOrderStatus occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao changeOrderStatus occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param OrderRestaurantVO: Order Restaurant master data to change deliveryman
	 * @return int: updated row cnt
	 */
	public int setDeliveryMan(OrderRestaurantVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("orderrestaurant.setDeliveryMan", vo);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao setDeliveryMan occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao setDeliveryMan occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
