package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
	//private static Session session=null;
	private static SessionFactory factory;
	
	static
	{
		try {
			Configuration cfg=new Configuration().configure();
			factory=cfg.buildSessionFactory();
			//session=factory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Session GetSession()
	{
		return factory.openSession();
	}
	

	public static void closeSession(Session session){
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	public static SessionFactory GetSessionFactory()
	{
		return factory;
	}
}
