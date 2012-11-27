package com.wowtasty.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

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
		for (CodeMasterVO vo : codeList) {
			if (code.equals(vo.getCode())) {
				codeName = vo.getName();
				break;
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
}
