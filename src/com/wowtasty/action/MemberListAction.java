package com.wowtasty.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.MemberMasterDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.CodeUtil;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;


/**
 * @author Hak C.
 *
 */
public class MemberListAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(MemberListAction.class);
	
	/** dropdown box list */
	private List<CodeMasterVO> roleList = new ArrayList<CodeMasterVO>();
	
	/** codemaster map */
	private Map<String, List<CodeMasterVO>> codeMap = new HashMap<String, List<CodeMasterVO>>();
	
	/** user information */
	private MemberMasterVO uservo = new MemberMasterVO();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name;at FoodDelivery WowTasty
	private String headTitle = "FoodDelivery WowStaty";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Keywords FoodDelivery,WowStaty,Vancouver";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Description FoodDelivery,WowStaty,Vancouver";
	
	/** Search Condition */
	private List<String> conditionRoles = new ArrayList<String>();
	
	/** Searched List */
	private List<MemberMasterVO> list = new ArrayList<MemberMasterVO>();
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---prepare start --->");
		// codes from session
		codeMap = (Map<String, List<CodeMasterVO>>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CODE_LIST);
		
		// set up dropdown menu from codes
		List<CodeMasterVO> orgList = codeMap.get(Constants.KEY_CD_ROLE);
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		// set up roleList with only same or lower level of user's role
		roleList = CodeUtil.copyCode(orgList);
		int size = roleList.size();
		for (int i = 0; i < size; i++) {
			if (Integer.parseInt(roleList.get(i).getCode()) < Integer.parseInt(uservo.getAuth())) {
				// In case of the same or lower level of user's role
				roleList.remove(i--);
				size--;
			}
		}
		logger.info("<---prepare end --->");
	}
	
	/**
	 * Initiate MemberList page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");
		
		MemberMasterDao dao = new MemberMasterDao();
		
		// Select all memember by same or lower level of user's role
		int size = roleList.size();
		for (int i = 0; i < size; i++) {
			conditionRoles.add(roleList.get(i).getCode());
		}
		list = dao.select(conditionRoles);

		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Select member list
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		MemberMasterDao dao = new MemberMasterDao();
		if (conditionRoles.size() == 0) {
			// Member type is required
			addFieldError("conditionRoles", getText("E0001_1", new String[]{"Member Type"}));
			list.clear();
			return INPUT;
		}
		
		list = dao.select(conditionRoles);
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}

	/**
	 * @return the roleList
	 */
	public List<CodeMasterVO> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<CodeMasterVO> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the uservo
	 */
	public MemberMasterVO getUservo() {
		return uservo;
	}

	/**
	 * @param uservo the uservo to set
	 */
	public void setUservo(MemberMasterVO uservo) {
		this.uservo = uservo;
	}

	/**
	 * @return the headTitle
	 */
	public String getHeadTitle() {
		return headTitle;
	}

	/**
	 * @param headTitle the headTitle to set
	 */
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}

	/**
	 * @return the metaKeywords
	 */
	public String getMetaKeywords() {
		return metaKeywords;
	}

	/**
	 * @param metaKeywords the metaKeywords to set
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	/**
	 * @return the metaDescription
	 */
	public String getMetaDescription() {
		return metaDescription;
	}

	/**
	 * @param metaDescription the metaDescription to set
	 */
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	/**
	 * @return the conditionRoles
	 */
	public List<String> getConditionRoles() {
		return conditionRoles;
	}

	/**
	 * @param conditionRoles the conditionRoles to set
	 */
	public void setConditionRoles(List<String> conditionRoles) {
		this.conditionRoles = conditionRoles;
	}

	/**
	 * @return the list
	 */
	public List<MemberMasterVO> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<MemberMasterVO> list) {
		this.list = list;
	}
}
