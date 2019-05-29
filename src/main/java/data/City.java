package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Miasto")
public class City {
	
	@Id
	@NotNull
	@Column(name = "id")
	private int id;
	@NotEmpty
	@Size (min=1, max=50)
	@Column(name = "nazwaMiejscowosci")
	private String cityName;
	@NotEmpty
	@Size (min=1, max=50)
	@Column(name = "wojewodztwo")
	private String region;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return String.format("Miasto: %s w wojewodztwie %s", cityName, region);
	}
}
