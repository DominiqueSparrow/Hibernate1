package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.User;

public class UserDAO extends DAO {

	public UserDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public User findByID(String userId) {
		Query<User> q = hibernateSession.createQuery(String.format("from User as u where u.id = '%s'", userId));
		List<User> rl = q.getResultList();
		return rl.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<User> listAll() {
		Query<User> q = hibernateSession.createQuery("from User ");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> findByProperties(String property, String value) {
		String hql = String.format("from User as u where u.%s = '%s'", property, value);
		Query<User> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
