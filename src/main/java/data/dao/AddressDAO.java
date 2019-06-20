package data.dao;

import data.Address;

/**
 * Klasa Data Access Object pozwalająca na dostęp do obiektów typu Adddress z
 * bazy danych
 *
 */
public class AddressDAO extends GenericDAO<Address>{

	public AddressDAO() {
		super(Address.class);
	}

}
