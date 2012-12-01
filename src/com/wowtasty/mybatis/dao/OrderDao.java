package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.OrderMasterVO;
import com.wowtasty.mybatis.vo.OrderRestaurantVO;

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
	 * @return OrderMasterVO: Order master data
	 */
	public OrderMasterVO selectByID(String orderID) {
		SqlSession sqlSession = factory.openSession();
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO returnObject = new OrderMasterVO();
		try {
			list = sqlSession.selectList("ordermaster.selectByID", orderID);
			if (list.size() > 0) {
				returnObject = list.get(0);
			}
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
	 * @return OrderMasterVO: Order master data
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
	 * @return OrderMasterVO: Order master data
	 */
	public int updateMaster(OrderMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("ordermaster.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!OrderDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return OrderRestaurantVO: Change order status 
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
	 * @return OrderRestaurantVO: Change order status 
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
