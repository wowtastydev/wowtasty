package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.DeliveryAreaVO;


/**
 * @author Hak C.
 *
 */
public class DeliveryAreaDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(DeliveryAreaDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public DeliveryAreaDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return List<DeliveryAreaVO>: Delivery area list
	 */
	public List<DeliveryAreaVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<DeliveryAreaVO> returnObject = new ArrayList<DeliveryAreaVO>();
		try {
			returnObject = sqlSession.selectList("deliveryarea.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!DeliveryAreaDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryAreaDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param id: deliveryID
	 * @return List<DeliveryAreaVO>: Delivery area list
	 */
	public List<DeliveryAreaVO> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		List<DeliveryAreaVO> returnObject = new ArrayList<DeliveryAreaVO>();
		try {
			//Get delivery master list
			returnObject = sqlSession.selectList("deliveryarea.selectByID", id);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryAreaDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryAreaDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return DeliveryAreaVO: Delivery area data
	 */
	public int insert(DeliveryAreaVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("deliveryarea.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!DeliveryAreaDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryAreaDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param list: Delivery area list
	 * @param id: Delivery CompanyID
	 */
	public void updateAll(List<DeliveryAreaVO> list, String id) {
		if (list == null) {
			return;
		}
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		try {
			// Delete all areas
			sqlSession.delete("deliveryarea.delete", id);
			
			// Insert area
			for (int i = 0; i < size; i++) {
				list.get(i).setSeq(i + 1);
				sqlSession.insert("deliveryarea.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!DeliveryAreaDao updateAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!DeliveryAreaDao updateAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
}
