package beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import common.Configuration;

public class Malfunction {

	
	private Integer id;
	private Boolean state;
	private Timestamp date;
	private String message;
	private Integer objectId;
	
	public Malfunction(ResultSet result) throws SQLException {
		id = result.getInt(1);
		state = result.getBoolean(2);
		date = result.getTimestamp(3);
		message = result.getString(4);
		objectId = result.getInt(5);
	}
	
	public Malfunction(int id) {
		this.id=id; 
	}

	public boolean isActive() {
		return state;
	}
	
	public String getTableLine() {
		String line = "<tr>\n";
		line+="<td>" + date + "</td>";
		line+="<td>" + message + "</td>";
		line+="<td>" + objectId + "</td>";
		if(state) {
			// If the malfunction is active, we add a link to disable it
			line+="<td><form action='' method='post'><input type='hidden' name='malfunctionId' value='"+id+"'>" +
					"<div class='row'><input type='submit' value='Disable'></div></form></td>";
		}
		line+="</tr>\n";
		return line;
	}
	
	public void disableOnDB() throws IOException {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the apartment
			PreparedStatement stmt = connection.prepareStatement("UPDATE Malfunctions SET State_Malfunction=0 WHERE ID_Malfunction=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			this.state=false;
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while updating the state of the malfunction : " + e.getMessage());
		}
	}
}
