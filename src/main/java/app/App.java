package app;

import java.util.List;
import java.util.Random;

import data.RentedBook;
import data.User;
import data.dao.RentedBookDAO;
import data.dao.UserDAO;

public class App {

	/**
	 * Metoda, która wypisuje na konsolę informację o użytkowniku z zadanym id
	 * 
	 * @param userId
	 *            - id użytkownika, o którym informacje chcemy wyświetlić
	 */
	static public void displayUser(String userId) {
		// wycigamy rzeczy z bazy danych wiec potzebne jest DAO, dlatego tu sa obiekty:
		UserDAO ud = new UserDAO();
		RentedBookDAO bd = new RentedBookDAO();
		
		//juz wyciagniety z bazy uzytkownik
		User u = ud.findByID(userId);
		//wyciagamy wszystkie ksiazki dla których User Id to jest konkterna liczba
		List<RentedBook> books = bd.findByProperties("user.id", userId);
		//i drukujemy 
		System.out.printf("User: %s", u);
		System.out.println("  Books: ");
		for (RentedBook b : books) {
			System.out.printf("    %s", b);
		}

	}

	/**
	 * Metoda, która zwraca losowo wybranego z bazy daych Użytkownika
	 */
	static void displayRandomUser() {
		UserDAO ud = new UserDAO();
		List<User> allUsers = ud.listAll();
		Random r = new Random();
		int userIndex = r.nextInt(allUsers.size());
		displayUser(allUsers.get(userIndex).getUserId() + "");
	}

	/**
	 * Metoda, która wyświetla informacje o wszystkich użytkownikach z bazy
	 * danych
	 * 
	 */
	static void displayAllUsers() {
		UserDAO ud = new UserDAO();
		for (User u : ud.listAll()) {
			displayUser(""+ u.getUserId());
		}
	}

	public static void main(String[] args) {
		displayRandomUser();
		displayAllUsers();

	}
}
