package business;

import java.sql.Connection;
import java.sql.PreparedStatement;

import common.Configuration;

public class ResidentForm {

	private String firstname;
	private String lastname;
	private String email;

	public ResidentForm(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public boolean executeInscription() {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Residents(Login_Resident, Password_Resident, Lastname_Resident, Firstname_Resident, Age_Resident, Information_Resident, ID_Apartment) values (?,?,?,?,?,?,?);");
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.setString(3, email); 
			//TODO
			stmt.executeUpdate();
			Configuration.connectionPool.closeConnection(connection);	
		} catch (Exception e) {
			Configuration.connectionPool.closeConnection(connection);	
			return false;
		}
		return true;
	}


}
