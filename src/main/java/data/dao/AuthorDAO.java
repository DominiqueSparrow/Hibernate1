package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.Author;

/**
 * Klasa Data Access Object pozwalająca na dostęp do obiektów typu Author z
 * bazy danych
 *
 */
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

	/**
	 * Metoda, która zraca liste wszystkich obiektow typu Author z bazy danych
	 * @return lista obiektów typu User
	 */
	@SuppressWarnings("unchecked")
	public List<Author> listAll() {
		Query<Author> q = hibernateSession.createQuery("from Author ");
		return q.getResultList();
	}

	
	/**
	 * Metoda pozwalająca na wyszukiwanie obiektów w bazie danych po wartości danego pola
	 * @param property - nazwa pola użytego do wyszukania obiektu
	 * @param value - szukana wartość wskazanego pola
	 * @return lista obiektów spełniających warunek, że pole ma odpowiednią wartość
	 */
	@SuppressWarnings("unchecked")
	public List<Author> findByProperties(String property, String value) {
		String hql = String.format("from Author as u where u.%s = '%s'", property, value);
		Query<Author> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
