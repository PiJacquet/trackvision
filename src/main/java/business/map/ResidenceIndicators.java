package business.map;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import beans.ConnectedObject;
import common.Configuration;

public class ResidenceIndicators {

	private ConnectedObject object;
	//	public int nbPannes;
	//	public int tauxPannes;
	private Map<String,Integer> listOfAlerts;
	private ArrayList<ConnectedObject> listObjects;
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
		retrieveAlertsByType();
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
				type = result.getString(1);
				nbAlert = result.getInt(2);
				listOfAlerts.put(type, nbAlert);
			}  

			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving alert by type of objects : " + e.getMessage());
		}
		 
	}

	private int  retrieveAlerts() throws IOException{ 
		int nbAlertTot = 0;
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "select count(*) from Alerts"; 
		try { 
			// We retrieve the alerts associated to the apartment
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			result.next();
			nbAlertTot = result.getInt(1); 
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving alert : " + e.getMessage());
		}
		return nbAlertTot; 
	}

//	public ArrayList<Integer> computeRate() throws IOException{
//		ArrayList<Integer> rateList = new ArrayList <Integer>();
//		for (String e : listOfAlerts.keySet()) {
//			tauxAlertByType	= ((listOfAlerts.get(e))*100/retrieveAlerts());
//			rateList.add(tauxAlertByType);
//		}
//		return rateList;
//	} 
	
	public String displaAlerts() throws IOException{
		String alerts = " ";
		alerts += retrieveAlerts()+" alertes";
		return alerts;
	} 


	public String alertByTypeTable() throws IOException{
		String str ="<h4>Alerts by type</h4>\n";

		if(listOfAlerts.isEmpty()) {
			return "<p>Votre liste est vide</p>";
		}

		str+="<table class='distinguishedAlertTable'><tr><th>Alerts: "+displaAlerts()+"</th></tr></table>\n";
		str+="<table class='distinguishedAlertTable'><tr>\n";
		str+="<td>Object Type</td><td>Number of Alerts</td><td>Taux d'alertes</td>\n";
		str+="</tr>\n";
		
		
		
			for (String e : listOfAlerts.keySet()) {
				str+="<tr>";
				str+="<th>"+e+"</th>";
				str+="<th>"+listOfAlerts.get(e)+"</th>";
				str+="<th>"+((listOfAlerts.get(e))*100/retrieveAlerts()+"%")+"</th>";
				
				
				}
			
			
			
				
				
				
			
			
		return str;

	}

}