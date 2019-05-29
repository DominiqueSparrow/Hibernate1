package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.RentedBook;

public class RentedBookDAO extends DAO {

	public RentedBookDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public RentedBook findByID(String bookId) {
		Query<RentedBook> q = hibernateSession.createQuery(String.format("from RentedBook as b where b.id = '%s'", bookId));
		List<RentedBook> rl = q.getResultList();
		return rl.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<RentedBook> listAll() {
		Query<RentedBook> q = hibernateSession.createQuery("from RentedBook ");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<RentedBook> findByProperties(String property, String value) {
		String hql = String.format("from RentedBook as b where b.%s = '%s'", property, value);
		Query<RentedBook> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<RentedBook> findByUserId(int userId){
		Query<RentedBook> q = hibernateSession.createQuery("from Book");
		init();
		return q.getResultList();
	}

}
