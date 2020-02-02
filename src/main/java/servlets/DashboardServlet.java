package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Employee;
import business.map.AlertsService;
import common.Configuration;
import common.NotifierHandler;

/**
 * Servlet implementation class Dashboard
 */
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Integer)request.getAttribute("isIdentified") != 2) {
			response.sendRedirect("/tv/connect"); // The user must be an administrator identified
			return;
		}
		try {
			AlertsService alerts = new AlertsService();
			request.setAttribute("name", ((Employee)Configuration.connectedAccounts.getAccount((String)request.getAttribute("hash")).getUser()).getFirstName());
			String activeAlerts = alerts.getActiveAlerts(false);
			if(!activeAlerts.isEmpty()) {
				NotifierHandler.setNotifierMessage(request, "There is currently " + alerts.getNumberOfActiveAlerts() + " alerts or malfunctions!");
				request.setAttribute("alerts", activeAlerts);
			}
			else
				request.setAttribute("alerts", "<i class='gg-check-o'></i> Everything is fine! There is no active alerts or malfunctions");
		}
		catch(Exception e){
			NotifierHandler.setNotifierMessage(request, e.getMessage());
		}
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
