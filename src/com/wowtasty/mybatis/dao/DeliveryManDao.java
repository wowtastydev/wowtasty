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
	 * @return List<DeliveryManVO>: delivery man list
	 */
	public List<DeliveryManVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<DeliveryManVO> returnObject = new ArrayList<DeliveryManVO>();
		try {
			returnObject = sqlSession.selectList("deliveryman.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!DeliveryManDao selectAll occurs error:" + e);
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
}
