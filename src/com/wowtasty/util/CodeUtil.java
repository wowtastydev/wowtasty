package com.wowtasty.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wowtasty.mybatis.vo.CodeMasterVO;

/**
 * @author Hak C.
 *
 */
public class CodeUtil {
	
	/**
	 * @param map : code map
	 * @param groupCD : group code
	 * @param code : code
	 * @return String : code Name
	 */
	static public String getCdName(Map<String, List<CodeMasterVO>> map, String groupCD, String code) {
		if (code == null || "".equals(code)) {
			return "";
		}
		String codeName = "";
		List<CodeMasterVO> codeList = map.get(groupCD);
		if (codeList != null) {
			for (CodeMasterVO vo : codeList) {
				if (code.equals(vo.getCode())) {
					codeName = vo.getName();
					break;
				}
			}
		}

		return codeName;
	}
	/**
	 * @param map : code map
	 * @param groupCD : group code
	 * @param code : code
	 * @return String : code short name
	 */
	static public String getCdShortName(Map<String, List<CodeMasterVO>> map, String groupCD, String code) {
		if (code == null || "".equals(code)) {
			return "";
		}
		String codeName = "";
		List<CodeMasterVO> codeList = map.get(groupCD);
		for (CodeMasterVO vo : codeList) {
			if (code.equals(vo.getCode())) {
				codeName = vo.getShortName();
				break;
			}
		}
		return codeName;
	}
	
	/**
	 * @param orgList : code list
	 * @return List<CodeMasterVO> : copied new code list
	 */
	static public List<CodeMasterVO> copyCode(List<CodeMasterVO> orgList) {
		if (orgList == null || orgList.size() == 0) {
			return orgList;
		}
		
		List<CodeMasterVO> newList = new ArrayList<CodeMasterVO>();
		CodeMasterVO vo = null;

		for (CodeMasterVO orgvo : orgList) {
			vo = new CodeMasterVO();
			vo.setGroupCd(orgvo.getGroupCd());
			vo.setCode(orgvo.getCode());
			vo.setName(orgvo.getName());
			vo.setShortName(orgvo.getShortName());
			vo.setSort(orgvo.getSort());
			vo.setValue(orgvo.getValue());
			vo.setNaFlag(orgvo.getNaFlag());
			vo.setUpdateID(orgvo.getUpdateID());
			vo.setUpdateTime(orgvo.getUpdateTime());
			newList.add(vo);
		}
		return newList;
	}
}
