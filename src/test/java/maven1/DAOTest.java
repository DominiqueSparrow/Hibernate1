package maven1;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;

import data.Address;
import data.Author;
import data.RentedBook;
import data.Book;
import data.City;
import data.User;
import data.dao.AddressDAO;
import data.dao.AuthorDAO;
import data.dao.RentedBookDAO;
import data.dao.BookDAO;
import data.dao.CityDAO;
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
		Assert.assertEquals(20, allUsers.size());
		for (User u : allUsers) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		for (User u : ud.findByProperties("username", "Rysiek")) {
			Assert.assertEquals(16, u.getUserId());
			System.out.println(u);
		}
	}

	@Test
	public void testAuthorDao() {

		AuthorDAO ud = new AuthorDAO();
		Author adas = ud.findByID("2");
		Assert.assertEquals("Mickiewicz", adas.getAuthorSurname());
		System.out.println("AuthorDAO.findById(2) : " + adas);
		System.out.println("ListAll");
		List<Author> allUsers = ud.listAll();
		Assert.assertEquals(10, allUsers.size());
		for (Author u : allUsers) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		for (Author u : ud.findByProperties("authorName", "Adam")) {
			Assert.assertEquals(2, u.getId());
			System.out.println(u);
		}
	}

	@Test
	public void testCityDao() {
		CityDAO ud = new CityDAO();
		City wroclaw = ud.findByID("1");
		Assert.assertEquals("DS", wroclaw.getRegion());
		System.out.println("CityDAO.findById(1) : " + wroclaw);
		System.out.println("ListAll");
		List<City> allCities = ud.listAll();
		Assert.assertEquals(5, allCities.size());
		for (City u : allCities) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		List<City> citiesFromDS = ud.findByProperties("region", "DS");
		Assert.assertEquals(5, citiesFromDS.size());
	}

	@Test
	public void testRentedBookDao() {
		RentedBookDAO bd = new RentedBookDAO();
		System.out.println("BookDAO.findById(2) : " + bd.findByID("2"));
		System.out.println("ListAll");
		for (RentedBook u : bd.listAll()) {
			System.out.println(u);
		}
	}

	@Test
	public void testBookDao() {
		BookDAO bd = new BookDAO();
		Book panTadeusz = bd.findByID("2");
		Assert.assertEquals(panTadeusz.getAuthor().getId(), 2);
		System.out.println("BookInfoDAO.findById(2) : " + panTadeusz);

		System.out.println("ListAll");
		for (Book u : bd.listAll()) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		for (Book u : bd.findByProperties("author.authorName", "Adam")) {
			System.out.println(u);
		}
	}

	@Test
	public void testAddressDao() {
		AddressDAO ad = new AddressDAO();
		System.out.println("AddressDAO.findById(2) : " + ad.findByID("2"));
		System.out.println("ListAll");
		for (Address u : ad.listAll()) {
			System.out.println(u);
		}
		System.out.println("findByProperty");
		for (Address u : ad.findByProperties("numerDomu", "12")) {
			System.out.println(u);
		}

	}

	@Test
	public void testBeanValidation() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		AddressDAO ad = new AddressDAO();
		for (Address a : ad.listAll()) {
			Set<ConstraintViolation<Address>> result = validator.validate(a);
			Assert.assertEquals(0, result.size());
		}

	}

	@Test
	public void testBeanValidation2() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		UserDAO ad = new UserDAO();
		for (User a : ad.listAll()) {
			Set<ConstraintViolation<User>> result = validator.validate(a);
			Assert.assertEquals(0, result.size());
		}

	}

	@Test
	public void testBeanValidationAuthor() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		AuthorDAO ad = new AuthorDAO();
		for (Author a : ad.listAll()) {
			Set<ConstraintViolation<Author>> result = validator.validate(a);
			Assert.assertEquals(0, result.size());
		}

	}

	@Test
	public void testBeanValidationBook() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		RentedBookDAO ad = new RentedBookDAO();
		for (RentedBook a : ad.listAll()) {
			Set<ConstraintViolation<RentedBook>> result = validator.validate(a);
			Assert.assertEquals(0, result.size());
		}

	}

	@Test
	public void testBeanValidationBookInfo() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		BookDAO ad = new BookDAO();
		for (Book a : ad.listAll()) {
			Set<ConstraintViolation<Book>> result = validator.validate(a);
			Assert.assertEquals(0, result.size());
		}

	}

	@Test
	public void testBeanValidationCity() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		CityDAO ad = new CityDAO();
		for (City a : ad.listAll()) {
			Set<ConstraintViolation<City>> result = validator.validate(a);
			Assert.assertEquals(0, result.size());
		}
	}
	
	@Test
	public void testBeanValidationUser() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		UserDAO ad = new UserDAO();
		for (User a : ad.listAll()) {
			Set<ConstraintViolation<User>> result = validator.validate(a);
			Assert.assertEquals(0, result.size());
		}
	}
	
	@Test
	public void testUserAddresWhereClause(){
		UserDAO ud = new UserDAO();
		List<User> allUsers = ud.listAll();
		for (User u : allUsers) {
			System.out.println(u);
		}
	}
	
	@Test
	public void testBookByUserIdFilter(){
		RentedBookDAO bd = new RentedBookDAO();
		List<RentedBook> books = bd.findByUserId(3);
		for(RentedBook book : books){
			System.out.println(book);
	}
	}
}
