package app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import data.Address;
import data.Book;
import data.RentedBook;
import data.User;

public class Queries {
	static EntityManager em = null;

	private static void init() {
		if (em == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("maven1.persistence");
			em = emf.createEntityManager();

		}
	}

	@SuppressWarnings("rawtypes")
	private static List<User> JPQLNamedParameterUserByName(String surname) {
		init();
		Query query = em.createQuery("from User u where u.surname = :surname_param").setParameter("surname_param", surname);
		List results = query.getResultList();
		List<User> users = new ArrayList<User>();
		for (Object result : results) {
			users.add((User) result);
		}
		return users;
	}

	@SuppressWarnings("rawtypes")
	private static List<Address> JPQLNumberedParameterSearchAddress(String streetName) {
		init();
		Query query = em.createQuery("from Address a where a.street = ?1").setParameter(1, streetName);
		List results = query.getResultList();
		List<Address> addresses = new ArrayList<Address>();
		for (Object result : results) {
			addresses.add((Address) result);
		}
		return addresses;
	}

	private static List<Book> criteriaBookByAuthorName(String authorName) {
		init();
		// utworzenie obiektu CriteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// utworzenie zapytania z typem Book
		CriteriaQuery<Book> query = cb.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		// zbudowanie ścieżki do wartosci użytej w warunku
		Path<Object> authorNameValue = root.get("author").get("authorName");
		// budowa warunku selekcji
		Predicate predicate = cb.equal(authorNameValue, authorName);
		// zdefiniewenie kwerendy
		query.select(root).where(predicate);
		// wykonanie kwerendy
		return em.createQuery(query).getResultList();
	}

	private static List<User> criteriaUserByCityName(String cityName) {
		init();
		// utworzenie obiektu CriteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// utworzenie zapytania z typem Book
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		// zbudowanie ścieżki do wartosci użytej w warunku
		Path<Object> cityNamePath = root.get("address").get("city").get("cityName");
		// budowa warunku selekcji
		Predicate predicate = cb.equal(cityNamePath, cityName);
		// zdefiniewenie kwerendy
		query.select(root).where(predicate);
		// wykonanie kwerendy
		return em.createQuery(query).getResultList();
	}

	private static List<RentedBook> criteriaRentedBookbyTitle(String bookTitle) {
		init();
		// utworzenie obiektu CriteriaBuilder
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// utworzenie zapytania z typem Book
		CriteriaQuery<RentedBook> query = cb.createQuery(RentedBook.class);
		Root<RentedBook> root = query.from(RentedBook.class);
		// zbudowanie ścieżki do wartosci użytej w warunku
		Path<Object> bookTitlePath = root.get("book").get("title");
		// budowa warunku selekcji
		Predicate predicate = cb.equal(bookTitlePath, bookTitle);
		// zdefiniewenie kwerendy
		query.select(root).where(predicate);
		// wykonanie kwerendy
		return em.createQuery(query).getResultList();
	}

	private static Book namedQueryAnnotationFindBookById(int bookId) {
		init();
		return em.createNamedQuery("BookById", Book.class).setParameter("id", bookId).getSingleResult();
	}

	public static void main(String[] dupa) {
		init();
		final String userSurname = "Nowak";
		final String addressStreetName = "ul. Partyzantów";
		final int bookId = 7;
		final String authorName = "Henryk";
		final String cityName = "Wrocław";
		final String bookTitle = "Pan Tadeusz";
		System.out.println("Listing all users with name: " + userSurname);
		for (User u : JPQLNamedParameterUserByName(userSurname)) {
			System.out.println("  User with surname " + userSurname + ": " + u);
		}
		System.out.println("Listing all addresses with street " + addressStreetName);
		for (Address a : JPQLNumberedParameterSearchAddress(addressStreetName)) {
			System.out.println("  Address with street " + addressStreetName + ": " + a);
		}
		System.out.println("Finding book by id: " + bookId);
		System.out.println("  " + namedQueryAnnotationFindBookById(bookId));
		System.out.println("Listing all books by " + authorName);
		for (Book b : criteriaBookByAuthorName(authorName)) {
			System.out.println("  Book by " + authorName + " : " + b);
		}

		System.out.println("Lising all users from city: " + cityName);
		for (User u : criteriaUserByCityName(cityName)) {
			System.out.println("  User from city " + cityName + " : " + u);
		}
		System.out.println("Lising all times book " + bookTitle + " was rented");
		for (RentedBook rb : criteriaRentedBookbyTitle(bookTitle)) {
			System.out.println("  " + rb);
		}

	}

}
