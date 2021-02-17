/**
 * 
 */
package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Jinwei Xiong
 *
 */
public class HibernateUtil {
	public static SessionFactory sessionFactory;
	private static final Logger LOGGER = LogManager.getFormatterLogger(HibernateUtil.class);
	private HibernateUtil() {
		
	}
    public static synchronized SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
        	/*
        	sessionFactory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        	*/
            // Creating Configuration Instance & Passing Hibernate Configuration File
            try{
            	Configuration configObj = new Configuration();
            	configObj.configure("hibernate.cfg.xml");
     
            	// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
            	ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
     
            	// Creating Hibernate SessionFactory Instance
            	sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
            }catch(HibernateException e) {
            	e.printStackTrace();
            	LOGGER.error("Error creating Session: ",e);
            }
        }
        return sessionFactory;
    }	
    
    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
            	LOGGER.error("Couldn't close SessionFactory", e);
            }
        }
    }
}