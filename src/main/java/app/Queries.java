package app;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
	/**
	 * Obiekt klasy EntityManager używany w metodach do wyszukiwania obiktów z
	 * BD
	 */
	static EntityManager em = null;

	/**
	 * Medota do zainicjowania obiektu klasy {@link EntityManager} używanej w
	 * pozostałych metodach
	 */
	private static void init() {
		if (em == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("maven1.persistence");
			em = emf.createEntityManager();
		}
	}

	/**
	 * Metoda wykorzystjąca JPQL query bez parametru do wyszukania wszystkich
	 * książek wypożyczonych przez użytkowników z Wrocławia
	 * 
	 * @return lista obiektów typu Book reprezentująca wszystkie książki
	 *         wypożyczone prez użytkowników z Wrocławia
	 */
	@SuppressWarnings("rawtypes")
	private static List<RentedBook> JPQLNoParameterRentedBookFromWroclawCity() {
		init();
		Query query = em.createQuery("from RentedBook as rb where rb.user.address.city.cityName = 'Wrocław' ");
		List results = query.getResultList();
		List<RentedBook> users = new ArrayList<RentedBook>();
		for (Object result : results) {
			users.add((RentedBook) result);
		}
		return users;
	}

	/**
	 * Metoda wykorzystująca zapytani JPQL bez parametrów w celu znalezienia
	 * wszystkic użytkowników, którzy wyporzyczyli więcej niż jedną książkę
	 * 
	 * @return lista obiektów typu User, które odpowiadają użytkownikom, którzy
	 *         pożyczyli więcej niż jedną książkę
	 */
	@SuppressWarnings("rawtypes")
	private static List<Entry<User, Long>> JPQLNoParameterUserMoreThanOneBook() {
		init();
		Query query = em.createQuery("select rb.user, count(rb)  from RentedBook as rb group by rb.user.id having count(rb) > 1 ");
		List results = query.getResultList();
		List<Entry<User, Long>> usersWithBokkCount = new ArrayList<Entry<User, Long>>();
		for (Object result : results) {
			Object[] resultArray = (Object[]) result;
			usersWithBokkCount.add(new AbstractMap.SimpleEntry<User, Long>((User) resultArray[0], (Long) resultArray[1]));
		}
		return usersWithBokkCount;
	}

	/**
	 * Metoda wykorzystująca zapytani JPQL z parametrem do znalezienia
	 * wszystkich użytkowników o danym nazwisko
	 * 
	 * @param surname
	 *            - nazwisko, z którym użytkowników będziemy szukać
	 * @return lista użytkowników o zadanym nazwisku
	 */
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

	/**
	 * Metoda korzystająca z zapytania JPQL z parametrem do wyszukiwania adresu
	 * po naziw ulicy
	 * 
	 * @param streetName
	 *            - nazwa ulicy, z którą chcemy adres
	 * @return lista obiektów Address o danej nazwie ulicy
	 */
	@SuppressWarnings("rawtypes")
	private static List<Address> JPQLNumberedParameterSearchAddress(String streetName) {
		init();
		Query query = em.createQuery("from Address a where a.street = ?1").setParameter(1, streetName);
		List results = query.getResultList();//return
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
		// utworzenie zapytania z typem User
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

	/**
	 * Metoda wykorzystująca NAmedQury - query zdefiniwane w adnotacji w klasie
	 * {@link Book}, w celu znalezienia książki po id
	 * 
	 * @param bookId
	 *            - id książki, której szukamy
	 * @return ksiązka (obiekt typu Book) z zadanym id
	 */
	private static Book namedQueryAnnotationFindBookById(int bookId) {
		init();
		return em.createNamedQuery("BookById", Book.class).setParameter("id", bookId).getSingleResult();
	}

	public static void main(String[] zupa) {
		init();
		final String userSurname = "Nowak";
		final String addressStreetName = "ul. Partyzantów";
		final int bookId = 7;
		final String authorName = "Henryk";
		final String cityName = "Wrocław";
		final String bookTitle = "Pan Tadeusz";

		System.out.println("Listing all books rented by users from wroclaw  ");
		for (RentedBook b : JPQLNoParameterRentedBookFromWroclawCity()) {
			System.out.println("  Book rented by user from wrocław : " + b);
		}

		System.out.println("Listing users who rented more than one book ");
		for (Entry<User, Long> b : JPQLNoParameterUserMoreThanOneBook()) {
			System.out.println("  User " + b.getKey() + " rented " + b.getValue() + " books");
		}

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
