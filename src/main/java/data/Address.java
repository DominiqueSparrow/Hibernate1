package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Adres")
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	Integer id;
	
	@Column(name = "city")
	String miasto;
	
	@Column(name = "street")
	String ulica;
	
	@Column(name = "house_number")
	Integer numerDomu;
	
	@Column(name ="postal_code")
	String kodPocztowy;
	
	public String getKodPocztowy() {
		return kodPocztowy;
	}
	public String getMiasto() {
		return miasto;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public Integer getNumerDomu() {
		return numerDomu;
	}
	public void setNumerDomu(Integer numerDomu) {
		this.numerDomu = numerDomu;
	}
	
	@Override
	public String toString() {
		return String.format("Address (%d,%s,%s,%d,%s)", id, miasto, ulica,numerDomu,kodPocztowy);
	}

}
