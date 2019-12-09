package business;

import java.sql.Connection;
import java.sql.PreparedStatement;

import beans.Candidate;
import common.Configuration;

public class CandidateForm {
	Candidate candidate;
	
	
	public boolean executeInscription() {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Candidate(firstname, lastname, email, password) values (?,?,?,?);");
			stmt.setString(1, candidate.getFirstname());
			stmt.setString(2, candidate.getLastname());
			stmt.setString(3, candidate.getEmail());
			stmt.setString(4, candidate.getPassword());
			stmt.executeUpdate();
			Configuration.connectionPool.closeConnection(connection);	
		} catch (Exception e) {
			Configuration.connectionPool.closeConnection(connection);	
			return false;
		}
		return true;
	}
	// test
	

}
