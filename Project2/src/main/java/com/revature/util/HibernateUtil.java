package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	static Configuration configuration = new Configuration().configure();
	static StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
	applySettings(configuration.getProperties());
	
	
private static SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
	
	public static Session getSession(){
		
		return sessionFactory.openSession();
		
}

}
