package com.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author onkar_kotasthane
 *
 * SESSION FACTORY MANAGER
 */
public class SessionFactoryManager {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	private SessionFactoryManager() {
	}

	public static SessionFactory getActiveSessionFactory() {
		return sessionFactory;
	}
}
