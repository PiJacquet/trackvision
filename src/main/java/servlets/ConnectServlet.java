package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AccountType;
import business.ConnectAccount;
import common.Configuration;

/**
 * Servlet implementation class addEmployeeServlet
 */
public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if((Integer)request.getAttribute("isIdentified") != 0) {
			// The user is already connected, we redirect him
			response.sendRedirect("/tv");
			return;
		}
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/connect.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		if((Integer)request.getAttribute("isIdentified") != 0) {
			// The user is already connected, we redirect him
			response.sendRedirect("/tv");
			return;
		}
		
		try {
	    	ConnectAccount connect;
	    	
	    	if(request.getParameter("isResident")!=null)
	    		connect = new ConnectAccount(request.getParameter("login"), request.getParameter("password"), AccountType.Resident);
	    	else
	    		connect = new ConnectAccount(request.getParameter("login"), request.getParameter("password"), AccountType.Admin);
	    	
	    	Cookie token = new Cookie("TokenTV", Configuration.connectedAccounts.connectAccount(connect));
	    	token.setMaxAge(365*24*60*60);
	    	token.setPath("/tv");
	    	response.addCookie(token);
	    	
	    	response.sendRedirect("/tv");
			
	    }catch(Exception e) {
	    	request.setAttribute("optionalMessage", e.getMessage());
	    	RequestDispatcher view = request.getRequestDispatcher("WEB-INF/connect.jsp");
			view.forward(request, response);
	    }
	}

}
