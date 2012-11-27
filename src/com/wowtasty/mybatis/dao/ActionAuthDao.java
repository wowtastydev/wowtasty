package com.wowtasty.mybatis.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.ActionAuthVO;



/**
 * @author Hak C.
 *
 */
public class ActionAuthDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(ActionAuthDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public ActionAuthDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return List<ActionAuthVO>: Authorization by action list
	 */
	public List<ActionAuthVO> selectAll() throws SQLException{
		SqlSession sqlSession = factory.openSession();
		List<ActionAuthVO> list = null;
		
		try {
			list = sqlSession.selectList("actionauth.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!ActionAuthDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!ActionAuthDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return list;
	}
	
	
}
