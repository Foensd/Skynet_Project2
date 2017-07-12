package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
<<<<<<< HEAD
	static Configuration configuration = new Configuration().configure();
	static StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
	applySettings(configuration.getProperties());
	
	
private static SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
=======
@SuppressWarnings("deprecation")
private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
>>>>>>> e241552fbb52e9314aa13b5e63b4e44f50cfbfbf
	
	public static Session getSession(){
		
		return sessionFactory.openSession();
		
}

}
