package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	public Employee(ResultSet result) throws SQLException {
		id = result.getInt(1);
		firstName = result.getString(2);
		lastName = result.getString(3);
		emailAddress = result.getString(4);
	}
	
	public Integer getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getEmployeeTableLine() {
		String action = "<form action='/tv/listEmployees' method='post'><input type='hidden' name='id' value='"+id+"'>" +
				"<div class='row'><input type='submit' value='Delete'></div></form>";
		
		return "<tr><td>" + id + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + emailAddress + 
				"</td><td>" + action + "</td></tr>";
	}
}
