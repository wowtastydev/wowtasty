package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.vo.RestaurantListConditionVO;



/**
 * @author Hak C.
 *
 */
public class RestaurantMasterDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantMasterDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public RestaurantMasterDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @return List<RestaurantMasterVO>: All Restaurant list
	 */
	public List<RestaurantMasterVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantMasterVO> returnObject = new ArrayList<RestaurantMasterVO>();
		try {
			returnObject = sqlSession.selectList("restaurantmaster.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return List<RestaurantMasterVO>: Restaurant list
	 */
	public List<RestaurantMasterVO> select(RestaurantListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<RestaurantMasterVO> returnObject = new ArrayList<RestaurantMasterVO>();
		try {
			returnObject = sqlSession.selectList("restaurantmaster.select", condition);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDao select occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDao select occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param restaurantID: restaurantID
	 * @return RestaurantMasterVO: Restaurant Master Data
	 */
	public RestaurantMasterVO selectByID(String restaurantID) {
		SqlSession sqlSession = factory.openSession();
		RestaurantMasterVO returnObject = new RestaurantMasterVO();
		try {
			//Get restaurant master data
			returnObject = sqlSession.selectOne("restaurantmaster.selectByID", restaurantID);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return String: RestaurantID
	 */
	public String selectMaxID() {
		SqlSession sqlSession = factory.openSession();
		String returnObject = "";
		try {
			returnObject = sqlSession.selectOne("restaurantmaster.selectMaxID");
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDao selectMaxID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDao selectMaxID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantMasterVO: Restaurant master data
	 */
	public int insert(RestaurantMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantmaster.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return RestaurantMasterVO: Order master data
	 */
	public int update(RestaurantMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("restaurantmaster.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!RestaurantDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!RestaurantDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
