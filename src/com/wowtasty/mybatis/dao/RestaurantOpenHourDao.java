package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantOpenHourVO;
import com.wowtasty.vo.HourListVO;



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
	 * @param id: restaurantID
	 * @return List<HourListVO>: Restaurant 1st and 2nd Open Hour list
	 */
	public List<HourListVO> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		List<HourListVO> returnObject = new ArrayList<HourListVO>();
		try {
			//Get 1st and 2nd Open Hour
			returnObject = sqlSession.selectList("restaurantopenhour.selectByID", id);
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
	 * @param vo: Restaurant open hour
	 * @return int: inserted row cnt
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
	 * @param vo: Restaurant open hour
	 * @return int: updated row cnt
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
