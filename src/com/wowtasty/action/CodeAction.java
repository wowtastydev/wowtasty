package com.wowtasty.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.CodeMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;



/**
 * @author Hak C.
 *
 */
public class CodeAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** Logger */
	private static Logger logger = Logger.getLogger(CodeAction.class);
	
	/** View Variables */
	private List<CodeMasterVO> codeMasterList = new ArrayList<CodeMasterVO>();
	
	public void prepare() throws Exception {
		logger.debug("<---CodeAction prepare start --->");
		
		logger.debug("<---CodeAction prepare end --->");
	}

	public String init() throws Exception {
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		logger.debug("<---CodeAction execute start --->");
	
		//CodeMasterDao dao = new CodeMasterDao();
		//codeMasterList = dao.selectAll();
		
		logger.debug("<---CodeAction execute end --->");
		return SUCCESS;
	}

	public List<CodeMasterVO> getCodeMasterList() {
		return codeMasterList;
	}

	public void setCodeMasterList(List<CodeMasterVO> codeMasterList) {
		this.codeMasterList = codeMasterList;
	}
	

}
