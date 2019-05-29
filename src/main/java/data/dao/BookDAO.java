package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.Book;

public class BookDAO extends DAO {

	public BookDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public Book findByID(String bookInfoId) {
		Query<Book> q = hibernateSession.createQuery(String.format("from Book as u where u.id = '%s'", bookInfoId));
		List<Book> rl = q.getResultList();
		return rl.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Book> listAll() {
		Query<Book> q = hibernateSession.createQuery("from Book ");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Book> findByProperties(String property, String value) {
		String hql = String.format("from Book as u where u.%s = '%s'", property, value);
		Query<Book> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
