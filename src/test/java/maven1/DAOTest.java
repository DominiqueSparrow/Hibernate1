package maven1;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import data.Address;
import data.Book;
import data.User;
import data.dao.AddressDAO;
import data.dao.BookDAO;
import data.dao.UserDAO;


public class DAOTest {
	@Test
	public void testUserDao() {

		UserDAO ud = new UserDAO();
		User seba = ud.findByID("2");
		Assert.assertEquals("sebastian", seba.getUsername());
		System.out.println("UserDAO.findById(2) : " + seba);
		System.out.println("ListAll");
		List<User> allUsers = ud.listAll();
		Assert.assertEquals( 20,allUsers.size());
		for(User u : allUsers) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		for(User u : ud.findByProperties("username", "Rysiek")) {
			Assert.assertEquals(16, u.getUserId());
			System.out.println(u);
		}
	}
	
	@Test
	public void testBookDao() {
		BookDAO bd = new BookDAO();
		System.out.println("BookDAO.findById(2) : " + bd.findByID("2"));
		System.out.println("ListAll");
		for(Book u : bd.listAll()) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		for(Book u : bd.findByProperties("userId", "3")) {
			System.out.println(u);
		}
	}
	
	@Test
	public void testAddressDao() {
		AddressDAO ad = new AddressDAO();
		System.out.println("AddressDAO.findById(2) : " + ad.findByID("2"));
		System.out.println("ListAll");
		for(Address u : ad.listAll()) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		for(Address u : ad.findByProperties("numerDomu", "12")) {
			System.out.println(u);
		}
	}

}
