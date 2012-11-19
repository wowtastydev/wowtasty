package com.wowtasty.mybatis.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.MemberMasterVO;



/**
 * @author Hak C.
 *
 */
public class MemberMasterDao {
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	public MemberMasterDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return List<MemberMasterVO>: Member lists
	 */
	public List<MemberMasterVO> selectAll() throws SQLException{
		SqlSession sqlSession = factory.openSession();
		List<MemberMasterVO> returnObject = new ArrayList<MemberMasterVO>();
		try {
			returnObject = sqlSession.selectList("membermaster.selectAll");
		} catch (Exception e) {
			throw new RuntimeException(":::::MemberMasterDao selectList occurs error:" + e, e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				throw new RuntimeException(":::::MemberMasterDao selectList occurs error:" + e, e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return MemberMasterVO: Member master data
	 */
	public MemberMasterVO selectByEmail(String email) throws SQLException{
		SqlSession sqlSession = factory.openSession();
		List<MemberMasterVO> list = new ArrayList<MemberMasterVO>();
		MemberMasterVO returnObject = new MemberMasterVO();
		try {
			list = sqlSession.selectList("membermaster.selectByEmail", email);
			if (list.size() > 0) {
				returnObject = list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(":::::MemberMasterDao selectByEmail occurs error:" + e, e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				throw new RuntimeException(":::::MemberMasterDao selectByEmail occurs error:" + e, e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return String: MemberID
	 */
	public String selectMaxID() throws SQLException{
		SqlSession sqlSession = factory.openSession();
		String returnObject = "";
		try {
			returnObject = sqlSession.selectOne("membermaster.selectMaxID");
		} catch (Exception e) {
			throw new RuntimeException(":::::MemberMasterDao insert occurs error:" + e, e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				throw new RuntimeException(":::::MemberMasterDao insert occurs error:" + e, e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return MemberMasterVO: Member master data
	 */
	public int insert(MemberMasterVO vo) throws SQLException{
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("membermaster.insert", vo);
		} catch (Exception e) {
			throw new RuntimeException(":::::MemberMasterDao insert occurs error:" + e, e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				throw new RuntimeException(":::::MemberMasterDao insert occurs error:" + e, e);
			}
		}
		return returnObject;
	}
}
