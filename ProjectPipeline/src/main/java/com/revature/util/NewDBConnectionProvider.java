/**
 * 
 */
package com.revature.util;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;

/**
 * @author Jinwei Xiong
 *
 */
public class NewDBConnectionProvider extends C3P0ConnectionProvider {
	private static final Logger LOGGER = LogManager.getFormatterLogger(NewDBConnectionProvider.class); 
	private static final String url = System.getenv("DBURL");
	private static final String username = System.getenv("DBUsername");
	private static final String password = System.getenv("DBPassword");
	
	public NewDBConnectionProvider() {
		super();
	}
	@Override
	public void configure(Map props) throws HibernateException {	
		try {			
			props.put(Environment.USER, username);
			props.put(Environment.PASS, password);
			super.configure(props);
		}catch(HibernateException e) {
			e.printStackTrace();
			LOGGER.error("Error: can't resolve the username and password of DB.",e);
		}
	}

}
