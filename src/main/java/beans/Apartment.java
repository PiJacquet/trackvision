package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Apartment {
	
	private Integer id;
	private String apartment;
	private Integer level;
	private Integer numberOfAlerts;
	private Integer numberOfMalfunctions;
	
	public Apartment(ResultSet result) throws SQLException {
		id=result.getInt(1);
		apartment=result.getString(2);
		level=result.getInt(3);
		numberOfAlerts=result.getInt(4);
		numberOfMalfunctions=result.getInt(5);
	}
	
	public String toString() {
		if(isInAlert())
			return "<a class='apartment alert' href='map/" + id + "'>" + apartment + " (" + getTotalReports() + ")</a>\n";
		else
			return "<a class='apartment noalert' href='map/" + id + "'>" + apartment + "</a>\n";
	}

	public Integer getTotalReports() {
		return numberOfAlerts+numberOfMalfunctions;
	}
	
	public Boolean isInAlert() {
		return getTotalReports()>0;
	}
}
