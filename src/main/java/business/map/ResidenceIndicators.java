package business.map;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Alert;
import beans.Apartment;
import beans.ConnectedObject;
import beans.Resident;
import common.Configuration;

public class ResidenceIndicators {
	
	private ConnectedObject object;
//	public int nbPannes;
//	public int tauxPannes;
	private ArrayList<Integer> listOfAlerts;
	private ArrayList<ConnectedObject> listObjects;
	private int nbAlertTot;
	private int tauxAlert;
	
	public ResidenceIndicators() throws IOException {
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "SELECT * FROM Objects";
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			while(result.next()) {
				listObjects.add(new ConnectedObject(result));
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while getting the Object list : " + e.getMessage());
		}
//		retrieveResidentsList();
//		retrieveObjectsList();
//		retrieveAlerts();
//		retrieveMalfunctions();
	}
	private void retrieveAlertsByType() throws IOException{
		listOfAlerts = new ArrayList<Integer>();
		Number nbAlert = 0;
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "select count(*) from Alerts al, Objects ob where al.ID_Object=ob.ID_Object group  by type_object;";

		try {
			// We retrieve the alerts associated to the apartment
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			while(result.next()) {
				nbAlert = (Number)result;
				listOfAlerts.add((Integer) nbAlert);
			}
			
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving alert by type of objects : " + e.getMessage());
		}
	}
	
	private void retrieveAlerts() throws IOException{
		Number nbAlertTot = 0;
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "select count(*) from Alerts;";

		try {
			// We retrieve the alerts associated to the apartment
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			nbAlertTot = (Number)result;
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving alert : " + e.getMessage());
		}
	}

}