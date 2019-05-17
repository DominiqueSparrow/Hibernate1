package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Column(name = "user")
	private String username;

	@Length(min = 5, max = 50)
	@Column(name = "password")
	private String password;

	@Column(name = "address_id")
	private Integer addressId;

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

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return String.format("User (%s,%s,%s,%s)", userId, username, password, addressId);
	}

}
