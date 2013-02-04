package com.wowtasty.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.OrderAdjustmentVO;

/**
 * @author Hak C.
 *
 */
public class OrderAdjustmentDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(OrderAdjustmentDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public OrderAdjustmentDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @return OrderAdjustmentVO: Order adjustment data
	 */
	public int insert(OrderAdjustmentVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("orderadjustment.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!OrderAdjustmentDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderAdjustmentDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param seq: Order adjustment seq
	 */
	public int delete(Integer seq) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			// Delete Order adjustment
			returnObject = sqlSession.delete("orderadjustment.delete", seq);
		} catch (Exception e) {
			logger.error("!!!!!OrderAdjustmentDao delete occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!OrderAdjustmentDao delete occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
