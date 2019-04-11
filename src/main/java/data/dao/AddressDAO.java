package data.dao;

import java.util.List;

import org.hibernate.query.Query;

import data.Address;

public class AddressDAO extends DAO{
	
	public AddressDAO() {
		init();
	}


	@SuppressWarnings("unchecked")
	public Address findByID(String userId) {
		Query<Address> q = hibernateSession.createQuery(String.format("from Address as u where u.id = '%s'",userId));
		List<Address> rl = q.getResultList();
		return rl.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Address> listAll() {
		Query<Address> q = hibernateSession.createQuery("from Address ");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Address> findByProperties(String property, String value){
		String hql = String.format("from Address as u where u.%s = '%s'",property,value);
		Query<Address> q = hibernateSession.createQuery(hql);
		return q.getResultList();
	}
	
	

}
