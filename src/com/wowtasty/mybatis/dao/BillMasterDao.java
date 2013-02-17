package com.wowtasty.mybatis.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.wowtasty.mybatis.FactoryService;
import com.wowtasty.mybatis.vo.BillDetailVO;
import com.wowtasty.mybatis.vo.BillMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.vo.BillingListConditionVO;
import com.wowtasty.vo.BillingListVO;
import com.wowtasty.vo.ReportingExcelVO;
import com.wowtasty.vo.ReportingListVO;



/**
 * @author Hak C.
 *
 */
public class BillMasterDao {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(BillMasterDao.class);
	
	/**Sql Session Factory*/
	SqlSessionFactory factory = null;
	
	/**Contruction: get factory instance*/
	public BillMasterDao() {
		factory = FactoryService.getFactory();
	}
	
	/**
	 * @return List<BillingListVO>: Billing list
	 */
	public List<BillingListVO> select(BillingListConditionVO condition) {
		SqlSession sqlSession = factory.openSession();
		List<BillingListVO> returnObject = new ArrayList<BillingListVO>();
		try {
			returnObject = sqlSession.selectList("billmaster.select", condition);
		} catch (Exception e) {
			logger.error("!!!!!BillMasterDao select occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao select occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param id: billID
	 * @return Map<String, Object>: Bill Master Data & Bill Detail list
	 */
	public Map<String, Object> selectByID(String id) {
		SqlSession sqlSession = factory.openSession();
		Map<String, Object> returnObject = new HashMap<String, Object>();
		BillMasterVO master = new BillMasterVO();
		List<BillDetailVO> detailList = new ArrayList<BillDetailVO>();
		try {
			//Get bill master data
			master = sqlSession.selectOne("billmaster.selectByID", id);
			returnObject.put(Constants.KEY_BILL_MASTER, master);
			
			//Get bill detail list
			detailList = sqlSession.selectList("billdetail.selectByID", id);
			returnObject.put(Constants.KEY_BILL_DETAIL, detailList);
		} catch (Exception e) {
			logger.error("!!!!!BillMasterDao selectByID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectByID occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param yearMonth: bill's yearMonth
	 * @return List<ReportingListVO>: Reporting Detail list
	 */
	public List<ReportingListVO> selectReportingSummary(String yearMonth) {
		SqlSession sqlSession = factory.openSession();
		List<ReportingListVO> returnObject = new ArrayList<ReportingListVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Timestamp(sdf.parse(yearMonth).getTime()));
			Integer days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("yearMonth", yearMonth);
			paramMap.put("days", days.toString());
			
			//Get Summary
			returnObject = sqlSession.selectList("billdetail.selectReportingSummary", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("!!!!!BillMasterDao selectReportingSummary occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectReportingSummary occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param yearMonth: bill's yearMonth
	 * @return List<ReportingListVO>: Reporting Detail list
	 */
	public List<ReportingListVO> selectReportingSummaryRate(String yearMonth) {
		SqlSession sqlSession = factory.openSession();
		List<ReportingListVO> returnObject = new ArrayList<ReportingListVO>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Timestamp(sdf.parse(yearMonth).getTime()));
			cal.add(Calendar.MONTH, -1);
			String lastYearMonth = sdf.format(cal.getTime());
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("yearMonth", yearMonth);
			paramMap.put("lastYearMonth", lastYearMonth);
			
			//Get Summary Rate
			returnObject = sqlSession.selectList("billdetail.selectReportingSummaryRate", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("!!!!!BillMasterDao selectReportingSummaryRate occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectReportingSummaryRate occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param yearMonth: bill's yearMonth
	 * @param reportType: restaurant/day/date/hour/deliveryman
	 * @return List<ReportingListVO>: Reporting Detail list
	 */
	public List<ReportingListVO> selectReporting(String yearMonth, String reportType) {
		SqlSession sqlSession = factory.openSession();
		String mapper = "";
		if (Constants.REPORT_TYPE_RESTAURANT.equals(reportType)) {
			mapper = "billdetail.selectReportingRestaurant";
		} else if (Constants.REPORT_TYPE_DAY.equals(reportType)) {
			mapper = "billdetail.selectReportingDay";
		} else if (Constants.REPORT_TYPE_DATE.equals(reportType)) {
			mapper = "billdetail.selectReportingDate";
		} else if (Constants.REPORT_TYPE_HOUR.equals(reportType)) {
			mapper = "billdetail.selectReportingHour";
		} else if (Constants.REPORT_TYPE_DELIVERY.equals(reportType)) {
			mapper = "billdetail.selectReportingDelivery";
		}
		List<ReportingListVO> returnObject = new ArrayList<ReportingListVO>();
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Timestamp(sdf.parse(yearMonth).getTime()));
			cal.add(Calendar.MONTH, -1);
			String lastYearMonth = sdf.format(cal.getTime());
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("yearMonth", yearMonth);
			paramMap.put("lastYearMonth", lastYearMonth);
			
			//Get bill detail list by reportType
			returnObject = sqlSession.selectList(mapper, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("!!!!!BillMasterDao selectReporting occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectReporting occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param yearMonth: billing YearMonth
	 * @return List<ReportingExcelVO>: Reporting data list for excel
	 */
	public List<ReportingExcelVO> selectReportingExcel(String yearMonth) {
		SqlSession sqlSession = factory.openSession();
		List<ReportingExcelVO> returnObject = new ArrayList<ReportingExcelVO>();
		try {
			//Get Reporting data list for excel
			returnObject = sqlSession.selectList("billdetail.selectReportingExcel", yearMonth);
		} catch (Exception e) {
			logger.error("!!!!!BillMasterDao selectReportingExcel occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectReportingExcel occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @param yearMonth: billing YearMonth
	 * @param semiMonthType: billing SemiMonthType
	 * @param companyID: billing companyID
	 * @return BillMasterVO: Bill Master Data
	 */
	public BillMasterVO selectLastPayment(String yearMonth, String semiMonthType, String companyID) {
		SqlSession sqlSession = factory.openSession();
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("yearMonth", yearMonth);
		paramMap.put("semiMonthType", semiMonthType);
		paramMap.put("companyID", companyID);
		BillMasterVO returnObject = new BillMasterVO();
		try {
			//Get bill master data
			returnObject = sqlSession.selectOne("billmaster.selectLastPayment", paramMap);
		} catch (Exception e) {
			logger.error("!!!!!BillMasterDao selectLastPayment occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectLastPayment occurs error:" + e);
	        	throw new RuntimeException(e);
	        }
		}
		return returnObject;
	}
	
	/**
	 * @return String: billID
	 */
	public String selectMaxID() {
		SqlSession sqlSession = factory.openSession();
		String returnObject = "";
		try {
			returnObject = sqlSession.selectOne("billmaster.selectMaxID");
		} catch (Exception e) {
			logger.error("!!!!!BillMasterDao selectMaxID occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectMaxID occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @return String: Max YearMonth
	 */
	public String selectMaxYearMonth() {
		SqlSession sqlSession = factory.openSession();
		String returnObject = "";
		try {
			returnObject = sqlSession.selectOne("billmaster.selectMaxYearMonth");
		} catch (Exception e) {
			logger.error("!!!!!BillMasterDao selectMaxYearMonth occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao selectMaxYearMonth occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
		return returnObject;
	}
	
	/**
	 * @param BillMasterVO: Bill master data
	 * @param List<BillDetailVO>: Bill detail list
	 */
	public void insert(BillMasterVO vo, List<BillDetailVO> list) {
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		try {
			// Insert Bill master
			sqlSession.insert("billmaster.insert", vo);
			
			// Insert Bill detail
			for (int i = 0; i < size; i++) {
				list.get(i).setSeq(i + 1);
				sqlSession.insert("billdetail.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!BillMasterDao insert occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao insert occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Delete and insert 
	 * @param BillMasterVO: Bill master data
	 * @param List<BillDetailVO>: Bill detail list
	 */
	public void update(BillMasterVO vo, List<BillDetailVO> list) {
		if (vo == null || list == null) {
			return;
		}
		SqlSession sqlSession = factory.openSession();
		int size = list.size();
		try {
			
			// Delete Bill master
			sqlSession.delete("billmaster.delete", vo.getBillingID());
			
			// Delete Bill detail
			sqlSession.delete("billdetail.delete", vo.getBillingID());
			
			// Insert Bill master
			sqlSession.insert("billmaster.insert", vo);
			
			// Insert Bill detail
			for (int i = 0; i < size; i++) {
				list.get(i).setSeq(i + 1);
				sqlSession.insert("billdetail.insert", list.get(i));
			}
			// After inserting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!BillMasterDao update occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao update occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * @param id: billingID
	 */
	public void delete(String id) {
		// autocommit session open
		SqlSession sqlSession = factory.openSession();
		try {
			// Delete Bill master
			sqlSession.delete("billmaster.delete", id);
			
			// Delete Bill detail
			sqlSession.delete("billdetail.delete", id);
			
			// After deleting, Commit;
			sqlSession.commit();
		} catch (Exception e) {
			// If error occurs, rollback
			sqlSession.rollback();
			logger.error("!!!!!BillMasterDao delete occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				sqlSession.close();
			} catch (Exception e) {
				logger.error("!!!!!BillMasterDao delete occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
}
