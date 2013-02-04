package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantMenuVO;
import com.wowtasty.vo.MenuListConditionVO;

/**
 * @author Hak C.
 *
 */
public class RestaurantMenuDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantMenuDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public RestaurantMenuDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @param restaurantID: restaurantID
	 * @param menuID: restaurantID
	 * @return List<RestaurantMenuVO>: Restaurant menu category list
	 */
	public RestaurantMenuVO selectByID(String restaurantID, Integer menuID) {
		SqlSession sqlSession = factory.openSession();
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("restaurantID", restaurantID);
		condition.put("menuID", menuID.toString());
		RestaurantMenuVO returnObject = new RestaurantMenuVO();
		try {
			//Restaurant menu list
			returnObject = sqlSession.selectOne("restaurantmenu.selectByID", condition);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param id: restaurantID
	 * @return List<RestaurantMenuVO>: Restaurant menu category list
	 */
	public List<RestaurantMenuVO> selectByRestaurant(String id) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantMenuVO> returnObject = new ArrayList<RestaurantMenuVO>();
		try {
			//Restaurant menu list
			returnObject = sqlSession.selectList("restaurantmenu.selectByRestaurant", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuDao selectByRestaurant occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuDao selectByRestaurant occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param condition: MenuList Condition
	 * @return List<RestaurantMenuVO>: Restaurant menu category list
	 */
	public List<RestaurantMenuVO> selectByCategory(MenuListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantMenuVO> returnObject = new ArrayList<RestaurantMenuVO>();
		try {
			//Restaurant menu list
			returnObject = sqlSession.selectList("restaurantmenu.selectByCategory", condition);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuDao selectByCategory occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuDao selectByCategory occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param restaurantID : Restaurant ID
	 * @return Integer: MenuID
	 */
	public Integer selectMaxID(String restaurantID) {
		SqlSession sqlSession = factory.openSession();
		Integer returnObject = 1;
		try {
			returnObject = sqlSession.selectOne("restaurantmenu.selectMaxID", restaurantID);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuDao selectMaxID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuDao selectMaxID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantMenuVO: Restaurant Category data
	 */
	public int insert(RestaurantMenuVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantmenu.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantMenuVO: Restaurant Category data
	 */
	public int update(RestaurantMenuVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantmenu.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantMenuVO: Restaurant Category data
	 */
	public int delete(RestaurantMenuVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantmenu.delete", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuDao delete occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuDao delete occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
