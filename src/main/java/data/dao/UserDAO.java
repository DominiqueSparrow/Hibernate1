package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.User;

/**
 * klasa pozwalajaca na dostep do obiektow typu User przechowywanych w bazie
 * danych
 * 
 * @author wrobe
 *
 */
public class UserDAO extends DAO {

	public UserDAO() {
		init();
	}

	/**
	 * Metoda ktorej w arumencie przekazujemy Id a ona z bazy wyciaga obiekt
	 * (typu User) z tym Id i go wyrzuca
	 * 
	 * @param userId
	 *            - id użytkownika, którego chcemy pobrać z bazy danych
	 * @return obiekt typu User o odpowiednim id
	 */
	@SuppressWarnings("unchecked")
	public User findByID(String userId) {
		// samo zapytanie HQL->(znajdz usera o dokladnie takim Id z warunkiem
		// where
		// czyli znajdzie tylko te gdzie jest takie ID)
		Query<User> q = hibernateSession.createQuery(String.format("from User as u where u.id = '%s'", userId));
		List<User> rl = (List<User>) q.getSingleResult();
		return rl.get(0);
	}

	/**
	 * Metoda, która zraca liste wszystkich obiektow typu User z bazy danych
	 * 
	 * @return lista obiektów typu User
	 */
	@SuppressWarnings("unchecked")
	public List<User> listAll() {
		Query<User> q = hibernateSession.createQuery("from User ");
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
	public List<User> findByProperties(String property, String value) {
		String hql = String.format("from User as u where u.%s = '%s'", property, value);
		Query<User> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
