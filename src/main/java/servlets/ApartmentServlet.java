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
public class ApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApartmentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Integer)request.getAttribute("isIdentified") != 2 || request.getPathInfo() == null) {
			// The user must be an administrator identified
			response.sendRedirect("/tv");
			return;
		}
		String pathInfo = request.getPathInfo().substring(1);
		request.setAttribute("apartName", pathInfo);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/apartment.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}
