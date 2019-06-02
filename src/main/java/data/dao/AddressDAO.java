package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.Address;

/**
 * Klasa Data Access Object pozwalająca na dostęp do obiektów typu Adddress z
 * bazy danych
 *
 */
public class AddressDAO extends DAO {

	public AddressDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public Address findByID(String userId) {
		Query<Address> q = hibernateSession.createQuery(String.format("from Address as u where u.id = '%s'", userId));
		List<Address> rl = q.getResultList();
		return rl.get(0);
	}

	/**
	 * Metoda, która zraca liste wszystkich obiektow typu Address z bazy danych
	 * 
	 * @return lista obiektów typu User
	 */
	@SuppressWarnings("unchecked")
	public List<Address> listAll() {
		Query<Address> q = hibernateSession.createQuery("from Address ");
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
	public List<Address> findByProperties(String property, String value) {
		String hql = String.format("from Address as u where u.%s = '%s'", property, value);
		Query<Address> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
