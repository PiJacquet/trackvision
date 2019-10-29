package business;

import java.sql.Connection;
import java.sql.PreparedStatement;

import common.Configuration;

public class EmployeeForm {

	private String firstname;
	private String lastname;
	private String email;

	public EmployeeForm(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public boolean executeInscription() {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO Employees(first_name, last_name, email_address) values(?,?,?);");
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.setString(3, email); 
			stmt.executeUpdate();
			Configuration.connectionPool.closeConnection(connection);	
		} catch (Exception e) {
			Configuration.connectionPool.closeConnection(connection);	
			return false;
		}
		return true;
	}


}
