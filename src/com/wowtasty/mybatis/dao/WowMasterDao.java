package com.wowtasty.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.WowMasterVO;



/**
 * @author Hak C.
 *
 */
public class WowMasterDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(WowMasterDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public WowMasterDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return WowMasterVO: Wow master data
	 */
	public WowMasterVO selectBySeq(Integer seq) {
		SqlSession sqlSession = factory.openSession();
		WowMasterVO returnObject = new WowMasterVO();
		try {
			returnObject = sqlSession.selectOne("wowmaster.selectBySeq", seq);
		} catch (Exception e) {
			logger.error("!!!!!WowMasterDao selectBySeq occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!WowMasterDao selectBySeq occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return WowMasterVO: Wow master data
	 */
	public int insert(WowMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("wowmaster.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!WowMasterDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!WowMasterDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return WowMasterVO: Wow master data
	 */
	public int update(WowMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("wowmaster.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!WowMasterDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!WowMasterDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
