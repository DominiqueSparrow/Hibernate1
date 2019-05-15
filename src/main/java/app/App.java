package app;

import java.util.List;
import java.util.Random;

import data.Address;
import data.Book;
import data.User;
import data.dao.AddressDAO;
import data.dao.BookDAO;
import data.dao.UserDAO;

public class App {
	static public void displayUser(String userId) {
		UserDAO ud = new UserDAO();
		AddressDAO ad = new AddressDAO();
		BookDAO bd = new BookDAO();
		

		User u = ud.findByID(userId);
		Address a = ad.findByID(u.getAddressId().toString());
		List<Book> books = bd.findByProperties("userId", userId);
		System.out.printf("User: id: %d, name: %s, password: %s \n",u.getUserId(), u.getUsername(), u.getPassword());
		System.out.printf("  Address: street: %s, house number: %d, city: %s, postal code: %s\n", a.getUlica(), a.getNumerDomu(),
				a.getMiastoId(), a.getKodPocztowy());
		System.out.println("  Books: ");
		for (Book b : books) {
			System.out.printf("    Book: id: %d, title: %s, rented from: %s, rented till: %s\n",b.getId(),b.getTitle(),b.getDateFrom(),b.getDateTo());
		}

	}

	static void displayRandomUser() {
		UserDAO ud = new UserDAO();
		List<User> allUsers = ud.listAll();
		Random r = new Random();
		int userIndex = r.nextInt(allUsers.size());
		displayUser(allUsers.get(userIndex).getUserId() + "");
	}
	
	static void displayAllUsers() {
		UserDAO ud = new UserDAO();
		for (User u : ud.listAll()) {
			displayUser(""+u.getUserId());
		}
	}
	public static void main(String[] args) {
		displayRandomUser();
		displayAllUsers();
		
	}
}
