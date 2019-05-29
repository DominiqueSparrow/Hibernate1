package app;

import java.util.List;
import java.util.Random;

import data.RentedBook;
import data.User;
import data.dao.RentedBookDAO;
import data.dao.UserDAO;

public class App {
	static public void displayUser(String userId) {
		UserDAO ud = new UserDAO();
		RentedBookDAO bd = new RentedBookDAO();
		

		User u = ud.findByID(userId);
		List<RentedBook> books = bd.findByProperties("user.id", userId);
		System.out.printf("User: %s",u);
		System.out.println("  Books: ");
		for (RentedBook b : books) {
			System.out.printf("    %s",b);
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
