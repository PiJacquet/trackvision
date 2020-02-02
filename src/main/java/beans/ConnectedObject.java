package beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Configuration;

public class ConnectedObject {
	
	private Integer id;
	private String type;
	private Boolean state;
	private Integer apartmentId;
	private String macAddress;
	private String nickname;
	private Integer watchMinutesOn;
	private Integer watchMinutesOff;
	
	public ConnectedObject (ResultSet result) throws SQLException{
		id = result.getInt(1);
		type = result.getString(2);
		state = result.getBoolean(3);
		apartmentId = result.getInt(4);
		macAddress = result.getString(5);
		nickname = result.getString(6);
		watchMinutesOn=result.getInt(7);
		watchMinutesOff=result.getInt(8);
	}
	
	public ConnectedObject (Integer id) throws SQLException{
		this.id=id;
	}

	public String getTableLine() {
		String line = "<tr>\n";
		line+="<td>" + id + "</td>";
		line+="<td>" + type + "</td>";
		line+="<td>" + ( (state)? "On":"Off") + "</td>";

		// We add a link to change the nickname
		line+="<td><form action='' method='post'><input type='hidden' name='objectId' value='"+id+"'>" +
		"<div class='row'><input type='text' name='nickname' value='" + nickname +"'></div></form></td>";
		
		line+="<td>" + macAddress + "</td>";
		line+= "</tr>\n";
		return line;
	}
	
	public void updateNickNameDB(String newNickname) throws IOException {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			// We retrieve the apartment
			PreparedStatement stmt = connection.prepareStatement("UPDATE Objects SET Nickname_Object=? WHERE ID_Object=?;");
			stmt.setString(1, newNickname);
			stmt.setInt(2, id);
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
