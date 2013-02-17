package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantDeliveryAreaVO;


/**
 * @author Hak C.
 *
 */
public class RestaurantDeliveryAreaDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantDeliveryAreaDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public RestaurantDeliveryAreaDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return List<RestaurantDeliveryAreaVO>: Restaurant Delivery area list
	 */
	public List<RestaurantDeliveryAreaVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantDeliveryAreaVO> returnObject = new ArrayList<RestaurantDeliveryAreaVO>();
		try {
			returnObject = sqlSession.selectList("restaurantdeliveryarea.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDeliveryAreaDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDeliveryAreaDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param id: Restaurant ID
	 * @return List<RestaurantDeliveryAreaVO>: Restaurant Delivery area list
	 */
	public List<RestaurantDeliveryAreaVO> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantDeliveryAreaVO> returnObject = new ArrayList<RestaurantDeliveryAreaVO>();
		try {
			//Get delivery master list
			returnObject = sqlSession.selectList("restaurantdeliveryarea.selectByID", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDeliveryAreaDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDeliveryAreaDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantDeliveryAreaVO: Restaurant Delivery area data
	 */
	public int insert(RestaurantDeliveryAreaVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantdeliveryarea.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDeliveryAreaDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDeliveryAreaDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param list: Restaurant Delivery area list
	 * @param id: Restaurant ID
	 */
	public void updateAll(List<RestaurantDeliveryAreaVO> list, String id) {
		if (list == null) {
			return;
		}
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		try {
			// Delete all areas
			sqlSession.delete("restaurantdeliveryarea.delete", id);
			
			// Insert area
			for (int i = 0; i < size; i++) {
				list.get(i).setSeq(i + 1);
				sqlSession.insert("restaurantdeliveryarea.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!RestaurantDeliveryAreaDao updateAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDeliveryAreaDao updateAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * @param id: Delivery Company ID
	 * @return int: delete count
	 */
	public int deleteByDeliveryID(String id) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantdeliveryarea.deleteByDeliveryID", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDeliveryAreaDao deleteByDeliveryID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDeliveryAreaDao deleteByDeliveryID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
