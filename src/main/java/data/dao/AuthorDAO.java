package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.Author;

public class AuthorDAO extends DAO {

	public AuthorDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public Author findByID(String userId) {
		Query<Author> q = hibernateSession.createQuery(String.format("from Author as u where u.id = '%s'", userId));
		List<Author> rl = q.getResultList();
		return rl.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Author> listAll() {
		Query<Author> q = hibernateSession.createQuery("from Author ");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Author> findByProperties(String property, String value) {
		String hql = String.format("from Author as u where u.%s = '%s'", property, value);
		Query<Author> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
