package com.wowtasty.mybatis;

import java.io.Reader;
import java.io.IOException;	

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class FactoryService {
	
	/** Logger */
	private static Logger logger = Logger.getLogger(FactoryService.class);
	
	/** Sql session factory */
	private static SqlSessionFactory factory = null;
	
	private FactoryService(){}
	
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("com/wowtasty/mybatis/mapper/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
        	logger.error("!!!!!FactoryService occurs error:" + e);
        	throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error("!!!!!FactoryService occurs error:" + e);
	        	throw new RuntimeException(e);
			}
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
