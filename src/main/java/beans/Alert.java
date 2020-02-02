package beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import common.Configuration;

public class Alert {

	private Integer id;
	private Boolean state;
	private Integer level;
	private Timestamp date;
	private String message;
	private Integer objectId;
	//Optional
	private String type_Object;
	private String nickname_Object;
	private Integer id_Apartment;
	private String name_Apartment;
	
	public Alert(ResultSet result) throws SQLException {
		id = result.getInt(1);
		state = result.getBoolean(2);
		level = result.getInt(3);
		date = result.getTimestamp(4);
		message = result.getString(5);
		objectId = result.getInt(6);
		if(result.getMetaData().getColumnCount()>6) {
			//Optional data related to the object
			type_Object = result.getString(7);
			nickname_Object = result.getString(8);
			id_Apartment = result.getInt(9);
			name_Apartment = result.getString(10);
		}
	}
	
	public Alert(int id) {
		this.id=id;
	}

	public boolean isActive() {
		return state;
	}

	public String getTableLine(Boolean disableButton) {
		String line = "<tr>\n";
		line+="<td>" + date + "</td>";
		line+="<td>" + level + "</td>";
		line+="<td>" + message + "</td>";
		line+="<td>" + type_Object + "</td>";
		line+="<td>" + nickname_Object + "</td>";
		if(disableButton) {
			// If asked, we add a link to disable it
			line+="<td><form action='' method='post'><input type='hidden' name='alertId' value='"+id+"'>" +
					"<div class='row'><input type='submit' value='Disable'></div></form></td>";
		}
		else if(state==true && disableButton==false){
			//Case of the dashboard
			line+="<td>" + name_Apartment + "</td>";
			line+="<td><form action='/tv/map' method='POST'><input name='apartmentId' type='hidden' value='" + id_Apartment + "'>" +
					"<button type='submit' class='locate' value='Locate'><i class='gg-pin'></i>Locate</button></form></td>";
		}
		line+="</tr>\n";
		return line;
	}

	public void disableOnDB() throws IOException {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the apartment
			PreparedStatement stmt = connection.prepareStatement("UPDATE Alerts SET State_Alert=0 WHERE ID_Alert=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			this.state=false;
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while updating the state of the alert : " + e.getMessage());
		}
	}

}
