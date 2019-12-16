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
import business.map.Map;
import common.Configuration;

/**
 * Servlet implementation class ListEmployeesServlet
 */
public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MapServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if((Integer)request.getAttribute("isIdentified") != 2) {
			// The user must be an administrator identified
			response.sendRedirect("/tv/connect");
			return;
		}

		request.setAttribute("map", new Map());
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/map.jsp");
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer locatedApartId;
		try {
			locatedApartId = Integer.parseInt(request.getParameter("apartmentId"));
		}catch (NumberFormatException nfe) {
			doGet(request,response);
			return;
		}
		request.setAttribute("map", new Map(locatedApartId));
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/map.jsp");
		view.forward(request, response);

	}

}
