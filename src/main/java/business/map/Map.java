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
	
	public Map() throws IOException {
		map = new HashMap<Integer, ArrayList<Apartment>>();
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Apartments");
			Integer level;
			while(result.next()) {
				level = result.getInt(2);
				if(!map.containsKey(level))
					map.put(level, new ArrayList<Apartment>());
				map.get(level).add(new Apartment());
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
	
	public String toString() {
		return null;
	}

}
