package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.AccountType;
import beans.Employee;
import beans.Resident;
import beans.User;
import common.Configuration;

public class ConnectAccount {

	private AccountType account;
	private User user;

	public ConnectAccount(String login, String password, AccountType account) throws Exception {
		this.account = account;
		Connection connection = Configuration.connectionPool.getConnection();
		
		if(account==AccountType.Resident) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Residents  WHERE Login_Resident=? AND Password_Resident=?;");
			ResultSet result = establishConnection(stmt, login, password);
			this.user = new Resident(result);
		}
		else {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Employees WHERE Login_Employee=? AND Password_Employee=?;");
			ResultSet result = establishConnection(stmt, login, password);
			this.user = new Employee(result);
		}

	}

	private ResultSet establishConnection(PreparedStatement statement, String login, String password) throws Exception {
			statement.setString(1,login);
			statement.setString(2,password);
			ResultSet result = statement.executeQuery();
			if(!result.next())
				throw new Exception("Wrong login or password");
			return result;
	}
	
	public AccountType getAccount() {
		return account;
	}

	public User getUser() {
		return user;
	}

}
