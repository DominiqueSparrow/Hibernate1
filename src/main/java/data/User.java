package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Uzytkownik")
public class User {

	@NotNull
	@Id
	@Column(name = "id")
	private int userId;

	@Length(min = 3, max = 50)
	@Column(name = "nazwaUzytkownika")
	private String username;

	@Length(min = 5, max = 50)
	@Column(name = "haslo")
	private String password;

	@Length(min = 3, max = 50)
	@Column(name = "imie")
	private String name;

	@Length(min = 3, max = 50)
	@Column(name = "nazwisko")
	private String surname;

	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "adresId")
	private Address address;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAdres() {
		return address;
	}

	public void setAdres(Address adres) {
		this.address = adres;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("%s %s (%s, %s, %s, mieszka w %s)", name, surname, username, userId, password, address);
	}

}
