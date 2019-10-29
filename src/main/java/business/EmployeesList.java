package business;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Employee;
import common.Configuration;

public class EmployeesList {

	private ArrayList<Employee> employees;

	public EmployeesList() throws IOException {
		employees = new ArrayList<Employee>();
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "SELECT * FROM Employees";
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			while(result.next()) {
				employees.add(new Employee(result.getInt(1),result.getString(2),result.getString(3),result.getString(4)));
			}
			Configuration.connectionPool.closeConnection(connection);
		}
		catch(Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			throw new IOException("An error occured while getting the Employees list : " + e.getMessage());
		}
	}
	
	public boolean deleteEmployee(String id) {
		
		Connection connection = Configuration.connectionPool.getConnection();
		
	    try {
	    	PreparedStatement stmt = connection.prepareStatement("DELETE FROM Employees WHERE id=?;");
	    	stmt.setString(1, id);
			stmt.executeUpdate();
			Configuration.connectionPool.closeConnection(connection);
		} catch (Exception e) {
			Configuration.connectionPool.closeConnection(connection);
			return false;
		}
	    
	    Integer intId = Integer.parseInt(id);
	    // We still have to delete the employee from the list
	    for(Employee employee : employees)
	    	if(employee.getId()==intId) {
	    		employees.remove(employee);
	    		return true;
	    	}
	    return false;
	}
	
	public String getEmployeesTableLines() {
		String tableLines = "";
		for(Employee employee : employees)
			tableLines+= employee.getEmployeeTableLine() + "\n";
		return tableLines;
	}

}
