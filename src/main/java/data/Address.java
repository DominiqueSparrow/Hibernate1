package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Adres")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "miastoId")
	City city;

	@NotNull(message = "Name cannot be null")
	@Length(min = 3)
	@Column(name = "ulica")
	String street;

	@Positive
	@Column(name = "numerDomu")
	Integer homeNumber;

	@Length(min = 5, max = 5)
	@Column(name = "kodPocztowy")
	String postalCode;

	public String getKodPocztowy() {
		return postalCode;
	}

	public String getUlica() {
		return street;
	}

	public void setUlica(String ulica) {
		this.street = ulica;
	}

	public Integer getNumerDomu() {
		return homeNumber;
	}

	public void setNumerDomu(Integer numerDomu) {
		this.homeNumber = numerDomu;
	}

	public City getMiasto() {
		return city;
	}

	public void setMiasto(City miasto) {
		this.city = miasto;
	}

	@Override
	public String toString() {
		return String.format("Adres: %d, %s, %s, %d, %s", id, city, street, homeNumber, postalCode);
	}

}
