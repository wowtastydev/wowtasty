package com.wowtasty.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.RestaurantMasterDao;
import com.wowtasty.mybatis.dao.RestaurantPictDao;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.mybatis.vo.RestaurantMasterVO;
import com.wowtasty.mybatis.vo.RestaurantPictVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;

/**
 * @author Hak C.
 *
 */
public class RestaurantPhotoAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** constants */
	private static final Integer MAX_ROW = 5;
	private static final Long MAX_SIZE = 2*1024*1024l;
	private static final String DELIMITER = ",";

	/** Logger */
	private static Logger logger = Logger.getLogger(RestaurantPhotoAction.class);
	
	/** user information */
	private MemberMasterVO uservo = new MemberMasterVO();
	
	/** Title&Metatag */
	// Title : Restaurant Name;City Name;at FoodDelivery WowTasty
	private String headTitle = "FoodDelivery WowStaty";
	// Meta Keywords : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaKeywords = "Keywords FoodDelivery,WowStaty,Vancouver";
	// Meta Description : Restaurant Name,City Name,Postal prefix, Cuisine Type, Delivery/Take out
	private String metaDescription = "Description FoodDelivery,WowStaty,Vancouver";
	
	/** Restaurant VO*/
	private RestaurantMasterVO restVO = new RestaurantMasterVO();
	
	/** Restaurant Vos Columns */
	private String selectedID = "";
	private List<RestaurantPictVO> pictureList = new ArrayList<RestaurantPictVO>();
	private List<RestaurantPictVO> pictureUploadList = new ArrayList<RestaurantPictVO>();
	private String sortStr = "";
	
	/**
	 * Prepared method
	 */	
	public void prepare() throws Exception {
		logger.info("<---Prepare start --->");
		
		// userinfo from httpsession
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);
		uservo = (MemberMasterVO)httpSession.getAttribute(Constants.KEY_SESSION_USER);
		
		logger.info("<---Prepare end --->");
	}
	
	/**
	 * Initiate Restaurant Photo page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---Init start --->");
		
		if ("".equals(selectedID)) {
			return INPUT;
		}
		
		// Restaurant Master
		RestaurantMasterDao rdao = new RestaurantMasterDao();
		restVO = rdao.selectByID(selectedID);
		
		// Select current picture
		RestaurantPictDao dao = new RestaurantPictDao();
		pictureList = dao.selectByID(selectedID);
		
		// Add Upload picture
		RestaurantPictVO rpvo = null; 
		pictureUploadList.clear();
		for (int i = 0; i < MAX_ROW; i++) {
			rpvo = new RestaurantPictVO();
			rpvo.setRestaurantID(selectedID);
			rpvo.setSeq(i + pictureList.size() + 1);
			pictureUploadList.add(rpvo);
		}

		logger.info("<---Init end --->");
		return SUCCESS;
	}

	/**
	 * Insert restaurant photo
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		
		RestaurantPictDao rpdao = new RestaurantPictDao();
		
		// Upload Images
		int size = pictureUploadList.size();
		
		// Validation check
		boolean hasError = false;
		RestaurantPictVO rpvo = null;
		long filesize = 0;
		String fileContentType = "";
		
		for (int i = 0; i < size; i++) {
			rpvo = pictureUploadList.get(i);
			if (rpvo != null && rpvo.getFile() != null) {
				// File size MAX_SIZE
				filesize = rpvo.getFile().length();
				if (filesize > MAX_SIZE) {
					addFieldError("pictureUploadList[" + i + "].file", getText("E0006", new String[]{"Image", MAX_SIZE.toString()}));
					hasError = true;
				}
				
				// File type
				fileContentType = rpvo.getFileContentType();
				if (!fileContentType.startsWith("image/")) {
					addFieldError("pictureUploadList[" + i + "].file", getText("E0003_1", new String[]{"Image"}));
					hasError = true;
				}
			}
		}
		
		// In case of validation error, return INPUT
		if (hasError) {
			return INPUT;
		}
		
		// Upload files
		for (int i = 0; i < size; i++) {
			rpvo = pictureUploadList.get(i);
			if (rpvo != null && rpvo.getFile() != null) {
				filesize = rpvo.getFile().length();
				rpvo.setFileSize(filesize);
				upload(rpvo);
				
				// Insert new pictures
				rpvo.setUpdateID(uservo.getMemberID());
				rpdao.insert(rpvo);
			}
		}
		
		// Select current picture
		pictureList = rpdao.selectByID(selectedID);
		
		// Add Upload picture
		pictureUploadList.clear();
		for (int i = 0; i < MAX_ROW; i++) {
			rpvo = new RestaurantPictVO();
			rpvo.setRestaurantID(selectedID);
			rpvo.setSeq(i + pictureList.size() + 1);
			pictureUploadList.add(rpvo);
		}
		
		addActionMessage("Restaurant photos have been inserted successfully");
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Edit restaurant photo
	 * @return SUCCESS
	 */
	public String edit() throws Exception {
		try{
		logger.info("<---edit start --->");
		String[] sortArray = sortStr.split(DELIMITER);
		RestaurantPictDao rpdao = new RestaurantPictDao();
		String orgFileName = "";
		String newFileName = "";
		StringBuilder sb = new StringBuilder();
		List<RestaurantPictVO> newList = new ArrayList<RestaurantPictVO>();
		
		// Change all original files to temp files
		int size = pictureList.size();
		for (int i = 0; i < size; i++) {
			orgFileName = pictureList.get(i).getFileName();
			sb.setLength(0);
			newFileName = sb.append(orgFileName).append(FileUtil.STR_TEMP).toString();
			FileUtil.renameRestImage(orgFileName, newFileName);
		}

		// Change Images
		String newSeq = "";
		RestaurantPictVO newVo = null;
		RestaurantPictVO orgVo = null;
		
		for (int i = 0; i < sortArray.length; i++) {
			// Sorted new sequence
			newSeq = sortArray[i];
			// If new sequence is not the same as original sequence, because of changing orders or delete pictures
			if (!"".equals(newSeq)) {
				// change seqence
				orgVo = pictureList.get(Integer.parseInt(newSeq) - 1);
				newVo = new RestaurantPictVO();
				newVo.setRestaurantID(orgVo.getRestaurantID());
				newVo.setSeq(i + 1);
				newVo.setFileSize(orgVo.getFileSize());
				newVo.setFilePath(orgVo.getFilePath());
				newVo.setUpdateID(uservo.getMemberID());
				sb.setLength(0);
				orgFileName = sb.append(orgVo.getFileName()).append(FileUtil.STR_TEMP).toString();
				sb.setLength(0);
				newFileName = sb.append(newVo.getRestaurantID()).append("_").append(String.format("%03d", newVo.getSeq())).append(".jpg").toString();
				FileUtil.renameRestImage(orgFileName, newFileName);
				newVo.setFileName(newFileName);
				
				newList.add(newVo);
			}
		}
		// Delete original's temp file if there is any
		for (int i = 0; i < size; i++) {
			FileUtil.deleteRestTempImage(pictureList.get(i).getFileName());
		}
		
		// Update db table
		if (newList.size() == 0) {
			// Delete all
			rpdao.delete(selectedID);
		} else {
			rpdao.updateAll(newList);
		}
		
		
		// Select current picture
		pictureList = rpdao.selectByID(selectedID);
		
		// Add Upload picture
		pictureUploadList.clear();
		RestaurantPictVO rpvo = null;  
		for (int i = 0; i < MAX_ROW; i++) {
			rpvo = new RestaurantPictVO();
			rpvo.setRestaurantID(selectedID);
			rpvo.setSeq(i + pictureList.size() + 1);
			pictureUploadList.add(rpvo);
		}
		
		addActionMessage("Restaurant photos have been edited successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("<---edit end --->");
		return SUCCESS;
	}	
	
	/**
	 * File upload
	 * @param rpvo : Restaurant picture vo
	 */	
	private void upload(RestaurantPictVO rpvo) {
		StringBuilder sb = new StringBuilder();
		// File Upload
		sb.append(rpvo.getRestaurantID()).append("_").append(String.format("%03d", rpvo.getSeq())).append(".jpg");
		FileUtil.writePict(rpvo.getFile(), FileUtil.RESTAURANT_DIR, sb.toString());
		rpvo.setFileName(sb.toString());
	}

	/**
	 * @param pictureList the pictureList to set
	 */
	public void setPictureList(List<RestaurantPictVO> pictureList) {
		this.pictureList = pictureList;
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
	 * @return the pictureUploadList
	 */
	public List<RestaurantPictVO> getPictureUploadList() {
		return pictureUploadList;
	}

	/**
	 * @param pictureUploadList the pictureUploadList to set
	 */
	public void setPictureUploadList(List<RestaurantPictVO> pictureUploadList) {
		this.pictureUploadList = pictureUploadList;
	}

	/**
	 * @return the pictureList
	 */
	public List<RestaurantPictVO> getPictureList() {
		return pictureList;
	}

	/**
	 * @return the selectedID
	 */
	public String getSelectedID() {
		return selectedID;
	}

	/**
	 * @param selectedID the selectedID to set
	 */
	public void setSelectedID(String selectedID) {
		this.selectedID = selectedID;
	}

	/**
	 * @return the sortStr
	 */
	public String getSortStr() {
		return sortStr;
	}

	/**
	 * @param sortStr the sortStr to set
	 */
	public void setSortStr(String sortStr) {
		this.sortStr = sortStr;
	}

	/**
	 * @return the restVO
	 */
	public RestaurantMasterVO getRestVO() {
		return restVO;
	}

	/**
	 * @param restVO the restVO to set
	 */
	public void setRestVO(RestaurantMasterVO restVO) {
		this.restVO = restVO;
	}
}
