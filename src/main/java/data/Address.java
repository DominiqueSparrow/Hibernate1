package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@NotNull(message = "Name cannot be null")
	@Column(name = "city")
	Integer miastoId;
	
	@NotNull(message = "Name cannot be null")
	@Length(min=3)
	@Column(name = "street")
	String ulica;
	
	@Positive 
	@Column(name = "house_number")
	Integer numerDomu;
	
	@Length(min=5,max=5)
	@Column(name ="postal_code")
	String kodPocztowy;
	
	public String getKodPocztowy() {
		return kodPocztowy;
	}
	public Integer getMiastoId() {
		return miastoId;
	}
	public void setMiasto(Integer miasto) {
		this.miastoId = miasto;
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
		return String.format("Address (%d,%s,%s,%d,%s)", id, miastoId, ulica,numerDomu,kodPocztowy);
	}

}
