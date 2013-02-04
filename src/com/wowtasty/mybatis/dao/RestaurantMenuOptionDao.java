package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantMenuOptionVO;

/**
 * @author Hak C.
 *
 */
public class RestaurantMenuOptionDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantMenuOptionDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public RestaurantMenuOptionDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @return ist<RestaurantMenuOptionVO>: Restaurant menu list
	 */
	public List<RestaurantMenuOptionVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantMenuOptionVO> returnObject = new ArrayList<RestaurantMenuOptionVO>();
		try {
			returnObject = sqlSession.selectList("restaurantmenuoption.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuOptionDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuOptionDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}

	/**
	 * @param menuID: menuID
	 * @param restaurantID: restaurantID
	 * @return List<RestaurantMenuOptionVO>: Restaurant menu option list
	 */
	public List<RestaurantMenuOptionVO> selectByID(String restaurantID, Integer menuID) {
		SqlSession sqlSession = factory.openSession();
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("restaurantID", restaurantID);
		condition.put("menuID", menuID.toString());
		List<RestaurantMenuOptionVO> returnObject = new ArrayList<RestaurantMenuOptionVO>();
		try {
			//Restaurant menu list
			returnObject = sqlSession.selectList("restaurantmenuoption.selectByID", condition);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuOptionDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuOptionDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	
	/**
	 * @param id: restaurantID
	 * @return List<RestaurantMenuOptionVO>: Restaurant menu category list
	 */
	public List<RestaurantMenuOptionVO> selectByRestaurant(String id) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantMenuOptionVO> returnObject = new ArrayList<RestaurantMenuOptionVO>();
		try {
			//Restaurant menu list
			returnObject = sqlSession.selectList("restaurantmenuoption.selectByRestaurant", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuOptionDao selectByRestaurant occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuOptionDao selectByRestaurant occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantMenuOptionVO: Restaurant Category data
	 */
	public int insert(RestaurantMenuOptionVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantmenuoption.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuOptionDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuOptionDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantMenuOptionVO: Restaurant Category data
	 */
	public int update(RestaurantMenuOptionVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantmenuoption.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantMenuOptionDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuOptionDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * Delete and insert 
	 * @param List<RestaurantMenuOptionVO>: Menu Option list
	 */
	public void updateAll(List<RestaurantMenuOptionVO> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		String restaurantID = list.get(0).getRestaurantID();
		Integer menuID = list.get(0).getMenuID();
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("restaurantID", restaurantID);
		paraMap.put("menuID", menuID.toString());
		try {
			// Delete all Menu Option
			sqlSession.delete("restaurantmenuoption.delete", paraMap);
			
			// Insert Menu Option
			for (int i = 0; i < size; i++) {
				list.get(i).setOptionID(i + 1);
				sqlSession.insert("restaurantmenuoption.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!RestaurantMenuOptionDao updateAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantMenuOptionDao updateAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
}
