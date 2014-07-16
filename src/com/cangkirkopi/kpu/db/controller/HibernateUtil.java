package com.cangkirkopi.kpu.db.controller;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
 
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	static {
		PropertiesConfiguration config=null;
		try {
			try {
	    		 
	          config =new PropertiesConfiguration("prop/config.properties");
	    		 
	    	} catch (ConfigurationException e) {
				 e.printStackTrace();
			 }
			Configuration configuration = new Configuration();
			//  configuration.setInterceptor( new AuditInterceptor() );
			configuration.configure();
			configuration.setProperty("hibernate.connection.url", config.getString("DB_URL"));
			configuration.setProperty("hibernate.connection.username", config.getString("DB_USERNAME"));
			configuration.setProperty("hibernate.connection.password", config.getString("DB_PASSWORD"));
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
			
		    return sessionFactory;
	}
}
