package data.dao;

import data.User;

/**
 * klasa pozwalajaca na dostep do obiektow typu User przechowywanych w bazie
 * danych
 * 
 * @author wrobe
 *
 */
public class UserDAO extends GenericDAO<User> {

	public UserDAO() {
		super(User.class);
	}

}
