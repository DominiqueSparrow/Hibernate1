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

	@SuppressWarnings("unchecked")
	public List<City> listAll() {
		Query<City> q = hibernateSession.createQuery("from City ");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<City> findByProperties(String property, String value) {
		String hql = String.format("from City as u where u.%s = '%s'", property, value);
		Query<City> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}

}
