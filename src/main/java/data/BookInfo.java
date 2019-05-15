package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Ksiazka")

public class BookInfo {
	
	@Id
	@NotNull
	@Column(name = "idKsiazka")
	private int id;

	@Size(min=1, max=150)
	@Column(name = "TytulKsiazki")
	private String title;
	@NotNull
	@Column(name = "AutorId")
	private Integer authorId;

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

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	@Override
	public String toString() {
		return String.format("Książka: \"%s\" , numer autora %d ", this.title, this.authorId);
	}
}
