package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.DeliveryMasterVO;


/**
 * @author Hak C.
 *
 */
public class DeliveryMasterDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(DeliveryMasterDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public DeliveryMasterDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return List<DeliveryMasterVO>: Delivery Master list
	 */
	public List<DeliveryMasterVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<DeliveryMasterVO> returnObject = new ArrayList<DeliveryMasterVO>();
		try {
			returnObject = sqlSession.selectList("deliverymaster.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!DeliveryMasterDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryMasterDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param id: deliveryID
	 * @return DeliveryMasterVO: Delivery Master
	 */
	public DeliveryMasterVO selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		DeliveryMasterVO returnObject = new DeliveryMasterVO();
		try {
			//Get delivery master
			returnObject = sqlSession.selectOne("deliverymaster.selectByID", id);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryMasterDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryMasterDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return String: DeliveryID
	 */
	public String selectMaxID() {
		SqlSession sqlSession = factory.openSession();
		String returnObject = "";
		try {
			returnObject = sqlSession.selectOne("deliverymaster.selectMaxID");
		} catch (Exception e) {
			logger.error("!!!!!DeliveryMasterDao selectMaxID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryMasterDao selectMaxID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param DeliveryMasterVO: Delivery master data
	 * @return int: update count
	 */
	public int insert(DeliveryMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliverymaster.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryMasterDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryMasterDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param DeliveryMasterVO: Delivery master data
	 * @return int: update count
	 */
	public int update(DeliveryMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliverymaster.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryMasterDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryMasterDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param id: Delivery Company ID
	 * @return int: delete count
	 */
	public int delete(String id) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliverymaster.delete", id);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryMasterDao delete occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryMasterDao delete occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
