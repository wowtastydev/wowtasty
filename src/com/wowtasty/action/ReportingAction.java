package com.wowtasty.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.wowtasty.mybatis.dao.BillMasterDao;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.FileUtil;
import com.wowtasty.vo.ReportingExcelVO;
import com.wowtasty.vo.ReportingListVO;


/**
 * @author Hak C.
 *
 */
public class ReportingAction extends ActionSupport implements Preparable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(ReportingAction.class);
	
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
	private String selectedYearMonth = "";
	
	/** Searched List */
	private List<ReportingListVO> summaryList = new ArrayList<ReportingListVO>();
	private List<ReportingListVO> summaryRateList = new ArrayList<ReportingListVO>();
	private List<ReportingListVO> detailList = new ArrayList<ReportingListVO>();
	private List<ReportingListVO> detailListDay = new ArrayList<ReportingListVO>();
	private List<ReportingListVO> detailListDate = new ArrayList<ReportingListVO>();
	private List<ReportingListVO> detailListHour = new ArrayList<ReportingListVO>();
	private List<ReportingListVO> detailListDelivery = new ArrayList<ReportingListVO>();

	/** Excel variable*/
	private InputStream inputStream = null;
	private String excelFileName = "";
	
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
	 * Initialize Reporting Page
	 * @return SUCCESS
	 */
	public String init() throws Exception {
		logger.info("<---init start --->");
		
		//Select Reporting data by the latest yearmonth
		BillMasterDao dao = new BillMasterDao();
		selectedYearMonth = dao.selectMaxYearMonth();
		// Default : Report by restaurants
		summaryList = dao.selectReportingSummary(selectedYearMonth);
		summaryRateList = dao.selectReportingSummaryRate(selectedYearMonth);
		detailList = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_RESTAURANT);
		detailListDay = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_DAY);
		detailListDate = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_DATE);
		detailListHour = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_HOUR);
		detailListDelivery = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_DELIVERY);
		
		logger.info("<---init end --->");
		return SUCCESS;
	}
	
	/**
	 * Search Reporting data
	 * @return SUCCESS
	 */
	@Override
	public String execute() throws Exception {
		logger.info("<---execute start --->");
		
		//Select Reporting data by the selected yearmonth
		BillMasterDao dao = new BillMasterDao();
		summaryList = dao.selectReportingSummary(selectedYearMonth);
		summaryRateList = dao.selectReportingSummaryRate(selectedYearMonth);
		detailList = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_RESTAURANT);
		detailListDay = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_DAY);
		detailListDate = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_DATE);
		detailListHour = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_HOUR);
		detailListDelivery = dao.selectReporting(selectedYearMonth, Constants.REPORT_TYPE_DELIVERY);
		
		logger.info("<---execute end --->");
		return SUCCESS;
	}
	
	/**
	 * Download Reporting data list as a excel file
	 * @return SUCCESS
	 */
	public String download() throws Exception {
		logger.info("<---download start --->");
		
		// get reporting data list 
		BillMasterDao dao = new BillMasterDao();
		List<ReportingExcelVO> excelList = dao.selectReportingExcel(selectedYearMonth);
		
		// download excel file 
		File downloadFile = FileUtil.downloadReportList(selectedYearMonth, excelList);
		inputStream = new FileInputStream(downloadFile);
		// Set excel file name
		excelFileName = selectedYearMonth;
		
		logger.info("<---download end --->");
		return SUCCESS;
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
	 * @return the selectedYearMonth
	 */
	public String getSelectedYearMonth() {
		return selectedYearMonth;
	}

	/**
	 * @param selectedYearMonth the selectedYearMonth to set
	 */
	public void setSelectedYearMonth(String selectedYearMonth) {
		this.selectedYearMonth = selectedYearMonth;
	}

	/**
	 * @return the summaryList
	 */
	public List<ReportingListVO> getSummaryList() {
		return summaryList;
	}

	/**
	 * @return the detailList
	 */
	public List<ReportingListVO> getDetailList() {
		return detailList;
	}

	/**
	 * @return the detailListDay
	 */
	public List<ReportingListVO> getDetailListDay() {
		return detailListDay;
	}

	/**
	 * @param detailListDay the detailListDay to set
	 */
	public void setDetailListDay(List<ReportingListVO> detailListDay) {
		this.detailListDay = detailListDay;
	}

	/**
	 * @return the detailListDate
	 */
	public List<ReportingListVO> getDetailListDate() {
		return detailListDate;
	}

	/**
	 * @param detailListDate the detailListDate to set
	 */
	public void setDetailListDate(List<ReportingListVO> detailListDate) {
		this.detailListDate = detailListDate;
	}

	/**
	 * @return the detailListHour
	 */
	public List<ReportingListVO> getDetailListHour() {
		return detailListHour;
	}

	/**
	 * @param detailListHour the detailListHour to set
	 */
	public void setDetailListHour(List<ReportingListVO> detailListHour) {
		this.detailListHour = detailListHour;
	}

	/**
	 * @param summaryList the summaryList to set
	 */
	public void setSummaryList(List<ReportingListVO> summaryList) {
		this.summaryList = summaryList;
	}

	/**
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<ReportingListVO> detailList) {
		this.detailList = detailList;
	}

	/**
	 * @return the detailListDelivery
	 */
	public List<ReportingListVO> getDetailListDelivery() {
		return detailListDelivery;
	}

	/**
	 * @param detailListDelivery the detailListDelivery to set
	 */
	public void setDetailListDelivery(List<ReportingListVO> detailListDelivery) {
		this.detailListDelivery = detailListDelivery;
	}

	/**
	 * @return the summaryRateList
	 */
	public List<ReportingListVO> getSummaryRateList() {
		return summaryRateList;
	}

	/**
	 * @param summaryRateList the summaryRateList to set
	 */
	public void setSummaryRateList(List<ReportingListVO> summaryRateList) {
		this.summaryRateList = summaryRateList;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the excelFileName
	 */
	public String getExcelFileName() {
		return excelFileName;
	}

	/**
	 * @param excelFileName the excelFileName to set
	 */
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
}
