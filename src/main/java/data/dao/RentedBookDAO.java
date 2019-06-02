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
		List<RentedBook> rl = (List<RentedBook>) q.getSingleResult();
		return rl.get(0);
	}

	/**
	 * Metoda, która zraca liste wszystkich obiektow typu RentedBook z bazy
	 * danych
	 * 
	 * @return lista obiektów typu User
	 */
	@SuppressWarnings("unchecked")
	public List<RentedBook> listAll() {
		Query<RentedBook> q = hibernateSession.createQuery("from RentedBook ");
		return q.getResultList();
	}

	/**
	 * Metoda pozwalająca na wyszukiwanie obiektów w bazie danych po wartości
	 * danego pola
	 * 
	 * @param property
	 *            - nazwa pola użytego do wyszukania obiektu
	 * @param value
	 *            - szukana wartość wskazanego pola
	 * @return lista obiektów spełniających warunek, że pole ma odpowiednią
	 *         wartość
	 */
	@SuppressWarnings("unchecked")
	public List<RentedBook> findByProperties(String property, String value) {
		String hql = String.format("from RentedBook as b where b.%s = '%s'", property, value);
		Query<RentedBook> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
