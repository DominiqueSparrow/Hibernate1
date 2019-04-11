package data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import data.Book;

public class BookDAO extends DAO {

	public BookDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public Book findByID(String bookId) {
		Query<Book> q = hibernateSession.createQuery(String.format("from Book as b where b.id = '%s'", bookId));
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
		String hql = String.format("from Book as b where b.%s = '%s'", property, value);
		Query<Book> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
