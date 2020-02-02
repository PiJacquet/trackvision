package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends User{
	
	private Integer id;
	private String login;
	private String lastName;
	private String firstName;
	private String function;
	private String number;
	private Integer addresseId;
	
	public Employee(ResultSet result) throws SQLException {
		id = result.getInt(1);
		login = result.getString(2);
		lastName = result.getString(4);
		firstName = result.getString(5);
		number = result.getString(6);
		addresseId = result.getInt(7);
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
	public String getLogin() {
		return login;
	}

	public String getEmployeeTableLine() {
		String action = "<form action='/tv/listEmployees' method='post'><input type='hidden' name='id' value='"+id+"'>" +
				"<div class='row'><input type='submit' value='Delete'></div></form>";
		
		return "<tr><td>" + id + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + login + 
				"</td><td>" + action + "</td></tr>";
	}
	
}
