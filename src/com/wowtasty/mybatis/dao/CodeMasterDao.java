package com.wowtasty.mybatis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.CodeMasterVO;



/**
 * @author Hak C.
 *
 */
public class CodeMasterDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(CodeMasterDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public CodeMasterDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return Map<String, List<CodeVO>>: Code lists Map by group code
	 */
	public Map<String, List<CodeMasterVO>> selectAll() {
		SqlSession sqlSession = factory.openSession();
		List<CodeMasterVO> codeList = new ArrayList<CodeMasterVO>();
		Map<String, List<CodeMasterVO>> returnObject = new HashMap<String, List<CodeMasterVO>>();
		CodeMasterVO vo = null;
		List<CodeMasterVO> list = null;
		
		try {
			codeList = sqlSession.selectList("codemaster.selectAll");
			int size = codeList.size();
			String curGroupCd = "";
			String newGroupCd = "";
			for (int i = 0; i < size; i++) {
				newGroupCd = codeList.get(i).getGroupCd();
				if (!curGroupCd.equals(newGroupCd)) {
					list = new ArrayList<CodeMasterVO>();
					returnObject.put(newGroupCd, list);
					curGroupCd = newGroupCd;
				}
				vo = codeList.get(i);
				list.add(vo);
			}
		} catch (Exception e) {
			logger.error("!!!!!CodeMasterDao selectAll occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!CodeMasterDao selectAll occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return CodeMasterVO: Code master data
	 */
	public int update(CodeMasterVO vo) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession(true);
		int returnObject = 0;
		try {
			returnObject = sqlSession.insert("codemaster.update", vo);
		} catch (Exception e) {
			logger.error("!!!!!CodeMasterDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!CodeMasterDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
}
