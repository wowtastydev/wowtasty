package com.wowtasty.mybatis.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.CodeMasterVO;



/**
 * @author Hak C.
 *
 */
public class CodeMasterDao {
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	public CodeMasterDao() {
		factory = FactoryService.getFactory();
	}

	/**
	 * @return Map<String, List<CodeVO>>: Code lists Map by group code
	 */
	public Map<String, List<CodeMasterVO>> selectAll() throws SQLException{
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
				vo = new CodeMasterVO();
				newGroupCd = codeList.get(i).getGroupCd();
				if (!curGroupCd.equals(newGroupCd)) {
					list = new ArrayList<CodeMasterVO>();
					returnObject.put(newGroupCd, list);
					curGroupCd = newGroupCd;
				}
				vo.setGroupCd(newGroupCd);
				vo.setCode(codeList.get(i).getCode());
				vo.setShortName(codeList.get(i).getShortName());
				vo.setName(codeList.get(i).getName());
				vo.setSort(codeList.get(i).getSort());
				vo.setNaFlag(codeList.get(i).getNaFlag());
				vo.setUpdateID(codeList.get(i).getUpdateID());
				vo.setUpdateTime(codeList.get(i).getUpdateTime());
				list.add(vo);
			}
		} catch (Exception e) {
			throw new RuntimeException(":::::CodeDao selectList occurs error:" + e, e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				throw new RuntimeException(":::::CodeDao selectList occurs error:" + e, e);
			}
		}
		return returnObject;
	}
	
	
}
