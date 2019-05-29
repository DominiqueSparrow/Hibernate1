package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries(value = { @NamedQuery(name = "BookById", query = "from Book as b where b.id = :id") })
@Entity
@Table(name = "Ksiazka")

public class Book {

	@Id
	@NotNull
	@Column(name = "id")
	private int id;

	@Size(min = 1, max = 150)
	@Column(name = "tytulKsiazki")
	private String title;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "autorId")
	private Author author;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return String.format("[Książka: \"%s\" ,  autor: %s ]", this.title, this.author);
	}
}
