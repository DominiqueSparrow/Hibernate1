package data.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import app.HibernateUtils;

public class DAO {
	// obiekt do komunikacji z baza danych
	static public Session hibernateSession = null;

	// inicjacja polaczeniaz bazą danych
	static protected void init() {
		if (hibernateSession == null) {
			SessionFactory factory = HibernateUtils.getSessionFactory();
			hibernateSession = factory.getCurrentSession();
			hibernateSession.getTransaction().begin();
		}
	}
	

	
}
