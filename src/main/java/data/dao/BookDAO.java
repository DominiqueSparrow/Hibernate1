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

	/**
	 * Metoda, która zraca liste wszystkich obiektow typu Book z bazy danych
	 * @return lista obiektów typu User
	 */
	@SuppressWarnings("unchecked")
	public List<Book> listAll() {
		Query<Book> q = hibernateSession.createQuery("from Book ");
		return q.getResultList();
	}

	
	/**
	 * Metoda pozwalająca na wyszukiwanie obiektów w bazie danych po wartości danego pola
	 * @param property - nazwa pola użytego do wyszukania obiektu
	 * @param value - szukana wartość wskazanego pola
	 * @return lista obiektów spełniających warunek, że pole ma odpowiednią wartość
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findByProperties(String property, String value) {
		String hql = String.format("from Book as u where u.%s = '%s'", property, value);
		Query<Book> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
