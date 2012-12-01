package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.ContentsTextVO;




/**
 * @author Hak C.
 *
 */
public class ContentsTextDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(ContentsTextDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public ContentsTextDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return ContentsTextVO: Contents data
	 */
	public ContentsTextVO selectByID(Integer seq) {
		SqlSession sqlSession = factory.openSession();
		List<ContentsTextVO> list = new ArrayList<ContentsTextVO>();
		ContentsTextVO returnObject = new ContentsTextVO();
		try {
			list = sqlSession.selectList("contentstext.selectByID", seq);
			if (list.size() > 0) {
				returnObject = list.get(0);
			}
		} catch (Exception e) {
			logger.error("!!!!!ContentsTextDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!ContentsTextDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
}
