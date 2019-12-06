package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Apartment {
	
	private Integer id;
	private String name;
	private Integer level;
	private Integer numberOfAlerts;
	private Integer numberOfMalfunctions;
	
	public Apartment(ResultSet result) throws SQLException {
		id=result.getInt(1);
		name=result.getString(2);
		level=result.getInt(3);
		numberOfAlerts=result.getInt(4);
		numberOfMalfunctions=result.getInt(5);
	}
	
	public Apartment(Integer id, String name, Integer level) {
		this.id=id;
		this.name=name;
		this.level=level;
	}

	public Integer getTotalReports() {
		return numberOfAlerts+numberOfMalfunctions;
	}
	
	public Boolean isInAlert() {
		return getTotalReports()>0;
	}

	public String getLink(Integer locatedApartId) {
		String classLocated = "";
		if(id==locatedApartId)
			classLocated = "blink"; // If this is the apart asked to be located, we add the blink css effect.
		if(isInAlert())
			return "<a class='apartment alert " + classLocated + "' href='map/" + id + "'>" + name + " (" + getTotalReports() + ")</a>\n";
		else
			return "<a class='apartment noalert " + classLocated + "' href='map/" + id + "'>" + name + "</a>\n";
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getLevel() {
		return level;
	}
}
