package data.dao;

import data.Author;

/**
 * Klasa Data Access Object pozwalająca na dostęp do obiektów typu Author z
 * bazy danych
 *
 */
public class AuthorDAO extends GenericDAO<Author>{

	public AuthorDAO() {
		super(Author.class);
	}

}
