package business.map;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import beans.Apartment;
import common.Configuration;

public class Map {

	private HashMap<Integer,ArrayList<Apartment>> map;
	private Integer locatedApartId = -1;

	public Map() throws IOException {
		map = new HashMap<Integer, ArrayList<Apartment>>();
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			Statement statement = connection.createStatement();
			// We retrieve the list of Apartments with the alerts associated
			ResultSet result = statement.executeQuery
					("SELECT Apartments.ID_Apartment, Apartments.Name_Apartment, Apartments.ID_Level, sum(case when Alerts.State_Alert = 1 then 1 else 0 end) as Alerts, sum(case when Malfunctions.State_Malfunction= 1 then 1 else 0 end) as Malfunctions " + 
							"FROM Apartments LEFT JOIN Objects ON Objects.ID_Apartment=Apartments.ID_Apartment " + 
							"LEFT JOIN Alerts ON Objects.ID_Object=Alerts.ID_Object " + 
							"LEFT JOIN Malfunctions ON Objects.ID_Object=Malfunctions.ID_Object " + 
							"GROUP BY Apartments.ID_Apartment, Apartments.Name_Apartment, Apartments.ID_Level " +
							"ORDER BY Apartments.ID_LEVEL,Apartments.ID_Apartment");
			Integer level;
			while(result.next()) {
				level = result.getInt(3);
				if(!map.containsKey(level))
					map.put(level, new ArrayList<Apartment>());
				map.get(level).add(new Apartment(result));
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while constructing the map : " + e.getMessage());
		}
	}

	public Map(HashMap<Integer, ArrayList<Apartment>> map) {
		this.map = map;
	}

	public Map(Integer locatedApartId) throws IOException {
		this();
		this.locatedApartId = locatedApartId;
	}

	public String toString() {
		String result = "";
		final Integer maxnumberLine = 5;
		for (Integer level : map.keySet()) {
			result+="<div class='level'>" + "\n";
			result+="<h4>Level " + level + "</h4>" + "\n";
			result+="<div>" + "\n";
			Integer numberLine = 0;
			for(Apartment apart : map.get(level)) {
				result+= apart.getLink(locatedApartId);
				if(++numberLine==maxnumberLine) { //number maximum on the same line
					result+="</div>\n<div>\n";
					numberLine=0;
				}
			}
			result+="</div>" + "\n";
			result+="</div>" + "\n";
		}
		return result;
	}

}
