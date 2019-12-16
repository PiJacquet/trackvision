package business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beans.ConnectedObjects;
import beans.Employee;
import common.Configuration;

public class ConnectedObjectsList {
	
	private ArrayList<ConnectedObjects> CObjects;
	
	public ConnectedObjectsList() throws IOException {
		CObjects = new ArrayList<ConnectedObjects>();
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "SELECT * FROM Objects";
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			while(result.next()) {
				CObjects.add(new ConnectedObjects(result));
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while getting the Object list : " + e.getMessage());
		}
	}
}
