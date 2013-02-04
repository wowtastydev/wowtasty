package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantPictVO;


/**
 * @author Hak C.
 *
 */
public class RestaurantPictDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantPictDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public RestaurantPictDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @param id: Restaurant ID
	 * @return List<RestaurantPictVO>: Restaurant picture list
	 */
	public List<RestaurantPictVO> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantPictVO> returnObject = new ArrayList<RestaurantPictVO>();
		try {
			//Get restaurant photo list
			returnObject = sqlSession.selectList("restaurantpict.selectByID", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantPictDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantPictDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantPictVO: Restaurant picture data
	 */
	public int insert(RestaurantPictVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantpict.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantPictDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantPictDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param list: Restaurant pictures list
	 */
	public void updateAll(List<RestaurantPictVO> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		String id = list.get(0).getRestaurantID();
		try {
			// Delete all pictures
			sqlSession.delete("restaurantpict.delete", id);
			
			// Insert pictures
			for (int i = 0; i < size; i++) {
				list.get(i).setSeq(i + 1);
				sqlSession.insert("restaurantpict.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!RestaurantPictDao updateAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantPictDao updateAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * @param id: Restaurant ID
	 */
	public int delete(String id) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			// Delete all restaurant pictures
			returnObject = sqlSession.delete("restaurantpict.delete", id);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantPictDao delete occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantPictDao delete occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
