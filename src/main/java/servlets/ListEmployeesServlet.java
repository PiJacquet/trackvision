package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Employee;
import business.EmployeesList;
import common.Configuration;

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
		
		if((Integer)request.getAttribute("isIdentified") != 2) {
			// The user must be an administrator identified
			response.sendRedirect("/tv/connect");
			return;
		}
		
		EmployeesList list = new EmployeesList();
		request.setAttribute("linesTable",list.getEmployeesTableLines());
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/listEmployees.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if((Integer)request.getAttribute("isIdentified") != 2) {
			// The user must be an administrator identified
			response.sendRedirect("/tv");
			return;
		}
		
		EmployeesList list = new EmployeesList();
		
		if(list.deleteEmployee(request.getParameter("id")))
			request.setAttribute("optionalMessage", "The employee was deleted!<br>");
	    else
	    	request.setAttribute("optionalMessage", "A problem occured during the process :(<br>");
		
		request.setAttribute("linesTable",list.getEmployeesTableLines());
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/listEmployees.jsp");
		view.forward(request, response);
	}
	

}
