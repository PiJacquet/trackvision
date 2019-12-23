package servlets;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Alert;
import beans.Malfunction;
import business.map.ResidenceIndicators;

/**
 * Servlet implementation class ListEmployeesServlet
 */
public class IndicatorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndicatorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if((Integer)request.getAttribute("isIdentified") != 2) {
			response.sendRedirect("/tv/connect"); // The user must be an administrator identified
			return;
		}

		ResidenceIndicators indicators;
		try {
			
			indicators = new ResidenceIndicators();
			request.setAttribute("alertByType", indicators.alertByTypeTable());
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/indicator.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("optionalMessage", e.getMessage());
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/indicator.jsp");
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

