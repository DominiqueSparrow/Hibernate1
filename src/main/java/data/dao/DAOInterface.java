package data.dao;

import java.util.List;

/**
 * Iterface z metodami DAO, ktorych będziemy używac
 * 
 * @param <T>
 *            tyb do którego chcemy mieć dostęp
 */
public interface DAOInterface<T> {
	T findByID(String s);

	List<T> findByProperties(String property, String value);

	List<T> listAll();

}
