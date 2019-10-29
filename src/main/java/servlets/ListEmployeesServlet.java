package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Configuration;
import common.Employee;

/**
 * Servlet implementation class ListEmployeesServlet
 */
public class ListEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListEmployeesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		Connection connection = Configuration.connectionPool.getConnection();
		String requestSQL = "SELECT * FROM Employees";
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(requestSQL);
			while(result.next()) {
				employees.add(new Employee(result.getInt(1),result.getString(2),result.getString(3),result.getString(4)));
			}
		}
		catch(Exception e) {System.out.println("Error : " + e.getMessage());}
		Configuration.connectionPool.closeConnection(connection);
		String linesTable = "";
		for(Employee employee : employees)
			linesTable+= employee + "\n";
		request.setAttribute("linesTable",linesTable);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/listEmployees.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	    Connection connection = Configuration.connectionPool.getConnection();
	    Boolean error = false;
	    try {
	    	PreparedStatement stmt = connection.prepareStatement("DELETE FROM Employees WHERE id=?;");
	    	stmt.setString(1, request.getParameter("id"));
			stmt.executeUpdate();
		} catch (Exception e) {
			error = true;
		}finally {
			Configuration.connectionPool.closeConnection(connection);	
		}
	    
	    if(error)
	    	request.setAttribute("optionalMessage", "A problem occured during the process :(<br>");
	    else
	    	request.setAttribute("optionalMessage", "The employee was deleted!<br>");
		
		doGet(request, response);
	}

}
