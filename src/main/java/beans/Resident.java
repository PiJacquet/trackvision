package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Resident extends User {
	
	private Integer id;
	private String login;
	private String lastName;
	private String firstName;
	
	public Resident(ResultSet result) throws SQLException {
		id = result.getInt(1);
		login = result.getString(2);
		lastName = result.getString(4);
		firstName = result.getString(5);
	}
	

}
