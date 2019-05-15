package data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Autor")
public class Author {

	@NotNull
	@Id
	@Column(name = "idAutor")
	private int id;
	@Length(min = 1, max = 50)
	@Column(name = "ImieAutora")
	private String authorName;
	@Length(min = 1, max = 50)
	@Column(name = "NazwiskoAutora")
	private String authorSurname;
	@Past
	@Column(name = "DataUrodzenia")
	private Date dateOfBirth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorSurname() {
		return authorSurname;
	}

	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}

	public Date getDateFrom() {
		return dateOfBirth;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateOfBirth = dateFrom;
	}

	@Override
	public String toString() {
		return String.format("Autor: %s %s urodzony w %s", authorName, authorSurname, dateOfBirth);
	}

}
