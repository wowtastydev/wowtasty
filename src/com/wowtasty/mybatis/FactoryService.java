package com.wowtasty.mybatis;

import java.io.Reader;
import java.io.IOException;	

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FactoryService {
	
	private static SqlSessionFactory factory = null;
	
	private FactoryService(){}
	
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("com/wowtasty/mybatis/mapper/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(":::::FactoryService occurs error:" + e, e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(":::::FactoryService error occur:" + e, e);
			}
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
