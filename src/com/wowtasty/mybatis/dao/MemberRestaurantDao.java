package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.MemberRestaurantVO;



/**
 * @author Hak C.
 *
 */
public class MemberRestaurantDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(MemberRestaurantDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public MemberRestaurantDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @param id: Member ID
	 * @return MemberRestaurantVO: Member's restaurant role data
	 */
	public List<MemberRestaurantVO> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		List<MemberRestaurantVO> returnObject = new ArrayList<MemberRestaurantVO>();
		try {
			returnObject = sqlSession.selectList("memberrestaurant.selectByID", id);
		} catch (Exception e) {
			logger.error("!!!!!MemberRestaurantDao selectByEmail occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!MemberRestaurantDao selectByEmail occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}

	/**
	 * Delete and insert 
	 * @param List<MemberRestaurantVO>: Member's restaurant list
	 * @param id: Member ID
	 */
	public void updateAll(List<MemberRestaurantVO> list, String id) {
		if (list == null) {
			return;
		}
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		try {
			// Delete all Member's restaurant
			sqlSession.delete("memberrestaurant.delete", id);
			
			// Insert Member's restaurant
			for (int i = 0; i < size; i++) {
				sqlSession.insert("memberrestaurant.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!MemberRestaurantDao updateAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!MemberRestaurantDao updateAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
}
