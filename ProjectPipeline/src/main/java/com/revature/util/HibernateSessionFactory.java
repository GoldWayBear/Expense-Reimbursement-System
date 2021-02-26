/**
 * 
 */
package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Jinwei Xiong
 *
 */
public class HibernateSessionFactory {
	private static SessionFactory sessionFactory;
	private static final Logger LOGGER = LogManager.getFormatterLogger(HibernateSessionFactory.class);
	private HibernateSessionFactory() {
		
	}
    public static synchronized SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
        	/*
        	sessionFactory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        	*/
            // Creating Configuration Instance & Passing Hibernate Configuration File
            try{
            	/*
            	Configuration configObj = new Configuration();
            	configObj.configure("hibernate.cfg.xml");
     
            	// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
            	ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
     
            	// Creating Hibernate SessionFactory Instance
            	sessionFactory = configObj.buildSessionFactory(serviceRegistryObj);
            	*/
            	
            	/*
            	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                */
            	
            	
            	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

                sessionFactory = new MetadataSources( serviceRegistry )
                            .buildMetadata()
                            .buildSessionFactory();
                            	
        		
            }catch(HibernateException e) {
            	e.printStackTrace();
            	LOGGER.error("Error creating Session: ",e);
            }
        }
        return sessionFactory;
    }	
    
    
	public static Session getSession() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", System.getenv("DBURL"))
					.setProperty("hibernate.connection.username", System.getenv("DBUsername"))
					.setProperty("hibernate.connection.password", System.getenv("DBPassword"))
					.buildSessionFactory();
		}
		
		return sessionFactory.getCurrentSession();
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
