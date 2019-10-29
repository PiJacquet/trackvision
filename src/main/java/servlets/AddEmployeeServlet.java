package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Configuration;

/**
 * Servlet implementation class addEmployeeServlet
 */
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/addEmployee.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    Connection connection = Configuration.connectionPool.getConnection();
	    Boolean error = false;
	    try {
	    	PreparedStatement stmt = connection.prepareStatement("INSERT INTO Employees(first_name, last_name, email_address) values(?,?,?);");
		    stmt.setString(1, request.getParameter("firstname"));
		    stmt.setString(2, request.getParameter("lastname"));
		    stmt.setString(3, request.getParameter("email")); 
			stmt.executeUpdate();
		} catch (Exception e) {
			error = true;
		}finally {
			Configuration.connectionPool.closeConnection(connection);	
		}
	    
	    if(error)
	    	request.setAttribute("optionalMessage", "A problem occured during the process :(");
	    else
	    	request.setAttribute("optionalMessage", "The employee was added!");
	    
		doGet(request, response);
	}

}
