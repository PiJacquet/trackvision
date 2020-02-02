package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Alert;
import beans.ConnectedObject;
import beans.Malfunction;
import business.map.AlertsService;
import common.NotifierHandler;

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
		if((Integer)request.getAttribute("isIdentified") != 2) {
			response.sendRedirect("/tv/connect"); // The user must be an administrator identified
			return;
		}
		Integer apartmentId;
		AlertsService apartmentInfo;
		try {
			apartmentId = Integer.parseInt(request.getPathInfo().substring(1));
			apartmentInfo = new AlertsService(apartmentId);
		} catch (Exception e) {
			NotifierHandler.setNotifierMessage(request, e.getMessage());
			response.sendRedirect("/tv/map"); // Wrong id specified in the URI, we redirect to the map
			return;
		} 

		request.setAttribute("apartmentName", apartmentInfo.getApartment().getName());
		request.setAttribute("apartmentLevel", apartmentInfo.getApartment().getLevel());
		request.setAttribute("apartmentId", apartmentId);
		request.setAttribute("residentsInfo", apartmentInfo.getResidentsInfo());
		request.setAttribute("activeAlerts", apartmentInfo.getActiveAlerts(true));
		request.setAttribute("objects", apartmentInfo.getRelatedObjects());
		request.setAttribute("oldAlerts", apartmentInfo.getOldAlerts());

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/apartment.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("alertId") != null) {
				Integer id = Integer.parseInt(request.getParameter("alertId"));
				Alert alert = new Alert(id);
				alert.disableOnDB();
				NotifierHandler.setNotifierMessage(request, "The alert n°" + id + " was disabled");
			}
			else if(request.getParameter("malfunctionId") != null) {
				Integer id = Integer.parseInt(request.getParameter("malfunctionId"));
				Malfunction malfunction = new Malfunction(id);
				malfunction.disableOnDB();
				NotifierHandler.setNotifierMessage(request, "The malfunction n°" + id + " was disabled");
			}
			else if(request.getParameter("objectId") != null) {
				Integer id = Integer.parseInt(request.getParameter("objectId"));
				ConnectedObject object = new ConnectedObject(id);
				object.updateNickNameDB(request.getParameter("nickname"));
				NotifierHandler.setNotifierMessage(request, "The object n°" + id + " was renamed to '" + request.getParameter("nickname") + "'");
			}
		}catch(Exception e) {
			NotifierHandler.setNotifierMessage(request, "An error occured during the process");
		}
		doGet(request,response);
	}

}
