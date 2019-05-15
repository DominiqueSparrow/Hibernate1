package data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
@Table(name = "WypozyczoneKsiazki")
public class Book {

	@Id
	@NotNull
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "userid")
	private int userId;
	
	@PastOrPresent
	@Column(name = "date_from")
	private Date dateFrom;
	
	@PastOrPresent
	@Column(name = "date_to")
	private Date dateTo;
	
	@NotEmpty
	@Size (min=1, max=150)
	@Column(name = "title")
	private String title;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return String.format("Book (%s,%s,%s,%s)", id,userId,dateFrom,dateTo);
	}

}
