package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.DeliveryOpenHourVO;



/**
 * @author Hak C.
 *
 */
public class DeliveryOpenHourDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(DeliveryOpenHourDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public DeliveryOpenHourDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @param deliveryID: deliveryID
	 * @return List<DeliveryOpenHourVO>: Delivery Open Hour list
	 */
	public List<DeliveryOpenHourVO> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		List<DeliveryOpenHourVO> returnObject = new ArrayList<DeliveryOpenHourVO>();
		try {
			//Get restaurant master data
			returnObject = sqlSession.selectList("deliveryopenhour.selectByID", id);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryOpenHourDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryOpenHourDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return DeliveryOpenHourVO: Restaurant master data
	 */
	public int insert(DeliveryOpenHourVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliveryopenhour.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryOpenHourDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryOpenHourDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return DeliveryOpenHourVO: Order master data
	 */
	public int update(DeliveryOpenHourVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliveryopenhour.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryOpenHourDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryOpenHourDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
