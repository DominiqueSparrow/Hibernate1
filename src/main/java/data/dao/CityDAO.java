package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.City;

public class CityDAO extends DAO {

	public CityDAO() {
		init();
	}

	@SuppressWarnings("unchecked")
	public City findByID(String cityId) {
		Query<City> q = hibernateSession.createQuery(String.format("from City as u where u.id = '%s'", cityId));
		List<City> rl = q.getResultList();
		return rl.get(0);
	}

	
	/**
	 * Metoda, która zraca liste wszystkich obiektow typu City z bazy danych
	 * @return lista obiektów typu User
	 */
	@SuppressWarnings("unchecked")
	public List<City> listAll() {
		Query<City> q = hibernateSession.createQuery("from City ");
		return q.getResultList();
	}

	
	/**
	 * Metoda pozwalająca na wyszukiwanie obiektów w bazie danych po wartości danego pola
	 * @param property - nazwa pola użytego do wyszukania obiektu
	 * @param value - szukana wartość wskazanego pola
	 * @return lista obiektów spełniających warunek, że pole ma odpowiednią wartość
	 */
	@SuppressWarnings("unchecked")
	public List<City> findByProperties(String property, String value) {
		String hql = String.format("from City as u where u.%s = '%s'", property, value);
		Query<City> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
