package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.DeliveryManVO;


/**
 * @author Hak C.
 *
 */
public class DeliveryManDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(DeliveryManDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public DeliveryManDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @param id: Delivery Comapny ID
	 * @return List<DeliveryManVO>: delivery man list
	 */
	public List<DeliveryManVO> selectByCompany(String id) {
		SqlSession sqlSession = factory.openSession();
		List<DeliveryManVO> returnObject = new ArrayList<DeliveryManVO>();
		try {
			returnObject = sqlSession.selectList("deliveryman.selectByCompany", id);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryManDao selectByCompany occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryManDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param : Delivery company ID
	 * @return Integer: DeliveryManID
	 */
	public Integer selectMaxID(String id) {
		SqlSession sqlSession = factory.openSession();
		Integer returnObject = 0;
		try {
			returnObject = sqlSession.selectOne("deliveryman.selectMaxID", id);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryManDao selectMaxID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryManDao selectMaxID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return DeliveryManVO: Delivery man data
	 */
	public int insert(DeliveryManVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliveryman.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryManDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryManDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return DeliveryManVO: Delivery man data
	 */
	public int update(DeliveryManVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliveryman.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryManDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryManDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
