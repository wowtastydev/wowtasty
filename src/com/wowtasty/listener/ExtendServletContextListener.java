package com.wowtasty.listener;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.wowtasty.mybatis.dao.CodeMasterDao;
import com.wowtasty.mybatis.dao.ActionAuthDao;
import com.wowtasty.mybatis.dao.ContentsTextDao;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.ActionAuthVO;
import com.wowtasty.mybatis.vo.ContentsTextVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;

public class ExtendServletContextListener implements ServletContextListener {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(ExtendServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.debug("<---contextInitialized start --->");
		try {
            // Load code list
			CodeMasterDao dao = new CodeMasterDao();
			Map<String, List<CodeMasterVO>> map = (Map<String, List<CodeMasterVO>>)dao.selectAll();
			// Put the code list on the single tone session
			SessionUtil.getInstance().setApplicationAttribute(Constants.KEY_SESSION_CODE_LIST, map);
			
            // Load page authorization
			ActionAuthDao adao = new ActionAuthDao();
			List<ActionAuthVO> alist = (List<ActionAuthVO>)adao.selectAll();
			// Put the page authorization list on the single tone session
			SessionUtil.getInstance().setApplicationAttribute(Constants.KEY_SESSION_ACTIONAUTH_LIST, alist);

			// Load config.properties
			Properties config = new Properties();
			config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			// Put the config.properties on the single tone session
			SessionUtil.getInstance().setApplicationAttribute(Constants.KEY_SESSION_CONFIG_PROPERTIES, config);
        } catch (IOException e) {
        	logger.error("!!!!!Loading config.properties failed:" + e);
        	throw new RuntimeException(e);
        } catch (Exception e) {
        	logger.error("!!!!!contextInitialized occurs error:" + e);
        	throw new RuntimeException(e);
        }
        logger.debug("<---contextInitialized end --->");
	}
}
