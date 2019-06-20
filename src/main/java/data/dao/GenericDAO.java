package data.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Generyczna klasa implementująca zapytania do bazy danych
 *
 * @param <T>
 *            typ, do którego chcemy mieć dostęp
 */
public class GenericDAO<T> implements DAOInterface<T> {

	Class<T> clazz;

	static EntityManager em = null;

	protected GenericDAO(Class<T> clazz) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("maven1.persistence");
		em = emf.createEntityManager();
		this.clazz = clazz;
	}

	/**
	 * Metoda, która zraca obiekt typu T wedle jego id
	 * 
	 * @return obiekt typu T o zadanym id
	 */

	public T findByID(String objectId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root = query.from(clazz);
		Path<Object> idPath = root.get("id");
		Predicate predicate = cb.equal(idPath, objectId);
		query.select(root).where(predicate);
		return em.createQuery(query).getSingleResult();
	}

	/**
	 * Metoda, która zraca liste wszystkich obiektow typu T z bazy danych
	 * 
	 * @return lista obiektów typu T
	 */
	public List<T> listAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root = query.from(clazz);
		query.select(root);
		return em.createQuery(query).getResultList();
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
	public List<T> findByProperties(String property, String value) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(clazz);
		Root<T> root = query.from(clazz);
		/*
		 * jeśli property zawiera kropki - czyli jest to parametr zagnieżdzony,
		 * dzielmy łancuch na kilka stringów oddzielonych kropkami i w pętli
		 * tworzymy obiekt Path, ktory odpowiada zagnieżdzonemu parametrowi
		 */
		String[] properties = property.split("\\.");
		Path<Object> propertyPath = root.get(properties[0]);
		for (int i = 1; i < properties.length; i++) {
			propertyPath = propertyPath.get(properties[i]);
		}
		Predicate predicate = cb.equal(propertyPath, value);
		query.select(root).where(predicate);
		return em.createQuery(query).getResultList();
	}

}
