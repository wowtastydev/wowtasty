package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.MemberMasterVO;



/**
 * @author Hak C.
 *
 */
public class MemberMasterDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(MemberMasterDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public MemberMasterDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return List<MemberMasterVO>: Member lists
	 */
	public List<MemberMasterVO> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<MemberMasterVO> returnObject = new ArrayList<MemberMasterVO>();
		try {
			returnObject = sqlSession.selectList("membermaster.selectAll");
		} catch (Exception e) {
			logger.error("!!!!!MemberMasterDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!MemberMasterDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return MemberMasterVO: Member master data
	 */
	public MemberMasterVO selectByEmail(String email) {
		SqlSession sqlSession = factory.openSession();
		List<MemberMasterVO> list = new ArrayList<MemberMasterVO>();
		MemberMasterVO returnObject = new MemberMasterVO();
		try {
			list = sqlSession.selectList("membermaster.selectByEmail", email);
			if (list.size() > 0) {
				returnObject = list.get(0);
			}
		} catch (Exception e) {
			logger.error("!!!!!MemberMasterDao selectByEmail occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!MemberMasterDao selectByEmail occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return String: MemberID
	 */
	public String selectMaxID() {
		SqlSession sqlSession = factory.openSession();
		String returnObject = "";
		try {
			returnObject = sqlSession.selectOne("membermaster.selectMaxID");
		} catch (Exception e) {
			logger.error("!!!!!MemberMasterDao selectMaxID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!MemberMasterDao selectMaxID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return MemberMasterVO: Member master data
	 */
	public int insert(MemberMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("membermaster.insert", vo);
		} catch (Exception e) {
			logger.error("!!!!!MemberMasterDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!MemberMasterDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return MemberMasterVO: Member master data
	 */
	public int update(MemberMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("membermaster.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!MemberMasterDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!MemberMasterDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
}
