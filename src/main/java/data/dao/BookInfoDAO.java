package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.BookInfo;

public class BookInfoDAO extends DAO {

	public BookInfoDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public BookInfo findByID(String bookInfoId) {
		Query<BookInfo> q = hibernateSession.createQuery(String.format("from BookInfo as u where u.id = '%s'", bookInfoId));
		List<BookInfo> rl = q.getResultList();
		return rl.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<BookInfo> listAll() {
		Query<BookInfo> q = hibernateSession.createQuery("from BookInfo ");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BookInfo> findByProperties(String property, String value) {
		String hql = String.format("from BookInfo as u where u.%s = '%s'", property, value);
		Query<BookInfo> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
