package business;

import java.sql.Connection;
import java.sql.PreparedStatement;

import beans.Candidate;
import common.Configuration;

public class CandidateForm {
	
	Candidate candidate;
	
	public CandidateForm(Candidate candidate) {
		this.candidate=candidate;
	}
	
	
	public boolean executeInscription() {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Candidate(civility,age,firstname, lastname, email, password,profil) VALUES (?,?,?,?,?,?,?);");
			stmt.setString(1, candidate.getCivility());
			stmt.setInt(2, candidate.getAge());
			stmt.setString(3, candidate.getFirstname());
			stmt.setString(4, candidate.getLastname());
			stmt.setString(5, candidate.getEmail());
			stmt.setString(6, candidate.getPassword());
			stmt.setString(7, "");
			stmt.executeUpdate();
			System.out.println("cool");
			Configuration.connectionPool.closeConnection(connection);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Configuration.connectionPool.closeConnection(connection);	
			return false;
		}
		return true;
	}
	// test
	

}
