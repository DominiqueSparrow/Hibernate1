package data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;


@Entity
@Table(name = "WypozyczoneKsiazki")
public class RentedBook {

	@Id
	@NotNull
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "uzytkownikId")
	private User user;

	@PastOrPresent
	@Column(name = "dataOd")
	private Date dateFrom;

	@PastOrPresent
	@Column(name = "dataDo")
	private Date dateTo;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "ksiazkaId")
	private Book book;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return String.format("User: %s rented book %s from %s to %s", user, book, dateFrom, dateTo);
	}

}
