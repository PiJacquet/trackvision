package business.map;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beans.Alert;
import beans.Apartment;
import beans.ConnectedObject;
import beans.Resident;
import common.Configuration;

public class ResidenceIndicators {

	private ConnectedObject object;
	//	public int nbPannes;
	//	public int tauxPannes;
	private Map<String,Integer> listOfAlerts;
	private ArrayList<ConnectedObject> listObjects;
	private int nbAlertTot;
	private int tauxAlertByType;

	public ResidenceIndicators() throws IOException {
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "SELECT * FROM Objects";
		listObjects = new ArrayList<ConnectedObject>();
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
		listOfAlerts = new HashMap<String,Integer>();
		int nbAlert = 0;
		String type;
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "select type_object, count(*) from Alerts al, Objects ob where al.ID_Object=ob.ID_Object group  by type_object;";

		try {
			// We retrieve the alerts associated to the apartment
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			while(result.next()) {
				nbAlert = result.getInt(2);
				type = result.getString(1);
				listOfAlerts.put(type,(Integer) nbAlert);
			}

			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving alert by type of objects : " + e.getMessage());
		}
	}

	private void retrieveAlerts() throws IOException{
		nbAlertTot = 0;
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "select count(*) from Alerts;";

		try {
			// We retrieve the alerts associated to the apartment
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			nbAlertTot = result.getInt(1);
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving alert : " + e.getMessage());
		}
	}

	public ArrayList<Integer> computeRate() throws IOException{
		tauxAlertByType = 0;
		ArrayList<Integer> rateList = new ArrayList <Integer>();
		for (String e : listOfAlerts.keySet()) {
			tauxAlertByType	= (listOfAlerts.get(e)/nbAlertTot) * 100;
			rateList.add(tauxAlertByType);
		}
		return rateList;
	} 


	public String alertByTypeTable() {
		String str ="<h4>Alerts by type</h4>\n";

		if(listOfAlerts.isEmpty()) {
			return "";
		}else {
			str+="<table class='distinguishedAlertTable'><tr><th>Alerts: </th>"+nbAlertTot+"</tr></table>\n";
			str+="<table class='distinguishedAlertTable'><tr>\n";
			str+="<th>Object Type</th><th>Number of Alerts</th><th>Message</th>\n";
			str+="</tr>\n";

		}
		return str;

	}

}