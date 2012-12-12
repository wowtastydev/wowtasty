package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantOpenHourVO;



/**
 * @author Hak C.
 *
 */
public class RestaurantOpenHourDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantOpenHourDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public RestaurantOpenHourDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @param restaurantID: restaurantID
	 * @return List<RestaurantOpenHourVO>: Restaurant Open Hour list
	 */
	public List<RestaurantOpenHourVO> selectByID(String restaurantID) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantOpenHourVO> returnObject = new ArrayList<RestaurantOpenHourVO>();
		try {
			//Get restaurant master data
			returnObject = sqlSession.selectList("restaurantopenhour.selectByID", restaurantID);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantOpenHourDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantOpenHourDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantOpenHourVO: Restaurant master data
	 */
	public int insert(RestaurantOpenHourVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantopenhour.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantOpenHourDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantOpenHourDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantOpenHourVO: Order master data
	 */
	public int update(RestaurantOpenHourVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantopenhour.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantOpenHourDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantOpenHourDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
