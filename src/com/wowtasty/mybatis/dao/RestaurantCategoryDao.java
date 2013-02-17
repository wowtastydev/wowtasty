package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantCategoryVO;

/**
 * @author Hak C.
 *
 */
public class RestaurantCategoryDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantCategoryDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public RestaurantCategoryDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @return list<RestaurantCategoryVO>: Restaurant menu category list
	 */
	public List<RestaurantCategoryVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantCategoryVO> returnObject = new ArrayList<RestaurantCategoryVO>();
		try {
			returnObject = sqlSession.selectList("restaurantcategory.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!RestaurantCategoryDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantCategoryDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param id: restaurantID
	 * @return List<RestaurantCategoryVO>: Restaurant menu category list
	 */
	public List<RestaurantCategoryVO> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantCategoryVO> returnObject = new ArrayList<RestaurantCategoryVO>();
		try {
			//Restaurant menu category list
			returnObject = sqlSession.selectList("restaurantcategory.selectByID", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantCategoryDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantCategoryDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param id: restaurantID
	 * @return List<RestaurantCategoryVO>: Restaurant menu category list
	 */
	public List<RestaurantCategoryVO> selectByRestaurant(String id) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantCategoryVO> returnObject = new ArrayList<RestaurantCategoryVO>();
		try {
			//Restaurant menu category list
			returnObject = sqlSession.selectList("restaurantcategory.selectByRestaurant", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantCategoryDao selectByRestaurant occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantCategoryDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantCategoryVO: Restaurant Category data
	 */
	public int insert(RestaurantCategoryVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantcategory.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantCategoryDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantCategoryDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantCategoryVO: Restaurant Category data
	 */
	public int update(RestaurantCategoryVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantcategory.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantCategoryDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantCategoryDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param list: Restaurant Category list
	 * @param id: Restaurant ID
	 */
	public void updateAll(List<RestaurantCategoryVO> list, String id) {
		if (list == null) {
			return;
		}
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		try {
			// Delete all Categorys
			sqlSession.delete("restaurantcategory.delete", id);
			
			// Insert Categorys
			for (int i = 0; i < size; i++) {
				sqlSession.insert("restaurantcategory.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!RestaurantCategoryDao updateAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantCategoryDao updateAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
}
