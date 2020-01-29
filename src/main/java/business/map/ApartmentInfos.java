package business.map;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Alert;
import beans.Apartment;
import beans.ConnectedObject;
import beans.Malfunction;
import beans.Resident;
import common.Configuration;

public class ApartmentInfos {

	private Apartment apartment;
	private ArrayList<Resident> listResidents;
	private ArrayList<ConnectedObject> listObjects;
	private ArrayList<Alert> listCurrentAlerts;
	private ArrayList<Malfunction> listCurrentMalfunction;
	private ArrayList<Alert> listOldAlerts;
	private ArrayList<Malfunction> listOldMalfunction;

	public ApartmentInfos(Integer idApartment) throws IOException {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the apartment
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Apartments WHERE ID_Apartment=?;");
			stmt.setInt(1, idApartment);
			ResultSet result = stmt.executeQuery();
			if(!result.next())
				throw new Exception("No apartment associated to the id");
			apartment = new Apartment(result.getInt(1),result.getString(2),result.getInt(3));
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving the apartment informations : " + e.getMessage());
		}
		retrieveResidentsList();
		retrieveObjectsList();
		retrieveAlerts();
		retrieveMalfunctions();
	}

	private void retrieveResidentsList() throws IOException {
		listResidents = new ArrayList<Resident>();
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the residents associated to the apartment
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Residents WHERE ID_Apartment=?;");
			stmt.setInt(1, apartment.getId());
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				listResidents.add(new Resident(result));
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving the apartment residents : " + e.getMessage());
		}
	}

	private void retrieveObjectsList() throws IOException{
		listObjects = new ArrayList<ConnectedObject>();
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the residents associated to the apartment
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Objects WHERE ID_Apartment=?;");
			stmt.setInt(1, apartment.getId());
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				listObjects.add(new ConnectedObject(result));
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving the apartment objects : " + e.getMessage());
		}
	}

	private void retrieveAlerts() throws IOException{
		listCurrentAlerts = new ArrayList<Alert>();
		listOldAlerts = new ArrayList<Alert>();
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the alerts associated to the apartment
			PreparedStatement stmt = connection.prepareStatement("SELECT ID_Alert,State_Alert,Level_Alert,Date_Alert,Message_Alert,Alerts.ID_Object FROM Alerts INNER JOIN Objects ON Alerts.ID_Object=Objects.ID_Object WHERE Objects.ID_Apartment=? ORDER BY Date_Alert DESC;");
			stmt.setInt(1, apartment.getId());
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Alert alert = new Alert(result);
				if(alert.isActive())
					listCurrentAlerts.add(alert);
				else
					listOldAlerts.add(alert);
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving the apartment objects : " + e.getMessage());
		}
	}

	private void retrieveMalfunctions() throws IOException{
		listCurrentMalfunction = new ArrayList<Malfunction>();
		listOldMalfunction = new ArrayList<Malfunction>();
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the alerts associated to the apartment
			PreparedStatement stmt = connection.prepareStatement("SELECT ID_Malfunction,State_Malfunction,Date_Malfunction,Message_Malfunction,Malfunctions.ID_Object FROM Malfunctions INNER JOIN Objects ON Malfunctions.ID_Object=Objects.ID_Object WHERE Objects.ID_Apartment=? ORDER BY Date_Malfunction DESC;");
			stmt.setInt(1, apartment.getId());
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Malfunction malfunction = new Malfunction(result);
				if(malfunction.isActive())
					listCurrentMalfunction.add(malfunction);
				else
					listOldMalfunction.add(malfunction);
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while retrieving the apartment objects : " + e.getMessage());
		}
	}

	public Apartment getApartment() {
		return apartment;
	}

	public String getResidentsInfo() {
		if(listResidents.isEmpty()) {
			return "No resident currently lives in this apartment";
		}
		String list = "<ul>\n";
		for(Resident resident : listResidents)
			list+= "<li>" + resident + "</li>\n";
		list+="</ul>\n";
		return list;
	}

	public String getActiveAlerts() {

		if(listCurrentAlerts.isEmpty() && listCurrentMalfunction.isEmpty())
			return "";

		String tableAlerts = "<h2>Active alerts</h2>\n";

		if(!listCurrentAlerts.isEmpty()) {
			tableAlerts+="<table class='distinguishedAlertTable'><tr><th>Alerts</th></tr></table>\n";
			tableAlerts+="<table class='distinguishedAlertTable'><tr>\n";
			tableAlerts+="<th>Date</th><th>Level</th><th>Message</th><th>Object</th><th>Disable</th>\n";
			tableAlerts+="</tr>\n";
			for(Alert alert : listCurrentAlerts)
				tableAlerts+=alert.getTableLine();
			tableAlerts+="</table>\n";
			if(!listCurrentMalfunction.isEmpty())
				tableAlerts+="<br>\n";
		}

		if(!listCurrentMalfunction.isEmpty()) {
			tableAlerts+="<table class='distinguishedMalfunctionTable'><tr><th>Malfunctions</th></tr></table>\n";
			tableAlerts+="<table class='distinguishedMalfunctionTable'><tr>\n";
			tableAlerts+="<th>Date</th><th>Message</th><th>Object</th><th>Disable</th>\n";
			tableAlerts+="</tr>\n";
			for(Malfunction malfunction : listCurrentMalfunction)
				tableAlerts+=malfunction.getTableLine();
			tableAlerts+="</table>\n";
		}

		return tableAlerts;

	}

	public String getRelatedObjects() {

		if(listObjects.isEmpty())
			return "<p>No objects are currently related to this apartment </p>";
		
		String tableObject ="<table class='distinguishedTable'>\n";
		tableObject+="<tr><th>ID</th><th>Type</th><th>State</th><th>Mac address</th></tr>\n";
		for(ConnectedObject object : listObjects)
			tableObject+=object.getTableLine();
		tableObject+="</table>\n";
		
		return tableObject;
	}

	public String getOldAlerts() {

		if(listOldAlerts.isEmpty() && listOldMalfunction.isEmpty())
			return "";

		String tableAlerts = "<h2>Old alerts</h2>\n";

		if(!listOldAlerts.isEmpty()) {
			tableAlerts+="<table class='distinguishedTable'><tr><th>Alerts</th></tr></table>\n";
			tableAlerts+="<table class='distinguishedTable'><tr>\n";
			tableAlerts+="<th>Date</th><th>Level</th><th>Message</th><th>Object</th>\n";
			tableAlerts+="</tr>\n";
			for(Alert alert : listOldAlerts)
				tableAlerts+=alert.getTableLine();
			tableAlerts+="</table>\n";
			if(!listOldMalfunction.isEmpty())
				tableAlerts+="<br>\n";
		}

		if(!listOldMalfunction.isEmpty()) {
			tableAlerts+="<table class='distinguishedTable'><tr><th>Malfunctions</th></tr></table>\n";
			tableAlerts+="<table class='distinguishedTable'><tr>\n";
			tableAlerts+="<th>Date</th><th>Message</th><th>Object</th>\n";
			tableAlerts+="</tr>\n";
			for(Malfunction malfunction : listOldMalfunction)
				tableAlerts+=malfunction.getTableLine();
			tableAlerts+="</table>\n";
		}

		return tableAlerts;

	}

}
