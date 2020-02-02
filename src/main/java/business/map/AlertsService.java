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

/*
 * The goal of this class is to get and restitute (in HTML format) the informations linked to
 * the alerts and malfunctions (it includes object, apartment and resident) from the DB
 */

public class AlertsService {

	private Apartment apartment;
	private ArrayList<Resident> listResidents;
	private ArrayList<ConnectedObject> listObjects;
	private ArrayList<Alert> listCurrentAlerts;
	private ArrayList<Malfunction> listCurrentMalfunction;
	private ArrayList<Alert> listOldAlerts;
	private ArrayList<Malfunction> listOldMalfunction;

	// To be called to retrieve all the alerts/Malfunctions
	public AlertsService() throws IOException {
		retrieveAlerts();
		retrieveMalfunctions();
	}

	// To be called in the context of the restitution of alerts/malfunctions linked to a specific apartment
	public AlertsService(Integer idApartment) throws IOException {
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
		PreparedStatement stmt;
		try {
			// We retrieve the alerts associated to the apartment  (if its precized), or all active alerts (if not)
			if(apartment!=null) {
				stmt = connection.prepareStatement("SELECT ID_Alert,State_Alert,Level_Alert,Date_Alert,Message_Alert,Alerts.ID_Object,Objects.Type_Object,Objects.Nickname_Object,Apartments.ID_Apartment,Apartments.Name_Apartment FROM Alerts INNER JOIN Objects ON Alerts.ID_Object=Objects.ID_Object INNER JOIN Apartments ON Objects.ID_Apartment=Apartments.ID_Apartment WHERE Objects.ID_Apartment=? ORDER BY Date_Alert DESC;");
				stmt.setInt(1, apartment.getId());
			}
			else {
				stmt = connection.prepareStatement("SELECT ID_Alert,State_Alert,Level_Alert,Date_Alert,Message_Alert,Alerts.ID_Object,Objects.Type_Object,Objects.Nickname_Object,Apartments.ID_Apartment,Apartments.Name_Apartment FROM Alerts INNER JOIN Objects ON Alerts.ID_Object=Objects.ID_Object  INNER JOIN Apartments ON Objects.ID_Apartment=Apartments.ID_Apartment WHERE State_Alert=1 ORDER BY Date_Alert DESC;");
			}
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
			throw new IOException("An error occured while retrieving the alerts : " + e.getMessage());
		}
	}

	private void retrieveMalfunctions() throws IOException{
		listCurrentMalfunction = new ArrayList<Malfunction>();
		listOldMalfunction = new ArrayList<Malfunction>();
		Connection connection = Configuration.connectionPool.getConnection();
		PreparedStatement stmt;
		try {
			// We retrieve the malfunctions associated to the apartment (if its precized), or all active malfunctions (if not)
			if(apartment!=null) {
				stmt = connection.prepareStatement("SELECT ID_Malfunction,State_Malfunction,Date_Malfunction,Message_Malfunction,Malfunctions.ID_Object,Objects.Type_Object,Objects.Nickname_Object,Apartments.ID_Apartment,Apartments.Name_Apartment FROM Malfunctions INNER JOIN Objects ON Malfunctions.ID_Object=Objects.ID_Object INNER JOIN Apartments ON Objects.ID_Apartment=Apartments.ID_Apartment WHERE Objects.ID_Apartment=? ORDER BY Date_Malfunction DESC;");
				stmt.setInt(1, apartment.getId());
			}
			else {
				stmt = connection.prepareStatement("SELECT ID_Malfunction,State_Malfunction,Date_Malfunction,Message_Malfunction,Malfunctions.ID_Object,Objects.Type_Object,Objects.Nickname_Object,Apartments.ID_Apartment,Apartments.Name_Apartment FROM Malfunctions INNER JOIN Objects ON Malfunctions.ID_Object=Objects.ID_Object INNER JOIN Apartments ON Objects.ID_Apartment=Apartments.ID_Apartment WHERE State_Malfunction=1 ORDER BY Date_Malfunction DESC;");
			}
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
			throw new IOException("An error occured while retrieving the malfunctions : " + e.getMessage());
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

	//DisableButton : do we want to add a disable button on the page (cf : map)
	public String getActiveAlerts(Boolean disableButton) {

		if(listCurrentAlerts.isEmpty() && listCurrentMalfunction.isEmpty())
			return "";

		String tableAlerts = "<h2>Active alerts</h2>\n";

		if(!listCurrentAlerts.isEmpty()) {
			tableAlerts+="<table class='distinguishedAlertTable'><tr><th>Alerts</th></tr></table>\n";
			tableAlerts+="<table class='distinguishedAlertTable'><tr>\n";
			if(disableButton)
				tableAlerts+="<th>Date</th><th>Level</th><th>Message</th><th>Object Type</th><th>Object Nickname</th><th>Disable</th>\n";
			else
				tableAlerts+="<th>Date</th><th>Level</th><th>Message</th><th>Object Type</th><th>Object Nickname</th><th>Apartment</th><th>Locate</th>\n";
			tableAlerts+="</tr>\n";
			for(Alert alert : listCurrentAlerts)
				tableAlerts+=alert.getTableLine(disableButton);
			tableAlerts+="</table>\n";
			if(!listCurrentMalfunction.isEmpty())
				tableAlerts+="<br>\n";
		}

		if(!listCurrentMalfunction.isEmpty()) {
			tableAlerts+="<table class='distinguishedMalfunctionTable'><tr><th>Malfunctions</th></tr></table>\n";
			tableAlerts+="<table class='distinguishedMalfunctionTable'><tr>\n";
			if(disableButton)
				tableAlerts+="<th>Date</th><th>Message</th><th>Object Type</th><th>Object Nickname</th><th>Disable</th>\n";
			else
				tableAlerts+="<th>Date</th><th>Message</th><th>Object Type</th><th>Object Nickname</th><th>Apartment</th><th>Locate</th>\n";
			tableAlerts+="</tr>\n";
			for(Malfunction malfunction : listCurrentMalfunction)
				tableAlerts+=malfunction.getTableLine(disableButton);
			tableAlerts+="</table>\n";
		}

		return tableAlerts;

	}

	public String getRelatedObjects() {

		if(listObjects.isEmpty())
			return "<p>No objects are currently related to this apartment </p>";

		String tableObject ="<table class='distinguishedTable'>\n";
		tableObject+="<tr><th>ID</th><th>Type</th><th>State</th><th>Nickname</th><th>Mac address</th></tr>\n";
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
			tableAlerts+="<th>Date</th><th>Level</th><th>Message</th><th>Object Type</th><th>Object Nickname</th>\n";
			tableAlerts+="</tr>\n";
			for(Alert alert : listOldAlerts)
				tableAlerts+=alert.getTableLine(false);
			tableAlerts+="</table>\n";
			if(!listOldMalfunction.isEmpty())
				tableAlerts+="<br>\n";
		}

		if(!listOldMalfunction.isEmpty()) {
			tableAlerts+="<table class='distinguishedTable'><tr><th>Malfunctions</th></tr></table>\n";
			tableAlerts+="<table class='distinguishedTable'><tr>\n";
			tableAlerts+="<th>Date</th><th>Message</th><th>Object Type</th><th>Object Nickname</th>\n";
			tableAlerts+="</tr>\n";
			for(Malfunction malfunction : listOldMalfunction)
				tableAlerts+=malfunction.getTableLine(false);
			tableAlerts+="</table>\n";
		}

		return tableAlerts;

	}

	public Integer getNumberOfActiveAlerts() {
		return listCurrentAlerts.size() + listCurrentMalfunction.size();
	}

}
